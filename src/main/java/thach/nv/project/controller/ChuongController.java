package thach.nv.project.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import thach.nv.project.entity.Chuong;
import thach.nv.project.service.ChuongService;
import thach.nv.project.service.TruyenService;

@Controller
public class ChuongController {

	@Autowired
	private ChuongService chuongService;

	@Autowired
	private TruyenService truyenService;
	
	@GetMapping("/admin/chuong/{id_truyen}")
	public String chuongListPage(@PathVariable(value="id_truyen") int id_truyen,Model model) {
		List<Chuong> dsChuong = this.chuongService.getAllChuongByID(id_truyen);
		model.addAttribute("chuongList", dsChuong);
		model.addAttribute("truyen_id",id_truyen);
		model.addAttribute("tenTruyen",truyenService.getTruyenById(id_truyen).getTruyen_ten());
		return "admin/chuong/index";
	}
	
	@GetMapping("/admin/chuong/chi-tiet/{id_chuong}")
	public String chuongDetailPage(@PathVariable(value="id_chuong") int id_chuong, Model model) {
		Chuong chuong = this.chuongService.getChuongById(id_chuong);
		
		model.addAttribute("chuong", chuong);
		return "admin/chuong/detail";
	}
	
	
	    
	@GetMapping("/admin/chuong/them-moi/{truyen_id}")
	public String createTruyen(@PathVariable(value="truyen_id") int truyen_id,Model model) {
		Chuong chuong = new Chuong();
		
		
		String temp = this.chuongService.findFirstChuongTruyen(truyen_id);
		
		if (temp!=null)
		{
			int index = temp.indexOf("Chương");
			
			String oldNumb = temp.substring(index + 7,temp.length());
				
			int newNumb = Integer.parseInt(oldNumb)  + 1;
			
			String newChuongSo = temp.replace(oldNumb, String.valueOf(newNumb));
			

			chuong.setChuong_so(newChuongSo);
		}
		
		
	    model.addAttribute("chuong", chuong);
	    model.addAttribute("truyen_id",truyen_id);
	    return "admin/chuong/add_chuong";
	}
	    
	@PostMapping("/admin/chuong/save-chuong")
	public String saveTruyen(@ModelAttribute("chuong") Chuong chuong,	
			RedirectAttributes redirectAttrs){
		this.chuongService.saveChuong(chuong);
		redirectAttrs.addFlashAttribute("alertType", "success");
		redirectAttrs.addFlashAttribute("alertText", "Thành công");
		return "redirect:/admin/chuong/" + chuong.getTruyen().getTruyen_id();
	}
	
	@GetMapping("/admin/chuong/cap-nhat/{id_chuong}")
	public String updateChuong(@PathVariable(value="id_chuong") int id_chuong, Model model) {
		Chuong chuong = this.chuongService.getChuongById(id_chuong);
	    model.addAttribute("chuong", chuong);
	    return "admin/chuong/update_chuong";
	}
	
	@GetMapping("/admin/chuong/xoa-chuong/{id_chuong}")
	public String removeChuong(@PathVariable(value="id_chuong") int id_chuong, 
			RedirectAttributes redirectAttrs) {
		Chuong chuong = this.chuongService.getChuongById(id_chuong);

		this.chuongService.removeChuong(id_chuong);
		redirectAttrs.addFlashAttribute("alertType", "success");
		redirectAttrs.addFlashAttribute("alertText", "Xóa thành công");
		return "redirect:/admin/chuong/" + chuong.getTruyen().getTruyen_id();
	}
}
