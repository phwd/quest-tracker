package defpackage;

import android.os.Bundle;

/* renamed from: Rf0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1052Rf0 {

    /* renamed from: a  reason: collision with root package name */
    public final Bundle f8844a;
    public C0629Kg0 b;

    public C1052Rf0(C0629Kg0 kg0, boolean z) {
        if (kg0 != null) {
            Bundle bundle = new Bundle();
            this.f8844a = bundle;
            this.b = kg0;
            bundle.putBundle("selector", kg0.b);
            bundle.putBoolean("activeScan", z);
            return;
        }
        throw new IllegalArgumentException("selector must not be null");
    }

    public final void a() {
        if (this.b == null) {
            C0629Kg0 b2 = C0629Kg0.b(this.f8844a.getBundle("selector"));
            this.b = b2;
            if (b2 == null) {
                this.b = C0629Kg0.f8380a;
            }
        }
    }

    public boolean b() {
        return this.f8844a.getBoolean("activeScan");
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof C1052Rf0)) {
            return false;
        }
        C1052Rf0 rf0 = (C1052Rf0) obj;
        a();
        C0629Kg0 kg0 = this.b;
        rf0.a();
        if (!kg0.equals(rf0.b) || b() != rf0.b()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        a();
        return this.b.hashCode() ^ b();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DiscoveryRequest{ selector=");
        a();
        sb.append(this.b);
        sb.append(", activeScan=");
        sb.append(b());
        sb.append(", isValid=");
        a();
        C0629Kg0 kg0 = this.b;
        kg0.a();
        sb.append(!kg0.c.contains(null));
        sb.append(" }");
        return sb.toString();
    }
}
