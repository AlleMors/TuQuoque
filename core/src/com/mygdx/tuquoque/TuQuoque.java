package com.mygdx.tuquoque;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.awt.*;

public class TuQuoque extends ApplicationAdapter {
	SpriteBatch batch;
	private TextureRegion[] walkFramesRight;
	private TextureRegion[] walkFramesLeft;
	private Animation walkRightAnimation;
	private Animation walkLeftAnimation;
	float elapsedTime;
	private OrthographicCamera camera;
	private Rectangle player;


	Texture imgRight;
	Texture imgLeft;

	
	@Override
	public void create () {
		batch = new SpriteBatch();

		player=new Rectangle();
		player.width=32;
		player.height=32;
		player.x=1280/2-32/2;
		player.y=720/2-32/2;

		imgRight = new Texture("Gladiator-Sprite Sheet.png");
		imgLeft=new Texture("Gladiator-Sprite Sheet-Left.png");

		camera=new OrthographicCamera();
		camera.setToOrtho(false,1280,720);


		TextureRegion[][] tmpFramesRight =TextureRegion.split(imgRight, 32,32);
		TextureRegion[][] tmpFramesLeft =TextureRegion.split(imgLeft, 32,32);


		walkFramesRight =new TextureRegion[8];
		walkFramesLeft =new TextureRegion[8];

		int index=0;

		for(int i=0;i<8;i++){
			walkFramesRight[index++]= tmpFramesRight[1][i];
		}

		index=0;

		for(int i=0;i<8;i++){
			walkFramesLeft[index++]= tmpFramesLeft[1][i];
		}

		walkRightAnimation =new Animation(1/8f, walkFramesRight);
		walkLeftAnimation=new Animation(1/8f,walkFramesLeft);
	}

	@Override
	public void render () {
		elapsedTime+=Gdx.graphics.getDeltaTime();
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT|GL20.GL_DEPTH_BUFFER_BIT);
		batch.begin();
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			player.x+=100*Gdx.graphics.getDeltaTime();
			batch.draw((TextureRegion) walkRightAnimation.getKeyFrame(elapsedTime,true),player.x,player.y);
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			player.x-=100*Gdx.graphics.getDeltaTime();
			batch.draw((TextureRegion) walkLeftAnimation.getKeyFrame(elapsedTime,true),player.x,player.y);
		}

		batch.end();
	}

	public SpriteBatch getBatch(){
		return batch;
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		imgRight.dispose();
	}
}
