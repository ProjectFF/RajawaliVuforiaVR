package com.monyetmabuk.rajawali.tutorials.examples.materials;

import android.content.Context;

import com.monyetmabuk.rajawali.tutorials.R;
import com.monyetmabuk.rajawali.tutorials.examples.AExampleFragment;

import org.rajawali3d.animation.Animation;
import org.rajawali3d.animation.EllipticalOrbitAnimation3D;
import org.rajawali3d.materials.Material;
import org.rajawali3d.materials.textures.ATexture;
import org.rajawali3d.materials.textures.AnimatedGIFTexture;
import org.rajawali3d.math.vector.Vector3;
import org.rajawali3d.primitives.Plane;

public class AnimatedGIFTextureFragment extends AExampleFragment {

	@Override
    public AExampleRenderer createRenderer() {
		return new AnimatedGIFTextureRenderer(getActivity());
	}

	private final class AnimatedGIFTextureRenderer extends AExampleRenderer {

		private AnimatedGIFTexture mGifTexture;

		public AnimatedGIFTextureRenderer(Context context) {
			super(context);
		}

        @Override
		protected void initScene() {
			final Material material = new Material();
			final Plane plane = new Plane(1, 1, 1, 1);
			plane.setMaterial(material);
			plane.setRotY(180);
			getCurrentScene().addChild(plane);

			try {
				mGifTexture = new AnimatedGIFTexture("animGif", R.drawable.animated_gif);
				material.addTexture(mGifTexture);
				material.setColorInfluence(0);
				mGifTexture.rewind();
				plane.setScaleX((float) mGifTexture.getWidth() / (float) mGifTexture.getHeight());
			} catch (ATexture.TextureException e) {
				e.printStackTrace();
			}

			final EllipticalOrbitAnimation3D anim = new EllipticalOrbitAnimation3D(new Vector3(0, 0, 3), new Vector3(
					0.5, 0.5, 3), 0, 359);
			anim.setDurationMilliseconds(12000);
			anim.setRepeatMode(Animation.RepeatMode.INFINITE);
			anim.setTransformable3D(getCurrentCamera());
			getCurrentScene().registerAnimation(anim);
			anim.play();
		}

        @Override
        protected void onRender(long ellapsedRealtime, double deltaTime) {
            super.onRender(ellapsedRealtime, deltaTime);
			if (mGifTexture != null) {
				try {
					mGifTexture.update();
				} catch (ATexture.TextureException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
