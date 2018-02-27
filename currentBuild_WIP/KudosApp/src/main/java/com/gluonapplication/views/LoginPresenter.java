package com.gluonapplication.views;
// Login Page Controller

import com.gluonhq.charm.glisten.animation.BounceInUpTransition;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
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
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginPresenter {
    @FXML
    private View login;
    @FXML
	private TextField userNameField;
	@FXML
	private PasswordField passWordField;
	@FXML
	private Label testLabel;
	String r2;
	public String user_name;
	public String passwordEntered;
	public static String loggedInName;

    public void initialize() {
    	userNameField.setPromptText("User Name");
    	passWordField.setPromptText("Password");
    	login.setShowTransitionFactory(BounceInUpTransition::new);
        login.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();
                appBar.setVisible(true);
               /* appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> 
                        MobileApplication.getInstance().showLayer(Main.MENU_LAYER))); */
                appBar.setTitleText("Log In");
            }
        });
    }
    // Log in Check 
   	@FXML
   	String loginCheck() throws SQLException, NoSuchAlgorithmException {
   		String login_url = "http://kudosapp.org/kudosLibb/checkUser.php";
   		try {
   			user_name = userNameField.getText();
   			loggedInName = user_name;
   			passwordEntered = passWordField.getText();
   			URL url = new URL(login_url);
   			HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
   			httpURLConnection.setRequestMethod("POST");
   			httpURLConnection.setDoOutput(true);
   			httpURLConnection.setDoInput(true);
   			OutputStream outputStream = httpURLConnection.getOutputStream();
   			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
   			String post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")
   			+"&" +URLEncoder.encode("passwordEntered","UTF-8")+"="+URLEncoder.encode(passwordEntered,"UTF-8");
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
   			r2 = result;
   			bufferedReader.close();
   			inputStream.close();
   			httpURLConnection.disconnect();
   			if(r2.equals("Pass")){
   				MobileApplication.getInstance().switchView("Welcome View");
   				testLabel.setText("User Name & Pass Match.");
   			}
   			else if(r2.equals("Fail")){
   				// do something
   				testLabel.setText("No Match Found");
   			}
   			return result;
   			} catch (MalformedURLException e) {
   				e.printStackTrace();
   				} catch (IOException e) {
   					e.printStackTrace();
   					}
   		return null;
   		}
	// Go Back
	@FXML
	void goBack(){
		MobileApplication.getInstance().switchView("Landing View");
	}
}
