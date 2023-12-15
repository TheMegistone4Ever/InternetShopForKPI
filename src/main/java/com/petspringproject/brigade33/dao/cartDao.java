package com.petspringproject.brigade33.dao;

import com.petspringproject.brigade33.models.Cart;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Data Access Object (DAO) для роботи з сутністю "Кошик" (Cart) у базі даних.
 */
@Repository
public class cartDao {
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
     * Додає новий об'єкт Cart у базу даних.
     *
     * @param cart Об'єкт Cart для додавання.
     * @return Доданий об'єкт Cart.
     */
    @Transactional
    public Cart addCart(Cart cart) {
        this.sessionFactory.getCurrentSession().save(cart);
        return cart;
    }

    /**
     * Отримує список всіх об'єктів Cart з бази даних.
     *
     * @return Список об'єктів Cart.
     */
    @Transactional
    public List<Cart> getCarts() {
        return this.sessionFactory.getCurrentSession().createQuery("from CART").list();
    }

    @Transactional
    public Cart getCartById(int id) {
        return (Cart) this.sessionFactory.getCurrentSession().createQuery("from CART where id = :id").setParameter("id", id).uniqueResult();
    }

    /**
     * Оновлює інформацію про об'єкт Cart у базі даних.
     *
     * @param cart Об'єкт Cart для оновлення.
     */
    @Transactional
    public void updateCart(Cart cart) {
        this.sessionFactory.getCurrentSession().update(cart);
    }

    /**
     * Видаляє об'єкт Cart з бази даних.
     *
     * @param cart Об'єкт Cart для видалення.
     */
    @Transactional
    public void deleteCart(Cart cart) {
        this.sessionFactory.getCurrentSession().delete(cart);
    }
}
