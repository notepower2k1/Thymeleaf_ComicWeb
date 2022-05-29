package thach.nv.project.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import thach.nv.project.entity.BaiDang;
import thach.nv.project.entity.NguoiDung;

public interface BaiDangRepository extends JpaRepository<BaiDang, Integer>{

	
	List<BaiDang> findByTaikhoan(NguoiDung user_id);
	
	

	@Query(value="SELECT COUNT(*) FROM baidang WHERE baidang.id_taikhoan = :#{#user_id} and MONTH(baidang.ngay_dang) = :#{#month}",nativeQuery = true)
	int TotalBaiDangOnThang(@Param("user_id") int user_id, @Param("month") int month);

	
	
	@Query(value="SELECT * FROM baidang WHERE baidang.ngay_dang=(SELECT MAX(ngay_dang) FROM baidang WHERE baidang.id_taikhoan = :#{#user_id})",nativeQuery = true)
	BaiDang newBaiDang(@Param("user_id") int user_id);
}
