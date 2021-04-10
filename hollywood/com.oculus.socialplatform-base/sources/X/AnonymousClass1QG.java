package X;

/* renamed from: X.1QG  reason: invalid class name */
public enum AnonymousClass1QG implements AnonymousClass1Q4 {
    ServiceName("sn", String.class),
    ClientCoreName("cn", String.class),
    NotificationStoreName("nsn", String.class),
    Country("ct", String.class),
    NetworkType("nt", String.class),
    NetworkSubtype("ns", String.class),
    ConnectionQuality("cq", String.class),
    AppState("as", String.class),
    ScreenState("ss", String.class),
    YearClass("yc", String.class),
    MqttGKs("gk", String.class),
    MqttQEs("qe", String.class),
    MqttFlags("f", String.class),
    IsEmployee("e", String.class),
    ValidCompatibleApps("va", String.class),
    EnabledCompatibleApps("ea", String.class),
    RegisteredApps("ra", String.class);
    
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
    AnonymousClass1QG(String str, Class cls) {
        this.mJsonKey = str;
        this.mType = cls;
    }
}
