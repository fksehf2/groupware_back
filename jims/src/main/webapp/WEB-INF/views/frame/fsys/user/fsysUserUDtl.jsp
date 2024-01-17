<%--
/**
 * <pre>
 * 1. 작성일 : 2021. 4. 14. 오전 10:49:11
 * 2. 작성자 : ilyong
 * 3. 화면명 : 사용자관리 > 사용자 수정
 * 4. 설명 : 화면명과 동일하거나 기타 특이사항 기술
 * </pre>
 */
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>

<html>
<head>
<%@ include file="/WEB-INF/views/frame/fcom/jimsCommon.jsp" %>


<script type="text/javaScript" language="javascript" defer="defer">
var PW_Error = 0;
var ID_Duple = 1;
var tabId;

$(document).ready(function() {
	tabId = parent.$('li[id*=li_tabs-M][aria-expanded=true]').attr('aria-controls');

	var codeInfo2 = [{cdId:'C06',selectId:'pwdFindQues',type:'1', callbackNm:'fn_ajaxPwdCallback', sqlQueryId:''}];
	fn_ajaxCodeList(codeInfo2);
	
	
});

/**
 * 공통 코드 콜백함수 
 * @param {string} prgID
 * @returns 
 */
function fn_ajaxPwdCallback(data){
  	$('#pwdFindQues option:eq(0)').before("<option value='' selected>선택</option>");

  	requestUtil.search({callUrl:"<c:url value='/fsys/user/queryFsysUserUDtl.do'/>", srhFormNm:'insForm', callbackNm:'fn_callback'});

}
 
function fn_callback(data){
// 	$("#topMenuNo").val(data.topMenuNo);
// 	if(data.sysGrpList.length > 0){
// 		$("#menuLvl").append('<option value="lvl2">Level2</option>');
		
// 		$.each(data.sysGrpList, function(idx, row) {
// 	    	$("#upperMenuNo").append("<option value='"+row.sysGrp+"'>"+row.sysGrpNm+"</option>");
// 	    });
// 	}
	
}

function fn_prgdetail() {

	var callUrl = "<c:url value = "/com/PageLink.do"/>"

	requestUtil.mdPop({
		popUrl : callUrl+"?link="+"frame/fsys/menu/fsysMenuSchPrgmPop",
		height: 700,
        width: 1000,
        title: '프로그램 목록',
        divId : 'divPrgPopup'
	});

}

//정보 수정
function fn_updFsysUserUDtl(){

	var userNm = $("#userNm").val();
	var email = $("#email").val();
	var regStatus = $("#regStatus").val();
	var telNo = $("#telNo").val();
	var hpTelNo = $("#hpTelNo").val();
	var pwdFindQues = $("#pwdFindQues").val();
	var pwdFindAsw = $("#pwdFindAsw").val();
	var useYn = $("#useYn").val();
	

	if(userNm.length < 1){
		fn_showUserPage( "이름을 입력하세요.", function() { //regStatus
			$("#userNm").focus();
        });
		return;
	}else if(telNo.length < 1){
		fn_showUserPage( "대표전화번호를 입력하세요.", function() {
			$("#telNo").focus();
        });
		return;
	}else if(hpTelNo.length < 1){
		fn_showUserPage( "핸드폰번호를 입력하세요.", function() {
			$("#hpTelNo").focus();
        });
		return;
	}else if(pwdFindQues.length < 1){
		fn_showUserPage( "아이디/비밀번호 찾기 질문을 선택하세요.", function() {
			$("#pwdFindQues").focus();
        });
		return;
	}else if(pwdFindAsw.length < 1){
		fn_showUserPage( "답변을 입력하세요.", function() {
			$("#pwdFindAsw").focus();
        });
		return;
	}else if(useYn.length < 1){
		fn_showUserPage( "사용여부를 선택하세요.", function() {
			$("#useYn").focus();
        });
		return;
	}
	

	//Step2. 정규식 체크 
	if(email.length > 0){
		if(!emailCheck(email)){	
			fn_showUserPage( "이메일이 잘못되었습니다.", function() {
				$("#email").focus();
	        });
			return;	
		}	
	}
	
	if(telNo.length > 4  || hpTelNo.length > 0){
		if(telNo.length > 4 && !gfn_validitionTelNo(telNo)){
			fn_showUserPage( "정확한 대표전화번호를 입력하세요.", function() {
				$("#telNo").focus();
	        });
			return;	
		}else if(!gfn_validitionTelNo(hpTelNo)){
			fn_showUserPage( "정확한 핸드폰번호를 입력하세요.", function() {
				$("#hpTelNo").focus();
	        });
			return;	
		}	
	}		
	
// 	switch(gfn_validitionPw(pwd))
// 	{
// 		case 'ERROR_001' : alert("비밀번호는 10자리 이상이어야 합니다.");	return;
// 		case 'ERROR_002' : alert("비밀번호에 문자가 포함되어야 합니다.");	return;
// 		case 'ERROR_003' : alert("비밀번호에 숫자가 포함되어야 합니다.");	return;
// 		case 'ERROR_004' : alert("비밀번호에 특수문자가 포함되어야 합니다.");	return;
// 		case 'ERROR_005' : alert("비밀번호에 같은 문자를 3번 이상 사용하실 수 없습니다."); return;
// 	}		

	fn_showModalPage("저장 하시겠습니까?", function() {
		//사용자 정보 수정
        var callUrl = "<c:url value='/fsys/user/updFsysUserUDtl.do'/>";
        requestUtil.save({callUrl:callUrl,srhFormNm:'insForm',callbackNm:'fn_updFsysUserUDtlCallback'});
	});
}

/**
 * @!@ 사용자 관리 삭제
 * @param
 * @returns 
 */
function fn_delFsysUserUDtl() {
	
	fn_showModalPage("삭제 하시겠습니까?", function() {	
		//사용자 정보 수정
        var callUrl = "<c:url value='/fsys/menu/delFsysUserUDtl.do'/>";
        requestUtil.save({callUrl:callUrl,srhFormNm:'insForm',callbackNm:'fn_delFsysUserUDtlCallback'});
	
	});
	
}

/**
 * @!@ 메뉴 관리 삭제 콜백
 * @param
 * @returns 
 */
function fn_delFsysUserUDtlCallback(data){
	fn_indexFsysUserMList();
}

//이메일 체크
function emailCheck(email) {
    var regExp = /[0-9a-zA-Z][_0-9a-zA-Z-]*@[_0-9a-zA-Z-]+(\.[_0-9a-zA-Z-]+){1,2}$/;
    if ( !email.match(regExp) ) {	return false;    } else {		return true;    }
}

//비밀번호 비교
function fn_saveComparePW(){
	var pw = $('input[id=pwd]').val();
	var pw2 = $('input[id=pwdConfirm]').val();
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

function fn_updFsysUserUDtlCallback(data){
	if(data.result == "fail"){
		
	}else{
		fn_indexFsysUserMList();
	}
}
// /fsys/user/indexFsysUserMList.do

function fn_indexFsysUserMList(){
	parent.$('#tabs-M000000401').find("iframe").attr("src", '<c:url value="/fsys/user/indexFsysUserMList.do"/>');
}


<%/*아이디 중복 체크*/%>
function fn_IDcheck(){
	
	var checkParamId = $("#userId").val(); 
// 	alert(checkParamId.search(/\s/));
	
	if( checkParamId.search(/\s/) > 0 ){
		alert('ID에 공백이 들어갈 수 없습니다.');
		$("#userId").focus();
		return;
	} 
	
	if(checkParamId == ""){
		alert("아이디를 입력해 주세요.");
		$("#userId").focus();
		return;
	}else{
		var callUrl = "<c:url value='/fsys/user/sysUserCheckId.do'/>";
		requestUtil.save({callUrl:callUrl,srhFormNm:'insForm',setFormNm:'insForm',callbackNm:'fn_callbackpop'});
	}
	
}

function fn_callbackpop(data) {
	
	var resultCnt = data.egovMap.resultCnt;
// 	alert("fn_callbackpop 진입 resultCnt===>>> "+resultCnt);
// 	if(resultCnt > 0){
// 		alert("111111");
// 	}else{
// 		alert("2222222");
// 	}
// 	var existsCnt = resultCnt.substring(0,resultCnt.indexOf('-'));
// 	alert("fn_callbackpop 진입 1-1");
	if( resultCnt > 0 ) {
		alert('이미 존재하는 ID입니다.');
		$('#userId').val('');
		ID_Duple=1;
		return;
	} else {
		alert('사용가능합니다.');
		ID_Duple =0;
		return;
	}
	
}

//비밀번호 초기화 : 초기화시 아이디를 넣어준다.
function fn_pwInit(setPwd){

	//alert($("#puserId").val());
	$("#pwd").val('1');
	$("#pwAlert").text("※ 초기화 설정되었습니다. 정보수정을 하셔야 최종적으로 적용됩니다.");
	
}



</script>

</head>

<body>
<div id="con_wrap">
        <div class="content">
           <!----현재위치----->
             
            <div id="contents_info">
                 <div class="sub_ttl">사용자 정보 수정</div><!-----타이틀------>
                 
                  <div class="sub">
                     <!------------검색------------------->
                     <form name="insForm" id="insForm" method="post">
                     
                     <input type="hidden" id="userId" name="userId" value="<c:out value="${param.userId}" />" />
                     <input type="hidden" id="pwd" name="pwd"  value=""/>
                     <input type="hidden" id="insttCd" name="insttCd"  value=""/>
					
					 
                      <div class="t_list">
		                 <table class="iptTblX">
			               <caption>수정</caption>
			               <colgroup>
				             <col width="15%" />
				             <col width="35%" />
				             <col width="15%" />
				             <col width="*" />
			               </colgroup>
			               <tbody>
			                 <tr>
				                 <th scope="row">사용자구분</th>
				                 <td colspan="3">
				                 	<span id="userGbNm"> </span>
<!-- 						            <input id="userGb" name="userGb" type="text" value=""/> -->
				                 </td>
			                 </tr>
			                 <tr>
								<th scope="row">아이디</th>
								<td >
									<span id="userId"> </span>
								</td>
								<th scope="row">부서</th>
				                <td >
				                	<span id="insttCdNm"> </span>
				                	
				                </td>
							</tr>
			                 <tr>
				                  <th scope="row">비밀번호</th>
				                  <td colspan="3">
<!-- 			                  		<input type="button"  class="btns" value="초기화" id="input" onclick="fn_pwInit();"/> -->
			                  		<a href="#" class="buttonG80" onclick="fn_pwInit();return false;">초기화</a>
			                  		<span id="pwAlert" class="col_green txt_small">※ 비밀번호 초기화시 비밀번호는 1로 설정됩니다.</span> 
				                  </td>
			                 </tr>
			                 <tr>
				                 <th scope="row">이름<span class="fontred">*</span></th>
				                 <td colspan="3">
				                 	<input id="userNm" name="userNm" type="text" value=""  maxlength="25" data-requireNm="사용자명" data-maxLength="50" title="사용자명" class="inpw20"/>
				                 </td>
				                 
			                 </tr>
			                 <tr>
			                   <th scope="row">이메일</th>
			                   <td colspan="3">
			                   		<input id="email" name="email" type="text" value=""  maxlength="50" class="inpw20"/>
				               </td>
<!-- 				               <th scope="row">가입상태<span class="fontred">*</span></th> -->
<!-- 			                   <td > -->
<!-- 			                   		<select class="selw10" id="regStatus" name="regStatus" onchange="" data-requireNm="가입상태" data-maxLength="1" title="가입상태" > -->
<!-- 			                   			<option value="Y">선택</option> -->
<!-- 			                   			<option value="Y">승인</option> -->
<!-- 			                   			<option value="N">미승인</option> -->
<!-- 			                   		</select> -->
<!-- 				               </td> -->
			                 </tr>
			                 <tr>
			                   <th scope="row">대표전화번호<span class="fontred">*</span></th>
			                   <td>
			                   		<input id="telNo" name="telNo" type="text" value=""  maxlength="13" class="inpw40" data-requireNm="전화번호" data-maxLength="20" title="전화번호" />
				               </td>
				               <th scope="row">핸드폰번호<span class="fontred">*</span></th>
			                   <td>
			                   		<input id="hpTelNo" name="hpTelNo" type="text" value=""  maxlength="13" class="inpw40" data-requireNm="휴대전화번호" data-maxLength="20" title="휴대전화번호" />
				               </td>
			                 </tr>
			                 <tr>
			                   <th scope="row">아이디/비밀번호 찾기 질문<span class="fontred">*</span></th>
			                   <td>
			                   		<select class="" id="pwdFindQues" name="pwdFindQues" onchange="" style=width:300px;" data-requireNm="비밀번호찾기질문코드" data-maxLength="6" title="비밀번호찾기질문코드" >
									</select>
				               </td>
				               <th scope="row">답변<span class="fontred">*</span></th>
			                   <td>
			                   		<input id="pwdFindAsw" name="pwdFindAsw" type="text" value=""  maxlength="11" class="inpw40" data-requireNm="비밀번호찾기답" data-maxLength="100" title="비밀번호찾기답"/>
				               </td>
			                 </tr>
			                 <tr>
			                 	<th scope="row">사용여부<span class="fontred">*</span></th>
				                 <td colspan="3">
				                 	<select class="selw10" id="useYn" name="useYn" onchange="" data-requireNm="사용여부" data-maxLength="1" title="사용여부" >
			                   			<option value="">선택</option>
			                   			<option value="Y">사용</option>
			                   			<option value="N">미사용</option>
			                   		</select>
				                 </td>
			                 </tr>
			                </tbody>
		                 </table>
	                  </div>
					</form>
                    <div class="btn_c">
                      <ul>
                        <li><a href="#" class="RdButton" onclick="fn_updFsysUserUDtl();return false;">저장</a></li>
                        <li><a href="#" class="RdButton" onclick="fn_delFsysUserUDtl();return false;">삭제</a></li>
                        <!-- <li><a href="#" class="myButton">재입력</a></li> -->
                        <li><a href="#" class="myButton" onclick="fn_indexFsysUserMList();return false;">목록</a></li>
                      </ul>
                    </div>
                    <!-----------//-검색------------------->   
                       
                    
                          
                  </div>
            </div>
        
        </div>
 </div>
<div id="divPrgPopup"></div>
</body>
</html>