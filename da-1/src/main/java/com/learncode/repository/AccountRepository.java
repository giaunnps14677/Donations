package com.learncode.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learncode.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{

	Account findByEmail(String email);
//	Account findByUsername(String username);
	Account findByUsername(String username);
	Integer deleteByAccountId(Integer accountId);
	Account findByAccountId(Integer id);
}
