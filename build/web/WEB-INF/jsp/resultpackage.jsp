<%-- 
    Document   : resultpackage
    Created on : May 20, 2011, 9:08:32 AM
    Author     : Do Tien Hung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
     <head>
        <link rel="stylesheet" type="text/css" href="./css/style.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>
         <%@include file="include/header.jsp" %>

        <!----- Menu ------->
        <div class="menu">
            <a href="home.html">Trang chủ</a> »<a href="gameincat.html?catId=${lst.game.catId}">  ${lst.game.catName}</a> »<a href="details.html?id=${lst.game.dataId}">  ${lst.game.name}</a></div>
            <img class="floatRight" src="./images/inf/common/muiTen.jpg"/>
        </div>	
        
        <!------ SEARCH ---->
        <%@include file="include/search.jsp" %>
        
        <br>
        <div class="confirm">
		<center>
	<form action="" method="post" enable_wml="true">
               
                    <c:if test="${lst.st == '1'}">
                        <font color="blue">Bạn đã download thành công gói game khuyến mại: <b><c:out value="${lst.package.name}"></c:out></b> với giá ${lst.package.packagePrice}</font><br/>
                    </c:if>
                    <c:if test="${lst.st == '0'}">
                        <font color="red">Yêu cầu của bạn, chưa được thực hiện, bạn vui lòng thử lại!</font><br/>
                    </c:if>
                
            </form>
			</center>
                    
        </div>
                        
        <%@include file="include/catgories.jsp" %>
        <%@include file="include/footer.jsp" %>
    </body>
</html>
