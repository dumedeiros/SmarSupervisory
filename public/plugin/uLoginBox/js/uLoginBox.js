/*!
 * jQuery uLoginBox
 * http://www.userdot.net/#!/jquery
 *
 * Copyright 2011, UserDot www.userdot.net
 * Licensed under the GPL Version 3 license.
 * Version 1.0.0
 *
 */
var uLoginBox = {
    skeleton: '<div id="uLoginBoxOverlay" style="display:none" class="opaque">\n\
                            <div id="uLoginBox" class="shadow top bottom" style="display:none">\n\
                                    <div id="loginBoxHeader" class="top"></div>\n\
                                    <div id="loginBoxContent">\n\
                                            <div class="erroneous erroLogin" id="ajaxMessage">\n\
                                                    <div class="msg-box">\n\
                                                        <label id="loginMessage">msg</label>\n\
                                                    </div>\n\
                                            </div>\n\
                                            <div class="AS">\n\
                                                    <form action="/home" method="get" id="loginForm">\n\
                                                        <ol>\n\
                                                            <li>\n\
                                                                <label for="login">Usuário</label>\n\
                                                                <input name="login" type="text" value=""  id="login-input"/>\n\
                                                            </li>\n\
                                                            <li>\n\
                                                                <label for="senha">Senha</label>\n\
                                                                <input name="senha" type="password" value="" id="senha-input"/>\n\
                                                            </li>\n\
                                                            <li id="send">\n\
                                                                <button class="btn btn-primary" id="botao" type="submit">Ok</button>\n\
                                                                <button class="btn btn-primary" id="loginCancel" type="button">Cancelar</button>\n\
                                                                <span id="loginWaiting" class="notDisplayable"><img src="../../../public/img/load.gif" title="Loader" alt="Loader"/></span>\n\
                                                            </li>\n\
                                                        </ol>\n\
                                                    </form>\n\
                                            </div>\n\
                                    </div>\n\
                                    <div id="loginBoxFooter" class="bottom"></div>\n\
                            </div>\n\
                     </div>',
    settings: {
        override:true,
        background: 'black',
        centerOnResize: true,
        fade: true
    },
    init: function(opts) {
        uLoginBox.settings = uLoginBox.settings ;
        jQuery('body').append(uLoginBox.skeleton);
        uLoginBox.resize();
        
        
    },
    alert: function(options) {
        //        if (uLoginBox.isOpen()) {
        //            return false;
        //        }
        jQuery('#uLoginBox').css({
            width: options.width
        });
        uLoginBox.resize();
        jQuery('#uLoginBox #loginBoxHeader').html('<header>LOGIN</header>');
        
        jQuery('#uLoginBox #loginBoxContent').html(options.text);
        uLoginBox.listen();
        if (uLoginBox.settings.fade) {
            jQuery('#uLoginBoxOverlay').fadeIn();
        }
        else {
            jQuery('#uLoginBoxOverlay').show();
        }
        if (!!window.jQuery.ui) {
            jQuery('#uLoginBox').draggable({
                handle: '#loginBoxHeader, #loginBoxFooter', 
                containment: 'parent'
            }).show();
        }
        else {
            jQuery('#uLoginBox').show();
        }
        if (typeof options.opened == 'function') {
            options.opened.call(this);
        }
        if (typeof options.onClick == 'function') {
            uLoginBox.onClick = options.onClick;
        }
    },
    //    isOpen: function() {
    //        var open = jQuery('#uLoginBox').css('display') == "block";
    //        return open;
    //    },
    clear: function() {
        jQuery('#uLoginBoxOverlay').hide();
    //        jQuery('#uLoginBoxOverlay').remove();
    //        if (uLoginBox.settings.fade) {
    //            jQuery('#uLoginBoxOverlay').fadeOut();
    //        }
    //        else {
    //            jQuery('#uLoginBoxOverlay').fadeOut();
    //        }	
    //        jQuery('body').append(uLoginBox.skeleton);
    //        uLoginBox.resize();
    },
    listen: function() {
        jQuery('#loginBoxFooter input').each(function() {
            jQuery(this).attr({
                'id': this.value
            });
        });
        jQuery('#loginBoxFooter input').click(function() {
            uLoginBox.action(jQuery(this).val());
        });
    },
    action: function(key) {
        if (key == "Cancelar" || key == "Cancel" || key == "Close" || key == "Quit" 
            || key == "Back" || key == "OK") {
            uLoginBox.clear();
        }
        uLoginBox.onClick(key);
    },
    resize: function() {
        var lbox = jQuery('#uLoginBox');
        var height = parseInt((lbox.css('height')).replace('px', ''));
        var width = parseInt((lbox.css('width')).replace('px', ''));
        lbox.css({
            top: (jQuery(window).height()/2 ) - 100 + 'px',
            left: (jQuery(window).width() - width)/2 + 'px'
        });
    },
    onClick: function() {
    },
    destroy: function() {
        jQuery('#uLoginBoxOverlay').remove();
        jQuery(window).unbind('resize');
    }
}