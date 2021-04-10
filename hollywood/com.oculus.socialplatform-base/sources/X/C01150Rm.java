package X;

import com.facebook.infer.annotation.Nullsafe;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0Rm  reason: invalid class name and case insensitive filesystem */
public final class C01150Rm {
    public static final ThreadLocal<AnonymousClass0RH> A00 = new AnonymousClass0Rl();
    public static final Map<Object, WeakReference<AnonymousClass0lg>> A01 = new WeakHashMap();

    /* JADX WARN: Incorrect return type in method signature: <T:Ljava/lang/Object;>(TT;LX/0lg;)TT; */
    @Nullable
    public static void A00(@Nullable Object obj, AnonymousClass0lg r3) {
        if (obj != null) {
            A01.put(obj, new WeakReference<>(r3));
        }
    }
}
