<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%--<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<!-- Truyen thêm cái catid de biet có dang duyet trong The loai nào do hay không -->
<!--<form action="searchresults.html?catId=${catId}&page=1" method="post">
    <div id="search_game" class="clear">
        <input name="txtSearch" id="search" class="textbox" /> <input name="Seachbutton" type="submit" value="Tìm" class="btn_search"/><br/>
    </div>
</form>-->
<div id="search_game" class="clear"> 
<form action="searchresults.html?page=1&radio=0" method="get">
    
        <input name="txtSearch" id="search" class="textbox" /> <input name="Seachbutton" type="submit" value="Tìm" class="btn_search"/><br/>
        <c:if test="${radio == null}">
            <input type="radio" checked="checked" value="0" name="radio" id="radio01">
            <label for="radio01">Theo tên</label>
            <input type="radio" value="1" name="radio" id="radio02">
            <label for="radio02">Theo đời máy</label>
        </c:if>
        <c:if test="${radio == 0}">
            <input type="radio" checked="checked" value="0" name="radio" id="radio01">
            <label for="radio01">Theo tên</label>
            <input type="radio" value="1" name="radio" id="radio02">
            <label for="radio02">Theo đời máy</label>
        </c:if>
        <c:if test="${radio == 1}">
            <input type="radio"  value="0" name="radio" id="radio01">
            <label for="radio01">Theo tên</label>
            <input type="radio" checked="checked" value="1" name="radio" id="radio02">
            <label for="radio02">Theo đời máy</label>
        </c:if>
    
    <br/><br/>
</form>
            </div>





