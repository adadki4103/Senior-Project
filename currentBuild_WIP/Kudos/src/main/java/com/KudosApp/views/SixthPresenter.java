package com.KudosApp.views;

// Get the textfield value and the selected item. Go to Payment screen
// --> Pass item seleceted, textfield userName to payment view. Generate code.
// Payment information, Item & code sent to brand email. 
// Error message if any textfield is blank. Send payment information to brand email. Generate code for item. insert reciever, sender, item, and code into 
// itemSent table for temp storage. 
// Alert User Item has been paid for. 
// Alert Receiver Item has been received. (( Recieve Item page checks ItemsSent Table for name.
//If found. show items. "Redeem button" removes item from Itemsent & Shows Code. 
// itemsSent and show generate code. 

import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.animation.BounceInRightTransition;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.mysql.jdbc.StringUtils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import com.KudosApp.Main;
import com.gluonhq.charm.glisten.mvc.View;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import com.gluonhq.charm.glisten.control.AppBar;

// Send a Gift Page
public class SixthPresenter {
	@FXML
	private View primary;
    @FXML
    private Label label;
    @FXML
    private ComboBox<String> brandCombo;
    @FXML
    private ComboBox<String> itemCombo;
	@FXML
	private TextField toSendName;
	@FXML
	private Label errorLabel;
	String r3;
	@FXML
	private Button sendItemButton;
    ObservableList<String> brandList = FXCollections.observableArrayList();
    ObservableList<String> itemList = FXCollections.observableArrayList();
    public String itemChosen = "nullItem";
    public String nameGiven = "nullName";
    public void initialize() {
    	primary.setShowTransitionFactory(BounceInRightTransition::new);
        primary.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
            	try {
					brandToCombo();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
                AppBar appBar = MobileApplication.getInstance().getAppBar();
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> 
                        MobileApplication.getInstance().showLayer(Main.MENU_LAYER)));
                appBar.setTitleText("Welcome");
               /* appBar.getActionItems().add(MaterialDesignIcon.FAVORITE.button(e -> 
                        System.out.println("Favorite"))); */
            }});}
    // Populate Brand Combo Box from DB
    private String brandToCombo() throws SQLException{
    	
    	String login_url = "http://kudosapp.org/kudosLibb/populateBrand.php";
		try {
			URL url = new URL(login_url);
			HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setDoInput(true);
			InputStream inputStream = httpURLConnection.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
			String result="";
			String line="";
			while((line = bufferedReader.readLine())!= null) {
				result += line;
				}
			    
			bufferedReader.close();
			inputStream.close();
			httpURLConnection.disconnect();
			String[] splitted = result.split("\\.");
			for(int i=0; i < splitted.length;i++){
				brandList.add(splitted[i]);
			}
			brandCombo.setItems(brandList);
			return result;
			} catch (MalformedURLException e) {
				e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
					}
		return null;
    }
    // Get brandCombo Selection. Use it to populate itemCombo
    @FXML
    private String getBrandBox() throws SQLException, NoSuchAlgorithmException{
    	itemCombo.getItems().clear();
		String value = brandCombo.getValue();
		String login_url = "http://kudosapp.org/kudosLibb/whatItem.php";
		try {
			URL url = new URL(login_url);
			HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setDoInput(true);
			OutputStream outputStream = httpURLConnection.getOutputStream();
			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
			String post_data = URLEncoder.encode("value","UTF-8")+"="+URLEncoder.encode(value,"UTF-8");
			bufferedWriter.write(post_data);
			bufferedWriter.flush();
			bufferedWriter.close();
			outputStream.close();
			InputStream inputStream = httpURLConnection.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
			String result="";
			String line="";
			while((line = bufferedReader.readLine())!= null) {
				result += line;
				}

			bufferedReader.close();
			inputStream.close();
			httpURLConnection.disconnect();
			String[] splitted = result.split("\\.");
			for(int i=0; i < splitted.length;i++){
				itemList.add(splitted[i]);
			}
			itemCombo.setItems(itemList);
			return result;
			
			} catch (MalformedURLException e) {
				e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
					}
		return null;
		}
    
    // Checks for User. Displays Error Message if they don't exist.
    @FXML
    private String checkForUser(){
    	String nameToCheck = toSendName.getText();
		String login_url = "http://kudosapp.org/kudosLibb/doesExist.php";
		try {
			URL url = new URL(login_url);
			HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setDoInput(true);
			OutputStream outputStream = httpURLConnection.getOutputStream();
			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
			String post_data = URLEncoder.encode("nameToCheck","UTF-8")+"="+URLEncoder.encode(nameToCheck,"UTF-8");
			bufferedWriter.write(post_data);
			bufferedWriter.flush();
			bufferedWriter.close();
			outputStream.close();
			InputStream inputStream = httpURLConnection.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
			String result="";
			String line="";
			while((line = bufferedReader.readLine())!= null) {
				result += line;
				}
			r3 = result;
			errorLabel.setText(String.valueOf(r3));
			boolean isMyComboBoxEmpty = itemCombo.getSelectionModel().isEmpty();
			boolean nameExist = false;
			String str = toSendName.getText();
			String noName ="User Name Does Not Exist";
			String yesName = "User Name Exist";
			String errorMessage = "Please Select an Item";
			if(StringUtils.isEmptyOrWhitespaceOnly(str) == true){
				errorMessage = "Name cannot be blank";
			}
			if(r3.equals(noName) || toSendName.getText().isEmpty()){
				nameExist = false;
				errorMessage = "User Name Does Not Exist";
			}
			else if(r3.equals(yesName)){
				nameExist = true;
			}
			if(isMyComboBoxEmpty == false && nameExist == true && r3.equals(yesName) && StringUtils.isEmptyOrWhitespaceOnly(str) == false){
				itemChosen = itemCombo.getValue(); 
				nameGiven = toSendName.getText();
				//System.out.println("Hitting");
				MobileApplication.getInstance().switchView("Seventh View");
			}
			else if(isMyComboBoxEmpty == true || nameExist == false){
				errorLabel.setText(String.valueOf(errorMessage));
				if(toSendName.getText().isEmpty()){
					errorMessage = "Please Enter a Name";
					errorLabel.setText(String.valueOf(errorMessage));}
			}
			bufferedReader.close();
			inputStream.close();
			httpURLConnection.disconnect();
			return result;
			
			} catch (MalformedURLException e) {
				e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
					}
		return null;
    }
}