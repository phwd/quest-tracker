package defpackage;

import java.util.Map;

/* renamed from: rO0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4568rO0 implements Map.Entry {
    public final Object F;
    public final Object G;
    public C4568rO0 H;
    public C4568rO0 I;

    public C4568rO0(Object obj, Object obj2) {
        this.F = obj;
        this.G = obj2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C4568rO0)) {
            return false;
        }
        C4568rO0 ro0 = (C4568rO0) obj;
        return this.F.equals(ro0.F) && this.G.equals(ro0.G);
    }

    @Override // java.util.Map.Entry
    public Object getKey() {
        return this.F;
    }

    @Override // java.util.Map.Entry
    public Object getValue() {
        return this.G;
    }

    public int hashCode() {
        return this.F.hashCode() ^ this.G.hashCode();
    }

    @Override // java.util.Map.Entry
    public Object setValue(Object obj) {
        throw new UnsupportedOperationException("An entry modification is not supported");
    }

    public String toString() {
        return this.F + "=" + this.G;
    }
}
