package X;

import com.google.gson.internal.bind.ArrayTypeAdapter$1;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

/* renamed from: X.0X1  reason: invalid class name */
public final class AnonymousClass0X1<E> extends AnonymousClass0Bd<Object> {
    public static final AnonymousClass0C3 A02 = new ArrayTypeAdapter$1();
    public final AnonymousClass0Bd<E> A00;
    public final Class<E> A01;

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: X.0Bd<E> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // X.AnonymousClass0Bd
    public final void A03(AnonymousClass0GL r5, Object obj) throws IOException {
        if (obj == null) {
            r5.A0A();
            return;
        }
        r5.A06();
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            this.A00.A03(r5, Array.get(obj, i));
        }
        r5.A08();
    }

    public AnonymousClass0X1(AnonymousClass08D r2, AnonymousClass0Bd<E> r3, Class<E> cls) {
        this.A00 = new AnonymousClass0Wf(r2, r3, cls);
        this.A01 = cls;
    }

    @Override // X.AnonymousClass0Bd
    public final Object A02(AnonymousClass0Fo r6) throws IOException {
        if (r6.A0D() == AnonymousClass007.A0I) {
            r6.A0L();
            return null;
        }
        ArrayList arrayList = new ArrayList();
        r6.A0H();
        while (r6.A0N()) {
            arrayList.add(this.A00.A02(r6));
        }
        r6.A0J();
        int size = arrayList.size();
        Object newInstance = Array.newInstance((Class<?>) this.A01, size);
        for (int i = 0; i < size; i++) {
            Array.set(newInstance, i, arrayList.get(i));
        }
        return newInstance;
    }
}
