package X;

import android.content.Context;
import android.content.res.Resources;
import javax.annotation.Nullable;

/* renamed from: X.1lT  reason: invalid class name */
public final class AnonymousClass1lT implements AnonymousClass0KW<AnonymousClass1pD> {
    public final Context A00;
    public final AnonymousClass1lU A01;
    public final C09801pc A02;

    public AnonymousClass1lT(Context context, @Nullable AnonymousClass1iK r8) {
        AnonymousClass1n6 r3;
        C10051qt r2 = C10051qt.A0I;
        AnonymousClass0KU.A02(r2, "ImagePipelineFactory was not initialized!");
        this.A00 = context;
        C09801pc r0 = r2.A04;
        if (r0 == null) {
            r0 = C10051qt.A05(r2);
            r2.A04 = r0;
        }
        this.A02 = r0;
        AnonymousClass1lU r5 = new AnonymousClass1lU();
        this.A01 = r5;
        Resources resources = context.getResources();
        synchronized (AnonymousClass1n6.class) {
            r3 = AnonymousClass1n6.A00;
            if (r3 == null) {
                r3 = new AnonymousClass1n4();
                AnonymousClass1n6.A00 = r3;
            }
        }
        C10051qt.A07(r2);
        AnonymousClass1lS r22 = AnonymousClass1lS.A00;
        if (r22 == null) {
            r22 = new AnonymousClass1lS();
            AnonymousClass1lS.A00 = r22;
        }
        AbstractC10301rt<AnonymousClass1kC, AnonymousClass1q1> r1 = this.A02.A02;
        AnonymousClass0KW<Boolean> r02 = r8.A00;
        r5.A00 = resources;
        r5.A02 = r3;
        r5.A04 = r22;
        r5.A03 = r1;
        r5.A01 = r02;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0KW
    public final AnonymousClass1pD get() {
        return new AnonymousClass1pD(this.A00, this.A01, this.A02);
    }
}
