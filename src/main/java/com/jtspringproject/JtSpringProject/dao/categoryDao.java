package com.jtspringproject.JtSpringProject.dao;

import com.jtspringproject.JtSpringProject.models.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Data Access Object (DAO) для роботи з сутністю "Категорія" (Category) у базі даних.
 */
@Repository
public class categoryDao {
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
     * Додає нову категорію у базу даних.
     *
     * @param name Назва нової категорії.
     * @return Створений об'єкт Category.
     */
    @Transactional
    public Category addCategory(String name) {
        Category category = new Category();
        category.setName(name);
        this.sessionFactory.getCurrentSession().saveOrUpdate(category);
        return category;
    }

    /**
     * Отримує список всіх категорій з бази даних.
     *
     * @return Список об'єктів Category.
     */
    @Transactional
    public List<Category> getCategories() {
        return this.sessionFactory.getCurrentSession().createQuery("from CATEGORY").list();
    }

    /**
     * Видаляє категорію з бази даних за її ідентифікатором.
     *
     * @param id Ідентифікатор категорії.
     * @return Логічне значення, чи була видалена категорія.
     */
    @Transactional
    public Boolean deleteCategory(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Object persistanceInstance = session.load(Category.class, id);

        if (persistanceInstance != null) {
            session.delete(persistanceInstance);
            return true;
        }
        return false;
    }

    /**
     * Оновлює інформацію про категорію у базі даних за її ідентифікатором.
     *
     * @param id   Ідентифікатор категорії.
     * @param name Нова назва категорії.
     * @return Оновлений об'єкт Category.
     */
    @Transactional
    public Category updateCategory(int id, String name) {
        Category category = this.sessionFactory.getCurrentSession().get(Category.class, id);
        category.setName(name);

        this.sessionFactory.getCurrentSession().update(category);
        return category;
    }

    /**
     * Отримує категорію з бази даних за її ідентифікатором.
     *
     * @param id Ідентифікатор категорії.
     * @return Об'єкт Category.
     */
    @Transactional
    public Category getCategory(int id) {
        return this.sessionFactory.getCurrentSession().get(Category.class, id);
    }

    /**
     * Отримує категорію з бази даних за її назвою.
     *
     * @param name Назва категорії.
     * @return Об'єкт Category.
     */
    @Transactional
    public Category getCategory(String name) {
        return this.sessionFactory.getCurrentSession().createQuery("from CATEGORY where name =:name", Category.class).getSingleResult();
    }
}
