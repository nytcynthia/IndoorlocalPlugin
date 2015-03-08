package com.ionicframework.linkedit757044;

// import mtrec.wherami.lbs.datatype.PointF;
// import mtrec.wherami.lbs.process.ILocationUtil.OnGetLocationResultListener;
// import mtrec.wherami.lbs.process.ILocationUtil.OnReadingDataBeginListener;
// import mtrec.wherami.lbs.process.ILocationUtil.OnWifiConnectedListener;
// import mtrec.wherami.lbs.process.ILocationUtil.OnWifiNotEnabledWhenScanningListener;
// import mtrec.wherami.lbs.process.IndoorLocationManager;
// import mtrec.wherami.lbs.process.IndoorLocationManager.OnVerificationListener;
import android.app.Activity;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;
import android.util.Log;
import android.provider.Settings;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
// public class locationplugin extends CordovaPlugin {

/**
* Constructor.
*/
public class locationplugin extends CordovaPlugin {
	public static final String TAG = "Location Plugin";
	private static final String POSITION_DATA_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/position_data";
	private TextView positionTextView;
	// private IndoorLocationManager indoorLocationManager;

	private void initialize() {
	String siteName = "hkust";
	// IndoorLocationManager.newInstance(this, siteName, POSITION_DATA_PATH, new OnVerificationListener() {

	// 	@Override
	// 	public void onVerificationFinished(boolean isSuccess, String resultCode) {
	// 		Log.e("mtrec", "Passed : " + isSuccess + "!!!!!" + resultCode);
	// 		if(isSuccess) {
	// 			positionTextView.setText("Verification passed!");
	// 			indoorLocationManager = IndoorLocationManager.getInstance();
	// 			startPositioning();
	// 		} else {
	// 			positionTextView.setText("Verification failed : " + resultCode);
	// 		}
	// 	}
	// 	});
	}

	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		this.callbackContext = callbackContext;
		this.activity = this.cordova.getActivity();
		boolean retValue = true;
		if (action.equals("startPositioning")){
			this.startPositioning();
		} else {
			retValue = false;
		}
		return retValue;
	}

	private void startPositioning() {
		new AlertDialog.Builder(cordova.getActivity()).setMessage("helloworld").create().show();
	}
}
/**
* Sets the context of the Command. This can then be used to do things like
* get file paths associated with the Activity.
*
* @param cordova The context of the main Activity.
* @param webView The CordovaWebView Cordova is running in.
*/

// @Override
// protected void onCreate(Bundle savedInstanceState) {
// 	super.onCreate(savedInstanceState);
// 	setContentView(R.layout.activity_main);

// 	initialize();
// 	positionTextView = (TextView)findViewById(R.id.position_text);
// }


/*
public void initialize(CordovaInterface cordova, CordovaWebView webView) {
super.initialize(cordova, webView);
Log.v(TAG,"Init CoolPlugin");
}
*/

// @Override
// public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
// 	this.callbackContext = callbackContext;
// 	this.activity = this.cordova.getActivity();
// 	boolean retValue = true;
// 	if (action.equals("startPositioning")){
// 		this.startPositioning();
// 	// } else if (action.equals("appendMessage")){
// 	// 	this.appendMessage(args);
// 	// } else if (action.equals("stopPositioning")){
// 	// 	this.stopPositioning();
// 	// } else if (action.equals("onBackPressed")){
// 	// 	this.onBackPressed();
// 	} else {
// 		retValue = false;
// 	}
    

// 	return retValue;
// }

/*
public boolean execute(final String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
final int duration = Toast.LENGTH_SHORT;
// Shows a toast
Log.v(TAG,"CoolPlugin received:"+ action);
cordova.getActivity().runOnUiThread(new Runnable() {
public void run() {
Toast toast = Toast.makeText(cordova.getActivity().getApplicationContext(), action, duration);
toast.show();
}
});
return true;
}
*/



// private void startPositioning() {
// 	indoorLocationManager.setOnWifiNotEnabledWhenScanningListener(new OnWifiNotEnabledWhenScanningListener() {

// 		@Override
// 		public void onWifiNotEnabledWhenScanning(WifiManager WifiManager) {
// 			// TODO Handle the case that the WIFI is disabled when scanning
// 			appendMessage("WIFI disabled");
// 		}
// 	});
// 	indoorLocationManager.setOnWifiConnectedListener(new OnWifiConnectedListener() {

// 		@Override
// 		public void onWifiConnected() {
// 			// TODO Handle the case that the WIFI is connected
// 			appendMessage("WIFI enabled");
// 		}
// 	});
// 	indoorLocationManager.setOnReadingDataBeginListener(new OnReadingDataBeginListener() {

// 		@Override
// 		public void onReadingDataBegin() {
// 			// TODO The callback that the positioning process is pending to read data
// 			runOnUiThread(new Runnable() {

// 				@Override
// 				public void run() {
// 					// TODO Auto-generated method stub
// 					appendMessage("Start reading data");
// 				}
// 			});
// 		}
// 	});
// 	indoorLocationManager.setOnGetLocationResultListener(new OnGetLocationResultListener() {

// 		@Override
// 		public void onGetLocationResult(String area, PointF[] points,
// 				Integer[] radius, String symbol) {
// 			// TODO The callback that finish calculating the position
// 			if(area != null && points != null && points.length > 0) {
// 				StringBuilder positionFoundString = new StringBuilder();
// 				positionFoundString.append("Position found\n");
// 				int i = 0;
// 				for(PointF point : points) {
// 					positionFoundString.append("Area : ").append(area).append(", x : ").append(point.x).append(", y : ")
// 						.append(point.y).append(", radius : ").append(radius[i]).append("\n");
// 					i++;
// 				}
// 				appendMessage(positionFoundString.toString());
// 			} else {
// 				appendMessage("Calculate failed, waiting another try");
// 			}
// 		}
// 	});
// }

// private void appendMessage(JSONArray args) throws JSONException {
// 	String message = args.getString(0);
// 	String lastText = positionTextView.getText() + "\n";
// 	positionTextView.setText(lastText + message);

// 	new Handler().post(new Runnable() {

// 		@Override
// 		public void run() {
// 			((ScrollView)findViewById(R.id.scroll_view)).fullScroll(ScrollView.FOCUS_DOWN);
// 		}
// 	});
// }

// private void stopPositioning() {
// 	if(indoorLocationManager != null) {
// 		try {
// 			indoorLocationManager.stopLocation();
// 		} catch (Exception e) {
// 			e.printStackTrace();
// 		}
// 	}
// }

// //need to add into execute()??
// public void onButtonClick(View view) {
// 	int id = view.getId();
// 	switch (id) {
// 	case R.id.start_button:
// 		if(indoorLocationManager != null)
// 			indoorLocationManager.startLocation();
// 		findViewById(R.id.start_button).setEnabled(false);
// 		break;

// 	case R.id.stop_button:
// 		stopPositioning();
// 		findViewById(R.id.start_button).setEnabled(true);
// 		break;
// 	default:
// 		break;
// 	}
// }

// @Override
// public void onBackPressed() {
// 	stopPositioning();
// 	finish();
// 	android.os.Process.killProcess(android.os.Process.myPid());
// }
// }