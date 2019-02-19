
$(function() {
    $("#wxbind").click(function () {
        var username = $("#username").val();
        var password = $("#password").val();
        var wxopenid = $("#wxopenid").val();

        if(username.trim().length == 0 || password.trim().length == 0 || wxopenid.trim().length == 0) {
            alert("用户名或密码或微信OPENID不能为空");
            return;
        }

        var wxbind_data = {
            username: username,
            password: password,
            wxopenid: wxopenid
        };

        wxbind(wxbind_data);
    });
});

function wxbind(wxbind_data) {
    $.ajax({
        url: "/wxbind",
        type: "POST",
        data: JSON.stringify(wxbind_data),
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        success: function(data){
            if (data["code"] != 2000) {
                alert("用户名或密码错误");
                return;
            }
            alert("绑定成功！");
            window.location = "/user";
        }
    });
}
