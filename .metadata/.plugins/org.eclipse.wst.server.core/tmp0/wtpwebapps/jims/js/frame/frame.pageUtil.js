(function(){
	
	var frame =frame||{};
	pageUtil = frame.pageUtil||{};

	
	$.extend(true, pageUtil, {

		
		/*
		next: false
		page: "2"
		pageStart: 10
		perPageNum: 10
		prev: false
		startPage: 1
		totalCount: 34
		totalPages: 4
		
		page 1 1-10
		page 2 11-20
		page 3 21-30
		
		page: "1"
		pageStart: 0
		perPageNum: 10
		prev: false
		sqlQueryId: "fsysMenuDAO.queryFsysMenuMList"
		srcMenuNm: ""
		srcSysGrp: ""
		startPage: 1
		totalCount: 1054
		totalPages: 106
		__callFuncName__: "fn_queryFsysMenuMList"
		
		
		*/
		setPageNavi : function (args){

			//console.log('args___',args);
		
			var data = args;
			var naviID = "#page_navi";
			//var pageID = "page_nation";
			//console.log(data.__naviID__);
			if(typeof data.__naviID__ != "undefined"){
			
				naviID = "#"+data.__naviID__;
			}
			
			var page_nationID = "page_nation"+data.__naviID__;
			
			if(data.totalPages < data.page){
				data.page = data.totalPages;
			}
			
			$(naviID).empty();
			$(naviID).append("<div id='"+page_nationID+"' class='page_nation'>");
			
			if( data.prev) {
				$("#"+page_nationID).append("<a class='arrow pprev' href='javascript:"+data.__callFuncName__+"(\"1\")' ></a>");
				$("#"+page_nationID).append("<a class='arrow prev' href='javascript:"+data.__callFuncName__+"("+(data.startPage-1)+")' ></a>");
			}
			
			for( var i =data.startPage ; i <= (data.endPage) ; i++) {

				
				if( i == data.page) {

					$("#"+page_nationID).append("<a href='#' class='active'>"+i+"</a>");
				} else {

					$("#"+page_nationID).append("<a href='javascript:"+data.__callFuncName__+"("+i+")' >"+i+"</a>");
				}
				 
			}
			

			if( data.next && data.endPage > 0 ) {
				$("#"+page_nationID).append("<a class='arrow next' href='javascript:"+data.__callFuncName__+"("+(data.endPage+1)+")' ></a>");
				$("#"+page_nationID).append("<a class='arrow nnext' href='javascript:"+data.__callFuncName__+"("+(data.totalPages)+")' ></a>");
			}
			
			$("#"+page_nationID).append("</div>");
			
		} // end of setPageNavi
		,setPageNavi_bak : function (args){

			//console.log('args___',args);
			var data = args;
			
			$("#page_navi").empty();
			$("#page_navi").append("<div class='page_nation'>");
			
			if( data.prev == 'true' ) {
				$(".page_nation").append("<a class='arrow pprev' href='#'></a>");
			}
			
			for( var i =(data.pageStart+1) ; i <= data.totalPages ; i++) {

				var pageIdx = (data.startPage - 1 ) * data.perPageNum +i;
				
				if( i == data.page) {

					$(".page_nation").append("<a href='#' class='active'>"+pageIdx+"</a>");
				} else {

					$(".page_nation").append("<a href='javascript:"+data.__callFuncName__+"("+pageIdx+")' >"+pageIdx+"</a>");
				}
				 
			}
			
			if( data.next=='true') {
				$(".page_nation").append("<a class='arrow nnext' href='#'></a>");
			}
			
			$(".page_nation").append("</div>");
			
		} // end of setPageNavi

	});
	
})();


