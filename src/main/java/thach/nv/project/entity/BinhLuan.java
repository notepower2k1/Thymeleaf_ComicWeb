package thach.nv.project.entity;

import java.sql.Date;
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
@Table(name = "binhluan")
public class BinhLuan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "binhluan_id", nullable = false)
	private int binhluan_id;
	@Column(name = "binh_luan", nullable = false)
	private String binh_luan;
	
	@Column(name = "thoi_gian", nullable = false)
	private String thoi_gian;
	
	public String getThoi_gian() {
		return thoi_gian;
	}

	public void setThoi_gian(String thoi_gian) {
		this.thoi_gian = thoi_gian;
	}

	@ManyToOne
	@JoinColumn(name = "baidang_id", insertable = true, updatable = true)
	private BaiDang baidang;
	
	@ManyToOne
	@JoinColumn(name = "id_taikhoan", insertable = true, updatable = true)
	private NguoiDung account;

	public int getBinhluan_id() {
		return binhluan_id;
	}

	public void setBinhluan_id(int binhluan_id) {
		this.binhluan_id = binhluan_id;
	}

	public String getBinh_luan() {
		return binh_luan;
	}

	public void setBinh_luan(String binh_luan) {
		this.binh_luan = binh_luan;
	}

	

	public NguoiDung getAccount() {
		return account;
	}

	public void setAccount(NguoiDung account) {
		this.account = account;
	}

	

	

	public BinhLuan(int binhluan_id, String binh_luan, String thoi_gian, BaiDang baidang, NguoiDung account,
			List<PhanHoi> dsPhanHoi) {
		super();
		this.binhluan_id = binhluan_id;
		this.binh_luan = binh_luan;
		this.thoi_gian = thoi_gian;
		this.baidang = baidang;
		this.account = account;
		this.dsPhanHoi = dsPhanHoi;
	}

	public BinhLuan() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public BaiDang getBaidang() {
		return baidang;
	}

	public void setBaidang(BaiDang baidang) {
		this.baidang = baidang;
	}

	public List<PhanHoi> getDsPhanHoi() {
		return dsPhanHoi;
	}

	public void setDsPhanHoi(List<PhanHoi> dsPhanHoi) {
		this.dsPhanHoi = dsPhanHoi;
	}

	@OneToMany(mappedBy = "binhluan")
    private List<PhanHoi> dsPhanHoi = new ArrayList<>();
	
	
}
