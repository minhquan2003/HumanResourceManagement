package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import Connection.ConnectionDB;
import Model.NHANVIEN;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	private TextField txtAccount;
	
	@FXML
	private TextField txtPassword;
	
	@FXML
	private Button btnLogin;
	
	@FXML
	void login() throws IOException {
		try{
    		Connection conn = ConnectionDB.getConnection();
    		Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT username FROM taikhoan where username= '" + txtAccount.getText() +
            		"' and pass= '" + txtPassword.getText() + "' and maNhomQuyen= 'QUANLY1'");
            String username = null;
            while (rs.next()) {
                username = rs.getString("username");
            }
            if(username != null) {
            	loginSuccess();
            }else {
            	Alert errorAlert = new Alert(Alert.AlertType.ERROR);
    	        errorAlert.setTitle("Login Error");
    	        errorAlert.setHeaderText(null);
    	        errorAlert.setContentText("Your username or your password is incorrect!");
    	        errorAlert.showAndWait();
    	        txtAccount.clear();
    	        txtPassword.clear();
            }
            } catch (SQLException e) {
                e.printStackTrace();
            }
	}
	
	void loginSuccess() throws IOException {
	    root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
	    stage = (Stage) txtAccount.getScene().getWindow();
	    scene = new Scene(root);
	    stage.setScene(scene);
	    stage.show();
	}
	
}
