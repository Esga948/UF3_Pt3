package com.mygdx.game.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.AssetsDesc;
import com.mygdx.game.Drop;

public class GameOverScreen implements Screen {
    final Drop game;
    OrthographicCamera camera;
    final AssetManager assetManager = new AssetManager();
    Texture background;

    public GameOverScreen(final Drop game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 440);
    }

    @Override
    public void show() {
        //assetManager.load(AssetsDesc.background);
    }

    @Override
    public void render(float delta) {
        /*while(!assetManager.update()){
            assetManager.getProgress();
        }
        background = assetManager.get(AssetsDesc.background);*/
        ScreenUtils.clear(0, 0, 0.2f, 1);
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
