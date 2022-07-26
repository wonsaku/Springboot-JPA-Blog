package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {

	
	//interface 안해서는 public 굳이 안 붙여도 되니까
	@Modifying
	@Query(value="INSERT INTO reply(userId, boardId, content, createDate) VALUES(?1, ?2, ?3, now())", nativeQuery=true) //영속화 시켜서 집어넣을 필요 없다.
	int mSave(int userId, int boardId, String content); // insert, update, delete update된 행의 개수를 리턴해준다.
	
}
