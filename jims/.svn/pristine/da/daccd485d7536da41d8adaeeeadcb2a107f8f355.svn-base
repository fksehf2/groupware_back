
<%--
/**
 * <pre>
 * 1. 작성일 : 2021. 4. 14. 오전 10:11:33
 * 2. 작성자 : jmkim
 * 3. 화면명 : 아이디 찾기 화면
 * 4. 설명 : 아이디 찾기 화면
 * </pre>
 */
--%>

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>


<%@ include file="/WEB-INF/views/frame/fcom/jimsCommon.jsp"%>

<link rel="stylesheet" type="text/css" href="<c:url value="/css/main/jims_common.css" />" />


<link rel="stylesheet" type="text/css" href="<c:url value="/css/jims/findidpw.css" />" /> 



<script type="text/javaScript" language="javascript" defer="defer">

/**
 * 화면 로딩시.
 * @param 
 * @returns 
 */
function page_onload() {

	$('#popup1').hide();
	$('#popup2').hide();

}

/**
 * 찾기
 * @param 
 * @returns 
 */
function fn_findIdInfo() {

	
	if(!validUtil.checkInputValid()){
		return;
	}
	
	var data = $("#findForm").serialize();

	$.ajax({
		type : "POST",
		url : "<c:url value = "/flyt/login/findIDFLytLoginFindIDPDtlPop.do"/>",
		data : data,
		async : false,
		dataType : "json",
		success : function(data) {
			if (data.findIdInfo != undefined && data.findIdInfo.length > 0) {
				var userNm = $('#userNm').val();
				var userId = data.findIdInfo[0].userId;
				$('#popup1 > p').html(
						userNm + '님의 아이디는 <span class="bold">' + userId
								+ '</span>입니다.');
				fn_showMessagePopup('popup1');
			} else {
				
				if(data.userYn == 'Y'){
					fn_showMessagePopup('popup3'); //입력하신정보로 동일한 사용자가 존재합니다		
				}else{
					fn_showMessagePopup('popup2'); //입력하신정보로 사용자정보가 존재하지 않습니다						
				}
				
			}
		}
	});
}



/**
 * 에러 메시지 팝업
 * @param 
 * @returns 
 */
function fn_showMessagePopup(div) {

	$('#' + div).dialog({
		modal : true,
		buttons : {},
		oepn : function(event, ui) {
		},
		height : 270,
		width : 500

	});
}

/**
 * 닫기. 로그인 화면으로 이동
 * @param 
 * @returns 
 */
function fn_goMain() {
	document.actionForm.action = "<c:url value='/flyt/login/indexFLytLoginIDtl.do'/>";
	document.actionForm.submit();
}


/**
 * 입력 화면 trim 처리.
 * @param 
 * @returns 
 */
function fn_trim(obj) {
	$(obj).val($(obj).val().replace(/\s/g, ""));
}
	

</script>

</head>
<body onLoad="page_onload();">
	<div class="skipnavigation">
		<a href="#lnb">주메뉴 바로가기</a> <a href="#contents">본문 바로가기</a>
	</div>
	<!-- wrap -->
	<div id="wrap">
		<hr />
		<!-- container -->
		<div id="container_03">
			<!-- contents -->
			<article id="contents" class="contents" role="main">
				<form id="findForm" name="findForm" method="post">
					<div class="log_box">
						<h2 class="log_tit">아이디 찾기</h2>

						<p class="log_tit_sub mt20">찾으실 아이디의 사용자 정보를 입력하세요.</p>

						<h4 class="log_tit_02 mt40">사용자명</h4>
						<input class="mt10 mb10" type="text" title="사용자명" id="userNm" data-requireNm="사용자명" 
							name="userNm" style="width: 100%;" maxlength="50" data-maxLength="50" onkeyup="fn_trim(this);">
						<h4 class="log_tit_02 mt40">질문</h4>
						<select class="mt10 mb10" title="질문" style="width: 100%;" name="pwdFindQues" id="pwdFindQues">
							<c:forEach items="${pwdFindQuesCodeList}"
								var="pwdFindQuesCodeInfo" varStatus="status">
								<option value="<c:out value="${pwdFindQuesCodeInfo.cd}"/>">${pwdFindQuesCodeInfo.cdNm}</option>
							</c:forEach>
						</select>
						<h4 class="log_tit_02 mt40">답변</h4>
						<input class="mt10 mb10" type="text" title="답변" id="pwdFindAsw" data-requireNm="비밀번호 찾기 답변" 
							name="pwdFindAsw" style="width: 100%;" maxlength="100" data-maxLength="100" onkeyup="fn_trim(this);">

						<div class="btn_center">
							<button type="button" class="btns btn_ty1"
								onclick="javascript:fn_findIdInfo();">찾기</button>
								<button type="button" class="btns btn_ty1"
								onclick="javascript:fn_goMain();">닫기</button>
						</div>
					</div>
				</form>

				<!-- 아이디 일치(S) -->
				<div tabindex="-1" role="dialog"
					style="position: absolute; height: auto; width: 500px; top: 100px; left: 417.5px; display: none; z-index: 101;"
					class="ui-dialog ui-corner-all ui-widget ui-widget-content ui-front ui-draggable ui-resizable"
					aria-describedby="popup" aria-labelledby="ui-id-1">
					<div id="popup1" class="ui-dialog-content ui-widget-content"
						style="width: auto; min-height: 0px; max-height: none; height: auto;">

						<!-- 상세페이지 -->
						<p class="txt_main"></p>

						<div class="btn_center">
							<button type="button" class="btns btn_ty2"
								onclick="javascript:fn_goMain();">로그인</button>
							<button type="button" class="btns"
								onclick="javascript:$('#popup1').dialog('close');">취소</button>
						</div>

						<!-- //상세페이지 -->

					</div>
					<div class="ui-resizable-handle ui-resizable-n"
						style="z-index: 90;"></div>
					<div class="ui-resizable-handle ui-resizable-e"
						style="z-index: 90;"></div>
					<div class="ui-resizable-handle ui-resizable-s"
						style="z-index: 90;"></div>
					<div class="ui-resizable-handle ui-resizable-w"
						style="z-index: 90;"></div>
					<div
						class="ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se"
						style="z-index: 90;"></div>
					<div class="ui-resizable-handle ui-resizable-sw"
						style="z-index: 90;"></div>
					<div class="ui-resizable-handle ui-resizable-ne"
						style="z-index: 90;"></div>
					<div class="ui-resizable-handle ui-resizable-nw"
						style="z-index: 90;"></div>
				</div>
				<!-- 아이디 일치(E) -->

				<!-- 아이디 일치(S) -->
				<div tabindex="-1" role="dialog"
					style="position: absolute; height: auto; width: 500px; top: 150px; left: 430px; display: none; z-index: 101;"
					class="ui-dialog ui-corner-all ui-widget ui-widget-content ui-front ui-draggable ui-resizable"
					aria-describedby="popup" aria-labelledby="ui-id-1">
					<div id="popup2" class="ui-dialog-content ui-widget-content"
						style="width: auto; min-height: 0px; max-height: none; height: auto;">

						<!-- 상세페이지 -->
						<p class="txt_main">
							입력하신정보로 사용자정보가 존재하지 않습니다.<br /> 정보를 확인하시고 다시 조회하시기 바랍니다.
						</p>

						<div class="btn_center">
							<button type="button" class="btns btn_ty9"
								onclick="javascript:$('#popup2').dialog('close');">닫기</button>
						</div>

						<!-- //상세페이지 -->

					</div>
					<div class="ui-resizable-handle ui-resizable-n"
						style="z-index: 90;"></div>
					<div class="ui-resizable-handle ui-resizable-e"
						style="z-index: 90;"></div>
					<div class="ui-resizable-handle ui-resizable-s"
						style="z-index: 90;"></div>
					<div class="ui-resizable-handle ui-resizable-w"
						style="z-index: 90;"></div>
					<div
						class="ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se"
						style="z-index: 90;"></div>
					<div class="ui-resizable-handle ui-resizable-sw"
						style="z-index: 90;"></div>
					<div class="ui-resizable-handle ui-resizable-ne"
						style="z-index: 90;"></div>
					<div class="ui-resizable-handle ui-resizable-nw"
						style="z-index: 90;"></div>
				</div>
				<!-- 아이디 일치(E) -->
				
				<!-- 아이디 일치(S) -->
				<div tabindex="-1" role="dialog"
					style="position: absolute; height: auto; width: 500px; top: 150px; left: 430px; display: none; z-index: 101;"
					class="ui-dialog ui-corner-all ui-widget ui-widget-content ui-front ui-draggable ui-resizable"
					aria-describedby="popup" aria-labelledby="ui-id-1">
					<div id="popup3" class="ui-dialog-content ui-widget-content"
						style="width: auto; min-height: 0px; max-height: none; height: auto;">

						<!-- 상세페이지 -->
						<p class="txt_main">
							입력하신정보로 동일한 사용자가 존재합니다.<br /> 관리자에게 문의하시기 바랍니다.
						</p>

						<div class="btn_center">
							<button type="button" class="btns btn_ty9"
								onclick="javascript:$('#popup3').dialog('close');">닫기</button>
						</div>

						<!-- //상세페이지 -->

					</div>
					<div class="ui-resizable-handle ui-resizable-n"
						style="z-index: 90;"></div>
					<div class="ui-resizable-handle ui-resizable-e"
						style="z-index: 90;"></div>
					<div class="ui-resizable-handle ui-resizable-s"
						style="z-index: 90;"></div>
					<div class="ui-resizable-handle ui-resizable-w"
						style="z-index: 90;"></div>
					<div
						class="ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se"
						style="z-index: 90;"></div>
					<div class="ui-resizable-handle ui-resizable-sw"
						style="z-index: 90;"></div>
					<div class="ui-resizable-handle ui-resizable-ne"
						style="z-index: 90;"></div>
					<div class="ui-resizable-handle ui-resizable-nw"
						style="z-index: 90;"></div>
				</div>
				<!-- 아이디 일치(E) -->

			</article>
			
			<!-- // contents -->
		</div>
		<!-- // container -->
	</div>
	<!-- <div id='popup'></div> -->
	<form name="actionForm" method="post"></form>
	<!-- // wrap -->
</body>
</html>
