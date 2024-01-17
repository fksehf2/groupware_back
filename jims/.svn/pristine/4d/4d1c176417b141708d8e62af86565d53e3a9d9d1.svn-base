<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>

<title>Jenix 통합 관리 시스템</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="description" content="" />
<meta name="keywords" content="" />
<meta name="author" content="" />

<!-- CSS -->
<link rel="stylesheet" href="<c:url value="/css/com/jquery-ui.css" />">

<link rel="stylesheet" type="text/css" href="<c:url value="/css/main/jims_common.css" />" />
<!-- CSS -->


<!-- Javascript -->
<script type="text/javascript" src="<c:url value="/js/frame/thirdparty/jquery/jquery-1.9.1.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/frame/thirdparty/ui.js" />"></script>
<script src="<c:url value="/js/frame/thirdparty/jquery/jquery-ui.js" />"></script>
<!-- session time out 체크 -->
<script src="<c:url value="/js/frame/timeOutCheck.js" />"></script>
<!-- request 공통 -->
<%@ include file="/WEB-INF/views/frame/fcom/jimsCommon.jsp"%>
<!-- 
<script type="text/javascript" src="<c:url value='/js/frame/jims.requestUtil.js' />" ></script>
 -->


<style>
  #tabs { /*margin-top: 1em; */}
  #tabs li .ui-icon-close { float: left; margin: 0.4em 0.2em 0 0; cursor: pointer; }
.ui-tabs-anchor{outline:none;}
  </style>
<script type="text/javascript">
var pwdChng = "${pwdChng}"; // 패스워드 변경 여부
var pwdClr = "${pwdClr}"; // 관리자가 비번초기화한후 변경유무 (NOCLEAR:변경X, CLEAR: 변경O)

var session = <%=session.getMaxInactiveInterval()%> ;
//세션타임으로 남은시간 확인
sessionTime = session;

// 초기화
var tabs
$( function() {

	$(window).resize(function() {
		var Cwidth = $(window).width();
		$('#container, #container_02').css({'left':'287px'});
		$('#container, #container_02').css({'width':Cwidth - 300 +'px'});
	});

        tabs = $("#tabs").tabs();

        tabs.on( "click", "span.ui-icon-close", function() {
             //alert($( this ).closest( "li" ));
             var panelId = $( this ).parents( "li" ).remove().attr( "aria-controls" );
             //alert(panelId);
             $("#comSearchDiv").find("form[id$="+panelId+"]").remove();
             $( "#" + panelId ).remove();

             tabs.tabs( "refresh" );

             // 활성화된 탭의 제목을 상단 Navigation에 세팅

             $("#fullPath").text($("li[aria-selected=true]").attr("data"));

        });

        tabs.on("click", "span.ui-icon-reload", function() {

            var menuId = $( this ).parent('li').attr('aria-controls');
            var src = $('#'+menuId + ' > p > iframe').attr('src');
            $('#'+menuId + ' > p > iframe').attr('src', src);

            var id = $( this ).parent('li').attr('id');
            $('#'+id + ' > a').trigger('click');


       });

        tabs.on( "keyup", function( event ) {
             if ( event.altKey && event.keyCode === $.ui.keyCode.BACKSPACE ) {

               var panelId = tabs.find( ".ui-tabs-active" ).remove().attr( "aria-controls" );
               $( "#" + panelId ).remove();
               tabs.tabs( "refresh" );


             }
        });

        $("#tabs").tabs({activate:function(event,ui){
        	$("#fullPath").text(ui.newTab.attr('data'));
        }});


         //전체 닫기
         $("#close_all").click(function() {


         //var openTabCnts = $("li[id*='tabs-']").length;

         $("li[id*='tabs-']").each(function() {

             // main tab 은 제외
             if( this.id =='tabs-main') {
             	return true;
             }
             var parentId = $(this).remove().attr("aria-controls");

              //alert();
              //$( this).remove();
              $( "#" + parentId ).remove();
         })
         tabs.tabs( "refresh" );

         //상단 Naviagtion 메인으로 세팅
         $("#fullPath").text("메인");


         });

         
         var url = "<c:url value="/sys/dash/indexSysDashIDtl.do" />";
      	 
      	 
		 $("#tabs-1").append("<p>"+ "<iframe src="+url+" style=border:0;scroll:no;width:100%; onload=calcHeight(this);setFullPath('"+url+"'); />"+ "</p>");
		 tabs.tabs("refresh");


		 var prev;

		 $('select').on('focus', function(){
			 prev = this.value;
		 }).change(function(){
			 if(confirm("열려있는 화면이 모두 닫힙니다. 계속 진행하시겠습니까?")){
			    	$("li[id*='tabs-']").each(function() {
			            if( this.id =='tabs-main') {
			            	return true;
			            }
			            var parentId = $(this).remove().attr("aria-controls");
			            $( "#" + parentId ).remove();
			        })
			        tabs.tabs( "refresh" );
			        $("#fullPath").text("메인");
		    		modInsttCd(this);
		    		var src = $('#tabs-1 > p > iframe').attr('src');
		    		$('#tabs-1 > p > iframe').attr('src', src);
		    	}else{
					 $(this).val(prev);
		    	}
		 });


		 
		 if(pwdChng == "OVER"){

			 	var alertMsg = (pwdClr == 'CLEAR') ? "비밀번호 변경 3개월이 경과하여 비밀번호를 변경해주시기 바랍니다." : "비밀번호가 초기화 되었습니다. 비밀번호를 변경해주세요.";
			 	fn_showUserPage(alertMsg);
			    var callUrl = "<c:url value = "/com/PageLink.do"/>" +'?link=/frame/flyt/login/flytLoginOverPWPDtlPop&divId=ChgPwPopup&pwdClr='+pwdClr;
				
			    	
			    $('#ChgPwPopup')
			     .dialog({
			       modal: true,
			       open: function (){
			       	$(this).load(callUrl, {limit: 5},  function (responseText, textStatus, req) {

			       			 if (textStatus == "error") {
			       				//alert('요청작업 중 오류가 발생했습니다.');
			       	  	           // alert(responseText);
			       				 $('#ChgPwPopup').html(responseText);
			       	  	          //$(this).dialog("close");//();
			       	  	         return;
			       	        }
			       	});
			       },
			       height: 400,
			       width: 500,
			       title: '비밀번호 변경',
			       resizable: false,
			       close : function(){
			    	   $(this).dialog("close");
			    	   $(this).empty();
			       }
			   });
			}
});


/**
 *
 * 상단 탭 열기
 * @author: Kjm
 * @since : 2018-10-10
 * @param {fullName}   상단 페이지 Full 경로
 * @param {url}        호출 URL
 * @param {menuNm}     메뉴명
 * @param {menuId}     메뉴 ID
 *
 *
 */
	function addNaviTab(fullName, url, menuNm, menuId) {

		//console.log( $("#"+menuId));

		// @!@ 재오픈 시키기
		if(menuId == "M000000033") {
			if ($("#tabs-M000000033").length > 0) {
				var removedId = $("#li_tabs-M000000033").remove().attr("aria-controls");
	            $( "#" + removedId ).remove();
			}
 		}else if(menuId == "M000000032") {
			if ($("#tabs-M000000032").length > 0) {
				var removedId = $("#li_tabs-M000000032").remove().attr("aria-controls");
	            $( "#" + removedId ).remove();
			}
 		}else if(menuId == "M000000035") {
			if ($("#tabs-M000000035").length > 0) {
				var removedId = $("#li_tabs-M000000035").remove().attr("aria-controls");
	            $( "#" + removedId ).remove();
			}
 		}else if(menuId == "M000000113") {
			if ($("#tabs-M000000113").length > 0) {
				var removedId = $("#li_tabs-M000000113").remove().attr("aria-controls");
	            $( "#" + removedId ).remove();
			}
 		}else if(menuId == "M000000087") {
			if ($("#tabs-M000000087").length > 0) {
				var removedId = $("#li_tabs-M000000087").remove().attr("aria-controls");
	            $( "#" + removedId ).remove();
			}
 		}else if(menuId == "M000000088") {
			if ($("#tabs-M000000088").length > 0) {
				var removedId = $("#li_tabs-M000000088").remove().attr("aria-controls");
	            $( "#" + removedId ).remove();
			}
 		}


		$("#fullPath").text(fullName);

		//현재 탭이 열려 있는 경우 해당 탭으로 이동
		if ($("#tabs-" + menuId).length > 0) {
			$('#tabs').tabs("option", "active", $("#tabs-" + menuId).index()-1);
			return;
		}

		var maxOpenTabCnt = 10;
		if ($("li[id*='tabs-']").length >= maxOpenTabCnt) {
			fn_showUserPage('탭은 ' + maxOpenTabCnt + ' 개 까지만 열 수 있습니다.');
			return;
		}
		//tabTemplate = "<li><a href='@{href}'>@{label}</a> <span class='ui-icon ui-icon-close' role='presentation'>Remove Tab</span></li>",
		//li = $( tabTemplate.replace( /@\{href\}/g, "#" + menuId ).replace( /@\{label\}/g, label ) ),

		var id = "tabs-" + menuId
		  , li  = "<li id='li_"+id+"' data='"+fullName+"'><a href='#"+id+"'>"+ menuNm+ "</a> <span class='ui-icon ui-icon-close' role='presentation' style='padding-left: 10px; margin-right: 3px;'>Remove Tab</span><span class='ui-icon-reload' role='presentation' style='position: absolute;width: 12px;height: 12px;right: 6px;top: 12px;font-size: 0;'><a href='javascript:void(0)'><img src='/css/jims/images/icons/btn_refrsh.png'/></a></span></li>";

		tabs.find(".ui-tabs-nav").append(li);
		tabs.append("<div id='" + id + "'><p>"+ "<iframe src="+url+" style=border:0;width:100%; scrolling=no onload=calcHeight(this);setFullPath('"+url+"');/></iframe>"+ "</p></div>");
		tabs.tabs("refresh");

		// index는 0부터 시작 ,방금 연 탭을 활성화
		$('#tabs').tabs("option", "active", $('#tabs >ul >li').length - 1);

	}

	 // 20201022 iframe 자동 높이 추가
	function calcHeight(arg){
		var the_height = arg.contentWindow.document.body.scrollHeight+20;
		arg.height = the_height > 730 ? the_height : 730 ;
		arg.style.overflow = "hidden";
 	}
 
	 function setFullPath(url){
		 $("iframe[src='"+url+"']").contents().find('div.loca_list').text($("li[aria-selected=true]").attr("data"));
	 }


	function fn_openMenu(fullName, url, menuNm, menuId) {

		//console.log()
		//console.log('resultInfo__'+$('#contextPathHolder').attr('data-contextPath'));
		addNaviTab(fullName, fn_getRequestURI() + url, menuNm, menuId);

	}
	

    function fn_getRequestURI()
    {
        var requestURI = "<%= request.getContextPath()%>";

        return requestURI;
    }


    function fn_userInfoChange(userId)
    {
    	
    	var uHeight = 800;

    	var callUrl = "<c:url value = "/com/PageLink.do"/>"

    	requestUtil.mdPop({
    		popUrl : callUrl+"?link="+"frame/fsys/user/fsysUserUserUpdPDtlPop&userId="+encodeURIComponent(userId),
    		height: 700,
            width: 1000,
            title: '사용자 정보 수정',
            divId : 'divUserPopUp'
    	});
    	
    	
    }

    /*
    */
    function fn_UserDialogCallBack(data)
    {
    
        $("#userName").text(data.resultMap.userNm+'['+data.resultMap.userGbNm+']');
    }
    

    

	 function fn_onTabClose(menuNo){
	 	$('#tabs-'+menuNo).remove();
	  	$('#li_tabs-'+menuNo).remove();
	  	$("#tabs").tabs().tabs( "refresh" );
	 }

	 var divHelpdialog;

	 var serverIp = "<%=request.getServerName()%>" + ":" + "<%=request.getServerPort()%>";
	 
	 /* ------------------------------------------ */
	 // 「WebSocketEx」는 프로젝트 명

	// 「websocket」는 호스트 명
	// WebSocket 오브젝트 생성 (자동으로 접속 시작한다. - onopen 함수 호출)
	//var webSocket = new WebSocket("ws://localhost:8080/WebSocketEx/websocket");
	//var webSocket = new WebSocket("ws://192.168.0.122:8088/websocket/"+session_userip+"/"+session_usergb+"/"+session_userid);
	var webSocket = new WebSocket("ws://"+serverIp+"/websocket/"+session_userip+"/"+session_usergb+"/"+session_userid);
	
	// 콘솔 텍스트 에리어 오브젝트
	var messageTextArea = document.getElementById("messageTextArea");
	
	// WebSocket 서버와 접속이 되면 호출되는 함수
	webSocket.onopen = function(message) {
		// 콘솔 텍스트에 메시지를 출력한다.
		document.getElementById("messageTextArea").value += "Server connect...\n";
	};
	
	// WebSocket 서버와 접속이 끊기면 호출되는 함수
	webSocket.onclose = function(message) {
		// 콘솔 텍스트에 메시지를 출력한다.
		document.getElementById("messageTextArea").value += "Server Disconnect...\n";
	};
	
	// WebSocket 서버와 통신 중에 에러가 발생하면 요청되는 함수
	webSocket.onerror = function(message) {
		// 콘솔 텍스트에 메시지를 출력한다.
		document.getElementById("messageTextArea").value += "error...\n";
	};
	
	// WebSocket 서버로 부터 메시지가 오면 호출되는 함수
	webSocket.onmessage = function(message) {
		// 콘솔 텍스트에 메시지를 출력한다.
		document.getElementById("messageTextArea").value += "Recieve From Server => " + message.data+ "\n";
		var msgArr = message.data.split(", ");
		$("#vltnDt").val(msgArr[0].split(":")[1]);
		$("#vltnSno").val(msgArr[1].split(":")[1]);
		fn_clickBtn("open", message.data);
	};
	
	// Send 버튼을 누르면 호출되는 함수
	function sendMessage() {
		// 송신 메시지를 작성하는 텍스트 박스 오브젝트를 취득한다.
		var message = document.getElementById("textMessage");
		
		// 콘솔 텍스트에 메시지를 출력한다.
		document.getElementById("messageTextArea").value += "Send to Server => " + message.value + "\n";
		
		// WebSocket 서버에 메시지를 송신한다.
		webSocket.send(message.value);
		
		// 송신 메시지를 작성하는 텍스트 박스를 초기화한다.
		message.value = "";
	}
	
	// Disconnect 버튼을 누르면 호출되는 함수
	function disconnect() {
		// WebSocket 접속 해제
		webSocket.close();
	}
	
	function fn_clickBtn(state, messageData){
		switch(state){
			case 'open':
		        $( 'div.newBoard > div.b' ).slideUp();
		        $( 'div.newBoard > div.c' ).slideDown();
				break;
				
			case 'close':
		        $( 'div.newBoard > div.b' ).slideDown();
		        $( 'div.newBoard > div.c' ).slideUp();
				break;
		}
		$( '#messageData' ).text(messageData);
	} // fn_clickBtn
	
	function fn_updEvdcWarnAlamCfrmY(){
		var callUrl = "<c:url value='/fsys/alam/updEvdcWarnAlamCfrmY.do'/>";
        requestUtil.save({callUrl:callUrl,srhFormNm:'updEvdcWarnAlamCfrmYForm',callbackNm:'fn_updEvdcWarnAlamCfrmYCallback',skip:"Y"});
	}
	
	function fn_updEvdcWarnAlamCfrmYCallback(data){
		fn_clickBtn("close", "");
	}
</script>

</head>
<body>

<link id="contextPathHolder" data-contextPath="${pageContext.request.contextPath}"/>
<!-- wrap 

<div id="wrap">

	<div class="header_wrap">
    <div class="header">
      <div class="logo"><a href=""><img src="/images/logo.gif" /></a></div>
      <div>
		<ul class="gnb_comm">
			  <li><img src="/images/login.png" /> <strong>이기현</strong>님이 로그인하셨습니다.</li>
              <li><img src="/images/my_info.png" /> 내사건</li>
			  <li><img src="/images//info_cha.png"/> 정보변경</li>
              <li>
              <img src="/images/logout.png" /> <strong><a href="<c:url value="/login/actionLogout.do" />" >로그아웃</a></strong></li>
		  </ul>
      </div>
    </div>  
</div>
//-->

	<!-- sidebar -->
	 <div id="left_wrap"> 
        <nav class="sidebar">
            <div class="header">
              <div  class="logo"><a href="/flyt/main/indexFLytMainIDtl.do"><img src="/images/logo.gif" width="240px" height="45px" /></a></div>
              <div>
		        <ul class="gnb_comm" style="display:flex; 
											align-content:flex-start; 
											flex-direction:column; 
											flex-wrap:wrap; 
											overflow:auto;">
			     <li>
				     <img src="/images/login.png" /> 
				     <strong>
					     <a id="userName" href="#" style="font-size:14px;text-decoration: none;" onclick="javascript:fn_userInfoChange('<c:out value="${user.userId}"/>');">
					     	<%-- <c:out value="${user.userNm}"/>[<c:out value="${user.userGbNm}"/>] --%>
					     	<c:choose>
						        <c:when test="${fn:length(user.userNm) gt 6}">
							        <c:out value="${fn:substring(user.userNm, 0, 6)}"/>..
						        </c:when>
						        <c:otherwise>
							        <c:out value="${user.userNm}"/>
							    </c:otherwise>
							</c:choose>
							[<c:out value="${user.userGbNm}"/>]
					     </a>
				     </strong>님
			     </li>
				 <li>
					 <img src="/images/logout.png" title="로그아웃"/>
					 <strong>
					 	<a href="<c:url value="/flyt/login/procFLytLoginLogoutPDtl.do" />" style="font-size:14px;text-decoration: none;">로그아웃</a>
					 </strong>
				 </li>
		        </ul>
		      </div>
           </div>
		<!-- <a href="#" class="btn_menu">기본메뉴</a>
		<a href="javascript:void(0); alert('준비중입니다.');" class="btn_bookmark">즐겨찾기</a> -->
		<h2 class="hidden">주메뉴</h2>
		<div class="btn_lock lock"><a href="#">메뉴 펼침</a></div>
		<ul class="lnb" id="lnb">
			<c:forEach items="${menuList}" var="resultInfo" varStatus="status">
				<c:if test="${status.first}"><li></c:if>
				<c:if test="${resultInfo.lvl == 2}">
					<c:if test="${top == 3}">
						</ul></li><li>
					</c:if>
					<c:if test="${top == 2}">
						</li><li>
					</c:if>
				</c:if>
				<c:if test="${resultInfo.lvl == 2}"><a href="#">${resultInfo.menuNm}</a></c:if>

				<c:if test="${resultInfo.lvl == 3}"><c:if test="${top == 2}"><ul></c:if></c:if>

				<c:if test="${resultInfo.lvl == 3}">
					<li>
					    <a href="javascript:fn_openMenu('${resultInfo.depthFullname}','${resultInfo.url}','${resultInfo.menuNm}','${resultInfo.menuNo}')">
					    	<img src="/images/3dpt_icon.gif">
					    	${resultInfo.menuNm}
					    	</img>
					    </a>
					</li>
				</c:if>

				<c:set var="top" value="${resultInfo.lvl}"/>

			</c:forEach>
		</ul>
	</nav>
 	</div>
	
	
	
	<hr />
	<div id="container">
		<!-- contents -->
		<article id="contents" class="contents" role="main">
		

		 <div id="tabs">
		
 



 
			  <ul>
			    <div style="float:left"><a id="close_all" href="#" class="btn_close_all">전체닫기</a></div> 
			    <li id='tabs-main' data='메인'><a href="#tabs-1">Main</a>
			    <span class='ui-icon-reload' role='presentation' style='position: absolute;width: 12px;height: 12px;right: 6px;top: 12px;font-size: 0;'><a href='javascript:void(0)'><img src='/css/jims/images/icons/btn_refrsh.png'/></a></span>
			    <!--
			    <span class="ui-icon ui-icon-close" role="presentation">Remove Tab</span>
			     -->
			    </li>
			  </ul>
			  <div id="tabs-1">
			    <!--
			    <p>Main Dashboard</p>
			     -->
<form style="display: none;">
<!-- 송신 메시지를 작성하는 텍스트 박스 -->
<input id="textMessage" type="text">
<!-- 메시지 송신을 하는 버튼 -->
<input onclick="sendMessage()" value="Send" type="button">
<!-- WebSocket 접속 종료하는 버튼 -->
<input onclick="disconnect()" value="Disconnect" type="button">
<!-- 콘솔 메시지의 역할을 하는 로그 텍스트 에리어.(수신 메시지도 표시한다.) -->
<textarea id="messageTextArea" rows="10" cols="50"></textarea>
</form>
<form id="updEvdcWarnAlamCfrmYForm" name="updEvdcWarnAlamCfrmYForm" style="display: none;">
	<input type="hidden" name="vltnDt" id="vltnDt" value="" />
	<input type="hidden" name="vltnSno" id="vltnSno" value="" />
</form>
<div class="newBoard">
<div class="b" style="background-color: green; display: block;"></div>
<div class="c" style="background-color: #00ffb8; display: none;"><span id="messageData"></span><a href="javascript:void(0)" onclick="fn_updEvdcWarnAlamCfrmY();return false;" class="myButton">확인</a></div>
</div>
			  </div>
		</div>
		
			<div class="help-tip" style="display:none">

              <p>1. 순수하게 CSS로 만들었어요

              <br>1. 순수하게 CSS로 만들었어요

              <br>1. 순수하게 CSS로 만들었어요

              <br>1. 순수하게 CSS로 만들었어요

              <br>1. 순수하게 CSS로 만들었어요

              <br>1. 순수하게 CSS로 만들었어요

              </p>

        </div>

		</article >
		<!-- // contents -->
	</div>
	<!-- // container -->
</div>

<!-- 사용자 정보 수정 팝업 -->
<div id='divUserPopUp'></div>
<!-- 비밀번호 변경 팝업 -->
<div id='ChgPwPopup'></div>

<!-- 
<div id='orgPopup'></div>
<div id='divChgPwPopup'></div>
<div id='divUserMwPopup'></div>
<div id='divHelpPopup'></div>
<div id='test'></div>
 -->
<!-- // wrap -->
<div id="comSearchDiv" style="display: none;"></div>
</body>
</html>