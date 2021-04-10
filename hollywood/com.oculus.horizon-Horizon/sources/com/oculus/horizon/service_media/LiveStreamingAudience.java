package com.oculus.horizon.service_media;

public enum LiveStreamingAudience {
    PUBLIC(1),
    FRIENDS(2),
    ONLY_ME(3);
    
    public static final String INTENT_KEY = "audience_selector";
    public final int value;

    /* renamed from: com.oculus.horizon.service_media.LiveStreamingAudience$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$horizon$service_media$LiveStreamingAudience;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001b */
        static {
            /*
                com.oculus.horizon.service_media.LiveStreamingAudience[] r0 = com.oculus.horizon.service_media.LiveStreamingAudience.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.horizon.service_media.LiveStreamingAudience.AnonymousClass1.$SwitchMap$com$oculus$horizon$service_media$LiveStreamingAudience = r2
                com.oculus.horizon.service_media.LiveStreamingAudience r0 = com.oculus.horizon.service_media.LiveStreamingAudience.PUBLIC     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.horizon.service_media.LiveStreamingAudience r0 = com.oculus.horizon.service_media.LiveStreamingAudience.FRIENDS     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                com.oculus.horizon.service_media.LiveStreamingAudience r0 = com.oculus.horizon.service_media.LiveStreamingAudience.ONLY_ME     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r0 = 3
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.service_media.LiveStreamingAudience.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: public */
    LiveStreamingAudience(int i) {
        this.value = i;
    }

    public static LiveStreamingAudience fromValue(int i) {
        LiveStreamingAudience[] values = values();
        for (LiveStreamingAudience liveStreamingAudience : values) {
            if (liveStreamingAudience.getValue() == i) {
                return liveStreamingAudience;
            }
        }
        throw new IllegalArgumentException("");
    }

    public int getValue() {
        return this.value;
    }

    public String toGraphAPIString() {
        switch (ordinal()) {
            case 0:
                return "EVERYONE";
            case 1:
                return "ALL_FRIENDS";
            case 2:
                return "SELF";
            default:
                throw new IllegalArgumentException();
        }
    }
}
