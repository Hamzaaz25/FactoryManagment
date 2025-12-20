import java.util.Date;

public class Task {
    private int taskNumber;
    private String requestedProduct;
    private int requestedQuantity;
    private String clientName;
    private Date startDate = new Date();
    private Date endDate = new Date();
    private TaskStatus status;
    private int productLine ;
    private int progressPercentage ;


    public Task(int taskNumber, String requestedProduct, int requestedQuantity, String clientName, Date startDate, Date endDate, TaskStatus status, int productLine, int progressPercentage) {
        this.taskNumber = taskNumber;
        this.requestedProduct = requestedProduct;
        this.requestedQuantity = requestedQuantity;
        this.clientName = clientName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.productLine = productLine;
        this.progressPercentage = progressPercentage;
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public int getProgressPercentage() {
        return progressPercentage;
    }

    public void setProgressPercentage(int progressPercentage) {
        this.progressPercentage = progressPercentage;
    }

    public int getProductLine() {
        return productLine;
    }

    public void setProductLine(int productLine) {
        this.productLine = productLine;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public int getRequestedQuantity() {
        return requestedQuantity;
    }

    public void setRequestedQuantity(int requestedQuantity) {
        this.requestedQuantity = requestedQuantity;
    }

    public String getRequestedProduct() {
        return requestedProduct;
    }

    public void setRequestedProduct(String requestedProduct) {
        this.requestedProduct = requestedProduct;
    }
}
