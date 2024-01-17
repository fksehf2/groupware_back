<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<%--
<%@ include file="/WEB-INF/jsp/spms/com/spmsCommonfordtl.jsp" %>
 --%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">

<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- 
<script type="text/javascript" src="<c:url value='/js/spms/spms.requestUtil.js' />" ></script>
<script type="text/javascript" src="<c:url value='/js/common.js' />" ></script>
 -->
<script type="text/javaScript" language="javascript" defer="defer">
var divId = '${param.divId}';
 
 /**
 * 비밀번호 유효성 검증
 * @author: Kimkh
 * @since : 2018-11-23
 * @param {} 
 */
function fn_changePw(){
	 
	
	 if($("#chkPwd").val() == ""){
		 $("#chkPwd").focus();
		 fn_showUserPage("현재 비밀번호를 입력하시기 바랍니다.");
		return false;
	 }
	 
     fn_comparePW();
	 
    if(PW_Error == 1)
    {   fn_showUserPage("비밀번호를 확인해주세요.");
        return false;   
    }

    //비밀번호 유효성 검사
    var modPWD = document.getElementById("pwd");

    /* switch(gfn_validitionPw(modPWD.value))
    {
        case 'ERROR_001' : fn_showUserPage("비밀번호는 10자리 이상이어야 합니다.");  return false;
        case 'ERROR_002' : fn_showUserPage("비밀번호에 문자가 포함되어야 합니다.");   return false;
        case 'ERROR_003' : fn_showUserPage("비밀번호에 숫자가 포함되어야 합니다.");   return false;
        case 'ERROR_004' : fn_showUserPage("비밀번호에 특수문자가 포함되어야 합니다."); return false;
        case 'ERROR_005' : fn_showUserPage("비밀번호에 같은 문자를 3번 이상 사용하실 수 없습니다.");    return false;
    } */
    
    var validChkRst = gfn_validitionPw2(modPWD.value, session_userid);
	   
    if(validChkRst != "SUCCESS"){
 	   fn_showUserPage(validChkRst);
 	   return false;
    }
    
    fn_showModalPage("변경 하시겠습니까?", function() {
	    var callUrl = "<c:url value='/flyt/login/chgPWFLytLoginOverPWPDtlPop.do'/>";
	
	    requestUtil.save({callUrl:callUrl,srhFormNm:'memberForm',callbackNm:'fn_saveCallback'});
	});
    
}
 
 /**
  * 비밀번호 3개월 후 변경
  * @author: Kimkh
  * @since : 2018-11-23
  * @param {} 
  */
 function fn_changePwLater(){
    
	  
     var callUrl = "<c:url value='/flyt/login/chgThrMonChgFLytLoginOverPWPDtlPop.do'/>";
     requestUtil.save({callUrl:callUrl,srhFormNm:'memberForm',callbackNm:'fn_saveCallbackLater'});
     
 }

//에러코드
var PW_Error = 1;
	
//패스워드 확인
function fn_comparePW()
{
	var pw = $('input[id=pwd]').val();
	var pw2 = $('input[id=pwdConfirm]').val();
	if(pw.length == 0 || pw2.length == 0) return;
	if(pw != pw2)
	{
		$("#comparePw").attr("style","visibility: visible;");
		PW_Error = 1;	
	}
	else
	{
		$("#comparePw").attr("style","visibility: hidden;");
		PW_Error = 0;	
	}
}

/**
 * 닫기버튼
 * @author: Kimkh
 * @param : 
 * @since : 2018-11-23
 * @param {} 
 */
function fn_close(){
	 $('#'+divId).dialog( "close" );
}
 
/**
 * 저장 callback
 * @author: Kimkh
 * @param : 사용자 pw
 * @since : 2018-11-23
 * @param {} 
 */ 
 function fn_saveCallback(data){
    if(typeof data._server_user_err_message_ != "undefined"){
        //alert(data._server_user_err_message_);
    } else {
        msg = "비밀번호가 변경되었습니다.\n재 로그인시 새로 설정한 비밀번호로 로그인하시기 바랍니다.";   
        fn_showUserPage(msg);
        $('#'+divId).dialog( "close" );
    }
}

function fn_saveCallbackLater()
{
	$('#'+divId).dialog( "close" );
}
 
</script>

</head>
<body>
	
<!--콘텐츠 영역-->
	<form commandName="loginVO" name="memberForm" id="memberForm" method="post">
		<div id="popup" class="ui-dialog-content ui-widget-content" style="width: auto; min-height: 0px; max-height: none; height: auto;">
	
			<!-- 상세페이지 -->
	
			<div class="tbl_ty3">
				<table>
					<caption>사용자정보관리123</caption>
					<colgroup>
						<col style="width:150px" />
						<col style="width:*" />
					</colgroup>
					<tbody>
						<tr>
							<th scope="row">현재 비밀번호 *</th>
							<td>
			                 <input id="chkPwd" name="chkPwd"  title="현재 비밀번호"  maxlength="64" data-maxLength="64" type="password"  style='width:200px' />
							</td>
						</tr>
						<tr>
							<th scope="row">비밀번호 *</th>
							<td>
							   <input id="pwd" name="pwd"  title="비밀번호"  maxlength="64" data-maxLength="64" type="password"  style='width:200px' onkeyup="fn_comparePW();"/><br/><br/>
							   <span style="font-size:15px;color:#0076b4;">9자 이상 영문 , 숫자, 특수문자를 모두 사용하세요.</span>
							</td>
						</tr>
	                             <tr>
	                                 <th scope="row">비밀번호 확인 * </th>
	                                 <td>
	                                    <input id="pwdConfirm" name="pwdConfirm"  title="비밀번호"  maxlength="64" data-maxLength="64" type="password"  style='width:200px' onkeyup="fn_comparePW();"/><br/><label id="comparePw" style="visibility: hidden;"><font color="red">비밀번호가 다릅니다.</font></label>
	                                 </td>
	                             </tr>
					</tbody>
				</table>
			</div>
	
			<div class="btn_center line">
			<c:choose>
          		<c:when test="${param.pwdClr eq 'CLEAR'}">
					<button type="submit" class="btns btn_ty3" onclick="fn_changePw();return false;">변경하기</button>
					<button type="button" class="btns btn_ty2" onclick="fn_close();return false;">다음에 변경</button>
					<button type="button" class="btns btn_ty2" onclick="fn_changePwLater();return false;">3개월 후 변경</button>
				</c:when>
				<c:when test="${param.pwdClr eq 'NOCLEAR'}">
				
					<button type="submit" class="btns btn_ty3" onclick="fn_changePw();return false;">변경하기</button>
				</c:when>
			</c:choose>
			</div>
	
			<!-- //상세페이지 -->
	
		</div>
				
</form>


</body>
</html>