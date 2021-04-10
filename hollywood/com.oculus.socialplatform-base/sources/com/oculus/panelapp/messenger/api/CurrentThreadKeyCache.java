package com.oculus.panelapp.messenger.api;

import android.content.Context;
import android.util.Log;
import com.oculus.vrshell.sharedprefs.PrefKey;
import com.oculus.vrshell.sharedprefs.SharedKeys;
import com.oculus.vrshell.sharedprefs.SharedPreferencesHelper;

public class CurrentThreadKeyCache {
    public static final PrefKey FB_MESSENGER_CACHED_THREAD;
    public static final PrefKey OC_CHATS_CACHED_THREAD;
    public static final String TAG = "CurrentThreadKeyCache";
    public static final PrefKey TEST_CACHED_THREAD;

    /* renamed from: com.oculus.panelapp.messenger.api.CurrentThreadKeyCache$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$messenger$api$MessengerAPIType;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001b */
        static {
            /*
                com.oculus.panelapp.messenger.api.MessengerAPIType[] r0 = com.oculus.panelapp.messenger.api.MessengerAPIType.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.panelapp.messenger.api.CurrentThreadKeyCache.AnonymousClass1.$SwitchMap$com$oculus$panelapp$messenger$api$MessengerAPIType = r2
                com.oculus.panelapp.messenger.api.MessengerAPIType r0 = com.oculus.panelapp.messenger.api.MessengerAPIType.TEST     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.panelapp.messenger.api.MessengerAPIType r0 = com.oculus.panelapp.messenger.api.MessengerAPIType.FB_MSYS     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                com.oculus.panelapp.messenger.api.MessengerAPIType r0 = com.oculus.panelapp.messenger.api.MessengerAPIType.OC_CHATS     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r0 = 3
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.messenger.api.CurrentThreadKeyCache.AnonymousClass1.<clinit>():void");
        }
    }

    static {
        PrefKey prefKey = SharedKeys.FLAGS;
        OC_CHATS_CACHED_THREAD = new PrefKey(prefKey, "OC_CHATS_CACHED_THREAD_KEY");
        FB_MESSENGER_CACHED_THREAD = new PrefKey(prefKey, "FB_MESSENGER_CACHED_THREAD_KEY");
        TEST_CACHED_THREAD = new PrefKey(prefKey, "TEST_CACHED_THREAD");
    }

    public static long get(Context context, MessengerAPIType messengerAPIType) {
        return SharedPreferencesHelper.getLong(context, getPrefKeyFromAPI(messengerAPIType), -1);
    }

    public static PrefKey getPrefKeyFromAPI(MessengerAPIType messengerAPIType) {
        switch (messengerAPIType.ordinal()) {
            case 0:
                return TEST_CACHED_THREAD;
            case 1:
                return FB_MESSENGER_CACHED_THREAD;
            case 2:
                return OC_CHATS_CACHED_THREAD;
            default:
                StringBuilder sb = new StringBuilder("Unhandled api type provided: ");
                sb.append(messengerAPIType);
                Log.e(TAG, sb.toString());
                return null;
        }
    }

    public static void put(Context context, MessengerAPIType messengerAPIType, long j) {
        SharedPreferencesHelper.putLong(context, getPrefKeyFromAPI(messengerAPIType), j);
    }
}
