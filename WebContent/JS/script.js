$(document).ready(function() {
	
	$('.buttonMenu').click(function(e){
	        e.preventDefault();
	        $('body').toggleClass('with--sidebar');
	        $('#container').css('overflow', 'hidden');
	    });
	
	$('.site-cache').click(function(e){
	    $('body').removeClass('with--sidebar');
	    $('#container').css('overflow-y', 'auto');
	});
    
});