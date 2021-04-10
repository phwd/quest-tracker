package com.oculus.horizoncontent.social;

import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.socialplatform.R;
import java.util.HashMap;
import java.util.Map;

public enum SocialPartyType {
    CLOSED(R.string.anytime_tablet_social_party_privacy_private_invite_only, R.drawable.oc_icon_lock_filled_24_d2d2d2),
    JOINABLE_BY_FRIENDS(R.string.anytime_tablet_social_party_privacy_private_directly_joinable, R.drawable.oc_icon_friends_filled_24_d2d2d2),
    OPEN(R.string.anytime_tablet_social_party_privacy_dogfooding_open, R.drawable.oc_icon_lock_off_filled_24_d2d2d2);
    
    public static final Map<SocialPartyType, Integer> PRIVACY_SETTING_ICON_MAP = new HashMap();
    public static final Map<SocialPartyType, Integer> PRIVACY_SETTING_LABEL_MAP = new HashMap();
    public static final String TAG = LoggingUtil.tag(SocialPartyType.class);
    public final int mIconId;
    public final int mStringId;

    /* renamed from: com.oculus.horizoncontent.social.SocialPartyType$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$horizoncontent$social$SocialPartyType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.oculus.horizoncontent.social.SocialPartyType[] r0 = com.oculus.horizoncontent.social.SocialPartyType.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.horizoncontent.social.SocialPartyType.AnonymousClass1.$SwitchMap$com$oculus$horizoncontent$social$SocialPartyType = r2
                com.oculus.horizoncontent.social.SocialPartyType r0 = com.oculus.horizoncontent.social.SocialPartyType.JOINABLE_BY_FRIENDS     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.horizoncontent.social.SocialPartyType r0 = com.oculus.horizoncontent.social.SocialPartyType.CLOSED     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizoncontent.social.SocialPartyType.AnonymousClass1.<clinit>():void");
        }
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

    public static SocialPartyType getDefault() {
        return CLOSED;
    }

    public static Map<SocialPartyType, Integer> getIconMap() {
        return PRIVACY_SETTING_ICON_MAP;
    }

    public static Map<SocialPartyType, Integer> getLabelMap() {
        return PRIVACY_SETTING_LABEL_MAP;
    }

    public int getIconId() {
        return this.mIconId;
    }

    public int getStringId() {
        return this.mStringId;
    }

    /* access modifiers changed from: public */
    static {
        SocialPartyType[] values = values();
        for (SocialPartyType socialPartyType : values) {
            PRIVACY_SETTING_LABEL_MAP.put(socialPartyType, Integer.valueOf(socialPartyType.getStringId()));
            PRIVACY_SETTING_ICON_MAP.put(socialPartyType, Integer.valueOf(socialPartyType.getIconId()));
        }
    }

    /* access modifiers changed from: public */
    SocialPartyType(int i, int i2) {
        this.mStringId = i;
        this.mIconId = i2;
    }

    public String toTypeForMutation() {
        switch (ordinal()) {
            case 0:
                return "CLOSED";
            case 1:
                return "JOINABLE_BY_FRIENDS";
            default:
                String str = TAG;
                StringBuilder sb = new StringBuilder("Unhandled join type provided for mutation: ");
                sb.append(this);
                Log.e(str, sb.toString());
                return null;
        }
    }
}
