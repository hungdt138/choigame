<%-- 
    Document   : singlepackage
    Created on : May 12, 2011, 4:21:50 PM
    Author     : Nguyen Dinh Doan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String host = "http://" + request.getServerName() + ":" + request.getLocalPort() + request.getContextPath() + "?" + request.getQueryString();
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="./css/style.css" />
        <meta name="viewport" content="user-scalable=no, width=device-width"/>
        <title>${pkg.name}</title>
    </head>
    <body>
        <%@include file="include/header.jsp" %>
        <!----- Menu ------->
        <div class="menu">
            <a href="home.html">Trang chủ</a>
            <span><img src="./images/inf/common/chiaMenu.jpg"/></span>
            <a href="package.html">Khuyến mại</a>
            <span><img src="./images/inf/common/chiaMenu.jpg"/></span>
            <a href="">Chi tiết</a>
            <img class="floatRight" src="./images/inf/common/muiTen.jpg"/>
        </div>
        <%@include file="include/Ad1.jsp" %>
        <!------ SEARCH ---->
        <%@include file="include/search.jsp" %>
        <br/><br/>
        <c:if test="${size == 0}">
            <br/>
            <span style="color: red;">Gói khuyến mại không có game phù hợp với máy bạn, bạn có thể chọn các gói khuyến mại khác thích hợp hơn.</span> <br/><br/>
            </c:if>
            <c:if test="${size !=0}">
              
                <p><span class="game_title" style="color: #00CC33;font-size: 12px;"><b>${pkg.name}</b></span><br/>
              <span class="intro">Giới thiệu</span>
            <br/>${pkg.details} <br/>
            <br><span class="gamePackPrice">Giá chỉ: &nbsp;<span style="color: red; font-weight: bold;">${pkg.packagePrice} đ</span></span></p>
        <form method="post" action="singlepackage.html" >
            
           
            <c:if test="${checkmobile == true}">
                <c:forEach items="${lstGamesInPack}" var="game">
                <div class="gameitem">
                    <div class="img_gameitem">
                        <a href="details.html?id=${game.dataId}&pid=${pkg.pId}" ><img src="${imgUrl}${game.logoUrl}" width="50px" height="50px"/></a>
                    </div>
                    <span class="game_title"><a href="details.html?id=${game.dataId}&pid=${pkg.pId}" >${game.name}</a></span><br>
                    <span class="game_desc"><c:out value="${game.gameInfo}"/></span>
                    <div class="game_download_type_1">
                        <a style="color: darkred; font-weight: normal" href="gameincat.html?catId=${game.catId}">${game.catName}</a>
                    
                    
                </div>
                        <br/>
                </div>
                </c:forEach>
                </c:if>
                    <c:if test="${checkmobile == false}">
                         <c:forEach items="${lstGamesInPack}" var="game">
                <div class="gameitem">
                    <div class="img_gameitem">
                        <a href="details.html?id=${game.dataId}&pid=${pkg.pId}" ><img src="${imgUrl}${game.logoUrl}" width="50px" height="50px"/></a>
                    </div>
                    <span class="game_title"><a href="details.html?id=${game.dataId}&pid=${pkg.pId}" >${game.name}</a></span><br>
                    <span class="game_desc"><c:out value="${game.gameInfo}"/></span>
                    <div class="game_download_type_1">
                        <a style="color: darkred; font-weight: normal" href="gameincat.html?catId=${game.catId}">${game.catName}</a>
                    <div class="checksupport">
                    
                    <c:if test="${game.check == false}">
                   Game không hỗ trợ điện thoại của bạn
                    </c:if>
                        </div>
                    </div>
                </div>
            </c:forEach>
                    </c:if>
            <br>

            <c:if test="${check == true}">
                <div>
                    <span>-----------</span><br>
                    <span style="color: red">Bạn đã tải gói game này, bạn được download các game thuộc gói trong vòng 1 ngày</span>   
                </div>
            </c:if>
            <c:if test="${check == false}">
                <div class="pkgButton">
                    <a href="singlepackage.html?catid=2&pId=${pkg.pId}"><span class="btn">TẢI NGAY</span></a><br>
                </div>
            </c:if>

        </form>
            </c:if>
        <div class="intro">Các gói khuyến mại khác</div>
        <c:forEach items="${lstRelatedPkg}" var="pkg">
            <div class="gameitem">

                <div class="img_gameitem">
                    <a href="singlepackage.html"><img src="${imgUrl}${pkg.logoUrl}" width="50px" height="50px"/></a>
                </div>

                <span class="game_title"><a href="singlepackage.html?pId=${pkg.pId}">${pkg.name}</a></span><br>
                <span class="game_desc">${pkg.details}</span><br/>
                <span style="color:red; font-weight: bold;">${pkg.packagePrice} đ<br/><br/></span>
            </div>
                            

        </c:forEach>

        <!--------- FOOTER -------->
        <%@include file="include/catgories.jsp" %>
        <%@include file="include/Ad.jsp" %>
        <%@include file="include/footer.jsp" %>
    </body>
</html>
