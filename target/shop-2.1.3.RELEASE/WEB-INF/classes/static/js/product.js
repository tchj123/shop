var ulNum=0;
var activeLi=0;
$(document).ready(function(){
	$("ul.skuValueList li").bind('click',function(){
		if(!$(this).hasClass("active")){
			$(this).addClass("active");
			activeLi+=1;
		}else
			return;
		var liList=$(this).parent().find("li");
		var activeIndex=$(this).index();
		liList.each(function(index,element){
			if($(element).hasClass("active")&&index!=activeIndex) {
				$(element).removeClass("active");
				activeLi--;
			}
		});
		//如果所有skpr都选上了，到后台查询相应的价格
		if(activeLi==ulNum &&activeLi!=0){
			$.ajax("/getSku",{
				data:{
					'prodId':$("#prodId").attr('prodId'),
					'skprIdList':getSkprIdList()
				},
				method:"POST"
			}).done(function (data) {
				$("#price_item_1").text("￥"+data.price)
				$(".price").text(data.price+"元")

				$("#skuId").attr('skuId',data.sku_id)
				recalc()
			}).fail(function (xhr, status) {
				alert("查询价格失败"+status)
			})
		}
	})
	ulNum=$("ul.skuValueList").size();
});
function check(){
	if(activeLi<ulNum){
		alert("选项未勾选完");
		return false;
	}
	else
		return true;
}
function getSkprIdList() {
	var skprIdList="";
	var ulList=$("ul.skuValueList");
	var i=0;
	ulList.each(function (index,element) {
		var liList=$(element).find("li");
		liList.each(function (index2,element2) {
			if($(element2).hasClass("active")) {
				skprIdList += $(element2).attr("skprId");
				i++;
				if(i<ulNum)
					skprIdList+=",";
			}
		})
	})
	return skprIdList
}

function buy(){
	if(!check())
		return;
	alert("购买");
	$.ajax("/order/buy",{
		data:{
			'skuId':$("#skuId").attr('skuId'),
			'num':$("#qty_item_1").val()
		}
	}).done(function (data) {
		document.write(data)
	}).fail(function (xhr, status) {
		alert("购买失败"+status)
})

}
function addToCart(){
	if(!check())
		return;
	alert("加入购物车");
	$.ajax("/cart/addProduct",{
		data:{
			'skuId':$("#skuId").attr('skuId'),
			'num':$("#qty_item_1").val()
		}
	}).done(function (data) {
		if(data=="success")
			alert("添加成功")
		else
			alert("添加失败")
	}).fail(function (xhr, status) {
		alert("购买失败"+status)
	})

}
