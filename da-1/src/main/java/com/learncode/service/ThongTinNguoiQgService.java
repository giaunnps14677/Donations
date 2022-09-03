package com.learncode.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learncode.entity.CacDotQuyenGop;
import com.learncode.entity.ThongTinNguoiQG;
import com.learncode.repository.ThongTinNguoiQGRepository;

@Service
public class ThongTinNguoiQgService {
	
	@Autowired
	private ThongTinNguoiQGRepository ThongTinNguoiQGRepository;
	
	public void save(ThongTinNguoiQG thongTinNguoiQG) {
		ThongTinNguoiQGRepository.save(thongTinNguoiQG);
	}
	
	public ThongTinNguoiQG findById(int id) {
		Optional<ThongTinNguoiQG> thongTinNguoiQG =  ThongTinNguoiQGRepository.findById(id);
		return thongTinNguoiQG.get();
	}
}
