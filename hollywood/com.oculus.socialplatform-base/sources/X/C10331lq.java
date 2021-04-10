package X;

import com.facebook.cache.disk.DiskStorage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* renamed from: X.1lq  reason: invalid class name and case insensitive filesystem */
public class C10331lq implements AbstractC10421mU {
    public final List<DiskStorage.Entry> A00 = new ArrayList();
    public final /* synthetic */ AnonymousClass1lQ A01;

    @Override // X.AbstractC10421mU
    public final void A8S(File file) {
    }

    @Override // X.AbstractC10421mU
    public final void A8T(File file) {
    }

    public C10331lq(AnonymousClass1lQ r2) {
        this.A01 = r2;
    }

    @Override // X.AbstractC10421mU
    public final void AAz(File file) {
        AnonymousClass1mB A002 = AnonymousClass1lQ.A00(this.A01, file);
        if (A002 != null && A002.A01 == ".cnt") {
            this.A00.add(new C10361lt(A002.A00, file));
        }
    }
}
