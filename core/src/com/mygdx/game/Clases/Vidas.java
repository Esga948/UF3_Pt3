package com.mygdx.game.Clases;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.AssetsDesc;

import java.util.ArrayList;

public class Vidas {
    ArrayList<Texture> listaVidas;
    Texture vida;
    int numVidas;
    Sound vidaSound;

    public Vidas(AssetManager assetManager, Texture vida, int numVidas) {
        this.vida = vida;
        this.numVidas = numVidas;
        this.listaVidas = new ArrayList<>();
        vidaSound = assetManager.get(AssetsDesc.sonidoVida);
        crearListaVidas(vida, numVidas);
    }

    private void crearListaVidas(Texture vida, int numVidas){
        for (int i = 0; i < numVidas; i++) {
            listaVidas.add(vida);
        }
    }

    public void eliminarVida(){
        if (numVidas <= 0){
            numVidas = 0;
        }else{
            numVidas--;
            listaVidas.remove(listaVidas.size()-1);
        }
        System.out.println("Vidas: " + numVidas);
    }

    public void añadirVida(Texture vida){
        if (numVidas >= 3){
            numVidas = 3;
        }else{
            numVidas++;
            vidaSound.play(0.5f);
            listaVidas.add(vida);
        }
        System.out.println("Vidaaaas " + numVidas);
    }
    public void añadirVidaExtra(Texture vida){
        if (numVidas >= 4){
            numVidas = 4;
        }else{
            numVidas++;
            vidaSound.play(0.5f);
            listaVidas.add(vida);
        }
    }
    public ArrayList<Texture> getListaVidas() {
        return listaVidas;
    }

    public void setListaVidas(ArrayList<Texture> listaVidas) {
        this.listaVidas = listaVidas;
    }

    public Texture getVida() {
        return vida;
    }

    public void setVida(Texture vida) {
        this.vida = vida;
    }

    public int getNumVidas() {
        return numVidas;
    }

    public void setNumVidas(int numVidas) {
        this.numVidas = numVidas;
    }
}
