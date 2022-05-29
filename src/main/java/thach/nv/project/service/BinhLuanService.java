package thach.nv.project.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import thach.nv.project.entity.BaiDang;
import thach.nv.project.entity.BinhLuan;

public interface BinhLuanService {

	List<BinhLuan> getAllBinhLuan();
	BinhLuan saveBinhLuan(BinhLuan 	binhluan_id);
	void removeBinhLuan(int binhluan_id);
	BinhLuan getBinhLuanById(int binhluan_id);
	
	
	List<BinhLuan> SelectAllBinhLuan(int id_taikhoan);
	List<BinhLuan> SelectAllBinhLuanOnBaiDang(int id_baidang);
	
	
	int TotalBinhLuan(int user_id);
	int TotalPhanHoi(int user_id);
	int TotalBL_BD(int baidang_id);
	int TotalPH_BD(int binhluan_id);

	List<BinhLuan> findByBaidang(BaiDang baidang);

}
