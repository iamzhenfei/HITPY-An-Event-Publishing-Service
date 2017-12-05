<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <script charset="utf-8" src="http://map.qq.com/api/js?v=2.exp"></script>
    <script>
        var searchService, map, markers = [];
        var init = function () {
            var user = "${username}";

            console.log(user);

            if (user == "") {
                document.getElementById("joinbutton").style.visibility = "hidden";
            }
            else {
                document.getElementById("joinbutton").style.visibility = "visible";
            }
            var center = new qq.maps.LatLng(45.740627, 126.633210);
            map = new qq.maps.Map(document.getElementById('map_canvas'), {
                center: center,
                zoom: 16
            });
            var latlngBounds = new qq.maps.LatLngBounds();
            //调用Poi检索类
            searchService = new qq.maps.SearchService({
                complete: function (results) {
                    var pois = results.detail.pois;
                    for (var i = 0, l = pois.length; i < l; i++) {
                        var poi = pois[i];
                        latlngBounds.extend(poi.latLng);
                        var marker = new qq.maps.Marker({
                            map: map,
                            position: poi.latLng
                        });

                        marker.setTitle(i + 1);

                        markers.push(marker);
                    }
                    map.fitBounds(latlngBounds);
                }
            });
            searchKeyword();
        }
        //清除地图上的marker
        function clearOverlays(overlays) {
            var overlay;
            while (overlay = overlays.pop()) {
                overlay.setMap(null);
            }
        }
        function searchKeyword() {
            var keyword = "${eventLocation}";
            var region = "哈尔滨";
            clearOverlays(markers);
            searchService.setLocation(region);
            searchService.search(keyword);
        }
    </script>
    <style>
        #MyBody {
            background-color: rgb(41, 41, 41);
        }

        nav {
            margin: 0;
            padding: 0;
        }

        div {
            margin: 0;
            padding: 0;
            border-radius: 10px;
        }

        #container {
            width: 70%;
            height: 100%;
            border: 0px solid #ccc;
            background: rgb(0, 0, 0);
        }

        * {
            margin: 0px;
            padding: 0px;
        }

        p {
            width: 603px;
            padding-top: 3px;
            margin-top: 10px;
            overflow: hidden;
        }

        #map_canvas {
            min-width: 700px;
            min-height: 400px;
        }
    </style>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${eventName}</title>
    <script src="resources/jquery.js"></script>
    <link href="resources/bootstrap.min.js"/>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <script type="text/javascript">
        function join() {
            var aid = ${aid};
            var name = "${eventName}";
            window.location.href = "joinActivity?aid=" + aid + "&name=" + name;
        }
    </script>

</head>

<body id="MyBody" onLoad="init()">
<nav class="navbar navbar-inverse" role="navigation">
    <div class="navbar-header">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="index">HIT_PY</a>
                <ul class="nav navbar-nav navbar-right" id="username">
                    <li class="navbar-right"><a style="text-align: center"
                                                href="myspace"> ${username} </a></li>
                </ul>
            </div>
        </div>
    </div>
</nav>
<div style="padding:3% 10% 10%">
    <!-- <br />${aid}  -->
    <br/>
    <h1 style="color: mintcream">${eventName}</h1>
    <br/> <a style="color: mintcream">活动时间：${eventTime} </a>
    <br/> <a style="color: mintcream">活动地点：${eventLocation}</a>
    <div id="map_canvas"></div>
    <div>
        <br/>
        <a style="color: mintcream;">已参加活动的小伙伴：</a>
        <c:forEach items="${party}" var="p">
            <a style="color: mintcream;padding:0% 5%">${p}</a> <br/>
        </c:forEach>
    </div>
    <br/>
    <div style="margin:5% 5%;background: rgba(255, 255, 255, 0.8);">
        <br/>
        <div style="padding-left: 5%;">${content} </div>
        <br/>
    </div>
    <br/> <a style="color: mintcream"> 发布人员： ${poster} </a>
    <br/> <a style="color: mintcream"> 发布时间： ${created}</a>
    <br/>
    <br/>
    <br/>
    <br/>

    <br/>
    <div style="margin: 1% 25%">
        <button onclick="join()" type="button" class="btn btn-primary btn-lg btn-block" id="joinbutton"> 参加活动</button>
    </div>
</div>
</body>

</html>
