package Model;

import Enums.TaskStatus;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicBoolean;

public class Task {
    private volatile int taskNumber;
    private String requestedProduct;
    private int requestedQuantity;
    private String clientName;
    private LocalDate startDate ;
    private LocalDate endDate ;
    private TaskStatus status;
    private int productLine ;
    private int progressPercentage ;
    private volatile boolean  valid;
    private AtomicBoolean working = new AtomicBoolean(false);
    static int count = 100;


    public Task( String requestedProduct, int requestedQuantity, String clientName, LocalDate startDate, LocalDate endDate, TaskStatus status, int productLine, int progressPercentage) {
        this.taskNumber = ++count;
        this.requestedProduct = requestedProduct;
        this.requestedQuantity = requestedQuantity;
        this.clientName = clientName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.productLine = productLine;
        this.progressPercentage = progressPercentage;
    }

    public Task( String requestedProduct, int requestedQuantity, String clientName, int productLine , TaskStatus s ) {
        this.taskNumber = ++count;
        this.requestedProduct = requestedProduct;
        this.requestedQuantity = requestedQuantity;
        this.clientName = clientName;
        this.productLine = productLine;
        this.status = s;
        this.startDate = LocalDate.now();
        this.endDate = LocalDate.now();
        this.progressPercentage =0;
    }

    public Task(int taskNumber) {
        this.taskNumber = taskNumber;
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

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
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

    public synchronized boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public AtomicBoolean isWorking() {
        return working;
    }

    public void setWorking(boolean working) {

        this.working.set(working);
    }

    public static void decrementCount(){
        count--;
    }
}
