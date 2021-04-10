package X;

import com.fasterxml.jackson.databind.JsonSerializer;
import java.util.Collection;

/* renamed from: X.Ph  reason: case insensitive filesystem */
public abstract class AbstractC0285Ph {
    public abstract JsonSerializer A02(AbstractC1031r2 r2Var, AbstractC1024qt qtVar);

    public final PU A03(AnonymousClass2H r6, AbstractC1024qt qtVar) {
        PT A00;
        C1043rI rIVar = ((C1046rL) r6.A02(r6.A03(qtVar._class))).A09;
        AbstractC1020qp A01 = r6.A01();
        if (!(A01 instanceof Rw)) {
            A00 = null;
        } else {
            A00 = Rw.A00((Rw) A01, r6, rIVar);
        }
        Collection collection = null;
        if (A00 == null) {
            A00 = r6._base._typeResolverBuilder;
            if (A00 == null) {
                return null;
            }
        } else {
            collection = r6._subtypeResolver.A01(rIVar, r6, A01);
        }
        return A00.A1O(r6, qtVar, collection);
    }
}
