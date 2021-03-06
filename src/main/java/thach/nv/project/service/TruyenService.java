package thach.nv.project.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import thach.nv.project.entity.NguoiDung;
import thach.nv.project.entity.Truyen;


public interface TruyenService {
	List<Truyen> getAllTruyen();
	Truyen saveTruyen(Truyen truyen);
	void removeTruyen(int id_truyen);
	Truyen getTruyenById(int id_truyen);
	String getMaxId();
	List<Truyen> findTruyenByUserID(NguoiDung user_id);
	
	List<Truyen> SearchTruyenWithID(int theloai_id);
	
	List<Truyen> SearchTruyenName(String truyen_ten);
	

	int TotalTruyen(int user_id);


	List<Truyen> SearchTruyenNotPost(int user_id);
}
