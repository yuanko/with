<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
        <div id="footer">
            <a href="index.jsp"><img class="logo" src="images/logo.svg" alt='i iwith'/></a>
<!--            <a href='mailto:ko@iwith.it'>contact us</a>-->
            <p>design and code © iwith <%= Calendar.getInstance().get(Calendar.YEAR) %></p>
        </div>
