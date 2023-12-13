package com.jtspringproject.JtSpringProject.services;

import com.jtspringproject.JtSpringProject.dao.categoryDao;
import com.jtspringproject.JtSpringProject.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервіс, який надає функціонал для роботи з категоріями товарів.
 */
@Service
public class categoryService {
    @Autowired
    private categoryDao categoryDao;

    /**
     * Додає нову категорію товарів.
     *
     * @param name Назва категорії.
     * @return Об'єкт Category, який представляє категорію товарів.
     */
    public Category addCategory(String name) {
        return this.categoryDao.addCategory(name);
    }

    /**
     * Отримує список усіх категорій товарів.
     *
     * @return Список категорій товарів.
     */
    public List<Category> getCategories() {
        return this.categoryDao.getCategories();
    }

    /**
     * Видаляє категорію товарів за заданим ідентифікатором.
     *
     * @param id Ідентифікатор категорії товарів.
     * @return true, якщо категорію видалено успішно; false, якщо категорії не існує.
     */
    public Boolean deleteCategory(int id) {
        return this.categoryDao.deleteCategory(id);
    }

    /**
     * Оновлює інформацію про категорію товарів.
     *
     * @param id   Ідентифікатор категорії товарів.
     * @param name Нова назва категорії.
     * @return Об'єкт Category після оновлення.
     */
    public Category updateCategory(int id, String name) {
        return this.categoryDao.updateCategory(id, name);
    }

    /**
     * Отримує категорію товарів за заданим ідентифікатором.
     *
     * @param id Ідентифікатор категорії товарів.
     * @return Об'єкт Category, який представляє категорію товарів.
     */
    public Category getCategory(int id) {
        return this.categoryDao.getCategory(id);
    }

    /**
     * Отримує категорію товарів за заданою назвою.
     *
     * @param name Назва категорії товарів.
     * @return Об'єкт Category, який представляє категорію товарів.
     */
    public Category getCategory(String name) {
        return this.categoryDao.getCategory(name);
    }
}
