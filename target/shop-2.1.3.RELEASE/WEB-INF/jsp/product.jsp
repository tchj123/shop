<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>立即购买</title>
    <link rel="stylesheet" type="text/css" href="./css/xiangqing.css">
    <link rel="stylesheet" type="text/css" href="./css/style.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/scrollAd.js"></script>
    <script src="js/product.js"></script>
    <script src="js/payfor.js"></script>
</head>
<body>
<!-- start header -->
<header>
    <div class="top center clearfix">
        <div class="left fl">
            <ul>
                <li><a href="${ctx}/" target="_blank">商城</a></li>
                <li>|</li>
                <li><a href="">问题反馈</a></li>
                <li>|</li>
                <li><a href="">Select Region</a></li>
            </ul>
        </div>
        <div class="right fr">
            <div class="gouwuche fr">
                <a href="">购物车</a>
            </div>
            <div class="fr">
                <ul>
                    <li><a href="${ctx}/login.html" target="_blank">登录</a></li>
                    <li>|</li>
                    <li><a href="${ctx}/register.html" target="_blank">注册</a></li>
                    <li>|</li>
                    <li><a href="">消息通知</a></li>
                </ul>
            </div>
            <div class="clear"></div>
        </div>
        <div class="clear"></div>
    </div>
</header>
<!--end header -->

<!-- start banner_x -->
<div class="banner_x center">
    <a href="../../../resources/static/index.html" target="_blank">
        <div class="logo fl"></div>
    </a>
    <a href="">
        <div class="ad_top fl"></div>
    </a>
    <div class="nav fl">
        <ul>
            <li><a href="">手机</a></li>
            <li><a href="">电脑</a></li>
            <li><a href="">生鲜</a></li>
            <li><a href="">服务</a></li>
            <li><a href="">社区</a></li>
        </ul>
    </div>
    <div class="search fr">
        <form action="" method="post">
            <div class="text fl">
                <input type="text" class="shuru" placeholder="输入你想要搜索的东西">
            </div>
            <div class="submit fl">
                <input type="submit" class="sousuo" value="搜索">
            </div>
            <div class="clear"></div>
        </form>
        <div class="clear"></div>
    </div>
</div>
<!-- end banner_x -->


<!-- xiangqing -->
<form action="post" method="">
    <div class="xiangqing">
        <div class="neirong w">
            <div class="xiaomi6 fl">${prod.name}</div>
            <nav class="fr">
                <li><a href="">参数</a></li>
                <li>|</li>
                <li><a href="">用户评价</a></li>
                <div class="clear"></div>
            </nav>
            <div class="clear"></div>
        </div>
    </div>


    <div class="jieshao mt20 w">

        <!-- 图片展示 -->
        <div class="left fl">
            <div class="box">
                <ol></ol>
                <ul>
                    <c:if test="${prod.prodImg!=null && !prod.prodImg.isEmpty()}">
                        <li class="active" style="left:0;z-index:11;">
                            <img src="images/1.jpg">
                        </li>
                        <c:forEach var="i" begin="1" end="${prod.prodImg.size()-1}">
                            <li><img src="${prod.prodImg.get(i)}"/></li>
                        </c:forEach>
                    </c:if>
                </ul>
            </div>
        </div>


        <div class="xq fr">
            <div class="prodName">${prod.name}</div>
            <a href="javascript:void(0);" id="prodId" prodId="${prod.prodId}"></a>
            <a href="javascript:void(0);" id="skuId" skuId=""></a>
            <div class="price">${prod.minPrice}-${prod.maxPrice}元
                <hr align="left"/>
            </div>
            <div class="skuProperty">
                <c:forEach items="${prod.skuPropertyMap}" var="entry">
                    <div class="skuName" skuValue="0">${entry.key}</div>
                    <div>
                        <ul class="skuValueList">
                            <c:forEach items="${entry.value}" var="prop">
                                <li class="skuValue" skprId="${prop.skprId}"><a href="javascript:void(0);">${prop.value}</a></li>
                            </c:forEach>
                        </ul>
                    </div>
                </c:forEach>

                <div class="p_number">
                    <div style="height:36px;font-size:16px;">商品单价：<strong id="price_item_1">￥0</strong></div>
                    <div class="f_l add_chose"><a class="reduce" onClick="setAmount.reduce('#qty_item_1')"
                                                  href="javascript:void(0)"> -</a>
                        <input type="text" name="qty_item_1" value="1" id="qty_item_1"
                               onKeyUp="setAmount.modify('#qty_item_1')" class="text"/>
                        <a class="add" onClick="setAmount.add('#qty_item_1')" href="javascript:void(0)"> +</a></div>
                    <div class="f_l buy"> 总价：<span class="total-font" id="total_item_1">￥89.00</span>
                        <input type="hidden" name="total_price" id="total_price" value=""/>
                    </div>
                </div>
                <div>
                    <input type="button" class="buyThing" onclick="buy()" value="立即选购"/>
                    <input type="button" class="buyThing" onclick="addToCart()" value="加入购物车"/>
                </div>

            </div>


            <div class="clear"></div>
        </div>

        <div class="clearfix"></div>
        <div class="fengexian"></div>
        <div class="introImg">
            <c:forEach items="${prod.introImg}" var="img">
                <img src="${img}"/>
            </c:forEach>
        </div>
    </div>
</form>
<!-- footer -->
<footer class="mt20 center">

    <div class="mt20">小米商城|MIUI|米聊|多看书城|小米路由器|视频电话|小米天猫店|小米淘宝直营店|小米网盟|小米移动|隐私政策|Select
        Region
    </div>
    <div>©mi.com 京ICP证110507号 京ICP备10046444号 京公网安备11010802020134号
        京网文[2014]0059-0009号
    </div>
    <div>违法和不良信息举报电话：185-0130-1238，本网站所列数据，除特殊说明，所有数据均出自我司实验室测试</div>

</footer>


</body>
</html>