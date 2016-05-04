package com.yazlab.proje;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import static com.yazlab.proje.Sabitler.ekranGenisligi;
import static com.yazlab.proje.Sabitler.ekranYuksekligi;
import static com.yazlab.proje.Sabitler.puan;

public class OyunEkrani implements Screen {
    private final Oyun oyun;
    private float spawnSuresi;
    private float spawnAraligi;
    private float gecenSure;
    private float sureAraligi;
    private int kalanSure;
    private Tema tema;
    private Label puanLabel;
    private Label kalanSureLabel;
    private Label sureLabel;
    private Sound patlamaSesi;
    private Stage stage;
    private BalonKirmizi kirmiziBalon;
    private BalonYesil yesilBalon;

    public OyunEkrani(final Oyun oyun) {
        puan = 0;
        this.oyun = oyun;
        this.stage = new Stage();
        this.tema = new Tema();
        this.spawnSuresi = 0f;
        this.spawnAraligi = 0.8f; // Balonların oluşturulma aralığı
        this.kalanSure = 30; // Oyun süre limiti
        this.gecenSure = 0f;
        this.sureAraligi = 1.0f;

        Gdx.input.setInputProcessor(stage); //Inputları etkinleştir
        Gdx.input.setCatchBackKey(true); //Sistem "geri" tuşu yerine libgdx "geri" tuşunu kullan

        // "Puan" yazısı
        puanLabel = new Label("Puan", tema);
        puanLabel.setX(0);
        puanLabel.setY(ekranYuksekligi - puanLabel.getHeight());

        // "Kalan süre" yazısı
        kalanSureLabel = new Label("Kalan Süre", tema);
        kalanSureLabel.setX(ekranGenisligi - kalanSureLabel.getWidth());
        kalanSureLabel.setY(ekranYuksekligi - kalanSureLabel.getHeight());
        stage.addActor(kalanSureLabel);

        // Kalan süreyi göster
        sureLabel = new Label(Integer.toString(kalanSure), tema);
        sureLabel.setX(ekranGenisligi - kalanSureLabel.getWidth() / 2);
        sureLabel.setY(ekranYuksekligi - kalanSureLabel.getHeight() * 2);

        // TODO: 4.05.2016 Ses efektleri ve arkaplan müziği
        /*
		patlamaSesi = Gdx.audio.newSound(Gdx.files.internal("patlama.mp3"));
		arkaPlanMuzigi = Gdx.audio.newMusic(Gdx.files.internal("muzik.mp3"));
		arkaPlanMuzigi.setLooping(true);
        */
    }

    @Override
    public void render(float delta) {

        // TODO: 4.05.2016 Arkaplan resmi ekle
        // Arkaplan rengi
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        spawnSuresi += delta;
        gecenSure += delta;

        // 'kalanSure'yi her 'sureAraligi'nda 1 azalt
        if (gecenSure > sureAraligi) {
            kalanSure--;
            gecenSure -= sureAraligi;
        }

        if (spawnSuresi > spawnAraligi) {
            spawnSuresi -= spawnAraligi;

            // Kırmızı balonları oluştur
            kirmiziBalon = new BalonKirmizi();
            stage.addActor(kirmiziBalon);

            // Yeşil balonları oluştur
            yesilBalon = new BalonYesil();
            stage.addActor(yesilBalon);

            // FIXME: 4.05.2016 Siyah balonlar
            /*
            BalonSiyah siyahBalon = new BalonSiyah();
            siyahBalon.setTouchable(Touchable.enabled);
			stage.addActor(siyahBalon);*/
        }

        puanLabel.setText("Puan: " + puan);
        sureLabel.setText(Integer.toString(kalanSure));
        stage.addActor(puanLabel);
        stage.addActor(sureLabel);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        // Ekran dışına çıkan balonları oyundan kaldır
        for (Actor a : stage.getActors()) {
            if (a.getX() > ekranGenisligi || a.getY() > ekranYuksekligi) {
                //escaped++;
                a.remove();
            }
        }

        if(kalanSure == 0){
            dispose();
            // TODO: 4.05.2016 Bölüm bitince dialog oluştur ve bölüm puanını göster
            oyun.setScreen(new OyunEkrani(oyun));
        }

        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        //// TODO: 4.05.2016 Arkaplan müziğini çal
        // Arkaplan müziği burada eklenebilir
        //arkaPlanMuzigi.play();
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
        oyun.setScreen(new MenuEkrani(oyun));
        // TODO: 4.05.2016 Sesler-müzikler dispose
        //dropSound.dispose();
        //arkaPlanMuzigi.dispose();
    }
}