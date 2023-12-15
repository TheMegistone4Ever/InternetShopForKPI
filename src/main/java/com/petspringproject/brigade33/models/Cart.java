package com.petspringproject.brigade33.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Клас сутності, яка представляє кошик для покупок у системі.
 */
@Entity(name = "CART")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "cart_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

    public Cart() {
        products = new ArrayList<>();
    }

    /**
     * Отримує унікальний ідентифікатор кошика.
     *
     * @return Ідентифікатор кошика.
     */
    public int getId() {
        return id;
    }

    /**
     * Встановлює унікальний ідентифікатор кошика.
     *
     * @param id Ідентифікатор кошика.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Отримує клієнта (користувача), пов'язаного з кошиком.
     *
     * @return Клієнт (користувач), пов'язаний з кошиком.
     */
    public User getCustomer() {
        return customer;
    }

    /**
     * Встановлює клієнта (користувача), пов'язаного з кошиком.
     *
     * @param customer Клієнт (користувач), пов'язаний з кошиком.
     */
    public void setCustomer(User customer) {
        this.customer = customer;
    }

    /**
     * Отримує список товарів у кошику.
     *
     * @return Список товарів у кошику.
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * Встановлює список товарів у кошику.
     *
     * @param products Список товарів для встановлення у кошику.
     */
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    /**
     * Додає товар у кошик.
     *
     * @param product Товар для додавання у кошик.
     */
    public void addProduct(Product product) {
        products.add(product);
    }

    /**
     * Видаляє товар з кошика.
     *
     * @param product Товар для видалення з кошика.
     */
    public void removeProduct(Product product) {
        products.remove(product);
    }
}
