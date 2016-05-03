package com.yazlab.proje;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Oyun extends Game {

    public SpriteBatch batch;
    public BitmapFont font;

    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        setScreen(new MenuEkrani(this));
    }

    public void render() {
        super.render();
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}