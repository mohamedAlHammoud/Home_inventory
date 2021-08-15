

<%@page contentType="text/html" pageEncoding="UTF-8"    %>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="./assets/styles/main-style.css">
        <title>JSP Page</title>
    </head>



    <header class="header">
        <h1 class="logo"><a href="#">Home Inventory</a></h1>
        <ul class="main-nav">
            <li > <a href="/Home_Inventory/inventory">Inventory</a></li>
            <li > <a href="/Home_Inventory/admin">Admin</a></li>

            <li > <a href="/Home_Inventory/categories">Manage Item Categories</a></li>
            <li > <a href="/Home_Inventory/reports">Reports</a></li>
            <a href="/Home_Inventory/editUserInfo">Edit My Info</a>
            <li > <a href="/Home_Inventory/login?logout">Logout</a></li>
        </ul>
    </header>  
    <body>


        <h1>Inventory for ${user.firstName} ${user.lastName}   </h1>





        <ul>

            <table class="table">
                <thead>
                    <tr>
                <div class="table-headers">
                    <th class="email-span">Category</th>
                    <th class="span">Name</th>
                    <th class="span">Price</th>
                    <th class="span">Delete</th>
                    <th class="span">Edit</th>
                    </tr>
                    </thead>



                </div>
                <c:forEach items="${listOfItems}" var="item">




                    <tr>
                    <form method="post" action="inventory">
                        <td data-label="Category"> <span >${item.getCategory().getCategoryName()}</span></td>
                        <td data-label="Name"><span>${item.itemName}</span></td>
                        <td data-label="Price"><span>$${item.price}</span></td>


                        <td data-label="Delete"><input type="submit" value="Delete" name></td>
                        <input type="hidden" name="deleteItem" value="${item.itemID}"> 

                    </form>

                    <form method="post" action="inventory">

                        <td data-label="Edit"><input type="submit" value="Edit" > </td>




                        <input type="hidden" name="editItem" value="${item.itemID}">

                    </form>

                    </tr>

                </c:forEach>
            </table>
        </ul>  


        <div class="forms">



            <!--            ADDING THE an Item-->
            <div class="add-item"> 


                <h2>Add Item</h2>
                <form method="post" action="inventory">

                    <label for="category">Category </label>
                    <select name="item_category" id="">
                        <c:forEach  items="${category}" var="c">
                            <option value="${c.categoryID}">  ${c.categoryName}</option>
                        </c:forEach>

                    </select>
                    <br><br>
                    <label for="item_name">Name: </label>
                    <input type="text" name="item_name" id="" required>
                    <br><br>
                    <label for="price">Price </label>
                    <input type="text" name="item_price" id="" required>
                    <br><br>



                    <input type="submit" value="Save">
                    <input type="hidden" name="saveNewItem" > 

                </form>
            </div>  
            <br>
            <br>
            <!--            EDIT ITEM-->


            <c:if test="${isClicked==true}">



                <div class="editArea">
                    <h2>Edit Item</h2>
                    <form method="post" action="inventory">

                        <label for="category">Category </label>
                        <select name="itemNewCategory" id="">
                            <c:forEach  items="${category}" var="c">


                                <c:choose>
                                    <c:when test="${selectedCategory.equals(c.categoryName)}">
                                        <option value="${c.categoryID}" selected=" ${selectedCategory}">  ${c.categoryName}</option>
                                    </c:when>    
                                    <c:otherwise>
                                        <option value="${c.categoryID}">  ${c.categoryName}</option>
                                    </c:otherwise>
                                </c:choose>







                            </c:forEach>

                        </select>

                        <br><br>
                        <label for="firstName"> Name:  </label>
                        <input type="text" name="itemNewName" id="" required   value="${selectedItemName}"  >
                        <br><br>
                        <label for="lastName"> Price </label>
                        <input type="text" name="itemNewPrice" id="" required  value="${selectedItemPrice}"  >
                        <br><br>


                        <br><br>
                        <input type="submit" value="Save">
                        <input type="hidden" name="saveUserEditedItem" >



                    </form>

                    <form method="post" action="admin">
                        <input type="submit" name="clearFields" value="Cancel">
                    </form> 
                </div>
            </c:if>

        </div>
    </body>
</html>
