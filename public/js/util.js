var KEYCODE_ESC = 27;
var KEYCODE_ENTER = 13;
 
function showErro(msg){
    jQuery('#centralMessage').addClass("erroneous").show(500).children().text(msg);
}

function showInfo(msg){
    jQuery('#centralMessage').addClass("informe").show(500).children().text(msg);
}

function showWarning(msg){
    jQuery('#centralMessage').addClass("warning").show(500).children().text(msg);
}

function hideMessage(){
    jQuery('#centralMessage').hide();
}

function disableButton(id){
    jQuery(id).attr("disabled", "disabled");
}

function enableButton(id){
    jQuery(id).removeAttr("disabled");
}

function maskare(id, mask){
    jQuery(function(jQuery){
        jQuery(id).mask(mask);
    });
    
}

function initULightBox(){
    uLightBox.init({
        override:true,
        background: 'black',
        centerOnResize: true,
        fade: true
    });
}

function initLoginBox(){
    uLoginBox.init({
        });
}

function hasError(str){
    return str.indexOf("ERRO:") != -1;
}

function errorValue(str){
    return str.substr(str.indexOf("ERRO:") + 5);
}

function updateBagQuantity(){
    jQuery.ajax({
        type : 'GET',
        url: '/getBagQuantity', 
        dataType : 'json',
        success : function(qtd){
            jQuery("#bagQuantity").text(qtd);
        }
    });
}

function disableInput(id){
    jQuery(id).attr("disabled", true);
    jQuery(id).addClass("inputDisabled");
}

function enableInput(id){
    jQuery(id).attr("disabled", false);
    jQuery(id).removeClass("inputDisabled");
}
