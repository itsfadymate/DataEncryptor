package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

public class UIController implements Initializable{
	@FXML
	private TextArea decryptionArea;

	@FXML
	private TextArea encryptionArea;
	private AES aes;
	private static final String KEY_PATH = "path.csv";
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.aes = new AES();
		try {
			aes.init(KEY_PATH);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void decrypt(ActionEvent event) {
		String decrypted;
		try {
			decrypted = aes.decrypt(decryptionArea.getText());
			decryptionArea.setText(decrypted);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	void encrypt(ActionEvent event) {
		try {
			String encrypted  = aes.encrypt(encryptionArea.getText());
			encryptionArea.setText(encrypted);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}



}
