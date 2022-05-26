package thach.nv.project.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import thach.nv.project.entity.NguoiDung;
import thach.nv.project.entity.Truyen;
import thach.nv.project.repository.TruyenRepository;
import thach.nv.project.service.TruyenService;

@Service
public class TruyenServiceImpl implements TruyenService{

	@Autowired
	private TruyenRepository truyenRepo;
	@Override
	public List<Truyen> getAllTruyen() {
		// TODO Auto-generated method stub
		return this.truyenRepo.findAll();
	}

	@Override
	public Truyen saveTruyen(Truyen truyen) {
		// TODO Auto-generated method stub
		return this.truyenRepo.save(truyen);
	}

	@Override
	public void removeTruyen(int id_truyen) {
		this.truyenRepo.deleteById(id_truyen);
	}

	@Override
	public Truyen getTruyenById(int id_truyen) {
		// TODO Auto-generated method stub
		Optional<Truyen> optional = this.truyenRepo.findById(id_truyen);
		Truyen truyen = null;
		if(optional.isPresent()) {
			truyen = optional.get();
		} else {
			throw new RuntimeException("Employee not found	for id::" + id_truyen);
		}
		return truyen;
	}

	@Override
	public String getMaxId() {
		// TODO Auto-generated method stub
		return this.truyenRepo.findFirstMaTruyen();
	}

	@Override
	public List<Truyen> findTruyenByUserID(NguoiDung user_id) {
		// TODO Auto-generated method stub
		return this.truyenRepo.findByUserID(user_id);
	}

	@Override
	public List<Truyen> SearchTruyenWithID(int theloai_id) {
		// TODO Auto-generated method stub
		return this.truyenRepo.SearchTruyenWithID(theloai_id);
	}

	@Override
	public List<Truyen> SearchTruyenName(String truyen_ten) {
		// TODO Auto-generated method stub
		return this.truyenRepo.SearchTruyenName(truyen_ten);
	}

	@Override
	public Truyen SelectNewTruyen(int user_id) {
		// TODO Auto-generated method stub
		return this.truyenRepo.SelectNewTruyen(user_id);
	}

	@Override
	public int TotalTruyen(int user_id) {
		// TODO Auto-generated method stub
		return this.truyenRepo.TotalTruyen(user_id);
	}

	@Override
	public int TotalChuongOnThang(int user_id, int month) {
		// TODO Auto-generated method stub
		return this.truyenRepo.TotalChuongOnThang(user_id, month);
	}

	@Override
	public int TotalBinhLuanOnThang(int user_id,int month) {
		// TODO Auto-generated method stub
		return this.truyenRepo.TotalBinhLuanOnThang(user_id,month);
	}

	

	

}
