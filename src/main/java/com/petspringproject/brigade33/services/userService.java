package com.petspringproject.brigade33.services;

import com.petspringproject.brigade33.dao.userDao;
import com.petspringproject.brigade33.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервіс, який надає функціонал для роботи з користувачами.
 */
@Service
public class userService {
    @Autowired
    private userDao userDao;

    /**
     * Отримує список усіх користувачів.
     *
     * @return Список користувачів.
     */
    public List<User> getUsers() {
        return this.userDao.getAllUser();
    }

    /**
     * Отримує актуального користувача, тобто останнього зміненого.
     *
     * @return Об'єкт User, який представляє користувача.
     */
    public User getActualUser() {
        return this.userDao.getActualUser();
    }

    /**
     * Отримує користувача за заданим ідентифікатором.
     *
     * @param id Ідентифікатор користувача.
     * @return Об'єкт User, який представляє користувача.
     */
    public User getById(int id) {
        return this.userDao.getUserById(id);
    }

    /**
     * Додає нового користувача.
     *
     * @param user Об'єкт User, який представляє користувача.
     */
    public void addUser(User user) {
        this.userDao.saveUser(user);
    }

    /**
     * Видаляє користувача за заданим ідентифікатором.
     *
     * @param id Ідентифікатор користувача.
     */
    public void deleteUser(int id) {
        this.userDao.deleteUser(id);
    }

    /**
     * Перевіряє логін та пароль користувача.
     *
     * @param username Логін користувача.
     * @param password Пароль користувача.
     * @return Об'єкт User, який представляє користувача, якщо відповідність знайдена; інакше, пустий об'єкт User.
     */
    public User checkLogin(String username, String password) {
        return this.userDao.getUser(username, password);
    }

    /**
     * Перевіряє, чи існує користувач за заданим логіном.
     *
     * @param username Логін користувача.
     * @return true, якщо користувач існує; false, якщо користувача не існує.
     */
    public boolean checkUserExists(String username) {
        return this.userDao.userExists(username);
    }
}
