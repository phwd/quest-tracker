package X;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.Map;

/* renamed from: X.0Uo  reason: invalid class name and case insensitive filesystem */
public class C01370Uo extends AnonymousClass0yl<AbstractC08820ye> {
    /* access modifiers changed from: private */
    /* renamed from: A01 */
    public final void A03(C09130zU r4, AbstractC08820ye r5) throws IOException {
        String str;
        if (r5 == null || (r5 instanceof AnonymousClass0c8)) {
            r4.A09();
        } else if (r5 instanceof C03090c6) {
            C03090c6 r52 = (C03090c6) r5;
            Object obj = r52.A00;
            if (obj instanceof Number) {
                r4.A0B(r52.A03());
            } else if (obj instanceof Boolean) {
                boolean booleanValue = ((Boolean) obj).booleanValue();
                C09130zU.A03(r4);
                C09130zU.A02(r4);
                Writer writer = r4.A07;
                if (booleanValue) {
                    str = "true";
                } else {
                    str = "false";
                }
                writer.write(str);
            } else {
                r4.A0D(r52.A04());
            }
        } else if (r5 instanceof C03140cI) {
            r4.A05();
            Iterator<AbstractC08820ye> it = ((C03140cI) r5).iterator();
            while (it.hasNext()) {
                A03(r4, it.next());
            }
            r4.A07();
        } else if (r5 instanceof C03100c7) {
            r4.A06();
            for (Map.Entry<String, AbstractC08820ye> entry : r5.A02().A00.entrySet()) {
                r4.A0C(entry.getKey());
                A03(r4, entry.getValue());
            }
            r4.A08();
        } else {
            StringBuilder sb = new StringBuilder("Couldn't write ");
            sb.append(r5.getClass());
            throw new IllegalArgumentException(sb.toString());
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final AbstractC08820ye A02(C09120zR r5) throws IOException {
        switch (r5.A0G().intValue()) {
            case 0:
                C03140cI r2 = new C03140cI();
                r5.A0L();
                while (r5.A0R()) {
                    r2.A00.add(A02(r5));
                }
                r5.A0N();
                return r2;
            case 1:
            case 3:
            case 4:
            default:
                throw new IllegalArgumentException();
            case 2:
                C03100c7 r3 = new C03100c7();
                r5.A0M();
                while (r5.A0R()) {
                    r3.A00.put(r5.A0I(), A02(r5));
                }
                r5.A0O();
                return r3;
            case 5:
                return new C03090c6(r5.A0J());
            case 6:
                return new C03090c6(new C08950z1(r5.A0J()));
            case 7:
                return new C03090c6(Boolean.valueOf(r5.A0S()));
            case 8:
                r5.A0P();
                return AnonymousClass0c8.A00;
        }
    }
}
