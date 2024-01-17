<%--
/**
 * <pre>
 * 1. 작성일 : 2021. 4. 14. 오전 10:49:11
 * 2. 작성자 : ilyong
 * 3. 화면명 : 사용자관리 > 사용자 등록
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
	
	var codeInfo = [{cdId:'C01',selectId:'userGb' ,type:'1', callbackNm:'fn_ajaxUserGbCallback'},{cdId:'C06',selectId:'pwdFindQues' ,type:'1'}];
	fn_ajaxCodeList(codeInfo);
	
});

/**
 * 공통 코드 콜백함수 
 * @param {string} prgID
 * @returns 
 */
function fn_ajaxUserGbCallback(data){
 	$('#userGb option:eq(0)').before("<option value='' selected>선택</option>");
 	$('#pwdFindQues option:eq(0)').before("<option value='' selected>선택</option>");
}
 


function fn_callback(data){
	$("#topMenuNo").val(data.topMenuNo);
	if(data.sysGrpList.length > 0){
		$("#menuLvl").append('<option value="lvl2">Level2</option>');
		
		$.each(data.sysGrpList, function(idx, row) {
	    	$("#upperMenuNo").append("<option value='"+row.sysGrp+"'>"+row.sysGrpNm+"</option>");
	    });
	}
	
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
function fn_regFsysUserRDtl(){
	var userGb = $("#userGb").val(); 
	var insttCd = $("#insttCd").val();
	var userId = $("#userId").val(); 
	var pwd = $("#pwd").val();
	var pwdConfirm = $("#pwdConfirm").val();
	var userNm = $("#userNm").val();
	var telNo = $("#telNo").val();
	var hpTelNo = $("#hpTelNo").val();
	var email = $("#email").val();
	var pwdFindQues = $("#pwdFindQues").val();
	var pwdFindAsw = $("#pwdFindAsw").val();
	var regStatus = $("#regStatus").val();

	if(userGb.length < 1){	
		fn_showUserPage( "사용자구분을 선택하세요.", function() {
			$("#userGb").focus();
        });
		return;  
	}else if(insttCd.length < 1){	
		fn_showUserPage( "부서를 선택하세요.", function() {
			$("#insttCd").focus();
        });
		return;  
	}else if(userId.length < 1){	
		fn_showUserPage( "아이디를 입력하세요.", function() {
			$("#userId").focus();
        });
		return;  
	}else if(pwd.length < 1){	
		fn_showUserPage( "비밀번호를 입력하세요.", function() {
			$("#pwd").focus();
        });
		return;  
	}else if(pwdConfirm.length < 1){
		fn_showUserPage( "비밀번호확인을 입력하세요.", function() {
			$("#pwdConfirm").focus();
        });
		return;
	}else if(userNm.length < 1){
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
	
	if(telNo.length > 4 || hpTelNo.length > 0){
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
	
	/* switch(gfn_validitionPw(pwd))
	{
		case 'ERROR_001' : fn_showUserPage("비밀번호는 10자리 이상이어야 합니다.");	return;
		case 'ERROR_002' : fn_showUserPage("비밀번호에 문자가 포함되어야 합니다.");	return;
		case 'ERROR_003' : fn_showUserPage("비밀번호에 숫자가 포함되어야 합니다.");	return;
		case 'ERROR_004' : fn_showUserPage("비밀번호에 특수문자가 포함되어야 합니다.");	return;
		case 'ERROR_005' : fn_showUserPage("비밀번호에 같은 문자를 3번 이상 사용하실 수 없습니다."); return;
	} */
	
	var validChkRst = gfn_validitionPw2(pwd, userId);
	   
   if(validChkRst != "SUCCESS"){
	   fn_showUserPage(validChkRst);
	   return false;
   }

	fn_saveComparePW();
	//Step3. 비밀번호 동일체크, 아이디 중복체크
	if(PW_Error == 1){	
		fn_showUserPage( "비밀번호를 확인해주세요.", function() {
			$("#pwd").focus();
        });
		return;	
	}
	
	if(ID_Duple != 0){	
		fn_showUserPage( "아이디 중복 확인을 해주세요.", function() {
			
        });
		return;	
	}
	
	var pwd = $("#pwd").val();
	
	fn_showModalPage("등록 하시겠습니까?", function() {
		//사용자 정보 수정
        var callUrl = "<c:url value='/fsys/user/regFsysUserRDtl.do'/>";
        requestUtil.save({callUrl:callUrl,srhFormNm:'insForm',callbackNm:'fn_regFsysUserRDtlCallback'});
	});
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

function fn_regFsysUserRDtlCallback(data){
	fn_indexFsysUserMList();
}
// /fsys/user/indexFsysUserMList.do

function fn_indexFsysUserMList(){
	parent.$('#tabs-M000000401').find("iframe").attr("src", '<c:url value="/fsys/user/indexFsysUserMList.do"/>');
	
}

function fn_indexFsysMenuMList(){
	parent.$('#tabs-M000000403').find("iframe").attr("src", '<c:url value="/fsys/menu/indexFsysMenuMList.do"/>');
}
/**
 * @!@ 메뉴 관리 등록 콜백
 * @param {json} data
 * @returns 
 */
function fn_regFsysMenuRDtlCallback(data){
	fn_indexFsysMenuMList();
}

<%/*아이디 중복 체크*/%>
function fn_IDcheck(){
	
	var checkParamId = $("#userId").val(); 
// 	alert(checkParamId.search(/\s/));
	
	if( checkParamId.search(/\s/) > 0 ){
		fn_showUserPage( "ID에 공백이 들어갈 수 없습니다.", function() {
			$("#userId").focus();
        });
		return;
	} 
	
	if(checkParamId == ""){
		fn_showUserPage( "아이디를 입력해 주세요.", function() {
			$("#userId").focus();
        });
		return;
	}else{
		var callUrl = "<c:url value='/fsys/user/sysUserCheckId.do'/>";
		requestUtil.search({callUrl:callUrl,srhFormNm:'insForm',setFormNm:'insForm',callbackNm:'fn_callbackpop'});
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
		fn_showUserPage( "이미 존재하는 ID입니다.", function() {
			$('#userId').val('');
			ID_Duple=1;
        });
		return;
	} else {
		fn_showUserPage( "사용가능한 ID입니다.", function() {
			ID_Duple=0;
        });
		return;
	}
	
}

/**
 * @!@ 부서조회 팝업
 * @param cd
 * @returns  
 */
function fn_searchDeptFIndListPop() {
   
		

//  	fn_showUserPage("이미 추가된 장비는 선택할 수 없습니다. \n ggg \n ggg \n ggg \n ggg");
// 	return;
	var callUrl = "<c:url value = "/com/PageLink.do"/>"

	requestUtil.mdPop({
		popUrl : callUrl+"?link="+"frame/fsys/user/fsysDeptFIndListPop&paramInsttCd="+$("#searchInsttCd2").val()+"&paramInsttCdNm="+$("#searchInsttCdNm2").val(),
		height: 700,
        width: 1000,
        title: '부서조회 팝업',
        divId : 'fsysDeptFIndListPop'
        //divId : 'eqpMgmtMListPop'
	});

}

function fn_deptFindPopCallBack(insttCd, deptNm, teamNm){
	//alert("=======insttCd====>>>"+insttCd+"\n=======deptNm====>>>"+deptNm+"\n=======teamNm====>>>"+teamNm);
	$("#insttCd").val(insttCd);
	$("#insttNm").val(deptNm+" "+teamNm);
}
</script>

</head>

<body>
<div id="con_wrap">
        <div class="content">
           <!----현재위치----->
             
            <div id="contents_info">
                 <div class="sub_ttl">사용자 등록</div><!-----타이틀------>
                 
                  <div class="sub">
                     <!------------검색------------------->
                     <form name="insForm" id="insForm" method="post">
                     
                     <input type="hidden" id="menuNo" name="menuNo" value="<c:out value="${param.menuNo}" />" />
					 <input type="hidden" id="sysGrp" name="sysGrp" value="<c:out value="${param.sysGrp}" />" />
					 <input type="hidden" id="topMenuNo" name="topMenuNo" value="" />
					 
                      <div class="t_list">
		                 <table class="iptTblX">
			               <caption>등록</caption>
			               <colgroup>
				             <col width="15%" />
				             <col width="35%" />
				             <col width="15%" />
				             <col width="*" />
			               </colgroup>
			               <tbody>
			                 <tr>
				                 <th scope="row">사용자구분<span class="fontred">*</span></th>
				                 <td >
				                 	<select class="selw10" id="userGb" name="userGb" onchange="" data-requireNm="사용자구분" data-maxLength="6" title="사용자구분" >
							 			<option value="C01002">Level1</option>
										<%-- <option value="" <c:out value="${sysGrpInfo.cd==''?\"selected\":\"\"}"/> >전체</option>
										<c:forEach items="${sysGrpList}" var="sysGrpInfo" varStatus="status">
										<option value="<c:out value="${sysGrpInfo.cd}"/>"><c:out value="${sysGrpInfo.cdNm}"/></option>
										</c:forEach> --%>
									</select>
				                 </td>
				                 <th scope="row">부서<span class="fontred">*</span></th>
				                 <td >
				                 	<input type="text" id="insttNm" name="insttNm"  value=""  class="inpw40" maxlength="25" readonly/>
				                 	<input type="hidden" id="insttCd" name="insttCd"  value=""  class="inpw40" maxlength="25" data-requireNm="소속기관코드" data-maxLength="10" title="소속기관코드" /> &nbsp;
<!-- 				                 	<input type="button"  class="btns" value="부서검색" id="input" onclick="fn_findInstt();"/> -->
										<a href="#" class="buttonG80" onclick="fn_searchDeptFIndListPop();return false;">부서검색</a>
				                 </td>
			                 </tr>
			                 <tr>
								<th scope="row">아이디<span class="fontred">*</span></th>
								<td colspan="3">
									<input id="userId" name="userId" type="text" value="" maxlength="20" class="inpw20" data-requireNm="사용자ID" data-maxLength="20" title="사용자ID" />&nbsp;	
<!-- 									<input type="button"  class="button100" value="아이디 중복검색" id="input" onclick="fn_IDcheck();"/>  -->
									<a href="#" class="buttonG80" onclick="fn_IDcheck();return false;">ID 중복확인</a>
								</td>
							</tr>
			                 <tr>
				                  <th scope="row">비밀번호<span class="fontred">*</span></th>
				                  <td colspan="3">
			                  		<input id="pwd" name="pwd" type="password" value="" maxlength="30" class="inpw20" data-requireNm="패스워드" data-maxLength="64" title="패스워드"/>
				                  </td>
			                 </tr>
			                 <tr>
				                 <th scope="row">비밀번호 확인<span class="fontred">*</span></th>
				                 <td colspan="3">
				                 	<input id="pwdConfirm" name="pwdConfirm" type="password" value=""  maxlength="30" class="inpw20"/> &nbsp;&nbsp;<font color="blue">※ 문자,숫자,특수문자 혼용, 9자리 이상입력.</font>	
				                 </td>
			                 </tr>
			                 <tr>
				                 <th scope="row">이름<span class="fontred">*</span></th>
				                 <td colspan="3">
				                 	<input id="userNm" name="userNm" type="text" value=""  maxlength="25"  class="inpw20" data-requireNm="사용자명" data-maxLength="50" title="사용자명" />
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
			                   		<select class="selw25" id="pwdFindQues" name="pwdFindQues" onchange="" data-requireNm="비밀번호찾기질문코드" data-maxLength="6" title="비밀번호찾기질문코드" >
									</select>
				               </td>
				               <th scope="row">답변<span class="fontred">*</span></th>
			                   <td>
			                   		<input id="pwdFindAsw" name="pwdFindAsw" type="text" value=""  maxlength="50" class="inpw40" data-requireNm="비밀번호찾기답" data-maxLength="100" title="비밀번호찾기답" />
				               </td>
			                 </tr>
			                </tbody>
		                 </table>
		                 
	                  </div>
					</form>
                    <div class="btn_c">
                      <ul>
                        <li><a href="#" class="RdButton" onclick="fn_regFsysUserRDtl();return false;">등록</a></li>
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
<div id="fsysDeptFIndListPop"></div>
</body>
</html>