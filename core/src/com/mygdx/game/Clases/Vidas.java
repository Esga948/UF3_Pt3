package com.mygdx.game.Clases;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class Vidas {
    ArrayList<Texture> listaVidas;
    Texture vida;
    int numVidas;

    public Vidas(Texture vida, int numVidas) {
        this.vida = vida;
        this.numVidas = numVidas;
        this.listaVidas = new ArrayList<>();
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
    }

    public void añadirVida(Texture vida){
        if (numVidas >= 3){
            numVidas = 3;
        }else{
            System.out.println("numVidas: " + numVidas);
            numVidas++;
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
