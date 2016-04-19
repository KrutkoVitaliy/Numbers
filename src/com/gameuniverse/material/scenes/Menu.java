package com.gameuniverse.material.scenes;

import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.IOnAreaTouchListener;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.ITouchArea;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.util.FPSLogger;
import org.andengine.input.touch.TouchEvent;

import com.gameuniverse.material.managers.Scenes;
import com.gameuniverse.material.managers.Scenes.SceneType;

import android.widget.Toast;

public class Menu extends Base implements IOnSceneTouchListener, IOnAreaTouchListener
{
	int Exit = 2;
	boolean _ToGame = false;
	int Seconds = 0, Minutes = 0;
	int _Count = 0;
			
	@Override
	public void createScene()
	{
		Background();
		Logo();
		Buttons();
		_Engine.registerUpdateHandler(new TimerHandler(0.25F, true, new ITimerCallback()
		{
			@Override
			public void onTimePassed(TimerHandler pTimerHandler)
			{
				if(_Count < 20)
					Effects((int)(Math.random()*10));
			}
		}));
		this._Engine.registerUpdateHandler(new FPSLogger());
	}
	
	private void Effects(int Value)
	{
		final Text _Text = new Text(0, -100, _Resources._MenuOswaldLightSmallF, ""+Value, _VBO);
		_Text.setColor(0.125F, 0.125F, 0.125F);
		attachChild(_Text);
		
		_Text.setX((int)(Math.random()*720));
		final float _Scale = (float)(Math.random()*2+1);
		final int _Rotate = (int)(Math.random()*360);
		final float _Alpha = (float)(Math.random()*1+0.3F);
		final int _Speed = (int)(Math.random() * 4 + 2);
		
		_Engine.registerUpdateHandler(new TimerHandler(0.01F, true, new ITimerCallback()
		{
			@Override
			public void onTimePassed(TimerHandler pTimerHandler)
			{
				_Text.setScale(_Scale);
				_Text.setRotation(_Rotate);
				_Text.setAlpha(_Alpha);
				_Text.setY(_Text.getY() + _Speed);
				if(_Text.getY() > 1280)
		    	{
		    		_Text.setY(-100);
		    		_Text.setX((int)(Math.random()*720));
		    	}
			}
		}));
		_Count++;
	}
	
	private void Background()
	{
		final Rectangle _Rect = new Rectangle(0, 0, 720, 1280, _VBO);
		_Rect.setColor(0.98F, 0.98F, 0.98F);
		attachChild(_Rect);
	}
	
	private void Logo()
	{
		final Sprite _Logo = new Sprite(110, 150, _Resources._AppName, _Resources._VBO);
		_Logo.setAlpha(0F);
		attachChild(_Logo);
		
		_Engine.registerUpdateHandler(new TimerHandler(0.5F, new ITimerCallback()
		{
			@Override
			public void onTimePassed(TimerHandler pTimerHandler)
			{
				_Logo.registerEntityModifier(new AlphaModifier(0.3F, 0F, 1F));
			}
		}));
	}
	
	private void Buttons()
	{
		final Sprite _Start = new Sprite(260, 800, _Resources._Play, _Resources._VBO)
		{
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY)
			{
				if(pSceneTouchEvent.isActionDown())
				{
					Scenes.getInstance().CreateGameScene(_Engine);
					return true;
				}
				return false;
			}
		};
		_Start.setAlpha(0F);
		registerTouchArea(_Start);
		attachChild(_Start);
		
		_Engine.registerUpdateHandler(new TimerHandler(0.6F, new ITimerCallback()
		{
			@Override
			public void onTimePassed(TimerHandler pTimerHandler)
			{
				_Start.registerEntityModifier(new AlphaModifier(0.3F, 0F, 1F));
			}
		}));
	}
	
	@Override
	public void onBackKeyPressed()
	{
		Toast _Exit = Toast.makeText(_Activity, "Кликните дважды для выхода!", Toast.LENGTH_SHORT);
		_Exit.show();
		
		Exit--;
		if(Exit == 0)
			android.os.Process.killProcess(android.os.Process.myPid());
		_Engine.registerUpdateHandler(new TimerHandler(2F, new ITimerCallback()
		{
			@Override
			public void onTimePassed(TimerHandler pTimerHandler)
			{
				Exit = 2;
			}
		}));
	}

	@Override
	public SceneType getSceneType()
	{
		return SceneType.SCENE_MENU;
	}

	@Override
	public void disposeScene()
	{
		this.detachSelf();
		this.dispose();
	}

	@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent, ITouchArea pTouchArea, float pTouchAreaLocalX, float pTouchAreaLocalY)
	{
		return false;
	}

	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent)
	{
		return false;
	}
}