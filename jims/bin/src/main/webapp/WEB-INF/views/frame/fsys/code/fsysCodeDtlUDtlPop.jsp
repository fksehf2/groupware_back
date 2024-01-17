<%--
/**
 * <pre>
 * 1. 작성일 : 2021. 4. 14. 오전 10:11:33
 * 2. 작성자 : jij
 * 3. 화면명 : 상세 코드 수정, 삭제 팝업
 * 4. 설명 : @!@ 상세 코드 수정, 삭제 팝업
 * </pre>
 */
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Jenix 통합 관리 시스템</title>

<script type="text/javaScript" language="javascript" defer="defer">

$(document).ready(function() {
	fn_queryMDtl();
	$("#sortOrd").on("keyup",function(){
		gfn_new_number("sortOrd");
	})
});

/**
 * @!@ 상세 코드 상세 조회
 * @param
 * @returns 
 */
function fn_queryMDtl(){
	
	var callUrl = "<c:url value='/fsys/code/queryFsysCodeDtlMDtl.do'/>";
	
	requestUtil.search({callUrl:callUrl, srhFormNm:'insForm', callbackNm:'fn_queryMDtlCallback'});
	
}

/**
 * @!@ 상세 코드 상세 조회 콜백
 * @param
 * @returns 
 */
function fn_queryMDtlCallback(data){
}

/**
* @!@ 상세 코드 관리 수정
* @param
* @returns 
*/
function fn_updFsysCodeDtlUDtl(){

	if(!validUtil.checkInputValid({valFormID:'insForm'})){
		return;
	}
	
	fn_showModalPage("저장 하시겠습니까?", function() {
        var callUrl = "<c:url value='/fsys/code/updFsysCodeDtlUDtl.do'/>";
        requestUtil.save({callUrl:callUrl,srhFormNm:'insForm',callbackNm:'fn_updFsysCodeDtlUDtlCallback'});
	});
	
}

/**
 * @!@ 상세 코드 관리 수정 콜백
 * @param {string} data
 * @returns 
 */
function fn_updFsysCodeDtlUDtlCallback(data){
	 fn_queryMList2(1);
	 fn_dialogClose("fsysCodeDtlUDtlPop");
}
 
/**
* @!@ 상세 코드 관리 삭제
* @param
* @returns 
*/
function fn_delFsysCodeDtlUDtl(){
	
	fn_showModalPage("삭제 하시겠습니까?", function() {
        var callUrl = "<c:url value='/fsys/code/delFsysCodeDtlUDtl.do'/>";
        requestUtil.save({callUrl:callUrl,srhFormNm:'insForm',callbackNm:'fn_delFsysCodeDtlUDtlCallback'});
	});
	
}

/**
 * @!@ 상세 코드 관리 삭제 콜백
 * @param {string} data
 * @returns 
 */
function fn_delFsysCodeDtlUDtlCallback(data){
	 fn_queryMList2(1);
	 fn_dialogClose("fsysCodeDtlUDtlPop");
}
 
</script>

</head>
<body>
<div id="con_wrap_pop">
	<div class="contents">
               <div id="contents_info">
                      <!--- contnets  적용 ------>
                      <div class="window_popup">
                          <div class="sub_ttl">상세 코드 상세</div>
                         
                          <div class="sub">
                             <!--------------검색------------------>
                             <form name="insForm" id="insForm" method="post">
			                     <input type="hidden" id="srcCd" name="srcCd" value="<c:out value="${paramMap.srcCd}" />" />
			                     <input type="hidden" id="srcCdId" name="srcCdId" value="<c:out value="${paramMap.srcCdId}" />" />
			                      <div class="t_list">
					                 <table class="iptTblX">
						               <caption>수정</caption>
						               <colgroup>
							             <col width="20%" />
							             <col width="*" />
						               </colgroup>
						               <tbody>
						               		<tr>
						                        <th scope="row">상세 코드</th>
						                        <td >
						                            <span  id="cd"> </span>
						
						                        </td>
						                    </tr>
						                    <tr>
						                        <th scope="row">상세 코드명<span class="fontred">*</span></th>
						                        <td>
						                            <input id="cdNm" name="cdNm"  title="상세코드명"  type="text" class="inpw30" data-requireNm="상세코드명" data-maxLength="200" />
						                        </td>
						                    </tr>
						                    <tr>
						                        <th scope="row">상세 코드 설명</th>
						                        <td>
						                            <input id="cdExpl" name="cdExpl"  title="상세코드설명"  type="text" class="inpw80" data-maxLength="200" />
						                        </td>
						                    </tr>
						                    <tr>
						                        <th scope="row">정렬순서</th>
						                        <td>
						                            <input id="sortOrd" name="sortOrd" type="text" value=""  title="정렬순서" maxlength="3" class="inpw10" />
						                        </td>
						                    </tr>
						                    <tr>
						                        <th scope="row">사용여부</th>
						                        <td>
						                            <select id="useYn" name="useYn" class="selw6" title="사용여부">
						                                <option value="Y">사용</option>
						                                <option value="N">미사용</option>
						                            </select>
						                        </td>
						                    </tr>
						            </tbody>
					                 </table>
				                  </div>
								</form>
			                    <div class="btn_c">
			                      <ul>
			                        <li><a href="javascript:void(0);" class="RdButton" onclick="fn_updFsysCodeDtlUDtl();return false;">저장</a></li>
			                        <li><a href="javascript:void(0);" class="RdButton" onclick="fn_delFsysCodeDtlUDtl();return false;">삭제</a></li>
			                        <!-- <li><a href="javascript:void(0);" class="myButton" onclick="fn_indexFsysProgramMList();return false;">목록</a></li> -->
			                      </ul>
			                    </div>
                           
                          </div>
                         
                    </div>
               </div>
                 <!---  //contnets  적용 ------>
       </div>
  </div>
</body>
</html>