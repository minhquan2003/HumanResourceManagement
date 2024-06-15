package Model;

import java.util.Date;

public class HOPDONGLAODONG {
    private String maHopDong;
    private Date tuNgay;
    private Date denNgay;
    private String loaiHopDong;
    private double luongCoBan;
    private String moTa;

    // Constructors
    public HOPDONGLAODONG() {
    }

    public HOPDONGLAODONG(String maHopDong, Date tuNgay, Date denNgay, String loaiHopDong, double luongCoBan, String moTa) {
        this.maHopDong = maHopDong;
        this.tuNgay = tuNgay;
        this.denNgay = denNgay;
        this.loaiHopDong = loaiHopDong;
        this.luongCoBan = luongCoBan;
        this.moTa = moTa;
    }

    // Getters and Setters
    public String getMaHopDong() {
        return maHopDong;
    }

    public void setMaHopDong(String maHopDong) {
        this.maHopDong = maHopDong;
    }

    public Date getTuNgay() {
        return tuNgay;
    }

    public void setTuNgay(Date tuNgay) {
        this.tuNgay = tuNgay;
    }

    public Date getDenNgay() {
        return denNgay;
    }

    public void setDenNgay(Date denNgay) {
        this.denNgay = denNgay;
    }

    public String getLoaiHopDong() {
        return loaiHopDong;
    }

    public void setLoaiHopDong(String loaiHopDong) {
        this.loaiHopDong = loaiHopDong;
    }

    public double getLuongCoBan() {
        return luongCoBan;
    }

    public void setLuongCoBan(double luongCoBan) {
        this.luongCoBan = luongCoBan;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    @Override
    public String toString() {
        return "HOPDONGLAODONG{" +
                "maHopDong='" + maHopDong + '\'' +
                ", tuNgay=" + tuNgay +
                ", denNgay=" + denNgay +
                ", loaiHopDong='" + loaiHopDong + '\'' +
                ", luongCoBan=" + luongCoBan +
                ", moTa='" + moTa + '\'' +
                '}';
    }
}