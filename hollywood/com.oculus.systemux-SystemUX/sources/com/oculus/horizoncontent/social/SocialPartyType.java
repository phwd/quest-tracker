package com.oculus.horizoncontent.social;

import android.util.Log;
import com.oculus.common.horizoncontent.R;
import com.oculus.common.logutilities.LoggingUtil;
import java.util.HashMap;
import java.util.Map;

public enum SocialPartyType {
    CLOSED(R.string.anytime_tablet_social_party_privacy_private_invite_only, R.drawable.oc_icon_lock_filled_24_d2d2d2),
    JOINABLE_BY_FRIENDS(R.string.anytime_tablet_social_party_privacy_private_directly_joinable, R.drawable.oc_icon_friends_filled_24_d2d2d2),
    OPEN(R.string.anytime_tablet_social_party_privacy_dogfooding_open, R.drawable.oc_icon_lock_off_filled_24_d2d2d2);
    
    private static final Map<SocialPartyType, Integer> PRIVACY_SETTING_ICON_MAP = new HashMap();
    private static final Map<SocialPartyType, Integer> PRIVACY_SETTING_LABEL_MAP = new HashMap();
    private static final String TAG = LoggingUtil.tag(SocialPartyType.class);
    private final int mIconId;
    private final int mStringId;

    static {
        SocialPartyType[] values = values();
        for (SocialPartyType socialPartyType : values) {
            PRIVACY_SETTING_LABEL_MAP.put(socialPartyType, Integer.valueOf(socialPartyType.getStringId()));
            PRIVACY_SETTING_ICON_MAP.put(socialPartyType, Integer.valueOf(socialPartyType.getIconId()));
        }
    }

    private SocialPartyType(int i, int i2) {
        this.mStringId = i;
        this.mIconId = i2;
    }

    public static SocialPartyType fromPartyType(String str) {
        if (str.equals("JOINABLE_BY_FRIENDS")) {
            return JOINABLE_BY_FRIENDS;
        }
        if (str.equals("OPEN")) {
            return OPEN;
        }
        return CLOSED;
    }

    /* renamed from: com.oculus.horizoncontent.social.SocialPartyType$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$horizoncontent$social$SocialPartyType = new int[SocialPartyType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            /*
                com.oculus.horizoncontent.social.SocialPartyType[] r0 = com.oculus.horizoncontent.social.SocialPartyType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.horizoncontent.social.SocialPartyType.AnonymousClass1.$SwitchMap$com$oculus$horizoncontent$social$SocialPartyType = r0
                int[] r0 = com.oculus.horizoncontent.social.SocialPartyType.AnonymousClass1.$SwitchMap$com$oculus$horizoncontent$social$SocialPartyType     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.horizoncontent.social.SocialPartyType r1 = com.oculus.horizoncontent.social.SocialPartyType.JOINABLE_BY_FRIENDS     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.horizoncontent.social.SocialPartyType.AnonymousClass1.$SwitchMap$com$oculus$horizoncontent$social$SocialPartyType     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.horizoncontent.social.SocialPartyType r1 = com.oculus.horizoncontent.social.SocialPartyType.CLOSED     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizoncontent.social.SocialPartyType.AnonymousClass1.<clinit>():void");
        }
    }

    public String toTypeForMutation() {
        int i = AnonymousClass1.$SwitchMap$com$oculus$horizoncontent$social$SocialPartyType[ordinal()];
        if (i == 1) {
            return "JOINABLE_BY_FRIENDS";
        }
        if (i == 2) {
            return "CLOSED";
        }
        String str = TAG;
        Log.e(str, "Unhandled join type provided for mutation: " + this);
        return null;
    }

    public int getStringId() {
        return this.mStringId;
    }

    public int getIconId() {
        return this.mIconId;
    }

    public static Map<SocialPartyType, Integer> getLabelMap() {
        return PRIVACY_SETTING_LABEL_MAP;
    }

    public static Map<SocialPartyType, Integer> getIconMap() {
        return PRIVACY_SETTING_ICON_MAP;
    }

    public static SocialPartyType getDefault() {
        return CLOSED;
    }
}
