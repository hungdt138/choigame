<%-- 
    Document   : grouponreturn
    Created on : Dec 30, 2011, 3:20:22 PM
    Author     : noname
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="./css/style.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="user-scalable=no, width=device-width"/>
        <title>Thong tin dang ki goi mua chung game</title>
    </head>
    <body>
         <%@include file="include/header.jsp" %>

        <!----- Menu ------->
        <div class="menu">
            <a href="home.html">Trang chủ</a>
           
            <span><img src="./images/inf/common/chiaMenu.jpg"/></span>
<!--            <a href="gameincat.html?catId=${lst.game.catId}">  ${lst.game.catName}</a>
            
            <span><img src="./images/inf/common/chiaMenu.jpg"/></span>
            <a href="details.html?id=${lst.game.dataId}"> ${lst.game.name}</a>-->
            <a href="#">Mua chung</a>
           
            
<!--             <img class="floatRight" src="./images/inf/common/muiTen.jpg"/>-->
            </div>
        

        
        <%@include file="include/Ad1.jsp" %>
        <!------ SEARCH ---->
        <%@include file="include/search.jsp" %>
        
        <br>
        <div class="confirm">
		<center>
                    <c:if test="${act == '1'}">
                        <div style="padding: 7px;">Cảm ơn bạn đã tham gia vào gói mua chung <b>${gr.name}</b>, đường dẫn tải game sẽ gửi tới quý khách khi chương trình đạt được số lượng mua tối thiểu.<br/></div>
                        <div><a href="groupondetail.html?id=${gr.id}">Quay lại trang trước</a> </div>
                    </c:if>
                    <c:if test="${act == '0'}">
                        <font color="red">Yêu cầu của bạn, chưa được thực hiện, bạn vui lòng thử lại!</font><br/>
                    </c:if>
			</center>
                    
        </div>
                        
        <%@include file="include/catgories.jsp" %>
        <%@include file="include/Ad.jsp" %>
        <%@include file="include/footer.jsp" %>
    </body>
</html>
