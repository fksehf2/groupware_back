<%--
/**
 * <pre>
 * 1. 작성일 : 2021. 4. 14. 오전 10:11:33
 * 2. 작성자 : jij
 * 3. 화면명 : 메뉴관리 리스트 화면
 * 4. 설명 : @!@ 메뉴관리 리스트 화면 
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
	var codeInfo = [{cdId:'C01',selectId:'srcSysGrp',type:'1', callbackNm:'fn_ajaxCodeListCallback', sqlQueryId:''}];
	//fn_ajaxCodeList(codeInfo);
	gfn_init({startFnNm:'', param:codeInfo, codeSet:'Y'});
});

/**
 * @!@ 그룹죄명 코드 리스트 조회 콜백
 * @param {json} data 
 * @returns 
 */
function fn_ajaxCodeListCallback(data){
	$('#srcSysGrp').prepend("<option value='' selected>전체</option");
	requestUtil.getSearchForm({targetFormId:"searchForm", callbackNm:"fn_queryMList"});
}

/**
 * @!@ 메뉴 관리 리스트 조회
 * @param {int} page
 * @returns 
 */
function fn_queryMList(page){
	
	var callUrl = "<c:url value='/fsys/menu/queryFsysMenuMList.do'/>";
	$("#page").val(page);
	requestUtil.searchList({callUrl:callUrl, srhFormNm:'searchForm', callbackNm:'fn_queryMListCallback', page:$("#page").val(), perPageNum:10});
	
}

/**
 * @!@ 메뉴 관리 리스트 조회 콜백
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
	
			append += "<td><a href='javascript:void(0)' onclick=javascript:fn_indexFsysMenuUDtl('"+row.menuNo+"','"+row.sysGrp+"','"+row.menuLvl+"');return false;><u>"+row.menuNo+"</u></a></td>";
			append += "<td>" + gfn_nullRtnSpace(row.menuNm) + "</td>";
			append += "<td>" + gfn_nullRtnSpace(row.upperMenuNo) + "</td>";
			append += "<td>" + gfn_nullRtnSpace(row.upperMenuNm) + "</td>";
			append += "<td>" + gfn_nullRtnSpace(row.menuOrdr) + "</td>";
			append += "<td>" + gfn_nullRtnSpace(row.menuAuthor) + "</td>";
			append += "<td>" + gfn_nullRtnSpace(row.menuDisplayYn) + "</td>";
			append += "<td>" + gfn_nullRtnSpace(row.programId) + "</td>";
			append += "<td>" + gfn_nullRtnSpace(row.programExpl) + "</td>";
			append += "<td>" + gfn_nullRtnSpace(row.menuLvl) + "</td>";
	
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
 * @!@ 메뉴 관리 등록 화면 이동
 * @param
 * @returns
 */
function fn_indexFsysMenuRDtl(){
	if($("select[name=srcSysGrp] option:selected").val() == ""){
		fn_showUserPage("시스템그룹을 선택하세요.", function() {
		});
		$("select[name=srcSysGrp]").focus();
		return false;
	}

	requestUtil.setSearchForm("searchForm");
	$("#searchForm").append('<input type="hidden" class="" id="sysGrp" name="sysGrp" value="'+$("#srcSysGrp").val()+'" />');
	var param = "?"+$("#searchForm").serialize();
	parent.$('#'+tabId+' iframe').attr('src', '<c:url value="/fsys/menu/indexFsysMenuRDtl.do"/>'+param);
}

/**
 * @!@ 메뉴 관리 수정 화면 이동
 * @param {string} memuNo, sysGrp, menuLvl
 * @returns 
 */
function fn_indexFsysMenuUDtl(menuNo, sysGrp, menuLvl){
	requestUtil.setSearchForm("searchForm");
	$("#searchForm").append('<input type="hidden" class="" id="menuNo" name="menuNo" value="'+menuNo+'"/>');
	$("#searchForm").append('<input type="hidden" class="" id="sysGrp" name="sysGrp" value="'+sysGrp+'" />');
	$("#searchForm").append('<input type="hidden" class="" id="menuLvl" name="menuLvl" value="'+menuLvl+'" />');
	var param = "?"+$("#searchForm").serialize();
	parent.$('#'+tabId+' iframe').attr('src', '<c:url value="/fsys/menu/indexFsysMenuUDtl.do"/>'+param);
}

</script>
</head>
<body>
          <div id="con_wrap">
               <div id="contents_info">
                      <!--- contnets  적용 ------>
                    <div>
                         <div class="loca">
		                    <div class="ttl">메뉴 관리</div>
		                    <div class="loca_list">Home > 시스템 관리 > 메뉴관리</div>
		                  </div>
                          <div class="sub">
                             <!--------------검색------------------>
                             <form name="searchForm" id="searchForm" method="post" onsubmit="return false;">
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
                                         <th scope="col" class="hcolor">시스템그룹</th>
                                         <th scope="col">
											<select id="srcSysGrp" name="srcSysGrp" onchange="fn_queryMList(1);" class="selw10" title="시스템그룹">
												<%-- <option value="" <c:out value="${sysGrpInfo.cd==''?\"selected\":\"\"}"/> >전체</option>
												<c:forEach items="${sysGrpList}" var="sysGrpInfo" varStatus="status">
												<option value="<c:out value="${sysGrpInfo.cd}"/>"><c:out value="${sysGrpInfo.cdNm}"/></option>
												</c:forEach> --%>
											</select>
                                         </th>
                                         <th scope="col" class="hcolor">메뉴명</th>
                                         <th scope="col">
                                         	<input type="text" id="srcMenuNm" name="srcMenuNm" maxlength="200" title="메뉴명" value="<c:out value='${param.srcMenuNm}'/>" class="inpw50">
                                         </th>
                                         <th scope="col" class="hcolor">메뉴레벨</th>
                                         <th scope="col">
                                           <select id="srcMenuLvl" name="srcMenuLvl" onchange="fn_queryMList(1);" class="selw6" title="메뉴레벨">
                                            <option value="" selected>전체</option>
                                            <option value="lvl1">Level1</option>
                                            <option value="lvl2">Level2</option>
                                           </select>
                                         </th>
                                       </tr>
                                   </thead>
                                </table>
                              </div>
                            <div class="btn_c">
                                  <ul>
                                     <!-- <li><a href="javascript:void(0);" onclick="" class="myButton">엑셀</a></li> -->
                                     <li><a href="javascript:void(0);" onclick="fn_indexFsysMenuRDtl();return false;" class="RdButton">등록</a></li>
                                     <li><a href="javascript:void(0);" onclick="fn_queryMList(1);return false;" class="gyButton">조회</a></li>
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
                                              <col width="10%">
                                              <col width="10%">
                                              <col width="10%">
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
                                                 <th scope="col">메뉴ID</th>
                                                 <th scope="col">메뉴명</th>
                                                 <th scope="col">상위메뉴ID</th>
                                                 <th scope="col">상위메뉴명</th>
                                                 <th scope="col">정렬순서</th>
                                                 <th scope="col">쓰기권한</th>
                                                 <th scope="col">표시여부</th>
                                                 <th scope="col">프로그램ID</th>
                                                 <th scope="col">프로그램</th>
                                                 <th scope="col">LVL</th>
                                              </tr>
                                            </thead>
                                            <tbody>
												<tr><td colspan="10">조회 결과가 없습니다.</td></tr>
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