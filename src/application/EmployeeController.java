package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Date;

import Connection.ConnectionDB;
import Model.NHANVIEN;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class EmployeeController {
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
    
    public ObservableList<NHANVIEN> nhanVienList() {
    	ObservableList<NHANVIEN> data = FXCollections.observableArrayList();
    	
    	try{
    		Connection conn = ConnectionDB.getConnection();
    		Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM nhanvien where trangThai = '1'");
                while (rs.next()) {
                    String maNhanVien = rs.getString("maNhanVien");
                    String CCCD = rs.getString("CCCD");
                    String hoTen = rs.getString("hoTen");
                    String gioiTinh = rs.getString("gioiTinh");
                    Date ngaySinh = rs.getDate("ngaySinh");
                    String diaChi = rs.getString("diaChi");
                    String SDT = rs.getString("SDT");
                    String maPhong = rs.getString("maPhong");
                    String maTrinhDo = rs.getString("maTrinhDo");
                    String maChucVu = rs.getString("maChucVu");
                    String maHopDong = rs.getString("maHopDong");
                    String maTruongPhong = rs.getString("maTruongPhong");
                    Date ngayBatDauThuViec = rs.getDate("ngayBatDauThuViec");
                    Date ngayKetThucThuViec = rs.getDate("ngayKetThucThuViec");
                    Double luongThuViec = rs.getDouble("luongThuViec");
                    String trangThai = rs.getString("trangThai");
                    NHANVIEN employee = new NHANVIEN(maNhanVien, CCCD, hoTen, gioiTinh, ngaySinh, diaChi, SDT, maPhong, maTrinhDo, maChucVu, maHopDong, maTruongPhong, ngayBatDauThuViec, ngayKetThucThuViec, luongThuViec, trangThai);
                    data.add(employee);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
    	return data;
    }
    
    @FXML
    private TableView<NHANVIEN> tableViewNHANVIEN;
    
    @FXML
    private TableColumn<NHANVIEN, String> maNhanVien;
    
    @FXML
    private TableColumn<NHANVIEN, String> CCCD;
    
    @FXML
    private TableColumn<NHANVIEN, String> hoTen;
    
    @FXML
    private TableColumn<NHANVIEN, String> gioiTinh;
    
    @FXML
    private TableColumn<NHANVIEN, Date> ngaySinh;
    
    @FXML
    private TableColumn<NHANVIEN, String> diaChi;
    
    @FXML
    private TableColumn<NHANVIEN, String> SDT;
    
    @FXML
    private TableColumn<NHANVIEN, String> maPhong;
    
    @FXML
    private TableColumn<NHANVIEN, String> maTrinhDo;
    
    @FXML
    private TableColumn<NHANVIEN, String> maChucVu;
    
    @FXML
    private TableColumn<NHANVIEN, String> maHopDong;
    
    @FXML
    private TableColumn<NHANVIEN, String> maTruongPhong;
    
    @FXML
    private TableColumn<NHANVIEN, Date> ngayBatDauThuViec;
    
    @FXML
    private TableColumn<NHANVIEN, String> ngayKetThucThuViec;
    
    @FXML
    private TableColumn<NHANVIEN, Double> luongThuViec;
    
    @FXML
    private TableColumn<NHANVIEN, String> trangThai;
    
    @FXML
    private TextField searchtxt;
    
    @FXML
    private ComboBox<String> attributeCbb;
    
    @FXML
    private Button searchbtn;
    
    @FXML
    private Button btnReload;
    
    public void loadNhanVien() {
    	maNhanVien.setCellValueFactory(new PropertyValueFactory<>("maNhanVien"));
    	CCCD.setCellValueFactory(new PropertyValueFactory<>("CCCD"));
    	hoTen.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
    	gioiTinh.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
    	ngaySinh.setCellValueFactory(new PropertyValueFactory<>("ngaySinh"));
    	diaChi.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
    	SDT.setCellValueFactory(new PropertyValueFactory<>("SDT"));
    	maPhong.setCellValueFactory(new PropertyValueFactory<>("maPhong"));
    	maTrinhDo.setCellValueFactory(new PropertyValueFactory<>("maTrinhDo"));
    	maChucVu.setCellValueFactory(new PropertyValueFactory<>("maChucVu"));
    	maHopDong.setCellValueFactory(new PropertyValueFactory<>("maHopDong"));
    	maTruongPhong.setCellValueFactory(new PropertyValueFactory<>("maTruongPhong"));
    	ngayBatDauThuViec.setCellValueFactory(new PropertyValueFactory<>("ngayBatDauThuViec"));
    	ngayKetThucThuViec.setCellValueFactory(new PropertyValueFactory<>("ngayKetThucThuViec"));
    	luongThuViec.setCellValueFactory(new PropertyValueFactory<>("luongThuViec"));
    	DecimalFormat decimalFormat = new DecimalFormat("#");
        luongThuViec.setCellValueFactory(cellData -> {
            Double luong = cellData.getValue().getLuongThuViec();
            String formattedLuong = (luong != null) ? decimalFormat.format(luong) : " ";
            return new SimpleObjectProperty(formattedLuong);
        });
        
    	trangThai.setCellValueFactory(new PropertyValueFactory<>("trangThai"));
    	tableViewNHANVIEN.setItems(nhanVienList());
    }
    
    @FXML
    void reload() {
    	loadNhanVien();
    }
    
    @FXML
    void initialize() {
        loadNhanVien();
    }
    
    @FXML
    void searchNhanVien() {
	ObservableList<NHANVIEN> data = FXCollections.observableArrayList();
    	try{
    		Connection conn = ConnectionDB.getConnection();
    		Statement stmt = conn.createStatement();
    		String searchText = searchtxt.getText();
            ResultSet rs = stmt.executeQuery("SELECT *\r\n"
            		+ "FROM quanlynhansu.nhanvien\r\n"
            		+ "WHERE CONCAT(maNhanVien, CCCD, hoTen, gioiTinh, ngaySinh, diaChi, SDT, maPhong, maTrinhDo, maChucVu, maHopDong, maTruongPhong, trangThai) LIKE '%" + searchText +"%'");
                while (rs.next()) {
                    String maNhanVien = rs.getString("maNhanVien");
                    String CCCD = rs.getString("CCCD");
                    String hoTen = rs.getString("hoTen");
                    String gioiTinh = rs.getString("gioiTinh");
                    String diaChi = rs.getString("diaChi");
                    String SDT = rs.getString("SDT");
                    String maPhong = rs.getString("maPhong");
                    String maTrinhDo = rs.getString("maTrinhDo");
                    String maChucVu = rs.getString("maChucVu");
                    String maHopDong = rs.getString("maHopDong");
                    String maTruongPhong = rs.getString("maTruongPhong");
                    String trangThai = rs.getString("trangThai");
                    NHANVIEN employee = new NHANVIEN(maNhanVien, CCCD, hoTen, gioiTinh, diaChi, SDT, maPhong, maTrinhDo, maChucVu, maHopDong, maTruongPhong, trangThai);
                    data.add(employee);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
    	
    	maNhanVien.setCellValueFactory(new PropertyValueFactory<>("maNhanVien"));
    	CCCD.setCellValueFactory(new PropertyValueFactory<>("CCCD"));
    	hoTen.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
    	gioiTinh.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
    	ngaySinh.setCellValueFactory(new PropertyValueFactory<>("ngaySinh"));
    	diaChi.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
    	SDT.setCellValueFactory(new PropertyValueFactory<>("SDT"));
    	maPhong.setCellValueFactory(new PropertyValueFactory<>("maPhong"));
    	maTrinhDo.setCellValueFactory(new PropertyValueFactory<>("maTrinhDo"));
    	maChucVu.setCellValueFactory(new PropertyValueFactory<>("maChucVu"));
    	maHopDong.setCellValueFactory(new PropertyValueFactory<>("maHopDong"));
    	maTruongPhong.setCellValueFactory(new PropertyValueFactory<>("maTruongPhong"));
    	ngayBatDauThuViec.setCellValueFactory(new PropertyValueFactory<>("ngayBatDauThuViec"));
    	ngayKetThucThuViec.setCellValueFactory(new PropertyValueFactory<>("ngayKetThucThuViec"));
    	luongThuViec.setCellValueFactory(new PropertyValueFactory<>("luongThuViec"));
    	trangThai.setCellValueFactory(new PropertyValueFactory<>("trangThai"));
    	tableViewNHANVIEN.setItems(data);
    }
    
    @FXML
    void insertEmp(MouseEvent event) throws IOException {
        Parent departmentRoot = FXMLLoader.load(getClass().getResource("AddEmployee.fxml"));
        loadNhanVien();
        Scene departmentScene = new Scene(departmentRoot);
        Stage departmentStage = new Stage();
        departmentStage.setScene(departmentScene);
        departmentStage.show();
    }
    
    @FXML
    void updateEmp(MouseEvent event) throws IOException {
        NHANVIEN selectedEmployee = tableViewNHANVIEN.getSelectionModel().getSelectedItem();
        
        if (selectedEmployee != null) {
            String maNhanVien = selectedEmployee.getMaNhanVien();
            String CCCD = selectedEmployee.getCCCD();
            String hoTen = selectedEmployee.getHoTen();
            String gioiTinh = selectedEmployee.getGioiTinh();
            Date ngaySinh = selectedEmployee.getNgaySinh();
            String diaChi = selectedEmployee.getDiaChi();
            String SDT = selectedEmployee.getSDT();
            String maPhong = selectedEmployee.getMaPhong();
            String maTrinhDo = selectedEmployee.getMaTrinhDo();
            String maChucVu = selectedEmployee.getMaChucVu();
            String maHopDong = selectedEmployee.getMaHopDong();
            String maTruongPhong = selectedEmployee.getMaTruongPhong();
            Date ngayBayDauThuViec = selectedEmployee.getNgayBatDauThuViec();
            Date ngayKetThucThuViec = selectedEmployee.getNgayKetThucThuViec();
            Double luongThuViec = selectedEmployee.getLuongThuViec();
            String trangThai = selectedEmployee.getTrangThai();
           
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddEmployee.fxml"));
            Parent departmentRoot = loader.load();
            AddEmployeeController addEmployeeController = loader.getController();
            addEmployeeController.setEmployeeInfo(maNhanVien, CCCD, hoTen, gioiTinh, ngaySinh, diaChi, SDT, maPhong, maTrinhDo, maChucVu, maHopDong, maTruongPhong, ngayBayDauThuViec, ngayKetThucThuViec, luongThuViec, trangThai);
            loadNhanVien();
            Scene departmentScene = new Scene(departmentRoot);

            Stage departmentStage = new Stage();
            departmentStage.setScene(departmentScene);
            departmentStage.show();
        }
    }
    
    @FXML
    void deleteEmp() {
    	NHANVIEN selectedEmployee = tableViewNHANVIEN.getSelectionModel().getSelectedItem();
    	if (selectedEmployee != null) {
    		try {
    			Connection conn = ConnectionDB.getConnection();
    	        Statement stmt = conn.createStatement();
    	        String maNhanVien = selectedEmployee.getMaNhanVien();
    	        String query = "UPDATE NHANVIEN SET  trangThai = '0' " + "WHERE maNhanVien = '" + maNhanVien + "'";

    	        stmt.executeUpdate(query);
    	        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
    	        successAlert.setTitle("Delete Employee");
    	        successAlert.setHeaderText(null);
    	        successAlert.setContentText("Employee delete successfully!");
    	        loadNhanVien();
    		}
    		catch(SQLException e) {
    			e.printStackTrace();
    	        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
    	        errorAlert.setTitle("Delete Employee");
    	        errorAlert.setHeaderText(null);
    	        errorAlert.setContentText("An error occurred while delete employee!");
    	        errorAlert.showAndWait();
    		}
    	}
    }
}