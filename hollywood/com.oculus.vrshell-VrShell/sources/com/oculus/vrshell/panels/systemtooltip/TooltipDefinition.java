package com.oculus.vrshell.panels.systemtooltip;

import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

public final class TooltipDefinition {
    private static final String TAG = "TooltipDefinition";
    public static final String TOOLTIP_COLOR_KEY = "color";
    public static final String TOOLTIP_HOST_LAYER_NAME_KEY = "hostLayerName";
    public static final String TOOLTIP_HOST_LAYER_U_COORDINATE_KEY = "hostLayerUCoordinate";
    public static final String TOOLTIP_HOST_LAYER_V_COORDINATE_KEY = "hostLayerVCoordinate";
    public static final String TOOLTIP_ID_KEY = "tooltipId";
    public static final String TOOLTIP_POSITION_KEY = "position";
    public static final String TOOLTIP_SUBTEXT_KEY = "subtext";
    public static final String TOOLTIP_TEXT_KEY = "text";
    private final String mHostLayerName;
    private final TooltipColor mTooltipColor;
    private final String mTooltipId;
    private final TooltipPosition mTooltipPosition;
    private String mTooltipSubtext;
    private String mTooltipText;

    public TooltipDefinition(String str, String str2, TooltipPosition tooltipPosition, TooltipColor tooltipColor, String str3) {
        this(str, str2, "", tooltipPosition, tooltipColor, str3);
    }

    public TooltipDefinition(String str, String str2, String str3, TooltipPosition tooltipPosition, TooltipColor tooltipColor, String str4) {
        this.mHostLayerName = str;
        this.mTooltipText = str2;
        this.mTooltipSubtext = str3;
        this.mTooltipPosition = tooltipPosition;
        this.mTooltipColor = tooltipColor;
        this.mTooltipId = str4;
    }

    public String getTooltipConfigurationJson(TooltipUVCoordinates tooltipUVCoordinates) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(TOOLTIP_ID_KEY, this.mTooltipId);
            jSONObject.put("text", this.mTooltipText);
            jSONObject.put(TOOLTIP_SUBTEXT_KEY, this.mTooltipSubtext);
            jSONObject.put(TOOLTIP_POSITION_KEY, this.mTooltipPosition.getIPCString());
            jSONObject.put("color", this.mTooltipColor.getIPCString());
            jSONObject.put(TOOLTIP_HOST_LAYER_NAME_KEY, this.mHostLayerName);
            jSONObject.put(TOOLTIP_HOST_LAYER_U_COORDINATE_KEY, (double) tooltipUVCoordinates.U);
            jSONObject.put(TOOLTIP_HOST_LAYER_V_COORDINATE_KEY, (double) tooltipUVCoordinates.V);
            return jSONObject.toString();
        } catch (JSONException e) {
            Log.w(TAG, "Unable to get tooltip configuration JSON.", e);
            return "";
        }
    }

    public void setTooltipText(String str) {
        this.mTooltipText = str;
    }

    public void setTooltipSubtext(String str) {
        this.mTooltipSubtext = str;
    }

    public String getHostLayerName() {
        return this.mHostLayerName;
    }

    public String getTooltipText() {
        return this.mTooltipText;
    }

    public String getTooltipSubtext() {
        return this.mTooltipSubtext;
    }

    public TooltipPosition getTooltipPosition() {
        return this.mTooltipPosition;
    }

    public String getTooltipId() {
        return this.mTooltipId;
    }
}
