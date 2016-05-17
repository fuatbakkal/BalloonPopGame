package com.yazlab.proje.pencereler;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.utils.Align;

import static com.yazlab.proje.sabitler_globaller.Globaller.bolumPuani;
import static com.yazlab.proje.sabitler_globaller.Globaller.patlatilanKirmizi;
import static com.yazlab.proje.sabitler_globaller.Globaller.patlatilanSari;
import static com.yazlab.proje.sabitler_globaller.Globaller.patlatilanSiyah;
import static com.yazlab.proje.sabitler_globaller.Globaller.patlatilanYesil;
import static com.yazlab.proje.sabitler_globaller.Globaller.seviye;
import static com.yazlab.proje.sabitler_globaller.Globaller.tema;
import static com.yazlab.proje.sabitler_globaller.Globaller.toplamPuan;

/* Bölüm sonunda çıkan pencerelerin üst sınıfı */
abstract class BolumSonu extends Pencere {
    private Image kirmiziBalon, sariBalon, yesilBalon, siyahBalon;
    private Label kirmiziPuan, sariPuan, yesilPuan, siyahPuan;
    private Stack kirmiziStack, sariStack, yesilStack, siyahStack;

    public BolumSonu(String title) {
        super(title);

        kirmiziBalon = new Image(new Texture("kirmizi_balon.png"));
        kirmiziPuan = new Label(Integer.toString(patlatilanKirmizi * 10), tema);
        kirmiziPuan.setAlignment(Align.center);
        kirmiziStack = new Stack();
        kirmiziStack.addActor(kirmiziBalon);
        kirmiziStack.addActor(kirmiziPuan);
        getContentTable().add(kirmiziStack);

        getContentTable().add("+");

        sariBalon = new Image(new Texture("sari_balon.png"));
        sariPuan = new Label(Integer.toString(patlatilanSari * 20), tema);
        sariPuan.setAlignment(Align.center);
        sariStack = new Stack();
        sariStack.addActor(sariBalon);
        sariStack.addActor(sariPuan);
        getContentTable().add(sariStack);

        getContentTable().add("+");

        yesilBalon = new Image(new Texture("yesil_balon.png"));
        yesilPuan = new Label(Integer.toString(patlatilanYesil * 5), tema);
        yesilPuan.setAlignment(Align.center);
        yesilStack = new Stack();
        yesilStack.addActor(yesilBalon);
        yesilStack.addActor(yesilPuan);
        getContentTable().add(yesilStack);

        getContentTable().add("-");

        siyahBalon = new Image(new Texture("siyah_balon.png"));
        siyahPuan = new Label(Integer.toString(patlatilanSiyah * 10), tema);
        siyahPuan.setAlignment(Align.center);
        siyahStack = new Stack();
        siyahStack.addActor(siyahBalon);
        siyahStack.addActor(siyahPuan);
        getContentTable().add(siyahStack);

        getButtonTable().row();
        getButtonTable().add(seviye + ". Bölüm Puanı: " + bolumPuani);
        getButtonTable().row();
        getButtonTable().add("Toplam Puan: " + toplamPuan);

        getButtonTable().row();
    }
}