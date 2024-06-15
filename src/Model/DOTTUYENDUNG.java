package Model;

import java.util.Date;

public class DOTTUYENDUNG {
	private String maTuyenDung;
	private String maNhanVien;
	private Date ngayTuyenDung;
	
	public DOTTUYENDUNG(String maTuyenDung, String maNhanVien, Date ngayTuyenDung) {
		this.maTuyenDung=maTuyenDung;
		this.maNhanVien=maNhanVien;
		this.ngayTuyenDung=ngayTuyenDung;
	}
	
	public String getMaTuyenDung() {
		return this.maTuyenDung;
	}
	
	public void setMaTuyenDung(String maTuyenDung) {
		this.maTuyenDung=maTuyenDung;
	}
	
	public String getMaNhanVien() {
		return this.maNhanVien;
	}
	
	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien=maNhanVien;
	}
	
	public Date getNgayTuyenDung() {
		return this.ngayTuyenDung;
	}
	
	public void setNgayTuyenDung(Date ngayTuyenDung) {
		this.ngayTuyenDung=ngayTuyenDung;
	}
}