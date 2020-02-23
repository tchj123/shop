<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>我的购物车-小米商城</title>
		<link rel="stylesheet" type="text/css" href="./css/style.css">
		<script src="js/jquery.min.js"></script>
		<script src="js/cart.js"></script>
	</head>
	<body>
	<!-- start header -->
	<!--end header -->

<!-- start banner_x -->
		<div class="banner_x center">
			<a href="./index.html" target="_blank"><div class="logo fl"></div></a>
			
			<div class="wdgwc fl ml40">我的购物车</div>
			<div class="wxts fl ml20">温馨提示：产品是否购买成功，以最终下单为准哦，请尽快结算</div>
			<div class="dlzc fr">


			</div>
			<div class="clear"></div>
		</div>
		<div class="xiantiao"></div>


		<div class="gwcxqbj">
			<div class="gwcxd center" id="normal">
				<div class="top2 center">
					<div class="sub_top fl">
						<input type="checkbox" name="selectAll" class="quanxuan" />全选
					</div>
					<div class="sub_top fl">商品名称</div>
					<div class="sub_top fl">单价</div>
					<div class="sub_top fl">数量</div>
					<div class="sub_top fl">小计</div>
					<div class="sub_top fr">操作</div>
					<div class="clear"></div>
				</div>

				<c:forEach items="${cartItemList}" var="cartItem">
					<div class="content2 center">
						<div class="sub_content fl ">
							<input type="checkbox" value="select" class="quanxuan" />
						</div>
						<a href="" name="cartId" cartId="${cartItem.cartId}"></a>
						<div class="sub_content fl"><img src="./image/gwc_xiaomi6.jpg"></div>
						<div class="sub_content fl ft20">${cartItem.prodName}(${cartItem.skuValueList})</div>
						<div class="sub_content fl " price="${cartItem.price}">${cartItem.price}元</div>
						<div class="sub_content fl">
							<input class="shuliang" type="number" value="${cartItem.num}" step="1" min="1" >
						</div>
						<div class="sub_content fl" totalPrice="">${cartItem.price}元</div>
						<div class="sub_content fl"><a href="" name="deleteButton">×</a></div>
						<div class="clear"></div>
					</div>
				</c:forEach>

			</div>
			<div class="jiesuandan mt20 center">
				<div class="tishi fl ml20">
					<ul>
						<li><a href="./liebiao.html">继续购物</a></li>
						<li>|</li>
						<li>共<span id="totalProd">${cartItemList.size()}</span>件商品，已选择<span id="selectedProd">0</span>件</li>
						<div class="clear"></div>
					</ul>
				</div>
				<div class="jiesuan fr">
					<div class="jiesuanjiage fl" totalPrice="">合计（不含运费）：<span>0元</span></div>
					<div class="jsanniu fr"><input class="jsan" type="submit" name="jiesuan"  value="去结算"/></div>
					<div class="clear"></div>
				</div>
				<div class="clear"></div>
			</div>

			<!--失效商品区域-->
			<div class="gwcxd center">
				<div class="top2 center  expired">
					失效商品
				</div>

				<c:forEach items="${cartItemList}" var="cartItem">

				</c:forEach>

			</div>
		</div>

  

	
	<!-- footer -->
	<footer class="center">
			
			<div class="mt20">小米商城|MIUI|米聊|多看书城|小米路由器|视频电话|小米天猫店|小米淘宝直营店|小米网盟|小米移动|隐私政策|Select Region</div>
			<div>©mi.com 京ICP证110507号 京ICP备10046444号 京公网安备11010802020134号 京网文[2014]0059-0009号</div> 
			<div>违法和不良信息举报电话：185-0130-1238，本网站所列数据，除特殊说明，所有数据均出自我司实验室测试</div>
		</footer>

	</body>
</html>
