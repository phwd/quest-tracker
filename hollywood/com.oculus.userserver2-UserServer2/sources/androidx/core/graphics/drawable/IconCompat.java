package androidx.core.graphics.drawable;

import X.AnonymousClass2O;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import android.util.Log;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.versionedparcelable.CustomVersionedParcelable;
import com.facebook.acra.CrashTimeDataCollector;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationTargetException;

public class IconCompat extends CustomVersionedParcelable {
    public static final PorterDuff.Mode A09 = PorterDuff.Mode.SRC_IN;
    @RestrictTo({AnonymousClass2O.LIBRARY})
    public int A00 = 0;
    @RestrictTo({AnonymousClass2O.LIBRARY})
    public int A01 = 0;
    @RestrictTo({AnonymousClass2O.LIBRARY_GROUP_PREFIX})
    public int A02 = -1;
    @RestrictTo({AnonymousClass2O.LIBRARY})
    public ColorStateList A03 = null;
    public PorterDuff.Mode A04 = A09;
    @RestrictTo({AnonymousClass2O.LIBRARY})
    public Parcelable A05 = null;
    public Object A06;
    @RestrictTo({AnonymousClass2O.LIBRARY})
    public String A07 = null;
    @RestrictTo({AnonymousClass2O.LIBRARY})
    public byte[] A08 = null;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({AnonymousClass2O.LIBRARY})
    public @interface IconType {
    }

    @DrawableRes
    @IdRes
    @RequiresApi(23)
    public static int A00(@NonNull Icon icon) {
        if (Build.VERSION.SDK_INT >= 28) {
            return icon.getResId();
        }
        try {
            return ((Number) icon.getClass().getMethod("getResId", new Class[0]).invoke(icon, new Object[0])).intValue();
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            Log.e("IconCompat", "Unable to get icon resource", e);
            return 0;
        }
    }

    @RequiresApi(23)
    public static int A01(@NonNull Icon icon) {
        if (Build.VERSION.SDK_INT >= 28) {
            return icon.getType();
        }
        try {
            return ((Number) icon.getClass().getMethod("getType", new Class[0]).invoke(icon, new Object[0])).intValue();
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Unable to get icon type ");
            sb.append(icon);
            Log.e("IconCompat", sb.toString(), e);
            return -1;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x00ee  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00f7  */
    @androidx.annotation.NonNull
    @androidx.annotation.RequiresApi(23)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final android.graphics.drawable.Icon A02(@androidx.annotation.Nullable androidx.core.graphics.drawable.IconCompat r11) {
        /*
        // Method dump skipped, instructions count: 276
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.graphics.drawable.IconCompat.A02(androidx.core.graphics.drawable.IconCompat):android.graphics.drawable.Icon");
    }

    @Nullable
    @RequiresApi(23)
    public static Uri A03(@NonNull Icon icon) {
        if (Build.VERSION.SDK_INT >= 28) {
            return icon.getUri();
        }
        try {
            return (Uri) icon.getClass().getMethod("getUri", new Class[0]).invoke(icon, new Object[0]);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            Log.e("IconCompat", "Unable to get icon uri", e);
            return null;
        }
    }

    @Nullable
    @RequiresApi(23)
    @RestrictTo({AnonymousClass2O.LIBRARY_GROUP_PREFIX})
    public static IconCompat A04(@NonNull Icon icon) {
        String str;
        String obj;
        if (icon != null) {
            int A012 = A01(icon);
            if (A012 != 2) {
                int i = 4;
                if (A012 != 4) {
                    i = 6;
                    if (A012 != 6) {
                        IconCompat iconCompat = new IconCompat(-1);
                        iconCompat.A06 = icon;
                        return iconCompat;
                    }
                }
                Uri A032 = A03(icon);
                if (A032 == null || (obj = A032.toString()) == null) {
                    str = "Uri must not be null.";
                } else {
                    IconCompat iconCompat2 = new IconCompat(i);
                    iconCompat2.A06 = obj;
                    return iconCompat2;
                }
            } else {
                String A062 = A06(icon);
                int A002 = A00(icon);
                if (A062 == null) {
                    str = "Package must not be null.";
                } else if (A002 != 0) {
                    IconCompat iconCompat3 = new IconCompat(2);
                    iconCompat3.A00 = A002;
                    iconCompat3.A06 = A062;
                    return iconCompat3;
                } else {
                    str = "Drawable resource ID must not be 0";
                }
            }
            throw new IllegalArgumentException(str);
        }
        throw null;
    }

    @NonNull
    private final String A05() {
        int i = this.A02;
        if (i == -1) {
            return A06((Icon) this.A06);
        }
        if (i == 2) {
            return ((String) this.A06).split(":", -1)[0];
        }
        StringBuilder sb = new StringBuilder("called getResPackage() on ");
        sb.append(this);
        throw new IllegalStateException(sb.toString());
    }

    @Nullable
    @RequiresApi(23)
    public static String A06(@NonNull Icon icon) {
        if (Build.VERSION.SDK_INT >= 28) {
            return icon.getResPackage();
        }
        try {
            return (String) icon.getClass().getMethod("getResPackage", new Class[0]).invoke(icon, new Object[0]);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            Log.e("IconCompat", "Unable to get icon package", e);
            return null;
        }
    }

    @IdRes
    public final int A07() {
        int i = this.A02;
        if (i == -1) {
            return A00((Icon) this.A06);
        }
        if (i == 2) {
            return this.A00;
        }
        StringBuilder sb = new StringBuilder("called getResId() on ");
        sb.append(this);
        throw new IllegalStateException(sb.toString());
    }

    @NonNull
    public final String toString() {
        String str;
        int i = this.A02;
        if (i == -1) {
            return String.valueOf(this.A06);
        }
        StringBuilder sb = new StringBuilder("Icon(typ=");
        switch (i) {
            case 1:
                str = "BITMAP";
                break;
            case 2:
                str = "RESOURCE";
                break;
            case 3:
                str = "DATA";
                break;
            case 4:
                str = "URI";
                break;
            case 5:
                str = "BITMAP_MASKABLE";
                break;
            case 6:
                str = "URI_MASKABLE";
                break;
            default:
                str = CrashTimeDataCollector.ANDROID_RUNTIME_UNKNOWN;
                break;
        }
        sb.append(str);
        switch (i) {
            case 1:
            case 5:
                sb.append(" size=");
                sb.append(((Bitmap) this.A06).getWidth());
                sb.append("x");
                sb.append(((Bitmap) this.A06).getHeight());
                break;
            case 2:
                sb.append(" pkg=");
                sb.append(A05());
                sb.append(" id=");
                sb.append(String.format("0x%08x", Integer.valueOf(A07())));
                break;
            case 3:
                sb.append(" len=");
                sb.append(this.A00);
                int i2 = this.A01;
                if (i2 != 0) {
                    sb.append(" off=");
                    sb.append(i2);
                    break;
                }
                break;
            case 4:
            case 6:
                sb.append(" uri=");
                sb.append(this.A06);
                break;
        }
        ColorStateList colorStateList = this.A03;
        if (colorStateList != null) {
            sb.append(" tint=");
            sb.append(colorStateList);
        }
        PorterDuff.Mode mode = this.A04;
        if (mode != A09) {
            sb.append(" mode=");
            sb.append(mode);
        }
        sb.append(")");
        return sb.toString();
    }

    @RestrictTo({AnonymousClass2O.LIBRARY})
    public IconCompat() {
    }

    public IconCompat(int i) {
        this.A02 = i;
    }
}
