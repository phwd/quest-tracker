package X;

import java.util.HashMap;

public final class C2 extends W7 {
    public final WZ<?> A00;
    public final HashMap<String, AbstractC0224Wl> A01;

    /* JADX WARN: Incorrect args count in method signature: (LX/WZ<*>;LX/Wl;Ljava/util/HashMap<Ljava/lang/String;Ljava/lang/String;>;Ljava/util/HashMap<Ljava/lang/String;LX/Wl;>;)V */
    public C2(WZ wz, AbstractC0224Wl wl, HashMap hashMap) {
        super(wl, wz._base._typeFactory);
        this.A00 = wz;
        this.A01 = hashMap;
    }

    @Override // X.V3
    public final String A32(Object obj, Class<?> cls) {
        if (obj == null) {
            return null;
        }
        throw null;
    }

    @Override // X.V3
    public final AbstractC0224Wl A5W(String str) throws IllegalArgumentException {
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
