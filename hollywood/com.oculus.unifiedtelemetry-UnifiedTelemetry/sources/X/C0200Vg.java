package X;

import java.io.IOException;

/* renamed from: X.Vg  reason: case insensitive filesystem */
public final class C0200Vg {
    public Object A00;
    public final Object A01;

    public final void A00(Object obj) throws IOException {
        if (this.A00 == null) {
            this.A00 = obj;
            return;
        }
        StringBuilder sb = new StringBuilder("Already had POJO for id (");
        Object obj2 = this.A01;
        sb.append(obj2.getClass().getName());
        sb.append(") [");
        sb.append(obj2);
        sb.append("]");
        throw new IllegalStateException(sb.toString());
    }

    public C0200Vg(Object obj) {
        this.A01 = obj;
    }
}
