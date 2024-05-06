package com.mygdx.game;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetsDesc {
    //Imagenes de fondo
    public static final AssetDescriptor<Texture> diaTexture = new AssetDescriptor("background-dia.png",Texture.class);
    public static final AssetDescriptor<Texture> nocheTexture = new AssetDescriptor("background-noche.jpg",Texture.class);
    public static final AssetDescriptor<Texture> back1Texture = new AssetDescriptor("background.jpg",Texture.class);
    public static final AssetDescriptor<Texture> inicioTexture = new AssetDescriptor("inicio.jpg",Texture.class);
    public static final AssetDescriptor<Texture> finalDerrotaTexture = new AssetDescriptor("finalDerrota.png",Texture.class);


    public static final AssetDescriptor<Texture> vidaTexture = new AssetDescriptor("vida.png",Texture.class);

    //Imagenes obstaculos
    public static final AssetDescriptor<Texture> diglettTexture = new AssetDescriptor("diglett.png",Texture.class);
    public static final AssetDescriptor<Texture> dugtrioTexture = new AssetDescriptor("dugtrio.png",Texture.class);
    //Gif eevee
    public static final AssetDescriptor<Texture> eevee1Texture = new AssetDescriptor("eevee.png",Texture.class);
    public static final AssetDescriptor<Texture> eevee2Texture = new AssetDescriptor("eevee2.png", Texture.class);
    public static final AssetDescriptor<Texture> eevee3Texture = new AssetDescriptor("eevee3.png",Texture.class);
    public static final AssetDescriptor<Texture> eevee4Texture = new AssetDescriptor("eevee4.png",Texture.class);
    //Gif umbreon
    public static final AssetDescriptor<Texture> umbreon1Texture = new AssetDescriptor("umbreon.png",Texture.class);
    public static final AssetDescriptor<Texture> umbreon2Texture = new AssetDescriptor("umbreon2.png",Texture.class);
    public static final AssetDescriptor<Texture> umbreon3Texture = new AssetDescriptor("umbreon3.png",Texture.class);
    public static final AssetDescriptor<Texture> umbreon4Texture = new AssetDescriptor("umbreon4.png",Texture.class);
    /*//Gif leafeon
    public static final AssetDescriptor<Texture> leafeon1Texture = new AssetDescriptor("leafeon.png",Texture.class);
    public static final AssetDescriptor<Texture> leafeon2Texture = new AssetDescriptor("leafeon2.png",Texture.class);
    public static final AssetDescriptor<Texture> leafeon3Texture = new AssetDescriptor("leafeon3.png",Texture.class);
    public static final AssetDescriptor<Texture> leafeon4Texture = new AssetDescriptor("leafeon4.png",Texture.class);
    */
    //Sonidos
    public static final AssetDescriptor<Music> ambienteJuego = new AssetDescriptor("musica-back.mp3", Music.class);
    public static final AssetDescriptor<Music> ambienteInicio = new AssetDescriptor("musica-inicio.mp3", Music.class);
    public static final AssetDescriptor<Music> ambienteFinal = new AssetDescriptor("musica-final.mp3", Music.class);
    public static final AssetDescriptor<Sound> sonidoEv = new AssetDescriptor("sonido-ev.mp3", Sound.class);
    public static final AssetDescriptor<Sound> sonidoDerrota = new AssetDescriptor("sonido-derrota.mp3", Sound.class);
    public static final AssetDescriptor<Sound> sonidoSalto = new AssetDescriptor("sonido-salto.mp3", Sound.class);




}
