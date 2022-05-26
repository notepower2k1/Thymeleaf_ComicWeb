package thach.nv.project.service;

import java.util.List;


import thach.nv.project.entity.BinhLuan;

public interface BinhLuanService {

	List<BinhLuan> getAllBinhLuan();
	BinhLuan saveBinhLuan(BinhLuan 	binhluan_id);
	void removeBinhLuan(int binhluan_id);
	BinhLuan getBinhLuanById(int binhluan_id);
	
	
	List<BinhLuan> SelectAllBinhLuan(int id_taikhoan);
	List<BinhLuan> SelectAllBinhLuanOnTruyen(int id_truyen);

}
