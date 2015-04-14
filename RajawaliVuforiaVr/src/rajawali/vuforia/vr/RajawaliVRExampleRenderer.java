package rajawali.vuforia.vr;

import java.io.File;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

import org.rajawali3d.Camera;
import org.rajawali3d.Object3D;
import org.rajawali3d.SerializedObject3D;
import org.rajawali3d.animation.mesh.SkeletalAnimationObject3D;
import org.rajawali3d.animation.mesh.SkeletalAnimationSequence;
import org.rajawali3d.bounds.IBoundingVolume;
import org.rajawali3d.lights.DirectionalLight;
import org.rajawali3d.materials.Material;
import org.rajawali3d.materials.methods.DiffuseMethod;
import org.rajawali3d.materials.textures.Texture;
import org.rajawali3d.math.Matrix4;
import org.rajawali3d.math.Quaternion;
import org.rajawali3d.math.vector.Vector3;
import org.rajawali3d.loader.Loader3DSMax;
import org.rajawali3d.loader.LoaderAWD;
import org.rajawali3d.loader.LoaderOBJ;
import org.rajawali3d.loader.ParsingException;
import org.rajawali3d.loader.md5.LoaderMD5Anim;
import org.rajawali3d.loader.md5.LoaderMD5Mesh;
import org.rajawali3d.primitives.Cube;
import org.rajawali3d.primitives.Plane;
import org.rajawali3d.surface.IRajawaliSurfaceRenderer;
import org.rajawali3d.terrain.SquareTerrain;
import org.rajawali3d.util.MeshExporter;
import org.rajawali3d.util.exporter.AExporter;
import org.rajawali3d.util.exporter.AwdExporter;
import org.rajawali3d.util.exporter.SerializationExporter;

import rajawali.vr.RajawaliVRRenderer;

import org.rajawali3d.loader.AMeshLoader;

import com.google.vrtoolkit.cardboard.sensors.HeadTracker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.Bitmap.Config;
import android.graphics.Paint.Align;
import android.graphics.PorterDuff.Mode;
import android.os.Build;
import android.os.Vibrator;
import android.text.Html;
import android.util.Log;
import android.view.InputDevice;
import android.view.KeyEvent;


public class RajawaliVRExampleRenderer extends RajawaliVRRenderer implements IRajawaliSurfaceRenderer {
	float count = 0;
	Quaternion mPlayerOrientation = new Quaternion();
	Quaternion q = new Quaternion();
	Object3D room;
	ArrayList<String> objects = new ArrayList<String>();
	Resources res;
	String tr, tl;
	SkeletalAnimationObject3D player;
	Object3D playerbox;
	boolean intersected = false;
	boolean loaded = false;
	Object3D[] colliders = new Object3D[20];
	IBoundingVolume bbox, bbox2;
	Vector3 oldpos = new Vector3(0,0,0);
	
	public RajawaliVRExampleRenderer(Context context) {
		super(context);
		res = context.getResources();
	}
	
	public void setHeadTracker(HeadTracker headTracker){
		super.setHeadTracker(headTracker);
	}
	
	@Override
	public void initScene() {
		
		DirectionalLight light = new DirectionalLight(0.2f, -1f, 0f);
		light.setPower(.7f);
		getCurrentScene().addLight(light);
		
		light = new DirectionalLight(0.2f, 1f, 0f);
		light.setPower(1f);
		getCurrentScene().addLight(light);
		
		getCurrentCamera().setFarPlane(10000);
		//getCurrentCamera().setFieldOfView(120);
		
		getCurrentScene().setBackgroundColor(0xdddddd);
		
		try {
			
			getCurrentScene().setSkybox(R.drawable.posx, R.drawable.negx, R.drawable.posy, R.drawable.negy, R.drawable.posz, R.drawable.negz);
			objects.add("SkyBox");			
			
			player = showMonster("player", new Vector3( 0f, -1.5f, -1.5), new Vector3(0,180,0), new Vector3(.5f));
		    player.setRotY(180);
		    player.setFps(15);
			loadAnim2Obj(player,"player_idle", false); 
			
			Material cM = new Material();
			playerbox = new Cube(1);
			playerbox.setShowBoundingVolume(true);
			playerbox.setMaterial(cM);
			playerbox.setVisible(true);
			getCurrentScene().addChild(playerbox);
			
			
			//Loader3DSMax loader = new Loader3DSMax(this, R.raw.room2);
			LoaderAWD loader = new LoaderAWD(mContext.getResources(), mTextureManager, R.raw.model);
			//LoaderOBJ loader = new LoaderOBJ(mContext.getResources(), mTextureManager, R.raw.torbogen_obj);
			
			loader.parse();
			
			Material roommat = new Material();
			roommat.setDiffuseMethod(new DiffuseMethod.Lambert());
			roommat.setColorInfluence(0);
			roommat.enableLighting(true);
			roommat.addTexture(new Texture("roommat", R.drawable.desert));
			
			room = loader.getParsedObject();
			for (int i = 0; i < room.getNumChildren(); i++){
				String Name = room.getChildAt(i).getName();
				if(Name.startsWith("collider")){ 
					colliders[i]  = room.getChildAt(i).clone();
					colliders[i].setVisible(false);
				}
			}
			room.setMaterial(roommat);
			room.setDoubleSided(true);
			room.setScale(.05,.05,.05);
			room.setPosition(-10,-2,-10);
			room.setShowBoundingVolume(true);
			getCurrentScene().addChild(room);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		super.initScene();
	}
	
	public SkeletalAnimationObject3D showMonster(String Monster, Vector3 pos, Vector3 rot, Vector3 scale, int fps){
		
	    SkeletalAnimationObject3D mObject = new SkeletalAnimationObject3D();
	    		
		int mesh = getContext().getResources().getIdentifier(Monster + "_mesh", "raw", "rajawali.vuforia.vr");
		//scale = scale.multiply(0.01f); 
		
		try {
			LoaderMD5Mesh meshParser = new LoaderMD5Mesh(this,
					mesh);
			meshParser.parse();

			mObject = (SkeletalAnimationObject3D) meshParser
					.getParsedAnimationObject();
			
			mObject.setPosition(pos);
			mObject.setRotation(rot);
			mObject.setScale(scale);
			mObject.setFps(fps);
			mObject.setTransparent(false);
			getCurrentScene().addChild(mObject);
			
			return mObject;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mObject;
	}
	
	public void loadAnim2Obj(SkeletalAnimationObject3D obj, String animname, boolean loop)
	{
		try{
		
		int anim = getContext().getResources().getIdentifier(animname, "raw", "rajawali.vuforia.vr");

		LoaderMD5Anim animParser = new LoaderMD5Anim(animname, this,
													 anim);
		animParser.parse();

		SkeletalAnimationSequence sequence = (SkeletalAnimationSequence) animParser
			.getParsedAnimationSequence();
		
		obj.setAnimationSequence(sequence);
		obj.play(loop);
		} catch (ParsingException e) {
			e.printStackTrace();
		}
	
			
	}
	
	public SkeletalAnimationObject3D showMonster(String Monster, Vector3 pos, Vector3 rot, Vector3 scale)
	{	
		return showMonster(Monster,pos,rot,scale , 8);
	}
	
	public SkeletalAnimationObject3D showMonster(String Monster, Vector3 pos, Vector3 rot)
	{	
		return showMonster(Monster,pos,rot,new Vector3(0.0001f), 8);
	}
	
	public Bitmap textAsBitmap(String text) 
	{
		Bitmap mScoreBitmap = Bitmap.createBitmap(256, 256, Config.ARGB_8888);
		
		Canvas mScoreCanvas = new Canvas(mScoreBitmap);
		Paint mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mTextPaint.setColor(Color.WHITE);
		mTextPaint.setTextSize(50);
		mTextPaint.setTypeface(Typeface.MONOSPACE);
		
		mScoreCanvas.drawColor(0, Mode.CLEAR);
		
		mScoreCanvas.drawText(text, 80,
				148, mTextPaint);
		
		return mScoreBitmap;
    }
	
	private static boolean isFireKey(int keyCode) {
        return KeyEvent.isGamepadButton(keyCode)
                || keyCode == KeyEvent.KEYCODE_DPAD_CENTER
                || keyCode == KeyEvent.KEYCODE_SPACE;
    }	
	
	public boolean inList(String search, ArrayList<String> myList){
		try{
			for(String str: myList) {
				Log.d("OBJECT", search);
				if (str.contains(search))
					return true;
				}
		}
		catch(Exception e){
			Log.d("Exception", e.toString());
		}
		return false;
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    float speed = 1;    
 		int deviceId = event.getDeviceId();
	        boolean handled = false;
 		if (deviceId != -1) {
 				oldpos = player.getPosition();
	        	Log.d("KEYCODE", Integer.toString(event.getKeyCode()));
	        	 switch (keyCode) {
	                case KeyEvent.KEYCODE_DPAD_LEFT:
	                	if (!loaded){
	                		player.setFps(15);
	                		loadAnim2Obj(player,"player_sidestep", false); loaded = true;
	                		} 
	                		player.setPosition(player.getPosition().x-speed,player.getPosition().y,player.getPosition().z);
	                	handled = true;
	                    break;
	                case KeyEvent.KEYCODE_DPAD_RIGHT:
	                	if (!loaded){ 
	                		player.setFps(15);
	                		loadAnim2Obj(player,"player_sidestep", false); loaded = true;
	                		} 
                			player.setPosition(player.getPosition().x+speed,player.getPosition().y,player.getPosition().z);
                		handled = true;
	                    break;
	                case KeyEvent.KEYCODE_DPAD_UP:
	                	if (!loaded){ 
	                		player.setFps(15);
	                		loadAnim2Obj(player,"player_run", false); loaded = true;
	                		} 
                			player.setPosition(player.getPosition().x,player.getPosition().y,player.getPosition().z-speed);
	                	handled = true;
	                    break;
	                case KeyEvent.KEYCODE_DPAD_DOWN:
	                	if (!loaded){ 
	                		loadAnim2Obj(player,"player_walk", false); loaded = true;player.setFps(15);
	                		}
                			player.setPosition(player.getPosition().x,player.getPosition().y,player.getPosition().z+speed);
	                	handled = true;
	                    break;
	                case 105:
	                	if (!loaded){
	                		player.setFps(30);
	                		loadAnim2Obj(player,"player_shoot", false); loaded = true;
	                		}
	                	handled = true;
	                    break;
	                case 104:
	                	if (!loaded){
	                		player.setFps(30);
	                		loadAnim2Obj(player,"player_shoot", false); loaded = true;
	                		}
	                	handled = true;
	                    break;
	                default:
	                    if (isFireKey(keyCode)) {
	                    	handled = true;
	                    }
	                    loadAnim2Obj(player,"player_idle", false); 
            			break;
	            }
	        }
	       return handled;
 }
       
    public boolean onKeyUp(int keyCode, KeyEvent event) {
	        int deviceId = event.getDeviceId();
	        if (deviceId != -1) {
	        	loadAnim2Obj(player,"player_idle", false); loaded = false;
	        }
	        return true;
    }
	
	public void onDrawFrame(GL10 glUnused) {
		super.onRenderFrame(glUnused);
		count+=0.1f;
		super.mCameraLeft.setPosition(player.getPosition().x,player.getPosition().y+2,player.getPosition().z+5);
		super.mCameraRight.setPosition(player.getPosition().x,player.getPosition().y+2,player.getPosition().z+5);
		
//			if (colliders.length != 0){
//				for (Object3D collider : colliders){
//					Log.d("colliders", collider.getName());
//					bbox = collider.getGeometry().getBoundingBox();
//					bbox.transform(collider.getModelMatrix());
//					bbox2 = playerbox.getGeometry().getBoundingBox();
//					bbox2.transform(playerbox.getModelMatrix());
//		
//					intersected = bbox.intersectsWith(bbox2);
//				
//					if (intersected){
//						Log.d("intersected", "intersected");
//					
//					}
//				}
//			}
		}
	}

