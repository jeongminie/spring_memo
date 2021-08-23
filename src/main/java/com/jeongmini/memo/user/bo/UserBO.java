package com.jeongmini.memo.user.bo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeongmini.memo.common.EncryptUtils;
import com.jeongmini.memo.user.dao.UserDAO;
import com.jeongmini.memo.user.model.User;

@Service
public class UserBO {
	@Autowired
	private UserDAO userDAO;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public int signUp(String loginId, String password, String name, String email) {
		String encryptPassword = EncryptUtils.md5(password);
		if(encryptPassword.equals("")) {
			logger.error("[UserBO signUp] 암호화 실패");
			return 0;
		}
		
		return userDAO.insertUser(loginId, encryptPassword, name, email);
	}
	
	public User signIn(String loginId, String password) {
		// 비밀번호를 암호화 하고 DAO로 전달한다
		String encryptPassword = EncryptUtils.md5(password);
		return userDAO.selectUserByIdPassword(loginId, encryptPassword);
			
	}

}
