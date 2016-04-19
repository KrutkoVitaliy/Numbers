package com.gameuniverse.material.scenes;

import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.IOnAreaTouchListener;
import org.andengine.entity.scene.ITouchArea;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;

import com.gameuniverse.material.managers.Scenes;
import com.gameuniverse.material.managers.Scenes.SceneType;

import android.widget.Toast;

public class Game extends Base implements IOnAreaTouchListener
{
	Rectangle _Zero, _One, _Two, _Three, _Four, _Five, _Six, _Seven, _Eight, _Nine, _Del, _Ok;
	int _MyResult, _ProgramResult;
	String _sMyResult = "";
	Text _EnterResult;
	Text[] _ButtonText = new Text[12];
	
	int Seconds, Minutes, Hours, Exit = 2, _Correct, _Incorrect, _CurrentMode;
	final static float _ColorUnit = 3.921568627F;
	Rectangle _SelectedItem;
	Text Time, _TitleText, _GamingTime, _CorrectText, _IncorrectText, _PercentageText;
	
	@Override
	public void createScene()
	{
		Background();
		Manage();
	}
	private void Background()
	{
		final Sprite Background = new Sprite(0, 0, _Resources.SelectLevel, _VBO);
		Background.registerEntityModifier(new AlphaModifier(0.5F, 0F, 1F));
		attachChild(Background);
	}
	private void Manage()
	{
		Button(37, 286, 140, 70, 1);
		Button(205, 286, 140, 70, 2);
		Button(373, 286, 140, 70, 3);
		Button(541, 286, 140, 70, 4);
		Button(37, 388, 140, 70, 5);
		Button(205, 388, 140, 70, 6);
		Button(373, 388, 140, 70, 7);
		Button(541, 388, 140, 70, 8);
		Button(205, 490, 140, 70, 9);
		Button(373, 490, 140, 70, 10);
		Button(37, 885, 644, 70, 14);
		Button(37, 972, 644, 70, 15);
		Button(37, 1060, 644, 70, 16);
		Button(37, 1148, 644, 70, 17);
		Button(626, 78, 66, 66, 18);
	}
	private void Button(final int x, final int y, final int w, final int h, final int m)
	{
		final Rectangle Button = new Rectangle(x, y, w, h, _VBO)
		{
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY)
			{
				if(pSceneTouchEvent.isActionDown())
				{
					this.registerEntityModifier(new AlphaModifier(0.5F, 0.5F, 0F));
					Scenes.getInstance().CreateGameModeScene(_Engine, m);
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
	@Override public SceneType getSceneType(){ return SceneType.SCENE_GAME; }
	@Override public void disposeScene() { this.detachSelf(); this.dispose(); }
	@Override public boolean onAreaTouched(TouchEvent pSceneTouchEvent, ITouchArea pTouchArea, float pTouchAreaLocalX, float pTouchAreaLocalY) { return false; }
}