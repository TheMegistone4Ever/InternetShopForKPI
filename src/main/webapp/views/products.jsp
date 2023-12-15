<%@page import="java.sql.*" %>
<%@page import="java.util.*" %>
<%@page import="java.text.*" %>
<%@page import="java.io.FileOutputStream" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page import=" java.io.ObjectOutputStream" %>
<!doctype html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
          crossorigin="anonymous">

    <title>Document</title>
</head>
<body class="bg-light">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="#"> <img
                th:src="@{/images/logo.png}" src="../static/images/logo.png"
                width="auto" height="40" class="d-inline-block align-top" alt=""/>
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse"
                data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto"></ul>
            <ul class="navbar-nav">
                <li class="nav-item active"><a class="nav-link" href="adminhome">Home
                    Page</a></li>
                <li class="nav-item active"><a class="nav-link" href="logout">Logout</a>
                </li>

            </ul>

        </div>
    </div>
</nav>
<br>
<div class="container-fluid">

    <a style="margin: 20px 0" class="btn btn-primary"
       href="/admin/products/add">Add Product</a><br>
    <table class="table">

        <tr>
            <th scope="col">Serial No.</th>
            <th scope="col">Product Name</th>
            <th scope="col">Category</th>
            <th scope="col">Preview</th>
            <th scope="col">Quantity</th>
            <th scope="col">Price</th>
            <th scope="col">Weight</th>
            <th scope="col">Description</th>
            <th scope="col">Delete</th>
            <th scope="col">Update</th>
        </tr>
        <tbody>

        <c:forEach var="product" items="${products}">
            <tr>
                <td>
                        ${product.id}
                </td>
                <td>
                        ${product.name }
                </td>
                <td>
                        ${product.category.name}

                </td>

                <td><img src="${product.image}"
                         height="100px" width="100px" alt="Product Image"></td>
                <td>
                        ${product.quantity }
                </td>
                <td>$
                        ${product.price }
                </td>
                <td>
                        ${product.weight }
                </td>
                <td>
                        ${product.description }
                </td>

                <td>
                    <form action="products/delete" method="get">
                        <input type="hidden" name="id" value="${product.id}">
                        <input type="submit" value="Delete" class="btn btn-danger">
                    </form>
                </td>
                <td>
                    <form action="products/update" method="get">
                        <!-- Button trigger modal -->
                        <button type="button" class="btn btn-warning" data-toggle="modal"
                                data-target="#exampleModalCenter2"
                                onclick="setProductValues(${product.id}, '${product.name}', '${product.category.name}', '${product.image}', ${product.quantity}, ${product.price}, ${product.weight}, '${product.description}')">
                            Update
                        </button>

                        <!-- Modal -->
                        <div class="modal fade" id="exampleModalCenter2" tabindex="-1" role="dialog"
                             aria-labelledby="exampleModalCenterTitle"
                             aria-hidden="true">
                            <div class="modal-dialog modal-dialog-centered" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLongTitle">Update Product Details</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body text-center">
                                        <div class="form-group">
                                            <input class="form-control" type="number" readonly="readonly"
                                                   name="productid" id="productid"
                                                   value="0">
                                        </div>
                                        <div class="form-group">
                                            <input class="form-control" type="text" readonly="readonly"
                                                   name="categoryname" id="categoryname" value="categoryname">
                                        </div>
                                        <div class="form-group">
                                            <input class="form-control" type="text" name="productname" id="productname"
                                                   value="productname">
                                        </div>
                                        <div class="form-group">
                                            <input class="form-control" type="text" name="productimage"
                                                   id="productimage" value="productimage">
                                        </div>
                                        <div class="form-group">
                                            <input class="form-control" type="number" name="productquantity"
                                                   id="productquantity" value="0">
                                        </div>
                                        <div class="form-group">
                                            <input class="form-control" type="number" name="productprice"
                                                   id="productprice" value="0">
                                        </div>
                                        <div class="form-group">
                                            <input class="form-control" type="number" name="productweight"
                                                   id="productweight" value="0">
                                        </div>
                                        <div class="form-group">
                                            <label for="productdescription"></label><textarea class="form-control"
                                                                                              name="productdescription"
                                                                                              id="productdescription"
                                                                                              rows="5">productdescription</textarea>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close
                                        </button>
                                        <button type="submit" class="btn btn-primary">Update changes</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>

</div>
<script>
    function setProductValues(id, name, categoryName, image, quantity, price, weight, description) {
        document.getElementById('productid').value = id;
        document.getElementById('productname').value = name;
        document.getElementById('categoryname').value = categoryName;
        document.getElementById('productimage').value = image;
        document.getElementById('productquantity').value = quantity;
        document.getElementById('productprice').value = price;
        document.getElementById('productweight').value = weight;
        document.getElementById('productdescription').value = description;
    }
</script>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
<script
        src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script
        src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
</body>
</html>