package com.gameuniverse.material.managers;

import org.andengine.engine.Engine;
import org.andengine.ui.IGameInterface.OnCreateSceneCallback;

import com.gameuniverse.material.gamescenes.Addition;
import com.gameuniverse.material.gamescenes.Cube;
import com.gameuniverse.material.gamescenes.Differentiation;
import com.gameuniverse.material.gamescenes.Division;
import com.gameuniverse.material.gamescenes.Fraction;
import com.gameuniverse.material.gamescenes.Integrals;
import com.gameuniverse.material.gamescenes.Limits;
import com.gameuniverse.material.gamescenes.Logarithm;
import com.gameuniverse.material.gamescenes.Multiplication;
import com.gameuniverse.material.gamescenes.Percentage;
import com.gameuniverse.material.gamescenes.Root;
import com.gameuniverse.material.gamescenes.Series;
import com.gameuniverse.material.gamescenes.Square;
import com.gameuniverse.material.gamescenes.Subtraction;
import com.gameuniverse.material.scenes.Base;
import com.gameuniverse.material.scenes.Game;
import com.gameuniverse.material.scenes.Menu;
import com.gameuniverse.material.scenes.Settings;
import com.gameuniverse.material.scenes.Splash;
import com.gameuniverse.material.scenes.Statistics;

public class Scenes 
{
	public Base _CurrentScene;
	public Base _SplashScene;
	public Base _MenuScene;
	public Base _GameScene;
	public Base _SettingsScene;
	public Base _StatisticsScene;
	
	public Base _AdditionScene;
	public Base _SubtractionScene;
	public Base _MultiplicationScene;
	public Base _DivisionScene;
	public Base _SquareScene;
	public Base _CubeScene;
	public Base _RootScene;
	public Base _LogarithmScene;
	public Base _PercentageScene;
	public Base _FractionScene;
	public Base _LimitsScene;
	public Base _DifferentiationScene;
	public Base _IntegralsScene;
	public Base _SeriesScene;
	
	private boolean _SplashIsLoaded = false;
	private boolean _MenuIsLoaded = false;
	private boolean _GameIsLoaded = false;
	
	private static final Scenes	INSTANCE = new Scenes();
	private SceneType _CurrentSceneType = SceneType.SCENE_SPLASH;
	private Engine _Engine = Resources.getInstance()._Engine;
	
	public enum SceneType
	{
		SCENE_SPLASH,
		SCENE_MENU,
		SCENE_GAME,
		SCENE_SETTINGS,
		SCENE_STATISTICS,
		
		ADDITION, SUBTRACTION, MULTIPLICATION, DIVISION, SQUARE, CUBE, ROOT, LOGARITHM, PERCENTAGE, FRACTION, INTEGRALS,
		DIFFERENTIATION, SERIES, LIMITS
	}
	
	public void setScene (Base _Scene)
	{
		_Engine.setScene(_Scene);
		_CurrentScene = _Scene;
		_CurrentSceneType = _Scene.getSceneType();
	}
	
	public void setScene (SceneType sceneType)
	{
		switch (sceneType)
		{
			case SCENE_SPLASH:setScene(_SplashScene);break;
			case SCENE_MENU:setScene(_MenuScene);break;
			case SCENE_GAME:setScene(_GameScene);break;
			case SCENE_SETTINGS:setScene(_SettingsScene);break;
			case SCENE_STATISTICS:setScene(_StatisticsScene);break;
			
			case ADDITION:setScene(_AdditionScene);break;
			case SUBTRACTION:setScene(_SubtractionScene);break;
			case MULTIPLICATION:setScene(_MultiplicationScene);break;
			case DIVISION:setScene(_DivisionScene);break;
			case SQUARE:setScene(_SquareScene);break;
			case CUBE:setScene(_CubeScene);break;
			case ROOT:setScene(_RootScene);break;
			case LOGARITHM:setScene(_LogarithmScene);break;
			case PERCENTAGE:setScene(_PercentageScene);break;
			case FRACTION:setScene(_FractionScene);break;
			case INTEGRALS:setScene(_IntegralsScene);break;
			case DIFFERENTIATION:setScene(_DifferentiationScene);break;
			case SERIES:setScene(_SeriesScene);break;
			case LIMITS:setScene(_LimitsScene);break;
			default:break;
		}
	}
	
	public static Scenes getInstance() 
	{ 
		return INSTANCE;
	}
	
	public SceneType getCurrentSceneType() 
	{
		return _CurrentSceneType;
	}
	
	public Base getCurrentScene() 
	{
		return _CurrentScene;
	}
	
	public void CreateSplashScene(OnCreateSceneCallback pOnCreateSceneCallback)
	{
		CreateResources(0);
		CreateResources(1);
		_SplashScene = new Splash();
		_CurrentScene = _SplashScene;
		pOnCreateSceneCallback.onCreateSceneFinished(_SplashScene);
	}
	public void CreateMenuScene(final Engine mEngine)
	{
		CreateResources(2);
		if(_SplashIsLoaded == true)DestroyResources(1);
		if(_GameIsLoaded == true)DestroyResources(3);
		_MenuScene = new Menu();
		setScene(_MenuScene);
	}
	public void CreateSettingsScene(final Engine mEngine)
	{
		_SettingsScene = new Settings();
		setScene(_SettingsScene);
	}
	public void CreateStatisticsScene(final Engine mEngine)
	{
		_StatisticsScene = new Statistics();
		setScene(_StatisticsScene);
	}
	public void CreateGameModeScene(final Engine mEngine, final int m)
	{
		switch(m)
		{
			case 1:CreateAdditionScene(_Engine);break;
			case 2:CreateSubtractionScene(_Engine);break;
			case 3:CreateMultiplicationScene(_Engine);break;
			case 4:CreateDivisionScene(_Engine);break;

			case 5:CreatePercentageScene(_Engine);break;
			case 6:CreateRootScene(_Engine);break;
			case 7:CreateFractionScene(_Engine);break;
			case 8:CreateLogarithmScene(_Engine);break;
			
			case 9:CreateSquareScene(_Engine);break;
			case 10:CreateCubeScene(_Engine);break;
			
			case 14:CreateLimitsScene(_Engine);break;
			case 15:CreateDifferentiationScene(_Engine);break;
			case 16:CreateIntegralsScene(_Engine);break;
			case 17:CreateSeriesScene(_Engine);break;
			
			case 18:CreateSettingsScene(_Engine);break;
		}
	}
	public void CreateGameScene(final Engine mEngine)
	{
		if(_GameIsLoaded == false)CreateResources(3);
		if(_MenuIsLoaded == true)DestroyResources(2);
		_GameScene = new Game();
		setScene(_GameScene);
	}
	public void CreateAdditionScene(final Engine mEngine)
	{
		_AdditionScene = new Addition();
		setScene(_AdditionScene);
	};
	public void CreateSubtractionScene(final Engine mEngine)
	{
		_SubtractionScene = new Subtraction();
		setScene(_SubtractionScene);
	};
	public void CreateMultiplicationScene(final Engine mEngine)
	{
		_MultiplicationScene = new Multiplication();
		setScene(_MultiplicationScene);
	};
	public void CreateDivisionScene(final Engine mEngine)
	{
		_DivisionScene = new Division();
		setScene(_DivisionScene);
	};
	public void CreateSquareScene(final Engine mEngine)
	{
		_SquareScene = new Square();
		setScene(_SquareScene);
	};
	public void CreateCubeScene(final Engine mEngine)
	{
		_CubeScene = new Cube();
		setScene(_CubeScene);
	};
	public void CreateRootScene(final Engine mEngine)
	{
		_RootScene = new Root();
		setScene(_RootScene);
	};
	public void CreateLogarithmScene(final Engine mEngine)
	{
		_LogarithmScene = new Logarithm();
		setScene(_LogarithmScene);
	};
	public void CreatePercentageScene(final Engine mEngine)
	{
		_PercentageScene = new Percentage();
		setScene(_PercentageScene);
	};
	public void CreateFractionScene(final Engine mEngine)
	{
		_FractionScene = new Fraction();
		setScene(_FractionScene);
	};
	public void CreateLimitsScene(final Engine mEngine)
	{
		_LimitsScene = new Limits();
		setScene(_LimitsScene);
	};
	public void CreateDifferentiationScene(final Engine mEngine)
	{
		_DifferentiationScene = new Differentiation();
		setScene(_DifferentiationScene);
	};
	public void CreateIntegralsScene(final Engine mEngine)
	{
		_IntegralsScene = new Integrals();
		setScene(_IntegralsScene);
	};
	public void CreateSeriesScene(final Engine mEngine)
	{
		_SeriesScene = new Series();
		setScene(_SeriesScene);
	};

	public void DestroyResources(int SceneName)
	{
		switch(SceneName)
		{
		/*Menu*/case 2:Resources.getInstance().DestroyMenuResources(); _MenuIsLoaded = false; _MenuScene.disposeScene(); break;
		/*Game*/case 3:Resources.getInstance().DestroyGameResources(); _GameIsLoaded = false; _GameScene.disposeScene(); break;
		default: ; break;
		}
	}
	public void CreateResources(int SceneName)
	{
		switch(SceneName)
		{
		/*Menu*/case 2:Resources.getInstance().CreateMenuResources(); _MenuIsLoaded = true; break;
		/*Game*/case 3:Resources.getInstance().CreateGameResources(); _GameIsLoaded = true; break;
		default: Resources.getInstance().ResourcesPathsInitialization(); break;
		}
	}
}