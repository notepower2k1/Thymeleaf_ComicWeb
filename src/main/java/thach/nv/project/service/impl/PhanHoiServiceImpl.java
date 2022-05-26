package thach.nv.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import thach.nv.project.entity.PhanHoi;
import thach.nv.project.repository.PhanHoiRepository;
import thach.nv.project.service.PhanHoiService;
@Service
public class PhanHoiServiceImpl implements PhanHoiService {

	@Autowired
	private PhanHoiRepository PhanHoiRepo;
	@Override
	public List<PhanHoi> getAllPhanHoi() {
		// TODO Auto-generated method stub
		return this.PhanHoiRepo.findAll();
	}

	@Override
	public PhanHoi savePhanHoi(PhanHoi phanhoi_id) {
		// TODO Auto-generated method stub
		return this.PhanHoiRepo.save(phanhoi_id);
	}

	@Override
	public void removePhanHoi(int phanhoi_id) {
		// TODO Auto-generated method stub
		this.PhanHoiRepo.deleteById(phanhoi_id);
	}

	@Override
	public PhanHoi getPhanHoiById(int phanhoi_id) {
		// TODO Auto-generated method stub
		return this.PhanHoiRepo.getById(phanhoi_id);
	}

	
}
