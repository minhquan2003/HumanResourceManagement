package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.StringConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.chrono.Chronology;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.function.UnaryOperator;

import Connection.ConnectionDB;
public class AddTimekeepingController {

	 @FXML
	    private Button btnThem;

	    @FXML
	    private ComboBox<String> cmbMaNhanVien;

	    @FXML
	    private TextField txtNamChamCong;
   
	    
	    @FXML
	    private TextField txtThangChamCong;

	    @FXML
	    private TextArea txtChiTiet;

	    @FXML
	    private TextField txtMaChamCong;

	    @FXML
	    private TextField txtSoGioiLamThem;

	    @FXML
	    private TextField txtSoNgayLamViec;

	    @FXML
	    private TextField txtSoNgayNghi;

	    @FXML
	    private TextField txtSoNgayTre;
	    public void loadMaNhanVien() {
	    	 // Connect to MySQL
	        Connection conn = ConnectionDB.getConnection();
	        if (conn != null) {
	            System.out.println("Connected to MySQL successfully!");

	            // Fetch data from database
	            ObservableList<String> maNhanVienList = FXCollections.observableArrayList();
	            try {
	                Statement stmt = conn.createStatement();
	                ResultSet rs = stmt.executeQuery("SELECT maNhanVien FROM NhanVien ORDER BY maNhanVien");
	                while (rs.next()) {
	                    maNhanVienList.add(rs.getString("maNhanVien"));
	                }
	                rs.close();
	                stmt.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }

	            // Sort and add initial item
	            maNhanVienList.sort(String::compareToIgnoreCase);
	         

	            // Populate ComboBox with fetched data
	            cmbMaNhanVien.setItems(maNhanVienList);

	            try {
	                conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        } else {
	            System.out.println("Can not connect to MySQL!");
	        }
	    }
	    public void themBangChamCong() {
	    	
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
	                String query = "INSERT INTO BangChamCong (maBangChamCong, maNhanVien, thangChamCong, namChamCong, soNgayLamViec, soNgayNghi, soNgayTre, soGioLamThem, chiTiet) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	                PreparedStatement preparedStatement = conn.prepareStatement(query);
	                preparedStatement.setString(1, maBangChamCong);
	                preparedStatement.setString(2, maNhanVien);
	                preparedStatement.setInt(3, thangChamCong);
	                preparedStatement.setInt(4, namChamCong);
	                preparedStatement.setInt(5, soNgayLamViec);
	                preparedStatement.setInt(6, soNgayNghi);
	                preparedStatement.setInt(7, soNgayTre);
	                preparedStatement.setInt(8, soGioLamThem);
	                preparedStatement.setString(9, chiTiet);

	                // Execute the SQL statement
	                preparedStatement.executeUpdate();

	                // Close the statement
	                preparedStatement.close();

	                hienThongBao("Thông báo", "Thêm bảng chấm công mới thành công!");	            } catch (SQLException e) {
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
	    
	    private void hienThongBao(String title, String content) {
	        Alert alert = new Alert(Alert.AlertType.INFORMATION);
	        alert.setTitle(title);
	        alert.setHeaderText(null);
	        alert.setContentText(content);
	        alert.showAndWait();
	    }


	    @FXML
	    private void initialize() {
	    	loadMaNhanVien();
	  
	        LocalDate currentDate = LocalDate.now();
	        txtThangChamCong.setText(String.valueOf(currentDate.getMonthValue()));
	        txtNamChamCong.setText(String.valueOf(currentDate.getYear()));

	        txtThangChamCong.setDisable(true);
	        txtNamChamCong.setDisable(true);
	    	   addIntegerOnlyConstraint(txtSoNgayNghi);
	           addIntegerOnlyConstraint(txtSoNgayTre);
	           addIntegerOnlyConstraint(txtSoNgayLamViec);
	           addIntegerOnlyConstraint(txtSoGioiLamThem);
	        btnThem.setOnAction(event -> themBangChamCong());

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
}
