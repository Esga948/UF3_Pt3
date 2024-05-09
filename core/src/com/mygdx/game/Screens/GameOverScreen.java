package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.AssetsDesc;
import com.mygdx.game.Drop;

public class GameOverScreen implements Screen {
    final Drop game;
    OrthographicCamera camera;
    final AssetManager assetManager = new AssetManager();
    Texture finalBackground;
    Music ambienteFinal;
    int score;

    public GameOverScreen(final Drop game, int score){
        this.game = game;
        this.score = score;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 440);
    }
    private void loadAssets() {
        assetManager.load(AssetsDesc.finalDerrotaTexture);
        assetManager.load(AssetsDesc.ambienteFinal);
        assetManager.finishLoading();
    }

    @Override
    public void show() {
        loadAssets();

        ambienteFinal = assetManager.get(AssetsDesc.ambienteFinal);
        ambienteFinal.setLooping(true);
        ambienteFinal.play();
    }

    @Override
    public void render(float delta) {
        while(!assetManager.update()){
            assetManager.getProgress();
        }
        finalBackground = assetManager.get(AssetsDesc.finalDerrotaTexture);
        ScreenUtils.clear(0, 0, 0.2f, 1);

        //Cogemos las dimensiones del texto para centrarlo en la pantalla
        GlyphLayout layoutTitulo = new GlyphLayout(game.fontTitulo, "Game over");
       // GlyphLayout layoutScore = new GlyphLayout(game.font, "Final score: " + score);
        GlyphLayout layoutTxt = new GlyphLayout(game.font, "Tap to go to start");

        //Localizamos la posicion central para el texto
        float tituloX = (800 - layoutTitulo.width) / 2;
        float tituloY = (700 - layoutTitulo.height) / 2 - 45;
        //float scoreX = (800 - layoutScore.width) / 2;
        float txtX = (800 - layoutTxt.width) / 2;

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(finalBackground, 0, 0);
        game.fontTitulo.draw(game.batch, "Game over", tituloX, tituloY);
        game.fontTitulo2.draw(game.batch, "Game over", tituloX, tituloY);
        game.fontScore.draw(game.batch, "Final score:  " + score, 55, 375);
        game.font.draw(game.batch, "Tap to go to start", txtX, 125);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
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
}
