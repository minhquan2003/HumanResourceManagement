CREATE SCHEMA IF NOT EXISTS `QuanLyNhanSu`;
USE QuanLyNhanSu;

CREATE TABLE IF NOT EXISTS `QuanLyNhanSu`.`NhomQuyen` (
	`maNhomQuyen` nvarchar(10) NOT NULL,
    `tenNhomQuyen` nvarchar(30) NOT NULL,
    PRIMARY KEY (`maNhomQuyen`)
);

CREATE TABLE IF NOT EXISTS `QuanLyNhanSu`.`TaiKhoan` (
	`maTaiKhoan` nvarchar(10) NOT NULL,
	`username` nvarchar(30) NOT NULL,
    `pass` nvarchar(30) NOT NULL,
    `maNhomQuyen` nvarchar(10) NOT NULL,
    `avatar` TEXT,
    `disabled` boolean NOT NULL DEFAULT FALSE,
    PRIMARY KEY (`maTaiKhoan`),
    CONSTRAINT `FK_TaiKhoan_NhomQuyen` FOREIGN KEY (`maNhomQuyen`) REFERENCES `QuanLyNhanSu`.`NhomQuyen` (`maNhomQuyen`)
);

CREATE TABLE IF NOT EXISTS `QuanLyNhanSu`.`ChucVu` (
	`maChucVu` nvarchar(10) NOT NULL,
	`tenChucVu` nvarchar(30) NOT NULL,
    `phuCapChucVu` decimal(10,2) NOT NULL,
    `ngayNhanChuc` date NULL DEFAULT NULL,
    `moTa` nvarchar(255) NULL DEFAULT NULL,
    PRIMARY KEY (`maChucVu`)
);

CREATE TABLE IF NOT EXISTS `QuanLyNhanSu`.`TrinhDo` (
	`maTrinhDo` nvarchar(10) NOT NULL,
	`trinhDoHocVan` nvarchar(30) NOT NULL,
    `trinhDoChuyenMon` nvarchar(50) NOT NULL,
    `chuyenNganh` nvarchar(50) NULL DEFAULT NULL,
    PRIMARY KEY (`maTrinhDo`)
);

CREATE TABLE IF NOT EXISTS `QuanLyNhanSu`.`PhongBan` (
	`maPhong` nvarchar(10) NOT NULL,
	`tenPhong` nvarchar(30) NOT NULL,
    `ngayThanhLap` date NULL DEFAULT NULL,
    `disable` boolean NOT NULL DEFAULT TRUE,
    PRIMARY KEY (`maPhong`)
);


CREATE TABLE IF NOT EXISTS `QuanLyNhanSu`.`HopDongLaoDong` (
	`maHopDong` nvarchar(10) NOT NULL,
	`tuNgay` date NOT NULL,
    `denNgay` date NOT NULL,
    `loaiHopDong` nvarchar(20) NOT NULL,
    `luongCoBan` decimal(10,2) NOT NULL,
    `moTa` nvarchar(255) NULL DEFAULT NULL,
    PRIMARY KEY (`maHopDong`)
);

CREATE TABLE IF NOT EXISTS `QuanLyNhanSu`.`NhanVien` (
	`maNhanVien` nvarchar(10) NOT NULL,
	`CCCD` nvarchar(30) NOT NULL,
    `hoTen` nvarchar(255) NOT NULL,
    `gioiTinh` nvarchar(10) NOT NULL,
    `ngaySinh` date NOT NULL,
    `diaChi` nvarchar(255) NOT NULL,
    `SDT` nvarchar(12) NOT NULL,
    `maPhong` nvarchar(10) NOT NULL,
    `maTrinhDo` nvarchar(10) NOT NULL,
    `maChucVu` nvarchar(10) NOT NULL,
    `maHopDong` nvarchar(10) NULL DEFAULT NULL,
    `maTruongPhong` nvarchar(10) NOT NULL,
    `ngayBatDauThuViec` date NULL DEFAULT NULL,
    `ngayKetThucThuViec` date NULL DEFAULT NULL,
    `luongThuViec` decimal(10,2) NULL DEFAULT NULL,
    `trangThai` nvarchar(10) NOT NULL,
    PRIMARY KEY (`maNhanVien`),
    CONSTRAINT `FK_NhanVien_PhongBan` FOREIGN KEY (`maPhong`) REFERENCES `QuanLyNhanSu`.`PhongBan` (`maPhong`),
    CONSTRAINT `FK_NhanVien_TrinhDo` FOREIGN KEY (`maTrinhDo`) REFERENCES `QuanLyNhanSu`.`TrinhDo` (`maTrinhDo`),
    CONSTRAINT `FK_NhanVien_ChucVu` FOREIGN KEY (`maChucVu`) REFERENCES `QuanLyNhanSu`.`ChucVu` (`maChucVu`),
    CONSTRAINT `FK_NhanVien_HopDongLaoDong` FOREIGN KEY (`maHopDong`) REFERENCES `QuanLyNhanSu`.`HopDongLaoDong` (`maHopDong`),
    CONSTRAINT `FK_NhanVien_NhanVien` FOREIGN KEY (`maTruongPhong`) REFERENCES `QuanLyNhanSu`.`NhanVien` (`maNhanVien`)
);

CREATE TABLE IF NOT EXISTS `QuanLyNhanSu`.`BangChamCong` (
	`maBangChamCong` nvarchar(15) NOT NULL,
	`maNhanVien` nvarchar(10) NOT NULL,
    `thangChamCong` int NOT NULL,
    `namChamCong` int NOT NULL,
    `soNgayLamViec` int NOT NULL,
    `soNgayNghi` int NULL DEFAULT NULL,
    `soNgayTre` int NULL DEFAULT NULL,
    `soGioLamThem` int NULL DEFAULT NULL,
    `chiTiet` nvarchar(255) NULL DEFAULT NULL,
    PRIMARY KEY (`maBangChamCong`),
    CONSTRAINT `FK_BangChamCong_NhanVien` FOREIGN KEY (`maNhanVien`) REFERENCES `QuanLyNhanSu`.`NhanVien` (`maNhanVien`)
);

CREATE TABLE IF NOT EXISTS `QuanLyNhanSu`.`Luong` (
	`maLuong` nvarchar(10) NOT NULL,
	`maBangChamCong` nvarchar(15) NOT NULL,
    `phuCapChucVu` decimal(10,2),
    `phuCapKhac` decimal(10,2) NULL DEFAULT NULL,
    `luongCoBan` decimal(10,2) NOT NULL,
    `khoanThuong` decimal(10,2) NULL DEFAULT NULL,
    `khoanTruBaoHiem` decimal(10,2) NOT NULL,
    `khoanTruKhac` decimal(10,2) NULL DEFAULT NULL,
    `thue` decimal(10,2) NOT NULL,
    `thucLanh` decimal(10,2) NULL DEFAULT NULL,
    PRIMARY KEY (`maLuong`),
    CONSTRAINT `FK_Luong_BangChamCong` FOREIGN KEY (`maBangChamCong`) REFERENCES `QuanLyNhanSu`.`BangChamCong` (`maBangChamCong`)
);

CREATE TABLE IF NOT EXISTS `QuanLyNhanSu`.`BaoCaoTuyenDung` (
	`maTuyenDung` nvarchar(10) NOT NULL,
	`chucVu` nvarchar(30) NOT NULL,
    `hocVan` nvarchar(30) NOT NULL,
    `yeuCauGioiTinh` nvarchar(5) NULL DEFAULT NULL,
    `yeuCauDoTuoi` nvarchar(30) NOT NULL,
    `soLuongCanTuyen` int NOT NULL,
    `hanNopHoSo` date NULL DEFAULT NULL,
    `mucLuongToiThieu` decimal(10,2) NOT NULL,		
    `mucLuongToiDa` decimal(12,2) NOT NULL,
        `disable` boolean NOT NULL DEFAULT TRUE,

    PRIMARY KEY (`maTuyenDung`)
);

CREATE TABLE IF NOT EXISTS `QuanLyNhanSu`.`DotTuyenDung` (
	`maTuyenDung` nvarchar(10) NOT NULL,
	`maNhanVien` nvarchar(10) NOT NULL,
    `ngayTuyenDung` date NOT NULL,
    PRIMARY KEY (`maTuyenDung`, `maNhanVien`),
    CONSTRAINT `FK_DotTuyenDung_BaoCaoTuyenDung` FOREIGN KEY (`maTuyenDung`) REFERENCES `QuanLyNhanSu`.`BaoCaoTuyenDung` (`maTuyenDung`),
    CONSTRAINT `FK_DotTuyenDung_NhanVien` FOREIGN KEY (`maNhanVien`) REFERENCES `QuanLyNhanSu`.`NhanVien` (`maNhanVien`)
);

CREATE TABLE IF NOT EXISTS `QuanLyNhanSu`.`UngVien` (
	`maTuyenDung` nvarchar(10) NOT NULL,
    `maUngVien` nvarchar(10) NOT NULL,
	`CCCD` nvarchar(15) NOT NULL,
    `hoTen` nvarchar(30) NOT NULL,
    `gioiTinh` nvarchar(5) NOT NULL,
    `ngaySinh` date NOT NULL,
    `diaChi` nvarchar(255) NOT NULL,
    `SDT` nvarchar(15) NOT NULL,
    `danToc` nvarchar(15) NULL DEFAULT NULL,
    `tonGiao` nvarchar(15) NULL DEFAULT NULL,
    `email` nvarchar(50) NOT NULL,
    `mucLuongDeal` decimal(10,2) NOT NULL,
    `maTrinhDo` nvarchar(10) NOT NULL,
    `tenChucVu` nvarchar(20) NOT NULL,
    `trangThai` nvarchar(20) NOT NULL,
    `disable` boolean NOT NULL DEFAULT TRUE,
    PRIMARY KEY (`maUngVien`),
    CONSTRAINT `FK_UngVien_BaoCaoTuyenDung` FOREIGN KEY (`maTuyenDung`) REFERENCES `QuanLyNhanSu`.`BaoCaoTuyenDung` (`maTuyenDung`),
    CONSTRAINT `FK_UngVien_TrinhDo` FOREIGN KEY (`maTrinhDo`) REFERENCES `QuanLyNhanSu`.`TrinhDo` (`maTrinhDo`)
);

CREATE TABLE IF NOT EXISTS `QuanLyNhanSu`.`ThongBao` (
	`maThongBao` nvarchar(10) NOT NULL,
	`tenThongBao` nvarchar(255) NOT NULL,
    `noiDungThongBao` nvarchar(255) NOT NULL,
    `nguoiGui` nvarchar(10) NOT NULL,
    `ngayGui` date NOT NULL,
    PRIMARY KEY (`maThongBao`),
    CONSTRAINT `FK_ThongBao_NhanVien` FOREIGN KEY (`nguoiGui`) REFERENCES `QuanLyNhanSu`.`NhanVien` (`maTruongPhong`)
);