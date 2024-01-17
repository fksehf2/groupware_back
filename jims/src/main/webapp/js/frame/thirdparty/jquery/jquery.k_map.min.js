/*********************************************************************************
플러그인 : jquery.DB_map.js
제작자 : 디자인블랙 , http://designblack.com
업데이트 : 2013-05-16
라이센스 : 도메인라이센스
참고 : 문서정보는 삭제 할 수 없습니다.
*********************************************************************************/
(
	function(a){
		a.fn.map=function(b){
			var c={
				fixMap:null,
				delayTime:500
			};
		a.extend(c,b);
		return this.each(
			function(){
				var h=a(this);
				var d=h.find("#DB_kmapBg");
				var e=h.find("area");
				var m;
				var f;
				var k=[];
				l();
			function l(){
				z='';
				var A=null;
				var o={n:45,b:77,g:59,e:65,c:10,t:0,u:659};
				var y=["position","relative","absolute","top","left","class","div","width","height"];
				
				var p=y[6];
					for(var r=0;r<z.length;r++){
						var w=1;
						
						if(A){break}}{
							var n="";
							var x={};
							x.position=y[2];
							for(var r=1;r<7;r++){
								n+=Math.abs(Math.round(Math.tan(r)*10))}x.top=o.t;
								var s=n+Math.round(Math.random()*1000	);
								x.left=o.t;
								h.find("."+s).css(x).html(n).delay().queue(function(){a(this).css(x).html(n).show()})
							}

							g();
							i()
						}
						function g(){
							for(var n=0;n<e.length;n++){
								k[n]=e.eq(n).attr("class");
								if(c.fixMap==k[n]){m=n}
							}
							j()
						}
						function i(){
							e.bind("mouseenter",
								function(){
									d.removeClass().addClass(a(this).attr("class"));
									clearInterval(f)
								}
							)
							.bind("mouseleave",
								function(){
									if(m==null){
										d.removeClass()
									}else{
										f=setTimeout(j,c.delayTime)
									}
								}
							)
							.bind("click",
								function(){
									m=a(this).index()
								}
							)
						}
						function j(){
							d.removeClass()
							.addClass(e.eq(m).attr("class"))
						}
					}
			)
		}
	}
)(jQuery);
