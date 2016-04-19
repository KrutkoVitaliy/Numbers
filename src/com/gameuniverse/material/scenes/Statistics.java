package com.gameuniverse.material.scenes;

import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.IOnAreaTouchListener;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.ITouchArea;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;

import com.gameuniverse.material.managers.Scenes;
import com.gameuniverse.material.managers.Storage;
import com.gameuniverse.material.managers.Scenes.SceneType;

public class Statistics extends Base implements IOnAreaTouchListener, IOnSceneTouchListener
{
	int Seconds, Minutes, Hours, Correct, Incorrect;
	private Text tTime, tCorrect, tIncorrect, tAll;
	
	@Override public void createScene()
	{
		Load();
		Background();
		Manage();
		Titles();
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
		final Sprite Background = new Sprite(0, 0, _Resources.StatisticsBackground, _VBO);
		attachChild(Background);
	}
	private void Manage()
	{
		final Rectangle Button = new Rectangle(41, 81, 60, 60, _VBO)
		{
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY)
			{
				if(pSceneTouchEvent.isActionDown())
				{
					Scenes.getInstance().CreateSettingsScene(_Engine);
					return true;
				}
				return false;
			};
		};
		Button.setAlpha(0F);
		registerTouchArea(Button);
		attachChild(Button);
	}
	private void Titles()
	{
		tTime = new Text(0, 0, _Resources._PlayF, "0 1 2 3 4 5 6 7 8 9 :", _VBO);
		tCorrect = new Text(0, 0, _Resources._PlayF, "0 1 2 3 4 5 6 7 8 9 ", _VBO);
		tIncorrect = new Text(0, 0, _Resources._PlayF, "0 1 2 3 4 5 6 7 8 9 ", _VBO);
		tAll = new Text(0, 0, _Resources._PlayF, "0 1 2 3 4 5 6 7 8 9 ", _VBO);
		
		if(Hours < 10)
			tTime.setText("0"+Hours+":");
		else
			tTime.setText(Hours+":");
		
		if(Minutes < 10)
			tTime.setText(tTime.getText()+"0"+Minutes+":");
		else
			tTime.setText(tTime.getText()+""+Minutes+":");
		
		if(Seconds <10)
			tTime.setText(tTime.getText()+"0"+Seconds);
		else
			tTime.setText(tTime.getText()+""+Seconds);

		tCorrect.setText(""+Correct);
		tIncorrect.setText(""+Incorrect);
		tAll.setText(""+(Correct+Incorrect));
		
		tTime.setColor(0.5F, 0.5F, 0.5F);
		tCorrect.setColor(0.5F, 0.5F, 0.5F);
		tIncorrect.setColor(0.5F, 0.5F, 0.5F);
		tAll.setColor(0.5F, 0.5F, 0.5F);
		
		tTime.setPosition(355 - tTime.getWidth() / 2, 338);
		tCorrect.setPosition(355 - tCorrect.getWidth() / 2, 733);
		tIncorrect.setPosition(355 - tIncorrect.getWidth() / 2, 930);
		tAll.setPosition(355 - tAll.getWidth() / 2, 541);
		
		attachChild(tTime);
		attachChild(tCorrect);
		attachChild(tIncorrect);
		attachChild(tAll);
		
		
		
	}
	@Override public void onBackKeyPressed(){Scenes.getInstance().CreateSettingsScene(_Engine);}
	@Override public SceneType getSceneType(){return SceneType.SCENE_STATISTICS;}
	@Override public void disposeScene(){}
	@Override public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent){return false;}
	@Override public boolean onAreaTouched(TouchEvent pSceneTouchEvent, ITouchArea pTouchArea, float pTouchAreaLocalX, float pTouchAreaLocalY){return false;}
}