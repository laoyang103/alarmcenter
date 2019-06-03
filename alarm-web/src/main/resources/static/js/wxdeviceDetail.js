$(function() {
	var did = $("#did").val();
	$.ajax({
	    url: "/getDeviceById/" + did,
	    type: "GET",
	    success: function(data) {
        var content = new Array();
        var title = new Array("设备名称", "网卡地址", "CPUID", "最大分析流量", "日期", "多观察点", "服务端", "客户端", "HTTP", "URL", "报文", "MYSQL", "ORACLE", "SQLServer", "拓扑图", "通信对", "流量存储", "地图", "iDigger");
	    	var html = "<div class=\"weui-cells\">";

	      content[0] = data.device.devname;
	      content[1] = data.device.macs;
	      content[2] = data.device.cpus;
	      content[3] = data.device.maxFlow;
	      content[4] = new Date(parseInt(data.device.validTerm) * 1000).toLocaleString().replace(/:\d{1,2}$/,' ');
        content[5] = data.device.manyWatchpoint == 1 ? "开启":"关闭";
        content[6] = data.device.server == 1 ? "开启":"关闭";
        content[7] = data.device.client == 1 ? "开启":"关闭";
        content[8] = data.device.http == 1 ? "开启":"关闭";
        content[9] = data.device.url == 1 ? "开启":"关闭";
        content[10] = data.device.message == 1 ? "开启":"关闭";
        content[11] = data.device.mysql == 1 ? "开启":"关闭";
        content[12] = data.device.oracle == 1 ? "开启":"关闭";
        content[13] = data.device.sqlserver == 1 ? "开启":"关闭";
        content[14] = data.device.topo == 1 ? "开启":"关闭";
        content[15] = data.device.trafficPair == 1 ? "开启":"关闭";
        content[16] = data.device.flowStorage == 1 ? "开启":"关闭";
        content[17] = data.device.map == 1 ? "开启":"关闭";
        content[18] = data.device.digger == 1 ? "开启":"关闭";

        for ( var i = 0; i < title.length; i++) {
          html +="<div class=\"weui-cell\">" + "<div class=\"weui-cell__bd\">" + "<p>" + title[i] + "</p>"  
          + "</div>" + "<div class=\"weui-cell__ft\">" + content[i] + "</div></div>"
        }
            
	    	html += "</div>";
	    	$("#deviceDetail").html(html);
	    }
	  });
})
