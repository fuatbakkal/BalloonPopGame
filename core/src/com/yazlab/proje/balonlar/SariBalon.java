package com.yazlab.proje.balonlar;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import static com.yazlab.proje.sabitler_globaller.Globaller.bolumPuani;
import static com.yazlab.proje.sabitler_globaller.Globaller.patlamaSesi;
import static com.yazlab.proje.sabitler_globaller.Globaller.patlatilanSari;
import static com.yazlab.proje.sabitler_globaller.Globaller.sesAcik;
import static com.yazlab.proje.sabitler_globaller.Sabitler.balonGenisligi;
import static com.yazlab.proje.sabitler_globaller.Sabitler.balonYuksekligi;
import static com.yazlab.proje.sabitler_globaller.Sabitler.ekranGenisligi;
import static com.yazlab.proje.sabitler_globaller.Sabitler.ekranYuksekligi;

public class SariBalon extends Actor {
    private Texture texture;
    private float baslangicY;
    private float baslangicX;
    private float zaman;

    public SariBalon() {
        super();
        zaman = 0;
        texture = new Texture("sari_balon.png");
        baslangicY = MathUtils.random(balonYuksekligi, ekranYuksekligi - balonYuksekligi * 2);
        baslangicX = MathUtils.random(balonGenisligi, ekranGenisligi - balonGenisligi * 2);
        setX(baslangicX);
        setY(baslangicY);
        setBounds(getX(), getY(), texture.getWidth(), texture.getHeight());
        setTouchable(Touchable.enabled);

        // Balon patlatılırsa ekrandan kaldır ve bolumPuani ekle
        addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                bolumPuani += 20;
                patlatilanSari++;
                if (sesAcik) {
                    patlamaSesi.play();
                }
                return remove();
            }
        });
    }

    public void draw(Batch batch, float alpha) {
        batch.draw(texture, getX(), getY());
    }

    public void act(float delta) {
        super.act(delta);
        zaman += delta;

        // 1 saniye sonra balonu kaldır
        if (zaman > 1f) {
            remove();
        }
    }
}