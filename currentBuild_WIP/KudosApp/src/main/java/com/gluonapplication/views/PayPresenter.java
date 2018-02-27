package com.gluonapplication.views;
// Pay Page Controller
// ( After Item Selected ) 

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
import java.util.Random;
import com.gluonhq.charm.glisten.animation.BounceInUpTransition;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class PayPresenter {
	@FXML
    private View pay;
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
	String r5 = "blankCode";
	//r2=brandName, r3=brandEmail, r4=itemPrice, r5=pickUpID
	String itemName = GivePresenter.giveItemName();
	String nameName = GivePresenter.giveRecName();
	String itemStr,nameStr;
	String sharedCode = String.valueOf(generateCode());;

    public void initialize() throws IOException { 
        getSelectedBrandName();
        getSelectedBrandEmail();
        getSelectedItemPrice();
        getSelectedPickUpID();
        itemStr = "Item: "+(String.valueOf(itemName));
        nameStr = "Receiver: "+(String.valueOf(nameName));
    	errorLabel.setText(String.valueOf(itemStr));
    	errorLabel1.setText(String.valueOf(nameStr));
    	credDate.setShowWeekNumbers(false);
    	credDate.setEditable(false);
    	credDate.setValue(LocalDate.now());
    	pay.setShowTransitionFactory(BounceInUpTransition::new);
        pay.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();
                appBar.setVisible(false);
            }});}
// Use item name to get brand name.
private String getSelectedBrandName(){
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
// Gets the ID # associated with the transaction in the tempTable from DB
private String getSelectedPickUpID(){
	// r5 becomes pickUpID
String login_url = "http://kudosapp.org/kudosLibb/getThatID.php";
try {
	URL url = new URL(login_url);
	HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
	httpURLConnection.setRequestMethod("POST");
	httpURLConnection.setDoOutput(true);
	httpURLConnection.setDoInput(true);
	OutputStream outputStream = httpURLConnection.getOutputStream();
	BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
	String post_data = URLEncoder.encode("theCode","UTF-8")+"="+URLEncoder.encode(sharedCode,"UTF-8");
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
	r5 = result;
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
// Use get brand name to get brandEmail
private String getSelectedBrandEmail(){
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
// Get the Price associated with the selected item from DB
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
// Generate Shared Random Code
private String generateCode(){
	Random generator = new Random();
    StringBuilder randomStringBuilder = new StringBuilder();
    char tempChar;
    for (int i = 0; i < 6; i++){
        tempChar = (char) (generator.nextInt(96) + 32);
        randomStringBuilder.append(tempChar);
    }
    return randomStringBuilder.toString();
}
// Send Item Brand, Item Name, Item Price, CredCard Information:(name, number, date, ccv number), & Shared Code
// to Brand Email of Selected Item.
// Also, Send receiver Name, Item Name, Shared Code to Temp Table ( For redeeming later ) to DB
// Brand name = r2. Brand email = r3. Item Price = r4 
// Name = itemName. pickUpID = r5 Receiver Name = nameName
private String sendEmail(){
	String sendCredName = credName.getText();
	String sendCredNum = credNum.getText();
	String sendCredDate = credDate.getValue().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
	String sendCredCCVNum = credCCVNum.getText();
	
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
		+"&" +URLEncoder.encode("recName","UTF-8")+"="+URLEncoder.encode(nameName,"UTF-8")
		+"&" +URLEncoder.encode("pickId","UTF-8")+"="+URLEncoder.encode(r5,"UTF-8");
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
			drpLabel.setTextFill(Color.RED);
			drpLabel.setText("Incorrect Input");
			}
		else if(credNum.getLength() < 19 && credNum.getLength() > 13 && credNum.getText().matches("[0-9]+") 
				&& credCCVNum.getText().matches("[0-9]+") && credCCVNum.getLength() < 4 && credNum.getLength() >= 3){
			drpLabel.setTextFill(Color.GREEN);
			drpLabel.setText("Correct Input");
			payLabel.setText("Payment Status: "+String.valueOf(mailSent));
			MobileApplication.getInstance().switchView("Sent View");
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
//Send Item Brand, Item Name, Item Price, CredCard Information.(name, number, date, ccv number), & Shared Code to DB.
private String insertDets(){
	String login_url = "http://kudosapp.org/kudosLibb/insertDets.php";
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
		+"&" +URLEncoder.encode("genCode","UTF-8")+"="+URLEncoder.encode(sharedCode,"UTF-8")
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
// Payment Button Action.
// Must remain in this order.
@FXML
private void buttonAction(){
	insertDets();
	getSelectedPickUpID();
	sendEmail();
}
}
