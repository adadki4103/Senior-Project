package com.gluonapplication.views;
// Gift Sent Success Controller

import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import javafx.application.Platform;
import javafx.fxml.FXML;

public class SentPresenter {
    @FXML
    private View sent;
   
    public void initialize() {
        sent.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();
                appBar.setVisible(false);

            }});}
    // Completes Transaction. Exits Program
    @FXML
    private void goExit(){
    	Platform.exit();
    }
}
