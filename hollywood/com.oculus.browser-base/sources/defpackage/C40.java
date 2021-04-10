package defpackage;

/* JADX WARN: Init of enum G can be incorrect */
/* JADX WARN: Init of enum N can be incorrect */
/* renamed from: C40  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public enum C40 {
    VOID(Void.class, Void.class, null),
    INT(r1, Integer.class, 0),
    LONG(Long.TYPE, Long.class, 0L),
    FLOAT(Float.TYPE, Float.class, Float.valueOf(0.0f)),
    DOUBLE(Double.TYPE, Double.class, Double.valueOf(0.0d)),
    BOOLEAN(Boolean.TYPE, Boolean.class, Boolean.FALSE),
    STRING(String.class, String.class, ""),
    BYTE_STRING(AbstractC1248Uk.class, AbstractC1248Uk.class, AbstractC1248Uk.F),
    ENUM(r1, Integer.class, null),
    MESSAGE(Object.class, Object.class, null);
    
    public final Class Q;
    public final Object R;

    /* access modifiers changed from: public */
    static {
        Class cls = Integer.TYPE;
    }

    /* access modifiers changed from: public */
    C40(Class cls, Class cls2, Object obj) {
        this.Q = cls2;
        this.R = obj;
    }
}
