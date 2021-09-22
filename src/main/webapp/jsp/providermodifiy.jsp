<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<%@include file="common/header.jsp"%>
<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>供应商管理页面 >> 供应商信息修改页面</span>
    </div>
    <div class="providerAdd">
        <form id="providerForm" name="providerForm" method="post" action="${pageContext.request.contextPath }/jsp/provider.do">
            <input type="hidden" name="method" value="modifydata" >
            <input type="hidden" name="proId" value="${provider.id}" >
            <div class="">
                <label for="proCode">供应商编码：</label>
                <input type="text" name="proCode" id="proCode" value="${provider.proCode}" readonly="readonly">
            </div>
            <div>
                <label for="proName">供应商名称：</label>
                <input type="text" name="proName" id="proName" value="${provider.proName}">
                <font color="red"></font>
            </div>

            <div>
                <label for="proContact">联系人：</label>
                <input type="text" name="proContact" id="proContact" value="${provider.proContact}">
                <font color="red"></font>
            </div>

            <div>
                <label for="proPhone">联系电话：</label>
                <input type="text" name="proPhone" id="proPhone" value="${provider.proPhone}">
                <font color="red"></font>
            </div>

            <div>
                <label for="proAddress">联系地址：</label>
                <input type="text" name="proAddress" id="proAddress" value="${provider.proAddress}">
            </div>

            <div>
                <label for="proFax">传真：</label>
                <input type="text" name="proFax" id="proFax" value="${provider.proFax}">
            </div>

            <div>
                <label for="proDesc">描述：</label>
                <input type="text" name="proDesc" id="proDesc" value="${provider.proDesc}">
            </div>
            <div class="providerAddBtn">
                <input type="button" name="save" id="save" value="保存">
                <input type="button" id="back" name="back" value="返回" >
            </div>
        </form>
    </div>

</div>
</section>

<%@include file="common/footer.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/providermodify.js"></script>