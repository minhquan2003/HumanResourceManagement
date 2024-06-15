package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.function.UnaryOperator;

import Connection.ConnectionDB;
import Model.BANGCHAMCONG;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

public class UpdateTimeKeepingController {

	   @FXML
	    private Button btnUpdate;

	    @FXML
	    private ComboBox<String> cmbMaNhanVien;

	    @FXML
	    private TextArea txtChiTiet;

	    @FXML
	    private TextField txtMaChamCong;

	    @FXML
	    private TextField txtNamChamCong;

	    @FXML
	    private TextField txtSoGioiLamThem;

	    @FXML
	    private TextField txtSoNgayLamViec;

	    @FXML
	    private TextField txtSoNgayNghi;

	    @FXML
	    private TextField txtSoNgayTre;

	    @FXML
	    private TextField txtThangChamCong;
	    public void setBangChamCong(BANGCHAMCONG bangChamCong) {
	      
	        cmbMaNhanVien.setValue(bangChamCong.getMaNhanVien());
	        txtChiTiet.setText(bangChamCong.getChiTiet());
	        txtMaChamCong.setText(bangChamCong.getMaBangChamCong());
	        txtNamChamCong.setText(String.valueOf(bangChamCong.getNamChamCong()));
	        txtSoGioiLamThem.setText(String.valueOf(bangChamCong.getSoGioLamThem()));
	        txtSoNgayLamViec.setText(String.valueOf(bangChamCong.getSoNgayLamViec()));
	        txtSoNgayNghi.setText(String.valueOf(bangChamCong.getSoNgayNghi()));
	        txtSoNgayTre.setText(String.valueOf(bangChamCong.getSoNgayTre()));
	        txtThangChamCong.setText(String.valueOf(bangChamCong.getThangChamCong()));
	    }
	    @FXML
	    private void initialize() {
	        LocalDate currentDate = LocalDate.now();
	        btnUpdate.setOnAction(event -> updateBangChamCong());
	    	  txtThangChamCong.setText(String.valueOf(currentDate.getMonthValue()));
		        txtNamChamCong.setText(String.valueOf(currentDate.getYear()));
		        txtMaChamCong.setDisable(true);
		        cmbMaNhanVien.setDisable(true);

		        txtThangChamCong.setDisable(true);
		        txtNamChamCong.setDisable(true);
		    	   addIntegerOnlyConstraint(txtSoNgayNghi);
		           addIntegerOnlyConstraint(txtSoNgayTre);
		           addIntegerOnlyConstraint(txtSoNgayLamViec);
		           addIntegerOnlyConstraint(txtSoGioiLamThem);

	    }
	    public void updateBangChamCong() {
	        // Kiểm tra xem các trường thông tin đã được nhập đầy đủ chưa
	        if (cmbMaNhanVien.getValue() == null ||
	            txtThangChamCong.getText().isEmpty() ||
	            txtNamChamCong.getText().isEmpty() ||
	            txtSoNgayLamViec.getText().isEmpty() ||
	            txtSoNgayNghi.getText().isEmpty() ||
	            txtSoNgayTre.getText().isEmpty() ||
	            txtSoGioiLamThem.getText().isEmpty() ||
	            txtChiTiet.getText().isEmpty() ||
	            txtMaChamCong.getText().isEmpty()) {
	            hienThongBao("Lỗi", "Vui lòng nhập đầy đủ thông tin");
	            return;
	        }

	        String maNhanVien = cmbMaNhanVien.getValue();
	        int thangChamCong = Integer.parseInt(txtThangChamCong.getText());
	        int namChamCong = Integer.parseInt(txtNamChamCong.getText());
	        int soNgayLamViec = Integer.parseInt(txtSoNgayLamViec.getText());
	        int soNgayNghi = Integer.parseInt(txtSoNgayNghi.getText());
	        int soNgayTre = Integer.parseInt(txtSoNgayTre.getText());
	        int soGioLamThem = Integer.parseInt(txtSoGioiLamThem.getText());
	        String chiTiet = txtChiTiet.getText();
	        String maBangChamCong = txtMaChamCong.getText();

	        // Connect to MySQL
	        Connection conn = ConnectionDB.getConnection();
	        if (conn != null) {
	            try {
	                // Prepare the SQL statement
	                String query = "UPDATE BangChamCong SET maNhanVien=?, thangChamCong=?, namChamCong=?, soNgayLamViec=?, soNgayNghi=?, soNgayTre=?, soGioLamThem=?, chiTiet=? WHERE maBangChamCong=?";
	                PreparedStatement preparedStatement = conn.prepareStatement(query);
	                preparedStatement.setString(1, maNhanVien);
	                preparedStatement.setInt(2, thangChamCong);
	                preparedStatement.setInt(3, namChamCong);
	                preparedStatement.setInt(4, soNgayLamViec);
	                preparedStatement.setInt(5, soNgayNghi);
	                preparedStatement.setInt(6, soNgayTre);
	                preparedStatement.setInt(7, soGioLamThem);
	                preparedStatement.setString(8, chiTiet);
	                preparedStatement.setString(9, maBangChamCong);

	                // Execute the SQL statement
	                preparedStatement.executeUpdate();

	                // Close the statement
	                preparedStatement.close();

	                hienThongBao("Thông báo", "Cập nhật thông tin bảng chấm công thành công!");
	            } catch (SQLException e) {
	                e.printStackTrace();
	            } finally {
	                try {
	                    // Close the connection
	                    conn.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	        } else {
	            System.out.println("Không thể kết nối tới MySQL!");
	        }
	    }

	    private void addIntegerOnlyConstraint(TextField textField) {
	        UnaryOperator<TextFormatter.Change> filter = change -> {
	            String newText = change.getControlNewText();
	            if (newText.matches("\\d*")) {
	                return change;
	            }
	            return null;
	        };

	        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
	        textField.setTextFormatter(textFormatter);
	    }
	    private void hienThongBao(String title, String content) {
	        Alert alert = new Alert(Alert.AlertType.INFORMATION);
	        alert.setTitle(title);
	        alert.setHeaderText(null);
	        alert.setContentText(content);
	        alert.showAndWait();
	    }
}
