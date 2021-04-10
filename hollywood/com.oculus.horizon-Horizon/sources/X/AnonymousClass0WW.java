package X;

import com.facebook.internal.AnalyticsEvents;

/* renamed from: X.0WW  reason: invalid class name */
public enum AnonymousClass0WW {
    UNKNOWN(AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN),
    NoNetwork("NoNetwork"),
    WIFI("Wifi"),
    MOBILE_2G("Cell_2G"),
    MOBILE_3G("Cell_3G"),
    MOBILE_4G("Cell_4G"),
    MOBILE_OTHER("Cell_other"),
    Other("Other");
    
    public final String name;

    /* access modifiers changed from: public */
    AnonymousClass0WW(String str) {
        this.name = str;
    }

    public String toString() {
        return this.name;
    }
}
