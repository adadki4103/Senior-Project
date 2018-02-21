package com.KudosApp.views;

// Redeemed Gift Info
import com.gluonhq.charm.glisten.animation.BounceInUpTransition;
import com.gluonhq.charm.glisten.mvc.View;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class TenthPresenter {
	@FXML
	private View primary;
	@FXML
	private Label brandLabel;
	@FXML
	private Label itemLabel;
	@FXML
	private Label codeLabel;

	String myName = SecondaryPresenter.loggedInName;
	String myItem = NinthPresenter.selectedItem;
	String theItemBrand = NinthPresenter.brandNameOfItem;
	String myItemCode = NinthPresenter.myGenCode;
	public void initialize() { 
    	primary.setShowTransitionFactory(BounceInUpTransition::new);
        primary.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
            	System.out.println("Logged in as: "+myName);
            	System.out.println("Brand of Item: "+theItemBrand);
            	System.out.println("Item Redeemed Name: "+myItem);
            	brandLabel.setText(theItemBrand);
            	itemLabel.setText(myItem);
            	codeLabel.setText(myItemCode);
            }});
	}
    @FXML
    private void goBack(){
    	Platform.exit();
    }
	
}


