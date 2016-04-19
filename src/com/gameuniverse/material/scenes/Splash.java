package com.gameuniverse.material.scenes;

import com.gameuniverse.material.managers.Scenes.SceneType;

public class Splash extends Base
{
	
	@Override
	public void createScene() 
	{
		
	}
	
	@Override
	public void onBackKeyPressed() 
	{
		
	}
	
	@Override
	public SceneType getSceneType() 
	{
		return SceneType.SCENE_SPLASH;
	}
	
	@Override
	public void disposeScene()
	{
		this.detachSelf();
		this.dispose();
	}
}
