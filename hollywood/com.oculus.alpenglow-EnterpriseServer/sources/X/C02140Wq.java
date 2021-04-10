package X;

import com.google.gson.internal.bind.ObjectTypeAdapter$1;
import java.io.IOException;
import java.util.ArrayList;

/* renamed from: X.0Wq  reason: invalid class name and case insensitive filesystem */
public final class C02140Wq extends AnonymousClass0Bd<Object> {
    public static final AnonymousClass0C3 A01 = new ObjectTypeAdapter$1();
    public final AnonymousClass08D A00;

    @Override // X.AnonymousClass0Bd
    public final void A03(AnonymousClass0GL r4, Object obj) throws IOException {
        if (obj == null) {
            r4.A0A();
            return;
        }
        AnonymousClass0Bd A07 = this.A00.A07(new AnonymousClass0Fe(obj.getClass()));
        if (A07 instanceof C02140Wq) {
            r4.A07();
            r4.A09();
            return;
        }
        A07.A03(r4, obj);
    }

    public C02140Wq(AnonymousClass08D r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass0Bd
    public final Object A02(AnonymousClass0Fo r4) throws IOException {
        switch (r4.A0D().intValue()) {
            case 0:
                ArrayList arrayList = new ArrayList();
                r4.A0H();
                while (r4.A0N()) {
                    arrayList.add(A02(r4));
                }
                r4.A0J();
                return arrayList;
            case 1:
            case 3:
            case 4:
            default:
                throw new IllegalStateException();
            case 2:
                C01100Dk r2 = new C01100Dk();
                r4.A0I();
                while (r4.A0N()) {
                    r2.put(r4.A0E(), A02(r4));
                }
                r4.A0K();
                return r2;
            case 5:
                return r4.A0F();
            case 6:
                return Double.valueOf(r4.A09());
            case 7:
                return Boolean.valueOf(r4.A0O());
            case 8:
                r4.A0L();
                return null;
        }
    }
}
