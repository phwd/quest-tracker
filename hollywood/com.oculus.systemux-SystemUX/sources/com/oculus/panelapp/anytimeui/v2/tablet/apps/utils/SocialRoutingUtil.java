package com.oculus.panelapp.anytimeui.v2.tablet.apps.utils;

import com.oculus.panelapp.anytimeui.v2.AnytimeUIPanelAppBase;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.LibraryViewModel;
import com.oculus.vrshell.SystemUXRoute;

public class SocialRoutingUtil {
    private static final String SOCIAL_URI_WITH_INVALID_TAB_TO_FORCE_DEFAULT = "/social/invalid_tab_to_force_default";

    public static void navigateToSocial(LibraryViewModel libraryViewModel, AnytimeUIPanelAppBase anytimeUIPanelAppBase) {
        if (libraryViewModel.getEventsEntryEnabled()) {
            anytimeUIPanelAppBase.actionNavigate(SystemUXRoute.EVENTS, "");
        } else {
            anytimeUIPanelAppBase.actionNavigate(SystemUXRoute.SOCIAL, SOCIAL_URI_WITH_INVALID_TAB_TO_FORCE_DEFAULT);
        }
    }
}
