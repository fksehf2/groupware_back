var frame =frame||{};
validUtil = frame.validUtil||{};




validUtil.checkInputValid = function (args)
{
	
	
	var valFormID ='';

	if(typeof args != "undefined" ) {
		if(typeof args.valFormID != "undefined" ){
			
			valFormID = "#"+args.valFormID;
		}
	}
	
	var isValid = true;

	var periodArray = new Array();
	var stdDateId = "";
	var endDateId = "";
	var fieldNm = "";

	// 붉은색표시 초기화
	$(valFormID+ " :input").each(function(){
		$(this).css("border-color", "#e2e2e2");
	});

	$(valFormID+ " :input").each(function(){
		
		//alert('aaa');
		//조회화면에서 호출했을경우 pNotSearch == null
		/*
		if(pNotSearch == null){
        // 조회조건 글자길이 체크
			if(this.type != "textarea" && this.type != "hidden" && this.value.length > 150 && this.disabled == false)
			{
				var tempThis = $(this);
				
				fn_showUserPage( "길이초과", "조회조건은 150글자를 초과 할 수 없습니다.", function() {
					
					tempThis.focus();
					//tempThis.css("border-color", "red");
				});
				
				isValid = false;
				return false;
			}
		}
		*/

		// 필수체크 설정여부
		var requireNm = $(this).attr("data-requireNm");
		
		if(requireNm != undefined && requireNm != null)
		{
			//alert();
			if($.trim(this.value) == "")
			{
				var tempThis = $(this);
				
				fn_showUserPage( requireNm + "은(는) 필수 입력 항목입니다.", function() {
					
					tempThis.focus();
					//tempThis.css("border-color", "red");
				});
				
				
				//tempThis.focus();
				
				isValid = false;
				return false;
			}
		}
		
		// input 맥스 랭스 체크
		var maxLength = $(this).attr("data-maxLength");
		if(maxLength != undefined && maxLength != null)
		{
			var checkByte = 0;
	        var subStrLength = 0;
	
	        var hangul = maxLength / 2;
	        var english = maxLength;
	
	        var str = $(this).val();
	        var tmpStr = "";
	
	        for(var i = 0; i < str.length; i++){
	        	var oneChar = str.charAt(i);
	
	            /*if(escape(oneChar).length > 4){
	                checkByte += 3;
	            }else {
	            	if(escape(oneChar) == "%0A"){ //ibatis에서 엔터코드변환으로 2byte 적용
	            		checkByte += 2;
	            	}else {
	            		checkByte++;
	            	}
	            }*/
	        	// mariadb varchar(20)은 자릿수로 계산
            	if(escape(oneChar) == "%0A"){ //ibatis에서 엔터코드변환으로 2byte 적용
            		checkByte += 2;
            	}else {
            		checkByte++;
            	}
	
	            if(checkByte <= maxLength){
	                subStrLength = i + 1;
	            }else {
	                break;
	            }
	
	            tmpStr += oneChar;
	        }
	        
	        if(checkByte > maxLength){
	        	var tempThis = $(this);
	        	//fn_showUserPage( $(this).prop("title") + "은(는) "+ maxLength + "byte를 넘을 수 없습니다.", function() {
	        	fn_showUserPage( $(this).prop("title") + "은(는) "+ maxLength + "자를 넘을 수 없습니다.", function() {
	        		tempThis.val(tmpStr);
	        		tempThis.focus();
					//tempThis.css("border-color", "red");
				});
	        	isValid = false;
				return false;
	            /*alert(maxLength+"byte를 넘을 수 없습니다.");
	            $("#"+objName).removeClass("col_red");
	            $("#"+objName).val(tmpStr);
	
	            if($("#"+objName).prop("tagName").toLowerCase() == "textarea"){
	                var length = gfn_checkByte($("#"+objName).val());
	                $("#"+objName+"ByteChk").html("("+length+"/"+maxLength+")");
	            }*/
	        }
		}
		
		// 날짜기간 체크
		var tempObj = new Object();
		var stdDateCheck = $(this).attr("data-stdDateCheck");
		if(stdDateCheck != undefined && stdDateCheck != null)
		{
			tempObj.fieldNm = stdDateCheck;
			tempObj.stdDateId = this.id;
			periodArray.push(tempObj);
		}
		var endDateCheck = $(this).attr("data-endDateCheck");
		if(endDateCheck != undefined && endDateCheck != null)
		{
			tempObj.fieldNm = endDateCheck;
			tempObj.endDateId = this.id;
			periodArray.push(tempObj);
		}
	});

	if(isValid)
	{
		// 날짜기간 체크 있을때
		if(periodArray.length > 0)
		{
			mainLoop : for(var i=0; i<periodArray.length; i++)
			{
				for(var j=0; j<i; j++)
				{
					if(periodArray[i].fieldNm == periodArray[j].fieldNm)
					{
						stdDateId = periodArray[i].stdDateId == undefined ? periodArray[j].stdDateId : periodArray[i].stdDateId;
						endDateId = periodArray[i].endDateId == undefined ? periodArray[j].endDateId : periodArray[i].endDateId;
						isValid = validUtil.checkPeriodDate(stdDateId, endDateId, periodArray[i].fieldNm);

						if(!isValid) break mainLoop;
					}
				}
			}
		}
	}
	return isValid;
}


/*
 * 기간 DatePicker 날짜 범위 체크
 * stdDateId : 시작일 id
 * endDateId : 종료일 id
 * dateRange : 날짜 범위 int (기본 7일전)
 * */
validUtil.checkPeriodDate = function (stdDateId, endDateId, fieldNm, dateRange)
{
	var isValid = true;
	var stdDate = $("#" + stdDateId).val().replace(/\-/g,'');
	var endDate = $("#" + endDateId).val().replace(/\-/g,'');

	var stdDay = new Date();
	var endDay = new Date();

	if(stdDate != "" && endDate != "")
	{
		var yyyy = stdDate.substr(0,4);
	    var mm = stdDate.substr(4,2);
	    var dd = stdDate.substr(6,2);

	    stdDay = new Date(yyyy, mm-1, dd);
	}
	if(endDate != "")
	{
		var yyyy = endDate.substr(0,4);
		var mm = endDate.substr(4,2);
		var dd = endDate.substr(6,2);

		endDay = new Date(yyyy, mm-1, dd);
	}

	if(stdDay > endDay)
	{
		LayerPopup.alert("날짜기간", fieldNm + " 종료일은 시작일보다 빠를 수 없습니다.");
		isValid = false;
	}

	if(isValid)
	{
		// 기본날짜로 셋팅됐는지
		var isDefaultRange = false;
		// 날짜범위 셋팅
		if(dateRange == "" || dateRange == null || dateRange == undefined)
		{
			dateRange = __DEFAULT_DATE_RANGE_;
			isDefaultRange = true;
		}
		// 필드명 셋팅
		if(fieldNm == "" || fieldNm == null || fieldNm == undefined)
		{
			fieldNm = "날짜기간";
		}

		var diffDays = Math.abs(endDay.getTime() - stdDay.getTime());
		diffDays = Math.ceil(diffDays / (1000 * 3600 * 24));

		if(diffDays >= dateRange)
		{
			if(isDefaultRange)
			{
				LayerPopup.alert("날짜기간", fieldNm + "은(는) 한달 이내만 설정 가능합니다.");
			}
			else
			{
				LayerPopup.alert("날짜기간", fieldNm + "은(는) " + dateRange + "일 이내만 설정 가능합니다.");
			}
			isValid = false;
		}
	}
	return isValid;
}