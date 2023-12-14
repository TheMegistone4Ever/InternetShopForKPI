package com.jtspringproject.JtSpringProject.services;

import com.jtspringproject.JtSpringProject.dao.userDao;
import com.jtspringproject.JtSpringProject.models.User;
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
     * @return Об'єкт User після додавання.
     */
    public User addUser(User user) {
        return this.userDao.saveUser(user);
    }

    /**
     * Видаляє користувача за заданим ідентифікатором.
     *
     * @param id Ідентифікатор користувача.
     * @return true, якщо користувача видалено успішно; false, якщо користувача не існує.
     */
    public Boolean deleteUser(int id) {
        return this.userDao.deleteUser(id);
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
