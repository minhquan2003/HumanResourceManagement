package Model;

import java.util.Date;

public class NHANVIEN {
    private String maNhanVien;
    private String CCCD;
    private String hoTen;
    private String gioiTinh;
    private Date ngaySinh;
	private String diaChi;
    private String SDT;
    private String maPhong;
    private String maTrinhDo;
    private String maChucVu;
    private String maHopDong;
    private String maTruongPhong;
    private Date ngayBatDauThuViec;
    private Date ngayKetThucThuViec;
    private double luongThuViec;
    private String trangThai;

    // Constructors
    public NHANVIEN() {
    }
    
    public NHANVIEN(String maNhanVien, String CCCD, String diaChi, String SDT, String maPhong, String maTrinhDo, String maChucVu, String maHopDong, String maTruongPhong, String trangThai) {
        this.maNhanVien = maNhanVien;
        this.CCCD = CCCD;
        this.diaChi = diaChi;
        this.SDT = SDT;
        this.maPhong = maPhong;
        this.maTrinhDo = maTrinhDo;
        this.maChucVu = maChucVu;
        this.maHopDong = maHopDong;
        this.maTruongPhong = maTruongPhong;
        this.trangThai = trangThai;
    }
    
    public NHANVIEN(String maNhanVien, String CCCD, String hoTen, String gioiTinh, String diaChi, String SDT, String maPhong, String maTrinhDo, String maChucVu, String maHopDong, String maTruongPhong, String trangThai) {
        this.maNhanVien = maNhanVien;
        this.CCCD = CCCD;
        this.hoTen = hoTen;
		this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.SDT = SDT;
        this.maPhong = maPhong;
        this.maTrinhDo = maTrinhDo;
        this.maChucVu = maChucVu;
        this.maHopDong = maHopDong;
        this.maTruongPhong = maTruongPhong;
        this.trangThai = trangThai;
    }

    public NHANVIEN(String maNhanVien, String CCCD, String diaChi, String SDT, String maPhong, String maTrinhDo, String maChucVu, String maHopDong, String maTruongPhong, Date ngayBatDauThuViec, Date ngayKetThucThuViec, double luongThuViec, String trangThai) {
        this.maNhanVien = maNhanVien;
        this.CCCD = CCCD;
        this.diaChi = diaChi;
        this.SDT = SDT;
        this.maPhong = maPhong;
        this.maTrinhDo = maTrinhDo;
        this.maChucVu = maChucVu;
        this.maHopDong = maHopDong;
        this.maTruongPhong = maTruongPhong;
        this.ngayBatDauThuViec = ngayBatDauThuViec;
        this.ngayKetThucThuViec = ngayKetThucThuViec;
        this.luongThuViec = luongThuViec;
        this.trangThai = trangThai;
    }

    // Getters and Setters
    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }
    
    public String getHoTen() {
		return hoTen;
	}
    
    public NHANVIEN(String maNhanVien, String CCCD, String hoTen, String gioiTinh, Date ngaySinh, String diaChi,
			String SDT, String maPhong, String maTrinhDo, String maChucVu, String maHopDong, String maTruongPhong,
			Date ngayBatDauThuViec, Date ngayKetThucThuViec, double luongThuViec, String trangThai) {
		super();
		this.maNhanVien = maNhanVien;
		this.CCCD = CCCD;
		this.hoTen = hoTen;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.diaChi = diaChi;
		this.SDT = SDT;
		this.maPhong = maPhong;
		this.maTrinhDo = maTrinhDo;
		this.maChucVu = maChucVu;
		this.maHopDong = maHopDong;
		this.maTruongPhong = maTruongPhong;
		this.ngayBatDauThuViec = ngayBatDauThuViec;
		this.ngayKetThucThuViec = ngayKetThucThuViec;
		this.luongThuViec = luongThuViec;
		this.trangThai = trangThai;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getMaTrinhDo() {
        return maTrinhDo;
    }

    public void setMaTrinhDo(String maTrinhDo) {
        this.maTrinhDo = maTrinhDo;
    }

    public String getMaChucVu() {
        return maChucVu;
    }

    public void setMaChucVu(String maChucVu) {
        this.maChucVu = maChucVu;
    }

    public String getMaHopDong() {
        return maHopDong;
    }

    public void setMaHopDong(String maHopDong) {
        this.maHopDong = maHopDong;
    }

    public Date getNgayBatDauThuViec() {
        return ngayBatDauThuViec;
    }

    public void setNgayBatDauThuViec(Date ngayBatDauThuViec) {
        this.ngayBatDauThuViec = ngayBatDauThuViec;
    }

    public Date getNgayKetThucThuViec() {
        return ngayKetThucThuViec;
    }

    public void setNgayKetThucThuViec(Date ngayKetThucThuViec) {
        this.ngayKetThucThuViec = ngayKetThucThuViec;
    }

    public double getLuongThuViec() {
        return luongThuViec;
    }

    public void setLuongThuViec(double luongThuViec) {
        this.luongThuViec = luongThuViec;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "NHANVIEN{" +
                "maNhanVien='" + maNhanVien + '\'' +
                ", CCCD='" + CCCD + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", SDT='" + SDT + '\'' +
                ", maPhong='" + maPhong + '\'' +
                ", maTrinhDo='" + maTrinhDo + '\'' +
                ", maChucVu='" + maChucVu + '\'' +
                ", maHopDong='" + maHopDong + '\'' +
                ", ngayBatDauThuViec=" + ngayBatDauThuViec +
                ", ngayKetThucThuViec=" + ngayKetThucThuViec +
                ", luongThuViec=" + luongThuViec +
                ", trangThai='" + trangThai + '\'' +
                '}';
    }

	public String getMaTruongPhong() {
		return maTruongPhong;
	}

	public void setMaTruongPhong(String maTruongPhong) {
		this.maTruongPhong = maTruongPhong;
	}
}