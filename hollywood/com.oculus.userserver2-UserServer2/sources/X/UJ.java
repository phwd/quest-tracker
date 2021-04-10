package X;

import java.util.ArrayDeque;

public class UJ implements AnonymousClass1N {
    public final AnonymousClass1Q A00;
    public final /* synthetic */ AnonymousClass1R A01;

    public UJ(AnonymousClass1R r1, AnonymousClass1Q r2) {
        this.A01 = r1;
        this.A00 = r2;
    }

    @Override // X.AnonymousClass1N
    public final void cancel() {
        ArrayDeque<AnonymousClass1Q> arrayDeque = this.A01.A00;
        AnonymousClass1Q r0 = this.A00;
        arrayDeque.remove(r0);
        r0.A00.remove(this);
    }
}
