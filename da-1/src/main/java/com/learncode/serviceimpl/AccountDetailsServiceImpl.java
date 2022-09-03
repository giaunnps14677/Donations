package com.learncode.serviceimpl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.learncode.entity.Account;
import com.learncode.repository.AccountRepository;

@Service("AccountDetailsServiceImpl")
public class AccountDetailsServiceImpl implements UserDetailsService{

	@Autowired
	AccountRepository accountRepository;
	 public UserDetails loadUserByUsername(String email) {
	        Account account = accountRepository.findByEmail(email);
	        if (account == null) throw new UsernameNotFoundException(email);

	        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
	        
	        if(account.getRole() == 1) {
	        	grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
	        }else {
	        	grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
	        }
	            
	            System.out.println(account.getRole().toString());
	            

	            org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(account.getUsername(), account.getPassword(), grantedAuthorities);
//	        
//	            org.springframework.security.web.access.expression. DefaultWebSecurityExpressionHandler
//	            org.springframework.security.access.expression.SecurityExpressionRoot
	            return user;
	    }
}
