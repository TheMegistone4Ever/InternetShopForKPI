package com.petspringproject.brigade33.dao;

import com.petspringproject.brigade33.models.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Data Access Object (DAO) для роботи з сутністю "Товар" (Product) у базі даних.
 */
@Repository
public class productDao {
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Встановлює об'єкт SessionFactory для роботи з сесіями Hibernate.
     *
     * @param sf Об'єкт SessionFactory.
     */
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    /**
     * Отримує список всіх товарів з бази даних.
     *
     * @return Список об'єктів Product.
     */
    @Transactional
    public List<Product> getProducts() {
        return this.sessionFactory.getCurrentSession().createQuery("from PRODUCT").list();
    }

    /**
     * Додає новий товар у базу даних.
     *
     * @param product Об'єкт Product для додавання.
     * @return Створений об'єкт Product.
     */
    @Transactional
    public Product addProduct(Product product) {
        this.sessionFactory.getCurrentSession().save(product);
        return product;
    }

    /**
     * Отримує товар з бази даних за його ідентифікатором.
     *
     * @param id Ідентифікатор товару.
     * @return Об'єкт Product.
     */
    @Transactional
    public Product getProduct(int id) {
        return this.sessionFactory.getCurrentSession().get(Product.class, id);
    }

    /**
     * Оновлює інформацію про товар у базі даних за його ідентифікатором.
     *
     * @param id          Ідентифікатор товару.
     * @param name        Нова назва товару.
     * @param image       Новий шлях до зображення товару.
     * @param quantity    Нова кількість товару.
     * @param price       Нова ціна товару.
     * @param weight      Нова вага товару.
     * @param description Новий опис товару.
     * @return Оновлений об'єкт Product.
     */
    @Transactional
    public Product updateProduct(int id, String name, String image, int quantity, int price, int weight, String description) {
        Product product = this.sessionFactory.getCurrentSession().get(Product.class, id);
        product.setName(name);
        product.setImage(image);
        product.setQuantity(quantity);
        product.setPrice(price);
        product.setWeight(weight);
        product.setDescription(description);

        this.sessionFactory.getCurrentSession().update(product);
        return product;
    }

    /**
     * Видаляє товар з бази даних за його ідентифікатором.
     *
     * @param id Ідентифікатор товару.
     * @return Логічне значення, чи був видалений товар.
     */
    @Transactional
    public Boolean deleteProduct(int id) {

        Session session = this.sessionFactory.getCurrentSession();
        Object persistanceInstance = session.load(Product.class, id);

        if (persistanceInstance != null) {
            session.delete(persistanceInstance);
            return true;
        }
        return false;
    }
}
