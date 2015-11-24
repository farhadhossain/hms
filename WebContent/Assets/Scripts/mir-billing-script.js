
jQuery(document).ready(function () {

    /* select menu */
    jQuery('.sidebar-nav ul:first').addClass('clearfix');
    
    jQuery('.alert').each(function () {
        var html = jQuery(this).html();
        html = '<button data-dismiss="alert" class="close">&#215;</button>' + html;
        jQuery(this).html(html);
    });


    jQuery('.timepicker').timepicker({
        minuteStep: 1,
        template: 'modal',
        showSeconds: true,
        showMeridian: false
    });


    jQuery('a[title]').tooltip();

    jQuery('.date').datepicker().on('changeDate', function (ev) {
        jQuery(this).datepicker('hide');
    });

});


function isValidEmailAddress(emailAddress) {
    var pattern = new RegExp(/^(("[\w-\s]+")|([\w-]+(?:\.[\w-]+)*)|("[\w-\s]+")([\w-]+(?:\.[\w-]+)*))(@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)|(@\[?((25[0-5]\.|2[0-4][0-9]\.|1[0-9]{2}\.|[0-9]{1,2}\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\]?$)/i);
    return pattern.test(emailAddress);
}


/**
 * cross-browser html5 placeholder support script
 ********************************************************************************/
(function ($) {

    /**
    * Spoofs placeholders in browsers that don't support them (eg Firefox 3)
    *
    * Copyright 2011 Dan Bentley
    * Licensed under the Apache License 2.0
    *
    * Author: Dan Bentley [github.com/danbentley]
    */

    // Return if native support is available.
    if ("placeholder" in document.createElement("input")) return;

    $(document).ready(function () {
        $(':input[placeholder]').not(':password').each(function () {
            setupPlaceholder($(this));
        });

        $(':password[placeholder]').each(function () {
            setupPasswords($(this));
        });

        $('form').submit(function (e) {
            clearPlaceholdersBeforeSubmit($(this));
        });
    });

    function setupPlaceholder(input) {

        var placeholderText = input.attr('placeholder');

        setPlaceholderOrFlagChanged(input, placeholderText);
        input.focus(function (e) {
            if (input.data('changed') === true) return;
            if (input.val() === placeholderText) input.val('');
        }).blur(function (e) {
            if (input.val() === '') input.val(placeholderText);
        }).change(function (e) {
            input.data('changed', input.val() !== '');
        });
    }

    function setPlaceholderOrFlagChanged(input, text) {
        (input.val() === '') ? input.val(text) : input.data('changed', true);
    }

    function setupPasswords(input) {
        var passwordPlaceholder = createPasswordPlaceholder(input);
        input.after(passwordPlaceholder);

        (input.val() === '') ? input.hide() : passwordPlaceholder.hide();

        $(input).blur(function (e) {
            if (input.val() !== '') return;
            input.hide();
            passwordPlaceholder.show();
        });

        $(passwordPlaceholder).focus(function (e) {
            input.show().focus();
            passwordPlaceholder.hide();
        });
    }

    function createPasswordPlaceholder(input) {
        return $('<input>').attr({
            placeholder: input.attr('placeholder'),
            value: input.attr('placeholder'),
            id: input.attr('id'),
            readonly: true
        }).addClass(input.attr('class'));
    }

    function clearPlaceholdersBeforeSubmit(form) {
        form.find(':input[placeholder]').each(function () {
            if ($(this).data('changed') === true) return;
            if ($(this).val() === $(this).attr('placeholder')) $(this).val('');
        });
    }
})(jQuery);