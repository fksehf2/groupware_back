<%--
/**
 * <pre>
 * 1. 작성일 : 2021. 4. 14. 오전 10:11:33
 * 2. 작성자 : ilyong
 * 3. 화면명 : 프로그램 찾기 팝업
 * 4. 설명 : @!@ 프로그램 찾기 팝업 
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

var beforeDeptNm = "";

$(document).ready(function() {
	fn_queryMDtl();
});

/**
 * @!@ 화면 셋팅
 * @param
 * @returns 
 */
function fn_init(deptLvl){
	
	var append = "";
	
	if(deptLvl=="2"){
		append += "<span class='fontred'>*</span>";
		$("#thDept").append(append);
		$("#teamNm").attr("disabled",true);
	}else if(deptLvl=="3"){
		append += "<span class='fontred'>*</span>";
		$("#thTeam").append(append);
		$("#deptNm").attr("disabled",true);
	}else{
		$("#deptNm").attr("disabled",true);
		$("#teamNm").attr("disabled",true);
	}
	
 	
}


/**
 * @!@ 그룹 코드 상세 조회
 * @param
 * @returns 
 */
function fn_queryMDtl(){
	
	var callUrl = "<c:url value='/fsys/dept/queryFsysDeptMDtl.do'/>";
	
	requestUtil.search({callUrl:callUrl, srhFormNm:'insForm', callbackNm:'fn_queryMDtlCallback'});
	
}

/**
 * @!@ 그룹 코드 상세 조회 콜백
 * @param
 * @returns 
 */
function fn_queryMDtlCallback(data){
	
	var dispInsttNm = gfn_nullRtnSpace(data.rtnMap.insttNm); //기관명
	var dispDeptNm = gfn_nullRtnSpace(data.rtnMap.deptNm); //과명
	var deptLvl = gfn_nullRtnSpace(data.rtnMap.deptLvl); //과명
	beforeDeptNm = dispDeptNm;
	
	$("#beforeDeptNm").val(beforeDeptNm);
	$("#dispInsttNm").text(dispInsttNm);
	$("#dispDeptNm").text(dispDeptNm);
	
	fn_init(deptLvl);
 	
}

/**
* @!@ 부서 상세 정보 수정
* @param
* @returns 
*/
function fn_updFsysDeptUDtl(){
// 	alert("======deptUserCnt====>>>>"+$("#deptUserCnt").val());
	if(!fn_chkVal()) return;
	
	fn_showModalPage("수정 하시겠습니까?", function() {
		$("#deptNm").attr("disabled",false);
		$("#teamNm").attr("disabled",false);
		//var callUrl = "<c:url value='/fsys/dept/updFsysDeptUDtl.do'/>";
		var callUrl = "<c:url value='/fsys/dept/modifyFsysDeptUDtl.do'/>";
        requestUtil.save({callUrl:callUrl,srhFormNm:'insForm',callbackNm:'fn_updFsysDeptUDtlCallback'});
	});
	
}

/**
 * @!@ 유효성 체크
 * @param {string} data
 * @returns 
 */
function fn_chkVal(){
	var deptLvl =  $("#deptLvl").val(); //부서레벨
	var teamNm = $("#teamNm").val(); //팀명
	var deptNm = $("#deptNm").val(); //과명
	
	if(deptLvl == "3"){
		if(teamNm.length < 1){	
			fn_showUserPage("팀명을 입력하세요.", function() {
				$("#teamNm").focus();
			});
			return false;
		}
	}else if(deptLvl == "2"){
		if(deptNm.length < 1){	
			fn_showUserPage("과명을 입력하세요.", function() {
				$("#deptNm").focus();
			});
			return false;
		}
	}
	 
	return true; 
}

/**
 * @!@ 부서 상세 정보 수정 콜백
 * @param {string} data
 * @returns 
 */
function fn_updFsysDeptUDtlCallback(data){
	 fn_queryDeptMList(1);
	 fn_dialogClose('fsysDeptUDtlPop');
}
 
/**
* @!@ 부서 상세 정보 삭제
* @param
* @returns 
*/
function fn_delFsysCodeUDtl(){
	var deptUserCnt = $("#deptUserCnt").val();
	var teamNm = $("#teamNm").val();
	
	if(deptUserCnt > 0){
		fn_showUserPage("해당 부서의("+teamNm+") 사용자가 있어서 삭제가 불가능합니다.", function() {
			
		});
		return;
	}else{
	    //alert("삭제가능");	
	    fn_showModalPage("삭제 하시겠습니까?", function() {
	    	var callUrl = "<c:url value='/fsys/dept/delFsysDeptUDtl.do'/>";
	        requestUtil.save({callUrl:callUrl,srhFormNm:'insForm',callbackNm:'fn_delFsysDeptUDtlCallback'});
		});
	    
	}
		
	
	
}

/**
 * @!@ 부서 상세 정보 삭제 콜백
 * @param {string} data
 * @returns 
 */
function fn_delFsysDeptUDtlCallback(data){
	 fn_queryDeptMList(1);
	 fn_dialogClose('fsysDeptUDtlPop');	
}
 
</script>

</head>
<body>
<div id="con_wrap_pop">
	<div class="content">
               <div id="contents_info">
<!--                       - contnets  적용 ---- -->
                      <div class="window_popup">
                          <div class="sub_ttl">부서 상세 정보 수정</div>
                         
                          <div class="sub">
<!--                              ------------검색---------------- -->
                             <form name="insForm" id="insForm" method="post">
			                     <input type="hidden" id="insttCd" name="insttCd" value="<c:out value="${paramMap.insttCd}" />" />
			                     <input type="hidden" id="deptUserCnt" name="deptUserCnt" value="" />
			                     <input type="hidden" id="deptLvl" name="deptLvl" value="" /> 
			                     <input type="hidden" id="beforeDeptNm" name="beforeDeptNm" value="" /> 
			                      <div class="t_list">
					                 <table class="iptTblX">
						               <caption>수정</caption>
						               <colgroup>
							             <col width="20%" />
							             <col width="*" />
						               </colgroup>
						               <tbody>
						               		<tr>
						                        <th scope="row">기관명</th>
						                        <td >
<!-- 						                            <input id="insttNm" name="insttNm"  title="기관명"  type="text" style='width:470px' /> -->
													<span id="dispInsttNm"></span>	
						                        </td>
						                    </tr>
<!-- 						                    <tr> -->
<!-- 						                        <th scope="row">상위기관명</th> -->
<!-- 						                        <td> -->
<!-- 						                            <input id="deptNm" name="deptNm"  title="상위기관명"  type="text" style='width:470px' /> -->
<!-- 						                            <input type="hidden" id="upInsttCd"   name="upInsttCd"   value="0000000001"/> -->
<!-- 						                        </td> -->
<!-- 						                    </tr> -->
						                    <tr>
						                        <th scope="row" id ="thDept">과명</th>
						                        <td>
						                            <input id="deptNm" name="deptNm"  title="과명"  type="text" style='width:470px' />
<!-- 													<span id="dispDeptNm"></span>	 -->
						                        </td>
						                    </tr>
						                    <tr>
						                        <th scope="row" id ="thTeam">팀명</th>
						                        <td>
						                            <input id="teamNm" name="teamNm"  title="팀명"  type="text" maxlength="50" style='width:470px' />
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
<%-- 			                        <li><a href="#" class="myButton" onclick="fn_updFsysDeptUDtl('<c:out value="${paramMap.insttCd}" />');return false;">수정</a></li> --%>
									<li><a href="#" class="myButton" onclick="fn_updFsysDeptUDtl();return false;">수정</a></li>
			                        <li><a href="#" class="myButton" onclick="fn_delFsysCodeUDtl();return false;">삭제</a></li>
			                        <li><a href="#" class="myButton" onclick="fn_dialogClose('fsysDeptUDtlPop');return false;">닫기</a></li>
			                        <!-- <li><a href="#" class="myButton" onclick="fn_indexFsysProgramMList();return false;">목록</a></li>  fn_dialogClose-->
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