<%-- 
    Document   : details
    Created on : May 19, 2011, 9:37:02 AM
    Author     : Do Tien Hung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="./css/style.css" />
        <meta name="viewport" content="user-scalable=no, width=device-width"/>
        <title>CHOIGAME - Thuc tinh dam me</title>
    </head>
    <body>
        <%@include file="include/header.jsp" %>

        <!----- Menu ------->
        <div class="menu">
            <a href="home.html">Trang chủ</a>
            <span><img src="./images/inf/common/chiaMenu.jpg"/></span>
            <a href="gameincat.html?catId=${lst.game.catId}">  ${lst.game.catName}</a>
            <span><img src="./images/inf/common/chiaMenu.jpg"/></span>
            <a href="details.html?id=${lst.game.dataId}">  ${lst.game.name}</a>

        </div>

        <%@include file="include/Ad1.jsp" %>
        <!------ SEARCH ---->
        <%@include file="include/search.jsp" %>

        <!---- Game Details ----->
        <br/><br/>
        <div class="gamedetail">
            <img border="0" width="95px" height="85px" style="margin-right: 10px;" src="${lst.game.logoUrl}" class="floatLeft img_game" />
            <span class="game_title">
                <a href="#">${lst.game.name} </a>
            </span><br/>
            <span class="game_title">
                Game: <b>${lst.game.catName}</b>
            </span><br/>
            <span class="game_title">
                Nhà cung cấp: <b>${lst.game.supplier}</b>
            </span><br/>
            <c:if test="${lst.pid == '0'}">
                <span class="game_title">
                    Giá: <b>${lst.game.gamePrice} VNĐ</b>
                </span>
            </c:if>
            <c:if test="${lst.pid != '0'}">
                <c:if test="${lst.check == true}">
                    <span class="game_title" style="color: red">
                        Game khuyến mại
                    </span>
                </c:if>
                <c:if test="${lst.check == false}">
                    <span class="game_title">
                        Giá: <b>${lst.game.gamePrice} VNĐ</b>
                    </span>
                </c:if>
            </c:if>

            <br/><br/>
            <c:if test="${lst.pid == '0'}">
                <span class="icon1">
                    <c:if test="${lst.checkmobile == true}">
                        <a href="${lst.confirmurl}?id=${lst.game.dataId}&url=${lst.game.downloadUrl}&pr=${lst.game.gamePrice}&catid=1&type=game">Tải</a>
                        &nbsp;&nbsp;
                    </c:if>
                    <c:if test="${lst.checkmobile == false}">
                    </c:if>
                    <a href="send.html?uri=${lst.uri}&url=${lst.game.downloadUrl}&pr=${lst.game.gamePrice}&id=${lst.game.dataId}&catid=1&type=game">Tặng</a>
                </span>
            </c:if>
            <c:if test="${lst.pid != '0'}">
                <c:if test="${lst.check == true}">
                    <span class="icon1">
                        <c:if test="${lst.checkmobile == true}">
                            <a href="${lst.confirmurl}?id=${lst.game.dataId}&url=${lst.game.downloadUrl}&pr=${lst.game.gamePrice}&catid=1&type=game">Tải</a>
                            &nbsp;&nbsp;
                        </c:if>
                        <c:if test="${lst.checkmobile == false}">
                        </c:if>

                        <a href="send.html?uri=${lst.uri}&url=${lst.game.downloadUrl}&pr=0&id=${lst.game.dataId}&catid=1&type=game"> Tặng</a>
                    </span>
                </c:if>
                <c:if test="${lst.check == false}">
                    <span class="icon1">
                        <c:if test="${lst.checkmobile == true}">
                            <a href="${lst.confirmurl}?id=${lst.game.dataId}&url=${lst.game.downloadUrl}&pr=${lst.game.gamePrice}&catid=1&type=game">Tải</a>
                            &nbsp;&nbsp;
                        </c:if>
                        <c:if test="${lst.checkmobile == false}">
                        </c:if>
                        <a href="send.html?uri=${lst.uri}&url=${lst.game.downloadUrl}&pr=${lst.game.gamePrice}&id=${lst.game.dataId}&catid=1&type=game"> Tặng</a>
                    </span>
                </c:if>

            </c:if>
            <br/><br/>
            <div class="checksupport">
                <c:if test="${lst.checkmobile == true}"></c:if>
                <c:if test="${lst.checkmobile == false}">
                    <span >Game không hỗ trợ điện thoại của bạn</span>
                </c:if>

            </div>
            <div class="game_download_type_1">
                <span style="color: #1387e5; font-weight: bold;"> Mô tả</span>
            </div>
            <br/>
            <span class="game_desc"> ${lst.game.gameInfo} </span>



            <div class="game_download_type_1">
                <br/>
                <span style="color: #368B36; font-weight: bold;"> <a href="mobilesupport.html?id=${lst.game.dataId}">Dòng máy hỗ trợ</a></span>
            </div>
        </div>  
        <br/>
        <div class="gameOther"> 
            <div class="title"><span> GAME CÙNG THẺ LOẠI</span></div>
            <div class="listOther"> 
                <c:forEach items="${lst.relatedCat}" var = "related" step="1" begin="0">
                    <span  > <img border="0" align="" title="" src="./images/inf/common/theloai.gif" class="menu_bottom_bullet"></span>
                    <a href="details.html?id=${related.dataId}"><span class="lnkOther"> ${related.name}</span></a>
                    <br/>
                    
                </c:forEach>
                    

            </div>

        </div>


        <%@include file="include/catgories.jsp" %>
        <%@include file="include/Ad.jsp" %>
        <%@include file="include/footer.jsp" %>

    </body>
</html>
