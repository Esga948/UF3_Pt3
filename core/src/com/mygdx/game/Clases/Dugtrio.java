package com.mygdx.game.Clases;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Dugtrio extends Image {
    public Dugtrio (Texture textura){
        super(textura);
        setPosition(800, 34);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if ( getX() == 0 )
            getParent().removeActor(this);
    }
    public boolean colisiona(float y ) {
        // Calculem els rectangles de col·lisió dels dos objectes
        Rectangle rectangleDugtrio = new Rectangle(getX(), getY(), getWidth(), getHeight());
        System.out.println("Y " + y);
        Rectangle rectangleEevee = new Rectangle(55, y, 150, 150);
        //El mètode overlaps indica si hi ha solapament entre els dos rectangles calculats
        return rectangleDugtrio.overlaps(rectangleEevee);
    }
}
