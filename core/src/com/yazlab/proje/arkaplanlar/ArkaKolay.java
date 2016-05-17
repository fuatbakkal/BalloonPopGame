package com.yazlab.proje.arkaplanlar;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public final class ArkaKolay extends Actor {
    public Texture texture;

    public ArkaKolay() {
        super();
        texture = new Texture("arka_kolay.png");
        setTouchable(Touchable.disabled);
    }

    public void draw(Batch batch, float alpha) {
        batch.draw(texture, getX(), getY());
    }
}