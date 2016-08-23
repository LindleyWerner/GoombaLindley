package br.cefetmg.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author fegemo
 */
public class Goomba {
    private Sprite player;   
    private Animation frente, tras, dir, esq, animacaoCorrente;
    private float tempoDaAnimacao;
    private boolean andando; 
            
    public Goomba(Animation frente, Animation tras, Animation esq, Animation dir) {
        this.frente = frente;
        this.dir = dir;
        this.esq = esq;
        this.tras = tras;        
        player = new Sprite();
        player.setPosition(117, 100); 
        animacaoCorrente = frente;
    }
    
    public void update() {
        andando = true;
        // verifica qual seta está pressionada
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.getX() >= 0) {
            animacaoCorrente = esq;            
            player.setPosition(player.getX()-1, player.getY()); 
        }else{
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.getX() <= 235) {
                animacaoCorrente = dir;                
                player.setPosition(player.getX()+1, player.getY());
            }else{
                if (Gdx.input.isKeyPressed(Input.Keys.UP) && player.getY() <= 200) {
                    animacaoCorrente = tras;                    
                    player.setPosition(player.getX(), player.getY()+1);
                }else{
                    if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && player.getY() >= 0) {
                        animacaoCorrente = frente;                        
                        player.setPosition(player.getX(), player.getY()-1);
                    }else{//nenhuma tecla pressionada
                        andando = false;                        
                    }
                }                
            }            
        }        
    }
    
    public void render(SpriteBatch batch) {
        if(andando){//se estiver andando, varia animação (anda com o tempo)
            tempoDaAnimacao += Gdx.graphics.getDeltaTime();
        }else{//se estiver parado, para animação (para o tempo)
            tempoDaAnimacao = 1;
        }        
        //desenha animação especificada
        batch.draw(animacaoCorrente.getKeyFrame(tempoDaAnimacao,true),player.getX(), player.getY());                
    }
}
