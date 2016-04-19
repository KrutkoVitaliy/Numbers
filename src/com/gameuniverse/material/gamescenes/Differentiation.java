package com.gameuniverse.material.gamescenes;

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
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.color.Color;

import android.content.Intent;
import android.net.Uri;

import com.gameuniverse.material.managers.Scenes;
import com.gameuniverse.material.managers.Storage;
import com.gameuniverse.material.managers.Scenes.SceneType;
import com.gameuniverse.material.scenes.Base;

public class Differentiation extends Base implements IOnAreaTouchListener, IOnSceneTouchListener
{
	int Seconds, Minutes, Hours, Correct, Incorrect, Mode;
	private Text tTitle;
	private Sprite Task1, Task2, Task3;
	private Rectangle Result1, Result2, Result3, Next;
	
	@Override public void createScene()
	{
		Load();
		Timer();
		Environment();
		Tasks(1);
		Board();
	}
	private void Tasks(int mode)
	{
		switch(mode)
		{
		case 1:
			Task1.setVisible(true);
			Task2.setVisible(false);
			Task3.setVisible(false);
			Result1.setX(38);
			Result2.setX(-1000);
			Result3.setX(-1000);
			Mode = 1;
		break;
		case 2:
			Task1.setVisible(false);
			Task2.setVisible(true);
			Task3.setVisible(false);
			Result1.setX(-1000);
			Result2.setX(38);
			Result3.setX(-1000);
			Mode = 2;
		break;
		case 3:
			Task1.setVisible(false);
			Task2.setVisible(false);
			Task3.setVisible(true);
			Result1.setX(-1000);
			Result2.setX(-1000);
			Result3.setX(38);
			Mode = 3;
		break;
		}
	}
	private void Load()
	{
		Storage.Init(_Activity);
		Seconds = Storage.Get("Seconds");
		Minutes = Storage.Get("Minutes");
		Hours = Storage.Get("Hours");
		Correct = Storage.Get("Correct");
		Incorrect = Storage.Get("Incorrect");
	}
	private void Save()
	{
		Storage.Init(_Activity);
		Storage.Add("Seconds", Seconds);
		Storage.Add("Minutes", Minutes);
		Storage.Add("Hours", Hours);
		Storage.Add("Correct", Correct);
		Storage.Add("Incorrect", Incorrect);
	}
	private void Timer()
	{
		_Engine.registerUpdateHandler(new TimerHandler(1F, true, new ITimerCallback()
		{
			@Override
			public void onTimePassed(TimerHandler pTimerHandler)
			{
				Seconds ++;
	            if(Seconds > 59) 
	            {
	            	Minutes ++;
	            	Seconds = 0;
	            	 if(Minutes > 59) 
	 	            {
	 		            Hours ++;
	 		            Minutes = 0;
	 	            }
	            }
			Save();
			}
		}));
	}
	private void Environment()
	{
		final Sprite GameBackground = new Sprite(0, 0, _Resources.TestBackground, _VBO);
		attachChild(GameBackground);

		tTitle = new Text(120, 80, _Resources._OswaldLightLargeF, "Дифференцир.", _VBO);
		tTitle.setHorizontalAlign(HorizontalAlign.CENTER);
		attachChild(tTitle);
		
		Task1 = new Sprite(40, 350, _Resources.Calc[3], _Resources._VBO);
		Task2 = new Sprite(40, 350, _Resources.Calc[4], _Resources._VBO);
		Task3 = new Sprite(40, 350, _Resources.Calc[5], _Resources._VBO);

		Task1.setVisible(false);
		Task2.setVisible(false);
		Task3.setVisible(false);
		
		attachChild(Task1);
		attachChild(Task2);
		attachChild(Task3);
		
		Result1 = new Rectangle(38, 884, 640, 70, _VBO)
		{
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY)
			{
				if(pSceneTouchEvent.isActionDown())
				{
					Uri URL = Uri.parse("http://lifexperience.ru/Numbers/Differentation/2_1.PNG");
					Intent LINK = new Intent(Intent.ACTION_VIEW, URL);
					_Activity.startActivity(LINK);
					return true;
				}
				return false;
			}
		};
		Result2 = new Rectangle(38, 884, 640, 70, _VBO)
		{
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY)
			{
				if(pSceneTouchEvent.isActionDown())
				{
					Uri URL = Uri.parse("http://lifexperience.ru/Numbers/Differentation/2_2.PNG");
					Intent LINK = new Intent(Intent.ACTION_VIEW, URL);
					_Activity.startActivity(LINK);
					return true;
				}
				return false;
			}
		};
		Result3 = new Rectangle(38, 884, 640, 70, _VBO)
		{
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY)
			{
				if(pSceneTouchEvent.isActionDown())
				{
					Uri URL = Uri.parse("http://lifexperience.ru/Numbers/Differentation/2_3.PNG");
					Intent LINK = new Intent(Intent.ACTION_VIEW, URL);
					_Activity.startActivity(LINK);
					return true;
				}
				return false;
			}
		};
		Next = new Rectangle(38, 1005, 640, 70, _VBO)
		{
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY)
			{
				if(pSceneTouchEvent.isActionDown())
				{
					if(Mode == 1)
						Tasks(2);
					else if(Mode == 2)
						Tasks(3);
					else if(Mode == 3)
						Tasks(1);
					return true;
				}
				return false;
			}
		};
		Result1.setX(-1000);
		Result2.setX(-1000);
		Result3.setX(-1000);

		Result1.setAlpha(0F);
		Result2.setAlpha(0F);
		Result3.setAlpha(0F);
		Next.setAlpha(0F);

		registerTouchArea(Result1);
		registerTouchArea(Result2);
		registerTouchArea(Result3);
		registerTouchArea(Next);
		
		attachChild(Result1);
		attachChild(Result2);
		attachChild(Result3);
		attachChild(Next);
	}
	private void Board()
	{
		Button(41, 81, 60, 60, -3);
		Button(625, 81, 60, 60, -4);
	}
	private void Button(int x, int y, int w, int h, final int v)
	{
		final Rectangle CorrectEffect = new Rectangle(x, y, w, h, _VBO);
		CorrectEffect.setColor(Color.GREEN);
		CorrectEffect.setAlpha(0F);
		attachChild(CorrectEffect);
		
		final Rectangle IncorrectEffect = new Rectangle(x, y, w, h, _VBO);
		IncorrectEffect.setColor(Color.RED);
		IncorrectEffect.setAlpha(0F);
		attachChild(IncorrectEffect);
		
		final Rectangle Button = new Rectangle(x, y, w, h, _VBO)
		{
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY)
			{
				if(pSceneTouchEvent.isActionDown())
				{
					this.registerEntityModifier(new AlphaModifier(0.5F, 0.5F, 0F));
					if(v == -3)
					{
						Scenes.getInstance().CreateGameScene(_Engine);
					}
					else if(v == -4)
					{
						Uri URL = Uri.parse("https://ru.wikipedia.org/wiki/%D0%94%D0%B8%D1%84%D1%84%D0%B5%D1%80%D0%B5%D0%BD%D1%86%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D0%B5");
						Intent LINK = new Intent(Intent.ACTION_VIEW, URL);
						_Activity.startActivity(LINK);
					}
					else if(v == 1)
					{
						CorrectEffect.registerEntityModifier(new AlphaModifier(0.5F, 0.5F, 0F));
						Correct++;
					}
					else if(v == 0)
					{
						IncorrectEffect.registerEntityModifier(new AlphaModifier(0.5F, 0.5F, 0F));
						Incorrect++;
					}
					return true;
				}
				return false;
			};
		};
		Button.setColor(0.6F, 0.6F, 0.6F);
		Button.setAlpha(0F);
		registerTouchArea(Button);
		attachChild(Button);
	}
	@Override public void onBackKeyPressed(){Scenes.getInstance().CreateGameScene(_Engine);}
	@Override public SceneType getSceneType(){return SceneType.DIFFERENTIATION;}
	@Override public void disposeScene(){}
	@Override public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent){return false;}
	@Override public boolean onAreaTouched(TouchEvent pSceneTouchEvent, ITouchArea pTouchArea, float pTouchAreaLocalX, float pTouchAreaLocalY){return false;}
}