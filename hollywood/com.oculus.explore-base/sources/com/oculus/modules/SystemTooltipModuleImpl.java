package com.oculus.modules;

import android.util.Log;
import com.oculus.modules.codegen.SystemTooltipModule;
import com.oculus.panellib.ShellIPC;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class SystemTooltipModuleImpl extends SystemTooltipModule {
    private static final String TAG = SystemTooltipModuleImpl.class.getSimpleName();

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.SystemTooltipModule
    public void showSystemTooltipImpl(SystemTooltipModule.SystemTooltipDefinition tooltipDefinition, SystemTooltipModule.SystemTooltipUVCoordinates tooltipCoordinates) {
        Log.d(TAG, "::showSystemTooltipImpl");
        try {
            JSONObject tooltipConfigurationJson = tooltipDefinition.convertToJSONObject();
            JSONObject tooltipCoordinatesJson = tooltipCoordinates.convertToJSONObject();
            Iterator<String> tooltipCoordinateKeyIterator = tooltipCoordinatesJson.keys();
            while (tooltipCoordinateKeyIterator.hasNext()) {
                String tooltipCoordinateKey = tooltipCoordinateKeyIterator.next();
                tooltipConfigurationJson.put(tooltipCoordinateKey, tooltipCoordinatesJson.get(tooltipCoordinateKey));
            }
            ShellIPC.tooltipShow(tooltipConfigurationJson);
        } catch (JSONException jsonException) {
            Log.w(TAG, "Unable to create system tooltip configuration.", jsonException);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.SystemTooltipModule
    public void hideSystemTooltipImpl() {
        Log.d(TAG, "::hideSystemTooltipImpl");
        ShellIPC.tooltipHide();
    }
}
