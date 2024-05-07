package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.Drop;
import com.mygdx.game.GifDecoder;
import com.mygdx.game.Screens.GameScreen;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class SplashScreen implements Screen {
    final Drop game;
    OrthographicCamera camera;
    private float timetoSplashScreen = 5f;
    long lastFrameTime = 0;
    long FRAME_DURATION = 100;
    ArrayList<Texture> frames = new ArrayList<>();
    int currentFrameIndex = 0;
    public SplashScreen(final Drop game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    private static ArrayList<Texture> loadGifFrames(String gifFilePath) {
        ArrayList<Texture> frames = new ArrayList<>();

        try (InputStream inputStream = Gdx.files.internal(gifFilePath).read()) {
            GifDecoder gifDecoder = new GifDecoder();
            gifDecoder.read(inputStream);
            int frameCount = gifDecoder.getFrameCount();

            for (int i = 0; i < frameCount; i++) {
                Texture texture = new Texture(gifDecoder.getFrame(i), true);
                frames.add(texture);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return frames;
    }

    @Override
    public void show() {
       frames = loadGifFrames("gifSplash.gif");
    }

    @Override
    public void render(float delta) {
        timetoSplashScreen -= delta;

        if (TimeUtils.timeSinceMillis(lastFrameTime) > FRAME_DURATION) {
            // Actualizar el tiempo de la última actualización de frame
            lastFrameTime = TimeUtils.millis();

            GlyphLayout layoutTitulo = new GlyphLayout(game.fontTitulo, "Juego del Eevee");
            float tituloX = (Gdx.graphics.getWidth() - layoutTitulo.width) / 2;

            camera.update();
            game.batch.setProjectionMatrix(camera.combined);

            // Dibujar el frame actual
            game.batch.begin();
            game.batch.draw(frames.get(currentFrameIndex), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            game.fontTitulo.draw(game.batch, "Juego del Eevee", tituloX, 200);
            game.fontTitulo2.draw(game.batch, "Juego del Eevee", tituloX, 200);

            game.batch.end();

            // Incrementar el índice del frame actual
            currentFrameIndex++;

            // Verificar si se ha alcanzado el final del ciclo de frames
            if (currentFrameIndex >= frames.size()) {
                currentFrameIndex = 0; // Volver al primer frame
            }
        }
        if (timetoSplashScreen <=0){
            //game.setScreen(new MainMenuScreen(game));
            game.setScreen(new InicioScreen(game));
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
