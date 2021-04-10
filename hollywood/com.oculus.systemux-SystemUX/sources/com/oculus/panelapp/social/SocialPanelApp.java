package com.oculus.panelapp.social;

import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.ocui.OCEventHandler;
import com.oculus.panelapp.social.logging.SocialPartyEvent;
import com.oculus.systemdialog.DialogManager;
import com.oculus.tablet.logging.ClickEventButtonId;
import com.oculus.tablet.logging.SectionTrackerEvent;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.panels.AndroidPanelApp;

public interface SocialPanelApp extends OCEventHandler {
    SocialViewModel acquireSocialViewModel();

    void actionNavigate(SystemUXRoute systemUXRoute, String str);

    void actionNavigate(String str, String str2);

    AndroidPanelApp getAndroidPanelApp();

    DialogManager getDialogManager();

    String getLocalUserId();

    String getReturnComponent();

    boolean isGKEnabled(Gatekeeper gatekeeper);

    boolean isSocialPlatformApp();

    void logButtonClick(ClickEventButtonId clickEventButtonId);

    void logSectionEntry(SectionTrackerEvent sectionTrackerEvent);

    void logSocialPartyEvent(SocialPartyEvent socialPartyEvent, String... strArr);

    void rawLogEvent(String str, String... strArr);

    void rawLogJsonEvent(String str, String str2);

    void releaseSocialViewModel();
}
