package thach.nv.project.entity;

import java.sql.Date;
import java.sql.Timestamp;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;




@Entity
@Table(name = "truyen")
public class Truyen {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "truyen_id", nullable = false)
	private int truyen_id;
	@Column(name = "truyen_ma", nullable = false)
	private String truyen_ma;
	@Column(name = "truyen_ten", nullable = false)
	private String truyen_ten;
	@Column(name = "truyen_tacgia", nullable = true)
	private String truyen_tacgia;
	@Column(name = "truyen_mota", nullable = false)
	private String truyen_mota;
	@Column(name = "truyen_tinhtrang", nullable = false)
	private int truyen_tinhtrang;
	
	
	public int getTruyen_tinhtrang() {
		return truyen_tinhtrang;
	}

	public void setTruyen_tinhtrang(int truyen_tinhtrang) {
		this.truyen_tinhtrang = truyen_tinhtrang;
	}

	@Column(name = "truyen_hinhdaidien", nullable = false)
	private String truyen_anh;
	
	@ManyToOne
	@JoinColumn(name = "userID", insertable = true, updatable = true)
	private NguoiDung userID;
	
	@OneToMany(mappedBy = "truyen")
    private List<Chuong> dsChuong = new ArrayList<>();

	@OneToOne(mappedBy = "truyen")
    private BaiDang baidang;
	
	@ManyToOne
	@JoinColumn(name = "theloai_id", insertable = true, updatable = true)
    private TheLoai loai;
	
	
	public TheLoai getLoai() {
		return loai;
	}

	public BaiDang getBaidang() {
		return baidang;
	}

	public void setBaidang(BaiDang baidang) {
		this.baidang = baidang;
	}

	public void setLoai(TheLoai loai) {
		this.loai = loai;
	}

	public Truyen() {
		super();
		// TODO Auto-generated constructor stub
	}

	



	
	public Truyen(int truyen_id, String truyen_ma, String truyen_ten, String truyen_tacgia, String truyen_mota,
			int truyen_tinhtrang, String truyen_anh, NguoiDung userID, TheLoai loai) {
		super();
		this.truyen_id = truyen_id;
		this.truyen_ma = truyen_ma;
		this.truyen_ten = truyen_ten;
		this.truyen_tacgia = truyen_tacgia;
		this.truyen_mota = truyen_mota;
		this.truyen_tinhtrang = truyen_tinhtrang;
		this.truyen_anh = truyen_anh;
		this.userID = userID;
		this.loai = loai;
	}

	public int getTruyen_id() {
		return truyen_id;
	}

	public void setTruyen_id(int truyen_id) {
		this.truyen_id = truyen_id;
	}

	public String getTruyen_ma() {
		return truyen_ma;
	}

	public void setTruyen_ma(String truyen_ma) {
		this.truyen_ma = truyen_ma;
	}

	public String getTruyen_ten() {
		return truyen_ten;
	}

	public void setTruyen_ten(String truyen_ten) {
		this.truyen_ten = truyen_ten;
	}



	public String getTruyen_tacgia() {
		return truyen_tacgia;
	}

	public void setTruyen_tacgia(String truyen_tacgia) {
		this.truyen_tacgia = truyen_tacgia;
	}

	public String getTruyen_mota() {
		return truyen_mota;
	}

	public void setTruyen_mota(String truyen_mota) {
		this.truyen_mota = truyen_mota;
	}

	

	public String getTruyen_anh() {
		return truyen_anh;
	}

	public void setTruyen_anh(String truyen_anh) {
		this.truyen_anh = truyen_anh;
	}

	public NguoiDung getUserID() {
		return userID;
	}

	public void setUserID(NguoiDung userID) {
		this.userID = userID;
	}

	public List<Chuong> getDsChuong() {
		return dsChuong;
	}

	public void setDsChuong(List<Chuong> dsChuong) {
		this.dsChuong = dsChuong;
	}
	
	
}
