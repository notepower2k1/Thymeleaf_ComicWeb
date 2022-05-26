package thach.nv.project.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import thach.nv.project.entity.Chuong;
import thach.nv.project.entity.Truyen;
import thach.nv.project.repository.ChuongRepository;
import thach.nv.project.service.ChuongService;
@Service
public class ChuongServiceImpl implements ChuongService {

	@Autowired
	private ChuongRepository chuongRepo;
	
	@Override
	public List<Chuong> getAllChuong() {
		// TODO Auto-generated method stub
		return chuongRepo.findAll();
	}

	@Override
	public Chuong saveChuong(Chuong chuong) {
		// TODO Auto-generated method stub
		return chuongRepo.save(chuong);
	}

	@Override
	public void removeChuong(int id_chuong) {
		// TODO Auto-generated method stub
		chuongRepo.deleteById(id_chuong);
	}

	@Override
	public List<Chuong> getAllChuongByID(int id_truyen) {
		// TODO Auto-generated method stub
		return chuongRepo.getAllChuongByID(id_truyen);
	}

	@Override
	public Chuong getChuongById(int id_chuong) {
		// TODO Auto-generated method stub
		Optional<Chuong> optional = this.chuongRepo.findById(id_chuong);
		Chuong chuong = null;
		if(optional.isPresent()) {
			chuong = optional.get();
		} else {
			throw new RuntimeException("Employee not found	for id::" + id_chuong);
		}
		return chuong;
	}

	@Override
	public Chuong nextChuong(int chuong_id, int truyen_id) {
		// TODO Auto-generated method stub
		return this.chuongRepo.nextChuong(chuong_id, truyen_id);
	}

	@Override
	public Chuong previousChuong(int chuong_id, int truyen_id) {
		// TODO Auto-generated method stub
		return this.chuongRepo.previousChuong(chuong_id, truyen_id);
	}

	@Override
	public Integer maxChuong(int truyen_id) {
		// TODO Auto-generated method stub
		return this.chuongRepo.maxChuong(truyen_id);
	}

	@Override
	public Integer minChuong(int truyen_id) {
		// TODO Auto-generated method stub
		return this.chuongRepo.minChuong(truyen_id);
	}

	@Override
	public String findFirstChuongTruyen(int truyen_id) {
		// TODO Auto-generated method stub
		return this.chuongRepo.findFirstChuongTruyen(truyen_id);
	}

}
