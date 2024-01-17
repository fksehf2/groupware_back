<%--
/**
 * <pre>
 * 1. 작성일 : 2021. 4. 14. 오전 10:49:11
 * 2. 작성자 : ilyong
 * 3. 화면명 : 사용자관리 조회
 * 4. 설명 : 화면명과 동일하거나 기타 특이사항 기술
 * </pre>
 */
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>

<html>
<head>
<%@ include file="/WEB-INF/views/frame/fcom/jimsCommon.jsp" %>

<script type="text/javaScript" language="javascript">
// var flag = true;
// var perPageNum;
 var tabId;

$(document).ready(function() {
	
	tabId = parent.$('li[id*=li_tabs-M][aria-expanded=true]').attr('aria-controls');
	
	var codeInfo2 = [{cdId:'C01',selectId:'schUserGb',type:'1', callbackNm:'fn_ajaxSchUserGbCallback', sqlQueryId:''}];
	// fn_ajaxCodeList(codeInfo2);
	gfn_init({startFnNm:'', param:codeInfo2, codeSet:'Y'});
	
// 	var paramRow = [];
	
// 	paramRow.push({"target":"searchInstt", "cdId": "C01"});
	/* paramRow.push({"target":"srcSysGrp2", "cdId": "C02"}); */
	/* paramRow.push({"target":"select3", "val": "C03"});
	paramRow.push({"target":"select4", "val": "C04"}); */

// 	var param = {paramRow : paramRow};
// 	fn_queryCodeList(param);
});

/**
 * 공통 코드 콜백함수 
 * @param {string} prgID
 * @returns 
 */
function fn_ajaxSchUserGbCallback(data){
 	$('#schUserGb option:eq(0)').before("<option value='' selected>전체</option>");
 	requestUtil.getSearchForm({targetFormId:"searchForm", callbackNm:"fn_queryFsysUserMList"});
}

/**
 * @!@ 
 * @param {string} prgID
 * @returns 
 */
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

function redraw(page){
	var vTelNo;
	if(flag){
		flag = false;
		$('#commNotcMList').empty();
		var data = $("form[name=searchForm]").serialize();	
		var imsi = "";
		
		$.ajax({
	        url: "<c:url value = "/fsys/user/queryFsysUserMList.do"/>" ,
	        data: data+"&page="+page, //data+"&page="+page+"&perPageNum="+perPageNum,
	        type: "POST",
	        dataType: 'json',
	        async: false,
	        success: function(data) {
	        	if(data.commNotcMList.length < 1){
	    			$('#commNotcMList').append('<tr><td colspan="7">조회된 결과가 없습니다.</td></tr>');
	    			$('#pagination-div').twbsPagination('destroy');
	    		}else{
		    		$.each(data.commNotcMList, function(index, item){
		    			$('#commNotcMList').append("<tr><td>"+(page == 1 ? index+1 : ((page-1)*perPageNum)+index+1)+"</td>"
		    			        +"<td>"+item.userNm+"</td>"
		    			        +"<td style='max-width: 200px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis'>"
		    							+"<a href='#' onclick=javascript:fn_indexFsysUserUDtl("+item.userId+")><u><strong><font color=green>"+item.userId+"</font></strong></u></a>"
		    					+"</td>"
		    					+"<td>"+gfn_nullRtnSpace(item.userGb)+"</td>"
		    					+"<td>"+gfn_nullRtnSpace(item.telNo)+"</td>"
		    					+"<td>"+gfn_nullRtnSpace(item.hpTelNo)+"</td>"
		    					+"<td>"+gfn_nullRtnSpace(item.email)+"</td>"
		    					+"<td>"+gfn_nullRtnSpace(item.dispRegDt)+"</td>"
		    					+"<td>"+gfn_nullRtnSpace(item.dispPwdChgDt)+"</td>"
		    					+"<td>"+item.pwdErrCnt+"</td>"
		    					+"<td>"+gfn_nullRtnSpace(item.regStatusNm)+"</td>"
		    					+"<td>"+"&nbsp;"+"</td>"
		    					+"</tr>"
		    			);
		    		 });

	        		$("#pagination-div").twbsPagination("changeTotalPages", data.totalPages , page);
	    		}
	        },
	        beforeSend: function() {
	        	$(".modal").show();
	        }
	        ,complete: function(data){	
	        	var reuslt = JSON.parse(data.responseText);
				$(".modal").hide();
				flag = true;
				$('#totalcnt').text(reuslt.totalCount);
			}
	    });
	}

}

function fn_noticeSearch() {
	$('#pagination-div').twbsPagination('destroy');
	$("<div id='pagination-div'></div>").insertAfter('div.t_list');
	
	 $('#pagination-div').twbsPagination({
	    totalPages: 10,	// 총 페이지 번호 수
	    visiblePages: 10,	// 하단에서 한번에 보여지는 페이지 번호 수
	    startPage : 1, // 시작시 표시되는 현재 페이지
	    initiateStartPageClick: false,	// 플러그인이 시작시 페이지 버튼 클릭 여부 (default : true)
	    first : "첫 페이지",	// 페이지네이션 버튼중 처음으로 돌아가는 버튼에 쓰여 있는 텍스트
	    prev : "이전 페이지",	// 이전 페이지 버튼에 쓰여있는 텍스트
	    next : "다음 페이지",	// 다음 페이지 버튼에 쓰여있는 텍스트
	    last : "마지막 페이지",	// 페이지네이션 버튼중 마지막으로 가는 버튼에 쓰여있는 텍스트
	    nextClass : "page-item next",	// 이전 페이지 CSS class
	    prevClass : "page-item prev",	// 다음 페이지 CSS class
	    lastClass : "page-item last",	// 마지막 페이지 CSS calss
	    firstClass : "page-item first",	// 첫 페이지 CSS class
	    pageClass : "page-item",	// 페이지 버튼의 CSS class
	    activeClass : "active",	// 클릭된 페이지 버튼의 CSS class
	    disabledClass : "disabled",	// 클릭 안된 페이지 버튼의 CSS class
	    anchorClass : "page-link",	//버튼 안의 앵커에 대한 CSS class
	
	    onPageClick: function (event, page) {
	    	redraw(page);
	    }
	});
redraw(1);
}

/**
 * @!@ 사용자 관리 리스트 조회
 * @param {int} page
 * @returns 
 */
function fn_queryFsysUserMList(page){
// 	debugger;
	var callUrl = "<c:url value='/fsys/user/queryFsysUserMList.do'/>";
	$("#page").val(page);
	requestUtil.searchList({callUrl:callUrl, srhFormNm:'searchForm', callbackNm:'fn_queryFsysUserMListCallback', page:$("#page").val(), perPageNum:10});
	
// 	var callUrl = "<c:url value='/fbbs/ntc/queryFBbsNtcMList.do'/>";	
// 	requestUtil.searchList({callUrl:callUrl,srhFormNm:'searchForm',callbackNm:'fn_callback',page:page,perPageNum:10});
	
}

 /**
  * @!@ 메뉴 관리 리스트 조회 콜백
  * @param {json} data
  * @returns 
  */
 function fn_queryFsysUserMListCallback(data){
 	
//  	var list = data.commNotcMList;
//  	$("#listTab > tbody").empty();
//  	$.each(list,function(idx,row){
//  		var append = "";
//  		append += "<tr>";
		
//  		append += "<td>" + row.rnum + "</td>";
//  		append += "<td>" + row.userNm + "</td>";
//  		append += "<td><a href='javascript:void(0)' onclick=javascript:fn_indexFsysUserUDtl('"+row.userId+"')><u>"+row.userId+"</u></a></td>";
//  		append += "<td>" + row.userGb + "</td>";
//  		append += "<td>" + row.telNo + "</td>";
//  		append += "<td>" + row.hpTelNo + "</td>";
//  		append += "<td>" + row.email + "</td>";
//  		append += "<td>" + row.dispRegDt + "</td>";
//  		append += "<td>" + row.dispPwdChgDt + "</td>";
//  		append += "<td>" + row.pwdErrCnt + "</td>";
//  		append += "<td>" + row.regStatus + "</td>";
//  		append += "<td>" + "&nbsp;" + "</td>";

//  		append += "</tr>";
//          $("#listTab > tbody").append(append);
//   	});
 	
 	
//  	data.__callFuncName__ ="fn_queryFsysUserMList";
//  	data.__naviID__ ="page_navi";
//  	pageUtil.setPageNavi(data);
 	
 	
 	
 	
//  	debugger;
 	$("#commNotcMList").empty();
	$("#totalcnt").text(data.totalCount);
	
  	if(data.commNotcMList.length < 1){
		$('#commNotcMList').append('<tr><td colspan="10">조회된 결과가 없습니다.</td></tr>');
	}else{
// 		$.each(data.commNotcMList, function(index, item){

// 			$('#commNotcMList').append("<tr><td>"+(data.page == 1 ? index+1 : ((data.page-1)*data.perPageNum)+index+1)+"</td><td>"+item.putupSno+"</td><td style='max-width: 200px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis'>"
// 					+"<a href='#' onclick=javascript:fn_detail("+item.putupSno+")><u><strong><font color=green>"+item.title+"</font></strong></u></a>"+"</td><td>"
// 					+item.fileCount+"</td><td>"+item.putupDt+"</td><td>"+item.regrNm+"</td><td>"+item.selectNum+"</td></tr>");
// 		});
		var list = data.commNotcMList;
	 	$.each(list,function(idx,row){
	 		var append = "";
	 		append += "<tr>";
			
// 	 		append += "<td>" + row.rnum + "</td>";
	 		append += "<td>" + row.userNm + "</td>";
	 		append += "<td><a href='javascript:void(0)' onclick=javascript:fn_indexFsysUserUDtl('"+row.userId+"')><u>"+row.userId+"</u></a></td>";
	 		append += "<td>" + gfn_nullRtnSpace(row.insttCdNm) + "</td>";
	 		append += "<td>" + gfn_getTelNo(gfn_nullRtnSpace(row.telNo)) + "</td>";
	 		append += "<td>" + gfn_getTelNo(gfn_nullRtnSpace(row.hpTelNo)) + "</td>";
	 		append += "<td>" + gfn_nullRtnSpace(row.email) + "</td>";
	 		append += "<td>" + gfn_nullRtnSpace(row.dispRegDt) + "</td>";
	 		append += "<td>" + gfn_nullRtnSpace(row.dispPwdChgDt) + "</td>";
	 		append += "<td>" + row.pwdErrCnt + "</td>";
// 	 		append += "<td>" + gfn_nullRtnSpace(row.regStatusNm) + "</td>";
	 		append += "<td>" + gfn_nullRtnSpace(row.useYnNm) + "</td>";
	
	 		append += "</tr>";
	         $("#commNotcMList").append(append);
	  	});
	}
  	
  	data.__callFuncName__ ="fn_queryFsysUserMList";
	data.__naviID__ ="page_navi";
	pageUtil.setPageNavi(data);
 	
 	
 	
 	
 } 
 
function fn_indexFsysUserRDtl(){
	requestUtil.setSearchForm("searchForm");
	parent.$('#tabs-M000000401').find("iframe").attr("src", '<c:url value="/fsys/user/indexFsysUserRDtl.do"/>?sysGrp='+$("#srcSysGrp").val());
}


function fn_indexFsysUserUDtl(userId){
// debugger;
	requestUtil.setSearchForm("searchForm");
	parent.$('#tabs-M000000401').find("iframe").attr("src", '<c:url value="/fsys/user/indexFsysUserUDtl.do"/>?userId='+encodeURIComponent(userId));
	 
}

function gfn_getTelNo(that){
	
	//alert("=========that=====>>>>"+that)
	var returnValue = that.replace(/[^0-9]/g, "").replace(/(^02|^0505|^1[0-9]{3}|^0[0-9]{2})([0-9]+)?([0-9]{4})$/,"$1-$2-$3").replace("--", "-"); 
	
	//alert("=========returnValue=====>>>>"+returnValue)
	//$(this).val( $(this).val().replace(/[^0-9]/g, "").replace(/(^02|^0505|^1[0-9]{3}|^0[0-9]{2})([0-9]+)?([0-9]{4})$/,"$1-$2-$3").replace("--", "-") );
	return returnValue;
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
                    <div class="ttl">사용자 <c:if test="${loginVO.userGb == 'C01999'}">관리</c:if></div>
                    <div class="loca_list">Home > 시스템관리 > 사용자관리</div>
                  </div>
                 
                  <div class="sub">
                     <!--------------검색------------------>
<!--                     <form id="searchForm" name="searchForm" method="post"> -->
					 <form id="searchForm" name="searchForm" onsubmit="return false;">	
					 <input type="hidden" class="" id="page" name="page" value="1"/>
                    <div class="t_head">
				        <input type="hidden" id="boardKind" class="b_put"  name="boardKind"   value="C23001"/>
				        <input type="hidden" id="userGb"   name="userGb"   value="C00000"/>
				        
                          <table class="tbl_type_hd" border="1" cellspacing="0" onkeydown="if(gfn_enterChk())fn_queryFsysUserMList(1);">
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
					            <th scope="col" class="hcolor">사용자명</th>
					            <td scope="col" >
					               <input class="inpw70" type="text" name="schUserNm" id="schUserNm" />
					            </td>
					            <th scope="col" class="hcolor">사용자ID</th>
					            <td scope="col" >
					               <input class="inpw70" type="text" name="schUserId" id="schUserId" />
					            </td>
					            <th scope="col" class="hcolor">사용자구분</th>
					            <td scope="col" >
					               <select class="selw10" id="schUserGb" name="schUserGb" onchange="fn_queryFsysUserMList(1)"  >
								   </select>
					            </td>
					          </tr>
					          <tr>
					          	<th scope="col" class="hcolor">부서명</th>
					            <td scope="col" >
					               <input class="inpw70" type="text" name="schInsttNm" id="schInsttNm" />
					            </td>
<!-- 					          	<th scope="col" class="hcolor">가입상태</th> -->
<!-- 					          	<td scope="col" > -->
<!-- 					               <select class="selw6" id="schRegStatus" name="schRegStatus" onchange=""  > -->
<!-- 					               		<option value="">전체</option> -->
<!-- 							 			<option value="Y">승인</option> -->
<!-- 										<option value="N">미승인</option> -->
<!-- 					               </select> -->
<!-- 					            </td> -->
					            <th scope="col" class="hcolor">사용여부</th>
					          	<td scope="col" colspan="3">
					               <select class="selw6" id="schUseYn" name="schUseYn" onchange="fn_queryFsysUserMList(1)"  >
					               		<option value="">전체</option>
							 			<option value="Y">사용</option>
										<option value="N">미사용</option>
					               </select>
					            </td>
					          </tr>    
                           </thead>
                        </table>
                      <div  class="btn_c">
					       <ul>
					       	 <c:if test="${loginVO.userGb == 'C01999'}">
                             <li><a href="javascript:void(0);" class='RdButton' onclick="fn_indexFsysUserRDtl(); return false;">등록</a></li>
                               </c:if>
                             <li><a href="javascript:void(0);" class='gyButton' onclick="fn_queryFsysUserMList(1); return false;">조회</a></li>
                               
                             </ul>   
					  </div>
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
<%--                                       <col width="5%">  순번 --%>
                                      <col width="11%">	<%-- 이름 --%>
                                      <col width="11%">	<%-- ID --%>
                                      <col />	<%-- 부서명 --%>
                                      <col width="8%">	<%-- 전화번호 --%>
                                      <col width="8%">	<%-- 핸드폰번호 --%>
                                      <col width="12%">	<%-- 이메일 --%>
                                      <col width="8%">	<%-- 가입일자 --%>
                                      <col width="8%">	<%-- 비밀번호최종변경일자 --%>
                                      <col width="7%">	<%-- 비밀번호 오류횟수 --%>
<%--                                       <col width="5%">	상태 --%>
                                      <col width="5%">	사용여부
                                      </colgroup>
                                    <thead>
                                      <tr>
<!--                                          <th scope="col">순번</th> -->
                                         <th scope="col">이름</th>
                                         <th scope="col">ID</th>
                                         <th scope="col">부서명</th>
                                         <th scope="col">전화번호</th>
                                         <th scope="col">핸드폰번호</th>
                                         <th scope="col">이메일</th>
                                         <th scope="col">가입일자</th>
                                         <th scope="col">비밀번호<br>최종변경일자</th>
                                         <th scope="col">비밀번호<br>오류횟수</th>
<!--                                          <th scope="col">가입상태</th> -->
                                         <th scope="col">사용여부</th>
                                      </tr>
                                    </thead>
                                    <tbody id="commNotcMList">
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

</body>
</html>