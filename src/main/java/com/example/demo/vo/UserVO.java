package com.example.demo.vo;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {
	private int userKey;
	private String name;
	private String email;
	private String password;
	private int followers;
	private int following;
	private LocalDate birthday;
	private String interests;
	private boolean verified;	
}
