package com.petspringproject.brigade33.dao;

import com.petspringproject.brigade33.models.CartProduct;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class cartProductDao {
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
     * Додає новий об'єкт CartProduct у базу даних.
     *
     * @param cartProduct Об'єкт CartProduct для додавання.
     * @return Доданий об'єкт CartProduct.
     */
    @Transactional
    public CartProduct addCartProduct(CartProduct cartProduct) {
        this.sessionFactory.getCurrentSession().save(cartProduct);
        return cartProduct;
    }

    /**
     * Видаляє об'єкт CartProduct з бази даних.
     *
     * @param cartProduct Об'єкт CartProduct для видалення.
     */
    @Transactional
    public CartProduct deleteCartProduct(CartProduct cartProduct) {
        this.sessionFactory.getCurrentSession().delete(cartProduct);
        return cartProduct;
    }

}
