package com.gameuniverse.material.scenes;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.BoundCamera;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.gameuniverse.material.Activity;
import com.gameuniverse.material.managers.Resources;
import com.gameuniverse.material.managers.Scenes.SceneType;

public abstract class Base extends Scene
{
	protected Engine _Engine;
	protected Activity _Activity;
	protected BoundCamera _Camera;
	protected Resources _Resources;
	protected VertexBufferObjectManager _VBO;
	protected float _ColorUnit;

	public Base()
	{
		this._ColorUnit = 3.921568627F;
		this._Resources = Resources.getInstance();
		this._Engine = _Resources._Engine;
		this._Activity = _Resources._Activity;
		this._Camera = _Resources._Camera;
		this._VBO = _Resources._VBO;
		createScene();
	}

	public abstract void createScene();
	public abstract void onBackKeyPressed();
	public abstract SceneType getSceneType();
	public abstract void disposeScene();
}
