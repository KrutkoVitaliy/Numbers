package com.gameuniverse.material.scenes;

import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.IOnAreaTouchListener;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.ITouchArea;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;

import com.gameuniverse.material.managers.Scenes;
import com.gameuniverse.material.managers.Storage;
import com.gameuniverse.material.managers.Scenes.SceneType;

import android.content.Intent;
import android.net.Uri;

public class Settings extends Base implements IOnAreaTouchListener, IOnSceneTouchListener
{
	int Seconds, Minutes, Hours, Correct, Incorrect;
	
	@Override public void createScene()
	{
		Load();
		Background();
		Manage();
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
	private void Background()
	{
		final Sprite Background = new Sprite(0, 0, _Resources.SettingsBackground, _VBO);
		attachChild(Background);
	}
	private void Manage()
	{
		Button(39, 210, 640, 66, 1);
		Button(39, 310, 640, 66, 2);
		Button(39, 410, 640, 66, 3);
		Button(41, 81, 60, 60, 4);
	}
	private void Button(int x, int y, int w, int h, final int v)
	{
		final Rectangle Button = new Rectangle(x, y, w, h, _VBO)
		{
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY)
			{
				if(pSceneTouchEvent.isActionDown())
				{
					switch(v)
					{
						case 1:
							Intent _RateApp = new Intent(Intent.ACTION_VIEW);
							_RateApp.setData(Uri.parse("market://details?id=com.gameuniverse.material"));
							_Activity.startActivity(_RateApp);
							break;
						case 2:Scenes.getInstance().CreateStatisticsScene(_Engine);break;
						case 3:
							Storage.Init(_Activity);
							Storage.Clear("Seconds");
							Storage.Clear("Minutes");
							Storage.Clear("Hours");
							Storage.Clear("Correct");
							Storage.Clear("Incorrect");
							android.os.Process.killProcess(android.os.Process.myPid());
							break;
						case 4:Scenes.getInstance().CreateGameScene(_Engine);break;
						
					}
					return true;
				}
				return false;
			};
		};
		Button.setAlpha(0F);
		registerTouchArea(Button);
		attachChild(Button);
	}
	@Override public void onBackKeyPressed(){Scenes.getInstance().CreateGameScene(_Engine);}
	@Override public SceneType getSceneType(){return SceneType.SCENE_SETTINGS;}
	@Override public void disposeScene(){}
	@Override public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent){return false;}
	@Override public boolean onAreaTouched(TouchEvent pSceneTouchEvent, ITouchArea pTouchArea, float pTouchAreaLocalX, float pTouchAreaLocalY){return false;}
}