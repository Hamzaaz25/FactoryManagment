package Model;


import Repository.ProductLineRepository;
import Repository.TaskRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collectors;

public class ProductLineManager {

    private final HashMap<ProductLine, ProductLineService> lines = new HashMap<>();
    private final ProductLineRepository productLineRepository;
    private final TaskService taskService;

    public ProductLineManager(ProductLineRepository repo, TaskService taskService ) {
        this.productLineRepository = repo;
        this.taskService = taskService;
    }

    public void register(ProductLine line) {
        ProductLineService service =
                new ProductLineService(productLineRepository, line, taskService);

        lines.put(line, service);

    }

    public ProductLineService getService(ProductLine productName) {
        return lines.get(productName);
    }

    public void addProductLine(String name ){
        ProductLine pl = new ProductLine(name);
        productLineRepository.insert(pl);
        register(pl);

    }

    public ArrayList<ProductLine> filterByProduct(TaskRepository taskRepository, String product) {

        HashSet<ProductLine> setOfProductLines = new HashSet<>();

        for (Task t : taskRepository.getListOfTasks()) {
            if (t.getRequestedProduct().equalsIgnoreCase(product)) {
                setOfProductLines.add(
                        productLineRepository.getProductLineByNumber(t.getProductLine())
                );
            }
        }

        return new ArrayList<>(setOfProductLines);
    }


}
