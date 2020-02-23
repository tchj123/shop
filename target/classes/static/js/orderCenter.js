var box;
var orderState;
var stateMap={
	"UNPAID":"未付款",
	"UNDELIVERED":"未发货",
	"UNRECEIVED":"未收货",
	"UNCOMMENTED":"待评价",
	"CANCELD":"取消",
	"REFUND":"退款/售后"
}
var orderActionMap={
	"UNPAID":["去付款","/pay/alipay?orderNo="],
	"UNDELIVERED":["催发货",""],
	"UNRECEIVED":["确认收货",""],
	"UNCOMMENTED":["去评价",""],
	"CANCELD":["",""],
	"REFUND":["查看订单",""]
}
var prodTemplate='    <div class="product">\n' +
	'        <div class="title"><span class="createTime">  </span> <span class="orderNo" >  </span>\n' +
	'        </div>\n' +
	'\n' +
	'    </div>';
var ulTemplate='        <ul class="clearfix">\n' +
	'            <li>\n' +
	'                <div class="prodImg" ><img src=""></div>\n' +
	'                <div class="prodName"> </div>\n' +
	'                <div class="skuValueList"> </div>\n' +
	'            </li>\n' +
	'            <li class="price"> </li>\n' +
	'            <li class="num"> </li>\n' +
	'            <li class="prodAction"> <a href="">退款/售后</a> </li>\n' +
	'            <li class="orderAmount"> </li>\n' +
	'            <li class="orderState"> </li>\n' +
	'            <li class="orderAction"> <a href=""></a> </li>\n' +
	'        </ul>';
$(document).ready(function(){

	orderState=$(".ddzxbt li.active").attr(name);
	//从后端请求订单数据
	$.ajax("/order/getOrderList",{
		data:{
			orderState:orderState=="all"?null:orderState,
			page:0
		}
	}).done(function (data) {
		box = new CustomPagination('#page', {
			total: data.total_page,//总页数
			changePage: function (pageNum) {//切换页码成功回调
				$.ajax("/order/getOrderList",{
					data:{
						orderState:orderState=="all"?null:orderState,
						page:pageNum
					}
				}).done(function (data) {
					//移出原订单列表
					$(".product").detach();
					//写入新的订单列表
					for(let i=0;i<data.data.length;i++){
						let orderListDiv=$(".orderList");
						orderListDiv.append(prodTemplate);
						let prodDiv=orderListDiv.find(".product:last-child");
						console.log(data.data[i])
						prodDiv.find(".createTime").text(data.data[i].create_time.substring(0,10));
						prodDiv.find(".orderNo").text("订单编号:"+data.data[i].order_no);
						let list=data.data[i].order_item_volist;
						for(let j=0;j<list.length;j++){
							prodDiv.append(ulTemplate);
							let ulDiv=prodDiv.find("ul:last-child");
							if(j==0) {
								ulDiv.find(".orderAmount").text("￥" + data.data[i].order_amount);
								let state=data.data[i].state;
								ulDiv.find(".orderState").text(stateMap[state])
								ulDiv.find(".orderAction a").text(orderActionMap[state][0]).attr('href',orderActionMap[state][1]+data.data[i].order_no)
							}
							ulDiv.find(".prodImg img").attr("src",list[j].prod_img);
							ulDiv.find(".prodName").text(list[j].prod_name);
							ulDiv.find(".skuValueList").text(list[j].sku_value_list);
							ulDiv.find(".price").text("￥"+list[j].prod_price);
							ulDiv.find(".num").text(list[j].num);
						}

					}
				})
			}
		});
	}).fail(function (xhr,status) {
		alert("失败"+status);
	});



});
