package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import com.oculus.vrshell.panels.systemtooltip.TooltipDefinition;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class SystemTooltipModule extends RCTBaseJavaModule {
    public static final String MODULE_NAME = "SystemTooltipModule";

    public enum SystemTooltipColor {
        black,
        gray
    }

    public static class SystemTooltipDefinition extends NativeModuleParcel {
        public SystemTooltipColor color;
        public String hostLayerName;
        public SystemTooltipPosition position;
        public String subtext;
        public String text;
        public String tooltipId;

        public static final SystemTooltipDefinition makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            SystemTooltipDefinition systemTooltipDefinition = new SystemTooltipDefinition();
            systemTooltipDefinition.setFromJSONObject(jSONObject);
            return systemTooltipDefinition;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                SystemTooltipColor systemTooltipColor = this.color;
                if (systemTooltipColor != null) {
                    jSONObject.put(TooltipDefinition.TOOLTIP_COLOR_KEY, systemTooltipColor.name());
                }
                jSONObject.put(TooltipDefinition.TOOLTIP_HOST_LAYER_NAME_KEY, this.hostLayerName);
                jSONObject.put(TooltipDefinition.TOOLTIP_POSITION_KEY, this.position.name());
                String str = this.subtext;
                if (str != null) {
                    jSONObject.put(TooltipDefinition.TOOLTIP_SUBTEXT_KEY, str);
                }
                jSONObject.put("text", this.text);
                jSONObject.put(TooltipDefinition.TOOLTIP_ID_KEY, this.tooltipId);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            SystemTooltipColor valueOf;
            String str = null;
            if (jSONObject.isNull(TooltipDefinition.TOOLTIP_COLOR_KEY)) {
                valueOf = null;
            } else {
                valueOf = SystemTooltipColor.valueOf(jSONObject.optString(TooltipDefinition.TOOLTIP_COLOR_KEY));
            }
            this.color = valueOf;
            this.hostLayerName = jSONObject.optString(TooltipDefinition.TOOLTIP_HOST_LAYER_NAME_KEY);
            this.position = SystemTooltipPosition.valueOf(jSONObject.optString(TooltipDefinition.TOOLTIP_POSITION_KEY));
            if (!jSONObject.isNull(TooltipDefinition.TOOLTIP_SUBTEXT_KEY)) {
                str = jSONObject.optString(TooltipDefinition.TOOLTIP_SUBTEXT_KEY);
            }
            this.subtext = str;
            this.text = jSONObject.optString("text");
            this.tooltipId = jSONObject.optString(TooltipDefinition.TOOLTIP_ID_KEY);
        }
    }

    public enum SystemTooltipPosition {
        bottom,
        left,
        right,
        top
    }

    public static class SystemTooltipUVCoordinates extends NativeModuleParcel {
        public double hostLayerUCoordinate;
        public double hostLayerVCoordinate;

        public static final SystemTooltipUVCoordinates makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            SystemTooltipUVCoordinates systemTooltipUVCoordinates = new SystemTooltipUVCoordinates();
            systemTooltipUVCoordinates.setFromJSONObject(jSONObject);
            return systemTooltipUVCoordinates;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(TooltipDefinition.TOOLTIP_HOST_LAYER_U_COORDINATE_KEY, this.hostLayerUCoordinate);
                jSONObject.put(TooltipDefinition.TOOLTIP_HOST_LAYER_V_COORDINATE_KEY, this.hostLayerVCoordinate);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.hostLayerUCoordinate = jSONObject.optDouble(TooltipDefinition.TOOLTIP_HOST_LAYER_U_COORDINATE_KEY, 0.0d);
            this.hostLayerVCoordinate = jSONObject.optDouble(TooltipDefinition.TOOLTIP_HOST_LAYER_V_COORDINATE_KEY, 0.0d);
        }
    }

    public abstract void hideSystemTooltipImpl();

    public final String marshallJSConstants() {
        return null;
    }

    public abstract void showSystemTooltipImpl(SystemTooltipDefinition systemTooltipDefinition, SystemTooltipUVCoordinates systemTooltipUVCoordinates);

    public void shutdownModule() {
    }

    public static final List<Pair<String, String>> describe() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Pair("hideSystemTooltip", ""));
        arrayList.add(new Pair("showSystemTooltip", "jj"));
        return arrayList;
    }

    public final String getModuleName() {
        return MODULE_NAME;
    }

    public final void hideSystemTooltip() {
        hideSystemTooltipImpl();
    }

    public final void showSystemTooltip(JSONObject jSONObject, JSONObject jSONObject2) {
        showSystemTooltipImpl(SystemTooltipDefinition.makeFromJSONObject(jSONObject), SystemTooltipUVCoordinates.makeFromJSONObject(jSONObject2));
    }
}
