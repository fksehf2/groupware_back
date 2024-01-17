<%--
/**
 * <pre>
 * 1. 작성일 : 2021. 4. 14. 오전 10:11:33
 * 2. 작성자 : jmkim
 * 3. 화면명 : 로그인 화면
 * 4. 설명 : 로그인 화면
 * </pre>
 */
--%>

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>Jenix 통합 관리 시스템</title>
<%@ include file="/WEB-INF/views/frame/fcom/jimsCommon.jsp" %>

<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
<meta name="robots" content="noindex, nofollow">

<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<link rel="stylesheet" type="text/css" href="/css/main/common.css" />
<link rel="stylesheet" type="text/css" href="/css/main/main.css" />
<script type="text/javascript" src="/js/frame/common.js" ></script>
<script type="text/javascript">

</script>



<style type="text/css">
.modal {        position: fixed;
                z-index: 999;
                height: 100%;
                width: 100%;
                top: 0;
                left: 0;
                /* background:url('/images/main/bg_pattern.png') repeat 0 0; */
        }
.center {       z-index: 1000;
                margin: auto;
                padding: 10px;
                width: 500px;
                background-color: none ;
                border-radius: 10px;
         }
</style>

<script type="text/javaScript" language="javascript" defer="defer">

var message = "";
var loginId = "";
var pwdErrCnt = 0;
/**
 * 화면 로딩시.
 * @param 
 * @returns 
 */
function jims_onLoad() {
	
	 $(".pwdErr").hide();

	    if (message != "") {
	        if((typeof(loginId) != "undefined")){
	            
	        	if((typeof(loginId) != "undefined") && (loginId != "Admin")){
	                chkPwdErr(loginId);
	            }
	            /* if(pwdErrCnt < 5){
	            	fn_showUserPage(message);
	            } */
	        }else{
	        	fn_showUserPage(message);
	        }
	    }

	    var saveId = gfn_getCookie("saveId");
	    if (saveId != "" && typeof(saveId) != "undefined") {
	        $("#sid").val(saveId);
	        $("#chksave").attr("checked", true);
	    }



}

/**
 * 로그인 처리.
 * @param 
 * @returns 
 */
function fn_actionLogin() {

	 if(!validUtil.checkInputValid({valFormID:'loginForm'})){
	        return;
	  }
	
    if ($("#sid").val() =="")
    {
        //alert('aaa');
    }
    else if ($("#spw").val() =="")
    {
    	
    }
    else
    {
        if ($("#chksave").is(":checked"))
        {
            gfn_setCookie("saveId", $("#sid").val(), 30);
        }
        else
        {
            gfn_setCookie("saveId", "", 0);
        }

        //로그인 전 전역 메시지 변수 초기화
        loginUserId  = "";


        //var callUrl = "<c:url value='/flyt/login/procFLytLoginPDtl.do'/>";
        //requestUtil.search({callUrl:callUrl,srhFormNm:'loginForm',callbackNm:'fn_saveCallbackLogin'});
        
        $.ajax({
            url:  "<c:url value ="/flyt/login/procFLytLoginPDtl.do"/>",
            data: "sid="+$("#sid").val() + "&spw="+CryptoJS.SHA256($("#spw").val()),
            type: "POST",
            dataType: 'json',
            success: function(data) {
            	fn_saveCallbackLogin(data);
            },
            error : function(xhr, status, error){
            	fn_saveCallbackLogin(data);
            }
        }); 
        
    }

}

/**
* 로그인 처리. callback
* @data callback data 
* @returns 
*/
function fn_saveCallbackLogin(data){

	
	//alert(1);
    if(typeof data._server_user_err_message_ != "undefined"){ // 오류 발생

    	message = data.message;
        loginId = data.loginId;
        jims_onLoad();
    } else {

        message = "";
        loginId = "";

        jims_onLoad();
        fn_actionLogin_step2();
    }
}


/**
* 인증 처리를 위해 2 step으로 구성
* 
* @returns 
*/
function fn_actionLogin_step2() {

   	if(loginUserId != "") $("#sid").val(loginUserId);
   	$("#spw").val(CryptoJS.SHA256($("#spw").val()));

    document.loginForm.action="<c:url value='/flyt/login/procFLytLoginPDtl2.do'/>";
    document.loginForm.submit();
}



/**
 * 아이디 찾기
 * @param 
 * @returns 
 */
function fn_findID()
{

    $("#findForm").attr("action","<c:url value='/flyt/login/indexFLytLoginFindIDPDtlPop.do'/>").submit();
}



/**
 * 비밀번호 찾기
 * @param 
 * @returns 
 */
function fn_findPassword()
{

    $("#findForm").attr("action","<c:url value='/flyt/login/indexFLytLoginFindPWPDtlPop.do'/>").submit();
}

function chkPwdErr(userId){
    $.ajax({
        type : "POST",
        url : "<c:url value = "/flyt/login/checkPwdError.do"/>",
        data : "userId="+userId,
        //async: false,
        dataType : "json",
        success : function(response){
            pwdErrCnt = response.pwdErrCnt;
            if(pwdErrCnt >= 5){
            	fn_showUserPage("5회이상 로그인 실패로 인해 로그인 하실 수 없습니다.\n관리자에게 문의하시기 바랍니다.")
            	//비밀번호 초기화 기능 필요 시 아래 링크에서 기능 개발 후 처리
                //document.location.href="<c:url value='/user/idLockClear.do' />";
                $(".pwdErr").show();
                $(".pwdErr span").text(pwdErrCnt+"/5");
            }else{
                $(".pwdErr").show();
                $(".pwdErr span").text(pwdErrCnt+"/5");
            }
        }
    });
}

</script>

<div id="_error_div_">
 <p id="_error_div_para_"></p>
</div>

<div class="modal" style="display: none">
    <div class="center">
        <img style="position:absolute; top:40%; left:40%; margin-top:-50px; margin-left:-50px;"  alt="" src="/images/main/loading_icon.gif" />
    </div>
</div>




</head>
<body class="bg1" onLoad="jims_onLoad();">
<div class="skipnavigation">
    <a href="#lnb">주메뉴 바로가기</a>
    <a href="#contents">본문 바로가기</a>
</div>
<!-- wrap -->
<div id="wrap" class="bg_log">

    <!-- container -->
    <div id="contents" class="login_wrap">
    	<div class="login_wrap_in">
			<div class="log_left">
<!-- 				<img src="/images/main/loginVisual.jpg" /> -->
				<p class="login_txt">Jenix 통합 관리 시스템 방문을 환영합니다.</p>
			</div>
			<div class="log_right">
		        <form id="loginForm" name="loginForm" method="post">
		            <div id="loginFirSet" style="display:block;">
		            <fieldset class="login_box">
		            	<div class="log_logoT">
							<!---<img src="images/mainlogoNew.png" alt="로그인로고">//--> LOGIN
						</div>
		                <legend>로그인</legend>
		                <input type="text" class="txt" name="sid" id="sid" data-requireNm="아이디" onkeydown="if(gfn_enterChk())fn_actionLogin();" title="아이디" placeholder="아이디" maxlength="" data-maxLength="20" />
		                <input type="password" class="txt" id="spw" name="spw" data-requireNm="비밀번호" maxlength="64" data-maxLength="64"  onkeydown="if(gfn_enterChk())fn_actionLogin();" title="비밀번호" placeholder="비밀번호"/>

		                <button type="button" class="login" onclick="fn_actionLogin();return false;">로그인</button>
		                <div class="check_agree1">
		                    <input type="checkbox" name="chksave" id="chksave"><label for="chksave">아이디 저장</label>
		                </div>
		                <div class="check_agree1">
		                    <p class="pwdErr" style="display:none;color:red">아이디 또는 비밀번호를 다시 확인하세요.</br>(입력한도 : <span>0/5</span>)</p>
		                </div> 
		                <div class="link">
		                    <a href="javascript:void(0);" onclick="fn_findID();return false;">아이디 찾기</a>
		                    <a href="javascript:void(0);" onclick="fn_findPassword();return false;">비밀번호 찾기</a>
		                    <!-- 
		                    <a href="javascript:void(0);" onclick="fn_memPolicyAgmtPrc();return false;">회원가입</a>
		                     -->
		                    
		                </div>
		            </fieldset>
		            </div>
		        </form>
			</div>
		</div>
    </div >
    <!-- // container -->
</div>
<!-- // wrap -->
<form id="findForm" name="findForm" method="post">
<input type="hidden" id="passingData" name="passingData" value="">
</form>

<form name="idLockForm" method="post">
    <input type="hidden" name="userId"/>
</form>
</body>
</html>