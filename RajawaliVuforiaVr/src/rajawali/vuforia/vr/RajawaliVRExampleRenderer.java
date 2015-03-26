package rajawali.vuforia.vr;

import java.io.File;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

import rajawali.Camera;
import rajawali.Object3D;
import rajawali.SerializedObject3D;
import rajawali.animation.mesh.SkeletalAnimationObject3D;
import rajawali.animation.mesh.SkeletalAnimationSequence;
import rajawali.lights.DirectionalLight;
import rajawali.materials.Material;
import rajawali.materials.methods.DiffuseMethod;
import rajawali.materials.textures.Texture;
import rajawali.math.Quaternion;
import rajawali.math.vector.Vector3;
import rajawali.parser.Loader3DSMax;
import rajawali.parser.ParsingException;
import rajawali.parser.md5.LoaderMD5Anim;
import rajawali.parser.md5.LoaderMD5Mesh;
import rajawali.primitives.Plane;
import rajawali.terrain.SquareTerrain;
import rajawali.util.MeshExporter;
import rajawali.util.exporter.AExporter;
import rajawali.util.exporter.SerializationExporter;
import rajawali.vr.RajawaliVRRenderer;
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


public class RajawaliVRExampleRenderer extends RajawaliVRRenderer {
	float count = 0;
	Quaternion mPlayerOrientation = new Quaternion();
	Object3D crosshair;
	ArrayList<String> objects = new ArrayList<String>();
	Resources res;
	String tr, tl;
	SkeletalAnimationObject3D player;
	boolean loaded = false;
	
	public RajawaliVRExampleRenderer(Context context) {
		super(context);
		res = context.getResources();
	}
	
	@Override
	public void initScene() {
		
		DirectionalLight light = new DirectionalLight(0.2f, -1f, 0f);
		light.setPower(.7f);
		getCurrentScene().addLight(light);

		light = new DirectionalLight(0.2f, 1f, 0f);
		light.setPower(1f);
		getCurrentScene().addLight(light);
		
		getCurrentCamera().setFarPlane(1000);
		getCurrentCamera().setFieldOfView(120);
		
		getCurrentScene().setBackgroundColor(0xdddddd);
		
		try {
			
			getCurrentScene().setSkybox(R.drawable.posx, R.drawable.negx, R.drawable.posy, R.drawable.negy, R.drawable.posz, R.drawable.negz);
			objects.add("SkyBox");			
			
			Loader3DSMax loader = new Loader3DSMax(this, R.raw.room);
			loader.parse();
			
			Material roommat = new Material();
			roommat.setDiffuseMethod(new DiffuseMethod.Lambert());
			roommat.setColorInfluence(0);
			roommat.enableLighting(true);
			roommat.addTexture(new Texture("roommat", R.drawable.room));
			
			Object3D room = loader.getParsedObject();
			room.setMaterial(roommat);
			room.setDoubleSided(true);
			room.setName("room");
			room.setScale(4,2,4);
			objects.add("room");
			getCurrentScene().addChild(room);		
			
			player = showMonster("player", new Vector3( 0f, -1.5f, -1.5), new Vector3(0,180,0), new Vector3(.5f));
			player.setFps(15);
			loadAnim2Obj(player,"player_idle", false); 
			
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
	
	public void setLogData(String text){
			((RajawaliVRExampleActivity) mContext).setText(text);
	}
	
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//	        
//    		int deviceId = event.getDeviceId();
//	        boolean handled = false;
//    		if (deviceId != -1) {
//	        	Log.d("KEYCODE", Integer.toString(event.getKeyCode()));
//	        	 switch (keyCode) {
//	                case KeyEvent.KEYCODE_DPAD_LEFT:
//	                	for (Object3D o : getCurrentScene().getChildrenCopy()){
//	                		if (inList(o.getName(), objects)){
//	                		Vector3 pos = o.getPosition();
//		        			o.setPosition(pos.x-.5, pos.y, pos.z);}
//		        		}
//	                	handled = true;
//	                    break;
//	                case KeyEvent.KEYCODE_DPAD_RIGHT:
//	                	for (Object3D o : getCurrentScene().getChildrenCopy()){
//	                		if (inList(o.getName(), objects)){
//	                		Vector3 pos = o.getPosition();
//		        			o.setPosition(pos.x+.5, pos.y, pos.z);}}
//		        		handled = true;
//	                    break;
//	                case KeyEvent.KEYCODE_DPAD_UP:
//	                	for (Object3D o : getCurrentScene().getChildrenCopy()){
//	                		if (inList(o.getName(), objects)){
//	                		Vector3 pos = o.getPosition();
//		        			o.setPosition(pos.x, pos.y+.5, pos.z);}}
//		        		handled = true;
//	                    break;
//	                case KeyEvent.KEYCODE_DPAD_DOWN:
//	                	for (Object3D o : getCurrentScene().getChildrenCopy()){
//	                		if (inList(o.getName(), objects)){
//	                		Vector3 pos = o.getPosition();
//		        			o.setPosition(pos.x, pos.y-.5, pos.z);}}
//	                    handled = true;
//	                    break;
//	                case 105:
//	                	for (Object3D o : getCurrentScene().getChildrenCopy()){
//	                		if (inList(o.getName(), objects)){
//	                		Vector3 pos = o.getPosition();
//		        			o.setPosition(pos.x, pos.y, pos.z+.5);}}
//	                    handled = true;
//	                    break;
//	                case 104:
//	                	for (Object3D o : getCurrentScene().getChildrenCopy()){
//	                		if (inList(o.getName(), objects)){
//		                		Vector3 pos = o.getPosition();
//	                			o.setPosition(pos.x, pos.y, pos.z-.5);}}
//	                    handled = true;
//	                    break;
//	                default:
//	                    if (isFireKey(keyCode)) {
//	                        handled = true;
//	                    }
//	                    break;
//	            }
//	        }
//	       return handled;
//    }

	 public boolean onKeyDown(int keyCode, KeyEvent event) {
	        
 		int deviceId = event.getDeviceId();
	        boolean handled = false;
 		if (deviceId != -1) {
	        	Log.d("KEYCODE", Integer.toString(event.getKeyCode()));
	        	 switch (keyCode) {
	                case KeyEvent.KEYCODE_DPAD_LEFT:
	                	if (!loaded){ 
	                		player.setFps(15);
	                		loadAnim2Obj(player,"player_sidestep", false); loaded = true;
	                		} 
	                		super.mCameraLeft.setPosition(super.mCameraLeft.getPosition().x-0.5f,super.mCameraLeft.getPosition().y,super.mCameraLeft.getPosition().z);
	                		super.mCameraRight.setPosition(super.mCameraRight.getPosition().x-0.5f,super.mCameraRight.getPosition().y,super.mCameraRight.getPosition().z);
	                		player.setPosition(player.getPosition().x-.5f,player.getPosition().y,player.getPosition().z);
	                	handled = true;
	                    break;
	                case KeyEvent.KEYCODE_DPAD_RIGHT:
	                	if (!loaded){ 
	                		player.setFps(15);
	                		loadAnim2Obj(player,"player_sidestep", false); loaded = true;
	                		} 
	                		super.mCameraLeft.setPosition(super.mCameraLeft.getPosition().x+0.5f,super.mCameraLeft.getPosition().y,super.mCameraLeft.getPosition().z);
	                		super.mCameraRight.setPosition(super.mCameraRight.getPosition().x+0.5f,super.mCameraRight.getPosition().y,super.mCameraRight.getPosition().z);
	                		player.setPosition(player.getPosition().x+.5f,player.getPosition().y,player.getPosition().z);
                		handled = true;
	                    break;
	                case KeyEvent.KEYCODE_DPAD_UP:
	                	if (!loaded){ 
	                		player.setFps(15);
	                		loadAnim2Obj(player,"player_run", false); loaded = true;
	                		} 
	                		super.mCameraLeft.setPosition(super.mCameraLeft.getPosition().x,super.mCameraLeft.getPosition().y,super.mCameraLeft.getPosition().z-.5f);
	                		super.mCameraRight.setPosition(super.mCameraRight.getPosition().x,super.mCameraRight.getPosition().y,super.mCameraRight.getPosition().z-.5f);
	                		player.setPosition(player.getPosition().x,player.getPosition().y,player.getPosition().z-.5f);
	                	handled = true;
	                    break;
	                case 20:
	                	if (!loaded){ 
	                		loadAnim2Obj(player,"player_walk", false); loaded = true;player.setFps(15);
	                		}
	                		super.mCameraLeft.setPosition(super.mCameraLeft.getPosition().x,super.mCameraLeft.getPosition().y,super.mCameraLeft.getPosition().z+.5f);
	                		super.mCameraRight.setPosition(super.mCameraRight.getPosition().x,super.mCameraRight.getPosition().y,super.mCameraRight.getPosition().z+.5f);
	                		player.setPosition(player.getPosition().x,player.getPosition().y,player.getPosition().z+.5f);
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

	
	@Override
	public void onDrawFrame(GL10 glUnused) {
		super.onDrawFrame(glUnused);
		count+=0.1f;
//		mHeadTracker.getLastHeadView(mHeadViewMatrix, 0);
//		mHeadViewMatrix4.setAll(mHeadViewMatrix);
//		mPlayerOrientation.fromMatrix(mHeadViewMatrix4);
//		mPlayerOrientation.y *= -1;
//		player.setOrientation(mPlayerOrientation);
    	
    	
		
	}
	
}
