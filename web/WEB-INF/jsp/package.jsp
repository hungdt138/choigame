<%-- 
    Document   : gamepackage
    Created on : May 18, 2011, 1:11:30 PM
    Author     : Nguyen Dinh Doan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="./css/style.css" />
        <meta name="viewport" content="user-scalable=no, width=device-width"/>
        <title>Khuyến mại</title>
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
        <%@include file="include/header.jsp" %>


        <div class="menu">
            <a href="home.html">Trang chủ</a> 
            <img class="floatRight" src="./images/inf/common/muiTen.jpg"/>
        </div>		

<!--        <div class="welcome">Xin chào <b>${mobileNumer}</b></div>-->

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
        <br/>
        <div class="tab">
            <div class="selected">
                <div class="left">
                    <a class="tabUnselected" href="home.html?type=newest" >Mới nhất |</a>
                </div>
            </div>
            <div class="selected">
                <div class="left">
                    <a class="tabUnselected" href="home.html?type=hottest" >Hot nhất</a>
                </div>
            </div>
            <div class="selected">
                <div class="left"><a class="menuLink" href="package.html?type=package" >| Khuyến mại</a></div>
                <div class="right" ></div>
            </div>
        </div>


        <c:forEach items="${lstPackage}" var="pkg">
            <div class="gameitem">
                <div class="img_gameitem">
                    <a href="singlepackage.html?pId=${pkg.pId}"><img src="${imgUrl}${pkg.logoUrl}" width="50px" height="50px"/></a>
                </div>
                <span class="game_title"><a href="singlepackage.html?pId=${pkg.pId}">${pkg.name}</a></span><br>
                <span class="game_desc">${pkg.details}</span>
                <div class="game_title" style="color: red">
                    ${pkg.packagePrice} đ
                </div><br/>
            </div>
        </c:forEach>


        <a href="allpackage.html?page=1" class="viewAll">Xem tất cả </a>
        <span><img src="./images/inf/common/viewAll.jpg"/></span>

        <%@include file="include/search.jsp" %>
        <br/>
        <%@include file="include/catgories.jsp" %>
        <%@include file="include/Ad.jsp" %>
        <%@include file="include/footer.jsp" %>
    </body>
</html>
