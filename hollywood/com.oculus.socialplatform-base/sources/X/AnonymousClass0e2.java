package X;

import com.google.gson.internal.bind.ArrayTypeAdapter$1;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

/* renamed from: X.0e2  reason: invalid class name */
public final class AnonymousClass0e2<E> extends AnonymousClass13Y<Object> {
    public static final AnonymousClass13Z A02 = new ArrayTypeAdapter$1();
    public final AnonymousClass13Y<E> A00;
    public final Class<E> A01;

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: X.13Y<E> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // X.AnonymousClass13Y
    public final void A03(AnonymousClass14L r5, Object obj) throws IOException {
        if (obj == null) {
            r5.A09();
            return;
        }
        r5.A05();
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            this.A00.A03(r5, Array.get(obj, i));
        }
        r5.A07();
    }

    public AnonymousClass0e2(AnonymousClass13N r2, AnonymousClass13Y<E> r3, Class<E> cls) {
        this.A00 = new AnonymousClass0dg(r2, r3, cls);
        this.A01 = cls;
    }

    @Override // X.AnonymousClass13Y
    public final Object A02(AnonymousClass14I r6) throws IOException {
        if (r6.A0G() == AnonymousClass007.A09) {
            r6.A0P();
            return null;
        }
        ArrayList arrayList = new ArrayList();
        r6.A0L();
        while (r6.A0R()) {
            arrayList.add(this.A00.A02(r6));
        }
        r6.A0N();
        int size = arrayList.size();
        Object newInstance = Array.newInstance((Class<?>) this.A01, size);
        for (int i = 0; i < size; i++) {
            Array.set(newInstance, i, arrayList.get(i));
        }
        return newInstance;
    }
}
