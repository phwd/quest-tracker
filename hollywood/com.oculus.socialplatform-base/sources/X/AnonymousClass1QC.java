package X;

import java.util.concurrent.atomic.AtomicLong;

/* renamed from: X.1QC  reason: invalid class name */
public enum AnonymousClass1QC implements AnonymousClass1Q4 {
    CountSuccessfulConnection("sc", AtomicLong.class),
    CountConnectAttempt("ca", AtomicLong.class),
    ConnectingMs("ce", AtomicLong.class),
    ConnectTriggerReason("tr", String.class),
    LastConnectFailureReason("fr", String.class),
    LastDisconnectReason("dr", String.class);
    
    public final String mJsonKey;
    public final Class<?> mType;

    @Override // X.AnonymousClass1Q4
    public String getKey() {
        return this.mJsonKey;
    }

    @Override // X.AnonymousClass1Q4
    public Class<?> getValueType() {
        return this.mType;
    }

    /* access modifiers changed from: public */
    AnonymousClass1QC(String str, Class cls) {
        this.mJsonKey = str;
        this.mType = cls;
    }
}
