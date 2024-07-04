package integrify.inventory.presentation.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class test {
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public String getAdminDashboard() {
        return "Admin Dashboard";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/public")
    public String getPublicEndpoint() {
        return "Public Endpoint";
    }
}
