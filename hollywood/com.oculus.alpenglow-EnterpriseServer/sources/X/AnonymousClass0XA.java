package X;

import com.google.gson.internal.Excluder;
import java.io.IOException;

/* renamed from: X.0XA  reason: invalid class name */
public class AnonymousClass0XA extends AnonymousClass0Bd<T> {
    public AnonymousClass0Bd<T> A00;
    public final /* synthetic */ boolean A01;
    public final /* synthetic */ boolean A02;
    public final /* synthetic */ AnonymousClass08D A03;
    public final /* synthetic */ Excluder A04;
    public final /* synthetic */ AnonymousClass0Fe A05;

    public AnonymousClass0XA(Excluder excluder, boolean z, boolean z2, AnonymousClass08D r4, AnonymousClass0Fe r5) {
        this.A04 = excluder;
        this.A01 = z;
        this.A02 = z2;
        this.A03 = r4;
        this.A05 = r5;
    }

    @Override // X.AnonymousClass0Bd
    public final T A02(AnonymousClass0Fo r4) throws IOException {
        if (this.A01) {
            r4.A0M();
            return null;
        }
        AnonymousClass0Bd<T> r0 = this.A00;
        if (r0 == null) {
            r0 = this.A03.A06(this.A04, this.A05);
            this.A00 = r0;
        }
        return r0.A02(r4);
    }

    @Override // X.AnonymousClass0Bd
    public final void A03(AnonymousClass0GL r4, T t) throws IOException {
        if (this.A02) {
            r4.A0A();
            return;
        }
        AnonymousClass0Bd<T> r0 = this.A00;
        if (r0 == null) {
            r0 = this.A03.A06(this.A04, this.A05);
            this.A00 = r0;
        }
        r0.A03(r4, t);
    }
}
