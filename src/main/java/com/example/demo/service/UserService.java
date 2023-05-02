package com.example.demo.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.vo.UserSearchVO;
import com.example.demo.vo.UserVO;

public interface UserService {

	public int insert(MultipartFile file);
	
	public List<UserVO> getUser(UserSearchVO userSearchVO);
	
	public int delete(UserVO userVO);
	
	public int update(UserVO userVO);
	
	public int insert(UserVO userVO);
}
