package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
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

    public GameOverScreen(final Drop game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 440);
    }
    private void loadAssets() {
        assetManager.load(AssetsDesc.finalDerrotaTexture);
        assetManager.finishLoading();
    }

    @Override
    public void show() {
        loadAssets();
    }

    @Override
    public void render(float delta) {
        while(!assetManager.update()){
            assetManager.getProgress();
        }
        finalBackground = assetManager.get(AssetsDesc.finalDerrotaTexture);
        ScreenUtils.clear(0, 0, 0.2f, 1);

        //Cogemos las dimensiones del texto
        GlyphLayout layoutTitulo = new GlyphLayout(game.fontTitulo, "Game over");
        GlyphLayout layoutTxt = new GlyphLayout(game.font, "Tap to go to start");

        float tituloX = (800 - layoutTitulo.width) / 2;
        float tituloY = (700 - layoutTitulo.height) / 2 - 50;
        float txtX = (800 - layoutTxt.width) / 2;

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(finalBackground, 0, 0);
        game.fontTitulo.draw(game.batch, "Game over", tituloX, tituloY);
        game.fontTitulo2.draw(game.batch, "Game over", tituloX, tituloY);
        game.font.draw(game.batch, "Tap to go to start", txtX, 155);
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

    }
}
