package com.oculus.panelapp.people.util;

import android.net.Uri;
import com.oculus.panelapp.people.PeopleTabletPanelApp;
import com.oculus.vrshell.SystemUXRoute;

public class FBLinkingUpsellLauncher {
    public static final String CONTAINER_PEOPLE_TABLET = "aui_v2_people_tablet";
    public static final String SOURCE_PEOPLE_TABLET = "aui_v2_people_tablet";

    public static class LoggingParameters {
        public String mAction;
        public String mContainer;
        public String mEntryPoint;
        public String mMustInteract;
        public String mProduct;
        public String mSource;

        public LoggingParameters(String str, String str2, String str3, String str4, String str5, String str6) {
            this.mSource = str;
            this.mAction = str2;
            this.mContainer = str3;
            this.mEntryPoint = str4;
            this.mMustInteract = str5;
            this.mProduct = str6;
        }
    }

    public static String getUpsellRoute(LoggingParameters loggingParameters) {
        return new Uri.Builder().encodedPath("/fb-connect/").appendPath(loggingParameters.mSource).appendQueryParameter("action", loggingParameters.mAction).appendQueryParameter("container", loggingParameters.mContainer).appendQueryParameter("entrypoint", loggingParameters.mEntryPoint).appendQueryParameter("mustInteract", loggingParameters.mMustInteract).appendQueryParameter("product", loggingParameters.mProduct).build().toString();
    }

    public static void showUpsell(PeopleTabletPanelApp peopleTabletPanelApp, String str, String str2) {
        peopleTabletPanelApp.mFrameCommandChannel.launch(SystemUXRoute.SOCIAL.path(), getUpsellRoute(new LoggingParameters("aui_v2_people_tablet", str, "aui_v2_people_tablet", str2, "true", peopleTabletPanelApp.getLoggingSurface())));
    }
}
