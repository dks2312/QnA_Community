$(document).ready(function() {
	$('#summernote').summernote({
        height: 700,                    // 에디터 높이
        minHeight: null,                // 최소 높이
        maxHeight: null,                // 최대 높이
        focus: true,                    // 에디터 로딩후 포커스를 맞출지 여부
        lang: "ko-KR",					// 한글 설정
        placeholder: '내용을 입력하세요'	//placeholder 설정
    });

    // 서머노트에 text 쓰기
    // $('#summernote').summernote('insertText', "써머노트에 쓸 텍스트");


    // 서머노트 쓰기 비활성화
    // $('#summernote').summernote('disable');
    // 서머노트 쓰기 활성화
    // $('#summernote').summernote('enable');
    // 서머노트 리셋
    // $('#summernote').summernote('reset');
    // 마지막으로 한 행동 취소 ( 뒤로가기 )
    // $('#summernote').summernote('undo');
    //  앞으로가기
    // $('#summernote').summernote('redo');
});