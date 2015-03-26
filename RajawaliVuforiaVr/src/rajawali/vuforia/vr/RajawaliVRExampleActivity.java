package rajawali.vuforia.vr;

import rajawali.vr.RajawaliVRActivity;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.InputDevice;
import android.view.KeyEvent;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.inputmanagercompat.InputManagerCompat;
import com.example.inputmanagercompat.InputManagerCompat.InputDeviceListener;

public class RajawaliVRExampleActivity extends RajawaliVRActivity implements InputDeviceListener {
	private RajawaliVRExampleRenderer mRenderer;
	private InputManagerCompat mInputManager;
	private InputDevice dev;
	private TextView tl, tr ;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(
				WindowManager.LayoutParams.FLAG_FULLSCREEN
						| WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
				WindowManager.LayoutParams.FLAG_FULLSCREEN
						| WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
						);

		super.onCreate(savedInstanceState);
		
		mInputManager = InputManagerCompat.Factory.getInputManager(this);
	    mInputManager.registerInputDeviceListener(this, null);

	    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		
		mRenderer = new RajawaliVRExampleRenderer(this);
		mRenderer.setSurfaceView(mSurfaceView);
		setRenderer(mRenderer);

		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.HORIZONTAL);
		layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

	    Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int width = size.x;
		int height = size.y;
		
	    tl = new TextView(this);
	    tl.setLayoutParams(new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f));
	    tl.setWidth(0);
	    tl.setText("+");
	    tl.setPaddingRelative(width/4, height/2, 0, 0);
	    tl.setTextSize(20);
	    tl.setAlpha(.30f);
	    mLayout.addView(tl);
		
	    tr = new TextView(this);
	    tr.setLayoutParams(new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f));
	    tr.setWidth(0);
	    tr.setPaddingRelative((width/4)*3, height/2, 0, 0);
	    tr.setText("+");
	    tr.setAlpha(.30f);
	    tr.setTextSize(20);
	    
	    mLayout.addView(tr);
		
		
	}
	
	public void setText(String text){
		
		try {
			
			tr.setText(getResources().getString(R.string.textRight));
			tl.setText(getResources().getString(R.string.textLeft));
			
		}
		catch(Exception e){
			Log.d("Exception", e.toString());
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		mRenderer.onKeyDown(keyCode, event); 
		return super.onKeyDown(keyCode, event);
	}
	
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		mRenderer.onKeyUp(keyCode, event); 
		return super.onKeyUp(keyCode, event);
	}

	@Override
	public void onInputDeviceAdded(int deviceId) {
		// TODO Auto-generated method stub
		dev = InputDevice.getDevice(deviceId);
		Log.d("deviceName", dev.getName());
	}

	@Override
	public void onInputDeviceChanged(int deviceId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onInputDeviceRemoved(int deviceId) {
		// TODO Auto-generated method stub
		
	}
}
