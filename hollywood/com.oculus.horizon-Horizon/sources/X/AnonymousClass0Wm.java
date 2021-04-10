package X;

import com.google.gson.internal.bind.ObjectTypeAdapter$1;
import java.io.IOException;
import java.util.ArrayList;

/* renamed from: X.0Wm  reason: invalid class name */
public final class AnonymousClass0Wm extends AnonymousClass0yl<Object> {
    public static final AbstractC08860ym A01 = new ObjectTypeAdapter$1();
    public final C08780ya A00;

    @Override // X.AnonymousClass0yl
    public final void A03(C09130zU r4, Object obj) throws IOException {
        if (obj == null) {
            r4.A09();
            return;
        }
        AnonymousClass0yl A04 = this.A00.A04(new C09110zQ(obj.getClass()));
        if (A04 instanceof AnonymousClass0Wm) {
            r4.A06();
            r4.A08();
            return;
        }
        A04.A03(r4, obj);
    }

    public AnonymousClass0Wm(C08780ya r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass0yl
    public final Object A02(C09120zR r4) throws IOException {
        switch (r4.A0G().intValue()) {
            case 0:
                ArrayList arrayList = new ArrayList();
                r4.A0L();
                while (r4.A0R()) {
                    arrayList.add(A02(r4));
                }
                r4.A0N();
                return arrayList;
            case 1:
            case 3:
            case 4:
            default:
                throw new IllegalStateException();
            case 2:
                C09000zB r2 = new C09000zB();
                r4.A0M();
                while (r4.A0R()) {
                    r2.put(r4.A0I(), A02(r4));
                }
                r4.A0O();
                return r2;
            case 5:
                return r4.A0J();
            case 6:
                return Double.valueOf(r4.A0C());
            case 7:
                return Boolean.valueOf(r4.A0S());
            case 8:
                r4.A0P();
                return null;
        }
    }
}
