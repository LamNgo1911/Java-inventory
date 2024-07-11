package integrify.inventory.presentation.controllers;

import integrify.inventory.application.dtos.order.OrderCreateDto;
import integrify.inventory.application.dtos.order.OrderReadDto;
import integrify.inventory.application.services.order.OrderService;
import integrify.inventory.application.shared.PaginationPage;
import integrify.inventory.domain.OrderStatusEnum;
import integrify.inventory.infrastructure.email.EmailService;
import integrify.inventory.infrastructure.email.EmailServiceEnum;
import integrify.inventory.presentation.shared.SuccessResponseEntity;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService _orderService;

    @Autowired
    private EmailService _emailService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<OrderReadDto> createOrder(@RequestBody @Valid OrderCreateDto orderCreateDto) {
        logger.info("Creating a new order...");

        OrderReadDto orderReadDto = _orderService.createOrder(orderCreateDto);

        logger.info("New order created with ID: {}", orderReadDto.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(orderReadDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/status")
    public ResponseEntity<OrderReadDto> updateOrderStatus(@PathVariable UUID id, @RequestBody Map<String, String> requestBody) throws MessagingException {
        logger.info("Updating order status for ID: {}", id);

        String status = requestBody.get("status");
        OrderStatusEnum orderStatus = OrderStatusEnum.valueOf(status);
        OrderReadDto orderReadDto = _orderService.updateOrderStatus(id, orderStatus);

        // Sending an email to the customer when an order is shipped
        if (orderReadDto.getStatus().equals(OrderStatusEnum.SHIPPED)) {
            _emailService.sendHtmlEmail(EmailServiceEnum.UPDATEORDERSTATUS);
        }

        logger.info("Order status updated for ID: {}", id);

        return ResponseEntity.status(HttpStatus.OK).body(orderReadDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<SuccessResponseEntity<OrderReadDto>> getAllOrders(
            @RequestParam(defaultValue = "5") int limit, @RequestParam(defaultValue = "0") int offset) {
        logger.info("Retrieving all orders...");

        PaginationPage<OrderReadDto> orders = _orderService.getAllOrders(limit, offset);
        List<OrderReadDto> ordersList = new ArrayList<>(orders.getRecords());
        SuccessResponseEntity<OrderReadDto> successResponseEntity = new SuccessResponseEntity<>();
        successResponseEntity.setData(ordersList);

        logger.info("Retrieved {} orders", ordersList.size());

        return ResponseEntity.ok(successResponseEntity);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<OrderReadDto> getOrderById(@PathVariable UUID id) {
        logger.info("Retrieving order with ID: {}", id);

        OrderReadDto orderReadDto = _orderService.getOrderById(id);

        logger.info("Retrieved order with ID: {}", id);

        return ResponseEntity.ok(orderReadDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelOrder(@PathVariable UUID id) {
        logger.info("Canceling order with ID: {}", id);

        _orderService.cancelOrder(id);

        logger.info("Order with ID {} canceled", id);

        return ResponseEntity.noContent().build();
    }
}