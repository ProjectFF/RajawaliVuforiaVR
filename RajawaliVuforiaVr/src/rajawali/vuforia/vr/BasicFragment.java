package rajawali.vuforia.vr;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import org.rajawali3d.IRajawaliDisplay;
import org.rajawali3d.Object3D;
import org.rajawali3d.materials.Material;
import org.rajawali3d.materials.textures.ATexture;
import org.rajawali3d.materials.textures.Texture;
import org.rajawali3d.math.vector.Vector3;
import org.rajawali3d.primitives.Sphere;
import org.rajawali3d.surface.IRajawaliSurface;
import org.rajawali3d.surface.IRajawaliSurfaceRenderer;
import org.rajawali3d.surface.RajawaliSurfaceView;

import rajawali.vr.RajawaliVRRenderer;

public class BasicFragment extends Fragment implements IRajawaliDisplay {

	private final class BasicRenderer extends RajawaliVRRenderer implements IRajawaliSurfaceRenderer {

		private Object3D mSphere;

		public BasicRenderer(Context context) {
			super(context);
		}

        @Override
		public void initScene() {
			try {
				Material material = new Material();
				material.addTexture(new Texture("earthColors",
						R.drawable.dedust_material_1));
				material.setColorInfluence(0);
				mSphere = new Sphere(1, 24, 24);
				mSphere.setMaterial(material);
				getCurrentScene().addChild(mSphere);
			} catch (ATexture.TextureException e) {
				e.printStackTrace();
			}

            getCurrentCamera().enableLookAt();
            getCurrentCamera().setLookAt(0, 0, 0);
            getCurrentCamera().setZ(6);
        }

        @Override
        public void onRender(final long elapsedTime, final double deltaTime) {
			super.onRender(elapsedTime, deltaTime);
			mSphere.rotate(Vector3.Axis.Y, 1.0);
		}
	}

	private IRajawaliSurface mRajawaliSurface;
	private FrameLayout mLayout;
	private IRajawaliSurfaceRenderer mRenderer;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);

        mLayout = (FrameLayout) inflater.inflate(getLayoutID(), container, false);
		mLayout.bringToFront();
//
		mRajawaliSurface = (IRajawaliSurface) mLayout.findViewById(R.id.rajwali_surface);
//		
//		getActivity().setTitle("Test");
//		
		mRenderer = createRenderer();
//        
		mRajawaliSurface.setSurfaceRenderer(mRenderer);
//        
		return mLayout;
	}

	@Override
	public IRajawaliSurfaceRenderer createRenderer() {
		return new BasicRenderer(getActivity().getBaseContext());
	}

	@Override
	public int getLayoutID() {
		return R.layout.main_layout;
	}
}
