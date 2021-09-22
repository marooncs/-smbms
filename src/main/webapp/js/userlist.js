var userObj;

function changeDLGContent(contentStr){
    var p = $(".removeMain").find("p");
    p.html(contentStr);
}
function openYesOrNoDLG(){
    $('.zhezhao').css('display', 'block');
    $('#removeUse').fadeIn();
}
function cancleBtn(){
    $('.zhezhao').css('display', 'none');
    $('#removeUse').fadeOut();
}
function deleteUser(obj){
    $.ajax({
        type:"GET",
        url:path+"/jsp/user.do",
        data:{method:"deluser",uid:obj.attr("userid")},
        dataType:"json",
        success:function(data){
            if(data.delResult == "true"){//删除成功：移除删除行
                cancleBtn();
                obj.parents("tr").remove();
            }else if(data.delResult == "false"){//删除失败
                //alert("对不起，删除用户【"+obj.attr("username")+"】失败");
                changeDLGContent("sorry,delete ["+obj.attr("username")+"] failed");
            }else if(data.delResult == "notExist"){
                //alert("对不起，用户【"+obj.attr("username")+"】不存在");
                changeDLGContent("sorry, ["+obj.attr("username")+"] not exist");
            }
        },
        error:function(data){
            //alert("对不起，删除失败");
            changeDLGContent("sorry, delete failed");
        }
    });
}

$(function () {
    $(".viewUser").on("click",function () {
        var obj = $(this);
        window.location.href = path + "/jsp/user.do?method=viewUser&uid=" + obj.attr("userid");
    })
    $(".modifyUser").on("click",function(){
        var obj = $(this);
        window.location.href=path+"/jsp/user.do?method=modifyUser&uid="+ obj.attr("userid");
    });
    $(".deleteUser").on("click",function () {
        userObj = $(this);
        changeDLGContent("Confirm delete [" + userObj.attr("username") + "]?");
        openYesOrNoDLG();
    })

    $('#no').click(function () {
        cancleBtn();
    });

    $('#yes').click(function () {
        deleteUser(userObj);
    });

})