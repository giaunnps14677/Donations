package com.learncode.controller;

import java.sql.Date;
import java.util.Calendar;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.learncode.entity.Account;
import com.learncode.entity.ThongTinNguoiQG;
import com.learncode.security.SecurityUtils;
import com.learncode.service.AccountService;
import com.learncode.service.ThongTinNguoiQgService;

@Controller
@RequestMapping("account")
public class ThongTinNguoiQGController {
	
	@Autowired
	ThongTinNguoiQgService thongTinNguoiQgService;
	
	@Autowired
	AccountService accountService;
	
	@GetMapping("createThongTinNguoiQg")
	public String createThongTinNguoiQg(ModelMap model, @RequestParam int idCacDotQg) {
		ThongTinNguoiQG thongTinNguoiQG = new ThongTinNguoiQG();
		thongTinNguoiQG.setIdCacDotQg(idCacDotQg);
		Account account = accountService.findByUsername(SecurityUtils.getPrincipal().getUsername());
		thongTinNguoiQG.setAccountId(account.getAccountId());
		model.addAttribute("thongTinNguoiQg", thongTinNguoiQG);
		return "account/CreateCacDotQG";
	}
	
	@PostMapping("createThongTinNguoiQg")
	public String createThongTinNguoiQgNew(ModelMap model, ThongTinNguoiQG thongTinNguoiQG) {
		java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		thongTinNguoiQG.setNgayQuyenGop(date);
		thongTinNguoiQgService.save(thongTinNguoiQG);
		model.addAttribute("message", "Create Information of dunations successfull");
//		return this.createThongTinNguoiQg(model, thongTinNguoiQG.getIdCacDotQg());
		return "redirect:/account/donationHistory";
		
	}
	
}
