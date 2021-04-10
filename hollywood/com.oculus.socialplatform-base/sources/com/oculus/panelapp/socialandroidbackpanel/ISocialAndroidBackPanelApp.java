package com.oculus.panelapp.socialandroidbackpanel;

import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.common.sociallogger.SocialLogger;
import com.oculus.common.socialtablet.deviceconfig.DeviceConfigSocialPlatformMC;
import com.oculus.ocui.OCEventHandler;
import com.oculus.panelapp.socialandroidbackpanel.graphql.GraphQLService;

public interface ISocialAndroidBackPanelApp extends OCEventHandler {
    GraphQLService getGraphQLService();

    SocialLogger getLogger();

    boolean isGKEnabled(Gatekeeper gatekeeper);

    boolean isGKEnabled(DeviceConfigSocialPlatformMC deviceConfigSocialPlatformMC);

    void quitApp();
}
