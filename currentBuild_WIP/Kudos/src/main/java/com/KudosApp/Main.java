package com.KudosApp;

import com.KudosApp.views.PrimaryView;
import com.KudosApp.views.SecondaryView;
import com.KudosApp.views.ThirdView;
import com.KudosApp.views.FourthView;
import com.KudosApp.views.FifthView;
import com.KudosApp.views.SixthView;
import com.KudosApp.views.SeventhView;
import com.KudosApp.views.EighthView;
import com.KudosApp.views.NinthView;
import com.KudosApp.views.TenthView;
import com.KudosApp.views.EleventhView;
import com.KudosApp.views.TwelthView;
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
    public static final String EIGHTH_VIEW = "Eighth View"; // Gift Has Been Sent Page
    public static final String NINTH_VIEW = "Ninth View"; // Redeem a gift
    public static final String TENTH_VIEW = "Tenth View"; // Redeemed Gift Info
    public static final String ELEVENTH_VIEW = "Eleventh View"; // Brand User Log In
    public static final String TWELTH_VIEW = "Twelth View"; // Brand User Approve/Deny Page
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
        addViewFactory(EIGHTH_VIEW, () -> (View) new EighthView().getView());
        addViewFactory(NINTH_VIEW, () -> (View) new NinthView().getView());
        addViewFactory(TENTH_VIEW, () -> (View) new TenthView().getView());
        addViewFactory(ELEVENTH_VIEW, () -> (View) new EleventhView().getView());
        addViewFactory(TWELTH_VIEW, () -> (View) new TwelthView().getView());
        addLayerFactory(MENU_LAYER, () -> new SidePopupView(new DrawerManager().getDrawer()));
    }

    @Override
    public void postInit(Scene scene) {
        Swatch.BLUE.assignTo(scene);
        ((Stage) scene.getWindow()).getIcons().add(new Image(Main.class.getResourceAsStream("/icon.png")));
    }
}
