package X;

import android.content.Context;
import android.content.res.Resources;
import javax.annotation.Nullable;

/* renamed from: X.1Wv  reason: invalid class name and case insensitive filesystem */
public final class C06221Wv implements AbstractC00750Ik<AnonymousClass1k8> {
    public final Context A00;
    public final C06231Ww A01;
    public final AnonymousClass1k6 A02;

    public C06221Wv(Context context, @Nullable AnonymousClass1Vw r8) {
        AbstractC00890Mb r3;
        C09761ji r2 = C09761ji.A0I;
        C00740Ii.A02(r2, "ImagePipelineFactory was not initialized!");
        AbstractC00750Ik<Boolean> r0 = null;
        this.A00 = context;
        AnonymousClass1k6 r1 = r2.A04;
        if (r1 == null) {
            r1 = C09761ji.A05(r2);
            r2.A04 = r1;
        }
        this.A02 = r1;
        C06231Ww r5 = new C06231Ww();
        this.A01 = r5;
        Resources resources = context.getResources();
        synchronized (AbstractC00890Mb.class) {
            r3 = AbstractC00890Mb.A00;
            if (r3 == null) {
                r3 = new C03710oL();
                AbstractC00890Mb.A00 = r3;
            }
        }
        C09761ji.A08(r2);
        C01240Vi r22 = C01240Vi.A00;
        if (r22 == null) {
            r22 = new C01240Vi();
            C01240Vi.A00 = r22;
        }
        AbstractC03450mg<AnonymousClass0H3, AnonymousClass0VM> r12 = this.A02.A02;
        r0 = r8 != null ? r8.A00 : r0;
        r5.A00 = resources;
        r5.A02 = r3;
        r5.A04 = r22;
        r5.A03 = r12;
        r5.A01 = r0;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AbstractC00750Ik
    public final /* bridge */ /* synthetic */ AnonymousClass1k8 get() {
        return new AnonymousClass1k8(this.A00, this.A01, this.A02);
    }
}
