package service;

import dao.ProductDao;
import entity.Product;

import java.util.List;
import java.util.Optional;

public class ProductService {
    private static ProductService INSTANCE = null;
    private static Object LOCK = new Object();

    private ProductService() {}

    public static ProductService getInstance() {
        if(INSTANCE == null) {
            synchronized (LOCK) {
                if(INSTANCE == null) {
                    INSTANCE = new ProductService();
                }
            }
        }
        return INSTANCE;
    }

    public Optional<Product> save(Product product) {
        return ProductDao.getInstance().save(product);
    }

    public Optional<Product> getByID(long id) {
        return ProductDao.getInstance().getByID(id);
    }

    public List<Product> getAll() {
        return ProductDao.getInstance().getAll();
    }

    public void addExistingProduct(long productID, int amount){
        ProductDao.getInstance().addExistingProduct(productID, amount);
    }
}
