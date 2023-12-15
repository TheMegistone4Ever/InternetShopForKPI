package com.jtspringproject.JtSpringProject.dao;

import com.jtspringproject.JtSpringProject.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Data Access Object (DAO) для роботи з сутністю "Користувач" (User) у базі даних.
 */
@Repository
public class userDao {
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
     * Отримує список всіх користувачів з бази даних.
     *
     * @return Список об'єктів User.
     */
    @Transactional
    public List<User> getAllUser() {
        Session session = this.sessionFactory.getCurrentSession();
        List<User> userList = session.createQuery("from CUSTOMER").list();
        return userList;
    }

    /**
     * Зберігає (додає або оновлює) користувача у базі даних.
     *
     * @param user Об'єкт User для збереження.
     * @return Створений або оновлений об'єкт User.
     */
    @Transactional
    public User saveUser(User user) {
        this.sessionFactory.getCurrentSession().saveOrUpdate(user);
        System.out.println("User added" + user.getId());
        return user;
    }

    /**
     * Отримує актуального користувача, тобто того, чий запис має найбільшу дату оновлення.
     *
     * @return Об'єкт User, який є актуальним.
     */
    @Transactional
    public User getActualUser() {
        Session session = this.sessionFactory.getCurrentSession();
        String hql = "FROM CUSTOMER c WHERE c.role = 'ROLE_NORMAL'";
        Query<User> query = session.createQuery(hql, User.class);

        return query.uniqueResult();
    }

    /**
     * Отримує користувача за логіном та паролем.
     *
     * @param username Логін користувача.
     * @param password Пароль користувача.
     * @return Об'єкт User, якщо логін та пароль вірні; інакше - пустий об'єкт User.
     */
    @Transactional
    public User getUser(String username, String password) {
        Query query = sessionFactory.getCurrentSession().createQuery("from CUSTOMER where username = :username");
        query.setParameter("username", username);

        try {
            User user = (User) query.getSingleResult();
            System.out.println(user.getPassword());
            if (password.equals(user.getPassword())) {
                return user;
            } else {
                return new User();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            User user = new User();
            return user;
        }

    }

    /**
     * Отримує користувача за його ідентифікатором.
     *
     * @param id Ідентифікатор користувача.
     * @return Об'єкт User за ідентифікатором.
     */
    @Transactional
    public User getUserById(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from CUSTOMER where id = :id");
        query.setParameter("id", id);

        try {
            User user = (User) query.getSingleResult();
            return user;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            User user = new User();
            return user;
        }

    }

    /**
     * Видаляє користувача за його ідентифікатором.
     *
     * @param id Ідентифікатор користувача.
     * @return Логічне значення, чи був видалений користувач.
     */
    @Transactional
    public Boolean deleteUser(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Object persistanceInstance = session.load(User.class, id);

        if (persistanceInstance != null) {
            session.delete(persistanceInstance);
            return true;
        }
        return false;
    }

    /**
     * Перевіряє наявність користувача за логіном.
     *
     * @param username Логін користувача.
     * @return Логічне значення, чи існує користувач з таким логіном.
     */
    @Transactional
    public boolean userExists(String username) {
        Query query = sessionFactory.getCurrentSession().createQuery("from CUSTOMER where username = :username");
        query.setParameter("username", username);
        return !query.getResultList().isEmpty();
    }
}
