package com.KudosApp.views;


import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.KudosApp.Main;
import com.gluonhq.charm.glisten.mvc.View;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import com.gluonhq.charm.glisten.control.AppBar;

// Mock 'Home Page' 
public class FifthPresenter {
	@FXML
	private View primary;
    @FXML
    private Label label;
    
    public void initialize() {
        primary.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> 
                        MobileApplication.getInstance().showLayer(Main.MENU_LAYER)));
                appBar.setTitleText("Welcome");
            }});}
    
    @FXML
    private void goGiveGift(){
    	MobileApplication.getInstance().switchView("Sixth View");
    }
}