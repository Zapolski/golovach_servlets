package by.zapolski.eshop.dao.impl;

import com.sun.tools.internal.ws.wsdl.framework.NoSuchEntityException;
import by.zapolski.eshop.dao.ProductDao;
import by.zapolski.eshop.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ProductDaoMock implements ProductDao {
    private final Map<Integer, Product> memory = new ConcurrentHashMap<Integer, Product>();

    public ProductDaoMock(){
        this.memory.put(1,new Product(1,"Bread"));
        this.memory.put(2,new Product(2,"Paper"));
        this.memory.put(3,new Product(3,"Suger"));
    }

    public Product selectById(int id) throws NoSuchEntityException {
        if (!memory.containsKey(id)){
            throw new NoSuchEntityException("No Product for id === '"+id+"', only");
        }
        return memory.get(id);
    }

    public List<Product> selectAll()  {
        return new ArrayList<Product>(memory.values());
    }
}
