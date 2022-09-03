package com.learncode.serviceimpl;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.learncode.entity.Account;
import com.learncode.entity.ThongTinNguoiQG;
import com.learncode.repository.AccountRepository;
import com.learncode.repository.ThongTinNguoiQGRepository;
import com.learncode.repository.AccountRepository;

@Service
public class AccountServiceImpl {

	 @Autowired
	    private AccountRepository userRepository;
	 
	    @Autowired
	    private BCryptPasswordEncoder bCryptPasswordEncoder;

	    public void save(Account account) {
	        account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
	        System.out.println("ROLE OF ACCOUNT CURRENT NE: "+account.getRole());
	        if(account.getRole() == 1) {
	        	account.setRole(1);
	        }else{
	        	account.setRole(0);
	        }
	        
	        userRepository.save(account);
	    }

	    public Account findByUsername(String email) {
	        return userRepository.findByEmail(email);
	    }
	    
	    public Account findByAccountId(int id) {
	        return userRepository.findByAccountId(id);
	    }
	    
	    @Autowired
	    private ThongTinNguoiQGRepository thongTinNguoiQGRepository;
	    
	    
	    public Integer deleteByAccountId(Integer id) {
	    	thongTinNguoiQGRepository.deleteByAccountId(id);
	        return userRepository.deleteByAccountId(id);
	    }
	    
}
