package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.AssetsDesc;
import com.mygdx.game.Drop;

public class InicioScreen implements Screen {
    final Drop game;
    OrthographicCamera camera;
    final AssetManager assetManager = new AssetManager();
    Texture inicioBackground;
    Music ambienteInicio;

    public InicioScreen(final Drop game){
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 440);
    }
    private void loadAssets() {
        assetManager.load(AssetsDesc.inicioTexture);
        assetManager.load(AssetsDesc.ambienteInicio);
        assetManager.finishLoading();
    }
        @Override
    public void show() {
            loadAssets();

            ambienteInicio = assetManager.get(AssetsDesc.ambienteInicio);
            ambienteInicio.setLooping(true);
            ambienteInicio.play();
        }

    @Override
    public void render(float delta) {
        while(!assetManager.update()){
            assetManager.getProgress();
        }
        inicioBackground = assetManager.get(AssetsDesc.inicioTexture);
        ScreenUtils.clear(0, 0, 0.2f, 1);

        //Cogemos las dimensiones del texto
        GlyphLayout layoutTitulo = new GlyphLayout(game.fontTitulo, "Start");
        GlyphLayout layoutTxt = new GlyphLayout(game.font, "Tap to begin");

        //Localizamos la posicion central para el texto
        float tituloX = (800 - layoutTitulo.width) / 2;
        float tituloY = (700 - layoutTitulo.height) / 2;
        float txtX = (800 - layoutTxt.width) / 2;

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(inicioBackground, 0, -60);
        game.fontTitulo.draw(game.batch, "Start", tituloX, tituloY);
        game.fontTitulo2.draw(game.batch, "Start", tituloX, tituloY);
        game.font.draw(game.batch, "Tap to begin", txtX, 185);
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
