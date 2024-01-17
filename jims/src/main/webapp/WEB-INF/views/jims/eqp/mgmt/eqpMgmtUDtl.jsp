<%--
/**
 * <pre>
 * 1. 작성일 : 2021. 7. 16.
 * 2. 작성자 : sjw7240
 * 3. 화면명 : 장비관리 > 장비상세
 * 4. 설명 : 장비상세
 * </pre>
 */
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>

<html>
<head>
<%@ include file="/WEB-INF/views/frame/fcom/jimsCommon.jsp"%>


<script type="text/javaScript" language="javascript" defer="defer">
var PW_Error = 0;
var ID_Duple = 1;
var tabId;

var eqpSno = '${param.eqpSno}';
$(document).ready(function() {
	tabId = parent.$('li[id*=li_tabs-M][aria-expanded=true]').attr('aria-controls');
	
	var codeInfo = [{cdId:'C04',selectId:'eqpBuyDiv' ,type:'1', callbackNm:'fn_ajaxEqpBuyDivCallback'},{cdId:'C05',selectId:'eqpTyp' ,type:'1'}];
	fn_ajaxCodeList(codeInfo);
	
	<%/* 달력 세팅 */%> 
	gfn_calendarConfig("purcDt", "", "", "");   <%/* 도입일자 */%>
	gfn_calendarConfig("exprDt", "", "", "");   <%/* 만료일자 */%>
});

function fn_ajaxEqpBuyDivCallback(data){
	$('#eqpBuyDiv option:eq(0)').before("<option value='' selected>선택</option");
	$('#eqpTyp option:eq(0)').before("<option value='' selected>선택</option");
	
	gfn_toNumber("unitAmt"); <%/* 단가 */%>
	gfn_toNumber("purcQty"); <%/* 도입수량 */%>
	
	fn_dispCont();
}

function fn_ajaxEqpTypCallback(data){
	//$('#eqpTyp option:eq(0)').before("<option value='' selected>선택</option");
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

//장비 수정
function fn_updEqpMgmt(){
	var eqpBuyDiv = $("#eqpBuyDiv").val(); 
	var eqpNm = $("#eqpNm").val(); 
	var srNo = $("#srNo").val();
	var eqpTyp = $("#eqpTyp").val();
	var purcDt = $("#purcDt").val();
	var exprDt = $("#exprDt").val();
	var guarTrm = $("#guarTrm").val();
	var mnftCo = $("#mnftCo").val();
	var mdlNm = $("#mdlNm").val();
	var mnftNat = $("#mnftNat").val();
	var deprPrid = $("#deprPrid").val();
	var unitAmt = $("#unitAmt").val();
	var hldPlc = $("#hldPlc").val();

	var cpu = $("#cpu").val();
	var ram = $("#ram").val();
	var hddVol = $("#hddVol").val();
	var ssdVol = $("#ssdVol").val();
	var graphics = $("#graphics").val();

	var mntrSize = $("#mntrSize").val();
	var mntrRes = $("#mntrRes").val();

	var remarks = $("#remarks").val();

	if(unitAmt == null || unitAmt == ''){
		$("#unitAmt").val('0');
	}
	
	fn_showModalPage("수정 하시겠습니까?", function() {
 		//장비 정보 등록
		var callUrl = "<c:url value='/eqp/mgmt/updEqpMgmtUDtl.do'/>";
        requestUtil.save({callUrl:callUrl,srhFormNm:'insForm',callbackNm:'fn_searchList'});
	});
}

function fn_searchList(){
	
	parent.$('#'+tabId+' iframe').attr('src', '<c:url value="/eqp/mgmt/indexEqpMgmtMList.do"/>');
}

/**
 * @ 장비 관리 삭제
 * @param
 * @returns 
 */
 function fn_delEqpMgmt() {
	
	fn_showModalPage("삭제 하시겠습니까?", function() {
 		//장비 정보 삭제
		var callUrl = "<c:url value='/eqp/mgmt/delEqpMgmtUDtl.do'/>";
        requestUtil.save({callUrl:callUrl,srhFormNm:'insForm',callbackNm:'fn_searchList'});
	});
	
}

/**
 * 화면 컨트롤
 * @param  
 * @returns
 */
function fn_dispCont(){

	var sno = "${param.eqpSno}";
	
	if ( sno != null && sno != "" ) {
		$("#eqpSno").val(sno);
		var callUrl = "<c:url value = "/eqp/mgmt/queryEqpMgmtUDtl.do"/>";
		requestUtil.search({callUrl:callUrl,srhFormNm:'insForm',setFormNm:'modForm',callbackNm:'fn_eqpTyp'});
	}
}

function fn_eqpTyp(data) {

	var devEqp = "";
	var moniEqp = "";

	var map = data.eqpMgmtInfoMap;

	$("#eqpNm").val(gfn_nullRtnSpace(map.eqpNm));
	$("#deprPrid").val(gfn_nullRtnSpace(map.deprPrid));
	$("#exprDt").val(gfn_dashDate2(gfn_nullRtnSpace(map.exprDt),'-'));
	$("#guarTrm").val(gfn_nullRtnSpace(map.guarTrm));
	$("#hldPlc").val(gfn_nullRtnSpace(map.hldPlc));
	$("#mdlNm").val(gfn_nullRtnSpace(map.mdlNm));
	$("#mnftCo").val(gfn_nullRtnSpace(map.mnftCo));
	$("#mnftNat").val(gfn_nullRtnSpace(map.mnftNat));
	$("#purcDt").val(gfn_dashDate2(gfn_nullRtnSpace(map.purcDt),'-'));
	$("#remarks").val(gfn_nullRtnSpace(map.remarks));
	$("#srNo").val(gfn_nullRtnSpace(map.srNo));
	$("#unitAmt").val(gfn_nullRtnSpace(map.unitAmt));
	$("#remarks").val(gfn_nullRtnSpace(map.remarks));

	switch (gfn_nullRtnSpace(map.eqpTyp)) {
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

			$("#mntrSize").val(map.mntrSize);
			$("#mntrRes").val(map.mntrRes);
			break;
	
		default:
			$("#devEqp").empty();
			$("#moniEqp").empty();
			break;
	}

	$("#eqpBuyDiv").val(gfn_nullRtnSpace(map.eqpBuyDiv));
	$("#eqpTyp").val(map.eqpTyp);
} 

</script>

</head>

<body>
	<div id="con_wrap">
		<div class="content">
			<!----현재위치----->

			<div id="contents_info">
				<div class="sub_ttl">장비 관리 상세</div>
				<!-----타이틀------>

				<div class="sub">
					<!------------검색------------------->
					<form name="insForm" id="insForm" method="post">

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
									<input type="hidden" name="eqpSno" id="eqpSno" />
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
										<th scope="row">장비유형</th>
										<td><select class="" id="eqpTyp" name="eqpTyp" style="width: 80px;" data-requireNm="장비유형"
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
								onclick="fn_updEqpMgmt();return false;" id="btn_updMgmt"
								name="btn_updMgmt">수정</a></li>
							<li><a href="#" class="RdButton"
								onclick="fn_delEqpMgmt();return false;" id="btn_delMgmt"
								name="btn_delMgmt">삭제</a></li>
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
</script>
</body>
</html>