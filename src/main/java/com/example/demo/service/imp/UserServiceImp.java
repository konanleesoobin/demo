package com.example.demo.service.imp;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.mapper.UserMapper;
import com.example.demo.util.ComparisonExpUtil;
import com.example.demo.util.DateUtil;
import com.example.demo.vo.UserSearchVO;
import com.example.demo.vo.UserVO;

@Service
public class UserServiceImp implements com.example.demo.service.UserService {
	
	@Autowired
	private DateUtil dateUtil;
	
	@Autowired
	private ComparisonExpUtil comparisonExpUtil;
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public int insert(MultipartFile file) {
		
		List<UserVO> list = new ArrayList<>();
		
		try {
			Workbook workbook = new XSSFWorkbook(file.getInputStream());
			
			Sheet sheet = workbook.getSheetAt(0);
			int i = 0;
			String format = "MM/dd/yyyy";
			
			for (Row row : sheet) {
				i++;
				if(i == 1) continue;		
				String name = row.getCell(0).getStringCellValue();
				String email = row.getCell(1).getStringCellValue();
				String password = row.getCell(2).getStringCellValue();
				int followers = (int) row.getCell(3).getNumericCellValue();
				int following = (int) row.getCell(4).getNumericCellValue();
				String birthdayString = row.getCell(5).getStringCellValue();
				String param = dateUtil.parseDateFormat(birthdayString, format, "/");
				LocalDate birthday = dateUtil.parseStringToLocalDate(param, format);
				String interests = row.getCell(6).getStringCellValue();
				boolean verified = row.getCell(7).getBooleanCellValue();
				
				UserVO userVO = UserVO.builder()
						.name(name)
						.email(email)
						.password(password)
						.followers(followers)
						.following(following)
						.birthday(birthday)
						.interests(interests)
						.verified(verified)
						.build();
				
				list.add(userVO);
			}
			
			userMapper.inserts(list);
		} catch (IOException | IllegalStateException | NullPointerException e) {			
			System.out.println("내용이 이상함");
			return 300;
		} catch (NotOfficeXmlFileException e) {
			System.out.println("office 파일이 아님");
			return 301;
		}
		
		return 200;
	}

	@Override
	public List<UserVO> getUser(UserSearchVO userSearchVO) {
		String[] cmds = {userSearchVO.getFollowersCmd(), userSearchVO.getFollowingCmd(), userSearchVO.getBirthdayCmd()}; 
		if(comparisonExpUtil.checkCmd(cmds)) {
			List<String> exps = comparisonExpUtil.parseExp(cmds);
			int i = 0;
			for(String exp : exps) {
				if(exp != null) {
					if(i == 0) {
						userSearchVO.setFollowersCmd(exp);
					} else if(i == 1) {
						userSearchVO.setFollowingCmd(exp);
					} else if(i == 2) {
						userSearchVO.setBirthdayCmd(exp);
					}
				}
				i++;
			}
			test(userSearchVO);
			System.out.println(userSearchVO);
			return userMapper.getUser(userSearchVO);
		}
		return Collections.emptyList();
	}

	@Override
	public int delete(UserVO userVO) {
		return userMapper.delete(userVO);
	}

	@Override
	public int update(UserVO userVO) {
		return userMapper.update(userVO);
	}

	@Override
	public int insert(UserVO userVO) {
		return userMapper.insert(userVO);
	}
	
	private void test(UserSearchVO userSearchVO) {
		if(userSearchVO.getVerifiedCmd() != null) {
			switch (userSearchVO.getVerifiedCmd()) {
				case "true":
					userSearchVO.setVerified(true);
					break;
				case "false":
					userSearchVO.setVerified(false);
					break;
				default:
					userSearchVO.setVerifiedCmd(null);
					break;
			}
		}
	}
}
