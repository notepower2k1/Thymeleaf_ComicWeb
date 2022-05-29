package thach.nv.project.controller;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import thach.nv.project.entity.Truyen;
import thach.nv.project.service.NguoiDungService;
import thach.nv.project.service.TheLoaiService;
import thach.nv.project.service.TruyenService;
import thach.nv.project.ultity.Helper;


@Controller
public class TruyenController {

	@Autowired
	private TruyenService truyenService;
	
	@Autowired
	private NguoiDungService nguoidungService;
	
	@Autowired
	private TheLoaiService theloaiService;
	@GetMapping( value = {"/admin/truyen"} )
	public String truyenListPage(Model model) {
		NguoiDung account = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    String currentUserName = authentication.getName();
		    account = nguoidungService.getByUsername(currentUserName);
		    List<Truyen> dsTruyen = this.truyenService.findTruyenByUserID(account);
			model.addAttribute("truyenList", dsTruyen);
			return "admin/truyen/index";
		}
		
			
		
		else {
			 return "redirect:/admin/login";
		}
	}
	
	@GetMapping("/admin/truyen/chi-tiet/{id_truyen}")
	public String truyenDetailPage(@PathVariable(value="id_truyen") int id_truyen, Model model) {
		Truyen truyen = this.truyenService.getTruyenById(id_truyen);
		model.addAttribute("truyen", truyen);
		return "admin/truyen/detail";
	}
	    
	@GetMapping("/admin/truyen/them-moi")
	public String createTruyen(Model model) {
		Truyen truyen = new Truyen();
		
		
		


 	    String newId = Helper.getNewID(this.truyenService.getMaxId(), 2, 2, "T");
 	    
 	    truyen.setTruyen_ma(newId);
 	    
 	    truyen.setTruyen_tinhtrang(0);
	    model.addAttribute("listTheLoai",theloaiService.getAllTheLoai());
	    model.addAttribute("truyen", truyen);
	    return "admin/truyen/add_truyen";
	}
	    
	@PostMapping("/admin/truyen/save-truyen")
	public String saveTruyen(@ModelAttribute("truyen") Truyen truyen,
			@RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttrs , @Param("img") String img ){
		Path path = Paths.get("src/main/resources/static/admin/img/");

		NguoiDung account = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    String currentUserName = authentication.getName();
		    account = nguoidungService.getByUsername(currentUserName);
		}
		
		truyen.setUserID(account);
		if (truyen.getTruyen_ma() == null) {
			String newId = Helper.getNewID(this.truyenService.getMaxId(), 2, 2, "T");
		    truyen.setTruyen_ma(newId);
		    truyen.setTruyen_anh(file.getOriginalFilename());
		}	
			if (file.isEmpty()){
				truyen.setTruyen_anh(img);				
			}
			
			
			else{
				truyen.setTruyen_anh(file.getOriginalFilename());		
			}
				
				try {				
					  
					InputStream inputStream = file.getInputStream();
					Files.copy(inputStream, path.resolve(file.getOriginalFilename()),
							StandardCopyOption.REPLACE_EXISTING);
				}
					catch(Exception e){
						e.printStackTrace();
					}

		this.truyenService.saveTruyen(truyen);
		redirectAttrs.addFlashAttribute("alertType", "success");
		redirectAttrs.addFlashAttribute("alertText", "Thành công");
		return "redirect:/admin/truyen";
	}
	
	@GetMapping("/admin/truyen/cap-nhat/{id_truyen}")
	public String updateTruyen(@PathVariable(value="id_truyen") int id_truyen, Model model) {
		Truyen truyen = this.truyenService.getTruyenById(id_truyen);
	    model.addAttribute("truyen", truyen);
	    model.addAttribute("listTheLoai",theloaiService.getAllTheLoai());
	    return "admin/truyen/update_truyen";
	}
	
	@GetMapping("/admin/truyen/xoa-truyen/{id_truyen}")
	public String deleteTruyen(@PathVariable(value="id_truyen") int id_truyen, 
			RedirectAttributes redirectAttrs) {
		this.truyenService.removeTruyen(id_truyen);
		redirectAttrs.addFlashAttribute("alertType", "success");
		redirectAttrs.addFlashAttribute("alertText", "Xóa thành công");
	    return "redirect:/admin/truyen";
	}
}
