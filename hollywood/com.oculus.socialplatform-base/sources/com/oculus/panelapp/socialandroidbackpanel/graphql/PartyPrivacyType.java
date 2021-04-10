package com.oculus.panelapp.socialandroidbackpanel.graphql;

import android.util.Log;
import com.facebook.acra.CrashTimeDataCollector;
import com.oculus.common.logutilities.LoggingUtil;

public enum PartyPrivacyType {
    CLOSED("CLOSED"),
    JOINABLE_BY_FRIENDS("JOINABLE_BY_FRIENDS"),
    OPEN("OPEN"),
    UNKNOWN(CrashTimeDataCollector.ANDROID_RUNTIME_UNKNOWN);
    
    public static final String TAG = LoggingUtil.tag(PartyPrivacyType.class);
    public final String mValue;

    /* access modifiers changed from: public */
    PartyPrivacyType(String str) {
        this.mValue = str;
    }

    public static PartyPrivacyType fromString(String str) {
        PartyPrivacyType[] values = values();
        for (PartyPrivacyType partyPrivacyType : values) {
            if (partyPrivacyType.mValue.equals(str)) {
                return partyPrivacyType;
            }
        }
        Log.e(TAG, String.format("Unexpected party privacy type \"%s\".", str));
        return UNKNOWN;
    }
}
