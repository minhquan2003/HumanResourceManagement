package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Connection.ConnectionDB;
import Model.PHONGBAN;
import Model.TAIKHOAN;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AccountController implements AccountListener {
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
    void Salary_BonusScreen(MouseEvent event) throws IOException {
  	 root = FXMLLoader.load(getClass().getResource("Salary_Bonus.fxml"));
  	 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  	 scene = new Scene(root);
  	 stage.setScene(scene);
  	 stage.show();
    }
    
    public ObservableList<TAIKHOAN> getAllAccountInfo() {
        ObservableList<TAIKHOAN> data = FXCollections.observableArrayList();

        try {
            Connection conn = ConnectionDB.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT maTaiKhoan, username, maNhomQuyen FROM TaiKhoan WHERE disabled = FALSE");
            while (rs.next()) {
                String maTaiKhoan = rs.getString("maTaiKhoan");
                String username = rs.getString("username");
                String maNhomQuyen = rs.getString("maNhomQuyen");

                TAIKHOAN tk = new TAIKHOAN(maTaiKhoan, username, maNhomQuyen);
                data.add(tk);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    
    public void disableSelectedAccount() {
        TAIKHOAN selectedAcc = tableViewTaiKhoan.getSelectionModel().getSelectedItem();
        
        if (selectedAcc != null) {
            Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirm Disable");
            confirmationAlert.setHeaderText("Disable this account?");
            confirmationAlert.setContentText("Account ID: " + selectedAcc.getMaTaiKhoan());

            if (confirmationAlert.showAndWait().get() == ButtonType.OK) {
                try {
                    Connection conn = ConnectionDB.getConnection();
                    PreparedStatement stmt = conn.prepareStatement("UPDATE TaiKhoan SET disabled = TRUE WHERE maTaiKhoan = ?");
                    stmt.setString(1, selectedAcc.getMaTaiKhoan());
                    
                    int rowsAffected = stmt.executeUpdate();
                    
                    if (rowsAffected > 0) {
                        showAlert("Succeed", "Account Disabled.");
                        loadAccount();
                    } else {
                        showAlert("Error", "An error occurred when disabling the account!");
                    }
                   
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
             showAlert("Alert", "Please select an account to disable!");
        }
    }
    
    @FXML private TableView<TAIKHOAN> tableViewTaiKhoan;
    @FXML private TableColumn<TAIKHOAN, String> maTaiKhoan;
    @FXML private TableColumn<TAIKHOAN, String> username;
    @FXML private TableColumn<TAIKHOAN, String> maNhomQuyen;
    @FXML private TableColumn<TAIKHOAN, Void> resetPasswordColumn;
    @FXML private Button btnAdd;
    @FXML private Button btnDisable;
    
    public void loadAccount() {
    	System.out.print("Loading form Account");
        // Set cell value factories for other TableColumns
    	maTaiKhoan.setCellValueFactory(new PropertyValueFactory<>("maTaiKhoan"));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        maNhomQuyen.setCellValueFactory(new PropertyValueFactory<>("maNhomQuyen"));
        tableViewTaiKhoan.setItems(getAllAccountInfo());
        
        resetPasswordColumn.setCellFactory(param -> new TableCell<>() {
            private final Button btnResetPassword = new Button("Reset Password");
            
            {
            	btnResetPassword.setStyle("-fx-background-color: #068751; -fx-text-fill: white; -fx-font-weight: bold;");
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btnResetPassword);
                    btnResetPassword.setOnAction(event -> {
                        // Get the selected item from the TableView
                        TAIKHOAN tk = getTableView().getItems().get(getIndex());
                        // Call a method to open the password changing form, passing the account details
                        openPasswordResetForm(tk);
                    });
                }
            }
        });
    }
    
    public void openPasswordResetForm(TAIKHOAN tk) {
    	Parent resetPasswordRoot;
			try {
				resetPasswordRoot = FXMLLoader.load(getClass().getResource("ResetPassword.fxml"));
				  Scene resetPasswordScene = new Scene(resetPasswordRoot);
            
            Stage resetPasswordStage = new Stage();
            resetPasswordStage.setScene(resetPasswordScene);
            resetPasswordStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    void initialize() {
        loadAccount();
        
        btnAdd.setOnAction(event -> {
          	Parent accountRoot;
   			try {
   				accountRoot = FXMLLoader.load(getClass().getResource("AddAccount.fxml"));
   				  Scene accountScene = new Scene(accountRoot);

   		             Stage accountStage = new Stage();
   		             accountStage.setScene(accountScene);
   		             accountStage.show();
   			} catch (IOException e) {
   				// TODO Auto-generated catch block
   				e.printStackTrace();
   			}
         });  
    }
  
    
    @Override
    public void onAccountChanged(AccountEvent event) {
        // Refresh TableView data after changing the account
    	if (tableViewTaiKhoan == null)
    		tableViewTaiKhoan = new TableView<TAIKHOAN>();
    	tableViewTaiKhoan.getItems().clear();
        tableViewTaiKhoan.setItems(getAllAccountInfo());
    }
    
    //	SIDE METHODS
	public void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
