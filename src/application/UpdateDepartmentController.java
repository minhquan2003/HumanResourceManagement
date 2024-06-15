package application;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.ZoneId;

import Connection.ConnectionDB;
import Model.PHONGBAN;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
public class UpdateDepartmentController {

    @FXML
    private Button btnUpdate;

 

    @FXML
    private DatePicker dtpNgayThanhLap;

    @FXML
    private TextField txtMaPhongBan;

    @FXML
    private TextField txtTenPhongBan;

    public void capNhatPhongBan() {
        String maPhongBan = txtMaPhongBan.getText();
        String tenPhongBan = txtTenPhongBan.getText();
    

        if (maPhongBan.isEmpty() || tenPhongBan.isEmpty()) {
            hienThongBao("Lỗi", "Vui lòng điền đầy đủ thông tin.");
            return;
        }

        try {
            Connection conn = ConnectionDB.getConnection();
            if (conn != null) {
                String query = "UPDATE PhongBan SET tenPhong = ? WHERE maPhong = ?";
                PreparedStatement statement = conn.prepareStatement(query);
                statement.setString(1, tenPhongBan);
//                statement.setString(2, truongPhong);
                statement.setString(2, maPhongBan);
                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    hienThongBao("Thông báo", "Cập nhật thông tin phòng ban thành công!");
                } else {
                    hienThongBao("Lỗi", "Không tìm thấy mã phòng ban để cập nhật!");
                }
                conn.close();
            } else {
                hienThongBao("Lỗi", "Không thể kết nối đến CSDL!");
            }
        } catch (SQLException e) {
            hienThongBao("Lỗi", "Lỗi khi cập nhật thông tin phòng ban: " + e.getMessage());
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
    	   
        btnUpdate.setOnAction(event -> capNhatPhongBan());
      
    }
	public void setPhongBan(PHONGBAN phongBan) {
        txtMaPhongBan.setText(phongBan.getMaPhong());
        txtTenPhongBan.setText(phongBan.getTenPhong());
//        cmbTruongPhong.setValue(phongBan.get);
        java.util.Date utilDate = new java.util.Date(phongBan.getNgayThanhLap().getTime());
        dtpNgayThanhLap.setValue(utilDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }
}
