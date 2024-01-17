<%--
/**
 * <pre>
 * 1. 작성일 : 2021. 4. 14. 오전 10:11:33
 * 2. 작성자 : jmkim
 * 3. 화면명 : 사용자 정보 수정
 * 4. 설명 : 사용자 정보 수정
 * </pre>
 */
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<head>

<!--  requestUtil -->

<script type="text/javaScript" language="javascript" defer="defer">

$(document).ready(function() {
	

	var codeInfo = [{cdId:'C06',selectId:'pwdFindQues',type:'1', callbackNm:'fn_cbCallback'}]
	fn_ajaxCodeList(codeInfo);
	
});



/**
 * 사용자 정보 조회.
 * @param 
 * @returns 
 */
function fn_search() {

	// 조회 
	var callUrl = "<c:url value='/fsys/user/queryFLytLoginUserUpdPDtlPop.do'/>";
	requestUtil.search({callUrl:callUrl,srhFormNm:'userSelfForm'});
	
}



function fn_closeUser(){
	 //fn_UserDialogCallBack();
}

/**
 * 사용자 정보 수정
 * @param 
 * @returns 
 */
function fn_memberMod()
{

	//validation 체크하기 ************************************************

	 if(!validUtil.checkInputValid({valFormID:'userSelfForm'})){
		return;
	} 
	

	fn_showModalPage("저장 하시겠습니까?", function() {
		
		var callUrl = "<c:url value='/fsys/user/updFsysUserUserUpdPDtlPop.do'/>";
		requestUtil.save({callUrl:callUrl,srhFormNm:'userSelfForm',callbackNm:'fn_memberMod_Callback'});
		
	});
		

}


/**
 * 비밀번호 변경.
 * @param 
 * @returns 
 */
function fn_changePw(){
	 
	if(!validUtil.checkInputValid()){
		return;
	}
	
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
   
   var validChkRst = gfn_validitionPw2(modPWD.value, $("#userId").text());
   
   if(validChkRst != "SUCCESS"){
	   fn_showUserPage(validChkRst);
	   return false;
   }
   
   fn_showModalPage("저장 하시겠습니까?", function() {
		
	   var callUrl = "<c:url value='/fsys/user/chgPwFsysUserUserUpdPDtlPop.do'/>";
	   requestUtil.save({callUrl:callUrl,srhFormNm:'userPwdForm',callbackNm:'fn_changePW_Callback'});
		
	});
   
   
}



//에러코드
var PW_Error = 1;
/**
 * 입력 비밀번호 값 일치 여부 확인.
 * @param 
 * @returns 
 */
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
 * 사용자 정보 수정후 calllback.
 * @param data , callback에서 수신한 데이터 
 * @returns 
 */
function fn_memberMod_Callback(data) {
		
	var callUrl = "<c:url value='/fsys/user/queryFLytLoginUserUpdPDtlPop.do'/>";
	requestUtil.search({callUrl:callUrl,srhFormNm:'userSelfForm',callbackNm:'fn_searchMemberCallback'});
	
}

/**
 * 비밀번호 수정 수정후 calllback.
 * @param data , callback에서 수신한 데이터 
 * @returns 
 */

function fn_changePW_Callback(data) {
	
	$("#divUserPopUp").dialog("close");
}


/**
 * 사용자 정보 수정후 main 호면 조회 calllback.
 * @param data , callback에서 수신한 데이터 
 * @returns 
 */
function fn_searchMemberCallback(data) {

	//debugger;
	//console.log('data____[]'+data.resultMap.userNm);
	fn_UserDialogCallBack(data);
	$("#divUserPopUp").dialog("close");
	
}




/**
 * 코드 세팅 callback.
 * @param 
 * @returns 
 */
function fn_cbCallback() {
	
	fn_search();
}



</script>

</head>
<body>

	                     
             <div id="con_wrap_pop">
	<div class="contents">             
<div id="contents_info">
<div class="window_popup">
                   <!--- contnets  적용 ------>
                       <div class="sub_ttl">사용자 정보 수정</div>
<form name="userSelfForm" id="userSelfForm" method="post">
                       <div class="sub">
                          <input type="hidden" id="userId" name="userId" value="<c:out value="${paramMap.userId}" />" />
                 		  <input type="hidden" id="srcCdId" name="srcCdId" value="<c:out value="${paramMap.srcCdId}" />" />
                      <div class="t_list">
		                 <table class="iptTblX">
			               <caption>수정</caption>
			               <colgroup>
				             <col width="20%" />
				             <col width="30%" />
				             <col width="20%" />
				             <col width="30%" />
			               </colgroup>
			               <tbody>
			               		<tr>
			                        <th scope="row">회원구분</th>
			                        <td colspan="3">
										<span id="userGbNm"></span>
			                        </td>
			                    </tr>
			                    <tr>
			                        <th scope="row">사용자명<span class="fontred">*</span></th>
			                        <td>
				                        <input id="userNm" name="userNm" title="사용자명" data-requireNm="사용자명" maxlength="25" data-maxLength="50"  type="text" class="inpw80"/>
			                        </td>
			                        <th scope="row">아이디</th>
			                        <td>
			                            <span id="userId"></span>
										<input id="userGb" name="userGb" type="hidden"/>
			                        </td>
			                    </tr>
			                    <tr>
			                        <th scope="row">email</th>
			                        <td>
				                        <input id="email" name="email" title="email" type="text" maxlength="80" data-maxLength="80"  class="inpw80" />
			                        </td>
			                        <th scope="row">전화번호</th>
			                        <td>
			                            <input id="telNo" name="telNo" title="전화번호" maxlength="20" data-maxLength="20"  type="text"  class="inpw80"/>
			                        </td>
			                    </tr>
			                    			                    
			            </tbody>
		                 </table>
	                  </div>
					
                    <div class="btn_c">
                      <ul>
                        <li><a href="javascript:void(0);" class="RdButton" onclick="fn_memberMod();return false;">저장</a></li>
                      </ul>
                    </div>
                    
                        
                       </div>
 </form>
 
<form name="userPwdForm" id="userPwdForm" method="post">
                  
                        <div class="sub_ttl">비밀번호 변경</div>
                       <div class="sub">
                          <!--------------검색------------------>
					<input type="hidden" id="userId2" name="userId" value="<c:out value="${paramMap.userId}" />" />
                     <input type="hidden" id="srcCdId" name="srcCdId" value="<c:out value="${paramMap.srcCdId}" />" />
                      <div class="t_list">
		                 <table class="iptTblX">
			               <caption>수정</caption>
			               <colgroup>
				             <col width="20%" />
				             <col width="30%" />
				             <col width="20%" />
				             <col width="30%" />
			               </colgroup>
			               <tbody>
			               		<tr>
			                        <th scope="row">현재 비밀번호<span class="fontred">*</span></th>
			                        <td colspan="3">
			                            <input id="chkPwd" name="chkPwd"   title="현재 비밀번호" data-requireNm="현재 비밀번호"  title="현재 비밀번호"  maxlength="30" data-maxLength="30" type="password"  />
			                    </tr>
			                    <tr>
			                        <th scope="row">새 비밀번호<span class="fontred">*</span></th>
			                        <td colspan="3">
			                            <input id="pwd" name="pwd"  data-requireNm="비밀번호"  title="비밀번호"  maxlength="30" data-maxLength="30" type="password" class="inpw40"  />
			                            &nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size:15px;color:#0076b4;">※ 9자 이상 영문 , 숫자, 특수문자를 모두 사용하세요.</span>
			                    </tr>
			                    <tr>
			                        <th scope="row">새 비밀번호 확인<span class="fontred">*</span></th>
			                        <td colspan="3">
			                            <input id="pwdConfirm" name="pwdConfirm"  data-requireNm="새 비밀번호"  title="새 비밀번호"  maxlength="30" data-maxLength="30" type="password" />
			                    </tr>			
			                    <tr>
			                        <th scope="row">아이디/비밀번호 </br> 찾기 질문<span class="fontred">*</span></th>
			                        <td>
			                        <select title="질문"  name="pwdFindQues" id="pwdFindQues" data-requireNm="질문"  class="selw25">
									</select>
			                        </td>
			                        <th scope="row">답변<span class="fontred">*</span></th>
			                        <td>
			                            <input id="pwdFindAsw" name="pwdFindAsw"  title="답변"  data-requireNm="답변"  maxlength="50"  data-maxLength="50" type="text"  class="inpw80"/>
			                        </td>
			                    </tr>
			                    			                    
			            </tbody>
		                 </table>
	                  </div>

                    <div class="btn_c">
                      <ul>
                        <li><a href="#" class="RdButton" onclick="fn_changePw();return false;">저장</a></li>
                        <!-- 
                        <li><a href="#" class="myButton" onclick="fn_closeUser();return false;">취소</a></li>
                         -->
                      </ul>
                    </div>
                    
                    
                       </div>
                      
</form>
                 </div>
                    
</div>

     </div>
                      
                 </div>
</body>
</html>