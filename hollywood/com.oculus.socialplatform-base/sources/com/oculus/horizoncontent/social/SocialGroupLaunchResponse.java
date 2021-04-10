package com.oculus.horizoncontent.social;

import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;

public enum SocialGroupLaunchResponse {
    ACCEPT,
    REJECT,
    PENDING_RESPONSE;
    
    public static final String TAG = LoggingUtil.tag(SocialGroupLaunchResponse.class);

    /* renamed from: com.oculus.horizoncontent.social.SocialGroupLaunchResponse$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$horizoncontent$social$SocialGroupLaunchResponse;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001b */
        static {
            /*
                com.oculus.horizoncontent.social.SocialGroupLaunchResponse[] r0 = com.oculus.horizoncontent.social.SocialGroupLaunchResponse.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.horizoncontent.social.SocialGroupLaunchResponse.AnonymousClass1.$SwitchMap$com$oculus$horizoncontent$social$SocialGroupLaunchResponse = r2
                com.oculus.horizoncontent.social.SocialGroupLaunchResponse r0 = com.oculus.horizoncontent.social.SocialGroupLaunchResponse.ACCEPT     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.horizoncontent.social.SocialGroupLaunchResponse r0 = com.oculus.horizoncontent.social.SocialGroupLaunchResponse.REJECT     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                com.oculus.horizoncontent.social.SocialGroupLaunchResponse r0 = com.oculus.horizoncontent.social.SocialGroupLaunchResponse.PENDING_RESPONSE     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r0 = 3
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizoncontent.social.SocialGroupLaunchResponse.AnonymousClass1.<clinit>():void");
        }
    }

    public static SocialGroupLaunchResponse fromString(String str) {
        if (str != null) {
            if (str.equals("ACCEPT")) {
                return ACCEPT;
            }
            if (str.equals("REJECT")) {
                return REJECT;
            }
        }
        return PENDING_RESPONSE;
    }

    public static String getUserFacingString(SocialGroupLaunchResponse socialGroupLaunchResponse) {
        if (socialGroupLaunchResponse.equals(ACCEPT)) {
            return "Ready";
        }
        return "Not Ready";
    }

    public String toTypeForMutation() {
        switch (ordinal()) {
            case 0:
                return "ACCEPT";
            case 1:
                return "REJECT";
            case 2:
                return "PENDING_RESPONSE";
            default:
                String str = TAG;
                StringBuilder sb = new StringBuilder("Unhandled response provided for mutation: ");
                sb.append(this);
                Log.e(str, sb.toString());
                return null;
        }
    }
}
