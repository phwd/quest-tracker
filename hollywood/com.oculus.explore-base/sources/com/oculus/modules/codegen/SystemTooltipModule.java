package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.common.build.BuildConfig;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class SystemTooltipModule extends RCTBaseJavaModule {
    protected static final String MODULE_NAME = SystemTooltipModule.class.getSimpleName();

    public enum SystemTooltipColor {
        black,
        gray
    }

    public enum SystemTooltipPosition {
        bottom,
        left,
        right,
        top
    }

    /* access modifiers changed from: protected */
    public abstract void hideSystemTooltipImpl();

    /* access modifiers changed from: protected */
    public abstract void showSystemTooltipImpl(SystemTooltipDefinition systemTooltipDefinition, SystemTooltipUVCoordinates systemTooltipUVCoordinates);

    /* access modifiers changed from: protected */
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("hideSystemTooltip", BuildConfig.PROVIDER_SUFFIX));
        list.add(new Pair<>("showSystemTooltip", "jj"));
        return list;
    }

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        return null;
    }

    /* access modifiers changed from: protected */
    public final void hideSystemTooltip() {
        hideSystemTooltipImpl();
    }

    /* access modifiers changed from: protected */
    public final void showSystemTooltip(JSONObject tooltipDefinition, JSONObject tooltipCoordinates) {
        showSystemTooltipImpl(SystemTooltipDefinition.makeFromJSONObject(tooltipDefinition), SystemTooltipUVCoordinates.makeFromJSONObject(tooltipCoordinates));
    }

    public void shutdownModule() {
    }

    public static class SystemTooltipDefinition extends NativeModuleParcel {
        public SystemTooltipColor color;
        public String hostLayerName;
        public SystemTooltipPosition position;
        public String subtext;
        public String text;
        public String tooltipId;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                if (this.color != null) {
                    parcel.put("color", this.color.name());
                }
                parcel.put("hostLayerName", this.hostLayerName);
                parcel.put("position", this.position.name());
                if (this.subtext != null) {
                    parcel.put("subtext", this.subtext);
                }
                parcel.put("text", this.text);
                parcel.put("tooltipId", this.tooltipId);
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            String str = null;
            this.color = json.isNull("color") ? null : SystemTooltipColor.valueOf(json.optString("color"));
            this.hostLayerName = json.optString("hostLayerName");
            this.position = SystemTooltipPosition.valueOf(json.optString("position"));
            if (!json.isNull("subtext")) {
                str = json.optString("subtext");
            }
            this.subtext = str;
            this.text = json.optString("text");
            this.tooltipId = json.optString("tooltipId");
        }

        public static final SystemTooltipDefinition makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            SystemTooltipDefinition result = new SystemTooltipDefinition();
            result.setFromJSONObject(json);
            return result;
        }
    }

    public static class SystemTooltipUVCoordinates extends NativeModuleParcel {
        public double hostLayerUCoordinate;
        public double hostLayerVCoordinate;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("hostLayerUCoordinate", this.hostLayerUCoordinate);
                parcel.put("hostLayerVCoordinate", this.hostLayerVCoordinate);
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.hostLayerUCoordinate = json.optDouble("hostLayerUCoordinate", 0.0d);
            this.hostLayerVCoordinate = json.optDouble("hostLayerVCoordinate", 0.0d);
        }

        public static final SystemTooltipUVCoordinates makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            SystemTooltipUVCoordinates result = new SystemTooltipUVCoordinates();
            result.setFromJSONObject(json);
            return result;
        }
    }
}
