package com.yazlab.proje;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import static com.yazlab.proje.Sabitler.*;

public class OyunEkrani implements Screen {
    private final Oyun oyun;
    private float spawnSuresi;
    private float spawnAraligi;
    private float gecenSure;
    private float sureAraligi;
    private int kalanSure;
    private boolean durdurulduMu;
    private Tema tema;
    private Label puanYazisiLabel;
    private Label puanLabel;
    private Label kalanSureLabel;
    private Label sureLabel;
    private Sound patlamaSesi;
    private Stage stage;

    public OyunEkrani(final Oyun oyun) {
        puan = 0;
        patlatilanKirmizi = 0;
        patlatilanSari = 0;
        patlatilanYesil = 0;
        patlatilanSiyah = 0;
        this.durdurulduMu = false;
        this.oyun = oyun;
        this.stage = new Stage();
        this.tema = new Tema();
        this.spawnSuresi = 0f;
        this.spawnAraligi = 0.8f; // Balonların oluşturulma aralığı
        this.kalanSure = 30; // Oyun süre limiti
        this.gecenSure = 0f;
        this.sureAraligi = 1.0f;

        Gdx.graphics.setContinuousRendering(false); //Sadece oyun durdurulana kadar render et
        Gdx.graphics.requestRendering();
        Gdx.input.setInputProcessor(stage); //Inputları etkinleştir
        Gdx.input.setCatchBackKey(true); //Sistem "geri" tuşu yerine libgdx "geri" tuşunu kullan

        // "Puan" yazısını göster
        puanYazisiLabel = new Label("Puan", tema);
        puanYazisiLabel.setX(0);
        puanYazisiLabel.setY(ekranYuksekligi - puanYazisiLabel.getHeight());
        stage.addActor(puanYazisiLabel);

        // Puanı göster
        puanLabel = new Label("0000", tema);
        puanLabel.setX(puanYazisiLabel.getWidth() / 4);
        puanLabel.setY(ekranYuksekligi - puanYazisiLabel.getHeight() * 2);

        // "Kalan süre" yazısını göster
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
        // Oyun durdurulmadıysa render et
        if (!durdurulduMu) {
            Gdx.graphics.requestRendering();
        }

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

        // Balonları ekrana ekle
        if (spawnSuresi > spawnAraligi) {
            spawnSuresi -= spawnAraligi;
            stage.addActor(new BalonKirmizi());
            stage.addActor(new BalonYesilSiyah());
        }

        sureLabel.setText(Integer.toString(kalanSure));
        puanLabel.setText(Integer.toString(puan));
        stage.addActor(sureLabel);
        stage.addActor(puanLabel);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        // Ekran dışına çıkan balonları oyundan kaldır
        for (Actor balon : stage.getActors()) {
            if (balon.getX() > ekranGenisligi || balon.getY() > ekranYuksekligi) {
                balon.remove();
            }
        }

        // Süre dolunca yapılacaklar
        if(kalanSure == 0) {
            durdurulduMu = true;

            // Bölüm geçme koşulu sağlanırsa
            if(patlatilanKirmizi != 0 && patlatilanSari != 0 && patlatilanYesil != 0 && patlatilanSiyah != 0 && puan >= 100) {
                oyun.setScreen(new OyunEkrani(oyun));
            }

            // Koşul sağlanmazsa
            else {
                dispose();
            }
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
        //oyun.pause();
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