package com.gluonapplication.views;
// Selected Gift Controller
// Shows information for previously selected item

import com.gluonhq.charm.glisten.animation.BounceInUpTransition;
import com.gluonhq.charm.glisten.mvc.View;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PickPresenter {
    @FXML
    private View pick;
    @FXML
	private Label brandLabel;
	@FXML
	private Label itemLabel;
	@FXML
	private Label codeLabel;

	String myName = LoginPresenter.loggedInName;
	String myItem = GetPresenter.selectedItem;
	String theItemBrand = GetPresenter.brandNameOfItem;
	String myItemCode = GetPresenter.myGenCode;
	public void initialize() { 
    	pick.setShowTransitionFactory(BounceInUpTransition::new);
        pick.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
            //	System.out.println("Logged in as: "+myName);
            //	System.out.println("Brand of Item: "+theItemBrand);
            //	System.out.println("Item Redeemed Name: "+myItem);
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
