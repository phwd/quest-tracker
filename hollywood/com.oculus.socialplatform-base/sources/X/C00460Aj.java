package X;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import java.util.HashMap;

/* renamed from: X.0Aj  reason: invalid class name and case insensitive filesystem */
public final class C00460Aj {
    public final AnonymousClass0Ah A00;
    public final C00470Ak A01;

    public C00460Aj(@NonNull C00470Ak r1, @NonNull AnonymousClass0Ah r2) {
        this.A00 = r2;
        this.A01 = r1;
    }

    @NonNull
    @MainThread
    public final <T extends AnonymousClass0Ag> T A00(@NonNull Class<T> cls) {
        String canonicalName = cls.getCanonicalName();
        if (canonicalName != null) {
            String A07 = AnonymousClass006.A07("androidx.lifecycle.ViewModelProvider.DefaultKey:", canonicalName);
            HashMap<String, AnonymousClass0Ag> hashMap = this.A01.A00;
            T t = (T) hashMap.get(A07);
            if (cls.isInstance(t)) {
                AnonymousClass0Ah r1 = this.A00;
                if (r1 instanceof AnonymousClass0Ai) {
                    ((AnonymousClass0Ai) r1).A00(t);
                }
            } else {
                AnonymousClass0Ah r12 = this.A00;
                t = r12 instanceof AnonymousClass0ur ? (T) ((AnonymousClass0ur) r12).A01(A07, cls) : (T) r12.A2L(cls);
                AnonymousClass0Ag put = hashMap.put(A07, t);
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
