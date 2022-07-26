package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplyRepository;
import com.cos.blog.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	/*
	 * @Autowired private BoardRepository boardRepository;
	 * 
	 * @Autowired private ReplyRepository replyRepository;
	 */
	
	/*
	 * public BoardService(BoardRepository bRepo, ReplyRepository rRepo) {
	 * this.boardRepository = bRepo; this.replyRepository = rRepo; }
	 */
	
	private final BoardRepository boardRepository;
	private final ReplyRepository replyRepository;



	@Transactional
	public void 글쓰기(Board board, User user) {
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}

	@Transactional(readOnly = true)
	public Page<Board> 글목록(Pageable pageable) {
		return boardRepository.findAll(pageable);

	}

	@Transactional(readOnly = true)
	public Board 글상세보기(int id) {
		return boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("글 상세보기 실패");
		});

	}

	@Transactional
	public void 글삭제하기(int id) {
		boardRepository.deleteById(id);

	}

	@Transactional
	public void 글수정하기(int id, Board board) {
		Board requestBoard = boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("글 찾기 실패");
		});
		requestBoard.setTitle(board.getTitle());
		requestBoard.setContent(board.getContent());
		// 해당 함수 종료시 -> service 종료될 때 트랜잭션 종료. 영속화 컨텍스트 값이 바뀌었으니까 더티체킹. db로 자동 flush
	}

	/*
	 * ¿@Transactional public void 댓글쓰기(ReplySaveRequestDto reply) {
	 * 
	 * Board board = boardRepository.findById(boardId) .orElseThrow(()->{ return new
	 * IllegalArgumentException("댓글 쓰기 실패: 게시글 id를 찾을 수 없습니다."); });
	 * 
	 * requestReply.setUser(user); requestReply.setBoard(board);
	 * 
	 * replyRepository.save(requestReply); }
	 */

	@Transactional
	public void 댓글쓰기(ReplySaveRequestDto requestReply) {
		/*
		 * User user = userRepository.findById(requestReply.getUserId())
		 * .orElseThrow(()->{ return new
		 * IllegalArgumentException("댓글 쓰기 실패: 유저 id를 찾을 수 없습니다."); });
		 * 
		 * 
		 * Board board = boardRepository.findById(requestReply.getBoardId())
		 * .orElseThrow(()->{ return new
		 * IllegalArgumentException("댓글 쓰기 실패: 게시글 id를 찾을 수 없습니다."); });
		 * 
		 * Reply reply = Reply.builder() .user(user) .board(board)
		 * .content(requestReply.getContent()) .build();
		 * 
		 * Reply reply = new Reply(); reply.update(user, board,
		 * requestReply.getContent());
		 */

		//replyRepository.save(reply);
		
		int result = replyRepository.mSave(requestReply.getUserId(), requestReply.getBoardId(), requestReply.getContent());
		System.out.println("test: " + result);
	}

	@Transactional
	public void 댓글삭제(int replyId) {
		replyRepository.deleteById(replyId);
	}

//	@Transactional(readOnly=true)
//	public User 로그인(User user) {
//		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//	}
}
