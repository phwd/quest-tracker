package X;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class Sb extends AbstractC0131Ob<M4> {
    /* access modifiers changed from: private */
    /* renamed from: A01 */
    public final void A03(mm mmVar, M4 m4) throws IOException {
        if (m4 == null || (m4 instanceof U3)) {
            mmVar.A0B();
        } else if (m4 instanceof U1) {
            U1 u1 = (U1) m4;
            Object obj = u1.A00;
            if (obj instanceof Number) {
                mmVar.A0E(u1.A04());
            } else if (obj instanceof Boolean) {
                mmVar.A0H(((Boolean) obj).booleanValue());
            } else {
                mmVar.A0G(u1.A03());
            }
        } else if (m4 instanceof U5) {
            mmVar.A07();
            Iterator<M4> it = ((U5) m4).iterator();
            while (it.hasNext()) {
                A03(mmVar, it.next());
            }
            mmVar.A09();
        } else if (m4 instanceof U2) {
            mmVar.A08();
            for (Map.Entry<String, M4> entry : m4.A02().A00.entrySet()) {
                mmVar.A0F(entry.getKey());
                A03(mmVar, entry.getValue());
            }
            mmVar.A0A();
        } else {
            StringBuilder sb = new StringBuilder("Couldn't write ");
            sb.append(m4.getClass());
            throw new IllegalArgumentException(sb.toString());
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final M4 A02(lk lkVar) throws IOException {
        switch (lkVar.A0G().intValue()) {
            case 0:
                U5 u5 = new U5();
                lkVar.A0L();
                while (lkVar.A0R()) {
                    u5.A00.add(A02(lkVar));
                }
                lkVar.A0N();
                return u5;
            case 1:
            case 3:
            case 4:
            default:
                throw new IllegalArgumentException();
            case 2:
                U2 u2 = new U2();
                lkVar.A0M();
                while (lkVar.A0R()) {
                    u2.A00.put(lkVar.A0I(), A02(lkVar));
                }
                lkVar.A0O();
                return u2;
            case 5:
                return new U1(lkVar.A0J());
            case 6:
                return new U1(new C0190Ut(lkVar.A0J()));
            case 7:
                return new U1(Boolean.valueOf(lkVar.A0S()));
            case 8:
                lkVar.A0P();
                return U3.A00;
        }
    }
}
