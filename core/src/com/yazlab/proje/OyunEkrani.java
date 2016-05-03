package com.yazlab.proje;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import static com.yazlab.proje.Sabitler.ekranGenisligi;
import static com.yazlab.proje.Sabitler.ekranYuksekligi;

public class OyunEkrani implements Screen {
    private final Oyun oyun;
    private Sound patlamaSesi;
    private Stage stage;
    private float spawnSuresi;
    private float spawnAraligi;

    public OyunEkrani(final Oyun oyun) {
        this.oyun = oyun;
        this.stage = new Stage();
        this.spawnSuresi = 0;
        this.spawnAraligi = 0.8f;

        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);

        /*
        // Ses efektleri ve arkaplan müziği
		//patlamaSesi = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
		//rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
		//rainMusic.setLooping(true);
        */
    }

    @Override
    public void render(float delta) {
        // Arkaplan
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        spawnSuresi += delta;
        if (spawnSuresi > spawnAraligi) {
            spawnSuresi -= spawnAraligi;
            BalonKirmizi balon = new BalonKirmizi();
            balon.setTouchable(Touchable.enabled);
            stage.addActor(balon);

            BalonYesil yesilBalon = new BalonYesil();
            yesilBalon.setTouchable(Touchable.enabled);
            stage.addActor(yesilBalon);

            /*
            BalonSiyah siyahBalon = new BalonSiyah();
            siyahBalon.setTouchable(Touchable.enabled);
			stage.addActor(siyahBalon);*/
        }

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        // Ekran dışına çıkan balonları oyundan kaldır
        for (Actor a : stage.getActors()) {
            if (a.getX() > ekranGenisligi || a.getY() > ekranYuksekligi) {
                //escaped++;
                a.remove();
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
        // Arkaplan müziği burada eklenebilir
        //rainMusic.play();
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
        //dropSound.dispose();
        //rainMusic.dispose();
    }
}