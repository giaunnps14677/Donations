package com.learncode.entity;

import java.beans.Transient;
import java.util.Iterator;
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
@Table(name="DONATIONS")
public class CacDotQuyenGop {
	
	//mot dot quyen gop co mot danh sach nguoi quyen gop
	@OneToMany(mappedBy="dotQG")
    private List<ThongTinNguoiQG> items;
	
	public List<ThongTinNguoiQG> getItems() {
		return items;
	}
	
	public void setItems(List<ThongTinNguoiQG> items) {
		this.items = items;
	}
	
	//tao anotaion nay de su dung
	@Transient
	public double getTotalMoney() {
		double total = 0;
		for(int i=0; i<items.size(); i++) {
			ThongTinNguoiQG thongTinNguoiQG = items.get(i);
			total += thongTinNguoiQG.getSoTien();
		}
		return total;
	}
	
	@Transient
	public int getTotalNumberDonation() {
		
		return items.size();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_DONATION")
	private int idDotQg;
	
	@Column(name="IMAGES", length=200)
	private String images;
	
	@Column(name="TITLE", length=100)
	private String title;
	
	@Column(name="MONEY_DONATIONS")
	private Float soTienQg;
	
	@Column(name="NUMBER_DONATIONS")
	private Integer soLuotQg;

	public CacDotQuyenGop() {
		super();
	}

	public CacDotQuyenGop(int idDotQg, String images, String title, Float soTienQg, Integer soLuotQg) {
		super();
		this.idDotQg = idDotQg;
		this.images = images;
		this.title = title;
		this.soTienQg = soTienQg;
		this.soLuotQg = soLuotQg;
	}

	@Transient
    public String getPhotosImagePath() {
        if (images == null || idDotQg == 0) return null;
         
        return "/images/" + images;
    }
	
	public int getIdDotQg() {
		return idDotQg;
	}

	public void setIdDotQg(int idDotQg) {
		this.idDotQg = idDotQg;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Float getSoTienQg() {
		return soTienQg;
	}

	public void setSoTienQg(Float soTienQg) {
		this.soTienQg = soTienQg;
	}

	public Integer getSoLuotQg() {
		return soLuotQg;
	}

	public void setSoLuotQg(Integer soLuotQg) {
		this.soLuotQg = soLuotQg;
	}

}
