package thach.nv.project.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import thach.nv.project.entity.BaiDang;
import thach.nv.project.entity.NguoiDung;

public interface BaiDangService {
	List<BaiDang> getAllBaiDang();
	BaiDang saveBaiDang(BaiDang baidang);
	void removeBaiDang(int id_baidang);
	BaiDang getBaiDangById(int id_baidang);
	
	
	List<BaiDang> findByTaikhoan(NguoiDung user_id);
	int TotalBaiDangOnThang(int user_id, int month);

	BaiDang newBaiDang(int user_id);

}
