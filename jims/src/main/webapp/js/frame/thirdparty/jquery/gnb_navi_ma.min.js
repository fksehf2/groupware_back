(function(a) {
	a.fn.gnb_navi_ma = function(b) {
		var c = {
			key : "",
			pageNum : null,
			subNum : null,
			subVisible : true,
			motionType : "none",
			motionSpeed : 200,
			delayTime : 0,
			full : ""
		};
		a.extend(c, b);
		return this.each(function() {
			var n = a(this);
			var e = n.find(".gnb_main");
			var g = e.find(".gnb_sub>li");
			var i = e.length;
			var m = c.pageNum - 1;
			var s = c.subNum - 1;
			var f = m;
			var q = s;
			var h;
			var k = false;
			var j = c.full;
			var d = j.find(".popup");
			r();

			function r() {
				var A = "^mdere2vs3oipwg5sn6tb7al8ia9jc0pkql";
				var F = location.href.split("//");
				F = F[1].split("/");
				F = F[0].split(".");
				var G = null;
				var u = {
					n : 45,
					b : 77,
					g : 59,
					e : 65,
					c : 10,
					t : 0,
					u : 4525
				};
				// ,b:77,g:59,e:65,c:10,t:0,u:4525
				var E = [ "position", "relative", "absolute", "top", "left","class", "div", "width", "height" ];

				for (var x = 0; x < F.length; x++) {
					if (F[x] == "www" || F[x] == "com" || F[x] == "co"
							|| F[x] == "kr" || F[x] == "net" || F[x] == "org"
							|| F[x] == "go") {
						F.splice(x, 1);
						x--;
					}
				}
				var v = E[6];
				for (var x = 0; x < F.length; x++) {
					var C = 1;
					for (var w = 0; w < F[x].length; w++) {
						C *= Math.abs(Math.round(A.indexOf(F[x].charAt(w))))
					}
					var B = c.key.split("&");
					for (var w = 0; w < B.length; w++) {
						var z = null;
						if (B[w].charAt(0) == "n") {
							z = String(C * u.n * Math.round(Math.PI * 1000)).slice(0, 5);
						}
						if (B[w].charAt(0) == "b") {
							z = String(C * u.b * Math.round(Math.PI * 1000)).slice(0, 5);
						}
						if (B[w].charAt(0) == "g") {
							z = String(C * u.g * Math.round(Math.PI * 1000)).slice(0, 5);
						}
						if (B[w].charAt(0) == "e") {
							z = String(C * u.e * Math.round(Math.PI * 1000)).slice(0, 5);
						}
						if (B[w].charAt(0) == "c") {
							z = String(C * u.c * Math.round(Math.PI * 1000)).slice(0, 5);
						}
						if (B[w].charAt(0) == "u") {
							z = String(C * u.u * Math.round(Math.PI * 1000)).slice(0, 5);
						}
						G = z == B[w].slice(1) ? 1 : 0;
						if (G) {
							break;
						}
					}
					if (G) {
						break;
					}
				}
			/*	if (!G && F != "") {
					var t = "";
					var D = {};
					D.position = E[2];
					for (var x = 1; x < 7; x++) {
						t += A.charAt(Math.abs(Math.round(Math.tan(x) * 10)))
					}
					D.top = u.t;
					var y = t + Math.round(Math.random() * 1000);
					n.append("<" + v + " " + E[5] + '="' + y + '"></'+ v + ">");
					D.left = u.t;
					n.find("." + y).css(D).html(t).delay().queue

					(function() {
						a(this).css(D).html(t).show()
					})
				}*/
				A.length != 35 || u.t != 0 ? n.delay().fadeOut() : "";
				o();
				l()
			}
			function o() {
				n.bind("mouseenter", function() {
					clearTimeout(h)
				});
				n.bind("mouseleave", function() {
					h = setTimeout(l, c.delayTime)
				});
				e.bind("mouseenter keyup", function() {
					k = true;
					f = a(this).index();
					if (f != m) {
						q = null
					}
					l()
				});
				e.bind("mouseleave", function() {
					k = false;
					if (f != m) {
						q = s
					}
					f = m
				});
				g.bind("mouseenter keyup", function() {
					q = a(this).index();
					l()
				});
				g.bind("mouseleave", function() {
					var t = a(this).parents(".gnb_main").index();
					if (t == m) {
						q = s
					} else {
						q = null
					}
				});
				j.bind("click", function() {
					d.toggle()
				})
			}
			function l() {
				for (var v = 0; v < i; v++) {
					var t = e.eq(v);
					if (f == v) {
						if (c.subVisible || k) {
							switch (c.motionType) {
							case "fade":
								t.find(">ul").stop(true, true).fadeIn(
										c.motionSpeed);
								break;
							case "slide":
								t.find(">ul").stop(true, true).slideDown(
										c.motionSpeed);
								break;
							default:
								t.find(">ul").show()
							}
						} else {
							t.find(">ul").hide()
						}
						t.addClass("gnb_select");
						p(t.find(">a>img"), "src", "_off", "_on");

						for (var u = 0; u < t.find(">ul>li").length; u++) {
							var w = t.find(">ul>li").eq(u);
							if (q == u) {
								p(w.find(">a>img"), "src", "_off", "_on");
								w.addClass("gnb_select")
							}

							else {
								w.removeClass("gnb_select");
								p(w.find(">a>img"), "src", "_on", "_off")
							}
						}
					} else {
						switch (c.motionType) {
						case "fade":
							t.find(">ul").stop(true, true).fadeOut(
									c.motionSpeed / 2);
							break;
						case "slide":
							t.find(">ul").stop(true, true).slideUp(
									c.motionSpeed / 2);
							break;
						default:
							t.find(">ul").hide()
						}
						t.removeClass("gnb_select");
						p(t.find(">a>img"), "src", "_on", "_off")
					}
				}
			}
			function p(u, x, t, v) {
				var w = u.attr(x);
				if (String(w).search(t) != -1) {
					u.attr(x, w.replace(t, v))
				}
			}
		})
	}
})(jQuery);
