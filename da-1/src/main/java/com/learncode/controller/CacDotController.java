package com.learncode.controller;

import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.learncode.entity.Account;
import com.learncode.entity.CacDotQuyenGop;
import com.learncode.entity.ThongTinNguoiQG;
import com.learncode.repository.CacDotQuyenGopRepository;
import com.learncode.security.FileUploadUtil;
import com.learncode.service.CacDotQgService;
import com.learncode.service.ThongTinNguoiQgService;

import org.springframework.util.*;

@Controller
@RequestMapping("account")
public class CacDotController {

	@Autowired
	CacDotQuyenGopRepository cacDotQuyenGopRepository;

	@Autowired
	CacDotQgService cacDotQgService;

	@GetMapping("newOrEditCacDot")
	public String newOrEditCacDot(ModelMap model) {
		CacDotQuyenGop cacDotQuyenGop = new CacDotQuyenGop();
		model.addAttribute("cacdot", cacDotQuyenGop);
		return "account/cacdotquyengop";
	}

	@GetMapping("listCacDot")
	public String list(ModelMap model, @RequestParam(required = false, defaultValue = "") String searchText) {
//		List<CacDotQuyenGop> cacDotQG = cacDotQuyenGopRepository.findAll();
//		Locale localeVN = new Locale("vi", "VN");
//		NumberFormat numberFormat = NumberFormat.getCurrencyInstance(localeVN);
//		numberFormat.setCurrency(Currency.getInstance(localeVN));
//		model.addAttribute("lsCacDot", cacDotQG);
//		model.addAttribute("vn", numberFormat);
//		return "account/cacdotquyengop";

		return findPaginatedCacDot(1, model, searchText);
	}

	@GetMapping("listCacDot/page/{pageNo}")
	public String findPaginatedCacDot(@PathVariable(value = "pageNo") int pageNo, ModelMap model, @RequestParam(required = false, defaultValue = "") String searchText) {
		int pageSize = 4;

		Page<CacDotQuyenGop> page = cacDotQgService.findPaginated(pageNo, pageSize, searchText);
		List<CacDotQuyenGop> lsCacDotQuyenGop = page.getContent();

		Locale localeVN = new Locale("vi", "VN");
		NumberFormat numberFormat = NumberFormat.getCurrencyInstance(localeVN);
		numberFormat.setCurrency(Currency.getInstance(localeVN));
		model.addAttribute("vn", numberFormat);

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("lsCacDot", lsCacDotQuyenGop);

		return "account/cacdotquyengop";
	}

//	@RequestMapping("displayDonations")
//	public String displayDonations(ModelMap model) {
//		List<CacDotQuyenGop> donations = cacDotQuyenGopRepository.findAll();
//		Locale localeVN = new Locale("vi", "VN");
//		NumberFormat numberFormat = NumberFormat.getCurrencyInstance(localeVN);
//		numberFormat.setCurrency(Currency.getInstance(localeVN));
//		model.addAttribute("vn", numberFormat);
//		model.addAttribute("donations", donations);
//		return "account/Donations";
//	}

	@RequestMapping("displayDonations")
	public String displayDonation(ModelMap model) {
		return findPaginated(1, model);
	}

	@GetMapping("displayDonations/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, ModelMap model) {
		int pageSize = 3;

		Page<CacDotQuyenGop> page = cacDotQgService.findPaginated(pageNo, pageSize, null);
		List<CacDotQuyenGop> lsCacDotQuyenGop = page.getContent();

		Locale localeVN = new Locale("vi", "VN");
		NumberFormat numberFormat = NumberFormat.getCurrencyInstance(localeVN);
		numberFormat.setCurrency(Currency.getInstance(localeVN));
		model.addAttribute("vn", numberFormat);

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("lsCacDotQuyenGop", lsCacDotQuyenGop);

		return "account/PagingDonation";
	}

	@GetMapping("/account/createDonation")
	public String createDonation(ModelMap model) {
		CacDotQuyenGop cacDotQuyenGop = new CacDotQuyenGop();
		model.addAttribute("donation", cacDotQuyenGop);

		return "account/CreateDonation";
	}

	@PostMapping("createDonation")
	public String createDonationNew(ModelMap model, CacDotQuyenGop cacDotQuyenGop,
			@RequestParam("image") MultipartFile multipartFile) throws IOException {
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		cacDotQuyenGop.setImages(fileName);

		CacDotQuyenGop savedCacDotQuyenGop = cacDotQuyenGopRepository.save(cacDotQuyenGop);

		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File("src/main/resources/static/images/user-photos");

		FileUploadUtil.saveFile("user-photos", fileName, multipartFile);

		return "redirect:/account/displayDonations";
	}

	@GetMapping("updateDonation/{id}")
	public String updateDonation(@PathVariable int id, ModelMap model) {
		CacDotQuyenGop cacDotQuyenGop = cacDotQgService.findById(id);
		model.addAttribute("cacDotQuyenGop", cacDotQuyenGop);
		return "account/UpdateDonation";
	}

	@PostMapping("updateDonation")
	public String updateDonationNew(ModelMap model, CacDotQuyenGop cacDotQuyenGop,
			@RequestParam("image") MultipartFile multipartFile) throws IOException {
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		cacDotQuyenGop.setImages(fileName);

		CacDotQuyenGop savedCacDotQuyenGop = cacDotQuyenGopRepository.save(cacDotQuyenGop);

//        ClassLoader classLoader = getClass().getClassLoader();
//        File file = new File("src/main/resources/static/images/user-photos");

		FileUploadUtil.saveFile("user-photos", fileName, multipartFile);

		return "redirect:/account/displayDonations";
	}
	
	@GetMapping("viewDonor/{id}")
	public String updateDonator(@PathVariable int id, ModelMap model) {
		CacDotQuyenGop cacDotQuyenGop = cacDotQgService.findById(id);
		model.addAttribute("cacDotQuyenGop", cacDotQuyenGop);
		return "account/Donor";
	}
	
	@Autowired
	private ThongTinNguoiQgService thongTinNguoiQgService;
	@GetMapping("confirmDonation/{id}")
	public String confirmDonation(@PathVariable int id, ModelMap model) {
		ThongTinNguoiQG thongTinNguoiQg = thongTinNguoiQgService.findById(id);
		thongTinNguoiQg.setXacNhanDaQG(true);
		thongTinNguoiQgService.save(thongTinNguoiQg);
		return "redirect:/account/viewDonor/" +thongTinNguoiQg.getIdCacDotQg();
	}
	@Transactional
	@GetMapping("deleteDonation/{id}")
	public String deleteDonation(@PathVariable int id, ModelMap model) {
		cacDotQgService.deleteById(id);
		return "redirect:/account/displayDonations";
	}

//	@RequestMapping("search")
//	public String home(CacDotQuyenGop cacDotQuyenGop, ModelMap model, String keyword) {
//		if (keyword != null) {
//			List<CacDotQuyenGop> list = cacDotQgService.getByKeyword(keyword);
//			model.addAttribute("list", list);
//		} else {
//			List<CacDotQuyenGop> list = cacDotQgService.getAllCacDotQG();
//			model.addAttribute("list", list);
//		}
//		return "index";
//	}
}
