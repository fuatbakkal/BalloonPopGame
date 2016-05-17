package com.yazlab.proje.pencereler;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.yazlab.proje.MenuEkrani;
import com.yazlab.proje.Oyun;
import com.yazlab.proje.OyunEkrani;

/* Oyun tamamlanınca çıkan pencere */
public class OyunTamamlandi extends BolumSonu {

    public OyunTamamlandi(final Oyun oyun, final OyunEkrani ekran) {
        super("Oyunu Tamamladınız");

        button("Ana Menüye Dön", new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                ekran.dispose();
                oyun.setScreen(new MenuEkrani(oyun));
                return remove();
            }
        });
    }
}