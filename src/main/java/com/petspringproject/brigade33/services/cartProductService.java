package com.petspringproject.brigade33.services;

import com.petspringproject.brigade33.dao.cartProductDao;
import com.petspringproject.brigade33.models.CartProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class cartProductService {
    @Autowired
    private cartProductDao cartProductDao;

    public CartProduct addCartProduct(CartProduct cartProduct) {
        return cartProductDao.addCartProduct(cartProduct);
    }

    public CartProduct deleteCartProduct(CartProduct cartProduct) {
        return cartProductDao.deleteCartProduct(cartProduct);
    }
}
