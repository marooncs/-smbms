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

function deleteBill(obj){
    $.get({
        url:path + "/jsp/bill.do?method=delBill",
        data:{bid:obj.attr("billid")},
        dataType:"json",
        success:function(data) {
            if(data.delResult == "true"){//删除成功：移除删除行
                cancleBtn();
                obj.parents("tr").remove();
            }else if(data.delResult == "false"){//删除失败
                changeDLGContent("sorry,delete ["+obj.attr("billcode")+"] failed");
            }else if(data.delResult == "notExist"){
                changeDLGContent("sorry, ["+obj.attr("billcode")+"] not exist");
            }
        }
    });
}

$(function () {
    $(".viewBill").on("click",function () {
        var obj = $(this);
        window.location.href = path + "/jsp/bill.do?method=viewBill&bid=" + obj.attr("billid");
    })
    $(".modifyBill").on("click",function(){
        var obj = $(this);
        window.location.href = path + "/jsp/bill.do?method=modifyBill&bid="+ obj.attr("billid");
    });
    $(".deleteBill").on("click",function () {
        userObj = $(this);
        changeDLGContent("Confirm delete bill: [" + userObj.attr("billcode") + "]?");
        openYesOrNoDLG();
    })
    $('#no').click(function () {
        cancleBtn();
    });

    $('#yes').click(function () {
        deleteBill(userObj);
    });

})