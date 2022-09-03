package com.learncode.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.learncode.entity.CacDotQuyenGop;

@Repository
public interface CacDotQuyenGopRepository extends JpaRepository<CacDotQuyenGop, Integer>{

	//Search
	
	Page<CacDotQuyenGop> findByTitleContaining(String firstname, Pageable pageable);
}
