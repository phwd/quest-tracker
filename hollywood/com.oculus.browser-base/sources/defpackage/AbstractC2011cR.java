package defpackage;

/* renamed from: cR  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC2011cR {

    /* renamed from: a  reason: collision with root package name */
    public int f9606a;
    public boolean b = false;

    public AbstractC2011cR(int i) {
        this.f9606a = i;
    }

    public AbstractC2011cR a(int i, boolean z) {
        if (!this.b) {
            if (z) {
                this.f9606a = i | this.f9606a;
            } else {
                this.f9606a = (~i) & this.f9606a;
            }
            return this;
        }
        throw new UnsupportedOperationException("Flags is immutable.");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.f9606a == ((AbstractC2011cR) obj).f9606a;
    }

    public int hashCode() {
        return this.f9606a;
    }
}
