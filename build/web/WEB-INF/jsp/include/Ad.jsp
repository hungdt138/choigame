<%-- 
    Document   : Ad
    Created on : Aug 23, 2011, 3:16:23 PM
    Author     : Hungdt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:forEach items="${list}" var="ad" begin="0" step="1" >  
<c:if test="${ad.link != null}">
    <div class ="bannerbox">
        <a href="${ad.link}" style="padding: 3px;"><img src="${ad.picture}" width="100%" /></a>
    </div>
</c:if> 
</c:forEach>


