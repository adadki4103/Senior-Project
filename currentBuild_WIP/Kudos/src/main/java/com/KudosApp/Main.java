package com.KudosApp;

import com.KudosApp.views.PrimaryView;
import com.KudosApp.views.SecondaryView;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.layout.layer.SidePopupView;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.Swatch;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends MobileApplication {

    public static final String PRIMARY_VIEW = HOME_VIEW;
    public static final String SECONDARY_VIEW = "Secondary View";
    public static final String MENU_LAYER = "Side Menu";
    
    @Override
    public void init() {
        addViewFactory(PRIMARY_VIEW, () -> (View) new PrimaryView().getView());
        addViewFactory(SECONDARY_VIEW, () -> (View) new SecondaryView().getView());
        
        addLayerFactory(MENU_LAYER, () -> new SidePopupView(new DrawerManager().getDrawer()));
    }

    @Override
    public void postInit(Scene scene) {
        Swatch.YELLOW.assignTo(scene);

        
        ((Stage) scene.getWindow()).getIcons().add(new Image(Main.class.getResourceAsStream("/icon.png")));
    }
}
