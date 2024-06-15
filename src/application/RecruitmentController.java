package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import Connection.ConnectionDB;
import Model.BAOCAOTUYENDUNG;
import Model.DOTTUYENDUNG;
import Model.NHANVIEN;
import Model.PHONGBAN;
import Model.UNGVIEN;
import javafx.beans.property.SimpleObjectProperty;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RecruitmentController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
    void MainMenuScreen(MouseEvent event) throws IOException {
	 root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
	 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	 scene = new Scene(root);
	 stage.setScene(scene);
	 stage.show();
    }
	
	@FXML
    void EmployeeScreen(MouseEvent event) throws IOException {
	 root = FXMLLoader.load(getClass().getResource("Employee.fxml"));
	 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	 scene = new Scene(root);
	 stage.setScene(scene);
	 stage.show();
    }
    
    @FXML
    void ContractScreen(MouseEvent event) throws IOException {
	 root = FXMLLoader.load(getClass().getResource("Contract.fxml"));
	 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	 scene = new Scene(root);
	 stage.setScene(scene);
	 stage.show();
    }
    
    @FXML
    void DepartmentScreen(MouseEvent event) throws IOException {
	 root = FXMLLoader.load(getClass().getResource("Department.fxml"));
	 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	 scene = new Scene(root);
	 stage.setScene(scene);
	 stage.show();
    }
    
    @FXML
    void TimekeepingScreen(MouseEvent event) throws IOException {
	 root = FXMLLoader.load(getClass().getResource("Timekeeping.fxml"));
	 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	 scene = new Scene(root);
	 stage.setScene(scene);
	 stage.show();
    }
    
    @FXML
    void Salary_BonusScreen(MouseEvent event) throws IOException {
   	 root = FXMLLoader.load(getClass().getResource("Salary_Bonus.fxml"));
   	 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
   	 scene = new Scene(root);
   	 stage.setScene(scene);
   	 stage.show();
     }

    @FXML
    void AccountScreen(MouseEvent event) throws IOException {
  	 root = FXMLLoader.load(getClass().getResource("Account.fxml"));
  	 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  	 scene = new Scene(root);
  	 stage.setScene(scene);
  	 stage.show();
    }
    
   

    public ObservableList<BAOCAOTUYENDUNG> baocaotuyendungList() {
        ObservableList<BAOCAOTUYENDUNG> data = FXCollections.observableArrayList();

        try {
        	System.out.print("Loadding form tuyển dụng");

            Connection conn = ConnectionDB.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM baocaotuyendung WHERE disable = FALSE");
            while (rs.next()) {
                String maTuyenDung = rs.getString("maTuyenDung");
                String chucVu = rs.getString("chucVu");
                String hocVan = rs.getString("hocVan");
                String yeuCauGioiTinh = rs.getString("yeuCauGioiTinh");
                String yeuCauDoTuoi = rs.getString("yeuCauDoTuoi");
                int soLuongCanTuyen = rs.getInt("soLuongCanTuyen");
                Date hanNopHoSo = rs.getDate("hanNopHoSo");
                double mucLuongToiThieu = rs.getDouble("mucLuongToiThieu");
                double mucLuongToiDa = rs.getDouble("mucLuongToiDa");

                BAOCAOTUYENDUNG report = new BAOCAOTUYENDUNG(maTuyenDung, chucVu, hocVan, yeuCauGioiTinh, yeuCauDoTuoi, soLuongCanTuyen, hanNopHoSo, mucLuongToiThieu, mucLuongToiDa);
                data.add(report);
            }
            conn.close(); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnTuyen;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnReload;
    
    @FXML
    private Button btnThemUngVien;
    
    @FXML
    private Button btnXoaUngVien;
    @FXML
    private TableView<BAOCAOTUYENDUNG> tableViewBAOCAOTUYENDUNG;
    @FXML
    private TableColumn<BAOCAOTUYENDUNG, String> chuVu;

    @FXML
    private TableColumn<BAOCAOTUYENDUNG, Date> hanNopHoSo;

    @FXML
    private TableColumn<BAOCAOTUYENDUNG, String> hocVan;

    @FXML
    private TableColumn<BAOCAOTUYENDUNG, String> maTuyenDung;

    @FXML
    private TableColumn<BAOCAOTUYENDUNG, Double> mucLuongToiDa;

    @FXML
    private TableColumn<BAOCAOTUYENDUNG, Double> mucLuongToiThieu;

    @FXML
    private TableColumn<BAOCAOTUYENDUNG, Integer> soLuongCanTuyen;


    @FXML
    private TableColumn<BAOCAOTUYENDUNG, String> yeuCauDoTuoi;

    @FXML
    private TableColumn<BAOCAOTUYENDUNG, String> yeuCauGioiTinh;
    
    
    
    //Bang thu 2
    @FXML
    private TableColumn<DOTTUYENDUNG, String> maTuyenDung2;

    @FXML
    private TableView<DOTTUYENDUNG> tableViewDotTuyenDung;


    @FXML
    private TableColumn<DOTTUYENDUNG, String> maNhanVien;

    @FXML
    private TableColumn<DOTTUYENDUNG, Date> ngayTuyenDung;
    
    
    //Bang 3
    @FXML
    private TableColumn<UNGVIEN, String> CCCD;

    @FXML
    private TableColumn<UNGVIEN, String> SDT;
    
    @FXML
    private TableColumn<UNGVIEN, String> diaChi;

    @FXML
    private TableColumn<UNGVIEN, String> email;
    @FXML
    private TableColumn<UNGVIEN, String> maUngVien;

    @FXML
    private TableColumn<UNGVIEN, String> matTuyenDung3;
    @FXML
    private TableColumn<UNGVIEN, String> hoTen;
    @FXML
    private TableColumn<UNGVIEN, String> gioiTinh2;
    @FXML
    private TableColumn<UNGVIEN, Date> ngaySinh;

    @FXML
    private TableColumn<UNGVIEN, String> maTrinhDo;
    @FXML
    private TableColumn<UNGVIEN, String> trangThai;
    
    @FXML
    private TableView<UNGVIEN> tableViewUngVien;
    public ObservableList<DOTTUYENDUNG> doiTuyenDungList() {
        ObservableList<DOTTUYENDUNG> data = FXCollections.observableArrayList();

        try {
            System.out.print("Loadding form tuyển dụng");

            Connection conn = ConnectionDB.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM dottuyendung");

            while (rs.next()) {
                String maTuyenDung = rs.getString("maTuyenDung");
                String maNhanVien = rs.getString("maNhanVien");
                Date ngayTuyenDung = rs.getDate("ngayTuyenDung");

                DOTTUYENDUNG dot = new DOTTUYENDUNG(maTuyenDung, maNhanVien, ngayTuyenDung);
                data.add(dot);
            }
            conn.close(); // Đóng kết nối sau khi sử dụng xong
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    
    
    public void loadBaoCaoTuyenDung() {
    	System.out.print("Loadding form tuyển dụng");
        maTuyenDung.setCellValueFactory(new PropertyValueFactory<>("maTuyenDung"));
        chuVu.setCellValueFactory(new PropertyValueFactory<>("chucVu"));
        hocVan.setCellValueFactory(new PropertyValueFactory<>("hocVan"));
        yeuCauGioiTinh.setCellValueFactory(new PropertyValueFactory<>("yeuCauGioiTinh"));
        yeuCauDoTuoi.setCellValueFactory(new PropertyValueFactory<>("yeuCauDoTuoi"));
        soLuongCanTuyen.setCellValueFactory(new PropertyValueFactory<>("soLuongCanTuyen"));
        hanNopHoSo.setCellValueFactory(new PropertyValueFactory<>("hanNopHoSo"));
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        mucLuongToiThieu.setCellValueFactory(cellData -> {
            Double luong = cellData.getValue().getMucLuongToiThieu();
            String formattedLuong = decimalFormat.format(luong);
            return new SimpleObjectProperty(formattedLuong);
        });
        mucLuongToiDa.setCellValueFactory(cellData -> {
            Double luong = cellData.getValue().getMucLuongToiDa();
            String formattedLuong = decimalFormat.format(luong);
            return new SimpleObjectProperty(formattedLuong);
        });

        tableViewBAOCAOTUYENDUNG.setItems(baocaotuyendungList());
    }
    public void loadDotTuyenDung() {
        System.out.print("Loadding form tuyển dụng");
        maTuyenDung2.setCellValueFactory(new PropertyValueFactory<>("maTuyenDung"));
        maNhanVien.setCellValueFactory(new PropertyValueFactory<>("maNhanVien"));
        ngayTuyenDung.setCellValueFactory(new PropertyValueFactory<>("ngayTuyenDung"));

        tableViewDotTuyenDung.setItems(doiTuyenDungList());
    }
    public static ObservableList<UNGVIEN> ungVienList() {
        ObservableList<UNGVIEN> data = FXCollections.observableArrayList();

        try {
            Connection conn = ConnectionDB.getConnection();
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM ungvien WHERE disable = FALSE";

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String maTuyenDung = rs.getString("maTuyenDung");

                String maUngVien = rs.getString("maUngVien");
                String CCCD = rs.getString("CCCD");
                String hoTen = rs.getString("hoTen");
                String gioiTinh = rs.getString("gioiTinh");
                Date ngaySinh = rs.getDate("ngaySinh");
                String diaChi = rs.getString("diaChi");
                String SDT = rs.getString("SDT");
                String danToc = rs.getString("danToc");
                String tonGiao = rs.getString("tonGiao");
                String email = rs.getString("email");
                double mucLuongDeal = rs.getDouble("mucLuongDeal");
                String maTrinhDo = rs.getString("maTrinhDo");
                String tenChucVu = rs.getString("tenChucVu");
                String trangThai = rs.getString("trangThai");

                UNGVIEN ungVien = new UNGVIEN(maTuyenDung, maUngVien, CCCD, hoTen, gioiTinh, ngaySinh, diaChi, SDT, danToc, tonGiao, email, mucLuongDeal, maTrinhDo, tenChucVu, trangThai);
                data.add(ungVien);
;
                
            }
            conn.close(); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    public void loadUngVien() {
        maUngVien.setCellValueFactory(new PropertyValueFactory<>("maUngVien"));
        CCCD.setCellValueFactory(new PropertyValueFactory<>("CCCD"));
        hoTen.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        gioiTinh2.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
        ngaySinh.setCellValueFactory(new PropertyValueFactory<>("ngaySinh"));
        diaChi.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
        SDT.setCellValueFactory(new PropertyValueFactory<>("SDT"));
        maTrinhDo.setCellValueFactory(new PropertyValueFactory<>("maTrinhDo"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        matTuyenDung3.setCellValueFactory(new PropertyValueFactory<>("maTuyenDung"));
        trangThai.setCellValueFactory(new PropertyValueFactory<>("trangThai"));
        
        tableViewUngVien.setItems(ungVienList());
    }
    private void deleteSelectedUngVien() {
        UNGVIEN selectedUngVien = tableViewUngVien.getSelectionModel().getSelectedItem();
        
        if (selectedUngVien != null) {
            Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Xác nhận xóa");
            confirmationAlert.setHeaderText("Bạn có chắc chắn muốn xóa ứng viên này không?");
            confirmationAlert.setContentText("Tên ứng viên: " + selectedUngVien.getHoTen());

            if (confirmationAlert.showAndWait().get() == ButtonType.OK) {
                try {
                    Connection conn = ConnectionDB.getConnection();
                    PreparedStatement stmt = conn.prepareStatement("UPDATE UngVien SET disable = TRUE WHERE maUngVien = ?");
                    stmt.setString(1, selectedUngVien.getMaUngVien());
                    
                    int rowsAffected = stmt.executeUpdate();
                    
                    if (rowsAffected > 0) {
                        showAlert(Alert.AlertType.INFORMATION, "Xóa thành công", "Ứng viên đã được xóa thành công.");
                        loadUngVien(); // Gọi hàm loadUngVien() để cập nhật lại danh sách ứng viên
                    } else {
                        showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể xóa ứng viên.");
                    }
                   
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Vui lòng chọn một ứng viên để xóa.");
        }
    }

    private void deleteBaoCaoTuyenDung() {
        BAOCAOTUYENDUNG selectedBaoCaoTuyenDung = tableViewBAOCAOTUYENDUNG.getSelectionModel().getSelectedItem();
        
        if (selectedBaoCaoTuyenDung != null) {
            Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Xác nhận xóa");
            confirmationAlert.setHeaderText("Bạn có chắc chắn muốn xóa báo cáo tuyển dụng này không?");
            confirmationAlert.setContentText("Tên báo cáo tuyển dụng: " + selectedBaoCaoTuyenDung.getMaTuyenDung());

            if (confirmationAlert.showAndWait().get() == ButtonType.OK) {
                try {
                    Connection conn = ConnectionDB.getConnection();
                    PreparedStatement stmt = conn.prepareStatement("UPDATE baocaotuyendung SET disable = TRUE WHERE maTuyenDung = ?");
                    stmt.setString(1, selectedBaoCaoTuyenDung.getMaTuyenDung());
                    
                    int rowsAffected = stmt.executeUpdate();
                    
                    if (rowsAffected > 0) {
                        showAlert(Alert.AlertType.INFORMATION, "Xóa thành công", "Báo cáo tuyển dụng đã được xóa thành công.");
                        loadBaoCaoTuyenDung() ;
                    } else {
                        showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể xóa báo cáo tuyển dụng.");
                    }
                   
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Vui lòng chọn báo cáo tuyển dụng để xóa.");
        }
    }
    @FXML
    void loadBaoCaoTuyenDung(MouseEvent event) {
    	loadBaoCaoTuyenDung();
    	
    }
    @FXML
    void initialize() {
    	 btnDelete.setOnAction(event -> deleteBaoCaoTuyenDung());
    	  btnAdd.setOnAction(event -> {
         	 Parent departmentRoot;
 			try {
 				departmentRoot = FXMLLoader.load(getClass().getResource("AddBaoCaoTuyenDung.fxml"));
 				  Scene departmentScene = new Scene(departmentRoot);

 		             Stage departmentStage = new Stage();
 		             departmentStage.setScene(departmentScene);
 		             departmentStage.show();
 			} catch (IOException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
            
         });
    	  btnThemUngVien.setOnAction(event -> {
          	 Parent departmentRoot;
  			try {
  				departmentRoot = FXMLLoader.load(getClass().getResource("AddCandidate.fxml"));
  				  Scene departmentScene = new Scene(departmentRoot);

  		             Stage departmentStage = new Stage();
  		             departmentStage.setScene(departmentScene);
  		             departmentStage.show();
  			} catch (IOException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
             
          });
    	  btnXoaUngVien.setOnAction(event -> {
    		  deleteSelectedUngVien();
          });

    	  btnReload.setOnAction(event -> {
              loadBaoCaoTuyenDung();
              loadDotTuyenDung();
              loadUngVien();
          });
    	  btnTuyen.setOnAction(event -> {
    	        UNGVIEN selectedUngVien = tableViewUngVien.getSelectionModel().getSelectedItem();
    	        
    	        if (selectedUngVien != null) {
    	            if ("Đã tuyển".equals(selectedUngVien.getTrangThai())) {
    	                showAlert(Alert.AlertType.ERROR, "Lỗi", "Nhân viên này đã được tuyển rồi, vui lòng chọn nhân viên khác.");
    	            } else {
    	                try {
    	                    FXMLLoader loader = new FXMLLoader(getClass().getResource("TuyenNhanVien.fxml"));
    	                    Parent departmentRoot = loader.load();
    	                    TuyenNhanVienController controller = loader.getController();
    	                    
    	                    controller.setUngVien(selectedUngVien);
    	                    
    	                    Scene departmentScene = new Scene(departmentRoot);

    	                    Stage departmentStage = new Stage();
    	                    departmentStage.setScene(departmentScene);
    	                    departmentStage.show();
    	                } catch (IOException e) {
    	                    e.printStackTrace();
    	                }
    	            }
    	        } else {
    	            showAlert(Alert.AlertType.ERROR, "Lỗi", "Vui lòng chọn một ứng viên để tuyển dụng.");
    	        }
    	    });
    	  btnUpdate.setOnAction(event -> {
    		  BAOCAOTUYENDUNG selectedBaoCaoTuyenDung = tableViewBAOCAOTUYENDUNG.getSelectionModel().getSelectedItem();
              
              if (selectedBaoCaoTuyenDung != null) {
                  try {
                      FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateBaoCaoTuyenDung.fxml"));
                      Parent departmentRoot = loader.load();
                      UpdateBaoCaoTuyenDungController controller = loader.getController();
                      
                      // Pass the selected PHONGBAN instance to the UpdateDepartmentController
                      controller.setBaoCaoTuyenDung(selectedBaoCaoTuyenDung);
                      
                      Scene departmentScene = new Scene(departmentRoot);

                      Stage departmentStage = new Stage();
                      departmentStage.setScene(departmentScene);
                      departmentStage.show();
                  } catch (IOException e) {
                      e.printStackTrace();
                  }
              } else {
                  showAlert(Alert.AlertType.ERROR, "Lỗi", "Vui lòng chọn một báo cáo tuyển dụng để cập nhật.");
              }
             
          });
    	  loadBaoCaoTuyenDung();
          loadDotTuyenDung();
          loadUngVien();
    }

    private void showAlert() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText("Bạn đã nhấp vào nút Add!");

        alert.showAndWait();
    }
    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
