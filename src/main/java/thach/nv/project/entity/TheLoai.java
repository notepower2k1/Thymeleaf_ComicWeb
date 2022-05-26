package thach.nv.project.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "theloai")
public class TheLoai {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theloai_id", nullable = false)
    private int theloai_id;
	@Column(name = "theloai_ten", nullable = false)
    private String theloai_ten;
	
	
	


	public int getTheloai_id() {
		return theloai_id;
	}


	public void setTheloai_id(int theloai_id) {
		this.theloai_id = theloai_id;
	}


	public String getTheloai_ten() {
		return theloai_ten;
	}


	public void setTheloai_ten(String theloai_ten) {
		this.theloai_ten = theloai_ten;
	}


	

	public TheLoai(int theloai_id, String theloai_ten) {
		super();
		this.theloai_id = theloai_id;
		this.theloai_ten = theloai_ten;
	}


	public TheLoai() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
