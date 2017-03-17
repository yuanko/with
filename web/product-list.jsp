<%@page import="com.iwith.entity.Seller"%>
<%@page import="java.util.List"%>
<%@page import="com.iwith.service.SellerService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    request.setCharacterEncoding("UTF-8");
    String search = request.getParameter("search");
    SellerService service = new SellerService();
    Seller s = new Seller();
    List<Seller> list = null;
    if (search != null) {
        list = service.getSellersByGender(search);
    } else {
        list = service.getSellersByType("Seller");
        search = "";
    }
%>
<html>
    <head>
        <title>People</title>
        <%@ include file="include.jsp" %>
    </head>
    <script>
        function getProduct(id) {
            //alert("查詢產品: No." + id);
            //同步請求:
            location.href = "<%= request.getContextPath()%>/profile.jsp?id=" + id;
        }
    </script> 
    <body>
        <div id="header-container">
            <jsp:include page="WEB-INF/header.jsp" />
        </div>

        <div class="cover-produst-list">
            <div class="city">The people are waiting to meet you in <span id="cityname"></span></div>
            <div class="search">
                <label for="search-gender">I'm looking for</label>
                <select name="gender">
                    <option>female</option>
                    <option>male</option>
                </select>
                <label for="age">between</label>
                <select class="age" name="age-from">
                    <% for (int i = 18; i < 101; i++) {
                            if (i == 20) {
                                out.print("<option selected=\"selected\">" + i + "</option>");
                            } else {
                                out.print("<option>" + i + "</option>");
                            }
                        }%>
                </select>
                <label for="to">to</label>
                <select class="age" name="age-to">
                    <% for (int i = 18; i < 101; i++) {
                            if (i == 30) {
                                out.print("<option selected=\"selected\">" + i + "</option>");
                            } else {
                                out.print("<option>" + i + "</option>");
                            }
                        }%>
                </select>
                <button class="go">Go</button>
            </div>

            <div class="main-picture">
                <%if (list != null && list.size() > 0) {
                        for (int i = 1; i < list.size(); i++) {
                            s = list.get(i);
                %>
                <div class="person">
                    <a href='javascript:getProduct(<%= s.getId()%>)'>
                        <div class="main-picture-images" style="background-image: url(<% out.print("images/p" + i + ".png");%>)"></div>
                        <div class="overlay">
                            <div class="text"><td>Hello I'm <%= s.getName()%></td><br>
                                <td>price/hour：<%= s.getPrice()%></td><br>
                                <td>地區：<%= s.getLocation()%></td><br>
                                <td>age：<%= s.getAge()%></td><br></div>
                        </div>
                </div>
                </a>
                <%}
                    }%>
            </div>
        </div>
        <%@include  file="WEB-INF/footer.jsp" %>
    </body>
</html>
