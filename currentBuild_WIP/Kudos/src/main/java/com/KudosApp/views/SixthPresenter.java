package com.KudosApp.views;


import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
    ObservableList<String> brandList = FXCollections.observableArrayList();
    ObservableList<String> itemList = FXCollections.observableArrayList();
    public void initialize() {
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
		// Error message if textfield is blank. Get the textfield value and the selected item. Go to Payment screen. 
		// Error message if any textfield is blank. Send payment information to brand email. Generate code for item. insert reciever, sender, item, and code into 
		// itemSent table for temp storage. 
		// Alert User Item has been paid for. 
		// Alert Receiver Item has been received. (( Recieve Item page checks ItemsSent Table for name. If found. show items. "Redeem button" removes
		// itemsSent and show generate code. 
		
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
    
    
}