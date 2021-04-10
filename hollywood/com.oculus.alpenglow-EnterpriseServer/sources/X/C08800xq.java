package X;

import android.content.Context;
import com.facebook.infer.annotation.Nullsafe;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0xq  reason: invalid class name and case insensitive filesystem */
public final class C08800xq {
    public final Context A00;
    public final C08110wa A01 = C08110wa.A00;
    public final ConcurrentHashMap<Class, Object> A02 = new ConcurrentHashMap<>();

    public final <T> AbstractC09150yk<T> A00(String str, Class<T> cls) {
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
                AnonymousClass0NK.A07("SystemServiceManager", "Cannot get system service after MAX_RETRIES: %s", cls.getName());
                return C09340zG.A00;
            }
        } while (obj == null);
        return new AnonymousClass0yR(obj);
    }

    public C08800xq(Context context) {
        this.A00 = context;
    }
}
