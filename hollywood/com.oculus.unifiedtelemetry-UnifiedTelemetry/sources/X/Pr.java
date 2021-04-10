package X;

public final class Pr<T> {
    public byte A00;
    public C0475qE<T> A01;
    public Class A02;
    public eJ<? extends T> A03;
    public eJ<? extends T> A04;

    public final String toString() {
        String simpleName = getClass().getSimpleName();
        String canonicalName = this.A02.getCanonicalName();
        C0475qE<T> qEVar = this.A01;
        eJ<? extends T> eJVar = this.A04;
        boolean z = true;
        if ((this.A00 & 1) != 1) {
            z = false;
        }
        return String.format("%s{declaringModuleName = %s, key = %s, provider = %s, scope = %s, default = %s}", simpleName, canonicalName, qEVar, eJVar, null, Boolean.valueOf(z));
    }
}
