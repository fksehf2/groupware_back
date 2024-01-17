<%--
/**
 * <pre>
 * 1. 작성일 : 2021. 4. 14. 오전 10:11:33
 * 2. 작성자 : ilyong
 * 3. 화면명 : 부서 조회 팝업
 * 4. 설명 : 부서 조회 팝업
 * </pre>
 */
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<script type="text/javaScript" language="javascript" defer="defer">

$(document).ready(function() {
	tabId = parent.$('li[id*=li_tabs-M][aria-expanded=true]').attr('aria-controls');
	var codeInfo2 = [{cdId:'C14',selectId:'schEqpTyp',type:'1', callbackNm:'fn_ajaxSchEqpTypCallback', sqlQueryId:''}];
	fn_ajaxCodeList(codeInfo2);	
	fn_searchDeptMList(1);
	
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


/* function fn_ajaxSchEqpTypCallback(data){
 	 $('#schEqpTyp').prepend("<option value='' selected>전체</option>"); 
 	
} */
/**
 * @!@ 프로그램 관리 리스트 조회
 * @param {int} page
 * @returns 
 */
function fn_searchDeptMList(page){
	var popGubun = "pop";
	var callUrl = "<c:url value='/fsys/user/queryDeptFIndListPop.do'/>?popGubun="+popGubun;
	
	requestUtil.searchList({callUrl:callUrl, srhFormNm:'searchForm', callbackNm:'fn_queryDeptFIndListPopCallback', page:page, perPageNum:10});
	
}

/**
 * @!@ 프로그램 관리 리스트 조회 콜백
 * @param {json} data
 * @returns 
 */
function fn_queryDeptFIndListPopCallback(data){
	
	var list = data.list;
	var listCnt = list.length;
	var tabTdCnt = $("#listTab > colgroup").find("col").length;
	
	$("#deptFindList > tbody").empty();
	$("#totalcnt").text(data.totalCount);
	
	if(listCnt == 0){
		var append = "";
		append += "<tr>";
		
		append += "<td colspan='"+tabTdCnt+"'>"+nullListMsg+"</td>";
		
		append += "</tr>";
		$("#deptFindList > tbody").append(append);
	}else{
		$.each(list,function(idx,row){
			var append = "";
			append += "<tr>";
			
//				<th scope="col">순번</th>
//          <th scope="col">부서코드</th>
//          <th scope="col">기관명</th>
//          <th scope="col">과명</th>
//          <th scope="col">팀명</th>
//          <th scope="col">부서레벨</th>
//          <th scope="col">사용여부</th>
//          <th scope="col">선택</th>
			
// 	 		append += '<td>';
//  	 		append +=  row.rnum;	
//  	 		append += '</td>';
 	 		append += '<td>';
 	 		append +=  "<a href=\"javascript:void(0)\" onclick=javascript:fn_searchDetail('"+row.insttCd+"')><u>"+row.insttCd+"</u></a>";	<%/* 장비명 */%>
 	 		append +=  "<input type=\"hidden\" name=\"popInsttCd\"  value=\""+gfn_nullRtnSpace(row.insttCd)+"\" maxlength=\"50\" class=\"inpw70\"/>";	<%/* 장비일련번호 */%>
 	 		append +=  "<input type=\"hidden\" name=\"popDeptNm\"  value=\""+gfn_nullRtnSpace(row.deptNm)+"\" maxlength=\"50\" class=\"inpw70\"/>";	<%/* 장비명 */%>
 	 		append +=  "<input type=\"hidden\" name=\"popTeamNm\"  value=\""+gfn_nullRtnSpace(row.teamNm)+"\" maxlength=\"50\" class=\"inpw70\"/>";	<%/* 장비명 */%>
 	 		append += '</td>';
 	 		append += '<td>';
 	 		append +=  gfn_nullRtnSpace(row.insttNm);
 	 		append += '</td>';
 	 		append += '<td>';
 	 		append +=  gfn_nullRtnSpace(row.deptNm);
 	 		append += '</td>';
 	 		append += '<td>';
 	 		append +=  gfn_nullRtnSpace(row.teamNm);
 	 		append += '</td>';
 	 		append += '<td>';
 	 		append +=  gfn_nullRtnSpace(row.deptLvl);
 	 		append += '</td>';
 	 		append += '<td>';
 	 		append +=  gfn_nullRtnSpace(row.useYnNm);
 	 		append += '</td>';
 	 		append += "<td><a href='javascript:fn_setFsysDept(\""+row.insttCd+"\", \""+row.deptNm+"\", \""+row.teamNm+"\");' class='byButton'>선택</a></td>";
 	 		
	
			append += "</tr>";
	        $("#deptFindList > tbody").append(append);
	 	});
	}
	
	
	data.__callFuncName__ ="fn_searchDeptMList";
	data.__naviID__ ="page_navi";
	pageUtil.setPageNavi(data);
	
	
}

 /**
  * 장비 선택
  * @param
  * @returns 
  */
 function fn_setFsysDept(insttCd, deptNm, teamNm){

	 
	 fn_dialogClose('fsysDeptFIndListPop');
	 fn_deptFindPopCallBack(insttCd, decodeURIComponent(deptNm), decodeURIComponent(teamNm));
 }
 
 function fn_selMgmt(){
	 //alert("aaaaaaaaaaa");
	 //deptFindList
	 var chkCnt = 0;
	 $('#deptFindList :input[name=popChk]').each(function(index) {
		//console.log("====fn_selMgmt	index========>>>"+index);
		 if($("input:checkbox[name=popChk]").eq(index).is(":checked")==true){
//  			 chkCnt++;
			if(!fn_dupMgmtChk($("input[name^=popEqpSno]").eq(index).val(), $("input[name^=popEqpNm]").eq(index).val())) return; //장비 추가 중복 체크
			console.log("====fn_selMgmt	index222========>>>"+index);	
			 var append = "";
				append += "<tr>";
				
// 				<th scope="col">순번</th>
//                 <th scope="col">부서코드</th>
//                 <th scope="col">기관명</th>
//                 <th scope="col">과명</th>
//                 <th scope="col">팀명</th>
//                 <th scope="col">부서레벨</th>
//                 <th scope="col">사용여부</th>
//                 <th scope="col">선택</th>
				
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
	 	 		append +=  $("input[name^=popEqpNm]").eq(index).val();	<%/* 장비명 */%>
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

	var arr = new Array();
	var chkCnt = 0;
    var i =0;
    var checkNo="";
    var checkNO2="";
    
    $('#deptFindList :input[name=popChk]').each(function(index) {
		//console.log("====fn_selMgmt	index========>>>"+index);
		 if($("input:checkbox[name=popChk]").eq(index).is(":checked")==true){
			 var retVal  = new Object();
			 retVal.popEqpSno       = $("input[name^=popEqpSno]").eq(index).val();	<%/* 장비일련번호 */%>
             retVal.popEqpNm        = $("input[name^=popEqpNm]").eq(index).val();	<%/* 장비명 */%>
             retVal.popEqpTypNm     = $("input[name^=popEqpTypNm]").eq(index).val();	<%/* 장비유형 */%>	 
             retVal.popSrNo         = $("input[name^=popSrNo]").eq(index).val();	<%/* S/N */%> 
             retVal.popMdlNm        = $("input[name^=popMdlNm]").eq(index).val(); 	<%/* 모델명 */%>
             retVal.popMnftCo       = $("input[name^=popMnftCo]").eq(index).val(); 	<%/* 제조사 */%>
             retVal.popPurcDt       = $("input[name^=popPurcDt]").eq(index).val(); 	<%/* 도입일자 */%>
             
             arr[i] = retVal;
             ++i;
		 }
    });	
    
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
				
// 			<th scope="col">순번</th>
//             <th scope="col">부서코드</th>
//             <th scope="col">기관명</th>
//             <th scope="col">과명</th>
//             <th scope="col">팀명</th>
//             <th scope="col">부서레벨</th>
//             <th scope="col">사용여부</th>
//             <th scope="col">선택</th>
				
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
 	 		append +=  retVal.popEqpNm;	<%/* 장비명 */%>
 	 		append +=  "<input type=\"hidden\" name=\"eqpSno\"  value=\""+ retVal.popEqpSno+"\" maxlength=\"50\" class=\"inpw70\"/>";	<%/* 장비일련번호 */%>
 	 		append += '</td>';
 	 		
 	 		append += '<td style=\"text-align:center\">';
 	 		append +=  retVal.popEqpTypNm;	<%/* 장비유형 */%>
 	 		append += '</td>';
 	 		
 	 		append += '<td style=\"text-align:center\">';
 	 		append +=  retVal.popSrNo;	<%/* S/N */%>
 	 		append += '</td>';
 	 		
 	 		append += '<td style=\"text-align:center\">';
 	 		append +=  retVal.popMdlNm;	<%/* 모델명 */%>
 	 		append += '</td>';
 	 		
 	 		append += '<td style=\"text-align:center\">';
 	 		append +=  retVal.popMnftCo;	<%/* 제조사 */%>
 	 		append += '</td>';
 	 		
 	 		append += '<td style=\"text-align:center\">';
 	 		append +=  gfn_dashDate2(gfn_nullRtnSpace(retVal.popPurcDt),'-');	<%/* 도입일자 */%>
 	 		append += '</td>';
 	 		
	
			append += "</tr>";
	        $("#tbLendDtl > tbody").append(append);	
        }
        
        
        
	}    
	if(!gfn_isNull(dupEqpNm)){
   	 	fn_showUserPage("이미 추가된 장비는 선택할 수 없습니다.\n "+dupEqpNm);
    }
	fn_dialogClose("eqpMgmtFIndEqupQListPop2");
	
//     var objTbl = document.getElementById('table_list2');
//     var trs = objTbl.getElementsByTagName("tr");
        
//     var size = arrObj.length;
    
//     var foodDataGubun = $('#searchDataGubun').val();
    
//     //alert("=======foodDataGubun=========>>>>"+foodDataGubun);
    
//     $("#noDataMsg").remove();
    
//     var str = "";

//     var mainCode2 = "";
//     var str="";
//     var dispCfrmDt = "";
//     var paramValue="";
//     var dupCheckFlag;
//     var shiprNm="";
//     var paramName = "";
	
    
//     for(var i=0; i<size; i++){
//         var retVal = arrObj[i];
//         var rowIndex = retVal.rowIndex;
//         if(i > 0){
//             rowIndex = 0;
//         }

//         paramValue = retVal.foodCd;
//         //paramName = "(식품코드: "+retVal.foodCd+", 식품명: "+retVal.fdSclasNm+")";
        
//         var testRowCnt=0;
//         $('#table_list2 :input[id^=foodCd]').each(function(index) {
//              var afterFoodCd = $("input[id='foodCd']").eq(index).val();

//              if(afterFoodCd == paramValue){
//             	 //alert("이미 등록된 "+paramName+"은(는) 선택할 수 없습니다.");
//             	 paramName += "(식품코드: "+retVal.foodCd+", 식품명: "+retVal.fdSclasNm+")\n";
//             	 testRowCnt++;
//              }
//          });
        
//         var html = "";
      
//         if(testRowCnt < 1){
        	
//         	var delCnt = 0;
//        	    $('#table_list2 :input[id^=delchk2]').each(function(index) {
//        	        delCnt++;
//        	    });
          
//        	    var evenChk = delCnt%2==0?"":"even";
        	
//         	html += "<tr class=\""+evenChk+"\">";
//         	html += "<td>";
//         	html += "<input type=\"checkbox\" name=\"delchk2\" id=\"delchk2\" title=\"체크박스\" value=\"\" class=\"insCheck\">";
//         	html += "<input type=\"hidden\" id=\"foodWahoSno\" name=\"foodWahoSno\"  value=\"\"/>";
//         	html += "<input type=\"hidden\" id=\"foodSno\" name=\"foodSno\"  value=\"\"/>";
//         	html += "<input type=\"hidden\" id=\"foodCd\" name=\"foodCd\"  value=\""+retVal.foodCd+"\"/>";
//         	html += "<input type=\"hidden\" id=\"foodDataGubun\" name=\"foodDataGubun\"  value=\""+foodDataGubun+"\"/>";
//         	html += "</td>";
//         	html += "<td>"+retVal.fdSclasNm+"</td>";
//         	html += "</tr>\n";
          
//             $("#table_list2").append(html);
//         }
// //         else{
// //         	alert("이미 등록된 "+paramName+"은(는) 선택할 수 없습니다.");
// //         }
//      }
//      if(paramName != ''){
//     	 alert("이미 등록된 품목은 선택할 수 없습니다.\n"+paramName);
//      }
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
	
// 	if(){
// 		fn_showUserPage("이미 추가된 장비는 선택할 수 없습니다.\n "+message+")");
// 	}
	
	
// 	if(dupCnt == 0){
// 		return true;
// 	}else{
// 		fn_showUserPage("이미 추가된 장비는 선택할 수 없습니다.\n "+message+")");
// 		return false;
// 	}
	
	
// 	if(dupCnt>0){
// 		fn_showUserPage( "이미 추가된 장비는 선택할 수 없습니다.(장비명: "+message+")", function() {
// 			return false;	
//         });
// 	}else{
// 		return true;
// 	}
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
                          <div class="sub_ttl">부서 찾기</div>
                         
                          <div class="sub">
                             <!--------------검색------------------>
                             <form name="searchForm" id="searchForm" method="post">
                             <input type="hidden" id="srcUseYn" name="srcUseYn" value="Y"/>
                            <div class="t_head">
                                  <table class="tbl_type_hd" border="1" cellspacing="0" onkeydown="if(gfn_enterChk())fn_searchDeptMList(1);">
                                        <caption>검색</caption>
                                         <colgroup>
                                            <col width="20%">
                                            <col width="30%">
                                            <col width="20%">
                                            <col width="30%">
                                         </colgroup>
                                   <thead>
                                      <tr>
                                           <th scope="row" class="hcolor">기관명</th>
								           <td>
								               <input type="text" id="searchInsttNm" name="searchInsttNm" title="기관명" class="input20" maxlength="100"/>
								           </td>
								           <th scope="row" class="hcolor">사용여부</th>
								           <td colspan="3">
								           		<select id="searchUseYn" name="searchUseYn" onchange="" class="selw10">
								                   <option value="" selected>전체</option>
								                   <option value="Y">사용</option>
								                   <option value="N">미사용</option>
								               </select>
								           </td>
                                       </tr>
                                   </thead>
                                </table>
                              </div>
                            <div class="btn_c">
                                  <ul>
                                     <li><a href="javascript:fn_searchDeptMList(1);" class="gyButton">조회</a></li>
                                  </ul>
                               </div>
                               </form>
                            <!--------------//검색------------------>
                            
                            <!--------------결과------------------>
                             <div class="r_num">| 결과  <strong id="totalcnt" style="color:#C00"></strong>건</div>
                             
                             <!--------------목록---------------------->
                             <div class="t_list">  
                                  <table id="deptFindList" class="tbl_type" border="1" cellspacing="0" >
                                        <caption>목록</caption>
                                          <colgroup>
<%--                                               <col width="5%">  순번 --%>
                                              <col width="11%">	<%-- 부서코드 --%>
                                              <col />			<%-- 기관명 --%>
                                              <col width="11%">	<%-- 과명 --%>
                                              <col width="11%">	<%-- 팀명 --%>
                                              <col width="11%">	<%-- 부서레벨 --%>
                                              <col width="12%">	<%-- 사용여부 --%>
                                              <col width="12%">	<%-- 선택 --%>
                                           </colgroup>
                                            <thead>
                                              <tr>
<!--                                                  <th scope="col">순번</th> -->
                                                 <th scope="col">부서코드</th>
                                                 <th scope="col">기관명</th>
                                                 <th scope="col">과명</th>
                                                 <th scope="col">팀명</th>
                                                 <th scope="col">부서레벨</th>
                                                 <th scope="col">사용여부</th>
                                                 <th scope="col">선택</th>
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
			                        <li><a href="#" class="gyButton" onclick="fn_dialogClose('fsysDeptFIndListPop');return false;">닫기</a></li>
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