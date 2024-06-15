package Model;

import java.util.Date;

public class BAOCAOTUYENDUNG {
    private String maTuyenDung;
    private String chucVu;
    private String hocVan;
    private String yeuCauGioiTinh;
    private String yeuCauDoTuoi;
    private int soLuongCanTuyen;
    private Date hanNopHoSo;
    private double mucLuongToiThieu;
    private double mucLuongToiDa;

    // Constructors
    public BAOCAOTUYENDUNG() {
    }

    public BAOCAOTUYENDUNG(String maTuyenDung, String chucVu, String hocVan, String yeuCauGioiTinh, String yeuCauDoTuoi, int soLuongCanTuyen, Date hanNopHoSo, double mucLuongToiThieu, double mucLuongToiDa) {
        this.maTuyenDung = maTuyenDung;
        this.chucVu = chucVu;
        this.hocVan = hocVan;
        this.yeuCauGioiTinh = yeuCauGioiTinh;
        this.yeuCauDoTuoi = yeuCauDoTuoi;
        this.soLuongCanTuyen = soLuongCanTuyen;
        this.hanNopHoSo = hanNopHoSo;
        this.mucLuongToiThieu = mucLuongToiThieu;
        this.mucLuongToiDa = mucLuongToiDa;
    }

    // Getters and Setters
    public String getMaTuyenDung() {
        return maTuyenDung;
    }

    public void setMaTuyenDung(String maTuyenDung) {
        this.maTuyenDung = maTuyenDung;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public String getHocVan() {
        return hocVan;
    }

    public void setHocVan(String hocVan) {
        this.hocVan = hocVan;
    }

    public String getYeuCauGioiTinh() {
        return yeuCauGioiTinh;
    }

    public void setYeuCauGioiTinh(String yeuCauGioiTinh) {
        this.yeuCauGioiTinh = yeuCauGioiTinh;
    }

    public String getYeuCauDoTuoi() {
        return yeuCauDoTuoi;
    }

    public void setYeuCauDoTuoi(String yeuCauDoTuoi) {
        this.yeuCauDoTuoi = yeuCauDoTuoi;
    }

    public int getSoLuongCanTuyen() {
        return soLuongCanTuyen;
    }

    public void setSoLuongCanTuyen(int soLuongCanTuyen) {
        this.soLuongCanTuyen = soLuongCanTuyen;
    }

    public Date getHanNopHoSo() {
        return hanNopHoSo;
    }

    public void setHanNopHoSo(Date hanNopHoSo) {
        this.hanNopHoSo = hanNopHoSo;
    }

    public double getMucLuongToiThieu() {
        return mucLuongToiThieu;
    }

    public void setMucLuongToiThieu(double mucLuongToiThieu) {
        this.mucLuongToiThieu = mucLuongToiThieu;
    }

    public double getMucLuongToiDa() {
        return mucLuongToiDa;
    }

    public void setMucLuongToiDa(double mucLuongToiDa) {
        this.mucLuongToiDa = mucLuongToiDa;
    }

    @Override
    public String toString() {
        return "BAOCAOTUYENDUNG{" +
                "maTuyenDung='" + maTuyenDung + '\'' +
                ", chucVu='" + chucVu + '\'' +
                ", hocVan='" + hocVan + '\'' +
                ", yeuCauGioiTinh='" + yeuCauGioiTinh + '\'' +
                ", yeuCauDoTuoi='" + yeuCauDoTuoi + '\'' +
                ", soLuongCanTuyen=" + soLuongCanTuyen +
                ", hanNopHoSo=" + hanNopHoSo +
                ", mucLuongToiThieu=" + mucLuongToiThieu +
                ", mucLuongToiDa=" + mucLuongToiDa +
                '}';
    }
}