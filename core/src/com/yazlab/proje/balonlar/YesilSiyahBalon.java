package com.yazlab.proje.balonlar;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import static com.yazlab.proje.sabitler_globaller.Globaller.patlatilanSiyah;
import static com.yazlab.proje.sabitler_globaller.Globaller.patlatilanYesil;
import static com.yazlab.proje.sabitler_globaller.Globaller.puan;
import static com.yazlab.proje.sabitler_globaller.Sabitler.balonGenisligi;
import static com.yazlab.proje.sabitler_globaller.Sabitler.balonYuksekligi;

public class YesilSiyahBalon extends Actor {
    private Texture texture;
    private int hiz;
    private float baslangicX;
    private float zaman;
    private float gecenSure;
    private float donusumAraligi;
    private float puanDegeri;
    private boolean yesilMi;

    public YesilSiyahBalon() {
        super();
        yesilMi = MathUtils.randomBoolean(); //Yeşil ya da Siyah balon
        donustur();
        zaman = 0;
        hiz = 700;
        baslangicX = MathUtils.random(-0.05f, 3.85f);
        setX(baslangicX * balonGenisligi);
        setBounds(getX(), getY(), texture.getWidth(), texture.getHeight());
        gecenSure = 0f;
        donusumAraligi = MathUtils.random(0.5f, 2.0f);
        setTouchable(Touchable.enabled); //Dokunmayı etkinleştir

        // Balon patlatılırsa ekrandan kaldır ve puan ekle
        addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                puan += puanDegeri;

                if(yesilMi)
                    patlatilanYesil++;
                else
                    patlatilanSiyah++;

                return remove();
            }
        });
    }

    public void draw(Batch batch, float alpha) {
        batch.draw(texture, getX(), getY());
    }

    public void act(float delta) {
        super.act(delta);
        moveBy(delta, delta);
        zaman += delta;
        setY(hiz * zaman - balonYuksekligi);

        // donusumAraligi süresi sonunda balonu dönüştür
        gecenSure += delta;
        if (gecenSure > donusumAraligi) {
            donustur();
            gecenSure -= donusumAraligi;
        }
    }

    private void donustur() {
        // Balon yeşilse siyah yap
        if(yesilMi) {
            yesilMi = false;
            texture = new Texture("siyah_balon.png");
            puanDegeri = -10;
        }

        // Balon siyahsa yeşil yap
        else {
            yesilMi = true;
            texture = new Texture("yesil_balon.png");
            puanDegeri = 5;
        }
    }
}