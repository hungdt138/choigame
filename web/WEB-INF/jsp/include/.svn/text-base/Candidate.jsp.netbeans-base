<%-- 
    Document   : Candidate
    Created on : Dec 27, 2011, 10:47:42 AM
    Author     : noname
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <div class="gameCandidate clear">
            <a href="details.html?id=${candidate.dataId}" ><img border="0" width="110px" height="95px" style="margin-right: 10px;" src="${imgUrl}${candidate.logoUrl}" class="floatLeft img_game" /></a>
            <span class="game_title">
                <a href="details.html?id=${candidate.dataId}" style="font-size: 15px;">${candidate.name}</a>
            </span>
            <br/><br/>
            <c:if test="${lv1 == 1}">
                <img src="./images/inf/common/4sao.gif"/>
            </c:if>
            <c:if test="${lv1 == 2}">
                <img src="./images/inf/common/2sao.gif"/>
            </c:if>
            <c:if test="${lv1 == 3}">
                <img src="./images/inf/common/3sao.gif"/>
            </c:if>
            <c:if test="${lv1 == 4}">
                <img src="./images/inf/common/4sao.gif"/>
            </c:if>
            <c:if test="${lv1 == 5}">
                <img src="./images/inf/common/5sao.gif"/>
            </c:if>

            <!--            <span class="game_title" style="background:#FFF8E3;border:solid 1px #ccc;width:90%; color: red;">
                            <img src="./images/inf/common/newnew.gif"/><b>Miễn phí</b> 
                        </span>-->
            <br/>
            <b>Lượt tải</b>: ${view}
            <br>

            <br>
            <span class="game_desc">
                ${candidate.gameInfo}
            </span>
            <span class="linkdetail"><a href="details.html?id=${candidate.dataId}" class="gameDetails ">Xem chi tiết</a></span>
        </div>