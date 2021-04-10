package com.oculus.nux.ota;

import android.util.Log;
import java.util.Locale;

public enum LegacyNuxOtaState {
    NEW_DEVICE,
    APP_NUX_COMPLETE,
    DAY0_OTA_READY,
    DAY0_NO_OTA,
    WAITING_FOR_REBOOT,
    REBOOTING,
    WAITING_FOR_HIGH_PRI_APPS_DOWNLOAD,
    NOTIFY_ENDPOINT,
    NUX_COMPLETE;
    
    private static final String TAG = LegacyNuxOtaState.class.getSimpleName();

    public static LegacyNuxOtaState fromString(String str) {
        try {
            return valueOf(str.toUpperCase(Locale.ENGLISH));
        } catch (IllegalArgumentException unused) {
            String str2 = TAG;
            Log.e(str2, "Unable to convert " + str + " to LegacyNuxOtaState.");
            return NEW_DEVICE;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.nux.ota.LegacyNuxOtaState$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$nux$ota$LegacyNuxOtaState = new int[LegacyNuxOtaState.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(20:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|20) */
        /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x004b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0056 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0062 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
            // Method dump skipped, instructions count: 111
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.nux.ota.LegacyNuxOtaState.AnonymousClass1.<clinit>():void");
        }
    }

    public NuxOtaState toNuxOtaState() {
        switch (AnonymousClass1.$SwitchMap$com$oculus$nux$ota$LegacyNuxOtaState[ordinal()]) {
            case 1:
                return NuxOtaState.NEW_DEVICE;
            case 2:
                return NuxOtaState.OKAY_TO_REBOOT;
            case 3:
                return NuxOtaState.OTA_READY;
            case 4:
                return NuxOtaState.NO_OTA;
            case 5:
                return NuxOtaState.WAITING_FOR_REBOOT;
            case 6:
                return NuxOtaState.REBOOTING;
            case 7:
                return NuxOtaState.WAITING_FOR_HIGH_PRI_APPS_DOWNLOAD;
            case 8:
                return NuxOtaState.NOTIFY_ENDPOINT;
            case 9:
                return NuxOtaState.COMPLETE;
            default:
                String str = TAG;
                Log.e(str, "Unable to convert " + toString() + " to NuxOtaState.");
                return NuxOtaState.NEW_DEVICE;
        }
    }

    public static boolean isNuxCompleteInState(String str) {
        return fromString(str).isNuxCompleteInState();
    }

    public boolean isNuxCompleteInState() {
        int i = AnonymousClass1.$SwitchMap$com$oculus$nux$ota$LegacyNuxOtaState[ordinal()];
        return i == 2 || i == 9 || i == 5 || i == 6;
    }
}
