package Model;

import java.util.Date;

public class THONGBAO {
	private String maThongBao;
	private String tieuDe;
	private String noiDungThongBao;
	private String nguoiGui;
	private Date ngayGui;
	
	public THONGBAO(String maTB, String tieuDe, String noiDungTB, String nguoiGui, Date ngayGui) {
		this.maThongBao=maTB;
		this.tieuDe=tieuDe;
		this.noiDungThongBao=noiDungTB;
		this.nguoiGui=nguoiGui;
		this.ngayGui=ngayGui;
	}
	
	public String getMaThongBao() {
		return this.maThongBao;
	}
	
	public void setMaThongBao(String maThongBao) {
		this.maThongBao=maThongBao;
	}
	
	public String getTieuDe() {
		return this.tieuDe;
	}
	
	public void setTieuDe(String tieuDe) {
		this.tieuDe=tieuDe;
	}
	
	public String getNoiDungThongBao() {
		return this.noiDungThongBao;
	}
	
	public void setNoiDungThongBao(String noiDungTB) {
		this.noiDungThongBao=noiDungTB;
	}
	
	public Date getNgayGui() {
		return this.ngayGui;
	}
	
	public void setNgayGui(Date ngayGui) {
		this.ngayGui=ngayGui;
	}
}
