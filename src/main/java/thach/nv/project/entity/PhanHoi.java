package thach.nv.project.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "phanhoi")
public class PhanHoi {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "phanhoi_id ", nullable = false)
	private int phanhoi_id ;
	
	public int getPhanhoi_id() {
		return phanhoi_id;
	}

	public void setPhanhoi_id(int phanhoi_id) {
		this.phanhoi_id = phanhoi_id;
	}

	public String getPhan_hoi() {
		return phan_hoi;
	}

	public PhanHoi() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public void setPhan_hoi(String phan_hoi) {
		this.phan_hoi = phan_hoi;
	}

	public BinhLuan getBinhluan() {
		return binhluan;
	}

	public void setBinhluan(BinhLuan binhluan) {
		this.binhluan = binhluan;
	}

	@Column(name = "phan_hoi", nullable = false)
	private String phan_hoi;
	
	@ManyToOne
	@JoinColumn(name = "binhluan_id", insertable = true, updatable = true)
	private BinhLuan binhluan;
	
	
	@ManyToOne
	@JoinColumn(name = "id_taikhoan", insertable = true, updatable = true)
	private NguoiDung account;

	
	@Column(name = "thoi_gian", nullable = false)
	private String thoi_gian;
	
	public String getThoi_gian() {
		return thoi_gian;
	}

	public void setThoi_gian(String thoi_gian) {
		this.thoi_gian = thoi_gian;
	}
	
	public NguoiDung getAccount() {
		return account;
	}

	public void setAccount(NguoiDung account) {
		this.account = account;
	}

	public PhanHoi(int phanhoi_id, String phan_hoi, BinhLuan binhluan, NguoiDung account, String thoi_gian) {
		super();
		this.phanhoi_id = phanhoi_id;
		this.phan_hoi = phan_hoi;
		this.binhluan = binhluan;
		this.account = account;
		this.thoi_gian = thoi_gian;
	}

	
	
	
}
