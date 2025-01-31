package integrify.inventory.application.services.order;

import integrify.inventory.application.dtos.order.OrderCreateDto;
import integrify.inventory.application.dtos.order.OrderMapper;
import integrify.inventory.application.dtos.order.OrderReadDto;
import integrify.inventory.application.dtos.orderItem.OrderItemCreateDto;
import integrify.inventory.application.dtos.orderItem.OrderItemMapper;

import integrify.inventory.application.shared.OffsetPage;
import integrify.inventory.application.shared.PaginationPage;
import integrify.inventory.domain.OrderStatusEnum;
import integrify.inventory.domain.model.Order;
import integrify.inventory.domain.model.OrderItem;
import integrify.inventory.domain.model.Stock;
import integrify.inventory.domain.repository.IOrderItemRepo;
import integrify.inventory.domain.repository.IOrderRepo;

import integrify.inventory.domain.repository.IStockRepo;
import integrify.inventory.infrastructure.email.EmailService;
import integrify.inventory.infrastructure.email.EmailServiceEnum;
import integrify.inventory.presentation.errorHandlers.OutOfStockException;
import integrify.inventory.presentation.errorHandlers.ResourceNotFound;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;
import jakarta.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private IOrderRepo _orderRepo;
    @Autowired
    private IOrderItemRepo _orderItemRepo;
    @Autowired
    private IStockRepo _stockRepo;

    @Autowired
    OrderMapper _orderMapper;

    @Autowired
    OrderItemMapper _orderItemMapper;

    @Autowired
    private Validator validator;

    @Autowired
    private EmailService _emailService;

    @Override
    @Transactional
    public OrderReadDto createOrder(OrderCreateDto orderCreateDto) {
        // Validate input data
        Set<ConstraintViolation<OrderCreateDto>> violations = validator.validate(orderCreateDto);
        if (!violations.isEmpty()) {
            // Handle validation errors by throwing an exception
            throw new ValidationException("Validation failed: " + violations.toString());
        }

        Order order = _orderMapper.toOrder(orderCreateDto);
        order.setOrderDate(LocalDate.now());
        // Create order items
        List<OrderItemCreateDto> orderItemCreateDtos = orderCreateDto.getOrderItemCreateDtoList();

        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemCreateDto orderItemCreateDto : orderItemCreateDtos) {

            OrderItem orderItem = _orderItemMapper.toOrderItem(orderItemCreateDto);
            orderItem.setOrder(order);

            //  Checking supplier if supplier has this product
            List<Stock> stocks = _stockRepo.findAllBySupplierId(orderCreateDto.getSupplierId());

            if(stocks.isEmpty()){
                throw new ResourceNotFound("Supplier with ID: " + orderCreateDto.getSupplierId() + " not found in inventory.");
            }

            boolean isAvailableItem = false;
            for (Stock stock : stocks){

                if(stock.getProductId().equals(orderItem.getProductId())) {

                    if(stock.getQuantity() < orderItem.getQuantity()){
                        throw new OutOfStockException("Quantity of Product with ID: " + orderItem.getProductId() + " can not greater than " +  stock.getQuantity());
                    }

                    isAvailableItem = true;
                    break;
                }
            }

            //  Checking stock if product is available
            if(!isAvailableItem){
               throw new ResourceNotFound("Supplier with ID " + orderCreateDto.getSupplierId()  + " does not contain product with ID: " + orderItem.getProductId());
            }

            orderItems.add(orderItem);
        }

        order.setOrderItems(orderItems);

        // Save the order and order items to the database
        _orderRepo.save(order);
        _orderItemRepo.saveAll(orderItems);

        // Update stock after making an order
        for (OrderItem orderItem : order.getOrderItems()){
            Stock stock = _stockRepo.findByProductId(orderItem.getProductId())
                    .orElseThrow(() -> new ResourceNotFound("OrderItem with ID: " + orderItem.getId() + " not found in inventory."));
            stock.setQuantity(stock.getQuantity() - orderItem.getQuantity());

        //  Sending notification for supplier when a stock is < 2
            if(stock.getQuantity() < 2){
                try {
                    _emailService.sendHtmlEmail(EmailServiceEnum.LOWSTOCK);
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            }
            _stockRepo.save(stock);
        }

        return _orderMapper.toOrderReadDto(order);
    }

    @Override
    public OrderReadDto updateOrderStatus(UUID id, OrderStatusEnum status) {
        Order order = _orderRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Order not found with ID: " + id));
        order.setStatus(status);

        _orderRepo.save(order);
        return _orderMapper.toOrderReadDto(order);
    }

    @Override
    public PaginationPage<OrderReadDto> getAllOrders(int limit, int offset) {
        OffsetPage pageable = new OffsetPage(limit, offset);

        Page<Order> orders = _orderRepo.findAll(pageable);

        List<OrderReadDto> orderReadDtos = orders.stream().map(order ->
             _orderMapper.toOrderReadDto(order)
        ).collect(Collectors.toList());


        return new PaginationPage<OrderReadDto>()
                .setLimit(orders.getSize())
                .setTotalRecords(orders.getTotalElements())
                .setOffset(offset)
                .setRecords(orderReadDtos);
    }

    @Override
    public OrderReadDto getOrderById(UUID id) {
        Order order = _orderRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Order not found with ID: " + id));

        return _orderMapper.toOrderReadDto(order);
    }

    @Override
    public void cancelOrder(UUID id) {
        Order order = _orderRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Order not found with ID: " + id));

        for (OrderItem orderItem : order.getOrderItems()){
            _orderItemRepo.deleteById(orderItem.getId());
        }

        // Delete in Database
        _orderRepo.deleteById(id);
    }

    @Override
    public List<OrderReadDto> getOrdersByCurrentDay() {

        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(1);

        // Retrieve orders using the startDate and endDate
        List<Order> orders = _orderRepo.findAllByDateRange(startDate, endDate);
        List<OrderReadDto> orderReadDtoList = new ArrayList<>();
        for (Order order : orders){
            orderReadDtoList.add(_orderMapper.toOrderReadDto(order));
        }

        return orderReadDtoList;
    }

    @Override
    public List<OrderReadDto> getOrdersByCurrentWeek() {
        LocalDate currentDate = LocalDate.now();
        LocalDate startDate = currentDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate endDate = currentDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)).plusDays(1);

        // Retrieve orders using the startDate and endDate
        List<Order> orders = _orderRepo.findAllByDateRange(startDate, endDate);
        List<OrderReadDto> orderReadDtoList = new ArrayList<>();
        for (Order order : orders){
            orderReadDtoList.add(_orderMapper.toOrderReadDto(order));
        }

        return orderReadDtoList;
    }

    @Override
    public List<OrderReadDto> getOrdersByCurrentMonth() {
        LocalDate currentDate = LocalDate.now();
        LocalDate startDate = currentDate.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate endDate = currentDate.with(TemporalAdjusters.firstDayOfNextMonth());

        // Retrieve orders using the startDate and endDate
        List<Order> orders = _orderRepo.findAllByDateRange(startDate, endDate);
        List<OrderReadDto> orderReadDtoList = new ArrayList<>();
        for (Order order : orders){
            orderReadDtoList.add(_orderMapper.toOrderReadDto(order));
        }

        return orderReadDtoList;
    }
}

