package application;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

import Connection.ConnectionDB;
import Model.LUONG;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Salary_BonusController {
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
    void TimekeepingScreen(MouseEvent event) throws IOException {
   	 root = FXMLLoader.load(getClass().getResource("Timekeeping.fxml"));
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
    
    @FXML Button btnCalculateSalary;
    @FXML Button btnRefresh;
    
    @FXML
    void initialize() {
        loadLuong();
        
        btnExportSalary.setOnAction(event -> {
   			exportSalaryToExcel();
         });
        
     // Add listener to txtSearchSalary textProperty
        txtSearchSalary.textProperty().addListener((observable, oldValue, newValue) -> {
            searchSalary();
        });
        
        btnSearchSalary.setOnAction(event -> {
        	searchSalary();
        });
        
        btnCalculateSalary.setOnAction(event -> {
       	 Parent calSalRoot;
			try {
				calSalRoot = FXMLLoader.load(getClass().getResource("CalculateSalary.fxml"));
				  Scene calSalScene = new Scene(calSalRoot);
		             Stage calSalStage = new Stage();
		             calSalStage.setScene(calSalScene);
		             calSalStage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       });
        
       btnRefresh.setOnAction(event -> {
    	   loadLuong();
       });
    }
    
    @FXML
    public ObservableList<LUONG> luongList() {
        ObservableList<LUONG> data = FXCollections.observableArrayList();

        try {
            Connection conn = ConnectionDB.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Luong");
            while (rs.next()) {
                String maLuong = rs.getString("maLuong");
                String maBangChamCong = rs.getString("maBangChamCong");
                double luongCoBan = rs.getDouble("luongCoBan");
                double phuCapChucVu = rs.getDouble("phuCapChucVu");
                double phuCapKhac = rs.getDouble("phuCapKhac");
                double khoanThuong = rs.getDouble("khoanThuong");
                double khoanTruBaoHiem = rs.getDouble("khoanTruBaoHiem");
                double khoanTruKhac = rs.getDouble("khoanTruKhac");
                double thue = rs.getDouble("thue");
                double thucLanh = rs.getDouble("thucLanh");

                LUONG luong = new LUONG(maLuong, maBangChamCong, luongCoBan, phuCapChucVu, phuCapKhac, khoanThuong, khoanTruBaoHiem, khoanTruKhac, thue, thucLanh);
                data.add(luong);
            }
            conn.close(); // Đóng kết nối sau khi sử dụng xong
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    
    @FXML
    private TableView<LUONG> tableViewLuong;
    @FXML
    private TableColumn<LUONG, String> maLuong;

    @FXML
    private TableColumn<LUONG, String> maBangChamCong;

    @FXML
    private TableColumn<LUONG, Double> luongCoBan;

    @FXML
    private TableColumn<LUONG, Double> phuCapChucVu;

    @FXML
    private TableColumn<LUONG, Double> phuCapKhac;

    @FXML
    private TableColumn<LUONG, Double> khoanThuong;

    @FXML
    private TableColumn<LUONG, Double> khoanTruBaoHiem;

    @FXML
    private TableColumn<LUONG, Double> khoanTruKhac;

    @FXML
    private TableColumn<LUONG, Double> thue;
    
    @FXML
    private TableColumn<LUONG, Double> thucLanh;
    
    public void loadLuong() {
    	System.out.print("Loading form Salary");
        maLuong.setCellValueFactory(new PropertyValueFactory<>("maLuong"));
        maBangChamCong.setCellValueFactory(new PropertyValueFactory<>("maBangChamCong"));
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        luongCoBan.setCellValueFactory(cellData -> {
            Double luong = cellData.getValue().getLuongCoBan();
            String formattedLuong = decimalFormat.format(luong);
            return new SimpleObjectProperty(formattedLuong);
        });
        phuCapChucVu.setCellValueFactory(new PropertyValueFactory<>("phuCapChucVu"));
        phuCapKhac.setCellValueFactory(new PropertyValueFactory<>("phuCapKhac"));
        khoanThuong.setCellValueFactory(new PropertyValueFactory<>("khoanThuong"));
        khoanTruBaoHiem.setCellValueFactory(new PropertyValueFactory<>("khoanTruBaoHiem"));
        khoanTruKhac.setCellValueFactory(new PropertyValueFactory<>("khoanTruKhac"));
        thue.setCellValueFactory(new PropertyValueFactory<>("thue"));
        thucLanh.setCellValueFactory(new PropertyValueFactory<>("thucLanh"));
           
        tableViewLuong.setItems(luongList());
    }
    
    @FXML private Button btnExportSalary;
    
    @FXML
    public void exportSalaryToExcel() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Salary List");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));
        File file = fileChooser.showSaveDialog(new Stage());
        if (file != null) {
            try {
                XSSFWorkbook workbook = new XSSFWorkbook();
                Sheet sheet = workbook.createSheet("Sheet1");

                // Get the table columns
                List<TableColumn<LUONG, ?>> columns = tableViewLuong.getColumns();

                // Create header row
                Row headerRow = sheet.createRow(0);
                for (int i = 0; i < columns.size(); i++) {
                    TableColumn<LUONG, ?> column = columns.get(i);
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(column.getText());
                }

                // Populate data rows
                ObservableList<LUONG> data = tableViewLuong.getItems();
                for (int i = 0; i < data.size(); i++) {
                    LUONG row = data.get(i);
                    Row dataRow = sheet.createRow(i + 1);
                    // Assuming LUONG class has getter methods for its properties
                    for (int j = 0; j < columns.size(); j++) {
                        TableColumn<LUONG, ?> column = columns.get(j);
                        Cell cell = dataRow.createCell(j);
                        Object cellValue = column.getCellData(row);
                        if (cellValue != null) {
                            if (cellValue instanceof String) {
                                cell.setCellValue((String) cellValue);
                            } else if (cellValue instanceof Integer) {
                                cell.setCellValue((Integer) cellValue);
                            } else if (cellValue instanceof Double) {
                                cell.setCellValue((Double) cellValue);
                            } // Add more conditions for other data types if needed
                        }
                    }
                }

                // Write the workbook to the file
                try (FileOutputStream fos = new FileOutputStream(file)) {
                    workbook.write(fos);
                }
                showAlert("Success", "File written successfully!");
                workbook.close();
            } catch (IOException e) {
                showAlert("Error", "Could not export file"); 
                e.printStackTrace();
            }
        }
    }
    
    @FXML TextField txtSearchSalary;
    @FXML Button btnSearchSalary;
    
    @FXML void searchSalary() {
	ObservableList<LUONG> data = FXCollections.observableArrayList();
    	try{
    		Connection conn = ConnectionDB.getConnection();
    		Statement stmt = conn.createStatement();
    		String searchText = (String) txtSearchSalary.getText();
            ResultSet rs = stmt.executeQuery("SELECT *\r\n"
            		+ "FROM Luong\r\n"
            		+ "WHERE CONCAT(maLuong, maBangChamCong) LIKE '%" + searchText +"%'");
                while (rs.next()) {
                    String maLuong = rs.getString("maLuong");
                    String maBangChamCong = rs.getString("maBangChamCong");
                    double luongCoBan = rs.getDouble("luongCoBan");
                    double phuCapChucVu = rs.getDouble("phuCapChucVu");
                    double phuCapKhac = rs.getDouble("phuCapKhac");
                    double khoanThuong = rs.getDouble("khoanThuong");
                    double khoanTruBaoHiem = rs.getDouble("khoanTruBaoHiem");
                    double khoanTruKhac = rs.getDouble("khoanTruKhac");
                    double thue = rs.getDouble("thue");
                    double thucLanh = rs.getDouble("thucLanh");
                    LUONG salary = new LUONG(maLuong, maBangChamCong, luongCoBan, phuCapChucVu, phuCapKhac, khoanThuong, khoanTruBaoHiem, khoanTruKhac, thue, thucLanh);
                    data.add(salary);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
    	
    	maLuong.setCellValueFactory(new PropertyValueFactory<>("maLuong"));
    	maBangChamCong.setCellValueFactory(new PropertyValueFactory<>("maBangChamCong"));
    	luongCoBan.setCellValueFactory(new PropertyValueFactory<>("luongCoBan"));
    	phuCapChucVu.setCellValueFactory(new PropertyValueFactory<>("phuCapChucVu"));
    	phuCapKhac.setCellValueFactory(new PropertyValueFactory<>("phuCapKhac"));
    	khoanThuong.setCellValueFactory(new PropertyValueFactory<>("khoanThuong"));
    	khoanTruBaoHiem.setCellValueFactory(new PropertyValueFactory<>("khoanTruBaoHiem"));
    	khoanTruKhac.setCellValueFactory(new PropertyValueFactory<>("khoanTruKhac"));
    	thue.setCellValueFactory(new PropertyValueFactory<>("thue"));
    	thucLanh.setCellValueFactory(new PropertyValueFactory<>("thucLanh"));
    	tableViewLuong.setItems(data);
    }

    
    // SIDE METHODS
    public void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
