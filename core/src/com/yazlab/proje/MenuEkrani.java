package com.yazlab.proje;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
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
    private AcilirPencere hakkindaPencere;

    public MenuEkrani(final Oyun oyun) {
        this.oyun = oyun;
        this.stage = new Stage(new StretchViewport(ekranGenisligi, ekranYuksekligi));
        menuyuOlustur(stage);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    public void menuyuOlustur(final Stage stage) {
        final float BUTON_GENISLIGI = ekranGenisligi / 2f;
        final float BUTON_YUKSEKLIGI = 95f;
        final float BUTON_ARALIGI = 30f;
        final float BUTON_X = (ekranGenisligi - BUTON_GENISLIGI) / 2;
        final float BUTON_Y = 785;

        // Ekranın üstündeki yazı
        Label yazi = new Label("Balon Patlatma Oyunu", tema);
        yazi.setX((ekranGenisligi - yazi.getWidth()) / 2);
        yazi.setY(ekranYuksekligi - 180f);

        // "Yeni Oyun" butonu
        TextButton yeniOyunButon = new TextButton("Yeni Oyun", tema);
        yeniOyunButon.setX(BUTON_X);
        yeniOyunButon.setY(BUTON_Y);
        yeniOyunButon.setWidth(BUTON_GENISLIGI);
        yeniOyunButon.setHeight(BUTON_YUKSEKLIGI);
        yeniOyunButon.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                oyun.setScreen(new OyunEkrani(oyun));
                dispose();
                return true;
            }
        });

        // "Yüksek Skorlar" butonu
        TextButton yuksekSkorlarButon = new TextButton("Yüksek Skorlar", tema);
        yuksekSkorlarButon.setX(BUTON_X);
        yuksekSkorlarButon.setY(BUTON_Y - (BUTON_YUKSEKLIGI + BUTON_ARALIGI));
        yuksekSkorlarButon.setWidth(BUTON_GENISLIGI);
        yuksekSkorlarButon.setHeight(BUTON_YUKSEKLIGI);
        yuksekSkorlarButon.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                dispose();
                return true;
            }
        });

        // "Hakkında" butonu
        TextButton hakkindaButon = new TextButton("Hakkında", tema);
        hakkindaButon.setX(BUTON_X);
        hakkindaButon.setY(BUTON_Y - (BUTON_YUKSEKLIGI + BUTON_ARALIGI) * 2);
        hakkindaButon.setWidth(BUTON_GENISLIGI);
        hakkindaButon.setHeight(BUTON_YUKSEKLIGI);
        hakkindaButon.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                hakkindaPencere.show(stage);
                stage.addActor(hakkindaPencere);
                return true;
            }
        });

        // "Hakkında" penceresi
        hakkindaPencere = new AcilirPencere("Hakkında");
        hakkindaPencere.getContentTable().add("Yazılım Laboratuvarı II - 2. Proje\n").colspan(2);
        hakkindaPencere.getContentTable().row();
        hakkindaPencere.getContentTable().add("130202087");
        hakkindaPencere.getContentTable().add("Fuat BAKKAL");
        hakkindaPencere.getContentTable().row();
        hakkindaPencere.getContentTable().add("130202089");
        hakkindaPencere.getContentTable().add("Mustafa KOÇ");
        hakkindaPencere.getContentTable().row();
        hakkindaPencere.getContentTable().add("130202077");
        hakkindaPencere.getContentTable().add("Kaan CÖRÜT");
        hakkindaPencere.getContentTable().row();
        hakkindaPencere.getContentTable().add("130202025");
        hakkindaPencere.getContentTable().add("Osman Avni KOÇOĞLU");
        hakkindaPencere.button("Tamam", new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return hakkindaPencere.remove();
            }
        });

        // "Çıkış" butonu
        TextButton cikisButon = new TextButton("Çıkış", tema);
        cikisButon.setX(BUTON_X);
        cikisButon.setY(BUTON_Y - (BUTON_YUKSEKLIGI + BUTON_ARALIGI) * 3);
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
        stage.addActor(yuksekSkorlarButon);
        stage.addActor(hakkindaButon);
        stage.addActor(cikisButon);
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