(function(){
	
	var jims =jims||{};
	requestUtil = jims.requestUtil||{};

	const userComMessageId = '_server_user_com_message_';
	const userErrMessageId = '_server_user_err_message_';
	
	
	var isErrorMsg =false;
	
	$.extend(true, requestUtil, {
	
		/**
		 * 상세 화면등에서 조회용 Transaction 관리
		 * @author KJM
		 * @since 2018.09.20
		 * @param callUrl:호출URL
		 * @param srhFormNm:조회 값이 있는 폼ID 
		 * @param setFormNm: 조회한 값을 세팅한 폼 ID 
		 * @param callbackNm: 콜백함수명 'fn_callback'
		 */
		search : function (args){
			
			var data = $("#"+args.srhFormNm+"").serialize();
			isErrorMsg = false;
			$.ajax({
				type:'POST',
				data : data,
				datatype:'json',
				url : args.callUrl,
				success : function(data){
					
					
					if(data[userComMessageId] != undefined  ) {
		        		
						alert(data[userMessageId]);
		        		
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
					fn_showErrorPage();
					
				}
			});
			
         },
		
		isErrorMsg: function(){ return isErrorMsg;},
		
		setComponent : function (data,args){

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
        },

		/**
		 * 저장 
		 */
		save : function (args){
			
			//gfn_loadingBlock('on');
			//console.log($("#"+args.srhFormNm+"").serialize());
			$.ajax({
				type:'POST',
				data : $("#"+args.srhFormNm+"").serialize(),
				datatype:'json',
				url : args.callUrl,
				success : function(data){
					//gfn_loadingBlock('off');
					eval(args.callbackNm+'(data)');
					
				},
				error : function(xhr, status, error){
					//alert('ajax error'+error );
					//gfn_loadingBlock('off');
					//alert("code:"+xhr.status+"\n"+"message:"+xhr.responseText+"\n"+"error:"+error);
					fn_showErrorPage();
					
				}
			});
			
        },




/*		objectifyForm : function(formArray) {
			  var returnArray = {};
			  for (var i = 0; i < formArray.length; i++){
			    returnArray[formArray[i]['name']] = formArray[i]['value'];
			  }
			  return returnArray;
		},*/

		
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
		searchGrid : function (args){

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
			
			jQuery("#"+args.gridID).setGridParam({
		        postData: objectifyForm(formArray),
		        datatype: "json",
		        mtype: 'POST',
		        page:page,
		        reccount : reccount,
		        loadComplete : function (data) {
		            //alert();
		        	//console.log(data);
		        	
		        	if(data[userComMessageId] != undefined) {
		        		alert(data[userComMessageId]);
		        	}
		        	
		        	if( args.callbackNm != undefined) {
						eval(args.callbackNm+'(data)');
					}
		        	
		    	}
		    }).trigger("reloadGrid");
			
			 function objectifyForm(formArray) {
				  var returnArray = {};
				  for (var i = 0; i < formArray.length; i++){
				    returnArray[formArray[i]['name']] = formArray[i]['value'];
				  }
				  return returnArray;
			}
			 
			
        },
        
        mdPop:function (args){


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
               	});
               },         
               height: args.height,
               width: args.width,
               title: args.title
           });
        	
           return $('#'+args.divId);
            
        }
		
	});
	
})();


