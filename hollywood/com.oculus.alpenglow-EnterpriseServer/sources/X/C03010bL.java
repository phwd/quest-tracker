package X;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/* renamed from: X.0bL  reason: invalid class name and case insensitive filesystem */
public final class C03010bL<T> implements AbstractC07240oz<T> {
    public static final byte[] A03 = {1};
    public T A00;
    public final C03020bM A01;
    public final AbstractC07240oz<T> A02;

    /* JADX INFO: finally extract failed */
    @Override // X.AbstractC07240oz
    public final T get() {
        String str;
        T t;
        V A5e;
        Context baseContext;
        AnonymousClass0RZ r5 = AnonymousClass0RZ.A01.get();
        C03020bM r3 = this.A01;
        AnonymousClass0RA A002 = r3.A00();
        Context A003 = A002.A00();
        if (A003 != null) {
            if (!(A003 instanceof Application)) {
                byte[] bArr = A03;
                for (byte b : bArr) {
                    boolean z = false;
                    if ((b & r5.A00) != 0) {
                        z = true;
                    }
                    if (z) {
                        String str2 = "ContextScope";
                        if (b == 1) {
                            str2 = "ApplicationScoped";
                        } else if (b == 4) {
                            str2 = "UserScope";
                        } else if (b != 8) {
                            throw new IllegalArgumentException(String.format("Invalid scope value %s", Integer.toBinaryString(b)));
                        }
                        str = AnonymousClass006.A08("Scope violation. Should not call inject ", str2, " into ", str2);
                    }
                }
            }
            byte b2 = r5.A00;
            r5.A00 = (byte) (8 | b2);
            Context context = A003;
            while (!AnonymousClass0LA.class.isInstance(context) && (context instanceof ContextWrapper) && context != (baseContext = ((ContextWrapper) context).getBaseContext())) {
                try {
                    context = baseContext;
                } finally {
                    r5.A00 = b2;
                }
            }
            synchronized (this) {
                t = this.A00;
                if (t == null) {
                    AnonymousClass0JG<Context, AnonymousClass00A> r32 = r3.A00;
                    WeakHashMap<K, WeakReference<V>> weakHashMap = r32.A01;
                    WeakReference<V> weakReference = weakHashMap.get(A003);
                    if (weakReference == null || (A5e = weakReference.get()) == null) {
                        A5e = r32.A00.A5e(A003);
                        weakHashMap.put(A003, new WeakReference<>(A5e));
                    }
                    A002.A02.add(A5e);
                    try {
                        t = this.A02.get();
                        A002.A01();
                        this.A00 = t;
                    } catch (Throwable th) {
                        A002.A01();
                        throw th;
                    }
                }
            }
            return t;
        }
        str = "Called context scoped provider outside of context scope";
        throw new AnonymousClass0RU(str);
    }

    public C03010bL(C03020bM r1, AbstractC07240oz<T> r2) {
        this.A01 = r1;
        this.A02 = r2;
    }
}
