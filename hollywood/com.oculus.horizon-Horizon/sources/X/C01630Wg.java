package X;

import android.content.Context;
import com.facebook.infer.annotation.Nullsafe;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0Wg  reason: invalid class name and case insensitive filesystem */
public final class C01630Wg {
    public final Context A00;
    public final AnonymousClass0Wc A01 = AnonymousClass0Wc.A00;
    public final ConcurrentHashMap<Class, Object> A02 = new ConcurrentHashMap<>();

    public final <T> AnonymousClass0W8<T> A00(String str, Class<T> cls) {
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
                obj = this.A01.A00(this.A00, str, cls);
                if (obj != null) {
                    concurrentHashMap.putIfAbsent(cls, obj);
                }
                i = i2;
            } else {
                AnonymousClass0NO.A0F("SystemServiceManager", "Cannot get system service after MAX_RETRIES: %s", cls.getName());
                return C06530na.A00;
            }
        } while (obj == null);
        return new AnonymousClass0nZ(obj);
    }

    public C01630Wg(Context context) {
        this.A00 = context;
    }
}
