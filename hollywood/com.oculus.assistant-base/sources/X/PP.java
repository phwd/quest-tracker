package X;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

public abstract class PP {
    public final Collection A01(C1043rI rIVar, AbstractC1032r3 r3Var, AbstractC1020qp qpVar) {
        C1054rT rTVar = (C1054rT) this;
        HashMap hashMap = new HashMap();
        LinkedHashSet linkedHashSet = rTVar._registeredSubtypes;
        if (linkedHashSet != null) {
            Class A0J = rIVar.A0J();
            Iterator it = linkedHashSet.iterator();
            while (it.hasNext()) {
                PO po = (PO) it.next();
                if (A0J.isAssignableFrom(po._class)) {
                    C1054rT.A00(rTVar, C1043rI.A01(po._class, qpVar, r3Var), po, r3Var, qpVar, hashMap);
                }
            }
        }
        C1054rT.A00(rTVar, rIVar, new PO(rIVar.A0J(), null), r3Var, qpVar, hashMap);
        return new ArrayList(hashMap.values());
    }

    public final Collection A02(AbstractC1044rJ rJVar, AbstractC1032r3 r3Var, AbstractC1020qp qpVar, AbstractC1024qt qtVar) {
        Class cls;
        C1054rT rTVar = (C1054rT) this;
        if (qtVar == null) {
            cls = rJVar.A0J();
        } else {
            cls = qtVar._class;
        }
        HashMap hashMap = new HashMap();
        LinkedHashSet linkedHashSet = rTVar._registeredSubtypes;
        if (linkedHashSet != null) {
            Iterator it = linkedHashSet.iterator();
            while (it.hasNext()) {
                PO po = (PO) it.next();
                if (cls.isAssignableFrom(po._class)) {
                    C1054rT.A00(rTVar, C1043rI.A01(po._class, qpVar, r3Var), po, r3Var, qpVar, hashMap);
                }
            }
        }
        List<PO> A0M = qpVar.A0M(rJVar);
        if (A0M != null) {
            for (PO po2 : A0M) {
                C1054rT.A00(rTVar, C1043rI.A01(po2._class, qpVar, r3Var), po2, r3Var, qpVar, hashMap);
            }
        }
        C1054rT.A00(rTVar, C1043rI.A01(cls, qpVar, r3Var), new PO(cls, null), r3Var, qpVar, hashMap);
        return new ArrayList(hashMap.values());
    }
}
