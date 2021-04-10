package X;

import java.util.concurrent.atomic.AtomicLong;

/* renamed from: X.0nG  reason: invalid class name */
public enum AnonymousClass0nG implements AbstractC01720Wy {
    PublishAcknowledgementMs("pub", AtomicLong.class),
    StackSendingLatencyMs("s", AtomicLong.class),
    StackReceivingLatencyMs("r", AtomicLong.class);
    
    public final String mJsonKey;
    public final Class<?> mType;

    /* access modifiers changed from: public */
    AnonymousClass0nG(String str, Class cls) {
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
