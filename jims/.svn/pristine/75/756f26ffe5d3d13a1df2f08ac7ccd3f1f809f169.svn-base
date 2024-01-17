<%--
/**
 * <pre>
 * 1. 작성일 : 2021. 7. 20
 * 2. 작성자 : sjw7240
 * 3. 화면명 : 장비 사용자 등록
 * 4. 설명 : 장비 사용자 관리 등록 페이지
 * </pre>
 */
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>

<html>
<head>
<%@ include file="/WEB-INF/views/frame/fcom/jimsCommon.jsp"%>


<script type="text/javaScript" language="javascript" defer="defer">
$(document).ready(function() {
	tabId = parent.$('li[id*=li_tabs-M][aria-expanded=true]').attr('aria-controls');	
	
	var codeInfo = [{cdId:'C03',selectId:'pgsStat',type:'1', callbackNm:'fn_ajaxCntrFormCallback', sqlQueryId:''}];
	fn_ajaxCodeList(codeInfo);
	
	<%/* 달력 세팅 */%> 
 	gfn_calendarConfig("rcvDt", "", "", "");    <%/* 대여신청일자 */%> 
 	gfn_calendarConfig("rtnDt", "", "", "");    <%/* 대여신청일자 */%> 
 	gfn_calendarConfig("rcvDt", "rtnDt", "minDate", "");    <%/* 대여시작일자 from */%>
	gfn_calendarConfig("rtnDt", "rcvDt", "maxDate", "");   <%/* 대여종료일자 to */%>
	
	$("#rcvDt").val(gfn_getDate());
    
});

function fn_ajaxCntrFormCallback(data){
	$('#pgsStat option:eq(0)').before("<option value='' selected>선택</option>");
}

/**
* 장비 상세 정보 조회 콜백
* @param  
* @returns
*/
function fn_queryEqpMgmtCallBack(data){
	
	var eqpSno = data.resultMap.eqpSno;	
	var eqpNm = data.resultMap.eqpNm;
	var eqpTypNm = data.resultMap.eqpTypNm;
	var srNo = gfn_nullRtnSpace(data.resultMap.srNo);
	var deprPrid = gfn_nullRtnSpace(data.resultMap.deprPrid);
	var purcDt = gfn_dashDate2(gfn_nullRtnSpace(data.resultMap.purcDt));
	var exprDt = gfn_dashDate2(gfn_nullRtnSpace(data.resultMap.exprDt));
	var mnftCo = gfn_nullRtnSpace(data.resultMap.mnftCo);
	
	$("#eqpSno").text(eqpSno); 		<%/* 장비번호 */%>
	$("#eqpNm").text(eqpNm); 		<%/* 장비명 */%>
	$("#eqpTypNm").text(eqpTypNm); 	<%/* 장비유형 */%>
	$("#srNo").text(srNo); 			<%/* S/N */%>
	
	$("#deprPrid").text(deprPrid); 	<%/* 내용연수 */%>
	$("#purcDt").text(purcDt); 		<%/* 도입일 */%>
	$("#exprDt").text(exprDt); 		<%/* 만료일 */%>
	$("#mnftCo").text(mnftCo); 		<%/* 제조사 */%>
	
}

//장비 사용자 등록
function fn_regEqpUser(){
	var conMsg = "장비 사용자 등록을 하시겠습니까?";
	
	if(!fn_saveCntChk()) return;
	
	//장비상세정보
	var tpFormArry = [];
	var $formDatas1 = $('#insForm').find('#tbMgmtDtl');
	var $formDatas2 = $('#insForm').find('#tbMgmtDtl2');
	var formObj1 = new Object();
	var formObj2 = new Object();
	var cnt = 0;
	var cnt2 = 0;
	$formDatas1.each(function(index,item){

		var $el = $(item).find('input, select, textarea');

		$el.each(function(index,item){
			formObj1[item.name] = item.value;
			cnt++;
		});
		

	});
	
	$formDatas2.each(function(index,item){

		var $el = $(item).find('input, select, textarea');

		$el.each(function(index,item){
			formObj2[item.name] = item.value;
			cnt2++;
		});
		

	});
	
	
	//유지보수 상세정보
	var tpArray = [];
	var dataObj = new Object();
	var cnt3 = 0;

	$('#detailTbody > tr').each(function(index,item){

		var $el = $(item).find('input, select, textarea');

		$el.each(function(index,item){
			dataObj[item.name] = item.value;
		});

			tpArray.push(dataObj);
			dataObj = {};

	});
	
	fn_showModalPage(conMsg, function() {
		var data = {rowDatas : tpArray, formDatas1 : formObj1, formDatas2 : formObj2};
	    var callUrl = "<c:url value='/eqp/user/regEqpUserRDtl.do'/>";
	    requestUtil.saveData({callUrl:callUrl,data:data,callbackNm:'fn_modifyEqpLendCallBack'});
	});
	
	
}

function fn_modifyEqpLendCallBack(data){
	fn_searchList();
}

function fn_searchList(){
	var param = "";
	var gotoUrl = "";
		gotoUrl = '<c:url value="/eqp/user/indexEqpUserMList.do"/>';
	parent.$('#'+tabId+' iframe').attr('src', gotoUrl+param);	
}


/**
 * @ 메뉴 관리 등록 콜백
 * @param {json} data
 * @returns 
 */
function fn_insEqpMgmtCallback(data){
	 fn_searchList();
}

<%/* 시작 처리 function */%>
function fn_addRow() {
		var idx = $('#detailTbody > tr').length == 0 ? 1 : $('#detailTbody > tr').length+1;

		var tpTag =     '<tr>'+
			'<td style="text-align:center"><input type="checkbox" name="chk" id="chk" value="Y" class="check_agree1"></td>'+
			'<td><textarea id="box" name="eqpNm" rows=2 cols=30 maxlength="2000"></textarea></td>'+
			'<td><textarea id="box" name="eqpTyp" rows=2 cols=30 maxlength="200"></textarea></td>'+
			'<td "text-align:center"><input type="text" name="mnftCo" id="mnftCo'+idx+'" class="inpw50"></td>'+
			'<td "text-align:center"><input type="text" name="mdlNm" id="mdlNm'+idx+'" class="inpw50"></td>'+
			'<td "text-align:center"><input type="text" name="srNo" class="inpw70" maxlength="50"></td>'+
			'<td "text-align:center"><input type="text" name="purcDt" class="inpw70" maxlength="50"></td>'+
			'<td "text-align:center"><input type="text" name="hldPlc" class="inpw70" maxlength="50"></td>'+
		'</tr>';
		$("#detailTbody").append(tpTag);

		
		gfn_calendarConfig("rprStartDt_"+idx, "", "", "");
		gfn_calendarConfig("rprEndDt_"+idx, "", "", "");
		
}

<%/* 삭제버튼 클릭 function */%>
function fn_delRow() {
	var cnt = 0;
	$('#detailTbody > tr').each(function(index,item){
		var $chkbox = $(item).find('input[type=checkbox]');
		$chkbox.each(function(index,item){

			if($(this).is(':checked') == true){
				cnt++;
				$(this).parent().parent('tr').remove();
			}
		})

	});

	if(cnt < 1){
		fn_showUserPage( "삭제할 내역을 선택하십시요.", function() {
			return;		
        });
	}

}
	
function fn_saveCntChk(){
	var chkCnt = 0; 
	$('#tbLendDtl :input[name=chk]').each(function(index) {
			chkCnt++;
		//}
	});
	
	if(chkCnt<=0){
		fn_showUserPage( "장비를 선택하세요.", function() {
				
        });
		return false;	
	}	
	return true;
	
       
}


/**
 * @!@ 장비조회 팝업
 * @param cd
 * @returns 
 */
function fn_searchEqp() {
	
	var callUrl = "<c:url value = "/com/PageLink.do"/>"

	requestUtil.mdPop({
		popUrl : callUrl+"?link="+"jims/eqp/mgmt/eqpMgmtFIndEqupQListPop",
		height: 650,
        width: 1000,
        title: '장비조회 팝업',
        divId : 'eqpMgmtFIndEqupQListPop'
	});

}


/**
 * @!@ 사용자 조회 팝업
 * @param cd
 * @returns 
 */
function fn_searchUserListPop() {
   
	var callUrl = "<c:url value = "/com/PageLink.do"/>"

	requestUtil.mdPop({
		popUrl : callUrl+"?link="+"jims/eqp/user/userFindPop",
		height: 700,
        width: 1000,
        title: '사용자 조회 팝업',
        divId : 'userFindPop'
	});

}


/**
* 사용자 조회 팝업 콜백함수
* @param {object} data 조회한 결과데이터
* @param {string} divId 팝업div아이디
* @returns
*/
function fn_popCallBack(data, divId){
	$("#"+divId).dialog('close');
	$("#"+divId).empty();
	
	$.each(data, function(index, value){
		if($('#'+index).is('span')){
			$('#'+index).text(value);
		}else{
			$('#'+index).val(value);
		}
	});
	
}

</script>

</head>

<body>
	<div id="con_wrap1">
		<div class="content">
			<!----현재위치----->

			<div id="contents_info">
				<div class="sub_ttl">장비 사용자 등록</div>
				<!-----타이틀------>

				<div class="sub">
					<!------------검색------------------->
					<form name="insForm" id="insForm" method="post">

							<table class="iptTblX" id="tbMgmtDtl">

								<caption>등록</caption>
								<colgroup>
									<col width="15%" />
									<col width="35%" />
									<col width="15%" />
									<col width="*" />
								</colgroup>
								<tbody>
									<tr>
										<th scope="row">부서명</th>
										<td>
											<span id="deptNm"></span>
											<input type="hidden" name="insttCd" id="insttCd" data-requireNm="기관코드" data-maxLength="10" title="기관코드" maxlength="10" />
										</td>
										<th scope="row">장비사용자명<span class="fontred">*</span></th>
										<td>
											<input type="hidden" id="userId" name="userId" data-requireNm="사용자ID" data-maxLength="20" title="사용자ID" maxlength="20" readonly/> 
											<input type="text" id="userNm" name="userNm"  readonly/> 
											<a href="#" class="buttonG60" onclick="fn_searchUserListPop();return false;">검색</a>
										</td>
									</tr>
									<tr>
										<th scope="row">수령일<span class="fontred">*</span></th>
										<td>
											<input id="rcvDt" name="rcvDt" type="text" class="inpw20" data-requireNm="수령일자" data-maxLength="8" title="수령일자" maxlength="8" />
										</td>
										<th scope="row">반납일</th>
										<td>
											<input id="rtnDt" name="rtnDt" type="text" class="inpw20" data-requireNm="반납일자"	data-maxLength="8" title="반납일자" maxlength="8" />
										</td>
									</tr>
									<tr>
										<th scope="row">상태<span class="fontred">*</span></th>
										<td>
											<select name="pgsStat"id="pgsStat" class="selw6" data-requireNm="상태" data-maxLength="6" title="상태" maxlength="3">
												<option value='' selected>선택</option>
											</select>
										</td>
											<th scope="row">현재사업명</th>
											<td>
												<input type="text" id="nowBizNm" name="nowBizNm" data-requireNm="현재사업명" data-maxLength="200" title="현재사업명" maxlength="100" /> 
											</td>
									</tr>


								</tbody>
							</table>
						<!-- </div> -->
						<!-----타이틀------>
						<br />
						<div class="sub">

							<div class="flR">
								<button class="buttonR60" name="addRow" id="addRow"
									onclick="fn_searchEqp();return false;">+
									추가</button>
								<button class="buttonG60" name="delRow" id="delRow"
									onclick="fn_delRow();return false;">- 삭제</button>
							</div>
							<div class="t_list" style="OVERFLOW-Y:auto; width:100%; height:250px;">
								<table class="iptTblX2" id="tbLendDtl">
									<caption>분석대상 상세정보 조회</caption>
									<colgroup>
										<col width="4%">
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
											<th scope="col">선택</th>
											<th scope="col">장비번호</th>
											<th scope="col">장비유형</th>
											<th scope="col">제조사</th>
											<th scope="col">모델명</th>
											<th scope="col">S/N</th>
											<th scope="col">도입일</th>
											<th scope="col">보유장소</th>
										</tr>
									</thead>
									<tbody id="detailTbody">
									</tbody>
								</table>
								</div>
					</form>
					<div class="btn_c">
						<ul>
							<li><a href="#" class="myButton"
								onclick="fn_regEqpUser();return false;">등록</a></li>
							<li><a href="#" class="myButton"
								onclick="fn_searchList(1);return false;">목록</a></li>
						</ul>
					</div>
					<!-----------//-검색------------------->



				</div>
			</div>

		</div>
	</div>
	<div id="userFindPop"></div>
	<div id="eqpMgmtFIndEqupQListPop"></div>
</body>
</html>