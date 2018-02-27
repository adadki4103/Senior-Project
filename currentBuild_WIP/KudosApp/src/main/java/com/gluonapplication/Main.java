package com.gluonapplication;

import com.gluonapplication.views.LandingView;
import com.gluonapplication.views.LoginView;
import com.gluonapplication.views.WelcomeView;
import com.gluonapplication.views.RegisterView;
import com.gluonapplication.views.GiveView;
import com.gluonapplication.views.PayView;
import com.gluonapplication.views.SentView;
import com.gluonapplication.views.GetView;
import com.gluonapplication.views.BrandView;
import com.gluonapplication.views.ResolveView;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.layout.layer.SidePopupView;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.Swatch;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends MobileApplication {

    public static final String LANDING_VIEW = HOME_VIEW;
    public static final String LAND_VIEW = "Landing View";
    public static final String LOGIN_VIEW = "Login View";
    public static final String WELCOME_VIEW = "Welcome View";
    public static final String REGISTER_VIEW = "Register View";
    public static final String GIVE_VIEW = "Give View";
    public static final String PAY_VIEW = "Pay View";
    public static final String SENT_VIEW = "Sent View";
    public static final String GET_VIEW = "Get View";
    public static final String BRAND_VIEW = "Brand View";
    public static final String RESOLVE_VIEW = "Resolve View";
    public static final String MENU_LAYER = "Side Menu";
    
    @Override
    public void init() {
        addViewFactory(LANDING_VIEW, () -> (View) new LandingView().getView());
        addViewFactory(LAND_VIEW, () -> (View) new LandingView().getView());
        addViewFactory(LOGIN_VIEW, () -> (View) new LoginView().getView());
        addViewFactory(REGISTER_VIEW, () -> (View) new RegisterView().getView());
        addViewFactory(WELCOME_VIEW, () -> (View) new WelcomeView().getView());
        addViewFactory(GIVE_VIEW, () -> (View) new GiveView().getView());
        addViewFactory(PAY_VIEW, () -> (View) new PayView().getView());
        addViewFactory(SENT_VIEW, () -> (View) new SentView().getView());
        addViewFactory(GET_VIEW, () -> (View) new GetView().getView());
        addViewFactory(BRAND_VIEW, () -> (View) new BrandView().getView());
        addViewFactory(RESOLVE_VIEW, () -> (View) new ResolveView().getView());
        
        addLayerFactory(MENU_LAYER, () -> new SidePopupView(new DrawerManager().getDrawer()));
        
 
    }

    @Override
    public void postInit(Scene scene) {
    	/*@FXML private AnchorPane ap;
    	Stage stage = (Stage) ap.getScene().getWindow();*/
        Swatch.BLUE.assignTo(scene);
        ((Stage) scene.getWindow()).getIcons().add(new Image(Main.class.getResourceAsStream("/icon.png")));
    }
}
