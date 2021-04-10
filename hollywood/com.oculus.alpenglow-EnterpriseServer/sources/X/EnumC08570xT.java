package X;

import java.util.concurrent.atomic.AtomicLong;

/* renamed from: X.0xT  reason: invalid class name and case insensitive filesystem */
public enum EnumC08570xT implements AbstractC09350zH {
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

    /* access modifiers changed from: public */
    EnumC08570xT(String str, Class cls) {
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
