<%--
/**
 * <pre>
 * 1. 작성일 : 2021. 4. 16.
 * 2. 작성자 : leeji
 * 3. 화면명 : 공지사항 수정
 * 4. 설명 : 공지사항 수정
 * </pre>
 */
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>

<html>
<head>
<%@ include file="/WEB-INF/views/frame/fcom/jimsCommon.jsp" %>

<script type="text/javaScript">
var tabId;

$(document).ready(function() {
	
	tabId = parent.$('li[id*=li_tabs-M][aria-expanded=true]').attr('aria-controls');
	
    <%/* 파일업로드 세팅 */%>
    gfn_fileUpload("atchFile", "fileList4","file", 4);
    
    var callUrl = "<c:url value = "/fbbs/ntc/goFBbsNtcUDtl.do"/>";	
	requestUtil.search({callUrl:callUrl,srhFormNm:'modForm',callbackNm:'fn_callBack'});
    
});

/**
* 자유게시판 수정 콜백함수
* @param {object} data 조회한 결과데이터
* @returns
*/
function fn_callBack(data){
	
	fn_fileCntChk();

    <%/* MaxLength 세팅 (textarea인 경우 span id=objName+"ByteChk"가 있으면 text 표시됨)*/%>
    gfn_overMaxLengthText("title",100);
    gfn_overMaxLength("cnts",4000);

    if(data.fbbsNtcMap.fixNoticeYn == 'Y'){
        $("#fixNoticeYn").attr("checked", true);
    }
    
    //파일첨부리스트 만들기
	for(var i = 0; i < data.atchFileList.length; i++){
		var div =  $('<div class="fileList4Class" style="padding: 5px;">');
		//div.append(data.atchFileList[i].oriFileNm);
		div.append("<a href=javascript:fn_fileDown('"+data.atchFileList[i].filePth+"','"+data.atchFileList[i].fileNm+"','"+data.atchFileList[i].oriFileNm+"')>"+data.atchFileList[i].oriFileNm+"</a>");
		div.append('<a href="#" onclick="javascript_:this.parentNode.parentNode.removeChild(this.parentNode); fn_fileCntChk(); fn_del(this);return false;" class="buttonG35">삭제</a><br/>');
		div.append('<input id="fileList4FileSno" name="fileList4FileSno" type="hidden" value="'+data.atchFileList[i].fileSno +'"/>');
		div.append('<input id="fileList4FilePth" name="fileList4FilePth" type="hidden" value="'+ data.atchFileList[i].filePth +'"/>');
		div.append('<input id="fileList4FileNm" name="fileList4FileNm" type="hidden" value="'+data.atchFileList[i].fileNm +'"/>');
		div.append('<input id="fileList4FileSize" name="fileList4FileSize" type="hidden" value="'+data.atchFileList[i].fileSize +'"/>');
		div.append('<input id="fileList4OriFileNm" name="fileList4OriFileNm" type="hidden" value="'+data.atchFileList[i].oriFileNm+'"/>');
		div.append('</div>');
		$('#fileList4').append(div);
	}
}

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
 * 자유게시판 수정
 * @param form
 * @returns fn_moveList
 */
function fn_modify(){

    gfn_overMaxLength("cnts",4000);

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

    if(confirm("수정하시겠습니까?")){
        var callUrl = "<c:url value = "/fbbs/ntc/updFBbsNtcUDtl.do"/>";	
    	requestUtil.search({callUrl:callUrl,srhFormNm:'modForm',callbackNm:'fn_moveList'});
    }
}

/**
 * 공지사항 삭제
 * @param {String} putupSno 페이지게시판순번
 * @returns fn_moveList
 */
function fn_delete(putupSno) {
	debugger;
    $("#putupSno").val(putupSno);
    if(confirm("삭제하시겠습니까?")){
        var callUrl = "<c:url value = "/fbbs/ntc/delFBbsNtcUDtl.do"/>";	
    	requestUtil.search({callUrl:callUrl,srhFormNm:'modForm',callbackNm:'fn_moveList'});
    }
}

/**
 * 공지사항 파일다운로드
 * @param {String} filePath 파일경로
 * @param {String} fileNm   파일이름
 * @param {String} atchFileNm 실제파일이름
 * @returns
 */
function fn_fileDown(filePath,fileNm,atchFileNm) {
    gfn_fileNmDownload(filePath,fileNm,encodeURI(atchFileNm));
}

 /**
  * 자유게시판 목록페이지이동
  * @param
  * @returns
  */
function fn_moveList(){
	var src = "<c:url value = "/fbbs/ntc/indexFBbsNtcMList.do"/>";	
	parent.$('#'+tabId+' iframe').attr('src', src);
}
</script>
</head>
<body>
<div id="con_wrap">
        <div class="content">
            <div id="contents_info">
                 <div class="sub_ttl">공지사항수정</div><!-----타이틀------>
                 
                  <div class="sub">
                     <!------------검색------------------->
                      <div class="t_list">
                      <form name="modForm" id="modForm" method="post">
		                <input id="putupSno" name="putupSno" type="hidden" value="<c:out value="${param.putupSno}" />"/>
		                 <table class="iptTblX">
			               <caption>공지사항수정</caption>
			               <colgroup>
				             <col width="20%" />
				             <col width="*" />
			               </colgroup>
			               <tbody>
			                 <tr>
				                 <th scope="row">제목</th>
				                 <td><input type="text" class="" maxlength="50" name="title" id="title" style="width:530px;" title="제목 입력" value="<c:out value='${fBbsNtcVO.title }'/>"/></td>
			                 </tr>
			                 <tr>
				                  <th scope="row">등록일</th>
				                  <td><input type="text" id="putupDt" name="putupDt" class="" style="width:120px;" value="<c:out value='${fBbsNtcVO.putupDt }'/>" readonly="true" title="등록일 입력"/></td>
			                 </tr>
			                 <tr>
				                  <th scope="row">작성자</th>
				                  <td><span id="regrNm"></span></td>
			                 </tr>
			                 <tr>
				                 <th scope="row">고정공지</th>
				                 <td><input type="checkbox" id="fixNoticeYn" name="fixNoticeYn" class="" value="Y"></td>
			                 </tr>
			                 <tr>
				                 <th scope="row">내용<span class="fontred">*</span></th>
				                 <td><textarea id="cnts" name="cnts" class="" rows="3" cols="20" style="width:540px;height:150px;" title="내용 입력"><c:out value='${fBbsNtcVO.cntsCvt }' escapeXml="false"/></textarea>
				                 <span class="txt_info" name="cntsByteChk" id="cntsByteChk"></span>
				                 </td>
			                 </tr>                            
                             <tr id="divFile">
                                <th scope="row">첨부파일</th>
                                <td scope="row" class="t_left1 bdrn">
                                    <button id="atchFile" name="atchFile" type="button" class="btn_sty3">찾아보기</button>
                                    <div id="fileList4" name="fileList4">
                                    	<input id="atchFileId" name="atchFileId" type="hidden" value="${atchFileId}"/>
                                    </div>
                                </td>
                              </tr>
			                </tbody>
		                 </table>
		                 </form>
	                  </div>
	
                    <div class="btn_c">
                      <ul>
                        <li>
                        	<a href="javascript:void(0);" class='RdButton' onclick="fn_modify(); return false;">저장</a>
                        </li>
                        <li>
                        	<a href="javascript:void(0);" class='RdButton' onclick="fn_delete('<c:out value="${param.putupSno}"/>'); return false;">삭제</a>
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