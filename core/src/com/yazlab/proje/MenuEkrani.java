package com.yazlab.proje;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.yazlab.proje.arkaplanlar.ArkaMenu;

import static com.yazlab.proje.sabitler_globaller.Globaller.tema;
import static com.yazlab.proje.sabitler_globaller.Sabitler.ekranGenisligi;
import static com.yazlab.proje.sabitler_globaller.Sabitler.ekranYuksekligi;

public class MenuEkrani implements Screen {
    private final Oyun oyun;
    private Stage stage;
    private Label yazi;
    private AcilirPencere hakkindaPencere;
    private TextButton yeniOyunButon, yuksekSkorlarButon, hakkindaButon, cikisButon;

    public MenuEkrani(final Oyun oyun) {
        this.oyun = oyun;
        this.stage = new Stage(new StretchViewport(ekranGenisligi, ekranYuksekligi));
        Gdx.input.setInputProcessor(stage);

        final float BUTON_GENISLIGI = ekranGenisligi - 150f;
        final float BUTON_YUKSEKLIGI = 90f;
        final float BUTON_ARALIGI = 20f;
        final float BUTON_X = (ekranGenisligi - BUTON_GENISLIGI) / 2;

        // Ekranın üstündeki yazı
        yazi = new Label("YazLab II - Proje 2\nBalon Patlatma Oyunu", tema);
        yazi.setX((ekranGenisligi - yazi.getWidth()) / 3);
        yazi.setY(ekranYuksekligi - 180f);

        // "Yeni Oyun" butonu
        yeniOyunButon = new TextButton("Yeni Oyun", tema);
        yeniOyunButon.setX(BUTON_X);
        yeniOyunButon.setY(ekranYuksekligi / 2);
        yeniOyunButon.setWidth(BUTON_GENISLIGI);
        yeniOyunButon.setHeight(BUTON_YUKSEKLIGI);
        yeniOyunButon.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                oyun.setScreen(new OyunEkrani(oyun));
                dispose();
                return true;
            }
        });

        // "Hakkında" butonu
        hakkindaButon = new TextButton("Hakkında", tema);
        hakkindaButon.setX(BUTON_X);
        hakkindaButon.setY(ekranYuksekligi / 2 - (BUTON_YUKSEKLIGI + BUTON_ARALIGI));
        hakkindaButon.setWidth(BUTON_GENISLIGI);
        hakkindaButon.setHeight(BUTON_YUKSEKLIGI);

        // "Hakkında" penceresi
        hakkindaPencere = new AcilirPencere("Hakkında");
        hakkindaPencere.button("Tamam", new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return hakkindaPencere.remove();
            }
        });
        hakkindaPencere.text("130202087 - Fuat Bakkal\n" +
                             "130202089 - Mustafa Koç\n" +
                             "130202025 - Osman Avni Koçoğlu\n" +
                             "130202077 - Kaan Cörüt");
        hakkindaPencere.setScale(.88f);
        //hakkindaPencere.setHeight(ekranYuksekligi/2f);

        // "Çıkış" butonu
        cikisButon = new TextButton("Çıkış", tema);
        cikisButon.setX(BUTON_X);
        cikisButon.setY(ekranYuksekligi / 2 - (BUTON_YUKSEKLIGI + BUTON_ARALIGI) * 2);
        cikisButon.setWidth(BUTON_GENISLIGI);
        cikisButon.setHeight(BUTON_YUKSEKLIGI);
        cikisButon.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                dispose();
                oyun.dispose();
                Gdx.app.exit();
                return true;
            }
        });

        stage.addActor(new ArkaMenu());
        stage.addActor(yazi);
        stage.addActor(yeniOyunButon);
        stage.addActor(hakkindaButon);
        stage.addActor(cikisButon);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        hakkindaButon.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                hakkindaPencere.show(stage);
                stage.addActor(hakkindaPencere);
                return true;
            }
        });

        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        stage.dispose();
        oyun.dispose();
    }
}