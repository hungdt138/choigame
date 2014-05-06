<%-- 
    Document   : gameincat
    Created on : May 11, 2011, 3:14:27 PM
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
        <title>Tìm kiếm</title>
    </head>
    <body>
        <%@include file="include/header.jsp" %>

        <!----- Menu ------->
        <div class="menu">
            <span><a href="home.html">Trang chủ</a></span>
            <img src="./images/inf/common/chiaMenu.jpg"/>
            <span><a href="home.html">Tìm kiếm</a></span>
            <img src="./images/inf/common/chiaMenu.jpg"/>
            <span><a href="gameincat.html?catId=${catId}" /> ${catName}</a></span>
            <!--            <img class="floatRight" src="./images/inf/common/muiTen.jpg"/>-->
        </div>	
        <%@include file="include/Ad1.jsp" %>
        <!------ SEARCH ---->
        <%@include file="include/search.jsp" %>
        <br/><br/>
        <c:if test="${check == true}">
            <p>Từ khóa: <b><font color="red">${searchQuery}</font></b> có <b>${resultSize}</b> kết quả phù hợp với điện thoại của bạn</p>
                    <c:forEach items="${lstGames}" var="game" begin="0" >
                <div class="gameitem">
                    <div class="img_gameitem">
                        <a href="details.html?id=${game.dataId}" ><img src="${imgUrl}${game.logoUrl}" width="50px" height="50px"/></a>
                    </div>

                    <span class="game_title"><a href="details.html?id=${game.dataId}" >${game.name}</a></span><br>
                    <span class="game_desc">${game.gameInfo}</span>

                    <div class="game_download_type_1">
                        <a style="color: darkred; font-weight: normal" href="gameincat.html?catId=${game.catId}">${game.catName}</a>
                    </div>
                    <br/>
                </div>
            </c:forEach>
            <c:if test="${count > 1}">
                <div class="split">    
                    <c:if test="${isback}">
                        <a href="searchresults.html?page=${page1-1}&txtSearch=${searchQuery}&radio=${radio}"><< &nbsp; &nbsp; </a>
                    </c:if>
                    <c:if test="${isback == false}">

                    </c:if>
                    <c:forEach begin="${splitStart}" end="${splitEnd}" var="i"> 
                        <c:if test="${page == i}">
                            <a href="searchresults.html?page=${i}&txtSearch=${searchQuery}&radio=${radio}" class="item">${i}</a>
                        </c:if>
                        <c:if test="${page != i}">
                            <a href="searchresults.html?page=${i}&txtSearch=${searchQuery}&radio=${radio}">${i}</a>
                        </c:if>
                    </c:forEach>
                    <c:if test="${isnext}">

                        <a href="searchresults.html?page=${page1+1}&txtSearch=${searchQuery}&radio=${radio}">&nbsp; &nbsp; >></a>

                    </c:if>
                    <c:if test="${isnext == false}">
                    </c:if>

                </div>
            </c:if>
        </c:if>
        <c:if test="${check == false}">
            <p>Từ khóa: <b><font color="red">${searchQuery}</font></b> có <b>${resultSize}</b> kết quả</p>
                    <c:forEach items="${lstGames}" var="game" begin="0">
                <div class="gameitem">
                    <div class="img_gameitem">
                        <a href="details.html?id=${game.dataId}" ><img src="${imgUrl}${game.logoUrl}" width="50px" height="50px"/></a>
                    </div>

                    <span class="game_title"><a href="details.html?id=${game.dataId}" >${game.name}</a></span><br>
                    <span class="game_desc">${game.gameInfo}</span>

                    <div class="game_download_type_1">
                        <a style="color: darkred; font-weight: normal" href="gameincat.html?catId=${game.catId}">${game.catName}</a>
                        <div class="checksupport">
                            <c:if test="${game.check == true}">
                                <br>
                            </c:if>
                            <c:if test="${game.check == false}">
                                Game không hỗ trợ điện thoại của bạn

                            </c:if>
                        </div>
                    </div>
                </div>
            </c:forEach>
            <c:if test="${count > 1}">
                <div class="split">    
                    <c:if test="${isback}">
                        <a href="searchresults.html?page=${page1-1}&txtSearch=${searchQuery}&radio=${radio}"><< &nbsp; &nbsp; </a>
                    </c:if>
                    <c:if test="${isback == false}">

                    </c:if>
                    <c:forEach begin="${splitStart}" end="${splitEnd}" var="i"> 
                        <c:if test="${page == i}">
                            <a href="searchresults.html?page=${i}&txtSearch=${searchQuery}&radio=${radio}" class="item">${i}</a>
                        </c:if>
                        <c:if test="${page != i}">
                            <a href="searchresults.html?page=${i}&txtSearch=${searchQuery}&radio=${radio}">${i}</a>
                        </c:if>
                    </c:forEach>
                    <c:if test="${isnext}">

                        <a href="searchresults.html?page=${page1+1}&txtSearch=${searchQuery}&radio=${radio}">&nbsp; &nbsp; >></a>

                    </c:if>
                    <c:if test="${isnext == false}">
                    </c:if>
                </c:if>
            </div>

        </c:if>
        <!----- FOOTER ------->
        <%@include file="include/catgories.jsp" %>
        <%@include file="include/Ad.jsp" %>
        <%@include file="include/footer.jsp" %>
    </body>
</html>
