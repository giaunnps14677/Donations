package com.learncode.entity;

import java.util.Date;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class PasswordResetToken {
 
    private static final int EXPIRATION = 60*24;
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    private String token;
  
 
    @OneToOne(targetEntity = Account.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "account_id")
    private Account account;
    public Account getAccount() {
		return account;
	}
 
    private Date expiryDate;
    
    public Date getExpiryDate() {
		return expiryDate;
	}
    
    public PasswordResetToken (String token, Account account) {
    	this.token = token;
    	this.account = account;
    	Calendar calendar = Calendar.getInstance();
    	calendar.add(Calendar.MINUTE, EXPIRATION);
    	this.expiryDate = (Date) calendar.getTime();
    }
    public PasswordResetToken() {
		// TODO Auto-generated constructor stub
	}
}