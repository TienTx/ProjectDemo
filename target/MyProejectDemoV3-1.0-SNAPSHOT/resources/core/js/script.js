
//get context path
function getContextPath() {
    return window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
}
var contextPath = getContextPath();

//back to top and fixed navigation event
function fBackToTop() {
    if ($('#back-to-top').length) {
        var scrollTrigger = 583,
                fnc = function () {
                    var scrollTop = $(window).scrollTop();
                    if (scrollTop > scrollTrigger) {
                        $('#back-to-top').addClass('show');
                    } else {
                        $('#back-to-top').removeClass('show');
                    }
                };
        fnc();
        $(window).on('scroll', function () {
            fnc();
        });
        $('#back-to-top').on('click', function (e) {
            e.preventDefault();
            $('html,body').animate({
                scrollTop: 0
            }, 700);
        });
    }
}

$(document).ready(function () {

    fBackToTop();
});