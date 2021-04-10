package X;

/* renamed from: X.6y  reason: invalid class name and case insensitive filesystem */
public enum EnumC00486y {
    CLIENT_EVENT("client_event", "extra"),
    EXPERIMENT("experiment", "result");
    
    public final String mExtraJsonKey;
    public final String mProtocolValue;

    /* access modifiers changed from: public */
    EnumC00486y(String str, String str2) {
        this.mProtocolValue = str;
        this.mExtraJsonKey = str2;
    }

    public String getExtraJsonKey() {
        return this.mExtraJsonKey;
    }

    public String getProtocolValue() {
        return this.mProtocolValue;
    }

    public String toString() {
        return this.mProtocolValue;
    }
}
