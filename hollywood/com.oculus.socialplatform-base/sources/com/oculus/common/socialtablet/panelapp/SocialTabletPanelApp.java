package com.oculus.common.socialtablet.panelapp;

import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.sociallogger.SocialLogger;
import com.oculus.common.sociallogger.SurfaceType;
import com.oculus.common.socialtablet.fetchers.FBLinkingFetcher;
import com.oculus.common.socialtablet.util.ImageHandler;
import com.oculus.ocui.OCEventHandler;

public interface SocialTabletPanelApp extends OCEventHandler {
    FBLinkingFetcher getFBLinkingFetcher();

    ImageHandler getImageHandler();

    SocialLogger getLogger();

    void logButtonClick(ClickEventButtonId clickEventButtonId, SurfaceType surfaceType);
}
