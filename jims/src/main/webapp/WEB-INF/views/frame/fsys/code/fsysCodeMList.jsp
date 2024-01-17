<%--
/**
 * <pre>
 * 1. 작성일 : 2021. 4. 14. 오전 10:11:33
 * 2. 작성자 : jij
 * 3. 화면명 : 코드 관리 리스트 화면
 * 4. 설명 : @!@ 코드 관리 리스트 화면 
 * </pre>
 */
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>

<html>
<head>
<%@ include file="/WEB-INF/views/frame/fcom/jimsCommon.jsp" %>


<script type="text/javaScript" language="javascript" defer="defer">

$(document).ready(function() {
	gfn_init({startFnNm:'fn_queryMList', param:1, codeSet:"N"});
});

/**
 * @!@ 그룹 코드 관리 리스트 조회
 * @param {int} page
 * @returns 
 */
function fn_queryMList(page){
	var callUrl = "<c:url value='/fsys/code/queryFsysCodeMList.do'/>";
	
	requestUtil.searchList({callUrl:callUrl, srhFormNm:'searchForm', callbackNm:'fn_queryMListCallback', page:page, perPageNum:10});
	
	fn_setSrcCdId("", "");
}

/**
 * @!@ 그룹 코드 관리 리스트 조회 콜백
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
	
			append += "<td><a href='javascript:void(0)' onclick=javascript:fn_indexFsysCodeUDtl('"+row.cdId+"')><u>"+row.cdId+"</u></a></td>";
			append += "<td>" + gfn_nullRtnSpace(row.cdIdNm) + "</td>";
			append += "<td>" + gfn_nullRtnSpace(row.cdIdExpl) + "</td>";
			append += "<td>" + gfn_nullRtnSpace(row.useYnNm) + "</td>";
			append += "<td><a href='javascript:fn_setSrcCdId(\""+row.cdId+"\", \""+row.cdIdNm+"\");' class='byButton'>상세코드 조회</a></td>";
	
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
 * @!@ 상세 코드 조회 조건 셋팅
 * @param {String} cdId, cdIdNm
 * @returns 
 */
function fn_setSrcCdId(cdId, cdIdNm){
	$("#searchCdId").html(cdId);
	$("#searchCdIdNm").html(cdIdNm);
	
	if(cdId != ""){
		$("#indexFsysCodeDtlRDtl").show();
	}else{
		$("#indexFsysCodeDtlRDtl").hide();
	}
	$("#srcCdId").val(cdId);
	fn_queryMList2(1);
}

/**
 * @!@ 상세 코드 관리 리스트 조회
 * @param {int} page
 * @returns 
 */
function fn_queryMList2(page){
	
	var callUrl = "<c:url value='/fsys/code/queryFsysCodeDtlMList.do'/>";
	
	requestUtil.searchList({callUrl:callUrl, srhFormNm:'searchForm2', callbackNm:'fn_queryMList2Callback', page:page, perPageNum:10});
	
}

/**
 * @!@ 상세 코드 관리 리스트 조회 콜백
 * @param {json} data
 * @returns 
 */
function fn_queryMList2Callback(data){
	
	var list = data.list;
	var listCnt = list.length;
	var tabTdCnt = $("#listTab2 > colgroup").find("col").length;
	
	$("#listTab2 > tbody").empty();
	
	if(listCnt == 0){
		var append = "";
		append += "<tr>";
		
		append += "<td colspan='"+tabTdCnt+"'>"+nullListMsg+"</td>";
		
		append += "</tr>";
		$("#listTab2 > tbody").append(append);
	}else{
		$.each(list,function(idx,row){
			var append = "";
			append += "<tr>";
	
			append += "<td><a href='javascript:void(0)' onclick=javascript:fn_indexFsysCodeDtlUDtl('"+row.cd+"')><u>"+row.cd+"</u></a></td>";
			append += "<td>" + gfn_nullRtnSpace(row.cdNm) + "</td>";
			append += "<td>" + gfn_nullRtnSpace(row.cdExpl) + "</td>";
			append += "<td>" + gfn_nullRtnSpace(row.useYnNm) + "</td>";
	
			append += "</tr>";
	        $("#listTab2 > tbody").append(append);
	 	});
	}
	
	
	data.__callFuncName__ ="fn_queryMList2";
	data.__naviID__ ="page_navi2";
	
	//pageUtil.setCalFuc("fn_queryMList2");
	//pageUtil.setNaviID("page_navi2");
	pageUtil.setPageNavi(data);
	$("#totalCount2").text(data.totalCount);
	
}

/**
 * @!@ 그룹 코드 등록
 * @param
 * @returns 
 */
function fn_indexFsysCodeRDtl() {

	var callUrl = "<c:url value = "/com/PageLink.do"/>"

	requestUtil.mdPop({
		popUrl : callUrl+"?link="+"frame/fsys/code/fsysCodeRDtlPop",
		height: 700,
        width: 1000,
        title: '그룹 코드 등록',
        divId : 'fsysCodeRDtlPop'
	});

}

/**
 * @!@ 그룹 코드 상세
 * @param cdId
 * @returns 
 */
function fn_indexFsysCodeUDtl(cdId) {

	var callUrl = "<c:url value = "/com/PageLink.do"/>"

	requestUtil.mdPop({
		popUrl : callUrl+"?link="+"frame/fsys/code/fsysCodeUDtlPop&srcCdId="+cdId,
		height: 700,
        width: 1000,
        title: '그룹 코드 상세',
        divId : 'fsysCodeUDtlPop'
	});

}

/**
 * @!@ 상세 코드 등록
 * @param cd
 * @returns 
 */
function fn_indexFsysCodeDtlRDtl() {

	var callUrl = "<c:url value = "/com/PageLink.do"/>"

	requestUtil.mdPop({
		popUrl : callUrl+"?link="+"frame/fsys/code/fsysCodeDtlRDtlPop&&srcCdId="+$("#srcCdId").val(),
		height: 700,
        width: 1000,
        title: '상세 코드 등록',
        divId : 'fsysCodeDtlRDtlPop'
	});

}

/**
 * @!@ 상세 코드 상세
 * @param cd
 * @returns 
 */
function fn_indexFsysCodeDtlUDtl(cd) {

	var callUrl = "<c:url value = "/com/PageLink.do"/>"

	requestUtil.mdPop({
		popUrl : callUrl+"?link="+"frame/fsys/code/fsysCodeDtlUDtlPop&srcCd="+cd+"&srcCdId="+$("#srcCdId").val(),
		height: 700,
        width: 1000,
        title: '상세 코드 상세',
        divId : 'fsysCodeDtlUDtlPop'
	});

}

</script>
</head>
<body>
          <div id="con_wrap">
               <div id="contents_info">
                      <!--- contnets  적용 ------>
                    <div>
                         <div class="loca">
		                    <div class="ttl">코드 관리</div>
		                    <div class="loca_list">Home > 시스템 관리 > 코드 관리</div>
		                  </div>
                          <div class="sub2">
                             <!--------------검색------------------>
                             <form name="searchForm" id="searchForm" method="post" onsubmit="return false;">
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
                                         	<th scope="row" class="hcolor">그룹코드명</th>
								           <td>
								               <input type="text" id="srcCdIdNm" name="srcCdIdNm" title="그룹코드명" class="inpw80" maxlength="50"/>
								           </td>
								           <th scope="row" class="hcolor">사용여부</th>
								           <td colspan="3">
								               <select id="srcUseYn" name="srcUseYn" title="사용여부" onchange="fn_queryMList(1)" class="selw6">
								                   <option value="" selected>전체</option>
								                   <option value="Y">사용</option>
								                   <option value="N">미사용</option>
								               </select>
								           </td>
                                      </tr>
                                   </thead>
                                </table>
                              </div>
                            <div class="btn_c">
                                  <ul>
                                     <li><a href="javascript:fn_indexFsysCodeRDtl();" class="RdButton">등록</a></li>
                                     <li><a href="javascript:fn_queryMList(1);" class="gyButton">조회</a></li>
                                     <!-- <li><a href="javascript:void(0);" class="myButton">엑셀</a></li> -->
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
                                              <col width="20%">
                                              <col width="20%">
                                              <col width="20%">
                                              <col width="20%">
                                              <col width="20%">
                                          </colgroup>
                                            <thead>
                                              <tr>
                                                 <th scope="col">그룹코드ID</th>
                                                 <th scope="col">그룹코드명</th>
                                                 <th scope="col">그룹코드설명</th>
                                                 <th scope="col">사용여부</th>
                                                 <th scope="col">상세코드관리</th>
                                              </tr>
                                            </thead>
                                            <tbody>
												<tr><td colspan="5">조회 결과가 없습니다.</td></tr>
                                          </tbody>
                                     </table>
                             </div>
                              <!--------------//목록---------------------->
                             
                             <!-----------------------페이징----------------------->
                             <div id="page_navi" class="page_wrap"></div>
                               <!-----------------------//페이징----------------------->
                          
                          </div>
                          
                          <!-- @!@ 상세 -->
                          <div class="sub3">
                             <!--------------검색------------------>
                             <form name="searchForm2" id="searchForm2" method="post">
                             <input type="hidden" class="" id="srcCdId" name="srcCdId" value=""/>
                            <div class="t_head">
                                  <table class="tbl_type_hd2" border="1" cellspacing="0" onkeydown="">
                                        <caption>검색</caption>
                                         <colgroup>
                                            <col width="20%">
                                            <col width="30%">
                                            <col width="20%">
                                            <col width="30%">
                                         </colgroup>
                                   <thead>
                                      <tr>
                                           <th scope="row" class="hcolor">그룹코드</th>
								           <td>
								               <span id="searchCdId"></span>
								           </td>
								           <th scope="row" class="hcolor">그룹코드명</th>
								           <td colspan="3">
								               <span id="searchCdIdNm"></span>
								           </td>
                                      </tr>
                                   </thead>
                                </table>
                                <div class="btn_c">
                                  <ul>
                                     <!-- <li><a href="javascript:fn_queryMList(1);" class="myButton">조회</a></li> -->
                                     <li><a href="javascript:fn_indexFsysCodeDtlRDtl();" class="RdButton" style="display: none;" id="indexFsysCodeDtlRDtl">상세코드등록</a></li>
                                     <!-- <li><a href="javascript:void(0);" class="myButton">엑셀</a></li> -->
                                  </ul>
                               </div>
                              </div>
                              
                               </form>
                            <!--------------//검색------------------>
                            
                            <!--------------결과------------------>
                             <div class="r_num">| 결과  <strong style="color:#C00" id="totalCount2">0</strong>건</div>
                             
                             <!--------------목록---------------------->
                             <div class="t_list">  
                                  <table id="listTab2" class="tbl_type" border="1" cellspacing="0" >
                                        <caption>목록</caption>
                                          <colgroup>
                                              <col width="25%">
                                              <col width="25%">
                                              <col width="25%">
                                              <col width="25%">
                                          </colgroup>
                                            <thead>
                                              <tr>
                                                 <th scope="col">상세코드ID</th>
                                                 <th scope="col">상세코드명</th>
                                                 <th scope="col">상세코드설명</th>
                                                 <th scope="col">사용여부</th>
                                              </tr>
                                            </thead>
                                            <tbody>
												<tr><td colspan="4">조회 결과가 없습니다.</td></tr>
                                          </tbody>
                                     </table>
                             </div>
                              <!--------------//목록---------------------->
                             
                             <!-----------------------페이징----------------------->
                             <div id="page_navi2" class="page_wrap"></div>
                               <!-----------------------//페이징----------------------->
                          
                          </div>
                          
                         
                    </div>
            </div>
                 <!---  //contnets  적용 ------>
  </div>
  
  <div id="fsysCodeRDtlPop"></div>
  <div id="fsysCodeUDtlPop"></div>
  <div id="fsysCodeDtlRDtlPop"></div>
  <div id="fsysCodeDtlUDtlPop"></div>
  
</body>
</html>