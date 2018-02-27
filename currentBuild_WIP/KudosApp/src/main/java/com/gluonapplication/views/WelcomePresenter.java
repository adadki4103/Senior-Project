package com.gluonapplication.views;

import com.gluonhq.charm.glisten.animation.BounceInUpTransition;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.gluonapplication.Main;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
// Welcome Page Controller
// ( Where User lands after successfull Login)
public class WelcomePresenter{

    @FXML
    private View welcome;
    @FXML
	private TextField userNameField;
	@FXML
	private PasswordField passWordField;

    public void initialize() {
      welcome.setShowTransitionFactory(BounceInUpTransition::new);
        welcome.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();
                appBar.setVisible(true);
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> 
                        MobileApplication.getInstance().showLayer(Main.MENU_LAYER)));
                appBar.setTitleText("Welcome");
            }
        });
    }
// Go to Give Gift
	@FXML
	void goGiveGift(){
		MobileApplication.getInstance().switchView("Give View");
	}
// Go to Get Gift
	@FXML
	void goGetGift(){
		MobileApplication.getInstance().switchView("Get View");
	}
    
   
}
