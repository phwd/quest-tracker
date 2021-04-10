package com.oculus.panelapp.assistant.dialogs;

import com.oculus.panelapp.assistant.AssistantPanelApp;
import com.oculus.panelapp.assistant.config.BroadcastConfig;

public interface ISurface {
    AssistantPanelApp getApp();

    BroadcastConfig getBroadcastConfig();

    String getSurfaceId();

    String getSurfaceType();

    long getVisibleDuration();
}
