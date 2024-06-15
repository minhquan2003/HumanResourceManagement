package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Connection.ConnectionDB;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ResetPasswordController {
	
	@FXML private TextField reset_txtAccountID;
	@FXML private TextField reset_txtUsername;
	@FXML private TextField reset_txtPassword;
	@FXML private Button btnResetPassword;
	
	@FXML void initialize() {
		
	}
	
	public void resetPassword() {
        String accID = reset_txtAccountID.getText();
        String username = reset_txtUsername.getText();
        String newPassword = reset_txtPassword.getText();

        if (newPassword.isEmpty()) {
            showAlert("Error", "Please fill in the required field.");
            return;
        }

        try {
            Connection conn = ConnectionDB.getConnection();
            if (conn != null) {
                String query = "UPDATE TaiKhoan SET pass = ? WHERE maTaiKhoan = ? AND username = ?";
                PreparedStatement statement = conn.prepareStatement(query);
                statement.setString(1, newPassword);
                statement.setString(2, accID);
                statement.setString(3, username);
                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    showAlert("Info", "Reset password successfully!");
                } else {
                    showAlert("Error", "Reset password failed");
                }
                conn.close();
            } else {
                showAlert("Error", "Could not connect to the database!");
            }
        } catch (SQLException e) {
            showAlert("Error", "Something went wrong when resetting password: \n" + e.getMessage());
        }
    }
	public void btnResetPasswordClick() {
		resetPassword();
	}
	
	// SIDE METHODS
	
	private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
