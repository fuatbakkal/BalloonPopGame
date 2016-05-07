package com.yazlab.proje.sabitler_globaller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public final class Tema extends Skin {

    public Tema() {
        /* Türkçe karakter desteği için FreeType font kullanıldı */

        super();

        // Font üretmek için parametreleri ayarla
        FreeTypeFontParameter parametre = new FreeTypeFontParameter();
        parametre.size = 48;
        parametre.characters = "abcçdefgğhıijklmnoöpqrsştuüvwxyzABCÇDEFGĞHIİJKLMNOÖPQRSŞTUÜVWXYZ0123456789][_!$%#@|\\\\/?-+=()*&.;,{}\\\"´`'<>:";

        // Fontu parametreye göre üret
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("DejaVuSans.ttf"));
        BitmapFont font = generator.generateFont(parametre); // Fontu parametreye göre oluştur
        generator.dispose();

        // Fontu temaya ekle
        add("myFont", font);

        // Temayı oluştur ve yükle
        addRegions(new TextureAtlas(Gdx.files.internal("uiskin.atlas")));
        load(Gdx.files.internal("uiskin.json"));
    }
}