package com.gameuniverse.material;

import org.andengine.engine.camera.BoundCamera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.ui.activity.BaseGameActivity;

import com.gameuniverse.material.managers.Resources;
import com.gameuniverse.material.managers.Scenes;

import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.WindowManager;

public class Activity extends BaseGameActivity
{
	private BoundCamera	_Camera;
	private static int _Camera_Width = 720, _Camera_Height = 1280, _Width, _Height;
	
	@Override
	public EngineOptions onCreateEngineOptions() 
	{
		final DisplayMetrics _DM = new DisplayMetrics();
		WindowManager _WM = (WindowManager)getSystemService(WINDOW_SERVICE);
		_WM.getDefaultDisplay().getMetrics(_DM);
		_Width = _DM.widthPixels;
		_Height = _DM.heightPixels;
		_Camera = new BoundCamera(0, 0, _Camera_Width, _Camera_Height);
		EngineOptions _EO = new EngineOptions(true,ScreenOrientation.PORTRAIT_FIXED, new RatioResolutionPolicy(_Width, _Height), _Camera);
		_EO.getAudioOptions().setNeedsMusic(true).setNeedsSound(true);
		_EO.setWakeLockOptions(WakeLockOptions.SCREEN_ON);
		_EO.getTouchOptions().setNeedsMultiTouch(true);
		return _EO;
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{  
	    if (keyCode == KeyEvent.KEYCODE_BACK)
	        Scenes.getInstance().getCurrentScene().onBackKeyPressed();
	    return false; 
	}
	
	@Override
	public void onCreateResources(OnCreateResourcesCallback pOnCreateResourcesCallback)	throws Exception 
	{
		Resources.prepareManager(mEngine, this, _Camera, getVertexBufferObjectManager());
		pOnCreateResourcesCallback.onCreateResourcesFinished();
	}

	@Override
	public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback) throws Exception 
	{
		Scenes.getInstance().CreateSplashScene(pOnCreateSceneCallback);
	}

	@Override
	public void onPopulateScene(Scene pScene, OnPopulateSceneCallback pOnPopulateSceneCallback) throws Exception 
	{
		mEngine.registerUpdateHandler(new TimerHandler(0.01F, new ITimerCallback() 
	    {
	        public void onTimePassed(final TimerHandler pTimerHandler) 
	        {
	            mEngine.unregisterUpdateHandler(pTimerHandler);
	            Scenes.getInstance().CreateMenuScene(mEngine);
	        }
	    }));
	    pOnPopulateSceneCallback.onPopulateSceneFinished();
	}
	
	@Override
	public void onResume() 
	{
		super.onResume();
	}
	@Override
	public void onPause() 
	{
		super.onPause();
	}
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		android.os.Process.killProcess(android.os.Process.myPid());
	}
}
