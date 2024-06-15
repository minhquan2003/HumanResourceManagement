package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

import Connection.ConnectionDB;
import Model.BANGCHAMCONG;
import Model.LUONG;
import Model.PHONGBAN;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class TimekeepingController {
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
    void DepartmentScreen(MouseEvent event) throws IOException {
	 root = FXMLLoader.load(getClass().getResource("Department.fxml"));
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
    public ObservableList<BANGCHAMCONG> bangChamCongList() {
        ObservableList<BANGCHAMCONG> data = FXCollections.observableArrayList();

        try {
        	System.out.print("Loadding form Lương");

            Connection conn = ConnectionDB.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM bangchamcong");
            while (rs.next()) {
                String maBangChamCong = rs.getString("maBangChamCong");
                String maNhanVien = rs.getString("maNhanVien");
                int thangChamCong = rs.getInt("thangChamCong");
                int namChamCong = rs.getInt("namChamCong");
                int soNgayLamViec = rs.getInt("soNgayLamViec");
                int soNgayNghi = rs.getInt("soNgayNghi");
                int soNgayTre = rs.getInt("soNgayTre");
                int soGioLamThem = rs.getInt("soGioLamThem");
                String chiTiet = rs.getString("chiTiet");
                BANGCHAMCONG bangchamcong = new BANGCHAMCONG(maBangChamCong, maNhanVien, thangChamCong, namChamCong, soNgayLamViec, soNgayNghi, soNgayTre, soGioLamThem, chiTiet);
                data.add(bangchamcong);
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
    private Button btnUpdate;
    @FXML
    private Button btnReload;
    @FXML
    private TableView<BANGCHAMCONG> tableViewBangChamCong;
    @FXML
    private TableColumn<BANGCHAMCONG, String> maBangChamCong;

    @FXML
    private TableColumn<BANGCHAMCONG, String> maNhanVien;

    @FXML
    private TableColumn<BANGCHAMCONG, Integer> thangChamCong;

    @FXML
    private TableColumn<BANGCHAMCONG, Integer> namChamCong;

    @FXML
    private TableColumn<BANGCHAMCONG, Integer> soNgayLamViec;

    @FXML
    private TableColumn<BANGCHAMCONG, Integer> soNgayNghi;

    @FXML
    private TableColumn<BANGCHAMCONG, Integer> soNgayTre;

    @FXML
    private TableColumn<BANGCHAMCONG, Integer> soGioLamThem;

    @FXML
    private TableColumn<BANGCHAMCONG, String> chiTiet;
    
    public void loadBangChamCong() {
    	maBangChamCong.setCellValueFactory(new PropertyValueFactory<>("maBangChamCong"));
    	maNhanVien.setCellValueFactory(new PropertyValueFactory<>("maNhanVien"));
    	thangChamCong.setCellValueFactory(new PropertyValueFactory<>("thangChamCong"));
    	namChamCong.setCellValueFactory(new PropertyValueFactory<>("namChamCong"));
    	soNgayLamViec.setCellValueFactory(new PropertyValueFactory<>("soNgayLamViec"));
    	soNgayNghi.setCellValueFactory(new PropertyValueFactory<>("soNgayNghi"));
    	soNgayTre.setCellValueFactory(new PropertyValueFactory<>("soNgayTre"));
    	soGioLamThem.setCellValueFactory(new PropertyValueFactory<>("soGioLamThem"));
    	chiTiet.setCellValueFactory(new PropertyValueFactory<>("chiTiet"));
    	tableViewBangChamCong.setItems(bangChamCongList());
    }
    
    @FXML
    void initialize() {
    	loadBangChamCong();
    	 btnReload.setOnAction(event -> {
    		 loadBangChamCong();
            
         });
    	  btnAdd.setOnAction(event -> {
         	 Parent departmentRoot;
 			try {
 				departmentRoot = FXMLLoader.load(getClass().getResource("AddTimekeeping.fxml"));
 				  Scene departmentScene = new Scene(departmentRoot);

 		             Stage departmentStage = new Stage();
 		             departmentStage.setScene(departmentScene);
 		             departmentStage.show();
 			} catch (IOException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
            
         });
    	  btnUpdate.setOnAction(event -> {
    			 // Get the selected PHONGBAN from the TableView
              BANGCHAMCONG selectedBangChamCong= tableViewBangChamCong.getSelectionModel().getSelectedItem();
              
              if (selectedBangChamCong != null) {
                  try {
                      FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateTimeKeeping.fxml"));
                      Parent departmentRoot = loader.load();
                      UpdateTimeKeepingController controller = loader.getController();
                      
                      controller.setBangChamCong(selectedBangChamCong);
                      
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
//            
          });
    }
    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
