package thach.nv.project.controller;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import thach.nv.project.entity.BaiDang;
import thach.nv.project.entity.BinhLuan;
import thach.nv.project.entity.NguoiDung;
import thach.nv.project.service.BaiDangService;
import thach.nv.project.service.BinhLuanService;
import thach.nv.project.service.NguoiDungService;
import thach.nv.project.service.TruyenService;

@Controller
public class BaiDangController {

	@Autowired
	private BaiDangService baiDangService;
	
	@Autowired
	private NguoiDungService nguoidungService;
	
	@Autowired
	private TruyenService truyenService;
	
	@Autowired
	private BinhLuanService binhluanService;
	
	@GetMapping( value = {"/admin/baidang","/admin"} )
	public String truyenListPage(Model model) {
	
		NguoiDung account = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    String currentUserName = authentication.getName();
		    account = nguoidungService.getByUsername(currentUserName);
		    List<BaiDang> ds = this.baiDangService.findByTaikhoan(account);
		    model.addAttribute("baidangList",ds);
			return "admin/baidang/index";
		}
		
			
		
		else {
			 return "redirect:/admin/login";
		}
			
	
	}
	
	
	@GetMapping("/admin/baidang/chi-tiet/{id_baidang}")
	public String baidangDetailPage(@PathVariable(value="id_baidang") int id_baidang, Model model) {
		BaiDang baidang = this.baiDangService.getBaiDangById(id_baidang);
		model.addAttribute("baidang", baidang);
		
		
		List<BinhLuan> dsBL = this.binhluanService.findByBaidang(baidang);
		
		if (dsBL.size()>0) {
			model.addAttribute("dsBL",this.binhluanService.findByBaidang(baidang));
			
			int totalPH_BD = 0;		
			model.addAttribute("totalBL_BD",dsBL.size());
			
			for(int i = 0 ; i < dsBL.size(); i++) {
				totalPH_BD += dsBL.get(i).getDsPhanHoi().size();
			}
			model.addAttribute("totalPH_BD",totalPH_BD);
		}
		
		return "admin/baidang/detail";
	}
	    
	@GetMapping("/admin/baidang/them-moi")
	public String createBaiDang(Model model) {
		BaiDang baidang = new BaiDang();
		NguoiDung account = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    String currentUserName = authentication.getName();
		    account = nguoidungService.getByUsername(currentUserName);
		    model.addAttribute("listTruyen",truyenService.SearchTruyenNotPost(account.getId_taikhoan()));

		}
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			
		baidang.setNgay_dang(sdf3.format(timestamp));
	    model.addAttribute("baidang", baidang);
	    return "admin/baidang/add_baidang";
	}
	    
	@PostMapping("/admin/baidang/save-baidang")
	public String saveBaiDang(@ModelAttribute("baidang") BaiDang baidang,
			RedirectAttributes redirectAttrs){
		
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		NguoiDung account = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    String currentUserName = authentication.getName();
		    account = nguoidungService.getByUsername(currentUserName);
		}
		
		baidang.setNgay_dang(sdf3.format(timestamp));
		baidang.setTaikhoan(account);
		this.baiDangService.saveBaiDang(baidang);
		redirectAttrs.addFlashAttribute("alertType", "success");
		redirectAttrs.addFlashAttribute("alertText", "Thành công");
		return "redirect:/admin/baidang";
	}
	

	
	@GetMapping("/admin/baidang/xoa-baidang/{id_baidang}")
	public String deleteTruyen(@PathVariable(value="id_baidang") int id_baidang, 
			RedirectAttributes redirectAttrs) {
		this.baiDangService.removeBaiDang(id_baidang);
		redirectAttrs.addFlashAttribute("alertType", "success");
		redirectAttrs.addFlashAttribute("alertText", "Xóa thành công");
	    return "redirect:/admin/baidang";
	}
}
