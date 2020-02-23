$(document).ready(function() {

    init_area();

    //编辑按钮
    $("input[name=edit]").click(function () {
        edits($(this).parent())
    })

    //选中地址块后高亮显示
    $("#addrList .addr").click(function () {
        $("#addrList .addr").each(function (index, element) {
            $(element).removeClass("active");
        });
        $(this).addClass("active");
    })

    //取消按钮的事件
    $("#cancel").on("click", function () {
        closes();
    })

    //添加新地址
    $("#addNewAddr").click(function () {

        let addrDiv = $("#template .addr");
        //显示弹出层和背景
        opens();

        //清除弹出窗的信息
        $("#receiverName").val("");
        $("#tel").val("");
        $("#street").val("");
        $("#zip").val("");
        $("#iDo-province").val("请选择省份")
        $("#iDo-province").trigger('onchange')
        $("#iDo-city").val("请选择城市")
        $("#iDo-city").trigger('onchange')
        $("#iDo-county").val("请选择地区")
        $("#iDo-county").trigger('onchange')
        $("#id").val("")

        //保存按钮的事件
        $("#save").unbind("click");
        $("#save").on("click", function () {
            saves(addrDiv);
            closes();
            //插入新的地址块
            $("#addrs").append($("#template").html());

            //为新插入的地址块绑定click事件
            let newAddrDiv = $("#addrs div:last-child");
            newAddrDiv.find("input[name=edit]").on("click", function () {
                edits(newAddrDiv)
            })
            newAddrDiv.find(".deleteButton").on("click", function () {
                deletes(newAddrDiv);
            })
            newAddrDiv.on("click", function () {
                $("#addrList .addr").each(function (index, element) {
                    $(element).removeClass("active");
                });
                $(this).addClass("active");
            })

        })

        $(".deleteButton").click(function () {
            let addrDiv = $(this).parent().parent();
            deletes(addrDiv);
        })

    })

    //为支付选项添加被选中后高亮特效
    $(".payChannel li").click(function () {
        $(".payChannel li").each(function (index, element) {
            $(element).removeClass("active");
        });
        $(this).addClass("active");
    })
    let cartIdList=[];
    $("input[name=cartId]").each(function (index,element) {
        cartIdList[index]=$(element).attr('cartId');
    });

    //提交订单按钮
    $("#checkoutToPay").click(function () {
        let payChannel=$(".payChannel .item.active").attr('payChannel');
        $.ajax("/order/createOrder",{
            contentType: 'application/x-www-form-urlencoded',
            traditional: true,
            data:{
                addrId:$(".addr.active input[name=addrId]").attr('addrId'),
                payChannel:$(".payChannel .item.active").attr('payChannel'),
                'cartIdList[]':cartIdList
            }
        }).done(function(data){
            if(data=="fail")
                alert("失败");
            location.replace("/pay/alipay?orderNo="+data);
        }).fail(function(xhr,status){
            alert("失败"+status);
        })


    })

})


//删除地址块
function deletes(addrDiv) {
    let addrId=addrDiv.find("input[name=addrId]").attr("addrId");
    addrDiv.detach();
}

//编辑地址
function edits(addrDiv) {

    //向弹出框添加信息
    $("#receiverName").val(addrDiv.find("[name=receiverName]").text());
    $("#tel").val(addrDiv.find("[name=tel]").text());
    $("#street").val(addrDiv.find("[name=street]").text());
    $("#zip").val(addrDiv.find("[name=zip]").text());
    $("#iDo-province").val(addrDiv.find("[name=province]").text())
    $("#iDo-province").trigger('onchange')
    $("#iDo-city").val(addrDiv.find("[name=city]").text())
    $("#iDo-city").trigger('onchange')
    $("#iDo-county").val(addrDiv.find("[name=area]").text())
    $("#iDo-county").trigger('onchange')
    $("#id").val(addrDiv.find("[name=addrId]").attr('addrId'))

    //显示弹出层和背景
    opens();

    //保存按钮的事件
    $("#save").unbind("click");
    $("#save").on("click",function () {
        saves(addrDiv);
        closes();

    })

}

//保存弹出窗中的信息
function saves(addrDiv){
    addrDiv.find("[name=receiverName]").text($("#receiverName").val())
    addrDiv.find("[name=tel]").text($("#tel").val())
    addrDiv.find("[name=street]").text($("#street").val());
    addrDiv.find("[name=zip]").text($("#zip").val());
    addrDiv.find("[name=province]").text($("#iDo-province").val())
    addrDiv.find("[name=city]").text($("#iDo-city").val())
    addrDiv.find("[name=area]").text($("#iDo-county").val())
}

//关闭弹出层和背景
function closes(){
    $(".background").css('display','none');
    $(".pop").css('display','none');
}

//显示弹出层和背景
function opens(){
    $(".background").css('display','block');
    $(".pop").css('display','block');
}

//弹出窗的数据校验
function check() {
}
