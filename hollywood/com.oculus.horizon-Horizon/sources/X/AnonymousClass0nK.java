package X;

import java.util.concurrent.atomic.AtomicLong;

/* renamed from: X.0nK  reason: invalid class name */
public enum AnonymousClass0nK implements AbstractC01720Wy {
    CountSuccessfulConnection("sc", AtomicLong.class),
    CountConnectAttempt("ca", AtomicLong.class),
    ConnectingMs("ce", AtomicLong.class),
    ConnectTriggerReason("tr", String.class),
    LastConnectFailureReason("fr", String.class),
    LastDisconnectReason("dr", String.class);
    
    public final String mJsonKey;
    public final Class<?> mType;

    /* access modifiers changed from: public */
    AnonymousClass0nK(String str, Class cls) {
        this.mJsonKey = str;
        this.mType = cls;
    }

    @Override // X.AbstractC01720Wy
    public String getKey() {
        return this.mJsonKey;
    }

    @Override // X.AbstractC01720Wy
    public Class<?> getValueType() {
        return this.mType;
    }
}
