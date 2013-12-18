(function($){
    
    var initLayout = function() {
        
        $('.dataInicial').DatePicker({
            flat: true,
            date: '2008-07-31',
            current: '2008-07-31',
            calendars: 1,
            starts: 1,
            view: 'years'
        });
        $('#date').DatePicker({
            flat: true,
            date: '2008-07-31',
            current: '2008-07-31',
            calendars: 1,
            starts: 1,
            view: 'years'
        });
        var now = new Date();
        now.addDays(-10);
        var now2 = new Date();
        now2.addDays(-5);
        now2.setHours(0,0,0,0);
        $('#date2').DatePicker({
            flat: true,
            date: ['2008-07-31', '2008-07-28'],
            current: '2008-07-31',
            format: 'Y-m-d',
            calendars: 1,
            mode: 'multiple',
            onRender: function(date) {
                return {
                    disabled: (date.valueOf() < now.valueOf()),
                    className: date.valueOf() == now2.valueOf() ? 'datepickerSpecial' : false
                }
            },
            onChange: function(formated, dates) {
            },
            starts: 0
        });
        $('#clearSelection').bind('click', function(){
            $('#date3').DatePickerClear();
            return false;
        });
        $('#date3').DatePicker({
            flat: true,
            date: ['2009-12-28','2010-01-23'],
            current: '2010-01-01',
            calendars: 3,
            mode: 'range',
            starts: 1
        });
        
        var now = new Date();
        
        $('#dataDevolucaoEmprestimo').DatePicker({
            format:'d/m/Y',
            date: now,
            current: now,
            starts: 1,
            position: 'right',
            onRender: function(date) {
                return {
                    disabled: (date.valueOf() < now.valueOf())
                //                    className: date.valueOf() == now.valueOf() ? 'datepickerSpecial' : false
                }
            },
            onBeforeShow: function(){
                $('#dataDevolucaoEmprestimo').DatePickerSetDate(now, true);
            },
            onChange: function(formated, dates){
                $('#dataDevolucaoEmprestimo').val(formated);
            }
        });
        $('#dataInicial').DatePicker({
            format:'d/m/Y',
            date: now,
            current: now,
            starts: 1,
            position: 'right',
            onBeforeShow: function(){
                $('#dataInicial').DatePickerSetDate(now, true);
            },
            onChange: function(formated, dates){
                $('#dataInicial').val(formated);
            }
        });
        $('#dataFinal').DatePicker({
            format:'d/m/Y',
            date: now,
            current: now,
            starts: 1,
            position: 'right',
            onBeforeShow: function(){
                $('#dataFinal').DatePickerSetDate(now, true);
            },
            onChange: function(formated, dates){
                $('#dataFinal').val(formated);
            }
        });
        
        $('#dataAquisicao').DatePicker({
            format:'d/m/Y',
            date: now,
            current: now,
            starts: 1,
            position: 'right',
            onBeforeShow: function(){
                $('#dataAquisicao').DatePickerSetDate(now, true);
            },
            onChange: function(formated, dates){
                $('#dataAquisicao').val(formated);
            }
        });

        
        var now3 = new Date();
        now3.addDays(-4);
        var now4 = new Date()
        $('#widgetCalendar').DatePicker({
            flat: true,
            format: 'd B, Y',
            date: [new Date(now3), new Date(now4)],
            calendars: 3,
            mode: 'range',
            starts: 1,
            onChange: function(formated) {
                $('#widgetField span').get(0).innerHTML = formated.join(' &divide; ');
            }
        });
        var state = false;
        $('#widgetField>a').bind('click', function(){
            $('#widgetCalendar').stop().animate({
                height: state ? 0 : $('#widgetCalendar div.datepicker').get(0).offsetHeight
            }, 500);
            state = !state;
            return false;
        });
        $('#widgetCalendar div.datepicker').css('position', 'absolute');
    };
	
    var showTab = function(e) {
        var tabIndex = $('ul.navigationTabs a')
        .removeClass('active')
        .index(this);
        $(this)
        .addClass('active')
        .blur();
        $('div.tab')
        .hide()
        .eq(tabIndex)
        .show();
    };
	
    EYE.register(initLayout, 'init');
})(jQuery)