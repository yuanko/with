<%@page import="com.iwith.entity.Seller"%>
<%@page import="com.iwith.service.SellerService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String id = request.getParameter("id");
    int pid = 1;
    if (id != null && id.matches("\\d+")) {
        pid = Integer.parseInt(id);
    }
    SellerService service = new SellerService();
    Seller s = service.get(pid);
%>
<html>
    <head>
        <title>profile</title>
        <%@ include file="include.jsp" %>
    </head>
    <script>
        function getProduct(id) {
            //alert("查詢產品: No." + id);
            //同步請求:
              location.href = "<%= request.getContextPath()%>/schedule.jsp?id=" + id;
        }
    </script> 
    <body>
        <div id="header-container">
            <jsp:include page="WEB-INF/header.jsp" />
        </div>

        <div class="content">
            
            <div class="schedule">
                <figure class="profile">
                    <div class="profile-image"><img src="images/p10.png" alt="personsPhoto"/></div>
                    <figcaption>
                        <h3><%=s.getName()%></h3>
                        <p class="getInterest"><%=s.getInterest()%></p><br>
                        <p>Weight:<%= s.getWeight()%></p><br>
                        <p>Height:<%= s.getHeight()%></p><br>
                        <p>Age:<%= s.getAge()%></p><br>
                        <p>Location:<%= s.getLocation()%></p><br>
                        <p>Price:<%= s.getPrice()%></p><br>
                    </figcaption>
                    <a href='javascript:getProduct(<%= s.getId()%>)'>Reservation</a>
                </figure>
            </div>

            <div class="p-img">
                <img src="images/p10.png" alt="" />
                <img src="images/p10.png" alt="" />
                <img src="images/p10.png" alt="" />
                <img src="images/p10.png" alt="" />
                <img src="images/p10.png" alt="" />
                <img src="images/p10.png" alt="" />
            </div>

        </div>
        <%@include  file="WEB-INF/footer.jsp" %>
    </body>
</html>
