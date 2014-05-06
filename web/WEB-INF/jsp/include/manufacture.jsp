<%-- 
    Document   : catgories
    Created on : Dec 07, 2011, 8:30:02 AM
    Author     : Nguyen Thi Hue
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<div style="border-top: dotted 1px #CCC; padding-top: 5px; margin-top: 5px;">
    
    <span style="padding-left: 5px;font-weight: bold;">Game theo điện thoại</span>
    <form action="gamemanufact.html?page=1" method="post">                  
        <table>
            <c:forEach items="${lstGameManufact}" var="m">
                <tr>
                    <td>
                        <%--  <c:if test="${m.manufact=='Nokia' || 'SamSung' || 'LG' || 'Motorola'}">--%>
                        <img src="./images/inf/common/muiTen3.png" class="img_muiten3"/>
                        <a href="gamemanufact.html?manufact=${m.manufact}" >${m.manufact} Mobile Game</a>  
                    </td>
                    <%--<td>
                        <c:if test="${m.manufact=='HTC' || 'BlackBerry' || 'SonyEricsson'}">
                            <img src="./images/inf/common/muiTen3.png" class="img_muiten3"/>
                            <a href="gamemanufact.html?manufact=${m.manufact}" >${m.manufact} Mobile Game</a>  
                        </c:if
                    </td>>'}">--%>
                </tr>
            </c:forEach>    
        </table>
    </form>
</div>
