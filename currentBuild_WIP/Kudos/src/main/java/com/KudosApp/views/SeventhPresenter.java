package com.KudosApp.views;

import com.gluonhq.charm.glisten.animation.BounceInUpTransition;

// Payment Page 

import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.KudosApp.Main;
import com.gluonhq.charm.glisten.mvc.View;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import com.gluonhq.charm.glisten.control.AppBar;

public class SeventhPresenter {
	@FXML
	private View primary;
	@FXML
	private Label errorLabel1; //item
	@FXML
	private Label errorLabel2; //name
	//String itemName, nameName;
  
    public void initialize() {

    	//errorLabel1.setText(String.valueOf(itemName));
    	//errorLabel2.setText(String.valueOf(nameName));
    	
    	primary.setShowTransitionFactory(BounceInUpTransition::new);
    	
        primary.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> 
                        MobileApplication.getInstance().showLayer(Main.MENU_LAYER)));
                appBar.setTitleText("Welcome");
               /* appBar.getActionItems().add(MaterialDesignIcon.FAVORITE.button(e -> 
                        System.out.println("Favorite"))); */
            }});}

}