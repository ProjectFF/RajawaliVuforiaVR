package rajawali.vuforia.vr;

import java.io.File;
import java.io.ObjectInputStream;

import javax.microedition.khronos.opengles.GL10;

import rajawali.Object3D;
import rajawali.SerializedObject3D;
import rajawali.animation.Animation.RepeatMode;
import rajawali.animation.SplineTranslateAnimation3D;
import rajawali.curves.CatmullRomCurve3D;
import rajawali.lights.DirectionalLight;
import rajawali.materials.Material;
import rajawali.materials.methods.DiffuseMethod;
import rajawali.materials.textures.ATexture.TextureException;
import rajawali.materials.textures.NormalMapTexture;
import rajawali.materials.textures.Texture;
import rajawali.math.Quaternion;
import rajawali.math.vector.Vector3;
import rajawali.parser.Loader3DSMax;
import rajawali.parser.LoaderAWD;
import rajawali.primitives.Plane;
import rajawali.terrain.SquareTerrain;
import rajawali.terrain.TerrainGenerator;
import rajawali.util.MeshExporter;
import rajawali.util.exporter.AExporter;
import rajawali.util.exporter.SerializationExporter;
import rajawali.vr.RajawaliVRRenderer;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class RajawaliVRExampleRenderer extends RajawaliVRRenderer {
	private SquareTerrain mTerrain;
	float count = 0;
	Quaternion q = new Quaternion();
	Object3D crosshair;

	public RajawaliVRExampleRenderer(Context context) {
		super(context);
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
						
			ObjectInputStream ois;
		    ois = new ObjectInputStream(mContext.getResources().openRawResource(R.raw.deckel));
		    Object3D deckel = new Object3D((SerializedObject3D)ois.readObject());
		    ois.close();
			
			Material deckelmat = new Material();
			deckelmat.setDiffuseMethod(new DiffuseMethod.Lambert());
			deckelmat.setColorInfluence(0);
			deckelmat.enableLighting(true);
			deckelmat.addTexture(new Texture("deckel", R.drawable.deckel));
			
			deckel.setMaterial(deckelmat);
			getCurrentScene().addChild(deckel);
			
			
			Material geraetmat = new Material();
			geraetmat.setDiffuseMethod(new DiffuseMethod.Lambert());
			geraetmat.setColorInfluence(0);
			geraetmat.enableLighting(true);
			geraetmat.addTexture(new Texture("geraet", R.drawable.geraet));
		
			ois = new ObjectInputStream(mContext.getResources().openRawResource(R.raw.geraet));
		    Object3D geraet = new Object3D((SerializedObject3D)ois.readObject());
		    ois.close();
			
			geraet.setMaterial(geraetmat);
			getCurrentScene().addChild(geraet);
			
			Loader3DSMax loader = new Loader3DSMax(this, R.raw.floor);
			
			loader.parse();
			
			Material floormat = new Material();
			floormat.setDiffuseMethod(new DiffuseMethod.Lambert());
			floormat.setColorInfluence(0);
			floormat.enableLighting(true);
			floormat.addTexture(new Texture("floor", R.drawable.floor));
			
			Object3D floor = loader.getParsedObject();
			floor.setMaterial(floormat);
			getCurrentScene().addChild(floor);
			
			Material tubemat = new Material();
			tubemat.setDiffuseMethod(new DiffuseMethod.Lambert());
			tubemat.setColorInfluence(0);
			tubemat.enableLighting(true);
			tubemat.addTexture(new Texture("tube", R.drawable.tube));
			
			ois = new ObjectInputStream(mContext.getResources().openRawResource(R.raw.tube));
		    Object3D tube = new Object3D((SerializedObject3D)ois.readObject());
		    ois.close();
			
			tube.setMaterial(tubemat);
			getCurrentScene().addChild(tube);
		
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
			
			getCurrentScene().addChild(crosshair);
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		super.initScene();
	}
	
	@Override
	public void onDrawFrame(GL10 glUnused) {
		count+=0.1f;
		getCurrentScene().getCamera().getOrientation(q);
		crosshair.setOrientation(q.inverse());
		super.onDrawFrame(glUnused);
		 
		
	}
	
}
