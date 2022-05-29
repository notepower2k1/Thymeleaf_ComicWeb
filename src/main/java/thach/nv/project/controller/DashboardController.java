package thach.nv.project.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import thach.nv.project.entity.BaiDang;
import thach.nv.project.entity.BinhLuan;
import thach.nv.project.entity.NguoiDung;
import thach.nv.project.entity.Truyen;
import thach.nv.project.service.BaiDangService;
import thach.nv.project.service.BinhLuanService;
import thach.nv.project.service.NguoiDungService;
import thach.nv.project.service.TruyenService;

@Controller
public class DashboardController {

	@Autowired
	private TruyenService truyenService;
	

	@Autowired
	private NguoiDungService nguoidungService;
	
	@Autowired
	private BinhLuanService binhluanService;
	
	@Autowired
	private BaiDangService baiDangService;
	
	@GetMapping("/admin/dashboard")
	public String dashboard(Model model) {
		NguoiDung account = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    String currentUserName = authentication.getName();
		    account = nguoidungService.getByUsername(currentUserName);	
			
			LocalDateTime localDateTime = LocalDateTime.now();
			LocalDate localDate = localDateTime.toLocalDate();	
			
			
			
			int totalBL = this.binhluanService.TotalBinhLuan(account.getId_taikhoan());
			int totalPH = this.binhluanService.TotalPhanHoi(account.getId_taikhoan());

			model.addAttribute("totalBaiDang",this.baiDangService.TotalBaiDangOnThang(account.getId_taikhoan(), localDate.getMonthValue()));	
			model.addAttribute("totalTruyen",this.truyenService.TotalTruyen(account.getId_taikhoan()));
			model.addAttribute("totalTT",totalBL + totalPH);
			
			BaiDang newBD = this.baiDangService.newBaiDang(account.getId_taikhoan());
			if (newBD!=null) {
				model.addAttribute("newBaiDang",newBD);
				
				
				List<BinhLuan> dsBL = this.binhluanService.findByBaidang(newBD);
				if (dsBL.size() > 0) {
					model.addAttribute("dsBL",dsBL);
					
					
					int totalPH_BD = 0;
					
					model.addAttribute("totalBL_BD",dsBL.size());
					
					for(int i = 0 ; i < dsBL.size(); i++) {
						totalPH_BD += dsBL.get(i).getDsPhanHoi().size();
					}
					model.addAttribute("totalPH_BD",totalPH_BD);
				}
			}
		
		}
		
		
		return "admin/dashboard";
	}
	
	
}
