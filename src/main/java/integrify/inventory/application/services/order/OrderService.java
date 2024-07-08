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
import integrify.inventory.presentation.errorHandlers.OutOfStockException;
import integrify.inventory.presentation.errorHandlers.ResourceNotFound;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;
import jakarta.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private IOrderRepo _orderRepo;
    private IOrderItemRepo _orderItemRepo;
    private IStockRepo _stockRepo;

    @Autowired
    OrderMapper _orderMapper;

    @Autowired
    OrderItemMapper _orderItemMapper;

    private Validator validator;

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

        // Create order items
        Set<OrderItemCreateDto> orderItemCreateDtos = orderCreateDto.getOrderItems();
        Set<OrderItem> orderItems = new HashSet<>();

        for (OrderItemCreateDto orderItemCreateDto : orderItemCreateDtos) {
            OrderItem orderItem = _orderItemMapper.toOrderItem(orderItemCreateDto);
            orderItem.setOrder(order);

            Stock stock = _stockRepo.findByProductId(orderItem.getProductId()).orElseThrow(() -> new ResourceNotFound("OrderItem with ID: " + orderItem.getId() + " not found in inventory."));

            if(stock.getQuantity() < orderItem.getQuantity()){
                throw new OutOfStockException("Quantity of orderItem with ID: " + orderItem.getId() + " can not greater than " +  stock.getQuantity());
            }

            orderItems.add(orderItem);
        }

        order.setOrderItems(orderItems);

        // Save the order and order items to the database
        _orderRepo.save(order);
        _orderItemRepo.saveAll(orderItems);

        return _orderMapper.toOrderReadDto(order);
    }

    @Override
    public OrderReadDto updateOrderStatus(Long id, OrderStatusEnum status) {
        Order order = _orderRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Order not found with ID: " + id));
        order.setStatus(status);

        _orderRepo.save(order);
        return _orderMapper.toOrderReadDto(order);
    }

    @Override
    public PaginationPage<OrderReadDto> getAllOrders(int limit, int offset) {
        OffsetPage pageable = new OffsetPage(limit, offset);

        Page<Order> orders = _orderRepo.findAll(pageable);

        List<OrderReadDto> orderReadDtos = orders.stream().map(order -> _orderMapper.toOrderReadDto(order)).collect(Collectors.toList());

        return new PaginationPage<OrderReadDto>()
                .setLimit(orders.getSize())
                .setTotalRecords(orders.getTotalElements())
                .setOffset(offset)
                .setRecords(orderReadDtos);
    }

    @Override
    public OrderReadDto getOrderById(Long id) {
        Order order = _orderRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Order not found with ID: " + id));

        return _orderMapper.toOrderReadDto(order);
    }

    @Override
    public void cancelOrder(Long id) {
        Order order = _orderRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Order not found with ID: " + id));

        // Delete in Database
        _orderRepo.deleteById(id);
    }
}

