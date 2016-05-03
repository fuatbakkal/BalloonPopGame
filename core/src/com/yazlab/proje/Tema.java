package com.yazlab.proje;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Tema extends Skin {

    public Tema() {
        super();

        // Türkçe karakter desteği için FreeType font kullanıldı
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("DejaVuSans.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 64;
        parameter.characters = "abcçdefgğhıijklmnoöpqrsştuüvwxyzABCÇDEFGĞHIİJKLMNOÖPQRSŞTUÜVWXYZ0123456789][_!$%#@|\\\\/?-+=()*&.;,{}\\\"´`'<>";
        BitmapFont font = generator.generateFont(parameter); // font size 12 pixels
        generator.dispose(); // don't forget to dispose to avoid memory leaks!

        // Fontu temaya ekle
        add("myFont", font);
        addRegions(new TextureAtlas(Gdx.files.internal("uiskin.atlas")));
        load(Gdx.files.internal("uiskin.json"));
    }
}