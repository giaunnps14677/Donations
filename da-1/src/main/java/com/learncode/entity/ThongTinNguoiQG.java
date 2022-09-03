package com.learncode.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import java.sql.Date;
@Entity
@Table(name="INFORMATION_CONTRIBUTORS")
public class ThongTinNguoiQG {
	
	@ManyToOne
    @JoinColumn(name="ID_DONATION", nullable=false, insertable =false, updatable =false)
    private CacDotQuyenGop dotQG;
	
	@Column(name="ID_ACCOUNT")
	private int accountId;
	
	@Column(name="ID_DONATION")
	private int idCacDotQg;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name="ID_INFORMATION_CONTRIBUTORS")
	private int idThongTinNguoiQg;
	
	@Column(name="NAME", length=20)
	private String hoTen;
	
	@Column(name="MONEY_DONATION")
	private float soTien;
	
	@Column(name="DATE_DONATION")
	private Date ngayQuyenGop;

	
	@Column(name="DA_QG",columnDefinition ="BIT")
	private Boolean daQG;
	
	@Column(name="XAC_NHAN_DA_QG", columnDefinition ="BIT")
	private Boolean xacNhanDaQG;
	
	@Column(name="HINH_THUC_QG")
	private String hinhThucQG;
	
	public String getHinhThucQG() {
		return hinhThucQG;
	}
	public void setHinhThucQG(String hinhThucQG) {
		this.hinhThucQG = hinhThucQG;
	}
	
	public Boolean getDaQG() {
		return daQG;
	}

	public void setDaQG(Boolean daQG) {
		this.daQG = daQG;
	}

	public Boolean isXacNhanDaQG() {
		return xacNhanDaQG;
	}

	public void setXacNhanDaQG(Boolean xacNhanDaQG) {
		this.xacNhanDaQG = xacNhanDaQG;
	}

	public ThongTinNguoiQG() {
		super();
	}

	public ThongTinNguoiQG(int accountId, int idCacDotQg, int idThongTinNguoiQg, String hoTen, float soTien,
			Date ngayQuyenGop) {
		super();
		this.accountId = accountId;
		this.idCacDotQg = idCacDotQg;
		this.idThongTinNguoiQg = idThongTinNguoiQg;
		this.hoTen = hoTen;
		this.soTien = soTien;
		this.ngayQuyenGop = ngayQuyenGop;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getIdCacDotQg() {
		return idCacDotQg;
	}

	public void setIdCacDotQg(int idCacDotQg) {
		this.idCacDotQg = idCacDotQg;
	}

	public int getIdThongTinNguoiQg() {
		return idThongTinNguoiQg;
	}

	public void setIdThongTinNguoiQg(int idThongTinNguoiQg) {
		this.idThongTinNguoiQg = idThongTinNguoiQg;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public float getSoTien() {
		return soTien;
	}

	public void setSoTien(float soTien) {
		this.soTien = soTien;
	}

	public Date getNgayQuyenGop() {
		return ngayQuyenGop;
	}

	public void setNgayQuyenGop(Date dateQg) {
		this.ngayQuyenGop = dateQg;
	}

}
