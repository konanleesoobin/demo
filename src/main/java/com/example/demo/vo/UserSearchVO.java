package com.example.demo.vo;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class UserSearchVO extends UserVO {
	private String followersCmd;
	private String followingCmd;
	private String birthdayCmd;
	private List<String> interestsList;
	private String verifiedCmd;
}
