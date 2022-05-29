package thach.nv.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import thach.nv.project.entity.BaiDang;
import thach.nv.project.entity.BinhLuan;
import thach.nv.project.repository.BinhLuanRepository;
import thach.nv.project.service.BinhLuanService;
@Service
public class BinhLuanServiceImpl implements BinhLuanService {

	@Autowired
	private BinhLuanRepository binhluanRepo;
	
	@Override
	public List<BinhLuan> getAllBinhLuan() {
		// TODO Auto-generated method stub
		return this.binhluanRepo.findAll();
	}

	@Override
	public BinhLuan saveBinhLuan(BinhLuan binhluan_id) {
		// TODO Auto-generated method stub
		return this.binhluanRepo.save(binhluan_id);
	}

	@Override
	public void removeBinhLuan(int binhluan_id) {
		// TODO Auto-generated method stub
		this.binhluanRepo.deleteById(binhluan_id);
	}

	@Override
	public BinhLuan getBinhLuanById(int binhluan_id) {
		// TODO Auto-generated method stub
		return this.binhluanRepo.getById(binhluan_id);
	}

	@Override
	public List<BinhLuan> SelectAllBinhLuan(int id_taikhoan) {
		// TODO Auto-generated method stub
		return this.binhluanRepo.SelectAllBinhLuan(id_taikhoan);
	}

	@Override
	public List<BinhLuan> SelectAllBinhLuanOnBaiDang(int baidang_id) {
		// TODO Auto-generated method stub
		return this.binhluanRepo.SelectAllBinhLuanOnBaiDang(baidang_id);
	}

	@Override
	public int TotalBinhLuan(int user_id) {
		// TODO Auto-generated method stub
		return this.binhluanRepo.TotalBinhLuan(user_id);
	}

	@Override
	public int TotalPhanHoi(int user_id) {
		// TODO Auto-generated method stub
		return this.binhluanRepo.TotalPhanHoi(user_id);
	}

	@Override
	public int TotalBL_BD(int baidang_id) {
		// TODO Auto-generated method stub
		return this.binhluanRepo.TotalBL_BD(baidang_id);
	}

	@Override
	public int TotalPH_BD(int binhluan_id) {
		// TODO Auto-generated method stub
		return this.binhluanRepo.TotalPH_BD(binhluan_id);
	}

	@Override
	public List<BinhLuan> findByBaidang(BaiDang baidang) {
		// TODO Auto-generated method stub
		return this.binhluanRepo.findByBaidang(baidang);
	}

}
