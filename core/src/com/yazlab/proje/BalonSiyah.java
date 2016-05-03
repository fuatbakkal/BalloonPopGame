package com.yazlab.proje;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import static com.yazlab.proje.Sabitler.balonGenisligi;
import static com.yazlab.proje.Sabitler.balonYuksekligi;
import static com.yazlab.proje.Sabitler.ekranGenisligi;

public class BalonSiyah extends Actor {
    public Texture texture;
    public int hiz;
    public int baslangicX;
    public float zaman;

    public BalonSiyah() {
        super();
        texture = new Texture("yesil_balon.png");
        zaman = 0;
        hiz = 700;
        baslangicX = MathUtils.random(0, ekranGenisligi - balonGenisligi);
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
        setY(hiz * zaman - balonYuksekligi);
    }
}