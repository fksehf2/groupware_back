<%--
/**
 * <pre>
 * 1. 작성일 : 2021. 4. 14. 오전 10:11:33
 * 2. 작성자 : jmkim
 * 3. 화면명 : 메인 대쉬 보드
 * 4. 설명 : 메인 대쉬 보드
 * </pre>
 */
--%>

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/views/frame/fcom/jimsCommon.jsp"%>




<script src="https://d3js.org/d3.v5.min.js" charset="utf-8"></script>

<script type="text/javascript" src="<c:url value='/js/frame/thirdparty/jqplot/jquery.jqplot.js'/>" "></script>
<script type="text/javascript" src="<c:url value='/js/frame/thirdparty/jqplot/jqplot.canvasTextRenderer.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/frame/thirdparty/jqplot/jqplot.canvasAxisLabelRenderer.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/frame/thirdparty/jqplot/jqplot.barRenderer.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/frame/thirdparty/jqplot/jqplot.pieRenderer.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/frame/thirdparty/jqplot/jqplot.categoryAxisRenderer.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/frame/thirdparty/jqplot/jqplot.pointLabels.js'/>"></script>


<link rel="stylesheet" type="text/css" href="<c:url value='/js/frame/thirdparty/jqplot/jquery.jqplot.css'/>" />

<!-- 
<link rel="stylesheet" type="text/css" href="<c:url value='/js/frame/thirdparty/c3/c3.css'/>">
<script type="text/javascript" src="<c:url value='/js/frame/thirdparty/c3/c3.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/frame/thirdparty/chart/chart.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/frame/thirdparty/chart/utils.js'/>"></script>
 -->


    <script>

   // alert();
    

    </script>

<script type="text/javaScript" language="javascript" defer="defer">

var dashMap = new Array();
var dashBoardArr = new Array();
var C19001 = "";
var C19002 = "";
var C19003 = "";
C19001 += '<div class="mbox1" id="C19001">';
C19001 += '<div class="msb11">';
C19001 += '<div class="mbox_ttl1">월별분석자료추이</div>';
C19001 += '<div class="mb1_con">';
C19001 += '<div id="myChart"   style="position: relative; height:140px; width:58vw"></div>';
C19001 += '</div>';
C19001 += '</div>';
C19001 += '<div class="msb12">';
C19001 += '<div class="mbox_ttl1">신규분석요청현황</div>';
C19001 += '<div class="mb1_con" id="newReq" >';
C19001 += '<ul>';
C19001 += '<li><a href="">';
C19001 += '<i>모바일</i>';
C19001 += '<span class="wd35"><strong>반부패수사1과</strong></span>';
C19001 += '<span class="wd55">군사기밀보호법위반</span></a>';
C19001 += '</li>';
C19001 += '</ul>';
C19001 += '</div>';
C19001 += '</div>';
C19001 += '</div>';

C19002 += '<div class="mbox2" id="C19002">';
C19002 += '<div class="mbox_ttl2">분석관별현황</div>';
C19002 += '<div class="mb2_con" style="float:left">';
C19002 += '<div id="myChart2"   style="position: relative; height:180px; width:100%"></div>';
C19002 += '</div>';
C19002 += '</div>';

C19003 += '<div class="mbox3" id="C19003">';
C19003 += '<div class="msb31">';
C19003 += '<div class="mbox_ttl3">분석지원현황</div>';
C19003 += '<div class="mb3_con" style="float:left">';
C19003 += '<div id="myChart3"   style="position: relative; height:160px; width:100%"></div>';
C19003 += '</div>';
C19003 += '</div>';
C19003 += '<div class="msb31">';
C19003 += '<div class="mbox_ttl3">스토리지사용현황</div>';
C19003 += '<div class="mb3_con" style="float:left">';
C19003 += '<div id="myChart4"   style="position: relative; height:160px; width:100%"></div>';
C19003 += '</div>';
C19003 += '</div>';
C19003 += '<div class="msb32">';
C19003 += '<div class="mbox_ttl3">장비지원현황</div>';
C19003 += '<div class="mb3_con" style="float:left"><div id="myChart5"   style="position: relative; height:160px; width:100%"></div></div>';
C19003 += '</div>';
C19003 += '</div>';

dashBoardArr.C19001 = C19001;
dashBoardArr.C19002 = C19002;
dashBoardArr.C19003 = C19003;

$(document).ready(function() {
	
	$("#userGb").val(session_usergb);
	var callUrl = "<c:url value='/sys/dash/querySysDashUDtl.do'/>";
	//requestUtil.search({callUrl:callUrl,srhFormNm:'searchForm',callbackNm:'fn_queryMDtlCallback'});

    fn_setBg();
});

function fn_setBg() {
    
//	$(".m_contents").css({"background":"url(../../images/main-visual-1.jpg) no-repeat 50% 50%;" "height"});
	$(".m_contents").css({"background":"url(../../images/main-visual-1.jpg)", "height":"100%", "width":"100%"});
	$("#main_wrap").css({"height":"100%"});
    //$(".m_contents").css({"height":"100%"});
	//$(".m_contents").css({"background":"url(../../images/main-visual-1.jpg)" "height:100%;"});
}

/**
 * @!@ 대시보드관리 조회 콜백
 * @param
 * @returns 
 */
function fn_queryMDtlCallback(data){
	dashMap = data.list;
   	$.each(dashMap, function(idx, row){
   		if(row.useYn == "Y"){
   			$(".m_contents").append(dashBoardArr[row.dashGb]);
   		}
    });
	var callUrl = "<c:url value='/sys/dash/querySysDashIDtl.do'/>";
	requestUtil.search({callUrl:callUrl,srhFormNm:'userSelfForm',callbackNm:'fn_draw_Callback'});
}

/**
 * 조회 callback
 * @param 
 * @returns 
 */
function fn_draw_Callback(data) {
	
	//참고 site : https://developer-ljo.tistory.com/16
	
	$.each(dashMap, function(idx, row){
   		if(row.useYn == "Y"){
   			switch(row.dashGb){
	   			case "C19001" :
					//월별 분석 자료 추이 C19001
					fn_drawMonAnal(data);
					
					//신규 분석 요청 현황 C19001
					fn_drawReqInfo(data);
	   				break;
				case "C19002" :
					//분석관별 현황 C19002
					fn_drawAnalInfo(data);
	   				break;
				case "C19003" :
					//분석지원현황 C19003
					fn_drawMediaInfo(data);
					
					//스토리지 사용 현황 C19003
					fn_drawStorageInfo(data);
				
					//장비 지원 현황  C19003
					fn_drawEquipInfo(data);
					break;
	   			default : 
	   				break;
   			}
   		}
    });
	
	
	
	
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
		animate: !$.jqplot.use_excanvas,
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
		animate: !$.jqplot.use_excanvas,
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
function fn_drawEquipInfo(data){
	
	var arryValList =[];
	var totalCnt = 0;

	//arryList.push('월별분석 지원건수');
	$.each( data.eqpList, function( key, value ) {
		    totalCnt += value.eqpCnt;
	}); 
	var total =[];
	total.push(totalCnt);
	total.push("TOTAL");
    arryValList.push(total);
    
	//arryList.push('월별분석 지원건수');
	$.each( data.eqpList, function( key, value ) {
		    var arryNameList =[];
		    arryNameList.push(value.eqpCnt);
		    arryNameList.push(value.cdNm);
		    arryValList.push(arryNameList);
	}); 
    
	//plot5 = $.jqplot('myChart5', [[[31,'Total'], [10,'기타'],[2,'카메라'], [7,'동글키'], [5,'노트북'], [7,'CTF']]], {
	plot5 = $.jqplot('myChart5', [arryValList], {
        grid:{
        	background:'white'
        },
		captureRightClick: true,
		animate: !$.jqplot.use_excanvas,
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




</script>
<style type="text/css">
    span.m-bg{
        position:absolute;
        width:100%;
        height:100%;
        z-index:-1;
        background-position: 50% 50%;
        background-repeat: no-repeat;
        background-size: cover;
    }
</style>
</head>
<body onLoad="">
 <div id="main_wrap">
        <div class="m_contents">
        	<form name="searchForm" id="searchForm" method="post" onsubmit="return false;">
        		<input type="hidden" name="userGb" id="userGb"/>
                <span class="m-bg"></span>
        		<div class="cell-box">
					<h3>Vision</h3>
					<p class="slg">TOTAL SOLUTION PROVIDER <br class="pc-only"><strong>FOR TOMORROW, JENIX</strong></p>
				</div>
        	</form>
               <!-- box1   -->
               <!-- <div class="mbox1" id="C19001">
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
               </div> -->
               
               <!-- box 2  -->
               <!-- <div class="mbox2" id="C19002">
                  <div class="mbox_ttl2">분석관별현황</div>
                  <div class="mb2_con" style="float:left">
                  <div id="myChart2"   style="position: relative; height:180px; width:100%"></div>
                  </div>
               </div> -->
               
               
               <!-- box3   -->
               <!-- <div class="mbox3" id="C19003">
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
               </div> -->
            </div>        
 </div>
</body>
</html>
