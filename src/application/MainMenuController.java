package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

import Connection.ConnectionDB;
import Model.CHUCVU;
import Model.LUONG;
import Model.TRINHDO;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.util.Date;

public class MainMenuController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
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
    private StackedBarChart<Number, Number> stackedBarChart;

    @FXML
    public void initialize() {
        // Fetch data from the database
        //XYChart.Series<Number, Number> series = fetchDataFromDatabase();

        // Add the series to the stackedBarChart
        //stackedBarChart.getData().add(series);
        
        loadPositions();
        loadLevels();
    }

    private XYChart.Series<Number, Number> fetchDataFromDatabase() {
        // Initialize an empty series
        XYChart.Series<Number, Number> series = new XYChart.Series<>();

        try {
            Connection conn = ConnectionDB.getConnection();
            String query = "SELECT namChamCong, AVG(luongCoBan) AS avg_luongCoBan " +
                           "FROM Luong " +
                           "INNER JOIN BangChamCong ON Luong.maBangChamCong = BangChamCong.maBangChamCong " +
                           "GROUP BY namChamCong";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int department = rs.getInt("namChamCong");
                double avgSalary = rs.getDouble("avg_luongCoBan");
                // Add data to the series
                series.getData().add(new XYChart.Data<>(department, avgSalary));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return series;
    }
    
    @FXML private TableView<CHUCVU> tableViewPositions;
    @FXML private TableColumn<CHUCVU, String> maChucVu;
    @FXML private TableColumn<CHUCVU, String> tenChucVu;
    @FXML private TableColumn<CHUCVU, Double> phuCapChucVu;
    @FXML private TableColumn<CHUCVU, String> moTa;

    
    @FXML private TableView<TRINHDO> tableViewLevels;
    @FXML private TableColumn<LUONG, String> maTrinhDo;
    @FXML private TableColumn<LUONG, String> trinhDoHocVan;
    @FXML private TableColumn<LUONG, String> trinhDoChuyenMon;
    @FXML private TableColumn<LUONG, String> chuyenNganh;
    
    public ObservableList<CHUCVU> getAllPositions() {
    	ObservableList<CHUCVU> data = FXCollections.observableArrayList();

        try {
            Connection conn = ConnectionDB.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT maChucVu, tenChucVu, phuCapChucVu, moTa FROM ChucVu");
            while (rs.next()) {
                String maChucVu = rs.getString("maChucVu");
                String tenChucVu = rs.getString("tenChucVu");
                double phuCapChucVu = rs.getDouble("phuCapChucVu");
                String moTa = rs.getString("moTa");

                CHUCVU cv = new CHUCVU(maChucVu, tenChucVu, phuCapChucVu, moTa);
                data.add(cv);
            }
            conn.close(); // Đóng kết nối sau khi sử dụng xong
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    };
    
    public ObservableList<TRINHDO> getAllLevels() {
    	ObservableList<TRINHDO> data = FXCollections.observableArrayList();

        try {
            Connection conn = ConnectionDB.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM TrinhDo");
            while (rs.next()) {
                String maTrinhDo = rs.getString("maTrinhDo");
                String trinhDoHocVan = rs.getString("trinhDoHocVan");
                String trinhDoChuyenMon = rs.getString("trinhDoChuyenMon");
                String chuyenNganh = rs.getString("chuyenNganh");

                TRINHDO trinhDo = new TRINHDO(maTrinhDo, trinhDoHocVan, trinhDoChuyenMon, chuyenNganh);
                data.add(trinhDo);
            }
            conn.close(); // Đóng kết nối sau khi sử dụng xong
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    };
    
    public void loadPositions() {
    	System.out.print("Loading form Position\n");
        maChucVu.setCellValueFactory(new PropertyValueFactory<>("maChucVu"));
        tenChucVu.setCellValueFactory(new PropertyValueFactory<>("tenChucVu"));
        phuCapChucVu.setCellValueFactory(new PropertyValueFactory<>("phuCapChucVu"));
        moTa.setCellValueFactory(new PropertyValueFactory<>("moTa"));
  
        tableViewPositions.setItems(getAllPositions());
    }
    
    public void loadLevels() {
    	System.out.print("Loading form Level\n");
        maTrinhDo.setCellValueFactory(new PropertyValueFactory<>("maTrinhDo"));
        trinhDoHocVan.setCellValueFactory(new PropertyValueFactory<>("trinhDoHocVan"));
        trinhDoChuyenMon.setCellValueFactory(new PropertyValueFactory<>("trinhDoChuyenMon"));
        chuyenNganh.setCellValueFactory(new PropertyValueFactory<>("chuyenNganh"));
        
        tableViewLevels.setItems(getAllLevels());
    }
    
}
