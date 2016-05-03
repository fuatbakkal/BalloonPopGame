package com.yazlab.proje;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

import static com.yazlab.proje.Sabitler.ekranGenisligi;
import static com.yazlab.proje.Sabitler.ekranYuksekligi;

public class MenuEkrani implements Screen {
    private final float BUTON_GENISLIGI = ekranGenisligi - 180f;
    private final float BUTON_YUKSEKLIGI = 180f;
    private final float BUTON_ARALIGI = 10f;
    private final float BUTON_X = (ekranGenisligi - BUTON_GENISLIGI) / 2;
    final Oyun oyun;
    private Stage stage;
    private Tema tema;
    private Label yazi;
    private Window pencere;
    private TextButton yeniOyunButonu;
    private TextButton hakkindaButonu;
    private TextButton cikisButonu;

    public MenuEkrani(final Oyun oyun) {
        this.oyun = oyun;
        this.stage = new Stage();
        this.tema = new Tema();
        Gdx.input.setInputProcessor(stage);

        float currentY = 1000f;

        // Ekranın üstündeki yazı
        yazi = new Label("YazLab II - Proje 2\nBalon Patlatma Oyunu", tema);
        yazi.setX((ekranGenisligi - yazi.getWidth()) / 3);
        yazi.setY(ekranYuksekligi - 180f);

        // "Yeni Oyun" butonu
        yeniOyunButonu = new TextButton("Yeni Oyun", tema);
        yeniOyunButonu.setX(BUTON_X);
        yeniOyunButonu.setY(currentY);
        yeniOyunButonu.setWidth(BUTON_GENISLIGI);
        yeniOyunButonu.setHeight(BUTON_YUKSEKLIGI);
        yeniOyunButonu.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                oyun.setScreen(new OyunEkrani(oyun));
                dispose();
                return true;
            }
        });

        // "Hakkında" butonu
        hakkindaButonu = new TextButton("Hakkında", tema);
        hakkindaButonu.setX(BUTON_X);
        hakkindaButonu.setY(currentY - (BUTON_YUKSEKLIGI + BUTON_ARALIGI));
        hakkindaButonu.setWidth(BUTON_GENISLIGI);
        hakkindaButonu.setHeight(BUTON_YUKSEKLIGI);

        // "Hakkında" penceresi
        pencere = new Window("Pencere Başlığı", tema);
        pencere.setSize(320, 640);
        pencere.setPosition(180, 540);
        pencere.setKeepWithinStage(true);

        // "Çıkış" butonu
        cikisButonu = new TextButton("Çıkış", tema);
        cikisButonu.setX(BUTON_X);
        cikisButonu.setY(currentY - (BUTON_YUKSEKLIGI + BUTON_ARALIGI) * 2);
        cikisButonu.setWidth(BUTON_GENISLIGI);
        cikisButonu.setHeight(BUTON_YUKSEKLIGI);
        cikisButonu.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                dispose();
                Gdx.app.exit();
                return true;
            }
        });

        stage.addActor(yazi);
        stage.addActor(yeniOyunButonu);
        stage.addActor(hakkindaButonu);
        stage.addActor(cikisButonu);
    }

    @Override
    public void render(float delta) {
        // Arkaplan
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        hakkindaButonu.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                stage.addActor(pencere);
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
        //skin.dispose();
        //stage.dispose();
        //oyun.dispose();
    }
}