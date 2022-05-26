package thach.nv.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import thach.nv.project.entity.BinhLuan;

public interface BinhLuanRepository extends JpaRepository<BinhLuan, Integer>{

	@Query(value="SELECT * from binhluan join truyen on binhluan.truyen_id = truyen.truyen_id WHERE binhluan.id_taikhoan= :#{#id_taikhoan} ",nativeQuery = true)
	List<BinhLuan> SelectAllBinhLuan(@Param("id_taikhoan") int id_taikhoan);
	
	
	@Query(value=" SELECT * from binhluan join truyen on binhluan.truyen_id = truyen.truyen_id WHERE truyen.truyen_id= :#{#id_truyen} ",nativeQuery = true)
	List<BinhLuan> SelectAllBinhLuanOnTruyen(@Param("id_truyen") int id_truyen);

}
