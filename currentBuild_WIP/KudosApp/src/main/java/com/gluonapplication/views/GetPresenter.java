package com.gluonapplication.views;
// Give a Gift Controller
// Shows List of Approved Gifts related to user names. 
// Allows user to Select one.

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
import java.util.ArrayList;
import java.util.Collection;
import com.gluonhq.charm.glisten.animation.BounceInUpTransition;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.mvc.View;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class GetPresenter {

    @FXML
    private View get;
    @FXML
	private Label errLabel;
	@FXML
	TableView<String> giftTable = new TableView<>();
	@FXML
	TableColumn<String, String> brandCol = new TableColumn<>();
	@FXML
	TableColumn<String, String> itemCol = new TableColumn<>();
	String myName = LoginPresenter.loggedInName;
	public static String selectedItem ="";
	public static String brandNameOfItem ="";
	public static String myGenCode = "";
	
	public void initialize() { 
    	get.setShowTransitionFactory(BounceInUpTransition::new);
        get.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
            	fillItemCol();
            }});
        giftTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
            	selectedItem = giftTable.getSelectionModel().selectedItemProperty().getValue();
            	errLabel.setText("Item Selected: "+selectedItem);
            }
        }); 
	}
// Get Approved Gifts from Temp Table with Logged in Name
	@SuppressWarnings("unchecked")
	private String fillItemCol(){
		String login_url = "http://kudosapp.org/kudosLibb/giftsByName.php";
		try {
			URL url = new URL(login_url);
			HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setDoInput(true);
			OutputStream outputStream = httpURLConnection.getOutputStream();
			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
			String post_data = URLEncoder.encode("value","UTF-8")+"="+URLEncoder.encode(myName,"UTF-8");
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
			Collection<String> list = new ArrayList<>();
			String[] splitted = result.split("\\.");
			for(int i=0; i < splitted.length;i++){
			    list.add(splitted[i]);
			}
	        ObservableList<String> details = FXCollections.observableArrayList(list);
	        giftTable.getColumns().clear();
	        giftTable.getColumns().addAll(itemCol);
	        itemCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
	        giftTable.setItems(details);
			return result;
			} catch (MalformedURLException e) {
				e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
					}
		return null;
	}
// Use item name to get brand name. (brandNameOfItem )
	private String getSelectedBrandName(){
	String login_url = "http://kudosapp.org/kudosLibb/getThatBrand.php";
	try {
		URL url = new URL(login_url);
		HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
		httpURLConnection.setRequestMethod("POST");
		httpURLConnection.setDoOutput(true);
		httpURLConnection.setDoInput(true);
		OutputStream outputStream = httpURLConnection.getOutputStream();
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
		String post_data = URLEncoder.encode("item_name","UTF-8")+"="+URLEncoder.encode(selectedItem,"UTF-8");
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
		brandNameOfItem = result;
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
// Gets the Shared Code 
	private String getCode(){
		String login_url = "http://kudosapp.org/kudosLibb/pickUpGet.php";
		try {
			URL url = new URL(login_url);
			HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setDoInput(true);
			OutputStream outputStream = httpURLConnection.getOutputStream();
			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
			String post_data = URLEncoder.encode("value","UTF-8")+"="+URLEncoder.encode(selectedItem,"UTF-8")
			+"&" +URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(myName,"UTF-8");
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
			myGenCode = String.valueOf(result);
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
// Removes the Entry from Temp Table
	private String removeEntry(){
		String login_url = "http://kudosapp.org/kudosLibb/removeGift.php";
		try {
			URL url = new URL(login_url);
			HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setDoInput(true);
			OutputStream outputStream = httpURLConnection.getOutputStream();
			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
			String post_data = URLEncoder.encode("item_name","UTF-8")+"="+URLEncoder.encode(selectedItem,"UTF-8")
			+"&" +URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(myName,"UTF-8")
			+"&" +URLEncoder.encode("code","UTF-8")+"="+URLEncoder.encode(myGenCode,"UTF-8");
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
// Go to Item Info Page
	@FXML
	private void goToNextPage(){
		if(!selectedItem.equals("")){
			getSelectedBrandName();
			getCode();
			removeEntry();
		MobileApplication.getInstance().switchView("Pick View");
		}
		if(selectedItem.equals("")){
			errLabel.setText("Please Select Item");
		}
		}
}
