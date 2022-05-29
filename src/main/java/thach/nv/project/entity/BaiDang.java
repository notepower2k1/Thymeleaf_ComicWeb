package thach.nv.project.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "baidang")
public class BaiDang {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "baidang_id", nullable = false)
	private int baidang_id;
	
	@Column(name = "ngay_dang", nullable = false)
	private String ngay_dang;	
	
	
	@ManyToOne
	@JoinColumn(name = "id_taikhoan", insertable = true, updatable = true)
	private NguoiDung taikhoan;
	
	
	@ManyToOne
	@JoinColumn(name = "truyen_id", insertable = true, updatable = true)
	private Truyen truyen;


	@OneToMany(mappedBy = "baidang")
    private List<BinhLuan> dsBL = new ArrayList<>();
	
	
	
	public List<BinhLuan> getDsBL() {
		return dsBL;
	}


	public void setDsBL(List<BinhLuan> dsBL) {
		this.dsBL = dsBL;
	}


	public int getBaidang_id() {
		return baidang_id;
	}


	public void setBaidang_id(int baidang_id) {
		this.baidang_id = baidang_id;
	}


	public String getNgay_dang() {
		return ngay_dang;
	}


	public void setNgay_dang(String ngay_dang) {
		this.ngay_dang = ngay_dang;
	}


	

	public NguoiDung getTaikhoan() {
		return taikhoan;
	}


	public void setTaikhoan(NguoiDung taikhoan) {
		this.taikhoan = taikhoan;
	}


	public Truyen getTruyen() {
		return truyen;
	}


	public void setTruyen(Truyen truyen) {
		this.truyen = truyen;
	}


	public BaiDang(int baidang_id, String ngay_dang, NguoiDung taikhoan, Truyen truyen) {
		super();
		this.baidang_id = baidang_id;
		this.ngay_dang = ngay_dang;
		this.taikhoan = taikhoan;
		this.truyen = truyen;
	}


	public BaiDang() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
