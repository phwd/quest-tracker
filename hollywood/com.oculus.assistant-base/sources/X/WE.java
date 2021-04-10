package X;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;

public final class WE {
    public static WE A05;
    public C0904oW A00;
    public W9 A01;
    public WB A02 = new WB();
    public final SettableFuture A03 = new SettableFuture();
    public final W8 A04 = new C1251w7(this);

    public final synchronized ListenableFuture A00() {
        C0904oW oWVar = this.A00;
        if (oWVar != null) {
            return new AnonymousClass1u(oWVar);
        }
        return AnonymousClass1O.A00(this.A03, new C1252w8(this), V3.INSTANCE);
    }

    public WE() {
        W9 w9 = new W9(BX.A00(), this.A04);
        this.A01 = w9;
        w9.A01();
    }
}
