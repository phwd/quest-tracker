package X;

import java.util.HashMap;

/* renamed from: X.0GK  reason: invalid class name */
public final class AnonymousClass0GK extends AbstractC03710fw {
    public final AbstractC03910gQ<?> A00;
    public final HashMap<String, AbstractC04000gb> A01;

    /* JADX WARN: Incorrect args count in method signature: (LX/0gQ<*>;LX/0gb;Ljava/util/HashMap<Ljava/lang/String;Ljava/lang/String;>;Ljava/util/HashMap<Ljava/lang/String;LX/0gb;>;)V */
    public AnonymousClass0GK(AbstractC03910gQ r2, AbstractC04000gb r3, HashMap hashMap) {
        super(r3, r2._base._typeFactory);
        this.A00 = r2;
        this.A01 = hashMap;
    }

    @Override // X.AbstractC05940mA
    public final String A4l(Object obj, Class<?> cls) {
        if (obj == null) {
            return null;
        }
        throw null;
    }

    @Override // X.AbstractC05940mA
    public final AbstractC04000gb A9e(String str) throws IllegalArgumentException {
        return this.A01.get(str);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("[");
        sb.append(getClass().getName());
        sb.append("; id-to-type=");
        sb.append(this.A01);
        sb.append(']');
        return sb.toString();
    }
}
