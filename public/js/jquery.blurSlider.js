// page init
jQuery(function(){


	jQuery(window).on('load', function(){
		initCycleCarousel(30);
		initMobileCarousel();
		resizeSliderElements();
	});
	jQuery(window).on('resize',function(){
		resizeSliderElements();
		initMobileCarousel();
	});
	
	
	jQuery('.carousel').on('mouseenter', function(){
		jQuery('.main-slider').trigger('pause');
		jQuery('.secondary-slider').trigger('pause');	
	}).on('mouseleave', function(){
		jQuery('.main-slider').trigger('play');
		jQuery('.secondary-slider').trigger('play');	
	});	
	jQuery('.carousel .btn-prev').on('click', function(){
		jQuery('.main-slider').trigger('prev', 1);
		jQuery('.secondary-slider').trigger('prev', 1);
		return false;
	});
	jQuery('.carousel .btn-next').on('click', function(){
		jQuery('.main-slider').trigger('next', 1);
		jQuery('.secondary-slider').trigger('next', 1);
		return false;
	});
	
	
	jQuery('.mobile-blur-slider').on('mouseenter', function(){
		jQuery('.mobile-blur-slider').trigger('pause');
	}).on('mouseleave', function(){
		jQuery('.mobile-blur-slider').trigger('play');	
	});	
	jQuery('#mobile-slider .btn-prev').on('click', function(){
		jQuery('.mobile-blur-slider').trigger('prev', 1);
		return false;
	});
	jQuery('#mobile-slider .btn-next').on('click', function(){
		jQuery('.mobile-blur-slider').trigger('next', 1);
		return false;
	});
	jQuery('#mobile-slider .btn-next').on('click', function(){
		jQuery('.mobile-blur-slider').trigger('next', 1);
		return false;
	});
	
	
});

// fix element heights on window resize
function resizeSliderElements(){
	var sliderHeight = jQuery('.secondary-slider').height();
	var imgHeight = jQuery('.secondary-slider').find('img').height();
	if (imgHeight <= sliderHeight){
		jQuery('.carousel').height(imgHeight);
	} else {
		jQuery('.carousel').height(sliderHeight);
	}
}

// cycle scroll galleries init
function initCycleCarousel(blurAmount) {
	if ( jQuery('.main-slider').length ){
		jQuery('.main-slider').carouFredSel({
			auto: false,
			width: 1440,
		    responsive: true,
		    items: {
		        visible: 1,
		        width: 1440,
		    },
		    scroll: {
		    	duration: 700,
		    	easing: 'easeInOutExpo',
		        onBefore: function(data) {
		        	jQuery('.main-wrap').css({'height':''}).addClass('hidden');
			        data.items.visible.each(function() {
						var imgId = jQuery(this).find('img').attr('id');
						var canvasId = jQuery(this).find('canvas').attr('id');
						stackBlurImage( imgId, canvasId, blurAmount, 0, 2 );
					});

				},
				onAfter: function(data){
					data.items.visible.each(function() {
						var captionHeight = jQuery(this).find('.caption').height() + 80;
						jQuery('.main-wrap').css({'height':captionHeight}).removeClass('hidden');
					});
					
				}
		    },
		    onCreate: function(data) {
		    	var heightSet = false;
		        data.items.each(function() {
					var imgId = jQuery(this).find('img').attr('id');
					var canvasId = jQuery(this).find('canvas').attr('id');
					stackBlurImage( imgId, canvasId, blurAmount, 0, 2 );
					if (!heightSet){
						var captionHeight = jQuery(this).find('.caption').height() + 80;
						jQuery('.main-wrap').css({'height':captionHeight}).removeClass('hidden');
						heightSet = true;
					}
				});
				setTimeout(function(){
					jQuery('.main-slider').animate({'opacity':1},500);
				},500);
			}
		});
		
		jQuery('.secondary-slider').carouFredSel({
			auto: false,
			width: 1440,
		    responsive: true,
		    scroll: {
		    	duration: 700,
		    	easing: 'easeInOutExpo',
		    },
		    items: {
		        visible: 1,
		        width: 1440,
		    },
		    onCreate: function(data) {
		    	setTimeout(function(){
					jQuery('.secondary-slider').animate({'opacity':1},300);
				},100);
			}
		});
	}
		
}

function initMobileCarousel() {
	if (jQuery('.mobile-blur-slider').length){
		jQuery('.mobile-blur-slider').carouFredSel({
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
}