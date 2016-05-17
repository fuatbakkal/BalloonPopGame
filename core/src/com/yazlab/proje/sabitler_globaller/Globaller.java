package com.yazlab.proje.sabitler_globaller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;

public final class Globaller {
    public static FileHandle dosya = Gdx.files.local("yuksek_skorlar.txt");
    public static Tema tema;
    public static boolean sesAcik = true;
    public static Sound patlamaSesi;
    public static Music arkaPlanSesi;
    public static float zorluk = 1f;
    public static int seviye = 1;
    public static int bolumPuani = 0;
    public static int toplamPuan = 0;
    public static int patlatilanKirmizi = 0;
    public static int patlatilanSari = 0;
    public static int patlatilanYesil = 0;
    public static int patlatilanSiyah = 0;
}