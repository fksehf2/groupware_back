<%
 /**
  * @Class Name : ifrFileUpload.jsp
  * @Description : 파일업로드
  * @Modification Information
  * 
  * @author 우성택
  * @since 2014.08.22
  * @version 1.0
  * @see
  *  
  * Copyright (C) All right reserved.
  */
%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="ko">
<head>

<!-- css 및 js include  -->
<%@ include file="/WEB-INF/views/frame/fcom/jimsCommon.jsp" %>

<title>파일 업로드</title>
<script type="text/javaScript" language="javascript" defer="defer">
<!--
<%/* 시작 처리 function */%>
function fn_onLoad() {
	<c:if test="${fn:length(message) gt 0}">
		parent.fn_showUserPage("<c:out value='${message}'/>");
	</c:if>
    /* if ("${message}" != "") {
    	fn_showUserPage("${message}");
    } */
}

<%/* 파일업로드 처리 function */%>
function fn_executeFileUpload(){

	if ($("#newFile").val() != "") {
	    form = document.forms[0];
	    form.action = "<c:url value = "/fileUpload.do"/>";
	    form.submit();
	    /* var form = document.forms[0];
    	var data = new FormData(form);
		$.ajax({
	        type: "POST",
	        enctype: 'multipart/form-data',
	        url: '<c:url value = "/fileUpload.do"/>',
	        data: data,
	        processData: false,
	        contentType: false,
	        cache: false,
	        timeout: 600000,
	        success: function (result) {
	        	console.log(result);
	        },
	        error: function (e) {
	        	//alert(data.message);
	        }
	    }); */
	}else {
		fn_showUserPage("<spring:message code="common.fileupload.notFile" />");
	}

}

<%/* 파일찾기 function */%>
function fn_fileSearch(objName, divName, type, maxFileNum){
	$("#objName").val(objName);
	$("#divName").val(divName);
	$("#type").val(type);
	$("#maxFileNum").val(maxFileNum);
    $("#newFile").trigger("click");
}

if ("${objName}" != "" && "${divName}" != "") {
	var retVal  = new Object();
	
	retVal.objName = "${objName}";
	retVal.divName = "${divName}";
	retVal.maxFileNum = "${maxFileNum}";
	retVal.filePth = "${fileVO.serverSubPath}";
	retVal.fileNm = "${fileVO.physicalName}";
	retVal.fileSize = "${fileVO.size}";
	retVal.oriFile = "${fileVO.fileName}";

    parent.gfn_fileUploadCallBack(retVal);
    parent.fn_fileCntChk();
}


// -->
</script>
</head>
<body onLoad="fn_onLoad();">
    <form id="frmUp" name="frmUp" action="/fileUpload.do" method="post" enctype="multipart/form-data" >
    <input type="file" name="newFile" id="newFile" onchange="fn_executeFileUpload()"/>
    <input type="hidden" id="objName" name="objName" value="${objName}"/>
    <input type="hidden" id="divName" name="divName" value="${divName}"/>
    <input type="hidden" id="type" name="type"/>
    <input type="hidden" id="maxFileNum" name="maxFileNum" value="${maxFileNum}"/>
    <input id="btnUpLoad" name="btnUpload" type="button" value="업로드" onclick="fn_executeFileUpload()"/>
    </form>
</body>
</html>