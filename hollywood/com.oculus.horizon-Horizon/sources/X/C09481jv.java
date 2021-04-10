package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1jv  reason: invalid class name and case insensitive filesystem */
public final class C09481jv extends AnonymousClass1k9<AnonymousClass1k2> {
    public Executor A00;
    @Nullable
    public final C08580wo A01;
    public final AnonymousClass0N1 A02;

    public C09481jv(AnonymousClass0N1 r3) {
        ExecutorService A022 = r3.A0J.A02();
        this.A02 = r3;
        this.A00 = A022;
        C08590wp r1 = new C08590wp();
        r1.A02 = true;
        this.A01 = new C08580wo(r1);
    }
}
