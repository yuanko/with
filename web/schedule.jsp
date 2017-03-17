<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.iwith.entity.Seller"%>
<%@page import="com.iwith.service.SellerService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>schedule</title>
        <%@ include file="include.jsp" %>
    </head>
    <%
        String id = request.getParameter("id");
        int pid = 1;
        if (id != null && id.matches("\\d+")) {
            pid = Integer.parseInt(id);
        }
        SellerService service = new SellerService();
        Seller s = service.get(pid);
    %>
    <body>
        <div id="header-container">
            <jsp:include page="WEB-INF/header.jsp" />
        </div>
        <div id='calendars'>
            <div class="container-date">
                <ul>
                    <%
                        Date today = new Date();
                        SimpleDateFormat formattedDay = new SimpleDateFormat("dd");
                        SimpleDateFormat formattedMonth = new SimpleDateFormat("MMMMM");
                        SimpleDateFormat formattedDayOfWeek = new SimpleDateFormat("E");
                        SimpleDateFormat formattedDateFull = new SimpleDateFormat("yyyy-mm-dd");
                        Calendar c = Calendar.getInstance();
                        for (int i = 1; i < 15; i++) {
                            c.add(Calendar.DATE, 1);  // number of days to add      
                            String day = (String) (formattedDay.format(c.getTime()));
                            String month = (String) (formattedMonth.format(c.getTime()));
                            String dayOfWeek = (String) (formattedDayOfWeek.format(c.getTime()));
                            String dateFull = (String) (formattedDateFull.format(c.getTime()));
                    %>
                    
                    <li>
                        <time data-date="<%= day%>" class="icon monday">
                            <span class="month"><%= month%></span>
                            <span class="day"><%= day%></span>
                            <span class="week"><%= dayOfWeek%></span>
                            <div class="time-selector">
                                <input class="time start timeFrom" type="text" /> to <input class="time end timeTo" type="text" />
                            </div>
                        </time>
                    </li>
                    <%}%>
                </ul>
            </div>

            <form action="check-out.jsp" method="POST">
                <input type='hidden' name='date' value="" id='date'>
                <input type='hidden' name='startTime' value="" id='startTime'>
                <input type='hidden' name='endTime' value="" id='endTime'>
                <button onclick="timeFunction()" class="checkout go ui-button ui-corner-all ui-widget">CONTINUE</button>
                <div id="snackbar">等等～親愛的！ 您還沒選時呢 ʕ⁎̯͡⁎ʔ༄</div>
            </form>
        </div>
    <%@include  file="WEB-INF/footer.jsp" %>
</body>
</html>



