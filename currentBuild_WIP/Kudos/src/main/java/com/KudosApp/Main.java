package com.KudosApp;

import com.KudosApp.views.PrimaryView;
import com.KudosApp.views.SecondaryView;
import com.KudosApp.views.ThirdView;
import com.KudosApp.views.FourthView;
import com.KudosApp.views.FifthView;
import com.KudosApp.views.SixthView;
import com.KudosApp.views.SeventhView;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.layout.layer.SidePopupView;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.Swatch;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends MobileApplication {

    public static final String PRIMARY_VIEW = HOME_VIEW;   // Landing Page
    public static final String SECONDARY_VIEW = "Secondary View"; // Log in
    public static final String THIRD_VIEW = "Third View";  // Registration
    public static final String FOURTH_VIEW = "Fourth View"; // Mock Landing 
    public static final String FIFTH_VIEW = "Fifth View"; // Mock Landing 
    public static final String SIXTH_VIEW = "Sixth View"; // Send a Gift Page
    public static final String SEVENTH_VIEW = "Seventh View"; // Payment Page
    public static final String MENU_LAYER = "Side Menu";
    
    @Override
    public void init() {
        addViewFactory(PRIMARY_VIEW, () -> (View) new PrimaryView().getView());
        addViewFactory(SECONDARY_VIEW, () -> (View) new SecondaryView().getView());
        addViewFactory(THIRD_VIEW, () -> (View) new ThirdView().getView());
        addViewFactory(FOURTH_VIEW, () -> (View) new FourthView().getView());
        addViewFactory(FIFTH_VIEW, () -> (View) new FifthView().getView());
        addViewFactory(SIXTH_VIEW, () -> (View) new SixthView().getView());
        addViewFactory(SEVENTH_VIEW, () -> (View) new SeventhView().getView());
        addLayerFactory(MENU_LAYER, () -> new SidePopupView(new DrawerManager().getDrawer()));
    }

    @Override
    public void postInit(Scene scene) {
        Swatch.BLUE.assignTo(scene);
        ((Stage) scene.getWindow()).getIcons().add(new Image(Main.class.getResourceAsStream("/icon.png")));
    }
}
