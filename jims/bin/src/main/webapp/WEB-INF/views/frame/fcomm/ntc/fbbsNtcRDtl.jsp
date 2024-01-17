<%--
/**
 * <pre>
 * 1. 작성일 : 2021. 4. 16.
 * 2. 작성자 : leeji
 * 3. 화면명 : 공지사항 등록
 * 4. 설명 : 공지사항 등록
 * </pre>
 */
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>

<html>
<head>
<%@ include file="/WEB-INF/views/frame/fcom/jimsCommon.jsp" %>

<script type="text/javaScript">
var tabId;

$(document).ready(function() {
	tabId = parent.$('li[id*=li_tabs-M][aria-expanded=true]').attr('aria-controls');
	$("#putupDt").val(gfn_getDate());
    <%/* 달력 세팅 */%>


    <%/* 파일업로드 세팅 */%>
    gfn_fileUpload("atchFile", "fileList4","file", 4);
    fn_fileCntChk();

    <%/* MaxLength 세팅 (textarea인 경우 span id=objName+"ByteChk"가 있으면 text 표시됨)*/%>
    gfn_overMaxLengthText("title",100);
    gfn_overMaxLength("cnts",4000);
});

/**
 * 첨부파일갯수 체크
 * @param  
 * @returns
 */
function fn_fileCntChk(){
    var numCnt=0;
    $('#divFile :input[id^=fileList4FileSno]').each(function(index) {
        numCnt++;
    });

    if(numCnt > 4){
        $("#atchFile").hide();
    }else{
        $("#atchFile").show();
    }
}

/**
 * 자유게시판 등록
 * @param form
 * @returns fn_bbsFreeRDtlCallBack
 */
function fn_addView(){

    gfn_overMaxLength("cnts",4000);

    var putupDt = $("#putupDt").val();
    if( $("#title").val()==''){
    	fn_showUserPage("제목을 입력하세요.");
        $("#title").focus();
        return false;
    }
    if( $("#cnts").val()==''){
    	fn_showUserPage("내용을 입력하세요.");
        editor.focus();
        return false;
    } else {
        //4000바이트 까지만 저장 가능
        if(gfn_checkByte($("#cnts").val()) > 4000){
        	fn_showUserPage("내용이 너무 길어 저장할 수 없습니다.");
            editor.focus();
            return false;
        }
    }

    if(confirm("등록하시겠습니까?")){
    	$("#putupDt").val(putupDt);
        var callUrl = "<c:url value='/fbbs/ntc/regFBbsNtcRDtl.do'/>";
        requestUtil.save({callUrl:callUrl,srhFormNm:'insForm',callbackNm:'fn_fBbsNtcRDtlCallBack'});

    }

}

/**
 * 공지사항 목록화면이동
 * @param
 * @returns
 */
function fn_moveList(){
	var src = "<c:url value = "/fbbs/ntc/indexFBbsNtcMList.do"/>";
	parent.$('#'+tabId+' iframe').attr('src', src);
}

/**
* 공지사항 등록 콜백함수
* @param
* @returns
*/
function fn_fBbsNtcRDtlCallBack(){
	fn_moveList();
}
</script>

</head>
<body>
<div id="con_wrap">
        <div class="content">
            <div id="contents_info">
                 <div class="sub_ttl">공지사항등록</div><!-----타이틀------>
                 
                  <div class="sub">
                     <!------------검색------------------->
                      <div class="t_list">
                      <form:form commandName="fBbsNtcVO" name="insForm" id="insForm" method="post">
			            <input id="upmuGubun" name="upmuGubun" type="hidden" value="2"/>
			            <input id="putupDt1" name="putupDt1" type="hidden" value=""/>
		                 <table class="iptTblX">
		                   <caption>등록하기</caption>
			               <colgroup>
				             <col width="20%" />
				             <col width="*" />
			               </colgroup>
			               <tbody>
			                 <tr>
				                 <th scope="row">제목</th>
				                 <td><input type="text" class="inpw500" maxlength="50" name="title" id="title" require="true" title="제목 입력"/></td>
			                 </tr>
			                 <tr>
				                  <th scope="row">등록일</th>
				                  <td><input type="text" id="putupDt" name="putupDt" class="inpw150" value="" disabled="true" title="등록일 입력"/></td>
			                 </tr>
			                 <tr>
				                 <th scope="row">고정공지</th>
				                 <td><input type="checkbox" id="fixNoticeYn" name="fixNoticeYn" class="" value="Y"></td>
			                 </tr>
			                 <tr>
				                 <th scope="row">내용<span class="fontred">*</span></th>
				                 <td><textarea id="cnts" name="cnts" class="" rows="3" cols="20" style="width:540px;height:150px;" title="내용 입력"></textarea>
				                 <span class="txt_info" name="cntsByteChk" id="cntsByteChk"></span>
				                 </td>
			                 </tr>
			                 <tr id="divFile">
                                <th scope="row">첨부파일</th>
                                <td>
                                    <button id="atchFile" name="atchFile" type="button" class="btn_sty3" >찾아보기</button>
                                    <div id="fileList4" name="fileList4">
                                    </div>
                                </td>
                            </tr>
			                </tbody>
		                 </table>
		                 </form:form>
	                  </div>
	
                    <div class="btn_c">
                      <ul>
                        <li>
                        	<a href="javascript:void(0);" class='RdButton' onclick="fn_addView(); return false;">등록</a>
                        </li> 
                        <li>
                        	<a href="javascript:void(0);" class='myButton' onclick="fn_moveList(); return false;">목록</a>
                       	</li>
                      </ul>
                    </div>
                    <!-----------//-검색------------------->   
                       
                    
                          
                  </div>
            </div>
        
        </div>
 </div>
</body>
</html>