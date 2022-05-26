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

import thach.nv.project.entity.BinhLuan;
import thach.nv.project.entity.NguoiDung;
import thach.nv.project.entity.Truyen;
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
	
	@GetMapping("/admin/dashboard")
	public String dashboard(Model model) {
		NguoiDung account = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    String currentUserName = authentication.getName();
		    account = nguoidungService.getByUsername(currentUserName);	
			model.addAttribute("newtruyen",this.truyenService.SelectNewTruyen(account.getId_taikhoan()));
			
			LocalDateTime localDateTime = LocalDateTime.now();
			LocalDate localDate = localDateTime.toLocalDate();	
			Truyen newTruyen = this.truyenService.SelectNewTruyen(account.getId_taikhoan());
			
			List<BinhLuan> dsBL = this.binhluanService.SelectAllBinhLuan(account.getId_taikhoan());
			if (dsBL.size() > 0) {
				model.addAttribute("dsBL",dsBL);
			}
			if (newTruyen!=null) {
				List<BinhLuan> BL_Truyen = this.binhluanService.SelectAllBinhLuanOnTruyen(newTruyen.getTruyen_id());
				model.addAttribute("newTruyen",newTruyen);
				model.addAttribute("TotalBL_Truyen",BL_Truyen.size());
			}
					
			model.addAttribute("totalTruyen",this.truyenService.TotalTruyen(account.getId_taikhoan()));
			model.addAttribute("totalChuong",this.truyenService.TotalChuongOnThang(account.getId_taikhoan(),localDate.getMonthValue()));
			model.addAttribute("totalBinhLuan",this.truyenService.TotalBinhLuanOnThang(account.getId_taikhoan(),localDate.getMonthValue()));		
		}
		
		
		return "admin/dashboard";
	}
	
	
}
