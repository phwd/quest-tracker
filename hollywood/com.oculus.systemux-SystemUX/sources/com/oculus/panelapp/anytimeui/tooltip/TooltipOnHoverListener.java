package com.oculus.panelapp.anytimeui.tooltip;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.vrshell.panels.systemtooltip.TooltipDefinition;
import com.oculus.vrshell.panels.systemtooltip.TooltipPosition;
import com.oculus.vrshell.panels.systemtooltip.TooltipUVCoordinates;

public final class TooltipOnHoverListener implements View.OnHoverListener {
    private static final String TAG = LoggingUtil.tag(TooltipOnHoverListener.class);
    private final TooltipController mTooltipController;
    private final TooltipState mTooltipState;

    public TooltipOnHoverListener(TooltipController tooltipController, TooltipState tooltipState) {
        this.mTooltipController = tooltipController;
        this.mTooltipState = tooltipState;
    }

    public boolean onHover(View view, MotionEvent motionEvent) {
        if (!this.mTooltipState.isTooltipEnabled()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 9) {
            showTooltip(view);
            return false;
        } else if (action != 10) {
            return false;
        } else {
            hideTooltip();
            return false;
        }
    }

    private void showTooltip(View view) {
        if (this.mTooltipController == null) {
            Log.w(TAG, "Unable to show tooltip: null tooltip controller provided.");
            return;
        }
        TooltipDefinition tooltipDefinition = this.mTooltipState.getTooltipDefinition();
        TooltipUVCoordinates tooltipUVCoordinatesForView = getTooltipUVCoordinatesForView(view, tooltipDefinition.getTooltipPosition());
        if (tooltipUVCoordinatesForView == null) {
            Log.w(TAG, "Unable to show tooltip: UV coordinates could not be calculated.");
        } else {
            this.mTooltipController.showTooltip(tooltipDefinition, tooltipUVCoordinatesForView);
        }
    }

    public void hideTooltip() {
        this.mTooltipController.hideTooltip(this.mTooltipState.getTooltipDefinition());
    }

    public static TooltipUVCoordinates getTooltipUVCoordinatesForView(View view, TooltipPosition tooltipPosition) {
        View rootView = view.getRootView();
        int width = rootView.getWidth();
        int height = rootView.getHeight();
        if (width == 0 || height == 0) {
            Log.w(TAG, "Unable to compute tooltip target UV coordinates: root view has not been initialized.");
            return null;
        }
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        return TooltipUVCoordinates.getTooltipUVCoordinates(iArr[0], iArr[1], view.getWidth(), view.getHeight(), width, height, tooltipPosition);
    }
}
