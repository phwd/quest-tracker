package com.oculus.vrshell.panels.systemtooltip;

import android.util.Log;
import com.oculus.vrshell.sdk.BuildConfig;
import org.json.JSONException;
import org.json.JSONObject;

public final class TooltipDefinition {
    private static final String TAG = TooltipDefinition.class.getSimpleName();
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

    public TooltipDefinition(String hostLayerName, String tooltipText, TooltipPosition tooltipPosition, TooltipColor tooltipColor, String tooltipId) {
        this(hostLayerName, tooltipText, BuildConfig.FLAVOR, tooltipPosition, tooltipColor, tooltipId);
    }

    public TooltipDefinition(String hostLayerName, String tooltipText, String tooltipSubtext, TooltipPosition tooltipPosition, TooltipColor tooltipColor, String tooltipId) {
        this.mHostLayerName = hostLayerName;
        this.mTooltipText = tooltipText;
        this.mTooltipSubtext = tooltipSubtext;
        this.mTooltipPosition = tooltipPosition;
        this.mTooltipColor = tooltipColor;
        this.mTooltipId = tooltipId;
    }

    public String getTooltipConfigurationJson(TooltipUVCoordinates tooltipUVCoordinates) {
        try {
            JSONObject tooltipConfigurationJson = new JSONObject();
            tooltipConfigurationJson.put(TOOLTIP_ID_KEY, this.mTooltipId);
            tooltipConfigurationJson.put(TOOLTIP_TEXT_KEY, this.mTooltipText);
            tooltipConfigurationJson.put(TOOLTIP_SUBTEXT_KEY, this.mTooltipSubtext);
            tooltipConfigurationJson.put(TOOLTIP_POSITION_KEY, this.mTooltipPosition.getIPCString());
            tooltipConfigurationJson.put(TOOLTIP_COLOR_KEY, this.mTooltipColor.getIPCString());
            tooltipConfigurationJson.put(TOOLTIP_HOST_LAYER_NAME_KEY, this.mHostLayerName);
            tooltipConfigurationJson.put(TOOLTIP_HOST_LAYER_U_COORDINATE_KEY, (double) tooltipUVCoordinates.U);
            tooltipConfigurationJson.put(TOOLTIP_HOST_LAYER_V_COORDINATE_KEY, (double) tooltipUVCoordinates.V);
            return tooltipConfigurationJson.toString();
        } catch (JSONException jsonException) {
            Log.w(TAG, "Unable to get tooltip configuration JSON.", jsonException);
            return BuildConfig.FLAVOR;
        }
    }

    public void setTooltipText(String tooltipText) {
        this.mTooltipText = tooltipText;
    }

    public void setTooltipSubtext(String tooltipSubtext) {
        this.mTooltipSubtext = tooltipSubtext;
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
