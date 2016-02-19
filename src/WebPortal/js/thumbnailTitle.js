/**
 * Created by Anson on 2016-02-18.
 * Custom Javascript borrowed from http://sevenx.de/demo/bootstrap/thumbnail-hover-caption.html
 */
$( document ).ready(function() {
    $("[rel='tooltip']").tooltip();

    $('.thumbnail').hover(
        function(){
            $(this).find('.caption').slideDown(250); //.fadeIn(250)
        },
        function(){
            $(this).find('.caption').slideUp(250); //.fadeOut(205)
        }
    );
});