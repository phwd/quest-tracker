package com.oculus.panelapp.anytimeui.logging;

import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;

public final class AUILogging {
    private static final String EXTRA_NAME = "name";
    private static final String NAME_INVALID_CALL_ORDER = "invalid_call_order";
    private static final String ROOT_EVENT = "oculus_aui_error";

    public static final void logInvalidCallOrder(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2) {
        anytimeUIAndroidPanelAppV2.getTelemetryManager().logEvent(ROOT_EVENT, "name", NAME_INVALID_CALL_ORDER);
    }
}
