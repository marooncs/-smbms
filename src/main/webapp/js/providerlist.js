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
function deleteProvider(obj){
    $.get({
        url:path + "/jsp/provider.do?method=delProvider",
        data:{pid:obj.attr("proid")},
        dataType:"json",
        success:function (data) {
            if(data.delResult == "true"){//删除成功：移除删除行
                cancleBtn();
                obj.parents("tr").remove();
            }else if(data.delResult == "false"){//删除失败
                changeDLGContent("sorry,delete ["+obj.attr("proname")+"] failed");
            }else if(data.delResult == "notExist"){
                changeDLGContent("sorry, ["+obj.attr("proname")+"] not exist");
            }
        },
        error:function(data){
            //alert("对不起，删除失败");
            changeDLGContent("sorry, delete failed");
        }
    });
}

$(function () {
    $(".viewProvider").on("click",function () {
        var obj = $(this);
        window.location.href = path + "/jsp/provider.do?method=viewProvider&pid=" + obj.attr("proid");
    })
    $(".modifyProvider").on("click",function(){
        var obj = $(this);
        window.location.href = path + "/jsp/provider.do?method=modifyProvider&pid="+ obj.attr("proid");
    });
    $(".deleteProvider").on("click",function () {
        userObj = $(this);
        changeDLGContent("Confirm delete [" + userObj.attr("username") + "]?");
        openYesOrNoDLG();
    })
    $('#no').click(function () {
        cancleBtn();
    });

    $('#yes').click(function () {
        deleteProvider(userObj);
    });

})