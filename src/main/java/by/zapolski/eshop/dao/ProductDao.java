package by.zapolski.eshop.dao;

import by.zapolski.eshop.dao.exception.DaoSystemException;
import by.zapolski.eshop.dao.exception.NoSuchEntityException;
import by.zapolski.eshop.model.Product;

import java.util.List;

// CRUD operations: create, read, update, delete
public interface ProductDao {

    //never return null! throws NoSuchEntityException
    public Product selectById (int id) throws DaoSystemException, NoSuchEntityException;

    public List<Product> selectAll() throws DaoSystemException;
}
