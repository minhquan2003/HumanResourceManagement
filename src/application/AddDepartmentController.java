package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Connection.ConnectionDB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddDepartmentController {

	   @FXML
	    private Button btnThem;


	    @FXML
	    private TextField txtMaPhongBan;

	    @FXML
	    private TextField txtTenPhongBan;
	    
	    @FXML
	    public void themPhongBan() {
	    	DepartmentController dp = new DepartmentController();
	        String maPhongBan = txtMaPhongBan.getText();
	        String tenPhongBan = txtTenPhongBan.getText();
	   

	        if (maPhongBan.isEmpty() || tenPhongBan.isEmpty() ) {
	            hienThongBao("Lỗi", "Vui lòng điền đầy đủ thông tin.");
	            return;
	        }

	        try {
	            Connection conn = ConnectionDB.getConnection();
	            if (conn != null) {
	                String query = "INSERT INTO PhongBan (maPhong, tenPhong, ngayThanhLap, disable) VALUES (?, ?, ?, ?)";
	                PreparedStatement statement = conn.prepareStatement(query);
	                statement.setString(1, maPhongBan);
	                statement.setString(2, tenPhongBan);
	                statement.setDate(3, Date.valueOf(LocalDate.now()));
	                statement.setBoolean(4, false);
	                int rowsInserted = statement.executeUpdate();
	                if (rowsInserted > 0) {
	                    hienThongBao("Thông báo", "Thêm phòng ban thành công!");
//	                    dp.loadPhongBan();
	                } else {
	                    hienThongBao("Lỗi", "Thêm phòng ban thất bại!");
	                }
	                conn.close();
	            } else {
	                hienThongBao("Lỗi", "Không thể kết nối đến CSDL!");
	            }
	        } catch (SQLException e) {
	            hienThongBao("Lỗi", "Lỗi khi thêm phòng ban: " + e.getMessage());
	        }
	    }
	    private void loadMaNhanVien() {
	        try {
	            Connection conn = ConnectionDB.getConnection();
	            if (conn != null) {
	                String query = "SELECT maNhanVien FROM NhanVien";
	                PreparedStatement statement = conn.prepareStatement(query);
	                ResultSet resultSet = statement.executeQuery();
	                List<String> maNhanVienList = new ArrayList<>();
	                while (resultSet.next()) {
	                    maNhanVienList.add(resultSet.getString("maNhanVien"));
	                }
	               
	                conn.close();
	            } else {
	                hienThongBao("Lỗi", "Không thể kết nối đến CSDL!");
	            }
	        } catch (SQLException e) {
	            hienThongBao("Lỗi", "Lỗi khi tải danh sách mã nhân viên: " + e.getMessage());
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
	    	   loadMaNhanVien();
	        btnThem.setOnAction(event -> themPhongBan());
	      
	    }
}
