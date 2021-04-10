package com.oculus.modules;

import android.util.Log;
import com.oculus.modules.codegen.SystemTooltipModule;
import com.oculus.panellib.ShellIPC;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class SystemTooltipModuleImpl extends SystemTooltipModule {
    public static final String TAG = "SystemTooltipModuleImpl";

    @Override // com.oculus.modules.codegen.SystemTooltipModule
    public void hideSystemTooltipImpl() {
        ShellIPC.tooltipHide();
    }

    @Override // com.oculus.modules.codegen.SystemTooltipModule
    public void showSystemTooltipImpl(SystemTooltipModule.SystemTooltipDefinition systemTooltipDefinition, SystemTooltipModule.SystemTooltipUVCoordinates systemTooltipUVCoordinates) {
        try {
            JSONObject convertToJSONObject = systemTooltipDefinition.convertToJSONObject();
            JSONObject convertToJSONObject2 = systemTooltipUVCoordinates.convertToJSONObject();
            Iterator<String> keys = convertToJSONObject2.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                convertToJSONObject.put(next, convertToJSONObject2.get(next));
            }
            ShellIPC.tooltipShow(convertToJSONObject);
        } catch (JSONException e) {
            Log.w(TAG, "Unable to create system tooltip configuration.", e);
        }
    }
}
