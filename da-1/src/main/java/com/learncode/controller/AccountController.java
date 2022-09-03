package com.learncode.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.learncode.dto.PasswordDto;
import com.learncode.entity.Account;
import com.learncode.entity.ThongTinNguoiQG;
import com.learncode.repository.AccountRepository;
import com.learncode.security.SecurityUtils;
import com.learncode.service.AccountService;
import com.learncode.service.EmailService;
import com.learncode.serviceimpl.AccountServiceImpl;
import com.learncode.serviceimpl.SecurityServiceImpl;

@Controller
@RequestMapping("account")
public class AccountController {

//	d00583c9729e2046538e536cc7104c10 username
//	f0fe2d31adab91a431b6871220ae0c1e mat khau cau hinh
//	in-v3.mailjet.com host smtp server
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private SecurityServiceImpl securityService;
	
	@Autowired
	private AccountServiceImpl accountServiceImpl;
	
	@Autowired
	private AccountService accountService;
	
	@RequestMapping("/")
	public String list(ModelMap model) {
		
//		model.addAttribute("accounts", accountRepository.findAll());
		return "account/index";
	}
	
	@GetMapping("dangKy")
	public String newOrEdit(ModelMap model) {
		
		if (securityService.isAuthenticated()) {
            return "redirect:/account/register-account";
        }
		
		Account account = new Account();
		model.addAttribute("account", account);
        
		return "account/register-account";
	}
	
	@PostMapping("/dangKy")
	public String saveOrUpdate(ModelMap model, Account item) {
		
		accountServiceImpl.save(item);	
		model.addAttribute("message", "New account is saved!");
		
		return "account/login";
	}
	
	@GetMapping("/login")
	public String login(ModelMap model) {
		
		if (securityService.isAuthenticated()) {
			
            return "redirect:/account/listCacDot";
        }
		
		return "account/login";
	}
	
	@GetMapping("/welcome")
	public String welcome() {
		return "account/welcome";
	}
	
	@RequestMapping("/login2")	
	public String login(ModelMap model, HttpServletRequest request, HttpSession session) {
		if (securityService.isAuthenticated()) {
            return "redirect:/";
        }
		
		String e = request.getParameter("email");
		String p = request.getParameter("password");
		
		List<Account> account = accountRepository.findAll();
		for(int i=0; i< account.size(); i++) {
			Account a = account.get(i);
			String email = a.getEmail();
			String password = a.getPassword();
			String username = a.getUsername();
			int id = a.getAccountId();
			
			if(email.equals(e) && password.equals(p)) {
				model.addAttribute("displayUser", username);
				session.setAttribute("email", email);
				session.setAttribute("password", password);
				session.setAttribute("username", username);
				session.setAttribute("id", id);
				
				return "account/login";
			}
			String sessionId = (String) session.getAttribute("email");
			System.out.println(sessionId);
			
		}
		
		return "account/login";
		
	}
//		@RequestMapping("displayAccounts")
//		public String displayAccounts(ModelMap model) {
//			return findPaginated(1, model);
//			
//		}
	
	@RequestMapping("displayAccounts")
	public String displayAccount(ModelMap model) {
		return findPaginated(1, model);
	}
	
	@GetMapping("displayAccounts/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, ModelMap model) {
	    int pageSize = 3;

	    Page < Account > page = accountService.findPaginated(pageNo, pageSize);
	    List < Account > lsAccount = page.getContent();

	    model.addAttribute("currentPage", pageNo);
	    model.addAttribute("totalPages", page.getTotalPages());
	    model.addAttribute("totalItems", page.getTotalElements());
	    model.addAttribute("lsAccounts", lsAccount);
	    
	    return "account/PagingAccount";
	}
//		@RequestMapping("displayAccounts")
//		public String displayAccounts(ModelMap model) {
//			List<Account> lsAccounts = accountRepository.findAll();
//			model.addAttribute("lsAccounts", lsAccounts);
//			
//			return "account/Accounts";
//		}
		
//		@GetMapping("displayAccounts/page/{pageNo}")
//		public String findPaginated(@PathVariable(value = "pageNo") int pageNo, ModelMap model) {
//		    int pageSize = 3;
//
//		    Page<Account> page = accountService.findPaginated(pageNo, pageSize);
//		    List <Account> lsAccount = page.getContent();
//
//		    model.addAttribute("currentPage", pageNo);
//		    model.addAttribute("totalPages", page.getTotalPages());
//		    model.addAttribute("totalItems", page.getTotalElements());
//		    model.addAttribute("lsAccounts", lsAccount);
//		    System.out.println(lsAccount.size());
//		    return "account/PagingAccount";
//		}

		@GetMapping("createAccount")
		public String createAccount(ModelMap model) {
			Account account = new Account();
			model.addAttribute("account", account);
			return "account/CreateAccount";
		}
		
		@PostMapping("createAccount")
		public String createAccountNew(ModelMap model, Account account) {
			accountServiceImpl.save(account);
			return "redirect:/account/displayAccounts";
		}
		
		@GetMapping("updateAccount/{id}")
		public String updateAccount(@PathVariable int id, ModelMap model) {
			Account account = accountServiceImpl.findByAccountId(id);
			model.addAttribute("account", account);
			return "account/UpdateAccount";
		}
		
		@PostMapping("updateAccount")
		public String updateAccountNew(ModelMap model, Account account) {
			accountServiceImpl.save(account);
			return "redirect:/account/displayAccounts";
		}
		
		@Transactional
		@GetMapping("deleteAccount/{id}")
		public String deleteAccount(@PathVariable int id, ModelMap model) {
			accountServiceImpl.deleteByAccountId(id);
			return "redirect:/account/displayAccounts";
		}
		
		@GetMapping("changePassword")
		public String changePassword(ModelMap model) {
			Account account = accountService.findByUsername(SecurityUtils.getPrincipal().getUsername());
			model.addAttribute("account",account);
			System.out.println(account);
			
			return "account/ChangePassword";
		}
		
		@PostMapping("changePassword")
		public String changePassword(ModelMap model, Account account, HttpServletRequest request) {

			System.out.println(account.getPassword());
				
				Account currentAccount = accountService.findByUsername(SecurityUtils.getPrincipal().getUsername());
				currentAccount.setPassword(account.getPassword());
				accountServiceImpl.save(currentAccount);
				
				
				new SecurityContextLogoutHandler().logout(request, null, null);
				
				
			return "redirect:/account/login";
		}
		
		@GetMapping("forgetPassword")
		public String forgetPassword(ModelMap model) {
			Account account = new Account();
			model.addAttribute("account", account);
			
			return "account/Email";
		}
		
		@Autowired
		private EmailService emailService;
		
		 private String getAppUrl(HttpServletRequest request) {
		        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		    }
		 @Autowired
		    private MessageSource messages;
		 @Value("${mail.from}")
		    private String from;
		 private SimpleMailMessage constructEmail(String subject, String body, 
				  Account account) {
				    SimpleMailMessage email = new SimpleMailMessage();
				    email.setSubject(subject);
				    email.setText(body);
				    email.setTo(account.getEmail());
				    email.setFrom(from);
				    return email;
				}
		 private SimpleMailMessage constructResetTokenEmail(
				  String contextPath, Locale locale, String token, Account account) {
				    String url = contextPath + "/account/resetNewPassword?token=" + token;
				    String message = messages.getMessage("message.resetPassword", 
				      null, locale);
				    return constructEmail("Reset Password", message + " \r\n" + url, account);
				}
		@PostMapping("forgetPassword")
		public String forgetPasswordNew(Account account, ModelMap model, HttpServletRequest request) {
			String email = account.getEmail();//lay mail ta nhap
			Account a = accountService.findByEmail(email);//tim account nao co mail do
			if(a != null) {
				//b3
				String token = UUID.randomUUID().toString();//tao token (random ngau nhien)
				accountService.createPasswordResetTokenForUser(a, token);//tao xong luu lai trong database (password_reset_token) token
//				emailService.send(email);
				
				emailService.send(constructResetTokenEmail(getAppUrl(request), 
					      request.getLocale(), token, account));//gui mail
				
				model.addAttribute("account", account);
				model.addAttribute("message", "Please check your mail");
				return "account/Email";
			}else {
				model.addAttribute("account", account);
				model.addAttribute("message", "This email doesn't exist");
				return "account/Email";
			}
			
		}
		 
		@GetMapping("resetNewPassword")
		public String showChangePasswordPage(Locale locale, ModelMap model, 
		  @RequestParam("token") String token) {
		    String result = accountService.validatePasswordResetToken(token);//kiem tra su ton tai token trong data
		    if(result != null) {
//		        String message = messages.getMessage("auth.message." + result, null, locale);
		        return "redirect:/account/login";
		    } else {
		        
		        PasswordDto passwordDto = new PasswordDto();
		        passwordDto.setToken(token);
		        model.addAttribute("passwordDto", passwordDto);
		        System.out.println("token"+passwordDto.getToken());
		        return "account/updatePassword";
		    }
		}
		
		@PostMapping("savePassword")
		public String savePassword(final Locale locale, PasswordDto passwordDto) {
			System.out.println("abc"+passwordDto.getToken());
		    String result = accountService.validatePasswordResetToken(passwordDto.getToken());

		    if(result != null) {
		    	return "redirect:/account/login";
		    }

		    Optional<Account> user = accountService.getUserByPasswordResetToken(passwordDto.getToken());//tim user boi token
		    if(user.isPresent()) {
		    	user.get().setPassword(passwordDto.getNewPassword());//set MK moi
		    	System.out.println("123"+user.get().getPassword());
		        accountServiceImpl.save(user.get());//luu vao db
		        return "redirect:/account/login";
		    } else {
		    	return "redirect:/account/login";
		    }
		}
		
		
}
