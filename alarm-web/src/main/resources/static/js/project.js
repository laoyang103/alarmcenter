var search_select="";
var search_input="";

$(function() {
    // project create modal
    $("#project-create").click(function () {
        var project_name = $("#project-name").val();
        if (project_name.trim().length == 0) {
            alert("请输入项目名称");
            return;
        }
        var project_ip = $("#project-ip").val();
        if (project_ip.trim().length == 0) {
            alert("请输入远端IP");
            return;
        }
        var project_data = {
            name: project_name,
            ip: projectIp2int(project_ip),
            comment: $("#project-comment").val()
        };
        $("#modal-project-create").modal('hide');
        createProject(project_data);
    });

    // project update modal
    $("#project-update").click(function () {
        var project_id = $("#current-id").attr("_pid");
        if (project_id == undefined) {
            console.log("update project fail, project_id=" + project_id);
            return;
        }
        var project_name = $("#project-name-update").val();
        if (project_name.trim().length == 0) {
            alert("请输入项目名称");
            return;
        }
        var project_ip = $("#project-ip-update").val();
        if (project_ip.trim().length == 0) {
            alert("请输入远端IP");
            return;
        }
        var project_data = {
            id: project_id,
            ip: projectIp2int(project_ip),
            name: project_name,
            comment: $("#project-comment-update").val()
        };
        $("#modal-project-update").modal('hide');
        updateProject(project_data);
    });

    // project delete modal
    $("#project-del").click(function() {
        var project_id = $("#current-id").attr("_pid");
        if(project_id == undefined) {
            console.log("delete project fail, project_id=" + project_id);
            return;
        }
        deleteProject(project_id);
    });

    // init data table
    getProjectList();

});


function projectInt2iP(num) 
{
  var str;
  var tt = new Array();
  tt[0] = (num >>> 24) >>> 0;
  tt[1] = ((num << 8) >>> 24) >>> 0;
  tt[2] = (num << 16) >>> 24;
  tt[3] = (num << 24) >>> 24;
  str = String(tt[0]) + "." + String(tt[1]) + "." + String(tt[2]) + "." + String(tt[3]);
  return str;
}

function projectIp2int(ip) 
{
  var num = 0;
  ip = ip.split(".");
  num = Number(ip[0]) * 256 * 256 * 256 + Number(ip[1]) * 256 * 256 + Number(ip[2]) * 256 + Number(ip[3]);
  num = num >>> 0;
  return num;
}

function getProjectList(search) {
    $.ajax({
        url: "/projects?page_no="+current_page+"&page_size="+page_length+"&"+search,
        type: "GET",
        success: function(data){
            if(data["code"] == 2000) {
                // project
                var i, html = "";
                var project_list = data["project_list"];
                var permission_mapper = data["permission_mapper"];
                for(i = 0; i < project_list.length; i++) {
                    var project  = project_list[i];
                    html += "<tr><td>" + project["id"] + "</td>"
                        + "<td>" + project["name"] + "</td>"
                        + "<td>" + project["creater"] + "</td>"
                        + "<td>" + formatDate(new Date(project["createTime"])) + "</td>"
                        + "<td>" + projectInt2iP(project["ip"]) + "</td>"
                        + "<td>" + project["port"] + "</td>"
                        + "<td>" + project["comment"] + "</td>";

                    if (permission_mapper[project["id"]] == 0 || data["user"]["type"] == 0) {
                        html += "<td><button class='btn btn-info project-update' _pid='" + project["id"] + "' "
                            + "data-toggle='modal' data-target='#modal-project-update' "
                            + "style='padding:0;margin:0;width:40px;height:26px;'>编辑</button>"
                            + "&nbsp;&nbsp; <button class='btn btn-danger project-del' _pid='" + project["id"] + "' "
                            + "data-toggle='modal' data-target='#modal-project-del' "
                            + "style='padding:0;margin:0;width:40px;height:26px;'>删除</button></td></tr>";
                    } else {
                        html += "<td></td></tr>"
                    }
                }
                $(".project-list").html(html);
                setProjectClickEvent();
                var page_count = parseInt((data["project_count"] + page_length - 1)/page_length);
                $(".page-footer").html(setPageButton(page_count, current_page));
                setPageBtnClick();
                setTableTotalSize(data["project_count"]);
            }
        }
    });
}

function setProjectClickEvent() {
    $(".project-update").click(function() {
        var project_id = $(this).attr("_pid");
        $("#current-id").attr("_pid", project_id);
        getProjectById(project_id);
    });

    $(".project-del").click(function() {
        $("#current-id").attr("_pid", $(this).attr("_pid"));
    });
}

function getProjectById(pid) {
    $.ajax({
        url: "/projects/"+pid,
        type: "GET",
        success: function(data){
            if(data["code"] == 2000) {
                var project = data["project"];
                $("#project-name-update").val(project["name"]);
                $("#project-ip-update").val(projectInt2iP(project["ip"]));
                $("#project-comment-update").val(project["comment"]);
            }
        }
    });
}

function createProject(project_data) {
    $.ajax({
        url: "/projects",
        type: "POST",
        data: JSON.stringify(project_data),
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        success: function(data){
            if (data["code"] != 2000) {
                alert("新建项目失败");
            }
            location.replace(location.href);
        }
    });
}

function updateProject(project_data) {
    $.ajax({
        url: "/projects",
        type: "PUT",
        data: JSON.stringify(project_data),
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        success: function(data){
            if(data["code"] != 2000) {
                alert("更新项目信息失败");
                return;
            }
            getProjectList(search_select + "=" + search_input);
        }
    });
}

function deleteProject(pid) {
    $.ajax({
        url: "/projects/"+pid,
        type: "DELETE",
        data: JSON.stringify({}),
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        success: function(data){
            if(data["code"] != 2000) {
                alert("删除项目失败");
                return;
            }
            getProjectList(search_select + "=" + search_input);
        }
    });
}

function setPageBtnClick() {
    $(".page-btn").click(function () {
        current_page = $(this).attr("_val");
        getProjectList(search_select + "=" + search_input);
    });
}

function setTableTotalSize(count) {
    if (count == null || count == undefined) count = 0;
    $(".table-total-size").text("共有 "+count+" 条数据");
}
