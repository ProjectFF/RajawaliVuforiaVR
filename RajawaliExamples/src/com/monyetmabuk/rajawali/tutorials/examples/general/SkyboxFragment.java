package com.monyetmabuk.rajawali.tutorials.examples.general;

import android.content.Context;

import com.monyetmabuk.rajawali.tutorials.R;
import com.monyetmabuk.rajawali.tutorials.examples.AExampleFragment;

import org.rajawali3d.materials.textures.ATexture;
import org.rajawali3d.math.vector.Vector3;

public class SkyboxFragment extends AExampleFragment {

	@Override
    public AExampleRenderer createRenderer() {
		return new SkyboxRenderer(getActivity());
	}

	private final class SkyboxRenderer extends AExampleRenderer {

		public SkyboxRenderer(Context context) {
			super(context);
		}

        @Override
		protected void initScene() {
			getCurrentCamera().setFarPlane(1000);
			/*
			 * Skybox images by Emil Persson, aka Humus. http://www.humus.name humus@comhem.se
			 */
			try {
				getCurrentScene().setSkybox(R.drawable.posx, R.drawable.negx,
						R.drawable.posy, R.drawable.negy, R.drawable.posz,
						R.drawable.negz);
			} catch (ATexture.TextureException e) {
				e.printStackTrace();
			}
		}

        @Override
        protected void onRender(long ellapsedRealtime, double deltaTime) {
            super.onRender(ellapsedRealtime, deltaTime);
			getCurrentCamera().rotate(Vector3.Axis.Y, -0.2);;
		}
	}

}
