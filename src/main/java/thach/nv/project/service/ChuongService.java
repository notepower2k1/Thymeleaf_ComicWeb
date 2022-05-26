package thach.nv.project.service;

import java.util.List;


import thach.nv.project.entity.Chuong;


public interface ChuongService {
	List<Chuong> getAllChuong();
	Chuong saveChuong(Chuong chuong);
	void removeChuong(int id_chuong);
	Chuong getChuongById(int id_chuong);
	List<Chuong> getAllChuongByID(int truyen_id);
	Chuong nextChuong(int chuong_id ,int truyen_id);
	Chuong previousChuong(int chuong_id ,int truyen_id);
	Integer maxChuong(int truyen_id);
	Integer minChuong(int truyen_id);
	String findFirstChuongTruyen(int truyen_id);


}
