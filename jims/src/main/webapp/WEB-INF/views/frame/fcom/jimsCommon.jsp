<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ include file="/WEB-INF/views/frame/fcom/meta.jsp" %>


<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<link rel="stylesheet" href="<c:url value="/css/style.css" />">

<link rel="stylesheet" href="<c:url value="/css/com/jquery-ui.css" />">

<%/* javascript */%>

<script type="text/javascript" src="<c:url value='/js/frame/thirdparty/jquery/jquery-1.9.1.min.js' />"></script>
<script src="<c:url value="/js/frame/thirdparty/jquery/jquery-ui.js" />"></script>

<!-- jquery.twbsPagination.min.js Paging-->
<script type="text/javascript" src="<c:url value='/js/frame/thirdparty/jquery/jquery.twbsPagination.min.js'/>"></script>

<!-- monthPicker.js - 월 달력 -->
<script src="<c:url value='/js/frame/thirdparty/jquery/jquery.mtz.monthpicker.js' />"></script>

<!-- @!@ 년 달력 -->
<script src="<c:url value="/js/frame/thirdparty/yearpicker/year-select.js" />"></script>

<!-- @!@ table to excel -->
<script type="text/javascript" src="<c:url value='/js/frame/thirdparty/tableExport/libs/js-xlsx/xlsx.core.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/frame/thirdparty/tableExport/libs/FileSaver/FileSaver.min.js'/>"></script>
<%-- <script type="text/javascript" src="<c:url value='/js/frame/thirdparty/tableExport/libs/jsPDF/jspdf.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/frame/thirdparty/tableExport/libs/jsPDF-AutoTable/jspdf.plugin.autotable.js'/>"></script> --%>
<script type="text/javascript" src="<c:url value='/js/frame/thirdparty/tableExport/libs/es6-promise/es6-promise.auto.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/frame/thirdparty/tableExport/libs/html2canvas/html2canvas.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/frame/thirdparty/tableExport/tableExport.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/frame/thirdparty/tableExport/tableExport.min.js'/>"></script>

<!-- @!@ fileDownload -->
<script type="text/javascript" src="<c:url value='/js/frame/thirdparty/jquery/jquery.fileDownload.js' />" ></script>
<script type="text/javascript" src="<c:url value='/js/frame/thirdparty/jquery/jquery.maskedinput.min.js' />" ></script>

<!--  requestUtil -->
<script type="text/javascript" src="<c:url value='/js/frame/jims.requestUtil.js' />" ></script>

<!-- pageUtil -->
<script type="text/javascript" src="<c:url value='/js/frame/frame.pageUtil.js' />" ></script>

<!-- validUtil -->
<script type="text/javascript" src="<c:url value='/js/frame/frame.validUtil.js' />" ></script>

<!--  common.js-->
<script type="text/javascript" src="<c:url value='/js/frame/common.js' />" ></script>

<!-- sha256.js  -->
<script type="text/javascript" src="<c:url value='/js/frame/sha256.js'/>"></script>

<!-- jquery.twbsPagination.min.js 좌측 메뉴 -->
<!-- 
<script type="text/javascript" src="<c:url value='/js/frame/thirdparty/ui.js' />"></script>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/jims/common.css" />" />
<link rel="stylesheet" type="text/css" href="<c:url value="/css/jims/main.css" />" />
 -->


<script>


</script>
  <style type="text/css">
.modal {        position: fixed;
                z-index: 999;
                height: 100%;
                width: 100%;
                top: 0;
                left: 0;
                /* background:url('/css/jims/images/common/bg_pattern.png') repeat 0 0; */
        }
.center {       z-index: 1000;
                margin: auto;
                padding: 10px;
                width: 500px;
                background-color: none ;
                border-radius: 10px;
         }
</style>

<script type="text/javascript">

//alert('<%=request.getAttribute("session.userid") %>');


var session_userid ='<%=request.getParameter("session_userid") %>';
var session_insttcd = '<%=request.getParameter("session_insttcd") %>';
var session_usernm ='<%=request.getParameter("session_usernm") %>';
var session_usergb ='<%=request.getParameter("session_usergb") %>';
var session_usergbnm ='<%=request.getParameter("session_usergbnm") %>';
var session_insttnm ='<%=request.getParameter("session_insttnm") %>';
var session_deptnm = '<%=request.getParameter("session_deptnm") %>';
var session_telno = '<%=request.getParameter("session_telno") %>';
var session_hptelno = '<%=request.getParameter("session_hptelno") %>';
var session_userip = '<%=request.getRemoteAddr() %>';

var comPgsStat = "";

switch (session_usergb) {
	case 'C01001':
		comPgsStat = "C03001,C03002,C03003,C03005,C03006";
	    break;
	case 'C01002':
		comPgsStat = "C03002,C03003,C03005,C03006";
	    break;
	case 'C01003':
		comPgsStat = "C03003,C03005,C03006";
	    break;
	case 'C01004':
		comPgsStat = "C03005,C03006";
	    break;
	case 'C01999':
		comPgsStat = "C03001,C03002,C03003,C03005,C03006,C03007,C03008";
	    break;
	default:
		break;
}



function fn_showLoginPage(){
	location.href = "<c:url value='/flyt/login/procFLytLoginSessoutPDtl.do' />";
}

function fn_showErrorPage() {
	
	$("#_error_div_para_").css("display","block");
	$("#_error_div_para_").text("요청하신 작업중 오류가 발생했습니다.  \n 관리자에게 문의해 주세요.");
	$('#_error_div_')
    //.data('userId', 'Manager')
    .dialog({
       modal: true,
       buttons: {
           닫기: function() {
             $( this ).dialog( "close" );
           }
         },
       open: function (event,ui) {
    	  // $(this).html("요청하신 작업중 오류가 발생했습니다.  \n 관리자에게 문의해 주세요.");
    	  // $("#_error_div_para_").append("요청하신 작업중 오류가 발생했습니다.  \n 관리자에게 문의해 주세요.");
       },
       height: 200,
       width: 500,
       title: '오류'

   });
}


function fn_showUserPage(msg,callback,data) {
	//alert("========msg=======>>>"+msg);
	//debugger;
	//$("#_error_div_para_").text(msg);
	$("#_error_div_para_").css("display","block");
	$("#_error_div_para_")[0].innerText = msg;
	//alert("========msg22=======>>>"+$("#_error_div_para_").text());
	$('#_error_div_')
	//.data('userId', 'Manager')
    .dialog({
       modal: true,
       buttons: {
           닫기: function() {
        	 
             $( this ).dialog( "close" );
             if(typeof callback === "function" ){
	             callback();
             } else {
            	 if( callback != undefined) {
            		if( data != undefined) {
	         			eval(callback+'(data)');
            		} else {
            			eval(callback+'()');
            		}
         		}
             }
           }
         },
       open: function (event,ui) {
    	  // $(this).html("요청하신 작업중 오류가 발생했습니다.  \n 관리자에게 문의해 주세요.");
    	  // $("#_error_div_para_").append("요청하신 작업중 오류가 발생했습니다.  \n 관리자에게 문의해 주세요.");
       },
       height: 200,
       width: 500,
       title: '경고'

   });
}


function fn_showInfoPage(msg,callback,data) {
	//alert("========msg=======>>>"+msg);
	//debugger;
	//$("#_error_div_para_").text(msg);
	$("#_error_div_para_").css("display","block");
	$("#_error_div_para_")[0].innerText = msg;
	//alert("========msg22=======>>>"+$("#_error_div_para_").text());
	$('#_error_div_')
	//.data('userId', 'Manager')
    .dialog({
       modal: true,
       buttons: {
           닫기: function() {
        	 
             $( this ).dialog( "close" );
             if(typeof callback === "function" ){
	             callback();
             } else {
            	 if( callback != undefined) {
            		if( data != undefined) {
	         			eval(callback+'(data)');
            		} else {
            			eval(callback+'()');
            		}
         		}
             }
           }
         },
       open: function (event,ui) {
    	  // $(this).html("요청하신 작업중 오류가 발생했습니다.  \n 관리자에게 문의해 주세요.");
    	  // $("#_error_div_para_").append("요청하신 작업중 오류가 발생했습니다.  \n 관리자에게 문의해 주세요.");
       },
       height: 200,
       width: 500,
       title: '정보'

   });
}


function fn_showModalPage(msg,callback) {
	$("#_error_div_para_").css("display","block");
	$("#_error_div_para_")[0].innerText = msg;
	//$("#_error_div_para_").text(msg);
	$('#_error_div_')
    //.data('userId', 'Manager')
    .dialog({
       modal: true,
       buttons: {
    	   확인: function() {
    		   $( this ).dialog( "close" );
    		   if(typeof callback === "function" ){
              	 
      	         callback();
                 }
            },
    	   닫기: function() {
        	 
             $( this ).dialog( "close" );
             
           }
         },
       open: function (event,ui) {
    	  // $(this).html("요청하신 작업중 오류가 발생했습니다.  \n 관리자에게 문의해 주세요.");
    	  // $("#_error_div_para_").append("요청하신 작업중 오류가 발생했습니다.  \n 관리자에게 문의해 주세요.");
       },
       height: 200,
       width: 500,
       title: '확인'

   });
}

//******* monthPicker setting *************
var monthpickerNowYear = gfn_getCurDate().substring(0,4);
var monthpickerOptions = {
		pattern:'yyyy-mm'                   // 포맷
	  , selectedYear: monthpickerNowYear	// 디폴트 년도
	  , startYear: 1990                     // 년도 리스트 범위 start
	  , finalYear: 2200                     // 년도 리스트 범위 end
	  , monthNames:['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] // 월표기 한글로 변경
      , openOnFocus:true
}
//******* monthPicker setting *************

// @!@ table to excel
function fn_tableToExport(selector, params) {
	var options = {
		//ignoreRow: [1,11,12,-2],
		//ignoreColumn: [0,-1],
		//pdfmake: {enabled: true},
		//onBeforeSaveToFile: DoOnBeforeSaveToFile,
		//onAfterSaveToFile: DoOnAfterSaveToFile,

		tableName: 'Table name'
	};

	jQuery.extend(true, options, params);

	$(selector).tableExport(options);
}

</script>

<div id="_error_div_">
 <p id="_error_div_para_" style="padding-top: 30px;display:none" ></p>
</div>

<div class="modal" style="display: none">
    <div class="center">
        <img style="position:absolute; top:40%; left:40%; margin-top:-50px; margin-left:-50px;"  alt="" src="/images/main/loading_icon.gif"/>
    </div>
</div>

