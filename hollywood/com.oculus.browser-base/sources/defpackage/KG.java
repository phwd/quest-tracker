package defpackage;

/* renamed from: KG  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class KG implements AbstractC2675gI0, M70 {

    /* renamed from: a  reason: collision with root package name */
    public static final Object f8355a = new Object();
    public volatile AbstractC2675gI0 b;
    public volatile Object c = f8355a;

    public KG(AbstractC2675gI0 gi0) {
        this.b = gi0;
    }

    public static M70 a(AbstractC2675gI0 gi0) {
        if (gi0 instanceof M70) {
            return (M70) gi0;
        }
        return new KG(gi0);
    }

    public static Object b(Object obj, Object obj2) {
        if (!(obj != f8355a && !(obj instanceof C2566fi0)) || obj == obj2) {
            return obj2;
        }
        throw new IllegalStateException("Scoped provider was invoked recursively returning different results: " + obj + " & " + obj2 + ". This is likely due to a circular dependency.");
    }

    @Override // defpackage.M70, defpackage.AbstractC2675gI0
    public Object get() {
        Object obj = this.c;
        Object obj2 = f8355a;
        if (obj == obj2) {
            synchronized (this) {
                obj = this.c;
                if (obj == obj2) {
                    obj = this.b.get();
                    b(this.c, obj);
                    this.c = obj;
                    this.b = null;
                }
            }
        }
        return obj;
    }
}
