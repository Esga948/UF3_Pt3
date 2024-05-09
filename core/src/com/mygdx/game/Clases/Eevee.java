package com.mygdx.game.Clases;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;

import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class Eevee extends Image{
    Array<TextureRegion> gifEevee;
    TextureRegion eevee1;
    TextureRegion eevee2;
    TextureRegion eevee3;
    TextureRegion eevee4;

    //Constructor al que pasamos todas las imagenes que forman el gif
    public Eevee(TextureRegion eevee1, TextureRegion eevee2, TextureRegion eevee3, TextureRegion eevee4) {
        this.gifEevee = new Array<>();
        this.eevee1 = eevee1;
        this.eevee2 = eevee2;
        this.eevee3 = eevee3;
        this.eevee4 = eevee4;
        crearGif(eevee1, eevee2, eevee3, eevee4);
    }

    //Creamos el array con las imagenes
    private void crearGif(TextureRegion eevee1, TextureRegion eevee2, TextureRegion eevee3, TextureRegion eevee4){
        gifEevee.add(eevee1);
        gifEevee.add(eevee2);
        gifEevee.add(eevee3);
        gifEevee.add(eevee4);
    }

    public Array<TextureRegion> getGifEevee() {
        return gifEevee;
    }

    public void setGifEevee(Array<TextureRegion> gifEevee) {
        this.gifEevee = gifEevee;
    }

    public TextureRegion getEevee1() {
        return eevee1;
    }

    public void setEevee1(TextureRegion eevee1) {
        this.eevee1 = eevee1;
    }

    public TextureRegion getEevee2() {
        return eevee2;
    }

    public void setEevee2(TextureRegion eevee2) {
        this.eevee2 = eevee2;
    }

    public TextureRegion getEevee3() {
        return eevee3;
    }

    public void setEevee3(TextureRegion eevee3) {
        this.eevee3 = eevee3;
    }

    public TextureRegion getEevee4() {
        return eevee4;
    }

    public void setEevee4(TextureRegion eevee4) {
        this.eevee4 = eevee4;
    }

}
