<%-- 
    Document   : groupondetail
    Created on : Dec 29, 2011, 1:38:12 PM
    Author     : noname
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="./css/style.css" />
        <meta name="viewport" content="user-scalable=no, width=device-width"/>
        <title>Mua chung game - cung mua cung re</title>
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
    <body id ="${tab}" onload="GetCount(${year},${month},${day},${hour},${minute},${second}, 'countbox1');">
        <%@include file="include/header.jsp" %>
        <!----- Menu ------->
        <div class="menu">
            <a href="home.html">Trang chủ</a>
            <span><img src="./images/inf/common/chiaMenu.jpg"/></span>
            <a href="#">Mua chung</a>

        </div>

        <%@include file="include/Ad1.jsp" %>
        <div style="margin-top:0px;clear: both;" >
            <div class="title_feature"><a href="#">${gr.name}, Game ${gr.namegame}</a></div>
            <div class="img_feature"> 
                <div id="team_images_" class="deal-buy-cover-img team_images">
                    <a href="/ha-noi/son-duong-moi-collagen_1870.html">
                        <img width="110" height="95" src="${gr.logurl}"></a>
                </div>
            </div>
            <div style="padding-left: 115px;">
                <div class="price_feature">
                    <div style="padding-bottom: 6px;padding-left: 5px; font-size: 12px;">Giá chỉ còn:</div>
                    <span class="hot_price">${gr.pricegroupon} <em style="font-size:15px; font-weight:bold;">đ</em></span><br/><br/>
                    <a class="buynowbtn_small" id="scriptbuynow" href="${confirmurl}?id=${id}&pr=${gr.pricegroupon}&type=g&catid=8"></a>
                </div>
            </div>
            <div style="clear: both;">
                <table style="width: 100%;border: 0px;padding-bottom: 5px;" >
                    <tr> 
                        <td colspan="3" style="padding-left: 15px;"><div id="countbox1" style="font-size:11px;"></div></td>
                    </tr>
                    <tr>
                        <td style="text-align: center;border-right: 1px dotted #BABABA;">Giá gốc</td>
                        <td style="text-align: center;border-right: 1px dotted #BABABA;">Số người đã mua</td>
                        <td style="text-align: center;border-right: 1px dotted #BABABA;">Tiết kiệm</td>
                    </tr>
                    <tr>
                        <td style="text-align: center;border-right: 1px dotted #BABABA;font-weight:bold; font-size:13px;">${gr.pricegame}</td>
                        <td style="text-align: center;border-right: 1px dotted #BABABA;color:#c22227;font-weight:bold; font-size:13px;">${countnumberbuy}</td>
                        <td style="text-align: center;border-right: 1px dotted #BABABA;color:#000;font-weight:bold; font-size:13px">${gr.percent} %</td>
                    </tr>
                </table>
            </div>
            <!--
             <div style="font-size:13px; font-weight:normal; margin-top:-5px; margin-bottom:0px;;color:#666;font-style:normal; ">
	Giá gốc: <span style="font-size:16px; color:#666;  text-decoration:line-through;">15.000 VNĐ</span></div>-->
        </div>
        <!--        <div class="bland">
        ${gr.description}
    </div>
    <div class="descriptiongame">
        ${gr.gameinfo}
    </div>-->

        <ul id="tabnav">
            <li class="tab1"><a href="groupondetail.html?id=${id}&t=1">Thể lệ</a></li>
            <li class="tab2"><a href="groupondetail.html?id=${id}&t=2">Giới thiệu</a></li>
            <li class="tab3"><a href="groupondetail.html?id=${id}&t=3">Danh sách mua</a></li>	


        </ul>
        <c:if test="${tab == 'tab1'}">

            <div class="bland">
                ${gr.description}
            </div>

        </c:if>
        <c:if test="${tab == 'tab2'}">
            <div class="descriptiongame">
                ${gr.gameinfo}
            </div>
        </c:if>
        <c:if test="${tab == 'tab3'}">
            <table style="margin-top: 3px;width: 100%; padding-left: 5px;padding-right: 5px;">
                <tr >
                    <td style=" border-bottom: 1px dashed #D4D3D3;font-weight: bold;">STT</td>
                    <td style=" border-bottom: 1px dashed #D4D3D3;font-weight: bold;">Số điện thoại</td>
                    <td style=" border-bottom: 1px dashed #D4D3D3;font-weight: bold;">Ngày mua</td>
                </tr>
                <c:forEach items="${lst}" var="l" begin="0" step="1" end="${num - 1}">
                    <tr>
                        <td style=" border-bottom: 1px dashed #D4D3D3;">${l.row}</td>
                        <td style=" border-bottom: 1px dashed #D4D3D3;">${l.msisdn}</td>
                        <td style=" border-bottom: 1px dashed #D4D3D3;">${l.datebuy}</td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="3">
                        <c:if test="${count > 1}">

                            <div class="split2">
                                <c:if test="${isback}">

                                    <a href="groupondetail.html?id=${id}&t=3&page=${page1-1}"><<&nbsp;</a>

                                </c:if>
                                <c:if test="${isback == false}">
                                </c:if>
                                <c:forEach begin="${splitStart}" end="${splitEnd}" var="i"> 

                                    <c:if test="${page == i}">
                                        <a href="groupondetail.html?id=${id}&t=3&page=${i}" class="item"><c:out value="${i}"/></a>
                                    </c:if>
                                    <c:if test="${page != i}">
                                        <a href="groupondetail.html?id=${id}&t=3&page=${i}"><c:out value="${i}"/></a>
                                    </c:if>
                                </c:forEach>
                                <c:if test="${isnext}">

                                    <a href="groupondetail.html?id=${id}&t=3&page=${page1+1}"> >> &nbsp; &nbsp;</a>

                                </c:if>
                                <c:if test="${istnext == false}">
                                </c:if>
                            </div>
                        </c:if>
                    </td>
                </tr>
            </table>
        </c:if>



        <%@include file="include/Ad.jsp" %>
        <%@include file="include/footer.jsp" %>
    </body>
</html>
