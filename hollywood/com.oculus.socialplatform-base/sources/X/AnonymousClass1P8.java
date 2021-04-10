package X;

import java.util.Comparator;

/* renamed from: X.1P8  reason: invalid class name */
public class AnonymousClass1P8 implements Comparator<AnonymousClass1P5> {
    public final /* synthetic */ AnonymousClass1P9 A00;

    public AnonymousClass1P8(AnonymousClass1P9 r1) {
        this.A00 = r1;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
    @Override // java.util.Comparator
    public final int compare(AnonymousClass1P5 r3, AnonymousClass1P5 r4) {
        return r4.A01 - r3.A01;
    }
}
