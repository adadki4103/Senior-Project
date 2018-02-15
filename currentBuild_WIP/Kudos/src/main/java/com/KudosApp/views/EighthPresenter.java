package com.KudosApp.views;



//Payment Success Page

import com.gluonhq.charm.glisten.mvc.View;
//import com.gluonhq.charm.glisten.application.MobileApplication;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class EighthPresenter {
	@FXML
	private View primary;
    @FXML
    private Label label;

    public void initialize() {
    	primary.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
            }});}
    @FXML
    private void goBack(){
    	Platform.exit();
    }
}




