<%--
/**
 * <pre>
 * 1. 작성일 : 2021. 4. 14. 오전 10:11:33
 * 2. 작성자 : jmkim
 * 3. 화면명 : 비밀번호  찾기 화면
 * 4. 설명 : 로그인 > 비밀번호 찾기 화면
 * </pre>
 */
--%>

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>


<%@ include file="/WEB-INF/views/frame/fcom/jimsCommon.jsp" %>
<!-- TODO 임시 -->
<link rel="stylesheet" type="text/css" href="<c:url value="/css/main/jims_common.css" />" />


<!--
 /> //-->

<link rel="stylesheet" type="text/css" href="<c:url value="/css/jims/findidpw.css" />" />

<script type="text/javaScript" language="javascript" defer="defer">
var pwChk = false;  
var confirmPwChk = false;

/**
 * 화면 로딩시.
 * @param 
 * @returns 
 */
function page_onload() {
	$(".pwd").hide();
	$(".chkPwd").hide();
  	$('#popup1').hide();
  	$('#popup2').hide();  	
}


/**
 * 찾기
 * @param 
 * @returns 
 */
function fn_findPwInfo(){
	
	if(!validUtil.checkInputValid()){
		return;
	}
  	var data = $("#findForm").serialize();

	  $.ajax({
	        type : "POST",
	        url : "<c:url value = "/flyt/login/findPWFLytLoginFindPWPDtlPop.do"/>",
	        data : data,
	        async: false,
	        dataType : "json",
	        success : function(data){
	        	if(data.cnt > 0){       		
	        		fn_showMessagePopup('popup1');        		
	        	} else {        		
	                fn_showMessagePopup('popup2');
	        	}
	        }
	    });
}


/**
* 로그인 잠금 해제
* @param 
* @returns 
*/
function fn_findPwClear(){
	

	if(!validUtil.checkInputValid()){
		return;
	}
  	var data = $("#findForm").serialize();

	  $.ajax({
	        type : "POST",
	        url : "<c:url value = "/flyt/login/lckCncFLytLoginFindPWPDtlPop.do"/>",
	        data : data,
	        async: false,
	        dataType : "json",
	        success : function(data){
	        	if(data.message == 'success'){
	        		
	        		fn_showInfoPage("비밀번호 횟수 초기화 성공", function() {
	        			
		        		document.actionForm.action="<c:url value='/flyt/login/indexFLytLoginIDtl.do'/>";
		        	    document.actionForm.submit();
	        			
	        		});
	        		
	        		
	        	} else {        		
	                fn_showMessagePopup('popup2');
	        	}
	        }
	    });
}

/**
* 비밀번호 변경
* @param 
* @returns 
* 
* Div로 있기때문에 공통 validation을 쓰면 같이 체크한다. 
* 따로 validation 체크
*/
function fn_pwChange(){
	
	if($('#pwd').val() == ''){
		fn_showUserPage('비밀번호를 입력해주세요.');
		$('#pwd').focus();
		return false;
	}
	
	if($('#chkPwd').val() == ''){
		fn_showUserPage('비밀번호 확인을 입력해주세요.');
		$('#chkPwd').focus();
		return false;
	} 
	
	if(!validUtil.checkInputValid()){
		return;
	}
	
	if(!pwChk){
		fn_showUserPage('비밀번호 형식에 맞지 않습니다.');
		$('#pwd').focus();
		return false;
	}
	
	if(!confirmPwChk){
		fn_showUserPage('비밀번호가 일치하지 않습니다.');
		$('#chkPwd').focus();
		return false;
	}
	
	var id = $('#userId').val();
	//var pwd = $('#pwd').val();
	var pwd = CryptoJS.SHA256($("#pwd").val());	
	$("#changeForm").append('<input type="hidden" name="uId" id="uId" value="'+id+'">');
	$("#changeForm").append('<input type="hidden" name="pwd" id="pwd" value="'+pwd+'">');

	var data = $("#changeForm").serialize();
	
	
	$("#changeForm").remove('#uId');
	$("#changeForm").remove('#pwd');
	

	$.ajax({
	        type : "POST",
	        url : "<c:url value = "/flyt/login/chgPWFLytLoginFindPWPDtlPop.do"/>",
	        data : data,
	        async: false,
	        dataType : "json",
	        success : function(data){
	        	if(data.message == 'success'){
	        		
	        		fn_showInfoPage("비밀번호가 변경되었습니다", function() {
	        			
		        		document.actionForm.action="<c:url value='/flyt/login/indexFLytLoginIDtl.do'/>";
		        	    document.actionForm.submit();
	        			
	        		});
	        		
	        	}
	        		
	        }
	    });
	
}




/**
 * 메시지 팝업 
 * @param 
 * @returns 
 */
function fn_showMessagePopup(div) {
	
	var height = (div == 'popup1') ? 450 : 270;
	
	$('#'+div)
    .dialog({
       modal: true,
       buttons: {
         },
       open: function (event,ui) {
       },
       height: height,
       width: 500
       
   });
}


/**
 * 닫기 로그인 페이지로 이동 
 * @param 
 * @returns 
 */
function fn_goMain(){	
	document.actionForm.action="<c:url value='/flyt/login/indexFLytLoginIDtl.do'/>";
    document.actionForm.submit();
}


/**
 * trim util 
 * @param 
 * @returns 
 */
function fn_trim(obj) {
	$(obj).val($(obj).val().replace(/\s/g,""));
}


/**
 * 비밀번호 검증
 * @param 
 * @returns 
 */
function fn_confirmPwd(){
	
	var pwd = $('#pwd').val();
	
	/* switch (gfn_validitionPw(pwd)) {
		case 'ERROR_001':		
		case 'ERROR_002':		
		case 'ERROR_003':		
		case 'ERROR_004':		
		case 'ERROR_005':
			pwChk = false;
			$('span.pwd').show();
			break;
		case 'SUCCESS':
			pwChk = true;
			$('span.pwd').hide();
			break;
	} */
	var validChkRst = gfn_validitionPw2(pwd, $("#userId").val());
	   
	   if(validChkRst != "SUCCESS"){
		   pwChk = false;
		   $('span.pwd').text(validChkRst);
		   $('span.pwd').show();
	   }else{
		   pwChk = true;
		   $('span.pwd').text("");
		   $('span.pwd').hide();
	   }
	
}


/**
 * 비밀번호 확인
 * @param 
 * @returns 
 */
function fn_confirmChkPwd(){
	var pwd = $('#pwd').val();
	var chkPwd = $('#chkPwd').val();
	
	if(pwd != chkPwd){
		confirmPwChk = false;
		$('span.chkPwd').show();
	}else{
		confirmPwChk = true;
	}
}
</script>

</head>
<body onLoad="page_onload();">
<div class="skipnavigation">
    <a href="#lnb">주메뉴 바로가기</a>
    <a href="#contents">본문 바로가기</a>
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
				<h2 class="log_tit">비밀번호 찾기</h2>

				<p class="log_tit_sub mt20">찾으실 비밀번호의 사용자 정보를 입력하세요.</p>

				<h4 class="log_tit_02 mt40">사용자ID</h4>
				<input class="mt10 mb10" type="text" title="사용자ID" id="userId" data-requireNm="사용자ID"  name="userId" maxlength="20" data-maxLength="20" style="width:100%;" onkeyup="fn_trim(this);">

				<h4 class="log_tit_02 mt40">사용자명</h4>
				<input class="mt10 mb10" type="text" title="사용자명" id="userNm" data-requireNm="사용자명" name="userNm" maxlength="50" data-maxLength="50" style="width:100%;" onkeyup="fn_trim(this);">

				<h4 class="log_tit_02 mt40">질문</h4>
				<select class="mt10 mb10" title="질문" style="width:100%;" name="pwdFindQues" id="pwdFindQues">
		        <c:forEach items="${pwdFindQuesCodeList}" var="pwdFindQuesCodeInfo" varStatus="status">
		          <option value="<c:out value="${pwdFindQuesCodeInfo.cd}"/>">${pwdFindQuesCodeInfo.cdNm}</option>
		        </c:forEach>
		        </select>

				<h4 class="log_tit_02 mt40">답변</h4>
				<input class="mt10 mb10" type="text" title="답변" id="pwdFindAsw" name="pwdFindAsw" data-requireNm="비밀번호 찾기 답변" maxlength="100" data-maxLength="100" style="width:100%;" onkeyup="fn_trim(this);">

				<div class="btn_center">
					<button type="button" class="btns btn_ty1" onclick="javascript:fn_findPwInfo();">찾기</button>
					
					<!-- 현재 에러 횟수를 증가시키고 있지 않기 때문에 불필요 kjm 2021.04.21
					<button type="button" class="btns btn_ty3" onclick="javascript:fn_findPwClear();">로그인 잠금 해제</button>
					 -->
					 
					<button type="button" class="btns btn_ty1" onclick="javascript:fn_goMain();">닫기</button>
				</div>
			</div>

<!-- 사용자 인증정보 일치(S) -->
			<div tabindex="-1" role="dialog" style="position: absolute; height: auto; width: 500px; top: 100px; left: 417.5px; display: block; z-index: 101;" class="ui-dialog ui-corner-all ui-widget ui-widget-content ui-front ui-draggable ui-resizable" aria-describedby="popup" aria-labelledby="ui-id-1">
				<div id="popup1" class="ui-dialog-content ui-widget-content" style="display:none; width: auto; min-height: 0px; max-height: none; height: auto;">
					<!-- 상세페이지 -->

				<p class="txt_main">변경할 비밀번호를 입력하세요.</p>

				<h4 class="log_tit_02 mt20">비밀번호</h4>
				<input class="mt10" type="password" title="비밀번호"  name="pwd" id="pwd" maxlength="64" data-maxLength="64" style="width:100%;" onkeyup="fn_trim(this);" onblur="fn_confirmPwd();" onkeydown='$("span.pwd").hide()'>
				<span class="pwd" style="color:red">10자리 이상 영문, 숫자, 특수문자를 사용하세요.</span>
				<br/>
				<br/>
				
				<h4 class="log_tit_02 mt20">비밀번호 확인</h4>
				<input class="mt10" type="password" title="비밀번호확인"  name="chkPwd" id="chkPwd" maxlength="64" data-maxLength="64" style="width:100%;" onkeyup="fn_trim(this);" onblur="fn_confirmChkPwd();" onkeydown='$("span.chkPwd").hide()'>
				<span class="chkPwd" style="color:red">비밀번호가 일치하지 않습니다.</span>

				<br/>
				<br/>
				
				<div class="btn_center">
					<button type="button" class="btns btn_ty6" onclick="javascript:fn_pwChange();">비밀번호 변경</button>
				</div>

					<!-- //상세페이지 -->
				</div>
				<div class="ui-resizable-handle ui-resizable-n" style="z-index: 90;"></div>
				<div class="ui-resizable-handle ui-resizable-e" style="z-index: 90;"></div>
				<div class="ui-resizable-handle ui-resizable-s" style="z-index: 90;"></div>
				<div class="ui-resizable-handle ui-resizable-w" style="z-index: 90;"></div>
				<div class="ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se" style="z-index: 90;"></div>
				<div class="ui-resizable-handle ui-resizable-sw" style="z-index: 90;"></div>
				<div class="ui-resizable-handle ui-resizable-ne" style="z-index: 90;"></div>
				<div class="ui-resizable-handle ui-resizable-nw" style="z-index: 90;"></div>
			</div>
<!-- 사용자 인증정보 일치(E) -->

<!-- 사용자 인증정보 불일치(S) -->
				<div tabindex="-1" role="dialog" style="position: absolute; height: auto; width: 500px; top: 150px; left: 430px; display: none; z-index: 101;" class="ui-dialog ui-corner-all ui-widget ui-widget-content ui-front ui-draggable ui-resizable" aria-describedby="popup" aria-labelledby="ui-id-1">
					<div id="popup2" class="ui-dialog-content ui-widget-content" style="display:none;width: auto; min-height: 0px; max-height: none; height: auto;">

						<!-- 상세페이지 -->
						<p class="txt_main">입력하신정보로 사용자정보가 존재하지 않습니다.<br/>
						정보를 확인하시고 다시 조회하시기 바랍니다.</p>

						<div class="btn_center">
							<button type="button" class="btns btn_ty9" onclick="$('#popup2').dialog('close');">닫기</button>
						</div>

						<!-- //상세페이지 -->

					</div>
					<div class="ui-resizable-handle ui-resizable-n" style="z-index: 90;"></div>
					<div class="ui-resizable-handle ui-resizable-e" style="z-index: 90;"></div>
					<div class="ui-resizable-handle ui-resizable-s" style="z-index: 90;"></div>
					<div class="ui-resizable-handle ui-resizable-w" style="z-index: 90;"></div>
					<div class="ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se" style="z-index: 90;"></div>
					<div class="ui-resizable-handle ui-resizable-sw" style="z-index: 90;"></div>
					<div class="ui-resizable-handle ui-resizable-ne" style="z-index: 90;"></div>
					<div class="ui-resizable-handle ui-resizable-nw" style="z-index: 90;"></div>
				</div>
<!-- 사용자 인증정보 불일치(E) -->

			</form>
		</article>
    <!-- // contents -->
  </div>
    <!-- // container -->
</div>
<form name="changeForm" id="changeForm" method="post"></form>
<form name="actionForm" id="actionForm" method="post"></form>
<!-- // wrap -->
</body>
</html>
