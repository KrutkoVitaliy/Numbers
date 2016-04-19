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

import com.gameuniverse.material.managers.Scenes;
import com.gameuniverse.material.managers.Storage;
import com.gameuniverse.material.managers.Scenes.SceneType;
import com.gameuniverse.material.scenes.Base;

import android.content.Intent;
import android.net.Uri;

public class Logarithm extends Base implements IOnAreaTouchListener, IOnSceneTouchListener
{
	int Seconds, Minutes, Hours, Correct, Incorrect;
	private Text tInput, tFirst, tSecond, tTitle;
	private String sInput = "";
	private int iFirst, iSecond, iThird, iResult;
	
	@Override public void createScene()
	{
		Load();
		Timer();
		Environment();
		Board();
		Generate();
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
		final Sprite GameBackground = new Sprite(0, 0, _Resources.LevelBackGround, _VBO);
		attachChild(GameBackground);

		tFirst = new Text(170, 350, _Resources._OswaldLightLargeF, "Log()0 1 2 3 4 5 6 7 8 9 ", _VBO);
		tSecond = new Text(530, 350, _Resources._OswaldLightSmallF, "0 1 2 3 4 5 6 7 8 9 ", _VBO);
		tInput = new Text(360, 570, _Resources._OswaldLightLargeF, "0 1 2 3 4 5 6 7 8 9 ", _VBO);
		tTitle = new Text(120, 80, _Resources._OswaldLightLargeF, "ֻמדאנטפלû", _VBO);
		
		tFirst.setHorizontalAlign(HorizontalAlign.CENTER);
		tSecond.setHorizontalAlign(HorizontalAlign.CENTER);
		tInput.setHorizontalAlign(HorizontalAlign.CENTER);
		tTitle.setHorizontalAlign(HorizontalAlign.CENTER);
		
		tFirst.setColor(0.5F, 0.5F, 0.5F);
		tSecond.setColor(0.5F, 0.5F, 0.5F);
		tInput.setColor(0.5F, 0.5F, 0.5F);
		
		tFirst.setText("");
		tSecond.setText("");
		tInput.setText("");
		
		attachChild(tFirst);
		attachChild(tSecond);
		attachChild(tInput);
		attachChild(tTitle);
	}
	private void Solve(String result)
	{
		final Rectangle CorrectEffect = new Rectangle(36, 547, 647, 104, _VBO);
		CorrectEffect.setColor(Color.GREEN);
		CorrectEffect.setAlpha(0F);
		
		final Rectangle IncorrectEffect = new Rectangle(36, 547, 647, 104, _VBO);
		IncorrectEffect.setColor(Color.RED);
		IncorrectEffect.setAlpha(0F);
		
		int _result = Integer.parseInt(result);
		if(_result == iResult)
		{
			CorrectEffect.registerEntityModifier(new AlphaModifier(0.5F, 0.5F, 0F));
			Correct++;
		}
		if(_result != iResult)
		{
			IncorrectEffect.registerEntityModifier(new AlphaModifier(0.5F, 0.5F, 0F));
			Incorrect++;
		}
		sInput = "";
		tInput.setText("");
		attachChild(CorrectEffect);
		attachChild(IncorrectEffect);
		
		Generate();
	}
	private void Board()
	{
		Button(36, 716, 215, 108, 1);
		Button(253, 716, 215, 108, 2);
		Button(470, 716, 215, 108, 3);
		Button(36, 826, 215, 108, 4);
		Button(253, 826, 215, 108, 5);
		Button(470, 826, 215, 108, 6);
		Button(36, 934, 215, 108, 7);
		Button(253, 934, 215, 108, 8);
		Button(470, 934, 215, 108, 9);
		Button(253, 1043, 215, 108, 0);
		Button(36, 1043, 215, 108, -1);
		Button(470, 1043, 215, 108, -2);
		Button(41, 81, 60, 60, -3);
		Button(625, 81, 60, 60, -4);
	}
	private void Generate()
	{
		if(Correct == 0) Correct = 1;
		iSecond = (int)(Math.random() * 8 + 2);
		iFirst = (int)(Math.random() * 3 + 2);
		iThird = (int) Math.pow(iSecond, iFirst);
		iResult = iFirst;
		
		tFirst.setText("Log"+"  "+iThird+"");
		tSecond.setText(""+iSecond);
		
		tFirst.setPosition(360 - tFirst.getWidth()/2, 350);
		tSecond.setPosition(tFirst.getX() + 100, 370);
	}
	private void Button(int x, int y, int w, int h, final int v)
	{
		final Rectangle Button = new Rectangle(x, y, w, h, _VBO)
		{
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY)
			{
				if(pSceneTouchEvent.isActionDown())
				{
					this.registerEntityModifier(new AlphaModifier(0.5F, 0.5F, 0F));
					if(v == -1)
					{
						tInput.setText("");
						sInput = "";
					}
					else if(v == -2 && sInput != "")
					{
						Solve(sInput);
					}
					else if(v == -3)
					{
						Scenes.getInstance().CreateGameScene(_Engine);
					}
					else if(v == -4)
					{
						Uri URL = Uri.parse("https://ru.wikipedia.org/wiki/%D0%9B%D0%BE%D0%B3%D0%B0%D1%80%D0%B8%D1%84%D0%BC");
						Intent LINK = new Intent(Intent.ACTION_VIEW, URL);
						_Activity.startActivity(LINK);
					}
					else if(v > -1)
					{
						if(sInput.length() < 10) sInput += ""+v;
						tInput.setText(sInput);
						tInput.setX(360-tInput.getWidth()/2);
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
	@Override public SceneType getSceneType(){return SceneType.LOGARITHM;}
	@Override public void disposeScene(){}
	@Override public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent){return false;}
	@Override public boolean onAreaTouched(TouchEvent pSceneTouchEvent, ITouchArea pTouchArea, float pTouchAreaLocalX, float pTouchAreaLocalY){return false;}
}