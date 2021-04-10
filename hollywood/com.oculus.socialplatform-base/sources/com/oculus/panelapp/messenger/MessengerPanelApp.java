package com.oculus.panelapp.messenger;

import com.oculus.common.deviceconfig.DeviceConfigMC;
import com.oculus.common.socialtablet.panelapp.SocialTabletPanelApp;
import com.oculus.panelapp.messenger.api.MessengerAPIManager;
import com.oculus.panelapp.messenger.api.MessengerAPIType;
import com.oculus.panelapp.messenger.fetchers.ICanViewerMessageFetcher;
import com.oculus.panelapp.messenger.fetchers.IContactFetcher;
import com.oculus.panelapp.messenger.views.MessengerEmojiHandler;
import com.oculus.tablet.logging.SectionTrackerEvent;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.panels.AndroidPanelApp;

public interface MessengerPanelApp extends SocialTabletPanelApp {
    void actionNavigate(SystemUXRoute systemUXRoute, String str);

    void actionNavigate(String str);

    void actionNavigateToProfile(long j);

    MessengerAPIManager getAPIManager();

    AndroidPanelApp getAndroidPanelApp();

    ICanViewerMessageFetcher getCanViewerMessageFetcher();

    IContactFetcher getContactQuery();

    boolean getDeviceConfig(DeviceConfigMC deviceConfigMC);

    MessengerEmojiHandler getEmojiHandler();

    void logSectionEntry(SectionTrackerEvent sectionTrackerEvent);

    void updateAPIs(MessengerAPIType messengerAPIType);
}
