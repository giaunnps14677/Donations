package com.learncode.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.learncode.entity.ThongTinNguoiQG;
import com.learncode.repository.AccountRepository;
import com.learncode.repository.CacDotQuyenGopRepository;
import com.learncode.security.SecurityUtils;
import com.learncode.service.AccountService;

@Controller
@RequestMapping("account")
public class DonationHistoryController {

	@Autowired
	CacDotQuyenGopRepository cacDotQuyenGopRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	AccountService accountService;
	
	@RequestMapping("donationHistory")
	public String showDonationHistory(ModelMap model) {
		System.out.println(SecurityUtils.getPrincipal().getUsername());
		List<ThongTinNguoiQG> cacDotQg = accountService.getThongTinNguoiQg(SecurityUtils.getPrincipal().getUsername());
		
			model.addAttribute("thongTinNguoiQg", cacDotQg);
			
			return "account/DonationHistory";
		
	}
	
	
}
