package integrify.inventory.domain.model;

public class SalesData {
    private String dateRange;
    private int totalSales;

    public SalesData(String dateRange, int totalSales) {
        this.dateRange = dateRange;
        this.totalSales = totalSales;
    }

    public String getDateRange() {
        return dateRange;
    }

    public void setDateRange(String dateRange) {
        this.dateRange = dateRange;
    }

    public int getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(int totalSales) {
        this.totalSales = totalSales;
    }
}