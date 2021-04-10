package X;

import com.google.gson.internal.bind.ArrayTypeAdapter$1;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

/* renamed from: X.0Yg  reason: invalid class name and case insensitive filesystem */
public final class C01960Yg<E> extends AnonymousClass0yl<Object> {
    public static final AbstractC08860ym A02 = new ArrayTypeAdapter$1();
    public final AnonymousClass0yl<E> A00;
    public final Class<E> A01;

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: X.0yl<E> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // X.AnonymousClass0yl
    public final void A03(C09130zU r5, Object obj) throws IOException {
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

    public C01960Yg(C08780ya r2, AnonymousClass0yl<E> r3, Class<E> cls) {
        this.A00 = new AnonymousClass0VA(r2, r3, cls);
        this.A01 = cls;
    }

    @Override // X.AnonymousClass0yl
    public final Object A02(C09120zR r6) throws IOException {
        if (r6.A0G() == AnonymousClass007.A0I) {
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
