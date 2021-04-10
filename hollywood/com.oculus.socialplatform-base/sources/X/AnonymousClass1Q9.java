package X;

import java.util.concurrent.atomic.AtomicLong;

/* renamed from: X.1Q9  reason: invalid class name */
public enum AnonymousClass1Q9 implements AnonymousClass1Q4 {
    PublishAcknowledgementMs("pub", AtomicLong.class),
    StackSendingLatencyMs("s", AtomicLong.class),
    StackReceivingLatencyMs("r", AtomicLong.class);
    
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
    AnonymousClass1Q9(String str, Class cls) {
        this.mJsonKey = str;
        this.mType = cls;
    }
}
