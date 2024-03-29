package com.yazlab.proje.pencereler;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.yazlab.proje.Oyun;
import com.yazlab.proje.OyunEkrani;

import static com.yazlab.proje.sabitler_globaller.Globaller.seviye;

/* Bölüm geçme koşulu sağlanınca çıkan pencere */
public class BolumTamamlandi extends BolumSonu {

    public BolumTamamlandi(final Oyun oyun, final OyunEkrani ekran) {
        super(seviye + ". Bölümü Tamamladınız");

        button("Sonraki Bölüme Geç", new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                ekran.dispose();
                oyun.setScreen(new OyunEkrani(oyun, ++seviye));
                return remove();
            }
        });
    }
}