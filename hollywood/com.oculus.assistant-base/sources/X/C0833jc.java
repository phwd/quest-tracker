package X;

/* renamed from: X.jc  reason: case insensitive filesystem */
public final class C0833jc implements AnonymousClass1g {
    public int A00;
    public final Object[] A01;
    public final Object A02 = new Object();

    @Override // X.AnonymousClass1g
    public final Object A18() {
        Object obj;
        synchronized (this.A02) {
            int i = this.A00;
            if (i > 0) {
                int i2 = i - 1;
                Object[] objArr = this.A01;
                obj = objArr[i2];
                objArr[i2] = null;
                this.A00 = i2;
            } else {
                obj = null;
            }
        }
        return obj;
    }

    public C0833jc(int i) {
        this.A01 = new Object[i];
    }
}
