<%--
/**
 * <pre>
 * 1. 작성일 : 2021. 7. 19. 오전 10:49:11
 * 2. 작성자 : ilyong
 * 3. 화면명 : 장비 사용자 관리 조회
 * 4. 설명 : 장비 사용자 관리 조회
 * </pre>
 */
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>

<html>
<head>
<%@ include file="/WEB-INF/views/frame/fcom/jimsCommon.jsp" %>

<%-- <link rel="stylesheet" href="<c:url value="/css/com/bootstrap.css" />"> --%>
<script type="text/javaScript" language="javascript">
// var flag = true;
// var perPageNum;
var tabId;

$(document).ready(function() {
	tabId = parent.$('li[id*=li_tabs-M][aria-expanded=true]').attr('aria-controls');
	
	//var codeInfo2 = [{cdId:'C05',selectId:'schEqpTyp',type:'1', callbackNm:'fn_ajaxEqpTypCallback', sqlQueryId:''}];
	var codeInfo2 = [{cdId:'C05',selectId:'schEqpTyp',type:'1', callbackNm:'fn_ajaxEqpTypCallback', sqlQueryId:'sysCrimeCodeDAO.querySysCrimeGrpCodeMList'},{cdId:'C03',selectId:'schPgsStat' ,type:'1'}];
	// fn_ajaxCodeList(codeInfo2);
	gfn_init({startFnNm:'', param:codeInfo2, codeSet:'Y'});
	
	gfn_calendarConfig("schStPurcDt", "schEdPurcDt", "minDate", "");    <%/* 도입일자 from */%>
	gfn_calendarConfig("schEdPurcDt", "schStPurcDt", "maxDate", "");   <%/* 도입일자 to */%>
	
	gfn_calendarConfig("schStExprDt", "schEdExprDt", "minDate", "");    <%/* 만료일자 from */%>
	gfn_calendarConfig("schEdExprDt", "schStExprDt", "maxDate", "");   <%/* 만료일자 to */%>
	
	fn_searchList(1);
	//fn_dispCont();
});


function fn_ajaxEqpTypCallback(data){
// 	$('#schEqpTyp option:eq(0)').before("<option value='' selected>전체</option>");
	$("select[name='schEqpTyp']").prepend('<option value="" selected>전체</option>');
	$("select[name='schPgsStat']").prepend('<option value="" selected>전체</option>');
	requestUtil.getSearchForm({targetFormId:"searchForm", callbackNm:"fn_searchList"});
}

/**
 * 달력 콜백함수(없으면 삭제가능) 
 * @param {string} prgID
 * @returns 
 */
function fn_callBack(){
}





/**
 * @!@ 사용자 관리 리스트 조회
 * @param {int} page
 * @returns 
 */
function fn_searchList(page){
// 	debugger;
	var callUrl = "<c:url value='/eqp/user/queryEqpUserMList.do'/>";
	$("#page").val(page);
	requestUtil.searchList({callUrl:callUrl, srhFormNm:'searchForm', callbackNm:'fn_searchListCallback', page:page, perPageNum:10});
	
// 	var callUrl = "<c:url value='/fbbs/ntc/queryFBbsNtcMList.do'/>";	
// 	requestUtil.searchList({callUrl:callUrl,srhFormNm:'searchForm',callbackNm:'fn_callback',page:page,perPageNum:10});
	
}

 /**
  * 장비관리 리스트 조회 콜백
  * @param {json} data
  * @returns 
  */
 function fn_searchListCallback(data){
//  	debugger;
	var list = data.eqpUserMList;
	var listCnt = list.length;
	var tabTdCnt = $("#listTab > colgroup").find("col").length;
	
	$("#listTab > tbody").empty();
	
 	$("#totalcnt").text(data.totalCount);
	
  	if(data.eqpUserMList.length < 1){
  		var append = "";
		append += "<tr>";
		append += "<td colspan='"+tabTdCnt+"'>"+nullListMsg+"</td>";
		append += "</tr>";
		$("#listTab > tbody").append(append);
	}else{
		
		$.each(list,function(idx,row){
	 		var append = "";
	 		append += "<tr>";
			
	 		append += "<td>" + row.eqpUserSno + "</td>";
	 		append += "<td><a href='javascript:void(0)' onclick=javascript:fn_searchDetail('"+row.eqpSno+"','"+row.eqpUserSno+"')><u>"+gfn_nullRtnSpace(row.eqpUserNm)+"</u></a></td>";
	 		append += "<td>" + gfn_nullRtnSpace(row.eqpNm) + "</td>";
	 		append += "<td>" + gfn_nullRtnSpace(row.eqpTypNm) + "</td>";
	 		append += "<td>" + gfn_nullRtnSpace(row.mnftCo) + "</td>";
	 		append += "<td>" + gfn_nullRtnSpace(row.mdlNm) + "</td>";
	 		append += "<td>" + gfn_dashDate(gfn_nullRtnSpace(row.rcvDt),'-') + "</td>";
	 		append += "<td>" + gfn_dashDate(gfn_nullRtnSpace(row.rtnDt),'-') + "</td>";
	 		append += "<td>" + gfn_nullRtnSpace(row.hldPlc) + "</td>";
	 		append += "<td>" + gfn_nullRtnSpace(row.pgsStatNm) + "</td>";
	 		append += "</tr>";
	        $("#listTab > tbody").append(append);
	  
	  		
	  		
	  	});
	}
  	
  	data.__callFuncName__ ="fn_searchList";
	data.__naviID__ ="page_navi";
	pageUtil.setPageNavi(data);
	
	
 } 

//장비 사용자 등록
function fn_insEqpUser(){

	requestUtil.setSearchForm("searchForm");
	parent.$('#'+tabId+' iframe').attr('src', '<c:url value="/eqp/user/indexEqpUserRDtl.do"/>');
}

//장비 사용자 상세
function fn_searchDetail(eqpSno, eqpUserSno){
	
	requestUtil.setSearchForm("searchForm");
	parent.$('#'+tabId+' iframe').attr('src', '<c:url value="/eqp/user/indexEqpUserUDtl.do"/>?eqpSno='+eqpSno+'&eqpUserSno='+eqpUserSno);
	 
}

/**
 * @!@ 장비조회 팝업
 * @param cd
 * @returns 
 */
function fn_searchEqpUserMListPop() {
   
	var callUrl = "<c:url value = "/com/PageLink.do"/>"

	requestUtil.mdPop({
		//popUrl : callUrl+"?link="+"dems/eqp/mgmt/eqpMgmtFIndEqupQListPop2&paramInsttCd="+$("#searchInsttCd2").val()+"&paramInsttCdNm="+$("#searchInsttCdNm2").val(),
		popUrl : callUrl+"?link="+"jims/eqp/mgmt/eqpMgmtFIndEqupQListPop",
		height: 650,
        width: 1000,
        title: '장비조회 팝업',
        //divId : 'eqpMgmtFIndEqupQListPop2'
        divId : 'eqpMgmtFIndEqupQListPop'
        //divId : 'eqpMgmtMListPop'
	});

}

</script>
</head>
<body>

<div id="con_wrap">
       <div id="contents_info">
              <!--- contnets  적용 ------>
            <div>
            <!-- 
                  <div><h3>공지사항 <c:if test="${loginVO.userGb == 'C01999'}">관리</c:if></h3></div>
             -->
                 <div class="loca">
                  <!--  <h3>공지사항 <c:if test="${loginVO.userGb == 'C01999'}">관리</c:if></h3>//-->
                    <div class="ttl">장비 사용자 관리</div>
                    <div class="loca_list">Home > 장비 지원 관리 > 장비 사용자 관리</div>
                  </div>
                 
                  <div class="sub">
                     <!--------------검색------------------>
<!--                     <form id="searchForm" name="searchForm" method="post"> -->
					 <form id="searchForm" name="searchForm" onsubmit="return false;">	
					<input type="hidden" class="" id="page" name="page" value="1"/>
                    <div class="t_head">
				        <input type="hidden" id="boardKind" class="b_put"  name="boardKind"   value="C23001"/>
				        <input type="hidden" id="userGb"   name="userGb"   value="C00000"/>
				        
                          <table class="tbl_type_hd" border="1" cellspacing="0" onkeydown="if(gfn_enterChk())fn_searchList(1);">
                                <caption>검색</caption>
                                 <colgroup>
                                   <col width="11%">
                                   <col width="22%">
                                   <col width="11%">
                                   <col width="23%">
                                   <col width="11%">
                                   <col width="22%">
                                 </colgroup>
                           <thead>                           
                           	  <tr>
					            <th scope="col" class="hcolor">장비명</th>
					            <td scope="col" >
					               <input class="b_put" type="text" name="schEqpNm" id="schEqpNm" style="width:300px;" maxlength="100"/>
					            </td>
					            <th scope="col" class="hcolor">장비사용자명</th>
					            <td scope="col" >
					               <input class="b_put" type="text" name="schEqpUserNm" id="schEqpUserNm" style="width:300px;" maxlength="25"/>
					            </td>
					            <th scope="col" class="hcolor">장비유형</th>
					            <td scope="col" >
					               <select class="" id="schEqpTyp" name="schEqpTyp" onchange="fn_searchList(1)" style=width:80px;" >
							 			<%-- <option value="" <c:out value="${sysGrpInfo.cd==''?\"selected\":\"\"}"/> >전체</option>
										<c:forEach items="${sysGrpList}" var="sysGrpInfo" varStatus="status">
										<option value="<c:out value="${sysGrpInfo.cd}"/>"><c:out value="${sysGrpInfo.cdNm}"/></option>
										</c:forEach> --%>
								   </select>
					            </td>
					          </tr>
					          <tr>
					          	<th scope="col" class="hcolor">모델명</th>
					          	<td scope="col" >
					               <input class="b_put" type="text" name="schMdlNm" id="schMdlNm" style="width:300px;" maxlength="100"/>
					            </td>
					            <th scope="col" class="hcolor">상태</th>
					          	<td scope="col" colspan='3'>
					               <select class="" id="schPgsStat" name="schPgsStat" onchange="fn_searchList(1)" style=width:80px;" >
							 			<%-- <option value="" <c:out value="${sysGrpInfo.cd==''?\"selected\":\"\"}"/> >전체</option>
										<c:forEach items="${sysGrpList}" var="sysGrpInfo" varStatus="status">
										<option value="<c:out value="${sysGrpInfo.cd}"/>"><c:out value="${sysGrpInfo.cdNm}"/></option>
										</c:forEach> --%>
								   </select>
					            </td>
<!-- 					            <th scope="col" class="hcolor">RFID TAG</th> -->
<!-- 					            <td scope="col" > -->
<!-- 					               <input class="b_put" type="text" name="schRfidTag" id="schRfidTag" style="width:300px;" maxlength="20"/> -->
<!-- 					            </td> -->
					          </tr>    
                           </thead>
                        </table>
                      
                      </div>
                      <div  class="btn_c">
					       <ul>
                             
<%--                                <c:if test="${loginVO.userGb == 'C01999'}"> --%>
							 <li><a href="javascript:void(0);" class='RdButton' onclick="fn_insEqpUser(); return false;" id="btn_insMgmt" name="btn_insMgmt" >등록</a></li>
                             <li><a href="javascript:void(0);" class='gyButton' onclick="fn_searchList(1); return false;">조회</a></li>
<%--                                </c:if> --%>
                             </ul>   
					  </div>
                     </form>
                       
                    <!--------------//검색------------------>
                    
                     <!--------------결과------------------>
<!--                      <div class="r_num">| 결과  <strong id="totalcnt" style="color:#C00"></strong>건</div> -->
<!--                      <div class="bo_num"> -->
<!--                          <select id="perPageNum" name="perPageNum"> -->
<!-- 			               <option value="5">5개씩</option> -->
<!-- 			               <option value="10" selected="selected">10개씩</option>		                -->
<!-- 			             </select> -->
<!--                      </div> -->
					 <div class="r_num">| 결과  <strong id="totalcnt" style="color:#C00"></strong>건</div>
                     
                     <!--------------목록---------------------->
                     <div class="t_list" style="OVERFLOW-Y:auto;overflow-x: hidden;width:100%; height:450px;">  
                          <table id="listTab" class="tbl_type" border="1" cellspacing="0" >
                                <caption>공지사항관리</caption>
                                  <colgroup>
                                      <col width="8%">
                                      <col width="10%">
                                      <col />
                                      <col width="10%">
                                      <col width="10%">
                                      <col width="10%">
                                      <col width="10%">
                                      <col width="10%">
                                      <col width="10%">
                                      <col width="10%">
                                      </colgroup>
                                    <thead>
                                      <tr>
                                         <th scope="col">일련번호</th>
                                         <th scope="col">장비사용자명</th>
                                         <th scope="col">장비명</th>
                                         <th scope="col">장비유형</th>
                                         <th scope="col">제조사</th>
                                         <th scope="col">모델명</th>
                                         <th scope="col">수령일</th>
                                         <th scope="col">반납일</th>
                                         <th scope="col">보유장소</th>
                                         <th scope="col">상태</th>
                                      </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>
                             </table>
                     </div>
                      <!--------------//목록---------------------->
                     
                     <!-----------------------페이징----------------------->
                    <div id="page_navi" class="page_wrap"></div>                   
                       <!-----------------------//페이징----------------------->
                 
                  </div>
                 
            </div>
    </div>
</div>
<div id="eqpMgmtFIndEqupQListPop"></div>
	<script type="text/javaScript">
		var tabId = parent.$('li[id*=li_tabs-M][aria-expanded=true]').attr('aria-controls');
		var ifa = $(top.document).find('div#'+tabId+' > p > iframe');
		ifa.attr('height', $("#contents_info").height()+120);
		//$(top.document).find("#container").width($(top.document).find("#container").width() - 5)
	</script>
</body>
</html>