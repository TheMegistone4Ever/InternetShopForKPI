package com.jtspringproject.JtSpringProject.controller;

import com.jtspringproject.JtSpringProject.models.Category;
import com.jtspringproject.JtSpringProject.models.Product;
import com.jtspringproject.JtSpringProject.models.User;
import com.jtspringproject.JtSpringProject.services.categoryService;
import com.jtspringproject.JtSpringProject.services.productService;
import com.jtspringproject.JtSpringProject.services.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Клас-контролер для адміністраторського функціоналу.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    int adminlogcheck = 0;
    String usernameforclass = "";
    @Autowired
    private userService userService;
    @Autowired
    private categoryService categoryService;
    @Autowired
    private productService productService;

    /**
     * Вихід з облікового запису адміністратора.
     *
     * @return Сторінка входу адміністратора.
     */
    @RequestMapping("logout")
    public String returnIndex() {
        adminlogcheck = 0;
        usernameforclass = "";
        return "adminlogin";
    }

    /**
     * Обробник GET-запиту для головної сторінки адміністратора.
     *
     * @param model Модель для передачі даних у представлення.
     * @return Сторінка входу адміністратора або головна сторінка з інформацією про користувача.
     */
    @GetMapping("")
    public String index(Model model) {
        if (usernameforclass.equalsIgnoreCase(""))
            return "adminlogin";
        else {
            model.addAttribute("username", usernameforclass);
            return "index";
        }

    }

    /**
     * Обробник GET-запиту для відображення сторінки входу адміністратора.
     *
     * @return Сторінка входу адміністратора.
     */
    @GetMapping("login")
    public String adminlogin() {

        return "adminlogin";
    }

    /**
     * Обробник GET-запиту для відображення сторінки панелі керування адміністратора.
     *
     * @param model Модель для передачі даних у представлення.
     * @return Сторінка панелі керування адміністратора або перенаправлення на сторінку входу.
     */
    @GetMapping("dashboard")
    public String adminDashboard(Model model) {
        if (adminlogcheck == 1)
            return "adminHome";
        else
            return "redirect:/admin/login";
    }

    /**
     * Обробник GET-запиту для перенаправлення на сторінку панелі керування або входу.
     *
     * @param model Модель для передачі даних у представлення.
     * @return Сторінка панелі керування адміністратора або сторінка входу.
     */
    @GetMapping("adminhome")
    public String adminHome(Model model) {
        if (adminlogcheck != 0)
            return "redirect:/admin/dashboard";
        else
            return "redirect:/admin";
    }

    /**
     * Обробник POST-запиту для аутентифікації адміністратора.
     *
     * @param username Ім'я користувача.
     * @param pass     Пароль користувача.
     * @return Модель та перенаправлення на відповідну сторінку відповідно до результату аутентифікації.
     */
    @RequestMapping(value = "dashboard", method = RequestMethod.POST)
    public ModelAndView adminlogin(@RequestParam("username") String username, @RequestParam("password") String pass) {

        User user = this.userService.checkLogin(username, pass);

        if (user.getRole() != null && user.getRole().equals("ROLE_ADMIN")) {
            ModelAndView mv = new ModelAndView("adminHome");
            adminlogcheck = 1;
            mv.addObject("admin", user);
            return mv;
        } else {
            ModelAndView mv = new ModelAndView("adminlogin");
            mv.addObject("msg", "Please enter correct username and password");
            return mv;
        }
    }

    /**
     * Обробник GET-запиту для відображення сторінки категорій або перенаправлення на сторінку входу.
     *
     * @return Модель та відображення сторінки категорій або сторінки входу.
     */
    @GetMapping("categories")
    public ModelAndView getcategory() {
        if (adminlogcheck == 0) {
            ModelAndView mView = new ModelAndView("adminlogin");
            return mView;
        } else {
            ModelAndView mView = new ModelAndView("categories");
            List<Category> categories = this.categoryService.getCategories();
            mView.addObject("categories", categories);
            return mView;
        }
    }

    /**
     * Обробник POST-запиту для додавання нової категорії.
     *
     * @param category_name Назва нової категорії.
     * @return Перенаправлення на сторінку категорій.
     */
    @RequestMapping(value = "categories", method = RequestMethod.POST)
    public String addCategory(@RequestParam("categoryname") String category_name) {
        System.out.println(category_name);

        Category category = this.categoryService.addCategory(category_name);
        if (category.getName().equals(category_name)) {
            return "redirect:categories";
        } else {
            return "redirect:categories";
        }
    }

    /**
     * Обробник GET-запиту для видалення категорії.
     *
     * @param id Ідентифікатор категорії для видалення.
     * @return Перенаправлення на сторінку категорій.
     */
    @GetMapping("categories/delete")
    public String removeCategoryDb(@RequestParam("id") int id) {
        this.categoryService.deleteCategory(id);
        return "redirect:/admin/categories";
    }

    /**
     * Обробник GET-запиту для оновлення назви категорії.
     *
     * @param id           Ідентифікатор категорії для оновлення.
     * @param categoryname Нова назва категорії.
     * @return Перенаправлення на сторінку категорій.
     */
    @GetMapping("categories/update")
    public String updateCategory(@RequestParam("categoryid") int id, @RequestParam("categoryname") String categoryname) {
        this.categoryService.updateCategory(id, categoryname);
        return "redirect:/admin/categories";
    }

    /**
     * Обробник GET-запиту для відображення сторінки продуктів або перенаправлення на сторінку входу.
     *
     * @return Модель та відображення сторінки продуктів або сторінки входу.
     */
    @GetMapping("products")
    public ModelAndView getproduct() {
        if (adminlogcheck == 0) {
            ModelAndView mView = new ModelAndView("adminlogin");
            return mView;
        } else {
            ModelAndView mView = new ModelAndView("products");

            List<Product> products = this.productService.getProducts();

            if (products.isEmpty()) {
                mView.addObject("msg", "No products are available");
            } else {
                mView.addObject("products", products);
            }
            return mView;
        }

    }

    /**
     * Обробник GET-запиту для відображення форми додавання продукту.
     *
     * @return Модель та відображення форми додавання продукту.
     */
    @GetMapping("products/add")
    public ModelAndView addProduct() {
        ModelAndView mView = new ModelAndView("productsAdd");
        List<Category> categories = this.categoryService.getCategories();
        mView.addObject("categories", categories);
        return mView;
    }

    /**
     * Обробник POST-запиту для додавання нового продукту.
     *
     * @param name         Назва продукту.
     * @param categoryId   Ідентифікатор категорії продукту.
     * @param price        Ціна продукту.
     * @param weight       Вага продукту.
     * @param quantity     Кількість продукту.
     * @param description  Опис продукту.
     * @param productImage URL-зображення продукту.
     * @return Перенаправлення на сторінку адміністраторського розділу з продуктами.
     */
    @RequestMapping(value = "products/add", method = RequestMethod.POST)
    public String addProduct(@RequestParam("name") String name, @RequestParam("categoryid") int categoryId, @RequestParam("price") int price, @RequestParam("weight") int weight, @RequestParam("quantity") int quantity, @RequestParam("description") String description, @RequestParam("productImage") String productImage) {
        System.out.println(categoryId);
        Category category = this.categoryService.getCategory(categoryId);
        Product product = new Product();
        product.setId(categoryId);
        product.setName(name);
        product.setCategory(category);
        product.setDescription(description);
        product.setPrice(price);
        product.setImage(productImage);
        product.setWeight(weight);
        product.setQuantity(quantity);
        this.productService.addProduct(product);
        return "redirect:/admin/products";
    }

    /**
     * Обробник GET-запиту для відображення сторінки оновлення продукту.
     *
     * @param id                 Ідентифікатор продукту.
     * @param productname        Нова назва продукту.
     * @param productimage       Нове зображення продукту.
     * @param productquantity    Нова кількість продукту.
     * @param productprice       Нова ціна продукту.
     * @param productweight      Нова вага продукту.
     * @param productdescription Новий опис продукту.
     * @return Перенаправлення на сторінку адміністраторського розділу з продуктами.
     */
    @GetMapping(value = "/products/update")
    public String updateProduct(@RequestParam("productid") int id, @RequestParam("productname") String productname, @RequestParam("productimage") String productimage,
                                @RequestParam("productquantity") int productquantity, @RequestParam("productprice") int productprice,
                                @RequestParam("productweight") int productweight, @RequestParam("productdescription") String productdescription) {
        this.productService.updateProduct(id, productname, productimage, productquantity, productprice, productweight, productdescription);
        return "redirect:/admin/products";
    }

    /**
     * Обробник GET-запиту для видалення продукту.
     *
     * @param id Ідентифікатор продукту.
     * @return Перенаправлення на сторінку адміністраторського розділу з продуктами.
     */
    @GetMapping("products/delete")
    public String removeProduct(@RequestParam("id") int id) {
        this.productService.deleteProduct(id);
        return "redirect:/admin/products";
    }

    /**
     * Обробник GET-запиту для відображення сторінки додавання продукту.
     *
     * @return Модель та представлення для сторінки додавання продукту.
     */
    @GetMapping("customers/delete")
    public String removeCustomer(@RequestParam("id") int id) {
        this.userService.deleteUser(id);
        return "redirect:/admin/customers";
    }

    /**
     * Обробник POST-запиту для виконання після завершення додавання продукту.
     *
     * @return Перенаправлення на сторінку адміністраторського розділу з категоріями.
     */
    @PostMapping("products")
    public String postproduct() {
        return "redirect:/admin/categories";
    }

    /**
     * Обробник GET-запиту для відображення інформації про користувачів (клієнтів).
     *
     * @return Модель та представлення для сторінки відображення користувачів.
     */
    @GetMapping("customers")
    public ModelAndView getCustomerDetail() {
        if (adminlogcheck == 0) {
            ModelAndView mView = new ModelAndView("adminlogin");
            return mView;
        } else {
            ModelAndView mView = new ModelAndView("displayCustomers");
            List<User> users = this.userService.getUsers();
            mView.addObject("customers", users);
            return mView;
        }
    }
}
