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
import static com.yazlab.proje.sabitler_globaller.Globaller.patlatilanKirmizi;
import static com.yazlab.proje.sabitler_globaller.Globaller.sesAcik;
import static com.yazlab.proje.sabitler_globaller.Globaller.zorluk;
import static com.yazlab.proje.sabitler_globaller.Sabitler.balonGenisligi;
import static com.yazlab.proje.sabitler_globaller.Sabitler.balonYuksekligi;

public class KirmiziBalon extends Actor {
    private Texture texture;
    private float hiz;
    private float genlik;
    private float salinim;
    private float baslangicY;
    private int baslangicX;
    private float zaman;

    public KirmiziBalon() {
        super();
        zaman = 0;
        texture = new Texture("kirmizi_balon.png");
        hiz = 320 * MathUtils.random(0.5f, 2.0f) * zorluk;
        genlik = 75 * MathUtils.random(0.5f, 2.0f);
        salinim = 0.01f * MathUtils.random(0.5f, 2.0f);
        baslangicY = MathUtils.random(.5f, 5f) * balonYuksekligi;
        baslangicX = -balonGenisligi;
        setX(baslangicX);
        setBounds(getX(), getY(), texture.getWidth(), texture.getHeight());
        setTouchable(Touchable.enabled);

        // Balon patlatılırsa ekrandan kaldır ve bolumPuani ekle
        addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                bolumPuani += 10;
                patlatilanKirmizi += 1;
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
        moveBy(delta, delta);
        zaman += delta;
        float x = hiz * zaman + baslangicX;
        float y = genlik * MathUtils.sin(salinim * x) + baslangicY;
        setPosition(x, y);
    }
}