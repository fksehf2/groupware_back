<%--
/**
 * <pre>
 * 1. 작성일 : 2021. 4. 14. 오전 10:11:33
 * 2. 작성자 : jij
 * 3. 화면명 : 프로그램 관리 등록 화면
 * 4. 설명 : @!@ 프로그램 관리 등록 화면 
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
});

/**
 * @!@ 프로그램 관리 등록
 * @param {string}
 * @returns 
 */
function fn_regFsysProgramRDtl(){
	
	if(!validUtil.checkInputValid({valFormID:'insForm'})){
			return;
	}

	fn_showModalPage("등록 하시겠습니까?", function() {
		var callUrl = "<c:url value='/fsys/program/regFsysProgramRDtl.do'/>";
		requestUtil.save({callUrl:callUrl,srhFormNm:'insForm',callbackNm:'fn_regFsysProgramRDtCallback'});
	});

}

/**
 * @!@ 프로그램 관리 등록 콜백
 * @param {string}
 * @returns 
 */
function fn_regFsysProgramRDtCallback(data){
	 fn_indexFsysProgramMList();
}

/**
 * @!@ 메뉴 관리 리스트 화면 이동
 * @param
 * @returns 
 */
function fn_indexFsysProgramMList(){
	parent.$('#'+tabId+' iframe').attr('src', '<c:url value="/fsys/program/indexFsysProgramMList.do"/>');
}

</script>

</head>

<body>
<div id="con_wrap">
        <div class="content">
           <!----현재위치----->
             
            <div id="contents_info">
                 <div class="sub_ttl">프로그램 등록</div><!-----타이틀------>
                 
                  <div class="sub">
                     <!------------검색------------------->
                     <form name="insForm" id="insForm" method="post">
                     
                      <div class="t_list">
		                 <table class="iptTblX">
			               <caption>등록</caption>
			               <colgroup>
				             <col width="20%" />
				             <col width="*" />
			               </colgroup>
			               <tbody>
			                    <tr>
			                        <th scope="row">프로그램 PATH<span class="fontred">*</span></th>
			                        <td>
			                            <input id="programPath" name="programPath"  title="프로그램 PATH"  type="text" class="inpw30" data-requireNm="프로그램 PATH" data-maxLength="200"/>
			                        </td>
			                    </tr>
			                    <tr>
			                        <th scope="row">프로그램명 (영문)<span class="fontred">*</span></th>
			                        <td>
			                            <input id="programNm" name="programNm"  title="프로그램명 (영문)"  type="text" class="inpw20" data-requireNm="프로그램 (영문)" data-maxLength="200"/>
			                        </td>
			                    </tr>   
			                    <tr>
			                        <th scope="row">프로그램명 (한글)<span class="fontred">*</span></th>
			                        <td>
			                            <input id="programExpl" name="programExpl"  title="프로그램명 (한글)"  type="text" class="inpw20" data-requireNm="프로그램 (한글)" data-maxLength="200"/>
			                        </td>
			                    </tr>   
			                    <tr>
			                        <th scope="row">URL<span class="fontred">*</span></th>
			                        <td>
			                            <input id="url" name="url" title="URL" type="text" class="inpw50" data-requireNm="URL" data-maxLength="200"/>
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
                        <li><a href="javascript:void(0);" class="RdButton" onclick="fn_regFsysProgramRDtl();return false;">등록</a></li>
                        <!-- <li><a href="javascript:void(0);" class="myButton">재입력</a></li> -->
                        <li><a href="javascript:void(0);" class="myButton" onclick="fn_indexFsysProgramMList();return false;">목록</a></li>
                      </ul>
                    </div>
                    <!-----------//-검색------------------->   
                       
                    
                          
                  </div>
            </div>
        
        </div>
 </div>
<div id="divPrgPopup"></div>
</body>
</html>