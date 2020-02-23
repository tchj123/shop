// JavaScript Document
$(document).ready(function(){

	//创建一个*
	$(".a1").each(function(){

		var hdw = $("<strong class='reda'>*</strong>");

		$(this).parent().append(hdw);

	});
	//end


	$("#username").blur(function () {
		$(this).parent().find(".a2").remove();
		var hdw1;
		if (this.value=="" || this.value.length < 6)
			hdw1 = $("<span class='a2 error'>用户名不得小于6位</span>");
		else
			hdw1 = $("<span class='a2 righta'>正确</span>");
		$(this).parent().append(hdw1);
	})


	$("#password").blur(function(){

		$(this).parent().find(".a2").remove();
		var hdw1;

		if (this.value=="")
			hdw1 = $("<span class='a2 error'>密码不得为空</span>");
		else
			hdw1 = $("<span class='a2 righta'>正确</span>");

			$(this).parent().append(hdw1);
	})


	$("#repassword").blur(function () {

		$(this).parent().find(".a2").remove();
		var hdw1;

		if(this.value=="" )
			hdw1 = $("<span class='a2 error'>不能为空</span>");
		else if (this.value!= $("#password").val())
			hdw1 = $("<span class='a2 error'>两次密码不一样</span>");
		else
			hdw1 = $("<span class='a2 righta'>正确</span>");

		$(this).parent().append(hdw1);
	})


	$("#email").blur(function () {

		$(this).parent().find(".a2").remove();
		var hdw1

		if (this.value=="" || ( this.value!="" && !/.+@.+\.[a-zA-Z]{2,4}$/.test(this.value) ))
			hdw1 = $("<span class='a2 error'>邮件的格式不正确</span>")
		else
			hdw1 = $("<span class='a2 righta'>正确</span>");

		$(this).parent().append(hdw1);
	})

	$("#tel").blur(function () {

		$(this).parent().find(".a2").remove();
		var hdw1

		if (this.value=="" || isNaN($(this).val()) || this.value.length < 11 )
			hdw1 = $("<span class='a2 error'>手机号不得为空，必须是11位数字</span>");
		else
			hdw1 = $("<span class='a2 righta'>正确</span>");

		$(this).parent().append(hdw1);
	})

	//注册
	$(".registerButton").click(function(){

		$("form :input").trigger("blur");

		var hdw3 = $(".error").length;

		if (hdw3!=0){
			return false;
		}

		$.ajax("/register",{
			data:{
				'username':$('#username').val(),
				'password':$('#password').val(),
				'phone':$('#tel').val(),
				'email':$('#email').val()
			},
			method:"POST"
		}).done(function (data) {
			switch (data) {
				case "SUCCESS":
					alert("注册成功，即将跳转")
					window.location.replace("/")
					break;
				case "FAIL":
					alert("注册失败")
					break;
				case "USERNAME_REPEATED":
					alert("注册失败，用户名重复")
					break;
				case "PASSWORD_INVALID":
					alert("注册失败，密码不合法")
					break;
				case "VERFICATION_CODE_INVALID":
					alert("验证码错误")
					break;
			}
		}).fail(function (xhr, status) {
			alert("注册失败"+status)
		})
		return false;

	});



	//重置
	$("#res").click(function(){

		$(".a2").remove();

	});
	//end














});