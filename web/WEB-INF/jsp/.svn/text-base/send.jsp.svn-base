<%-- 
    Document   : send
    Created on : May 24, 2011, 2:17:46 PM
    Author     : Do Tien Hung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
         <div class="menu">
            <a href="home.html">Trang chủ</a>
            <span><img src="./images/inf/common/chiaMenu.jpg"/></span>
            <a href="gameincat.html?catId=${lst.game.catId}">  ${lst.game.catName}</a> 
            <span><img src="./images/inf/common/chiaMenu.jpg"/></span>
            <a href="details.html?id=${lst.game.dataId}">  ${lst.game.name}</a>
            
        </div>
        <%@include file="include/Ad1.jsp" %>
        <%@include file="include/search.jsp" %>
        <br/>
          <div class="gamedetail">
            <img border="0" width="95px" height="85px" style="margin-right: 10px;" src="${lst.game.logoUrl}" class="floatLeft img_game">
             <span class="game_title">
                        <a href="#">${lst.game.name} </a>
              </span><br>
					<span class="game_title">
						Game: <b>${lst.game.catName}</b>
					</span><br>
                                        <span class="game_title">
						Nhà cung cấp: <b>${lst.game.supplier}</b>
					</span><br>
					<span class="game_title">
						Giá: <b>${lst.price} VNĐ</b>
					</span>
            <br><br>
                    
                    <br><br>
                                        	<div class="game_download_type_1">
						<span style="color: #1387e5; font-weight: bold;"> Mô tả</span>
					</div>
                                        <br>
                                        <form action="" method="post" enable_wml="true">
                                        <span>Số điện thoại sẽ nhận quà của bạn:</span><br>
					<input name="phone" id="send" class="textbox" maxlength="12" value="" format="NNNN-NNN-NNNNN" />
                                        <input type="hidden" name="session" value="gfsa87837" />
                                        <br>
                                        <font color="blue">${lst.warning}</font><br/>
					<input name="Seachbutton" type="submit" value="Gửi tặng" class="buttonconfirm" />
					<br>
					<span style="color:#FF0000">Ví dụ: 0983171490</span>
					</form>
					<br><br>
                    
          </div>
                    
                    
                       <br>
            <div class="gameOther"> 
			<div class="title"><span> GAME CÙNG THẺ LOẠI</span></div>
			<div class="listOther"> 
                            <c:forEach items="${lst.relatedCat}" var = "related" step="1" begin="0">
                                <span  > <img border="0" align="" title="" src="./images/inf/common/theloai.gif" class="menu_bottom_bullet"></span>
				<a href="details.html?id=${related.dataId}"><span class="lnkOther"> ${related.name}</span></a>
				<br>
                            </c:forEach>
				
				
			</div>
		</div>
        
         <%@include file="include/catgories.jsp" %>
         <%@include file="include/Ad.jsp" %>
        <%@include file="include/footer.jsp" %>
    </body>
</html>
