package Model;

import Repository.ProductRepository;
import Repository.TaskRepository;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ProductService {

    private final ProductRepository productRepository;
    private final TaskRepository taskRepository;

    public ProductService(ProductRepository productRepository, TaskRepository taskRepository) {
        this.productRepository = productRepository;
        this.taskRepository = taskRepository;
    }

    public Product getMostTaskedProduct(LocalDate start, LocalDate end) {
        ArrayList<Task> tasks = taskRepository.getListOfTasks();
        HashMap<String, Integer> taskCount = new HashMap<>();

        for (Task task : tasks) {

            // Include any task that overlaps with the period
            if (!(task.getEndDate().isBefore(start) || task.getStartDate().isAfter(end))) {
                String productName = task.getRequestedProduct();
                taskCount.merge(productName, 1, Integer::sum);
            }
        }

        // Find product name with the highest count
        String mostTaskedName = null;
        int maxCount = 0;
        for (HashMap.Entry<String, Integer> entry : taskCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostTaskedName = entry.getKey();
            }
        }

        // Return the actual Product object
        if (mostTaskedName == null) return null;
        return productRepository.getByName(mostTaskedName);
    }


    public ArrayList<Task> getTasksInPeriod(LocalDate start, LocalDate end) {
        ArrayList<Task> tasks = taskRepository.getListOfTasks();
        ArrayList<Task> result = new ArrayList<>();

        for (Task task : tasks) {
            if (!(task.getEndDate().isBefore(start) || task.getStartDate().isAfter(end))) {
                result.add(task);
            }
        }

        return result;
    }

    public ArrayList<Product> getProductByProductLine(int pl) {
        HashSet<Product> products = new HashSet<>();
        for (Task t : taskRepository.getListOfTasks()) {
            if (t.getProductLine() == pl) {
                Product p = productRepository.getByName(t.getRequestedProduct());
                if (p != null) {
                    products.add(p);
                }
            }
        }

        return new ArrayList<>(products);
    }

}
