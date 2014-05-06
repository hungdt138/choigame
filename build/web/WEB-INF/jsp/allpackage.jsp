<%-- 
    Document   : allpackage
    Created on : May 12, 2011, 1:49:00 PM
    Author     : Nguyen Dinh Doan
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="./css/style.css" />
        <meta name="viewport" content="user-scalable=no, width=device-width"/>
        <title>Khuyến mại</title>
    </head>
    <body>
        <%@include file="include/header.jsp" %>

        <!----- Menu ------->
        <div class="menu">
                <a href="home.html">Trang chủ</a>
                <span><img src="./images/inf/common/chiaMenu.jpg"/></span>
                <a href="">Khuyến mại</a>
                <img class="floatRight" src="./images/inf/common/muiTen.jpg"/>
        </div>	
        <%@include file="include/Ad1.jsp" %>
        <!------ SEARCH ---->
        <%@include file="include/search.jsp" %>

        <!------ Hiển thị tất cả Các Packages ---->
        <c:forEach items="${lstPackage}" var="pkg" >
            <div class="gameitem">
                    <div class="img_gameitem">
                        <a href="singlepackage.html?pId=${pkg.pId}"><img src="${imgUrl}${pkg.logoUrl}" width="50px" height="50px"/></a>
                    </div>
                <span class="game_title"><a href="singlepackage.html?pId=${pkg.pId}">${pkg.name}</a></span><br>
                <span class="game_desc">${pkg.details}</span><br>
                <span style="font-weight: bold; color: red;">
                    &nbsp; ${pkg.packagePrice} &nbsp; đ
                </span>
<!--                <div class="game_download_type_1">
                    <span class="floatRight icon2 ">
                        <img src="./images/inf/game/tai.jpg" />
                        &nbsp;&nbsp;
                        <img src="./images/inf/game/tang.jpg" />
                    </span>
                </div>-->
            </div>
        </c:forEach>
        <c:if test="${count > 1}">
        <div class="split">    
             <c:if test="${isback}">
                    <a href="allpackage.html?page=${page1-1}"><< &nbsp; &nbsp; </a>
                </c:if>
                <c:if test="${isback == false}">
                    
                </c:if>
            <c:forEach begin="${splitStart}" end="${splitEnd}" var="i"> 
                <c:if test="${page == i}">
                    <a href="allpackage.html?page=${i}" class="item">${i}</a>
                </c:if>
                <c:if test="${page != i}">
                    <a href="allpackage.html?page=${i}">${i}</a>
                </c:if>
            </c:forEach>
              <c:if test="${isnext}">

                        <a href="allpackage.html?page=${page1+1}">&nbsp; &nbsp; >></a>

                    </c:if>
                        <c:if test="${isnext == false}">
                </c:if>
        </div>
        </c:if>

        <!----- FOOTER ---->
        <%@include file="include/catgories.jsp" %>
        <%@include file="include/Ad.jsp" %>
        <%@include file="include/footer.jsp" %>
    </body>
</html>
