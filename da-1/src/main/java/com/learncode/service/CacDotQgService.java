package com.learncode.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.learncode.entity.CacDotQuyenGop;
import com.learncode.entity.ThongTinNguoiQG;
import com.learncode.repository.CacDotQuyenGopRepository;

@Service
public class CacDotQgService {

	@Autowired
	CacDotQuyenGopRepository cacDotQuyenGopRepository;
	
	public void save(CacDotQuyenGop cacDotQuyenGop){
		cacDotQuyenGopRepository.save(cacDotQuyenGop);
	}
	
	public CacDotQuyenGop findById(int id) {
		Optional<CacDotQuyenGop> cacDotQuyenGop =  cacDotQuyenGopRepository.findById(id);
		return cacDotQuyenGop.get();
	}
	
	public void deleteById(int id) {
		cacDotQuyenGopRepository.deleteById(id);
	}
	
	public Page<CacDotQuyenGop> findPaginated(int pageNo, int pageSize, String searchText) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		
		if(StringUtils.isEmpty(searchText)) {
			return this.cacDotQuyenGopRepository.findAll(pageable);
		}
		  return this.cacDotQuyenGopRepository.findByTitleContaining(searchText, pageable);
		}


	//Search
//	public List<CacDotQuyenGop> getAllCacDotQG(){
//		  List<CacDotQuyenGop> list =  (List<CacDotQuyenGop>)cacDotQuyenGopRepository.findAll();
//		  return list;
//		 }
}
