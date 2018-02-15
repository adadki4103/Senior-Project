package com.KudosApp.views;

// Payment Page

import com.gluonhq.charm.glisten.animation.BounceInUpTransition;
import com.gluonhq.charm.glisten.application.MobileApplication;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import com.gluonhq.charm.glisten.mvc.View;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SeventhPresenter {
	@FXML
	private View primary;
	@FXML
	private Label errorLabel; //item
	@FXML
	private Label errorLabel1; //name
	@FXML
	private Label errorLabel2; //price
	@FXML
	private TextField credName;
	@FXML
	private TextField credNum;
	@FXML
	private TextField credCCVNum;
	@FXML
	private DatePicker credDate;
	@FXML
	private Label payLabel;
	@FXML
	private Label drpLabel;
	
	String genCode ="b";
	String mailSent ="nopeFail";
	String brandName = "blankBrand";
	String itemPrice = "blankPrice";
	String r2,r3,r4;
	//brandName, brandEmail, itemPrice
	String itemName = "blankItem";
	String nameName = "blankName";
	String itemStr,nameStr;

    public void initialize() throws IOException {
        itemName = SixthPresenter.giveItemName();
        nameName = SixthPresenter.giveRecName();
        getSelectedBrandName();
        getSelectedBrandEmail();
        getSelectedItemPrice();
        generateCode();
        itemStr = "Item: "+(String.valueOf(itemName));
        nameStr = "Receiver: "+(String.valueOf(nameName));
    	errorLabel.setText(String.valueOf(itemStr));
    	errorLabel1.setText(String.valueOf(nameStr));
    	
    	credDate.setShowWeekNumbers(false);
    	credDate.setEditable(false);
    	credDate.setValue(LocalDate.now());
    	primary.setShowTransitionFactory(BounceInUpTransition::new);
        primary.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
            }});}
private String getSelectedBrandName(){
    	// Use item name to get brand name.
    	// r2 becomes brandName
	String login_url = "http://kudosapp.org/kudosLibb/getThatBrand.php";
	try {
		URL url = new URL(login_url);
		HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
		httpURLConnection.setRequestMethod("POST");
		httpURLConnection.setDoOutput(true);
		httpURLConnection.setDoInput(true);
		OutputStream outputStream = httpURLConnection.getOutputStream();
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
		String post_data = URLEncoder.encode("item_name","UTF-8")+"="+URLEncoder.encode(itemName,"UTF-8");
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
		return result;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				}
	return null;
	}  
private String getSelectedBrandEmail(){
	// Use get brand name to get brandEmail
	// r3 becomes brand email
	String login_url = "http://kudosapp.org/kudosLibb/getThatEmail.php";
	try {
		URL url = new URL(login_url);
		HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
		httpURLConnection.setRequestMethod("POST");
		httpURLConnection.setDoOutput(true);
		httpURLConnection.setDoInput(true);
		OutputStream outputStream = httpURLConnection.getOutputStream();
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
		String post_data = URLEncoder.encode("item_brand","UTF-8")+"="+URLEncoder.encode(r2,"UTF-8");
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
private String getSelectedItemPrice(){
	// r4 becomes item price
	String login_url = "http://kudosapp.org/kudosLibb/getThatPrice.php";
	try {
		URL url = new URL(login_url);
		HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
		httpURLConnection.setRequestMethod("POST");
		httpURLConnection.setDoOutput(true);
		httpURLConnection.setDoInput(true);
		OutputStream outputStream = httpURLConnection.getOutputStream();
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
		String post_data = URLEncoder.encode("item_price","UTF-8")+"="+URLEncoder.encode(itemName,"UTF-8");
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
		r4 = result;
		errorLabel2.setText("Item Price: "+String.valueOf(r4));
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
private String generateCode(){
    String uuid = UUID.randomUUID().toString();
    uuid.replace("-", "");
    return uuid;
}
@FXML
private String sendEmail(){
	// Send Item Brand. Item Name. Item Price. CredCard Information.(name, number, date, ccv number)  Gened Code. to Brand Email of Selected Item.
	// Send receiver Name. Item Name. Gen Code to Temp Table ( For redeeming later )
	// Brand name = r2.  
	//  Name = itemName.
	// Item Price = r4. 
	// Receiver Name = nameName
	String sendCredName = credName.getText();
	String sendCredNum = credNum.getText();
	String sendCredDate = credDate.getValue().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
	String sendCredCCVNum = credCCVNum.getText();
	String sharedCode = String.valueOf(generateCode());
	// Brand email = r3.
	String login_url = "http://kudosapp.org/kudosLibb/sendMail.php";
	try {
		URL url = new URL(login_url);
		HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
		httpURLConnection.setRequestMethod("POST");
		httpURLConnection.setDoOutput(true);
		httpURLConnection.setDoInput(true);
		OutputStream outputStream = httpURLConnection.getOutputStream();
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
		String post_data = URLEncoder.encode("itemBrand","UTF-8")+"="+URLEncoder.encode(r2,"UTF-8")
		+"&" +URLEncoder.encode("itemName","UTF-8")+"="+URLEncoder.encode(itemName,"UTF-8")
		+"&" +URLEncoder.encode("itemPrice","UTF-8")+"="+URLEncoder.encode(r4,"UTF-8")
		+"&" +URLEncoder.encode("credName","UTF-8")+"="+URLEncoder.encode(sendCredName,"UTF-8")
		+"&" +URLEncoder.encode("credNum","UTF-8")+"="+URLEncoder.encode(sendCredNum,"UTF-8")
		+"&" +URLEncoder.encode("credDate","UTF-8")+"="+URLEncoder.encode(sendCredDate,"UTF-8")
		+"&" +URLEncoder.encode("credCCV","UTF-8")+"="+URLEncoder.encode(sendCredCCVNum,"UTF-8")
		+"&" +URLEncoder.encode("genCode","UTF-8")+"="+URLEncoder.encode(sharedCode,"UTF-8")
		+"&" +URLEncoder.encode("brandEmail","UTF-8")+"="+URLEncoder.encode(r3,"UTF-8")
		+"&" +URLEncoder.encode("recName","UTF-8")+"="+URLEncoder.encode(nameName,"UTF-8");
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
		mailSent = result;;
		if(credNum.getLength() > 19 || credNum.getLength() < 13 || !credNum.getText().matches("[0-9]+") 
				|| !credCCVNum.getText().matches("[0-9]+") || credCCVNum.getLength() > 4 || credNum.getLength() <= 2){
			drpLabel.setText("Incorrect Input");
			}
		else if(credNum.getLength() < 19 && credNum.getLength() > 13 && credNum.getText().matches("[0-9]+") 
				&& credCCVNum.getText().matches("[0-9]+") && credCCVNum.getLength() < 4 && credNum.getLength() >= 3){
			drpLabel.setText("Correct Input");
			payLabel.setText("Payment Status: "+String.valueOf(mailSent));
			MobileApplication.getInstance().switchView("Eighth View");
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


