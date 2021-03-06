/**
 * 
 */
 
 let index = {
	init: function(){
		$("#btn-save").on("click", ()=>{ //function(){}.  ()=>{} this를 바인딩하기 위해서 !!
			this.save();
		});
		$("#btn-update").on("click", ()=>{ //function(){}.  ()=>{} this를 바인딩하기 위해서 !!
			this.update();
		});
	},
	
	save: function(){
		//alert('user의 save');
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val(),
		};
		
		
		console.log(data);
		// ajax 호출시 default가 비동기 호출
		$.ajax({
			type: "POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data), // http 바디 데이터 (MIME type 필요 헤더에서) //요청
			contentType: "application/json; charset=utf-8", //요청 
			dataType: "json" // 응답이 왔을 때 응답 결과가 text, int 등등이 올 수 있는데 기본적으로는 문자열
			                 // 생긴게 json이라면 javascript object로 변환
			
		}).done(function(resp){ //자바스크립트 object로 변환하여 resp 변수에 담아준다.
			if(resp.status === 500){
				alert("회원가입에 실패하였습니다.");
			} else{
				alert("회원가입이 완료되었습니다.");
				console.log(resp);
				location.href = "/";
			}
			
			
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); //ajax 통신 이용, 3개의 파라미터를 json으로 변경, insert 요청할꺼임
	},
	
	update: function(){
		//alert('user의 save');
		let data = {
			id: $("#id").val(),
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val(),
		};
		
		
		console.log(data);
		// ajax 호출시 default가 비동기 호출
		$.ajax({
			type: "PUT",
			url: "/user",
			data: JSON.stringify(data), // http 바디 데이터 (MIME type 필요 헤더에서) //요청
			contentType: "application/json; charset=utf-8", //요청 
			dataType: "json" // 응답이 왔을 때 응답 결과가 text, int 등등이 올 수 있는데 기본적으로는 문자열
			                 // 생긴게 json이라면 javascript object로 변환
			
		}).done(function(resp){ //자바스크립트 object로 변환하여 resp 변수에 담아준다.
			alert("회원수정이 완료되었습니다.");
			console.log(resp);
			location.href = "/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); //ajax 통신 이용, 3개의 파라미터를 json으로 변경, insert 요청할꺼임
	},
}

index.init();
 