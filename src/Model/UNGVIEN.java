package Model;
import java.util.Date;

public class UNGVIEN {
    private String maTuyenDung;
    private String maUngVien;
    private String CCCD;
    private String hoTen;
    private String gioiTinh;
    private Date ngaySinh;
    private String diaChi;
    private String SDT;
    private String danToc;
    private String tonGiao;
    private String email;
    private double mucLuongDeal;
    private String maTrinhDo;
    private String tenChucVu;
    private String trangThai;

    // Constructors
    public UNGVIEN() {
    }

    public UNGVIEN(String maTuyenDung, String maUngVien, String CCCD, String hoTen, String gioiTinh, Date ngaySinh, String diaChi, String SDT, String danToc, String tonGiao, String email, double mucLuongDeal, String maTrinhDo, String tenChucVu, String trangThai) {
        this.maTuyenDung = maTuyenDung;
        this.maUngVien = maUngVien;
        this.CCCD = CCCD;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.SDT = SDT;
        this.danToc = danToc;
        this.tonGiao = tonGiao;
        this.email = email;
        this.mucLuongDeal = mucLuongDeal;
        this.maTrinhDo = maTrinhDo;
        this.tenChucVu = tenChucVu;
        this.trangThai = trangThai;
    }

    // Getters and Setters
    public String getMaTuyenDung() {
        return maTuyenDung;
    }

    public void setMaTuyenDung(String maTuyenDung) {
        this.maTuyenDung = maTuyenDung;
    }

    public String getMaUngVien() {
        return maUngVien;
    }

    public void setMaUngVien(String maUngVien) {
        this.maUngVien = maUngVien;
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

    public String getDanToc() {
        return danToc;
    }

    public void setDanToc(String danToc) {
        this.danToc = danToc;
    }

    public String getTonGiao() {
        return tonGiao;
    }

    public void setTonGiao(String tonGiao) {
        this.tonGiao = tonGiao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getMucLuongDeal() {
        return mucLuongDeal;
    }

    public void setMucLuongDeal(double mucLuongDeal) {
        this.mucLuongDeal = mucLuongDeal;
    }

    public String getMaTrinhDo() {
        return maTrinhDo;
    }

    public void setMaTrinhDo(String maTrinhDo) {
        this.maTrinhDo = maTrinhDo;
    }

    public String getTenChucVu() {
        return tenChucVu;
    }

    public void setTenChucVu(String tenChucVu) {
        this.tenChucVu = tenChucVu;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "UNGVIEN{" +
                "maTuyenDung='" + maTuyenDung + '\'' +
                ", maUngVien='" + maUngVien + '\'' +
                ", CCCD='" + CCCD + '\'' +
                ", hoTen='" + hoTen + '\'' +
                ", gioiTinh='" + gioiTinh + '\'' +
                ", ngaySinh=" + ngaySinh +
                ", diaChi='" + diaChi + '\'' +
                ", SDT='" + SDT + '\'' +
                ", danToc='" + danToc + '\'' +
                ", tonGiao='" + tonGiao + '\'' +
                ", email='" + email + '\'' +
                ", mucLuongDeal=" + mucLuongDeal +
                ", maTrinhDo='" + maTrinhDo + '\'' +
                ", tenChucVu='" + tenChucVu + '\'' +
                ", trangThai='" + trangThai + '\'' +
                '}';
    }
}
