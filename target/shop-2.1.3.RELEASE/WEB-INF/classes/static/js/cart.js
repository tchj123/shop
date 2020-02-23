$(document).ready(function(){

	//每个物品分别计算价格
	$("#normal .content2.center").each(function (index,element) {
		var price=$($(this).find(".sub_content:eq(3)")).attr('price')
		var num=$($(this).find(".sub_content:eq(4) input")).val()
		//计算总价
		var node=$(this).find(".sub_content:eq(5)")
		var totalPrice=num*price;
		node.text(totalPrice+"元")
		node.attr('totalPrice',totalPrice)

	})
	//单选按钮的个数
	var numOfSelectButton=$(".content2 :checkbox").length;
	//已经选择了的单选按钮
	var numOfSelected=0;

	//全选按钮
	$(".sub_top :checkbox").click(function () {
		var checked=this.checked
		$(".content2 :checkbox").each(function(index,element){
			$(element).prop("checked",checked)
		})
		numOfSelected=checked?numOfSelectButton:0;

		calcTotalPrice()
	})

	//单选按钮
	$(".content2 :checkbox").click(function () {
		if(this.checked)
			numOfSelected++;
		else
			numOfSelected--;

		//如果单选按钮全选了，则将全选按钮激活，否则取消全选按钮
		if(numOfSelected==numOfSelectButton)
			$(".sub_top :checkbox").prop("checked",true)
		else
			$(".sub_top :checkbox").prop("checked",false)
		calcTotalPrice()
	})

	//数量按钮
	$(".shuliang").change(function () {
		var div=$(this).parent().parent();
		var price=div.find(".sub_content:eq(3)").attr("price")
		var num=$(this).val()
		$.ajax("/cart/changeNum",{
			data:{
				'cartId':div.find("[name=cartId]").attr('cartId'),
				'num':num
			}
		}).done(function (data) {
			if(data=="success"){
				calcTotalPrice()
			}else if(data=="fail")
				alert("改变数量失败嗷")
		}).fail(function (xhr,status) {
			alert("改变数量失败"+status)
		})
		//计算总价
		var price=num*price;
		var node=div.find(".sub_content:eq(5)")
		node.text(price+"元")
		node.attr('totalPrice',price)
	})

	//计算总价格
	function calcTotalPrice(){
		var totalPrice=0;
		$(".content2 :checkbox").each(function(index,element){
			if($(element).prop("checked")){
				var div=$(this).parent().parent();
				totalPrice+=Number(div.find(".sub_content:eq(5)").attr('totalPrice'))
			}
		})
		$(".jiesuanjiage span").text(totalPrice+'元')
		$(".jiesuanjiage").attr('totalPrice',totalPrice)
	}

	//提交提单
	$(".jsanniu").click(function () {
		if(numOfSelected<=0){
			alert("还没有选择任何商品！")
			return
		}
		$.ajax("/order/getCreateOrderPage",{
			contentType: 'application/json;charset=utf-8',
			data:getCartItemIdList(),
			method:"POST"
		}).done(function (data) {
			document.write(data)
		}).fail(function (xhr,status) {
			alert("失败")
		})
	})
	function getCartItemIdList() {
		var list=$("#normal .content2.center")
		var result=new Array()
		for(var i=0;i<list.length;i++)
		{
			if (!$(list[i]).find(":checkbox").prop('checked'))
				continue;
			var cartId = $(list[i]).find("a[name=cartId]").attr('cartId')
			result.push(Number(cartId));
		}
		return JSON.stringify(result)
	}
})
