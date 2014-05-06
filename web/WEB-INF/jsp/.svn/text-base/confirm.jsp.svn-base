<%-- 
    Document   : confirm
    Created on : May 20, 2011, 8:45:56 AM
    Author     : Do Tien Hung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="./css/style.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="viewport" content="user-scalable=no, width=device-width"/>
        <title></title>
    </head>
    <body>
        <%@include file="include/header.jsp" %>
        <div class="menu">
            <a href="home.html">Trang chủ</a> 
            <span><img src="./images/inf/common/chiaMenu.jpg"/></span>
            <c:if test="${lst.type == 'game'}">
            <a href="gameincat.html?catId=${lst.game.catId}">  ${lst.game.catName}</a> 
            <span><img src="./images/inf/common/chiaMenu.jpg"/></span>
            <a href="details.html?id=${lst.game.dataId}">  ${lst.game.name}</a>
            </c:if>

        </div>
        <%@include file="include/Ad1.jsp" %>   
        <%@include file="include/search.jsp" %>
        <br>
        <div class="confirm">
            <c:if test="${lst.type == 'game'}">
                <c:if test="${lst.status == null}">
                    <center>
                        <span> Tải game <b><c:out value="${lst.game.name}"/></b> với mức phí: <b><c:out value="${lst.pr}"/> VNĐ</b> được trừ trực tiếp vào tài khoản của bạn. Vui lòng xác nhận trước khi tải game </span>
                        <br><br>
                        <a href="${lst.chargeurl}" class="buttonconfirm">Tải về</a>
                        <br /><br />
                        <span class="back floatLeft"><a href="index.html">Về đầu trang</a></span><br />
                        <span class="back floatLeft"><a href="detail.html">Trở lại trang trước</a></span>
                        <br>
                    </center>
                </c:if>
                <c:if test="${lst.status != null}">
                    <center>
                        <span> Tải gói game khuyến mại <b><c:out value="${lst.gamepkg.name}"/></b> với mức phí: <b><c:out value="${lst.pr}"/> VNĐ</b> được trừ trực tiếp vào tài khoản của bạn. Vui lòng xác nhận trước khi tải. </span>
                        <br/><br/>
                        <a href="${lst.chargeurl}" class="buttonconfirm">Tải về</a>
                        <br /><br />
                        <span class="back floatLeft"><a href="index.html">Về đầu trang</a></span><br />
                        <span class="back floatLeft"><a href="detail.html">Trở lại trang trước</a></span>
                        <br>
                    </center>
                </c:if>
            </c:if>
            <c:if test="${lst.type == 'g'}">
                <center>
                    <span>Cảm ơn bạn đã tham gia <b>${lst.group.name}</b></span><br/>
                    <span>Gói mua chung có mức phí: <b><c:out value="${lst.pr}"/> VNĐ</b> sẽ được trừ trực tiếp vào tài khoản của bạn. Vui lòng xác nhận trước khi tham gia gói </span>
                    <br/><br/>
                    <a href="${lst.chargeurl}" class="buttonconfirm">Đồng ý</a>
                    <br /><br />
                    <span class="back floatLeft"><a href="index.html">Về đầu trang</a></span><br />
                    <span class="back floatLeft"><a href="detail.html">Trở lại trang trước</a></span>
                    <br/>
                </center>
            </c:if>

        </div>
        <%@include file="include/catgories.jsp" %>
        <%@include file="include/Ad.jsp" %>
        <%@include file="include/footer.jsp" %>
    </body>
</html>
