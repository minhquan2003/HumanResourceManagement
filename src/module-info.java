module QuanLyNhanSuCongTy {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.sql;
	requires javafx.base;
	requires org.apache.poi.poi;
	requires org.apache.poi.ooxml;
	
	opens application to javafx.graphics, javafx.controls, javafx.fxml;
    opens Model to javafx.base;
    
    exports application to javafx.graphics, javafx.controls, javafx.fxml;
}