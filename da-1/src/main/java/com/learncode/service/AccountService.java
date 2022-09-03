package com.learncode.service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.learncode.entity.Account;
import com.learncode.entity.PasswordResetToken;
import com.learncode.entity.ThongTinNguoiQG;
import com.learncode.repository.AccountRepository;
import com.learncode.repository.PasswordTokenRepository;
import com.learncode.repository.ThongTinNguoiQGRepository;

@Service
public class AccountService {

	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	ThongTinNguoiQGRepository thongTinNguoiQGRepository;
	
	@Autowired
	PasswordTokenRepository passwordTokenRepository;
	
	public List<ThongTinNguoiQG> getThongTinNguoiQg(String username){
		Account a = accountRepository.findByUsername(username);
		
		List<ThongTinNguoiQG> thongTinNguoiQg = thongTinNguoiQGRepository.findAllByAccountId(a.getAccountId());
		
		return thongTinNguoiQg;
				
}
	
//	public Account findByUsername(String username) {
//		Account a = accountRepository.findByUsername(username);
//
//		return a;
//	}
	public Account findByUsername(String username) {
		Account a = accountRepository.findByUsername(username);
		return a;
	}
	
	public boolean deleteAccount(String username) {
		Account a = accountRepository.findByUsername(username);
		int idAccount = a.getAccountId();
		
		int result = accountRepository.deleteByAccountId(idAccount);
		boolean check = false;
		if(result == 1) {
			check = true;
		}
		return check;
		
	}
	
	public Account findByEmail(String email) {
		Account a = accountRepository.findByEmail(email);

		return a;
	}
	
//	public Page<Account> findPaginated(int pageNo, int pageSize) {
//	 org.springframework.data.domain.Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
//	 return this.accountRepository.findAll(pageable);
//	}
	
	public Page<Account> findPaginated(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		 return this.accountRepository.findAll(pageable);
		}

	public void createPasswordResetTokenForUser(Account account, String token) {
		PasswordResetToken myToken = new PasswordResetToken(token, account);
	    passwordTokenRepository.save(myToken);
		
	}
	private boolean isTokenFound(PasswordResetToken passToken) {
	    return passToken != null;
	}

	private boolean isTokenExpired(PasswordResetToken passToken) {
	    final Calendar cal = Calendar.getInstance();
	    return passToken.getExpiryDate().before(cal.getTime());
	}
	public String validatePasswordResetToken(String token) {
	    final PasswordResetToken passToken = passwordTokenRepository.findByToken(token);//tim token

	    return !isTokenFound(passToken) ? "invalidToken"
	            : isTokenExpired(passToken) ? "expired"//het han
	            : null;//token ok
	}
	 public Optional<Account> getUserByPasswordResetToken(final String token) {
	        return Optional.ofNullable(passwordTokenRepository.findByToken(token) .getAccount());
	    }
	
}
