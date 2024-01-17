<%--
/**
 * <pre>
 * 1. 작성일 : 2021. 4. 26.
 * 2. 작성자 : sjw
 * 3. 화면명 : 로그 목록
 * 4. 설명 : 로그 목록
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
var tabId;

$(document).ready(function() {
	
	tabId = parent.$('li[id*=li_tabs-M][aria-expanded=true]').attr('aria-controls');

	gfn_calendarConfig("searchRegFromDt", "searchRegToDt", "minDate", "");
	gfn_calendarConfig("searchRegToDt", "searchRegFromDt", "maxDate", "");

	$('#searchRegToDt').val(gfn_dashDate(gfn_getCurDate(), "-"));
	$('#searchRegFromDt').val(gfn_dashDate(gfn_addDate(gfn_getCurDate(), -15), "-"));
	
	$('#perPageNum').on('change',function(){
		fn_queryFsysLogQList(1);
	});
	
	gfn_init({startFnNm:'fn_queryFsysLogQList', param:1, codeSet:"N"});

});

/**
 * @로그 리스트 조회
 * @param {int} page
 * @returns 
 */
function fn_queryFsysLogQList(page){
	var callUrl = "<c:url value="/fsys/log/queryFsysLogQList.do"/>";
	
	requestUtil.searchList({callUrl:callUrl, srhFormNm:'searchForm', callbackNm:'fn_queryQListCallback', page:page});
	
}

/**
  * @로그 리스트 조회 콜백
  * @param {json} data
  * @returns 
  */
function fn_queryQListCallback(data){

		$("#fsysLogList").empty();
		$("#totalcnt").text(gfn_toComma(data.totalCount));
		
		if(data.fsysLogList.length < 1){
			$('#fsysLogList').append('<tr><td colspan="6">조회된 결과가 없습니다.</td></tr>');
			$('#pagination-div').twbsPagination('destroy');
		}else{
		
			$.each(data.fsysLogList, function(index, item){
          var url = gfn_nullRtnSpace(item.logUrl).length > 50 ? item.logUrl.substr(0, 50) + "..." : gfn_nullRtnSpace(item.logUrl);
          var rqesterParam = gfn_nullRtnSpace(item.rqesterParam).length > 16 ? item.rqesterParam.substr(0, 16) + "..." : gfn_nullRtnSpace(item.rqesterParam);
          rqesterParam = rqesterParam.replace("\n", "");
          var append = "";

			append += "<tr>";
	
			// append += "<td>" + (data.page == 1 ? index+1 : ((data.page-1)*data.perPageNum)+index+1) + "</td>";
			append += "<td>" + gfn_nullRtnSpace(item.rqesterId) + "</td>";
			append += "<td>" + gfn_nullRtnSpace(item.menuNm) + "</td>";
			append += "<td>" + gfn_nullRtnSpace(url) + "</td>";
			append += "<td>" + gfn_nullRtnSpace(item.occrrncDeDt) + "</td>";
			append += "<td>" + gfn_nullRtnSpace(item.rqesterIp) + "</td>";
			append += "<td><a href='#' onclick=javascript:fn_queryFsysLogQListPop('" + item.requstId + "')>"
                + "<span style='display: none;' id='" + (item.requstId + "Lng") + "'>" + gfn_nullRtnSpace(item.rqesterParam) + "</span>"
                + "<span style='display: block;' id='" + (item.requstId + "Sht") + "'>" + rqesterParam + "</span>"
                + "</a></td>";
	
			append += "</tr>";
			$("#fsysLogList").append(append);
			});

		}
		
		data.__callFuncName__ ="fn_queryFsysLogQList";
		data.__naviID__ ="page_navi";
		pageUtil.setPageNavi(data);
}

function fn_rqesterParam(requstId) {

  var idLng = requstId + "Lng";
  var idSht = requstId + "Sht";
  
  
  if($('#'+idLng).is(':visible') == false ){

	  $('#'+idLng).css('display','block');
	  $('#'+idSht).css('display','none');
  } else {
	  $('#'+idLng).css('display','none');
	  $('#'+idSht).css('display','block');

  } 

	
}//fn_rqesterParam

function fn_queryFsysLogQListPop(id){

  var callUrl = "<c:url value = "/com/PageLink.do"/>";

  requestUtil.mdPop({
    popUrl : callUrl+"?link="+"frame/fsys/log/fsysLogDtlPop" + "&srcRequstId="+ gfn_nullRtnSpace(id),
    height: 700,
        width: 1000,
        title: '로그 상세',
        divId : 'divFsysLogDtlPop'
  });
}//fn_queryFsysLogQListPop
</script>
</head>
<body>
	<div id="con_wrap">
		<div id="contents_info">
			<!--- contnets  적용 ------>
			<div>
				<div class="loca">
					<div class="ttl">로그 조회</div>
					<div class="loca_list"></div>
				</div>

				<div class="sub">
					<!--------------검색------------------>
					<form id="searchForm" name="searchForm" onsubmit="return false;">
						<div class="t_head">
							<input type="hidden" id="boardKind" class="b_put" name="boardKind" value="C23008" />

							<table class="tbl_type_hd" border="1" cellspacing="0" onkeydown="if(gfn_enterChk())fn_queryFsysLogQList(1);">
								<caption>검색</caption>
								<colgroup>
									<col width="10%">
									<col width="25%">
									<col width="10%">
									<col width="25%">
									<col width="10%">
									<col width="20%">
								</colgroup>
								<thead>
									<tr> 
                    <th scope="col" class="hcolor">로그 ID</th> <th scope="col"><input type="text" id="fsysLogQListId" 	name="fsysLogQListId" maxlength="200" 	value="<c:out value='${param.fsysLogQListId}'/>" 	class="inpw50"></th>
										<th scope="col" class="hcolor">URL</th>
										<th scope="col"><input type="text" id="fsysLogQListURL" name="fsysLogQListURL" maxlength="200" value="<c:out value='${param.fsysLogQListURL}'/>" class="inpw50"></th>
										<th scope="col" class="hcolor">IP</th>
										<th scope="col"><input type="text" id="fsysLogQListIp" 	name="fsysLogQListIp" maxlength="200" 	value="<c:out value='${param.fsysLogQListIp}'/>" 	class="inpw50"></th>
									</tr>
								</thead>
								<thead>
									<tr>
										<th scope="col" class="hcolor">작성일자</th>
										<th scope="col">
											<input class="inpw20" type="text" name="searchRegFromDt" id="searchRegFromDt" /> 
											<input class="inpw20" type="text" name="searchRegToDt" 	id="searchRegToDt" />
                    					</th>
										<th scope="col" class="hcolor">메뉴명</th>
										<th scope="col" colspan="3"><input type="text" id="fsysLogQMenuNm" 	name="fsysLogQMenuNm" maxlength="200" class="inpw50"></th>
									</tr>
								</thead>
							</table>

						</div>
						<div class="btn_c">
							<ul>
								<li><a href="javascript:void(0);" class='myButton' onclick="fn_queryFsysLogQList(1); return false;">조회</a></li>
							</ul>
						</div>

						<!--------------//검색------------------>

						<!--------------결과------------------>
						<div class="r_num">
							| 결과 <strong id="totalcnt" style="color: #C00"></strong>건
						</div>
						<div class="bo_num">
							<select id="perPageNum" name="perPageNum" class="selw6" style="visibility: hidden;">
								<option value="5">5개씩</option>
								<option value="10" selected="selected">10개씩</option>
							</select>
						</div>
				</div>

				<!--------------목록---------------------->
				<div class="t_list">
					<table class="tbl_type" border="1" cellspacing="0">
						<caption>로그 조회</caption>
						<colgroup>
							<!-- <col width="5%"> -->
							<col width="12.5%">
							<col width="12.5%">
							<col width="30%">
							<col width="15%">
							<col width="15%">
							<col width="15%">
						</colgroup>
						<thead>
							<tr>
								<!-- <th scope="col"></th> -->
								<th scope="col">ID</th>
								<th scope="col">메뉴명</th>
								<th scope="col">URL</th>
								<th scope="col">등록일</th>
								<th scope="col">IP</th>
								<th scope="col">PARAM</th>
							</tr>
						</thead>
						<tbody id="fsysLogList">
						</tbody>
					</table>
				</div>
				<!--------------//목록---------------------->

				<!-----------------------페이징----------------------->
				<div id='page_navi' class="page_wrap"></div>
				<!-----------------------//페이징----------------------->
				</form>
			</div>

		</div>
	</div>

	<div id="divFsysLogDtlPop"></div>
</body>
</html>