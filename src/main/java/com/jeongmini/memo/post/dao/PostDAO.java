package com.jeongmini.memo.post.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jeongmini.memo.post.model.Post;

@Repository
public interface PostDAO {
	public int insertPost(
			@Param("userId") int id,
			@Param("subject") String subject,
			@Param("content") String content,
			@Param("imagePath") String imagePath
			);
	
	public List<Post> selectMemoList(@Param("userId") int userId);
	
	public Post selectMemo(@Param("id") int id,
			@Param("userId") int userId);
	
	public int updatePost(@Param("id") int id,
			@Param("subject") String subject,
			@Param("content") String content);

	public int deletePost(@Param("id") int id,
			@Param("userId") int userId);
}
