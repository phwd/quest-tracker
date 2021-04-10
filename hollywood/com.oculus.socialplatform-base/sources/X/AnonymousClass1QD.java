package X;

import java.util.concurrent.atomic.AtomicLong;

/* renamed from: X.1QD  reason: invalid class name */
public enum AnonymousClass1QD implements AnonymousClass1Q4 {
    MqttDurationMs("m", AtomicLong.class),
    MqttTotalDurationMs("mt", AtomicLong.class),
    NetworkDurationMs("n", AtomicLong.class),
    NetworkTotalDurationMs("nt", AtomicLong.class),
    ServiceDurationMs("s", AtomicLong.class),
    MessageSendAttempt("sa", AtomicLong.class),
    MessageSendSuccess("ss", AtomicLong.class),
    ForegroundPing("fp", AtomicLong.class),
    BackgroundPing("bp", AtomicLong.class),
    PublishReceived("pr", AtomicLong.class),
    FbnsNotificationReceived("fnr", AtomicLong.class),
    FbnsLiteNotificationReceived("flnr", AtomicLong.class),
    FbnsNotificationDeliveryRetried("fdr", AtomicLong.class),
    FbnsLiteNotificationDeliveryRetried("fldr", AtomicLong.class);
    
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
    AnonymousClass1QD(String str, Class cls) {
        this.mJsonKey = str;
        this.mType = cls;
    }
}
