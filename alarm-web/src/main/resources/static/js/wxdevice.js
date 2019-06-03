var search_select="";
var search_input="";

$(function() {
    // user create modal
    $("#user-device-create").click(function () {
        var user_account = $("#user-account").val();
        if (user_account.trim().length == 0) {
            alert("请输入用户账号");
            return;
        }
        var password = $("#password").val();
        if (password.trim().length == 0) {
            alert("请输入用户密码");
            return;
        }
        var user_name = $("#user-name").val();
        if (user_name.trim().length == 0) {
            alert("请输入用户名称");
            return;
        }
        var user_mail = $("#user-mail").val();
        if (user_mail.trim().length == 0) {
            alert("请输入邮箱");
            return;
        }else{
        	var myReg=/^[a-zA-Z0-9_-]+@([a-zA-Z0-9]+\.)+(com|cn|net|org)$/;
        	if(!myReg.test(user_mail)){
        		alert("邮箱格式不正确");
                return;
        	}
        }
        
        var user_phone = $("#user-phone").val();
        if (user_phone.trim().length == 0) {
            alert("请输入手机号");
            return;
        }else{
        	var myReg=/^[1][3,4,5,7,8,9][0-9]{9}$/;
        	if(!myReg.test(user_phone)){
        		alert("手机号不正确");
                return;
        	}
        }
        var time = $("#time").val();
        if (time.trim().length == 0) {
            alert("请输入日期");
            return;
        }else {
        	var d = new Date(time);
        	time = d.getTime() / 1000;
        }
        
        var maxFlow = $("#maxFlow").val();
        if (maxFlow.trim().length == 0) {
            alert("请输入最大分析流量");
            return;
        }else{
        	var re = /^[0-9]+.?[0-9]*/;
        	if(!re.test(maxFlow)){
        		alert("最大分析流量只能输入数字");
                return;
        	}
        }
        
        var box = document.getElementsByName('checkbox')
        var device_data = {};
        var checkedIndex = 0; 
        for(var i = 0; i <box.length; i++){
        	if(box[i].checked){
        		checkedIndex = 1;
        		device_data[box[i].value] = 1;
        	}else{
        		device_data[box[i].value] = 0;
        	}
        }
        
        if(checkedIndex == 0 ){
        	alert("请选择模块");
            return;
        }
        device_data["devname"] = $("#user-devname").val();
        device_data["macs"] = $("#user-macs").val();
        device_data["cpus"] = $("#user-cpus").val();
        device_data["maxFlow"] = parseInt(maxFlow);
        device_data["validTerm"] = time;
        
        var user_data = {
            account: user_account,
            password: password,
            name: user_name,
            mail: $("#user-mail").val(),
            phone: $("#user-phone").val(),
            wechat: $("#user-wechat").val()
        };
        
        createUserWithDevice(user_data, device_data);
    });

    $("#device-create").click(function () {
        var device_data = {
            userid: $("#user-userid").val(),
            macs: $("#user-macs").val(),
            cpus: $("#user-cpus").val()
        };
        bindDevice(device_data);
    });
});

function createUserWithDevice(user_data, device_data) {
    var userid = "";
    $.ajax({
        url: "/register",
        type: "POST",
        data: JSON.stringify(user_data),
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        success: function(data){
            if (data["code"] != 2000) {
                alert("新建用户账号失败，原因:"+data["reason"]);
            } else {
            	 device_data["userid"] =  data["userid"];
            	 bindDevice(device_data);
            } 
        }
    });
}

function bindDevice(device_data) {
    $.ajax({
        url: "/addDevice",
        type: "POST",
        data: JSON.stringify(device_data),
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        success: function(data){
            if (data["code"] != 2000) {
                alert("新建设备失败，原因:"+data["reason"]);
            } else {
                alert("绑定成功！");
                location.href="/wxdevice";
            }
        }
    });
}


function  getDeviceData(){
	$.ajax({
	    url: "/getDeviceList",
	    type: "GET",
	    success: function(data) {
	    	var html = "";
	    	var device_list = data["device_list"];
	    	for(var i = 0; i < device_list.length; i++){
	    		var device  = device_list[i];
	    		html += "<a class=\"weui-cell weui-cell_access\" href=\"/wxdeviceDetail?did="+ device.id +"\">";
	    		html += "<div class=\"weui-cell__bd\">";
	    		html += "<p>"+ device.devname+"</p>";
	    		html += "</div>";
	    		html += "<div class=\"weui-cell__ft\">";
	    		html += "</div></a>";
	    	}
	    	$("#deviceId").html(html);
	    }
	  });
}
getDeviceData();
