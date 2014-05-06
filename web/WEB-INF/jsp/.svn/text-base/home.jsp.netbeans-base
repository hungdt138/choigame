<%@page import="model.bean.Game"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="./css/style.css" />
        <meta name="viewport" content="user-scalable=no, width=device-width"/>
        <title>ChoiGame Portal</title>
        <script type="text/javascript">
            //######################################################################################
            // Author: ricocheting.com
            // Version: v2.0
            // Date: 2011-03-31
            // Description: displays the amount of time until the "dateFuture" entered below.

            // NOTE: the month entered must be one less than current month. ie; 0=January, 11=December
            // NOTE: the hour is in 24 hour format. 0=12am, 15=3pm etc
            // format: dateFuture1 = new Date(year,month-1,day,hour,min,sec)
            // example: dateFuture1 = new Date(2003,03,26,14,15,00) = April 26, 2003 - 2:15:00 pm

            
            dateFuture1 = new Date(2012,0,4,15,43,19);
            function getparam(year, month, day, hour, minute, second){
               date1 = new Date(year, month, day, hour, minute, second);
               //alert(year);
            }

            // TESTING: comment out the line below to print out the "dateFuture" for testing purposes
            //document.write(dateFuture +"<br />");


            //###################################
            //nothing beyond this point
            function GetCount(year, month, day, hour, minute, second,iid){
                
                ddate = new Date(year, month, day, hour, minute, second);
                dateNow = new Date();	//grab current date
                amount = ddate.getTime() - dateNow.getTime();	//calc milliseconds between dates
                delete dateNow;

                // if time is already past
                if(amount < 0){
                    document.getElementById(iid).innerHTML="Hết thời gian!";
                }
                // else date is still good
                else{
                    days=0;hours=0;mins=0;secs=0;out="";

                    amount = Math.floor(amount/1000);//kill the "milliseconds" so just secs

                    days=Math.floor(amount/86400);//days
                    amount=amount%86400;

                    hours=Math.floor(amount/3600);//hours
                    amount=amount%3600;

                    mins=Math.floor(amount/60);//minutes
                    amount=amount%60;

                    secs=Math.floor(amount);//seconds

                    if(days != 0){out += days +"  "+((days==1)?"ngày":"ngày")+", ";}
                    out += ((hours<10)?"0":"")+ hours +" :";
                    out += ((mins<10)?"0":"") + mins +" :";
                    out += ((secs<10)?"0":"")+secs +" :";
                    out = out.substr(0,out.length-2);
                    document.getElementById(iid).innerHTML="Thời gian còn lại: <font size = 1 ><b>"+ out + "</b></span>";

                    setTimeout(function(){GetCount(year, month, day, hour, minute, second,iid)}, 1000);
                }
            }

//            window.onload=function(){
//                GetCount(2012,0,4,15,43,19, 'countbox1');
//                //you can add additional countdowns here (just make sure you create dateFuture2 and countbox2 etc for each)
//            };
        </script>
    </head>

    <body onload="GetCount(${year},${month},${day},${hour},${minute},${second}, 'countbox1');">
        <div id="main">
            <%@include file="include/header.jsp" %>
            <div class="menu">
                <span class="floatLeft"><a href="home.html">Trang chủ</a> </span>
                <span class="floatRight" style="clear: right;"><img src="./images/inf/common/muiTen.jpg"/></span>
            </div>

<!--            <div class="welcome">Xin chào <b>${mobileNumer}</b>  
                 <c:if test="${mobiManu != 'unknown'}">
                    Có <b><u>${count} game</u></b> giành cho ${mobiManu} ${mobileModel}
                </c:if>
                <c:if test="${mobiManu == 'unknown'}">
                    
                </c:if>
            </div>-->
            <%@include file="include/Ad1.jsp" %>
            

            <c:forEach items="${BlockGroupon}" var="block" begin="0" step="1">
                <c:if test="${block.status == 1}">
                    <c:if test="${block.id == 1}">
                        <%@include file="include/Candidate.jsp" %>
                    </c:if>
                    <c:if test="${block.id == 2}">
                        <%@include file="include/Groupon.jsp" %>       
                    </c:if>
                </c:if>
            </c:forEach>
            
            <p>
            <div class="tab" style="clear: both;">
                <c:if test="${typeOfView == 'newest'}">
                    <div class="selected">
                        <div class="left"><a class="menuLink" href="home.html?type=newest" >Mới nhất</a></div>
                        <div class="right" ></div>
                    </div>
                    <div class="unselected"><a class="tabUnselected" href="home.html?type=hottest" >Hot nhất |</a></div>
                    <div class="unselected"><a class="tabUnselected" href="package.html" >Khuyến mại</a></div>
                </c:if>

                <c:if test="${typeOfView == 'hottest'}">
                    <div class="selected">
                        <div class="left">
                            <a class="tabUnselected" href="home.html?type=newest" > Mới nhất</a>
                        </div>
                    </div>
                    <div class="selected">
                        <div class="left"><a class="menuLink" href="home.html?type=hottest" >| Hot nhất</a></div>

                        <div class="right" ></div>
                    </div>
                    <div class="unselected"><a class="tabUnselected" href="package.html" >Khuyến mại</a></div>
                </c:if>
            </div>
        </p>
        <c:if test="${check==true}">
            <c:forEach items="${lst}" var="game" begin="0" step="1">  
                <div class="gameitem">
                    <div class="img_gameitem" >
                        <a href="details.html?id=${game.dataId}" ><img border="0" width="50" height="50" src="${imgUrl}${game.logoUrl}"  /></a>
                    </div>
                    <span class="game_title"><a href="details.html?id=${game.dataId}">${game.name}</a></span><br/>
                    <span class="game_desc">${game.gameInfo}</span><br/>
                    <div class="game_download_type_1">
                        <a style="color: darkred; font-weight: normal" href="gameincat.html?catId=${game.catId}&page=1">${game.catName}</a>

                    </div>
                    <br/>
                </div>                

            </c:forEach>
        </c:if>
        <c:if test="${check==false}">
            <c:forEach items="${lst}" var="game" begin="0" step="1">  

                <div class="gameitem">
                    <div class="img_gameitem" >
                        <a href="details.html?id=${game.dataId}" ><img border="0" width="50" height="50" src="${imgUrl}${game.logoUrl}"  /></a>
                    </div>
                    <span class="game_title"><a href="details.html?id=${game.dataId}">${game.name}</a></span><br/>
                    <span class="game_desc">${game.gameInfo}</span><br/>
                    <div class="game_download_type_1">
                        <a style="color: darkred; font-weight: normal" href="gameincat.html?catId=${game.catId}&page=1">${game.catName}</a>
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
        </c:if>
        <c:if test="${typeOfView == 'newest'}">
            <a href="allnewest.html?type=newest&page=1" class="viewAll">Xem tất cả </a>
            <span><img src="./images/inf/common/viewAll.jpg"/></span>
            </c:if>
            <c:if test="${typeOfView == 'hottest'}">
            <a href="allhottest.html?type=hottest&page=1" class="viewAll">Xem tất cả </a>
            <span><img src="./images/inf/common/viewAll.jpg"/></span>
            </c:if>

        
        <%@include file="include/manufacture.jsp" %>
        <%@include file="include/search.jsp" %>
        <br/>
        <%@include file="include/catgories.jsp" %>
        
        <%@include file="include/Ad.jsp" %>
        <%@include file="include/footer.jsp" %>
    </div>
</body>
</html>
