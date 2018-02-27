package com.gluonapplication.views;
// Give a Gift Controller

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
import com.gluonhq.charm.glisten.mvc.View;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ResolvePresenter {
	@FXML
	private View resolve;
	@FXML
	private Label welcomeLabel;
	@FXML
	private Label errLabel;
	@FXML
	TableView<String> giftTable = new TableView<>();
	@FXML
	TableColumn<String, String> brandCol = new TableColumn<>();
	@FXML
	TableColumn<String, String> itemCol = new TableColumn<>();
	public static String selectedItem ="";
	String myName = BrandPresenter.brandName;

	public void initialize() { 
		welcomeLabel.setText("Welcome "+myName);
    	resolve.setShowTransitionFactory(BounceInUpTransition::new);
        resolve.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
            	fillItemCol();
            }});
        giftTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
            	selectedItem = giftTable.getSelectionModel().selectedItemProperty().getValue();
            	errLabel.setText("ID Selected: "+selectedItem);
            }
       }); 
	}
	@SuppressWarnings("unchecked")
	private String fillItemCol(){
		String login_url = "http://kudosapp.org/kudosLibb/checkBrandUser3.php";
		try {
			URL url = new URL(login_url);
			HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setDoInput(true);
			OutputStream outputStream = httpURLConnection.getOutputStream();
			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
			String post_data = URLEncoder.encode("item_brand","UTF-8")+"="+URLEncoder.encode(myName,"UTF-8");
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
	@FXML
	private String denyItem(){
		String login_url = "http://kudosapp.org/kudosLibb/denyItem.php";
		try {
			URL url = new URL(login_url);
			HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setDoInput(true);
			OutputStream outputStream = httpURLConnection.getOutputStream();
			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
			String post_data = ("idPicked")+"="+(selectedItem);
			System.out.println("POST:" +post_data);
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
			fillItemCol();
			return result;
			} catch (MalformedURLException e) {
				e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
					}
		return null;
	}
	@FXML
	private String approveItem(){
		String login_url = "http://kudosapp.org/kudosLibb/approveItem.php";
		try {
			URL url = new URL(login_url);
			HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setDoInput(true);
			OutputStream outputStream = httpURLConnection.getOutputStream();
			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
			String post_data = ("idPicked")+"="+(selectedItem);
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
			fillItemCol();
			return result;
			} catch (MalformedURLException e) {
				e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
					}
		return null;
	}
}
