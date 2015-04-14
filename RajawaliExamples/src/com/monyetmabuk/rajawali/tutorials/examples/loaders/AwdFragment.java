package com.monyetmabuk.rajawali.tutorials.examples.loaders;

import android.content.Context;

import com.monyetmabuk.rajawali.tutorials.R;
import com.monyetmabuk.rajawali.tutorials.examples.AExampleFragment;

import org.rajawali3d.Object3D;
import org.rajawali3d.animation.Animation;
import org.rajawali3d.animation.Animation3D;
import org.rajawali3d.animation.RotateOnAxisAnimation;
import org.rajawali3d.loader.LoaderAWD;
import org.rajawali3d.math.vector.Vector3;

public class AwdFragment extends AExampleFragment {

	@Override
    public AExampleRenderer createRenderer() {
		return new AwdRenderer(getActivity());
	}

	private final class AwdRenderer extends AExampleRenderer {

		public AwdRenderer(Context context) {
			super(context);
		}

		@Override
		protected void initScene() {

			try {
				final LoaderAWD parser = new LoaderAWD(mContext.getResources(), mTextureManager, R.raw.awd_arrows);
				parser.parse();

				final Object3D obj = parser.getParsedObject();
				obj.setScale(0.25f);
				getCurrentScene().addChild(obj);

				final Animation3D anim = new RotateOnAxisAnimation(Vector3.Axis.Y, -360);
				anim.setDurationDelta(4d);
				anim.setRepeatMode(Animation.RepeatMode.INFINITE);
				anim.setTransformable3D(obj);
				anim.play();
				getCurrentScene().registerAnimation(anim);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

}
