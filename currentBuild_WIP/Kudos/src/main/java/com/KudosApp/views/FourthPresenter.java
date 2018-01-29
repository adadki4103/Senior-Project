package com.KudosApp.views;


import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.mvc.View;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


// Mock 'Home Page' 
public class FourthPresenter {
	@FXML
	private View primary;
    @FXML
    private Label label;
    
    public void initialize() {
        primary.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {}});}
  
    // Go to Login
    @FXML
    void buttonClick() {
    	MobileApplication.getInstance().switchView("Secondary View");
    }
    
    // Go to Registration
    @FXML
    void buttonClickTwo() {
        MobileApplication.getInstance().switchView("Third View");
    }
}