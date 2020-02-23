<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="author" content="order by dede58.com"/>
    <title>小米商城-个人中心</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
    <link rel="stylesheet" type="text/css" href="/css/addr.css">
    <script src="/js/jquery.min.js"></script>
    <script type="text/javascript" src="/js/create_order.js"></script>
    <script type="text/javascript" src="/js/jquery.Area.js"></script>

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
                    <li><a href="./register.html" target="_blank" >注册</a></li>
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
<div class="banner_x center">
    <a href="./index.html" target="_blank"><div class="logo fl"></div></a>
    <a href=""><div class="ad_top fl"></div></a>
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
                <input type="text" class="shuru"  placeholder="小米6&nbsp;小米MIX现货">
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
    <div class="selfinfo center ">

        <div class="rtcont">
            <div class="ddzxbt " >收货地址</div>
            <div class="addrList clearfix" id="addrList">
                <div id="addrs">
                    <c:forEach items="${addrList}" var="addr">

                        <div class="addr <c:if test="${addr.isDefaultAddress}">active</c:if>">
                        <div class="itemReceiver">
                            <strong class="itemConsignee" name="receiverName">${addr.name}</strong>
                            <span class="deleteButton">x</span>
                        </div>
                        <div>
                            <input hidden="hidden" name="addrId" addrId="${addr.addrId}">
                            <p name="tel">${addr.telephone}</p>
                            <p class="province">
                                <span name="province">${addr.province}</span>
                                <span name="city">${addr.city}</span>
                                <span name="area">${addr.area}</span></p>
                            <p>
                                <span name="street">${addr.street}</span>
                                (</span><span name="zip">${addr.zip}</span>)
                            </p>
                        </div>
                        <input type="button" name="edit" value="编辑">
                    </div>

                    </c:forEach>
                </div>
                <div class="newAddr" id="addNewAddr">
		<span class="icon-add"><img src="/images/add_cart.png">
		</span>
                    <div>添加新地址</div>
                </div>

            </div>


        </div>
        <div class="rtcont payChannel">
            <div class="ddzxbt">支付方式</div>
            <ul class="clearfix">
                <li class="item active" payChannel="alipay">
                    支付宝支付
                </li>
                <li class="item" payChannel="wechat">
                    微信支付
                </li>
            </ul>
        </div>

        <div class="rtcont ">
            <div class="ddzxbt">确认订单信息</div>
            <div class="prodBar">
                <ul class="clearfix">
                    <li class="col1">&nbsp</li>
                    <li class="col2">商品名称</li>
                    <li class="col3">规格</li>
                    <li class="col4">数量</li>
                    <li class="col5">单价（元）</li>
                    <li class="col6">小计（元）</li>
                </ul>
            </div>

            <c:forEach items="${cartItemList}" var="item">

            <div class="ddxq">
                    <div class="ztxx">
                        <ul>
                            <input hidden="hidden" name="cartId" cartId="${item.cartId}">
                            <li class="col1"><img src="/image/gwc_xiaomi6.jpg" alt=""></li>
                            <li class="col2">${item.prodName}</li>
                            <li class="col3">${item.skuValueList}</li>
                            <li class="col4">${item.num}</li>
                            <li class="col5">￥${item.price}</li>
                            <li class="col6">￥${item.price*item.num}</li>
                            <div class="clear"></div>
                        </ul>
                    </div>
                    <div class="clear"></div>
            </div>

            </c:forEach>
            <div class="clear"></div>
            </div>
        <div class="rtcont check-out clearfix">

            <div class="checkout-comment">
                <h3 class="title">会员留言</h3><br>

                <input type="text">

            </div>
            <!-- checkout-count-extend -->
            <div class="checkout-price">
                <ul>

                    <li>
                        <span class="priceLeft">订单总额：</span>
                        <span class="priceRight" >${totalAmount}元</span>
                    </li>
                    <li>
                        <span class="priceLeft">活动优惠：</span>
                        <span class="priceRight">-0元</span>
                    </li>
                    <li>
                        <span class="priceLeft">优惠券扣：</span>
                        <span class="priceRight">-0元</span>
                    </li>
                    <li>
                        <span class="priceLeft">运费：</span>
                        <span class="priceRight">0元</span>
                    </li>
                    <li>
                    <span class="priceLeft">应付总额：</span>
                    <span class="priceRight"><strong id="totalPrice">${totalAmount}</strong>元</span>
                    </li>
                </ul>

            </div>

            <!--  -->
        </div>
        <div class="checkout-confirm clearfix">

            <a href="/cart" class="returnCart">返回购物车</a>
            <input type="button"  value="立即下单" id="checkoutToPay">
        </div>
    </div>
</div>


<div class="background">
</div>

<div class="pop">
    <input hidden="hidden" value="" id="addrId">
    <div class="item">
        <input type="text" placeholder="收货人姓名" id="receiverName">
        <div class="alert"></div>
    </div>
    <div class="item">
        <input type="text" placeholder="11位手机号" id="tel">
        <div class="alert"></div>
    </div>
    <div class="item province">
        <label><select  id="iDo-province"><option>--请选择--</option></select></label>
        <div class="alert"></div>
    </div>

    <div class="item province">
        <label><select  id="iDo-city"><option>--请选择--</option></select></label>
        <div class="alert"></div>
    </div>

    <div class="item province">
        <label><select id="iDo-county"><option>--请选择--</option></select></label>
        <div class="alert"></div>
    </div>

    <div class="item">
        <input type="text" placeholder="路名或街道地址，门牌号" id="street">
        <div class="alert"></div>
    </div>
    <div class="item">
        <input type="text" placeholder="邮政编码" id="zip">
        <div class="alert"></div>
    </div>
    <div class="inputPanel clearfix">
        <input type="button" value="取消" id="cancel">
        <input type="button" value="保存" id="save">
    </div>
</div>


<div id="template" hidden="hidden">
    <div class="addr">
        <div class="itemReceiver">
            <strong class="itemConsignee" name="receiverName">潘骏杰</strong>
            <span class="deleteButton">x</span>
        </div>
        <div>
            <input hidden="hidden" name="addrId" addrId="1">
            <p name="tel">15961726437</p>
            <p class="province">
                <span name="province">江苏省</span>
                <span name="city">无锡市</span>
                <span name="area">北塘区</span></p>
            <p>
                <span name="street">民丰西苑82号202室</span>
                (</span><span name="zip">21212</span>)
            </p>
        </div>
        <input type="button" name="edit" value="编辑">
    </div>
</div>

<footer class="mt20 center">
    <div class="mt20">小米商城|MIUI|米聊|多看书城|小米路由器|视频电话|小米天猫店|小米淘宝直营店|小米网盟|小米移动|隐私政策|Select Region</div>
    <div>©mi.com 京ICP证110507号 京ICP备10046444号 京公网安备11010802020134号 京网文[2014]0059-0009号</div>
    <div>违法和不良信息举报电话：185-0130-1238，本网站所列数据，除特殊说明，所有数据均出自我司实验室测试</div>
</footer>
</body>
</html>