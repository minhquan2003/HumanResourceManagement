package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Connection.ConnectionDB;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CalculateSalaryController {
	@FXML TextField txtSID;
	@FXML TextField txtTID;
	@FXML TextField txtAllowances;
	@FXML TextField txtBonuses;
	@FXML TextField txtMinuses;
	@FXML Button btnCalculate;
	
	@FXML public void initialize() {
		btnCalculate.setOnAction(event -> {
			calculateSalary();
		});
	}
	
	public double getPhuCapChucVuByTID(String tID) {
		double result = 0;
		try {
            Connection conn = ConnectionDB.getConnection();
            String query = "SELECT cv.phuCapChucVu " +
                           "FROM BangChamCong bcc " +
                           "JOIN NhanVien nv ON bcc.maNhanVien = nv.maNhanVien " +
                           "JOIN ChucVu cv ON nv.maChucVu = cv.maChucVu " +
                           "WHERE bcc.maBangChamCong = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, tID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getDouble("phuCapChucVu");
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return result;
	}
	
	public Integer[] getNeedingFieldsInTimekeepingByTID(String tID) {
        Integer[] result = new Integer[3]; // Initialize the array
        try {
            Connection conn = ConnectionDB.getConnection();
            String query = "SELECT soNgayLamViec, soGioLamThem, soNgayTre FROM BangChamCong " +
                           "WHERE maBangChamCong = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, tID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result[0] = rs.getInt("soNgayLamViec");
                result[1] = rs.getInt("soGioLamThem");
                result[2] = rs.getInt("soNgayTre");
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
	
	public double getTaxBySID(String sID) {
		double result = 0;
		try {
            Connection conn = ConnectionDB.getConnection();
            String query = "SELECT thue FROM Luong WHERE maLuong = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, sID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getDouble("thue");
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return result;
	}
	
	public double getNetEarnBySID(String sID) {
		double result = 0;
		try {
            Connection conn = ConnectionDB.getConnection();
            String query = "SELECT luongCoBan FROM Luong WHERE maLuong = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, sID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getDouble("luongCoBan");
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return result;
	}
	
	public void updateThucLanh(String maLuong, double thucLanh) {
	    try {
	        Connection conn = ConnectionDB.getConnection();
	        String query = "UPDATE Luong SET thucLanh = ? WHERE maLuong = ?";
	        PreparedStatement pstmt = conn.prepareStatement(query);
	        pstmt.setDouble(1, thucLanh);
	        pstmt.setString(2, maLuong);
	        pstmt.executeUpdate();
	        conn.close();
	        System.out.println("ThucLanh updated successfully.");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	@FXML public void calculateSalary() {
		try {
			double luongCoBan = getNetEarnBySID(txtSID.getText());
			double phuCapChucVu = getPhuCapChucVuByTID(txtTID.getText());
	        double phuCapKhac = Double.parseDouble(txtAllowances.getText());
	        double khoanThuong = Double.parseDouble(txtBonuses.getText());
	        double khoanTruKhac = Double.parseDouble(txtMinuses.getText());
	        
	        Integer[] timekeepingFields = getNeedingFieldsInTimekeepingByTID(txtTID.getText());
	        int soNgayLamViec = timekeepingFields[0];
	        int soGioLamThem = timekeepingFields[1];
	        int soNgayTre = timekeepingFields[2];
	        
	        double thue = getTaxBySID(txtSID.getText());
	        // Calculate khoanCong
	        double khoanCong = (28 - soNgayLamViec) * (-300000) + phuCapChucVu + phuCapKhac 
	        		+ soGioLamThem * 200000 + khoanThuong;
	
	        // Calculate phat
	        double khoanTru = soNgayTre * 100000 + khoanTruKhac;
	
	        // Calculate thucLanh
	    	double thucLanh = luongCoBan + khoanCong - khoanTru - thue;
	    	showAlert("Success", "Real Earn: " + Double.toString(thucLanh));
	    	
	    	String maLuong = txtSID.getText();
	        updateThucLanh(maLuong, thucLanh);
		} catch (Exception e) {
	        showAlert("Error", "Could not calculate real earning.");
	        e.printStackTrace(); // Print stack trace for debugging
	    }
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
