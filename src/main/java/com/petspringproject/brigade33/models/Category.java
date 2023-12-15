package com.petspringproject.brigade33.models;

import javax.persistence.*;

/**
 * Клас сутності, яка представляє категорію товару в системі.
 */
@Entity(name = "CATEGORY")
public class Category {

    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    /**
     * Отримує ідентифікатор категорії.
     *
     * @return Ідентифікатор категорії.
     */
    public int getId() {
        return id;
    }

    /**
     * Встановлює ідентифікатор категорії.
     *
     * @param id Ідентифікатор категорії.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Отримує назву категорії.
     *
     * @return Назва категорії.
     */
    public String getName() {
        return name;
    }

    /**
     * Встановлює назву категорії.
     *
     * @param name Назва категорії.
     */
    public void setName(String name) {
        this.name = name;
    }
}
