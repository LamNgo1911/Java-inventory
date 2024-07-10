package integrify.inventory.presentation.controllers;

import integrify.inventory.application.dtos.order.OrderCreateDto;
import integrify.inventory.application.dtos.order.OrderReadDto;
import integrify.inventory.application.services.order.OrderService;
import integrify.inventory.application.shared.PaginationPage;
import integrify.inventory.domain.OrderStatusEnum;
import integrify.inventory.presentation.shared.SuccessResponseEntity;
import jakarta.validation.Valid;
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

    @Autowired
    private OrderService _orderService;



    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<OrderReadDto> createOrder(@RequestBody @Valid OrderCreateDto orderCreateDto) {
        OrderReadDto orderReadDto = _orderService.createOrder(orderCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderReadDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/status")
    public ResponseEntity<OrderReadDto> updateOrderStatus(@PathVariable UUID id, @RequestBody Map<String, String> requestBody) {
        String status = requestBody.get("status");
        OrderStatusEnum orderStatus = OrderStatusEnum.valueOf(status);
        OrderReadDto orderReadDto = _orderService.updateOrderStatus(id, orderStatus);
        return ResponseEntity.status(HttpStatus.OK).body(orderReadDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<SuccessResponseEntity<OrderReadDto>> getAllOrders(
            @RequestParam(defaultValue = "5") int limit, @RequestParam(defaultValue = "0") int offset) {
        PaginationPage<OrderReadDto> orders = _orderService.getAllOrders(limit, offset);
        List<OrderReadDto> ordersList = new ArrayList<>(orders.getRecords());
        SuccessResponseEntity<OrderReadDto> successResponseEntity = new SuccessResponseEntity<>();
        successResponseEntity.setData(ordersList);
        return ResponseEntity.ok(successResponseEntity);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<OrderReadDto> getOrderById(@PathVariable UUID id) {
        OrderReadDto orderReadDto = _orderService.getOrderById(id);
        return ResponseEntity.ok(orderReadDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelOrder(@PathVariable UUID id) {
        _orderService.cancelOrder(id);

        return ResponseEntity.noContent().build();
    }
}
