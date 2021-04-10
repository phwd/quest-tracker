package X;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.lifecycle.SavedStateHandleController;
import java.util.HashMap;

public final class CA {
    public final C8 A00;
    public final CB A01;

    public CA(@NonNull CB cb, @NonNull C8 c8) {
        this.A00 = c8;
        this.A01 = cb;
    }

    @NonNull
    @MainThread
    public final <T extends C7> T A00(@NonNull Class<T> cls) {
        String canonicalName = cls.getCanonicalName();
        if (canonicalName != null) {
            String A03 = AnonymousClass06.A03("androidx.lifecycle.ViewModelProvider.DefaultKey:", canonicalName);
            HashMap<String, C7> hashMap = this.A01.A00;
            T t = (T) hashMap.get(A03);
            if (cls.isInstance(t)) {
                C8 c8 = this.A00;
                if (c8 instanceof C9) {
                    C9 c9 = (C9) c8;
                    if (c9 instanceof CQ) {
                        CQ cq = (CQ) c9;
                        SavedStateHandleController.A01(t, cq.A04, cq.A02);
                    }
                }
            } else {
                C8 c82 = this.A00;
                t = c82 instanceof TY ? (T) ((TY) c82).A00(A03, cls) : (T) c82.A1E(cls);
                C7 put = hashMap.put(A03, t);
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
