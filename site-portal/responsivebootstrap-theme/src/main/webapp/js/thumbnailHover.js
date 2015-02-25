$(function($) {

	$('.interactive-thumbnail').hover(function(e) {
		e.preventDefault();

		$('.interactive-thumbnail').removeClass("active"); 
		var myData = $(this).addClass("active");

	},function(e) {
		e.preventDefault();

		$('.interactive-thumbnail').removeClass("active"); 

	});
	
	$('.interactive-thumbnail').focus(function(e) {
		e.preventDefault();
		$('.interactive-thumbnail').removeClass("active"); 
		var myData = $(this).addClass("active");

	});
	
	$('.interactive-thumbnail').blur(function(e) {
		$('.interactive-thumbnail').removeClass("active"); 
		
	});

	
	$('.interactive-thumbnail').click(function(e) {
		$(this).blur();
	});
	


 
});