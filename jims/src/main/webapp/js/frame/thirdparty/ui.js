jQuery(function($){

	// sidebar
	TriggerClick = 0;
	var Cheight = $(window).height();
	var Cwidth = $(window).width();
	$('#container, #container_02').css({'width':Cwidth - 300 +'px'});

	$('.btn_lock').click(function(){
		if (!$(this).is('.open')) {
			TriggerClick=1;
			$('body').addClass('open-menu');
			$('.sidebar').animate({'left':'-248px'}, 200);
			$(window).resize(function() {
				var Cwidth = $(window).width();
				$('#container, #container_02').css({'left':'44px'});
				$('#container, #container_02').css({'width':Cwidth - 56 +'px'});
			});
			$(this).addClass('open');
			$(window).trigger('resize');
			$(this).find("a").css({"background":"url(/images/main/bg_arrow_off.png) no-repeat 5px 50%"});
			return false;
		} else {
			TriggerClick=0;
			$('body').removeClass('open-menu');
			$('.sidebar').animate({'width':'255px','left':'0','overflow':'hidden'}, 200);
			$(window).resize(function() {
				var Cwidth = $(window).width();
				$('#container, #container_02').css({'left':'287px'});
				$('#container, #container_02').css({'width':Cwidth - 300 +'px'});
			});
			$(this).removeClass('open');
			$(window).trigger('resize');
			$(this).find("a").css({"background":"url(/images/main/bg_arrow.png) no-repeat 5px 50%"});
			return false;
		}
	});


	//lnb
	var navCurrentParam1;
	var navCurrentParam2;
	var navCurrentParam3;
	var navCurrentParam4;
	var navCurrentParam5;

	function hideMenu() {
		$topnavsbmnDepth2.fadeOut(250);
		$topnavsbmnDepth3.fadeOut(250);
		$topnavsbmnDepth4.fadeOut(250);
		$topnavsbmnDepth5.fadeOut(250);
		$topnavsbmn.fadeOut(250);
		if ($(this).is('.on')) {
			$topnavmn.children('a').removeClass('on');
		}
		$topnavmnDepth2.children('a').removeClass('on');
		$topnavmnDepth3.children('a').removeClass('on');
		$topnavmnDepth4.children('a').removeClass('on');
		$topnavmnDepth5.children('a').removeClass('on');
		if ($(this).is('.on')) {
			$('.lnb li a').removeClass('on');
		}
	}

	$('.lnb li:has("ul")').addClass('more');

	var $topnav = $('.lnb'),
		$topnavmn = $topnav.find('>li'),
		$topnavsbmn = $topnavmn.find('>ul');
	$topnavmn.children('a').click(function() {
		if (!$(this).is('.on')) {
			$('.lnb li a').removeClass('on');
		}
		hideMenu();
		if ($(this).parent().is('li:has("ul")')) {
			if (!$(this).is('.on')) {
				$topnavmn.children('a').removeClass('on');
				$topnavsbmn.fadeOut(250).find('>li >ul').hide();
				$(this).addClass('on');
				$(this).parent().find('>ul').slideDown(200).find('>li a').removeClass('on').find('>li a').removeClass('on');
			} else {
				$(this).removeClass('on');
				$(this).parent().find('>ul').fadeOut(250);
			}
			if ( $(this).parent().attr('LINK')==undefined ) {
				return false;
			}
		} else {
			hideMenu();
		}
	});
	
	var $topnavmnDepth2 = $topnavsbmn.find('>li'),
		$topnavsbmnDepth2 = $topnavmnDepth2.find('>ul');
	$topnavmnDepth2.children('a').click(function() {
		$('.lnb > li > ul > li > a').removeClass('on');
		if ($(this).parent().is('li:has("ul")')) {
			if (!$(this).is('.on')) {
				$topnavmnDepth2.children('a').removeClass('open');
				$topnavsbmnDepth2.slideUp(200);
				$(this).addClass('on');
				$(this).parent().find('>ul').slideDown(200).find('>li a').removeClass('on').find('>li a').removeClass('on');
			} else {
				$(this).removeClass('on');
				$(this).parent().find('>ul').slideUp(200);
			}
			if ( $(this).parent().attr('LINK')==undefined ) {
				return false;
			}
		} else {
			//hideMenu(); 2단계에서는 메뉴 숨기는 기능 없애달라는 요청,KJM 2018.11.8
		}
	});

	var $topnavmnDepth3 = $topnavsbmnDepth2.find('>li'),
		$topnavsbmnDepth3 = $topnavmnDepth3.find('>ul');
	$topnavmnDepth3.children('a').click(function() {
		$('.lnb > li > ul > li > ul > li > a').removeClass('on');
		if ($(this).parent().is('li:has("ul")')) {
			if (!$(this).is('.on')) {
				$topnavmnDepth3.children('a').removeClass('on');
				$topnavsbmnDepth3.slideUp(200);
				$(this).addClass('on');
				$(this).parent().find('>ul').slideDown(200).find('>li a').removeClass('on').find('>li a').removeClass('on');
			} else {
				$(this).removeClass('on');
				$(this).parent().find('>ul').slideUp(200);
			}
			if ( $(this).parent().attr('LINK')==undefined ) {
				return false;
			}
		} else {
			hideMenu();
		}
	});

	var $topnavmnDepth4 = $topnavsbmnDepth3.find('>li'),
		$topnavsbmnDepth4 = $topnavmnDepth4.find('>ul');
	$topnavmnDepth4.children('a').click(function() {
		$('.lnb > li > ul > li > ul > li > a').removeClass('on');
		if ($(this).parent().is('li:has("ul")')) {
			if (!$(this).is('.on')) {
				$topnavmnDepth4.children('a').removeClass('open');
				$topnavsbmnDepth4.slideUp(200);
				$(this).addClass('on');
				$(this).parent().find('>ul').slideDown(200).find('>li a').removeClass('on').find('>li a').removeClass('on');
			} else {
				$(this).removeClass('on');
				$(this).parent().find('>ul').slideUp(200);
			}
			if ( $(this).parent().attr('LINK')==undefined ) {
				return false;
			}
		} else {
			hideMenu();
		}
	});

	var $topnavmnDepth5 = $topnavsbmnDepth4.find('>li'),
		$topnavsbmnDepth5 = $topnavmnDepth5.find('>ul');
	$topnavmnDepth5.children('a').click(function() {
		$('.lnb > li > ul > li > ul > li > ul > li > a').removeClass('on');
		if ($(this).parent().is('li:has("ul")')) {
			if (!$(this).is('.on')) {
				$topnavmnDepth5.children('a').removeClass('open');
				$topnavsbmnDepth5.slideUp(200);
				$(this).addClass('on');
				$(this).parent().find('>ul').slideDown(200).find('>li a').removeClass('on').find('>li a').removeClass('on');
			} else {
				$(this).removeClass('on');
				$(this).parent().find('>ul').slideUp(200);
			}
			if ( $(this).parent().attr('LINK')==undefined ) {
				return false;
			}
		} else {
			hideMenu();
		}
	});
	
	var $topnavCurrent1 = $topnav.find('.navm'+navCurrentParam1);
	var $topnavCurrent2 = $topnav.find('.navm'+navCurrentParam1+'_'+navCurrentParam2);
	var $topnavCurrent3 = $topnav.find('.navm'+navCurrentParam1+'_'+navCurrentParam2+'_'+navCurrentParam3);
	var $topnavCurrent4 = $topnav.find('.navm'+navCurrentParam1+'_'+navCurrentParam2+'_'+navCurrentParam3+'_'+navCurrentParam4);
	var $topnavCurrent5 = $topnav.find('.navm'+navCurrentParam1+'_'+navCurrentParam2+'_'+navCurrentParam3+'_'+navCurrentParam4+'_'+navCurrentParam5);
	function currentReset() {
		if (!$topnavCurrent1.children('a').is('.on')) {
			$topnavmn.children('a').removeClass('on').parent().find('>ul').hide().find('>li a').removeClass('on');
			$topnavCurrent1.children('a').addClass('on').parent().find('>ul').show();
			$topnavCurrent2.children('a').addClass('on');
			$topnavCurrent3.children('a').addClass('on');
			$topnavCurrent4.children('a').addClass('on');
			$topnavCurrent5.children('a').addClass('on');
		}
	}
	currentReset();

	 //메인 >> 이미지맵 반응형
	 
	 // 파일선택
	 	$('.upload_text').val('...');
		$('.input_file').change(function(){
			var i = $(this).val();
			$('.upload_text').val(i);
		});
});