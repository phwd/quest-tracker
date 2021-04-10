package X;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import javax.inject.Provider;

/* renamed from: X.0li  reason: invalid class name and case insensitive filesystem */
public final class C03200li<T> implements Provider<T> {
    public static final byte[] A03 = {1};
    public T A00;
    public final C03210lj A01;
    public final Provider<T> A02;

    /* JADX INFO: finally extract failed */
    @Override // javax.inject.Provider
    public final T get() {
        T t;
        V A6G;
        Context baseContext;
        C01130Rh r5 = C01130Rh.A01.get();
        C03210lj r3 = this.A01;
        AnonymousClass0RH A002 = r3.A00();
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
                        String str = "ContextScope";
                        if (b == 1) {
                            str = "ApplicationScoped";
                        } else if (b == 4) {
                            str = "UserScope";
                        } else if (b != 8) {
                            throw new IllegalArgumentException(String.format("Invalid scope value %s", Integer.toBinaryString(b)));
                        }
                        throw new AnonymousClass0Rb(AnonymousClass006.A0B("Scope violation. Should not call inject ", str, " into ", str));
                    }
                }
            }
            byte b2 = r5.A00;
            r5.A00 = (byte) (8 | b2);
            Context context = A003;
            while (!AnonymousClass0Jq.class.isInstance(context) && (context instanceof ContextWrapper) && context != (baseContext = ((ContextWrapper) context).getBaseContext())) {
                try {
                    context = baseContext;
                } finally {
                    r5.A00 = b2;
                }
            }
            synchronized (this) {
                t = this.A00;
                if (t == null) {
                    AnonymousClass0HO<Context, AnonymousClass00A> r32 = r3.A00;
                    WeakHashMap<K, WeakReference<V>> weakHashMap = r32.A01;
                    WeakReference<V> weakReference = weakHashMap.get(A003);
                    if (weakReference == null || (A6G = weakReference.get()) == null) {
                        A6G = r32.A00.A6G(A003);
                        weakHashMap.put(A003, new WeakReference<>(A6G));
                    }
                    A002.A02.add(A6G);
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
        throw new AnonymousClass0Rb("Called context scoped provider outside of context scope");
    }

    public C03200li(C03210lj r1, Provider<T> provider) {
        this.A01 = r1;
        this.A02 = provider;
    }
}
