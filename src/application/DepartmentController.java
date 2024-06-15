package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class DepartmentController {
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
    void RecruitmentScreen(MouseEvent event) throws IOException {
	 root = FXMLLoader.load(getClass().getResource("Recruitment.fxml"));
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
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnUpdate;
    @FXML
    private Label txtTenPhongBan;
    @FXML
    private TableColumn<PHONGBAN, String> maPhong;

    @FXML
    private TableColumn<PHONGBAN, Date> ngayThanhLap;

    @FXML
    private TableView<PHONGBAN> tableViewDepartment;

    @FXML
    private TableColumn<PHONGBAN, String> tenPhong;
    
    
    
    
    
    //Table NhanVien
    @FXML
    private TableColumn<NHANVIEN, String> diaChi;

    @FXML
    private TableColumn<NHANVIEN, String> hoTen;

    @FXML
    private TableColumn<NHANVIEN, String> maChucVu;

    @FXML
    private TableColumn<NHANVIEN, String> maHopDong;

    @FXML
    private TableColumn<NHANVIEN, String> maNhanVien;
    
    @FXML
    private TableColumn<NHANVIEN, String> SDT;
    
    @FXML
    private TableView<NHANVIEN> tableViewNHANVIEN;
    

    
    
    
    public ObservableList<PHONGBAN> phongBanList() {
        ObservableList<PHONGBAN> data = FXCollections.observableArrayList();

        try {
            System.out.print("Loading danh sách phòng ban");

            Connection conn = ConnectionDB.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM phongban WHERE disable = FALSE"); 
            while (rs.next()) {
                String maPhong = rs.getString("maPhong");
                String tenPhong = rs.getString("tenPhong");
                Date ngayThanhLap = rs.getDate("ngayThanhLap");

                PHONGBAN phongBan = new PHONGBAN(maPhong, tenPhong, ngayThanhLap);
                data.add(phongBan);
            }
            conn.close(); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    
    public void loadPhongBan() {
        System.out.print("Loading department form");
        maPhong.setCellValueFactory(new PropertyValueFactory<>("maPhong"));
        tenPhong.setCellValueFactory(new PropertyValueFactory<>("tenPhong"));
        ngayThanhLap.setCellValueFactory(new PropertyValueFactory<>("ngayThanhLap"));

        tableViewDepartment.setItems(phongBanList());
    }
    private void deleteSelectedPhongBan() {
        PHONGBAN selectedPhongBan = tableViewDepartment.getSelectionModel().getSelectedItem();
        
        if (selectedPhongBan != null) {
            Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Xác nhận xóa");
            confirmationAlert.setHeaderText("Bạn có chắc chắn muốn xóa phòng ban này không?");
            confirmationAlert.setContentText("Tên phòng ban: " + selectedPhongBan.getTenPhong());

            if (confirmationAlert.showAndWait().get() == ButtonType.OK) {
                try {
                    Connection conn = ConnectionDB.getConnection();
                    PreparedStatement stmt = conn.prepareStatement("UPDATE phongban SET disable = TRUE WHERE maPhong = ?");
                    stmt.setString(1, selectedPhongBan.getMaPhong());
                    
                    int rowsAffected = stmt.executeUpdate();
                    
                    if (rowsAffected > 0) {
                        showAlert(Alert.AlertType.INFORMATION, "Xóa thành công", "Phòng ban đã được xóa thành công.");
                        loadPhongBan(); 
                    } else {
                        showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể thay đổi trạng thái phòng ban.");
                    }
                   
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Vui lòng chọn một phòng ban để xóa.");
        }
    }

   

    public void loadNhanVien(String maPhong) {
        ObservableList<NHANVIEN> nhanVienList = FXCollections.observableArrayList();

        try {
            Connection conn = ConnectionDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM nhanvien WHERE maPhong = ?");
            stmt.setString(1, maPhong);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String maNhanVien = rs.getString("maNhanVien");
                String hoTen = rs.getString("hoTen");
                String diaChi = rs.getString("diaChi");
                String maChucVu = rs.getString("maChucVu");
                String maHopDong = rs.getString("maHopDong");
                String sdt = rs.getString("sdt");
                NHANVIEN nhanVien = new NHANVIEN();
                nhanVien.setMaNhanVien(maNhanVien);
                nhanVien.setHoTen(hoTen);
                nhanVien.setDiaChi(diaChi);
                nhanVien.setMaChucVu(maChucVu);
                nhanVien.setMaHopDong(maHopDong);
                nhanVien.setSDT(sdt);
                nhanVienList.add(nhanVien);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    	maNhanVien.setCellValueFactory(new PropertyValueFactory<>("maNhanVien"));
    	diaChi.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
    	SDT.setCellValueFactory(new PropertyValueFactory<>("SDT"));
    	hoTen.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
    	maChucVu.setCellValueFactory(new PropertyValueFactory<>("maChucVu"));
    	maHopDong.setCellValueFactory(new PropertyValueFactory<>("maHopDong"));
        tableViewNHANVIEN.setItems(nhanVienList);
    }
    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    @FXML
    void initialize() {
        loadPhongBan();
        btnDelete.setOnAction(event -> deleteSelectedPhongBan());
        btnAdd.setOnAction(event -> {
        	 Parent departmentRoot;
			try {
				departmentRoot = FXMLLoader.load(getClass().getResource("AddDepartment.fxml"));
				  Scene departmentScene = new Scene(departmentRoot);

		             Stage departmentStage = new Stage();
		             departmentStage.setScene(departmentScene);
		             departmentStage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
           
        });
        tableViewDepartment.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                PHONGBAN selectedPhongBan = tableViewDepartment.getSelectionModel().getSelectedItem();
                if (selectedPhongBan != null) {
                	txtTenPhongBan.setText("Nhân viên - " + selectedPhongBan.getTenPhong());
                    loadNhanVien(selectedPhongBan.getMaPhong());
                }
            }
        });
        btnUpdate.setOnAction(event -> {
        	 // Get the selected PHONGBAN from the TableView
            PHONGBAN selectedPhongBan = tableViewDepartment.getSelectionModel().getSelectedItem();
            
            if (selectedPhongBan != null) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateDepartment.fxml"));
                    Parent departmentRoot = loader.load();
                    UpdateDepartmentController controller = loader.getController();
                    
                    // Pass the selected PHONGBAN instance to the UpdateDepartmentController
                    controller.setPhongBan(selectedPhongBan);
                    
                    Scene departmentScene = new Scene(departmentRoot);

                    Stage departmentStage = new Stage();
                    departmentStage.setScene(departmentScene);
                    departmentStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Vui lòng chọn một phòng ban để cập nhật.");
            }
       });
    }
   
}
