package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import Connection.ConnectionDB;
import Model.HOPDONGLAODONG;
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

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class ContractController {
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
    
    public ObservableList<HOPDONGLAODONG> hopDongLaoDongList() {
    	ObservableList<HOPDONGLAODONG> data = FXCollections.observableArrayList();
    	
    	try{
    		Connection conn = ConnectionDB.getConnection();
    		Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM hopdonglaodong");
                while (rs.next()) {
                    String maHopDong = rs.getString("maHopDong");
                    Date tuNgay = rs.getDate("tuNgay");
                    Date denNgay = rs.getDate("denNgay");
                    String loaiHopDong = rs.getString("loaiHopDong");
                    Double luongCoBan = rs.getDouble("luongCoBan");
                    String moTa = rs.getString("moTa");
                    HOPDONGLAODONG contract = new HOPDONGLAODONG(maHopDong, tuNgay, denNgay, loaiHopDong, luongCoBan, moTa);
                    data.add(contract);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
    	return data;
    }
    
    @FXML
    private TableView<HOPDONGLAODONG> tableViewHOPDONGLAODONG;
    
    @FXML
    private TableColumn<HOPDONGLAODONG, String> maHopDong;
    
    @FXML
    private TableColumn<HOPDONGLAODONG, Date> tuNgay;
    
    @FXML
    private TableColumn<HOPDONGLAODONG, Date> denNgay;
    
    @FXML
    private TableColumn<HOPDONGLAODONG, String> loaiHopDong;
    
    @FXML
    private TableColumn<HOPDONGLAODONG, Double> luongCoBan;
    
    @FXML
    private TableColumn<HOPDONGLAODONG, String> moTa;
    
    @FXML
    private TextField txtContractID;
    
    @FXML
    private DatePicker dpFromDate;
    
    @FXML
    private DatePicker dpToDate;
    
    @FXML
    private TextField txtTypeOfContract;
    
    @FXML
    private TextField txtBasicSalary;
    
    @FXML
    private TextArea txtaNote;
    
    @FXML
    private Button btnReload;
    
    @FXML
    private Button btnAdd;
    
    @FXML
    private Button btnChoose;
    
    @FXML
    private Button btnUpdate;
    
    public void loadHopDongLaoDong() {
    	maHopDong.setCellValueFactory(new PropertyValueFactory<>("maHopDong"));
    	tuNgay.setCellValueFactory(new PropertyValueFactory<>("tuNgay"));
    	denNgay.setCellValueFactory(new PropertyValueFactory<>("denNgay"));
    	loaiHopDong.setCellValueFactory(new PropertyValueFactory<>("loaiHopDong"));
    	DecimalFormat decimalFormat = new DecimalFormat("#.00");
        luongCoBan.setCellValueFactory(cellData -> {
            Double luong = cellData.getValue().getLuongCoBan();
            String formattedLuong = decimalFormat.format(luong);
            return new SimpleObjectProperty(formattedLuong);
        });
    	moTa.setCellValueFactory(new PropertyValueFactory<>("moTa"));
    	tableViewHOPDONGLAODONG.setItems(hopDongLaoDongList());
    }
    
    @FXML
    void initialize() {
    	loadHopDongLaoDong();
    	txtContractID.setDisable(false);
    	dpFromDate.setDisable(false);
    	dpToDate.setDisable(false);
    	txtTypeOfContract.setDisable(false);
    	txtBasicSalary.setDisable(false);
    	txtaNote.setDisable(false);
	    btnUpdate.setDisable(true);
    }
    
    @FXML
	void insertContract() {    	
	    try {
	        Connection conn = ConnectionDB.getConnection();
	        Statement stmt = conn.createStatement();
	        String contractID = txtContractID.getText();
	        String fromDate = dpFromDate.getValue() != null ? dpFromDate.getValue().toString() : "";
	        String toDate = dpToDate.getValue() != null ? dpToDate.getValue().toString() : "";
	        String typeContract = txtTypeOfContract.getText();
	        String basicSalary = txtBasicSalary.getText();
	        String note = txtaNote.getText();
	        String query = "INSERT INTO HOPDONGLAODONG (maHopDong, tuNgay, denNgay, loaiHopDong, luongCoBan, moTa) "
	                + "VALUES ('" + contractID + "', '" + fromDate +  "', '" + toDate + "', '" + typeContract + 
	                "', '" + basicSalary + "', '" + note + "')";

	        stmt.executeUpdate(query);
	        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
	        successAlert.setTitle("Insert Contract");
	        successAlert.setHeaderText(null);
	        successAlert.setContentText("Contract inserted successfully!");
	        successAlert.showAndWait();
	        resetFields();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
	        errorAlert.setTitle("Insert Contract");
	        errorAlert.setHeaderText(null);
	        errorAlert.setContentText("An error occurred while inserting contract!");
	        errorAlert.showAndWait();
	    }
	}
    
    @FXML
    void getValueToUpdate() {
    	HOPDONGLAODONG selectedContract = tableViewHOPDONGLAODONG.getSelectionModel().getSelectedItem();
    	if(selectedContract != null) {
            Date tuNgay = selectedContract.getTuNgay();
            Date denNgay = selectedContract.getDenNgay();
            java.util.Date utilFromDate = new java.util.Date(tuNgay.getTime());
            java.util.Date utilToDate = new java.util.Date(denNgay.getTime());
                       
            txtContractID.setText(selectedContract.getMaHopDong());
            LocalDate fromDate = utilFromDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate toDate = utilToDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            dpFromDate.setValue(fromDate);
            dpToDate.setValue(toDate);
            txtTypeOfContract.setText(selectedContract.getLoaiHopDong());
            Double luongCoBan = selectedContract.getLuongCoBan();
            String luongCoBanString = luongCoBan.toString();
            txtBasicSalary.setText(luongCoBanString);
    	    txtaNote.setText(selectedContract.getMoTa());
    	    
    	    txtContractID.setDisable(true);
    	    btnUpdate.setDisable(false);
    	    btnAdd.setDisable(true);
    	}
    }
    
    @FXML
	void updateContract() {    	
	    try {
	        Connection conn = ConnectionDB.getConnection();
	        Statement stmt = conn.createStatement();
	        String contractID = txtContractID.getText();
	        String fromDate = dpFromDate.getValue() != null ? dpFromDate.getValue().toString() : "";
	        String toDate = dpToDate.getValue() != null ? dpToDate.getValue().toString() : "";
	        String typeContract = txtTypeOfContract.getText();
	        String basicSalary = txtBasicSalary.getText();
	        String note = txtaNote.getText();
	        String query = "UPDATE HOPDONGLAODONG SET tuNgay = '" + fromDate + "', denNgay = '" + toDate + 
	        		"', loaiHopDong = '" + typeContract + "', luongCoBan = '" + basicSalary +
	        		"', moTa = '" + note + "' WHERE maHopDong = '" + contractID + "'";

	        stmt.executeUpdate(query);
	        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
	        successAlert.setTitle("Insert Contract");
	        successAlert.setHeaderText(null);
	        successAlert.setContentText("Contract inserted successfully!");
	        successAlert.showAndWait();
	        resetFields();
	        txtContractID.setDisable(false);
	        btnUpdate.setDisable(true);
	        btnAdd.setDisable(false);
	    } catch (SQLException e) {
	        e.printStackTrace();
	        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
	        errorAlert.setTitle("Update Contract");
	        errorAlert.setHeaderText(null);
	        errorAlert.setContentText("An error occurred while inserting contract!");
	        errorAlert.showAndWait();
	    }
	}
    
    void resetFields() {
    	txtContractID.clear();
	    dpFromDate.setValue(null);
	    dpToDate.setValue(null);
	    txtTypeOfContract.clear();
	    txtBasicSalary.clear();
	    txtaNote.clear();
    }
}
