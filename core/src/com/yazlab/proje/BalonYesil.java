package com.yazlab.proje;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import static com.yazlab.proje.Sabitler.*;

public class BalonYesil extends Actor {
    public Texture texture;
    public int hiz;
    public int baslangicX;
    public float zaman;

    public BalonYesil() {
        super();
        texture = new Texture("yesil_balon.png");
        zaman = 0;
        hiz = 700;
        baslangicX = MathUtils.random(0, ekranGenisligi - balonGenisligi);
        setX(baslangicX);
        setBounds(getX(), getY(), texture.getWidth(), texture.getHeight());
        setTouchable(Touchable.enabled);

        // Balon patlatılırsa ekrandan kaldır ve puan ekle
        addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                puan += 5;
                patlatilanYesil++;
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