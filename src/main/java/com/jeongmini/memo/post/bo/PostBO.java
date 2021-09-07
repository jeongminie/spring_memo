package com.jeongmini.memo.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jeongmini.memo.common.fileManagerService;
import com.jeongmini.memo.post.dao.PostDAO;
import com.jeongmini.memo.post.model.Post;

@Service
public class PostBO {
	@Autowired
	private PostDAO postDAO;
	
	public int addPost(int userId, String title, String content, MultipartFile file) {
		
		String filepath = null;
		if(file != null) {
			fileManagerService fileManager = new fileManagerService();
			
			filepath = fileManager.saveFile(userId, file);
			
			if(filepath == null) {
				return -1;
			}
		
		}
		
		return postDAO.insertPost(userId, title, content, filepath);
	}
	
	public List<Post> getMemoList(int userId){
		return postDAO.selectMemoList(userId);
	}
	
	public Post getMemo(int id, int userId) {
		return postDAO.selectMemo(id, userId);
	}

	public int updatePost(int id, String subject, String content) {
		return postDAO.updatePost(id, subject, content);
	}
	
	public int deletePost(int id, int userId) {
		return postDAO.deletePost(id, userId);
	}
}
