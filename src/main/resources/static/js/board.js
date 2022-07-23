/**
 * 
 */
 
 let index = {
	init: function(){
		$("#btn-save").on("click", ()=>{ 
			this.save();
		});
	},
	
	save: function(){

		let data = {
			title: $("#title").val(),
			content: $("#content").val(),
		};
		
		
		console.log(data);
		
		$.ajax({
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data), 
			contentType: "application/json; charset=utf-8", 
			dataType: "json" 	
		}).done(function(resp){ 
			alert("글쓰기가 완료되었습니다.");
			console.log(resp);
			location.href = "/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); //ajax 통신 이용, 3개의 파라미터를 json으로 변경, insert 요청할꺼임
	},
}

index.init();
 