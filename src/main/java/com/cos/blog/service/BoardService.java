package com.cos.blog.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.Board;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;


@Service
public class BoardService {
	@Autowired
	private BoardRepository boardRepository;
	
	
	@Transactional
	public void 글쓰기(Board board, User user) {
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}

	@Transactional(readOnly=true)
	public Page<Board> 글목록(Pageable pageable) {
		return boardRepository.findAll(pageable);

	}

	@Transactional(readOnly=true)
	public Board 글상세보기(int id) {
		return boardRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("글 상세보기 실패");
		});
		
	}


	@Transactional
	public void 글삭제하기(int id) {
		boardRepository.deleteById(id);

	}

	@Transactional
	public void 글수정하기(int id, Board board) {
		Board requestBoard = boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("글 찾기 실패");
				});
		requestBoard.setTitle(board.getTitle());
		requestBoard.setContent(board.getContent());
		//해당 함수 종료시 -> service 종료될 때 트랜잭션 종료. 영속화 컨텍스트 값이 바뀌었으니까 더티체킹. db로 자동 flush 
	}

//	@Transactional(readOnly=true)
//	public User 로그인(User user) {
//		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//	}
}
