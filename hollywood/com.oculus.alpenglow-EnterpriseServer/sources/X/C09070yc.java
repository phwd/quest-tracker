package X;

import android.util.SparseArray;
import com.facebook.infer.annotation.Nullsafe;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0yc  reason: invalid class name and case insensitive filesystem */
public final class C09070yc {
    public static final SparseArray<String> A00 = new AnonymousClass0yZ();
    public static final Map<String, Integer> A01 = new HashMap();

    static {
        int i = 0;
        while (true) {
            SparseArray<String> sparseArray = A00;
            if (i < sparseArray.size()) {
                A01.put(sparseArray.valueAt(i), Integer.valueOf(sparseArray.keyAt(i)));
                i++;
            } else {
                return;
            }
        }
    }

    @Nullable
    public static String A00(String str) {
        if (str.startsWith("/")) {
            return str;
        }
        try {
            return A00.get(Integer.parseInt(str));
        } catch (NumberFormatException unused) {
            return null;
        }
    }
}
