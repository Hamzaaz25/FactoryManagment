package Model;


import Repository.ProductLineRepository;

import java.util.HashMap;

public class ProductLineManager {

    private final HashMap<ProductLine, ProductLineService> lines = new HashMap<>();
    private final ProductLineRepository productLineRepository;
    private final TaskService taskService;

    public ProductLineManager(ProductLineRepository repo, TaskService taskService) {
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
        this.productLineRepository.insert(pl);
        register(pl);
    }

}
