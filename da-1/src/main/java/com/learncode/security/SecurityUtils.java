package com.learncode.security;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

//	 public static User getPrincipal() {
//	        return (User) (SecurityContextHolder.getContext()).getAuthentication().getPrincipal();
//	    }
	public static User getPrincipal() {
		return (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	}
