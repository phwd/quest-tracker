package androidx.core.util;

import android.support.v4.media.MediaDescriptionCompat;
import android.text.TextUtils;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import java.util.Collection;
import java.util.Locale;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class Preconditions {
    public static void checkArgument(boolean z) {
        if (!z) {
            throw new IllegalArgumentException();
        }
    }

    public static void checkArgument(boolean z, Object obj) {
        if (!z) {
            throw new IllegalArgumentException(String.valueOf(obj));
        }
    }

    @NonNull
    public static <T extends CharSequence> T checkStringNotEmpty(T t) {
        if (!TextUtils.isEmpty(t)) {
            return t;
        }
        throw new IllegalArgumentException();
    }

    @NonNull
    public static <T extends CharSequence> T checkStringNotEmpty(T t, Object obj) {
        if (!TextUtils.isEmpty(t)) {
            return t;
        }
        throw new IllegalArgumentException(String.valueOf(obj));
    }

    @NonNull
    public static <T> T checkNotNull(T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException();
    }

    @NonNull
    public static <T> T checkNotNull(T t, Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(String.valueOf(obj));
    }

    public static void checkState(boolean z, String str) {
        if (!z) {
            throw new IllegalStateException(str);
        }
    }

    public static void checkState(boolean z) {
        checkState(z, null);
    }

    public static int checkFlagsArgument(int i, int i2) {
        if ((i & i2) == i) {
            return i;
        }
        throw new IllegalArgumentException("Requested flags 0x" + Integer.toHexString(i) + ", but only 0x" + Integer.toHexString(i2) + " are allowed");
    }

    @IntRange(from = MediaDescriptionCompat.BT_FOLDER_TYPE_MIXED)
    public static int checkArgumentNonnegative(int i, String str) {
        if (i >= 0) {
            return i;
        }
        throw new IllegalArgumentException(str);
    }

    @IntRange(from = MediaDescriptionCompat.BT_FOLDER_TYPE_MIXED)
    public static int checkArgumentNonnegative(int i) {
        if (i >= 0) {
            return i;
        }
        throw new IllegalArgumentException();
    }

    public static long checkArgumentNonnegative(long j) {
        if (j >= 0) {
            return j;
        }
        throw new IllegalArgumentException();
    }

    public static long checkArgumentNonnegative(long j, String str) {
        if (j >= 0) {
            return j;
        }
        throw new IllegalArgumentException(str);
    }

    public static int checkArgumentPositive(int i, String str) {
        if (i > 0) {
            return i;
        }
        throw new IllegalArgumentException(str);
    }

    public static float checkArgumentFinite(float f, String str) {
        if (Float.isNaN(f)) {
            throw new IllegalArgumentException(str + " must not be NaN");
        } else if (!Float.isInfinite(f)) {
            return f;
        } else {
            throw new IllegalArgumentException(str + " must not be infinite");
        }
    }

    public static float checkArgumentInRange(float f, float f2, float f3, String str) {
        if (Float.isNaN(f)) {
            throw new IllegalArgumentException(str + " must not be NaN");
        } else if (f < f2) {
            throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%f, %f] (too low)", str, Float.valueOf(f2), Float.valueOf(f3)));
        } else if (f <= f3) {
            return f;
        } else {
            throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%f, %f] (too high)", str, Float.valueOf(f2), Float.valueOf(f3)));
        }
    }

    public static int checkArgumentInRange(int i, int i2, int i3, String str) {
        if (i < i2) {
            throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%d, %d] (too low)", str, Integer.valueOf(i2), Integer.valueOf(i3)));
        } else if (i <= i3) {
            return i;
        } else {
            throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%d, %d] (too high)", str, Integer.valueOf(i2), Integer.valueOf(i3)));
        }
    }

    public static long checkArgumentInRange(long j, long j2, long j3, String str) {
        if (j < j2) {
            throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%d, %d] (too low)", str, Long.valueOf(j2), Long.valueOf(j3)));
        } else if (j <= j3) {
            return j;
        } else {
            throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%d, %d] (too high)", str, Long.valueOf(j2), Long.valueOf(j3)));
        }
    }

    public static <T> T[] checkArrayElementsNotNull(T[] tArr, String str) {
        if (tArr != null) {
            for (int i = 0; i < tArr.length; i++) {
                if (tArr[i] == null) {
                    throw new NullPointerException(String.format(Locale.US, "%s[%d] must not be null", str, Integer.valueOf(i)));
                }
            }
            return tArr;
        }
        throw new NullPointerException(str + " must not be null");
    }

    @NonNull
    public static <C extends Collection<T>, T> C checkCollectionElementsNotNull(C c, String str) {
        if (c != null) {
            long j = 0;
            for (Object obj : c) {
                if (obj != null) {
                    j++;
                } else {
                    throw new NullPointerException(String.format(Locale.US, "%s[%d] must not be null", str, Long.valueOf(j)));
                }
            }
            return c;
        }
        throw new NullPointerException(str + " must not be null");
    }

    public static <T> Collection<T> checkCollectionNotEmpty(Collection<T> collection, String str) {
        if (collection == null) {
            throw new NullPointerException(str + " must not be null");
        } else if (!collection.isEmpty()) {
            return collection;
        } else {
            throw new IllegalArgumentException(str + " is empty");
        }
    }

    public static float[] checkArrayElementsInRange(float[] fArr, float f, float f2, String str) {
        checkNotNull(fArr, str + " must not be null");
        for (int i = 0; i < fArr.length; i++) {
            float f3 = fArr[i];
            if (Float.isNaN(f3)) {
                throw new IllegalArgumentException(str + "[" + i + "] must not be NaN");
            } else if (f3 < f) {
                throw new IllegalArgumentException(String.format(Locale.US, "%s[%d] is out of range of [%f, %f] (too low)", str, Integer.valueOf(i), Float.valueOf(f), Float.valueOf(f2)));
            } else if (f3 > f2) {
                throw new IllegalArgumentException(String.format(Locale.US, "%s[%d] is out of range of [%f, %f] (too high)", str, Integer.valueOf(i), Float.valueOf(f), Float.valueOf(f2)));
            }
        }
        return fArr;
    }

    private Preconditions() {
    }
}
