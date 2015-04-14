package rajawali.vuforia.vr;

import org.rajawali3d.IRajawaliDisplay;

import org.rajawali3d.surface.IRajawaliSurface;
import org.rajawali3d.surface.IRajawaliSurfaceRenderer;
import org.rajawali3d.surface.RajawaliSurfaceView;

import com.example.inputmanagercompat.InputManagerCompat;
import com.example.inputmanagercompat.InputManagerCompat.InputDeviceListener;
import com.google.vrtoolkit.cardboard.sensors.HeadTracker;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.InputDevice;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class RajawaliVRFragment extends Fragment implements IRajawaliDisplay, InputDeviceListener, OnKeyListener{

	protected IRajawaliSurfaceRenderer mRenderer;
	private InputManagerCompat mInputManager;
	private InputDevice dev;
	private TextView tl, tr ;
	private IRajawaliSurface mSurface;
	private FrameLayout mLayout;
	private IRajawaliSurface mRajawaliSurface;
	private HeadTracker mHeadTracker;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mInputManager = InputManagerCompat.Factory.getInputManager(activity.getBaseContext());
		mInputManager.registerInputDeviceListener(this, null);
		//mRenderer = new RajawaliVRExampleRenderer(activity);
		mHeadTracker = new HeadTracker(activity);
		
        
	};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);

        // Inflate the view
        mLayout = (FrameLayout) inflater.inflate(R.layout.main_layout, container, false);
		mLayout.bringToFront();

		//mRajawaliSurface = (IRajawaliSurface) mLayout.findViewById(R.id.rajwali_surface);

		mRajawaliSurface = new RajawaliSurfaceView(getActivity());
		mRajawaliSurface.setFrameRate(60.0);
		mRajawaliSurface.setRenderMode(IRajawaliSurface.RENDERMODE_WHEN_DIRTY);

		// Add mSurface to your root view
		
		getActivity().setTitle("Test");

        // Create the renderer
        mRenderer = new RajawaliVRExampleRenderer(getActivity());
		applyRenderer();
		return mLayout;
	}
	
	@Override
    public int getLayoutID() {
        return R.layout.main_layout;
    }
	
	protected void applyRenderer() {
		mRajawaliSurface.setSurfaceRenderer(mRenderer);
		//mRenderer.setHeadTracker(mHeadTracker);
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

	@Override
	public IRajawaliSurfaceRenderer createRenderer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onResume() {
		super.onResume();
		mHeadTracker.startTracking();
	}
	
	@Override
	public void onPause() {
		super.onPause();
		mHeadTracker.stopTracking();
	}
	
	
}
