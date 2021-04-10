package X;

/* renamed from: X.0wN  reason: invalid class name and case insensitive filesystem */
public final class C08330wN {
    public final Object A00;
    public final String A01;
    public final AnonymousClass0wX A02;
    public final C08400wU A03;
    public final AbstractC08320wM A04;
    public volatile C08580wo A05;

    public final String toString() {
        StringBuilder sb = new StringBuilder("Request{method=");
        sb.append(this.A01);
        sb.append(", url=");
        sb.append(this.A03);
        sb.append(", tag=");
        Object obj = this.A00;
        if (obj == this) {
            obj = null;
        }
        sb.append(obj);
        sb.append('}');
        return sb.toString();
    }

    public C08330wN(C08340wO r3) {
        this.A03 = r3.A04;
        this.A01 = r3.A01;
        this.A02 = new AnonymousClass0wX(r3.A03);
        this.A04 = r3.A02;
        Object obj = r3.A00;
        this.A00 = obj != null ? obj : this;
    }
}
