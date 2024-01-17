<%--
/**
 * <pre>
 * 1. 작성일 : 2021. 4. 14. 오전 10:11:33
 * 2. 작성자 : jij
 * 3. 화면명 : 상세 코드 등록 팝업
 * 4. 설명 : @!@ 상세 코드 등록 팝업 
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
	$("#sortOrd").on("keyup",function(){
		gfn_new_number("sortOrd");
	})
});

/**
* @!@ 상세 코드 관리 등록
* @param
* @returns 
*/
function fn_regFsysCodeDtlRDtl(){

	if(!validUtil.checkInputValid({valFormID:'insForm'})){
		return;
	}
	
	fn_showModalPage("저장 하시겠습니까?", function() {
        var callUrl = "<c:url value='/fsys/code/regFsysCodeDtlRDtl.do'/>";
        requestUtil.save({callUrl:callUrl,srhFormNm:'insForm',callbackNm:'fn_regFsysCodeDtlRDtlCallback'});
	});
	
}

/**
 * @!@ 상세 코드 관리 등록 콜백
 * @param {string} data
 * @returns 
 */
function fn_regFsysCodeDtlRDtlCallback(data){
	 fn_queryMList2(1);
	 fn_dialogClose("fsysCodeDtlRDtlPop");
}
 
</script>

</head>
<body>
<div id="con_wrap_pop">
	<div class="contents">
               <div id="contents_info">
                      <!--- contnets  적용 ------>
                      <div class="window_popup">
                          <div class="sub_ttl">상세 코드 등록</div>
                         
                          <div class="sub">
                             <!--------------검색------------------>
                             <form name="insForm" id="insForm" method="post">
			                     <input type="hidden" id="cdId" name="cdId" value="<c:out value="${paramMap.srcCdId}" />" />
			                      <div class="t_list">
					                 <table class="iptTblX">
						               <caption>등록</caption>
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
						                            <input id="sortOrd" name="sortOrd" type="text" title="정렬순서" value=""  maxlength="3" class="inpw10"/>
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
			                        <li><a href="javascript:void(0);" class="RdButton" onclick="fn_regFsysCodeDtlRDtl();return false;">등록</a></li>
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