<%--
/**
 * <pre>
 * 1. 작성일 : 2021. 4. 14. 오전 10:11:33
 * 2. 작성자 : ilyong
 * 3. 화면명 : 사업관리 조회 팝업
 * 4. 설명 : 사업관리 조회 팝업
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
var paramInsttCdNm2 = "${paramMap.paramInsttCdNm2}";

//alert("======paramInsttCdNm2======>>>"+paramInsttCdNm2); //searchInsttCdNm

$(document).ready(function() {
	fn_searchFsysUserListPop(1);	
});

/**
 * @!@ 프로그램 관리 리스트 조회
 * @param {int} page
 * @returns 
 */
function fn_searchFsysUserListPop(page){
	
	var callUrl = "<c:url value='/fsys/user/queryFsysUserFindMListPop.do'/>";
	
	requestUtil.searchList({callUrl:callUrl, srhFormNm:'searchUfmForm', callbackNm:'fn_searchFsysUserListPopCallback', page:page, perPageNum:10});
	
}

/**
 * @!@ 프로그램 관리 리스트 조회 콜백
 * @param {json} data
 * @returns 
 */
 /**
  * @!@ 프로그램 관리 리스트 조회 콜백
  * @param {json} data
  * @returns 
  */
 function fn_searchFsysUserListPopCallback(data){
	//gfn_getTelNo("12345");
 	var list = data.list;
 	var listCnt = list.length;
 	var tabTdCnt = $("#ufmlistTab3 > colgroup").find("col").length;
 	
 	$("#ufmlistTab3 > tbody").empty();
 	
 	//alert("=====totalCount======>>>>"+data.totalCount);
 	
 	$("#ufmTotalcnt").text(data.totalCount);
 	
 	if(listCnt == 0){
 		var append = "";
 		append += "<tr>";
 		
 		append += "<td colspan='"+tabTdCnt+"'>"+nullListMsg+"</td>";
 		
 		append += "</tr>";
 		$("#ufmlistTab3 > tbody").append(append);
 	}else{
 		$.each(list,function(idx,row){
 			var append = ""; //gfn_getTelNo
 			var telNo = "";
 			var hpTelNo = "";
 			
 			append += "<tr>";
 	
 			append += "<td>" + row.rnum + "</td>";
 	 		append += "<td>" + row.userNm + "</td>";
 	 		//append += "<td><a href='javascript:void(0)' onclick=javascript:fn_searchDetail('"+row.userId+"')><u>"+row.userNm+"</u></a></td>";
 	 		append += "<td>"+row.userId+"</td>";
 	 		append += "<td>" + gfn_nullRtnSpace(row.insttCdNm) + "</td>";
 	 		append += "<td>" + gfn_nullRtnSpace(gfn_getTelNo(row.telNo)) +"<br>"+gfn_nullRtnSpace(gfn_getTelNo(row.hpTelNo))+"</td>";
//  	 		append += "<td>" + gfn_nullRtnSpace(row.regStatusNm) + "</td>";
 	 		append += "<td>" + gfn_nullRtnSpace(row.useYnNm) + "</td>";
//  	 		append += "<td>" + gfn_dashDate2(gfn_nullRtnSpace(row.bizEndDt),'-') + "</td>";
 	 		append += "<td><a href='javascript:fn_setBeforeChk(\""+gfn_nullRtnSpace(row.userId)+"\", \""+gfn_nullRtnSpace(row.userNm)+"\", \""+gfn_nullRtnSpace(row.insttCd)+"\", \""+gfn_nullRtnSpace(row.insttCdNm)+"\");' class='byButton'>선택</a></td>";
//  	 		append += "<td>" + "선택" + "</td>";
 	
 			append += "</tr>";
 	        $("#ufmlistTab3 > tbody").append(append);
 	 	});
 	}
 	
//  	<th scope="col">순번</th>
//     <th scope="col">이름</th>
//     <th scope="col">ID</th>
//     <th scope="col">부서명</th>
//     <th scope="col">대표전화번호<br>(핸드폰번호)</th>
//     <th scope="col">가입상태</th>
//     <th scope="col">사용여부</th>
//     <th scope="col">선택</th>
    
 	data.__callFuncName__ ="fn_searchFsysUserListPop";
 	data.__naviID__ ="page_navi_ufm";
 	pageUtil.setPageNavi(data);
 	
 	
 }

 /**
  * 사용자 선택
  * @param
  * @returns 
  */
 function fn_setFsysUser(userId, userNm, insttCd, insttCdNm){
	 //if(!fn_setBeforeChk(userId, userNm, insttCd, insttCdNm))
 	 $("#userId").val(userId);
 	 $("#userNm").val(userNm);
	 fn_dialogClose('fsysUserFindMListPop');
 	
 }
 
 function gfn_getTelNo(that){
		
		var returnValue = that; 
		
		if(that.length < 5){
			returnValue = that; 
		}else{
			returnValue = that.replace(/[^0-9]/g, "").replace(/(^02|^0505|^1[0-9]{3}|^0[0-9]{2})([0-9]+)?([0-9]{4})$/,"$1-$2-$3").replace("--", "-"); 
		}
		
		return returnValue;
}

 /**
  * 소속된 부서가 있는 경우 유효성 체크
  * @param
  * @returns 
  */
function fn_setBeforeChk(userId, userNm, insttCd, insttCdNm){
	 	
// 	var userNm = $("#userNm").val(); //사용자ID(사용자명)
//  	var aprvDt = $("#aprvDt").val(); //승인일자
//  	var mainCrgrYn = $("#mainCrgrYn").val(); //주담당자 여부
//  	var aprvAuthYn = $("#aprvAuthYn").val(); //승인권자여부
	var msg = ""; 
	var result = false;
	if(!gfn_isNull(insttCd)){
		msg = "선택한 사용자는("+userNm+") "+insttCdNm+"에 이미 등록된 사용자입니다.\n"+paramInsttCdNm2+"에 등록하시겠습니까?\n 선택한 사용자는 "+insttCdNm+"에서 제외됩니다.";
		
		fn_showModalPage(msg, function() {
			$("#userId").val(userId);
		 	$("#userNm").val(userNm);
		 	$("#pBeforeInsInsttCd").val(insttCd);
			fn_dialogClose('fsysUserFindMListPop');
		});
	}else{
		$("#userId").val(userId);
	 	$("#userNm").val(userNm);
	 	$("#pBeforeInsInsttCd").val("");
		fn_dialogClose('fsysUserFindMListPop');
	}
	
 	
	 
 	
 	
 	
//  	if(userNm.length < 1){	
// 		fn_showUserPage("사용자를 선택하세요.", function() {
// 			$("#userNm").focus();
// 		});
// 		return false;  
// 	}else if(aprvDt.length < 1){	
// 		fn_showUserPage("승인일자를 입력하세요.", function() {
// 			$("#uprDept").focus();
// 		});
// 		return false;  
// 	}else if(gfn_isNull(mainCrgrYn) && gfn_isNull(aprvAuthYn)){
// 		fn_showUserPage("주담당자여부 또는 승인권자여부를 선택하세요.", function() {
// 			$("#mainCrgrYn").focus();
// 		});
// 		return false;
// 	}
// 	return true; 
 	
} 
 
</script>

</head>
<body>
<div id="con_wrap_pop">
	<div class="contents">
               <div id="contents_info">
                      <!--- contnets  적용 ------>
                      <div class="window_popup">
                          <div class="sub_ttl">사용자 찾기</div>
                         
                          <div class="sub">
                             <!--------------검색------------------>
                             <form name="searchUfmForm" id="searchUfmForm"  method="post" onsubmit="return false;">
                             <input type="hidden" id="srcUseYn" name="srcUseYn" value="Y"/>
                             <input type="hidden" id="paramInsttCdNm2" name="paramInsttCdNm2" value="<c:out value="${paramMap.paramInsttCdNm2}" />" />
                            <div class="t_head">
                                  <table class="tbl_type_hd" border="1" cellspacing="0" onkeydown="if(gfn_enterChk())fn_searchFsysUserListPop(1);">
                                        <caption>검색</caption>
                                         <colgroup>
                                            <col width="20%">
                                            <col width="30%">
                                            <col width="20%">
                                            <col width="30%">
                                         </colgroup>
                                   <thead>
                                      <tr>
                                           <th scope="row" class="hcolor">사용자ID</th>
								           <td>
								               <input type="text" id="schUserId" name="schUserId" title="사용자ID" class="inpw70" maxlength="20"/>
								           </td>
								           <th scope="row" class="hcolor">사용자명</th>
								           <td colspan="3">
								           		<input type="text" id="schUserNm" name="schUserNm" title="사용자명" class="inpw70" maxlength="25"/>
								           </td>
                                       </tr>
                                   </thead>
                                </table>
                              </div>
                            <div class="btn_c">
                                  <ul>
                                     <li><a href="javascript:fn_searchFsysUserListPop(1);" class="myButton">조회</a></li>
                                  </ul>
                               </div>
                               </form>
                            <!--------------//검색------------------>
                            
                            <!--------------결과------------------>
                             <div class="r_num">| 결과  <strong id="ufmTotalcnt" style="color:#C00"></strong>건</div>      
                             
                             <!--------------목록---------------------->
                             <div class="t_list">  
                                  <table id="ufmlistTab3" class="tbl_type" border="1" cellspacing="0" >
                                        <caption>목록</caption>
                                          <colgroup>
                                              <col width="7%">
                                              <col width="13%">
                                              <col />
                                              <col width="16%">
                                              <col width="11%">
<%--                                               <col width="11%"> --%>
                                              <col width="12%">
                                              <col width="11%">
                                           </colgroup>
                                            <thead>
                                              <tr>
                                                 <th scope="col">순번</th>
                                                 <th scope="col">이름</th>
                                                 <th scope="col">ID</th>
                                                 <th scope="col">부서명</th>
                                                 <th scope="col">대표전화번호<br>(핸드폰번호)</th>
<!--                                                  <th scope="col">가입상태</th> -->
                                                 <th scope="col">사용여부</th>
                                                 <th scope="col">선택</th>
                                              </tr>
                                            </thead>
                                            <tbody>
												<tr><td colspan="7">조회 결과가 없습니다.</td></tr>
                                          </tbody>
                                     </table>
                             </div>
                              <!--------------//목록---------------------->
                             
                             <!-----------------------페이징----------------------->
                             <div id="page_navi_ufm" class="page_wrap"></div>
                               <!-----------------------//페이징----------------------->
                          
                          		<div class="btn_c">
			                      <ul>
			                        <li><a href="#" class="myButton" onclick="fn_dialogClose('fsysUserFindMListPop');return false;">닫기</a></li>
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