<%--
/**
 * <pre>
 * 1. 작성일 : 2021. 7. 15.
 * 2. 작성자 : sjw7240
 * 3. 화면명 : 장비관리 > 장비등록
 * 4. 설명 : 장비등록
 * </pre>
 */
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>

<html>
<head>
<%@ include file="/WEB-INF/views/frame/fcom/jimsCommon.jsp"%>


<script type="text/javaScript" language="javascript" defer="defer">
var tabId;

$(document).ready(function() {
	tabId = parent.$('li[id*=li_tabs-M][aria-expanded=true]').attr('aria-controls');
	
	var codeInfo = [{cdId:'C04',selectId:'eqpBuyDiv' ,type:'1', callbackNm:'fn_ajaxEqpBuyDivCallback'},{cdId:'C05',selectId:'eqpTyp' ,type:'1'}];
	fn_ajaxCodeList(codeInfo);
	
	<%/* 달력 세팅 */%> 
	gfn_calendarConfig("purcDt", "", "", "");   <%/* 도입일자 */%>
	gfn_calendarConfig("exprDt", "", "", "");   <%/* 만료일자 */%>
	
	gfn_toNumber("unitAmt"); <%/* 단가 */%>
	gfn_toNumber("purcQty"); <%/* 도입수량 */%>
	
	fn_dispCont();
});

function fn_ajaxEqpBuyDivCallback(data){
	$('#eqpBuyDiv option:eq(0)').before("<option value='' selected>선택</option");
	$('#eqpTyp option:eq(0)').before("<option value='' selected>선택</option");
}

function fn_ajaxEqpTypCallback(data){
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

//장비 등록
function fn_insEqpMgmt(){
	var eqpBuyDiv = $("#eqpBuyDiv").val();	//장비도입구문
	var eqpNm = $("#eqpNm").val(); 			//장비명
	var eqpTyp = $("#eqpTyp").val();		//장비유형
	var srNo = $("#srNo").val();			//시리얼번호
	var purcDt = $("#purcDt").val();		//도입일자
	var exprDt = $("#exprDt").val();		//만료일자
	var guarTrm = $("#guarTrm").val();		//보증기간
	var mnftCo = $("#mnftCo").val();		//제조사
	var mdlNm = $("#mdlNm").val();			//모델명
	var mnftNat = $("#mnftNat").val();		//제조국가
	var deprPrid = $("#deprPrid").val();	//내용연수
	var unitAmt = $("#unitAmt").val();		//단가
	var hldPlc = $("#hldPlc").val();		//보유장소

	var cpu = $("#cpu").val();				//CPU
	var ram = $("#ram").val();				//메모리
	var hddVol = $("#hddVol").val();		//HDD용량
	var ssdVol = $("#ssdVol").val();		//SSD용량
	var graphics = $("#graphics").val();	//그래픽카드

	var mntrSize = $("#mntrSize").val();	//모니터크기
	var mntrRes = $("#mntrRes").val();		//해상도

	var remarks = $("#remarks").val();		//비고
	
	
	if(eqpBuyDiv.length < 1){	
		fn_showUserPage( "장비도입구분을 선택하세요.", function() {
			$("#eqpBuyDiv").focus();
        });
		return;  
	}else if(eqpNm.length < 1){	
		fn_showUserPage( "장비명을 입력하세요.", function() {
			$("#eqpNm").focus();
        });
		return;
	}else if(eqpTyp.length < 1){	
		fn_showUserPage( "장비유형을 선택하세요.", function() {
			$("#eqpTyp").focus();
        });
		return;
	}else if(mdlNm.length < 1){	
		fn_showUserPage( "모델명을 입력하세요.", function() {
			$("#mdlNm").focus();
        });
		return;  
	}else if(hldPlc.length < 1){	
		fn_showUserPage( "보유장소를 입력하세요.", function() {
			$("#hldPlc").focus();
        });
		return;  
	}

	fn_showModalPage("등록 하시겠습니까?", function() {
 		//장비 정보 등록
		var callUrl = "<c:url value='/eqp/mgmt/regEqpMgmtRDtl.do'/>";
        requestUtil.save({callUrl:callUrl,srhFormNm:'insForm',callbackNm:'fn_insEqpMgmtCallback'});
	});
}

function fn_searchList(){
	
	parent.$('#'+tabId+' iframe').attr('src', '<c:url value="/eqp/mgmt/indexEqpMgmtMList.do"/>');
}

function fn_insEqpMgmtCallback(data){
	 fn_searchList();
}


function fn_searchEqpMgmtMListPop() {

	var callUrl = "<c:url value = "/com/PageLink.do"/>"

	requestUtil.mdPop({
		popUrl : callUrl+"?link="+"jims/eqp/mgmt/eqpMgmtFIndEqupQListPop&paramInsttCd="+$("#searchInsttCd2").val()+"&paramInsttCdNm="+$("#searchInsttCdNm2").val(),
		height: 650,
        width: 1000,
        title: '장비조회 팝업',
        divId : 'eqpMgmtFIndEqupQListPop'
	});

}

function fn_dispCont(){
	
	$("#btn_insMgmt").hide();
	
	if(session_usergb == "C01999"){
		$("#btn_insMgmt").show();
	}
 	
}

function fn_eqpTyp() {

	var devEqp = "";
	var moniEqp = "";

	switch ($("#eqpTyp").val()) {
		case "C05001":
		case "C05002":
			$("#devEqp").empty();
			$("#moniEqp").empty();
			devEqp += "<tr>"
					+ "<th scope='row'>CPU</th>"
					+ "<td><input id='cpu' name='cpu' type='text' maxlength='50' /></td>"
					+ "<th scope='row'>메모리</th>"
					+ "<td><input id='ram' name='ram' type='text' maxlength='50' /></td>"
					+ "</tr><tr>"
					+ "<th scope='row'>HDD용량</th>"
					+ "<td><input id='hddVol' name='hddVol' type='text' maxlength='50' /></td>"
					+ "<th scope='row'>SDD용량</th>"
					+ "<td><input id='ssdVol' name='ssdVol' type='text' maxlength='50' /></td>"
					+ "</tr><tr>"
					+ "<th scope='row'>그래픽카드</th>"
					+ "<td colspan='3'><input id='graphics' name='graphics' type='text' maxlength='50' /></td>"
					+ "</tr>"
			$("#devEqp").append(devEqp);
			break;

		case "C05003":
			$("#devEqp").empty();
			$("#moniEqp").empty();
			moniEqp += "<tr>"
					+ "<th scope='row'>모니터크기</th>"
					+ "<td><input id='mntrSize' name='mntrSize' type='text' maxlength='50' /></td>"
					+ "<th scope='row'>해상도</th>"
					+ "<td><input id='mntrRes' name='mntrRes' type='text' maxlength='50' /></td>"
					+ "</tr>"
			$("#moniEqp").append(moniEqp);
			break;
	
		default:
			$("#devEqp").empty();
			$("#moniEqp").empty();
			break;
	}
} 

</script>

</head>

<body>
	<div id="con_wrap">
		<div class="content">
			<!----현재위치----->

			<div id="contents_info">
				<div class="sub_ttl">장비 등록</div>
				<!-----타이틀------>

				<div class="sub">
					<!------------검색------------------->
					<form name="insForm" id="insForm" method="post">

						<input type="hidden" id="menuNo" name="menuNo"
							value="<c:out value="${param.menuNo}" />" /> <input
							type="hidden" id="sysGrp" name="sysGrp"
							value="<c:out value="${param.sysGrp}" />" /> <input
							type="hidden" id="topMenuNo" name="topMenuNo" value="" />

						<div class="t_list">
							<table class="iptTblX">
								<caption>등록</caption>
								<colgroup>
									<col width="15%" />
									<col width="35%" />
									<col width="15%" />
									<col width="35%" />
								</colgroup>
								<tbody>
									<tr>
										<th scope="row">장비도입구분<span class="fontred">*</span></th>
										<td><select class="" id="eqpBuyDiv" name="eqpBuyDiv"
											onchange="" style="width: 80px;" " data-requireNm="장비도입구분"
											data-maxLength="6" title="장비도입구분" maxlength="3">
												<option value="001">구입</option>
										</select></td>
										<th scope="row">장비명<span class="fontred">*</span></th>
										<td><input id="eqpNm" name="eqpNm" type="text" value=""
											maxlength="100" class="inpw40" data-requireNm="장비명"
											data-maxLength="200" title="장비명" /></td>
									</tr>
									<tr>
										<th scope="row">장비유형<span class="fontred">*</span></th>
										<td><select class="" id="eqpTyp" name="eqpTyp"
											onchange="fn_eqpTyp(this);" style="width: 80px;" data-requireNm="장비유형"
											data-maxLength="6" title="장비유형" maxlength="3">
										</select></td>
										<th scope="row">시리얼번호</th>
										<td><input id="srNo" name="srNo" type="text" value=""
											maxlength="25" data-requireNm="시리얼번호" data-maxLength="50"
											title="시리얼번호" class="inpw40" /></td>
									</tr>
									<tr>
										<th scope="row">도입일자</th>
										<td><input id="purcDt" name="purcDt" type="text"
											value="" maxlength="4" data-requireNm="도입일자"
											data-maxLength="8" title="도입일자" /></td>
										<th scope="row">만료일자</th>
										<td><input id="exprDt" name="exprDt" type="text"
											value="" maxlength="4" data-requireNm="만료일자"
											data-maxLength="8" title="만료일자" /></td>
									</tr>
									<tr>
										<th scope="row">보증기간</th>
										<td><input id="guarTrm" name="guarTrm" type="text"
											value="" maxlength="4" class="inpw40" /></td>
										<th scope="row">제조사</th>
										<td><input id="mnftCo" name="mnftCo" type="text"
											value="" maxlength="100" class="inpw40" /> &nbsp;</td>
									</tr>
									<tr>
										<th scope="row">모델명<span class="fontred">*</span></th>
										<td>
											<!-- 				                 	<input id="mdlNm" name="mdlNm" type="text" value=""  maxlength="100" data-requireNm="모델명" data-maxLength="200" title="모델명" class="inpw40"/> -->
											<input id="mdlNm" name="mdlNm" type="text" value=""
											maxlength="100" title="모델명" class="inpw40" />
										</td>
										<th scope="row">제조국가</th>
										<td><input id="mnftNat" name="mnftNat" type="text"
											value="" maxlength="100" class="inpw40" /> &nbsp;</td>
									</tr>
									<tr>
										<th scope="row">내용년수</th>
										<td><input id="deprPrid" name="deprPrid" type="text"
											value="" maxlength="10" class="inpw40" data-requireNm="내용년수"
											data-maxLength="20" title="내용년수" /></td>
										<th scope="row">단가</th>
										<td><input id="unitAmt" name="unitAmt" type="text"
											value="" maxlength="12,0" class="inpw40" /></td>
									</tr>
									<tr>
										<th scope="row">보유장소<span class="fontred">*</span></th>
										<td colspan="3"><input id="hldPlc" name="hldPlc" type="text"
											value="" maxlength="100" class="inpw40" /></td>
									</tr>
								</tbody>
							</table>
							<br />
							<table class="iptTblX">
								<colgroup>
									<col width="15%" />
									<col width="35%" />
									<col width="15%" />
									<col width="35%" />
								</colgroup>
								<tbody id="devEqp">

								</tbody>
							</table>

							<table class="iptTblX">
								<colgroup>
									<col width="15%" />
									<col width="35%" />
									<col width="15%" />
									<col width="35%" />
								</colgroup>
								<tbody id="moniEqp">

								</tbody>
							</table>

							<br />

							<table class="iptTblX">
								<colgroup>
									<col width="15%" />
									<col width="85%" />
								</colgroup>
								<tbody>
									<tr>
										<th scope="row">비고</th>
										<td><input id="remarks" name="remarks" type="text" maxlength="500" /></td>
									</tr>
								</tbody>
							</table>
						</div>
					</form>
					<div class="btn_c">
						<ul>
							<li><a href="#" class="RdButton"
								onclick="fn_insEqpMgmt();return false;" id="btn_insMgmt"
								name="btn_insMgmt" style="display: none;">등록</a></li>
							<li><a href="#" class="myButton"
								onclick="fn_searchList(1);return false;">목록</a></li>
						</ul>
					</div>
					<!-----------//-검색------------------->

				</div>
			</div>

		</div>
	</div>
	<div id="divPrgPopup"></div>
	<div id="eqpMgmtFIndEqupQListPop"></div>
	<div id="eqpBizFIndEqupQListPop"></div>
</body>
</html>