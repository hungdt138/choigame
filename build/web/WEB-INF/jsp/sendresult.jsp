<%-- 
    Document   : sendresult
    Created on : May 20, 2011, 9:08:32 AM
    Author     : Do Tien Hung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
     <head>
        <link rel="stylesheet" type="text/css" href="./css/style.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="user-scalable=no, width=device-width"/>
        <title></title>
    </head>
    <body>
         <%@include file="include/header.jsp" %>

        <!----- Menu ------->
        <div class="menu">
            <a href="home.html">Trang chủ</a>
            <c:if test="${lst.game.dataId != null}">
            <span><img src="./images/inf/common/chiaMenu.jpg"/></span>
            <a href="gameincat.html?catId=${lst.game.catId}">  ${lst.game.catName}</a>
            <span><img src="./images/inf/common/chiaMenu.jpg"/></span>
            <a href="details.html?id=${lst.game.dataId}"> ${lst.game.name}</a>
           
            </c:if>
             <img class="floatRight" src="./images/inf/common/muiTen.jpg"/>
            </div>
        

        
        <%@include file="include/Ad1.jsp" %>
        <!------ SEARCH ---->
        <%@include file="include/search.jsp" %>
        
        <br>
        <div class="confirm">
		<center>
	<form action="" method="post" enable_wml="true">
               
                    <c:if test="${lst.status == '1'}">
                        <font color="blue">Bạn đã gửi tặng thành công đến số điện thoại: <c:out value="${lst.pn}"></c:out></font><br/>
                    </c:if>
                    <c:if test="${lst.status == '0'}">
                        <font color="red">Yêu cầu của bạn, chưa được thực hiện, bạn vui lòng thử lại!</font><br/>
                    </c:if>
                
            </form>
			</center>
                    
        </div>
                        
        <%@include file="include/catgories.jsp" %>
        <%@include file="include/Ad.jsp" %>
        <%@include file="include/footer.jsp" %>
    </body>
</html>
