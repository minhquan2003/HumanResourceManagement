package Model;
import java.util.Date;

public class PHONGBAN {
    private String maPhong;
    private String tenPhong;
    private Date ngayThanhLap;

    // Constructors
    public PHONGBAN() {
    }

    public PHONGBAN(String maPhong, String tenPhong, Date ngayThanhLap) {
        this.maPhong = maPhong;
        this.tenPhong = tenPhong;
        this.ngayThanhLap = ngayThanhLap;
    }

    // Getters and Setters
    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public Date getNgayThanhLap() {
        return ngayThanhLap;
    }

    public void setNgayThanhLap(Date ngayThanhLap) {
        this.ngayThanhLap = ngayThanhLap;
    }

    @Override
    public String toString() {
        return "PHONGBAN{" +
                "maPhong='" + maPhong + '\'' +
                ", tenPhong='" + tenPhong + '\'' +
                ", ngayThanhLap=" + ngayThanhLap +
                '}';
    }
}
