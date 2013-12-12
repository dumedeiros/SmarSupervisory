// page init
jQuery(document).ready(function($) {
	
	$('ul#mobileNav').slicknav({
		label: '',
		duration: 400,
		easingOpen: "easeOutExpo", //available with jQuery UI
		easingClose: "easeOutExpo",
		prependTo:'#mobileSlickNav'
	});

	$('.tweets-carousel').on('mouseenter', function(){
		$('.tweets-carousel').trigger('pause');
	}).on('mouseleave', function(){
		$('.tweets-carousel').trigger('play');	
	});	
	$('#recent-tweets .btn-prev').on('click', function(){
		$('.tweets-carousel').trigger('prev', 1);
		return false;
	});
	$('#recent-tweets .btn-next').on('click', function(){
		$('.tweets-carousel').trigger('next', 1);
		return false;
	});
	$('#recent-tweets .btn-next').on('click', function(){
		$('.tweets-carousel').trigger('next', 1);
		return false;
	});

	$('.social-search').on('click','.search',function(){
		$(this).addClass("active");
		$(this).parent().find('a.social').hide();
		$(this).find('form input[type=text]').focus();
	});

	$(document).on('focusin', '.field', function() {
		if(this.title==this.value) {
			this.value = '';
		}
	}).on('focusout', '.field', function(){
		if(this.value=='') {
			this.value = this.title;
		}
	});
    
    $('.dropdown').animate({opacity:0},0);
    
    $('nav ul > li').on('mouseenter', function() {
    	if($(this).find('.dropdown').length) {
    		if($.browser.msie && $.browser.version < 9) {
                $(this).find('.dropdown').show().animate({opacity:1},0);
            } else {
                $(this).find('.dropdown').show().stop().animate({opacity:1},150,'easeOutQuad');
            }
    	}
    }).on('mouseleave', function() {
    	if($(this).find('.dropdown').length) {
    		if($.browser.msie && $.browser.version < 9) {
                $(this).find('.dropdown').animate({opacity:0},0,function(){
                	$(this).hide();
                });
            } else {
                $(this).find('.dropdown').stop().animate({opacity:0},150,'easeInQuad',function(){
                	$(this).hide();
                });
            }
    	}
    });
    
    $('body').fitVids();
    
    $(window).on('load', function(){
    	initCarousels();    	
    });
    
    if (jQuery('.Collage').length){
    var resizeTimer = null;
		$(window).bind('resize', function() {
		    $('.Collage .Image_Wrapper').css("opacity", 0);
		    if (resizeTimer) clearTimeout(resizeTimer);
		    resizeTimer = setTimeout(initCarousels, 400);
		});
	}

});

// cycle scroll galleries init
function initCarousels() {

	if (jQuery('.tweets-carousel').length){
		jQuery('.tweets-carousel').carouFredSel({
			auto: false,
			width: 1440,
			height: 'variable',
		    responsive: true,
		    items: {
		        visible: 1,
		        width: 1440,
		    },
		    scroll: {
		    	duration: 700,
		    	easing: 'easeInOutExpo'
		    }
		});
	}

	if (jQuery('.Collage').length){
		jQuery('.Collage').collageCaption().removeWhitespace().collagePlus({
            'targetHeight'    		: 400,
            'fadeSpeed'       		: 2000,
            'effect'          		: 'effect-2',
            'direction'       		: 'horizontal',
            'allowPartialLastRow'	: false
		});
		jQuery('.Collage a').magnificPopup({
			type:'image',
			mainClass: 'mfp-with-zoom', // this class is for CSS animation below
			zoom: {
				enabled: true, // By default it's false, so don't forget to enable it
				duration: 300, // duration of the effect, in milliseconds
				easing: 'ease-in-out', // CSS transition easing function 
				opener: function(openerElement) {
					return openerElement.is('img') ? openerElement : openerElement.find('img');
				}
			},
			gallery: {
				enabled: true, // set to true to enable gallery
				preload: [0,2], // read about this option in next Lazy-loading section
				navigateByImgClick: true,
				arrowMarkup: '<button title="%title%" type="button" class="mfp-arrow mfp-arrow-%dir%"></button>', // markup of an arrow button
				tPrev: 'Previous (Left arrow key)', // title for left button
				tNext: 'Next (Right arrow key)', // title for right button
				tCounter: '<span class="mfp-counter">%curr% of %total%</span>' // markup of counter
			}
		});
		
	}

}