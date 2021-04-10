package com.oculus.horizoncontent.social;

import com.oculus.common.logutilities.LoggingUtil;

public enum SocialGroupLaunchState {
    PROPOSED,
    PREPARING,
    LAUNCHED;
    
    private static final String TAG = LoggingUtil.tag(SocialGroupLaunchState.class);

    public static SocialGroupLaunchState fromString(String str) {
        if (str == null) {
            return PROPOSED;
        }
        if (str.equals("PROPOSED")) {
            return PROPOSED;
        }
        if (str.equals("PREPARING")) {
            return PREPARING;
        }
        return LAUNCHED;
    }
}
