package X;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public abstract class O4 {
    public final AbstractC1024qt A00;

    public final C0244Mv A03(C0244Mv mv) {
        C0244Mv A01;
        C1046rL rLVar = (C1046rL) this;
        AbstractC1020qp qpVar = rLVar.A07;
        if (qpVar == null || (A01 = qpVar.A01(rLVar.A09)) == null) {
            return mv;
        }
        return A01;
    }

    public final AnonymousClass0q A04(String str, Class[] clsArr) {
        C1043rI rIVar = ((C1046rL) this).A09;
        if (rIVar.A01 == null) {
            C1043rI.A07(rIVar);
        }
        LinkedHashMap linkedHashMap = rIVar.A01.A00;
        if (linkedHashMap == null) {
            return null;
        }
        return (AnonymousClass0q) linkedHashMap.get(new PI(str, clsArr));
    }

    public final C0299Pv A05() {
        C1046rL rLVar = (C1046rL) this;
        C0299Pv pv = rLVar.A04;
        if (pv != null) {
            return pv;
        }
        C0300Pw pw = rLVar.A08._base._typeFactory;
        AbstractC1024qt qtVar = ((O4) rLVar).A00;
        C0299Pv pv2 = new C0299Pv(pw, null, qtVar._class, qtVar);
        rLVar.A04 = pv2;
        return pv2;
    }

    public final Q0 A06() {
        C1043rI rIVar = ((C1046rL) this).A09;
        if (rIVar.A02 == null) {
            C1043rI.A05(rIVar);
        }
        return rIVar.A02;
    }

    public final List A07() {
        C1046rL rLVar = (C1046rL) this;
        C1043rI rIVar = rLVar.A09;
        if (!rIVar.A06) {
            C1043rI.A06(rIVar);
        }
        List<AnonymousClass0q> list = rIVar.A04;
        if (list.isEmpty()) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        for (AnonymousClass0q r1 : list) {
            if (C1046rL.A02(rLVar, r1)) {
                arrayList.add(r1);
            }
        }
        return arrayList;
    }

    public O4(AbstractC1024qt qtVar) {
        this.A00 = qtVar;
    }
}
