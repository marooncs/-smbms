function page_nav(form,num){
    form.pageIndex.value = num;
    form.submit();
}

function jump_to(form,num){
    //alert(num);
    //验证用户的输入
    var regexp=/^[1-9]\d*$/;
    var totalPageCount = document.getElementById("totalPageCount").value;
    //alert(totalPageCount);
    if(!regexp.test(num)){
        alert("Please enter a positive integer greater than 0!");
        return false;
    }else if((num-totalPageCount) > 0){
        alert("Please enter a page number less than the total number of pages");
        return false;
    }else{
        page_nav(form,num);
    }
}