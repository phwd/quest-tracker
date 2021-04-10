package X;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import java.util.HashMap;

/* renamed from: X.0Ai  reason: invalid class name and case insensitive filesystem */
public final class C00510Ai {
    public final AnonymousClass0Ag A00;
    public final C00520Aj A01;

    public C00510Ai(@NonNull C00520Aj r1, @NonNull AnonymousClass0Ag r2) {
        this.A00 = r2;
        this.A01 = r1;
    }

    @NonNull
    @MainThread
    public final <T extends AnonymousClass0Af> T A00(@NonNull Class<T> cls) {
        String canonicalName = cls.getCanonicalName();
        if (canonicalName != null) {
            String A05 = AnonymousClass006.A05("androidx.lifecycle.ViewModelProvider.DefaultKey:", canonicalName);
            HashMap<String, AnonymousClass0Af> hashMap = this.A01.A00;
            T t = (T) hashMap.get(A05);
            if (cls.isInstance(t)) {
                AnonymousClass0Ag r1 = this.A00;
                if (r1 instanceof C00500Ah) {
                    ((C00500Ah) r1).A00(t);
                }
            } else {
                AnonymousClass0Ag r12 = this.A00;
                t = r12 instanceof AbstractC07250rj ? (T) ((AbstractC07250rj) r12).A01(A05, cls) : (T) r12.A1w(cls);
                AnonymousClass0Af put = hashMap.put(A05, t);
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
