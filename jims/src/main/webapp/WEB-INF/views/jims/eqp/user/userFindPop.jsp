<%--
/**
 * <pre>
 * 1. 작성일 : 2021. 7. 20.
 * 2. 작성자 : sjw7240
 * 3. 화면명 : 사용자 조회 팝업
 * 4. 설명 : 사용자 조회 팝업
 * </pre>
 */
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javaScript" language="javascript">
$(document).ready(function() {
  fn_crgrSearch(1);
});

function fn_codeCallback(){
	$('#searchCrimeCd').prepend('<option value="" selected>전체</option>');
}

/**
 * 사용자 리스트 조회
 * @param {string} page 항목에 대한 고유 식별자 
 * @returns fn_callBack
 */
function fn_crgrSearch(page){
	var callUrl = "<c:url value = "/eqp/user/queryEqpUserPop.do"/>";
	requestUtil.searchList({callUrl:callUrl,srhFormNm:'searchForm',callbackNm:'fn_callBack',page:page,perPageNum:10});
}

/**
 * 사건조회화면목록 조회 콜백함수
 * @param {object} data 조회한 결과데이터
 * @returns
 */
function fn_callBack(data){

	$("#popList").empty();
	$("#totalcnt").text(data.totalCount);

	if(data.popList.length < 1){
		$('#popList').append('<tr><td colspan="8">조회된 결과가 없습니다.</td></tr>');
	}else{
	
		$.each(data.popList, function(index, item){
			
			$('#popList').append(
      "<tr><td>"+item.rn+"</td>"+
			"<td>"+item.deptNm+"</td>"+
			"<td>"+item.userNm+"</td>"+
			//"<td>"+item.telNo+"</td>"+
			"<td>"+item.hpTelNo+"</td>"+
      		"<td><button class=button35 id='setBtn"+ decodeURIComponent(item.rn) +"'>선택</button></td></tr>"
      );
      $("#setBtn" + decodeURIComponent(item.rn)).attr("onclick", "fn_setCrgr('"+ decodeURIComponent(item.insttCd) +"','" + decodeURIComponent(item.deptNm) + "','"+ decodeURIComponent(item.userNm) +"','"+ decodeURIComponent(item.regUserId) +"')");
		 });
	}

	data.__callFuncName__ ="fn_crgrSearch";
	data.__naviID__ ="page_navi";
	pageUtil.setPageNavi(data);
}

function fn_setCrgr(insttCd,deptNm,userNm,userId){
	 
   var paramObj = new Object();
   var divId = 'userFindPop';
     paramObj.insttCd = gfn_nullRtnSpace(insttCd);
     paramObj.deptNm = gfn_nullRtnSpace(deptNm);
     paramObj.userNm = gfn_nullRtnSpace(userNm); 
     paramObj.userId = gfn_nullRtnSpace(userId);
   fn_popCallBack(paramObj,divId);
 }

</script>
</head>
<body>
<div id="con_wrap_pop">
<div class="contents">
       <div id="contents_info">
              <!--- contnets  적용 ------>
            <div class="window_popup">
                  <div><h2>사용자 조회</h2></div>
                 
                  <div class="sub">
                     <!--------------검색------------------>
                    <form name="searchForm" id="searchForm" method="post" onsubmit="return false;">
                    <div class="t_head">
                          <table class="tbl_type_hd" border="1" cellspacing="0" onkeydown="if(gfn_enterChk())fn_crgrSearch(1);">
                                <caption>검색</caption>
                                 <colgroup>
                                   <col width="35%">
                                   <col width="65%">                      
                                 </colgroup>
                           <thead>                           
                           	  <tr>
					            <th scope="col" class="hcolor">사용자명</th>
					            <td scope="col">
					               <input class="input20" type="text" name="searchUserNm" id="searchUserNm"/>
					            </td>
					          </tr>
                           </thead>
                        </table>
                        <div  class="btn_c">
				       	<ul>
                          <li><a href="javascript:void(0);" class='gyButton' onclick="fn_crgrSearch(1); return false;">조회</a></li>
                        </ul>   
					  </div>
                      
                      </div>
                     </form>
                      
                    <!--------------//검색------------------>
                    
                     <!--------------결과------------------>
                     <div class="r_num">| 결과  <strong id="totalcnt" style="color:#C00"></strong>건</div>                     
                     <!--------------목록---------------------->
                     <div class="t_list">  
                          <table class="tbl_type" border="1" cellspacing="0" >
                                <caption>사건조회</caption>
                                  <colgroup>
                                      <col width="5%">                                      
                                      <col width="15%">
                                      <col width="25%">
                                      <col width="25%">
                                      <col width="25%">
                                   </colgroup>
                                    <thead>
                                      <tr>
                                         <th scope="col">순번</th>
                                         <th scope="col">부서명</th>
                                         <th scope="col">사용자명</th>
                                         <th scope="col">전화번호</th>
                                         <th scope="col">선택</th>                                       
                                      </tr>
                                    </thead>
                                    <tbody id="popList">
                                    </tbody>
                             </table>
                     </div>
                      <!--------------//목록---------------------->
                     
                     <!-----------------------페이징----------------------->
                     <div id='page_navi' class="page_wrap"></div>                            
                       <!-----------------------//페이징----------------------->                  
                  </div>
                 
            </div>
    </div>
    </div>
</div>
</body>
</html>