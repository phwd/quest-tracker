package X;

import com.google.gson.internal.bind.ObjectTypeAdapter$1;
import java.io.IOException;
import java.util.ArrayList;

/* renamed from: X.0dr  reason: invalid class name and case insensitive filesystem */
public final class C01390dr extends AnonymousClass13Y<Object> {
    public static final AnonymousClass13Z A01 = new ObjectTypeAdapter$1();
    public final AnonymousClass13N A00;

    @Override // X.AnonymousClass13Y
    public final void A03(AnonymousClass14L r4, Object obj) throws IOException {
        if (obj == null) {
            r4.A09();
            return;
        }
        AnonymousClass13Y A03 = this.A00.A03(new AnonymousClass14H(obj.getClass()));
        if (A03 instanceof C01390dr) {
            r4.A06();
            r4.A08();
            return;
        }
        A03.A03(r4, obj);
    }

    public C01390dr(AnonymousClass13N r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass13Y
    public final Object A02(AnonymousClass14I r4) throws IOException {
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
                AnonymousClass142 r2 = new AnonymousClass142();
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
