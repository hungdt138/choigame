<%-- 
    Document   : catgories
    Created on : May 11, 2011, 1:39:02 PM
    Author     : Nguyen Dinh Doan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<!--<div>
    <form action="gameincat.html?page=1" method="post">
        <select  name="catId" style="width: 70%; margin-top: 5px; " name="id">
            <option value="0">Thể loại Game...</option>

<%--<c:forEach items="${lstCat}" var="cat" step="1" begin="0">
    <c:if test="${catId == cat.catId}">
        <option value="${cat.catId}" selected="selected">${cat.name}</option>
    </c:if>
    <c:if test="${catId != cat.catId}">
        <option value="${cat.catId}">${cat.name}</option>
    </c:if>

</c:forEach>--%>
</select>            
<input type="submit" value="Xem" class="btnXem"/>
</form>
</div>       -->

<div style="border-top: dotted 1px #CCC; padding-top: 5px; margin-top: 5px;">
   
    <form action="gameincat.html?page=1" method="post">                  
        <c:forEach items="${lstCat}" var="cat"> 
            <b><a href="gameincat.html?catId=${cat.catId}" class="game_title1">${cat.name} |</a></b>
        </c:forEach>      
    </form>
</div>        