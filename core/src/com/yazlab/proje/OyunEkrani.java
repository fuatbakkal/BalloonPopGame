package com.yazlab.proje;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.yazlab.proje.arkaplanlar.ArkaKolay;
import com.yazlab.proje.arkaplanlar.ArkaOrta;
import com.yazlab.proje.arkaplanlar.ArkaZor;
import com.yazlab.proje.balonlar.KirmiziBalon;
import com.yazlab.proje.balonlar.SariBalon;
import com.yazlab.proje.balonlar.YesilSiyahBalon;
import com.yazlab.proje.pencereler.BolumTamamlandi;
import com.yazlab.proje.pencereler.OyunBitti;
import com.yazlab.proje.pencereler.OyunTamamlandi;
import com.yazlab.proje.pencereler.Pencere;

import static com.yazlab.proje.sabitler_globaller.Globaller.arkaPlanSesi;
import static com.yazlab.proje.sabitler_globaller.Globaller.bolumPuani;
import static com.yazlab.proje.sabitler_globaller.Globaller.dosya;
import static com.yazlab.proje.sabitler_globaller.Globaller.patlamaSesi;
import static com.yazlab.proje.sabitler_globaller.Globaller.patlatilanKirmizi;
import static com.yazlab.proje.sabitler_globaller.Globaller.patlatilanSari;
import static com.yazlab.proje.sabitler_globaller.Globaller.patlatilanSiyah;
import static com.yazlab.proje.sabitler_globaller.Globaller.patlatilanYesil;
import static com.yazlab.proje.sabitler_globaller.Globaller.sesAcik;
import static com.yazlab.proje.sabitler_globaller.Globaller.seviye;
import static com.yazlab.proje.sabitler_globaller.Globaller.tema;
import static com.yazlab.proje.sabitler_globaller.Globaller.toplamPuan;
import static com.yazlab.proje.sabitler_globaller.Globaller.zorluk;
import static com.yazlab.proje.sabitler_globaller.Sabitler.ekranGenisligi;
import static com.yazlab.proje.sabitler_globaller.Sabitler.ekranYuksekligi;
import static com.yazlab.proje.sabitler_globaller.Sabitler.kirmiziAralik;
import static com.yazlab.proje.sabitler_globaller.Sabitler.sariAralik;
import static com.yazlab.proje.sabitler_globaller.Sabitler.yesilSiyahAralik;

public class OyunEkrani implements Screen {
    private final Oyun oyun;
    private Stage stage;
    private int kalanSure;
    private float sariSure, kirmiziSure, yesilSiyahSure;
    private float gecenSure, sureAraligi;
    private boolean durdurulduMu;
    private Label puanLabel, sureLabel;
    private Pencere cikisPencere;

    public OyunEkrani(final Oyun oyun, int seviye) {
        this.oyun = oyun;
        this.stage = new Stage(new StretchViewport(ekranGenisligi, ekranYuksekligi));
        arkaPlanSesi = Gdx.audio.newMusic(Gdx.files.internal("arkaplan_sesi.mp3"));
        patlamaSesi = Gdx.audio.newSound(Gdx.files.internal("patlama_sesi.mp3"));

        // Her yeni bölümde sıfırlanacak değişkenler
        bolumPuani = 0;
        patlatilanKirmizi = 0;
        patlatilanSari = 0;
        patlatilanYesil = 0;
        patlatilanSiyah = 0;
        this.durdurulduMu = false;
        this.sariSure = 0f;
        this.kirmiziSure = 0f;
        this.yesilSiyahSure = 0f;
        this.kalanSure = 30;
        this.gecenSure = 0f;
        this.sureAraligi = 1.0f;

        // Bölüme göre oyunun zorluğunu ve arkaplanı ayarla
        switch (seviye) {
            case 1:
                zorluk = 1f;
                stage.addActor(new ArkaKolay());
                break;
            case 2:
                zorluk = 1.7f;
                stage.addActor(new ArkaOrta());
                break;
            case 3:
                zorluk = 2f;
                stage.addActor(new ArkaZor());
                break;
        }

        // "Puan" yazısını göster
        Label puanYazisiLabel = new Label("Puan", tema);
        puanYazisiLabel.setX(0);
        puanYazisiLabel.setY(ekranYuksekligi - puanYazisiLabel.getHeight());
        stage.addActor(puanYazisiLabel);

        // Puanı göster
        puanLabel = new Label("0000", tema);
        puanLabel.setX(puanYazisiLabel.getWidth() / 4);
        puanLabel.setY(ekranYuksekligi - puanYazisiLabel.getHeight() * 2);
        stage.addActor(puanLabel);

        // "Kalan süre" yazısını göster
        Label kalanSureLabel = new Label("Kalan Süre", tema);
        kalanSureLabel.setX(ekranGenisligi - kalanSureLabel.getWidth());
        kalanSureLabel.setY(ekranYuksekligi - kalanSureLabel.getHeight());
        stage.addActor(kalanSureLabel);

        // Kalan süreyi göster
        sureLabel = new Label(Integer.toString(kalanSure), tema);
        sureLabel.setX(ekranGenisligi - kalanSureLabel.getWidth() / 2);
        sureLabel.setY(ekranYuksekligi - kalanSureLabel.getHeight() * 2);
        stage.addActor(sureLabel);

        // "Çıkış" penceresi
        cikisPencere = new Pencere("Oyundan çıkılsın mı?");
        cikisPencere.button("ÇIK", new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                dispose();
                oyun.setScreen(new MenuEkrani(oyun));
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

        Gdx.input.setCatchBackKey(true); //"Geri" tuşunu yakala
        Gdx.input.setInputProcessor(stage); //Inputları etkinleştir
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (!durdurulduMu) {
            gecenSure += delta;
            sariSure += delta;
            kirmiziSure += delta;
            yesilSiyahSure += delta;

            // Kalan süreyi azalt
            if (gecenSure > sureAraligi) {
                kalanSure--;
                gecenSure -= sureAraligi;
            }

            // Sarı balonların oluşturulma aralığı
            if (sariSure > sariAralik) {
                stage.addActor(new SariBalon());
                sariSure -= sariAralik;
            }

            // Kırmızı balonların oluşturulma aralığı
            if (kirmiziSure > kirmiziAralik) {
                stage.addActor(new KirmiziBalon());
                kirmiziSure -= kirmiziAralik;
            }

            // Yeşil ve siyah balonların oluşturulma aralığı
            if (yesilSiyahSure > yesilSiyahAralik) {
                stage.addActor(new YesilSiyahBalon());
                yesilSiyahSure -= yesilSiyahAralik;
            }

            // Ekran dışına çıkan balonları oyundan kaldır
            for (Actor balon : stage.getActors()) {
                if (balon.getX() > ekranGenisligi || balon.getY() > ekranYuksekligi) {
                    balon.remove();
                }
            }

            sureLabel.setText(Integer.toString(kalanSure));
            puanLabel.setText(Integer.toString(bolumPuani));
            stage.act(Gdx.graphics.getDeltaTime());
        }

        // Süre dolunca yapılacaklar
        if (kalanSure == 0 && !durdurulduMu) {
            durdurulduMu = true;
            toplamPuan += bolumPuani;

            // Bölüm geçme koşulu sağlanırsa
            if (patlatilanKirmizi != 0 && patlatilanSari != 0 && patlatilanYesil != 0 && patlatilanSiyah != 0 && bolumPuani >= 100) {

                // Oyun tamamlandıysa
                if (seviye == 3) {
                    dosya.writeString(toplamPuan + "\n", true); //Oyun sonu skoru dosyaya yaz
                    OyunTamamlandi tamamlandiPencere = new OyunTamamlandi(oyun, this);
                    tamamlandiPencere.show(stage);
                    stage.addActor(tamamlandiPencere);
                }

                // Sadece bölüm tamamlandıysa
                else {
                    BolumTamamlandi tamamlandiPencere = new BolumTamamlandi(oyun, this);
                    tamamlandiPencere.show(stage);
                    stage.addActor(tamamlandiPencere);
                }
            }

            // Bölüm geçme koşulu sağlanmazsa
            else {
                OyunBitti bitisPencere = new OyunBitti(oyun, this);
                bitisPencere.show(stage);
                stage.addActor(bitisPencere);
            }
        }

        // 'Geri' tuşuna basılınca yapılacaklar
        if (Gdx.input.isKeyPressed(Input.Keys.BACK) && !durdurulduMu) {
            durdurulduMu = true;
            cikisPencere.show(stage);
            stage.addActor(cikisPencere);
        }

        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        if (sesAcik) {
            arkaPlanSesi.setLooping(true);
            arkaPlanSesi.play();
        }
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
        arkaPlanSesi.dispose();
        stage.dispose();
    }
}