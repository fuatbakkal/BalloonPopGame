package com.yazlab.proje;

import com.badlogic.gdx.Game;
import com.yazlab.proje.sabitler_globaller.Tema;

import static com.yazlab.proje.sabitler_globaller.Globaller.tema;

public class Oyun extends Game {

    public void create() {
        tema = new Tema();
        setScreen(new MenuEkrani(this));
    }
}