package X;

import java.io.IOException;
import java.util.ArrayList;

public final class T9 extends AbstractC0131Ob<Object> {
    public static final AbstractC0132Os A01 = new TA();
    public final HY A00;

    @Override // X.AbstractC0131Ob
    public final void A03(mm mmVar, Object obj) throws IOException {
        if (obj == null) {
            mmVar.A0B();
            return;
        }
        AbstractC0131Ob A04 = this.A00.A04(new lQ(obj.getClass()));
        if (A04 instanceof T9) {
            mmVar.A08();
            mmVar.A0A();
            return;
        }
        A04.A03(mmVar, obj);
    }

    public T9(HY hy) {
        this.A00 = hy;
    }

    @Override // X.AbstractC0131Ob
    public final Object A02(lk lkVar) throws IOException {
        switch (lkVar.A0G().intValue()) {
            case 0:
                ArrayList arrayList = new ArrayList();
                lkVar.A0L();
                while (lkVar.A0R()) {
                    arrayList.add(A02(lkVar));
                }
                lkVar.A0N();
                return arrayList;
            case 1:
            case 3:
            case 4:
            default:
                throw new IllegalStateException();
            case 2:
                VD vd = new VD();
                lkVar.A0M();
                while (lkVar.A0R()) {
                    vd.put(lkVar.A0I(), A02(lkVar));
                }
                lkVar.A0O();
                return vd;
            case 5:
                return lkVar.A0J();
            case 6:
                return Double.valueOf(lkVar.A0C());
            case 7:
                return Boolean.valueOf(lkVar.A0S());
            case 8:
                lkVar.A0P();
                return null;
        }
    }
}
