package X;

import android.content.Intent;
import android.os.Looper;

/* renamed from: X.0mW  reason: invalid class name and case insensitive filesystem */
public class HandlerC06110mW extends HandlerC01920Ya {
    public final /* synthetic */ AbstractServiceC01930Yb A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HandlerC06110mW(AbstractServiceC01930Yb r1, Looper looper) {
        super(r1, looper);
        this.A00 = r1;
    }

    @Override // X.HandlerC01920Ya
    public final void A00() {
        this.A00.A0E();
    }

    @Override // X.HandlerC01920Ya
    public final void A01() {
        this.A00.A0B();
    }

    @Override // X.HandlerC01920Ya
    public final void A02(Intent intent, int i, int i2) {
        this.A00.A0F(intent, i, i2);
    }
}
