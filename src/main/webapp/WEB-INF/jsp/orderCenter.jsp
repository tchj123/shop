<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>小米商城-个人中心</title>
    <link rel="stylesheet" type="text/css" href="/css/orderCenter.css">
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <link rel="stylesheet" href="/css/custom-pagination.min.css"/>
    <script type="text/javascript" src="/js/custom-pagination.min.js"></script>
    <script type="text/javascript" src="/js/orderCenter.js"></script>


</head>
<body>
<!-- start header -->
<header>
    <div class="top center">
        <div class="left fl">
            <ul>
                <li><a href="http://www.mi.com/" target="_blank">小米商城</a></li>
                <li>|</li>
                <li><a href="">MIUI</a></li>
                <li>|</li>
                <li><a href="">米聊</a></li>
                <li>|</li>
                <li><a href="">游戏</a></li>
                <li>|</li>
                <li><a href="">多看阅读</a></li>
                <li>|</li>
                <li><a href="">云服务</a></li>
                <li>|</li>
                <li><a href="">金融</a></li>
                <li>|</li>
                <li><a href="">小米商城移动版</a></li>
                <li>|</li>
                <li><a href="">问题反馈</a></li>
                <li>|</li>
                <li><a href="">Select Region</a></li>
                <div class="clear"></div>
            </ul>
        </div>
        <div class="right fr">
            <div class="gouwuche fr"><a href="">我的订单</a></div>
            <div class="fr">
                <ul>
                    <li><a href="./login.html" target="_blank">登录</a></li>
                    <li>|</li>
                    <li><a href="./register.html" target="_blank">注册</a></li>
                    <li>|</li>
                    <li><a href="./self_info.html">个人中心</a></li>
                </ul>
            </div>
            <div class="clear"></div>
        </div>
        <div class="clear"></div>
    </div>
</header>
<!--end header -->
<!-- start banner_x -->
<div class="banner_x center"><a href="./index.html" target="_blank">
    <div class="logo fl"></div>
</a> <a href="">
    <div class="ad_top fl"></div>
</a>
    <div class="nav fl">
        <ul>
            <li><a href="">小米手机</a></li>
            <li><a href="">红米</a></li>
            <li><a href="">平板·笔记本</a></li>
            <li><a href="">电视</a></li>
            <li><a href="">盒子·影音</a></li>
            <li><a href="">路由器</a></li>
            <li><a href="">智能硬件</a></li>
            <li><a href="">服务</a></li>
            <li><a href="">社区</a></li>
        </ul>
    </div>
    <div class="search fr">
        <form action="" method="post">
            <div class="text fl">
                <input type="text" class="shuru" placeholder="小米6&nbsp;小米MIX现货">
            </div>
            <div class="submit fl">
                <input type="submit" class="sousuo" value="搜索"/>
            </div>
            <div class="clear"></div>
        </form>
        <div class="clear"></div>
    </div>
</div>
<!-- end banner_x -->
<!-- self_info -->
<div class="grzxbj">
    <div class="selfinfo center">
        <div class="lfnav fl">
            <div class="ddzx">订单中心</div>
            <div class="subddzx">
                <ul>
                    <li><a href="" style="color:#ff6700;font-weight:bold;">我的订单</a></li>
                    <li><a href="">意外保</a></li>
                    <li><a href="">团购订单</a></li>
                    <li><a href="">评价晒单</a></li>
                </ul>
            </div>
            <div class="ddzx">个人中心</div>
            <div class="subddzx">
                <ul>
                    <li><a href="./self_info.html">我的个人中心</a></li>
                    <li><a href="">消息通知</a></li>
                    <li><a href="">优惠券</a></li>
                    <li><a href="">收货地址</a></li>
                </ul>
            </div>
        </div>
        <div class="rtcont fr">
            <div class="ddzxbt clearfix">
                <ul class="clearfix">
                    <li class="active" name="all">所有订单</li>
                    <li name="UNPAID">待付款</li>
                    <li name="UNDELIVERED">待发货</li>
                    <li name="UNRECEIVED">待收货</li>
                    <li name="UNCOMMENTED">待评价</li>
                    <li name="REFUND">退款/售后</li>
                </ul>
            </div>
            <div class="orderPannel">
                <div class="pannelTitle">
                    <ul class="clearfix">
                        <li>商品</li>
                        <li>单价</li>
                        <li>数量</li>
                        <li>商品操作</li>
                        <li>实付款</li>
                        <li>交易状态</li>
                        <li>交易操作</li>
                    </ul>
                </div>
                <div class="orderList">

                </div>

                <div id="page" class="page"></div>

            </div>
            </div>
        </div>
        <div class="clear"></div>
    </div>
    <!-- self_info -->
    <footer class="mt20 center">
        <div class="mt20">小米商城|MIUI|米聊|多看书城|小米路由器|视频电话|小米天猫店|小米淘宝直营店|小米网盟|小米移动|隐私政策|Select Region</div>
        <div>©mi.com 京ICP证110507号 京ICP备10046444号 京公网安备11010802020134号 京网文[2014]0059-0009号</div>
        <div>违法和不良信息举报电话：185-0130-1238，本网站所列数据，除特殊说明，所有数据均出自我司实验室测试</div>
    </footer>
<div id="template" hidden="hidden">
    <div class="product">
        <div class="title"><span class="createTime"> 2011-11-21 </span> <span class="orderNo" > 订单编号：21154845121894 </span>
        </div>
        <ul class="clearfix">
            <li>
                <div class="prodImg" ><img src="/image/liebiao_xiaomi5c.jpg"></div>
                <div class="prodName"> </div>
                <div class="skuValueList"> 红色嗷</div>
            </li>
            <li class="price"> ￥10</li>
            <li class="num"> 1</li>
            <li class="prodAction"> <a href="">退款/售后</a> </li>
            <li class="orderAmount"> ￥10</li>
            <li class="orderState"> 已发货</li>
            <li class="orderAction"> <a href="">确认收货</a> </li>
        </ul>
    </div>
</div>
</body>
</html>