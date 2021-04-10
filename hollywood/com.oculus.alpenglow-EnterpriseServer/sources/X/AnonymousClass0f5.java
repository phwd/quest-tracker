package X;

import java.util.ArrayDeque;

/* renamed from: X.0f5  reason: invalid class name */
public class AnonymousClass0f5 implements AnonymousClass01N {
    public final AnonymousClass01Q A00;
    public final /* synthetic */ AnonymousClass01R A01;

    public AnonymousClass0f5(AnonymousClass01R r1, AnonymousClass01Q r2) {
        this.A01 = r1;
        this.A00 = r2;
    }

    @Override // X.AnonymousClass01N
    public final void cancel() {
        ArrayDeque<AnonymousClass01Q> arrayDeque = this.A01.A00;
        AnonymousClass01Q r0 = this.A00;
        arrayDeque.remove(r0);
        r0.A00.remove(this);
    }
}
