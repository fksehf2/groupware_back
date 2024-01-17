<%--
/**
 * <pre>
 * 1. 작성일 : 2021. 4. 14. 오전 10:11:33
 * 2. 작성자 : ilyong
 * 3. 화면명 : 부서관리 > 부서별 사용자 등록
 * 4. 설명 : 부서별 사용자 등록 
 * </pre>
 */
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<script type="text/javaScript" language="javascript" defer="defer">
var spanCd = "${paramMap.paramInsttCd}";
var spanText = "${paramMap.paramInsttCdNm}";

$(document).ready(function() {
	tabId = parent.$('li[id*=li_tabs-M][aria-expanded=true]').attr('aria-controls');	
// 	alert("===tabId====>>>"+tabId);
// 	$("#sortOrd").on("keyup",function(){
// 		gfn_new_number("sortOrd");
// 	})
	
// 	var codeInfo2 = [{cdId:'C02',selectId:'ioficStatCd',type:'1', callbackNm:'fn_ajaxIoficStatCdtCallback', sqlQueryId:''}];
// 	fn_ajaxCodeList(codeInfo2);
	
	$("#insttCdNm").text(spanCd+"("+decodeURIComponent(spanText)+")");
	$("#popInsttCdNm").val($("#insttCdNm").text());
	//alert($("#popInsttCdNm").val());
	gfn_calendarConfig("aprvDt", "", "", "");   <%/* 승인일자 */%>
	gfn_calendarConfig("rtirDt", "", "", "");   <%/* rtirDt */%>
	
	$("#aprvDt").val(gfn_getDate());
	
	//var test111 = decodeURIComponent(spanText);
	//alert("=====test111======>>>"+test111);
});

/**
 * 코드 조회 콜백
 * @param {string} data
 * @returns 
 */
function fn_ajaxIoficStatCdtCallback(data){
	
}

/**
* 부서별 사용자 등록
* @param
* @returns 
*/
function fn_insDeptUser(){
	if(!fn_chkVal()) return;
	
	 if($('input:checkbox[id="mainCrgrYn"]').is(":checked")==true){
		$("#mainCrgrYn").val("Y"); 
		$("#aprvAuthYn").val("N"); 
	}else if($('input:checkbox[id="aprvAuthYn"]').is(":checked")==true){
		$("#mainCrgrYn").val("N"); 
		$("#aprvAuthYn").val("Y"); 
	}
	
	//alert("=====mainCrgrYn====>>>"+$("#mainCrgrYn").val()+"\n =====aprvAuthYn====>>>"+$("#aprvAuthYn").val());
	
	fn_showModalPage("등록 하시겠습니까?", function() {
		var callUrl = "<c:url value='/fsys/dept/regFsysDeptUserRDtl.do'/>";
        requestUtil.save({callUrl:callUrl,srhFormNm:'insForm',callbackNm:'fn_fn_insDeptUserCallback'});
	});
	
}

/**
 * 부서별 사용자 등록 콜백
 * @param {string} data
 * @returns 
 */
function fn_fn_insDeptUserCallback(data){
	 //fn_queryMList(1);
	 fn_setSearchInsttCd(spanCd, spanText);
	 fn_dialogClose('fsysDeptUserRDtlPop');
}

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

/**
 * @!@ 유효성 체크
 * @param {string} data
 * @returns 
 */
function fn_chkVal(){
	 
	var userNm = $("#userNm").val(); //사용자ID(사용자명)
 	var aprvDt = $("#aprvDt").val(); //승인일자
 	var mainCrgrYn = $("#mainCrgrYn").val(); //주담당자 여부
 	var aprvAuthYn = $("#aprvAuthYn").val(); //승인권자여부
	 
	if(userNm.length < 1){	
		fn_showUserPage("사용자를 선택하세요.", function() {
			$("#userNm").focus();
		});
		return false;  
	}else if(aprvDt.length < 1){	
		fn_showUserPage("승인일자를 입력하세요.", function() {
			$("#uprDept").focus();
		});
		return false;  
	}else if(gfn_isNull(mainCrgrYn) && gfn_isNull(aprvAuthYn)){
		fn_showUserPage("주담당자여부 또는 승인권자여부를 선택하세요.", function() {
			$("#mainCrgrYn").focus();
		});
		return false;
	}
	return true; 
}


/**
 * @!@ 사용자 조회 팝업
 * @param cd
 * @returns 
 */
function fn_searchFsysUserFind() {
	//debugger;
	var callUrl = "<c:url value = "/com/PageLink.do"/>"
	var paramInsttCdNm2 = encodeURIComponent($("#searchInsttCdNm2").val());
	
	requestUtil.mdPop({
		//popUrl : callUrl+"?link="+"frame/fsys/user/fsysUserFindMListPop&paramInsttCd="+$("#searchInsttCd2").val()+"&paramInsttCdNm="+$("#searchInsttCdNm2").val(),
		//popUrl : callUrl+"?link="+"frame/fsys/user/fsysUserFindMListPop&paramInsttCdNm2="+$("#paramInsttCdNm").val(),
		popUrl : callUrl+"?link="+"frame/fsys/user/fsysUserFindMListPop&paramInsttCdNm2="+paramInsttCdNm2,
		
		height: 700,
        width: 1000,
        title: '사용자 조회 팝업',
        divId : 'fsysUserFindMListPop'
        //divId : 'eqpMgmtMListPop'  var paramInsttCdNm = encodeURIComponent($("#searchInsttCdNm2").val());
	});

}

function fn_chgVal(obj){
// 	if($(obj).is(":checked") == true){
// 		//alert("11111");
// 		obj.value = "Y";
// 		alert("obj.value===>>>"+obj.value);
// 	}
}

</script>

</head>
<body>
<div id="con_wrap_pop">
	<div class="content">
               <div id="contents_info">
                      <!--- contnets  적용 ------>
                      <div class="window_popup">
                          <div class="sub_ttl">부서별 사용자 등록</div>
                         
                          <div class="sub">
                             <!--------------검색------------------>
                             <form name="insForm" id="insForm" method="post">
			                     <input type="hidden" id="paramInsttCd" name="paramInsttCd" value="<c:out value="${paramMap.paramInsttCd}" />" />
			                     <input type="hidden" id="paramInsttCdNm" name="paramInsttCdNm" value="<c:out value="${paramMap.paramInsttCdNm}" />" />
			                     <input type="hidden" id="popInsttCdNm" name="popInsttCdNm" value="" /> 
			                     <input type="hidden" id="pBeforeInsInsttCd" name="pBeforeInsInsttCd" value="" /> 
			                      <div class="t_list">
					                 <table class="iptTblX">
						               <caption>등록</caption>
						               <colgroup>
							             <col width="20%" />
							             <col width="*" />
						               </colgroup>
						               <tbody>
						               		<tr>
						                        <th scope="row">부서코드(부서명)</th>
						                        <td >
						                            <span  id="insttCdNm" > </span>
						
						                        </td>
						                    </tr>
						                    <tr>
						                        <th scope="row">사용자ID(사용자명)<span class="fontred">*</span></th>
						                        <td>
													<input id="userNm" name="userNm"  title="사용자명"  type="text" style='width:200px' readonly/>&nbsp;
													<input type="hidden" id="userId" name="userId" value="yyy" />
													<a href="#" class="buttonG40" onclick="fn_searchFsysUserFind();return false;">검색</a>
						                        </td>
						                    </tr>
						                    <tr>
						                        <th scope="row">승인일자<span class="fontred">*</span></th>
						                        <td>
						                            <input id="aprvDt" name="aprvDt"  title="승인일자"  type="text" value="" style='width:200px' />
						                        </td>
						                    </tr>
						                    <tr>
						                        <th scope="row">주담당자여부</th>
						                        <td>
						                            <input type="checkbox" id="mainCrgrYn" name="mainCrgrYn" title="주담당자 여부"  class="" value="" onclick="javascript:fn_chgMainCrgrYn(this);">
						                        </td>
						                    </tr>
						                    <tr>
						                        <th scope="row">승인권자여부</th>
						                        <td>
						                            <input type="checkbox" id="aprvAuthYn" name="aprvAuthYn" title="승인권자여부"  class="" value="" onclick="javascript:fn_chgAprvAuthYn(this);">
						                        </td>
						                    </tr>
<!-- 						                    <tr> -->
<!-- 						                        <th scope="row">정지일자</th> -->
<!-- 						                        <td> -->
<!-- 						                            <input id="rtirDt" name="rtirDt"  title="정지일자"  type="text" value="" style='width:200px' /> -->
<!-- 						                        </td> -->
<!-- 						                    </tr> -->
<!-- 						                    <tr> -->
<!-- 						                        <th scope="row">재직상태<span class="fontred">*</span></th> -->
<!-- 						                        <td> -->
<!-- 						                            <select  id="ioficStatCd" name="ioficStatCd" onchange="" class="selw6" > -->
<!-- 					                				</select>  -->
<!-- 						                        </td> -->
<!-- 						                    </tr> -->
						                    <tr>
						                        <th scope="row">사용여부<span class="fontred">*</span></th>
						                        <td>
						                            <select id="useYn" name="useYn" class="selw6">
						                                <option value="Y">사용</option>
						                                <option value="N">미사용</option>
						                            </select>
						                        </td>
						                    </tr>
						            </tbody>
					                 </table>
					                 <span style="color:red">※ 주담당자여부, 승인권자여부 중 한가지는 필수체크</span>
				                  </div>
								</form>
			                    <div class="btn_c">
			                      <ul>
			                        <li><a href="#" class="myButton" onclick="fn_insDeptUser();return false;">등록</a></li>
			                        <li><a href="#" class="myButton" onclick="fn_dialogClose('fsysDeptUserRDtlPop');return false;">닫기</a></li>
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