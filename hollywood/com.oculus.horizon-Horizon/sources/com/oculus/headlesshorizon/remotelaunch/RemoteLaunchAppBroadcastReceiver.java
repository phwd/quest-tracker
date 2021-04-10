package com.oculus.headlesshorizon.remotelaunch;

import X.C00910Hi;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;

@Dependencies({})
public class RemoteLaunchAppBroadcastReceiver extends C00910Hi {
    public static final String ACTION_REMOTE_LAUNCH_APP = "com.oculus.horizon.REMOTE_LAUNCH_APP";

    @Inject
    public RemoteLaunchAppBroadcastReceiver() {
        super(ACTION_REMOTE_LAUNCH_APP, new RemoteLaunchAppAction());
    }
}
