package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.AssetsDesc;
import com.mygdx.game.Clases.DugtrioHandler;
import com.mygdx.game.Clases.Eevee;
import com.mygdx.game.Clases.Vidas;
import com.mygdx.game.Drop;
import com.mygdx.game.InputHandler;

import java.util.ArrayList;

import sun.jvm.hotspot.debugger.win32.coff.TestDebugInfo;

public class GameScreen implements Screen {
    final Drop game;
    public final Stage stage;
    final AssetManager assetManager = new AssetManager();
    //Fondo
    Texture backgraundDia, backgraundNoche, backgraund, imagenVida;
    Music ambienteJuego;
    Sound jumpSound, evSound;
    //Score
    private int score = 0;
    private int score2 = 0;
    private int score3 = 0;
    private int score4 = 0;

    private int index = 0;
    private float scoreTime = 0;
    private final float SCORE_INTERVAL = 1;
    //eevees gif
    Eevee eevee;
    Eevee leafeon;
    Eevee umbreon;
    Array<TextureRegion> gifEevee = new Array<>();
    TextureRegion eevee1;
    TextureRegion eevee2;
    TextureRegion eevee3;
    TextureRegion eevee4;
    TextureRegion umbreon1, umbreon2, umbreon3, umbreon4;
    private float stateTime;
    //Obstaculos
    DugtrioHandler dMove;
    //vida
    Vidas vidas;
    ArrayList<Texture> listaVidas = new ArrayList<>();
    //Salto
    private boolean isJumping = false;
    private float jumpTime = 0;
    private float JUMP_DURATION = 1.6f; // Duración del salto en segundos
    private float jumpStartY; // Posición Y inicial del salto
    float eeveeY = 34;

    public GameScreen(Drop game) {
        this.game = game;

        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        stage = new Stage(new StretchViewport(800, 480, camera));
    }

    @Override
    public void show() {
        loadAssets();

        //Cargamos la musica de fondo y los efectos de sonido necesarios
        ambienteJuego = assetManager.get(AssetsDesc.ambienteJuego);
        ambienteJuego.setLooping(true);
        ambienteJuego.setVolume(0.5f);
        jumpSound = assetManager.get(AssetsDesc.sonidoSalto);
        evSound = assetManager.get(AssetsDesc.sonidoEv);

        dMove = new DugtrioHandler(assetManager);

        //Crear el array para hacer el gif eevee
        eevee1 = new TextureRegion(assetManager.get(AssetsDesc.eevee1Texture));
        eevee2 = new TextureRegion(assetManager.get(AssetsDesc.eevee2Texture));
        eevee3 = new TextureRegion(assetManager.get(AssetsDesc.eevee3Texture));
        eevee4 = new TextureRegion(assetManager.get(AssetsDesc.eevee4Texture));
        eevee = new Eevee(eevee1, eevee2, eevee3, eevee4);
        gifEevee = eevee.getGifEevee();

        //Vidas
        vidas = new Vidas(assetManager, assetManager.get(AssetsDesc.vidaTexture), 3);
        listaVidas = vidas.getListaVidas();

        stage.addActor(dMove);

        //Gdx.input.setInputProcessor(new InputHandler(this));
        ambienteJuego.play();
    }

    @Override
    public void render(float delta) {
        while(!assetManager.update()){
            assetManager.getProgress();
        }
        stateTime += delta;
        scoreTime += delta;
        backgraundDia = assetManager.get(AssetsDesc.diaTexture);
        backgraundNoche = assetManager.get(AssetsDesc.nocheTexture);
        backgraund = assetManager.get(AssetsDesc.back1Texture);
        imagenVida = assetManager.get(AssetsDesc.vidaTexture);
        jumpSound = assetManager.get(AssetsDesc.sonidoSalto);
        evSound = assetManager.get(AssetsDesc.sonidoEv);

        ScreenUtils.clear(0, 0, 0.2f, 1);

        TextureRegion currentFrameE = gifEevee.get((int) (stateTime / 0.15f) % gifEevee.size);

        //Añadimos un controlador para que si el personaje ya esta saltando, aunque le vuelva
        // a dar a la pantalla o mantenga presionado no se quede volando
        if (Gdx.input.isTouched() && !isJumping){
            jumpSound.play(0.5f);
            jump();
        }

        //Hace el salto y cuando cae da la posibilidad de volver a saltar
        if (isJumping){
            jumpTime += delta;
            //float jumpProgress = jumpTime / JUMP_DURATION;
            //float newY = jumpStartY - 150* jumpProgress;
            if (jumpTime >= JUMP_DURATION) {
                isJumping = false;
                jumpTime = 0;
                eeveeY = 34;
            }
        }
        //Añadimos varios scores para poder manejar variables ya que si usamos el (score3 % 10 == 0)
        //como el render entra varias veces en el mismo segundo el funcionamiento es erroneo
        if (scoreTime >= SCORE_INTERVAL){
            score += 5;
            score2 += 5;
            score3 += 5;
            score4 += 5;
            scoreTime = 0;
        }

        //llama a la funcion para comprobar si han colisionado los peronajes
        if (dMove.collectD(eeveeY)){
            vidas.eliminarVida();
        }

        //Regeneramos una vida cada vez que el score llega a 100, si ha pasado el score donde evoluciona
        // añadiremos la cuarta vida como máximo
        if (score3 == 100){
            if (score >= 200){
                vidas.añadirVidaExtra(assetManager.get(AssetsDesc.vidaTexture));
            }else{
                vidas.añadirVida(assetManager.get(AssetsDesc.vidaTexture));
            }
            score3 = 0;

        }

        //Cada que el score llega a 10 reduciremos el tiempo de salto intentando coordinarlo con la velocidad creciente de los obstaculos
        if (score4 == 10){
            if (JUMP_DURATION > 0.7f){
                JUMP_DURATION -= 0.02f;
                System.out.println("Jump duration " + JUMP_DURATION);
            }
            score4 = 0;
        }

        //Controlaremos que si pierde todas las vidas dirija a la pantalla de Game Over
        if (vidas.getNumVidas() <= 0) {
            game.setScreen(new GameOverScreen(game, score));
            dispose();
        }

        //Cuando el score llegue a 200 el eevee evolucionara camviando su aspecto y añadiendo una vida más como máximo
        if (score == 200){
            newGifEevee();
        }

        stage.getBatch().begin();
        //Mostrar imagen back, dependiendo el score se verá una escena u otra
        switch (index){
            case 0:
                if (score2 >= 150){
                    stage.getBatch().draw(backgraund, 0, -20);
                    index++;
                    score2 = 0;
                }else{
                    stage.getBatch().draw(backgraundDia, 0, -20);
                }
                break;
            case 1:
                if (score2 >= 50){
                    stage.getBatch().draw(backgraundNoche, 0, -10);
                    index++;
                    score2 = 0;
                }else{
                    stage.getBatch().draw(backgraund, 0, -20);
                }
                break;
            case 2:
                if (score2 >= 200){
                    stage.getBatch().draw(backgraund, 0, -20);
                    index = 0;
                    score2 = 0;
                }else{
                    stage.getBatch().draw(backgraundNoche, 0, -10);
                }
            default:
                break;
        }
        //Mostramos el gif en la posicion correspondiente
        stage.getBatch().draw(currentFrameE, 55, eeveeY);

        //Score y vidas
        game.fontScore.draw(stage.getBatch(), "Score: " + score, 30,420);
        for (int i = 0; i < listaVidas.size(); i++) {
            stage.getBatch().draw(listaVidas.get(i), 800 -(listaVidas.size() - i) * (listaVidas.get(i).getWidth()+10) -20,  430-listaVidas.get(i).getHeight());
        }
        stage.getBatch().end();
        stage.act(delta);
        stage.draw();
    }

    //Impide que se pueda mantener pulsado para que se mantenga volando el personaje, ni que se pueda dar varias veces seguidas sin que baje primero
    private void jump(){
        jumpStartY = eeveeY;
        eeveeY = 150;
        isJumping = true;
    }
    private void newGifEevee(){
        //Crear el array para hacer el gif umbreon
        umbreon1 = new TextureRegion(assetManager.get(AssetsDesc.umbreon1Texture));
        umbreon2 = new TextureRegion(assetManager.get(AssetsDesc.umbreon2Texture));
        umbreon3 = new TextureRegion(assetManager.get(AssetsDesc.umbreon3Texture));
        umbreon4 = new TextureRegion(assetManager.get(AssetsDesc.umbreon4Texture));
        umbreon = new Eevee(umbreon1, umbreon2, umbreon3, umbreon4);
        gifEevee.clear();
        gifEevee = umbreon.getGifEevee();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        assetManager.dispose();
    }
    private void loadAssets(){
        assetManager.load(AssetsDesc.diaTexture);
        assetManager.load(AssetsDesc.nocheTexture);
        assetManager.load(AssetsDesc.back1Texture);
        assetManager.load(AssetsDesc.diglettTexture);
        assetManager.load(AssetsDesc.dugtrioTexture);
        assetManager.load(AssetsDesc.eevee1Texture);
        assetManager.load(AssetsDesc.eevee2Texture);
        assetManager.load(AssetsDesc.eevee3Texture);
        assetManager.load(AssetsDesc.eevee4Texture);
        assetManager.load(AssetsDesc.umbreon1Texture);
        assetManager.load(AssetsDesc.umbreon2Texture);
        assetManager.load(AssetsDesc.umbreon3Texture);
        assetManager.load(AssetsDesc.umbreon4Texture);
        assetManager.load(AssetsDesc.ambienteJuego);
        assetManager.load(AssetsDesc.ambienteInicio);
        assetManager.load(AssetsDesc.ambienteFinal);
        assetManager.load(AssetsDesc.sonidoDerrota);
        assetManager.load(AssetsDesc.sonidoEv);
        assetManager.load(AssetsDesc.sonidoSalto);
        assetManager.load(AssetsDesc.sonidoVida);
        assetManager.load(AssetsDesc.vidaTexture);
        assetManager.finishLoading();
    }
}
