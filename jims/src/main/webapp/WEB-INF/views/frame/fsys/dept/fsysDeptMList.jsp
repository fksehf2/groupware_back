<%--
/**
 * <pre>
 * 1. 작성일 : 2021. 4. 14. 오전 10:11:33
 * 2. 작성자 : ilyong
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
	tabId = parent.$('li[id*=li_tabs-M][aria-expanded=true]').attr('aria-controls');		

   gfn_init({startFnNm:'fn_queryDeptMList', param:1, codeSet:"N"});
});

/**
 * @!@ 부서 관리 리스트 조회
 * @param {int} page
 * @returns 
 */
function fn_queryDeptMList(page){
	
	var callUrl = "<c:url value='/fsys/dept/queryFsysDeptMList.do'/>";
	
	requestUtil.searchList({callUrl:callUrl, srhFormNm:'searchForm', callbackNm:'fn_queryDeptMListCallback', page:page, perPageNum:10});
	
	fn_setSearchInsttCd("", "");
}

/**
 * @!@ 그룹 코드 관리 리스트 조회 콜백
 * @param {json} data
 * @returns 
 */
function fn_queryDeptMListCallback(data){
	
	var list = data.list;
	$("#listTab > tbody").empty();
	$("#totalcnt").text(data.totalCount);
	
	if(list.length < 1){
		$('#listTab > tbody').append('<tr><td colspan="7">조회된 결과가 없습니다.</td></tr>');
	}else{
		$.each(list,function(idx,row){
			var append = "";
			var fullDeptNm = "";
			fullDeptNm = gfn_nullRtnSpace(row.deptNm)+' '+ gfn_nullRtnSpace(row.teamNm);
			console.log("=======fullDeptNm======>>>"+fullDeptNm);
			
			append += "<tr>";

			append += "<td><a href='javascript:void(0)' onclick=javascript:fn_indexFsysDeptUDtl('"+row.insttCd+"')><u>"+row.insttCd+"</u></a></td>";
			append += "<td>" + gfn_nullRtnSpace(row.insttNm) + "</td>";
			append += "<td>" + gfn_nullRtnSpace(row.deptNm) + "</td>";
			append += "<td>" + gfn_nullRtnSpace(row.teamNm) + "</td>";
			append += "<td>" + gfn_nullRtnSpace(row.deptLvl) + "</td>";
			append += "<td>" + gfn_nullRtnSpace(row.useYnNm) + "</td>";
// 			append += "<td>" + fullDeptNm + "</td>";
			append += "<td><a href='javascript:fn_setSearchInsttCd(\""+row.insttCd+"\", \""+fullDeptNm+"\");' class='byButton'>팀원관리</a></td>";

			append += "</tr>";
	        $("#listTab > tbody").append(append);
	 	});
	}
	
	
	
	data.__callFuncName__ ="fn_queryDeptMList";
	data.__naviID__ ="page_navi";
	pageUtil.setPageNavi(data);
	
	
}

/**
 * @!@ 상세 코드 조회 조건 셋팅
 * @param {String} cdId, cdIdNm
 * @returns 
 */
function fn_setSearchInsttCd(insttCd, insttCdNm){
 	//alert("===insttCd==>>"+insttCdNm); 
	$("#searchInsttCd").html(insttCd);
	$("#searchInsttCdNm").html(insttCdNm);
	
	if(insttCd != ""){
		$("#btnInsDeptUser").show();
	}else{
		$("#btnInsDeptUser").hide();
	}
	
	$("#searchInsttCd2").val(insttCd);
	$("#searchInsttCdNm2").val(insttCdNm);
	
	fn_queryMList2(1);
}

/**
 * @!@ 부서별 사용자 리스트 조회
 * @param {int} page
 * @returns 
 */
function fn_queryMList2(page){
	
	var callUrl = "<c:url value='/fsys/dept/queryFsysDeptUserMList.do'/>";
	
	requestUtil.searchList({callUrl:callUrl, srhFormNm:'searchForm2', callbackNm:'fn_queryMList2Callback', page:page, perPageNum:10});
	
}

/**
 * @!@ 상세 코드 관리 리스트 조회 콜백
 * @param {json} data
 * @returns 
 */
function fn_queryMList2Callback(data){
	
	var list = data.list;
	$("#listTab2 > tbody").empty();
	
	$("#totalcnt2").text(data.totalCount);
	
	if(list.length < 1){
		$('#listTab2 > tbody').append('<tr><td colspan="5">조회된 결과가 없습니다.</td></tr>');
	}else{
		$.each(list,function(idx,row){
			var append = "";
			append += "<tr>";

			append += "<td><a href='javascript:void(0)' onclick=javascript:fn_indexFsysDeptUserUDtl('"+row.userId+"','"+row.insttCd+"')><u>"+row.userId+"</u></a></td>";
			append += "<td>" + gfn_nullRtnSpace(row.userNm) + "</td>";
			append += "<td>" + gfn_nullRtnSpace(row.mainCrgrYn) + "</td>";
			append += "<td>" + gfn_nullRtnSpace(row.aprvAuthYn) + "</td>";
// 			append += "<td>" + gfn_nullRtnSpace(row.useYnNm) + "</td>";
			append += "<td>" + gfn_nullRtnSpace(row.dispRegDt) + "</td>"; 

			append += "</tr>";
	        $("#listTab2 > tbody").append(append);
	 	});
	}
	
	
	
	data.__callFuncName__ ="fn_queryMList2";
	data.__naviID__ ="page_navi2";
	
	//pageUtil.setCalFuc("fn_queryMList2");
	//pageUtil.setNaviID("page_navi2");
	pageUtil.setPageNavi(data);
	
	
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
		height: 500,
        width: 1000,
        title: '그룹 코드 등록',
        divId : 'fsysCodeRDtlPop'
	});

}

/**
 * @!@ 부서 관리 > 부서 등록
 * @param
 * @returns 
 */
function fn_indexFsysDeptRDtl() {

	var callUrl = "<c:url value = "/com/PageLink.do"/>"

	requestUtil.mdPop({
		popUrl : callUrl+"?link="+"frame/fsys/dept/fsysDeptRDtlPop",
		height: 500,
        width: 1000,
        title: '부서 등록',
        divId : 'fsysDeptRDtlPop'
	});

}

/**
 * @!@ 부서 상세 정보
 * @param cdId
 * @returns 
 */
function fn_indexFsysDeptUDtl(insttCd) {

	var callUrl = "<c:url value = "/com/PageLink.do"/>"

	requestUtil.mdPop({
		popUrl : callUrl+"?link="+"frame/fsys/dept/fsysDeptUDtlPop&insttCd="+insttCd,
		height: 500,
        width: 1000,
        title: '부서 상세',
        divId : 'fsysDeptUDtlPop'
	});

}

/**
 * @!@ 부서별 사용자 정보 상세
 * @param cd
 * @returns 
 */
function fn_indexFsysDeptUserUDtl(userId,insttCd) {

	var callUrl = "<c:url value = "/com/PageLink.do"/>"

	requestUtil.mdPop({
		popUrl : callUrl+"?link="+"frame/fsys/dept/fsysDeptUserUDtlPop&insttCd="+insttCd+"&userId="+userId,
		height: 700,
        width: 1000,
        title: '부서별 사용자 상세 정보',
        divId : 'fsysDeptUserUDtlPop'
	});

}

/**
 * @!@ 부서별 사용자 등록
 * @param cd
 * @returns 
 */
function fn_insDeptUserPop() {

	var callUrl = "<c:url value = "/com/PageLink.do"/>";
	var paramInsttCdNm = encodeURIComponent($("#searchInsttCdNm2").val());
	

	requestUtil.mdPop({
		popUrl : callUrl+"?link="+"frame/fsys/dept/fsysDeptUserRDtlPop&paramInsttCd="+$("#searchInsttCd2").val()+"&paramInsttCdNm="+paramInsttCdNm,
		height: 650,
        width: 1000,
        title: '부서별 사용자 등록',
        divId : 'fsysDeptUserRDtlPop'
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
		                    <div class="ttl">부서관리</div>
		                    <div class="loca_list">Home > 시스템 관리 > 부서관리</div>
		                  </div>
                          <div class="sub" style="float: left; width: 49%;">
                             <!--------------검색------------------>
                             <form name="searchForm" id="searchForm" method="post" onsubmit="return false;">
                            <div class="t_head">
                                  <table class="tbl_type_hd" border="1" cellspacing="0" onkeydown="if(gfn_enterChk())fn_queryDeptMList(1);">
                                        <caption>검색</caption>
                                         <colgroup>
                                            <col width="20%">
                                            <col width="30%">
                                            <col width="20%">
                                            <col width="30%">
                                         </colgroup>
                                   <thead>
                                      <tr>
                                         	<th scope="row" class="hcolor">기관명</th>
								           <td>
								               <input type="text" id="searchInsttNm" name="searchInsttNm" title="기관명" style="width:220px;"/>
								           </td>
								           <th scope="row" class="hcolor">사용여부</th>
								           <td colspan="3">
								               <select id="searchUseYn" name="searchUseYn" onchange="fn_queryDeptMList(1)" style="width:100px;">
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
                                     <li><a href="javascript:fn_indexFsysDeptRDtl();" class="RdButton">등록</a></li>
                                     <li><a href="javascript:fn_queryDeptMList(1);" class="gyButton">조회</a></li>
                                     <!-- <li><a href="javascript:void(0);" class="myButton">엑셀</a></li> -->
                                  </ul>
                               </div>
                               </form>
                            <!--------------//검색------------------>
                            
                            <!--------------결과------------------>
                             <div class="r_num">| 결과  <strong id="totalcnt" style="color:#C00"></strong>건</div>
                             
                             <!--------------목록---------------------->
                             <div class="t_list">  
                                  <table id="listTab" class="tbl_type" border="1" cellspacing="0" >
                                        <caption>목록</caption>
                                          <colgroup>
                                              <col width="14%">
                                              <col width="15%">
                                              <col width="15%">
                                              <col />
                                              <col width="8%">
                                              <col width="8%">
                                              <col width="14%">
                                          </colgroup>
                                            <thead>
                                              <tr>
                                                 <th scope="col">부서코드</th>
                                                 <th scope="col">기관명</th>
                                                 <th scope="col">과명</th>
                                                 <th scope="col">팀명</th>
                                                 <th scope="col">부서레벨</th>
                                                 <th scope="col">사용여부</th>
                                                 <th scope="col">사용자관리</th>
<!--                                                  <th scope="col">부서별<br>사용자관리</th> -->
                                              </tr>
                                            </thead>
                                            <tbody>

                                          </tbody>
                                     </table>
                             </div>
                              <!--------------//목록---------------------->
                             
                             <!-----------------------페이징----------------------->
                             <div id="page_navi" class="page_wrap"></div>
                               <!-----------------------//페이징----------------------->
                          
                          </div>
                          
                          <!-- @!@ 상세 -->
                          <div class="sub3" style="float: right; width: 49%;">
                             <!--------------검색------------------>
                             <form name="searchForm2" id="searchForm2" method="post">
                             <input type="hidden" class="" id="searchInsttCd2" name="searchInsttCd2" value=""/>
                             <input type="hidden" class="" id="searchInsttCdNm2" name="searchInsttCdNm2" value=""/>
                            <div class="t_head">
                                  <table class="tbl_type_hd2" border="1" cellspacing="0" >
                                        <caption>검색</caption>
                                         <colgroup>
                                            <col width="20%">
                                            <col width="20%">
                                            <col width="20%">
                                            <col width="40%">
                                         </colgroup>
                                   <thead>
                                      <tr>
                                         	<th scope="row" class="hcolor">부서코드</th>
								           <td>
								               <span id="searchInsttCd"></span>
								           </td>
								           <th scope="row" class="hcolor">부서명</th>
								           <td colspan="3">
								               <span id="searchInsttCdNm"></span>
								           </td>
                                      </tr>
                                   </thead>
                                </table>
                              </div>
                            <div class="btn_c">
                                  <ul>
                                     <!-- <li><a href="javascript:fn_queryMList(1);" class="myButton">조회</a></li> -->
                                     <li><a href="javascript:fn_insDeptUserPop();" class="RdButton" style="display: none;" id="btnInsDeptUser">사용자 등록</a></li>
                                     <!-- <li><a href="javascript:void(0);" class="myButton">엑셀</a></li> -->
                                  </ul>
                               </div>
                               </form>
                            <!--------------//검색------------------>
                            
                            <!--------------결과------------------>
                             <div class="r_num">| 결과  <strong id="totalcnt2" style="color:#C00"></strong>건</div>
                             
                             <!--------------목록---------------------->
                             <div class="t_list">  
                                  <table id="listTab2" class="tbl_type" border="1" cellspacing="0" >
                                        <caption>목록</caption>
                                          <colgroup>
                                              <col width="16%">
                                              <col />
                                              <col width="16%">
                                              <col width="16%">
<!--                                               <col width="16%"> -->
                                              <col width="16%">
                                          </colgroup>
                                            <thead>
                                              <tr>
                                                 <th scope="col">사용자ID</th>
                                                 <th scope="col">사용자명</th>
                                                 <th scope="col">주담당자여부</th>
                                                 <th scope="col">승인권자여부</th>
<!--                                                  <th scope="col">사용여부</th> -->
                                                 <th scope="col">등록일자</th>
                                              </tr>
                                            </thead>
                                            <tbody>

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
  
  <div id="fsysDeptRDtlPop"></div>
  <div id="fsysDeptUDtlPop"></div>
  <div id="fsysDeptUserRDtlPop"></div>
  <div id="fsysDeptUserUDtlPop"></div>
  <div id="fsysUserFindMListPop"></div> 
  
</body>
</html>