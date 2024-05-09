package com.mygdx.game.Clases;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.AssetsDesc;

import java.util.Iterator;
import java.util.Random;

public class DugtrioHandler extends Group {
    private long lastDTime;
    private float DUGTRIO_TIME_INTERVAL = 3000000000L;
    private final long MINIMO_INTERVAL = 500000000;
    private final Texture texture;
    private final Texture texture2;
    private final Sound chocarSound;
    private int DSpawned = 0;
    private Random random = new Random();
    private float duration = 5;
    private final float minDuration = 1;

    //Constructor, genera el primer obstaculo
    public DugtrioHandler(AssetManager assetManager){
        lastDTime = TimeUtils.nanoTime();
        texture = assetManager.get(AssetsDesc.dugtrioTexture);
        texture2 = assetManager.get(AssetsDesc.diglettTexture);
        chocarSound = assetManager.get(AssetsDesc.sonidoDerrota);
        spawnD();
    }

   @Override
    public void act(float delta) {
        super.act(delta);
        spawnD();
    }

    //Controla el riempo del ultimo obstaculo creado y genera otro
    private void spawnD() {
        long currentTime = TimeUtils.nanoTime();
        if ( currentTime - lastDTime > DUGTRIO_TIME_INTERVAL){
            lastDTime = TimeUtils.nanoTime();
            Texture randomTexture = random.nextBoolean() ? texture : texture2;
            Dugtrio dugtrio = new Dugtrio(randomTexture);
            // Afegim a l'obstacle l'acció de desplaçar-se fins la part esquerra de la pantalla
            dugtrio.addAction(Actions.moveTo(0, dugtrio.getY(),duration));
            //Afegim la gota  la llista d'actors del grup
            addActor(dugtrio);
            DSpawned++;

            if (DSpawned % 2 == 0 && DUGTRIO_TIME_INTERVAL > MINIMO_INTERVAL){
                DUGTRIO_TIME_INTERVAL -= 100000000;
                System.out.println("INTERVAL " + DUGTRIO_TIME_INTERVAL);
            }

            if (DSpawned % 6 == 0 && minDuration < duration) {
                duration -= 0.5f;
                System.out.println("DURACION " + duration);
            }
        }
    }

    //Controla si el obstaculo choca contra el personaje
    public boolean collectD(float y){
        boolean result = false;
        Iterator<Actor> it = getChildren().iterator();

        while (it.hasNext()){
            Dugtrio dugtrio = (Dugtrio) it.next();
            if(dugtrio.colisiona(y)){
                removeActor(dugtrio);
                result = true;
                chocarSound.play(0.4f);
            }
        }
        return result;
    }
}
