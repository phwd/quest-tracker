package X;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import java.util.HashMap;

/* renamed from: X.Ai  reason: case insensitive filesystem */
public final class C0045Ai {
    public final Ag A00;
    public final C0046Aj A01;

    public C0045Ai(@NonNull C0046Aj aj, @NonNull Ag ag) {
        this.A00 = ag;
        this.A01 = aj;
    }

    @NonNull
    @MainThread
    public final <T extends Af> T A00(@NonNull Class<T> cls) {
        String canonicalName = cls.getCanonicalName();
        if (canonicalName != null) {
            String A04 = AnonymousClass06.A04("androidx.lifecycle.ViewModelProvider.DefaultKey:", canonicalName);
            HashMap<String, Af> hashMap = this.A01.A00;
            T t = (T) hashMap.get(A04);
            if (cls.isInstance(t)) {
                Ag ag = this.A00;
                if (ag instanceof Ah) {
                    ((Ah) ag).A00(t);
                }
            } else {
                Ag ag2 = this.A00;
                t = ag2 instanceof Zs ? (T) ((Zs) ag2).A01(A04, cls) : (T) ag2.A1d(cls);
                Af put = hashMap.put(A04, t);
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
