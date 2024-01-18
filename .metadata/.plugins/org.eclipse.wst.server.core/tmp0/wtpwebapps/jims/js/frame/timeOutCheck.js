var sessionTime; // 세션 로그아웃 시간
var iSecond; //초단위로 환산
var timerchecker = null;

window.onload = function() {
    //fncClearTime();
    //initTimer();
}
 
function fncClearTime() {
    /*var timer = document.getElementById("timer");
    if(!timer){
    	timer = parent.document.getElementById("timer");
    }
    timer.style.color = "white";
    
    //사용시간 초기화
    iSecond = sessionTime
	return iSecond;*/
}
 
Lpad = function(str, len) {
    str = str + "";
    while (str.length < len) {
        str = "0" + str;
    }
    return str;
}
 
initTimer = function() {
    var timer = document.getElementById("timer");
    if(!timer){
    	timer = parent.document.getElementById("timer");
    }
    rHour = parseInt(iSecond / 3600);
    rHour = rHour % 60;
 
    rMinute = parseInt(iSecond / 60);
    rMinute = rMinute % 60;
 
    rSecond = iSecond % 60;
 
    if (iSecond > 0) {
        timer.innerHTML = "&nbsp;" + Lpad(rHour, 2) + "시간 " + Lpad(rMinute, 2)
                + "분 " + Lpad(rSecond, 2) + "초 ";
        iSecond--;
        
        timerchecker = setTimeout("initTimer()", 1000); // 1초 간격으로 체크
        
        //시간이 10분 이하로 떨어지면 폰트컬러를 레드로 변경
        if(iSecond == 599){
        	timer.style.color = "red";
        	
			/*if(confirm("시간을 연장하시겠습니까?")){
				refreshTimer();
			}*/
        }
    } else {
    	logoutUser();
    }
}
 
function refreshTimer() {
    var xhr = initAjax();
    xhr.open("POST", "/jims/com/loginTimeReset.do", false);
    xhr.send();
    fncClearTime();
}

function modInsttCd(arg) {
    var xhr = initAjax();
    var formData = new FormData();
    formData.append('insttCd', arg.value);
    xhr.open("POST", "/jims/com/modInsttCd.do", false);
    xhr.send(formData);    
}
 
function logoutUser() {
    clearTimeout(timerchecker);
    var xhr = initAjax();
    xhr.open("POST", "/jims/flyt/login/procFLytLoginSessoutPDtl.do", false);
    xhr.send();
    location.reload();
}
 
function initAjax() { // 브라우저에 따른 AjaxObject 인스턴스 분기 처리
    var xmlhttp;
    if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    } else {// code for IE6, IE5
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    return xmlhttp;
}
