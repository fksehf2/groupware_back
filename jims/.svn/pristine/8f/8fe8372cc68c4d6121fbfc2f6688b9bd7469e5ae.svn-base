<%--
/**
 * <pre>
 * 1. 작성일 : 2021. 7. 22
 * 2. 작성자 : sjw7240
 * 3. 화면명 : 장비 사용자 상세
 * 4. 설명 : 테스트중
 * </pre>
 */
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>

<html>
<head>
<%@ include file="/WEB-INF/views/frame/fcom/jimsCommon.jsp" %>


<script type="text/javaScript" language="javascript" defer="defer">

var eqpUserSno = '${param.eqpUserSno}';
var eqpSno = '${param.eqpSno}';

$(document).ready(function() {
	tabId = parent.$('li[id*=li_tabs-M][aria-expanded=true]').attr('aria-controls');
	
	var codeInfo = [{cdId:'C03',selectId:'pgsStat',type:'1', callbackNm:'fn_ajaxCntrFormCallback', sqlQueryId:''}];
	fn_ajaxCodeList(codeInfo);
	
	<%/* 달력 세팅 */%> 
 	gfn_calendarConfig("rcvDt", "", "", "");    <%/* 대여신청일자 */%> 
 	gfn_calendarConfig("rtnDt", "", "", "");    <%/* 대여신청일자 */%> 
 	gfn_calendarConfig("rcvDt", "rtnDt", "minDate", "");    <%/* 대여시작일자 from */%>
	gfn_calendarConfig("rtnDt", "rcvDt", "maxDate", "");   <%/* 대여종료일자 to */%>
    
});

function fn_ajaxCntrFormCallback(data){
	$('#pgsStat option:eq(0)').before("<option value='' selected>선택</option");

	//보유 장비 정보 조회
	fn_eqpLendReqInfoSearch();
}

/**
* //보유 장비 정보 조회
* @param {string} page 항목에 대한 고유 식별자 
* @returns fn_callBack
*/
function fn_eqpLendReqInfoSearch(){

	var searchForm = document.createElement('form');
	searchForm.setAttribute("name","searchForm");
	searchForm.setAttribute("id","searchForm");
	
	var input = document.createElement('input'); 
	input.setAttribute("type", "hidden");
	input.setAttribute("name", "eqpUserSno");
	input.setAttribute("value", eqpUserSno);
	searchForm.appendChild(input);	
	
	document.body.appendChild(searchForm);
	
	var callUrl = "<c:url value = "/eqp/user/queryEqpUserUDtl.do"/>";	
	requestUtil.search({callUrl:callUrl,srhFormNm:'searchForm',setFormNm:'insForm',callbackNm:'fn_eqpUserInfoSearchCallBack'});
}

function fn_eqpUserInfoSearchCallBack(data){
	$('form[name=searchForm]').remove();
	var dataList = data.eqpUserInfoMap;
	if(dataList.length > 0){
		$("#insttCd").val(gfn_nullRtnSpace(dataList[0].insttCd));
		$("#deptNm").text(gfn_nullRtnSpace(dataList[0].deptNm));
		$("#userId").val(gfn_nullRtnSpace(dataList[0].userId));
		$("#userNm").val(gfn_nullRtnSpace(dataList[0].userNm));
		$("#rcvDt").val(gfn_dashDate2(gfn_nullRtnSpace(dataList[0].rcvDt)));
		$("#rtnDt").val(gfn_dashDate2(gfn_nullRtnSpace(dataList[0].rtnDt)));
		$("#pgsStat").val(dataList[0].pgsStat);
		$("#nowBizNm").val(gfn_nullRtnSpace(dataList[0].nowBizNm));
		$("#eqpUserSno").val(eqpUserSno);

			$.each(dataList,function(idx,row){
	 		
			var append = "";
	 		append += "<tr>";
			
	 		append += '<td style=\"text-align:center\">';
	 		append +=  "<input type=\"checkbox\" name=\"chk\" id=\"chk\" value=\"Y\" class=\"check_agree1\" >";	<%/* 선택 */%>
	 		append += '</td>';
	 		
	 		append += '<td style=\"text-align:center\">';
 	 		append +=  gfn_nullRtnSpace(row.eqpSno);	<%/* 장비명 */%>
 	 		append +=  "<input type=\"hidden\" name=\"eqpSno\"  value=\""+ row.eqpSno+"\" maxlength=\"50\" class=\"inpw70\"/>";	<%/* 장비일련번호 */%>
 	 		append += '</td>';
 	 		
 	 		append += '<td style=\"text-align:center\">';
 	 		append +=  gfn_nullRtnSpace(row.eqpTypNm);	<%/* 장비유형 */%>
 	 		append += '</td>';
 	 		
 	 		append += '<td style=\"text-align:center\">';
 	 		append +=  gfn_nullRtnSpace(row.mnftCo);	<%/* 제조사 */%>
 	 		append += '</td>';
 	 		
 	 		append += '<td style=\"text-align:center\">';
 	 		append +=  gfn_nullRtnSpace(row.mdlNm);	<%/* 모델명 */%>
 	 		append += '</td>';
 	 		
 	 		append += '<td style=\"text-align:center\">';
 	 		append +=  gfn_nullRtnSpace(row.srNo);	<%/* S/N */%>
 	 		append += '</td>';
 	 		
 	 		append += '<td style=\"text-align:center\">';
 	 		append +=  gfn_dashDate2(gfn_nullRtnSpace(row.purcDt),'-');	<%/* 도입일자 */%>
 	 		append += '</td>';
 	 		
 	 		append += '<td style=\"text-align:center\">';
 	 		append +=  gfn_dashDate2(gfn_nullRtnSpace(row.hldPlc),'-');	<%/* 도입일자 */%>
 	 		append += '</td>';
 	 		
	 		append += "</tr>";
	        $("#tbLendDtl > tbody").append(append);
	  		
	  	});
	  	
	}
	
}


/**
* 장비 상세 정보 조회 콜백
* @param  
* @returns
*/
function fn_queryEqpLendReqInfoCallBack(data){
	

	var dispRentDeptNm = gfn_nullRtnSpace(data.resultMap.deptNm);
	
}

function fn_searchLendMgmtList(page){
	var callUrl = "<c:url value='/eqp/mgmt/queryEqpMgmtUDtl.do'/>";
	
	requestUtil.searchList({callUrl:callUrl, srhFormNm:'insForm', callbackNm:'fn_searchLendMgmtListCallBack'});
}


/**
 * 대여장비목록 조회 콜백
 * @param  
 * @returns
 */
 function fn_searchLendMgmtListCallBack(data){ 
	
	var eqpMgmtInfoMap = data.eqpMgmtInfoMap;
	var listCnt = eqpMgmtInfoMap.length;
	var tabTdCnt = $("#tbLendDtl > colgroup").find("col").length;
	
	$("#tbLendDtl > tbody").empty();
	
	
  	if(listCnt < 1){
  		var append = "";
		append += "<tr>";
		append += "<td colspan='"+tabTdCnt+"'>"+nullListMsg+"</td>";
		append += "</tr>";
		$("#tbLendDtl > tbody").append(append);
	}else{
	 	
		$.each(eqpMgmtInfoMap,function(idx,row){
	 		
			var append = "";
	 		append += "<tr>";
			
	 		append += '<td style=\"text-align:center\">';
	 		append +=  "<input type=\"checkbox\" name=\"chk\" id=\"chk\" value=\"Y\" class=\"check_agree1\" >";	<%/* 선택 */%>
	 		append += '</td>';
	 		
	 		append += '<td style=\"text-align:center\">';
 	 		append +=  gfn_nullRtnSpace(row.eqpNm);	<%/* 장비명 */%>
 	 		append +=  "<input type=\"hidden\" name=\"eqpSno\"  value=\""+ row.eqpSno+"\" maxlength=\"50\" class=\"inpw70\"/>";	<%/* 장비일련번호 */%>
 	 		append += '</td>';
 	 		
 	 		append += '<td style=\"text-align:center\">';
 	 		append +=  gfn_nullRtnSpace(row.eqpTypNm);	<%/* 장비유형 */%>
 	 		append += '</td>';
 	 		
 	 		append += '<td style=\"text-align:center\">';
 	 		append +=  gfn_nullRtnSpace(row.srNo);	<%/* S/N */%>
 	 		append += '</td>';
 	 		
 	 		append += '<td style=\"text-align:center\">';
 	 		append +=  gfn_nullRtnSpace(row.mdlNm);	<%/* 모델명 */%>
 	 		append += '</td>';
 	 		
 	 		append += '<td style=\"text-align:center\">';
 	 		append +=  gfn_nullRtnSpace(row.mnftCo);	<%/* 제조사 */%>
 	 		append += '</td>';
 	 		
 	 		append += '<td style=\"text-align:center\">';
 	 		append +=  gfn_dashDate2(gfn_nullRtnSpace(row.purcDt),'-');	<%/* 도입일자 */%>
 	 		append += '</td>';
 	 		
	 		append += "</tr>";
	        $("#tbLendDtl > tbody").append(append);
	  		
	  	});
	}
  	
 }	 

//장비대여신청 임시저장
function fn_modifyEqpUser(){
	
	var conMsg = "수정 하시겠습니까?";
	
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
	    var callUrl = "<c:url value='/eqp/user/updEqpUserUDtl.do'/>?eqpUserSno="+eqpUserSno;
	    requestUtil.saveData({callUrl:callUrl,data:data,callbackNm:'fn_modifyEqpUserCallBack'});
	});
	
	
}

function fn_modifyEqpUserCallBack(data){
	fn_searchList();
	//alert("임시저장 성공");
}

function fn_searchList(){
	var param = "";
	parent.$('#'+tabId+' iframe').attr('src', '<c:url value="/eqp/user/indexEqpUserMList.do"/>'+param);	
}

/**
 * @ 장비 관리 삭제
 * @param
 * @returns 
 */
function fn_deleteEqpUser() {
		
	fn_showModalPage("삭제 하시겠습니까?", function() {
		//사용자 정보 수정
		var callUrl = "<c:url value='/eqp/user/delEqpUserUDtl.do'/>";
		requestUtil.save({callUrl:callUrl,srhFormNm:'insForm',callbackNm:'fn_deleteEqpUserCallback'});
	});
}

/**
 * @!@ 메뉴 관리 삭제 콜백
 * @param
 * @returns 
 */
function fn_deleteEqpUserCallback(data){
	fn_searchList();
}

<%/* 시작 처리 function */%>
function fn_addRow() {
		var idx = $('#detailTbody > tr').length == 0 ? 1 : $('#detailTbody > tr').length+1;

		var tpTag =     '<tr>'+
			'<td style="text-align:center"><input type="checkbox" name="chk" id="chk" value="Y" class="check_agree1"></td>'+
			'<td><textarea id="box" name="dfecCnts" rows=2 cols=30 maxlength="2000"></textarea></td>'+
			'<td><textarea id="box" name="rprCnts" rows=2 cols=30 maxlength="200"></textarea></td>'+
			'<td "text-align:center"><input type="text" name="mnftCo" id="mnftCo'+idx+'" class="inpw50" maxlength="200"></td>'+
			'<td "text-align:center"><input type="text" name="mdlNm" id="mdlNm'+idx+'" class="inpw50" maxlength="200"></td>'+
			'<td "text-align:center"><input type="text" name="srNo" class="inpw70" maxlength="50"></td>'+
			'<td "text-align:center"><input type="text" name="purcDt" class="inpw70" maxlength="8"></td>'+
			'<td "text-align:center"><input type="text" name="hldPlc" class="inpw70" maxlength="200"></td>'+
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
 * 분석지원요청 진행관리 팝업 콜백함수
 * @param {object} data 조회한 결과데이터
 * @param {string} divId 팝업div아이디
 * @returns
 */
 function fn_popCallBack(data, divId){
 	$("#"+divId).dialog( "close" );
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
				<div class="sub_ttl">장비 사용자 수정</div>
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
									<input type="hidden" name="eqpUserSno" id="eqpUserSno" />
									<tr>
										<th scope="row">부서명</th>
										<td>
											<span id="deptNm"></span>
											<input type="hidden" name="insttCd" id="insttCd" data-requireNm="기관코드" data-maxLength="10" title="기관코드" maxlength="10" />
										</td>
										<th scope="row">장비사용자명<span class="fontred">*</span></th>
										<td>
											<input type="hidden" id="userId" name="userId" data-requireNm="사용자ID" data-maxLength="20" title="사용자ID" maxlength="20" readonly/> 
											<input type="text" id="userNm" name="userNm" readonly /> 
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
							<br />
							<div class="t_list"
								style="OVERFLOW-Y: auto; width: 100%; height: 250px;">
								<table class="iptTblX2" id="tbLendDtl">
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
							<li><a href="#" class="RdButton"
								onclick="fn_modifyEqpUser();return false;">수정</a></li>
							<li><a href="#" class="RdButton"
								onclick="fn_deleteEqpUser();return false;" id="btn_delMgmt"
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
	<div id="userFindPop"></div>
	<div id="eqpMgmtFIndEqupQListPop"></div>
</body>
</html>