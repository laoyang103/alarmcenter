$(function() {
    var logid = $("#logid").val();
    getLogList(logid)
});

function getLogList(logid) {
  $.ajax({
    url: "/logs?logid=" + logid,
    type: "GET",
    success: function(data){
      if(data["code"] == 2000) {
        var html = "";
        var status;
        var log = data["log_list"][0];
        if(log["status"] == 1) status = "已发送";
        else if(log["status"] == 2)status = "限频";
        else if(log["status"]== 3)status = "测试";
        else status = "创建";
 
        html += "<div class=\"weui-cells\">"
        + "<div class=\"weui-cell\">" + "<div class=\"weui-cell__bd\">" + "<p>报告ID</p>"     + "</div>" + "<div class=\"weui-cell__ft\">" + log["reportId"]                            + "</div></div>"
        + "<div class=\"weui-cell\">" + "<div class=\"weui-cell__bd\">" + "<p>告警级别</p>"   + "</div>" + "<div class=\"weui-cell__ft\">" + log["level"]                               + "</div></div>"  
        + "<div class=\"weui-cell\">" + "<div class=\"weui-cell__bd\">" + "<p>告警主机</p>"   + "</div>" + "<div class=\"weui-cell__ft\">" + log["ip"]                                  + "</div></div>"  
        + "<div class=\"weui-cell\">" + "<div class=\"weui-cell__bd\">" + "<p>告警时间</p>"   + "</div>" + "<div class=\"weui-cell__ft\">" + formatDate(new Date(log["createTime"]))    + "</div></div>"  
        + "<div class=\"weui-cell\">" + "<div class=\"weui-cell__bd\">" + "<p>告警名称</p>"   + "</div>" + "<div class=\"weui-cell__ft\">" + log["alarmName"]                           + "</div></div>" 
        + "<div class=\"weui-cell\">" + "<div class=\"weui-cell__bd\">" + "<p>告警项目</p>"   + "</div>" + "<div class=\"weui-cell__ft\">" + log["projectName"]                         + "</div></div>" 
        + "<div class=\"weui-cell\">" + "<div class=\"weui-cell__bd\">" + "<p>告警模块</p>"   + "</div>" + "<div class=\"weui-cell__ft\">" + log["moduleName"]                          + "</div></div>" 
        + "<div class=\"weui-cell\">" + "<div class=\"weui-cell__bd\">" + "<p>告警内容</p>"   + "</div>" + "<div class=\"weui-cell__ft\">" + log["content"]                             + "</div></div>" 
        + "<div class=\"weui-cell\">" + "<div class=\"weui-cell__bd\">" + "<p>告警状态</p>"   + "</div>" + "<div class=\"weui-cell__ft\">" + status                                     + "</div></div>" 
        + "<div class=\"weui-cell\">" + "<div class=\"weui-cell__bd\">" + "<p>接收组</p>"     + "</div>" + "<div class=\"weui-cell__ft\">" + log["groupName"]                           + "</div></div>" 
        + "<div class=\"weui-cell\">" + "<div class=\"weui-cell__bd\">" + "<p>接收人</p>"     + "</div>" + "<div class=\"weui-cell__ft\">" + log["receivers"]                           + "</div></div>"
        "</div>"
        html = html.replace("NaN", "");
        $(".log-detail").html(html);
      }
    }
  });
}
