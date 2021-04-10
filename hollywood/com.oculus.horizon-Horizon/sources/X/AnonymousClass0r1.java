package X;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* renamed from: X.0r1  reason: invalid class name */
public class AnonymousClass0r1 implements AnonymousClass0D4<Void, AnonymousClass0DC<Void>> {
    public final /* synthetic */ AnonymousClass0D3 A00;
    public final /* synthetic */ AnonymousClass0D4 A01;
    public final /* synthetic */ AnonymousClass0DC A02;
    public final /* synthetic */ Callable A03;
    public final /* synthetic */ Executor A04;

    public AnonymousClass0r1(AnonymousClass0DC r1, Callable callable, AnonymousClass0D4 r3, Executor executor, AnonymousClass0D3 r5) {
        this.A02 = r1;
        this.A03 = callable;
        this.A01 = r3;
        this.A04 = executor;
        this.A00 = r5;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0DC] */
    @Override // X.AnonymousClass0D4
    public final AnonymousClass0DC<Void> then(AnonymousClass0DC<Void> r4) throws Exception {
        if (!((Boolean) this.A03.call()).booleanValue()) {
            return AnonymousClass0DC.A04(null);
        }
        AnonymousClass0DC A042 = AnonymousClass0DC.A04(null);
        AnonymousClass0D4 r0 = this.A01;
        Executor executor = this.A04;
        return A042.A0D(r0, executor).A0D(this.A00.A00, executor);
    }
}
