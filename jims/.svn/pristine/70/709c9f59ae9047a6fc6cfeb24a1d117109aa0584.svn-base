/****************************************************************
 *
 * 파일명 : common.js
 * 설  명 : 공통 JavaScript
 *
 *    수정일      수정자      Version        Function 명
 * ------------  ---------  -------------  ----------------------------
 *  2014.08.29    우성택      1.0            최초생성
 *
 *
 * **************************************************************/



/**********************************************
 * 함수명 : gfn_tableSize
 * 설  명 : 리스트 사이즈변경
 * 인  자 : 객체ID, 변경할사이즈
 * 사용법 : gfn_tableSize('gtableExtendA','3000px')
 *
 * 수정일        수정자      수정내용
 * ------        ------     -------------------
 * 2014.09.01    우성택      신규작업
 * 2014.09.01    김규형      div 사이즈 변경 시 시작위치를 현재 창 가장 왼쪽부터 시작하도록 변경(일단 주석처리함)
 *
 * *********************************************/
function gfn_tableSize(vid,vsize){
    var fullWidth = document.body.scrollWidth; // 현재 오픈되어있는 창 사이즈
    var divWidth  = $('#'+vid).css('width').substring(0, $('#'+vid).css('width').length-2); //list DIV 사이즈
    var marginWidth = "";

    if($('#'+vid).css('width') == vsize){
        //marginWidth = "0 0 0 0";
        //$('#'+vid).css('margin', marginWidth); //div 위치 최초 위치로 초기화

        $('#'+vid).css('width','1000px');
        $('#gtableExtend').attr('src','/images/com/extend_on.gif');
    }else{
        //marginWidth = "0 0 0 " + parseInt((fullWidth - divWidth)/2)*-1 + "px";
        //$('#'+vid).css('margin', marginWidth); //div 위치를 왼쪽 처음부터 시작하도록 변경

        $('#'+vid).css('width',''+vsize+'');
        $('#gtableExtend').attr('src','/images/com/extend_off.gif');
  }
}

/**********************************************
 * 함수명 : gfn_checkByte
 * 설  명 : 문자 byte
 * 인  자 : 문자열
 * 사용법 : gfn_checkByte('123안녕하세요.abc')
 *
 * 수정일        수정자      수정내용
 * ------        ------     -------------------
 * 2014.09.01    우성택      신규작업
 *
 * *********************************************/
function gfn_checkByte(str)
{
    var checkByte = 0;

    for(var i = 0; i < str.length; i++)
    {
        var oneChar = str.charAt(i);

        if(escape(oneChar).length > 4)
        {
            checkByte += 3;
        }
        else
        {
        	if(escape(oneChar) == "%0A"){ //ibatis에서 엔터코드변환으로 2byte 적용
        		checkByte += 2;
        	}else {
        		checkByte++;
        	}
        }

    }
    return checkByte;
}


gfn_getDate = function()
{
 var today = new Date();
 var year = today.getFullYear();
 var month = today.getMonth();
 var day = today.getDate();
 var resultDate = new Date(year, month, day);


       year = resultDate.getFullYear();
       month = resultDate.getMonth() + 1;
       day = resultDate.getDate();

       if (month < 10)
           month = "0" + month;
       if (day < 10)
           day = "0" + day;

       return year + "-" + month + "-" + day;
}

/**********************************************
 * 함수명 : gfn_calendarConfig
 * 설  명 : 달력
 * 인  자 : 시작일, 종료일, from_to구분(minDate, maxDate), 콜백함수
 * 사용법 : gfn_calendarConfig("searchStrtDt", "searchEndDt", "minDate", "callBack1");
 *
 * 수정일        수정자      수정내용
 * ------        ------     -------------------
 * 2014.09.02    김규형      신규작업
 *
 * *********************************************/
gfn_calendarConfig = function (objName1, objName2, type, callBack) {
	//alert("<<<<<gfn_calendarConfig>>>>>>>");
	$("#"+objName1).datepicker({
		showOn: "button"   // 버튼 이미지 삽입 가능
      //, buttonImage: "/css/jims/images/icons/icon_calendar.png"  // 버튼 이미지 경로
	, buttonImage: "/images/main/bg_calendar.png"  // 버튼 이미지 경로
      , buttonImageOnly: true  // true:버튼 이미지만 표시  false:버튼 안에 이미지 표시(default)
      , changeMonth: true   // 월 선택을 combobox로 표현
      , changeYear: true   // 년 선택을 combobox로 표현
      , showOtherMonths: true  // 다른 월도 보여지도록
      , selectOtherMonths: true // 다른 월의 셀도 선택가능하도록
      , dateFormat: "yy-mm-dd" // 날짜 포맷 설정
      , showAnim: "slideDown"   // 달력 open, close시의 애니메이션 효과 설정
      , dayNamesMin: ["일","월","화","수","목","금","토"]      //요일 해더 표시 설정
      , monthNamesShort: ["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"] //월 combobox 표시 설정
      , onClose: function( selectedDate ) { //시작일을 선택하면 종료일은 시작일부터 선택할 수 있음(from~to)
          $( "#"+objName2 ).datepicker( "option", type, selectedDate );
          $("img.ui-datepicker-trigger").css({'cursor':'pointer','float':'left'});
          if(callBack != ""){
              eval(callBack+"()"); // 콜백함수 호출
          }
        }
    });
	//alert("<<<<<gfn_calendarConfig22222>>>>>>>");
	$("img.ui-datepicker-trigger").css({'cursor':'pointer','float':'left'});

	//$("button.ui-datepicker-trigger").addClass("btn_cal");
	//alert("<<<<<gfn_calendarConfig33333>>>>>>>");
	$("#"+objName1).mask("9999-99-99");  //달력 키보드 입력 포맷 설정
};

/**********************************************
 * 함수명 : gfn_calendarConfig
 * 설  명 : 달력
 * 인  자 : 시작일, 종료일, from_to구분(minDate, maxDate), 콜백함수
 * 사용법 : gfn_calendarConfig("searchStrtDt", "searchEndDt", "minDate", "callBack1");
 *
 * 수정일        수정자      수정내용
 * ------        ------     -------------------
 * 2014.09.02    김규형      신규작업
 *
 * *********************************************/
gfn_calendarConfigM = function (objName1, objName2, type, callBack) {
	//alert("<<<<<gfn_calendarConfig>>>>>>>");
	$("#"+objName1).datepicker({
        closeText : "선택"
      , prevText : "이전달"
      , nextText : "다음달"
      , currentText : "오늘"
	  ,	showOn: "button"   // 버튼 이미지 삽입 가능
      , buttonImage: "/css/jims/images/icons/icon_calendar.png"  // 버튼 이미지 경로
      , buttonImageOnly: true  // true:버튼 이미지만 표시  false:버튼 안에 이미지 표시(default)
      , changeMonth: true   // 월 선택을 combobox로 표현
      , changeYear: true   // 년 선택을 combobox로 표현
      , showOtherMonths: true  // 다른 월도 보여지도록
      , selectOtherMonths: true // 다른 월의 셀도 선택가능하도록
      , showButtonPanel: true
      , dateFormat: "yy-mm" // 날짜 포맷 설정
      , showAnim: "slideDown"   // 달력 open, close시의 애니메이션 효과 설정
      , dayNamesMin: ["일","월","화","수","목","금","토"]      //요일 해더 표시 설정
      , monthNamesShort: ["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"] //월 combobox 표시 설정
      , onClose: function (dateText, inst) { //시작일을 선택하면 종료일은 시작일부터 선택할 수 있음(from~to)

          var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
          var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
          $(this).datepicker( "option", "defaultDate", new Date(year, month, 1) );
          $(this).datepicker('setDate', new Date(year, month, 1));
          var year2 = $("#"+objName2).val().substr(0, 4);
          var month2 = $("#"+objName2).val().substr(5, 6) - 1;
          $("#"+objName2).datepicker( "option", type, new Date(year, month, 1) );
          $("#"+objName2).datepicker('setDate', new Date(year2, month2, 1));
          if(callBack != ""){
              eval(callBack+"()"); // 콜백함수 호출
          }
        }
    });
	//alert("<<<<<gfn_calendarConfig22222>>>>>>>");
	$("img.ui-datepicker-trigger").attr("style","cursor:pointer;");

	//$("button.ui-datepicker-trigger").addClass("btn_cal");
	//alert("<<<<<gfn_calendarConfig33333>>>>>>>");
	$("#"+objName1).mask("9999-99");  //달력 키보드 입력 포맷 설정
};


/**********************************************
 * 함수명 : gfn_overMaxLength
 * 설  명 : 입력글자 byte 체크(영문,숫자:1byte , 한글:3byte)
 * 인  자 : objName, MAX byte, 컬럼한글명
 * 사용법 : gfn_overMaxLength("objName",10);
 *
 * 수정일        수정자      수정내용
 * ------        ------     -------------------
 * 2014.09.02    우성택      신규작업
 *
 * *********************************************/
gfn_overMaxLength = function (objName, maxLength) {
	$("#"+objName).keyup(function(){
		var length = gfn_checkByte($("#"+objName).val());
        if(length > maxLength){
            $("#"+objName).addClass("col_red");
        }else {
            $("#"+objName).removeClass("col_red");
        }

        if($("#"+objName).prop("tagName").toLowerCase() == "textarea"){
        	$("#"+objName+"ByteChk").html("("+length+"/"+maxLength+")");
        }
	});

	$("#"+objName).blur(function(){
		var checkByte = 0;
        var subStrLength = 0;

        var hangul = maxLength / 2;
        var english = maxLength;

        var str = $("#"+objName).val();
        var tmpStr = "";

        for(var i = 0; i < str.length; i++){
        	var oneChar = str.charAt(i);

            if(escape(oneChar).length > 4){
                checkByte += 3;
            }else {
            	if(escape(oneChar) == "%0A"){ //ibatis에서 엔터코드변환으로 2byte 적용
            		checkByte += 2;
            	}else {
            		checkByte++;
            	}
            }

            if(checkByte <= maxLength){
                subStrLength = i + 1;
            }else {
                break;
            }

            tmpStr += oneChar;
        }

        if(checkByte > maxLength){
            /*alert(maxLength+"byte를 넘을 수 없습니다.");
            $("#"+objName).removeClass("col_red");
            $("#"+objName).val(tmpStr);

            if($("#"+objName).prop("tagName").toLowerCase() == "textarea"){
                var length = gfn_checkByte($("#"+objName).val());
                $("#"+objName+"ByteChk").html("("+length+"/"+maxLength+")");
            }*/
        	fn_showUserPage(maxLength+"byte를 넘을 수 없습니다.", function() {
        		$("#"+objName).removeClass("col_red");
                $("#"+objName).val(tmpStr);

                if($("#"+objName).prop("tagName").toLowerCase() == "textarea"){
                    var length = gfn_checkByte($("#"+objName).val());
                    $("#"+objName+"ByteChk").html("("+length+"/"+maxLength+")");
                }
			});
        }

     });

     if($("#"+objName).prop("tagName").toLowerCase() == "textarea"){
    	 var length = gfn_checkByte($("#"+objName).val());
         $("#"+objName+"ByteChk").html("("+length+"/"+maxLength+")");
     }
};

/**********************************************
 * 함수명 : gfn_overMaxLengthText
 * 설  명 : 입력글자 byte 체크(영문,숫자:1byte , 한글:3byte)
 * 인  자 : objName, MAX byte, 컬럼한글명
 * 사용법 : gfn_overMaxLengthText("objName",10);
 *
 * 수정일        수정자      수정내용
 * ------        ------     -------------------
 * 2015.03.09    장효원      text전용
 *
 * *********************************************/
gfn_overMaxLengthText = function (objName, maxLength) {
	//$("#"+objName).keyup(function(){
		var length = gfn_checkByte($("#"+objName).val());
        if(length > maxLength){
            $("#"+objName).addClass("col_red");
        }else {
            $("#"+objName).removeClass("col_red");
        }

	//});

	$("#"+objName).blur(function(){
		var checkByte = 0;
        var subStrLength = 0;

        var hangul = maxLength / 2;
        var english = maxLength;

        var str = $("#"+objName).val();
        var tmpStr = "";

        for(var i = 0; i < str.length; i++){
        	var oneChar = str.charAt(i);

            if(escape(oneChar).length > 4){
                checkByte += 3;
            }else {
            	if(escape(oneChar) == "%0A"){ //ibatis에서 엔터코드변환으로 2byte 적용
            		checkByte += 2;
            	}else {
            		checkByte++;
            	}
            }

            if(checkByte <= maxLength){
                subStrLength = i + 1;
            }else {
                break;
            }

            tmpStr += oneChar;
        }

        if(checkByte > maxLength){
            alert(maxLength+"byte를 넘을 수 없습니다.");
            $("#"+objName).removeClass("col_red");
            $("#"+objName).val(tmpStr);

        }

     });

};

/**********************************************
 * 함수명 : gfn_enterChk
 * 설  명 : 엔터 체크
 * 인  자 :
 * 사용법 : gfn_enterChk()
 *
 * 수정일        수정자      수정내용
 * ------        ------     -------------------
 * 2014.09.02    우성택      신규작업
 *
 * *********************************************/
gfn_enterChk = function () {
	if(event.keyCode == 13) {
		event.keyCode = 0;
		return true;
	}
	return false;
};

/**********************************************
 * 함수명 : gfn_fileUpload
 * 설  명 : iframe을 이용한 파일업로드
 * 인  자 : button obj id, 업로드파일표시 div id, ,업로드할 파일종류(image, file), 가능한 최대 파일수
 * 사용법 : gfn_fileUpload("file3Btn", "fileList3","image", 2);
 *
 * 수정일        수정자      수정내용
 * ------        ------     -------------------
 * 2014.09.02    우성택      신규작업
 *
 * *********************************************/
gfn_fileUpload = function (objName, divName, type, maxFileNum) {

	var frmUp;

    $("#"+objName).removeClass();
    $("#"+objName).addClass("btn_sty3");
    $("#"+objName).html("찾아보기...");

    //iframe 체크
    if(typeof(frmUp) == 'undefined') {
    	frmUp = document.createElement("iframe");
    	frmUp.src = '/fileUpload.do';
    	frmUp.style.display = 'none';
    	document.body.appendChild(frmUp);
    }

    //파일 찾기
	$('#'+objName).on('click', function () {

		try{
			frmUp.contentWindow.document.getElementById('objName').value = objName;
			frmUp.contentWindow.document.getElementById('divName').value = divName;
			frmUp.contentWindow.document.getElementById('type').value = type;
			frmUp.contentWindow.document.getElementById('maxFileNum').value = maxFileNum;

			var fileUp = frmUp.contentWindow.document.getElementById('newFile');
			fileUp.click();
	    }catch(e){
	    	alert("페이지가 정상적으로 호출되지 안아 재호출 되었습니다.\n다시 한번 클릭해주세요.");
	    	frmUp.contentWindow.location.reload(true);
	    }
	});
}

/**********************************************
 * 함수명 : gfn_excelUpload
 * 설  명 : 엑셀업로드
 * 인  자 : button obj id, actUrl
 * 사용법 : gfn_fileUpload("objName", "/excelUpload.do");
 *
 * 수정일        수정자      수정내용
 * ------        ------     -------------------
 * 2014.09.02    우성택      신규작업
 *
 * *********************************************/
gfn_excelUpload = function (objName, actUrl) {

    // iframe의 name이자, form의 target
    var target_name = 'iframe_excelUpload';

    //iframe 체크
    try{
    	var chk = document.getElementById(target_name).contentWindow.document;
    }catch(e){
        // iframe 생성
        var iframe = $('<iframe src="/excelUpload.do;" id="'+target_name+'" name="'+target_name+'" style="display:none;"></iframe>');
        $('body').append(iframe);
    }

    //파일 찾기
	$('#'+objName).on('click', function () {
	    try{
	    	$('#'+target_name)[0].contentWindow.fn_fileSearch(objName, actUrl);
	    }catch(e){
	    	alert("페이지가 정상적으로 호출되지 안아 재호출 되었습니다.\n다시 한번 클릭해주세요.");
	    	$('#'+target_name)[0].contentWindow.location.href="/excelUpload.do";
	    }
	});
}

/**********************************************
 * 함수명 : gfn_fileUploadCallBack
 * 설  명 : 파일 업로드 후 콜백
 * 인  자 : Object
 * 사용법 : 파일 업로드 후 자동 실행됨.
 *
 * 수정일        수정자      수정내용
 * ------        ------     -------------------
 * 2014.09.02    우성택      신규작업
 *
 * *********************************************/
function gfn_fileUploadCallBack (returnValue) {
    // Row div
    var new_row = document.createElement( 'div' );
    new_row.className = returnValue.divName+"Class";
    new_row.style.padding = '5px';

    // Delete button
    var new_row_button = document.createElement( 'input' );
    new_row_button.type = 'button';
    new_row_button.value = '삭제';
    new_row_button.className = 'buttonG35';
//    new_row_button.style.cursor = "pointer";
//    new_row_button.style.width="53px";
//    new_row_button.style.height="23px";
//    new_row_button.style.background = "url(/images/btn_board_02.gif) no-repeat";
//    new_row_button.style.border="0";
    //new_row_button.className="btn_ty4";

    // Delete function
    new_row_button.onclick= function(){

    	//파일삭제 추가
    	var filePth = $(this.parentNode).find('input[id*=FilePth]').val();
    	var fileNm = $(this.parentNode).find('input[id*=FileNm]').val();

    	//fn_fileDel(filePth,fileNm);

        this.parentNode.parentNode.removeChild(this.parentNode);

        $("#"+returnValue.objName).show();

        //    which nixes your already queued uploads
        return false;
    };

    // Set row value
    new_row.innerHTML = returnValue.oriFile;

    // Add button
    new_row.appendChild( new_row_button );

    // hidden input
    var new_row_input = document.createElement( 'input' );
    new_row_input.id = returnValue.divName+'FileSno';
    new_row_input.name = returnValue.divName+'FileSno';
    new_row_input.type = 'hidden';
    new_row_input.value = '0';

    new_row.appendChild( new_row_input );

    new_row_input = document.createElement( 'input' );
    new_row_input.id = returnValue.divName+'FilePth';
    new_row_input.name = returnValue.divName+'FilePth';
    new_row_input.type = 'hidden';
    new_row_input.value = returnValue.filePth;

    new_row.appendChild( new_row_input );

    new_row_input = document.createElement( 'input' );
    new_row_input.id = returnValue.divName+'FileNm';
    new_row_input.name = returnValue.divName+'FileNm';
    new_row_input.type = 'hidden';
    new_row_input.value = returnValue.fileNm;

    new_row.appendChild( new_row_input );

    new_row_input = document.createElement( 'input' );
    new_row_input.id = returnValue.divName+'FileSize';
    new_row_input.name = returnValue.divName+'FileSize';
    new_row_input.type = 'hidden';
    new_row_input.value = returnValue.fileSize;

    new_row.appendChild( new_row_input );

    new_row_input = document.createElement( 'input' );
    new_row_input.id = returnValue.divName+'OriFileNm';
    new_row_input.name = returnValue.divName+'OriFileNm';
    new_row_input.type = 'hidden';
    new_row_input.value = returnValue.oriFile;

    new_row.appendChild( new_row_input );

    // Add it to the list
    document.getElementById(returnValue.divName).appendChild(new_row);

    //buttion hide()
    if( returnValue.maxFileNum != -1 && $("."+returnValue.divName+"Class").length >= returnValue.maxFileNum ){
    	$("#"+returnValue.objName).hide();
    };

    try{
    	fn_fileUploadCallBack(returnValue);
    }catch(e){

    }

}

/**********************************************
 * 함수명 : gfn_fileUpload2
 * 설  명 : 파일업로드
 * 인  자 : input obj name, 업로드파일표시 div name, 업로근 가능한 최대 파일수
 * 사용법 : gfn_fileUpload("file","fileList",2);
 *
 * 수정일        수정자      수정내용
 * ------        ------     -------------------
 * 2014.09.02    우성택      신규작업
 *
 * *********************************************/
gfn_fileUpload2 = function (objName, divName, maxFileNum) {
    var multi_selector = new MultiSelector( document.getElementById( divName ), maxFileNum );
    multi_selector.addElement( document.getElementById( objName ) );
};

/**********************************************
 * 함수명 : gfn_fileIdDownload
 * 설  명 : 파일 다운로드
 * 인  자 : 첨부파일id, 첨부파일일련번호
 * 사용법 : gfn_fileIdDownload("18", "1");
 *
 * 수정일        수정자      수정내용
 * ------        ------     -------------------
 * 2014.09.02    우성택      신규작업
 *
 * *********************************************/
gfn_fileIdDownload = function (atchFileId, fileSno) {
	window.open("/fileDown.do?type=1&atchFileId="+atchFileId+"&fileSno="+fileSno);
};

/**********************************************
 * 함수명 : gfn_fileNmDownload
 * 설  명 : 파일 다운로드
 * 인  자 : 파일경로, 파일명, 원본파일명
 * 사용법 : gfn_fileNmDownload("20141006", "20141006032952700.jpg","test.jpg");
 *
 * 수정일        수정자      수정내용
 * ------        ------     -------------------
 * 2014.09.02    우성택      신규작업
 *
 * *********************************************/
gfn_fileNmDownload = function (filePth, fileNm, oriFileNm) {
	window.open("/fileDown.do?type=2&filePth="+filePth+"&fileNm="+fileNm+"&oriFileNm="+oriFileNm);
};

/**********************************************
 * 함수명 : gfn_schFileDownload
 * 설  명 : 학교급식 첨부파일 다운로드
 * 인  자 : 파일경로, 원본파일명
 * 사용법 : gfn_schFileDownload("/UserFiles/1410/07/20141007015521693.jpg", "test.jpg");
 *
 * 수정일        수정자      수정내용
 * ------        ------     -------------------
 * 2014.09.02    우성택      신규작업
 *
 * *********************************************/
gfn_schFileDownload = function (filePth, oriFileNm) {
	window.open("/jims/fileDown.do?type=3&filePth="+filePth+"&oriFileNm="+oriFileNm);
};

/**********************************************
 * 함수명 : gfn_fileIdView
 * 설  명 : 파일 미리보기
 * 인  자 : 첨부파일id, 첨부파일일련번호
 * 사용법 : gfn_fileIdView("18", "1");
 *
 * 수정일        수정자      수정내용
 * ------        ------     -------------------
 * 2015.02.22    장효원      신규작업
 *
 * *********************************************/
gfn_fileIdView = function (atchFileId, fileSno) {
	window.open("/jims/fileView.do?type=1&atchFileId="+atchFileId+"&fileSno="+fileSno);
};

/**********************************************
 * 함수명 : gfn_popupPosition
 * 설  명 : 팝업 위치 설정
 * 인  자 : 팝업 사이즈
 * 사용법 : gfn_popupPosition(768, 600);
 *
 * 수정일        수정자      수정내용
 * ------        ------     -------------------
 * 2014.09.02    우성택      신규작업
 *
 * *********************************************/
function gfn_popupPosition(width, height) {
	var rtn = "";
	var pos = {};
	pos.x   = (window.screen.availWidth  - width)  / 2;
	pos.y   = (window.screen.availHeight - height) / 2;
	rtn = ", width=" + width
	    + ", height=" + height
	    + ", left= " + pos.x
	    + ", top=" + pos.y;
	return rtn;
}

function gfn_popupPosition2(width, height) {
	var rtn = "";
	var pos = {};
	pos.x   = 0;
	pos.y   = window.screen.availHeight;
	rtn = ", width=" + width
	    + ", height=" + height
	    + ", left= " + pos.x
	    + ", top=" + pos.y;
	return rtn;
}

/**********************************************
 * 함수명 : gfn_openPopup
 * 설  명 : 팝업 호출
 * 인  자 : 팝업종류
 * 사용법 : gfn_comPopup("subject");
 *
 * 수정일        수정자      수정내용
 * ------        ------     -------------------
 * 2014.09.02    우성택      신규작업
 *
 * *********************************************/
gfn_openPopup = function (subject, param) {
	var url = "";
	var name = "";
	var opt = "";
	var pos = "";
	var wndWidth  = 768;
	var wndHeight = 600;
	switch (subject) {
	case 'image':	//이미지보기 팝업
		//url = "/imageViewPop.do?imgPath="+encodeURIComponent(param);
		url = param;
		name = "imageViewPopup";
		opt = "toolbar=no,menubar=no,scrollbars=yes,resizable=yes";
		wndWidth = 1024;
		wndHeight = 786;
		pos = gfn_popupPosition(wndWidth, wndHeight);
		var wndManual = window.open(url, name, opt+pos);
		wndManual.focus();
		break;

	case 'zipCode':	//주소 조회 팝업
		url = "/zipCode/zipCodeSearch.jsp";
		name = "zipCodeSearch";
		opt = "toolbar=no,menubar=no,scrollbars=no,resizable=no";
		wndWidth = 545;
		wndHeight = 705;
		pos = gfn_popupPosition(wndWidth, wndHeight);
		var wndManual = window.open(url, name, opt+pos);
		wndManual.focus();
		break;

	case 'ozView':	//오즈 리포트
		if (typeof(param.width)  != "undefined"){
        	wndWidth = param.width;
        }

        if (typeof(param.height)  != "undefined"){
        	wndHeight = param.height;
        }

		url = "/ozViewPop.do?ozFileNm="+param.fileName+"&ozParam="+param.param+"&odiName="+param.odiName;
		name = "ozViewPopup";
		opt = "toolbar=no,menubar=no,scrollbars=no,resizable=yes";
		pos = gfn_popupPosition(wndWidth, wndHeight);

		var wndManual = window.open(url, name, opt+pos);
		wndManual.focus();
		break;

	case 'openPop':	//팝업 호출
		url = param.url;
		name = param.name;
		opt = param.opt;
		wndWidth = param.width;
		wndHeight = param.height;
		pos = gfn_popupPosition(wndWidth, wndHeight);
		var wndManual = window.open(url, name, opt+pos);
		wndManual.focus();
		break;
	case 'openPop2':	//팝업 호출
		url = param.url;
		name = param.name;
		opt = param.opt;
		wndWidth = param.width;
		wndHeight = param.height;
		pos = gfn_popupPosition2(wndWidth, wndHeight);
		var wndManual = window.open(url, name, opt+pos);
		wndManual.focus();
		break;
	case 'notiPop':	//공지팝업 호출
		url = param.url;
		name = param.name;
		opt = param.opt;
		var wndManual = window.open(url, name, opt);
		wndManual.focus();
		break;
	default:
		break;
	}

};

/**********************************************
 * 함수명 : gfn_setSGOption
 * 설  명 : AJAX로 시군 조회
 * 인  자 : 시도코드, 시군구코드 select box ID
 * 사용법 : gfn_setSGOption(1, '', gunguCd);
 *
 * 수정일        수정자      수정내용
 * ------        ------     -------------------
 * 2014.09.02    우성택      신규작업
 *
 * *********************************************/
function gfn_setSiGunGuOpt(sidoCd, gunguCd, selectId) {
	$("#"+selectId).html("<option value=''>전체</option>");
	$.ajax({
		url:  '/ajaxGungu.do',
		data: "sidoCd="+sidoCd,
		dataType: 'json',
		success: function(data) {
			for(var i = 0; i < data.gunguList.length;i++) {
				$("#"+selectId).append("<option value='"+data.gunguList[i].gunguCd+"'>"+data.gunguList[i].gunguNm+"</option>");
			}

			if(gunguCd != ''){
				$("#"+selectId).val(gunguCd);
			}
		}
	});
}

/**********************************************
 * 함수명 : gfn_setSGOption
 * 설  명 : AJAX로 시군 조회
 * 인  자 : 시도코드, 시군구코드 select box ID, 해당 selectBox Index
 * 사용법 : gfn_setSGOption(1, '', gunguCd, 1);
 *
 * 수정일        수정자      수정내용
 * ------        ------     -------------------
 * 2014.09.02    우성택      신규작업
 *
 * *********************************************/
function gfn_setSiGunGuOpt(sidoCd, gunguCd, callBack, index) {

	$.ajax({
		url:  '/ajaxGungu.do',
		data: "sidoCd="+sidoCd,
		dataType: 'json',
		success: function(data) {
        	eval(callBack+"(data,"+index+")"); // 콜백함수 호출
		}
	});
}

/**********************************************
 * 함수명 : gfn_setitemOption
 * 설  명 : AJAX로 부류/품목 조회
 * 인  자 : 부류코드, 품목코드 select box ID, 해당 selectBox Index
 * 사용법 : gfn_setitemOption(1, '', gunguCd, 1);
 *
 * 수정일        수정자      수정내용
 * ------        ------     -------------------
 * 2014.09.02    우성택      신규작업
 * 2014.12.29	 김경민		부류에따른 품목 추가
 *
 * *********************************************/
function gfn_setitemOption(ldivCd, itemCd, callBack, index) {

	$.ajax({
		url:  '/ajaxItem.do',
		data: "ldivCd="+ldivCd,
		dataType: 'json',
		success: function(data) {
        	eval(callBack+"(data,"+index+")"); // 콜백함수 호출
		}
	});
}

/**********************************************
 * 함수명 : gfn_setCodeOpt
 * 설  명 : AJAX로 코드 조회
 * 인  자 : codeId, code, selectId
 * 사용법 : gfn_setCode("COM001,COM002", "REGC02,HIST02", "regCd,histCd");
 *
 * 수정일        수정자      수정내용
 * ------        ------     -------------------
 * 2014.09.02    우성택      신규작업
 *
 * *********************************************/
function gfn_setCodeOpt(codeId, code, selectId) {
	var tempCodId= codeId.split(",");
	var tempSelectId= selectId.split(",");
	var tempCode= code.split(",");
	for(var y = 0; y < tempCodId.length;y++) {
		$("#"+tempSelectId[y]).html("<option value=''>전체</option>");
	}
	$.ajax({
        url:  '/ajaxCode.do',
        data: "codeId="+codeId,
        dataType: 'json',
        success: function(data) {
        	for(var i = 0; i < data.codeList.length;i++) {
        		for(var y = 0; y < tempCodId.length;y++) {
        			if(tempCodId[y] == data.codeList[i].codeId){
        				$("#"+tempSelectId[y]).append("<option value='"+data.codeList[i].code+"'>"+data.codeList[i].codeNm+"</option>");
        			}
    			}
			}

    		for(var y = 0; y < tempCodId.length;y++) {
    			if(tempCode[y] != ''){
    				$("#"+tempSelectId[y]).val(tempCode[y]);
    			}
			}
		}
	});
}

/**********************************************
 * 함수명 : gfn_setCode
 * 설  명 : AJAX로 코드 조회
 * 인  자 : codeId, callBack
 * 사용법 : gfn_setCode("COM001,COM002", "fn_setCodeCallBack");
 *
 * 수정일        수정자      수정내용
 * ------        ------     -------------------
 * 2014.09.02    우성택      신규작업
 *
 * *********************************************/
function gfn_setCode(codeId, callBack) {
	$.ajax({
        url:  '/ajaxCode.do',
        data: "codeId="+codeId,
        dataType: 'json',
        success: function(data) {
        	eval(callBack+"(data)"); // 콜백함수 호출
		}
	});
}

/**********************************************
 * 함수명 : gfn_setCodeIndex
 * 설  명 : AJAX로 코드 조회
 * 인  자 : codeId, callBack, index
 * 사용법 : gfn_setCode("COM001,COM002", "fn_setCodeCallBack", index);
 *
 * 수정일        수정자      수정내용
 * ------        ------     -------------------
 * 2014.10.20    김규형      신규작업
 *
 * *********************************************/
function gfn_setCodeIndex(codeId, callBack, index) {
	$.ajax({
        url:  '/ajaxCode.do',
        data: "codeId="+codeId,
        dataType: 'json',
        async : false,
        success: function(data) {
        	eval(callBack+"(data,"+index+")"); // 콜백함수 호출
		}
	});
}

/**********************************************
 * 함수명 : gfn_setCookie
 * 설  명 : 쿠키 저장
 * 인  자 : 쿠키이름, 쿠키값, 쿠기유지일수(-1:현재창, 0:삭제, 1:하루...)
 * 사용법 : gfn_setCookie("test", "1111", 1);
 *
 * 수정일        수정자      수정내용
 * ------        ------     -------------------
 * 2014.09.02    우성택      신규작업
 *
 * *********************************************/
function gfn_setCookie (name, value, expireDays) {

	var expdate = new Date();

	if (expireDays > -1) {
		if(expireDays > 0 ){
			expdate.setTime(expdate.getTime() + 1000 * 3600 * 24 * expireDays); // 일수만큼 유지
		}else if (expireDays = 0) {
			expdate.setTime(expdate.getTime() - 1); // 쿠키 삭제조건
		}
		//document.cookie = name + "=" + escape (value) + "; path=/; expires=" + expdate.toGMTString();
		document.cookie = name + "=" + encodeURIComponent (value) + "; path=/; expires=" + expdate.toGMTString();
	}else {
		//document.cookie = name + "=" + escape (value) + "; path=/;";
		document.cookie = name + "=" + encodeURIComponent (value) + "; path=/; expires=" + expdate.toGMTString();
	}
}

/**********************************************
 * 함수명 : gfn_getCookie
 * 설  명 : 쿠키값 가져오기
 * 인  자 : 쿠키이름
 * 사용법 : gfn_getCookie("test");
 *
 * 수정일        수정자      수정내용
 * ------        ------     -------------------
 * 2014.09.02    우성택      신규작업
 *
 * *********************************************/
function gfn_getCookie(Name) {
    var search = Name + "=";
    if (document.cookie.length > 0) { // 쿠키가 설정되어 있다면
        offset = document.cookie.indexOf(search);
        if (offset != -1) { // 쿠키가 존재하면
            offset += search.length;
            // set index of beginning of value
            end = document.cookie.indexOf(";", offset);
            // 쿠키 값의 마지막 위치 인덱스 번호 설정
            if (end == -1)
                end = document.cookie.length;
            //return unescape(document.cookie.substring(offset, end));
            return decodeURIComponent(document.cookie.substring(offset, end));
        }
    }
    return "";
}
/**********************************************
 * 함수명 : gfn_checkCorpoNo
 * 설  명 : 법인번호 형식을 체크한다.
 * 인  자 : 법인번호
 * 사용법 : gfn_checkCorpoNo("215011-0001021");
 *
 * 수정일        수정자      수정내용
 * ------        ------     -------------------
 * 2014.10.10    탁원호      신규작업
 *
 * *********************************************/
function gfn_checkCorpoNo(corpnum) {
	  var re = /-/g;
	  sRegNo = corpnum.replace('-','');

	  if (sRegNo.length != 13){
	   return false;
	  }

	  //법인번호 예외처리
	  if(corpnum == "215011-0001021" ||
		 corpnum == "170137-0000357" ||
		 corpnum == "115236-0000259" ||
		 corpnum == "174736-0000224" ||
		 corpnum == "210171-0000017" ||
		 corpnum == "161411-0001720" ||
		 corpnum == "184571-0000709" ||
		 corpnum == "214936-0000192" ||
		 corpnum == "165132-0000143" ||
		 corpnum == "144311-0000362" ||
		 corpnum == "164736-0000956" ||
		 corpnum == "195511-0555577" ||
		 corpnum == "124136-0002744" ||
		 corpnum == "141211-0016409" ||
		 corpnum == "200111-0109048" ||
		 corpnum == "201114-0005737" ||
		 corpnum == "164311-0001540"){
		  return true;
	  }

	  var arr_regno  = sRegNo.split("");
	  var arr_wt   = new Array(1,2,1,2,1,2,1,2,1,2,1,2);
	  var iSum_regno  = 0;
	  var iCheck_digit = 0;

	  for (i = 0; i < 12; i++){
	    iSum_regno +=  eval(arr_regno[i]) * eval(arr_wt[i]);
	  }

	  iCheck_digit = 10 - (iSum_regno % 10);

	  iCheck_digit = iCheck_digit % 10;

	  if (iCheck_digit != arr_regno[12]){
	    return false;
	  }

	  return true;
}
/**********************************************
 * 함수명 : gfn_checkJumin
 * 설  명 : 주민번호 형식을 체크한다.
 * 인  자 : 주민번호
 * 사용법 : gfn_checkJumin("000000-0000000");
 *
 * 수정일        수정자      수정내용
 * ------        ------     -------------------
 * 2014.10.10    탁원호      신규작업
 *
 * *********************************************/
function gfn_checkJumin(varobj1) {
	var jumin_string = varobj1.replace(/[-|.]/gi,'');
	var a = jumin_string.substring(6,7);

	if (a < '0' || a > '2') {
		return false;
	}

	var sum = 0;
	var num = 2;

	for(var i=0;i<12;i++)
	{
		a = jumin_string.substring(i,i+1);
		sum = sum + num * (a - '0');
		num++;
		if( num == 10 ) num = 2;
	}

	i = (11 - (sum % 11)) % 10;

	a = jumin_string.substring(12,13);
	if( a != i)
	{
		return false;
	}

	return true;
}/**********************************************
 * 함수명 : gfn_checkBusino
 * 설  명 : 사업자번호 형식을 체크한다.
 * 인  자 : 사업자번호
 * 사용법 : gfn_checkBusino("123-45-10486");
 *
 * 수정일        수정자      수정내용
 * ------        ------     -------------------
 * 2014.10.10    탁원호      신규작업
 *
 * *********************************************/
function gfn_checkBusino(vencod) {
	vencod = vencod.replace(/[-|.]/gi,'');

    var sum = 0;
    var getlist =new Array(10);
    var chkvalue =new Array("1","3","7","1","3","7","1","3","5");
    for(var i=0; i<10; i++) { getlist[i] = vencod.substring(i, i+1); }
    for(var j=0; j<9; j++) { sum += getlist[j]*chkvalue[j]; }
    sum = sum + parseInt((getlist[8]*5)/10);
    sidliy = sum % 10;
    sidchk = 0;
    if(sidliy != 0) { sidchk = 10 - sidliy; }
    else { sidchk = 0; }
    if(sidchk != getlist[9]) { return false; }
    return true;
}


/**
 * Number 객체의 소수점 이하 자리 수를 반환한다.
 * @private
 * @param {Number} 소수점 이하 자리 수를 확인할 대상 객체
 * @return 객체의 소수점 이하 자리 수
 * @type {Number}
 */
function gfn_decimalPoint(nValue) {
	var sValue = String(gfn_delComma(nValue));
	var decimalPointIdx = 0;
	var decimalPoint = sValue.indexOf('.');
	if (decimalPoint != -1 && decimalPoint != sValue.length) {
		decimalPointIdx = sValue.length - decimalPoint - 1;
	}

	return decimalPointIdx;
}

/**
 * 숫자를 더한다. 정수일 경우는 기본적으로 제공되는 연산자(+)를 통해서 연산한다.<br/>
 * 단 IEEE 754에 의해 부동소수점 연산에 오차처리를 위해서 함수를 사용한다.
 * @public
 */
function gfn_add(nValue, mValue) {
	var nFraction = gfn_decimalPoint(nValue);
	var mFraction = gfn_decimalPoint(mValue);

	var expN = 1;

	if (nFraction < mFraction) {
		expN = Math.pow(10, mFraction);
	} else {
		expN = Math.pow(10, nFraction);
	}

	var tmpN = gfn_delComma(nValue) * expN;
	var tmpM = gfn_delComma(mValue) * expN;

	return (tmpN + tmpM) / expN;
}

/**
 * 숫자를 뺀다. 정수일 경우는 기본적으로 제공되는 연산자(-)를 통해서 연산한다.<br/>
 * 단 IEEE 754에 의해 부동소수점 연산에 오차처리를 위해서 함수를 사용한다.
 * @public
 */
function gfn_substract(nValue, mValue) {
	var nFraction = gfn_decimalPoint(nValue);
	var mFraction = gfn_decimalPoint(mValue);

	var expN = 1;

	if (nFraction < mFraction) {
		expN = Math.pow(10, mFraction);
	} else {
		expN = Math.pow(10, nFraction);
	}

	var tmpN = gfn_delComma(nValue) * expN;
	var tmpM = gfn_delComma(mValue) * expN;

	return (tmpN - tmpM) / expN;
}

/**
 * 숫자를 곱한다. 정수일 경우는 기본적으로 제공되는 연산자(*)를 통해서 연산한다.<br/>
 * 단 IEEE 754에 의해 부동소수점 연산에 오차처리를 위해서 함수를 사용한다.
 * @public
 */
function gfn_multiply(nValue, mValue) {
	var nFraction = gfn_decimalPoint(nValue);
	var mFraction = gfn_decimalPoint(mValue);

	var expN  = 1;
	var expNr = 1;

	if (nFraction < mFraction) {
		expN  = Math.pow(10, mFraction);
		expNr = Math.pow(10, (mFraction * 2));
	} else {
		expN  = Math.pow(10, nFraction);
		expNr = Math.pow(10, (nFraction * 2));
	}

	var tmpN = gfn_delComma(nValue) * expN;
	var tmpM = gfn_delComma(mValue) * expN;

	return (tmpN * tmpM) / expNr;
}

/**
 * 숫자를 나눈다. 정수일 경우는 기본적으로 제공되는 연산자(/)를 통해서 연산한다.<br/>
 * 단 IEEE 754에 의해 부동소수점 연산에 오차처리를 위해서 함수를 사용한다.
 * @public
 */
function gfn_divide(nValue, mValue) {
	var nFraction = gfn_decimalPoint(nValue);
	var mFraction = gfn_decimalPoint(mValue);

	var expN    = 1;
	var expNsum = Math.pow(10, (mFraction + nFraction));

	if (nFraction < mFraction) {
		expN = Math.pow(10, mFraction);
	} else {
		expN = Math.pow(10, nFraction);
	}

	var tmpN = gfn_delComma(nValue) * expN;
	var tmpM = gfn_delComma(mValue) * expN;

	return (tmpN / tmpM);
}

/**
 * 나머지를 구한다. 정수일 경우는 기본적으로 제공되는 연산자(/)를 통해서 연산한다.<br/>
 * 단 IEEE 754에 의해 부동소수점 연산에 오차처리를 위해서 함수를 사용한다.
 * @public
 */
function gfn_modular(nValue, mValue) {
	var nFraction = gfn_decimalPoint(nValue);
	var mFraction = gfn_decimalPoint(mValue);

	var expN = 1;

	if (nFraction < mFraction) {
		expN = Math.pow(10, mFraction);
	} else {
		expN = Math.pow(10, nFraction);
	}

	var tmpN = gfn_delComma(nValue) * expN;
	var tmpM = gfn_delComma(mValue) * expN;

	return (tmpN % tmpM) / expN;
}

/**
 * 숫자를 반올림한다.
 * @public
 * @param {Number} nValue 반올림할 대상 객체
 * @param {Number} precision 반올림할 소숫점 자리수
 * @return 반올림한 수
 * @type Number
 */
function gfn_round(nValue, precision) {
	tmpValue = gfn_delComma(nValue) * Math.pow(10, precision);
	tmpValue = Math.round(tmpValue);
	return tmpValue / Math.pow(10, precision);
}

/**
 * 숫자에만 자동으로 3자리 쉼표 붙이는 함수
 */
function gfn_toComma(nValue) {
	var ret;
	var reg_exp_comma, arrNumber;
	reg_exp_comma = new RegExp('([0-9])([0-9][0-9][0-9][,.])');

	arrNumber = (String(nValue)).split('.');

	arrNumber[0] += '.';

	do {
		arrNumber[0] = arrNumber[0].replace(reg_exp_comma, '$1,$2');
	} while (reg_exp_comma.test(arrNumber[0]));

	if (arrNumber.length > 1) {
		ret = arrNumber.join('');
	} else {
		ret = arrNumber[0].split('.')[0];
	}
	return ret;
}

/**
 * 문자(숫자)열에서 ','문자제거
 */
function gfn_delComma(str) {
    if (str.length < 1) {
        return "";
    } else {
        var st = "";
        var sp = ",";
        for (var i = 0; i < str.length; i++) {
            if (sp.indexOf(str.substring(i, i + 1)) == -1) {
                st += str.substring(i, i + 1);
            }
        }
        return st;
    }
}

/**
 * inputBox에 숫자만 입력
 * gfn_toNumber(objName)
 */
function gfn_new_number(objName){
	$(':input[id^='+objName+']').each(function(index) {
		$("input[id="+objName+"]").eq(index).keyup(function(){
	           gfn_number(this);

	    });
	});
}

/**
 * inputBox에 숫자만 입력받음
 * onblur="gfn_number(this);"
 * 이벤트는 사용자가 선택사용하여도 무방
 */
function gfn_number(obj) {
	$(obj).val($(obj).val().replace(/[^0-9]/gi,""));
}

/**
 * inputBox에 숫자만 입력 및 자동으로 3자리 쉼표
 * gfn_toNumber(objName)
 */
function gfn_toNumber(objName){
	$(':input[id^='+objName+']').each(function(index) {
		$("input[id="+objName+"]").eq(index).keyup(function(){
	           gfn_number(this);
	           this.value = gfn_toComma(this.value);
	    });
	});
}

/**********************************************
 * 함수명 : gfn_ifrAction
 * 설  명 : ifrAction
 * 인  자 : button obj id, actUrl
 * 사용법 : gfn_fileUpload("formName", "/insertTest.do");
 *
 * 수정일        수정자      수정내용
 * ------        ------     -------------------
 * 2014.09.02    우성택      신규작업
 * 2015.05.28    김경민      수정 및 추가(글자확대/축소 추가 에러부분 주석처리)
 *
 * *********************************************/
gfn_ifrAction = function (formName, actUrl) {

    // iframe의 name이자, form의 target
    var target_name = 'iframe_action';

    //iframe 체크
    try{
    	var chk = document.getElementById(target_name).contentWindow.document;
    }catch(e){
        // iframe 생성
        var iframe = $('<iframe src="/ifrAction.do;" id="'+target_name+'" name="'+target_name+'" style="display:none;"></iframe>');
        $('body').append(iframe);
    }

    $("#"+formName).attr("target",target_name);
    $("#"+formName).attr("action",actUrl).submit();

}

var Prototype = {
		  Version: '1.7',
		  Browser: (function(){
		    var ua = navigator.userAgent;
		    var isOpera = Object.prototype.toString.call(window.opera) == '[object Opera]';
		    return {
		      IE:             !!window.attachEvent && !isOpera,
		      Opera:          isOpera,
		      WebKit:         ua.indexOf('AppleWebKit/') > -1,
		      Gecko:          ua.indexOf('Gecko') > -1 && ua.indexOf('KHTML') === -1,
		      MobileSafari:   /Apple.*Mobile/.test(ua)
		    }
		  })(),

		  BrowserFeatures: {
		    XPath: !!document.evaluate,

		    SelectorsAPI: !!document.querySelector,

		    ElementExtensions: (function() {
		      var constructor = window.Element || window.HTMLElement;
		      return !!(constructor && constructor.prototype);
		    })(),
		    SpecificElementExtensions: (function() {
		      if (typeof window.HTMLDivElement !== 'undefined')
		        return true;

		      var div = document.createElement('div'),
		          form = document.createElement('form'),
		          isSupported = false;

		      if (div['__proto__'] && (div['__proto__'] !== form['__proto__'])) {
		        isSupported = true;
		      }

		      div = form = null;

		      return isSupported;
		    })()
		  },

		  ScriptFragment: '<script[^>]*>([\\S\\s]*?)<\/script>',
		  JSONFilter: /^\/\*-secure-([\s\S]*)\*\/\s*$/,

		  emptyFunction: function() { },

		  K: function(x) { return x }
		};

		var Zoom = {
			size : 1.0,

			exec : function (v) {
				var currentSize = this.size;
				if (v == 0) {
					currentSize = 1.0;
				} else {
					currentSize += v * 0.1;
				}

				if (currentSize < 0.8 || currentSize > 3.0) {
					//alert(currentSize);
				} else {
					this.size = currentSize;
				}

				if (!Prototype.Browser.IE) {
					$('body').css('-webkit-transform','scale(' + this.size + ')');
					$('body').css('-moz-transform','scale(' + this.size + ')');
					$('body').css('-o-transform','scale(' + this.size + ')');

					$('body').css('-webkit-transform-origin', '50% 0%');
					$('body').css('-moz-transform-origin', '50% 0%');
					$('body').css('-o-transform-origin', '50% 0%');
				} else {
					$('body').css('zoom', (this.size * 100) + '%');
					$('body').css('-ms-transform','scale(' + this.size + ')');
					$('body').css('-ms-transform-origin', '50% 0%');

					/* 메인화면 글자확대.축소 기능 추가 후 아래의 부분에 에러가 발생하여 주석처리 2015.05.28 */
					//$('body')[0].filters.item(0).M11 *= 1.5;
					//$('body')[0].filters.item(0).M12 *= 1.5;
					//$('body')[0].filters.item(0).M21 *= 1.5;
					//$('body')[0].filters.item(0).M22 *= 1.5;

				}
				$('body').css('transform','scale(' + this.size + ')');
				$('body').css('transform-origin', '50% 0%');
			},

			load : function (){

				if (this.size < 0.8 || this.size > 3.0) {
					return;
				}

				if (!Prototype.Browser.IE) {
					$('body').css('-webkit-transform','scale(' + this.size + ')');
					$('body').css('-moz-transform','scale(' + this.size + ')');
					$('body').css('-o-transform','scale(' + this.size + ')');

					$('body').css('-webkit-transform-origin', '50% 0%');
					$('body').css('-moz-transform-origin', '50% 0%');
					$('body').css('-o-transform-origin', '50% 0%');
				} else {
					$('body').css('zoom', (this.size * 100) + '%');
					$('body').css('-ms-transform','scale(' + this.size + ')');
					$('body').css('-ms-transform-origin', '50% 0%');

					$('body')[0].filters.item(0).M11 = this.size;
					$('body')[0].filters.item(0).M12 = this.size;
					$('body')[0].filters.item(0).M21 = this.size;
					$('body')[0].filters.item(0).M22 = this.size;
				}
				$('body').css('transform','scale(' + this.size + ')');
				$('body').css('transform-origin', '50% 0%');
			}

		};


		/**********************************************
		 * 함수명 : gfn_setScroll
		 * 설  명 : setScroll
		 * 인  자 : ul id
		 * 사용법 : gfn_setScroll("ulId");
		 *
		 * 수정일        수정자      수정내용
		 * ------        ------     -------------------
		 * 2014.12.18    오일용      신규작업
		 *
		 * *********************************************/
		function gfn_setScroll(ulId){
			if($("#"+ulId).children().length>10){
				$("#"+ulId).css("height","200px");
				$("#"+ulId).css("overflow-y","scroll");
			}
		}

		/**********************************************
		 * 함수명 : gfn_validitionTelNo
		 * 설  명 : validitionTelNo
		 * 인  자 : TelNo
		 * 사용법 : gfn_validitionTelNo("TelNo");
		 *
		 * 수정일        수정자      수정내용
		 * ------        ------     -------------------
		 * 2014.12.24    오일용      신규작업
		 *
		 * *********************************************/
		function gfn_validitionTelNo( strTelNo )
		{
			var rtnStr = "";
			var temp = new String(strTelNo);
			var regExp1 = /^01[016789]\d{3,4}\d{4}$/; 		//핸드폰 정규식
			var regExp2 = /^02\d{3,4}\d{4}$/;				//전화번호(02) 정규식
			var regExp3 = /^0[3,4,5,6][1-6]\d{3,4}\d{4}$/;	//전화번호(기타) 정규식
			var regExp4 = /^070\d{3,4}\d{4}$/;			    //전화번호(기타.070) 정규식

			if(strTelNo !="" )
			{
				//temp = temp.trim();
				temp = temp.replace(/(^\s*)|(\s*$)/g,"");
				temp = temp.replace(/-/g,"");
				if(temp.substr(0,2) == "01"){
					// 01로시작하는 번호는 핸드폰 체크
					rtnStr = regExp1.test(temp);
				}else if(temp.substr(0,2) == "02"){
					// 지역번호 02로 시작할경우 체크
					rtnStr = regExp2.test(temp);
				}else if(temp.substr(0,2) == "07"){
					// 지역번호 070으로 시작할경우 체크
					rtnStr = regExp4.test(temp);
				}else{
					// 기타일 경우 전화번호 체크
					rtnStr = regExp3.test(temp);
				}
			}
			return rtnStr;
		}

		/**********************************************
		 * 함수명 : gfn_checkNumber
		 * 설  명 : validitionNumber
		 * 인  자 : TelNo
		 * 사용법 : gfn_checkNumber("123");
		 *
		 * 수정일        수정자      수정내용
		 * ------        ------     -------------------
		 * 2014.12.24    오일용      신규작업
		 *
		 * *********************************************/
		function gfn_checkNumber(objName){
			$(':input[id^='+objName+']').keyup(function(event) {
				regexp = /[^0-9]/gi;

			    v = $(this).val();

			    if (regexp.test(v)) {

			        alert("숫자만 입력가능 합니다.");

			        $(this).val(v.replace(regexp, ''));
			        return false;
			    }else{
			    	return true;
			    }

			});
		}

		/**********************************************
		 * 함수명 : gfn_validitionPw
		 * 설  명 : 비밀번호 유효성 검사
		 * 			ERROR_001 : 자릿수 체크
		 * 			ERROR_002 : 숫자만 입력된 경우
		 * 			ERROR_003 : 문자만 입력된 경우
		 * 			ERROR_004 : 특수문자가 입력되지 않은경우
		 * 			ERROR_005 : 같은문자가 3번이상 반복된 경우
		 * 인  자 : Pw
		 * 사용법 : gfn_validitionPw("Pw");
		 *
		 * 수정일        수정자      수정내용
		 * ------        ------     -------------------
		 * 2015.1.6      이태주      신규작업
		 *
		 * *********************************************/
		function gfn_validitionPw( strPw )
		{
			var rtnStr = "";
			var temp = new String(strPw);


			if(strPw !="" )
			{
				temp = temp.replace(/(^\s*)|(\s*$)/g,"");
				temp = temp.replace(/-/g,"");

				//Step1. 자릿수 체크 : 10자리 이하인경우
				if(temp.length < 10){	rtnStr = 'ERROR_001';	}

				//Step2. 숫자만 입력된 경우 : 문자가 입력되지 않은경우
				else if(temp.match(/[^0-9]/g) == null){		rtnStr = 'ERROR_002';	}

				//Step3. 숫자가 입력되지 않은경우
				else if(temp.match(/[0-9]/g) == null){		rtnStr = 'ERROR_003';	}

				//Step4. 특수문자가 입력되지 않은 경우
				//else if(temp.match(/[!#$%^&*()?+=\/]/) == null){	alert();rtnStr = 'ERROR_004';	}
				//else if(temp.match(/[~!@#$%^&*()_-+={}|;:'"<>,.?\/]/) == null){	rtnStr = 'ERROR_004';	}
				//else if(temp.match(/[!@#$%^&*(),.?\":{}|<>]/) == null){	rtnStr = 'ERROR_004';	}
				else if(temp.match(/[!@#$%^*()\-_=+\\\|\[\]{};:\'",.<>\/?]/) == null){	rtnStr = 'ERROR_004';	}


				//Step5. 같은문자 3번이상 사용시
				else if(/(\w)\1\1/.test(temp)){		rtnStr = 'ERROR_005';	}

				else{	rtnStr = 'SUCCESS';		}
			}
			return rtnStr;
		}
		
		/**********************************************
		 * 함수명 : gfn_validitionPw2
		 * 설  명 : 비밀번호 유효성 검사
		 * 			ERROR_001 : 자릿수 체크
		 * 			ERROR_002 : 숫자만 입력된 경우
		 * 			ERROR_003 : 문자만 입력된 경우
		 * 			ERROR_004 : 특수문자가 입력되지 않은경우
		 * 			ERROR_005 : 같은문자가 3번이상 반복된 경우
		 * 인  자 : Pw
		 * 사용법 : gfn_validitionPw("Pw");
		 *
		 * 수정일        수정자      수정내용
		 * ------        ------     -------------------
		 * 2015.1.6      이태주      신규작업
		 *
		 * *********************************************/
		function gfn_validitionPw2( strPw, userId )
		{
			var rtnStr = "";
			var temp = new String(strPw);


			if(strPw !="" )
			{
				// 길이
		        if(!/^[a-zA-Z0-9!@#$%^&*()?_~]{9,}$/.test(strPw))
		        { 
		            return "숫자, 영문, 특수문자 조합으로 9자리 이상 사용해야 합니다.";
		        }
		         
		        // 영문, 숫자, 특수문자 혼용
		        var chk = 0;
		        if(strPw.search(/[0-9]/g) != -1 ) chk ++;
		        if(strPw.search(/[a-z]/ig)  != -1 ) chk ++;
		        if(strPw.search(/[!@#$%^&*()?_~]/g)  != -1  ) chk ++;
		        if(chk < 3)
		        { 
		            return "숫자, 영문, 특수문자를 혼용하여야 합니다.";
		        }
		         
		        // 동일한 문자/숫자/ 3이상, 연속된 문자
		        if(/(.)\1\1/.test(strPw))
		        {
		            return "동일한 문자/숫자/특수문자 3회 이상 사용하실 수 없습니다.";
		        }
		        
		        // 오름차순 내림차순 
		        if(isContinuedValue(strPw))
		        {
		        	return "3회 이상의 연속 문자 및 숫자를 사용하실 수 없습니다.";
		        }
		         
		        // 아이디 포함 여부
		        if(strPw.search(userId)>-1)
		        {
		            return "ID가 포함된 비밀번호는 사용하실 수 없습니다.";
		        }

		        return "SUCCESS"
			}
		}

		//  반복 문자 및 숫자 체크
		function isContinuedValue(value) {
	        //console.log("value = " + value);
	        var intCnt1 = 0;
	        var intCnt2 = 0;
	        var temp0 = "";
	        var temp1 = "";
	        var temp2 = "";
	        //var temp3 = "";
	 
	        //for (var i = 0; i < value.length-3; i++) {
	        for (var i = 0; i < value.length-2; i++) {
	            //console.log("=========================");
	            temp0 = value.charAt(i);
	            temp1 = value.charAt(i + 1);
	            temp2 = value.charAt(i + 2);
	            //temp3 = value.charAt(i + 3);
	 
	            //console.log(temp0)
	            //console.log(temp1)
	            //console.log(temp2)
	            //console.log(temp3)
	 
	            if (temp0.charCodeAt(0) - temp1.charCodeAt(0) == 1
	                    && temp1.charCodeAt(0) - temp2.charCodeAt(0) == 1
	                    /*&& temp2.charCodeAt(0) - temp3.charCodeAt(0) == 1*/) {
	                intCnt1 = intCnt1 + 1;
	            }
	 
	            if (temp0.charCodeAt(0) - temp1.charCodeAt(0) == -1
	                    && temp1.charCodeAt(0) - temp2.charCodeAt(0) == -1
	                    /*&& temp2.charCodeAt(0) - temp3.charCodeAt(0) == -1*/) {
	                intCnt2 = intCnt2 + 1;
	            }
	            //console.log("=========================");
	        }
	 
	        //console.log(intCnt1 > 0 || intCnt2 > 0);
	        return (intCnt1 > 0 || intCnt2 > 0);
	    }

		//이메일 체크
		function gfn_emailCheck(email) {
		    var regExp = /[0-9a-zA-Z][_0-9a-zA-Z-]*@[_0-9a-zA-Z-]+(\.[_0-9a-zA-Z-]+){1,2}$/;
		    if ( !email.match(regExp) ) {	return false;    } else {		return true;    }
		 }

		//전화번호 체크
		function gfn_telCheck(tel) {
		    var regExp = /^((0[1-9])[1-9]+[0-9]{6,7})|(010[1-9][0-9]{7})$/;
		    if ( !tel.match(regExp) ) {	return false;		} else {	return true;		}
		 }

		/*-----------------------------------------------------------------------------------------------
		 * function : gfn_dateTime(nYear, nMonth, nDay)
		 * 설명   : datetype을 string으로 변환
		 * param   : nYear, nMonth, nDay
		 * return  : yyyymmdd 형태의 string값
		 * Example :
		 ----------------------------------------------------------------------------------------------*/
		 function gfn_dateTime(nYear, nMonth, nDay) {
				if (nYear.toString().trim().length >= 5) {
			        var sDate    = new String(nYear);
			        var nYear    = sDate.substr(0,4);
			        var nMonth   = sDate.substr(4,2);
			        var nDate    = ((sDate.substr(6,2) == "") ? 1 : sDate.substr(6,2));
			        var nHours   = ((sDate.substr(8,2) == "") ? 0 : sDate.substr(8,2));
			        var nMinutes = ((sDate.substr(10,2) == "") ? 0 : sDate.substr(10,2));
			        var nSeconds = ((sDate.substr(12,2) == "") ? 0 : sDate.substr(12,2));

			        var objDate = new Date(parseInt(nYear), parseInt(nMonth)-1, parseInt(nDate), parseInt(nHours), parseInt(nMinutes), parseInt(nSeconds));
			    } else {
			        var objDate = new Date(parseInt(nYear), parseInt(nMonth)-1, parseInt(((nDay == null) ? 1 : nDay)));
			    }

			    var strYear  = objDate.getFullYear().toString();
			    var strMonth = (objDate.getMonth() + 1).toString();
			    var strDate  = objDate.getDate().toString();

			    if (strMonth.length == 1)  strMonth  = "0" + strMonth;
			    if (strDate.length == 1)   strDate   = "0" + strDate;

			    return strYear + strMonth + strDate;
			}

		 /*-----------------------------------------------------------------------------------------------
			 * function : gfn_addDate(strDate, offset)
			 * 설명     : offset 만큼 지정한 일을 더한다.
			 * param    : strDate  : yyyymmdd 형태의 string
			              offset   : 더할 숫자
			 * return   : offset만큼 더해진 일자
			 * Example  :
			 ----------------------------------------------------------------------------------------------*/
			function gfn_addDate(strDate, offset) {

				var intYear		= parseInt(strDate.substr(0, 4));
				var intMonth		= parseInt(strDate.substr(4, 2));
				var intDay		= parseInt(strDate.substr(6, 2)) + offset;

				return gfn_dateTime(intYear, intMonth, intDay);
			 }

			/*-----------------------------------------------------------------------------------------------
			 * function name : gfn_getCurDate()
			 * 설명 : 시스템 현재 일자를 가지고 온다.
			 * param : none
			 * return : 현재 일자
			 * example :
			           gfn_getCurDate();  //return 20111120
			 ----------------------------------------------------------------------------------------------*/
			function gfn_getCurDate(){
				 var today = new Date();
				 var year = today.getFullYear();
				 var month = today.getMonth();
				 var day = today.getDate();
				 var resultDate = new Date(year, month, day);


				       year = resultDate.getFullYear();
				       month = resultDate.getMonth() + 1;
				       day = resultDate.getDate();

				       if (month < 10)
				           month = "0" + month;
				       if (day < 10)
				           day = "0" + day;

				       return year +""+  month +""+  day;
			}

			/*-----------------------------------------------------------------------------------------------
			 * function : gfn_dashDate(strDate, dashType)
			 * 설명     : dashType를 구분자로 하여 날짜를 반환한다..
			 * param    : strDate  : yyyymmdd 형태의 string
			              dashType   : 날자 구분(-, . 등)
			 * return   : dashType으로 구분된 날짜
			 * Example  :
			 ----------------------------------------------------------------------------------------------*/
			function gfn_dashDate(strDate, dashType) {
				if(strDate.length != 8){
					return strDate;
				}else{
					var intYear		= parseInt(strDate.substr(0, 4));
					var intMonth    = parseInt(strDate.substr(4, 2));
					var intDay		= parseInt(strDate.substr(6, 2));

			       if (intMonth < 10)
			    	   intMonth = "0" + intMonth;
			       if (intDay < 10)
			    	   intDay = "0" + intDay;

				   return intYear + dashType + intMonth + dashType + intDay;
				}	
			}
			
			/*-----------------------------------------------------------------------------------------------
			 * function : gfn_dashDate2(strDate, dashType)
			 * 설명     : dashType를 구분자로 하여 날짜를 반환한다..
			 * param    : strDate  : yyyymmdd 형태의 string
			              dashType   : 날자 구분(-, . 등)
			 * return   : dashType으로 구분된 날짜
			 * Example  :
			 ----------------------------------------------------------------------------------------------*/
			function gfn_dashDate2(strDate, dashType){

			    if(!strDate) return "";
			    var formatNum = '';
			    
			    if(gfn_isNull(dashType)) dashType = "-";
			    
			    // 공백제거
			    strDate=strDate.replace(/\s/gi, "");

			    try{
			         if(strDate.length == 8) {
			              formatNum = strDate.replace(/(\d{4})(\d{2})(\d{2})/, '$1'+dashType+'$2'+dashType+'$3');
			         }
			    } catch(e) {
			         formatNum = strDate;
			         console.log(e);
			    }
			    return formatNum;
			}
			
			 /*-----------------------------------------------------------------------------------------------
			 * function : gfn_addDate(strDate, offset)
			 * 설명     : offset 만큼 지정한 월을 더한다.
			 * param    : strDate  : yyyymmdd 형태의 string
			              offset   : 더할 숫자
			 * return   : offset만큼 더해진 일자
			 * Example  :
			 ----------------------------------------------------------------------------------------------*/
			function gfn_addMonth(strDate, offset) {

				var intYear		= parseInt(strDate.substr(0, 4));
				var intMonth	= parseInt(strDate.substr(4, 2)) + offset;
				var intDay		= parseInt(strDate.substr(6, 2));

				return gfn_dateTime(intYear, intMonth, intDay);
			 }

			//한글 입력 못하게 하는 함수
			function NohanGul(event,obj) {
			  var key = window.event ? event.keyCode : event.which;
			  if(key == 229 ){	return false;	}
			  else			 {gfn_toNumber(obj);	return true;	}
			}

			/**********************************************
			 * 함수명 : gfn_checkSpletter
			 * 설  명 : input box에 특수문자 제한
			 * 		   한글/숫자/영문은 허용함. !,@ 같은 특수문자만 제외
			 * 인  자 : input box text 입력값
			 * 사용법 : gfn_notSpletter(this);
			 *
			 * 수정일        수정자      수정내용
			 * ------        ------     -------------------
			 * 2015.01.27    김경민      신규작업
			 *
			 * *********************************************/
			function gfn_checkSpletter()
			{
			 var objEv = event.srcElement;
			 var num ="{}[]()<>?_|~`!@#$%^&*-+\"'\\/ ";    //입력을 막을 특수문자 기재.
			 event.returnValue = true;

			 for (var i=0;i<objEv.value.length;i++)
			 {
			 if(-1 != num.indexOf(objEv.value.charAt(i)))
			 event.returnValue = false;
			 }

			 if (!event.returnValue)
			 {
			  alert("특수문자는 입력하실 수 없습니다.");
			  objEv.value="";
			 }
			}


			/**********************************************
			 * 함수명 : gfn_toDecimal(event)
			 * 설  명 : input box에 소숫점 첫째짜리까지만 허용
			 * 인  자 : input box text 입력값
			 * 사용법 : gfn_toDecimal(event);
			 *
			 * 수정일        수정자      수정내용
			 * ------        ------     -------------------
			 * 2015.03.10    오일용      신규작업
			 * 2015.03.11    손연아      function 수정
			 *
			 * *********************************************/
			 function gfn_toDecimal(obj) {
				 var val = obj.value;
					var re = /[^0-9.]/gi;
					obj.value = val.replace(re, '');

					var split = val.split(".");
					if(split.length > 2){  // 현재 value값에 소수점(.) 이 있으면 . 입력불가
						obj.value = val.substr(0,val.length-1);
					}
					if(split[1] != null){
						if(split[1].length > 1){ // 현재 value값이 소수점 둘째짜리 숫자이면 더이상 입력 불가
							obj.value = val.substr(0, val.length-1);
						}
					}
/*
			        var charCode = (evt.which) ? evt.which : event.keyCode;
			        if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57)){ return false;}

			        // Textbox value
			        var _value = event.srcElement.value;

			        // 소수점(.)이 두번 이상 나오지 못하게
			        var _pattern0 = /^\d*[.]\d*$/; // 현재 value값에 소수점(.) 이 있으면 . 입력불가

			        if (_pattern0.test(_value)) {
			            if (charCode == 46) {
			                return false;
			            }
			        }

			        // 1000 이하의 숫자만 입력가능
			        var _pattern1 = /^\d{3}$/; // 현재 value값이 3자리 숫자이면 . 만 입력가능

			        if (_pattern1.test(_value)) {
			            if (charCode != 46) {
			                //alert("1000 이하의 숫자만 입력가능합니다");
			                return false;
			            }
			        }

			        // 소수점 첫째자리까지만 입력가능

			        var _pattern2 = /^\d*[.]\d{1}$/; // 현재 value값이 소수점 둘째짜리 숫자이면 더이상 입력 불가

			        if (_pattern2.test(_value)) {
			            alert("소수점 첫째자리까지만 입력가능합니다.");
			            return false;
			        }

			        return true;*/

			    }

			 /*-----------------------------------------------------------------------------------------------
			  * function : gfn_pagingSearch(pageNo,formNm,gotoUrl,voId,voValue)
			  * 설명    : 페이지 버튼 클릭하면 VO에 담긴 검색조건으로 검색하기
			  * param    : pageNo(페이지번호),formNm(폼이름),gotoUrl(액션url),voArrId 배열,voArrValue 배열
			  * return   :
			  * Example  :
			 ----------------------------------------------------------------------------------------------*/
			 function gfn_pagingSearch(pageNo,formNm,gotoUrl,voArrId,voArrValue) {

			 		var arr = new Array();
			 		var i = 0;
			 		for(var i in voArrId){
			 			var retVal  = new Object();
			 			retVal.arrId = voArrId[i];
			 			retVal.arrValue = voArrValue[i];
			 			arr[i] = retVal;
			 			++i;
			 		}

			 		$("#pageIndex").val(pageNo);
			 		for(var i=0;i<arr.length;i++){
			 			var retVal = arr[i];
			 			$("#"+retVal.arrId).val(retVal.arrValue);
			 		}
			 		$("#"+formNm).attr("action",gotoUrl).submit();
			  }

			 /*-----------------------------------------------------------------------------------------------
			  * function : gfn_isNull(strValue)
			  * 설명    : Null에 해당하는 값 체크
			  * param    : strValue
			  * return   : true/false
			  * Example  : gfn_isNull(strValue);
			 ----------------------------------------------------------------------------------------------*/
			 function gfn_isNull(strValue)
			 {
			 	var stringValue = new String(strValue) ;

			 	if( new String(strValue).valueOf() == "undefined")
			 		return true;
			 	if( strValue == null )
			 		return true;
			 	if( strValue.length == 0 )
			 		return true;
			 	if( _gfn_isNull(stringValue.replace(/(^\s*)|(\s*$)/gi, "")))	// 빈칸만 입력한 경우도 null로 처리함
			 	    return true ;
			 	return false;
			 }

			 function _gfn_isNull(strValue)
			 {
			 	if( new String(strValue).valueOf() == "undefined")
			 		return true;
			 	if( strValue == null )
			 		return true;
			 	if( strValue.length == 0 )
			 		return true;
			 	return false;
			 }



 /**********************************************
  * 함수명 : gfn_noSpecial(that)
  * 설  명 : input box에 특수문자 방지
  * 인  자 : input box text 입력값
  * 사용법 : return gfn_noSpecial(this);
  *
  * 수정일       수정자      수정내용
  * ------         ------     -------------------
  * 2016.03.04  최진형      신규작업
  *
  * *********************************************/
 function gfn_noSpecial(that){
 	var val = $(that).val();
 	count =0;
 	tmpStr = "";
for(var i=0; i<val.length;i++){
	c_val = val.charAt(i);
	if(c_val.search(/[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/) == -1){
 			count++;
 		}else{
 			tmpStr += c_val;
 		}
 	}
 	if(count !=0){
 		$(that).val(tmpStr);
 		return false;
 	}
 }
 /**********************************************
  * 함수명 : gfn_telNo(that)
  * 설  명 : input box에 문자 방지 - 전화번호만 해당
  * 인  자 : input box text 입력값
  * 사용법 : return gfn_telNo(this);
  *
  * 수정일       수정자      수정내용
  * ------         ------     -------------------
  * 2016.03.04  최진형      신규작업
  *
  * *********************************************/
 function gfn_telNo(that){
 	var val = $(that).val();
 	count =0;
 	tmpStr = "";
 	for(var i=0; i<val.length;i++){
		c_val = val.charAt(i);
		if(c_val.search(/[0-9|-]/) == -1){
 			count++;
 		}else{
 			tmpStr += c_val;
 		}
 	}
 	if(count !=0){
 		$(that).val(tmpStr);
 		return false;
 	}
 }


 /**********************************************
  * 함수명 : gfn_viewHtml(boardCtx)
  * 설  명 : 에디터에서 사용한 태그 기능 그대로 사용
  * 인  자 : 게시글 내용
  * 사용법 : gfn_viewHtml(boardCtx);
  *
  * 수정일       수정자      수정내용
  * ------         ------     -------------------
  * 2016.03.24  최진형      신규작업
  *
  * *********************************************/

function gfn_viewHtml(boardCtx){
 	var iframe = document.createElement("iframe");
 	iframe.src = "/html/egovframework/com/cmm/utl/htmlarea3.0/popups/blank.html";
 	iframe.frameBorder = "0";
 	iframe.width = "100%";
 	iframe.height = "100%";
 	iframe.style.minHeight = "300px";
 	iframe.style.margin = "0";

 	$("#viewCnts").html(iframe);

 	var html = "";
 	html += "<html lang=\"ko\">\n";
 	html += "<head>\n";
 	html += "<style> html,body{border:0px;}</style>\n";
 	html += "</head>\n";
 	html += "<body>\n";
 	html += boardCtx+"\n";
 	html += "</body>\n";
 	html += "</html>";

 	var doc  = iframe.contentWindow.document;
 	doc.write(html);
 	doc.close();



}

/**********************************************
 * 함수명 : gfn_getCookie(cname)
 * 설  명 : 쿠키명으로 쿠키를 가져온다
 * 인  자 : 쿠키명
 * 사용법 : gfn_getCookie(cname);
 *
 * 수정일       수정자      수정내용
 * ------         ------     -------------------
 * 2017.03.24  김규형      신규작업
 * *********************************************/
function gfn_getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') c = c.substring(1);
        if (c.indexOf(name) == 0){
        	return decodeURIComponent(c.substring(name.length, c.length));
        }
    }
}

/**********************************************
 * 함수명 : gfn_loadingBlock
 * 설  명 : 처리중 입니다...
 * 인  자 :
 * 사용법 : gfn_loadingBlockOff();
 *
 * 수정일        수정자      수정내용
 * ------        ------     -------------------
 * 2017.04.21    노혁래      신규작업
 *
 * *********************************************/
gfn_loadingBlock = function (type) {
	if(type == 'on'){
		$.blockUI({ message: '<h1 style="margin-top:35px;"><img src="/images/com/loading.gif" /><span style="margin-top:70px;margin-left:20px;">처리중입니다...</span></h1>',
			css: { width:'300px',height:'100px'}
			, fadeIn : 0
		});
	}else {
		$.unblockUI();
	}
}

/**********************************************
 * 함수명 : gfn_fileUpload
 * 설  명 : iframe을 이용한 파일업로드
 * 인  자 : button obj id, 업로드파일표시 div id, ,업로드할 파일종류(image, file), 가능한 최대 파일수
 * 사용법 : gfn_fileUpload("file3Btn", "fileList3","image", 2);
 *
 * 수정일        수정자      수정내용
 * ------        ------     -------------------
 * 2014.09.02    우성택      신규작업
 *
 * *********************************************/
gfn_fileUpload3 = function (objName, divName, type, maxFileNum, pageGb) {

	var frmUp;

    $("#"+objName).removeClass();
    $("#"+objName).addClass("btn_sty3");
    $("#"+objName).html("찾아보기...");

    //iframe 체크
    if(typeof(frmUp) == 'undefined') {
    	frmUp = document.createElement("iframe");
    	frmUp.src = '/statsFileUpload.do';
    	frmUp.style.display = 'none';
    	document.body.appendChild(frmUp);
    }

    //파일 찾기
	$('#'+objName).on('click', function () {

		try{
			frmUp.contentWindow.document.getElementById('objName').value = objName;
			frmUp.contentWindow.document.getElementById('divName').value = divName;
			frmUp.contentWindow.document.getElementById('type').value = type;
			frmUp.contentWindow.document.getElementById('maxFileNum').value = maxFileNum;
			frmUp.contentWindow.document.getElementById('pageGb').value = pageGb;
			var fileUp = frmUp.contentWindow.document.getElementById('newFile');
			fileUp.click();
	    }catch(e){
	    	alert("페이지가 정상적으로 호출되지 안아 재호출 되었습니다.\n다시 한번 클릭해주세요.");
	    	frmUp.contentWindow.location.reload(true);
	    }
	});
}

/**
 * 시군조회
 * @constructor
 *
 *
 * @author: Kkh
 * @date  : 2018-10-10
 * @this {common}
 * @param {sidoCd, sigunID}
 * sidoCd    : 조회 시도 코드
 * sigunID   : 시군 select ID
 * @reutrn {void}
 */
function ajaxSigunList(sidoCd, sigunID){
    $.ajax({
        url:  "/com/ajaxSigunList.do",
        data: "sidoCd="+sidoCd,
        type: "POST",
        dataType: 'json',
        success: function(data, status) {

        var sigunList = data.sigunList;

        /*새로불러오기전 기존 셀렉트 옵션을 삭제한다.*/
        $("#"+sigunID).find('option').each(function() {
              $(this).remove();
          });

        $("#"+sigunID).append("<option value=''>시군구 선택</option>");

        $.each(sigunList,function(idx,row){

            var sigunCd =  row.sigunCd;
            var sigunNm =  row.sigunNm;
            $("#"+sigunID).append("<option value="+sigunCd+">"+sigunNm+"</option>");
        });
        }
    });
}

/**
 * 엑셀 다운로드
 * @constructor
 *
 *
 * @author: Kkh
 * @date  : 2018-10-11
 * @this {common}
 * @param {gridID}
 * gridID       : 조회할 그리드 ID
 * @reutrn 그리드 헤더정보
 */
function fn_excelHeader(gridID){
	var titleInfo = "";
	var colModel  = $("#"+gridID).jqGrid('getGridParam', 'colModel');
	for (var iCol = 0; iCol < colModel.length; iCol++) {
		if((colModel[iCol].name != "" && colModel[iCol].name != undefined) && (colModel[iCol].label != "" && colModel[iCol].label != undefined)){
			if(colModel[iCol].hidden == false){
				titleInfo += colModel[iCol].name + "::" + colModel[iCol].label + "^^"
			}

		}
	}
	return titleInfo;
}

/**
 * @!@ 그리드 NULL 공백 처리
 * @constructor
 * @author: JIJ
 * @date  :
 * @this {common}
 * @param {gridID}
 * gridID       : 조회할 그리드 ID
 * @reutrn 그리드 헤더정보
 */
var nullFormatter = function(cellvalue, options, rowObject){
	if(gfn_isNull(cellvalue)){
		cellvalue = '';
	}
	return cellvalue;
};

/**
 * @!@ undefined (DB NULL) 공백 처리
 * @constructor
 * @author: JIJ
 * @date  :
 * @this {common}
 * @param {gridID}
 * @reutrn rtnVal
 */
function gfn_nullRtnSpace(val){
	var rtnVal
	if(gfn_isNull(val)){
		rtnVal = '';
	}else{
		rtnVal = val;
	}
	return rtnVal;
};


/**
 * 파일삭제
 * @constructor
 * @author: leeji
 * @date  : 2020-09-07
 * @this {common}
 * @param {gridID}
 * @reutrn
 */
function fn_fileDel(filePth,fileNm){

	var callUrl = '/fileDel.do';

	$.ajax({
		type:'POST',
		data : 'filePth='+filePth+"&fileNm="+fileNm,
		datatype:'json',
		url : callUrl,
		success : function(data){}
	});
}

/**
 * 코드조회
 * @constructor
 *
 *
 * @author:
 * @date  : 2021-04-13
 * @this {common}
 * @param var codeInfo = [{cdId:'C01',selectId:'ID1',type:'1', callbackNm:'fn_cb'},{cdId:'C15001,C14001',selectId:'ID2',type:'2'},{cdId:'C03', minerCodeId:'C03001,C03003', selectId:'ID3',divId:'here', tag:'radio',type:'3'}]
 * cdId    : 조회 코드종류
 * selectId   : selectId
 * type : 1:코드종류 cdId 전부 조회
 *        2:명시한 cdId만 가져옴
 *        3:코드종류 cdId에서 minerCodeId코드사이의 값을 가져옴
 * tag : select,checkbox,radio
 * divId : divId
 * callbackNm : 콜백함수 첫번째행에 넣어야함
 * 같은 종류의 코드를 1개이상 쓸수 없음(cdId는 유니크해야함)
 * @reutrn {void}
 */
function fn_ajaxCodeList(codeInfo){

	var data = {codeInfo : codeInfo};
	var selectId;
	var tag;
	var divId;
	var rqesterMenuId = parent.$('li[aria-controls^=tabs-][aria-expanded=true]').attr('aria-controls').replace("tabs-", "");
	data.rqesterMenuId = rqesterMenuId;
	var callUrl = "/ajaxCode.do";
	isErrorMsg = false;

	$.ajax({
		type : "POST",
		url : callUrl,
		data :  JSON.stringify(data),
		dataType : "json",
		contentType:"application/json",
		success : function(result){

			if(result[userComMessageId] != undefined  ) {

				alert(result[userMessageId]);

        		if( codeInfo[0].errCallbackNm != undefined) {
    				eval(codeInfo[0].callbackNm+'(result)');
    			}

        		return;

			} else if ( result[userErrMessageId] != undefined) {
        		isErrorMsg = true;
        		alert(result[userErrMessageId]);
        		if( codeInfo[0].callbackNm != undefined) {
    				eval(codeInfo[0].callbackNm+'(result)');
    			}

        		return;
        	}

			for(var key in result){
				  var codeList = result[key];
				  selectId = '';
				  tag = '';
				  divId = '';

				  for(var i = 0; i < codeInfo.length; i++){

						  if(codeList.length > 0 && codeList[0].cdId == codeInfo[i].cdId || (codeInfo[i].type == '2' && codeInfo[i].cdId.indexOf(codeList[0].cdId) > 0)
								  || (codeInfo[i].type == '3' && codeInfo[i].cdId.indexOf(codeList[0].minerCodeId) > 0)){
							  selectId = codeInfo[i].selectId;
							  tag = codeInfo[i].tag;
							  divId = codeInfo[i].divId;

							  if(tag == undefined) {
								  $('#'+selectId).empty();
							  }else{
								  $('#'+divId).empty();
							  }

							  break;
						  }


				  }

				  for(var k = 0; k < codeList.length; k++){

					  if(tag == undefined){
						  if( $('#'+selectId).is('select')) {
							  $("#"+selectId).append("<option value='"+codeList[k].cd+"'>"+codeList[k].cdNm+"</option>");
						  }
					  }else{

						var strchk = (k == 0 ? "checked='checked'" : "");

						switch (tag) {
						case "select":

							if(k==0) $("#"+divId).append("<select name="+name+" id = "+id+">");
							$("#"+selectId).append("<option value='"+codeList[k].cd+"'>"+codeList[k].cdNm+"</option>");
							if(k==codeList.length-1) $("#"+divId).append("</select");
							break;

						case "checkbox":
							$("#"+divId).append("<input type='checkbox' id='"+ codeList[k].cd +"' name='"+ codeList[k].cdNm +"' value='"+ codeList[k].cd +"'" + strchk +">");
							$("#"+divId).append("<label for='"+codeList[k].cd+"'>"+codeList[k].cdNm+"</label>");
							break;

						case "radio":

							$("#"+divId).append("<input type='radio' id='"+ codeList[k].cd +"' name='"+ selectId +"' value='"+ codeList[k].cd +"'"+ strchk +">");
							$("#"+divId).append("<label for='"+codeList[k].cd+"'>"+codeList[k].cdNm+"</label>");
							break;
						}


					  }
				  }
			}

			// callback 호출
			if( codeInfo[0].callbackNm != undefined) {
				result.codeInfo = codeInfo;
				eval(codeInfo[0].callbackNm+'(result)');
			}

		},
		error : function(xhr, status, error){
			alert('실패');
		}
	});
}

/**
 * @!@ 다이얼로그 창 닫기
 * @param
 * @returns
 */
function fn_dialogClose(divId){
    $("#"+divId).dialog( "close" );
	$("#"+divId).empty();
}

/**
 * 프레임사이즈 자동 조절
 * @param
 * @returns
 */
function fn_setFrameSize(divHeight){
	var tabId = parent.$('li[id*=li_tabs-M][aria-expanded=true]').attr('aria-controls');
	var ifa = $(top.document).find('div#'+tabId+' > p > iframe');
	var height= divHeight == undefined ? ifa.get(0).contentWindow.document.body.scrollHeight : divHeight;
	ifa.attr('height', height > 730 ?  height : 730);	
	return tabId;
}

/**
 * id로 포커스 주기 콜백용
 * @param
 * @returns
 */
function fn_tagIdFocus(tagId){
	$("#"+tagId).focus();
}

/**
 * 화면 로드시 조회

$(document).ready(function() {

	// codeSet이 Y이면 startFnNm는 fn_ajaxCodeList 실행
	// startFnNm이 널이고 codeSet이 N이면 startFnNm는 requestUtil.getSearchForm 실행
	// param은 함수 실행 시 매개변수로 사용(예 : requestUtil.getSearchForm(param) 널이면 requestUtil.getSearchForm() 로 실행)
	// startFnNm이 널이 아니고 codeSet이 N이면 해당함수 실행 param은 상동
	// codeSet이 Y일 경우 fn_ajaxCodeListCallback(콜백함수)에서 requestUtil.getSearchForm을 실행해야 검색조건 유지 기능 작동
	// fn_ajaxCodeList나 requestUtil.getSearchForm로 시작 시 startFnNm 없이 param 과 codeSet만 설정가능 (예 : gfn_init({param:codeInfo, codeSet:'Y'});)

	var codeInfo = [{cdId:'C03',cd:comPgsStat,selectId:'srcPgsStat',type:'2', callbackNm:'fn_ajaxCodeListCallback', sqlQueryId:'sysCrimeCodeDAO.querySysCrimeGrpCodeMList'}];
	gfn_init({startFnNm:'', param:codeInfo, codeSet:'Y'});

	OR

	gfn_init({startFnNm:'', param:{targetFormId:"searchForm", callbackNm:"fn_bbsFreeSearch"}, codeSet:"N"});

});
	
 * @param
 * @returns
 */
gfn_init = function (args){
	var startFnNm = (args.startFnNm == undefined || args.startFnNm == "") ? "requestUtil.getSearchForm" : args.startFnNm;
	
	if(args.codeSet != undefined && args.codeSet != ""){
		if(args.codeSet == "Y" && (args.startFnNm == undefined || args.startFnNm == "")){
			startFnNm = "fn_ajaxCodeList";
		}
	}
	
	if(args.param == undefined || args.param == ""){
		eval(startFnNm + '()');
	}else{
		eval(startFnNm + '(args.param)');
	}
}