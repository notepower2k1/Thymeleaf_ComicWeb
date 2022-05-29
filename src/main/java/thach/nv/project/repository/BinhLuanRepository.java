package thach.nv.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import thach.nv.project.entity.BaiDang;
import thach.nv.project.entity.BinhLuan;

public interface BinhLuanRepository extends JpaRepository<BinhLuan, Integer>{

	@Query(value="SELECT * from binhluan join baidang on binhluan.baidang_id  = baidang.baidang_id WHERE binhluan.id_taikhoan= :#{#id_taikhoan} ",nativeQuery = true)
	List<BinhLuan> SelectAllBinhLuan(@Param("id_taikhoan") int id_taikhoan);
	
	
	@Query(value=" SELECT * from binhluan join baidang on binhluan.baidang_id  = baidang.baidang_id WHERE baidang.baidang_id= :#{#baidang_id} ",nativeQuery = true)
	List<BinhLuan> SelectAllBinhLuanOnBaiDang(@Param("baidang_id") int baidang_id);

	
	@Query(value="SELECT COUNT(*) FROM binhluan JOIN baidang on binhluan.baidang_id = baidang.baidang_id WHERE baidang.id_taikhoan = :#{#user_id} and MONTH(binhluan.thoi_gian) = :#{#month}",nativeQuery = true)
	int TotalBinhLuanOnThang(@Param("user_id") int user_id,@Param("month") int month);
	
	
	@Query(value="SELECT Count(binhluan.binh_luan) from binhluan join baidang on binhluan.baidang_id = baidang.baidang_id WHERE baidang.id_taikhoan = :#{#user_id}",nativeQuery = true)
	int TotalBinhLuan(@Param("user_id") int user_id);

	
	@Query(value="SELECT Count(phanhoi.phan_hoi) FROM (phanhoi JOIN binhluan on phanhoi.binhluan_id = binhluan.binhluan_id) join baidang on binhluan.baidang_id = baidang.baidang_id where baidang.id_taikhoan = :#{#user_id}",nativeQuery = true)
	int TotalPhanHoi(@Param("user_id") int user_id);
	
	
	
	
	@Query(value="SELECT count(*) from binhluan WHERE binhluan.baidang_id = :#{#baidang_id} ",nativeQuery = true)
	int TotalBL_BD(@Param("baidang_id") int baidang_id);
	
	@Query(value="SELECT count(*) from phanhoi WHERE phanhoi.binhluan_id = :#{#binhluan_id}  ",nativeQuery = true)
	int TotalPH_BD(@Param("binhluan_id") int binhluan_id);
	
	
	List<BinhLuan> findByBaidang(BaiDang baidang);
}
