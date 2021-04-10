package com.oculus.panelapp.socialandroidbackpanel.views;

import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.provider.OculusContent;

public enum SocialOverlayPanelViewType {
    JOIN_PARTY("join_party"),
    PARTY_PRIVACY(OculusContent.FriendList.PARTY_PRIVACY),
    APPLICATION_INVITES("application_invites"),
    START_MESSENGER_CALL("start_messenger_call"),
    UNKNOWN("unknown");
    
    public static final String TAG = LoggingUtil.tag(SocialOverlayPanelViewType.class);
    public final String mValue;

    /* access modifiers changed from: public */
    SocialOverlayPanelViewType(String str) {
        this.mValue = str;
    }

    public static SocialOverlayPanelViewType fromString(String str) {
        SocialOverlayPanelViewType[] values = values();
        for (SocialOverlayPanelViewType socialOverlayPanelViewType : values) {
            if (socialOverlayPanelViewType.mValue.equals(str)) {
                return socialOverlayPanelViewType;
            }
        }
        Log.e(TAG, String.format("Unexpected view type \"%s\".", str));
        return UNKNOWN;
    }
}
