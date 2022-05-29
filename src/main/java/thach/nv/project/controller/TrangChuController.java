package thach.nv.project.controller;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

import thach.nv.project.entity.BaiDang;
import thach.nv.project.entity.BinhLuan;
import thach.nv.project.entity.Chuong;
import thach.nv.project.entity.NguoiDung;
import thach.nv.project.entity.PhanHoi;
import thach.nv.project.entity.TheLoai;
import thach.nv.project.entity.Truyen;
import thach.nv.project.service.BaiDangService;
import thach.nv.project.service.BinhLuanService;
import thach.nv.project.service.ChuongService;
import thach.nv.project.service.NguoiDungService;
import thach.nv.project.service.PhanHoiService;
import thach.nv.project.service.RoleService;
import thach.nv.project.service.TheLoaiService;
import thach.nv.project.service.TruyenService;

@Controller
public class TrangChuController {

	@Autowired
	private TruyenService truyenService;
	
	@Autowired
	private NguoiDungService nguoidungService;
	
	@Autowired
	private TheLoaiService theloaiService;
	
	@Autowired
	private ChuongService chuongService;
	
	@Autowired
	private BaiDangService baidangService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private BinhLuanService binhluanService;
	
	@Autowired
	private PhanHoiService phanhoiService;
	
	@GetMapping( value ={"/home","/"})
	String homePageView(Model model) {
	    model.addAttribute("baidang", this.baidangService.getAllBaiDang());
	    model.addAttribute("listTheLoai",theloaiService.getAllTheLoai());
		return "user/home/homepage";
	}
	
	
	@GetMapping("/home/baidang/{baidang_id}")
	public String truyenDetailPage(@PathVariable(value="baidang_id") int baidang_id, Model model) {
		BaiDang baidang = this.baidangService.getBaiDangById(baidang_id);
		Truyen truyen = baidang.getTruyen();
		List<Chuong> dsChuong = this.chuongService.getAllChuongByID(truyen.getTruyen_id());
		
		List<BinhLuan> dsBL = this.binhluanService.SelectAllBinhLuanOnBaiDang(baidang_id);
		
		NguoiDung account = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    String currentUserName = authentication.getName();
		    account = nguoidungService.getByUsername(currentUserName);
		    
			model.addAttribute("currentUser",account);

		}
		
		model.addAttribute("chuongList", dsChuong);
		model.addAttribute("truyen", truyen);
		model.addAttribute("binhluan",dsBL);
		return "user/home/chi-tiet";
	}
	@GetMapping("/home/baidang/truyen/chuong/{id_chuong}")
	public String chuongDetailPage(@PathVariable(value="id_chuong") int id_chuong, Model model) {
		Chuong chuong = this.chuongService.getChuongById(id_chuong);
		List<Chuong> dsChuong = this.chuongService.getAllChuongByID(chuong.getTruyen().getTruyen_id());
		List<BinhLuan> dsBL = this.binhluanService.SelectAllBinhLuanOnBaiDang(chuong.getTruyen().getBaidang().getBaidang_id());

		NguoiDung account = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    String currentUserName = authentication.getName();
		    account = nguoidungService.getByUsername(currentUserName);
		    
			model.addAttribute("currentUser",account);

		}
		
		model.addAttribute("chuongList", dsChuong);
		model.addAttribute("chuong", chuong);
		model.addAttribute("maxChuong",this.chuongService.maxChuong(chuong.getTruyen().getTruyen_id()));
		model.addAttribute("minChuong",this.chuongService.minChuong(chuong.getTruyen().getTruyen_id()));
		model.addAttribute("binhluan",dsBL);

		return "user/home/chi-tiet-chuong";
	}
	@PostMapping("/home/baidang/truyen/chuong/{id_chuong}")
	public String nextDetailPage(@PathVariable(value="id_chuong") int id_chuong ,@RequestParam(value="isNext") boolean isNext) {	
		Chuong temp = this.chuongService.getChuongById(id_chuong);
		Chuong chuong = null;
		if (isNext == true)
		{
			chuong = this.chuongService.nextChuong(id_chuong, temp.getTruyen().getTruyen_id());
		}
		else {
			chuong = this.chuongService.previousChuong(id_chuong, temp.getTruyen().getTruyen_id());
		}
		return "redirect:/home/baidang/truyen/chuong/" + chuong.getChuong_id();
	}
	@GetMapping("/account")
	public String account(Model model) {
		NguoiDung account = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    String currentUserName = authentication.getName();
		    account = nguoidungService.getByUsername(currentUserName);
		}
		
			model.addAttribute("user", account);
		
		
		return "user/account/account";
		
	}
	
	@GetMapping("/account/update-info")
	public String updateInfoPage(Model model) {
		NguoiDung account = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    String currentUserName = authentication.getName();
		    account = nguoidungService.getByUsername(currentUserName);
		}
		model.addAttribute("user", account);
		return "user/account/update_info";
	}
	@PostMapping("/account/save-info")
	public String saveInfo(@ModelAttribute("user") NguoiDung account,	
			@RequestParam("file") MultipartFile file, 
			@Param("img") String img ,
			RedirectAttributes redirectAttrs){
		Path path = Paths.get("src/main/resources/static/admin/img/");
		
		NguoiDung exist = nguoidungService.getById(account.getId_taikhoan());
		if (file.isEmpty()){
			exist.setHinhanh(img);
		}
		
		
		else{
			exist.setHinhanh(file.getOriginalFilename());		
		}
			
			try {				
				  
				InputStream inputStream = file.getInputStream();
				Files.copy(inputStream, path.resolve(file.getOriginalFilename()),
						StandardCopyOption.REPLACE_EXISTING);
			}
				catch(Exception e){
					e.printStackTrace();
				}
		
		exist.setRole(roleService.getById(2));
		exist.setDia_chi(account.getDia_chi());
		exist.setHoten(account.getHoten());
		exist.setDien_thoai(account.getDien_thoai());
		this.nguoidungService.saveAccount(exist);
		redirectAttrs.addFlashAttribute("alertType", "success");
		redirectAttrs.addFlashAttribute("alertText", "Thành công");
		return "redirect:/account";
	}

	@GetMapping("/account/update-account")
	public String updateAccountPage(Model model) {
		NguoiDung account = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    String currentUserName = authentication.getName();
		    account = nguoidungService.getByUsername(currentUserName);
		}
		
		model.addAttribute("account", account);
		return "user/account/update_account";
	}
	@PostMapping("/account/update-account")
	public String updateAccount(@RequestParam("accountId") int accountId,		
			@RequestParam("password") String password) {
		
		NguoiDung account = this.nguoidungService.getById(accountId);
		account.setMatKhau(passwordEncoder.encode(password));
		this.nguoidungService.saveAccount(account);
		
		return "redirect:/logout";
	}
	@GetMapping("/about")
	public String aboutPage(Model model) {
		return "user/home/about";
	}
	
	@GetMapping("/contact")
	public String contactPage(Model model) {
		return "user/home/contact";
	}
	
	
	@PostMapping("/baidang/save-comment")
	public String saveComment(@ModelAttribute("binhluan") BinhLuan binhluan , @RequestParam(value="binh_luan") String binh_luan , 
			@RequestParam(value="baidang_id") int baidang_id, @Param("isChuong") boolean isChuong, @Param("chuong_id") int chuong_id,
			RedirectAttributes redirectAttrs){
		
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        
        BaiDang baidang = this.baidangService.getBaiDangById(baidang_id);
		NguoiDung account = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    String currentUserName = authentication.getName();
		    account = nguoidungService.getByUsername(currentUserName);
		}

		binhluan.setBinh_luan(binh_luan);
		binhluan.setBaidang(baidang);
		binhluan.setAccount(account);
		binhluan.setThoi_gian(sdf3.format(timestamp));
		this.binhluanService.saveBinhLuan(binhluan);
		redirectAttrs.addFlashAttribute("alertType", "success");
		redirectAttrs.addFlashAttribute("alertText", "Thành công");
		
		if (isChuong == true)
		{
			return "redirect:/home/baidang/truyen/chuong/" + chuong_id;
		}
		else
		{
			return "redirect:/home/baidang/" + baidang_id;

		}
	}
	
	@GetMapping("/baidang/{baidang_id}/binhluan/xoa-binhluan/{binhluan_id}")
	public String deleteBinhLuanOnBaiDang(@PathVariable(value="binhluan_id") int binhluan_id, 
			@PathVariable(value="baidang_id") int baidang_id) {
		this.binhluanService.removeBinhLuan(binhluan_id);
		
		return "redirect:/home/baidang/" + baidang_id;
	}

	@GetMapping("/baidang/{baidang_id}/phanhoi/xoa-phanhoi/{phanhoi_id}")
	public String deletePhanHoiOnBaiDang(@PathVariable(value="phanhoi_id") int phanhoi_id, 
			@PathVariable(value="baidang_id") int baidang_id) {
		this.phanhoiService.removePhanHoi(phanhoi_id);
		
		return "redirect:/home/baidang/" + baidang_id;
	}
	
	@PostMapping("/baidang/save-reply")
	public String saveComment(@ModelAttribute("phanhoi") PhanHoi phanhoi , @RequestParam(value="phan_hoi") String phan_hoi ,
			@RequestParam(value="binhluanID") int binhluanID,
			@RequestParam(value="baidang_id") int baidang_id,
			@Param("isChuong") boolean isChuong, @Param("chuong_id") int chuong_id
			){
		

		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		NguoiDung account = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    String currentUserName = authentication.getName();
		    account = nguoidungService.getByUsername(currentUserName);
		}
		BinhLuan binhluan = this.binhluanService.getBinhLuanById(binhluanID);
		phanhoi.setAccount(account);
		phanhoi.setBinhluan(binhluan);
		phanhoi.setThoi_gian(sdf3.format(timestamp));
		phanhoi.setPhan_hoi(phan_hoi);
		this.phanhoiService.savePhanHoi(phanhoi);
		
		if (isChuong == true)
		{
			return "redirect:/home/baidang/truyen/chuong/" + chuong_id;
		}
		else
		{
			return "redirect:/home/baidang/" + baidang_id;
		}
	}
	
	@GetMapping("/baidang/truyen/chuong/{chuong_id}/binhluan/xoa-binhluan/{binhluan_id}")
	public String deleteBinhLuanOnChuong(@PathVariable(value="binhluan_id") int binhluan_id, 
			@PathVariable(value="chuong_id") int chuong_id) {
		this.binhluanService.removeBinhLuan(binhluan_id);
		
		return "redirect:/home/baidang/truyen/chuong/" + chuong_id;
	}
	@GetMapping("/baidang/truyen/chuong/{chuong_id}/phanhoi/xoa-phanhoi/{phanhoi_id}")
	public String deletePhanHoiOnChuong(@PathVariable(value="phanhoi_id") int phanhoi_id, 
			@PathVariable(value="chuong_id") int chuong_id) {
		this.phanhoiService.removePhanHoi(phanhoi_id);		
		return "redirect:/home/baidang/truyen/chuong/" + chuong_id;
	}
	
	
	@GetMapping("/the-loai/{idloai}")
	public String TheLoaiPage(@PathVariable(value="idloai") int idloai, Model model) {
	    List<TheLoai> categories = this.theloaiService.getAllTheLoai();
	    model.addAttribute("listTheLoai", categories);

	    List<Truyen> dsTruyen = this.truyenService.SearchTruyenWithID(idloai);
	    if (dsTruyen.size()!=0) {
	    	model.addAttribute("truyen", dsTruyen);
			return "user/home/search_page";
	    }
	    else {
			return "user/home/comic-not-found";
	    }
	}
	
	@GetMapping("/search-page")
	public String searchPage(String tenNT, Model model) {
	    List<TheLoai> categories = this.theloaiService.getAllTheLoai();
	    model.addAttribute("listTheLoai", categories);

	    List<Truyen> dsTruyen = this.truyenService.SearchTruyenName(tenNT);
	    if (dsTruyen.size()!=0) {
	    	model.addAttribute("truyen", dsTruyen);
			return "user/home/search_page";
	    }
	    else {
			return "user/home/comic-not-found";
	    }
	}
}
