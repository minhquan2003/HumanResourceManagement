package Model;

import java.util.Date;

public class CHUCVU {
    private String maChucVu;
    private String tenChucVu;
    private double phuCapChucVu;
    private Date ngayNhanChuc;
    private String moTa;

    // Constructors
    public CHUCVU() {
    }

    public CHUCVU(String maChucVu, String tenChucVu, double phuCapChucVu, Date ngayNhanChuc, String moTa) {
        this.maChucVu = maChucVu;
        this.tenChucVu = tenChucVu;
        this.phuCapChucVu = phuCapChucVu;
        this.ngayNhanChuc = ngayNhanChuc;
        this.moTa = moTa;
    }

    public CHUCVU(String maChucVu2, String tenChucVu2, double phuCapChucVu2, String moTa2) {
		// TODO Auto-generated constructor stub
    	this.maChucVu=maChucVu2;
    	this.tenChucVu=tenChucVu2;
    	this.phuCapChucVu=phuCapChucVu2;
    	this.moTa=moTa2;
	}

	// Getters and Setters
    public String getMaChucVu() {
        return maChucVu;
    }

    public void setMaChucVu(String maChucVu) {
        this.maChucVu = maChucVu;
    }

    public String getTenChucVu() {
        return tenChucVu;
    }

    public void setTenChucVu(String tenChucVu) {
        this.tenChucVu = tenChucVu;
    }

    public double getPhuCapChucVu() {
        return phuCapChucVu;
    }

    public void setPhuCapChucVu(double phuCapChucVu) {
        this.phuCapChucVu = phuCapChucVu;
    }

    public Date getNgayNhanChuc() {
        return ngayNhanChuc;
    }

    public void setNgayNhanChuc(Date ngayNhanChuc) {
        this.ngayNhanChuc = ngayNhanChuc;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    @Override
    public String toString() {
        return "CHUCVU{" +
                "maChucVu='" + maChucVu + '\'' +
                ", tenChucVu='" + tenChucVu + '\'' +
                ", phuCapChucVu=" + phuCapChucVu +
                ", ngayNhanChuc=" + ngayNhanChuc +
                ", moTa='" + moTa + '\'' +
                '}';
    }
}