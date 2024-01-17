<%--
/**
 * <pre>
 * 1. 작성일 : 2021. 4. 14. 오전 10:11:33
 * 2. 작성자 : jij
 * 3. 화면명 : 프로그램 관리 리스트 화면
 * 4. 설명 : @!@ 프로그램 관리 리스트 화면 
 * </pre>
 */
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>

<html>
<head>
<%@ include file="/WEB-INF/views/frame/fcom/jimsCommon.jsp" %>


<script type="text/javaScript" language="javascript" defer="defer">
var tabId;

$(document).ready(function() {
	tabId = parent.$('li[id*=li_tabs-M][aria-expanded=true]').attr('aria-controls');
	//requestUtil.getSearchForm({targetFormId:"searchForm", callbackNm:"fn_queryMList"});
	gfn_init({startFnNm:'', param:{targetFormId:"searchForm", callbackNm:"fn_queryMList"}, codeSet:'N'});
});

/**
 * @!@ 프로그램 관리 리스트 조회
 * @param {int} page
 * @returns 
 */
function fn_queryMList(page){
	
	var callUrl = "<c:url value='/fsys/program/queryFsysProgramMList.do'/>";
	$("#page").val(page);
	requestUtil.searchList({callUrl:callUrl, srhFormNm:'searchForm', callbackNm:'fn_queryMListCallback', page:$("#page").val(), perPageNum:10});
	
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
	
			append += "<td><a href='javascript:void(0)' onclick=javascript:fn_indexFsysProgramUDtl('"+row.programId+"');return false;><u>"+row.programId+"</u></a></td>";
			append += "<td>" + row.programPath + "</td>";
			append += "<td>" + row.programNm + "</td>";
			append += "<td>" + row.programExpl + "</td>";
			append += "<td>" + row.url + "</td>";
			append += "<td>" + row.useYnNm + "</td>";
	
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
 * @!@ 프로그램 관리 등록 화면 이동
 * @param
 * @returns 
 */
function fn_indexFsysProgramRDtl(){
	requestUtil.setSearchForm("searchForm");
	parent.$('#'+tabId+' iframe').attr('src', '<c:url value="/fsys/program/indexFsysProgramRDtl.do"/>');
}

/**
 * @!@ 프로그램 관리 수정 화면 이동
 * @param programId
 * @returns 
 */
function fn_indexFsysProgramUDtl(programId){
	requestUtil.setSearchForm("searchForm");
	parent.$('#'+tabId+' iframe').attr('src', '<c:url value="/fsys/program/indexFsysProgramUDtl.do"/>?programId='+programId);
}

</script>
</head>
<body>
          <div id="con_wrap">
               <div id="contents_info">
                      <!--- contnets  적용 ------>
                    <div>
                         <div class="loca">
		                    <div class="ttl">프로그램 관리</div>
		                    <div class="loca_list">Home > 시스템 관리 > 프로그램관리</div>
		                  </div>
                          <div class="sub">
                             <!--------------검색------------------>
                             <form name="searchForm" id="searchForm" method="post">
                             	<input type="hidden" class="" id="page" name="page" value="1"/>
                            <div class="t_head">
                                  <table class="tbl_type_hd" border="1" cellspacing="0" onkeydown="if(gfn_enterChk())fn_queryMList(1);">
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
                                         <th scope="col" class="hcolor">프로그램ID</th>
                                         <th scope="col">
                                         	<input type="text" id="srcProgramId" name="srcProgramId" title="프로그램ID" maxlength="200" class="inpw60"/>
                                         </th>
                                         <th scope="col" class="hcolor">프로그램명</th>
                                         <th scope="col">
                                         	<input type="text" id="srcProgramExpl" name="srcProgramExpl" title="프로그램명" maxlength="200" class="inpw60"/>
										 </th>
                                         <th scope="col" class="hcolor">사용여부</th>
                                         <th scope="col">
                                           <select id="srcUseYn" name="srcUseYn" onchange="fn_queryMList(1);" class="selw6" title="사용여부">
                                            <option value="" selected>전체</option>
                                            <option value="Y">사용</option>
                                            <option value="N">미사용</option>
                                           </select>
                                         </th>
                                       </tr>
                                   </thead>
                                </table>
                              </div>
                            <div class="btn_c">
                                  <ul>
                                     <li><a href="javascript:void(0)" onclick="fn_indexFsysProgramRDtl();return false;" class="RdButton">등록</a></li>
                                     <li><a href="javascript:void(0)" onclick="fn_queryMList(1);return false;" class="gyButton">조회</a></li>
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
                                              <col width="15%">
                                              <col width="15%">
                                              <col width="15%">
                                              <col width="15%">
                                              <col width="25%">
                                              <col width="15%">
                                              </colgroup>
                                            <thead>
                                              <tr>
                                                 <th scope="col">프로그램ID</th>
                                                 <th scope="col">프로그램PATH</th>
                                                 <th scope="col">프로그램명(EN)</th>
                                                 <th scope="col">프로그램명(KO)</th>
                                                 <th scope="col">URL</th>
                                                 <th scope="col">사용여부</th>
                                              </tr>
                                            </thead>
                                            <tbody>
												<tr><td colspan="6">조회 결과가 없습니다.</td></tr>
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
                 <!---  //contnets  적용 ------>
  </div>
</body>
</html>