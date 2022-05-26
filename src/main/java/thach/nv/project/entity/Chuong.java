package thach.nv.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "chuong")
public class Chuong {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "chuong_id", nullable = false)
	private int chuong_id;
	@Column(name = "chuong_so", nullable = false)
	private String chuong_so;
	@Column(name = "chuong_ten", nullable = false)
	private String chuong_ten;
	@Column(name = "chuong_noidung", nullable = false)
	private String chuong_noidung;
	
	@ManyToOne
	@JoinColumn(name = "truyen_id", insertable = true, updatable = true)
	private Truyen truyen;

	
	public Chuong() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Chuong(int chuong_id, String chuong_so, String chuong_ten, String chuong_noidung, Truyen truyen) {
		super();
		this.chuong_id = chuong_id;
		this.chuong_so = chuong_so;
		this.chuong_ten = chuong_ten;
		this.chuong_noidung = chuong_noidung;
		this.truyen = truyen;
	}


	public int getChuong_id() {
		return chuong_id;
	}


	public void setChuong_id(int chuong_id) {
		this.chuong_id = chuong_id;
	}


	public String getChuong_so() {
		return chuong_so;
	}


	public void setChuong_so(String chuong_so) {
		this.chuong_so = chuong_so;
	}


	public String getChuong_ten() {
		return chuong_ten;
	}


	public void setChuong_ten(String chuong_ten) {
		this.chuong_ten = chuong_ten;
	}


	public String getChuong_noidung() {
		return chuong_noidung;
	}


	public void setChuong_noidung(String chuong_noidung) {
		this.chuong_noidung = chuong_noidung;
	}


	public Truyen getTruyen() {
		return truyen;
	}


	public void setTruyen(Truyen truyen) {
		this.truyen = truyen;
	}
	
	
	
}
