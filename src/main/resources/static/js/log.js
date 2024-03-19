document.getElementById('prev').addEventListener('click', function() {
    toggleSlide('prev');
});

document.getElementById('next').addEventListener('click', function() {
    toggleSlide('next');
});

function toggleSlide(direction) {
    var lastWeek = document.getElementById('lastWeek');
    var thisWeek = document.getElementById('thisWeek');
    
    // 방향에 따라 슬라이드 전환
    if (direction === 'next') {
        if (lastWeek.classList.contains('active')) {
            lastWeek.classList.remove('active');
            lastWeek.style.display = 'none';
            thisWeek.classList.add('active');
            thisWeek.style.display = 'block';
        }
    } else if (direction === 'prev') {
        if (thisWeek.classList.contains('active')) {
            thisWeek.classList.remove('active');
            thisWeek.style.display = 'none';
            lastWeek.classList.add('active');
            lastWeek.style.display = 'block';
        }
    }
}

// 초기 화면 설정
document.getElementById('lastWeek').classList.add('active');
document.getElementById('lastWeek').style.display = 'block';
