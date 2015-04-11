package com.wheramiplugin;

// import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import mtrec.wherami.lbs.datatype.PointF;
import mtrec.wherami.lbs.process.ILocationUtil.OnGetLocationResultListener;
import mtrec.wherami.lbs.process.ILocationUtil.OnReadingDataBeginListener;
import mtrec.wherami.lbs.process.ILocationUtil.OnWifiConnectedListener;
import mtrec.wherami.lbs.process.ILocationUtil.OnWifiNotEnabledWhenScanningListener;
import mtrec.wherami.lbs.process.IndoorLocationManager;
import mtrec.wherami.lbs.process.IndoorLocationManager.OnVerificationListener;
import mtrec.wherami.lbs.datatype.Location;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import java.util.ArrayList;


public class WheramiPlugin extends CordovaPlugin {
    private CallbackContext callbackContext;
    private Activity activity = null;

    private static final String POSITION_DATA_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/mtrec/position_data";
    private TextView positionTextView;
    private IndoorLocationManager indoorLocationManager;

    @Override
    public boolean execute(String action, JSONArray args,
                           CallbackContext callbackContext) throws JSONException {
        this.callbackContext = callbackContext;
        this.activity = this.cordova.getActivity();
        boolean retValue = true;
        if (action.equals("initialize")) {
            this.initialize();
            //args
        } else if (action.equals("shortestPath")) {
            this.shortestPath(args);
        } else {
            retValue = false;
        }

        // if (action.equals("version")) {
        //     this.version();
        // } else if (action.equals("init")) {
        //     this.init(args);
        // } else if (action.equals("prepareToRender")) {
        //     this.prepareToRender(args);
        // } else if (action.equals("applicationCorrelationIDForEnvironment")) {
        //     this.clientMetadataID(args);
        // } else if (action.equals("clientMetadataID")) {
        //     this.clientMetadataID(args);
        // } else if (action.equals("renderSinglePaymentUI")) {
        //     this.renderSinglePaymentUI(args);
        // } else if (action.equals("renderFuturePaymentUI")) {
        //     this.renderFuturePaymentUI(args);
        // } else if (action.equals("renderProfileSharingUI")) {
        //     this.renderProfileSharingUI(args);
        // } else {
        //     retValue = false;
        // }

        return retValue;
    }


    // @Override
    // protected void onCreate(Bundle savedInstanceState) {
    //     super.onCreate(savedInstanceState);
    //     setContentView(R.layout.activity_main);

    //     initialize();
    //     positionTextView = (TextView)findViewById(R.id.position_text);
    // }

//JSONArray args throws JSONException
    private void initialize()  {
        // JSONObject jObject = args.getJSONObject(0);

        String siteName = "hkust";
        IndoorLocationManager.newInstance(activity, siteName, POSITION_DATA_PATH, new OnVerificationListener() {

            @Override
            public void onVerificationFinished(boolean isSuccess, String resultCode) {
                Log.e("mtrec", "Passed : " + isSuccess + "!!!!!" + resultCode);
                if(isSuccess) {
                    // positionTextView.setText("Verification passed!");
                    // callbackContext.success("Verfication passed!");
                    indoorLocationManager = IndoorLocationManager.getInstance();
                    startPositioning();
                    if(indoorLocationManager != null) {
                        indoorLocationManager.startLocation();
                    // callbackContext.success("start location");    
                    }
                        
                } else {
                    // positionTextView.setText("Verification failed : " + resultCode);
                    callbackContext.error("Verification failed : " + resultCode);
                }
            }
        });
    }



    private void startPositioning() {
        // indoorLocationManager.setOnWifiNotEnabledWhenScanningListener(new OnWifiNotEnabledWhenScanningListener() {

        //     @Override
        //     public void onWifiNotEnabledWhenScanning(WifiManager WifiManager) {
        //         // TODO Handle the case that the WIFI is disabled when scanning
        //         appendMessage("WIFI disabled");
        //     }
        // });
        // indoorLocationManager.setOnWifiConnectedListener(new OnWifiConnectedListener() {

        //     @Override
        //     public void onWifiConnected() {
        //         // TODO Handle the case that the WIFI is connected
        //         appendMessage("WIFI enabled");
        //     }
        // });
        // indoorLocationManager.setOnReadingDataBeginListener(new OnReadingDataBeginListener() {

        //     @Override
        //     public void onReadingDataBegin() {
        //         // TODO The callback that the positioning process is pending to read data
        //         runOnUiThread(new Runnable() {

        //             @Override
        //             public void run() {
        //                 // TODO Auto-generated method stub
        //                 appendMessage("Start reading data");
        //             }
        //         });
        //     }
        // });
        indoorLocationManager.setOnGetLocationResultListener(new OnGetLocationResultListener() {

            @Override
            public void onGetLocationResult(String area, PointF[] points,
                                            Integer[] radius, String symbol) {
                // TODO The callback that finish calculating the position
                if(area != null && points != null && points.length > 0) {
                    StringBuilder positionFoundString = new StringBuilder();
                    // positionFoundString.append("Position found\n");
                    int i = 0;
                    for(PointF point : points) {
                        // positionFoundString.append("Area : ").append(area).append(", x : ").append(point.x).append(", y : ")
                        //         .append(point.y).append(", radius : ").append(radius[i]).append("\n");
                        positionFoundString.append(area).append(",").append(point.x).append(",")
                                .append(point.y).append(",").append(radius[i]).append("\n");
                        i++;
                    }
                    callbackContext.success(positionFoundString.toString());
                    // callbackContext.sendPluginResult(positionFoundString.toString());
                    //appendMessage(positionFoundString.toString());
                } else {
                    callbackContext.error("Calculate failed, waiting another try");
                    //appendMessage("Calculate failed, waiting another try");
                }
            }
        });
    }

    private void shortestPath(JSONArray args) throws JSONException {
        // args: float start_area, float start_x, float start_y, float end_area, float end_x, float end_y       
        JSONArray jArray = args.getJSONArray(0);
        JSONObject start_jObject = jArray.getJSONObject(0);
        JSONObject end_jObject = jArray.getJSONObject(1);

        int start_area = start_jObject.getInt("area");
        float start_x = (float) start_jObject.getDouble("x");
        float start_y = (float) start_jObject.getDouble("y");

        int end_area = end_jObject.getInt("area");
        float end_x = (float) end_jObject.getDouble("x");
        float end_y = (float) end_jObject.getDouble("y");

        Location startLocation = new Location(start_area, start_x, start_y);
        Location endLocation = new Location(end_area, end_x, end_y);
        ArrayList<Location> path = IndoorLocationManager.getInstance().findShortestPath(startLocation, endLocation, start_area);
        
        // TODO: convert to JSON Object array
        // JSONArray path_jArray = new JSONArray(path);
        JSONArray path_jArray = new JSONArray;

        // TODO: success callback
        callbackContext.success(path_jArray);
    }
}
/*
    private void appendMessage(String message) {
        String lastText = positionTextView.getText() + "\n";
        positionTextView.setText(lastText + message);

        new Handler().post(new Runnable() {

            @Override
            public void run() {
                ((ScrollView)findViewById(R.id.scroll_view)).fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
    }

    private void stopPositioning() {
        if(indoorLocationManager != null) {
            try {
                indoorLocationManager.stopLocation();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void onButtonClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.start_button:
                appendMessage(POSITION_DATA_PATH);
                if(indoorLocationManager != null)
                    indoorLocationManager.startLocation();
                findViewById(R.id.start_button).setEnabled(false);
                break;

            case R.id.stop_button:
                stopPositioning();
                findViewById(R.id.start_button).setEnabled(true);
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        stopPositioning();
        finish();
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
*/
