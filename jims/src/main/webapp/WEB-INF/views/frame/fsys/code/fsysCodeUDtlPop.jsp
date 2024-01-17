<%--
/**
 * <pre>
 * 1. 작성일 : 2021. 4. 14. 오전 10:11:33
 * 2. 작성자 : jij
 * 3. 화면명 : 프로그램 찾기 팝업
 * 4. 설명 : @!@ 프로그램 찾기 팝업 
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
});

/**
 * @!@ 그룹 코드 상세 조회
 * @param
 * @returns 
 */
function fn_queryMDtl(){
	
	var callUrl = "<c:url value='/fsys/code/queryFsysCodeMDtl.do'/>";
	
	requestUtil.search({callUrl:callUrl, srhFormNm:'insForm', callbackNm:'fn_queryMDtlCallback'});
	
}

/**
 * @!@ 그룹 코드 상세 조회 콜백
 * @param
 * @returns 
 */
function fn_queryMDtlCallback(data){
}

/**
* @!@ 그룹 코드 관리 수정
* @param
* @returns 
*/
function fn_updFsysCodeUDtl(){

	if(!validUtil.checkInputValid({valFormID:'insForm'})){
		return;
	}
	
	fn_showModalPage("저장 하시겠습니까?", function() {
        var callUrl = "<c:url value='/fsys/code/updFsysCodeUDtl.do'/>";
        requestUtil.save({callUrl:callUrl,srhFormNm:'insForm',callbackNm:'fn_updFsysCodeUDtlCallback'});
	});
	
}

/**
 * @!@ 그룹 코드 관리 수정 콜백
 * @param {string} data
 * @returns 
 */
function fn_updFsysCodeUDtlCallback(data){
	 fn_queryMList(1);
	 fn_dialogClose("fsysCodeUDtlPop");
}
 
/**
* @!@ 그룹 코드 관리 삭제
* @param
* @returns 
*/
function fn_delFsysCodeUDtl(){

	fn_showModalPage("삭제 하시겠습니까?", function() {
        var callUrl = "<c:url value='/fsys/code/delFsysCodeUDtl.do'/>";
        requestUtil.save({callUrl:callUrl,srhFormNm:'insForm',callbackNm:'fn_delFsysCodeUDtlCallback'});
	});
	
}

/**
 * @!@ 그룹 코드 관리 삭제 콜백
 * @param {string} data
 * @returns 
 */
function fn_delFsysCodeUDtlCallback(data){
	 fn_queryMList(1);
	 fn_dialogClose("fsysCodeUDtlPop");
}
 
</script>

</head>
<body>
<div id="con_wrap_pop">
	<div class="contents">
               <div id="contents_info">
                      <!--- contnets  적용 ------>
                      <div class="window_popup">
                          <div class="sub_ttl">그룹 코드 상세</div>
                         
                          <div class="sub">
                             <!--------------검색------------------>
                             <form name="insForm" id="insForm" method="post">
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
						                        <th scope="row">그룹코드ID</th>
						                        <td >
						                            <span id="cdId" name="cdId"></span>
						                            
						                        </td>
						                    </tr>
						                    <tr>
						                        <th scope="row">그룹코드명<span class="fontred">*</span></th>
						                        <td>
						                            <input id="cdIdNm" name="cdIdNm"  title="그룹코드명"  type="text" class="inpw30" data-requireNm="그룹코드명" data-maxLength="50" />
						                        </td>
						                    </tr>
						                    <tr>
						                        <th scope="row">그룹코드설명</th>
						                        <td>
						                            <input id="cdIdExpl" name="cdIdExpl"  title="그룹코드설명"  type="text" class="inpw80" data-maxLength="100" />
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
			                        <li><a href="javascript:void(0);" class="RdButton" onclick="fn_updFsysCodeUDtl();return false;">저장</a></li>
			                        <li><a href="javascript:void(0);" class="RdButton" onclick="fn_delFsysCodeUDtl();return false;">삭제</a></li>
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