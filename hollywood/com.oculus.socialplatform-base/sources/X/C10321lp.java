package X;

import java.io.File;

/* renamed from: X.1lp  reason: invalid class name and case insensitive filesystem */
public class C10321lp implements AbstractC10421mU {
    public boolean A00;
    public final /* synthetic */ AnonymousClass1lQ A01;

    public C10321lp(AnonymousClass1lQ r1) {
        this.A01 = r1;
    }

    @Override // X.AbstractC10421mU
    public final void A8S(File file) {
        AnonymousClass1lQ r1 = this.A01;
        if (!r1.A01.equals(file) && !this.A00) {
            file.delete();
        }
        if (this.A00 && file.equals(r1.A02)) {
            this.A00 = false;
        }
    }

    @Override // X.AbstractC10421mU
    public final void A8T(File file) {
        if (!this.A00 && file.equals(this.A01.A02)) {
            this.A00 = true;
        }
    }

    @Override // X.AbstractC10421mU
    public final void AAz(File file) {
        if (this.A00) {
            AnonymousClass1lQ r3 = this.A01;
            AnonymousClass1mB A002 = AnonymousClass1lQ.A00(r3, file);
            boolean z = false;
            if (A002 != null) {
                String str = A002.A01;
                if (str != ".tmp") {
                    if (str == ".cnt") {
                        z = true;
                    }
                    C00740Ii.A03(z);
                    return;
                } else if (file.lastModified() > r3.A00.now() - AnonymousClass1lQ.A05) {
                    return;
                }
            }
        }
        file.delete();
    }
}
