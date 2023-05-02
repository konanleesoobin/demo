package com.example.demo.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ComparisonExpUtil {
	
	List<String> cmd = Arrays.asList("gt","ge","lt","le","eq","ne");
	List<String> arrow = Arrays.asList(">",">=","<","<=","=","!=");

	public boolean checkCmd(String... cmds) {
		boolean result = true;
		
		for(String c : cmds) {
			if(c != null && result) {
				result = cmd.contains(c);
			}
		}
		
		return result;
	}
	
	public List<String> parseExp(String... cmds) {
		List<String> result = new ArrayList<>();
		for(String c : cmds) {
			if(c != null) {
				int index = cmd.indexOf(c);
				String exp = arrow.get(index);
				result.add(exp);
			} else {
				result.add(null);
			}
		}
		
		return result;
	}
}
