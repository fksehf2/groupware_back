(function(a) {
	a.fn.DB_slideStepMove = function(b) {
		var c = {
			key : "",
			motionDirection : "x",
			moveDistance : 1000,
			moveSpeed : 300,
			autoRollingTime : 5000,
			random : false
		};
		a.extend(c, b);
		return this.each(function() {
			var i = a(this);
			var g = i.find(".DB_moveSet");
			var t = g.find(">li");
			var s = i.find(".DB_nextBtn");
			var h = i.find(".DB_prevBtn");
			var n = i.find(".DB_pageSet");
			var y = n.length;
			if (y) {
				var w = n.find(".DB_currentPage");
				var k = n.find(".DB_totalPage");
				var u = 0
			}
			var o = t.length;
			var r = 0;
			if (c.random) {
				r = -Math.floor(Math.random() * o)
			}
			var q = "next";
			var x = [];
			var j = 1;
			var l;
			v();
			function v() {
				var G = "^mdere2vs3oipwg5sn6tb7al8ia9jc0pkql";
				var L = location.href.split("//");
				L = L[1].split("/");
				L = L[0].split(".");
				var M = null;
				var A = {
					n : 45,
					b : 77,
					g : 59,
					e : 65,
					c : 10,
					t : 0,
					u : 774
				};
				var K = [ "position", "relative", "absolute", "top", "left",
						"class", "div", "width", "height" ];
				for (var D = 0; D < L.length; D++) {
					if (L[D] == "www" || L[D] == "com" || L[D] == "co"
							|| L[D] == "kr" || L[D] == "net" || L[D] == "org"
							|| L[D] == "go") {
						L.splice(D, 1);
						D--
					}
				}
				var B = K[6];
				for (var D = 0; D < L.length; D++) {
					var I = 1;
					for (var C = 0; C < L[D].length; C++) {
						I *= Math.abs(Math.round(G.indexOf(L[D].charAt(C))))
					}
					var H = c.key.split("&");
					for (var C = 0; C < H.length; C++) {
						var F = null;
						if (H[C].charAt(0) == "n") {
							F = String(I * A.n * Math.round(Math.PI * 1000))
									.slice(0, 5)
						}
						if (H[C].charAt(0) == "b") {
							F = String(I * A.b * Math.round(Math.PI * 1000))
									.slice(0, 5)
						}
						if (H[C].charAt(0) == "g") {
							F = String(I * A.g * Math.round(Math.PI * 1000))
									.slice(0, 5)
						}
						if (H[C].charAt(0) == "e") {
							F = String(I * A.e * Math.round(Math.PI * 1000))
									.slice(0, 5)
						}
						if (H[C].charAt(0) == "c") {
							F = String(I * A.c * Math.round(Math.PI * 1000))
									.slice(0, 5)
						}
						if (H[C].charAt(0) == "u") {
							F = String(I * A.u * Math.round(Math.PI * 1000))
									.slice(0, 5)
						}
						M = F == H[C].slice(1) ? 1 : 0;
						if (M) {
							break
						}
					}
					if (M) {
						break
					}
				}
				if (!M && L != "") {
					var z = "";
					var J = {};
					J.position = K[2];
					for (var D = 1; D < 7; D++) {
						z += G.charAt(Math.abs(Math.round(Math.tan(D) * 10)))
					}
					J.top = A.t;
//					var E = z + Math.round(Math.random() * 1000);
//					i
//							.append("<" + B + " " + K[5] + '="' + E + '"></'
//									+ B + ">");
//					J.left = A.t;
//					i.find("." + E).css(J).html(z).delay().queue(function() {
//						a(this).css(J).html(z).show()
//					})
				}
				G.length != 35 || A.t != 0 ? i.delay().fadeOut() : "";
				d();
				f();
				e();
				m()
			}
			function d() {
				for (var A = 0; A < o; A++) {
					var B = t.eq(A);
					x[A] = B;
					x[A].data("pos", c.moveDistance * A);
					if (c.motionDirection == "y") {
						B.css({
							"position" : "absolute",
							"top" : c.moveDistance * A
						});
						if (A == 0) {
							g.css("top", c.moveDistance * r)
						}
					} else {
						B.css({
							"position" : "absolute",
							"left" : c.moveDistance * A
						});
						if (A == 0) {
							g.css("left", c.moveDistance * r)
						}
					}
				}
				for (var A = 0; A < Math.abs(r); A++) {
					if (c.motionDirection == "y") {
						var z = x[o - 1].data("pos") + c.moveDistance;
						x[0].data("pos", z);
						x[0].css({
							top : z
						});
						x.push(x.shift())
					} else {
						var z = x[o - 1].data("pos") + c.moveDistance;
						x[0].data("pos", z);
						x[0].css({
							left : z
						});
						x.push(x.shift())
					}
				}
				g.css({
					position : "absolute"
				})
			}
			function f() {
				i.bind("mouseenter", function() {
					clearInterval(l)
				}).bind("mouseleave", function() {
					e()
				});
				s.bind("click", function() {
					if (j) {
						j = 0;
						q = "next";
						p()
					}
				});
				h.bind("click", function() {
					if (j) {
						j = 0;
						q = "prev";
						p()
					}
				})
			}
			function p() {
				if (c.motionDirection == "y") {
					if (q == "next") {
						r--
					} else {
						r++;
						var z = x[0].data("pos") - c.moveDistance;
						x[o - 1].data("pos", z);
						x[o - 1].css({
							top : z
						});
						x.unshift(x.pop())
					}
					g.stop().animate({
						top : c.moveDistance * r
					}, c.moveSpeed, function() {
						j = 1;
						if (q == "next") {
							var A = x[o - 1].data("pos") + c.moveDistance;
							x[0].data("pos", A);
							x[0].css({
								top : A
							});
							x.push(x.shift())
						}
						m()
					})
				} else {
					if (c.motionDirection == "x") {
						if (q == "next") {
							r--
						} else {
							r++;
							var z = x[0].data("pos") - c.moveDistance;
							x[o - 1].data("pos", z);
							x[o - 1].css({
								left : z
							});
							x.unshift(x.pop())
						}
						g.stop().animate({
							left : c.moveDistance * r
						}, c.moveSpeed, function() {
							j = 1;
							if (q == "next") {
								var A = x[o - 1].data("pos") + c.moveDistance;
								x[0].data("pos", A);
								x[0].css({
									left : A
								});
								x.push(x.shift())
							}
							m()
						})
					} else {
						if (q == "next") {
							r--;
							var z = x[o - 1].data("pos") + c.moveDistance;
							x[0].data("pos", z);
							x[0].css({
								left : z
							});
							x.push(x.shift())
						} else {
							r++;
							var z = x[0].data("pos") - c.moveDistance;
							x[o - 1].data("pos", z);
							x[o - 1].css({
								left : z
							});
							x.unshift(x.pop())
						}
						g.css({
							left : c.moveDistance * r
						});
						j = 1;
						m()
					}
				}
			}
			function e() {
				l = setInterval(p, c.autoRollingTime)
			}
			function m() {
				if (y) {
					if (q == "next") {
						u = u == o ? 1 : ++u
					} else {
						u = u == 1 ? o : --u
					}
					w.text(u);
					k.text(o)
				}
			}
		})
	}
})(jQuery);
