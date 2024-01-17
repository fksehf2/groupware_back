<%--
/**
 * <pre>
 * 1. 작성일 : 2021. 4. 14. 오전 10:11:33
 * 2. 작성자 : jij
 * 3. 화면명 : 메뉴관리 등록 화면
 * 4. 설명 : @!@ 메뉴관리 등록 화면 
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
	
	$("#menuLvl").on("change",function(){
		$("#programId").val("");
		if( $( "#menuLvl option:selected" ).val() =='lvl2') {
			$("#trUpperMenuNo").show();
			$("#trPrgId").show();
		} else {
			$("#trUpperMenuNo").hide();
			$("#trPrgId").hide();
			$("#upperMenuNo").val("");
		}
	})
	
	$("#menuOrdr").on("keyup",function(){
		gfn_new_number("menuOrdr");
	})
	
	<%/* MaxLength 세팅 (textarea인 경우 span id=objName+"ByteChk"가 있으면 text 표시됨)*/%>
	/* <span class="txt_info" name="ctnByteChk" id="ctnByteChk"></span> */
    //gfn_overMaxLength("",4000);
    
	requestUtil.search({callUrl:"<c:url value='/fsys/menu/queryFsysMenuUpper.do'/>", srhFormNm:'insForm', callbackNm:'fn_callback'});
	
});

/**
 * @!@ 업데이트 대상 정보 호출 콜백 
 * @param {json} data
 * @returns 
 */
function fn_callback(data){
	$("#topMenuNo").val(data.topMenuNo);
	if(data.sysGrpList.length > 0){
		$("#menuLvl").append('<option value="lvl2">Level2</option>');
		
		$.each(data.sysGrpList, function(idx, row) {
	    	$("#upperMenuNo").append("<option value='"+row.sysGrp+"'>"+row.sysGrpNm+"</option>");
	    });
	}
	
}
 
/**
 * @!@ 프로그램 목록 찾기 팝업
 * @param
 * @returns 
 */
function fn_prgdetail() {

	var callUrl = "<c:url value = "/com/PageLink.do"/>"

	requestUtil.mdPop({
		popUrl : callUrl+"?link="+"frame/fsys/menu/fsysMenuSchPrgmPop",
		height: 700,
        width: 1000,
        title: '프로그램 찾기',
        divId : 'divPrgPopup'
	});

}

/**
* @!@ 메뉴 관리 등록
* @param
* @returns 
*/
function fn_regFsysMenuRDtl(){

	if( $("#menuLvl option:selected").val()=='lvl2' && $("select[name=upperMenuNo] option:selected").val() == ""){
		fn_showUserPage("상위메뉴은(는) 필수 입력 항목입니다.", "fn_tagIdFocus", "upperMenuNo");
		return false;
	}
	
	if( $("#menuLvl option:selected").val()=='lvl2' && $("#programId").val() == ""){
		fn_showUserPage("프로그램은(는) 필수 입력 항목입니다.", "fn_tagIdFocus", "programId");
		return false;
	}


	if(!validUtil.checkInputValid({valFormID:'insForm'})){
		return;
	}
	
	fn_showModalPage("등록 하시겠습니까?", function() {
        var callUrl = "<c:url value='/fsys/menu/regFsysMenuRDtl.do'/>";
        requestUtil.save({callUrl:callUrl,srhFormNm:'insForm',callbackNm:'fn_regFsysMenuRDtlCallback'});
	});

}

/**
 * @!@ 메뉴 관리 등록 콜백
 * @param {json} data
 * @returns 
 */
function fn_regFsysMenuRDtlCallback(data){
	fn_indexFsysMenuMList();
}

/**
 * @!@ 메뉴 관리 리스트 화면 이동
 * @param
 * @returns 
 */
function fn_indexFsysMenuMList(){
	parent.$('#'+tabId+' iframe').attr('src', '<c:url value="/fsys/menu/indexFsysMenuMList.do"/>');
}
</script>

</head>

<body>
<div id="con_wrap">
        <div class="content">
           <!----현재위치----->
             
            <div id="contents_info">
                 <div class="sub_ttl">메뉴 등록</div><!-----타이틀------>
                 
                  <div class="sub">
                     <!------------검색------------------->
                     <form name="insForm" id="insForm" method="post">
                     
                     	<input type="hidden" class="" id="page" name="page" value="<c:out value='${param.page}'/>"/>
                     	<input type="hidden" class="" id="srcSysGrp" name="srcSysGrp" maxlength="200"  value="<c:out value='${param.srcSysGrp}'/>">
                     	<input type="hidden" class="" id="srcMenuNm" name="srcMenuNm" maxlength="200"  value="<c:out value='${param.srcMenuNm}'/>">
                     	<input type="hidden" class="" id="srcMenuLvl" name="srcMenuLvl" maxlength="200"  value="<c:out value='${param.srcMenuLvl}'/>">
                     	
					 <input type="hidden" id="sysGrp" name="sysGrp" value="<c:out value="${param.srcSysGrp}" />" />
					 <input type="hidden" id="topMenuNo" name="topMenuNo" value="" />
					 
                      <div class="t_list">
		                 <table class="iptTblX">
			               <caption>등록</caption>
			               <colgroup>
				             <col width="20%" />
				             <col width="*" />
			               </colgroup>
			               <tbody>
			                 <tr>
				                 <th scope="row">메뉴 레벨</th>
				                 <td>
				                 	<select id="menuLvl" name="menuLvl" class="selw6">
								 		<option value="lvl1">Level1</option>
							 		</select>
				                 </td>
			                 </tr>
			                 <tr id="trUpperMenuNo" style="display:none">
								<th scope="row">상위메뉴<span class="fontred">*</span></th>
								<td>
									 <select id="upperMenuNo" name="upperMenuNo" class="selw15" title="상위메뉴">
									 	<option value="">선택</option>
									 	<%-- <option value="" <c:out value="${sysGrpInfo.sysGrp==''?\"selected\":\"\"}"/> >전체</option>
							            <c:forEach items="${sysGrpList}" var="sysGrpInfo" varStatus="status">
								            <option value="<c:out value="${sysGrpInfo.sysGrp}"/>" <c:if test="${sysGrpInfo.sysGrp == menuvo.srcSysGrp}">selected="selected"</c:if>><c:out value="${sysGrpInfo.sysGrpNm}"/></option>
							            </c:forEach> --%>
							         </select>
								</td>
							</tr>
			                 <tr>
				                  <th scope="row">메뉴명<span class="fontred">*</span></th>
				                  <td>
			                  		<input id="menuNm" name="menuNm" type="text" value="" class="inpw20" data-requireNm="메뉴명" data-maxLength="50" title="메뉴명"/>
				                  </td>
			                 </tr>
			                 <tr>
				                 <th scope="row">정렬 순서</th>
				                 <td>
				                 	<input id="menuOrdr" name="menuOrdr" type="text" value=""  maxlength="3" class="inpw10" title="정렬 순서"/>
				                 </td>
			                 </tr>
			                 <tr>
				                 <th scope="row">쓰기 권한</th>
				                 <td>
				                 	<input type="radio" name="menuAuthor" value="C" id="menuAuthor1" class="inp-radio" title="쓰기권한C" checked/><label for="menuAuthor1">쓰기</label>
									<input type="radio" name="menuAuthor" value="R" id="menuAuthor2" class="inp-radio" title="쓰기권한R"/><label for="menuAuthor2">읽기</label>
				                 </td>
			                 </tr>
			                 <tr>
			                   <th scope="row">표시 여부</th>
			                   <td>
			                   		<input type="radio" name="menuDisplayYn" value="Y" id="menuDisplayYn1" class="inp-radio" title="표시 여부Y" checked /><label for="menuDisplayYn1">표시</label>
									<input type="radio" name="menuDisplayYn" value="N" id="menuDisplayYn2" class="inp-radio" title="표시 여부N" /><label for="menuDisplayYn1">표시안함</label>
				               </td>
			                 </tr>
			                 <tr id="trPrgId" style="display:none">
								<th scope="row">프로그램 ID<span class="fontred">*</span></th>
								<td><input id="programId" name="programId" readonly type="text" onclick="fn_prgdetail();return false;" class="inpw20" data-maxLength="10" title="프로그램 ID"/></td>
							</tr>
			                </tbody>
		                 </table>
	                  </div>
					</form>
                    <div class="btn_c">
                      <ul>
                        <li><a href="javascript:void(0);" class="RdButton" onclick="fn_regFsysMenuRDtl();return false;">등록</a></li>
                        <!-- <li><a href="javascript:void(0);" class="myButton">재입력</a></li> -->
                        <li><a href="javascript:void(0);" class="myButton" onclick="fn_indexFsysMenuMList();return false;">목록</a></li>
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