package application;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Connection.ConnectionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
public class AddCandidateController {
	@FXML
    private Button btnThem;

    @FXML
    private ComboBox<String> cmbChucVu;

    @FXML
    private ComboBox<String> cmbGioiTinh;

    @FXML
    private ComboBox<String> cmbMaTuyenDung;

    @FXML
    private DatePicker dtpNgaySInh;

    @FXML
    private TextField txtCCCD;

    @FXML
    private TextField txtDanToc;

    @FXML
    private TextArea txtDiaChi;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtHoTen;

    @FXML
    private ComboBox<String> txtMaTrinhDo;

    @FXML
    private TextField txtMaUngVien;

    @FXML
    private TextField txtMucLuong;

    @FXML
    private TextField txtSDT;

    @FXML
    private TextField txtTonGiao;
	public AddCandidateController() {
		// TODO Auto-generated constructor stub
	}
	 private void loadMaTuyenDung() {
	        try {
	            Connection conn = ConnectionDB.getConnection();
	            if (conn != null) {
	                String query = "SELECT maTuyenDung FROM baocaotuyendung";
	                PreparedStatement statement = conn.prepareStatement(query);
	                ResultSet resultSet = statement.executeQuery();
	                List<String> maNhanVienList = new ArrayList<>();
	                while (resultSet.next()) {
	                    maNhanVienList.add(resultSet.getString("maTuyenDung"));
	                }
	                cmbMaTuyenDung.getItems().addAll(maNhanVienList);
	                if (!maNhanVienList.isEmpty()) {
	                    cmbMaTuyenDung.setValue(maNhanVienList.get(0));
	                }
	                conn.close();
	            } else {
	              
	            }
	        } catch (SQLException e) {
	          
	        }
	    }
	 private void loadMaTrinhDo() {
	        try {
	            Connection conn = ConnectionDB.getConnection();
	            if (conn != null) {
	                String query = "SELECT maTrinhDo FROM trinhdo";
	                PreparedStatement statement = conn.prepareStatement(query);
	                ResultSet resultSet = statement.executeQuery();
	                List<String> maNhanVienList = new ArrayList<>();
	                while (resultSet.next()) {
	                    maNhanVienList.add(resultSet.getString("maTrinhDo"));
	                }
	                txtMaTrinhDo.getItems().addAll(maNhanVienList);
	                if (!maNhanVienList.isEmpty()) {
	                    txtMaTrinhDo.setValue(maNhanVienList.get(0));
	                }
	                conn.close();
	            } else {
	              
	            }
	        } catch (SQLException e) {
	          
	        }
	    }
	 private void loadTenChucVu() {
	        try {
	            Connection conn = ConnectionDB.getConnection();
	            if (conn != null) {
	            	String query = "SELECT DISTINCT tenChucVu FROM chucvu";

	                PreparedStatement statement = conn.prepareStatement(query);
	                ResultSet resultSet = statement.executeQuery();
	                List<String> maNhanVienList = new ArrayList<>();
	                while (resultSet.next()) {
	                    maNhanVienList.add(resultSet.getString("tenChucVu"));
	                }
	                cmbChucVu.getItems().addAll(maNhanVienList);
	                if (!maNhanVienList.isEmpty()) {
	                    cmbChucVu.setValue(maNhanVienList.get(0));
	                }
	                conn.close();
	            } else {
	              
	            }
	        } catch (SQLException e) {
	          
	        }
	    }
	 @FXML
	 public void themUngVien() {
	     AddCandidateController ac = new AddCandidateController();
	     String maTuyenDung = cmbMaTuyenDung.getValue();
	     String maUngVien = txtMaUngVien.getText();
	     String CCCD = txtCCCD.getText();
	     String hoTen = txtHoTen.getText();
	     String gioiTinh = cmbGioiTinh.getValue();
	     LocalDate ngaySinh = dtpNgaySInh.getValue();
	     String diaChi = txtDiaChi.getText();
	     String SDT = txtSDT.getText();
	     String danToc = txtDanToc.getText();
	     String tonGiao = txtTonGiao.getText();
	     String email = txtEmail.getText();
	     double mucLuong =0;
	     // Kiểm tra nếu trường txtMucLuong không rỗng
	     if (!txtMucLuong.getText().isEmpty()) {
	         try {
	             // Nếu không rỗng, chuyển đổi thành số nguyên
	             mucLuong = Double.parseDouble(txtMucLuong.getText());
	         } catch (NumberFormatException e) {
	             // Nếu không thể chuyển đổi, báo lỗi và kết thúc hàm
	             hienThongBao("Lỗi", "Mức lương phải là số.");
	             return;
	         }
	     }
	     String maTrinhDo = txtMaTrinhDo.getValue();
	     String tenChucVu = cmbChucVu.getValue();
	     String trangThai = "Chưa tuyển"; 

	     if (maTuyenDung.isEmpty() || maUngVien.isEmpty() || CCCD.isEmpty() || hoTen.isEmpty() || gioiTinh.isEmpty() || ngaySinh == null ||
	             diaChi.isEmpty() || SDT.isEmpty() || email.isEmpty() || maTrinhDo.isEmpty() || tenChucVu.isEmpty() ||  maUngVien.isEmpty() || txtMucLuong.getText().isEmpty()) {
	         hienThongBao("Lỗi", "Vui lòng điền đầy đủ thông tin.");
	         return;
	     }

	     try {
	         Connection conn = ConnectionDB.getConnection();
	         if (conn != null) {
	             String query = "INSERT INTO UngVien (maTuyenDung, maUngVien, CCCD, hoTen, gioiTinh, ngaySinh, diaChi, SDT, danToc, tonGiao, email, mucLuongDeal, maTrinhDo, tenChucVu, trangThai, disable) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	             PreparedStatement statement = conn.prepareStatement(query);
	             statement.setString(1, maTuyenDung);
	             statement.setString(2, maUngVien);
	             statement.setString(3, CCCD);
	             statement.setString(4, hoTen);
	             statement.setString(5, gioiTinh);
	             statement.setDate(6, Date.valueOf(ngaySinh));
	             statement.setString(7, diaChi);
	             statement.setString(8, SDT);
	             statement.setString(9, danToc);
	             statement.setString(10, tonGiao);
	             statement.setString(11, email);
	             statement.setDouble(12, mucLuong);
	             statement.setString(13, maTrinhDo);
	             statement.setString(14, tenChucVu);
	             statement.setString(15, trangThai);
	             statement.setBoolean(16, false); // Disable default is false
	             int rowsInserted = statement.executeUpdate();
	             if (rowsInserted > 0) {
	                 hienThongBao("Thông báo", "Thêm ứng viên thành công!");
	                 // ac.loadUngVien(); // Gọi phương thức loadUngVien để cập nhật giao diện
	             } else {
	                 hienThongBao("Lỗi", "Thêm ứng viên thất bại!");
	             }
	             conn.close();
	         } else {
	             hienThongBao("Lỗi", "Không thể kết nối đến CSDL!");
	         }
	     } catch (SQLException e) {
	         hienThongBao("Lỗi", "Lỗi khi thêm ứng viên: " + e.getMessage());
	     }
	 }
	 private void hienThongBao(String title, String content) {
	        Alert alert = new Alert(Alert.AlertType.INFORMATION);
	        alert.setTitle(title);
	        alert.setHeaderText(null);
	        alert.setContentText(content);
	        alert.showAndWait();
	    }
	 @FXML
	    void initialize() {
	    	   loadMaTuyenDung();
	    	   loadMaTrinhDo();
	    	   loadTenChucVu();
	        btnThem.setOnAction(event -> themUngVien());
	    	   ObservableList<String> gioiTinhList = FXCollections.observableArrayList("Nam", "Nữ");
	           cmbGioiTinh.setItems(gioiTinhList);
	           cmbGioiTinh.setValue("Nam");
	    }
}
