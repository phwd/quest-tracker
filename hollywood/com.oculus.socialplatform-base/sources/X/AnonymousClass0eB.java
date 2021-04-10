package X;

import com.google.gson.internal.Excluder;
import java.io.IOException;

/* renamed from: X.0eB  reason: invalid class name */
public class AnonymousClass0eB extends AnonymousClass13Y<T> {
    public AnonymousClass13Y<T> A00;
    public final /* synthetic */ boolean A01;
    public final /* synthetic */ boolean A02;
    public final /* synthetic */ AnonymousClass13N A03;
    public final /* synthetic */ Excluder A04;
    public final /* synthetic */ AnonymousClass14H A05;

    public AnonymousClass0eB(Excluder excluder, boolean z, boolean z2, AnonymousClass13N r4, AnonymousClass14H r5) {
        this.A04 = excluder;
        this.A01 = z;
        this.A02 = z2;
        this.A03 = r4;
        this.A05 = r5;
    }

    @Override // X.AnonymousClass13Y
    public final T A02(AnonymousClass14I r4) throws IOException {
        if (this.A01) {
            r4.A0Q();
            return null;
        }
        AnonymousClass13Y<T> r0 = this.A00;
        if (r0 == null) {
            r0 = this.A03.A02(this.A04, this.A05);
            this.A00 = r0;
        }
        return r0.A02(r4);
    }

    @Override // X.AnonymousClass13Y
    public final void A03(AnonymousClass14L r4, T t) throws IOException {
        if (this.A02) {
            r4.A09();
            return;
        }
        AnonymousClass13Y<T> r0 = this.A00;
        if (r0 == null) {
            r0 = this.A03.A02(this.A04, this.A05);
            this.A00 = r0;
        }
        r0.A03(r4, t);
    }
}
