package X;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* renamed from: X.1eW  reason: invalid class name and case insensitive filesystem */
public final class C08381eW {
    public static final char[] A00 = "0123456789abcdef".toCharArray();
    public static final char[] A01 = new char[64];

    public static int A00(int i, int i2, @Nullable Bitmap.Config config) {
        int i3 = i * i2;
        if (config == null) {
            config = Bitmap.Config.ARGB_8888;
        }
        int i4 = AnonymousClass1hC.A00[config.ordinal()];
        int i5 = 4;
        if (i4 == 1) {
            i5 = 1;
        } else if (i4 == 2 || i4 == 3) {
            i5 = 2;
        } else if (i4 == 4) {
            i5 = 8;
        }
        return i3 * i5;
    }

    public static int A02(@Nullable Object obj, int i) {
        int hashCode;
        if (obj == null) {
            hashCode = 0;
        } else {
            hashCode = obj.hashCode();
        }
        return (i * 31) + hashCode;
    }

    public static boolean A06(int i, int i2) {
        if (i <= 0 && i != Integer.MIN_VALUE) {
            return false;
        }
        if (i2 > 0 || i2 == Integer.MIN_VALUE) {
            return true;
        }
        return false;
    }

    public static boolean A07(@Nullable Object obj, @Nullable Object obj2) {
        if (obj != null) {
            return obj.equals(obj2);
        }
        if (obj2 == null) {
            return true;
        }
        return false;
    }

    @TargetApi(19)
    public static int A01(@NonNull Bitmap bitmap) {
        if (!bitmap.isRecycled()) {
            try {
                return bitmap.getAllocationByteCount();
            } catch (NullPointerException unused) {
                return bitmap.getHeight() * bitmap.getRowBytes();
            }
        } else {
            StringBuilder sb = new StringBuilder("Cannot obtain size for recycled Bitmap: ");
            sb.append(bitmap);
            sb.append("[");
            sb.append(bitmap.getWidth());
            sb.append("x");
            sb.append(bitmap.getHeight());
            sb.append("] ");
            sb.append(bitmap.getConfig());
            throw new IllegalStateException(sb.toString());
        }
    }

    @NonNull
    public static <T> List<T> A03(@NonNull Collection<T> collection) {
        ArrayList arrayList = new ArrayList(collection.size());
        for (T t : collection) {
            if (t != null) {
                arrayList.add(t);
            }
        }
        return arrayList;
    }

    public static void A04() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalArgumentException("You must call this method on the main thread");
        }
    }

    public static boolean A05() {
        boolean z = false;
        if (Looper.myLooper() == Looper.getMainLooper()) {
            z = true;
        }
        return !z;
    }
}
