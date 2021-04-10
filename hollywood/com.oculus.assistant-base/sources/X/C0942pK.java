package X;

/* renamed from: X.pK  reason: case insensitive filesystem */
public final class C0942pK implements JC {
    public JC A00;

    @Override // X.JC
    public final void A4o(String str, String str2, Throwable th) {
        JC jc;
        synchronized (this) {
            jc = this.A00;
        }
        jc.A4o(str, str2, th);
    }

    public C0942pK() {
        C0945pN pNVar = new C0945pN();
        synchronized (this) {
            this.A00 = pNVar;
        }
    }
}
