<%--
/**
 * <pre>
 * 1. 작성일 : 2021. 4. 14. 오전 10:11:33
 * 2. 작성자 : jij
 * 3. 화면명 : 대시보드관리 화면
 * 4. 설명 : @!@ 대시보드관리 화면 
 * </pre>
 */
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>

<html>
<head>
<%@ include file="/WEB-INF/views/frame/fcom/jimsCommon.jsp" %>

<link rel="stylesheet" type="text/css" href="<c:url value='/js/frame/thirdparty/jqplot/jquery.jqplot.css'/>" />

<script src="https://d3js.org/d3.v5.min.js" charset="utf-8"></script>

<script type="text/javascript" src="<c:url value='/js/frame/thirdparty/jqplot/jquery.jqplot.js'/>" "></script>
<script type="text/javascript" src="<c:url value='/js/frame/thirdparty/jqplot/jqplot.canvasTextRenderer.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/frame/thirdparty/jqplot/jqplot.canvasAxisLabelRenderer.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/frame/thirdparty/jqplot/jqplot.barRenderer.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/frame/thirdparty/jqplot/jqplot.pieRenderer.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/frame/thirdparty/jqplot/jqplot.categoryAxisRenderer.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/frame/thirdparty/jqplot/jqplot.pointLabels.js'/>"></script>

<style type="text/css">
   /*  table th, table td
    {
        width: 100px;
        padding: 5px;
        border: 1px solid #ccc;
    } */
    .selected
    {
        background-color: #d9d9d9;
        color: #fff;
    }
    .itemBoxHighlight {

	    border:solid 1px black;
	
	    width:400px;
	
	    height:50px;
	
	    padding:10px;
	
	    margin-bottom:10px;
	
	    background-color:gray;

	}

</style>

<script type="text/javaScript" language="javascript" defer="defer">
var tabId;
/* var req = "";
var cncl = "";
var reqStr = "";
var cnclStr = "";
switch (session_usergb) {
	case 'C01001':
		req = "C03002";
		cncl = "C03001";
		reqStr = "검사승인요청";
		cnclStr = "검사승인요청취소";
	    break;
	case 'C01002':
		req = "C03003";
		cncl = "C03002";
		reqStr = "검사승인";
		cnclStr = "검사승인취소";
	    break;
	case 'C01003':
		req = "C03005";
		cncl = "C03003";
		reqStr = "접수";
		cnclStr = "접수취소";
	    break;
	case 'C01004':
		req = "C03006";
		cncl = "C03005";
		reqStr = "과장승인";
		cnclStr = "과장승인취소";
	    break;
	default:
		break;
} */

$(document).ready(function() {
	tabId = parent.$('li[id*=li_tabs-M][aria-expanded=true]').attr('aria-controls');
    
	$("#listTab").sortable({
        items: 'tbody > tr',
        cursor: 'pointer',
        axis: 'y',
        dropOnEmpty: false,
        placeholder:"itemBoxHighlight",
        start: function (e, ui) {
        	ui.item.addClass("selected");
        },
        stop: function (e, ui) {
            ui.item.removeClass("selected");
            $(this).find("tr").each(function (index) {
                if (index > 0) {
                    $(this).find("td").eq(2).find("[name=dashOrder]").val(index);
                }
            });
        }
    }).disableSelection();
	
	var codeInfo = [{cdId:'C01',selectId:'userGb',type:'1', callbackNm:'fn_ajaxCodeListCallback', sqlQueryId:''}];
	fn_ajaxCodeList(codeInfo);
	
});

/**
 * @!@ 사용자구분 코드 리스트 조회 콜백
 * @param {json} data 
 * @returns 
 */
function fn_ajaxCodeListCallback(data){
    //fn_queryMDtl();
	var codeInfo2 = [{cdId:'C19',selectId:'',type:'1', callbackNm:'fn_ajaxCodeListCallback2', sqlQueryId:''}];
	fn_ajaxCodeList(codeInfo2);
}
 
/**
 * @!@ 대시보드구분 코드 리스트 조회 콜백
 * @param {json} data 
 * @returns 
 */
function fn_ajaxCodeListCallback2(data){
	var list = data.C19;
	var listCnt = list.length;
	
	$("#listTab > tbody").empty();
	
	if(listCnt == 0){
		var append = "";
		append += "<tr>";
		
		append += "<td colspan='"+tabTdCnt+"'>"+nullListMsg+"</td>";
		
		append += "</tr>";
		$("#listTab > tbody").append(append);
	}else{
		$.each(list,function(idx,row){
			var append = "";
			append += "<tr id='"+gfn_nullRtnSpace(row.cd)+"'>";
	
			append += "<td><span id='oriDashOrder' name='oriDashOrder' style='display: none;'></span><input name='dashGb' title='대시보드 정렬순서'  type='text' value='"+gfn_nullRtnSpace(row.cd)+"' readonly class='inpw50' data-requireNm='대시보드 정렬순서' /></td>";
			append += "<td>" + gfn_nullRtnSpace(row.cdNm)+ "</td>";
			append += "<td><input name='dashOrder' title='대시보드 정렬순서'  type='text' value='"+(idx+1)+"' readonly class='inpw50' data-requireNm='대시보드 정렬순서' /></td>";
			append += "<td><input type='checkbox' name='useYn' id='useYn' title='사용여부' value='Y' class='check_agree1'/></td>";
	
			append += "</tr>";
	        $("#listTab > tbody").append(append);
	 	});
	}
	
	fn_queryMDtl();
	
}
 
/**
 * @!@ 대시보드관리 조회
 * @param
 * @returns 
 */
function fn_queryMDtl(){
	
	var callUrl = "<c:url value='/sys/dash/querySysDashUDtl.do'/>";
	requestUtil.search({callUrl:callUrl,srhFormNm:'insForm',callbackNm:'fn_queryMDtlCallback'});
	//var callUrl = "<c:url value='/sys/dash/querySysDashIDtl.do'/>";
	//requestUtil.search({callUrl:callUrl,srhFormNm:'userSelfForm',callbackNm:'fn_draw_Callback'});
	
}

/**
 * @!@ 대시보드관리 조회 콜백
 * @param
 * @returns 
 */
function fn_queryMDtlCallback(data){
	var list = data.list;
	var listCnt = list.length;
	
	if(listCnt > 0){
		$.each(list,function(idx,row){
			$("#"+row.dashGb+" > td").find("[name=oriDashOrder]").text(row.dashOrder);
			$("#"+row.dashGb+" > td").find("[name=dashOrder]").val(row.dashOrder);
			if(row.useYn == "Y"){
				$("#"+row.dashGb+" > td").find("[name=useYn]").prop("checked", true);
				$("#"+row.dashGb+" > td").find("[name=useYn]").attr("checked", "checked");
			}else{
				$("#"+row.dashGb+" > td").find("[name=useYn]").prop("checked", false);
				$("#"+row.dashGb+" > td").find("[name=useYn]").attr("checked", "");
			}
	 	});
		
	    var trArr = $("#listTab tbody tr");
	    var tbody = $("#listTab tbody");
	    tbody.innerHTML = "";
	    var args = new Array();
	    
	    /* Array.from($trArr).reverse().forEach((el, i) => {
	    	args[$(el).find("#oriDashOrder").text()] = el;
	    })
	    console.log(args); */
	    $.each(trArr, function(idx, row){
	    	args[$(row).find("[name=oriDashOrder]").text()] = row;
	    });
	    
	    $.each(args, function(idx, row){
	    	if(row != undefined){
	    		$(tbody).append(row);
	    	}
	    });
	}
}

/**
 * @!@ 대시보드관리 조회 콜백
 * @param
 * @returns 
 */
function fn_draw_Callback(data) {
	
	//참고 site : https://developer-ljo.tistory.com/16
	
	
	//월별 분석 자료 추이
	fn_drawMonAnal(data);
	
	//신규 분석 요청 현황
	fn_drawReqInfo(data);
	
	//분석관별 현황
	fn_drawAnalInfo(data);
	
	//분석지원현황
	fn_drawMediaInfo(data);
	
	//스토리지 사용 현황
	fn_drawStorageInfo(data);

	//장비 지원 현황
	fn_drawEquipInfo(data);
}

/**
 * 월별 분석 자료 추이
 * @param 
 * @returns 
 */
function fn_drawMonAnal(data) {

	
	var arryList =[];

	//arryList.push('월별분석 지원건수');
	$.each( data.findIdInfo, function( key, value ) {

			arryList.push(value.cnt);
	});
	
	
	
	var plot1 = $.jqplot('myChart', [arryList],{
		
		grid: {
		    drawGridLines: true,        // wether to draw lines across the grid or not.
		        gridLineColor: '#cccccc',   // CSS color spec of the grid lines.
		        background: 'white',      // CSS color spec for background color of grid.
		        borderColor: '#cfcfcf',     // CSS color spec for border around grid.
		        borderWidth: 2.0,           // pixel width of border around grid.
		        shadow: false,               // draw a shadow for grid.
		        shadowAngle: 45,            // angle of the shadow.  Clockwise from x axis.
		        shadowOffset: 1.5,          // offset from the line of the shadow.
		        shadowWidth: 3,             // width of the stroke for the shadow.
		        shadowDepth: 3
		},
		axesStyles: {
	           borderWidth: 0,
	           ticks: {
	               fontSize: '12pt',
	               fontFamily: 'Times New Roman',
	               textColor: 'black'
	           },
	           label: {
	               fontFamily: 'Times New Roman',
	               textColor: 'black'
	           }
	        },
	        
	        axes: {
                xaxis: {
                          ticks: [1,2,3,4,5,6,7,8,9,10,11,12],
                          tickOptions: {
                              formatString: "%'d 월"
                          },
                        },
                 yaxis: {
                	 tickOptions:{
                		 formatString: "%'d"
                	 },
                	 min:0
                         
                 }

            },
            /*
            highlighter: {
                show: true, 
                showLabel: true, 
                tooltipAxes: 'y',
                sizeAdjust: 7.5 , tooltipLocation : 'ne'
            },*/
            series:[
                {color: '#ff7c7c', lineWidth: 1, label:'good line'}
            ]
	});
	
	$(window).resize(function() {
	      plot1.replot( { resetAxes: true } );
	});
}


/**
 * 신규 분석 요청 현황
 * @param 
 * @returns 
 */
function fn_drawReqInfo(data) {
	
	$("#newReq").empty();
	
	$.each( data.reqList, function( key, value ) {

		$("#newReq").append("<ul><li><a href='#'>"
				+"<i>"+value.cdNm+"</i>"
				+" <span class='wd35'><strong>"+value.deptNm+"</strong></span>"
				+" <span class='wd55'>"+value.incdtNm+"</span></a></li></ul>");
		  
	});
	
	
}


/**
 * 분석관별 현황
 * @param 
 * @returns 
 */
function fn_drawAnalInfo(data) {

	//console.log(data);
	
	var arryNameList =[];

	var arryPrgValList =[];
	var arryEndValList =[];

	//arryList.push('월별분석 지원건수');
	$.each( data.analList, function( key, value ) {

		    arryNameList.push(value.analCrgrId);
		    arryPrgValList.push(value.prg);
		    arryEndValList.push(value.end);
		    
	});
	
	
	 $.jqplot.config.enablePlugins = true;
     var s1 = arryPrgValList;//[2, 6, 7, 10];
     var s2 = arryEndValList;
     var ticks = arryNameList;//['a', 'b', 'c', 'd'];
      
     plot2 = $.jqplot('myChart2', [s1,s2], {
         // Only animate if we're not using excanvas (not in IE 7 or IE 8)..
         
         grid:{background: 'white'},      // CSS color spec for background color of grid.
	        
         stackSeries: true,
         animate: !$.jqplot.use_excanvas,
         seriesDefaults:{
             renderer:$.jqplot.BarRenderer,
             pointLabels: { show: true },
             shadow:false,
         },
         series:[
        	 {color:'#ff6666',
        		 label:'진행중'
        		 
        	 },{color:'#668cff',
        		 label:'완료'
        		 }
        	 ],
         axes: {
             xaxis: {
                 renderer: $.jqplot.CategoryAxisRenderer,
                 ticks: ticks
             }
         },
         legend: {
             show: true,
             label:'진행중',
             location: 'e',
             placement: 'inside'
         },
         highlighter: { show: false }
         
     });
  
     $('#myChart2').bind('jqplotDataClick', 
         function (ev, seriesIndex, pointIndex, data) {
             $('#info1').html('series: '+seriesIndex+', point: '+pointIndex+', data: '+data);
         }
     );
     
     
     $(window).resize(function() {
         plot2.replot( { resetAxes: true } );
   	});
		
	

}


/**
 * 분석지원현황
 * @param 
 * @returns 
 */
function fn_drawMediaInfo(data){
	
	var arryNameList =[];

	var arryValList =[];

	//arryList.push('월별분석 지원건수');
	 $.each( data.mediaList, function( key, value ) {

		    arryNameList =[];
		    arryNameList.push(value.media);
		    arryNameList.push(value.perc);
		    arryValList.push(arryNameList);
		    
	}); 
	

      var plot3 = $.jqplot('myChart3', [arryValList], {
    	  grid:{
          	background:'white'
          },
    	 gridPadding: {top:0, bottom:38, left:0, right:0},
         seriesDefaults:{
             renderer:$.jqplot.PieRenderer, 
             trendline:{ show:false }, 
             rendererOptions: { padding: 8, showDataLabels: true }
         },
         legend:{
             show:true, 
             placement: 'outside', 
             rendererOptions: {
                 numberRows: 1
             }, 
             location:'s',
             marginTop: '15px'
         }       
     }); 
  
    /*  $('#myChart3').bind('jqplotDataClick', 
         function (ev, seriesIndex, pointIndex, data) {
             $('#info1').html('series: '+seriesIndex+', point: '+pointIndex+', data: '+data);
         }
     );
     
     */
     $(window).resize(function() {
         plot3.replot( { resetAxes: true } );
   	}); 
	
}


/**
 * 스토리지 사용 현황
 * @param 
 * @returns 
 */
function fn_drawStorageInfo(){
	
	plot4 = $.jqplot('myChart4', [[[19,'Total'], [10,'기타'],[2,'디스크'], [7,'모바일']]], {
        grid:{
        	background:'white'
        },
		captureRightClick: true,
        seriesDefaults:{
            renderer:$.jqplot.BarRenderer,
            shadow:false,
            shadowAngle: 135,
            rendererOptions: {
                barDirection: 'horizontal',
                highlightMouseDown: true   
            },
            pointLabels: {show: true, formatString: '%d'}
        },
        legend: {
            show: false,
            location: 'e',
            placement: 'inside'
        },
        axes: {
            yaxis: {
                renderer: $.jqplot.CategoryAxisRenderer
            }
        }
    });
	
	 $(window).resize(function() {
         plot4.replot( { resetAxes: true } );
   	}); 
	
}


/**
 * 장비 지원 현황
 * @param 
 * @returns 
 */
function fn_drawEquipInfo(){
	
	plot5 = $.jqplot('myChart5', [[[31,'Total'], [10,'기타'],[2,'카메라'], [7,'동글키'], [5,'노트북'], [7,'CTF']]], {
        grid:{
        	background:'white'
        },
		captureRightClick: true,
        seriesDefaults:{
            renderer:$.jqplot.BarRenderer,
            shadow:false,
            shadowAngle: 135,
            rendererOptions: {
                barDirection: 'horizontal',
                highlightMouseDown: true   
            },
            pointLabels: {show: true, formatString: '%d'}
        },
        legend: {
            show: false,
            location: 'e',
            placement: 'inside'
        },
        axes: {
            yaxis: {
                renderer: $.jqplot.CategoryAxisRenderer
            }
        }
    });
	
	 $(window).resize(function() {
		 plot5.replot( { resetAxes: true } );
   	}); 
	
}

/**
 * @!@ 대시보드관리 저장
 * @param {json} data
 * @returns 
 */
function fn_regSysDashUDtl(){

	var params = new Array();
	
	var trArr = $("#listTab tbody tr");
	
	$.each(trArr, function(){
		var useYn = $(this).find("[name=useYn]").is(":checked")?"Y":"N";
		params.push({"userGb":$("#userGb").val(), "dashGb":$(this).find("[name=dashGb]").val(), "dashOrder":$(this).find("[name=dashOrder]").val(), "useYn":useYn, "session_userid":session_userid});
	})
	
	var data = {rowDatas : params};
	
	fn_showModalPage("저장 하시겠습니까?", function() {
		var callUrl = "<c:url value='/sys/dash/regSysDashUDtl.do'/>";
	    requestUtil.saveData({callUrl:callUrl,data:data,callbackNm:'fn_regSysDashUDtlCallback'});
	});
}

/**
 * @!@ 대시보드관리 저장 콜백
 * @param {json} data
 * @returns 
 */
function fn_regSysDashUDtlCallback(data){
	//requestUtil.setSearchFormPage({targetFormId:"searchForm", page:"1"});
	//fn_indexMList();
}
 
/**
 * @!@ 대시보드관리 리스트 화면 이동
 * @param
 * @returns 
 */
/* function fn_indexMList(){
	parent.$('#'+tabId+' iframe').attr('src', '<c:url value="/site/pgs/indexSitePgsMList.do"/>');
} */
</script>

</head>

<body>
<div id="con_wrap">
        <div class="content">
           <!----현재위치----->
             
            <div id="contents_info">
                 <div class="sub_ttl">대시 보드 관리</div><!-----타이틀------>
                  <div class="sub">
                     <!------------검색------------------->
                     <form name="searchForm" id="insForm" method="post" onsubmit="return false;">
                             <input type="hidden" class="" id="page" name="page" value="1"/>
                            <div class="t_head">
                                  <table class="tbl_type_hd" border="1" cellspacing="0" onkeydown="if(gfn_enterChk())fn_queryMList(1);">
                                        <caption>검색</caption>
                                         <colgroup>
                                            <col width="20%">
                                            <col width="30%">
                                            <col width="20%">
                                            <col width="30%">
                                         </colgroup>
                                   <thead>
                                      <tr>
                                         <th scope="col" class="hcolor">사용자그룹</th>
                                         <th scope="col">
											<select id="userGb" name="userGb" onchange="fn_queryMDtl();" class="selw10" title="사용자그룹">
											</select>
                                         </th>
                                         <th scope="col" class="hcolor"></th>
                                         <th scope="col">
                                         </th>
                                       </tr>
                                   </thead>
                                </table>
                              </div>
                            <div class="btn_c">
                                  <ul>
                                     <!-- <li><a href="javascript:void(0);" onclick="" class="myButton">엑셀</a></li> -->
                                     <li><a href="javascript:void(0);" onclick="fn_regSysDashUDtl();return false;" class="RdButton">저장</a></li>
                                     <!-- <li><a href="javascript:void(0);" onclick="fn_queryMList(1);return false;" class="gyButton">조회</a></li> -->
                                  </ul>
                               </div>
                               
                           <div class="t_list">  
                                  <table id="listTab" class="tbl_type" border="1" cellspacing="0" >
                                        <caption>목록</caption>
                                          <colgroup>
                                          		<col width="25%">
                                          		<col width="25%">
                                          		<col width="25%">
                                          		<col width="25%">
                                          </colgroup>
                                            <thead>
                                              <tr class="head-title">
                                              	 <th scope="col">대시보드구분</th>
                                                 <th scope="col">대시보드명</th>
                                                 <th scope="col">변경순서</th>
                                                 <th scope="col">사용여부</th>
                                              </tr>
                                            </thead>
                                            <tbody>
												<tr><td colspan="4">조회 결과가 없습니다.</td></tr>
                                          </tbody>
                                     </table>
		                             <span>※ 드래그하여 순서를 변경해주세요.</span>
                             </div>
                       </form>
					
					<!-- <div class="m_contents">     
		               box1  
		               <div class="mbox1">
		                  <div class="msb11">
		                      <div class="mbox_ttl1">월별분석자료추이</div>
		                      
		                      <div class="mb1_con" style="position: relative; height:120px; width:58vw">
		                      
		                       <div class="mb1_con">
		                      <div id="myChart"   style="position: relative; height:140px; width:58vw"></div>
		                      </div>
		                  </div>
		               
		            	<div class="msb12">
		                      <div class="mbox_ttl1">신규분석요청현황</div>
		                      <div class="mb1_con" id="newReq" >
		                        <ul>
		                          <li><a href="">
		                            <i>모바일</i>
		                            <span class="wd35"><strong>반부패수사1과</strong></span>
		                            <span class="wd55">군사기밀보호법위반</span></a>
		                          </li>
		
		                        </ul>
		                      </div>
		                  </div>
		               </div>
		               
		               box 2 
		               <div class="mbox2">
		                  <div class="mbox_ttl2">분석관별현황</div>
		                  <div class="mb2_con" style="float:left">
		                  <div id="myChart2"   style="position: relative; height:180px; width:100%"></div>
		                  </div>
		               </div>
		               
		               
		               box3  
		               <div class="mbox3">
		                  <div class="msb31">
		                     <div class="mbox_ttl3">분석지원현황</div>
		                     <div class="mb3_con" style="float:left">
		                     <div id="myChart3"   style="position: relative; height:160px; width:100%"></div>
		                     </div>
		                  </div>
		                  <div class="msb31">
		                     <div class="mbox_ttl3">스토리지사용현황</div>
		                     <div class="mb3_con" style="float:left">
		                     <div id="myChart4"   style="position: relative; height:160px; width:100%"></div>
		                     </div>
		                  </div>
		                  <div class="msb32">
		                     <div class="mbox_ttl3">장비지원현황</div>
		                     <div class="mb3_con" style="float:left"><div id="myChart5"   style="position: relative; height:160px; width:100%"></div></div>
		                  </div>
		               </div>
		            </div> -->
                          
                  </div>
            </div>
        
        </div>
 </div>
<script type="text/javaScript">
var tabId = parent.$('li[id*=li_tabs-M][aria-expanded=true]').attr('aria-controls');
var ifa = $(top.document).find('div#'+tabId+' > p > iframe');
ifa.attr('height', $("#contents_info").height()+50);
//$(top.document).find("#container").width($(top.document).find("#container").width() - 5)
</script>
</body>
</html>