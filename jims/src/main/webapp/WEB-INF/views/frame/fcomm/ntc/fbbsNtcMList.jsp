<%--
/**
 * <pre>
 * 1. 작성일 : 2021. 4. 16.
 * 2. 작성자 : Leeji
 * 3. 화면명 : 공지사항 목록
 * 4. 설명 : 공지사항 목록
 * </pre>
 */
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>

<html>
<head>
<%@ include file="/WEB-INF/views/frame/fcom/jimsCommon.jsp" %>


<%-- <link rel="stylesheet" href="<c:url value="/css/com/bootstrap.css" />">
<script type="text/javascript" src="<c:url value='/js/jquery.twbsPagination.min.js'/>"></script> --%>

<script type="text/javaScript" language="javascript">
var tabId;
/* var perPageNum; */

$(document).ready(function() {

	tabId = parent.$('li[id*=li_tabs-M][aria-expanded=true]').attr('aria-controls');
	//requestUtil.getSearchForm({targetFormId:"searchForm", callbackNm:"fn_noticeSearch"});
	gfn_init({startFnNm:'', param:{targetFormId:"searchForm", callbackNm:"fn_noticeSearch"}, codeSet:"N"});
	
});

/**
 * 공지사항목록 조회
 * @param {string} page 항목에 대한 고유 식별자 
 * @returns fn_callBack
 */
function fn_noticeSearch(page){
	var callUrl = "<c:url value='/fbbs/ntc/queryFBbsNtcMList.do'/>";
	$("#page").val(page);
	requestUtil.searchList({callUrl:callUrl,srhFormNm:'searchForm',callbackNm:'fn_callback',page:$("#page").val(),perPageNum:10});
}

 /**
  * 공지사항목록 조회 콜백함수
  * @param {object} data 조회한 결과데이터
  * @returns
  */
function fn_callback(data){

	$("#fbbsNtcList").empty();
	$("#totalcnt").text(data.totalCount);
	
  	if(data.fbbsNtcList.length < 1){
		$('#fbbsNtcList').append('<tr><td colspan="7">조회된 결과가 없습니다.</td></tr>');
	}else{
		$.each(data.fbbsNtcList, function(index, item){

			$('#fbbsNtcList').append("<tr><td>"+(data.page == 1 ? index+1 : ((data.page-1)*data.perPageNum)+index+1)+"</td><td>"+item.putupSno+"</td><td>"+item.fixNoticeYn+"</td><td style='max-width: 200px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis'>"
					+"<a href='#' onclick=javascript:fn_detail("+item.putupSno+")><u><strong><font color=green>"+item.title+"</font></strong></u></a>"+"</td><td>"
					+item.fileCount+"</td><td>"+item.putupDt+"</td><td>"+item.regrNm+"</td><td>"+item.selectNum+"</td></tr>");
		});
	}
  	
  	data.__callFuncName__ ="fn_noticeSearch";
	data.__naviID__ ="page_navi";
	pageUtil.setPageNavi(data);
}

/**
 * 자유게시판 등록화면이동
 * @param
 * @returns
 */
function fn_insNotice() {
	requestUtil.setSearchForm("searchForm");
	var src = "<c:url value = "/fbbs/ntc/indexFBbsNtcRDtl.do"/>";	
	parent.$('#'+tabId+' iframe').attr('src', src);
}

/**
* 자유게시판 상세화면이동
* @param {String} putupSno
* @returns
*/
function fn_detail(putupSno){
	requestUtil.setSearchForm("searchForm");
	var src = "<c:url value = "/fbbs/ntc/indexFBbsNtcUDtl.do"/>?putupSno="+putupSno;
	parent.$('#'+tabId+' iframe').attr('src', src);
}

</script>
</head>
<body>

<div id="con_wrap">
       <div id="contents_info">
              <!--- contnets  적용 ------>
            <div>
                 <div class="loca">
                    <div class="ttl">공지사항</div>
                    <div class="loca_list"></div>
                  </div>
                 
                  <div class="sub">
                     <!--------------검색------------------>
                    <form id="searchForm" name="searchForm" onsubmit="return false;">
                    	<input type="hidden" class="" id="page" name="page" value="1"/>
                    <div class="t_head">
				        <input type="hidden" id="boardKind" class="b_put"  name="boardKind"   value="C23001"/>
				        <input type="hidden" id="userGb"   name="userGb"   value="C00000"/>
				        
                          <table class="tbl_type_hd" border="1" cellspacing="0" onkeydown="if(gfn_enterChk())fn_noticeSearch(1);">
                                <caption>검색</caption>
                                 <colgroup>
                                   <col width="15%">
                                   <col width="85%">                                 
                                 </colgroup>
                           <thead>                           
                           	  <tr>
					            <th scope="col" class="hcolor">
						            <select id="searchType" name="searchType" style=width:80px;vertical-align:center;">
						                <option value="all" selected="selected">전체</option>
						                <option value="searchTitle">제목</option>
						                <option value="searchCnts">내용</option>
						                <option value="searchRegerNm">작성자</option>
						            </select>
					            </th>
					            <td scope="col" colspan="3">
					               <input type="text" name="searchValue" id="searchValue" style="width:400px;"/>
					            </td>
					          </tr>    
                           </thead>
                        </table>
                      
                      </div>
                      <div  class="btn_c">
					       <ul>
                             <li><a href="javascript:void(0);" class='RdButton' onclick="fn_insNotice(); return false;">등록</a></li>
                             <li><a href="javascript:void(0);" class='myButton' onclick="fn_noticeSearch(1); return false;">조회</a></li>
                           </ul>   
					  </div>
                      
                    <!--------------//검색------------------>
                    
                     <!--------------결과------------------>
                     <div class="r_num">| 결과  <strong id="totalcnt" style="color:#C00"></strong>건</div>
                     <!-- <div class="bo_num">
                         <select id="perPageNum" name="perPageNum">
			               <option value="5">5개씩</option>
			               <option value="10" selected="selected">10개씩</option>		               
			             </select>
                     </div> -->
                     
                     <!--------------목록---------------------->
                     <div class="t_list">  
                          <table class="tbl_type" border="1" cellspacing="0" >
                                <caption>공지사항관리</caption>
                                  <colgroup>
                                      <col width="3%">
                                      <col width="10%">
                                      <col width="10%">
                                      <col width="37%">
                                      <col width="10%">
                                      <col width="10%">
                                      <col width="10%">
                                      <col width="10%">
                                      </colgroup>
                                    <thead>
                                      <tr>
                                         <th scope="col"></th>
                                         <th scope="col">순번</th>
                                         <th scope="col">고정공지</th>
                                         <th scope="col">제목</th>
                                         <th scope="col">첨부파일</th>
                                         <th scope="col">등록일</th>
                                         <th scope="col">작성자</th>
                                         <th scope="col">조회수</th>
                                      </tr>
                                    </thead>
                                    <tbody id="fbbsNtcList">
                                    </tbody>
                             </table>
                     </div>
                      <!--------------//목록---------------------->
                     
                     <!-----------------------페이징----------------------->
                    <div id="page_navi" class="page_wrap"></div>        
                    <!-- <div id='pagination-div' class="page_wrap"></div>               -->
                       <!-----------------------//페이징----------------------->
                  </form>
                  </div>
                 
            </div>
    </div>
</div>

</body>
</html>