package com.jtspringproject.JtSpringProject.services;

import com.jtspringproject.JtSpringProject.dao.cartDao;
import com.jtspringproject.JtSpringProject.models.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервіс, який надає функціонал для роботи з корзинами товарів.
 */
@Service
public class cartService {
    @Autowired
    private cartDao cartDao;

    /**
     * Додає нову корзину товарів.
     *
     * @param cart Об'єкт Cart, який представляє корзину товарів.
     * @return Об'єкт Cart після додавання.
     */
    public Cart addCart(Cart cart) {
        return cartDao.addCart(cart);
    }

    /**
     * Отримує список усіх корзин товарів.
     *
     * @return Список корзин товарів.
     */
    public List<Cart> getCarts() {
        return this.cartDao.getCarts();
    }

    /**
     * Оновлює інформацію про корзину товарів.
     *
     * @param cart Об'єкт Cart, який представляє корзину товарів.
     */
    public void updateCart(Cart cart) {
        cartDao.updateCart(cart);
    }

    /**
     * Видаляє корзину товарів.
     *
     * @param cart Об'єкт Cart, який представляє корзину товарів.
     */
    public void deleteCart(Cart cart) {
        cartDao.deleteCart(cart);
    }
}
