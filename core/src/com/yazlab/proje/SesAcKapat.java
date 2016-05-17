package com.yazlab.proje;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import static com.yazlab.proje.sabitler_globaller.Globaller.sesAcik;
import static com.yazlab.proje.sabitler_globaller.Sabitler.ekranGenisligi;

public final class SesAcKapat extends Actor {
    public Texture texture;

    public SesAcKapat() {
        super();

        if (sesAcik) {
            texture = new Texture("ses_acik.png");
        } else {
            texture = new Texture("ses_kapali.png");
        }

        setPosition(ekranGenisligi - 128, 0f);
        setBounds(getX(), getY(), texture.getWidth(), texture.getHeight());
        setTouchable(Touchable.enabled);
        addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                acKapat();
                return true;
            }
        });
    }

    public void draw(Batch batch, float alpha) {
        batch.draw(texture, getX(), getY());
    }

    private void acKapat() {
        if (!sesAcik) {
            sesAcik = true;
            texture = new Texture("ses_acik.png");
        } else {
            sesAcik = false;
            texture = new Texture("ses_kapali.png");
        }
    }
}