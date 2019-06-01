$(function() {
	var did = $("#did").val();
	$.ajax({
	    url: "/getDeviceById/" + did,
	    type: "GET",
	    success: function(data) {
	    	var html = "<div class=\"weui-cells\">";
           
	    	html +="<div class=\"weui-cell\">" + "<div class=\"weui-cell__bd\">" + "<p>用户名</p>"  
	        + "</div>" + "<div class=\"weui-cell__ft\">" + data.device.id + "</div></div>"
	            
	        html +="<div class=\"weui-cell\">" + "<div class=\"weui-cell__bd\">" + "<p>网卡地址</p>"  
	        + "</div>" + "<div class=\"weui-cell__ft\">" + data.device.macs + "</div></div>"
	            
	        html +="<div class=\"weui-cell\">" + "<div class=\"weui-cell__bd\">" + "<p>CPUID</p>"  
	        + "</div>" + "<div class=\"weui-cell__ft\">" + data.device.cpus + "</div></div>"
	            
	        html +="<div class=\"weui-cell\">" + "<div class=\"weui-cell__bd\">" + "<p>最大分析流量</p>"  
	        + "</div>" + "<div class=\"weui-cell__ft\">" + data.device.maxFlow + "</div></div>"
	            
	        html +="<div class=\"weui-cell\">" + "<div class=\"weui-cell__bd\">" + "<p>日期</p>"  
	        + "</div>" + "<div class=\"weui-cell__ft\">" + data.device.validTerm + "</div></div>"
	            
	    	html +="<div class=\"weui-cell\">" + "<div class=\"weui-cell__bd\">" + "<p>多观察点</p>"  
            + "</div>" + "<div class=\"weui-cell__ft\">" + data.device.manyWatchpoint + "</div></div>"
            
            html +="<div class=\"weui-cell\">" + "<div class=\"weui-cell__bd\">" + "<p>服务端</p>"  
            + "</div>" + "<div class=\"weui-cell__ft\">" + data.device.server + "</div></div>"
            
            html +="<div class=\"weui-cell\">" + "<div class=\"weui-cell__bd\">" + "<p>客户端</p>"  
            + "</div>" + "<div class=\"weui-cell__ft\">" + data.device.client + "</div></div>"
            
            html +="<div class=\"weui-cell\">" + "<div class=\"weui-cell__bd\">" + "<p>HTTP</p>"  
            + "</div>" + "<div class=\"weui-cell__ft\">" + data.device.http + "</div></div>"
            
            html +="<div class=\"weui-cell\">" + "<div class=\"weui-cell__bd\">" + "<p>URL</p>"  
            + "</div>" + "<div class=\"weui-cell__ft\">" + data.device.url + "</div></div>"
            
            html +="<div class=\"weui-cell\">" + "<div class=\"weui-cell__bd\">" + "<p>报文</p>"  
            + "</div>" + "<div class=\"weui-cell__ft\">" + data.device.message + "</div></div>"
            
            html +="<div class=\"weui-cell\">" + "<div class=\"weui-cell__bd\">" + "<p>MYSQL</p>"  
            + "</div>" + "<div class=\"weui-cell__ft\">" + data.device.mysql + "</div></div>"
            
            html +="<div class=\"weui-cell\">" + "<div class=\"weui-cell__bd\">" + "<p>ORACLE</p>"  
            + "</div>" + "<div class=\"weui-cell__ft\">" + data.device.oracle + "</div></div>"
            
            html +="<div class=\"weui-cell\">" + "<div class=\"weui-cell__bd\">" + "<p>SQLServer</p>"  
            + "</div>" + "<div class=\"weui-cell__ft\">" + data.device.sqlserver + "</div></div>"
            
            html +="<div class=\"weui-cell\">" + "<div class=\"weui-cell__bd\">" + "<p>拓扑图</p>"  
            + "</div>" + "<div class=\"weui-cell__ft\">" + data.device.topo + "</div></div>"
            
            html +="<div class=\"weui-cell\">" + "<div class=\"weui-cell__bd\">" + "<p>通信对</p>"  
            + "</div>" + "<div class=\"weui-cell__ft\">" + data.device.trafficPair + "</div></div>"
            
            html +="<div class=\"weui-cell\">" + "<div class=\"weui-cell__bd\">" + "<p>流量存储</p>"  
            + "</div>" + "<div class=\"weui-cell__ft\">" + data.device.flowStorage + "</div></div>"
            
            html +="<div class=\"weui-cell\">" + "<div class=\"weui-cell__bd\">" + "<p>地图</p>"  
            + "</div>" + "<div class=\"weui-cell__ft\">" + data.device.map + "</div></div>"
            
            html +="<div class=\"weui-cell\">" + "<div class=\"weui-cell__bd\">" + "<p>iDigger</p>"  
            + "</div>" + "<div class=\"weui-cell__ft\">" + data.device.digger + "</div></div>"
            
	    	html += "</div>";
	    	$("#deviceDetail").html(html);
	    }
	  });
})