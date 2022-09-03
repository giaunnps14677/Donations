package com.learncode.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learncode.entity.ThongTinNguoiQG;

@Repository
public interface ThongTinNguoiQGRepository extends JpaRepository<ThongTinNguoiQG, Integer>{

	List<ThongTinNguoiQG> findAllByAccountId(int accountId);
	
	Integer deleteByAccountId(int id);
}
