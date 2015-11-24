
$(document).ready(function () {

    // Left side navigation: Minimize
    $("#side_nav a.minimize").click(function () {

        $("#side_nav").toggleClass('closed', 800);
        $("#side_nav > ul li a > span").fadeToggle();
        $("#side_nav > ul li > span.icon").fadeToggle();

        // resize main container and side nav
        if ($("#main_container").hasClass('span10')) {
            $('li.openable').siblings().removeClass('active').children('div.accordion').slideUp();
        }

        $(this).toggleClass('minimize_closed');

        // change logo type
        if ($(this).hasClass('minimize_closed')) {
            $('li.openable').siblings().removeClass('active').children('div.accordion').slideUp();
        }

        return false;

        $('#side_nav > ul > li.openable > div.accordion').slideUp().parent().removeClass('active');
        $('#side_nav > ul > li.openable > div.accordion').prev().removeClass('active');

    });

    // Left side navigation: Accordion 	
    $('li .accordion').parent().addClass('openable');
    //Set default open/close settings
    $('li .accordion').hide(); //Hide/close all containers

    //Left side navigation: On Click
    $('li.openable').live('click', function () {
        if ($(this).children('div.accordion').is(':hidden')) {
            //If immediate next container is closed...	
            $(this).siblings().removeClass('active').children('div.accordion').slideUp(); //Remove all "active" state and slide up the immediate next container
            $(this).toggleClass('active').children('div.accordion').slideDown(); //Add "active" state to clicked trigger and slide down the immediate next container

            // set cookie of the current menuid
            $.cookie('sidebar-selected-menu-id', $(this).attr('id'), { domain: document.domain, path: '/' });
        }
        else {
            $(this).removeClass('active').children('div.accordion').slideUp();
        }

        if ($(this).parent().parent().is('#side_nav')) {
            openTheSidenav();
        }

        return false; //Prevent the browser jump to the link anchor
    });

    // delete cookie of the current menuid if menu item other than openable
    $('.sidebar-nav li').live('click', function () {
        if (!$(this).hasClass('openable')) {
            $.cookie('sidebar-selected-menu-id', null, { domain: document.domain, path: '/' });
        }
    });


    // Left side navigation: external url open a new window
    $('ul.slide_left').siblings('span').addClass('left');
    $('.accordion > a').live('click', function () {
        var url = $(this).attr('href');
        window.location = url;
    });

    // Left side navigation: Open side nav function
    function openTheSidenav() {
        $("#side_nav").removeClass('closed', 800);
        $("#side_nav > ul li a span").fadeIn();
        $("#side_nav > ul li span.icon").fadeIn();
        $("#side_nav > ul li a span.icon").fadeIn();

        $(this).removeClass('minimize_closed');
    }


    /* manipulate menu html to implement accordion */
    jQuery('.sidebar-nav ul.level-1').children('li').each(function () {

        var ulObj = jQuery(this).find('ul');
        var noOfUls = ulObj.length;

        if (noOfUls == 1) {

            jQuery(this).find('ul').remove();
            jQuery(this).addClass('openable');
            var lis = jQuery(ulObj).children('li');

            var newMenuHtml = '';
            newMenuHtml += '<div class="accordion" style="display: none;">';
            for (var i = 0; i < lis.length; i++) {
                newMenuHtml += jQuery(lis[i]).html();
            }
            newMenuHtml += '</div>';

            jQuery(this).append(newMenuHtml);
        }

    });



    //alert($.cookie('sidebar-selected-menu-id'));
    if ($.cookie('sidebar-selected-menu-id') != null || $.cookie('sidebar-selected-menu-id') != '') {

        var menuId = $.cookie('sidebar-selected-menu-id');

        $('#' + menuId).siblings().removeClass('active').children('div.accordion').slideUp();
        $('#' + menuId).toggleClass('active').children('div.accordion').slideDown();

    }


});  		