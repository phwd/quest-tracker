package X;

/* renamed from: X.0xP  reason: invalid class name and case insensitive filesystem */
public enum EnumC08540xP implements AbstractC09350zH {
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

    /* access modifiers changed from: public */
    EnumC08540xP(String str, Class cls) {
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
