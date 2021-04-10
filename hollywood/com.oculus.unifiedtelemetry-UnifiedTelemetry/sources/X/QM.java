package X;

import java.util.Comparator;

public class QM implements Comparator<C0475qE> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
    @Override // java.util.Comparator
    public final int compare(C0475qE qEVar, C0475qE qEVar2) {
        return qEVar.A01.rawType.getName().compareTo(qEVar2.A01.rawType.getName());
    }
}
