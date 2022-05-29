package thach.nv.project.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import thach.nv.project.entity.BaiDang;
import thach.nv.project.entity.NguoiDung;
import thach.nv.project.repository.BaiDangRepository;
import thach.nv.project.service.BaiDangService;

@Service
public class BaiDangServiceImpl implements BaiDangService {

	@Autowired
	private BaiDangRepository baidangRepo;
	
	@Override
	public List<BaiDang> getAllBaiDang() {
		// TODO Auto-generated method stub
		return this.baidangRepo.findAll();
	}

	@Override
	public BaiDang saveBaiDang(BaiDang baidang) {
		// TODO Auto-generated method stub
		return this.baidangRepo.save(baidang);
	}

	@Override
	public void removeBaiDang(int id_baidang) {
		// TODO Auto-generated method stub
		this.baidangRepo.deleteById(id_baidang);
	}

	@Override
	public BaiDang getBaiDangById(int id_baidang) {
		// TODO Auto-generated method stub
		Optional<BaiDang> optional = this.baidangRepo.findById(id_baidang);
		BaiDang baidang = null;
		if(optional.isPresent()) {
			baidang = optional.get();
		} else {
			throw new RuntimeException("Employee not found	for id::" + id_baidang);
		}
		return baidang;
	}

	@Override
	public List<BaiDang> findByTaikhoan(NguoiDung user_id) {
		// TODO Auto-generated method stub
		return this.baidangRepo.findByTaikhoan(user_id);
	}

	@Override
	public int TotalBaiDangOnThang(int user_id, int month) {
		// TODO Auto-generated method stub
		return this.baidangRepo.TotalBaiDangOnThang(user_id, month);
	}

	@Override
	public BaiDang newBaiDang(int user_id) {
		// TODO Auto-generated method stub
		return this.baidangRepo.newBaiDang(user_id);
	}

}
