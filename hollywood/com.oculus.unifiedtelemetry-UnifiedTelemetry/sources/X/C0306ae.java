package X;

import java.util.ArrayDeque;

/* renamed from: X.ae  reason: case insensitive filesystem */
public class C0306ae implements AnonymousClass1M {
    public final AnonymousClass1P A00;
    public final /* synthetic */ AnonymousClass1Q A01;

    public C0306ae(AnonymousClass1Q r1, AnonymousClass1P r2) {
        this.A01 = r1;
        this.A00 = r2;
    }

    @Override // X.AnonymousClass1M
    public final void cancel() {
        ArrayDeque<AnonymousClass1P> arrayDeque = this.A01.A00;
        AnonymousClass1P r0 = this.A00;
        arrayDeque.remove(r0);
        r0.A00.remove(this);
    }
}
