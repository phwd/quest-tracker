package X;

import com.facebook.infer.annotation.Nullsafe;
import java.io.File;
import java.util.concurrent.Executors;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1oa  reason: invalid class name and case insensitive filesystem */
public final class C09571oa {
    public C09661oj A00;

    public final AnonymousClass1oR A00(C09581ob r6) {
        AnonymousClass0KW<File> r1 = r6.A05;
        String str = r6.A06;
        C09611oe r4 = r6.A01;
        AnonymousClass1oO r3 = new AnonymousClass1oO(r1, str, r4);
        Executors.newSingleThreadExecutor();
        new C09671ok();
        return new AnonymousClass1oR(r3, r6.A03, r6.A02, r4);
    }

    public C09571oa(C09661oj r1) {
        this.A00 = r1;
    }
}
