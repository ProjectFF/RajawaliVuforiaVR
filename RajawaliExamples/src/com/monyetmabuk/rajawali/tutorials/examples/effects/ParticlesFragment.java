package com.monyetmabuk.rajawali.tutorials.examples.effects;

import android.content.Context;

import com.monyetmabuk.rajawali.tutorials.examples.AExampleFragment;
import com.monyetmabuk.rajawali.tutorials.examples.effects.particles.ExampleParticleSystem;

public class ParticlesFragment extends AExampleFragment {

	@Override
    public AExampleRenderer createRenderer() {
		return new ParticlesRenderer(getActivity());
	}

	private final class ParticlesRenderer extends AExampleRenderer {
		private final int MAX_FRAMES = 200;
		private int mFrameCount;
		private ExampleParticleSystem mParticleSystem;

		public ParticlesRenderer(Context context) {
			super(context);
		}

        @Override
		protected void initScene() {
			getCurrentCamera().setPosition(0, 0, 10);
			// TODO add particle system
			/*
			mParticleSystem = new ExampleParticleSystem();
			ParticleMaterial material = new ParticleMaterial();
			try {
				material.addTexture(new Texture(R.drawable.particle));
			} catch (TextureException e) {
				e.printStackTrace();
			}
			mParticleSystem.setMaterial(material);
			mParticleSystem.setPointSize(100);
			addChild(mParticleSystem);
			*/
		}

        @Override
        protected void onRender(long ellapsedRealtime, double deltaTime) {
            super.onRender(ellapsedRealtime, deltaTime);
			/*
			mParticleSystem.setTime((float) mFrameCount * .2f);

			if (mFrameCount++ >= MAX_FRAMES)
				mFrameCount = 0;
				*/
		}

	}

}
