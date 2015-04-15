$(function() {
	
	$('.buttonMenu').click(function(e){
        e.preventDefault();
        $('body').toggleClass('with--sidebar');
        $('#container').css('overflow', 'hidden');
    });
	
	$('.site-cache').click(function(e){
	    $('body').removeClass('with--sidebar');
	    $('#container').css('overflow-y', 'auto');
	});
	
	// Récupérer l'url courant, et appliquer la classe 'clicked' sur le lien en question
	var url = location.pathname
	$('nav > a[href="'+url+'"]').addClass('clicked')
});