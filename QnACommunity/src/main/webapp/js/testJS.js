function validateForm(form){
	alter("form : "+ form);
	if(form.id.value){
		alter("아이디를 입력하세요");
		return false;
	}
	
	if(form.pw.value){
		alter("비밀번호를 입력하세요");
		return false;
	}
	
	if(form.pw.value != form.pw_check.value){
		alter("비밀번호를 다시 확인해주세요");
		return false;
	}
}
