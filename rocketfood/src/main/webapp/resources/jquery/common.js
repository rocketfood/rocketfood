/**
 * 
 */

$(document).ready(function(){
	//메뉴 
	$('#allCate').click(function(){
		if($(this).hasClass('open')){
			$('.menu_depth1').hide();
			$(this).removeClass('open');
		} else {
			$('.menu_depth1').show();
			$(this).addClass('open');
		}
	});

	$('.menu_depth1_item').mouseover(function(){
		$(this).children('ul').css('left', '320px');
		$(this).children('ul').css('z-index', '1');
	});
	$('.menu_depth1_item').mouseout(function(){
		$(this).children('ul').css('left', '0px');
		$(this).children('ul').css('z-index', '-1');
	});
	
	//탑메뉴 드롭다운
	$('header .dropdown').hide();
	$('#head_link >ul> li').mouseover(function(){
		$(this).find('ul.dropdown').show();
	});
	$('#head_link >ul> li').mouseout(function(){
		$(this).find('ul.dropdown').hide();
	});
	
	//탑버튼
	$(window).scroll(function () {
		if ($(this).scrollTop() > 200) {
			$('#btn_top').fadeIn(300);
		} else {
			$('#btn_top').fadeOut(300);
		}
	});
	$('#btn_top').click(function (event) {
		event.preventDefault();
		$('html, body').animate({ scrollTop: 0 }, 300);
	});
	
});