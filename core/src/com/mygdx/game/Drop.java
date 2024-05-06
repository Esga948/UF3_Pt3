package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.game.Screens.SplashScreen;

public class Drop extends Game {
    public SpriteBatch batch;
    public BitmapFont font;
    public BitmapFont fontScore;
    public BitmapFont fontTitulo;
    public BitmapFont fontTitulo2;
    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator generator2;
    private FreeTypeFontGenerator generator3;
    private FreeTypeFontGenerator generator4;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        fontScore = new BitmapFont();
        fontTitulo = new BitmapFont();
        fontTitulo2 = new BitmapFont();

        Color negro = Color.BLACK;
        Color amarillo = Color.YELLOW;
        Color azul = Color.BLUE;


        //font Score
        generator = new FreeTypeFontGenerator(Gdx.files.internal("bungeespiceregular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 24; // Tamaño de la fuente
        fontScore = generator.generateFont(parameter);
        generator.dispose(); // Importante liberar recursos

        //font
        generator2 = new FreeTypeFontGenerator(Gdx.files.internal("dogicapixel.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter2 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter2.size = 20;
        parameter2.color = negro;
        font = generator2.generateFont(parameter2);
        generator2.dispose(); // Importante liberar recursos

        //font Titulos
        generator3 = new FreeTypeFontGenerator(Gdx.files.internal("PokemonSolid.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter3 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter3.size = 100;
        parameter3.color = amarillo;
        fontTitulo = generator3.generateFont(parameter3);

        //font Titulos
        generator4 = new FreeTypeFontGenerator(Gdx.files.internal("PokemonHollow.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter4 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter4.size = 100;
        parameter4.color = azul;
        fontTitulo2 = generator4.generateFont(parameter4);

        this.setScreen(new SplashScreen(this));
    }
    @Override
    public void render () {
        super.render();
    }

    @Override
    public void dispose () {
        batch.dispose();
        font.dispose();
        fontScore.dispose();
        fontTitulo.dispose();

    }
}
