package application;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import Connection.ConnectionDB;
import Model.NHANVIEN;
import Model.UNGVIEN;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
public class TuyenNhanVienController {
	     @FXML
	    private Button btnTuyenDung;

	    @FXML
	    private ComboBox<String> cmbPhongBan;

	    @FXML
	    private ComboBox<String> cmbTrinhDo;

	    @FXML
	    private DatePicker dtpNgaySinh;

	    @FXML
	    private TextField txtCCCD;

	    @FXML
	    private TextArea txtDiaChi;

	    @FXML
	    private TextField txtGioiTinh;

	    @FXML
	    private TextField txtHoTen;

	    @FXML
	    private TextField txtMaNhanVien;

	    @FXML
	    private TextField txtSDT;
	    private String MaUngVien;
	    private String MaTuyenDung;

	    public void setUngVien(UNGVIEN ungvien) {
	    	   MaUngVien = ungvien.getMaUngVien();
	    	   MaTuyenDung = ungvien.getMaTuyenDung();
	           txtHoTen.setText(ungvien.getHoTen());
	           txtCCCD.setText(ungvien.getCCCD());
	           txtDiaChi.setText(ungvien.getDiaChi());
	           txtSDT.setText(ungvien.getSDT());
	           txtGioiTinh.setText(ungvien.getGioiTinh());
	           cmbTrinhDo.setValue(ungvien.getMaTrinhDo());
	           if (ungvien.getNgaySinh() != null) {
	        	    java.util.Date utilDate = new java.util.Date(ungvien.getNgaySinh().getTime());
	        	    Instant instant = utilDate.toInstant();
	        	    LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
	        	    dtpNgaySinh.setValue(localDate);
	           }

	    }
	    private void loadMaPhongBan() {
	        try {
	            Connection conn = ConnectionDB.getConnection();
	            if (conn != null) {
	                String query = "SELECT maPhong FROM PhongBan";
	                PreparedStatement statement = conn.prepareStatement(query);
	                ResultSet resultSet = statement.executeQuery();
	                List<String> maPhongBanList = new ArrayList<>();
	                while (resultSet.next()) {
	                    maPhongBanList.add(resultSet.getString("maPhong"));
	                }
	                cmbPhongBan.getItems().addAll(maPhongBanList);
	                if (!maPhongBanList.isEmpty()) {
	                    cmbPhongBan.setValue(maPhongBanList.get(0));
	                }
	                conn.close();
	            } else {
	                hienThongBao("Lỗi", "Không thể kết nối đến CSDL!");
	            }
	        } catch (SQLException e) {
	            hienThongBao("Lỗi", "Lỗi khi tải danh sách mã nhân viên: " + e.getMessage());
	        }
	    }
	    private void thayDoiTrangThai(String maUngVien) {
	        try {
	            Connection conn = ConnectionDB.getConnection();
	            if (conn != null) {
	                // Thay đổi trạng thái thành "Đã tuyển dụng" trong bảng UngVien
	                String updateQuery = "UPDATE UngVien SET trangThai = ? WHERE maUngVien = ?";
	                PreparedStatement updateStatement = conn.prepareStatement(updateQuery);
	                updateStatement.setString(1, "Đã tuyển");
	                updateStatement.setString(2, maUngVien);
	                updateStatement.executeUpdate();
	                
	                conn.close();
	            } else {
	                hienThongBao("Lỗi", "Không thể kết nối đến CSDL!");
	            }
	        } catch (SQLException e) {
	            hienThongBao("Lỗi", "Lỗi khi thay đổi trạng thái: " + e.getMessage());
	        }
	    }
	    @FXML
	    public void themDotTuyenDung() {
	        String maTuyenDung =MaTuyenDung;
	        String maNhanVien = txtMaNhanVien.getText();
	        LocalDate ngayTuyenDung = LocalDate.now();

	        if (maTuyenDung.isEmpty() || maNhanVien.isEmpty() || ngayTuyenDung == null) {
	            hienThongBao("Lỗi", "Vui lòng điền đầy đủ thông tin.");
	            return;
	        }

	        try {
	            Connection conn = ConnectionDB.getConnection();
	            if (conn != null) {
	                String query = "INSERT INTO DotTuyenDung (maTuyenDung, maNhanVien, ngayTuyenDung) VALUES (?, ?, ?)";
	                PreparedStatement statement = conn.prepareStatement(query);
	                statement.setString(1, maTuyenDung);
	                statement.setString(2, maNhanVien);
	                statement.setDate(3, Date.valueOf(ngayTuyenDung));
	                int rowsInserted = statement.executeUpdate();
	                if (rowsInserted > 0) {
	                    hienThongBao("Thông báo", "Thêm đợt tuyển dụng thành công!");
	                } else {
	                    hienThongBao("Lỗi", "Thêm đợt tuyển dụng thất bại!");
	                }
	                conn.close();
	            } else {
	                hienThongBao("Lỗi", "Không thể kết nối đến CSDL!");
	            }
	        } catch (SQLException e) {
	            hienThongBao("Lỗi", "Lỗi khi thêm đợt tuyển dụng: " + e.getMessage());
	        }
	    }
	    @FXML
	    public void themNhanVien() {
	        String maNhanVien = txtMaNhanVien.getText();
	        String hoTen = txtHoTen.getText();
	        String CCCD = txtCCCD.getText();
	        String diaChi = txtDiaChi.getText();
	        String SDT = txtSDT.getText();
	        String maPhong = cmbPhongBan.getValue();
	        String maTrinhDo = cmbTrinhDo.getValue();
	        String gioitinh  = txtGioiTinh.getText();
	        String maChucVu = "CV001";
	        String trangThai = "1";
	        LocalDate localDate = dtpNgaySinh.getValue(); // Lấy ngày từ DatePicker
	        java.sql.Date ngaySinh = java.sql.Date.valueOf(localDate); // 
	     
            String maTruongPhong = "022";
	        if (maNhanVien.isEmpty() || hoTen.isEmpty() || CCCD.isEmpty() || diaChi.isEmpty() || SDT.isEmpty() || maPhong.isEmpty() || maTrinhDo.isEmpty() ) {
	            hienThongBao("Lỗi", "Vui lòng điền đầy đủ thông tin.");
	            return;
	        }

	        try {
	            Connection conn = ConnectionDB.getConnection();
	            if (conn != null) {
	                String query = "INSERT INTO NhanVien (maNhanVien, hoTen, CCCD, diaChi, SDT, maPhong, maTrinhDo, maTruongPhong, gioiTinh, ngaySinh, maChucVu, trangThai) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	                PreparedStatement statement = conn.prepareStatement(query);
	                statement.setString(1, maNhanVien);
	                statement.setString(2, hoTen);
	                statement.setString(3, CCCD);
	                statement.setString(4, diaChi);
	                statement.setString(5, SDT);
	                statement.setString(6, maPhong);
	                statement.setString(7, maTrinhDo);
                    statement.setString(8, maTruongPhong);
                    statement.setString(9, gioitinh);
                    statement.setDate(10, ngaySinh);
                    statement.setString(11, maChucVu);
                    statement.setString(12, trangThai);
	                int rowsInserted = statement.executeUpdate();
	                if (rowsInserted > 0) {
	                    hienThongBao("Thông báo", "Tuyển nhân viên thành công!");
	                } else {
	                    hienThongBao("Lỗi", "Thêm nhân viên thất bại!");
	                }
	                conn.close();
	            } else {
	                hienThongBao("Lỗi", "Không thể kết nối đến CSDL!");
	            }
	        } catch (SQLException e) {
	            hienThongBao("Lỗi", "Lỗi khi thêm nhân viên: " + e.getMessage());
	        }
	    }
	    @FXML
	    void initialize() {
	  
	    	    cmbTrinhDo.setDisable(true);
	    	    dtpNgaySinh.setDisable(true);
	    	    txtCCCD.setDisable(true);
	    	    txtDiaChi.setDisable(true);
	    	    txtGioiTinh.setDisable(true);
	    	    txtHoTen.setDisable(true);
	    	    loadMaPhongBan();
	    	    txtSDT.setDisable(true);
	    	    btnTuyenDung.setOnAction(event -> {
	                themNhanVien();
	                thayDoiTrangThai(MaUngVien);
	                themDotTuyenDung();
	            });
	      
	    }
	    private void hienThongBao(String title, String content) {
	        Alert alert = new Alert(Alert.AlertType.INFORMATION);
	        alert.setTitle(title);
	        alert.setHeaderText(null);
	        alert.setContentText(content);
	        alert.showAndWait();
	    }

}
