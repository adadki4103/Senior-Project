package com.gluonapplication.views;
// Landing Page Controller
// ( Initial View )

import com.gluonhq.charm.down.Platform;
import com.gluonhq.charm.glisten.animation.BounceInDownTransition;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import javafx.fxml.FXML;

public class LandingPresenter {
    @FXML
    private View landing;
    public void initialize() {
    	landing.setShowTransitionFactory(BounceInDownTransition::new);
        landing.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();
                appBar.setVisible(false);
               /* appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> 
                        MobileApplication.getInstance().showLayer(Main.MENU_LAYER))); */
              //  appBar.setTitleText("Landing");
               /* appBar.getActionItems().add(MaterialDesignIcon.SEARCH.button(e -> 
                        System.out.println("Search"))); */
            }
        });
    }
    @FXML
    void goToLogin() {
    	MobileApplication.getInstance().switchView("Login View");
    }
    @FXML
    void goToRegister() {
    	MobileApplication.getInstance().switchView("Register View");
    }
    @FXML
    void goToBrandLogin() {
    	if(Platform.isDesktop()){
    	MobileApplication.getInstance().switchView("Brand View");
    }
    	}
}
