package X;

import java.util.concurrent.atomic.AtomicLong;

/* renamed from: X.0y6  reason: invalid class name */
public enum AnonymousClass0y6 implements AbstractC09350zH {
    PublishAcknowledgementMs("pub", AtomicLong.class),
    StackSendingLatencyMs("s", AtomicLong.class),
    StackReceivingLatencyMs("r", AtomicLong.class);
    
    public final String mJsonKey;
    public final Class<?> mType;

    /* access modifiers changed from: public */
    AnonymousClass0y6(String str, Class cls) {
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
