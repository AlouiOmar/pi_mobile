/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.velo.gui;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.velo.gui.ProfileForm;
import com.velo.gui.SideMenuBaseForm;
import com.velo.entities.Annonce;
import java.util.ArrayList;
import com.velo.entities.Stat;
import com.velo.services.AnnonceService;
import com.velo.util.Vars;
import java.io.IOException;


/**
 *
 * @author Aloui Omar
 */
public class StatistiqueAnnonce extends SideMenuBaseForm{

    public StatistiqueAnnonce(Resources res) {
        super(new BorderLayout());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Image profilePic = res.getImage("stat.jpg");        
        Image tintedImage = Image.createImage(profilePic.getWidth(), profilePic.getHeight());
        Graphics g = tintedImage.getGraphics();
        g.drawImage(profilePic, 0, 0);
        g.drawImage(res.getImage("gradient-overlay.png"), 0, 0, profilePic.getWidth(), profilePic.getHeight());
        
        tb.getUnselectedStyle().setBgImage(tintedImage);
        
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Button settingsButton = new Button("Retour");
        settingsButton.setUIID("Title");
        FontImage.setMaterialIcon(settingsButton, FontImage.MATERIAL_ARROW_BACK_IOS);
        settingsButton.addActionListener(e ->{ Vars.current_choice=1;new AfficherListeAnnonces(res).show();});
        
        Label space = new Label("", "TitlePictureSpace");
        space.setShowEvenIfBlank(true);
        Container titleComponent = 
                BorderLayout.north(
                    BorderLayout.west(menuButton).add(BorderLayout.EAST, settingsButton)
                ).
                add(BorderLayout.CENTER, space).
                add(BorderLayout.SOUTH, 
                        FlowLayout.encloseIn(
                                new Label("  Statistique des ", "WelcomeBlue"),
                                new Label("Annonces", "WelcomeWhite")
                        ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
        
        Label separator = new Label("", "BlueSeparatorLine");
        separator.setShowEvenIfBlank(true);
        add(BorderLayout.NORTH, separator);
        

       //#####begin
//        AnnonceService as = new AnnonceService();
        ArrayList<Stat> listStat1 =AnnonceService.getInstance().getStatGouv();
        ArrayList<Stat> listStat2 =AnnonceService.getInstance().getStatType();
        ArrayList<Stat> listStat3 =AnnonceService.getInstance().getStatSignCat();
        ArrayList<Stat> listStat4 =AnnonceService.getInstance().getStatSignCause();
        ChartComponent c1=createPieChartForm(listStat1);
        ChartComponent c2=createPieChartForm(listStat2);
        ChartComponent c3=createPieChartForm(listStat3);
        ChartComponent c4=createPieChartForm(listStat4);
        Container cont=new Container(BoxLayout.y());
        Container cont1=new Container(BoxLayout.y());
        Container cont2=new Container(BoxLayout.y());
        Container cont3=new Container(BoxLayout.y());
        Container cont4=new Container(BoxLayout.y());
        Label ld1=new Label("Statistique des annonces publiées par gouvernorat");
        Label ld2=new Label("Statistique des annonces publiées par type");
        Label ld3=new Label("Statistique des annonces signalées par catégorie");
        Label ld4=new Label("Statistique des annonces signalées par cause");
        ld1.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
        ld2.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
        ld3.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
        ld4.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
        cont1.add(BoxLayout.encloseXCenter(ld1));
        cont2.add(BoxLayout.encloseXCenter(ld2));
        cont3.add(BoxLayout.encloseXCenter(ld3));
        cont4.add(BoxLayout.encloseXCenter(ld4));
        cont.addAll(cont1);
        cont.addComponent(c1);        
        cont.addAll(cont2);
        cont.addComponent(c2);        
        cont.addAll(cont3);
        cont.addComponent(c3);
        cont.addAll(cont4);
        cont.addComponent(c4);
        cont.setScrollableY(true);
        add(BorderLayout.CENTER,cont);
       //#####end

        
        
        
        
        
        
        
        
        setupSideMenu(res);
    }


    
    
     private DefaultRenderer buildCategoryRenderer(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(15);
        renderer.setLegendTextSize(15);
        renderer.setMargins(new int[]{20, 30, 15, 0});
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }

    /**
     * Builds a category series using the provided values.
     *
     * @param titles the series titles
     * @param values the values
     * @return the category series
     */
    protected CategorySeries buildCategoryDataset(String title, ArrayList<Stat> stats) {
        CategorySeries series = new CategorySeries(title);
        int k = 0;
        for (Stat stat : stats) {
            series.add(stat.getNom(), stat.getNbr());
        }

        return series;
    }

    public ChartComponent createPieChartForm(ArrayList<Stat> listStat) {

        // Generate the values
//        double[] values = new double[]{12, 14, 11, 10, 19};
//        AnnonceService as = new AnnonceService();
//        ArrayList<Stat> listStat =as.getStatGouv();
//        ;
        

        // Set up the renderer
        int[] colorList=new int[]{ColorUtil.rgb(255, 0, 0), ColorUtil.rgb(255, 128, 0), ColorUtil.rgb(255, 191, 0), ColorUtil.rgb(51, 51, 255), ColorUtil.rgb(204, 51, 255), ColorUtil.rgb(0, 255, 255), ColorUtil.rgb(255, 51, 102), ColorUtil.rgb(153, 51, 255), ColorUtil.rgb(51, 102, 255), ColorUtil.rgb(51, 255, 153), ColorUtil.rgb(250, 153, 56), ColorUtil.rgb(51, 255, 51), ColorUtil.rgb(255, 255, 51), ColorUtil.rgb(204, 255, 51), ColorUtil.rgb(51, 255, 204), ColorUtil.rgb(255, 51, 102), ColorUtil.rgb(97, 209, 125), ColorUtil.rgb(0, 179, 45), ColorUtil.rgb(204, 0, 0), ColorUtil.rgb(122, 92, 214), ColorUtil.rgb(0, 0, 179), ColorUtil.rgb(219, 87, 87), ColorUtil.rgb(240, 66, 240), ColorUtil.rgb(240, 240, 66)};
        int[] colors = new int[listStat.size()];
        for(int i=0;i<listStat.size();i++){
            colors[i]=colorList[i];
        }
        DefaultRenderer renderer = buildCategoryRenderer(colors);
        renderer.setZoomButtonsVisible(true);
        renderer.setZoomEnabled(true);
        renderer.setChartTitleTextSize(20);
        renderer.setLabelsColor(ColorUtil.BLACK);
        renderer.setDisplayValues(true);
        renderer.setShowLabels(true);
//        SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
//        r.setGradientEnabled(true);
//        r.setGradientStart(0, ColorUtil.BLUE);
//        r.setGradientStop(0, ColorUtil.GREEN);
//        r.setHighlighted(true);

        // Create the chart ... pass the values and renderer to the chart object.
        PieChart chart = new PieChart(buildCategoryDataset("Stat gouv", listStat), renderer);

        // Wrap the chart in a Component so we can add it to a form
        ChartComponent c = new ChartComponent(chart);

//        // Create a form and show it.
//        Form f = new Form("Budget");
//        f.setLayout(new BorderLayout());
//        Label ld=new Label("Stat");
//        Container cc=new Container(BoxLayout.y());
//        cc.addAll(ld,c);
////        f.addComponent(BorderLayout.CENTER, ld);
//        f.addComponent(BorderLayout.CENTER, cc);
        //a

        return c;
    }
    
    @Override
    protected void showOtherForm(Resources res) {
        new ProfileForm(res).show();
    }
   

}
