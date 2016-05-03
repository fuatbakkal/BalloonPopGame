package com.yazlab.proje;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import static com.yazlab.proje.Sabitler.balonGenisligi;
import static com.yazlab.proje.Sabitler.balonYuksekligi;
import static com.yazlab.proje.Sabitler.ekranYuksekligi;

// Deneme commit
public class BalonKirmizi extends Actor {
    public Texture texture;
    public float hiz;
    public float genlik;
    public float salinim;
    public float baslangicY;
    public int baslangicX;
    public float zaman;

    public BalonKirmizi() {
        super();
        zaman = 0;
        texture = new Texture("kirmizi_balon.png");
        hiz = 320 * MathUtils.random(0.5f, 2.0f);
        genlik = 75 * MathUtils.random(0.5f, 2.0f);
        salinim = 0.01f * MathUtils.random(0.5f, 2.0f);
        baslangicY = MathUtils.random(0, ekranYuksekligi / balonYuksekligi - 2) * balonYuksekligi;
        baslangicX = -balonGenisligi * 2;
        setX(baslangicX);
        setBounds(getX(), getY(), texture.getWidth(), texture.getHeight());

        // Balon patlatılırsa ekrandan kaldır ve puan ekle
        addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
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