package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import Connection.ConnectionDB;

public class AddAccountController {
	
	private List<AccountListener> listeners = new ArrayList<>();

    public void addListener(AccountListener listener) {
        listeners.add(listener);
    }

    public void removeListener(AccountListener listener) {
        listeners.remove(listener);
    }
	@FXML private TextField txtAccountID;
	@FXML private TextField txtUsername;
	@FXML private TextField txtPassword;
	@FXML private ComboBox<String> cmbRoleGroupID;
	@FXML private ImageView imgAvatar;
	@FXML private Button btnAvatarChooser;
	
	private String selectedPath; // Store the selected directory
	
	@FXML private Button btnAddAccount;
	
	@FXML void initialize() {
		getRoleGroupIDs();
		// Register AccountController as a listener
        AccountController accountController = new AccountController();
        addListener(accountController);
	}
	
	@FXML
    private void btnAvatarChooserClick(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
	    fileChooser.setTitle("Choose Image File");
	    fileChooser.getExtensionFilters().addAll(
	            new ExtensionFilter("Image Files", "*.jpg", "*.png")
	    );
	    File selectedFile = fileChooser.showOpenDialog(null);
	    if (selectedFile != null) {
	        try {
	            Image image = new Image(selectedFile.toURI().toString());
	            imgAvatar.setImage(image);

	            // Store the selected file path as a string
	            selectedPath = selectedFile.getAbsolutePath();
	        } catch (Exception e) {
	            // Handle exceptions
	            e.printStackTrace();
	            showAlert("Error", "Error occurred while loading the image: " + e.getMessage());
	        }
	    }
    }
	// Get all role group IDs from database
	private void getRoleGroupIDs() {
        Connection conn = ConnectionDB.getConnection();
        String query = "SELECT DISTINCT maNhomQuyen FROM TaiKhoan";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            // Execute the query
            ResultSet resultSet = statement.executeQuery();
            
            // Create an ObservableList to store role group IDs
            ObservableList<String> roleGroupIDs = FXCollections.observableArrayList();
            
            // Loop through the result set and add role group IDs to the list
            while (resultSet.next()) {
                String roleGroupID = resultSet.getString("maNhomQuyen");
                roleGroupIDs.add(roleGroupID);
            }
            // Close the statement and result set
            statement.close();
            resultSet.close();
            conn.close();
            
            // Set the role group IDs to the ComboBox
            cmbRoleGroupID.setItems(roleGroupIDs);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	@FXML
    public void addOneAccount() {
		String accID = txtAccountID.getText();
	    String username = txtUsername.getText();
	    String password = txtPassword.getText();
	    String roleGroupID = cmbRoleGroupID.getValue();
	    String avatarLink = null;

	    if (accID.isEmpty() || username.isEmpty() || password.isEmpty() || roleGroupID.isEmpty()) {
	        showAlert("Error", "Please fill in the required fields.");
	        return;
	    }

	    if (selectedPath != null) {
	        avatarLink = selectedPath;
	    }
        try {
            Connection conn = ConnectionDB.getConnection();
            if (conn != null) {
                String query = "INSERT INTO TaiKhoan (maTaiKhoan, username, pass, maNhomQuyen, avatar) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement statement = conn.prepareStatement(query);
                statement.setString(1, accID);
                statement.setString(2, username);
                statement.setString(3, password);
                statement.setString(4, roleGroupID);
                statement.setString(5, avatarLink);
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    showAlert("Success", "Added account successfully!");
                 // Notify listeners about successful addition
                    AccountEvent event = new AccountEvent();
                    for (AccountListener listener : listeners) {
                        listener.onAccountChanged(event);
                    }
                } else {
                    showAlert("Error", "Adding failed!");
                }
                conn.close();
            } else {
                showAlert("Error", "Could not connect to the database!");
            }
        } catch (SQLException e) {
            showAlert("Error", "Error when adding account: " + e.getMessage());
        }
    }
	
	
	
	//	SIDE METHODS
	public void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
	
	public static String fileToString(File file) {
		try {
            // Read the content of the file
            byte[] fileContent = new byte[(int) file.length()];
            FileInputStream fis = new FileInputStream(file);
            fis.read(fileContent);
            fis.close();

            // Encode the content to Base64
            return Base64.getEncoder().encodeToString(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
