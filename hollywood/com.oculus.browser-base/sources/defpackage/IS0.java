package defpackage;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* renamed from: IS0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class IS0 extends C2740gj0 {
    public final C0942Pj0 d;
    public C2740gj0 e;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public IS0(C2740gj0 gj0) {
        super(gj0.f10015a, gj0.b);
        C0942Pj0 pj0 = new C0942Pj0(gj0);
        this.d = pj0;
    }

    @Override // defpackage.C2740gj0
    public IS0 a() {
        return this;
    }

    public C2740gj0 b() {
        if (this.e == null) {
            ByteBuffer slice = ((ByteBuffer) this.f10015a.position(this.d.c.f7794a)).slice();
            slice.order(ByteOrder.LITTLE_ENDIAN);
            this.e = new C2740gj0(slice, this.b);
        }
        return this.e;
    }

    public IS0(C2740gj0 gj0, C0942Pj0 pj0) {
        super(gj0.f10015a, gj0.b);
        this.d = pj0;
    }
}
