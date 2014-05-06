<%-- 
    Document   : mobilesupport
    Created on : May 27, 2011, 2:07:53 PM
    Author     : Do Tien Hung
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="./css/style.css" />
        <meta name="viewport" content="user-scalable=no, width=device-width"/>
        <title>Game HomePage</title>
    </head>
    <body>
        <%@include file="include/header.jsp" %>

        <!----- Menu ------->
        <div class="menu">
            <a href="home.html">Trang chủ</a>
            <span><img src="./images/inf/common/chiaMenu.jpg"/></span>
            <a href="gameincat.html?catId=${lst.game.catId}">  ${lst.game.catName}</a>
            <span><img src="./images/inf/common/chiaMenu.jpg"/></span>
            <a href="details.html?id=${lst.game.dataId}">${lst.game.name}</a>
            <img class="floatRight" src="./images/inf/common/muiTen.jpg"/>
        </div>
            <%@include file="include/Ad1.jsp" %>
        <!------ SEARCH ---->
        <%@include file="include/search.jsp" %>

        <div class="cont">
            <br>
            
            
            <c:if test="${lst.check == true}">
                <span style="color: red">Các dòng máy hỗ trợ cho game <b>${lst.game.name}</b></span>
            <c:if test="${lst.mobile != null}">
                <c:forEach items="${lst.mobile}" var="mobile">
                    <p><b>${fn:toUpperCase(mobile.key)}:&nbsp;</b>
                        <c:forEach items="${mobile.value}" var="model">
                            ${model}, &nbsp;
                        </c:forEach>
                    </p>
                </c:forEach>
            </c:if>
                    </c:if>
                <c:if test="${lst.check == false}">
                    
                    <span style="color: red">Thông tin đang được cập nhật</span>
                </c:if>
        </div>
        <%@include file="include/catgories.jsp" %>
        <%@include file="include/Ad.jsp" %>
        <%@include file="include/footer.jsp" %>

    </body>
</html>
