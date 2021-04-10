package X;

import java.util.concurrent.Executor;

/* renamed from: X.0rD  reason: invalid class name */
public class AnonymousClass0rD implements AnonymousClass0D4<TResult, Void> {
    public final /* synthetic */ AnonymousClass0D4 A00;
    public final /* synthetic */ AnonymousClass0DC A01;
    public final /* synthetic */ AnonymousClass0DD A02;
    public final /* synthetic */ Executor A03;

    public AnonymousClass0rD(AnonymousClass0DC r1, AnonymousClass0DD r2, AnonymousClass0D4 r3, Executor executor) {
        this.A01 = r1;
        this.A02 = r2;
        this.A00 = r3;
        this.A03 = executor;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0D4
    public final Void then(AnonymousClass0DC r5) throws Exception {
        AnonymousClass0DD r3 = this.A02;
        AnonymousClass0D4 r2 = this.A00;
        try {
            this.A03.execute(new AnonymousClass0D7(r3, r2, r5));
            return null;
        } catch (Exception e) {
            r3.A01(new AnonymousClass0D5(e));
            return null;
        }
    }
}
