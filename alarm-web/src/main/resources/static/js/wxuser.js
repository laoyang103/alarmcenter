var search_select="";
var search_input="";

$(function() {
    // user create modal
    $("#user-create").click(function () {
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
        $("#modal-user-create").modal('hide');
        createUser(user_data);
    });
});

function createUser(user_data) {
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
            window.location = "/wxlogin";
        }
    });
}
