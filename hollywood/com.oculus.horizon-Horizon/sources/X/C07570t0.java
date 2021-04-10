package X;

import java.util.ArrayDeque;

/* renamed from: X.0t0  reason: invalid class name and case insensitive filesystem */
public class C07570t0 implements AnonymousClass01M {
    public final AnonymousClass01P A00;
    public final /* synthetic */ AnonymousClass01Q A01;

    public C07570t0(AnonymousClass01Q r1, AnonymousClass01P r2) {
        this.A01 = r1;
        this.A00 = r2;
    }

    @Override // X.AnonymousClass01M
    public final void cancel() {
        ArrayDeque<AnonymousClass01P> arrayDeque = this.A01.A00;
        AnonymousClass01P r0 = this.A00;
        arrayDeque.remove(r0);
        r0.A00.remove(this);
    }
}
