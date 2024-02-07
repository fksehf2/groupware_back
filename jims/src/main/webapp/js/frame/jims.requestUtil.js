
var jims =jims||{};
requestUtil = jims.requestUtil||{};



/**
 * 상세 화면등에서 조회용 Transaction 관리
 * @param callUrl:호출URL
 * @param srhFormNm:조회 값이 있는 폼ID
 * @param setFormNm: 조회한 값을 세팅한 폼 ID
 * @param callbackNm: 콜백함수명 'fn_callback'
 */
requestUtil.search = function (args){
	
	//alert(session_userid);
	//alert(session_userid);

	var data = $("#"+args.srhFormNm+"").serialize();
	var rqesterMenuId = "";
	if( parent.$('li[aria-controls^=tabs-][aria-expanded=true]').attr('aria-controls') != undefined ) {
		rqesterMenuId = parent.$('li[aria-controls^=tabs-][aria-expanded=true]').attr('aria-controls').replace("tabs-", "");
		data += '&rqesterMenuId=' + rqesterMenuId;
	}
	
	//console.log('requestUtil data___['+data+']');
	isErrorMsg = false;
	$.ajax({
		type:'POST',
		data : data,
		datatype:'json',
		url : args.callUrl,
		success : function(data){

			//세션 타임시간 초기화
			try{
				//parent.fncClearTime();
			} catch (e){
				try {
					//parent.parent.fncClearTime();
				} catch(e){
					//fncClearTime();
				}
			}

			if(data[userComMessageId] != undefined  ) {

				fn_showUserPage(data[userComMessageId], args.callbackNm);

        		if( args.errCallbackNm != undefined) {
    				eval(args.callbackNm+'(data)');
    			}

        		return;

			} else if ( data[userErrMessageId] != undefined) {
        		isErrorMsg = true;
        		fn_showUserPage(data[userErrMessageId]);
        		if( args.callbackNm != undefined) {
    				eval(args.callbackNm+'(data)');
    			}

        		return;
        	}



			requestUtil.setComponent(data,args);

			//gfn_loadingBlock('off');
			// callback 호출
			if( args.callbackNm != undefined) {
				eval(args.callbackNm+'(data)');
			}
		},
		error : function(xhr, status, error){
			//alert('ajax error'+error );
			//gfn_loadingBlock('off');
			//alert("code:"+xhr.status+"\n"+"message:"+xhr.responseText+"\n"+"error:"+error);

			//세션 타임시간 초기화
			try{
				//parent.fncClearTime();
			} catch (e){
				try {
					//parent.parent.fncClearTime();
				} catch(e){r
					//fncClearTime();
				}
			}

			fn_showErrorPage();

		}
	});

}

requestUtil.searchList = function (args){

	//console.log(callee);
	//alert(args.callee.caller);
	var data = $("#"+args.srhFormNm+"").serialize();
	data += "&page="+args.page+"&perPageNum="+args.perPageNum;
	var rqesterMenuId = "";
	if( parent.$('li[aria-controls^=tabs-][aria-expanded=true]').attr('aria-controls') != undefined ) {
		rqesterMenuId = parent.$('li[aria-controls^=tabs-][aria-expanded=true]').attr('aria-controls').replace("tabs-", "");
		data += '&rqesterMenuId=' + rqesterMenuId;
	}
	
	//alert(data);
	//console.log('data'+data);
	
	isErrorMsg = false;
	$.ajax({
		type:'POST',
		data : data,
		datatype:'json',
		url : args.callUrl,
		beforeSend: function () {
			$(".modal").show();
			//debugger;
		},
		complete:  function () {
			$(".modal").hide();
		},
		success : function(data){

			//세션 타임시간 초기화
			try{
				//parent.fncClearTime();
			} catch (e){
				try {
					//parent.parent.fncClearTime();
				} catch(e){
					//fncClearTime();
				}
			}

			if(data[userComMessageId] != undefined  ) {

				fn_showUserPage(data[userComMessageId], args.callbackNm);

        		if( args.errCallbackNm != undefined) {
    				eval(args.callbackNm+'(data)');
    			}

        		return;

			} else if ( data[userErrMessageId] != undefined) {
        		isErrorMsg = true;
        		alert(data[userErrMessageId]);
        		if( args.callbackNm != undefined) {
    				eval(args.callbackNm+'(data)');
    			}

        		return;
        	}

			requestUtil.setComponent(data,args);

			//gfn_loadingBlock('off');
			// callback 호출
			if( args.callbackNm != undefined) {
				eval(args.callbackNm+'(data)');
			}
		},
		error : function(xhr, status, error){
			//alert('ajax error'+error );
			//gfn_loadingBlock('off');
			//alert("code:"+xhr.status+"\n"+"message:"+xhr.responseText+"\n"+"error:"+error);

			//세션 타임시간 초기화
			try{
				//parent.fncClearTime();
			} catch (e){
				try {
					//parent.parent.fncClearTime();
				} catch(e){r
					//fncClearTime();
				}
			}

			fn_showErrorPage();

		}
	});

}


/**
 * 조회 그리드용 공통 콜백
 * @param args:그리드 조회시 보낸 파라미터
 * @param data:서버에서 보낸 값
 */
requestUtil._globalCallBack = function(args,data) {

	// 화면 SIZE 조절
	$(window).trigger('resize');

	//세션 타임시간 초기화
	try{
		//parent.fncClearTime();
	} catch (e){
		//parent.parent.fncClearTime();
	}

	if( args.callbackNm != undefined) {
		eval(args.callbackNm+'(data)');
    }

}


requestUtil.isErrorMsg = function() { return isErrorMsg;},


requestUtil.setComponent = function (data,args){
	//alert(1);
	$.each( data, function( key, value ) {
		//console.log('data___'+data);
		//console.log( key + ": " + value );
		if( typeof value =='object'){ // 객체 ,배열인경우

			var setFormId;
			if( args.setFormNm == undefined) {
				setFormId = args.srhFormNm;
			} else {
				setFormId = args.setFormNm;
			}
			var selectorID = "#"+setFormId+" #"+key+"";
			var setVal     = value==null?'':value;

			$.each(value==null?'':value ,function( key2,value2){
				//console.log( key2 + ": " + value2 );
				//console.log( $.type($("#"+args.setFormNm+" #"+key2+"")));
				var radioSelectorID = "input[name="+key2+"]";
				selectorID      = "#"+setFormId+" #"+key2;
				setVal          = value2==null?'':value2;

				//console.log('selectorID__'+selectorID);
				//console.log('setVal__'+setVal);
				
				if( $(selectorID).is('input:checkbox')) {

				} else if( $(selectorID).is('select')) {
					$(selectorID).val(setVal).prop("selected", true);
					//$(selectorID + ' option[value='+setVal+']').attr('selected','selected');
				} else if( $(selectorID).is('span')) {
					$(selectorID).text(setVal);
				} else if ($(radioSelectorID).is('input:radio')) {
					$(radioSelectorID).val([setVal]);
				} else {
					
					$(selectorID).val(setVal);
				}

			  })

		  } else { //원시타입입경우 (문자열, 숫자, 불리언 )

			  $(selectorID).val(setVal);

		  }
	});
}

requestUtil.save = function (args){

	//gfn_loadingBlock('on');
	//console.log($("#"+args.srhFormNm+"").serialize());
	var data = $("#"+args.srhFormNm+"").serialize();
	var rqesterMenuId = "";
	if( parent.$('li[aria-controls^=tabs-][aria-expanded=true]').attr('aria-controls') != undefined ) {
		rqesterMenuId = parent.$('li[aria-controls^=tabs-][aria-expanded=true]').attr('aria-controls').replace("tabs-", "");
		data += '&rqesterMenuId=' + rqesterMenuId;
	}
	
	$.ajax({
		type:'POST',
		//data : $("#"+args.srhFormNm+"").serialize(),
		data : data,
		datatype:'json',
		url : args.callUrl,
		beforeSend: function () {
								 $(".modal").show();
							    },
        complete:  function () {
        						$(".modal").hide();
        					   },
		success : function(data){

			//세션 타임시간 초기화
			try{
				//parent.fncClearTime();
			} catch (e){
				try {
					//parent.parent.fncClearTime();
				} catch(e){
					//fncClearTime();
				}
			}

			//gfn_loadingBlock('off');
			if(data[userComMessageId] != undefined  ) {

				fn_showUserPage(data[userComMessageId], args.callbackNm);

        		if( args.errCallbackNm != undefined) {
    				eval(args.errCallbackNm+'(data)');
    			}

        		return;

			} else if ( data[userErrMessageId] != undefined) {
        		isErrorMsg = true;
        		fn_showUserPage(data[userErrMessageId]);
        		if( args.callbackNm != undefined) {
    				//eval(args.callbackNm+'(data)');
    			}

        		return;
        		
        	} 
			
			// 성공
			if ( data[sysSucMessageId] != undefined ){
				fn_showUserPage(data[sysSucMessageId],args.callbackNm,data);
			} else {
				$(".modal").hide();
				//fn_showUserPage("요청처리가 성공적으로 수행되었습니다.",args.callbackNm,data);
				//alert("요청처리가 성공적으로 수행되었습니다."); // success.request.msg
				if(args.skip != undefined && args.skip == 'Y'){
					//callback 호출
					if( args.callbackNm != undefined) {
						eval(args.callbackNm+'(data)');
					}
				}else{
					fn_showUserPage("요청처리가 성공적으로 수행되었습니다.",args.callbackNm,data);					
				}
			}

			// callback 호출
			//if( args.callbackNm != undefined) {
			//	eval(args.callbackNm+'(data)');
			//}



		},
		error : function(xhr, status, error){
			//alert('ajax error'+error );
			//gfn_loadingBlock('off');
			//alert("code:"+xhr.status+"\n"+"message:"+xhr.responseText+"\n"+"error:"+error);

			//세션 타임시간 초기화
			try{
				//parent.fncClearTime();
			} catch (e){
				try {
					//parent.parent.fncClearTime();
				} catch(e){
					//fncClearTime();
				}
			}

			fn_showErrorPage();

		}
	});

}

requestUtil.saveData = function (args){

	var data;
	var rqesterMenuId = "";
	if( parent.$('li[aria-controls^=tabs-][aria-expanded=true]').attr('aria-controls') != undefined ) {
		rqesterMenuId = parent.$('li[aria-controls^=tabs-][aria-expanded=true]').attr('aria-controls').replace("tabs-", "");
	}
	
	if(args.data != undefined){

		if( typeof args.data =='object'){
			args.data.rqesterMenuId = rqesterMenuId;
			data = JSON.stringify(args.data);
		}else{
			data = args.data;
		}

	}

	if(args.srhFormNm != undefined){

		data == null ? $("#"+args.srhFormNm+"").serialize() : args.data + "&" + $("#"+args.srhFormNm+"").serialize();
		
	}

	$.ajax({
		type:'POST',
		data : data,
		dataType : "json",
		contentType:"application/json",
		url : args.callUrl,
		beforeSend: function () {
								 $(".modal").show();
							    },
        complete:  function () {
        						$(".modal").hide();
        					   },
	   success : function(data){

			//세션 타임시간 초기화
			try{
				//parent.fncClearTime();
			} catch (e){
				try {
					//parent.parent.fncClearTime();
				} catch(e){
					//fncClearTime();
				}
			}

			//gfn_loadingBlock('off');
			if(data[userComMessageId] != undefined  ) {

				fn_showUserPage(data[userComMessageId], args.callbackNm);

        		if( args.errCallbackNm != undefined) {
    				eval(args.errCallbackNm+'(data)');
    			}

        		return;

			} else if ( data[userErrMessageId] != undefined) {
        		isErrorMsg = true;
        		fn_showUserPage(data[userErrMessageId]);
        		if( args.callbackNm != undefined) {
    				//eval(args.callbackNm+'(data)');
    			}

        		return;
        		
        	} 
			
			// 성공
			if ( data[sysSucMessageId] != undefined ){
				fn_showUserPage(data[sysSucMessageId],args.callbackNm,data);
			} else {
				$(".modal").hide();
				if(args.skip != undefined && args.skip == 'Y'){
					//callback 호출
					if( args.callbackNm != undefined) {
						eval(args.callbackNm+'(data)');
					}
				}else{
					fn_showUserPage("요청처리가 성공적으로 수행되었습니다.",args.callbackNm,data);					
				}
			}

		},
		error : function(xhr, status, error){
			//alert('ajax error'+error );
			//gfn_loadingBlock('off');
			//alert("code:"+xhr.status+"\n"+"message:"+xhr.responseText+"\n"+"error:"+error);

			//세션 타임시간 초기화
			try{
				//parent.fncClearTime();
			} catch (e){
				try {
					//parent.parent.fncClearTime();
				} catch(e){
					//fncClearTime();
				}
			}

			fn_showErrorPage();

		}
	});

}

requestUtil.ftpFileAjax = function (args){
	//data:{"type": "ftp_uploadFile","hash": "sha256","src" : ["C:/Users/admin/Desktop/c.exe"], "dst" : {"path": "/case1/ev1"}}  
	//data:{"type": "ftp_removeFile", "src": [ "/case1/ev1/c.exe" ]}
	//data:{"type": "ftp_downloadFile","path": "/case1/ev1/c.exe"}
	//fn_showUserPage("요청처리가 성공적으로 수행되었습니다. 첨부파일 관련 기능은 협의 후 완료 예정입니다.",args.callbackNm,data);	
	//return false;
	var data = JSON.stringify(args.data);
	var callUrl = 'http://localhost:57626/service';
	
	$.ajax({
		type:'POST',
		crossOrigin : true,
		data : data,
		dataType : "json",
		url : callUrl,
		beforeSend: function () {
								 $(".modal").show();
							    },
        complete:  function () {
        						$(".modal").hide();
        					   },
	   success : function(data){
		   			console.log("success :: \n"+JSON.stringify(data));
		   console.log("code : "+data.code+", msg : " +  data.msg);
					$(".modal").hide();
					
					if(data.code == '10000'){
						if(args.skip != undefined && args.skip == 'Y'){
							//callback 호출
							if( args.callbackNm != undefined) {
								eval(args.callbackNm+'(data)');
							}
						}else{
							fn_showUserPage("요청처리가 성공적으로 수행되었습니다.",args.callbackNm,data);					
						}						
					} else {
						fn_showErrorPage();
					}
					

		},
		fail:function(data){
            console.log("fail :: \n"+JSON.stringify(data));
            fn_showErrorPage();
        },
		error : function(xhr, status, error){			
			console.log("error :: \n"+JSON.stringify(data));	
			fn_showErrorPage();

		}
	});
	
/*	$.ajax({
		url:'http://localhost:57626/service',
        type:'post',
		dataType: 'json',
		crossOrigin : true,
        data: JSON.stringify({
        	"type": "ftp_removeFile",
            "src": [ "/1/Hello.java" ]
    	}),
        success:function(data){
            console.log("success :: \n"+JSON.stringify(data))
        },
        fail:function(data){
            console.log("fail :: \n"+JSON.stringify(data))
        }

	});*/

}


function objectifyForm(formArray) {
	// console.log('returnArray___'+console.dir(formArray));
	  var returnArray = {};
	  for (var i = 0; i < formArray.length; i++){

		  //console.log(formArray[i]['name']+',,'+formArray[i]['value']);


		var tmpVal = formArray[i]['value'];//.replaceAll('____-__-__','');

		// 달력의 경우 underline값을 제거
	    returnArray[formArray[i]['name']] = tmpVal.replace('____-__-__','');
	  }

	//  console.log('returnArray___'+console.dir(returnArray));
	  return returnArray;
}



$.fn.extractObject = function () {
    var accum = {};

    function add(accum, namev, value) {
        if (namev.length == 1) {
            accum[namev[0]] = value;
        } else {
            if (accum[namev[0]] == null)
                accum[namev[0]] = {};
            add(accum[namev[0]], namev.slice(1), value);
        }
    }
    ;
    this.find('input, textarea, select').each(function () {
        add(accum, $(this).attr('name').split('.'), $(this).val());
    });

    return accum;
}

/**
 * 그리드 조회
 * @constructor
 *
 *
 * @author: Kjm
 * @date  : 2018-10-10
 * @this {requestUtil}
 * @param {args}
 * searchFormID : 조회 폼 ID
 * gridID       : 조회할 그리드 ID
 * @reutrn {void}
 */
//const userMessageId ='_server_usermessage_';
const userComMessageId   = '_server_user_com_message_';
const userErrMessageId   = '_server_user_err_message_';
const sysSucMessageId = '_server_sys_suc_message_';
const nullListMsg = '조회 결과가 없습니다.';

var isErrorMsg =false;

requestUtil.searchGrid = function (args){
	var searchFormID =args.srhFormNm;

	//alert(searchFormID);
	var formArray = $("#"+searchFormID).serializeArray();

	var abc = formArray;
	//console.log('data___'+formArray);
	var page = 1;
	var reccount = 10;
	if(typeof args.page != "undefined"){
		page = args.page;
	}

	if(typeof args.reccount != "undefined"){
		reccount = args.reccount;
	}
	//alert(args.gridID);
	jQuery("#"+args.gridID).setGridParam({
        postData: objectifyForm(formArray),
        datatype: "json",
        mtype: 'POST',
        page:page,
        reccount : reccount,
        loadComplete : function (data) {
            //alert(data);
        	//console.log(data);

        	if(data[userComMessageId] != undefined) {
        		fn_showUserPage(data[userComMessageId]);
        	}

        	/*if( args.callbackNm != undefined) {
				eval(args.callbackNm+'(data)');
			}*/
        	requestUtil._globalCallBack(args,data);

    	},
    	error : function(){
    	}
    }).trigger("reloadGrid").jqGrid("setFrozenColumns");

	//$(window).trigger('resize');

}



requestUtil.mdPop = function (args){


	$.ajaxSetup({cache: false});
	$('#'+args.divId).html('');

	var callUrl =args.popUrl;
	$('#'+args.divId)
     .dialog({
       modal: true,
       open: function (){
       	$(this).load(callUrl, {limit: 5},  function (responseText, textStatus, req) {

       			 if (textStatus == "error") {
       				//alert('요청작업 중 오류가 발생했습니다.');
       	  	           // alert(responseText);
       				 $('#'+args.divId).html(responseText);
       	  	          //$(this).dialog("close");//();
       	  	         return;
       	        }
       			$(this).find('input:eq(0)').focus();  
       	});
       },
       height: args.height+1,
       width: args.width,
       title: args.title,
       resizable: args.resizable == null ? true : args.resizable, 
       close : function(){
    	   $(this).dialog("close");
    	   $(this).empty();
       }
   });

   return $('#'+args.divId);


}

/**
 * 엑셀다운로드 공통 기능
 * @constructor
 *
 *
 * @author: Kimkh
 * @date  : 2018-12-04
 * @this {requestUtil}
 * @param {args}
 * formNm  : 조회 폼명
 * gridID  : 헤더를 생성할 그리드 ID
 * titleinfo : 헤더정보( 그리드 헤더 사용 시 생략가능)
 * callUrl   : 엑셀 다운로드 경로
 * @reutrn {void}
 */
requestUtil.comExcel = function(args)
{
	var titleInfo;

	if(args.titleInfo != "" && typeof args.titleInfo != "undefined"){
		titleInfo = args.titleInfo;
	} else {
		titleInfo = fn_excelHeader(args.gridID)
	}

	var callUrl = "/jims/com/util/fileDownloadExcel.do";
	if(args.callUrl != "" && typeof args.callUrl != "undefined"){
		callUrl = args.callUrl
	}

	//파일 다운로드 완료 후 로딩바 제거위해 호출
	gfn_setCookie("fileDownload","false");
	requestUtil.comExcelProgress();

	//로딩바 출력
	$(".modal").show();

	$("#"+args.formNm + " #titleinfo").val(titleInfo);
	$("#"+args.formNm+"").attr("method","post");
	$("#"+args.formNm+"").attr("action",callUrl).submit();

}

requestUtil.comExcelProgress = function()
{
	//controler에서 파일 다운 완료 후 쿠키 저장
    if (document.cookie.indexOf("fileDownload=true") != -1) {
        var date = new Date(1000);
        document.cookie = "fileDownload=; expires=" + date.toUTCString() + "; path=/";
        //로딩바 OFF
        $(".modal").hide();
        return;
    }
    //파일 다운로드 완료 체크위해 1초 재귀호출
    setTimeout(requestUtil.comExcelProgress , 1000);
}


// form 필수값체크 [ formNm : 폼 name ]
// alert로 띄워질 부분은 해당 태그의 title에 입력
requestUtil.requiredFormChk = function(formNm)
{
	$('#'+formNm).find("input, selecct, textarea").each(function(){
		if($(this).prop("required")){
			//console.log($(this).val())
			if(!jQuery.trim($(this).val())){
				alert($(this).prop("title")+"을(를) 입력해 주십시요.")
				$(this).focus();
				return false;
			}
		}
	});
}

/**
 * 상세 화면등에서 조회용 Transaction 관리
 * @param callUrl:호출URL
 * @param srhFormNm:조회 값이 있는 폼ID
 * @param data:넘기는값
 * @param setFormNm: 조회한 값을 세팅한 폼 ID
 * @param callbackNm: 콜백함수명 'fn_callback'
 */
requestUtil.search2 = function (args){
	
	var data;
	var rqesterMenuId = "";
	if( parent.$('li[aria-controls^=tabs-][aria-expanded=true]').attr('aria-controls') != undefined ) {
		rqesterMenuId = parent.$('li[aria-controls^=tabs-][aria-expanded=true]').attr('aria-controls').replace("tabs-", "");
	}
	
	if(args.data != undefined){
		
		if( typeof args.data =='object'){
			args.data.rqesterMenuId = rqesterMenuId;
			data = JSON.stringify(data);
		}else{
			data = data;
		}

	}
	
	if(args.srhFormNm != undefined){
		
		data == null ? $("#"+args.srhFormNm+"").serialize() : data + "&" + $("#"+args.srhFormNm+"").serialize();  
		
	}
	
	
	isErrorMsg = false;
	$.ajax({
		type:'POST',
		data : data,
		datatype:'json',
		url : args.callUrl,
		success : function(data){

			//세션 타임시간 초기화
			try{
				//parent.fncClearTime();
			} catch (e){
				try {
					//parent.parent.fncClearTime();
				} catch(e){
					//fncClearTime();
				}
			}

			if(data[userComMessageId] != undefined  ) {

				fn_showUserPage(data[userComMessageId]);

        		if( args.errCallbackNm != undefined) {
    				eval(args.callbackNm+'(data)');
    			}

        		return;

			} else if ( data[userErrMessageId] != undefined) {
        		isErrorMsg = true;
        		alert(data[userErrMessageId]);
        		if( args.callbackNm != undefined) {
    				eval(args.callbackNm+'(data)');
    			}

        		return;
        	}


			if( args.setFormNm != undefined) {
				requestUtil.setComponent(data,args);
			}

			//gfn_loadingBlock('off');
			// callback 호출
			if( args.callbackNm != undefined) {
				eval(args.callbackNm+'(data)');
			}
		},
		error : function(xhr, status, error){
			//alert('ajax error'+error );
			//gfn_loadingBlock('off');
			//alert("code:"+xhr.status+"\n"+"message:"+xhr.responseText+"\n"+"error:"+error);

			//세션 타임시간 초기화
			try{
				//parent.fncClearTime();
			} catch (e){
				try {
					//parent.parent.fncClearTime();
				} catch(e){r
					//fncClearTime();
				}
			}

			fn_showErrorPage();

		}
	});

}

/**
 * 서치폼 가져오기
 * @constructor
 * @author: jij
 * @date  : 2021-05-14
 * @this {requestUtil}
 * @param {args}
 * requestUtil.getSearchForm({targetFormId:"searchForm", callbackNm:"fn_queryMList"});
 * targetFormId : 가져올 폼 아이디
 * callbackNm   : 콜백함수
 * @reutrn 
 */
requestUtil.getSearchForm = function(args){
	var tabNum = parent.$('li[id*=li_tabs-M][aria-expanded=true]').attr('aria-controls');
	var comSearchFormLen = parent.$("#comSearchDiv > #" + args.targetFormId + tabNum).length;
	/*if(comSearchFormLen > 0){*/
		var obj = {};
		var arr = parent.$("#comSearchDiv > #" + args.targetFormId + tabNum).find("input, select").serializeArray();
		$.each(arr, function(){
			obj[this.name] = this.value;
		});
		//console.log(arr)
		
		var data = {data:obj};
		requestUtil.setSearchComponent(data, {setFormNm: args.targetFormId});
		parent.$("#comSearchDiv > #" + args.targetFormId + tabNum).remove();
		if( args.callbackNm != undefined) {
			eval(args.callbackNm+'('+ $("#page").val() +')');
		}
	/*}*/
}

/**
 * 서치폼 복사해두기
 * @constructor
 * @author: jij
 * @date  : 2021-05-14
 * @this {requestUtil}
 * @param {String}
 * requestUtil.setSearchForm("searchForm");
 * @reutrn 
 */
requestUtil.setSearchForm = function(targetFormId){
	/*$("#srcPgsStat > option[value="+$("#srcPgsStat").val()+"]").attr("selected", "selected");
	$("#srcUseYn > option[value="+$("#srcUseYn").val()+"]").attr("selected", "selected");*/
	
	var tabNum = parent.$('li[id*=li_tabs-M][aria-expanded=true]').attr('aria-controls');
	
	$('#'+targetFormId).find("select").each(function(){
		var str = $(this).val();
		$(this).find("option[value='"+$(this).val()+"']").attr("selected", "selected");
	});
	
	var comSearchDiv = parent.$("#comSearchDiv");
	$( '#'+targetFormId ).clone().prop("id", (targetFormId + tabNum)).appendTo( comSearchDiv );
}

/**
 * 복사해둔 서치폼 페이지 변경
 * @constructor
 * @author: jij
 * @date  : 2021-05-14
 * @this {requestUtil}
 * @param {args}
 * requestUtil.setSearchFormPage({targetFormId:"searchForm", page:"1"});
 * targetFormId: 복사해둔 서치폼 아이디
 * page: 바꿀 페이지
 * @reutrn 
 */
requestUtil.setSearchFormPage = function(args){
	var tabNum = parent.$('li[id*=li_tabs-M][aria-expanded=true]').attr('aria-controls');
	var comSearchform = parent.$("#comSearchDiv > #" + args.targetFormId + tabNum);
	if(comSearchform.length > 0){
		parent.$("#comSearchDiv > #" + args.targetFormId + tabNum).find("#page").val(args.page);
	}
}

/**
 * 서치폼 셋팅
 * @constructor
 * @author: jij
 * @date  : 2021-05-14
 * @this {requestUtil}
 * @param {args}
 * requestUtil.setSearchComponent(data, {setFormNm: args.targetFormId});
 * data: 폼 태그 아이디 : 벨류 맵
 * setFormNm: 폼아이디
 * @reutrn 
 */
requestUtil.setSearchComponent = function (data,args){

	//alert(1);
	$.each( data, function( key, value ) {

		//console.log('data___'+data);
		if( typeof value =='object'){ // 객체 ,배열인경우

			var setFormId;
			if( args.setFormNm == undefined) {
				setFormId = args.srhFormNm;
			} else {
				setFormId = args.setFormNm;
			}
			var selectorID = "#"+setFormId+" #"+key+"";
			var setVal     = value==null?'':value;

			$.each(value ,function( key2,value2){

				//console.log( key2 + ": " + value2 );
				//console.log( $.type($("#"+args.setFormNm+" #"+key2+"")));
				var radioSelectorID = "input[name="+key2+"]";
				selectorID      = "#"+setFormId+" #"+key2;
				setVal          = value2==null?'':value2;

				//console.log('selectorID__'+selectorID);
				//console.log('setVal__'+setVal);
				//console.log(value2==null);
				
				if( $(selectorID).is('input:checkbox')) {
					$(selectorID).prop("checked", true);
				} else if( $(selectorID).is('select')) {
					$(selectorID).val(setVal).prop("selected", true);
					//$(selectorID + ' option[value='+setVal+']').attr('selected','selected');
				} else if( $(selectorID).is('span')) {
					$(selectorID).text(setVal);
				} else if ($(radioSelectorID).is('input:radio')) {
					$(radioSelectorID).val([setVal]);
				} else {
					
					$(selectorID).val(setVal);
				}

			  })

		  } else { //원시타입입경우 (문자열, 숫자, 불리언 )

			  $(selectorID).val(setVal);

		  }
	});
}

/**
 * 엑셀 다운로드 xlsx
 * @constructor
 * @author: jij
 * @date  : 2021-05-14
 * @this {requestUtil}
 * @param {args}
 * requestUtil.comExcelXlsx({
		formNm :'searchForm',
		filename : "현장지원",
		sheetNm : "현장지원",
		columnArr : "요청번호,등록일자,부서명,사건번호,사건명,지원예정일,담당자,주임검사(승인일자),진행상태",
		columnVarArr : "suppReqSno,regDt,reqInsttNm,incdtNo,incdtNm,cfscSechPlndDt,reqUserNm,prosrNm,pgsStatNm",
		sqlQueryId : "siteRcvDAO.querySiteRcvMListExcel"
	});
 * @reutrn 
 */
requestUtil.comExcelXlsx = function(args)
{

	//로딩바 출력
	$(".modal").show();

	var callUrl = "/com/util/queryMListExcel.do";
	var form = document.createElement("form");
	form.setAttribute("charset", "UTF-8");
	form.setAttribute("method", "Post");  //Post 방식
	form.setAttribute("action", callUrl); //요청 보낼 주소
	form.setAttribute("id", "excelForm"); //요청 보낼 주소
	form.setAttribute("name", "excelForm"); //요청 보낼 주소
	form.style.display = "none";
	$('#'+args.formNm).find("select").each(function(){
		$(this).find("option[value='"+$(this).val()+"']").attr("selected", "selected");
	});
	$("#"+args.formNm).find("input,select").clone().appendTo(form);
	
	var hiddenField1 = document.createElement("input");
	hiddenField1.setAttribute("type", "hidden");
	hiddenField1.setAttribute("name", "filename");
	hiddenField1.setAttribute("value", args.filename);
	form.appendChild(hiddenField1);
	
	var hiddenField2 = document.createElement("input");
	hiddenField2.setAttribute("type", "hidden");
	hiddenField2.setAttribute("name", "sheetNm");
	hiddenField2.setAttribute("value", args.sheetNm);
	form.appendChild(hiddenField2);
	
	var hiddenField3 = document.createElement("input");
	hiddenField3.setAttribute("type", "hidden");
	hiddenField3.setAttribute("name", "columnArr");
	hiddenField3.setAttribute("value", args.columnArr);
	form.appendChild(hiddenField3);
	
	var hiddenField4 = document.createElement("input");
	hiddenField4.setAttribute("type", "hidden");
	hiddenField4.setAttribute("name", "columnVarArr");
	hiddenField4.setAttribute("value", args.columnVarArr);
	form.appendChild(hiddenField4);
	
	var hiddenField5 = document.createElement("input");
	hiddenField5.setAttribute("type", "hidden");
	hiddenField5.setAttribute("name", "sqlQueryId");
	hiddenField5.setAttribute("value", args.sqlQueryId);
	form.appendChild(hiddenField5);
	
	var splitSearch = args.splitSearch == undefined ? "" : args.splitSearch;
	console.log(splitSearch)
	var hiddenField6 = document.createElement("input");
	hiddenField6.setAttribute("type", "hidden");
	hiddenField6.setAttribute("name", "splitSearch");
	hiddenField6.setAttribute("value", splitSearch);
	form.appendChild(hiddenField6);
	
	document.body.appendChild(form);

	var data = $(form).serialize();
	var rqesterMenuId = "";
	if( parent.$('li[aria-controls^=tabs-][aria-expanded=true]').attr('aria-controls') != undefined ) {
		rqesterMenuId = parent.$('li[aria-controls^=tabs-][aria-expanded=true]').attr('aria-controls').replace("tabs-", "");
		data += '&rqesterMenuId=' + rqesterMenuId;
	}
	
	$.fileDownload($(form).prop('action'),{
		httpMethod: "POST",
		data:data,
		successCallback: function (url) {
			$(".modal").hide();
			$(form).remove();
		},
		failCallback: function(responesHtml, url) {
			$(".modal").hide();
			$(form).remove();
			fn_showErrorPage();
		}
	});

}

/**
 * 엑셀 업로드
 * @constructor
 * @author: jij
 * @date  : 2021-05-14
 * @this {requestUtil}
 * @param {args}
 * requestUtil.updExcMList({
		fileNm:"excelFile"												// file 태그 id
		, sheetNum:0													// 시트번호
		, strartRowNum:1												// 읽어드릴 행(0부터 시작) 
		, startCelNum:0													// 읽어드릴 셀(0부터 시작)
		, celCnt:6														// 총 셀 갯수
		, culmnNmArr:"crimeCd,crimeNm,crimeGrp,crimeGrpNm,useYn,relLaw"	// 셀 컬럼
		, sqlQueryId:"sysCrimeCodeDAO.regSysCrimeCodeRDtl"				// insert 쿼리 id
		, callbackNm: "updExcMListCallback"								// 콜백함수
	});
 * @reutrn 
 */
requestUtil.updExcMList = function (args){
	$(".modal").show();
	var callUrl = "/com/util/updExcMList.do";
	var form = new FormData();
	form.append(args.fileNm, $("#"+args.fileNm)[0].files[0]);
	form.append("sheetNum", args.sheetNum);
	form.append("strartRowNum", args.strartRowNum);
	form.append("startCelNum", args.startCelNum);
	form.append("celCnt", args.celCnt);
	form.append("culmnNmArr", args.culmnNmArr);
	form.append("sqlQueryId", args.sqlQueryId);
	var rqesterMenuId = "";
	if( parent.$('li[aria-controls^=tabs-][aria-expanded=true]').attr('aria-controls') != undefined ) {
		rqesterMenuId = parent.$('li[aria-controls^=tabs-][aria-expanded=true]').attr('aria-controls').replace("tabs-", "");
	}
	form.append("rqesterMenuId", rqesterMenuId);
	jQuery.ajax({
		url : callUrl,
		type : "POST",
		processData : false,
		contentType : false,
		data : form,
		enctype : "multipart/form-data",
		complete : function(){
			//$("#"+args.fileNm).val("");
			//로딩바 출력
			$(".modal").hide();
		},
		success : function(data) {
			if(data[userComMessageId] != undefined  ) {

				fn_showUserPage(data[userComMessageId], args.callbackNm);

        		if( args.errCallbackNm != undefined) {
    				eval(args.errCallbackNm+'(data)');
    			}

        		return;

			} else if ( data[userErrMessageId] != undefined) {
        		isErrorMsg = true;
        		fn_showUserPage(data[userErrMessageId]);
        		if( args.callbackNm != undefined) {
    				//eval(args.callbackNm+'(data)');
    			}

        		return;
        		
        	} 
			
			// 성공
			if ( data[sysSucMessageId] != undefined ){
				fn_showUserPage(data[sysSucMessageId],args.callbackNm,data);
			} else {
				fn_showUserPage("요청처리가 성공적으로 수행되었습니다.",args.callbackNm,data);
				//alert("요청처리가 성공적으로 수행되었습니다."); // success.request.msg
			}
		},
		error : function(xhr, status, error){
			//alert('ajax error'+error );
			//gfn_loadingBlock('off');
			//alert("code:"+xhr.status+"\n"+"message:"+xhr.responseText+"\n"+"error:"+error);

			//세션 타임시간 초기화
			try{
				//parent.fncClearTime();
			} catch (e){
				try {
					//parent.parent.fncClearTime();
				} catch(e){
					//fncClearTime();
				}
			}

			fn_showErrorPage();

		}
	});
}
