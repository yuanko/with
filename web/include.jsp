<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link href="style.css" rel="stylesheet" type="text/css">
<link href="jquery-ui.min.css" rel="stylesheet" type="text/css">

<script src="jquery.timepicker.min.js"></script>
<link href="jquery.timepicker.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/userinfo/1.1.0/userinfo.min.js"></script>
<script type="text/javascript">

    $(function () {
        $('button').click(function () {
            if (($('#emailaddress').val() == '') || ($('#password').val() == '') || ($('#checkcode').val() == '')) { 
                $('form').addClass('shake-little');
                setTimeout(function () {
                    $('form').removeClass('shake-little');
                }, 500);
                return false;
            }
        });
    });
    $(function () {
        $('button.checkout').click(function () {
            if (($(".icon.shadow").length === 1) && ($(".icon.shadow .time.start").val() !== "") && ($(".icon.shadow .time.end").val() !== "")) {
                $("#date").val($(".shadow").attr("data-date"));
                $("#startTime").val($(".shadow .time.start").val());
                $("#endTime").val($(".shadow .time.end").val());
            } else {
                var x = document.getElementById("snackbar");
                x.className = "show";
                setTimeout(function () {
                    x.className = x.className.replace("show", "");
                }, 3000);
                return false;
            }
        });


        $(".icon").click(function () {

            if ($(this).hasClass("shadow")) {
                $(this).find(".time-selector").slideUp();
                $(this).removeClass("shadow");
            } else {
                $(".icon").removeClass("shadow");
                $(this).addClass("shadow");
                $(".time-selector").slideUp();
                $(this).find(".time-selector").slideDown();
            }

        });

        $('.scrolldown').click(function () {
            $("html, body").animate({scrollTop: $(window).height() - 67}, 800);
            return false;
        });


        $('.icon input').click(function () {
            return false;
        });

        $(".time.start").timepicker({ 'step': 60 });
        $(".time.end").timepicker({
            "step": 60,
            "minTime": "10:00am",
            "maxTime": "11:00pm",
            "showDuration": true
        });

        $(".time.start").on("changeTime", function () {
            var date = $(this).timepicker('getTime');
            var hour = date.getHours();
            var newDate = new Date(date.getTime());
            newDate.setHours(hour + 1);

            $(this).siblings(".time.end").timepicker('option', 'minTime', newDate);
            $(this).siblings(".time.end").timepicker('option', 'durationTime', date);
            $(this).siblings(".time.end").timepicker('option', 'showDuration', true);
            console.log(date);
        });

        $(".signin, .person").click(function () {
            $(".signin-page").fadeIn();
        });

        $(".close").click(function () {
            $(".signin-page").fadeOut();
        });

        $(".message a").click(function () {
            $(".signin-page form").animate({height: "toggle", opacity: "toggle"}, "slow");
        });

        $(window).on('scroll', function () {
            if ($(window).scrollTop() > 1) {
                $("#header-container").addClass("show");
            } else {
                $("#header-container").removeClass("show");
            }
        });


        $(".accordion").accordion({
            collapsible: true
        });


        $(".faq").click(function () {
            $(".sidebar").toggleClass("open");
        });

        $(".sidebar .close-key").click(function () {
            $(".sidebar").toggleClass("open");
        });

        $(".search select").selectmenu();

        $(".search .age").each(function () {
            $(this).selectmenu()
                    .selectmenu("menuWidget")
                    .addClass("overflow");
        });

        $(".search button").button();

        UserInfo.getInfo(function (data) {
            if (data.city.name === null) {
                $("#cityname").html(data.country.name);
            } else {
                $("#cityname").html(data.city.name);
                console.log(data);
            }
        }, function (err) {
            // the "err" object contains useful information in case of an error
        });

    });

    $(document).ready(function () {

        scaleVideoContainer();

        initBannerVideoSize('.video-container .poster img');
        initBannerVideoSize('.video-container .filter');
        initBannerVideoSize('.video-container video');

        $(window).on('resize', function () {
            scaleVideoContainer();
            scaleBannerVideoSize('.video-container .poster img');
            scaleBannerVideoSize('.video-container .filter');
            scaleBannerVideoSize('.video-container video');
        });

    });

    function scaleVideoContainer() {

        var height = $(window).height() + 5;
        var unitHeight = parseInt(height) + 'px';
        $('.homepage-hero-module').css('height', unitHeight);

    }

    function initBannerVideoSize(element) {

        $(element).each(function () {
            $(this).data('height', $(this).height());
            $(this).data('width', $(this).width());
        });

        scaleBannerVideoSize(element);

    }

    function scaleBannerVideoSize(element) {

        var windowWidth = $(window).width(),
                windowHeight = $(window).height() + 5,
                videoWidth,
                videoHeight;

        // console.log(windowHeight);

        $(element).each(function () {
            var videoAspectRatio = $(this).data('height') / $(this).data('width');

            $(this).width(windowWidth);

            if (windowWidth < 1000) {
                videoHeight = windowHeight;
                videoWidth = videoHeight / videoAspectRatio;
                $(this).css({'margin-top': 0, 'margin-left': -(videoWidth - windowWidth) / 2 + 'px'});

                $(this).width(videoWidth).height(videoHeight);
            }

            $('.homepage-hero-module .video-container video').addClass('fadeIn animated');

        });
    }
</script>

<link href="https://fonts.googleapis.com/css?family=Lato:300,400" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Codystar|Comfortaa|Megrim" rel="stylesheet">