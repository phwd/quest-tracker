package X;

import android.content.Context;
import com.facebook.infer.annotation.Nullsafe;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1QQ  reason: invalid class name */
public final class AnonymousClass1QQ {
    public final Context A00;
    public final C06141Qs A01 = C06141Qs.A00;
    public final ConcurrentHashMap<Class, Object> A02 = new ConcurrentHashMap<>();

    public final <T> AnonymousClass1QO<T> A00(String str, Class<T> cls) {
        Object obj;
        int i = 0;
        do {
            int i2 = i + 1;
            if (i < 3) {
                ConcurrentHashMap<Class, Object> concurrentHashMap = this.A02;
                obj = concurrentHashMap.get(cls);
                if (obj != null) {
                    break;
                }
                try {
                    obj = this.A00.getSystemService(str);
                    if (obj != null && cls.isInstance(obj)) {
                        concurrentHashMap.putIfAbsent(cls, obj);
                        i = i2;
                    }
                } catch (Exception unused) {
                }
                obj = null;
                i = i2;
            } else {
                AnonymousClass0MD.A0A("SystemServiceManager", "Cannot get system service after MAX_RETRIES: %s", cls.getName());
                return AnonymousClass1QP.A00;
            }
        } while (obj == null);
        return new AnonymousClass1QL(obj);
    }

    public AnonymousClass1QQ(Context context) {
        this.A00 = context;
    }
}
