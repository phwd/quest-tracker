package com.oculus.panelapp.anytimeui.tooltip;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.vrshell.panels.systemtooltip.TooltipColor;
import com.oculus.vrshell.panels.systemtooltip.TooltipDefinition;
import com.oculus.vrshell.panels.systemtooltip.TooltipPosition;
import com.oculus.vrshell.panels.systemtooltip.TooltipUVCoordinates;
import java.util.HashMap;
import java.util.Map;

public final class TooltipController {
    private static final String TAG = LoggingUtil.tag(TooltipController.class);
    private static final String TOOLTIP_DATA_COLOR_KEY = "tooltip_color";
    private static final String TOOLTIP_DATA_ENABLED_KEY = "tooltip_enabled";
    private static final String TOOLTIP_DATA_FIELD_DELIMITER = ",";
    private static final String TOOLTIP_DATA_FIELD_KEY_VALUE_DELIMITER = "=";
    private static final String TOOLTIP_DATA_ID_KEY = "tooltip_id";
    private static final String TOOLTIP_DATA_POSITION_KEY = "tooltip_position";
    private TooltipDefinition mCurrentTooltipDefinition;
    private final TooltipHost mTooltipHost;
    private final Map<String, TooltipState> mTooltipIdToTooltipState = new HashMap();
    private final Map<View, String> mViewToTooltipId = new HashMap();

    public interface TooltipHost {
        void onHideTooltip();

        void onShowTooltip(TooltipDefinition tooltipDefinition, TooltipUVCoordinates tooltipUVCoordinates);
    }

    public TooltipController(TooltipHost tooltipHost) {
        this.mTooltipHost = tooltipHost;
    }

    public void initializeTooltipsOnSubtree(View view, String str, int i, int i2, int i3) {
        initializeTooltipOnView(view, str, i, i2, i3);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i4 = 0; i4 < viewGroup.getChildCount(); i4++) {
                initializeTooltipsOnSubtree(viewGroup.getChildAt(i4), str, i, i2, i3);
            }
        }
    }

    private void initializeTooltipOnView(View view, String str, int i, int i2, int i3) {
        if (view == null) {
            Log.w(TAG, "Unable to initialize tooltip on view: null View provided.");
            return;
        }
        Object tag = view.getTag(i);
        Object tag2 = view.getTag(i3);
        Object tag3 = view.getTag(i2);
        if ((tag instanceof String) && (tag2 instanceof String)) {
            TooltipState tooltipState = getTooltipState(str, (String) tag, tag3 instanceof String ? (String) tag3 : "", (String) tag2);
            if (tooltipState == null) {
                Log.w(TAG, "Unable to initialize tooltip on view: tooltip state could not be determined.");
                return;
            }
            view.setOnHoverListener(new TooltipOnHoverListener(this, tooltipState));
            String tooltipId = tooltipState.getTooltipDefinition().getTooltipId();
            this.mTooltipIdToTooltipState.put(tooltipId, tooltipState);
            this.mViewToTooltipId.put(view, tooltipId);
        }
    }

    public void removeTooltipsOnSubtree(View view) {
        removeTooltipsOnView(view);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                removeTooltipsOnSubtree(viewGroup.getChildAt(i));
            }
        }
    }

    public void removeTooltipsOnView(View view) {
        if (this.mViewToTooltipId.containsKey(view)) {
            this.mTooltipIdToTooltipState.remove(this.mViewToTooltipId.remove(view));
        }
    }

    public void setTooltipText(String str, String str2) {
        TooltipState tooltipState = this.mTooltipIdToTooltipState.get(str);
        if (tooltipState == null) {
            Log.w(TAG, String.format("Unable to set the tooltip text: the given tooltip id \"%s\" has no corresponding tooltip state.", str));
            return;
        }
        tooltipState.getTooltipDefinition().setTooltipText(str2);
    }

    public void setTooltipSubtext(String str, String str2) {
        TooltipState tooltipState = this.mTooltipIdToTooltipState.get(str);
        if (tooltipState == null) {
            Log.w(TAG, String.format("Unable to set the tooltip subtext: the given tooltip id \"%s\" has no corresponding tooltip state.", str));
            return;
        }
        tooltipState.getTooltipDefinition().setTooltipSubtext(str2);
    }

    public void setTooltipEnabled(String str, boolean z) {
        TooltipDefinition tooltipDefinition;
        TooltipState tooltipState = this.mTooltipIdToTooltipState.get(str);
        if (tooltipState == null) {
            Log.w(TAG, String.format("Unable to enable/disable the tooltip: the given tooltip id \"%s\" has no corresponding tooltip state.", str));
            return;
        }
        tooltipState.setTooltipEnabled(z);
        if (!z && (tooltipDefinition = this.mCurrentTooltipDefinition) != null && tooltipDefinition.getTooltipId().equals(str)) {
            hideTooltip(tooltipState.getTooltipDefinition());
        }
    }

    public void showTooltip(TooltipDefinition tooltipDefinition, TooltipUVCoordinates tooltipUVCoordinates) {
        this.mTooltipHost.onShowTooltip(tooltipDefinition, tooltipUVCoordinates);
        this.mCurrentTooltipDefinition = tooltipDefinition;
    }

    public void hideTooltip(TooltipDefinition tooltipDefinition) {
        hideTooltip(tooltipDefinition.getTooltipId());
    }

    public void hideTooltip(String str) {
        TooltipDefinition tooltipDefinition = this.mCurrentTooltipDefinition;
        if (tooltipDefinition != null && tooltipDefinition.getTooltipId().equals(str)) {
            this.mTooltipHost.onHideTooltip();
            this.mCurrentTooltipDefinition = null;
        }
    }

    private TooltipState getTooltipState(String str, String str2, String str3, String str4) {
        TooltipColor tooltipColor;
        boolean z;
        String[] split = str4.split(",");
        if (split.length < 2) {
            return null;
        }
        String[] split2 = split[0].split(TOOLTIP_DATA_FIELD_KEY_VALUE_DELIMITER);
        String[] split3 = split[1].split(TOOLTIP_DATA_FIELD_KEY_VALUE_DELIMITER);
        if (split2.length != 2 || !split2[0].equals(TOOLTIP_DATA_ID_KEY) || split3.length != 2 || !split3[0].equals(TOOLTIP_DATA_POSITION_KEY)) {
            return null;
        }
        if (split.length >= 3) {
            String[] split4 = split[2].split(TOOLTIP_DATA_FIELD_KEY_VALUE_DELIMITER);
            if (split4.length != 2 || !split4[0].equals(TOOLTIP_DATA_COLOR_KEY)) {
                tooltipColor = TooltipColor.Black;
            } else {
                tooltipColor = TooltipColor.getTooltipColor(split4[1]);
            }
        } else {
            tooltipColor = TooltipColor.Black;
        }
        if (split.length >= 4) {
            String[] split5 = split[3].split(TOOLTIP_DATA_FIELD_KEY_VALUE_DELIMITER);
            if (split5.length != 2 || !split5[0].equals(TOOLTIP_DATA_ENABLED_KEY)) {
                Log.w(TAG, String.format("Invalid tooltip data specified: %s. Defaulting to enabled tooltip.", str4));
            } else {
                z = !"false".equals(split5[1]);
                return new TooltipState(new TooltipDefinition(str, str2, str3, TooltipPosition.getTooltipPosition(split3[1]), tooltipColor, split2[1]), z);
            }
        }
        z = true;
        return new TooltipState(new TooltipDefinition(str, str2, str3, TooltipPosition.getTooltipPosition(split3[1]), tooltipColor, split2[1]), z);
    }
}
