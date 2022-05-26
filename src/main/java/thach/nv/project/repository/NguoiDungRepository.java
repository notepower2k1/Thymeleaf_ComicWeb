package thach.nv.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import thach.nv.project.entity.NguoiDung;

public interface NguoiDungRepository extends JpaRepository<NguoiDung, Integer>{

	NguoiDung findByTaikhoan(String TaiKhoan);

	NguoiDung findTopByTaikhoan(String TaiKhoan);
}
