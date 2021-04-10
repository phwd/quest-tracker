package X;

/* renamed from: X.0y5  reason: invalid class name and case insensitive filesystem */
public enum EnumC08920y5 {
    ACKNOWLEDGED_DELIVERY(0),
    PROCESSING_LASTACTIVE_PRESENCEINFO(1),
    EXACT_KEEPALIVE(2),
    REQUIRES_JSON_UNICODE_ESCAPES(3),
    DELTA_SENT_MESSAGE_ENABLED(4),
    USE_ENUM_TOPIC(5),
    SUPPRESS_GETDIFF_IN_CONNECT(6),
    USE_THRIFT_FOR_INBOX(7),
    USE_SEND_PINGRESP(8),
    REQUIRE_REPLAY_PROTECTION(9),
    DATA_SAVING_MODE(10),
    TYPING_OFF_WHEN_SENDING_MESSAGE(11),
    PERMISSION_USER_AUTH_CODE(12),
    FBNS_EXPLICIT_DELIVERY_ACK(13),
    IS_LARGE_PAYLOAD_SUPPORTED(14),
    IS_MQTT_TOPIC_EXTENSION_SUPPORTED(15);
    
    public final byte mPosition;

    public long getMask() {
        return (long) (1 << this.mPosition);
    }

    /* access modifiers changed from: public */
    EnumC08920y5(int i) {
        boolean z = true;
        C08170wh.A00(i >= 0);
        C08170wh.A00(i >= 64 ? false : z);
        this.mPosition = (byte) i;
    }
}
