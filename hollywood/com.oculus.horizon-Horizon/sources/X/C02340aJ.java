package X;

import com.google.gson.internal.Excluder;
import java.io.IOException;

/* renamed from: X.0aJ  reason: invalid class name and case insensitive filesystem */
public class C02340aJ extends AnonymousClass0yl<T> {
    public AnonymousClass0yl<T> A00;
    public final /* synthetic */ boolean A01;
    public final /* synthetic */ boolean A02;
    public final /* synthetic */ C08780ya A03;
    public final /* synthetic */ Excluder A04;
    public final /* synthetic */ C09110zQ A05;

    public C02340aJ(Excluder excluder, boolean z, boolean z2, C08780ya r4, C09110zQ r5) {
        this.A04 = excluder;
        this.A01 = z;
        this.A02 = z2;
        this.A03 = r4;
        this.A05 = r5;
    }

    @Override // X.AnonymousClass0yl
    public final T A02(C09120zR r4) throws IOException {
        if (this.A01) {
            r4.A0Q();
            return null;
        }
        AnonymousClass0yl<T> r0 = this.A00;
        if (r0 == null) {
            r0 = this.A03.A03(this.A04, this.A05);
            this.A00 = r0;
        }
        return r0.A02(r4);
    }

    @Override // X.AnonymousClass0yl
    public final void A03(C09130zU r4, T t) throws IOException {
        if (this.A02) {
            r4.A09();
            return;
        }
        AnonymousClass0yl<T> r0 = this.A00;
        if (r0 == null) {
            r0 = this.A03.A03(this.A04, this.A05);
            this.A00 = r0;
        }
        r0.A03(r4, t);
    }
}
