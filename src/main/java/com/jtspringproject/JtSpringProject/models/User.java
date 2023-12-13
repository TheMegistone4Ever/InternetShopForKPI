package com.jtspringproject.JtSpringProject.models;

import javax.persistence.*;
import java.util.Date;

/**
 * Клас сутності, яка представляє користувача в системі.
 */
@Entity(name = "CUSTOMER")
@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(unique = true)
    private String username;

    private String email;
    private String password;
    private String role;
    private String address;

    @Column(name = "update_date")
    private Date updateDate;

    /**
     * Конструктор за замовчуванням.
     */
    public User() {
    }

    /**
     * Конструктор з параметрами.
     *
     * @param id       Унікальний ідентифікатор користувача.
     * @param username Ім'я користувача.
     * @param email    Електронна адреса користувача.
     * @param password Пароль користувача.
     * @param role     Роль користувача.
     * @param address  Адреса користувача.
     */
    public User(int id, String username, String email, String password, String role, String address) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.address = address;
    }

    /**
     * Отримує унікальний ідентифікатор користувача.
     *
     * @return Унікальний ідентифікатор користувача.
     */
    public int getId() {
        return id;
    }

    /**
     * Встановлює унікальний ідентифікатор користувача.
     *
     * @param id Унікальний ідентифікатор користувача.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Отримує ім'я користувача.
     *
     * @return Ім'я користувача.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Встановлює ім'я користувача.
     *
     * @param username Ім'я користувача.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Отримує електронну адресу користувача.
     *
     * @return Електронна адреса користувача.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Встановлює електронну адресу користувача.
     *
     * @param email Електронна адреса користувача.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Отримує пароль користувача.
     *
     * @return Пароль користувача.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Встановлює пароль користувача.
     *
     * @param password Пароль користувача.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Отримує роль користувача.
     *
     * @return Роль користувача.
     */
    public String getRole() {
        return role;
    }

    /**
     * Встановлює роль користувача.
     *
     * @param role Роль користувача.
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Отримує адресу користувача.
     *
     * @return Адреса користувача.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Встановлює адресу користувача.
     *
     * @param address Адреса користувача.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Отримує дату останнього оновлення користувача.
     *
     * @return Дата останнього оновлення користувача.
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * Встановлює дату останнього оновлення користувача.
     *
     * @param updateDate Дата останнього оновлення користувача.
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
