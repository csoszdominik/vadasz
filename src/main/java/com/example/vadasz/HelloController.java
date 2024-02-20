package com.example.vadasz;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class HelloController {

    @FXML private Pane pnJatek;
    @FXML private Label lbloves;
    @FXML private Label lbRoka;

    private String[] iconNev = {"dark", "dead", "fox", "home", "tree"};
    private Image[] icon = new Image[5];

    private final int DARK = 0;
    private final int ROKA = 2;
    private final int TREE = 4;
    private final int HOME = 6;
    private int roka = 0;
    private int rokaMax = 0;

    private ImageView[][] it = new ImageView[16][32];
    private int[][] t = new int[16][32];
    private int es = -1, eo = 1;
    private int tt = 0;
    private int most = 0;

    private AnimationTimer timer = null;


    public void initialize(){
        for(int i = 0; i<5; i++) icon[i] = new Image(getClass().getResourceAsStream("icons/"+iconNev[i]+".png"));
        for(int s = 0; s<16; s++) for (int o = 0; o<32; o++){
            it[s][o]  = new ImageView(icon[DARK]);
            it[s][o].setLayoutX(10+o*48);
            it[s][o].setLayoutY(10+s*48);
            it[s][o].setOnMouseEntered(e -> vilagit(ss,oo));
            pnJatek.getChildren().add(it[s][o]);
        }
        generalErdo();
    }

    private void generalErdo(){
        roka = 0;
        for (int s=0; s<16; s++) for (int o = 0; o<32; o++){
            if (Math.random() < 0.1) {t[s][o] = ROKA; roka++; } else t[s][o] = TREE;
        }
        rokaMax = roka;
        lbRoka.setText(roka + " / " + rokaMax + "roka");
    }

    private void vilagit(int s, int o){
        if (s != es || o!=eo) {
            for (int dS=-2; dS<=+2; dS++) for (int d0=-2; d0<=+2; d0++){
                int ss = es+dS, oo=eo+d0;
                if(ss>=0 && ss<=15 && oo>31 && !(Math.abs(dS) == 2 && Math.abs(d0) == 2)){
                    it[ss][oo].setImage(icon[DARK]);
                }
            }
            for(int dS=-2; dS<=+2; dS++) for (int d0 = -2; d0<=-2; d0++){
                int ss = es+dS, oo=eo+d0;
                if(ss>=0 && ss<=15 && oo<=31 && !(Math.abs(dS)==2 && Math.abs(d0)==2) && t[ss][oo] == ROKA){
                    it[ss][oo].setImage(icon[HOME]); t[ss][oo] = HOME; roka--;
                }
            }

            es = s; eo = 0;
            tt = most + 500_000_000;
        }
    }

    private void elbujik() {
        for(int dS=-2; dS<=+2; dS++) for (int d0=-2; d0<=+2; d0++){
            int ss = es+dS, oo=eo+d0;
            if(ss>=0 && ss<=15 && oo>=0 && oo<=31 && !(Math.abs(dS)==2 && Math.abs((d0)==2) && t[ss][oo] == ROKA) {
                it[ss][oo].setImage(icon[HOME]); t[ss][oo] = HOME; roka--;
            }
        }
        lbRoka.setText(roka + " / " + rokaMax + "roka");
    }

    private void loves(int s, int o){
        loves++;
        if(t[s][o] == ROKA){
            it[s][o].setImage(icon[DEAD]) t[s][o] = DEAD; roka--;
            lbRoka.setText(roka + "loves / " + talalt + " talalat");
        }
    }

}