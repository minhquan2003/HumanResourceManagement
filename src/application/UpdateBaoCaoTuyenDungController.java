package application;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;

import Connection.ConnectionDB;
import Model.BAOCAOTUYENDUNG;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
public class UpdateBaoCaoTuyenDungController {

	 @FXML
	    private Button btnUpdate;

	    @FXML
	    private ComboBox<String> cmbViTriTuyenDung;

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


	        if (!chucVuList.isEmpty()) {
	            cmbViTriTuyenDung.getSelectionModel().selectFirst();
	        }
	    }
	    @FXML
	    public void updateBaoCaoTuyenDung() {
	        // Lấy thông tin từ giao diện
	        String maTuyenDung = txtMaTuyenDung.getText();
	        String chucVu = cmbViTriTuyenDung.getValue();
	        if (maTuyenDung.isEmpty() || chucVu.isEmpty()) {
	            hienThongBao("Lỗi", "Vui lòng điền đầy đủ thông tin.");
	            return;
	        }

	        // Lấy dữ liệu từ các trường nhập liệu
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

	        // Kiểm tra các ràng buộc
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

	        // Tiến hành cập nhật báo cáo tuyển dụng
	        try {
	            Connection conn = ConnectionDB.getConnection();
	            if (conn != null) {
	                String query = "UPDATE BaoCaoTuyenDung SET chucVu=?, hocVan=?, yeuCauGioiTinh=?, yeuCauDoTuoi=?, soLuongCanTuyen=?, hanNopHoSo=?, mucLuongToiThieu=?, mucLuongToiDa=? WHERE maTuyenDung=?";
	                PreparedStatement statement = conn.prepareStatement(query);
	                statement.setString(1, chucVu);
	                statement.setInt(2, hocVan);
	                statement.setString(3, cmbYeuCauGioiTinh.getValue());
	                statement.setString(4, doTuoiMin + "-" + doTuoiMax);
	                statement.setInt(5, soLuongCanTuyen);
	                statement.setDate(6, Date.valueOf(hanNopHoSo));
	                statement.setBigDecimal(7, mucLuongToiThieu);
	                statement.setBigDecimal(8, mucLuongToiDa);
	                statement.setString(9, maTuyenDung);

	                int rowsUpdated = statement.executeUpdate();
	                if (rowsUpdated > 0) {
	                    hienThongBao("Thông báo", "Cập nhật báo cáo tuyển dụng thành công!");
	                } else {
	                    hienThongBao("Lỗi", "Không có báo cáo tuyển dụng nào được cập nhật!");
	                }
	                conn.close();
	            } else {
	                hienThongBao("Lỗi", "Không thể kết nối đến CSDL!");
	            }
	        } catch (SQLException e) {
	            hienThongBao("Lỗi", "Lỗi khi cập nhật báo cáo tuyển dụng: " + e.getMessage());
	        }
	    }

	    private void hienThongBao(String title, String content) {
	        Alert alert = new Alert(Alert.AlertType.INFORMATION);
	        alert.setTitle(title);
	        alert.setHeaderText(null);
	        alert.setContentText(content);
	        alert.showAndWait();
	    }

	    public void setBaoCaoTuyenDung(BAOCAOTUYENDUNG baocao) {
	        txtMaTuyenDung.setText(baocao.getMaTuyenDung());
	        cmbViTriTuyenDung.setValue(baocao.getChucVu());
	        txtTrinhDo.setText(baocao.getHocVan());
	        cmbYeuCauGioiTinh.setValue(baocao.getYeuCauGioiTinh());
	        String[] tuoi = baocao.getYeuCauDoTuoi().split("-");
	        txtDoTuoiMin.setText(tuoi[0]);
	        txtDoTuoiMax.setText(tuoi[1]);
	        txtSoLuong.setText(String.valueOf(baocao.getSoLuongCanTuyen()));

	        
	        java.util.Date utilDate = new java.util.Date(baocao.getHanNopHoSo().getTime());
	        dtpThoiHanNopHoSo.setValue(utilDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
	        txtMucLuongToiThieu.setText(String.valueOf(baocao.getMucLuongToiThieu()));
	        txtMucLuongToiDa.setText(String.valueOf(baocao.getMucLuongToiDa()));
	    }
	    @FXML
	    public void initialize() {
	    	loadChucVuData();
	    	 btnUpdate.setOnAction(event -> updateBaoCaoTuyenDung());

	    	   txtMaTuyenDung.setDisable(true); 
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


}
