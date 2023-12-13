package com.jtspringproject.JtSpringProject.services;

import com.jtspringproject.JtSpringProject.dao.productDao;
import com.jtspringproject.JtSpringProject.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервіс, який надає функціонал для роботи з товарами.
 */
@Service
public class productService {
    @Autowired
    private productDao productDao;

    /**
     * Отримує список усіх товарів.
     *
     * @return Список товарів.
     */
    public List<Product> getProducts() {
        return this.productDao.getProducts();
    }

    /**
     * Додає новий товар.
     *
     * @param product Об'єкт Product, який представляє товар.
     * @return Об'єкт Product після додавання.
     */
    public Product addProduct(Product product) {
        return this.productDao.addProduct(product);
    }

    /**
     * Отримує товар за заданим ідентифікатором.
     *
     * @param id Ідентифікатор товару.
     * @return Об'єкт Product, який представляє товар.
     */
    public Product getProduct(int id) {
        return this.productDao.getProduct(id);
    }

    /**
     * Оновлює інформацію про товар.
     *
     * @param id          Ідентифікатор товару.
     * @param name        Нова назва товару.
     * @param image       Нове зображення товару.
     * @param quantity    Нова кількість товару.
     * @param price       Нова ціна товару.
     * @param weight      Нова вага товару.
     * @param description Новий опис товару.
     * @return Об'єкт Product після оновлення.
     */
    public Product updateProduct(int id, String name, String image, int quantity, int price, int weight, String description) {
        return this.productDao.updateProduct(id, name, image, quantity, price, weight, description);
    }

    /**
     * Видаляє товар за заданим ідентифікатором.
     *
     * @param id Ідентифікатор товару.
     * @return true, якщо товар видалено успішно; false, якщо товар не існує.
     */
    public boolean deleteProduct(int id) {
        return this.productDao.deleteProduct(id);
    }
}
