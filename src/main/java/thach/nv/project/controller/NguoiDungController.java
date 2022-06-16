package thach.nv.project.controller;



import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import thach.nv.project.entity.NguoiDung;
import thach.nv.project.service.NguoiDungService;
import thach.nv.project.service.RoleService;

@Controller
public class NguoiDungController {

	@Autowired
	private NguoiDungService nguoidungService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping("/admin/nguoidung")
	public String AccountListPage(Model model) {
		model.addAttribute("nguoidung", this.nguoidungService.getAllAccounts());
		return "admin/nguoidung/index";
	}
	
	@GetMapping("/admin/nguoidung/chi-tiet/{id_taikhoan}")
	public String AccountDetailPage(@PathVariable(value="id_taikhoan") int id_taikhoan, Model model) {
		NguoiDung account = this.nguoidungService.getById(id_taikhoan);
		model.addAttribute("nguoidung", account);
		return "admin/nguoidung/detail";
	}
	    
	@GetMapping("/admin/nguoidung/them-moi")
	public String createAccount(Model model) {
		NguoiDung account = new NguoiDung();
	    model.addAttribute("nguoidung", account);
	    return "admin/nguoidung/add_nguoidung";
	}
	    
	@PostMapping("/admin/nguoidung/save-nguoidung")
	public String saveAccount(@ModelAttribute("nguoidung") NguoiDung nguoidung,@RequestParam("accountName") String accountName,
			@RequestParam("password") String password,	
			@RequestParam("file") MultipartFile file, 
			@Param("img") String img ,
			RedirectAttributes redirectAttrs){
		Path path = Paths.get("src/main/resources/static/admin/img/");

		if (file.isEmpty()){
			nguoidung.setHinhanh(img);
		}
		
		
		else{
			nguoidung.setHinhanh(file.getOriginalFilename());		
		}
			
			try {				
				  
				InputStream inputStream = file.getInputStream();
				Files.copy(inputStream, path.resolve(file.getOriginalFilename()),
						StandardCopyOption.REPLACE_EXISTING);
			}
				catch(Exception e){
					e.printStackTrace();
				}
			
			nguoidung.setTaikhoan(accountName);
			nguoidung.setMatKhau(passwordEncoder.encode(password));
			nguoidung.setRole(roleService.getById(2));
		
		this.nguoidungService.saveAccount(nguoidung);
		redirectAttrs.addFlashAttribute("alertType", "success");
		redirectAttrs.addFlashAttribute("alertText", "Thành công");
		return "redirect:/admin/nguoidung/";
	}
	
	@PostMapping("/admin/nguoidung/add-nguoidung")
	public String addAccount(@ModelAttribute("nguoidung") NguoiDung nguoidung,@RequestParam("accountName") String accountName,
			@RequestParam("password") String password,	
			@RequestParam("file") MultipartFile file, 
			@Param("img") String img ,
			RedirectAttributes redirectAttrs){
		Path path = Paths.get("src/main/resources/static/admin/img/");

		if (file.isEmpty()){
			nguoidung.setHinhanh(img);
		}
		
		
		else{
			nguoidung.setHinhanh(file.getOriginalFilename());		
		}
			
			try {				
				  
				InputStream inputStream = file.getInputStream();
				Files.copy(inputStream, path.resolve(file.getOriginalFilename()),
						StandardCopyOption.REPLACE_EXISTING);
			}
				catch(Exception e){
					e.printStackTrace();
				}
			
			NguoiDung exist_accout = this.nguoidungService.findTopByTaikhoan(accountName);	
			
			if(exist_accout!=null)
			{
				redirectAttrs.addFlashAttribute("alertType", "Fail");
				redirectAttrs.addFlashAttribute("alertText", "Tài khoản đã tồn tại");
				return "redirect:/admin/nguoidung/them-moi";

			}
			else {
				nguoidung.setTaikhoan(accountName);
				nguoidung.setMatKhau(passwordEncoder.encode(password));
				nguoidung.setRole(roleService.getById(2));
			
			this.nguoidungService.saveAccount(nguoidung);
			redirectAttrs.addFlashAttribute("alertType", "success");
			redirectAttrs.addFlashAttribute("alertText", "Thành công");
			return "redirect:/admin/nguoidung/";
			}
			
	}
	
	@GetMapping("/admin/nguoidung/cap-nhat/{id_taikhoan}")
	public String updateAccount(@PathVariable(value="id_taikhoan") int id_taikhoan, Model model) {
		NguoiDung account = this.nguoidungService.getById(id_taikhoan);
	    model.addAttribute("nguoidung", account);
	    return "admin/nguoidung/update_nguoidung";
	}
	
	@GetMapping("/admin/nguoidung/xoa-nguoidung/{id_taikhoan}")
	public String deleteAccount(@PathVariable(value="id_taikhoan") int id_taikhoan, 
			RedirectAttributes redirectAttrs) {
		this.nguoidungService.removeAccountById(id_taikhoan);
		redirectAttrs.addFlashAttribute("alertType", "success");
		redirectAttrs.addFlashAttribute("alertText", "Xóa thành công");
	    return "redirect:/admin/nguoidung/";
	}
}
