package Model;

public class LUONG {
    private String maLuong;
    private String maBangChamCong;
    private double luongCoBan;
    private double phuCapChucVu;
    private double phuCapKhac;
    private double khoanThuong;
    private double khoanTruBaoHiem;
    private double khoanTruKhac;
    private double thue;
    private double thucLanh;

    // Constructors
    public LUONG(String maLuong, String maBangChamCong, double luongCoBan, double phuCapChucVu, double phuCapKhac, double khoanThuong, double khoanTruBaoHiem, double khoanTruKhac, double thue, double thucLanh) {
        this.maLuong = maLuong;
        this.maBangChamCong = maBangChamCong;
        this.phuCapChucVu = phuCapChucVu;
        this.phuCapKhac = phuCapKhac;
        this.luongCoBan = luongCoBan;
        this.khoanThuong = khoanThuong;
        this.khoanTruBaoHiem = khoanTruBaoHiem;
        this.khoanTruKhac = khoanTruKhac;
        this.thue = thue;
        this.thucLanh = thucLanh;
    }

    // Getters and Setters
    public String getMaLuong() {
        return maLuong;
    }

    public void setMaLuong(String maLuong) {
        this.maLuong = maLuong;
    }

    public String getMaBangChamCong() {
        return maBangChamCong;
    }

    public void setMaBangChamCong(String maBangChamCong) {
        this.maBangChamCong = maBangChamCong;
    }

    public double getPhuCapChucVu() {
        return phuCapChucVu;
    }

    public void setPhuCapChucVu(double phuCapChucVu) {
        this.phuCapChucVu = phuCapChucVu;
    }

    public double getPhuCapKhac() {
        return phuCapKhac;
    }

    public void setPhuCapKhac(double phuCapKhac) {
        this.phuCapKhac = phuCapKhac;
    }

    public double getLuongCoBan() {
        return luongCoBan;
    }

    public void setLuongCoBan(double luongCoBan) {
        this.luongCoBan = luongCoBan;
    }

    public double getKhoanThuong() {
        return khoanThuong;
    }

    public void setKhoanThuong(double khoanThuong) {
        this.khoanThuong = khoanThuong;
    }

    public double getKhoanTruBaoHiem() {
        return khoanTruBaoHiem;
    }

    public void setKhoanTruBaoHiem(double khoanTruBaoHiem) {
        this.khoanTruBaoHiem = khoanTruBaoHiem;
    }

    public double getKhoanTruKhac() {
        return khoanTruKhac;
    }

    public void setKhoanTruKhac(double khoanTruKhac) {
        this.khoanTruKhac = khoanTruKhac;
    }

    public double getThue() {
        return thue;
    }

    public void setThue(double thue) {
        this.thue = thue;
    }

    public double getThucLanh() {
        return thucLanh;
    }

    public void setThucLanh(double thucLanh) {
        this.thucLanh = thucLanh;
    }

    @Override
    public String toString() {
        return "LUONG{" +
                "maLuong='" + maLuong + '\'' +
                ", maBangChamCong='" + maBangChamCong + '\'' +
                ", phuCapChucVu=" + phuCapChucVu +
                ", phuCapKhac=" + phuCapKhac +
                ", luongCoBan=" + luongCoBan +
                ", khoanThuong=" + khoanThuong +
                ", khoanTruBaoHiem=" + khoanTruBaoHiem +
                ", khoanTruKhac=" + khoanTruKhac +
                ", thue=" + thue +
                ", thucLanh=" + thucLanh +
                '}';
    }
}
