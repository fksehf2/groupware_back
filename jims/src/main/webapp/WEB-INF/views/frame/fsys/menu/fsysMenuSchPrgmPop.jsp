<%--
/**
 * <pre>
 * 1. 작성일 : 2021. 4. 14. 오전 10:11:33
 * 2. 작성자 : jij
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
<title>Jenix 통합 관리 시스템</title>

<script type="text/javaScript" language="javascript" defer="defer">

$(document).ready(function() {
	gfn_init({startFnNm:'fn_queryMList', param:1, codeSet:'N'});
});

/**
 * @!@ 프로그램 관리 리스트 조회
 * @param {int} page
 * @returns 
 */
function fn_queryMList(page){
	
	var callUrl = "<c:url value='/fsys/program/queryFsysProgramMList.do'/>";
	
	requestUtil.searchList({callUrl:callUrl, srhFormNm:'searchForm', callbackNm:'fn_queryMListCallback', page:page, perPageNum:10});
	
}

/**
 * @!@ 프로그램 관리 리스트 조회 콜백
 * @param {json} data
 * @returns 
 */
function fn_queryMListCallback(data){
	
	var list = data.list;
	var listCnt = list.length;
	var tabTdCnt = $("#listTab > colgroup").find("col").length;
	
	$("#listTab > tbody").empty();
	
	if(listCnt == 0){
		var append = "";
		append += "<tr>";
		
		append += "<td colspan='"+tabTdCnt+"'>"+nullListMsg+"</td>";
		
		append += "</tr>";
		$("#listTab > tbody").append(append);
	}else{
		$.each(list,function(idx,row){
			var append = "";
			append += "<tr>";
	
			append += "<td>" + row.programExpl + "</td>";
			append += "<td>" + row.programId + "</td>";
			append += "<td>" + row.url + "</td>";
			append += "<td><input type='button' class='byButton' value='선택' onclick=javascript:fn_choose('"+ row.programId +"') /></td>";
	
			append += "</tr>";
	        $("#listTab > tbody").append(append);
	 	});
	}
	
	
	data.__callFuncName__ ="fn_queryMList";
	data.__naviID__ ="page_navi";
	pageUtil.setPageNavi(data);
	$("#totalCount").text(data.totalCount);
	
}

/**
 * @!@ 선택 프로그램 ID 입력
 * @param {string} prgID
 * @returns 
 */
function fn_choose(prgID) {
	
	$("#programId").val(prgID);
	$("#divPrgPopup").dialog('close');
	
}

</script>

</head>
<body>
<div id="con_wrap_pop">
	<div class="contents">
               <div id="contents_info">
                      <!--- contnets  적용 ------>
                      <div class="window_popup">
                          <div><h2>프로그램 찾기</h2></div>
                         
                          <div class="sub">
                             <!--------------검색------------------>
                             <form name="searchForm" id="searchForm" method="post">
                             <input type="hidden" id="srcUseYn" name="srcUseYn" value="Y"/>
                            <div class="t_head">
                                  <table class="tbl_type_hd" border="1" cellspacing="0" onkeydown="if(gfn_enterChk())fn_queryMList(1);">
                                        <caption>검색</caption>
                                         <colgroup>
                                            <col width="20%">
                                            <col width="30%">
                                            <col width="20%">
                                            <col width="30%">
                                         </colgroup>
                                   <thead>
                                      <tr>
                                         <th scope="col" class="hcolor">프로그램명</th>
                                         <th scope="col">
                                         	<input type="text" id="srcProgramExpl" name="srcProgramExpl" maxlength="200" class="inpw70" tilte="프로그램명"/>
                                         </th>
                                         <th scope="col" class="hcolor">프로그램ID</th>
                                         <th scope="col">
                                         	<input type="text" id="srcProgramId" name="srcProgramId" maxlength="200" class="inpw70" title="프로그램 ID"/>
                                         </th>
                                       </tr>
                                   </thead>
                                </table>
                              </div>
                            <div class="btn_c">
                                  <ul>
                                     <li><a href="javascript:fn_queryMList(1);" class="gyButton">조회</a></li>
                                  </ul>
                               </div>
                               </form>
                            <!--------------//검색------------------>
                            
                            <!--------------결과------------------>
                             <div class="r_num">| 결과  <strong style="color:#C00" id="totalCount">0</strong>건</div>
                             
                             <!--------------목록---------------------->
                             <div class="t_list">  
                                  <table id="listTab" class="tbl_type" border="1" cellspacing="0" >
                                        <caption>목록</caption>
                                          <colgroup>
                                              <col width="25%">
                                              <col width="25%">
                                              <col width="25%">
                                              <col width="25%">
                                              </colgroup>
                                            <thead>
                                              <tr>
                                                 <th scope="col">프로그램명</th>
                                                 <th scope="col">프로그램ID</th>
                                                 <th scope="col">URL</th>
                                                 <th scope="col">선택</th>
                                              </tr>
                                            </thead>
                                            <tbody>
												<tr><td colspan="4">조회 결과가 없습니다.</td></tr>
                                          </tbody>
                                     </table>
                             </div>
                              <!--------------//목록---------------------->
                             
                             <!-----------------------페이징----------------------->
                             <div id="page_navi" class="page_wrap"></div>
                               <!-----------------------//페이징----------------------->
                          
                          		<div class="btn_c">
			                      <ul>
			                        <li><a href="javascript:void(0);" class="myButton" onclick="fn_dialogClose('divPrgPopup');return false;">닫기</a></li>
			                        <!-- <li><a href="javascript:void(0);" class="myButton" onclick="fn_indexFsysProgramMList();return false;">목록</a></li> -->
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