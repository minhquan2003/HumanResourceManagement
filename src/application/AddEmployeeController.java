package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import Connection.ConnectionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class AddEmployeeController {
	
	@FXML
	private Label lbHeader;
	
	@FXML
	private TextField txtEmpID;
	
	@FXML
	private TextField txtCitizenID;
	
	@FXML
	private TextField txtName;
	
	@FXML
	private TextField txtGender;
	
	@FXML
	private DatePicker dpBirthday;
	
	@FXML
	private TextArea txtAddress;
	
	@FXML
	private TextField txtPhone;
	
	@FXML
	private ComboBox<String> cbDepartmentID;
	
	@FXML
	private ComboBox<String> cbLevelID;
	
	@FXML
	private ComboBox<String> cbPositionID;
	
	@FXML
	private ComboBox<String> cbContractID;
	
	@FXML
	private ComboBox<String> cbManagerID;
	
	@FXML
	private DatePicker dpProbationaryfirstdate;
	
	@FXML
	private DatePicker dpProbationaryenddate;
	
	@FXML
	private TextField txtProbationarysalary;
	
	@FXML
	private TextField txtStatus;
	
	@FXML
	private Button btnInsert;
	
	@FXML
	private Button btnUpdate;
	
	List<StoreValue> levelList = new LinkedList<>();
	List<StoreValue> managerList = new LinkedList<>();
	List<StoreValue> positionList = new LinkedList<>();
	List<StoreValue> contractList = new LinkedList<>();
	
	void getValueCbPhongBan() {
        try {
        	Connection conn = ConnectionDB.getConnection();
			Statement stmt = conn.createStatement();
			
			String getDepartmentName = "SELECT tenPhong, maPhong FROM PHONGBAN";
            ResultSet resultSetDepartmentName = stmt.executeQuery(getDepartmentName);
            List<String> tenPhongBanList = new ArrayList<>();
            while (resultSetDepartmentName.next()) {
                String tenPhongBan = resultSetDepartmentName.getString("tenPhong");
                //String maPhongBan = resultSetDepartmentName.getString("maPhong");
                tenPhongBanList.add(tenPhongBan);// + " (" + maPhongBan + ")"
                cbDepartmentID.setValue(tenPhongBan);
            }
            ObservableList<String> optionsDeparmentID = FXCollections.observableArrayList(tenPhongBanList);
            cbDepartmentID.setItems(optionsDeparmentID);
            
            String getLevel = "SELECT maTrinhDo, trinhDoHocVan, trinhDoChuyenMon, chuyenNganh FROM trinhdo";
            ResultSet resultSetLevel = stmt.executeQuery(getLevel);
            List<String> trinhDoList = new ArrayList<>();
            while (resultSetLevel.next()) {
            	String maTrinhDo = resultSetLevel.getString("maTrinhDo");
                String trinhDoHocVan = resultSetLevel.getString("trinhDoHocVan");
                String trinhDoChuyenMon = resultSetLevel.getString("trinhDoChuyenMon");
                String chuyenNganh = resultSetLevel.getString("chuyenNganh");
                StoreValue value = new StoreValue(maTrinhDo, trinhDoHocVan + ", " + trinhDoChuyenMon + ", " + chuyenNganh);
                levelList.add(value);
                trinhDoList.add(trinhDoHocVan + ", " + trinhDoChuyenMon + ", " + chuyenNganh);
                cbLevelID.setValue(value.getValue());
            }
            ObservableList<String> optionsLevel = FXCollections.observableArrayList(trinhDoList);
            cbLevelID.setItems(optionsLevel);
            
            String getPosition = "SELECT maChucVu ,tenChucVu, ngayNhanChuc FROM chucvu";
            ResultSet resultSetPosition = stmt.executeQuery(getPosition);
            List<String> positionsList = new ArrayList<>();
            while (resultSetPosition.next()) {
            	String maChucVu = resultSetPosition.getString("maChucVu");
            	String tenChucVu = resultSetPosition.getString("tenChucVu");
            	String ngayNhanChuc = resultSetPosition.getString("ngayNhanChuc");
                StoreValue value = new StoreValue(maChucVu, tenChucVu + ", " + ngayNhanChuc);
                positionList.add(value);
                positionsList.add(tenChucVu + ", " + ngayNhanChuc);
                cbPositionID.setValue(tenChucVu + ", " + ngayNhanChuc);
            }
            ObservableList<String> optionsPosition = FXCollections.observableArrayList(positionsList);
            cbPositionID.setItems(optionsPosition);
            
            String getContract = "SELECT maHopDong, tuNgay, denNgay, luongCoBan FROM hopdonglaodong";
            ResultSet resultSetContract = stmt.executeQuery(getContract);
            List<String> hopDongList = new ArrayList<>();
            while (resultSetContract.next()) {
            	String maHopDong = resultSetContract.getString("maHopDong");
            	String tuNgay = resultSetContract.getString("tuNgay");
            	String denNgay = resultSetContract.getString("denNgay");
            	String luongCoBan = resultSetContract.getString("luongCoBan");
                StoreValue value = new StoreValue(maHopDong, tuNgay + ", " + denNgay + ", " + luongCoBan);
                contractList.add(value);
                hopDongList.add(tuNgay + ", " + denNgay + ", " + luongCoBan);
                cbContractID.setValue(tuNgay + ", " + denNgay + ", " + luongCoBan);
            }
            ObservableList<String> optionsContract = FXCollections.observableArrayList(hopDongList);
            cbContractID.setItems(optionsContract);
            
            String getManager = "SELECT maNhanVien, hoTen\r\n"
            		+ "FROM quanlynhansu.NHANVIEN NV\r\n"
            		+ "WHERE NV.maNhanVien IN (\r\n"
            		+ "    SELECT DISTINCT NV2.maTruongPhong\r\n"
            		+ "    FROM quanlynhansu.NHANVIEN NV2\r\n"
            		+ "    WHERE NV.maNhanVien = NV2.maNhanVien\r\n"
            		+ ")";
            ResultSet resultSetManager = stmt.executeQuery(getManager);
            List<String> ManagerList = new ArrayList<>();
            while (resultSetManager.next()) {
            	String maNhanVien = resultSetManager.getString("maNhanVien");
            	String hoTen = resultSetManager.getString("hoTen");
                StoreValue value = new StoreValue(maNhanVien, maNhanVien + ", " + hoTen);
                managerList.add(value);
                ManagerList.add(maNhanVien + ", " + hoTen);
                cbManagerID.setValue(maNhanVien + ", " + hoTen);
            }
            ObservableList<String> optionsManager = FXCollections.observableArrayList(ManagerList);
            cbManagerID.setItems(optionsManager);
            
		} catch (SQLException e) {
			e.printStackTrace();
	        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
	        errorAlert.setTitle("Error");
	        errorAlert.setHeaderText(null);
	        errorAlert.setContentText("An error occurred while load department!");
	        errorAlert.showAndWait();
		}
	}	
	
	@FXML
    void initialize() {
		txtEmpID.setDisable(false);
	    txtCitizenID.setDisable(false);
	    txtName.setDisable(false);
	    txtGender.setDisable(false);
	    dpBirthday.setDisable(false);
	    txtAddress.setDisable(false);
	    txtPhone.setDisable(false);
	    dpProbationaryfirstdate.setDisable(false);
	    dpProbationaryenddate.setDisable(false);
	    txtProbationarysalary.setDisable(false);
	    txtStatus.setDisable(false);
	    btnInsert.setDisable(false);
	    
		btnUpdate.setDisable(true);
		lbHeader.setText("Insert Employee");
		
		getValueCbPhongBan();
    }
	
	@FXML
	void insertEmployee() {    	
	    try {	
	        Connection conn = ConnectionDB.getConnection();
	        Statement stmt = conn.createStatement();
	        String empID = txtEmpID.getText();
	        
	        String citizenID = txtCitizenID.getText();
	        if (!citizenID.matches("\\d{12}")) {
	            showErrorMessage("Citizen ID must be a 12-digit number.");
	            return;
	        }
	        String name = txtName.getText();
	        String gender = txtGender.getText();
	        if (!gender.equals("nam") && !gender.equals("nữ")) {
	            showErrorMessage("Invalid gender. Please enter either 'nam' or 'nữ'.");
	            return;
	        }
	        String birthday = dpBirthday.getValue() != null ? dpBirthday.getValue().toString() : "";
	        if (birthday.isEmpty()) {
	            showErrorMessage("Date of birth cannot be empty.");
	            return;
	        }
	        LocalDate currentDate = LocalDate.now();
	        LocalDate eighteenYearsAgo = currentDate.minusYears(18);
	        LocalDate birthdate = LocalDate.parse(birthday);
	        if (birthdate.isAfter(eighteenYearsAgo)) {
	            showErrorMessage("You must be at least 18 years old.");
	            return;
	        }
	        String address = txtAddress.getText();
	        String phone = txtPhone.getText();
	        if (!phone.matches("\\d{10}")) {
	            showErrorMessage("Phone number must be a 10-digit number.");
	            return;
	        }
	        
	        String maPhong = null;
	        String getDepartmentID = "SELECT maPhong FROM PHONGBAN WHERE tenPhong = '" + cbDepartmentID.getValue() + "'";
	        ResultSet resultSetDepartmentID = stmt.executeQuery(getDepartmentID);
	        if (resultSetDepartmentID.next()) {
	        	maPhong = resultSetDepartmentID.getString("maPhong");
	        }
	        resultSetDepartmentID.close();
	        
	        String trinhDo = null;
	        for (StoreValue vl  : levelList) {
				System.out.print(vl.getId() + ", " + vl.getValue());
	        	if(cbLevelID.getValue().equals(vl.getValue())) {
	        		trinhDo = vl.getId();
	        	}
	        }
	        
	        String maCongViec = null;
	        for (StoreValue vl  : positionList) {
				System.out.print(vl.getId() + ", " + vl.getValue());
	        	if(cbPositionID.getValue().equals(vl.getValue())) {
	        		maCongViec = vl.getId();
	        	}
	        }
	        
	        String maHopDong = null;
	        for (StoreValue vl  : contractList) {
				System.out.print(vl.getId() + ", " + vl.getValue());
	        	if(cbContractID.getValue().equals(vl.getValue())) {
	        		maHopDong = vl.getId();
	        	}
	        }
	        
	        String quanLyID = null;
	        for (StoreValue vl  : managerList) {
				System.out.print(vl.getId() + ", " + vl.getValue());
	        	if(cbManagerID.getValue().equals(vl.getValue())) {
	        		quanLyID = vl.getId();
	        	}
	        }
	        
	        String probationaryFirstDate = dpProbationaryfirstdate.getValue() != null ? dpProbationaryfirstdate.getValue().toString() : "";
	        String probationaryEndDate = dpProbationaryenddate.getValue() != null ? dpProbationaryenddate.getValue().toString() : "";
	        if (probationaryEndDate.isEmpty() || probationaryFirstDate.isEmpty()) {
	            showErrorMessage("Probationary start and end dates are required.");
	            return;
	        }

	        // Parse the dates to LocalDate objects
	        LocalDate endDate = LocalDate.parse(probationaryEndDate);
	        LocalDate firstDate = LocalDate.parse(probationaryFirstDate);

	        // Check if the endDate is after the firstDate
	        if (!endDate.isAfter(firstDate)) {
	            showErrorMessage("Probationary end date must be after the start date.");
	            return;
	        }
	        String probationarySalary = txtProbationarysalary.getText();
	        try {
	            double salary = Double.parseDouble(probationarySalary);
	            if (salary <= 0) {
	                showErrorMessage("Probationary salary must be a positive number.");
	                return;
	            }
	        } catch (NumberFormatException e) {
	            showErrorMessage("Probationary salary must be a valid number.");
	            return;
	        }
	        String status = txtStatus.getText();
	        if (!status.equals("0") && !status.equals("1")) {
	            showErrorMessage("Status must be either 0 or 1.");
	            return;
	        }
	        
	        if (empID.isEmpty() || citizenID.isEmpty() || name.isEmpty() || gender.isEmpty() || address.isEmpty() || phone == null ||
	        		birthday.isEmpty() || probationaryFirstDate.isEmpty() || probationaryEndDate.isEmpty() || probationarySalary.isEmpty() || status.isEmpty()) {
	        	showErrorMessage("Please fill in all the required information.");
		         return;
		     }
	        String query = "INSERT INTO NHANVIEN (maNhanVien, CCCD, hoTen, gioiTinh, ngaySinh, diaChi, SDT, maPhong, maTrinhDo, maChucVu, maHopDong, maTruongPhong, ngayBatDauThuViec, ngayKetThucThuViec, luongThuViec, trangThai) "
	                + "VALUES ('" + empID + "', '" + citizenID +  "', '" + name + "', '" + gender + "', '" + birthday + "', '" + address + "', '" + phone + "', '" + maPhong + "', '" + trinhDo + "', '" + maCongViec + "', '" + maHopDong + "', '" + quanLyID + "', '" + probationaryFirstDate + "', '" + probationaryEndDate + "', '" + probationarySalary + "', '" + status + "')";

	        stmt.executeUpdate(query);
	        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
	        successAlert.setTitle("Insert Employee");
	        successAlert.setHeaderText(null);
	        successAlert.setContentText("Successfully!");
	        successAlert.showAndWait();
	        resetFields();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
	        errorAlert.setTitle("Insert Employee");
	        errorAlert.setHeaderText(null);
	        errorAlert.setContentText("An error occurred while inserting employee!");
	        errorAlert.showAndWait();
	    }
	    
	}

	private void resetFields() {
	    txtEmpID.clear();
	    txtCitizenID.clear();
	    txtName.clear();
	    txtGender.clear();
	    dpBirthday.setValue(null);
	    txtAddress.clear();
	    txtPhone.clear();

	    cbDepartmentID.setValue(null);

	    cbLevelID.setValue(null);

	    cbPositionID.setValue(null);

	    cbContractID.setValue(null);

	    cbManagerID.setValue(null);
	    dpProbationaryfirstdate.setValue(null);
	    dpProbationaryenddate.setValue(null);
	    txtProbationarysalary.clear();
	    txtStatus.clear();
	}
	
	public void setEmployeeInfo(String maNhanVien, String CCCD, String hoTen, String gioiTinh, Date ngaySinh, 
			String diaChi, String SDT, String maPhong, String maTrinhDo, String maChucVu, String maHopDong, 
			String maTruongPhong, Date ngayBatDauThuViec, Date ngayKetThucThuViec, Double luongThuViec, String trangThai) {
		txtEmpID.setText(maNhanVien);
	    txtCitizenID.setText(CCCD);
	    txtName.setText(hoTen);
	    txtGender.setText(gioiTinh);
	    java.util.Date utilBirthday = new java.util.Date(ngaySinh.getTime());
	    LocalDate birthday = utilBirthday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	    dpBirthday.setValue(birthday);
	    txtAddress.setText(diaChi);
	    txtPhone.setText(SDT);
	    
	    
	    if (ngayBatDauThuViec != null) {
	        java.util.Date utilProbationaryfirstdate = new java.util.Date(ngayBatDauThuViec.getTime());
	        LocalDate probationaryfirstdate = utilProbationaryfirstdate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	        dpProbationaryfirstdate.setValue(probationaryfirstdate);
	    }
	    if (ngayKetThucThuViec != null) {
	        java.util.Date utilProbationaryenddate = new java.util.Date(ngayKetThucThuViec.getTime());
	        LocalDate probationaryenddate = utilProbationaryenddate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	        dpProbationaryenddate.setValue(probationaryenddate);
	    }
        String luongCoBanString = luongThuViec.toString();
        txtProbationarysalary.setText(luongCoBanString);
	    txtStatus.setText(trangThai);
	    
	    String tenPhong = null;
	    String tenTrinhDo = null;
	    String tenViTri = null;
	    String tenHopDong = null;
	    String tenQuanLy = null;

	    try {
	        Connection conn = ConnectionDB.getConnection();
	        Statement stmt = conn.createStatement();
	        
	        String phong = "SELECT tenPhong FROM PHONGBAN WHERE maPhong = '" + maPhong + "'";
	        ResultSet resultSetDepartment = stmt.executeQuery(phong);
	        if (resultSetDepartment.next()) {
	            tenPhong = resultSetDepartment.getString("tenPhong");
	        }
	        resultSetDepartment.close();
	        
	        String getLevel = "SELECT trinhDoHocVan, trinhDoChuyenMon, chuyenNganh FROM trinhdo WHERE maTrinhDo = '"  + maTrinhDo + "'";
            ResultSet resultSetLevel = stmt.executeQuery(getLevel);
            if (resultSetLevel.next()) {
            	String trinhDoHocVan = resultSetLevel.getString("trinhDoHocVan");
            	String trinhDoChuyenMon = resultSetLevel.getString("trinhDoChuyenMon");
            	String chuyenNganh = resultSetLevel.getString("chuyenNganh");
            	tenTrinhDo = trinhDoHocVan + ", " + trinhDoChuyenMon + ", " + chuyenNganh;
	        }
            resultSetLevel.close();
            
            String getPosition = "SELECT tenChucVu, ngayNhanChuc FROM chucvu WHERE maChucVu = '"  + maChucVu + "'";
            ResultSet resultSetPosition = stmt.executeQuery(getPosition);
            if (resultSetPosition.next()) {
            	String tenChucVu = resultSetPosition.getString("tenChucVu");
            	String ngayNhanChuc = resultSetPosition.getString("ngayNhanChuc");
            	tenViTri = tenChucVu + ", " + ngayNhanChuc;
	        }
            resultSetPosition.close();
            
            String getContract = "SELECT tuNgay, denNgay, luongCoBan FROM hopdonglaodong WHERE maHopDong = '"  + maHopDong + "'";
            ResultSet resultSetContract = stmt.executeQuery(getContract);
            if (resultSetContract.next()) {
            	String tuNgay = resultSetContract.getString("tuNgay");
            	String denNgay = resultSetContract.getString("denNgay");
            	String luongCoBan = resultSetContract.getString("luongCoBan");
            	tenHopDong = tuNgay + ", " + denNgay + ", " + luongCoBan;
	        }
            resultSetContract.close();
            
            String getManager = "SELECT maNhanVien, hoTen FROM nhanvien WHERE maNhanVien = '"  + maTruongPhong + "'";
            ResultSet resultSetManager = stmt.executeQuery(getManager);
            if (resultSetManager.next()) {
            	String maNhanVien1 = resultSetManager.getString("maNhanVien");
            	String hoTen1 = resultSetManager.getString("hoTen");
            	tenQuanLy = maNhanVien1 + ", " + hoTen1;
	        }
            resultSetManager.close();
            
	        stmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    cbDepartmentID.setValue(tenPhong);
	    cbLevelID.setValue(tenTrinhDo);
	    cbPositionID.setValue(tenViTri);
	    cbContractID.setValue(tenHopDong);
	    cbManagerID.setValue(tenQuanLy);
	    
	    txtEmpID.setDisable(true);
	    dpProbationaryfirstdate.setDisable(true);
	    dpProbationaryenddate.setDisable(true);
	    txtProbationarysalary.setDisable(true);
	    btnInsert.setDisable(true);
	    
	    btnUpdate.setDisable(false);
	    lbHeader.setText("Update Employee");
	}
	
	@FXML
	void updateEmp() {
//	    try {
//	        Connection conn = ConnectionDB.getConnection();
//	        Statement stmt = conn.createStatement();
//	        String empID = txtEmpID.getText();
//	        String citizenID = txtCitizenID.getText();
//	        String name = txtName.getText();
//	        String gender = txtGender.getText();
//	        String birthday = dpBirthday.getValue() != null ? dpBirthday.getValue().toString() : "";
//	        String address = txtAddress.getText();
//	        String phone = txtPhone.getText();
//
//	        String maPhong = null;
//	        String getDepartmentID = "SELECT maPhong FROM PHONGBAN WHERE tenPhong = '" + cbDepartmentID.getValue() + "'";
//	        ResultSet resultSetDepartmentID = stmt.executeQuery(getDepartmentID);
//	        if (resultSetDepartmentID.next()) {
//	        	maPhong = resultSetDepartmentID.getString("maPhong");
//	        }
//	        resultSetDepartmentID.close();
//	        
//	        String trinhDo = null;
//	        for (StoreValue vl  : levelList) {
//				System.out.print(vl.getId() + ", " + vl.getValue());
//	        	if(cbLevelID.getValue().equals(vl.getValue())) {
//	        		trinhDo = vl.getId();
//	        	}
//	        }
//	        
//	        String maCongViec = null;
//	        for (StoreValue vl  : positionList) {
//				System.out.print(vl.getId() + ", " + vl.getValue());
//	        	if(cbPositionID.getValue().equals(vl.getValue())) {
//	        		maCongViec = vl.getId();
//	        	}
//	        }
//	        
//	        String maHopDong = null;
//	        for (StoreValue vl  : contractList) {
//				System.out.print(vl.getId() + ", " + vl.getValue());
//	        	if(cbContractID.getValue().equals(vl.getValue())) {
//	        		maHopDong = vl.getId();
//	        	}
//	        }
//	        
//	        String quanLyID = null;
//	        for (StoreValue vl  : managerList) {
//				System.out.print(vl.getId() + ", " + vl.getValue());
//	        	if(cbManagerID.getValue().equals(vl.getValue())) {
//	        		quanLyID = vl.getId();
//	        	}
//	        }
//	        String status = txtStatus.getText();
//	        String query = "UPDATE NHANVIEN SET CCCD = '" + citizenID + "', hoTen = '" + name + "', gioiTinh = '" + gender +  "', ngaySinh = '" + birthday + "', diaChi = '" + address + "', SDT = '" + phone + "', maPhong = '" + maPhong + "', maTrinhDo = '" + trinhDo + "', maChucVu = '" + maCongViec + "', maHopDong = '" + maHopDong + "', maTruongPhong = '" + quanLyID + "', trangThai = '" + status + "' "
//	                + "WHERE maNhanVien = '" + empID + "'";
//
//	        stmt.executeUpdate(query);
//	        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
//	        successAlert.setTitle("Update Employee");
//	        successAlert.setHeaderText(null);
//	        successAlert.setContentText("Employee updated successfully!");
//	        successAlert.showAndWait();
//	        resetFields();
//	        new EmployeeController().loadNhanVien();
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	        showErrorMessage("An error occurred while updating employee!");
//	    }
		insertEmployee();
	}
	
	private void showErrorMessage(String message) {
	    Alert alert = new Alert(Alert.AlertType.ERROR);
	    alert.setTitle("Error");
	    alert.setHeaderText(null);
	    alert.setContentText(message);
	    alert.showAndWait();
	}

}
