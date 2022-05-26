package thach.nv.project.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import thach.nv.project.entity.NguoiDung;
import thach.nv.project.repository.NguoiDungRepository;
import thach.nv.project.service.NguoiDungService;
@Service
public class NguoiDungServiceImpl implements NguoiDungService{

	@Autowired
	private NguoiDungRepository nguoidungRepo;
	
	@Override
	public List<NguoiDung> getAllAccounts() {
		// TODO Auto-generated method stub
		return this.nguoidungRepo.findAll();
	}

	@Override
	@Transactional
	public NguoiDung saveAccount(NguoiDung nguoidung) {
		// TODO Auto-generated method stub
		return this.nguoidungRepo.save(nguoidung);
	}

	@Override
	public NguoiDung getByUsername(String tenTaiKhoan) {
		// TODO Auto-generated method stub
		return this.nguoidungRepo.findByTaikhoan(tenTaiKhoan);
	}

	@Override
	public NguoiDung getById(int idTaiKhoan) {
		// TODO Auto-generated method stub
		Optional<NguoiDung> optional = this.nguoidungRepo.findById(idTaiKhoan);
		NguoiDung account = null;
		if(optional.isPresent()) {
			account = optional.get();
		} else {
			throw new RuntimeException("Account not found	for id:" + idTaiKhoan);
		}
		return account;
	}

	@Override
	public void removeAccountById(int idTaiKhoan) {
		this.nguoidungRepo.deleteById(idTaiKhoan);
		
	}

	@Override
	public NguoiDung findTopByTaikhoan(String tenTaiKhoan) {
		// TODO Auto-generated method stub
		return this.nguoidungRepo.findTopByTaikhoan(tenTaiKhoan);
	}

}
