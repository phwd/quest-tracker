package X;

import java.util.concurrent.atomic.AtomicLong;

/* renamed from: X.0y2  reason: invalid class name */
public enum AnonymousClass0y2 implements AbstractC09350zH {
    CountSuccessfulConnection("sc", AtomicLong.class),
    CountConnectAttempt("ca", AtomicLong.class),
    ConnectingMs("ce", AtomicLong.class),
    ConnectTriggerReason("tr", String.class),
    LastConnectFailureReason("fr", String.class),
    LastDisconnectReason("dr", String.class);
    
    public final String mJsonKey;
    public final Class<?> mType;

    /* access modifiers changed from: public */
    AnonymousClass0y2(String str, Class cls) {
        this.mJsonKey = str;
        this.mType = cls;
    }

    @Override // X.AbstractC09350zH
    public String getKey() {
        return this.mJsonKey;
    }

    @Override // X.AbstractC09350zH
    public Class<?> getValueType() {
        return this.mType;
    }
}
