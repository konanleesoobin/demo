package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.imp.UserServiceImp;
import com.example.demo.vo.UserSearchVO;
import com.example.demo.vo.UserVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	private UserServiceImp userService;

	@RequestMapping(value="/user/insertFile", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> insert(@RequestParam("file") MultipartFile file) {
		int result = userService.insert(file);
		if(result == 200) return ResponseEntity.ok("");
		return new ResponseEntity("에러 코드 : " + result,null,HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value="/user/insertRow", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> insert(@RequestBody UserVO userVO) {
		int result = 0;
		try {
			result = userService.insert(userVO);
		} catch(DataIntegrityViolationException e) {
			return new ResponseEntity("파라미터 잘못넣음",null,HttpStatus.BAD_REQUEST);
		}
		
		if(result == 1) return ResponseEntity.ok("");
		return new ResponseEntity("디비 인서트 실패",null,HttpStatus.INTERNAL_SERVER_ERROR);
	}
		
	@RequestMapping(value="/user/get", method = RequestMethod.POST)
	@ResponseBody
	public List<UserVO> getUser(@RequestBody UserSearchVO userSearchVO) {
		return userService.getUser(userSearchVO);
	}
	
	@RequestMapping(value="/user/delete", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> delete(@RequestBody UserVO userVO) {
		int result = userService.delete(userVO);
		if(result == 0) return new ResponseEntity("사용자가 없습니다",null,HttpStatus.BAD_REQUEST);
		return ResponseEntity.ok("");
	}
	
	@RequestMapping(value="/user/update", method = RequestMethod.POST)
	public ResponseEntity<String> update(@RequestBody UserVO userVO) {
		int result = userService.update(userVO);
		if(result == 0) return new ResponseEntity("사용자가 없습니다",null,HttpStatus.BAD_REQUEST);
		return ResponseEntity.ok("");
	}
}
