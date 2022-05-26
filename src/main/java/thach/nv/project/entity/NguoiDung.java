package thach.nv.project.entity;


import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;





@Entity
@Table(name = "nguoidung")
public class NguoiDung {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_taikhoan", nullable = false)
	private int id_taikhoan;
	@Column(name = "taikhoan", nullable = false)
	private String taikhoan;
	@Column(name = "matkhau", nullable = false)
	private String matKhau;
	@Column(name = "email", nullable = false)
	private String email;
	@Column(name = "dien_thoai", nullable = false)
	private String dien_thoai;
	@Column(name = "dia_chi", nullable = false)
	private String dia_chi;
	
	@Column(name = "hinhanh", nullable = false)
	private String hinhanh;
	
	@Column(name = "hoten", nullable = false)
	private String hoten;
	
	
	public NguoiDung(int id_taikhoan, String taikhoan, String matKhau, String email, String dien_thoai, String dia_chi,
			String hinhanh, String hoten, Role role) {
		super();
		this.id_taikhoan = id_taikhoan;
		this.taikhoan = taikhoan;
		this.matKhau = matKhau;
		this.email = email;
		this.dien_thoai = dien_thoai;
		this.dia_chi = dia_chi;
		this.hinhanh = hinhanh;
		this.hoten = hoten;
		this.role = role;
	}


	public String getHinhanh() {
		return hinhanh;
	}


	public void setHinhanh(String hinhanh) {
		this.hinhanh = hinhanh;
	}


	public String getHoten() {
		return hoten;
	}


	public void setHoten(String hoten) {
		this.hoten = hoten;
	}





	@ManyToOne
	@JoinColumn(name = "idVaiTro", insertable = true, updatable = true)
    private Role role;


	public int getId_taikhoan() {
		return id_taikhoan;
	}


	public void setId_taikhoan(int id_taikhoan) {
		this.id_taikhoan = id_taikhoan;
	}


	


	public String getTaikhoan() {
		return taikhoan;
	}


	public void setTaikhoan(String taikhoan) {
		this.taikhoan = taikhoan;
	}


	public String getMatKhau() {
		return matKhau;
	}


	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getDien_thoai() {
		return dien_thoai;
	}


	public void setDien_thoai(String dien_thoai) {
		this.dien_thoai = dien_thoai;
	}


	public String getDia_chi() {
		return dia_chi;
	}


	public void setDia_chi(String dia_chi) {
		this.dia_chi = dia_chi;
	}


	


	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}


	


	public NguoiDung() {
		super();
		// TODO Auto-generated constructor stub
	}





	
	
}
