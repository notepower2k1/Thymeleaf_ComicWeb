package thach.nv.project.controller;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import thach.nv.project.entity.NguoiDung;
import thach.nv.project.entity.Role;
import thach.nv.project.service.NguoiDungService;
import thach.nv.project.service.RoleService;



@Controller
public class SecurityController {

	@Autowired
	private NguoiDungService nguoidungService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/admin/login") 
    public String adminLoginPage(RedirectAttributes redirectAttrs) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
        	return "admin/security/login";
        }
        redirectAttrs.addFlashAttribute("alertType", "success");
		redirectAttrs.addFlashAttribute("alertText", "Đăng nhập thành công");
        return "redirect:/admin";
    }
	@PostMapping("/admin/logout")
    public String adminLogout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/admin/login";
    }
	@GetMapping("/admin/error") 
    public String adminErrorPage() {
        return "admin/security/error";
    }
	@PostMapping("/admin/save-account")
	public String saveAccount(@ModelAttribute("account") NguoiDung account,
			RedirectAttributes redirectAttrs) {
		account.setMatKhau(passwordEncoder.encode(account.getMatKhau()));
		this.nguoidungService.saveAccount(account);
		redirectAttrs.addFlashAttribute("alertType", "success");
		redirectAttrs.addFlashAttribute("alertText", "Thành công");
		return "redirect:/admin";
	}
	
	@GetMapping("/login")
	public String userLoginPage(RedirectAttributes redirectAttrs) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
        	return "user/security/login";
        }
    	redirectAttrs.addFlashAttribute("alertType", "Fail");
		redirectAttrs.addFlashAttribute("alertText", "Tài khoản hoặc mật khẩu không đúng!!!");
        return "redirect:/";
	}
	@RequestMapping(value = "/logout", method = {RequestMethod.POST, RequestMethod.GET})
    public String userLogout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/home";
    }
	
	@GetMapping("/register")
	public String userRegisterPage() {
		return "user/security/register";
		
	}
	@PostMapping("/user-register")
	public String userRegister(@RequestParam("username") String username,
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			@RequestParam("repassword") String repassword,
			RedirectAttributes redirectAttrs
			) {
	
		if (password.equals(repassword)) {
			NguoiDung exist_accout = this.nguoidungService.findTopByTaikhoan(username);	
			
			if(exist_accout!=null)
			{
				redirectAttrs.addFlashAttribute("alertType", "Fail");
				redirectAttrs.addFlashAttribute("alertText", "Tài khoản đã tồn tại");
			}
			else {
				NguoiDung account = new NguoiDung(0, username, passwordEncoder.encode(password),email,"","","","",this.roleService.getById(2));
				this.nguoidungService.saveAccount(account);
				redirectAttrs.addFlashAttribute("alertType", "Sucess!");
				redirectAttrs.addFlashAttribute("alertText", "Đăng ký thành công!!!");
			}			
			
		}
		else {
			redirectAttrs.addFlashAttribute("alertType", "Fail");
			redirectAttrs.addFlashAttribute("alertText", "Mật khẩu nhập lại không đúng");
		}
		return "redirect:/login";
		
	}
}
