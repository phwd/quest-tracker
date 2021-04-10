package X;

import androidx.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

/* renamed from: X.1lF  reason: invalid class name */
public abstract class AnonymousClass1lF extends Exception {
    public final int mErrorCode;
    @Nullable
    public Map<String, String> mExtras;

    public final void A00(String str, String str2) {
        Map map = this.mExtras;
        if (map == null) {
            map = new HashMap(1);
            this.mExtras = map;
        }
        map.put(str, str2);
    }

    public final void A01(@Nullable Map<String, String> map) {
        if (map != null && !map.isEmpty()) {
            Map map2 = this.mExtras;
            if (map2 == null) {
                map2 = new HashMap(map.size());
                this.mExtras = map2;
            }
            map2.putAll(map);
        }
    }

    public final String getMessage() {
        Object[] objArr;
        String str;
        if (super.getMessage() != null) {
            objArr = new Object[]{super.getMessage(), Integer.valueOf(this.mErrorCode)};
            str = "%s [ErrorCode=%d]";
        } else {
            objArr = new Object[]{Integer.valueOf(this.mErrorCode)};
            str = "[ErrorCode=%d]";
        }
        return String.format(null, str, objArr);
    }

    public AnonymousClass1lF() {
        this.mErrorCode = 1;
    }

    public AnonymousClass1lF(int i, Throwable th) {
        super(th.getMessage(), th);
        this.mErrorCode = i;
    }

    public AnonymousClass1lF(int i, String str) {
        super(str);
        this.mErrorCode = i;
    }

    public AnonymousClass1lF(int i, String str, @Nullable Throwable th) {
        super(str, th);
        this.mErrorCode = i;
    }
}
