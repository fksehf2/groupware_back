<%--
/**
 * <pre>
 * 1. 작성일 : 2021. 4. 14. 오전 10:11:33
 * 2. 작성자 : ilyong
 * 3. 화면명 : 시스템 관리 > 부서 관리
 * 4. 설명 : 부서 등록 팝업
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

$(document).ready(function() {
	tabId = parent.$('li[id*=li_tabs-M][aria-expanded=true]').attr('aria-controls');	
	
	var codeInfo = [{cdId:'deptLvl',selectId:'deptLvl',type:'4', callbackNm:'fn_deptLvlCallback', sqlQueryId:'fsysDeptDAO.queryDeptLvlList'}]; //부서레벨
	fn_ajaxCodeList(codeInfo);	
	
	
});

/**
* @!@ 부서레벨 콜백
* @param
* @returns 
*/
function fn_deptLvlCallback(){
	//alert("====부서레벨 콜백===>>>");
	//$('#deptLvl option:eq(0)').before("<option value='' selected>선택</option>");
	$('#deptLvl').prepend('<option value="" selected>선택</option>');
	
}

/**
* @!@ 상위부서 조회
* @param
* @returns 
*/
function fn_uprDeptList(obj){
	//alert("======fn_uprDeptList obj.value======>>>>"+obj.value);
	var deptLvl2 = obj.value;
	var selDeptLvl = deptLvl2 - 1;
	
	if(gfn_isNull(deptLvl2)){
		$("#uprDept").empty();
		//$("#uprDept").append('<option value="">선택</option>');
		$('#uprDept').prepend('<option value="">선택</option>');
	}else{
		$("#selDeptLvl").val(selDeptLvl);
		var callUrl = "<c:url value='/fsys/dept/queryUprDeptMList.do'/>?deptLvl2="+deptLvl2;
	    requestUtil.search({callUrl:callUrl,srhFormNm:'insForm',callbackNm:'fn_uprDeptListCallback'});
	}
	
	
	//alert("=========selDeptLvl========>>>"+selDeptLvl);
	
	
}

/**
* @!@ 상위부서 조회 콜백
* @param
* @returns 
*/
function fn_uprDeptListCallback(data){
	//alert("====상위부서 조회 콜백===>>>");
	
	//$("#topMenuNo").val(data.topMenuNo);
	$("#uprDept").empty();
	if(data.list.length > 0){
		//$("#uprDept").append('<option value="">선택</option>');
		//$('#uprDept').prepend('<option value="" selected>선택</option>');
		
		$.each(data.list, function(idx, row) {
	    	//$("#uprDept").append("<option value='"+row.cd+"'>"+row.cdNm+"</option>");
	    	$('#uprDept').prepend("<option value='"+row.cd+"'>"+row.cdNm+"</option>");
	    });
		$('#uprDept').prepend('<option value="" selected>선택</option>');
	}
}

/**
* @!@ 상위부서 조회 콜백
* @param
* @returns 
*/
function fn_setUprDeptNm(obj){
	//alert(obj.selectedIndex);
 	var uprDeptNm = obj.options[obj.selectedIndex].text;
 	var upInsttCd = obj.options[obj.selectedIndex].value;
 	//alert(upInsttCd);
 	if(obj.selectedIndex==0){
 		$("#uprDeptNm").text("");
 	}else{
 		$("#uprDeptNm").text(uprDeptNm);	
 		$("#upInsttCd").val(upInsttCd);
 		$("#deptNm").val(uprDeptNm);
 	}
 	
}

/**
* @!@ 부서 등록
* @param
* @returns 
*/
function fn_insDeptRDtl(){
	
	if(!fn_chkVal()) return;
	
	$("#insttNm").val("(주)제닉스");
	
	fn_showModalPage("등록 하시겠습니까?", function() {
		var callUrl = "<c:url value='/fsys/dept/regFsysDeptRDtl.do'/>";
        requestUtil.save({callUrl:callUrl,srhFormNm:'insForm',callbackNm:'fn_insDeptRDtlCallback'});
	});
	
	//parent.$('#tabs-M000000408').find("iframe").attr("src", '<c:url value="/fsys/dept/queryFsysDeptMList.do"/>');
	
	
// 	$("#rentAplnSno").val(rentAplnSno);
	
// 	var data = {rowDatas : tpArray, formDatas1 : formObj1, formDatas2 : formObj2, formDatas3 : formObj3};
//     var callUrl = "<c:url value='/eqp/lend/lendReqEqpLendLendReqRDtl.do'/>";
//     requestUtil.saveData({callUrl:callUrl,data:data,callbackNm:'fn_modifyEqpLendCallBack'});
}

/**
 * @!@ 유효성 체크
 * @param {string} data
 * @returns 
 */
function fn_chkVal(){
	 
	 var deptLvl = $("#deptLvl").val(); //부서레벨
	 var uprDept = $("#uprDept").val(); //상위부서
	 var teamNm = $("#teamNm").val(); //팀명
	 
	if(deptLvl.length < 1){	
		fn_showUserPage("부서레벨을 선택하세요.", function() {
			$("#deptLvl").focus();
		});
		return false;  
	}else if(uprDept.length < 1){	
		fn_showUserPage("상위부서를 선택하세요.", function() {
			$("#uprDept").focus();
		});
		return false;  
	}else if(teamNm.length < 1){
		fn_showUserPage("팀명을 입력하세요.", function() {
			$("#teamNm").focus();
		});
		return false;
	}
	return true; 
}


/**
 * @!@ 그룹 코드 관리 수정 콜백
 * @param {string} data
 * @returns 
 */
function fn_insDeptRDtlCallback(data){
	 
	 //alert("부서등록 성공");
	 fn_queryDeptMList(1);
	 fn_dialogClose('fsysDeptRDtlPop');
	 
}

function fn_closePop(){
	//alert("닫기 성공");
	fn_queryDeptMList(1);
	fn_dialogClose('fsysDeptRDtlPop');
} 
 
</script>

</head>
<body>
<div id="con_wrap_pop">
	<div class="content">
               <div id="contents_info">
                      <!--- contnets  적용 ------>
                      <div class="window_popup">
                          <div class="sub_ttl">부서 등록</div>
                         
                          <div class="sub">
                             <!--------------검색------------------>
                             <form name="insForm" id="insForm" method="post">
                             	 <input type="hidden" id="regrId"   name="regrId"   value="Admin"/>
			                      <div class="t_list">
					                 <table class="iptTblX">
						               <caption>수정</caption>
						               <colgroup>
							             <col width="20%" />
							             <col width="*" />
						               </colgroup>
						               <tbody>
						               		<tr>
						                        <th scope="row" id="test">부서레벨<span class="fontred">*</span></th>
						                        <td >
						                            <select id="deptLvl" name="deptLvl" class="selw6" onchange="fn_uprDeptList(this);return false;" data-requireNm="부서레벨" data-maxLength="4" title="부서레벨" >
						                            </select>
													<input type="hidden" id="selDeptLvl"   name="selDeptLvl"   value=""/>		
													<input type="hidden" id="deptNm"   name="deptNm"   value="" data-requireNm="과명" data-maxLength="100" title="과명" />				                            
						                        </td>
						                    </tr>
						                    <tr>
						                        <th scope="row">상위부서<span class="fontred">*</span></th>
						                        <td >
						                            <select id="uprDept" name="uprDept" class="selw15" onchange="fn_setUprDeptNm(this);return false;" >
						                            	<option value="">선택</option>
						                            </select>
						                            <input type="hidden" id="upInsttCd"   name="upInsttCd"   value="" data-requireNm="상위기관코드" data-maxLength="10" title="상위기관코드"/>
						                            <input type="hidden" id="insttNm"   name="insttNm"   value="" data-requireNm="기관명" data-maxLength="100" title="기관명"/>
						                        </td>
						                    </tr>
						                    <tr>
						                        <th scope="row">상위부서명<span class="fontred">*</span></th>
						                        <td >
						                            <span  id="uprDeptNm" name="uprDeptNm"></span>
<!-- 						                            <input type="hidden" id="upInsttCd"   name="upInsttCd"   value="0000000001"/> -->
						                        </td>
						                    </tr>
						                    <tr>
						                        <th scope="row">팀명<span class="fontred">*</span></th>
						                        <td>
						                            <input id="teamNm" name="teamNm"  title="팀명"  type="text" maxlength="50" style='width:470px' data-requireNm="팀명" data-maxLength="100" title="팀명" />
						                        </td>
						                    </tr>   
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
			                        <li><a href="#" class="myButton" onclick="fn_insDeptRDtl();return false;">등록</a></li>
			                        <li><a href="#" class="myButton" onclick="fn_closePop();return false;">닫기</a></li>
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