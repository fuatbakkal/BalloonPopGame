package com.yazlab.proje.pencereler;

import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;

import static com.yazlab.proje.sabitler_globaller.Globaller.tema;
import static com.yazlab.proje.sabitler_globaller.Sabitler.ekranYuksekligi;

/* Oyundaki bütün açılır pencerelerin üst sınıfı */
public class Pencere extends Dialog {

    public Pencere(String title) {
        super(title, tema);
        initialize();
    }

    private void initialize() {
        padTop(80);
        getTitleLabel().setAlignment(Align.center);
        getButtonTable().defaults().height(ekranYuksekligi / 16f);
        setModal(true);
        setMovable(false);
        setResizable(false);
    }

    @Override
    public Pencere text(String text) {
        super.text(new Label(text, tema));
        return this;
    }

    public Pencere button(String buttonText, InputListener listener) {
        TextButton button = new TextButton(buttonText, tema);
        button.addListener(listener);
        button(button);
        return this;
    }
}