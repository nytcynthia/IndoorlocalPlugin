package com.wheramiplugin;

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
        } 
        else if (action.equals("shortestPath")) {
            this.shortestPath(args);
        } else {
            retValue = false;
        }
        return retValue;
    }

    private void initialize()  {
        // JSONObject jObject = args.getJSONObject(0);

        String siteName = "hkust";
        IndoorLocationManager.newInstance(activity, siteName, POSITION_DATA_PATH, new OnVerificationListener() {

            @Override
            public void onVerificationFinished(boolean isSuccess, String resultCode) {
                Log.e("mtrec", "Passed : " + isSuccess + "!!!!!" + resultCode);
                if(isSuccess) {
                    indoorLocationManager = IndoorLocationManager.getInstance();
                    startPositioning();
                    if(indoorLocationManager != null) {
                        indoorLocationManager.startLocation();
                    }
                        
                } else {
                    callbackContext.error("Verification failed : " + resultCode);
                }
            }
        });
    }

    private void startPositioning() {
        indoorLocationManager.setOnGetLocationResultListener(new OnGetLocationResultListener() {

            @Override
            public void onGetLocationResult(String area, PointF[] points,
                                            Integer[] radius, String symbol) {
                if(area != null && points != null && points.length > 0) {
                    StringBuilder positionFoundString = new StringBuilder();
                    int i = 0;
                    for(PointF point : points) {
                        positionFoundString.append(area).append(",").append(point.x).append(",")
                                .append(point.y).append(",").append(radius[i]).append("\n");
                        i++;
                    }
                    callbackContext.success(positionFoundString.toString());
                } else {
                    callbackContext.error("Calculate failed, waiting another try");
                }
            }
        });
    }

    private void shortestPath(JSONArray args) throws JSONException {
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

        if (indoorLocationManager != null) {
            ArrayList<Location> path = IndoorLocationManager.getInstance().findShortestPath(startLocation, endLocation, start_area);
            String path_result = "";
            for (Location temp : path) {
                path_result = path_result.concat(temp.areaId + ",");
                path_result = path_result.concat(temp.x + ",");
                path_result = path_result.concat(temp.y + "|");
            }
            callbackContext.success(path_result);
        } else {
            callbackContext.error("indoorLocationManager is not initialized. Please start postioning before use.");
        }
    }
}