package rajawali.vuforia.vr;

import org.rajawali3d.surface.IRajawaliSurface;
import org.rajawali3d.surface.RajawaliSurfaceView;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;


public class RajawaliVRExampleActivity extends FragmentActivity {
	
	
	private RajawaliSurfaceView mRajawaliSurface;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);
		
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		
		BasicFragment fragment = new BasicFragment();
		fragmentTransaction.add(R.id.container, fragment);
		fragmentTransaction.commit();
		
	}
}

//		//setRenderer(mRenderer);
//		
//		mLayout = (FrameLayout)findViewById(R.id.mLayout);
//
//        // Find the TextureView
//        mSurface = (IRajawaliSurface) mLayout.findViewById(R.id.rajwali_surface);
//
//        mSurface.setSurfaceRenderer(mRenderer);
//        
//		LinearLayout layout = new LinearLayout(this);
//		layout.setOrientation(LinearLayout.HORIZONTAL);
//		layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

//	    Display display = getWindowManager().getDefaultDisplay();
//		Point size = new Point();
//		display.getSize(size);
//		int width = size.x;
//		int height = size.y;
//		
//	    tl = new TextView(this);
//	    tl.setLayoutParams(new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f));
//	    tl.setWidth(0);
//	    tl.setText("+");
//	    tl.setPaddingRelative(width/4, height/2, 0, 0);
//	    tl.setTextSize(20);
//	    tl.setAlpha(.30f);
//	    mLayout.addView(tl);
//		
//	    tr = new TextView(this);
//	    tr.setLayoutParams(new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f));
//	    tr.setWidth(0);
//	    tr.setPaddingRelative((width/4)*3, height/2, 0, 0);
//	    tr.setText("+");
//	    tr.setAlpha(.30f);
//	    tr.setTextSize(20);
//	    
//	    mLayout.addView(tr);
//		
//		
//	}
