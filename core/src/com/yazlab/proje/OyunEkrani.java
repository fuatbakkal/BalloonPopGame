package com.yazlab.proje;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.yazlab.proje.balonlar.KirmiziBalon;
import com.yazlab.proje.balonlar.SariBalon;
import com.yazlab.proje.balonlar.YesilSiyahBalon;

import static com.yazlab.proje.sabitler_globaller.Globaller.keypressed;
import static com.yazlab.proje.sabitler_globaller.Globaller.patlatilanKirmizi;
import static com.yazlab.proje.sabitler_globaller.Globaller.patlatilanSari;
import static com.yazlab.proje.sabitler_globaller.Globaller.patlatilanSiyah;
import static com.yazlab.proje.sabitler_globaller.Globaller.patlatilanYesil;
import static com.yazlab.proje.sabitler_globaller.Globaller.puan;
import static com.yazlab.proje.sabitler_globaller.Globaller.tema;
import static com.yazlab.proje.sabitler_globaller.Sabitler.ekranGenisligi;
import static com.yazlab.proje.sabitler_globaller.Sabitler.ekranYuksekligi;
import static com.yazlab.proje.sabitler_globaller.Sabitler.kirmiziAralik;
import static com.yazlab.proje.sabitler_globaller.Sabitler.sariAralik;
import static com.yazlab.proje.sabitler_globaller.Sabitler.yesilSiyahAralik;

public class OyunEkrani implements Screen {
    private final Oyun oyun;
    private float sariSure, kirmiziSure, yesilSiyahSure;
    private float spawnSuresi;
    private float spawnAraligi;
    private float gecenSure;
    private float sureAraligi;
    private int kalanSure;
    private boolean durdurulduMu;
    private Label puanYazisiLabel;
    private Label puanLabel;
    private Label kalanSureLabel;
    private Label sureLabel;
    private Sound patlamaSesi;
    private Texture arkaPlan;
    private Stage stage;
    private AcilirPencere cikisPencere;

    public OyunEkrani(final Oyun oyun) {
        keypressed = false;
        puan = 0;
        patlatilanKirmizi = 0;
        patlatilanSari = 0;
        patlatilanYesil = 0;
        patlatilanSiyah = 0;
        this.durdurulduMu = false;
        this.oyun = oyun;
        this.stage = new Stage(new StretchViewport(ekranGenisligi, ekranYuksekligi));
        this.spawnSuresi = 0f;
        this.spawnAraligi = 0.8f; // Balonların oluşturulma aralığı

        this.sariSure = 0f;
        this.kirmiziSure = 0f;
        this.yesilSiyahSure = 0f;

        this.kalanSure = 30; // Oyun süre limiti
        this.gecenSure = 0f;
        this.sureAraligi = 1.0f;
        arkaPlan = new Texture("arka_kolay.png");

        // TODO: 7.05.2016 Arkaplan eklenecek

        Gdx.graphics.setContinuousRendering(true); //Sadece oyun durdurulana kadar render et
        Gdx.input.setCatchBackKey(true);
        Gdx.input.setInputProcessor(stage); //Inputları etkinleştir

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

        // "Çıkış" penceresi
        cikisPencere = new AcilirPencere("Oyundan çıkılsın mı?");
        cikisPencere.button("ÇIK", new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                dispose();
                return cikisPencere.remove();
            }
        });

        cikisPencere.button("Devam Et", new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                durdurulduMu = false;
                return cikisPencere.remove();
            }
        });
        cikisPencere.text("\n");
        // TODO: 4.05.2016 Ses efektleri ve arkaplan müziği
        /*
        patlamaSesi = Gdx.audio.newSound(Gdx.files.internal("patlama.mp3"));
		arkaPlanMuzigi = Gdx.audio.newMusic(Gdx.files.internal("muzik.mp3"));
		arkaPlanMuzigi.setLooping(true);
        */
    }

    @Override
    public void render(float delta) {
        if (!durdurulduMu) {
            // TODO: 4.05.2016 Arkaplan resmi ekle
            // Arkaplan rengi
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            //spawnSuresi += delta;
            gecenSure += delta;
            sariSure += delta;
            kirmiziSure += delta;
            yesilSiyahSure += delta;


            // 'kalanSure'yi her 'sureAraligi'nda 1 azalt
            if (gecenSure > sureAraligi) {
                kalanSure--;
                gecenSure -= sureAraligi;
            }

            if (sariSure > sariAralik) {
                stage.addActor(new SariBalon());
                sariSure -= sariAralik;
            }

            if (kirmiziSure > kirmiziAralik) {
                stage.addActor(new KirmiziBalon());
                kirmiziSure -= kirmiziAralik;
            }

            if (yesilSiyahSure > yesilSiyahAralik) {
                stage.addActor(new YesilSiyahBalon());
                yesilSiyahSure -= yesilSiyahAralik;
            }

            /*
            // Balonları ekrana ekle
            if (spawnSuresi > spawnAraligi) {
                spawnSuresi -= spawnAraligi;
                stage.addActor(new KirmiziBalon());
                stage.addActor(new YesilSiyahBalon());
            }*/

            sureLabel.setText(Integer.toString(kalanSure));
            puanLabel.setText(Integer.toString(puan));
            stage.addActor(sureLabel);
            stage.addActor(puanLabel);
            stage.act(Gdx.graphics.getDeltaTime());
            /*
            stage.getBatch().begin();
            stage.getBatch().draw(arkaPlan, 0, 0, ekranGenisligi, ekranYuksekligi);
            stage.getBatch().end();*/
            stage.draw();

            // Ekran dışına çıkan balonları oyundan kaldır
            for (Actor balon : stage.getActors()) {
                if (balon.getX() > ekranGenisligi || balon.getY() > ekranYuksekligi) {
                    balon.remove();
                }
            }
        }

        // Süre dolunca yapılacaklar
        if (kalanSure == 0) {
            durdurulduMu = true;

            // Bölüm geçme koşulu sağlanırsa
            if (patlatilanKirmizi != 0 && patlatilanSari != 0 && patlatilanYesil != 0 && patlatilanSiyah != 0 && puan >= 100) {
                oyun.setScreen(new OyunEkrani(oyun));
            }

            // Koşul sağlanmazsa
            else {
                dispose();
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            durdurulduMu = true;
            cikisPencere.show(stage);
            stage.addActor(cikisPencere);
            stage.draw();
        }
    }

    @Override
    public void resize(int width, int height) {
        stage.draw();
    }

    @Override
    public void show() {
        // TODO: 4.05.2016 Arkaplan müziğini çal
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