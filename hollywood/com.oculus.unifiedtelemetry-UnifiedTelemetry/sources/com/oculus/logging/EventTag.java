package com.oculus.logging;

public class EventTag {
    public static final String EVENT_TAG = "utl_eventTag";

    public enum TagType {
        USL_ENABLED,
        LOGGED_THROUGH_REACT_NATIVE,
        IS_NT_EVENTS,
        LOGGED_THROUGH_XPLAT,
        HAS_DOWNLOADED_SAMPLING_POLICY,
        EVENT_IN_SAMPLING_CONFIG,
        LOCAL_PROFILING_EVENT
    }

    /* renamed from: com.oculus.logging.EventTag$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$logging$EventTag$TagType;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0036 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0024 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002d */
        static {
            /*
                com.oculus.logging.EventTag$TagType[] r0 = com.oculus.logging.EventTag.TagType.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.logging.EventTag.AnonymousClass1.$SwitchMap$com$oculus$logging$EventTag$TagType = r2
                com.oculus.logging.EventTag$TagType r0 = com.oculus.logging.EventTag.TagType.USL_ENABLED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.logging.EventTag$TagType r0 = com.oculus.logging.EventTag.TagType.LOGGED_THROUGH_REACT_NATIVE     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                com.oculus.logging.EventTag$TagType r0 = com.oculus.logging.EventTag.TagType.IS_NT_EVENTS     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r0 = 3
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                com.oculus.logging.EventTag$TagType r0 = com.oculus.logging.EventTag.TagType.LOGGED_THROUGH_XPLAT     // Catch:{ NoSuchFieldError -> 0x002d }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x002d }
                r0 = 4
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x002d }
            L_0x002d:
                com.oculus.logging.EventTag$TagType r0 = com.oculus.logging.EventTag.TagType.HAS_DOWNLOADED_SAMPLING_POLICY     // Catch:{ NoSuchFieldError -> 0x0036 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0036 }
                r0 = 5
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0036 }
            L_0x0036:
                com.oculus.logging.EventTag$TagType r0 = com.oculus.logging.EventTag.TagType.EVENT_IN_SAMPLING_CONFIG     // Catch:{ NoSuchFieldError -> 0x003f }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x003f }
                r0 = 6
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x003f }
            L_0x003f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.logging.EventTag.AnonymousClass1.<clinit>():void");
        }
    }

    public static void A00(OculusLoggingEvent oculusLoggingEvent, int i) {
        switch (TagType.values()[i].ordinal()) {
            case 0:
                oculusLoggingEvent.A5A();
                return;
            case 1:
                oculusLoggingEvent.A55();
                return;
            case 2:
                oculusLoggingEvent.A53();
                return;
            case 3:
                oculusLoggingEvent.A5C();
                return;
            case 4:
                oculusLoggingEvent.A50();
                return;
            case 5:
                oculusLoggingEvent.A4z();
                return;
            default:
                return;
        }
    }
}
