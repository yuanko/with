<%@page import="java.util.Date"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>check out</title>
        <%@ include file="include.jsp" %>

    </head>
    <body>
        <div id="header-container">
            <jsp:include page="WEB-INF/header.jsp" />
        </div>
        <form action="" method="POST">
            <div class="img-checkout">
                <h2>Check out cart</h2>
                <img src="images/p10.png" alt="personsphoto">
                <div class="cShowtime">
                    <h2>Booking date:<%= new Date()%></h2>
                    <h1>You're dating to: John Coven</h1>
                    <p class="reservationDate">Reservation date:${param.date}</p>
                    <p class="timeStart">Date time Start:${param.startTime}</p>
                    <p class="timeEnd">Date time End:${param.endTime}</p>
                    <hr>
                    <p class="sumCalculate">Time Total: 4 Hours  Total: NT$4000</p><br>
                    <button class="paid go ui-button ui-corner-all ui-widget">APPROVE</button>
                </div>
            </div>
        </form>
    </body>      
</html>
