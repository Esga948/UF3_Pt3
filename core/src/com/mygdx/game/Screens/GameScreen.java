package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
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
    //Score
    private int score = 0;
    private int score2 = 0;
    private int index = 0;
    private float scoreTime = 0;
    private final float SCORE_INTERVAL = 1;
    //eevees gif
    public Eevee eevee;
    Eevee leafeon;
    Eevee umbreon;
    Array<TextureRegion> gifEevee = new Array<>();
    public TextureRegion eevee1;
    TextureRegion eevee2;
    TextureRegion eevee3;
    TextureRegion eevee4;
    Array<TextureRegion> gifLeafeon = new Array<>();
    TextureRegion leafeon1, leafeon2, leafeon3, leafeon4;
    Array<TextureRegion> gifUmbreon = new Array<>();
    TextureRegion umbreon1, umbreon2, umbreon3, umbreon4;
    private float stateTime;
    //Obstaculos
    DugtrioHandler dMove;
    //vida
    Vidas vidas;
    ArrayList<Texture> listaVidas = new ArrayList<>();

    //public static TextureRegion currentFrameE;
    public GameScreen(Drop game) {
        this.game = game;

        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        stage = new Stage(new StretchViewport(800, 480, camera));
    }


    @Override
    public void show() {
        loadAssets();

        ambienteJuego = assetManager.get(AssetsDesc.ambienteJuego);
        ambienteJuego.setLooping(true);

        dMove = new DugtrioHandler(assetManager);

        //Crear el array para hacer el gif eevee
        eevee1 = new TextureRegion(assetManager.get(AssetsDesc.eevee1Texture));
        eevee2 = new TextureRegion(assetManager.get(AssetsDesc.eevee2Texture));
        eevee3 = new TextureRegion(assetManager.get(AssetsDesc.eevee3Texture));
        eevee4 = new TextureRegion(assetManager.get(AssetsDesc.eevee4Texture));
        eevee = new Eevee(eevee1, eevee2, eevee3, eevee4);
        gifEevee = eevee.getGifEevee();

        //Crear el array para hacer el gif leafeon
        leafeon1 = new TextureRegion(assetManager.get(AssetsDesc.leafeon1Texture));
        leafeon2 = new TextureRegion(assetManager.get(AssetsDesc.leafeon2Texture));
        leafeon3 = new TextureRegion(assetManager.get(AssetsDesc.leafeon3Texture));
        leafeon4 = new TextureRegion(assetManager.get(AssetsDesc.leafeon4Texture));
        leafeon = new Eevee(leafeon1, leafeon2, leafeon3, leafeon4);
        gifLeafeon = leafeon.getGifEevee();

        //Crear el array para hacer el gif umbreon
        umbreon1 = new TextureRegion(assetManager.get(AssetsDesc.umbreon1Texture));
        umbreon2 = new TextureRegion(assetManager.get(AssetsDesc.umbreon2Texture));
        umbreon3 = new TextureRegion(assetManager.get(AssetsDesc.umbreon3Texture));
        umbreon4 = new TextureRegion(assetManager.get(AssetsDesc.umbreon4Texture));
        umbreon = new Eevee(umbreon1, umbreon2, umbreon3, umbreon4);
        gifUmbreon = umbreon.getGifEevee();

        //Vidas
        vidas = new Vidas(assetManager.get(AssetsDesc.vidaTexture), 3);
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
        ambienteJuego = assetManager.get(AssetsDesc.ambienteJuego);
        backgraundDia = assetManager.get(AssetsDesc.diaTexture);
        backgraundNoche = assetManager.get(AssetsDesc.nocheTexture);
        backgraund = assetManager.get(AssetsDesc.back1Texture);
        imagenVida = assetManager.get(AssetsDesc.vidaTexture);

        ScreenUtils.clear(0, 0, 0.2f, 1);

        TextureRegion currentFrameE = gifEevee.get((int) (stateTime / 0.15f) % gifEevee.size);
        //TextureRegion currentFrameL = gifLeafeon.get((int) (stateTime / 0.15f) % gifLeafeon.size);
        //TextureRegion currentFrameU = gifUmbreon.get((int) (stateTime / 0.15f) % gifUmbreon.size);


        if (scoreTime >= SCORE_INTERVAL){
            score += 5;
            score2 += 5;
            scoreTime = 0;
        }
        if (dMove.collectD(currentFrameE.getRegionY())){
            vidas.eliminarVida();
        }
        if (vidas.getNumVidas() <= 0) {
            game.setScreen(new GameOverScreen(game));
            dispose();
        }
        stage.getBatch().begin();

        //Mostrar imagen back
        switch (index){
            case 0:
                if (score2 >= 50){
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
                if (score2 >= 50){
                    stage.getBatch().draw(backgraund, 0, -20);
                    index = 0;
                    score2 = 0;
                }else{
                    stage.getBatch().draw(backgraundNoche, 0, -10);
                }
            default:
                break;
        }

        //stage.getBatch().draw(currentFrameL,55, 34);
        //stage.getBatch().draw(currentFrameU,55, 34);
        if (Gdx.input.isTouched()){
            stage.getBatch().draw(currentFrameE, 55, 250);
        }else{
            stage.getBatch().draw(currentFrameE,55, 34);
        }
        //Score y vidas
        game.fontScore.draw(stage.getBatch(), "Score: " + score, 30,420);
        for (int i = 0; i < listaVidas.size(); i++) {
            stage.getBatch().draw(listaVidas.get(i), 800 -(listaVidas.size() - i) * (listaVidas.get(i).getWidth()+10) -20,  430-listaVidas.get(i).getHeight());
        }

        stage.getBatch().end();
        stage.act(delta);
        stage.draw();


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
        assetManager.load(AssetsDesc.leafeon1Texture);
        assetManager.load(AssetsDesc.leafeon2Texture);
        assetManager.load(AssetsDesc.leafeon3Texture);
        assetManager.load(AssetsDesc.leafeon4Texture);
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
        assetManager.load(AssetsDesc.vidaTexture);
        assetManager.finishLoading();
    }
}
