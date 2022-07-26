/**
 * 
 */

let index = {
	init: function() {
		$("#btn-save").on("click", () => {
			this.save();
		});

		$("#btn-delete").on("click", () => {
			this.deleteById();
		});
		$("#btn-update").on("click", () => {
			this.update();
		});

		$("#btn-reply-save").on("click", () => {
			this.replySave();
		});
	},

	save: function() {

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
		}).done(function(resp) {
			alert("글쓰기가 완료되었습니다.");
			console.log(resp);
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		}); //ajax 통신 이용, 3개의 파라미터를 json으로 변경, insert 요청할꺼임
	},

	deleteById: function() {
		let id = $("#id").text();
		$.ajax({
			type: "DELETE",
			url: "/api/board/" + id,
		}).done(function(resp) {
			alert("삭제가 완료되었습니다.");
			console.log(resp);
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		}); //ajax 통신 이용, 3개의 파라미터를 json으로 변경, insert 요청할꺼임
	},
	update: function() {

		let id = $("#id").val();

		let data = {
			title: $("#title").val(),
			content: $("#content").val(),
		};
		console.log(data);

		$.ajax({
			type: "PUT",
			url: "/api/board/" + id,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resp) {
			alert("글수정이 완료되었습니다.");
			console.log(resp);
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		}); //ajax 통신 이용, 3개의 파라미터를 json으로 변경, insert 요청할꺼임
	},
	replySave: function() {

		let id = $("#id").val();

		let data = {
			userId: $("#userId").val(),
			boardId: $("#boardId").val(),
			content: $("#reply-content").val(),
		};
		console.log(data);

		
		$.ajax({
			type: "POST",
			url: `/api/board/${data.boardId}/reply`,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resp) {
			alert("댓글작성이 완료되었습니다.");
			console.log(resp);
			location.href =`/board/${data.boardId}`;
		}).fail(function(error) {
			alert(JSON.stringify(error));
		}); //ajax 통신 이용, 3개의 파라미터를 json으로 변경, insert 요청할꺼임
	},
	replyDelete: function(boardId, replyId) {
		$.ajax({
			type: "DELETE",
			url: `/api/board/${boardId}/reply/${replyId}`,
			dataType: "json"
		}).done(function(resp) {
			alert("댓글 삭제가 완료되었습니다.");
			console.log(resp);
			location.href =`/board/${boardId}`;
		}).fail(function(error) {
			alert(JSON.stringify(error));
		}); //ajax 통신 이용, 3개의 파라미터를 json으로 변경, insert 요청할꺼임
	},


}

index.init();
