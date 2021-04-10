package X;

import android.util.Log;

public enum Zd {
    UNKNOWN(-1),
    COMFORTABLE_FOR_ALL(0),
    COMFORTABLE_FOR_MOST(1),
    COMFORTABLE_FOR_SOME(2),
    COMFORTABLE_FOR_FEW(3),
    NOT_RATED(4);
    
    public static final String TAG = Zd.class.getCanonicalName();
    public final int value;

    /* access modifiers changed from: public */
    Zd(int i) {
        this.value = i;
    }

    public static Zd fromString(String str) {
        Zd[] values = values();
        for (Zd zd : values) {
            if (zd.name().equals(str)) {
                return zd;
            }
        }
        Log.e(TAG, AnonymousClass08.A04("Cannot parse comfort rating: ", str));
        return null;
    }
}
