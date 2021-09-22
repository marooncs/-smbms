var proContact = null;
var proPhone = null;
var saveBtn = null;
var backBtn = null;

$(function () {
    proContact = $('#proContact');
    proPhone = $('#proPhone');
    saveBtn = $('#save');
    backBtn = $('#back');

    proContact.next().html('*');
    proPhone.next().html('*');

    proContact.on("focus",function(){
        validateTip(proContact.next(),{"color":"#666666"},"* please insert contact person",false);
    }).on("blur",function(){
        if(proContact.val() != null && proContact.val() != ""){
            validateTip(proContact.next(),{"color":"green"},imgYes,true);
        }else{
            validateTip(proContact.next(),{"color":"red"},imgNo+" contact person is null, re-set please",false);
        }

    });

    proPhone.bind("focus",function(){
        validateTip(proPhone.next(),{"color":"#666666"},"* insert phone number",false);
    }).bind("blur",function(){
        var patrn=/^(1[0-9][0-9])\d{8}$/;
        if(proPhone.val().match(patrn)){
            validateTip(proPhone.next(),{"color":"green"},imgYes,true);
        }else{
            validateTip(proPhone.next(),{"color":"red"},imgNo + " phone number pattern is wrong",false);
        }
    });

    saveBtn.on("click",function() {
        proContact.blur();
        proPhone.blur();
        if(proContact.attr("validateStatus") == "true"
            && proPhone.attr("validateStatus") == "true"){
            if(confirm("Confirm to change the data?")){
                $("#providerForm").submit();
            }
        }
    });

    backBtn.on("click",function(){
        if(referer != undefined
            && null != referer
            && "" != referer
            && "null" != referer
            && referer.length > 4){
            window.location.href = referer;
        }else{
            history.back(-1);
        }
    });


})