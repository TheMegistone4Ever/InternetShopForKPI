package com.jtspringproject.JtSpringProject.controller;

import com.jtspringproject.JtSpringProject.models.Cart;
import com.jtspringproject.JtSpringProject.models.CartProduct;
import com.jtspringproject.JtSpringProject.models.Product;
import com.jtspringproject.JtSpringProject.models.User;
import com.jtspringproject.JtSpringProject.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Клас-контролер для користувацького функціоналу.
 */
@Controller
public class UserController {

    @Autowired
    private userService userService;

    @Autowired
    private productService productService;

    @Autowired
    private categoryService categoryService;

    @Autowired
    private cartService cartService;

    @Autowired
    cartProductService cartProductService;

    /**
     * Відображає форму реєстрації користувача.
     *
     * @return Сторінка реєстрації користувача.
     */
    @GetMapping("/register")
    public String registerUser() {
        return "register";
    }

    /**
     * Обробник GET-запиту для відображення сторінки покупки товарів.
     *
     * @return Сторінка покупки товарів.
     */
    @GetMapping("/buy")
    public String buy() {
        return "buy";
    }

    /**
     * Обробник GET-запиту для відображення форми входу користувача.
     *
     * @param model Модель, яка містить атрибути для представлення.
     * @return Сторінка входу користувача.
     */
    @GetMapping("/")
    public String userLogin(Model model) {
        return "userLogin";
    }

    /**
     * Обробник GET-запиту для відображення сторінки замовлення товарів.
     *
     * @param username Ім'я користувача, передане через параметр запиту.
     * @param pass     Пароль користувача, переданий через параметр запиту.
     * @param model    Модель, яка містить атрибути для представлення.
     * @param res      Об'єкт HttpServletResponse для додавання cookie.
     * @return ModelAndView з переглядом або повертається до сторінки входу в разі невдалої аутентифікації.
     */
    @RequestMapping(value = "userloginvalidate", method = RequestMethod.POST)
    public ModelAndView userlogin(@RequestParam("username") String username, @RequestParam("password") String pass, Model model, HttpServletResponse res) {
        System.out.println(pass);
        User u = this.userService.checkLogin(username, pass);
        System.out.println(u.getUsername());
        u.setUpdateDate(new Date());
        // ToDo: write query for updating db...

        if (username.equals(u.getUsername())) {

            res.addCookie(new Cookie("username", u.getUsername()));
            ModelAndView mView = new ModelAndView("index");
            mView.addObject("user", u);
            List<Product> products = this.productService.getProducts();

            if (products.isEmpty()) {
                mView.addObject("msg", "No products are available");
            } else {
                mView.addObject("products", products);
            }
            return mView;

        } else {
            ModelAndView mView = new ModelAndView("userLogin");
            mView.addObject("msg", "Please enter correct email and password");
            return mView;
        }

    }

    /**
     * Обробник GET-запиту для відображення сторінки профілю користувача.
     *
     * @param model Модель, яка містить атрибути для представлення.
     * @return Сторінка профілю користувача.
     */
    @GetMapping("profileDisplay")
    public String profileDisplay(Model model) {
        try {
            User user = userService.getActualUser();

            model.addAttribute("userid", user.getId());
            model.addAttribute("username", user.getUsername());
            model.addAttribute("email", user.getEmail());
            model.addAttribute("password", user.getPassword());
        } catch (Exception e) {
            System.out.println("Exception:" + e);
        }
        return "updateProfile";
    }

    /**
     * Обробник POST-запиту для оновлення профілю користувача.
     *
     * @param userid   Ідентифікатор користувача, переданий через параметр запиту.
     * @param username Ім'я користувача, передане через параметр запиту.
     * @param email    Електронна адреса користувача, передана через параметр запиту.
     * @param password Пароль користувача, переданий через параметр запиту.
     * @return Редірект на головну сторінку після оновлення профілю.
     */
    @RequestMapping(value = "updateuser", method = RequestMethod.POST)
    public String updateUserProfile(@RequestParam("userid") int userid, @RequestParam("username") String username, @RequestParam("email") String email, @RequestParam("password") String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommjava", "root", "1598");

            PreparedStatement pst = con.prepareStatement("UPDATE customer c SET c.username = ?, c.email = ?, c.password = ?, c.address = ?, update_date = now() WHERE c.id = ?");

            pst.setString(1, username);
            pst.setString(2, email);
            pst.setString(3, password);
            pst.setString(4, "address");
            pst.setString(5, String.valueOf(userid));
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println("Exception:" + e);
        }
        return "redirect:/";
    }

    /**
     * Обробник GET-запиту для відображення списку продуктів.
     *
     * @return ModelAndView з переглядом та інформацією про продукти.
     */
    @GetMapping("/user/products")
    public ModelAndView getproduct() {

        ModelAndView mView = new ModelAndView("uproduct");

        List<Product> products = this.productService.getProducts();

        if (products.isEmpty()) {
            mView.addObject("msg", "No products are available");
        } else {
            mView.addObject("products", products);
        }

        return mView;
    }

    /**
     * Обробник POST-запиту для реєстрації нового користувача.
     *
     * @param user Об'єкт користувача, який передається через параметр запиту.
     * @return ModelAndView з переглядом для сторінки входу користувача або сторінки реєстрації в разі помилки.
     */
    @RequestMapping(value = "newuserregister", method = RequestMethod.POST)
    public ModelAndView newUseRegister(@ModelAttribute User user) {
        // Check if username already exists in database
        boolean exists = this.userService.checkUserExists(user.getUsername());

        if (!exists) {
            System.out.println(user.getEmail());
            user.setRole("ROLE_NORMAL");
            this.userService.addUser(user);

            System.out.println("New user created: " + user.getUsername());
            ModelAndView mView = new ModelAndView("userLogin");
            return mView;
        } else {
            System.out.println("New user not created - username taken: " + user.getUsername());
            ModelAndView mView = new ModelAndView("register");
            mView.addObject("msg", user.getUsername() + " is taken. Please choose a different username.");
            return mView;
        }
    }

    /**
     * Обробник GET-запиту для відображення тестової сторінки з моделлю.
     *
     * @param model Модель для передачі даних до перегляду.
     * @return Cторінка з тестовою інформацією.
     */
    @GetMapping("/test")
    public String Test(Model model) {
        System.out.println("test page");
        model.addAttribute("author", "Brigade 33");
        model.addAttribute("id", 40);

        List<String> friends = new ArrayList<String>();
        model.addAttribute("f", friends);
        friends.add("xyz");
        friends.add("abc");

        return "test";
    }

    /**
     * Обробник GET-запиту для відображення тестової сторінки з об'єктом ModelAndView.
     *
     * @return Об'єкт ModelAndView з переглядом та інформацією про автора.
     */
    @GetMapping("/test2")
    public ModelAndView Test2() {
        System.out.println("test page");
        ModelAndView mv = new ModelAndView();
        mv.addObject("name", "Brigade 33");
        mv.addObject("id", 40);
        mv.setViewName("test2");

        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(25);
        mv.addObject("marks", list);
        return mv;
    }

    /**
     * Обробник GET-запиту для відображення сторінки з деталями кошика.
     *
     * @return Cторінка з деталями кошика.
     */
    @GetMapping("carts")
    public String getCartDetail() {
        return "cartproduct";
    }

    /**
     * Обробник GET-запиту для виходу користувача.
     *
     * @return Редірект на головну сторінку після виходу ("redirect:/").
     */
    @RequestMapping("user/logout")
    public String returnIndex() {
        return "redirect:/";
    }

    /**
     * Обробник POST-запиту для додавання товару до кошика.
     *
     * @param id           Ідентифікатор товару.
     * @param customerId   Ідентифікатор користувача.
     * @param categoryId   Ідентифікатор категорії товару.
     * @param name         Назва товару.
     * @param price        Ціна товару.
     * @param weight       Вага товару.
     * @param quantity     Кількість товару.
     * @param description  Опис товару.
     * @param productImage Шлях до зображення товару.
     * @return Сторінка з деталями кошика.
     */
    @RequestMapping(value = "user/products/addtocart", method = RequestMethod.POST)
    public String addProduct(@RequestParam("id") int id,
                             @RequestParam("customerid") int customerId
//                             @RequestParam("categoryid") int categoryId,
//                             @RequestParam("name") String name,
//                             @RequestParam("price") int price,
//                             @RequestParam("weight") int weight,
//                             @RequestParam("quantity") int quantity,
//                             @RequestParam("description") String description,
//                             @RequestParam("productImage") String productImage
    ) {
//        Product product = new Product();
//        product.setId(id);
//        product.setName(name);
//        product.setCategory(categoryService.getCategory(categoryId));
//        product.setDescription(description);
//        product.setPrice(price);
//        product.setImage(productImage);
//        product.setWeight(weight);
//        product.setQuantity(quantity);
//        product.setUser(userService.getById(customerId));
//        productService.addProduct(product);

        Product product = productService.getProduct(id);
        Cart cart = new Cart();
        cart.addProduct(product);
        cart.setCustomer(userService.getById(customerId));
        cartService.addCart(cart);

//        CartProduct cartProduct = new CartProduct();
//        cartProduct.setCart(cart);
//        cartProduct.setProduct(product);
//        cartProductService.addCartProduct(cartProduct);

        return "redirect:/carts";
    }

    @GetMapping("cart/delete")
    public String removeCartDb(@RequestParam("id") int id) {
        Cart cart = cartService.getCartById(id);
        cartService.deleteCart(cart);
        return "redirect:/carts";
    }

    /**
     * Обробник GET-запиту для відображення сторінки з деталями кошика (зазначене в "return" не відповідає назві методу).
     *
     * @return Сторінка з деталями кошика.
     */
    @GetMapping("user/products/cartproduct")
    public String returnCartproduct() {
        return "userloginvalidate";
    }
}
