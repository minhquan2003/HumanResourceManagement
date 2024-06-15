package Model;

public class BANGCHAMCONG {
    private String maBangChamCong;
    private String maNhanVien;
    private int thangChamCong;
    private int namChamCong;
    private int soNgayLamViec;
    private int soNgayNghi;
    private int soNgayTre;
    private int soGioLamThem;
    private String chiTiet;
    
    public BANGCHAMCONG() {
    }

    public BANGCHAMCONG(String maBangChamCong, String maNhanVien, int thangChamCong, int namChamCong, int soNgayLamViec, int soNgayNghi, int soNgayTre, int soGioLamThem, String chiTiet) {
        this.maBangChamCong = maBangChamCong;
        this.maNhanVien = maNhanVien;
        this.thangChamCong = thangChamCong;
        this.namChamCong = namChamCong;
        this.soNgayLamViec = soNgayLamViec;
        this.soNgayNghi = soNgayNghi;
        this.soNgayTre = soNgayTre;
        this.soGioLamThem = soGioLamThem;
        this.chiTiet = chiTiet;
    }

    public String getMaBangChamCong() {
        return maBangChamCong;
    }

    public void setMaBangChamCong(String maBangChamCong) {
        this.maBangChamCong = maBangChamCong;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public int getThangChamCong() {
        return thangChamCong;
    }

    public void setThangChamCong(int thangChamCong) {
        this.thangChamCong = thangChamCong;
    }

    public int getNamChamCong() {
        return namChamCong;
    }

    public void setNamChamCong(int namChamCong) {
        this.namChamCong = namChamCong;
    }

    public int getSoNgayLamViec() {
        return soNgayLamViec;
    }

    public void setSoNgayLamViec(int soNgayLamViec) {
        this.soNgayLamViec = soNgayLamViec;
    }

    public int getSoNgayNghi() {
        return soNgayNghi;
    }

    public void setSoNgayNghi(int soNgayNghi) {
        this.soNgayNghi = soNgayNghi;
    }

    public int getSoNgayTre() {
        return soNgayTre;
    }

    public void setSoNgayTre(int soNgayTre) {
        this.soNgayTre = soNgayTre;
    }

    public int getSoGioLamThem() {
        return soGioLamThem;
    }

    public void setSoGioLamThem(int soGioLamThem) {
        this.soGioLamThem = soGioLamThem;
    }

    public String getChiTiet() {
        return chiTiet;
    }

    public void setChiTiet(String chiTiet) {
        this.chiTiet = chiTiet;
    }

    @Override
    public String toString() {
        return "BANGCHAMCONG{" +
                "maBangChamCong='" + maBangChamCong + '\'' +
                ", maNhanVien='" + maNhanVien + '\'' +
                ", thangChamCong=" + thangChamCong +
                ", namChamCong=" + namChamCong +
                ", soNgayLamViec=" + soNgayLamViec +
                ", soNgayNghi=" + soNgayNghi +
                ", soNgayTre=" + soNgayTre +
                ", soGioLamThem=" + soGioLamThem +
                ", chiTiet='" + chiTiet + '\'' +
                '}';
    }
}
