package com.petspringproject.brigade33.services;

import com.petspringproject.brigade33.dao.cartDao;
import com.petspringproject.brigade33.models.Cart;
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
     */
    public void addCart(Cart cart) {
        cartDao.addCart(cart);
    }

    /**
     * Отримує список усіх корзин товарів.
     *
     * @return Список корзин товарів.
     */
    public List<Cart> getCarts() {
        return this.cartDao.getCarts();
    }

    public Cart getCartById(int id) {
        return cartDao.getCartById(id);
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
