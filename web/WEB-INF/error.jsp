<%@page contentType="text/html" isErrorPage="true"%>
<%@page pageEncoding="UTF-8"%>
 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>error page</title>
        <script>
            var s1 = "block";
            var s2 = "width:80%;display:blocked;font-size:60%;color:blue";
            function show_details(){
                var d = document.getElementById("details");
                try{
                    d.style.setAttribute("display", s1);               
                    if (s1=="none"){
                        s1 = "block";
                    }else{s1 = "none";}
                }catch(err){
                    d.setAttribute("style", (s2==null?"width:80%;display:none;":s2));               
                    if (s2 == null){
                        s2 = "width:80%;display:blocked;font-size:60%;color:blue";
                    }else{s2 = null;}                   
                }
            }
        </script>
    </head>
    <body>
        <jsp:include page="/WEB-INF/header.jsp" >
            <jsp:param name="subtitle" value="系統錯誤"/>
        </jsp:include>
        <div id="article">
        <p style='font-size:80%'>執行<span style='color:darkred'>${requestScope["javax.servlet.error.request_uri"]}</span>時發生下列錯誤：<br/>
            <% if (exception!=null){          
                out.println(exception.getClass().getName() + ":\t" +exception.getMessage());%>
                <a href="javascript:show_details()">details...</a><br/>
            <span id='details' style="width:60%;display:none;color:blue">
                <%
                exception.printStackTrace(new java.io.PrintWriter(out));
                %>
            </span>
            <% }else{ 
                Integer code = (Integer)request.getAttribute("javax.servlet.error.status_code");
                if(code!=null && code.intValue()==404){
            %>
            <img src="<%= request.getContextPath() %>/images/404.jpg"
                
            <%}
            }%>
        </p>
        </div>
        <%@include  file="/WEB-INF/footer.jsp" %>
    </body