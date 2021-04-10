package X;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;

/* renamed from: X.rT  reason: case insensitive filesystem */
public final class C1054rT extends PP implements Serializable {
    public static final long serialVersionUID = 1;
    public final LinkedHashSet _registeredSubtypes;

    public static final void A00(C1054rT rTVar, C1043rI rIVar, PO po, AbstractC1032r3 r3Var, AbstractC1020qp qpVar, HashMap hashMap) {
        String A0H;
        if (po._name == null && (A0H = qpVar.A0H(rIVar)) != null) {
            po = new PO(po._class, A0H);
        }
        if (!hashMap.containsKey(po)) {
            hashMap.put(po, po);
            List<PO> A0M = qpVar.A0M(rIVar);
            if (!(A0M == null || A0M.isEmpty())) {
                for (PO po2 : A0M) {
                    C1043rI A01 = C1043rI.A01(po2._class, qpVar, r3Var);
                    if (po2._name == null) {
                        po2 = new PO(po2._class, qpVar.A0H(A01));
                    }
                    A00(rTVar, A01, po2, r3Var, qpVar, hashMap);
                }
            }
        } else if (po._name != null && ((PO) hashMap.get(po))._name == null) {
            hashMap.put(po, po);
        }
    }
}
