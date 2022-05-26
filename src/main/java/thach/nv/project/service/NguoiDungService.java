package thach.nv.project.service;

import java.util.List;

import thach.nv.project.entity.NguoiDung;


public interface NguoiDungService {
	List<NguoiDung> getAllAccounts();
	NguoiDung saveAccount(NguoiDung nguoidung);
	NguoiDung getByUsername(String tenTaiKhoan);
	NguoiDung getById(int idTaiKhoan);
	void removeAccountById(int idTaiKhoan);
	
	NguoiDung findTopByTaikhoan(String tenTaiKhoan);
}
