package com.petspringproject.brigade33.models;

import javax.persistence.*;

/**
 * Клас сутності, яка представляє товар у системі.
 */
@Entity(name = "PRODUCT")
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private User user;

    private String name;
    private String image;
    private int quantity;
    private int price;
    private int weight;
    private String description;

    /**
     * Отримує ідентифікатор товару.
     *
     * @return Ідентифікатор товару.
     */
    public int getId() {
        return id;
    }

    /**
     * Встановлює ідентифікатор товару.
     *
     * @param id Ідентифікатор товару.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Отримує назву товару.
     *
     * @return Назва товару.
     */
    public String getName() {
        return name;
    }

    /**
     * Встановлює назву товару.
     *
     * @param name Назва товару.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Отримує зображення товару.
     *
     * @return Шлях до зображення товару.
     */
    public String getImage() {
        return image;
    }

    /**
     * Встановлює зображення товару.
     *
     * @param image Шлях до зображення товару.
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Отримує категорію товару.
     *
     * @return Об'єкт Category, який представляє категорію товару.
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Встановлює категорію товару.
     *
     * @param category Об'єкт Category, який представляє категорію товару.
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Отримує користувача, який додав товар.
     *
     * @return Об'єкт User, який представляє користувача.
     */
    public User getUser() {
        return user;
    }

    /**
     * Встановлює користувача, який додав товар.
     *
     * @param user Об'єкт User, який представляє користувача.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Отримує кількість товару на складі.
     *
     * @return Кількість товару на складі.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Встановлює кількість товару на складі.
     *
     * @param quantity Кількість товару на складі.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Отримує ціну товару.
     *
     * @return Ціна товару.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Встановлює ціну товару.
     *
     * @param price Ціна товару.
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Отримує вагу товару.
     *
     * @return Вага товару.
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Встановлює вагу товару.
     *
     * @param weight Вага товару.
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * Отримує опис товару.
     *
     * @return Опис товару.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Встановлює опис товару.
     *
     * @param description Опис товару.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
