package thach.nv.project.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDVaiTro", nullable = false)
    private int idVaiTro;
	@Column(name = "TenVT", nullable = false)
    private String tenVT;
	
    @OneToMany(mappedBy = "role")
    private List<NguoiDung> dsTK;
    
	public Role() {
		super();
	}
	
	public Role(int idVaiTro, String tenVT) {
		super();
		this.idVaiTro = idVaiTro;
		this.tenVT = tenVT;
	}
	public int getIdVaiTro() {
		return idVaiTro;
	}
	public void setIdVaiTro(int idVaiTro) {
		this.idVaiTro = idVaiTro;
	}
	public String getTenVT() {
		return tenVT;
	}
	public void setTenVT(String tenVT) {
		this.tenVT = tenVT;
	}

	public List<NguoiDung> getDsTK() {
		return dsTK;
	}

	public void setDsTK(List<NguoiDung> dsTK) {
		this.dsTK = dsTK;
	}

	public Role(int idVaiTro, String tenVT, List<NguoiDung> dsTK) {
		super();
		this.idVaiTro = idVaiTro;
		this.tenVT = tenVT;
		this.dsTK = dsTK;
	}

	
    
}
