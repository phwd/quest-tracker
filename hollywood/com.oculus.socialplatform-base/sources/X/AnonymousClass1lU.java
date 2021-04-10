package X;

import com.facebook.infer.annotation.Nullsafe;
import java.io.File;
import java.util.concurrent.Executors;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1lU  reason: invalid class name */
public final class AnonymousClass1lU {
    public AnonymousClass1k1 A00;

    public final AnonymousClass1lR A00(AnonymousClass1lV r6) {
        AbstractC00750Ik<File> r1 = r6.A05;
        String str = r6.A06;
        C05080sU r4 = r6.A01;
        AnonymousClass1lS r3 = new AnonymousClass1lS(r1, str, r4);
        Executors.newSingleThreadExecutor();
        new AnonymousClass1mm();
        return new AnonymousClass1lR(r3, r6.A03, r6.A02, r4);
    }

    public AnonymousClass1lU(AnonymousClass1k1 r1) {
        this.A00 = r1;
    }
}
