package X;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import java.util.HashMap;

/* renamed from: X.0Dr  reason: invalid class name and case insensitive filesystem */
public final class C01140Dr {
    public final AbstractC01120Dp A00;
    public final C01150Ds A01;

    public C01140Dr(@NonNull C01150Ds r1, @NonNull AbstractC01120Dp r2) {
        this.A00 = r2;
        this.A01 = r1;
    }

    @NonNull
    @MainThread
    public final <T extends AnonymousClass0Do> T A00(@NonNull Class<T> cls) {
        String canonicalName = cls.getCanonicalName();
        if (canonicalName != null) {
            String A05 = AnonymousClass006.A05("androidx.lifecycle.ViewModelProvider.DefaultKey:", canonicalName);
            HashMap<String, AnonymousClass0Do> hashMap = this.A01.A00;
            T t = (T) hashMap.get(A05);
            if (cls.isInstance(t)) {
                AbstractC01120Dp r1 = this.A00;
                if (r1 instanceof C01130Dq) {
                    ((C01130Dq) r1).A00(t);
                }
            } else {
                AbstractC01120Dp r12 = this.A00;
                t = r12 instanceof AbstractC03500cY ? (T) ((AbstractC03500cY) r12).A01(A05, cls) : (T) r12.A1t(cls);
                AnonymousClass0Do put = hashMap.put(A05, t);
                if (put != null) {
                    put.A00();
                    return t;
                }
            }
            return t;
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }
}
