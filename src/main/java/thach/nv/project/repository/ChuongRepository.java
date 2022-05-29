package thach.nv.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import thach.nv.project.entity.Chuong;


public interface ChuongRepository extends JpaRepository<Chuong, Integer>{
	@Query(value="SELECT * from chuong WHERE truyen_id = :#{#truyen_id} ", nativeQuery = true)
	List<Chuong> getAllChuongByID(int truyen_id);
	
	@Query(value="SELECT * FROM chuong WHERE chuong_id > :#{#chuong_id} and truyen_id = :#{#truyen_id} ORDER BY chuong_id LIMIT 1", nativeQuery = true)
	Chuong nextChuong(@Param("chuong_id") int chuong_id , @Param("truyen_id") int truyen_id);
	
	@Query(value="SELECT * FROM chuong WHERE chuong_id < :#{#chuong_id} and truyen_id = :#{#truyen_id} ORDER BY chuong_id DESC LIMIT 1", nativeQuery = true)
	Chuong previousChuong(@Param("chuong_id") int chuong_id , @Param("truyen_id") int truyen_id);
	
	
	@Query(value="SELECT MAX(chuong_id) FROM chuong WHERE truyen_id = :#{#truyen_id}",nativeQuery = true)
	Integer maxChuong(@Param("truyen_id") int truyen_id);
	

	@Query(value="SELECT MIN(chuong_id) FROM chuong WHERE truyen_id = :#{#truyen_id}",nativeQuery = true)
	Integer minChuong(@Param("truyen_id") int truyen_id);
	
	
	@Query(value="Select chuong_so From chuong WHERE truyen_id = :#{#truyen_id} Order By chuong_id Desc Limit 1", nativeQuery = true)
	String findFirstChuongTruyen(@Param("truyen_id") int truyen_id);
}
