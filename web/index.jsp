<%@page import="com.iwith.service.SellerService"%>
<%@page import="com.iwith.entity.Seller"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>iwith.it</title>
        <%@ include file="include.jsp" %>
    </head>
    <script>
        function refreshImage() {
            var myImg = document.getElementById("check_img");
            myImg.src = "images/check.jpg?get=" + new Date();
        }
    </script>
    <body> 
        <div id="header-container">
            <jsp:include page="WEB-INF/header.jsp" />
        </div>

        <div class="main">
            <video autoplay muted playsinline loop class="fillWidth">
                <source src="images/Bokeh-Tov/MP4/Bokeh-Tov.mp4" type="video/mp4" />
                <source src="images/Bokeh-Tov/WEBM/Bokeh-Tov.webm" type="video/webm" />
            </video>

            <div class="sexycouple">
            </div>

            <div class="tital">
                <h1><strong>i</strong> wanna be <strong>with</strong> you.</h1>
                <p>you can choose who you want to be with</p>
            </div>

            <div class="scrolldown">
                <div class="chevron"></div>
                <div class="chevron"></div>
                <div class="chevron"></div>
                <span class="scrolldownText">Scroll down</span>
            </div>
        </div>

        <div class="containerShow">
            <div class="containerCitySearch">
                <div class="city">The people are waiting to meet you in <span id="cityname"></span></div>
                <div class="search">
                    <label for="search-gender">I'm looking for</label>
                    <select name="gender">
                        <option>female</option>
                        <option>male</option>
                    </select>
                    <label for="age">between</label>
                    <select class="age" name="age-from">
                        <% for (int i = 18; i < 86; i++) {
                                if (i == 20) {
                                    out.print("<option selected=\"selected\">" + i + "</option>");
                                } else {
                                    out.print("<option>" + i + "</option>");
                                }
                            }%>
                    </select>
                    <label for="to">to</label>
                    <select class="age" name="age-to">
                        <% for (int i = 18; i < 86; i++) {
                                if (i == 30) {
                                    out.print("<option selected=\"selected\">" + i + "</option>");
                                } else {
                                    out.print("<option>" + i + "</option>");
                                }
                            }%>
                    </select>
                    <button class="signin">Go</button>
                </div>
            </div>


            <div class="main-picture">
                <%for (int i = 1; i < 25; i++) {%>
                <a>
                    <div class="person">
                        <div class="main-picture-images" style="background-image: url(<% out.print("images/p" + i + ".png");%>)"></div>
                        <div class="overlay">
                            <div class="text"></div>
                        </div>
                    </div>
                </a>
                <%}%>
            </div>

        </div>


        <div class="signin-page">
            <div class="form">
                <form class="register-form" action="product-list.jsp" method="POST">
                    <input type="text" placeholder="name"/>
                    <input type="password" placeholder="password"/>
                    <input type="text"  placeholder="email address"/>
                    <button>create</button>
                    <p class="message">Already registered? <a>Sign In</a></p>
                </form>
                <form class="login-form" action="login.do" method="POST">
                    <input id="emailaddress" type="text" placeholder="email address" name="email"/>
                    <input id="password" type="password" placeholder="password" name="password"/>
                    <a href="javascript:refreshImage()"  title='點選即可更新圖片'>
                        <img src='images/check.jpg' id='check_img'>
                    </a>
                    <input id="checkcode" type="text" placeholder="check code" name="checkcode" autocomplete="off" autocorrect="off" autocapitalize="off" spellcheck="false"  />
                    <button>login</button>
                    <p class="message">Not registered? <a>Create an account</a></p>
                </form>
                <span class="close">x</span>
            </div>
        </div>

        <%@include  file="WEB-INF/footer.jsp" %>
    </body>
</html>
