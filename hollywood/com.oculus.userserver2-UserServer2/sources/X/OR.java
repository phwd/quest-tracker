package X;

public final class OR<T> {
    public byte A00;
    public gz<T> A01;
    public Class A02;
    public AbstractC0192Xx<? extends T> A03;
    public AbstractC0192Xx<? extends T> A04;

    public final String toString() {
        String simpleName = getClass().getSimpleName();
        String canonicalName = this.A02.getCanonicalName();
        gz<T> gzVar = this.A01;
        AbstractC0192Xx<? extends T> xx = this.A04;
        boolean z = true;
        if ((this.A00 & 1) != 1) {
            z = false;
        }
        return String.format("%s{declaringModuleName = %s, key = %s, provider = %s, scope = %s, default = %s}", simpleName, canonicalName, gzVar, xx, null, Boolean.valueOf(z));
    }
}
