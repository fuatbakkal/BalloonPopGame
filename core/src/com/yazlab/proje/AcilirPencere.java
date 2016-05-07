package com.yazlab.proje;

import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;

import static com.yazlab.proje.sabitler_globaller.Globaller.tema;
import static com.yazlab.proje.sabitler_globaller.Sabitler.ekranGenisligi;

public class AcilirPencere extends Dialog {

    public AcilirPencere(String title) {
        super(title, tema);
        initialize();
    }

    private void initialize() {
        getTitleLabel().setAlignment(Align.center);
        padTop(80); // set padding on top of the dialog title
        getButtonTable().defaults().height(120); // set buttons height
        getButtonTable().defaults().width(ekranGenisligi/2f);
        setModal(true);
        setMovable(false);
        setResizable(false);
    }

    @Override
    public AcilirPencere text(String text) {
        super.text(new Label(text, tema));
        return this;
    }

    public AcilirPencere button(String buttonText, InputListener listener) {
        TextButton button = new TextButton(buttonText, tema);
        button.addListener(listener);
        button(button);
        return this;
    }
}