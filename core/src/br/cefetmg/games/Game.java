package br.cefetmg.games;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Game extends ApplicationAdapter {

    private SpriteBatch batch;
    private Goomba goomba;
    private Texture map, player, spriteSheet;    
    private TextureRegion[][] quadrosDaAnimacao;
    private Animation andarParaFrente, andarParaTras, andarParaEsquerda, andarParaDireita;
    
    @Override
    public void create() {        
        batch = new SpriteBatch();
        map = new Texture("map.png");        
                
        //quebra o sprite sheet e o coloca nas devidas animações
        spriteSheet = new Texture("goomba-spritesheet.png");
        quadrosDaAnimacao = TextureRegion.split(spriteSheet, spriteSheet.getWidth()/5, spriteSheet.getHeight()/4);
        andarParaFrente = new Animation(0.1f, new TextureRegion[] {
            quadrosDaAnimacao[0][0], // 1ª linha, 1ª coluna
            quadrosDaAnimacao[0][1], // idem, 2ª coluna
            quadrosDaAnimacao[0][2],
            quadrosDaAnimacao[0][3],
            quadrosDaAnimacao[0][4],
        });
        andarParaFrente.setPlayMode(PlayMode.LOOP_PINGPONG);
        andarParaDireita = new Animation(0.1f, new TextureRegion[] {
            quadrosDaAnimacao[1][0], // 1ª linha, 1ª coluna
            quadrosDaAnimacao[1][1], // idem, 2ª coluna
            quadrosDaAnimacao[1][2],
            quadrosDaAnimacao[1][3],
            quadrosDaAnimacao[1][4],
        });
        andarParaDireita.setPlayMode(PlayMode.LOOP_PINGPONG);
        andarParaTras = new Animation(0.1f, new TextureRegion[] {
            quadrosDaAnimacao[2][0], // 1ª linha, 1ª coluna
            quadrosDaAnimacao[2][1], // idem, 2ª coluna
            quadrosDaAnimacao[2][2],
            quadrosDaAnimacao[2][3],
            quadrosDaAnimacao[2][4],
        });
        andarParaTras.setPlayMode(PlayMode.LOOP_PINGPONG);
        andarParaEsquerda = new Animation(0.1f, new TextureRegion[] {
            quadrosDaAnimacao[3][0], // 1ª linha, 1ª coluna
            quadrosDaAnimacao[3][1], // idem, 2ª coluna
            quadrosDaAnimacao[3][2],
            quadrosDaAnimacao[3][3],
            quadrosDaAnimacao[3][4],
        });
        andarParaEsquerda.setPlayMode(PlayMode.LOOP_PINGPONG);       
        //cria goomba com todas as animações
        goomba = new Goomba(andarParaFrente, andarParaTras, andarParaEsquerda, andarParaDireita);
    }

    public void update() {
        goomba.update();
    }
    
    @Override
    public void render() {

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        update();
        
        batch.begin();
            // desenhos são realizados aqui
            batch.draw(map, 0, 0);
            goomba.render(batch);           
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
