package thach.nv.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import thach.nv.project.entity.TheLoai;
import thach.nv.project.repository.TheLoaiRepository;
import thach.nv.project.service.TheLoaiService;

@Service
public class TheLoaiServiceImpl implements TheLoaiService {

	@Autowired
	private TheLoaiRepository theloaiRepo;
	@Override
	public List<TheLoai> getAllTheLoai() {
		// TODO Auto-generated method stub
		return theloaiRepo.findAll();
	}

}
