<%-- 
    Document   : Groupon
    Created on : Dec 27, 2011, 11:04:24 AM
    Author     : noname
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div style="margin-top:3px;clear: both;border: 1px solid #DBDBDB;" >
    <div class="title_feature"><a href="groupondetail.html?id=${gr.id}">${gr.name}, Game ${gr.namegame}</a></div>
    <div class="img_feature"> 
        <div id="team_images_" class="deal-buy-cover-img team_images">
            <a href="groupondetail.html?id=${gr.id}">
                <img width="110" height="95" src="${gr.logurl}"></a>
        </div>
    </div>
    <div style="padding-left: 115px;">
        <div class="price_feature">
            <div style="padding-bottom: 6px;padding-left: 5px; font-size: 12px;">Giá chỉ còn:</div>
            <span class="hot_price">${gr.pricegroupon} <em style="font-size:15px; font-weight:bold;">đ</em></span><br/><br/>
            <a class="buynowbtn_small" id="scriptbuynow" href="${confirmurl}?id=${gr.id}&pr=${gr.pricegroupon}&type=g&catid=8"></a>
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

