

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


        <link type="text/css" rel="stylesheet" href="./assets/styles/main-style.css">
        <link type="text/css" rel="stylesheet" href="./assets/styles/admin.css">
        <title>Admin</title>
    </head>
    <body>

        <div class="container">




            <header class="header">
                <h1 class="logo"><a href="#">Home Inventory</a></h1>
                <ul class="main-nav">
                    <li > <a href="/Home_Inventory/inventory">Inventory</a></li>
                    <li > <a href="/Home_Inventory/admin">Admin</a></li>

                    <li > <a href="/Home_Inventory/categories">Manage Item Categories</a></li>
                    <li > <a href="/Home_Inventory/reports">Reports</a></li>
                    <li > <a href="/Home_Inventory/login?logout">Logout</a></li>
                </ul>
            </header> 




            <h1>Manage Users</h1>




            <ul>



                <table class="table">
                    <div class="table_headers">
                        <thead>


                            <tr>


                                <th class="username">Username</th>
                                <th class="fname">First Name</th>
                                <th class="lname">Last Name</th>
                                <th class="active">Active</th>
                                <th class="edit">Edit</th>
                                <th class="delete">Delete</th>
                            </tr>
                        </thead>
                    </div>
                    <tbody>


                        <c:forEach items="${users}" var="user">


                            <tr>
                        <form method="post" action="admin">
                            <td data-label="Username"><span name="email" >${user.username}</span></td>
                            <td data-label="First Name"> <span>${user.firstName}</span></td>
                            <td data-label="Last Name">  <span>${user.lastName}</span></td>

                            <c:choose>
                                <c:when test="${user.active}">
                                    <td data-label="active"><span>Yes</span></td>
                                </c:when>    
                                <c:otherwise>
                                    <td data-label="active"><span>No</span></td>
                                </c:otherwise>
                            </c:choose>

                            <td data-label="Edit"><input type="submit" value="Edit"></td>
                            <input type="hidden" name="editButton" value="${user.username}"> 

                        </form>
                        <form method="post" action="admin">
                            <td data-label="Delete"><input type="submit" value="Delete" name></td>
                            <input type="hidden" name="deleteUser" value="${user.username}"> 
                        </form>
                        </tr>

                    </c:forEach>
                    </tbody>
                </table>
            </ul>  


            <div class="forms">



                <!--            ADDING THE USER-->
                <div class="add-user"> 


                    <h2>Add User</h2>
                    <form method="post" action="admin">

                        <label for="username">Username </label>
                        <input type="text" name="newUserName" id="" required>
                        <br><br>

                        <label for="newEmail">Email </label>
                        <input type="email" name="newEmail" id="" required>
                        <br><br>

                        <label for="newFirstName">First Name: </label>
                        <input type="text" name="newFirstName" id="" required>
                        <br><br>
                        <label for="newLastName">Last Name: </label>
                        <input type="text" name="newLastName" id="" required>
                        <br><br>
                        <label for="password">Password: </label>
                        <input type="password" name="newpassword" id="" required> 
                        <br><br>


                        <input type="submit" value="Save">
                        <input type="hidden" name="saveNewUser" > 

                    </form>

                </div>   
                <br>
                <br>


                <c:if test="${isClicked==true}">


                    <!--        EDIT USER-->
                    <div class="edit-user">

                        <h2>Edit User</h2>
                        <form method="post" action="admin">

                            <label for="username">Username: </label>
                            <input type="text" name="username" id="" value="${username}" readonly>

                            <br><br>
                            <label for="firstName">first name:  </label>
                            <input type="text" name="firstName" id="" value="${userFName}"  placeholder="First Name">
                            <br><br>
                            <label for="lastName">Lastname </label>
                            <input type="text" name="lastName" id="" value="${userLName}"  placeholder="First Name">
                            <br><br>




                            <label for="isActive">Active </label>
                            <select name="isUserActive" id="">



                                <c:choose>
                                    <c:when test="${isActive==true}">
                                        <option value="true" selected >  true</option>
                                        <option value="false">  false</option>
                                    </c:when>    
                                    <c:otherwise>
                                        <option value="true" >  true</option>
                                        <option value="false"  selected>  false</option>

                                    </c:otherwise>
                                </c:choose>

                            </select>
                            <br>
                            <br>
                            <label for="isAdmin"> Admin </label>
                            <select name="isUserAdmin" id="">



                                <c:choose>
                                    <c:when test="${isAdmin==true}">
                                        <option value="true" selected >  true</option>
                                        <option value="false">  false</option>
                                    </c:when>    
                                    <c:otherwise>
                                        <option value="true" >  true</option>
                                        <option value="false"  selected>  false</option>

                                    </c:otherwise>
                                </c:choose>







                            </select>








                            <br><br>
                            <input type="submit" value="Save">
                            <input type="hidden" name="saveUserNewInfo" value="${username}">



                        </form>

                        <form method="post" action="admin">
                            <input type="submit" name="clearFields" value="Cancel">
                        </form>  
                        <p>${errorMessage}</p>
                    </div>
                </c:if>
            </div>
        </div>
    </body>
</html>
