package com.yazlab.proje;

import com.badlogic.gdx.Game;

public class Oyun extends Game {

    public void create() {
        setScreen(new MenuEkrani(this));
    }
}