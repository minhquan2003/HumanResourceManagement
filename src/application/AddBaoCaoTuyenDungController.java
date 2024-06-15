package application;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import Connection.ConnectionDB;
import Model.NHANVIEN;
import Model.PHONGBAN;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AddBaoCaoTuyenDungController {
	  @FXML
	    private Button btnThem;
	    @FXML
	    private Button btnReload;

	    @FXML
	    private ComboBox<String> cmbYeuCauGioiTinh;

	    @FXML
	    private DatePicker dtpThoiHanNopHoSo;

	    @FXML
	    private TextField txtDoTuoiMax;

	    @FXML
	    private TextField txtDoTuoiMin;

	    @FXML
	    private TextField txtMaTuyenDung;

	    @FXML
	    private TextField txtMucLuongToiDa;

	    @FXML
	    private TextField txtMucLuongToiThieu;

	    @FXML
	    private TextField txtSoLuong;

	    @FXML
	    private TextField txtTrinhDo;

	    @FXML
	    private ComboBox<String> cmbViTriTuyenDung;
	    
	    private void loadChucVuData() {
	        ObservableList<String> chucVuList = FXCollections.observableArrayList();

	        try {
	            Connection conn = ConnectionDB.getConnection();
	            if (conn != null) {
	                String query = "SELECT DISTINCT tenChucVu FROM ChucVu";
	                PreparedStatement statement = conn.prepareStatement(query);
	                ResultSet resultSet = statement.executeQuery();
	                while (resultSet.next()) {
	                    chucVuList.add(resultSet.getString("tenChucVu"));
	                }
	                conn.close();
	            } else {
	                hienThongBao("Lỗi", "Không thể kết nối đến CSDL!");
	            }
	        } catch (SQLException e) {
	            hienThongBao("Lỗi", "Lỗi khi tải dữ liệu chức vụ từ CSDL: " + e.getMessage());
	        }

	        cmbViTriTuyenDung.setItems(chucVuList);

	        // Nếu có ít nhất một chức vụ được tải từ CSDL, chọn chức vụ đầu tiên mặc định
	        if (!chucVuList.isEmpty()) {
	            cmbViTriTuyenDung.getSelectionModel().selectFirst();
	        }
	    }
	    @FXML
	    public void themBaoCaoTuyenDung() {
	        // Kiểm tra ràng buộc sau khi người dùng nhấn nút "Thêm"
	        String maTuyenDung = txtMaTuyenDung.getText();
	        String chucVu = cmbViTriTuyenDung.getValue();
	        if (maTuyenDung.isEmpty() || chucVu.isEmpty()) {
	            hienThongBao("Lỗi", "Vui lòng điền đầy đủ thông tin.");
	            return;
	        }

	        // Nếu thông tin cần thiết đã được nhập, thực hiện kiểm tra các ràng buộc khác
	        int hocVan = 0;
	        int doTuoiMin = 0;
	        int doTuoiMax = 0;
	        BigDecimal mucLuongToiThieu = BigDecimal.ZERO;
	        BigDecimal mucLuongToiDa = BigDecimal.ZERO;
	        LocalDate hanNopHoSo = dtpThoiHanNopHoSo.getValue();
	        int soLuongCanTuyen = 0;

	        try {
	            hocVan = Integer.parseInt(txtTrinhDo.getText());
	            doTuoiMin = Integer.parseInt(txtDoTuoiMin.getText());
	            doTuoiMax = Integer.parseInt(txtDoTuoiMax.getText());
	            mucLuongToiThieu = new BigDecimal(txtMucLuongToiThieu.getText());
	            mucLuongToiDa = new BigDecimal(txtMucLuongToiDa.getText());
	            soLuongCanTuyen = Integer.parseInt(txtSoLuong.getText());
	        } catch (NumberFormatException e) {
	            hienThongBao("Lỗi", "Vui lòng nhập đúng định dạng cho các trường số.");
	            return;
	        }

	        // Kiểm tra các ràng buộc khác
	        if (hocVan > 12) {
	            hienThongBao("Lỗi", "Trình độ phải nhỏ hơn hoặc bằng 12.");
	            return;
	        }

	        if (mucLuongToiThieu.compareTo(mucLuongToiDa) > 0) {
	            hienThongBao("Lỗi", "Lương tối thiểu phải nhỏ hơn hoặc bằng lương tối đa.");
	            return;
	        }

	        if (doTuoiMin > doTuoiMax) {
	            hienThongBao("Lỗi", "Độ tuổi nhập không hợp lệ!");
	            return;
	        }

	        if (hanNopHoSo == null) {
	            hienThongBao("Lỗi", "Vui lòng chọn thời hạn nộp hồ sơ.");
	            return;
	        }

	        // Nếu vượt qua tất cả các kiểm tra, tiến hành thêm báo cáo tuyển dụng
	        try {
	            Connection conn = ConnectionDB.getConnection();
	            if (conn != null) {
	                String query = "INSERT INTO BaoCaoTuyenDung (maTuyenDung, chucVu, hocVan, yeuCauGioiTinh, yeuCauDoTuoi, soLuongCanTuyen, hanNopHoSo, mucLuongToiThieu, mucLuongToiDa, disable) " +
	                               "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	                PreparedStatement statement = conn.prepareStatement(query);
	                statement.setString(1, maTuyenDung);
	                statement.setString(2, chucVu);
	                statement.setInt(3, hocVan);
	                statement.setString(4, cmbYeuCauGioiTinh.getValue());
	                statement.setString(5, doTuoiMin + "-" + doTuoiMax);
	                statement.setInt(6, soLuongCanTuyen);
	                statement.setDate(7, Date.valueOf(hanNopHoSo));
	                statement.setBigDecimal(8, mucLuongToiThieu);
	                statement.setBigDecimal(9, mucLuongToiDa);
	                statement.setBoolean(10, false);

	                int rowsInserted = statement.executeUpdate();
	                if (rowsInserted > 0) {
	                    hienThongBao("Thông báo", "Thêm báo cáo tuyển dụng thành công!");
	                } else {
	                    hienThongBao("Lỗi", "Thêm báo cáo tuyển dụng thất bại!");
	                }
	                conn.close();
	            } else {
	                hienThongBao("Lỗi", "Không thể kết nối đến CSDL!");
	            }
	        } catch (SQLException e) {
	            hienThongBao("Lỗi", "Lỗi khi thêm báo cáo tuyển dụng: " + e.getMessage());
	        }
	    }


	    @FXML
	    public void initialize() {
	    	 btnThem.setOnAction(event -> themBaoCaoTuyenDung());
	    	 
	    	loadChucVuData();
	    	
	    	
	    	
	    	
	        cmbYeuCauGioiTinh.getItems().addAll("Nam", "Nữ", "Không");

	        cmbYeuCauGioiTinh.setValue("Nam");
	        restrictToPositiveInteger(txtDoTuoiMax);
	        restrictToPositiveInteger(txtDoTuoiMin);
	        restrictToPositiveInteger(txtMucLuongToiDa);
	        restrictToPositiveInteger(txtMucLuongToiThieu);
	        restrictToPositiveInteger(txtSoLuong);
	        restrictToPositiveInteger(txtTrinhDo);
	    }


	    private void restrictToPositiveInteger(TextField textField) {
	        textField.textProperty().addListener((observable, oldValue, newValue) -> {
	            if (!newValue.matches("\\d*")) {
	                textField.setText(newValue.replaceAll("[^\\d]", ""));
	            }
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
