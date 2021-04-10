package com.oculus.ocms.locationservices.graphql;

import com.facebook.wifiscan.WifiScanResult;
import java.util.List;
import javax.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LocationWifiGraphQLParams {
    private static final String COARSE_LOCATION_KEY = "coarse";
    private static final String IP_SIGNALS_KEY = "signals";
    private static final String WIFI_SCAN_OBJECTS_KEY = "wifi";
    private JSONObject mIPParams;
    private JSONArray mWifiParams;

    public LocationWifiGraphQLParams(@Nullable WifiScanResult wifiScanResult, @Nullable List<WifiScanResult> list, boolean z, boolean z2) {
        if (z2) {
            this.mIPParams = new JSONObject();
            try {
                this.mIPParams.put(IP_SIGNALS_KEY, new JSONObject());
            } catch (JSONException unused) {
            }
        } else {
            this.mWifiParams = new JSONArray();
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            try {
                jSONObject.put(WIFI_SCAN_OBJECTS_KEY, jSONArray);
                jSONObject.put(COARSE_LOCATION_KEY, z);
            } catch (JSONException unused2) {
            }
            this.mWifiParams.put(jSONObject);
            parseWifiScanResults(jSONArray, wifiScanResult, list);
        }
    }

    private void parseWifiScanResults(JSONArray jSONArray, @Nullable WifiScanResult wifiScanResult, @Nullable List<WifiScanResult> list) {
        JSONObject convertToJSON;
        JSONObject convertToJSON2 = convertToJSON(wifiScanResult, true);
        if (convertToJSON2 != null) {
            jSONArray.put(convertToJSON2);
        }
        if (list != null) {
            for (WifiScanResult wifiScanResult2 : list) {
                if ((wifiScanResult == null || !wifiScanResult.BSSID.equals(wifiScanResult2.BSSID)) && (convertToJSON = convertToJSON(wifiScanResult2, false)) != null) {
                    jSONArray.put(convertToJSON);
                }
            }
        }
    }

    @Nullable
    private static JSONObject convertToJSON(@Nullable WifiScanResult wifiScanResult, boolean z) {
        if (wifiScanResult == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("bssid", wifiScanResult.BSSID);
            jSONObject.put("rssi", wifiScanResult.level);
            if (wifiScanResult.frequency != null) {
                jSONObject.put("frequency_mhz", wifiScanResult.frequency);
            }
            jSONObject.put("timestamp", wifiScanResult.timestampMs);
            jSONObject.put("is_connected", z);
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }

    public String getJSONParams() {
        JSONObject jSONObject = this.mIPParams;
        if (jSONObject != null) {
            return jSONObject.toString();
        }
        JSONArray jSONArray = this.mWifiParams;
        return jSONArray != null ? jSONArray.toString() : "";
    }
}
