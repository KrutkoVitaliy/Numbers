package com.gameuniverse.material.managers;

import org.andengine.audio.music.MusicFactory;
import org.andengine.audio.sound.SoundFactory;
import org.andengine.engine.Engine;
import org.andengine.engine.camera.BoundCamera;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.gameuniverse.material.Activity;

import android.graphics.Color;

public class Resources 
{
	private static final Resources INSTANCE = new Resources();
	public Engine _Engine;
	public Activity	_Activity;
	public BoundCamera _Camera;
	public VertexBufferObjectManager _VBO;
	
	public BitmapTextureAtlas _SplashTA, _MenuTA, _GameTA, _GameTA2, _GameTA3;
	public BitmapTextureAtlas _CalcAtlas;
	public ITextureRegion[] Calc = new ITextureRegion[11];
	public BitmapTextureAtlas _PlayBoldTA, _OswaldLightSmallTA, _MenuOswaldLightSmallTA, _OswaldLightLargeTA, _PlayTA;
	public ITextureRegion _SplashTR, _MenuTR, SelectLevel, LevelBackGround, SettingsBackground, StatisticsBackground;
	public ITextureRegion _UpgradesTR, _SettingsTR;
	public ITextureRegion _LogoTR, _AppName, _Play, TestBackground;
	//public TiledTextureRegion 
	public Font _PlayBoldF, _OswaldLightSmallF, _MenuOswaldLightSmallF, _OswaldLightLargeF, _PlayF;
	//public Sound 
	//public Music 
	
	public void ResourcesPathsInitialization()
	{
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("Graphics/");
		FontFactory.setAssetBasePath("Font/");
		SoundFactory.setAssetBasePath("Sound/");
		MusicFactory.setAssetBasePath("Music/");
	}
	
	public void CreateMenuResources()
	{
		_MenuTA = new BitmapTextureAtlas(_Activity.getTextureManager(), 2048, 2048, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		_AppName = BitmapTextureAtlasTextureRegionFactory.createFromAsset(_MenuTA, _Activity, "AppName.png", 0, 0);
		_Play = BitmapTextureAtlasTextureRegionFactory.createFromAsset(_MenuTA, _Activity, "Play.png", 500, 0);
		_MenuTA.load();
		
		_MenuOswaldLightSmallTA = new BitmapTextureAtlas(_Activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		_MenuOswaldLightSmallF = FontFactory.createStrokeFromAsset(_Activity.getFontManager(), _MenuOswaldLightSmallTA, _Activity.getAssets(), "Oswald-Light.ttf", 36, true, Color.WHITE, 0, Color.WHITE);
		_MenuOswaldLightSmallF.load();
	}
	public void DestroyMenuResources()
	{
		_MenuTA.unload();
		_MenuOswaldLightSmallF.unload();
	}
	
	public void CreateGameResources()
	{
		_GameTA = new BitmapTextureAtlas(_Activity.getTextureManager(), 2048, 2048, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		SelectLevel = BitmapTextureAtlasTextureRegionFactory.createFromAsset(_GameTA, _Activity, "SelectLevel.png", 0, 0);
		LevelBackGround = BitmapTextureAtlasTextureRegionFactory.createFromAsset(_GameTA, _Activity, "GameBackground.png", 721, 0);
		_GameTA.load();
		
		_GameTA2 = new BitmapTextureAtlas(_Activity.getTextureManager(), 2048, 2048, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		SettingsBackground = BitmapTextureAtlasTextureRegionFactory.createFromAsset(_GameTA2, _Activity, "SettingsBackground.png", 0, 0);
		StatisticsBackground = BitmapTextureAtlasTextureRegionFactory.createFromAsset(_GameTA2, _Activity, "StatisticsBackground.png", 721, 0);
		_GameTA2.load();
		
		_GameTA3 = new BitmapTextureAtlas(_Activity.getTextureManager(), 2048, 2048, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		TestBackground = BitmapTextureAtlasTextureRegionFactory.createFromAsset(_GameTA3, _Activity, "TestBackground.png", 0, 0);
		_GameTA3.load();
		
		_CalcAtlas = new BitmapTextureAtlas(_Activity.getTextureManager(), 2048, 2048, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		Calc[0] = BitmapTextureAtlasTextureRegionFactory.createFromAsset(_CalcAtlas, _Activity, "1_1.png", 0, 0);
		Calc[1] = BitmapTextureAtlasTextureRegionFactory.createFromAsset(_CalcAtlas, _Activity, "1_2.png", 0, 261);
		Calc[2] = BitmapTextureAtlasTextureRegionFactory.createFromAsset(_CalcAtlas, _Activity, "1_3.png", 0, 522);
		Calc[3] = BitmapTextureAtlasTextureRegionFactory.createFromAsset(_CalcAtlas, _Activity, "2_1.png", 650, 0);
		Calc[4] = BitmapTextureAtlasTextureRegionFactory.createFromAsset(_CalcAtlas, _Activity, "2_2.png", 650, 261);
		Calc[5] = BitmapTextureAtlasTextureRegionFactory.createFromAsset(_CalcAtlas, _Activity, "2_3.png", 650, 522);
		Calc[6] = BitmapTextureAtlasTextureRegionFactory.createFromAsset(_CalcAtlas, _Activity, "3_1.png", 1301, 0);
		Calc[7] = BitmapTextureAtlasTextureRegionFactory.createFromAsset(_CalcAtlas, _Activity, "3_2.png", 1301, 261);
		Calc[8] = BitmapTextureAtlasTextureRegionFactory.createFromAsset(_CalcAtlas, _Activity, "3_3.png", 1301, 522);
		Calc[9] = BitmapTextureAtlasTextureRegionFactory.createFromAsset(_CalcAtlas, _Activity, "4_1.png", 0, 783);
		Calc[10] = BitmapTextureAtlasTextureRegionFactory.createFromAsset(_CalcAtlas, _Activity, "4_2.png", 650, 783);
		_CalcAtlas.load();
		
		_PlayBoldTA = new BitmapTextureAtlas(_Activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		_PlayBoldF = FontFactory.createStrokeFromAsset(_Activity.getFontManager(), _PlayBoldTA, _Activity.getAssets(), "Play-Bold.ttf", 42, true, Color.WHITE, 0, Color.WHITE);
		_PlayBoldF.load();
		_PlayTA = new BitmapTextureAtlas(_Activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		_PlayF = FontFactory.createStrokeFromAsset(_Activity.getFontManager(), _PlayTA, _Activity.getAssets(), "Play-Regular.ttf", 42, true, Color.WHITE, 0, Color.WHITE);
		_PlayF.load();
		_OswaldLightSmallTA = new BitmapTextureAtlas(_Activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		_OswaldLightSmallF = FontFactory.createStrokeFromAsset(_Activity.getFontManager(), _OswaldLightSmallTA, _Activity.getAssets(), "Oswald-Light.ttf", 36, true, Color.WHITE, 0, Color.WHITE);
		_OswaldLightSmallF.load();
		_OswaldLightLargeTA = new BitmapTextureAtlas(_Activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		_OswaldLightLargeF = FontFactory.createStrokeFromAsset(_Activity.getFontManager(), _OswaldLightLargeTA, _Activity.getAssets(), "Helios.ttf", 72, true, Color.WHITE, 0, Color.WHITE);
		_OswaldLightLargeF.load();
	}
	public void DestroyGameResources()
	{
		_GameTA.unload();
		_GameTA2.unload();
		_OswaldLightLargeF.unload();
		_PlayBoldF.unload();
		_PlayF.unload();
		_OswaldLightSmallF.unload();
	}
	
	public static void prepareManager (Engine _Engine, Activity _Activity,BoundCamera _Camera, VertexBufferObjectManager _VBO)
	{
		getInstance()._Engine = _Engine;
		getInstance()._Activity = _Activity;
		getInstance()._Camera = _Camera;
		getInstance()._VBO = _VBO;
	}
	public static Resources getInstance ()
	{
		return INSTANCE;
	}
}
