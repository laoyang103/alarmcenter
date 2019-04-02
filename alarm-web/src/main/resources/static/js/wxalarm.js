$(function() {
    getWxAlarmList();
});

function getWxAlarmList(aid) {
  $.ajax({
    url: "/alarms",
    type: "GET",
    success: function(data) {
      var i, html = "";
      var alarm_list = data["alarm_list"];
      for(i = 0; i < alarm_list.length; i++) {
        var alarm  = alarm_list[i];
        html += "<a class=\"weui-cell weui-cell_access\" href=\"/wxalarmDetail?aid=" + alarm["alarm"]["code"] + "\">"
        + "<div class=\"weui-cell\"><div class=\"weui-cell__bd\">" +
        + "<p>" + alarm["alarm"]["code"] + ": " + alarm["alarm"]["name"] + "</p>"
        + "</div>"
        + "<div class=\"weui-cell__ft\">"
        + "<p class=\"weui_media_desc\">" + alarm["config"] + "</p>"
        + "</div></div></a>";
      }
      html = html.replace("NaN", "");
      $(".alarm-list").html(html);
    }
  });
}

