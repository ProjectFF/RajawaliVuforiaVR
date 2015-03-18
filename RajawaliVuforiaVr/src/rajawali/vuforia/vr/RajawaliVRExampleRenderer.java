package rajawali.vuforia.vr;

import java.io.File;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

import rajawali.Object3D;
import rajawali.SerializedObject3D;
import rajawali.lights.DirectionalLight;
import rajawali.materials.Material;
import rajawali.materials.methods.DiffuseMethod;
import rajawali.materials.textures.Texture;
import rajawali.math.Quaternion;
import rajawali.math.vector.Vector3;
import rajawali.parser.Loader3DSMax;
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
	Quaternion q = new Quaternion();
	Object3D crosshair;
	ArrayList<String> objects = new ArrayList<String>();
	Resources res;
	String tr, tl;
	
	
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
			objects.add("room");
			getCurrentScene().addChild(room);			
			
//			ObjectInputStream ois;
//		    ois = new ObjectInputStream(mContext.getResources().openRawResource(R.raw.deckel));
//		    Object3D deckel = new Object3D((SerializedObject3D)ois.readObject());
//		    ois.close();
//			
//			Material deckelmat = new Material();
//			deckelmat.setDiffuseMethod(new DiffuseMethod.Lambert());
//			deckelmat.setColorInfluence(0);
//			deckelmat.enableLighting(true);
//			deckelmat.addTexture(new Texture("deckel", R.drawable.deckel));
//			
//			deckel.setMaterial(deckelmat);
//			deckel.setName("deckel");
//			objects.add("deckel");
//			getCurrentScene().addChild(deckel);
//			
//			
//			Material geraetmat = new Material();
//			geraetmat.setDiffuseMethod(new DiffuseMethod.Lambert());
//			geraetmat.setColorInfluence(0);
//			geraetmat.enableLighting(true);
//			geraetmat.addTexture(new Texture("geraet", R.drawable.geraet));
//		
//			ois = new ObjectInputStream(mContext.getResources().openRawResource(R.raw.geraet));
//		    Object3D geraet = new Object3D((SerializedObject3D)ois.readObject());
//		    ois.close();
//			
//			geraet.setMaterial(geraetmat);
//			geraet.setName("geraet");
//			objects.add("geraet");
//			getCurrentScene().addChild(geraet);
//			
//			
//			Material floormat = new Material();
//			floormat.setDiffuseMethod(new DiffuseMethod.Lambert());
//			floormat.setColorInfluence(0);
//			floormat.enableLighting(true);
//			floormat.addTexture(new Texture("floor", R.drawable.floor));
//			
//			Object3D floor = new Plane(100,100,1,1);
//			floor.setDoubleSided(true);
//			floor.setMaterial(floormat);
//			floor.setName("floor");
//			objects.add("floor");
//			getCurrentScene().addChild(floor);
//			
//			Material tubemat = new Material();
//			tubemat.setDiffuseMethod(new DiffuseMethod.Lambert());
//			tubemat.setColorInfluence(0);
//			tubemat.enableLighting(true);
//			tubemat.addTexture(new Texture("tube", R.drawable.tube));
//			
//			ois = new ObjectInputStream(mContext.getResources().openRawResource(R.raw.tube));
//		    Object3D tube = new Object3D((SerializedObject3D)ois.readObject());
//		    ois.close();
//			
//			tube.setMaterial(tubemat);
//			tube.setName("tube");
//			objects.add("tube");
//			getCurrentScene().addChild(tube);
//		
//			SerializationExporter exporter = new SerializationExporter();
//			File myFile = new File("/sdcard/tube.ser");
//            exporter.setExportFile(myFile);
//		    exporter.setExportModel(tube);
//            exporter.export();
			
			Material mat = new Material();
			mat.setDiffuseMethod(new DiffuseMethod.Lambert());
			mat.setColorInfluence(0);
			mat.enableLighting(true);
			mat.addTexture(new Texture("hair", R.drawable.crosshair));
			
			crosshair = new Plane(1,1,1,1);
			crosshair.setTransparent(true);
			crosshair.setMaterial(mat);
			crosshair.setDoubleSided(true);
			crosshair.setPosition(0, 0, .5);
			getCurrentScene().addChild(crosshair);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		super.initScene();
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
	
    public boolean onKeyDown(int keyCode, KeyEvent event) {
	        
    		int deviceId = event.getDeviceId();
	        boolean handled = false;
    		if (deviceId != -1) {
	        	Log.d("KEYCODE", Integer.toString(event.getKeyCode()));
	        	 switch (keyCode) {
	                case KeyEvent.KEYCODE_DPAD_LEFT:
	                	for (Object3D o : getCurrentScene().getChildrenCopy()){
	                		if (inList(o.getName(), objects)){
	                		Vector3 pos = o.getPosition();
		        			o.setPosition(pos.x-.1, pos.y, pos.z);}
		        		}
	                	handled = true;
	                    break;
	                case KeyEvent.KEYCODE_DPAD_RIGHT:
	                	for (Object3D o : getCurrentScene().getChildrenCopy()){
	                		if (inList(o.getName(), objects)){
	                		Vector3 pos = o.getPosition();
		        			o.setPosition(pos.x+.1, pos.y, pos.z);}}
		        		handled = true;
	                    break;
	                case KeyEvent.KEYCODE_DPAD_UP:
	                	for (Object3D o : getCurrentScene().getChildrenCopy()){
	                		if (inList(o.getName(), objects)){
	                		Vector3 pos = o.getPosition();
		        			o.setPosition(pos.x, pos.y+.1, pos.z);}}
		        		handled = true;
	                    break;
	                case KeyEvent.KEYCODE_DPAD_DOWN:
	                	for (Object3D o : getCurrentScene().getChildrenCopy()){
	                		if (inList(o.getName(), objects)){
	                		Vector3 pos = o.getPosition();
		        			o.setPosition(pos.x, pos.y-.1, pos.z);}}
	                    handled = true;
	                    break;
	                case 105:
	                	for (Object3D o : getCurrentScene().getChildrenCopy()){
	                		if (inList(o.getName(), objects)){
	                		Vector3 pos = o.getPosition();
		        			o.setPosition(pos.x, pos.y, pos.z+.1);}}
	                    handled = true;
	                    break;
	                case 104:
	                	for (Object3D o : getCurrentScene().getChildrenCopy()){
	                		if (inList(o.getName(), objects)){
		                		Vector3 pos = o.getPosition();
	                			o.setPosition(pos.x, pos.y, pos.z-.1);}}
	                    handled = true;
	                    break;
	                default:
	                    if (isFireKey(keyCode)) {
	                        handled = true;
	                    }
	                    break;
	            }
	        }
	       return handled;
    }

       
    public boolean onKeyUp(int keyCode, KeyEvent event) {
	        int deviceId = event.getDeviceId();
	        if (deviceId != -1) {
	        }
	        return true;
    }

	
	@Override
	public void onDrawFrame(GL10 glUnused) {
		super.onDrawFrame(glUnused);
		count+=0.1f;
		getCurrentScene().getCamera().getOrientation(q);
		crosshair.setOrientation(q.inverse());
		String textLeft = String.format(res.getString(R.string.textLeft),Double.toString(q.x));
		String textRight = String.format(res.getString(R.string.textRight),Double.toString(q.x));
		
	}
	
}
