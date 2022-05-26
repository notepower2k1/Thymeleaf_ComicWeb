package thach.nv.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import thach.nv.project.entity.NguoiDung;
import thach.nv.project.entity.Truyen;


public interface TruyenRepository extends JpaRepository<Truyen, Integer>{

	@Query(value="Select truyen_ma From truyen Order By truyen_ma Desc Limit 1", nativeQuery = true)
	String findFirstMaTruyen();
	
	
	
	List<Truyen> findByUserID(NguoiDung user_id);
	
	
	@Query(value="SELECT * FROM truyen WHERE truyen.theloai_id = :#{#theloai_id} ", nativeQuery = true)
	List<Truyen> SearchTruyenWithID(@Param("theloai_id") int theloai_id);
	
	
	@Query(value="SELECT * FROM truyen WHERE truyen.truyen_ten like %:truyen_ten% ", nativeQuery = true)
	List<Truyen> SearchTruyenName(@Param("truyen_ten") String truyen_ten);
	
	
	@Query(value ="SELECT * FROM truyen WHERE truyen.truyen_ngaydang=(SELECT MAX(truyen_ngaydang) FROM truyen WHERE truyen.userID= :#{#user_id})",nativeQuery = true)
	Truyen SelectNewTruyen(@Param("user_id") int user_id);
	
	
	@Query(value="SELECT COUNT(*) FROM truyen WHERE truyen.userID= :#{#user_id}",nativeQuery = true)
	int TotalTruyen(@Param("user_id") int user_id);

	@Query(value="SELECT COUNT(*) FROM chuong JOIN truyen on chuong.truyen_id = truyen.truyen_id WHERE truyen.userID = :#{#user_id} and MONTH(truyen.truyen_ngaydang) = :#{#month}",nativeQuery = true)
	int TotalChuongOnThang(@Param("user_id") int user_id, @Param("month") int month);
	
	
	@Query(value="SELECT COUNT(*) FROM binhluan JOIN truyen on binhluan.truyen_id = truyen.truyen_id WHERE truyen.userID = :#{#user_id} and MONTH(binhluan.thoi_gian) = :#{#month}",nativeQuery = true)
	int TotalBinhLuanOnThang(@Param("user_id") int user_id,@Param("month") int month);
	
}
