package integrify.inventory.presentation.controllers;


import com.opencsv.CSVWriter;
import integrify.inventory.application.dtos.order.OrderReadDto;
import integrify.inventory.application.services.order.OrderService;
import integrify.inventory.domain.model.SalesData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/v1/reports")
public class ReportController {

    @Autowired
    private OrderService _orderService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/sales")
    public ResponseEntity<StreamingResponseBody> generateDailySalesReport() {
        List<OrderReadDto> orderReadDtoListCurrentDay = _orderService.getOrdersByCurrentDay();
        List<OrderReadDto> orderReadDtoListCurrentWeek = _orderService.getOrdersByCurrentWeek();
        List<OrderReadDto> orderReadDtoListCurrentMonth = _orderService.getOrdersByCurrentMonth();

        int totalDaySales = calculateTotalSales(orderReadDtoListCurrentDay);
        int totalWeekSales = calculateTotalSales(orderReadDtoListCurrentWeek);
        int totalMonthSales = calculateTotalSales(orderReadDtoListCurrentMonth);

        List<SalesData> dataList = new ArrayList<>();
        dataList.add(new SalesData(getDayRange(), totalDaySales));
        dataList.add(new SalesData(getWeekRange(), totalWeekSales));
        dataList.add(new SalesData(getMonthRange(), totalMonthSales));

        String filename = "sales_report.csv";

        StreamingResponseBody stream = out -> {
            try (CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(out, StandardCharsets.UTF_8))) {
                csvWriter.writeNext(new String[]{"", "Date", "Total Sales"}); // Header row
                csvWriter.writeNext(new String[]{});
                csvWriter.writeNext(new String[]{"Daily", getDayRange(), String.valueOf(totalDaySales)});
                csvWriter.writeNext(new String[]{});
                csvWriter.writeNext(new String[]{"Weekly", getWeekRange(), String.valueOf(totalWeekSales)});
                csvWriter.writeNext(new String[]{});
                csvWriter.writeNext(new String[]{"Monthly", getMonthRange(), String.valueOf(totalMonthSales)});
            } catch (IOException e) {
                throw new RuntimeException("Failed to generate CSV report.", e);
            }
        };

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("text/csv; charset=UTF-8"));
        headers.set(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=%s", filename));

        return ResponseEntity.ok()
                .headers(headers)
                .body(stream);
    }

    private int calculateTotalSales(List<OrderReadDto> orderReadDtoList) {
        int totalSales = 0;
        for (OrderReadDto orderReadDto : orderReadDtoList) {
            totalSales += orderReadDto.getOrderItemReadDtoList().size();
        }
        return totalSales;
    }

    private String getDayRange(){
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return currentDate.format(formatter);
    }

    private String getWeekRange(){
        LocalDate currentDate = LocalDate.now();
        int year = currentDate.getYear();
        int weekNumber = currentDate.get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear());

        return year + "-W" + weekNumber;
    }

    private String getMonthRange(){
        LocalDate currentDate = LocalDate.now();
        int year = currentDate.getYear();
        int month = currentDate.getMonthValue();

        return year + "-M" + month;
    }
}
