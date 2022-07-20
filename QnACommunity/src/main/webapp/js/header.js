var items = document.querySelectorAll('.main_menu> li');
var sub_menus = document.querySelectorAll('.sub_menu');

// 메뉴 영역에 커서를 옮겼을 때 서브메뉴 활성화
items.forEach(item =>{
    item.addEventListener('mouseenter', (event)=>{
        // sub_menus.forEach(menu => menu.classList.remove('on')); // 모든 서브메뉴 닫음.
        event.target.querySelector('.sub_menu').classList.add('on');    // 선택된 서브메뉴 열음
    })
})
// 메뉴 영역에 커서를 벗어났을 때 서브메뉴 비활성화
items.forEach(item =>{
    item.addEventListener('mouseleave', (event)=>{
        // sub_menus.forEach(menu => menu.classList.remove('on')); // 모든 서브메뉴 닫음.
        event.target.querySelector('.sub_menu').classList.remove('on');    // 선택된 서브메뉴 열음
    })
})