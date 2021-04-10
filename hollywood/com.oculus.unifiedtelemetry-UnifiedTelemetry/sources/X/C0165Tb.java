package X;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

/* renamed from: X.Tb  reason: case insensitive filesystem */
public final class C0165Tb<E> extends AbstractC0131Ob<Object> {
    public static final AbstractC0132Os A02 = new Tc();
    public final AbstractC0131Ob<E> A00;
    public final Class<E> A01;

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: X.Ob<E> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // X.AbstractC0131Ob
    public final void A03(mm mmVar, Object obj) throws IOException {
        if (obj == null) {
            mmVar.A0B();
            return;
        }
        mmVar.A07();
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            this.A00.A03(mmVar, Array.get(obj, i));
        }
        mmVar.A09();
    }

    public C0165Tb(HY hy, AbstractC0131Ob<E> ob, Class<E> cls) {
        this.A00 = new C0162Sx(hy, ob, cls);
        this.A01 = cls;
    }

    @Override // X.AbstractC0131Ob
    public final Object A02(lk lkVar) throws IOException {
        if (lkVar.A0G() == AnonymousClass07.A08) {
            lkVar.A0P();
            return null;
        }
        ArrayList arrayList = new ArrayList();
        lkVar.A0L();
        while (lkVar.A0R()) {
            arrayList.add(this.A00.A02(lkVar));
        }
        lkVar.A0N();
        int size = arrayList.size();
        Object newInstance = Array.newInstance((Class<?>) this.A01, size);
        for (int i = 0; i < size; i++) {
            Array.set(newInstance, i, arrayList.get(i));
        }
        return newInstance;
    }
}
