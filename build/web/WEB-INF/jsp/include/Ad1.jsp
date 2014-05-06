<%-- 
    Document   : Ad
    Created on : Aug 23, 2011, 3:16:23 PM
    Author     : Hungdt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:forEach items="${list1}" var="ad1" begin="0" step="1" >  
<c:if test="${ad1.link != null}">
    <div class ="bannerbox">
        <a href="${ad1.link}" style="padding: 3px;"><img src="${ad1.picture}" width="100%" /></a>
    </div>
</c:if> 
</c:forEach>


