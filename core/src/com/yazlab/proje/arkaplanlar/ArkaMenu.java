package com.yazlab.proje.arkaplanlar;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public final class ArkaMenu extends Actor {
    public Texture texture;

    public ArkaMenu() {
        super();
        texture = new Texture("arka_menu.png");
        setPosition(0f, 0f);
        setTouchable(Touchable.disabled);
    }

    public void draw(Batch batch, float alpha) {
        batch.draw(texture, getX(), getY());
    }
}