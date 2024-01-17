<%--
/**
 * <pre>
 * 1. 작성일 : 2021. 7. 20. 오전 10:11:33
 * 2. 작성자 : ilyong
 * 3. 화면명 : 장비 조회 팝업
 * 4. 설명 : 장비 조회 팝업
 * </pre>
 */
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 
<title>Jenix 통합관리 시스템</title>

<script type="text/javaScript" language="javascript" defer="defer">

$(document).ready(function() {
	tabId = parent.$('li[id*=li_tabs-M][aria-expanded=true]').attr('aria-controls');
	var codeInfo2 = [{cdId:'C05',selectId:'schEqpTypPop',type:'1', callbackNm:'fn_ajaxSchEqpTypPopCallback', sqlQueryId:''}];
	fn_ajaxCodeList(codeInfo2);	
	fn_searchEqpMListPop(1);
	
	<%/* 전체 checkbox 클릭시 */%>
    $('#chkAll').change(function() {
        var chk = $(this).is(':checked');
        if(chk){
               $('input:checkbox[id=popChk]').each(function() {
                   $(this).prop("checked", true);         
               });
        }else{
               $('input:checkbox[id=popChk]').each(function() {
                   $(this).prop("checked", false);         
               });
        }
    });
});


function fn_ajaxSchEqpTypPopCallback(data){
 	$('#schEqpTypPop option:eq(0)').before("<option value='' selected>전체</option>");
}

/**
 * @!@ 프로그램 관리 리스트 조회
 * @param {int} page
 * @returns 
 */
function fn_searchEqpMListPop(page){
	var popGubun = "pop";
	var callUrl = "<c:url value='/eqp/mgmt/queryEqpMgmtFIndEqupQListPop.do'/>";
	
	requestUtil.searchList({callUrl:callUrl, srhFormNm:'searchPopForm', callbackNm:'fn_searchEqpMListPopCallback', page:page, perPageNum:10});
	
}

/**
 * @!@ 프로그램 관리 리스트 조회 콜백
 * @param {json} data
 * @returns 
 */
function fn_searchEqpMListPopCallback(data){
	
	var list = data.list;
	var listCnt = list.length;
	var tabTdCnt = $("#listMgmtFind > colgroup").find("col").length;
	
	$("#listMgmtFind > tbody").empty();
	$("#totalcnt").text(data.totalCount);
	
	if(listCnt == 0){
		var append = "";
		append += "<tr>";
		
		append += "<td colspan='"+tabTdCnt+"'>"+nullListMsg+"</td>";
		
		append += "</tr>";
		$("#listMgmtFind > tbody").append(append);
	}else{
		$.each(list,function(idx,row){
			var append = "";
			append += "<tr>";
			
	 		append += '<td>';
 	 		append +=  row.rnum;	<%/* 순번 */%>
 	 		append += '</td>';
	 		append += '<td style=\"text-align:center\">';
 	 		append +=  "<input type=\"checkbox\" name=\"popChk\" id=\"popChk\" value=\"Y\" class=\"check_agree1\">";	<%/* 선택 */%>
 	 		append += '</td>';
 	 		append += '<td>';
 	 		append +=  gfn_nullRtnSpace(row.eqpSno);	<%/* 장비명 */%>
 	 		append +=  "<input type=\"hidden\" name=\"popEqpSno\"  value=\""+gfn_nullRtnSpace(row.eqpSno)+"\" maxlength=\"50\" class=\"inpw70\"/>";	<%/* 장비일련번호 */%>
 	 		append +=  "<input type=\"hidden\" name=\"popEqpNm\"  value=\""+gfn_nullRtnSpace(row.eqpNm)+"\" maxlength=\"50\" class=\"inpw70\"/>";	<%/* 장비명 */%>
 	 		append += '</td>';
 	 		append += '<td>';
 	 		append +=  gfn_nullRtnSpace(row.eqpTypNm);
 	 		append +=  "<input type=\"hidden\" name=\"popEqpTypNm\"  value=\""+gfn_nullRtnSpace(row.eqpTypNm)+"\" maxlength=\"50\" class=\"inpw70\"/>";	<%/* 장비유형 */%>
 	 		append += '</td>';
 	 		append += '<td>';
 	 		append +=  gfn_nullRtnSpace(row.srNo);
 	 		append +=  "<input type=\"hidden\" name=\"popSrNo\"  value=\""+gfn_nullRtnSpace(row.srNo)+"\" maxlength=\"50\" class=\"inpw70\"/>";	<%/* S/N */%>
 	 		append += '</td>';
 	 		append += '<td>';
 	 		append +=  gfn_nullRtnSpace(row.mdlNm);
 	 		append +=  "<input type=\"hidden\" name=\"popMdlNm\"  value=\""+gfn_nullRtnSpace(row.mdlNm)+"\" maxlength=\"50\" class=\"inpw70\"/>";	<%/* 모델명 */%>
 	 		append += '</td>';
 	 		append += '<td>';
 	 		append +=  gfn_nullRtnSpace(row.mnftCo);
 	 		append +=  "<input type=\"hidden\" name=\"popMnftCo\"  value=\""+gfn_nullRtnSpace(row.mnftCo)+"\" maxlength=\"50\" class=\"inpw70\"/>";	<%/* 제조사 */%>
 	 		append += '</td>';
 	 		append += '<td>';
 	 		append +=  gfn_dashDate2(gfn_nullRtnSpace(row.purcDt),'-')
 	 		append +=  "<input type=\"hidden\" name=\"popPurcDt\"  value=\""+gfn_nullRtnSpace(row.purcDt)+"\" maxlength=\"50\" class=\"inpw70\"/>";	<%/* 도입일자 */%>
 	 		append += '</td>';
 	 		
	
			append += "</tr>";
	        $("#listMgmtFind > tbody").append(append);
	 	});
	}
	
	
	data.__callFuncName__ ="fn_searchEqpMListPop";
	data.__naviID__ ="page_navi";
	pageUtil.setPageNavi(data);
	
	
}

 /**
  * 장비 선택
  * @param
  * @returns 
  */
 function fn_setEqpSno(eqpSno, eqpNm){

	 $("#eqpSno").val(eqpSno);
	 $("#eqpNm").val(eqpNm);
	 fn_dialogClose('eqpMgmtFIndEqupQListPop');
 	
 }
 
 function fn_selMgmt(){
	 //alert("aaaaaaaaaaa");
	 //listMgmtFind
	 var chkCnt = 0;
	 $('#listMgmtFind :input[name=popChk]').each(function(index) {
		//console.log("====fn_selMgmt	index========>>>"+index);
		 if($("input:checkbox[name=popChk]").eq(index).is(":checked")==true){
//  			 chkCnt++;
			if(!fn_dupMgmtChk($("input[name^=popEqpSno]").eq(index).val(), $("input[name^=popEqpNm]").eq(index).val())) return; //장비 추가 중복 체크
			console.log("====fn_selMgmt	index222========>>>"+index);	
			 var append = "";
				append += "<tr>";
				
//	 			<th scope="col">순번</th>
//	             <th scope="col">선택</th>
//	             <th scope="col">장비명</th>
//	             <th scope="col">장비유형</th>
//	             <th scope="col">S/N</th>
//	             <th scope="col">모델명</th>
//	             <th scope="col">제조사</th>
//	             <th scope="col">도입일자</th>
				
//	 			append += "<td>" + row.rnum + "</td>";
//	 			append += '<td style="text-align:center"><input type="checkbox" name="chk" id="chk" value="Y" class="check_agree1"></td>';
//	 			append += "<td><a href='javascript:void(0)' onclick=javascript:fn_searchDetail('"+row.eqpSno+"')><u>"+row.eqpNm+"</u></a></td>";
//	 			append += "<td>" + gfn_nullRtnSpace(row.eqpTypNm) + "</td>";
//	 	 		append += "<td>" + gfn_nullRtnSpace(row.srNo) + "</td>";
//	 	 		append += "<td>" + gfn_nullRtnSpace(row.mdlNm) + "</td>";
//	 	 		append += "<td>" + gfn_nullRtnSpace(row.mnftCo) + "</td>";
//	  	 		append += "<td>" + gfn_dashDate2(gfn_nullRtnSpace(row.purcDt),'-') + "</td>"; popEqpSno
				
				
	 			append += '<td style=\"text-align:center\">';
		 		append +=  "<input type=\"checkbox\" name=\"chk\" id=\"chk\" value=\"Y\" class=\"check_agree1\" checked>";	<%/* 선택 */%>
		 		append += '</td>';
		 		
		 		append += '<td style=\"text-align:center\">';
	 	 		append +=  $("input[name^=popEqpSno]").eq(index).val();	<%/* 장비명 */%>
	 	 		append +=  "<input type=\"hidden\" name=\"eqpSno\"  value=\""+ $("input[name^=popEqpSno]").eq(index).val()+"\" maxlength=\"50\" class=\"inpw70\"/>";	<%/* 장비일련번호 */%>
	 	 		append += '</td>';
	 	 		
	 	 		append += '<td style=\"text-align:center\">';
	 	 		append +=  $("input[name^=popEqpTypNm]").eq(index).val();	<%/* 장비유형 */%>
	 	 		append += '</td>';
	 	 		
	 	 		append += '<td style=\"text-align:center\">';
	 	 		append +=  $("input[name^=popSrNo]").eq(index).val();	<%/* S/N */%>
	 	 		append += '</td>';
	 	 		
	 	 		append += '<td style=\"text-align:center\">';
	 	 		append +=  $("input[name^=popMdlNm]").eq(index).val();	<%/* 모델명 */%>
	 	 		append += '</td>';
	 	 		
	 	 		append += '<td style=\"text-align:center\">';
	 	 		append +=  $("input[name^=popMnftCo]").eq(index).val();	<%/* 제조사 */%>
	 	 		append += '</td>';
	 	 		
	 	 		append += '<td style=\"text-align:center\">';
	 	 		append +=  gfn_dashDate2(gfn_nullRtnSpace($("input[name^=popPurcDt]").eq(index).val()),'-');	<%/* 도입일자 */%>
	 	 		append += '</td>';
	 	 		
		
				append += "</tr>";
		        $("#tbLendDtl > tbody").append(append);	
		 }
	 });
//  	 console.log("====fn_selMgmt	chkCnt========>>>"+chkCnt);
	 fn_dialogClose("eqpMgmtFIndEqupQListPop2");
 }

function fn_selMgmt2(){ 
	//alert("zzzzzzzzzzzzzzzzzzz");
	var arr = new Array();
	var chkCnt = 0;
    var i =0;
    var checkNo="";
    var checkNO2="";
    
    $('#listMgmtFind :input[name=popChk]').each(function(index) {
		//console.log("====fn_selMgmt	index========>>>"+index);
		 if($("input:checkbox[name=popChk]").eq(index).is(":checked")==true){
			 var retVal  = new Object();
			 retVal.popEqpSno       = $("input[name^=popEqpSno]").eq(index).val();	<%/* 장비일련번호 */%>
             retVal.popEqpNm        = $("input[name^=popEqpSno]").eq(index).val();	<%/* 장비명 */%>
             retVal.popEqpTypNm     = $("input[name^=popEqpTypNm]").eq(index).val();	<%/* 장비유형 */%>	 
             retVal.popMnftCo       = $("input[name^=popMnftCo]").eq(index).val(); 	<%/* 제조사 */%>
             retVal.popMdlNm        = $("input[name^=popMdlNm]").eq(index).val(); 	<%/* 모델명 */%>
             retVal.popSrNo         = $("input[name^=popSrNo]").eq(index).val();	<%/* S/N */%> 
             retVal.popPurcDt       = $("input[name^=popPurcDt]").eq(index).val(); 	<%/* 도입일자 */%>
             retVal.popHldPlc       = $("input[name^=popHldPlc]").eq(index).val(); 	<%/* 보유장소 */%>
             
             arr[i] = retVal;
             ++i;
		 }
    });	
    
    if(i<1){
    	fn_showUserPage("장비를 선택하세요.", function() {
			
		});
		return;
    }
    
    fn_setItem(arr);
}


function fn_setItem(arrObj){
	var size = arrObj.length;
	var paramValue1="";
	var paramValue2="";
	var compareEqpSno = "";
	var	dupEqpNm = "";
	
	for(var i=0; i<size; i++){
        var retVal = arrObj[i];
        var rowIndex = retVal.rowIndex;
        if(i > 0){
            rowIndex = 0;
        }

        compareEqpSno = retVal.popEqpSno;
        //console.log("======paramValue1======>>>"+paramValue1+"======paramValue2======>>>"+paramValue2);
        
        var dupCnt=0;
        
        $('#tbLendDtl :input[name=chk]').each(function(index) {
    		if($("input[name^=eqpSno]").eq(index).val() == compareEqpSno){
     			dupEqpNm += "(장비명: "+retVal.popEqpNm+")\n";
     			dupCnt++;	
    	 	}	
    	});
        
        if(dupCnt < 1){
        	var append = "";
			append += "<tr>";
				
 			append += '<td style=\"text-align:center\">';
	 		append +=  "<input type=\"checkbox\" name=\"chk\" id=\"chk\" value=\"Y\" class=\"check_agree1\" checked>";	<%/* 선택 */%>
	 		append += '</td>';
	 		
	 		append += '<td style=\"text-align:center\">';
 	 		append +=  retVal.popEqpNm;	<%/* 장비명 */%>
 	 		append +=  "<input type=\"hidden\" name=\"eqpSno\"  value=\""+ retVal.popEqpSno+"\" maxlength=\"50\" class=\"inpw70\"/>";	<%/* 장비일련번호 */%>
 	 		append += '</td>';
 	 		
 	 		append += '<td style=\"text-align:center\">';
 	 		append +=  retVal.popEqpTypNm;	<%/* 장비유형 */%>
 	 		append += '</td>';
 	 		
 	 		append += '<td style=\"text-align:center\">';
 	 		append +=  retVal.popMnftCo;	<%/* 제조사 */%>
 	 		append += '</td>';
 	 		
 	 		append += '<td style=\"text-align:center\">';
 	 		append +=  retVal.popMdlNm;	<%/* 모델명 */%>
 	 		append += '</td>';
 	 		
 	 		append += '<td style=\"text-align:center\">';
 	 		append +=  retVal.popSrNo;	<%/* S/N */%>
 	 		append += '</td>';
 	 		
 	 		append += '<td style=\"text-align:center\">';
 	 		append +=  gfn_dashDate2(gfn_nullRtnSpace(retVal.popPurcDt),'-');	<%/* 도입일자 */%>
 	 		append += '</td>';
 	 		
 	 		append += '<td style=\"text-align:center\">';
 	 		append +=  gfn_nullRtnSpace(retVal.popHldPlc);	<%/* 보유장소 */%>
 	 		append += '</td>';
 	 		
	
			append += "</tr>";
	        $("#tbLendDtl > tbody").append(append);	
        }
        
	}    
	if(!gfn_isNull(dupEqpNm)){
   	 	fn_showUserPage("이미 추가된 장비는 선택할 수 없습니다.\n "+dupEqpNm);
    }
	fn_dialogClose("eqpMgmtFIndEqupQListPop");
	

}

function fn_dupMgmtChk(eqpSno, eqpNm){
	//debugger;
	var dupCnt = 0;
	var message = "";
	var pEqpSno = eqpSno;
	
	$('#tbLendDtl :input[name=chk]').each(function(index) {
		if($("input[name^=eqpSno]").eq(index).val() == pEqpSno){
 			dupCnt++;	
 			message += "(장비명: "+eqpNm+")\n";
	 	}	
	});
	

}
 
function fn_dialogClose(divId){
	 $("#"+divId).dialog( "close" );
     $("#"+divId).empty();
}
</script>

</head>
<body>
<div id="con_wrap_pop">
	<div class="contents">
               <div id="contents_info">
                      <!--- contnets  적용 ------>
                      <div class="window_popup">
                          <div class="sub_ttl">장비 찾기</div>
                         
                          <div class="sub">
                             <!--------------검색------------------>
                             <form name="searchPopForm" id="searchPopForm" method="post">
                             <input type="hidden" id="srcUseYn" name="srcUseYn" value="Y"/>
                            <div class="t_head">
                                  <table class="tbl_type_hd" border="1" cellspacing="0" onkeydown="">
                                        <caption>검색</caption>
                                         <colgroup>
                                            <col width="20%">
                                            <col width="30%">
                                            <col width="20%">
                                            <col width="30%">
                                         </colgroup>
                                   <thead>
                                      <tr>
                                           <th scope="row" class="hcolor">장비번호</th>
								           <td>
								               <input type="text" id="schEqpNmPop" name="schEqpNmPop" title="장비번호" style="width:220px;" maxlength="100"/>
								           </td>
								           <th scope="row" class="hcolor">장비유형</th>
								           <td colspan="3">
								           		<select class="selw10" id="schEqpTypPop" name="schEqpTypPop" onchange=""  >
					                			</select> 
								           </td>
                                       </tr>
                                   </thead>
                                </table>
                              </div>
                            <div class="btn_c">
                                  <ul>
                                  	 <li><a href='javascript:fn_selMgmt2();' class="gyButton">선택확인</a></li>
                                  	 <li><a href="javascript:fn_searchEqpMListPop(1);" class="gyButton">조회</a></li>
                                  </ul>
                               </div>
                               </form>
                            <!--------------//검색------------------>
                            
                            <!--------------결과------------------>
                             <div class="r_num">| 결과  <strong id="totalcnt" style="color:#C00"></strong>건</div>
                             
                             <!--------------목록---------------------->
                             <div class="t_list">  
                                  <table id="listMgmtFind" class="tbl_type" border="1" cellspacing="0" >
                                        <caption>목록</caption>
                                          <colgroup>
                                              <col width="5%">
                                              <col width="5%">
                                              <col />
                                              <col width="11%">
                                              <col width="11%">
                                              <col width="11%">
                                              <col width="12%">
                                              <col width="12%">
<%--                                               <col width="12%"> --%>
                                           </colgroup>
                                            <thead>
                                              <tr>
                                                 <th scope="col">순번</th>
                                                 <th scope="col"><input type="checkbox" name="chkAll" id="chkAll" ></th>
                                                 <th scope="col">장비번호</th>
                                                 <th scope="col">장비유형</th>
                                                 <th scope="col">S/N</th>
                                                 <th scope="col">모델명</th>
                                                 <th scope="col">제조사</th>
                                                 <th scope="col">도입일자</th>
<!--                                                  <th scope="col">선택</th> -->
                                              </tr>
                                            </thead>
                                            <tbody>
												<tr><td colspan="8">조회 결과가 없습니다.</td></tr>
                                          </tbody>
                                     </table>
                             </div>
                              <!--------------//목록---------------------->
                             
                             <!-----------------------페이징----------------------->
                             <div id="page_navi" class="page_wrap"></div>
                               <!-----------------------//페이징----------------------->
                          		
<!--                           		<p></p> -->
<%--                           		<p><center><button class="button80" onclick="javascirpt:fn_selMgmt2();return false;">선택적용</button></center></p> --%>
                          		<div class="btn_c">
			                      <ul>
<!-- 			                        <li><button class="button60" onclick="javascirpt:fn_selMgmt();return false;">선택적용</button></li> -->
			                        <li><a href="#" class="gyButton" onclick="fn_dialogClose('eqpMgmtFIndEqupQListPop');return false;">닫기</a></li>
			                        <!-- <li><a href="#" class="myButton" onclick="fn_indexFsysProgramMList();return false;">목록</a></li> button60-->
			                      </ul>
			                    </div>
                          </div>
                         
                    </div>
               </div>
                 <!---  //contnets  적용 ------>
       </div>
  </div>
</body>
</html>