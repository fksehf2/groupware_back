<%--
/**
 * <pre>
 * 1. 작성일 : 2021. 7. 14.
 * 2. 작성자 : sjw7240
 * 3. 화면명 : 장비관리 조회
 * 4. 설명 : 장비관리 조회
 * </pre>
 */
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>

<html>
<head>
<%@ include file="/WEB-INF/views/frame/fcom/jimsCommon.jsp"%>

<script type="text/javaScript" language="javascript">
var tabId;

$(document).ready(function() {
	tabId = parent.$('li[id*=li_tabs-M][aria-expanded=true]').attr('aria-controls');
	
	var codeInfo2 = [{cdId:'C05',selectId:'schEqpTyp',type:'1', callbackNm:'fn_ajaxEqpTypCallback', sqlQueryId:''}];
	gfn_init({startFnNm:'', param:codeInfo2, codeSet:'Y'});
	
	gfn_calendarConfig("schStPurcDt", "schEdPurcDt", "minDate", "");    <%/* 도입일자 from */%>
	gfn_calendarConfig("schEdPurcDt", "schStPurcDt", "maxDate", "");   <%/* 도입일자 to */%>
	
	gfn_calendarConfig("schStExprDt", "schEdExprDt", "minDate", "");    <%/* 만료일자 from */%>
	gfn_calendarConfig("schEdExprDt", "schStExprDt", "maxDate", "");   <%/* 만료일자 to */%>
	
	fn_searchList(1);
	fn_dispCont();
});


function fn_ajaxEqpTypCallback(data){
	$("select[name='schEqpTyp']").prepend('<option value="" selected>전체</option>');
	requestUtil.getSearchForm({targetFormId:"searchForm", callbackNm:"fn_searchList"});
}

function fn_callBack(){
}

function fn_queryCodeList(param) {
	$.each(param.paramRow,function(idx,row){
        $("#"+row.target).append("<option value=''>전체</option>");
    });
	
	$.ajax({
		url:  "<c:url value='/fcom/queryAjaxCodeList.do'/>",
		type : "POST",
		data: JSON.stringify(param),
		dataType: 'json',
		contentType:"application/json",
		success: function(data) {
		    $.each(data, function(idx, dataRow) {
		    	$.each(dataRow, function(idx, rowRow) {
			    	$("#"+rowRow.target).append("<option value='"+rowRow.cd+"'>"+rowRow.cdNm+"</option>");
			    });
		    });
		    
		}
	});
}

function fn_searchList(page){
	var callUrl = "<c:url value='/eqp/mgmt/queryEqpMgmtMList.do'/>";
	$("#page").val(page);
	requestUtil.searchList({callUrl:callUrl, srhFormNm:'searchForm', callbackNm:'fn_searchListCallback', page:page, perPageNum:10});
	
}

 function fn_searchListCallback(data){
	var list = data.eqpMgmtMList;
	var listCnt = list.length;
	var tabTdCnt = $("#listTab > colgroup").find("col").length;
	
	$("#listTab > tbody").empty();
	
 	$("#totalcnt").text(data.totalCount);
	
  	if(data.eqpMgmtMList.length < 1){
  		var append = "";
		append += "<tr>";
		append += "<td colspan='"+tabTdCnt+"'>"+nullListMsg+"</td>";
		append += "</tr>";
		$("#listTab > tbody").append(append);
	}else{
		
		$.each(list,function(idx,row){
	 		var append = "";
	 		append += "<tr>";
			
	 		append += "<td>" + gfn_nullRtnSpace(row.eqpSno) + "</td>";
	 		append += "<td><a href='javascript:void(0)' onclick=javascript:fn_searchDetail('"+row.eqpSno+"')><u>"+gfn_nullRtnSpace(row.eqpNm)+"</u></a></td>";
	 		append += "<td>" + gfn_nullRtnSpace(row.eqpTypNm) + "</td>";
	 		append += "<td>" + gfn_nullRtnSpace(row.mnftCo) + "</td>";
	 		append += "<td>" + gfn_nullRtnSpace(row.mdlNm) + "</td>";
	 		append += "<td>" + gfn_nullRtnSpace(row.srNo) + "</td>";
	 		//append += "<td>" + gfn_dashDate(gfn_nullRtnSpace(row.purcDt),'-') + "</td>";
	 		append += "<td>" + gfn_nullRtnSpace(row.remarks) + "</td>";
	 		append += "<td>" + gfn_nullRtnSpace(row.userNm) + "</td>";
	 		
	 		append += "</tr>";
	        $("#listTab > tbody").append(append);
	  		
	  	});
	}
  	
  	data.__callFuncName__ ="fn_searchList";
	data.__naviID__ ="page_navi";
	pageUtil.setPageNavi(data);
	
	
 } 
 
function fn_insEqpMgmt(){
	
	requestUtil.setSearchForm("searchForm");
	parent.$('#'+tabId+' iframe').attr('src', '<c:url value="/eqp/mgmt/indexEqpMgmtRDtl.do"/>');
}


function fn_searchDetail(eqpSno){
	
	requestUtil.setSearchForm("searchForm");
	parent.$('#'+tabId+' iframe').attr('src', '<c:url value="/eqp/mgmt/indexEqpMgmtUDtl.do"/>?eqpSno='+eqpSno);
	 
}

function fn_insMntInfo(eqpSno){
	var flag = "MgmtList"
	
	requestUtil.setSearchForm("searchForm");
	parent.$('#'+tabId+' iframe').attr('src', '<c:url value="/eqp/mnt/indexEqpMntRDtl.do"/>?flag='+flag+'&eqpSno='+eqpSno);
		 
}
 
function fn_modifyMntInfo(eqpSno){
	var flag = "MgmtList"
	
	requestUtil.setSearchForm("searchForm");
	parent.$('#'+tabId+' iframe').attr('src', '<c:url value="/eqp/mnt/indexEqpMntUDtl.do"/>?flag='+flag+'&eqpSno='+eqpSno);
		 
} 

function fn_updExcEqpMgmtMList() {
 	if($("#excelFile").val() == ""){
 		fn_showUserPage("엑셀파일을 선택해주세요.", function() {
 		});
 		return false;
 	}
 	requestUtil.updExcMList({
 		fileNm:"excelFile"												// file 태그 id
 		, sheetNum:0													// 시트번호
 		, strartRowNum:1												// 읽어드릴 행(0부터 시작) 
 		, startCelNum:0													// 읽어드릴 셀(0부터 시작)
 		, celCnt:15														// 총 셀 갯수
 		, culmnNmArr:"eqpSno,eqpNm,eqpTyp,eqpBuyDiv,purcDt,mnftCo,mdlNm,unitAmt,cpu,ram,hddVol,ssdVol,graphics,mntrSize,mntrRes,remarks"	// 셀 컬럼
 		, sqlQueryId:"eqpMgmtDAO.updExcEqpMgmtMList"				// insert 쿼리 id
 		, callbackNm: "updExcMListCallback"								// 콜백함수
 	});
} 

function updExcMListCallback() {
   fn_searchList(1);
}
 
 function fn_dispCont(){
	
	$("#btn_findExcel").hide(); //파일찾기
	$("#btn_excelUpload").hide(); //엑셀업로드
	$("#btn_excelDown").hide(); //엑셀양식다운로드
	//$("#btn_insMgmt").hide(); //등록
	
	if(session_usergb == "C01999"){ //수사관
		$("#btn_findExcel").show(); //파일찾기
		$("#btn_excelUpload").show(); //엑셀업로드
		$("#btn_excelDown").show(); //엑셀양식다운로드
		$("#btn_insMgmt").show(); //등록
	}
 	
 }  
</script>
</head>
<body>

	<div id="con_wrap">
		<div id="contents_info">
			<!--- contnets  적용 ------>
			<div>
				<div class="loca">
					<div class="ttl">장비 관리</div>
					<div class="loca_list">Home > 장비 지원 관리 > 장비 관리</div>
				</div>

				<div class="sub">
					<!--------------검색------------------>
					<form id="searchForm" name="searchForm" onsubmit="return false;">
						<input type="hidden" class="" id="page" name="page" value="1" />
						<div class="t_head">
							<input type="hidden" id="boardKind" class="b_put"
								name="boardKind" value="C23001" /> <input type="hidden"
								id="userGb" name="userGb" value="C00000" />

							<table class="tbl_type_hd" border="1" cellspacing="0"
								onkeydown="if(gfn_enterChk())fn_searchList(1);">
								<caption>검색</caption>
								<colgroup>
									<col width="16.66%">
									<col width="33.33%">
									<col width="16.66%">
									<col width="33.33%">
								</colgroup>
								<thead>
									<tr>
										<th scope="col" class="hcolor">장비번호</th>
										<td scope="col"><input class="b_put" type="text"
											name="schEqpSno" id="schEqpSno" style="width: 300px;"
											maxlength="10" /></td>
										<th scope="col" class="hcolor">장비명</th>
										<td scope="col"><input class="b_put" type="text"
											name="schEqpNm" id="schEqpNm" style="width: 300px;"
											maxlength="100" /></td>
									</tr>
									<tr>
										<th scope="col" class="hcolor">장비유형</th>
										<td scope="col"><select class="" id="schEqpTyp"
											name="schEqpTyp" onchange="fn_searchList(1)"
											style="width: 80px;"" >
										</select></td>
										<th scope="col" class="hcolor">도입일자</th>
										<td scope="col"><input id="schStPurcDt"
											name="schStPurcDt" title="도입시작일자" type="text" value=""
											style='width: 100px' maxlength="10" /> <input
											id="schEdPurcDt" name="schEdPurcDt" title="도입종료일자"
											type="text" value="" style='width: 100px' maxlength="10" /></td>
									</tr>
								</thead>
							</table>

						</div>
						<div class="btn_c">
							<ul>
								<c:if test="${loginVO.userGb == 'C01999'}">
									<li><div id="btn_findExcel" name="btn_findExcel"
											style="display: none;">
											<input name="excelFile" id="excelFile" type="file">
										</div></li>
									<li><a href="javascript:void(0);"
										onclick="fn_updExcEqpMgmtMList();return false;"
										class="myButton" id="btn_excelUpload" name="btn_excelUpload"
										style="display: none;">엑셀업로드</a></li>
									<li><a
										href="<c:url value='/resources/sample/장비엑셀업로드.xls'/>" download
										class="myButton" id="btn_excelDown" name="btn_excelDown"
										style="display: none;">엑셀양식다운로드</a></li>
								</c:if>
									<li><a href="javascript:void(0);" class='RdButton'
										onclick="fn_insEqpMgmt(); return false;" id="btn_insMgmt"
										name="btn_insMgmt" >등록</a></li>
								<li><a href="javascript:void(0);" class='gyButton'
									onclick="fn_searchList(1); return false;">조회</a></li>
							</ul>
						</div>
					</form>

					<!--------------//검색------------------>

					<!--------------결과------------------>
					<div class="r_num">
						| 결과 <strong id="totalcnt" style="color: #C00"></strong>건
					</div>

					<!--------------목록---------------------->
					<div class="t_list"
						style="OVERFLOW-Y: auto; overflow-x: hidden; width: 100%; height: 450px;">
						<table id="listTab" class="tbl_type" border="1" cellspacing="0">
							<caption>공지사항관리</caption>
							<colgroup>
								<col width="16%">
								<col width="12%">
								<col width="12%">
								<col width="12%">
								<col width="12%">
								<col width="12%">
								<col width="12%">
								<col width="12%">
							</colgroup>
							<thead>
								<tr>
									<th scope="col">장비번호</th>
									<th scope="col">장비명</th>
									<th scope="col">장비유형</th>
									<th scope="col">제조사</th>
									<th scope="col">모델명</th>
									<th scope="col">S/N</th>
									<th scope="col">비고</th>
									<th scope="col">장비사용자</th>
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
	<script type="text/javaScript">
		var tabId = parent.$('li[id*=li_tabs-M][aria-expanded=true]').attr('aria-controls');
		var ifa = $(top.document).find('div#'+tabId+' > p > iframe');
		ifa.attr('height', $("#contents_info").height()+120);
		//$(top.document).find("#container").width($(top.document).find("#container").width() - 5)
	</script>

</body>
</html>