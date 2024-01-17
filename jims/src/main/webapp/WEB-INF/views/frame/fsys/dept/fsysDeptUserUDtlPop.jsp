<%--
/**
 * <pre>
 * 1. 작성일 : 2021. 4. 14. 오전 10:11:33
 * 2. 작성자 : ilyong
 * 3. 화면명 : 부서별 사용자 정보 상세 팝업
 * 4. 설명 : 부서별 사용자 정보 상세 팝업
 * </pre>
 */
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<script type="text/javaScript" language="javascript" defer="defer">

var paramInsttCd = "${paramMap.insttCd}";
var paramUserId = "${paramMap.userId}";
var beforeUseYn;

$(document).ready(function() {
	tabId = parent.$('li[id*=li_tabs-M][aria-expanded=true]').attr('aria-controls');	
	
	var codeInfo2 = [{cdId:'C02',selectId:'ioficStatCd',type:'1', callbackNm:'fn_ajaxIoficStatCdtCallback', sqlQueryId:''}];
	fn_ajaxCodeList(codeInfo2);
	
	
	
	<%/* 달력 세팅 */%>
	gfn_calendarConfig("aprvDt", "", "", "");   <%/* 승인일자 */%>
<%-- 	gfn_calendarConfig("rtirDt", "", "", "");   <%/* rtirDt */%> --%>
<%-- 	gfn_calendarConfig("searchStrtDtTm", "searchEndDtTm", "minDate", "");    <%/* 신청일자 from */%> --%>
<%-- 	gfn_calendarConfig("searchEndDtTm", "searchStrtDtTm", "maxDate", "");   <%/* 신청일자 to */%> --%>
		
// 	$("#sortOrd").on("keyup",function(){
// 		gfn_new_number("sortOrd");
// 	})

	fn_selDeptUserDtl(); 
});

function fn_ajaxIoficStatCdtCallback(data){
	
	fn_selDeptUserDtl(); 
 	//$('#schPgsStat option:eq(0)').before("<option value='' selected>전체</option>");
}

/**
 * 부서별 사용자 정보 상세 조회
 * @param
 * @returns 
 */
function fn_selDeptUserDtl(){
	
	var callUrl = "<c:url value='/fsys/dept/queryFsysDeptUserMDtl.do'/>";
	
	requestUtil.search({callUrl:callUrl, srhFormNm:'insForm', callbackNm:'fn_selDeptUserDtlCallback'});
	
}

/**
 * 부서별 사용자 정보 상세 조회 콜백
 * @param
 * @returns 
 */
function fn_selDeptUserDtlCallback(data){
	//alert("=======aprvDt===>>>"+$("#aprvDt").val());
	var fAprvdt = fn_ymdFormatter($("#aprvDt").val());
	//var fRtirDt = fn_ymdFormatter($("#rtirDt").val());
	var dispUserNm = data.rtnMap.mainCrgrYn;
	var insttCd = data.rtnMap.insttCd;
	var teamNm = data.rtnMap.insttCdNm;
	var dispTeamNm = insttCd+" ("+teamNm+")";
	var userId = data.rtnMap.userId;
	var userNm = data.rtnMap.userNm;
	var dispUserNm = userId+" ("+userNm+")";
	var ioficStatCd = data.rtnMap.ioficStatCd;
	var mainCrgrYn = data.rtnMap.mainCrgrYn;
	var aprvAuthYn = data.rtnMap.aprvAuthYn;
	beforeUseYn = gfn_nullRtnSpace(data.rtnMap.useYn);
	var dispRtirDt = gfn_dashDate2(gfn_nullRtnSpace(data.rtnMap.rtirDt),'-');
	
	//alert("=====ioficStatCd=====>>>"+ioficStatCd);
	
	//$("#ioficStatCd").val(ioficStatCd).prop("selected", true);

	//var disuseDt = gfn_dashDate2(gfn_nullRtnSpace(data.resultMap.disuseDt),'-');


	
	$("#aprvDt").val(fAprvdt);
	//$("#rtirDt").val(fRtirDt);
	
	 if(data.rtnMap.mainCrgrYn == 'Y'){
		 //alert("1111111111111111111111");
 	 	$("#mainCrgrYn").attr("checked", true);
 	 	$("#mainCrgrYn").val(mainCrgrYn);
	 }else{
		$("#mainCrgrYn").attr("checked", false); 
		 //alert("22222222222222222222");
	 }
	//alert("=======test===>>>"+test); aprvAuthYn
	
	 if(data.rtnMap.aprvAuthYn == 'Y'){
		 //alert("1111111111111111111111");
 	 	$("#aprvAuthYn").attr("checked", true);
 	 	$("#aprvAuthYn").val(aprvAuthYn);
	 }else{
		$("#aprvAuthYn").attr("checked", false); 
		 //alert("22222222222222222222");
	 }
	 
	 $("#dispTeamNm").text(dispTeamNm); 
	 $("#dispUserNm").text(dispUserNm); 
	
	 $("#userNm").val();
	 $("#dispRtirDt").text(dispRtirDt); 
}

/**
 * 8자리 날짜 포맷 변경
 * @param
 * @returns 
 */
function fn_ymdFormatter(num){

    if(!num) return "";
    var formatNum = '';

    // 공백제거
    num=num.replace(/\s/gi, "");

    try{
         if(num.length == 8) {
              formatNum = num.replace(/(\d{4})(\d{2})(\d{2})/, '$1-$2-$3');
         }
    } catch(e) {
         formatNum = num;
         console.log(e);
    }
    return formatNum;
}





/**
* 부서별 사용자 상세 정보 수정 
* @param
* @returns 
*/
function fn_modifyDeptUserInfo(){
	if(!fn_chkVal()) return;
	
	fn_showModalPage("수정 하시겠습니까?", function() {
		var callUrl = "<c:url value='/fsys/dept/updFsysDeptUserUDtl.do'/>";
        requestUtil.save({callUrl:callUrl,srhFormNm:'insForm',callbackNm:'fn_modifyDeptUserInfoCallback'});
	});
	
}

/**
 * @!@ 유효성 체크
 * @param {string} data
 * @returns 
 */
function fn_chkVal(){
	 
	//var userNm = $("#userNm").val(); //사용자ID(사용자명)
 	var aprvDt = $("#aprvDt").val(); //승인일자
 	var mainCrgrYn = $("#mainCrgrYn").val(); //주담당자 여부
 	var aprvAuthYn = $("#aprvAuthYn").val(); //승인권자여부
 	var useYn = $("#useYn").val(); //승인권자여부
 	
 	if(aprvDt.length < 1){	
		fn_showUserPage("승인일자를 입력하세요.", function() {
			$("#uprDept").focus();
		});
		return false;  
	}else if(gfn_isNull(mainCrgrYn) && gfn_isNull(aprvAuthYn)){
		fn_showUserPage("주담당자여부 또는 승인권자여부를 선택하세요.", function() {
			$("#mainCrgrYn").focus();
		});
		return false;
	}else if(beforeUseYn=="N" && useYn == "Y"){
		fn_showUserPage("미사용인 사용자는 사용으로 수정할 수 없습니다.\n사용자등록 화면에서 재등록하십시요.", function() {
			$("#useYn").focus();
		});
		return false;
	}
	return true; 
}

/**
 * 부서별 사용자 상세 정보 수정  콜백
 * @param {string} data
 * @returns 
 */
function fn_modifyDeptUserInfoCallback(data){
	 var insttCd = "${paramMap.insttCd}";
	 var insttCdNm =  $("#insttCdNm").text();
	 fn_setSearchInsttCd(insttCd, insttCdNm);
	 fn_dialogClose('fsysDeptUserUDtlPop');
}
 
/**
* @!@ 상세 코드 관리 삭제
* @param
* @returns 
*/
function fn_delDeptUserInfo(){

	fn_showModalPage("삭제 하시겠습니까?", function() {
		var callUrl = "<c:url value='/fsys/dept/delFsysDeptUserUDtl.do'/>";
        requestUtil.save({callUrl:callUrl,srhFormNm:'insForm',callbackNm:'fn_delDeptUserInfoCallback'});
	});
	
}

/**
 * @!@ 상세 코드 관리 삭제 콜백
 * @param {string} data
 * @returns 
 */
function fn_delDeptUserInfoCallback(data){
	 var insttCd = "${paramMap.insttCd}";
	 var insttCdNm =  $("#insttCdNm").text();
	 fn_setSearchInsttCd(insttCd, insttCdNm);
	 fn_dialogClose('fsysDeptUserUDtlPop');
}
 /**
  * @!@ 상세 코드 관리 삭제 콜백
  * @param {string} data
  * @returns 
  */
 function fn_chgMainCrgrYn(){
	if($("input:checkbox[name=mainCrgrYn]").is(":checked") ){
		$("#aprvAuthYn").prop("checked", false);
		$("#aprvAuthYn").val("N");
		$("#mainCrgrYn").val("Y");
	}else{
	    $("#aprvAuthYn").prop("checked", true);
		$("#aprvAuthYn").val("Y");
		$("#mainCrgrYn").val("N");
    }
	 //alert("======aprvAuthYn=====>>>"+$("#aprvAuthYn").val());
}  
	 
function fn_chgAprvAuthYn(){
	 if($("input:checkbox[name=aprvAuthYn]").is(":checked") ){
		 $("#mainCrgrYn").prop("checked", false);
		 $("#mainCrgrYn").val("N");
		 $("#aprvAuthYn").val("Y");
	 }else{
		 $("#mainCrgrYn").prop("checked", true);
		 $("#mainCrgrYn").val("Y");
		 $("#aprvAuthYn").val("N");
	 }
	 //alert("======mainCrgrYn=====>>>"+$("#mainCrgrYn").val());
}  

function fn_closePop(){
	fn_dialogClose('fsysDeptUserUDtlPop');
}
</script>

</head>
<body>
<div id="con_wrap_pop">
	<div class="content">
               <div id="contents_info">
                      <!--- contnets  적용 ------>
                      <div class="window_popup">
                          <div class="sub_ttl">부서별 사용자 상세 정보</div>
                         
                          <div class="sub">
                             <!--------------검색------------------>
                             <form name="insForm" id="insForm" method="post">
			                     <input type="hidden" id="insttCd" name="insttCd" value="<c:out value="${paramMap.insttCd}" />" />
			                     <input type="hidden" id="userId" name="userId" value="<c:out value="${paramMap.userId}" />" />
			                      <div class="t_list">
					                 <table class="iptTblX">
						               <caption>수정</caption>
						               <colgroup>
							             <col width="20%" />
							             <col width="*" />
						               </colgroup>
						               <tbody>
						               		<tr>
						                        <th scope="row">부서코드(부서명)</th>
						                        <td >
						                            <span  id=dispTeamNm > </span>
						                        </td>
						                    </tr>
						                    <tr>
						                        <th scope="row">사용자ID(사용자명)</th>
						                        <td>
						                        	<span  id=dispUserNm > </span>
<!-- 													<input id="userNm" name="userNm"  title="사용자명"  type="text" style='width:200px' />&nbsp; -->
													<input type="hidden" id="userId" name="userId" value="" />
<!-- 													<a href="javascript:fn_insDeptUser();" class="myButton" id="btnInsDeptUser">사용자 검색</a> -->
						                        </td>
						                    </tr>
						                    <tr>
						                        <th scope="row">승인일자<span class="fontred">*</span></th>
						                        <td>
						                            <input id="aprvDt" name="aprvDt"  title="승인일자"  type="text" value="" style='width:200px' maxlength="10"/>
						                        </td>
						                    </tr>
						                    <tr>
						                        <th scope="row">주담당자 여부</th>
						                        <td>
<!-- 						                            <input id="mainCrgrYn" name="mainCrgrYn" type="text" title="주담당자 여부" value=""  style='width:200px' maxlength="3"/> -->
						                            <input type="checkbox" id="mainCrgrYn" name="mainCrgrYn" title="주담당자 여부" class="check_agree1" value="" onclick="javascript:fn_chgMainCrgrYn();">
						                        </td>
						                    </tr>
						                    <tr>
						                        <th scope="row">승인권자여부</th>
						                        <td>
<!-- 						                            <input id="aprvAuthYn" name="aprvAuthYn"  title="승인권자여부"  type="text" value="" style='width:200px' /> -->
													<input type="checkbox" id="aprvAuthYn" name="aprvAuthYn" title="승인권자여부" class="check_agree1" value="" onclick="javascript:fn_chgAprvAuthYn();">
						                        </td>
						                    </tr>
						                    <tr>
						                        <th scope="row">정지일자</th>
						                        <td>
						                        	<span  id=dispRtirDt> </span>
<!-- 						                            <input id="rtirDt" name="rtirDt"  title="정지일자"  type="text" value="" style='width:200px' maxlength="10"/> -->
						                        </td>
						                    </tr>
<!-- 						                    <tr> -->
<!-- 						                        <th scope="row">재직상태</th> -->
<!-- 						                        <td> -->
<!-- 					                				<select class="selw6"  id="ioficStatCd" name="ioficStatCd"  > -->
<!-- 													</select>	 -->
<!-- 						                        </td> -->
<!-- 						                    </tr> -->
						                    
						                    <tr>
						                        <th scope="row">사용여부</th>
						                        <td>
						                            <select id="useYn" name="useYn" class="selw6">
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
			                        <li><a href="#" class="myButton" onclick="fn_modifyDeptUserInfo();return false;">수정</a></li>
			                        <li><a href="#" class="myButton" onclick="fn_closePop();return false;">닫기</a></li>
<!-- 			                        <li><a href="#" class="myButton" onclick="fn_delDeptUserInfo();return false;">삭제</a></li> -->
			                        <!-- <li><a href="#" class="myButton" onclick="fn_indexFsysProgramMList();return false;">목록</a></li> -->
			                      </ul>
			                    </div>
                           
                          </div>
                         
                    </div>
               </div>
                 <!---  //contnets  적용 ------>
       </div>
  </div>
</body>
</html>