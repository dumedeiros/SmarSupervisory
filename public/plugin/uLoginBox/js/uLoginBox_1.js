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
                                          <label id="loginMessage">aASDAS</label>\n\
                                    </div>\n\
                                    </div>\n\
                                    <div class="AS">\n\
                                <form action="@{Application.redirectHomeSem()}" method="post" id="loginForm">\n\
                                    <label for="username">Username</label>\n\
                                    <input name="username" type="text" value="" required="required" id="matricula"/>\n\
                                    <label for="password">Password</label>\n\
                                    <input name="password" type="password" value="" required="required" id="senha"/>\n\
                                    <input type="submit" value="submit" onMouseOver="this.style.cursor="pointer"">\n\
                                </form>\n\
                            </div>\n\
                        </div>\n\\n\
                              </div>\n\
                            </div>\n\
                            <div id="loginBoxFooter" class="bottom"></div>\n\
                    </div>\n\
            </div>',
    settings: {},
    init: function(opts) {
        uLoginBox.settings = opts;
        $('body').append(uLoginBox.skeleton);
        if (uLoginBox.settings.override) {
            $('<script />').attr({
                type:'text/javascript'
            }).html("function alert(val){ uLoginBox.alert({ title: 'Alert', text: val, rightButtons: ['OK'] }); }").appendTo('head');
            if (uLoginBox.settings.background != "none" && (uLoginBox.settings.background == 'white' || uLoginBox.settings.background == 'black')) {
                $('#uLoginBoxOverlay').addClass(uLoginBox.settings.background);
            }
            else {
                $('#uLoginBoxOverlay').addClass('none');
            }
        }
        if (uLoginBox.settings.centerOnResize) {
            $(window).bind('resize', function() {
                uLoginBox.resize();
            });
        }
        
    },
    alert: function(options) {
        if (uLoginBox.isOpen()) {
            return false;
        }
        $('#uLoginBox').css({
            width: options.width
        });
        uLoginBox.resize();
        $('#uLoginBox #loginBoxHeader').html('<header>'+options.title+'</header>');
        buttons = '';
        lb = options.leftButtons;
        rb = options.rightButtons;
        if (lb) {
            for (var i=(options.leftButtons).length-1; i>=0; i--) {
                buttons += '<input type="button" class="flat" id="'+options.leftButtons[i]+'" value="'+options.leftButtons[i]+'">';
            }
        }
        if (rb) {
            for (var i=(options.rightButtons).length-1; i>=0; i--) {
                //                $("#tombamentoField").val(options.rightButtons[i]);
                buttons += '<input type="button" class="flat floatRight" id="'+options.rightButtons[i]+'" value="'+options.rightButtons[i]+'">';
            }
        }
        if (!lb && !rb) {
        //            Remove o botao ok
        //            buttons += '<input type="button" class="flat floatRight" value="OK">';
        }
        $('#uLoginBox #loginBoxFooter').html(buttons);
        $('#uLoginBox #loginBoxContent').html(options.text);
        uLoginBox.listen();
        if (uLoginBox.settings.fade) {
            $('#uLoginBoxOverlay').fadeIn();
        }
        else {
            $('#uLoginBoxOverlay').show();
        }
        if (!!window.jQuery.ui) {
            $('#uLoginBox').draggable({
                handle: '#loginBoxHeader, #loginBoxFooter', 
                containment: 'parent'
            }).show();
        }
        else {
            $('#uLoginBox').show();
        }
        if (typeof options.opened == 'function') {
            options.opened.call(this);
        }
        if (typeof options.onClick == 'function') {
            uLoginBox.onClick = options.onClick;
        }
    },
    isOpen: function() {
        var open = $('#uLoginBox').css('display') == "block";
        return open;
    },
    clear: function() {
        $('#uLoginBoxOverlay').hide();
//        $('#uLoginBoxOverlay').remove();
//        if (uLoginBox.settings.fade) {
//            $('#uLoginBoxOverlay').fadeOut();
//        }
//        else {
//            $('#uLoginBoxOverlay').fadeOut();
//        }	
//        $('body').append(uLoginBox.skeleton);
//        uLoginBox.resize();
    },
    listen: function() {
        $('#loginBoxFooter input').each(function() {
            $(this).attr({
                'id': this.value
            });
        });
        $('#loginBoxFooter input').click(function() {
            uLoginBox.action($(this).val());
        });
    },
    action: function(key) {
        if (key == "Cancelar" || key == "Cancel" || key == "Close" || key == "Quit" || key == "Back" || key == "OK") {
            uLoginBox.clear();
        }
        uLoginBox.onClick(key);
    },
    resize: function() {
        var lbox = $('#uLoginBox');
        var height = parseInt((lbox.css('height')).replace('px', ''));
        var width = parseInt((lbox.css('width')).replace('px', ''));
        lbox.css({
            top: ($(window).height()/2 ) - 100 + 'px',
            left: ($(window).width() - width)/2 + 'px'
        });
    },
    onClick: function() {
    },
    destroy: function() {
        $('#uLoginBoxOverlay').remove();
        $(window).unbind('resize');
    }
}