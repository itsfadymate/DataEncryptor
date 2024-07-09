module FileCrypt {
	requires javafx.base;
	requires javafx.fxml;
	requires javafx.controls;
	requires javafx.graphics;
	exports application to javafx.graphics,javafx.fxml;
	opens application to javafx.fxml;
}