package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.UserSearchVO;
import com.example.demo.vo.UserVO;

@Mapper
@Repository
public interface UserMapper {

	public int inserts(List<UserVO> users);
	
	public List<UserVO> getUser(UserSearchVO userSearchVO);
	
	public int delete(UserVO userVO);
	
	public int update(UserVO userVO);
	
	public int insert(UserVO userVO) throws DataIntegrityViolationException;
}
