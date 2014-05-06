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
        <title>${catName}</title>
    </head>
    <body>
        <%@include file="include/header.jsp" %>

        <!----- Menu ------->
        <div class="menu">
            <a href="home.html">Trang chủ</a>
            <span><img src="./images/inf/common/chiaMenu.jpg"/></span>
            <a href="gameincat.html?catId=${catId}" />${catName}</a>
            <img class="floatRight" src="./images/inf/common/muiTen.jpg"/>
    </div>
            <c:if test="${catId == 12}">
                 <div class ="bannerbox">
                    <a href="http://v.mobix.vn/choigame/gameincat.html?page=1&catId=25" style="padding: 3px;"><img src="./images/hot/game-mien-phi.gif" width="100%" /></a>
                </div>
            </c:if>
            <c:if test="${catId != 12}">
                <%@include file="include/Ad1.jsp" %>
            </c:if>
    
    <!------ SEARCH ---->
    <%@include file="include/search.jsp" %>

    <!------ Hiển thị tất cả Game của thể loại đã chọn ---->
   <br/><br/>

    <c:if test="${check==true}">
        <c:if test="${lstGamesSize == 0}">
            <br/> <center>Không có game phù hợp với máy của bạn.</center>
        </c:if>
        <c:forEach items="${lstGames}" var="game" begin="0" end="${num - 1}">
              
            <div class="gameitem">

                <div class="img_gameitem">
                    <a href="details.html?id=${game.dataId}" ><img src="${imgUrl}${game.logoUrl}" width="50" /></a>
                </div>

                <span class="game_title"><a href="details.html?id=${game.dataId}" >${game.name}</a></span><br>
                <span class="game_desc"><c:out value="${game.gameInfo}"/></span>
                
                <div class="game_download_type_1">
                    <a class="link" href="gameincat.html?catId=${game.catId}"/>${game.catName}</a>
                <br/><br/>
               
                </div>                
            </div>
                
           </c:forEach>
            
        <br/><br/>
        <c:if test="${count > 1}">

        <div class="split">
            <c:if test="${isback}">
               
              <a href="gameincat.html?catId=${catId}&page=${page1-1}"><<&nbsp;</a>
             
            </c:if>
            <c:if test="${isback == false}">
            </c:if>
            <c:forEach begin="${splitStart}" end="${splitEnd}" var="i"> 
                
                <c:if test="${page == i}">
                    <a href="gameincat.html?catId=${catId}&page=${i}" class="item"><c:out value="${i}"/></a>
                </c:if>
                <c:if test="${page != i}">
                    <a href="gameincat.html?catId=${catId}&page=${i}"><c:out value="${i}"/></a>
                </c:if>
            </c:forEach>
                <c:if test="${isnext}">
                                    
            <a href="gameincat.html?catId=${catId}&page=${page1+1}"> >> &nbsp; &nbsp;</a>
                                
            </c:if>
            <c:if test="${istnext == false}">
            </c:if>
        </div>
            </c:if>
    </c:if>
        <c:if test="${check==false}">
          <c:forEach items="${lstGames}" var="game" begin="0" end="${num - 1}">
              
          <div class="gameitem">

                <div class="img_gameitem">
                    <a href="details.html?id=${game.dataId}" ><img src="${imgUrl}${game.logoUrl}" width="50" /></a>
                </div>

                <span class="game_title"><a href="details.html?id=${game.dataId}" >${game.name}</a></span><br>
                <span class="game_desc"><c:out value="${game.gameInfo}"/></span>
                
                <div class="game_download_type_1">
                    <a class="link" href="gameincat.html?catId=${game.catId}"/>${game.catName}</a>
                <div class="checksupport">
                    <c:if test="${game.check == true}">
                        <br>
                    </c:if>
                    <c:if test="${game.check == false}">
                   Game không hỗ trợ điện thoại của bạn
                   
                    </c:if>
                        </div>
                <br/>
               
                </div>                
            </div>
                
           </c:forEach>
            
        <br/><br/>
        <c:if test="${count > 1}">
        <div class="split">
               <c:if test="${isback}">
               
              <a href="gameincat.html?catId=${catId}&page=${page1-1}"><<&nbsp;</a>
             
            </c:if>
            <c:if test="${isback == false}">
            </c:if>
            <c:forEach begin="${splitStart}" end="${splitEnd}" var="i"> 
                <c:if test="${page == i}">
                    <a href="gameincat.html?catId=${catId}&page=${i}" class="item"><c:out value="${i}"/></a>
                </c:if>
                <c:if test="${page != i}">
                    <a href="gameincat.html?catId=${catId}&page=${i}"><c:out value="${i}"/></a>
                </c:if>
            </c:forEach>
              <c:if test="${isnext}">
                                    
            <a href="gameincat.html?catId=${catId}&page=${page1+1}"> >> &nbsp; &nbsp;</a>
                                
            </c:if>
            <c:if test="${istnext == false}">
            </c:if>
        </div>
        </c:if>
         
        </c:if>

    <!----- FOOTER ---->
    <%@include file="include/catgories.jsp" %>
    <%@include file="include/Ad.jsp" %>
    <%@include file="include/footer.jsp" %>
</body>
</html>
