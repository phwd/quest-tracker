package defpackage;

/* renamed from: RO0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public enum RO0 implements C30 {
    SINGLE(0),
    BOOLEAN_ARRAY(1),
    DOUBLE_ARRAY(2),
    INT_ARRAY(3),
    LONG_ARRAY(4),
    STRING_ARRAY(5),
    NULL(6),
    UNRECOGNIZED(-1);
    
    public final int O;

    /* access modifiers changed from: public */
    RO0(int i) {
        this.O = i;
    }

    public final int a() {
        if (this != UNRECOGNIZED) {
            return this.O;
        }
        throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
    }
}
