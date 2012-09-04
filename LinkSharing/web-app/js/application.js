var urls = {
    markCurrentUnreadUrl:"",
    markCurrentReadUrl:"",
    checkEmailUrl:"",
    checkDocUrl:"",
    checkDocContentTypeUrl:"",
    markReadUrl:"",
    markUnreadUrl:"",
    subscribeUrl:"",
    unsubscribeUrl:"",
    markFavUrl:"",
    unmarkFavUrl:""
};
if (typeof jQuery !== 'undefined') {
    (function ($) {
        $('#spinner').ajaxStart(function () {
            $(this).fadeIn();
        }).ajaxStop(function () {
                $(this).fadeOut();
            });
    })(jQuery);
}
$(document).ready(function () {
    $('#nav_tabs').tabs();
    $('#datepicker').datepicker();
    $('#registrationForm').validate({
        rules:{
            email:{
                required:true,
                email:true,
                remote:urls.checkEmailUrl
            },
            password:{
                required:true,
                maxLength:8
            },
            confirmPassword:{
                required:true,
                equalTo:"#password"
            }
        }
    });
    $('#resource-dialog').dialog({
        autoOpen:false,
        draggable:false,
        modal:true,
        show:'fadeIn',
        hide:'fadeOut',
        width:700,
        resizable:false,
        buttons:{
            Ok:function () {
                $(this).dialog("close")
            },
            Cancel:function () {
                $(this).dialog("close")
            }
        }

    });
    $('#addResource').bind("click", function () {
        $('#resource-dialog').dialog("open")
    });
    $('#docButton').bind("click", function () {
        $('#resource-dialog').dialog("close")
        $('#document-dialog').dialog('open')
    });
    $('#document-dialog').dialog({
        autoOpen:false,
        draggable:false,
        modal:true,
        show:'fadeIn',
        hide:'fadeOut',
        width:800,
        resizable:false,
        buttons:{
            Ok:function () {
                $('form[name=docForm]').submit()
                $(this).dialog("close")
            },
            Cancel:function () {
                $(this).dialog("close")
            }
        }

    });
    $('#link-dialog').dialog({
        autoOpen:false,
        draggable:false,
        modal:true,
        show:'fadeIn',
        hide:'fadeOut',
        width:800,
        resizable:false,
        buttons:{
            Ok:function () {
                $('form[name=linkForm]').submit()
                $(this).dialog("close")
            },
            Cancel:function () {
                $(this).dialog("close")
            }
        }

    });
    $('#linkButton').bind("click", function () {
        $('#resource-dialog').dialog("close")
        $('#link-dialog').dialog('open')
    });

    $('select#selectTopic').bind('change', function () {
        if ($(this).val() == 'Check All') {
            $('input[name=status]').each(function () {
                $(this).attr('checked', true)
            });
        }
        if ($(this).val() == 'Uncheck All') {
            $('input[name=status]').each(function () {
                $(this).attr('checked', false)
            });
        }
        if ($(this).val() == 'Inverse') {
            $('input[name=status]').each(function () {
                if ($(this).is(':checked'))
                    $(this).attr('checked', false)
                else
                    $(this).attr('checked', true)
            });
        }
    });
    $('select#selectReadingItem').bind('change', function () {
        if ($(this).val() == 'Check All') {
            $('input[name=status]').each(function () {
                $(this).attr('checked', true)
            });
        }
        if ($(this).val() == 'Uncheck All') {
            $('input[name=status]').each(function () {
                $(this).attr('checked', false)
            });
        }
        if ($(this).val() == 'Inverse') {
            $('input[name=status]').each(function () {
                if ($(this).is(':checked'))
                    $(this).attr('checked', false)
                else
                    $(this).attr('checked', true)
            });
        }
    });
    $('input[name=mark-read-button]').bind('click', function () {
        var idValues = ''
        $('input#status:checked').each(function () {
            if (idValues.length > 0)
                idValues = idValues + ","
            idValues = idValues + $(this).val()
        });
        $.ajax({
            url:urls.markReadUrl,
            data:"ids=" + idValues,
            success:function (html) {
                $('div#readingItemListDiv').html(html)
            }
        });
    });
    $('input[name=mark-unread-button]').bind('click', function () {
        var idValues = ''
        $('input#status:checked').each(function () {
            if (idValues.length > 0)
                idValues = idValues + ","
            idValues = idValues + $(this).val()
        });
        $.ajax({
            url:urls.markUnreadUrl,
            data:"ids=" + idValues,
            success:function (html) {
                $('div#readingItemListDiv').html(html)
            }
        });
    });
    $('input[name=subscribe-button]').bind('click', function () {
        var idValues = ''
        $('input#status:checked').each(function () {
            if (idValues.length > 0)
                idValues = idValues + ","
            idValues = idValues + $(this).val()
        });
        $.ajax({
            url:urls.subscribeUrl,
            data:"ids=" + idValues,
            success:function (html) {
                $('div#subscriptionListDiv').html(html)
            }
        });
    });
    $('input[name=unsubscribe-button]').bind('click', function () {
        var idValues = ''
        $('input#status:checked').each(function () {
            if (idValues.length > 0)
                idValues = idValues + ","
            idValues = idValues + $(this).val()
        });
        $.ajax({
            url:urls.unsubscribeUrl,
            data:"ids=" + idValues,
            success:function (html) {
                $('div#subscriptionListDiv').html(html)
            }
        });
    });
});
function changeFavStatusUnmark(id) {
    $.ajax({
        url: urls.unmarkFavUrl,
        data:"id="+id,
        success:function(html) {
            $('div#readingItemListDiv').html(html)
        }
    })
}
function changeFavStatusMark(id) {
    $.ajax({
        url: urls.markFavUrl,
        data:"id="+id,
        success:function(html) {
            $('div#readingItemListDiv').html(html)
        }
    })
}
function markCurrentRead(id) {
    $.ajax({
        url:urls.markCurrentReadUrl,
        data:"id="+id,
        success:function(html) {
            $('div#readingItemListDiv').html(html)
        }
    })
}
function markCurrentUnread(id) {
    $.ajax({
        url:urls.markCurrentUnreadUrl,
        data:"id="+id,
        success:function(html) {
            $('div#readingItemListDiv').html(html)
        }
    })
}

