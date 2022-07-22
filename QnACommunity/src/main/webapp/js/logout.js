/**
 * 
 */

var startBtns = document.querySelectorAll('.logout_popup_on');
Array.from(startBtns).forEach((element) => {
    element.addEventListener('click', function(){
        document.getElementById('logout_popup').style.display = 'block';
    });
})

var popup = document.querySelector('.logout_no');
popup.addEventListener('click', function(){
    document.getElementById('logout_popup').style.display = 'none';
});
