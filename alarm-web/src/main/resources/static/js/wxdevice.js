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
        var user_data = {
            account: user_account,
            password: password,
            name: user_name,
            mail: $("#user-mail").val(),
            phone: $("#user-phone").val(),
            wechat: $("#user-wechat").val(),
            qq: $("#user-qq").val()
        };
        var device_data = {
            macs: $("#user-macs").val(),
            cpus: $("#user-cpus").val()
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
            } 
            userid = data["userid"];
        }
    });
    device_data["userid"] = userid;
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
                alert("新建用户设备成功！");
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
            }
        }
    });
}
