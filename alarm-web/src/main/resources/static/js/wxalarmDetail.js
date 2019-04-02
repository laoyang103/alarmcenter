$(function() {
    var aid = $("#aid").val();
    getWxAlarmById(aid)
});

function getWxAlarmById(aid) {
  $.ajax({
    url: "/alarmDetail/" + aid,
    type: "GET",
    success: function(data) {
      var i, html = "";
      var alarm = data["alarm"];
      html += "<div class=\"weui-cells\">"
      + "<div class=\"weui-cell\">" + "<div class=\"weui-cell__bd\">" + "<p>告警代号</p>"   + "</div>" + "<div class=\"weui-cell__ft\">" + alarm["alarm"]["code"]                        + "</div></div>"
      + "<div class=\"weui-cell\">" + "<div class=\"weui-cell__bd\">" + "<p>告警名称</p>"   + "</div>" + "<div class=\"weui-cell__ft\">" + alarm["alarm"]["name"]                        + "</div></div>"  
      + "<div class=\"weui-cell\">" + "<div class=\"weui-cell__bd\">" + "<p>告警项目</p>"   + "</div>" + "<div class=\"weui-cell__ft\">" + alarm["project"]["name"]                        + "</div></div>"  
      + "<div class=\"weui-cell\">" + "<div class=\"weui-cell__bd\">" + "<p>告警模块</p>"   + "</div>" + "<div class=\"weui-cell__ft\">" + alarm["module"]["name"]                        + "</div></div>"  
      + "<div class=\"weui-cell\">" + "<div class=\"weui-cell__bd\">" + "<p>接收组</p>"     + "</div>" + "<div class=\"weui-cell__ft\">" + alarm["group"]["name"]                        + "</div></div>" 
      + "<div class=\"weui-cell\">" + "<div class=\"weui-cell__bd\">" + "<p>路由KEY</p>"    + "</div>" + "<div class=\"weui-cell__ft\">" + alarm["alarm"]["routeKey"]                    + "</div></div>" 
      + "<div class=\"weui-cell\">" + "<div class=\"weui-cell__bd\">" + "<p>配置信息</p>"   + "</div>" + "<div class=\"weui-cell__ft\">" + alarm["config"]                      + "</div></div>" 
      "</div>"

      html += "<a href=\"javascript:void(0);\" onclick=\"testAlarm(" + alarm["code"]
      + ")\" class=\"weui-btn weui-btn_primary\">测试告警机制</a>"

      html = html.replace("NaN", "");
      $(".alarm-detail").html(html);
    }
  });
}

function testAlarm(aid) {
    $.ajax({
        url: "/testAlarm/"+aid,
        type: "GET",
        data: null,
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        success: function(data){
            if(data["code"] != 2000) {
                alert("测试告警失败");
                return;
            }
            alert("测试告警已发送");
        }
    });
}
