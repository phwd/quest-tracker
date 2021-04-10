package com.oculus.horizoncontent.social;

import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;

public enum SocialGroupLaunchResponse {
    ACCEPT,
    REJECT,
    PENDING_RESPONSE;
    
    private static final String TAG = LoggingUtil.tag(SocialGroupLaunchResponse.class);

    public static SocialGroupLaunchResponse fromString(String str) {
        if (str == null) {
            return PENDING_RESPONSE;
        }
        if (str.equals("ACCEPT")) {
            return ACCEPT;
        }
        if (str.equals("REJECT")) {
            return REJECT;
        }
        return PENDING_RESPONSE;
    }

    /* renamed from: com.oculus.horizoncontent.social.SocialGroupLaunchResponse$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$horizoncontent$social$SocialGroupLaunchResponse = new int[SocialGroupLaunchResponse.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            /*
                com.oculus.horizoncontent.social.SocialGroupLaunchResponse[] r0 = com.oculus.horizoncontent.social.SocialGroupLaunchResponse.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.horizoncontent.social.SocialGroupLaunchResponse.AnonymousClass1.$SwitchMap$com$oculus$horizoncontent$social$SocialGroupLaunchResponse = r0
                int[] r0 = com.oculus.horizoncontent.social.SocialGroupLaunchResponse.AnonymousClass1.$SwitchMap$com$oculus$horizoncontent$social$SocialGroupLaunchResponse     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.horizoncontent.social.SocialGroupLaunchResponse r1 = com.oculus.horizoncontent.social.SocialGroupLaunchResponse.ACCEPT     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.horizoncontent.social.SocialGroupLaunchResponse.AnonymousClass1.$SwitchMap$com$oculus$horizoncontent$social$SocialGroupLaunchResponse     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.horizoncontent.social.SocialGroupLaunchResponse r1 = com.oculus.horizoncontent.social.SocialGroupLaunchResponse.REJECT     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = com.oculus.horizoncontent.social.SocialGroupLaunchResponse.AnonymousClass1.$SwitchMap$com$oculus$horizoncontent$social$SocialGroupLaunchResponse     // Catch:{ NoSuchFieldError -> 0x002a }
                com.oculus.horizoncontent.social.SocialGroupLaunchResponse r1 = com.oculus.horizoncontent.social.SocialGroupLaunchResponse.PENDING_RESPONSE     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizoncontent.social.SocialGroupLaunchResponse.AnonymousClass1.<clinit>():void");
        }
    }

    public String toTypeForMutation() {
        int i = AnonymousClass1.$SwitchMap$com$oculus$horizoncontent$social$SocialGroupLaunchResponse[ordinal()];
        if (i == 1) {
            return "ACCEPT";
        }
        if (i == 2) {
            return "REJECT";
        }
        if (i == 3) {
            return "PENDING_RESPONSE";
        }
        String str = TAG;
        Log.e(str, "Unhandled response provided for mutation: " + this);
        return null;
    }

    public static String getUserFacingString(SocialGroupLaunchResponse socialGroupLaunchResponse) {
        return socialGroupLaunchResponse.equals(ACCEPT) ? "Ready" : "Not Ready";
    }
}
