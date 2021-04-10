package X;

/* renamed from: X.1Ds  reason: invalid class name */
public final class AnonymousClass1Ds {
    public static final AnonymousClass1Dq A02 = new AnonymousClass1Dq(0, "", -1);
    public final int A00;
    public final AnonymousClass1Dq A01;

    public AnonymousClass1Ds(int i, AnonymousClass1Dq r5) {
        AnonymousClass1Dq r0;
        Integer num;
        this.A00 = i;
        if (i != 2779001) {
            if (i == 2779002) {
                num = AnonymousClass007.A01;
            } else if (i != 2779003) {
                num = null;
                if (!(i == 2779004 || i == 2779005)) {
                    throw new IllegalStateException("Unrecognized error subcode.");
                }
            }
            r0 = new AnonymousClass1Dq(A02, num);
            this.A01 = r0;
        }
        r0 = new AnonymousClass1Dq(r5 == null ? A02 : r5, AnonymousClass007.A00);
        this.A01 = r0;
    }
}
