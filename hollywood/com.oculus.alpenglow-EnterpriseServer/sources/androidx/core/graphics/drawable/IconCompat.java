package androidx.core.graphics.drawable;

import X.AnonymousClass02D;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Shader;
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
import androidx.annotation.VisibleForTesting;
import androidx.versionedparcelable.CustomVersionedParcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationTargetException;

public class IconCompat extends CustomVersionedParcelable {
    public static final PorterDuff.Mode A09 = PorterDuff.Mode.SRC_IN;
    @RestrictTo({AnonymousClass02D.LIBRARY})
    public int A00 = 0;
    @RestrictTo({AnonymousClass02D.LIBRARY})
    public int A01 = 0;
    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public int A02 = -1;
    @RestrictTo({AnonymousClass02D.LIBRARY})
    public ColorStateList A03 = null;
    public PorterDuff.Mode A04 = A09;
    @RestrictTo({AnonymousClass02D.LIBRARY})
    public Parcelable A05 = null;
    public Object A06;
    @RestrictTo({AnonymousClass02D.LIBRARY})
    public String A07 = null;
    @RestrictTo({AnonymousClass02D.LIBRARY})
    public byte[] A08 = null;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({AnonymousClass02D.LIBRARY})
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
            return ((Integer) icon.getClass().getMethod("getResId", new Class[0]).invoke(icon, new Object[0])).intValue();
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
            return ((Integer) icon.getClass().getMethod("getType", new Class[0]).invoke(icon, new Object[0])).intValue();
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            Log.e("IconCompat", "Unable to get icon type " + icon, e);
            return -1;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x00b1  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00d3  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00dc  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00e0  */
    @androidx.annotation.NonNull
    @androidx.annotation.RequiresApi(23)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final android.graphics.drawable.Icon A03(@androidx.annotation.Nullable androidx.core.graphics.drawable.IconCompat r6, android.content.Context r7) {
        /*
        // Method dump skipped, instructions count: 268
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.graphics.drawable.IconCompat.A03(androidx.core.graphics.drawable.IconCompat, android.content.Context):android.graphics.drawable.Icon");
    }

    @NonNull
    private final Uri A04() {
        int i = this.A02;
        if (i == -1) {
            return A05((Icon) this.A06);
        }
        if (i == 4 || i == 6) {
            return Uri.parse((String) this.A06);
        }
        throw new IllegalStateException("called getUri() on " + this);
    }

    @Nullable
    @RequiresApi(23)
    public static Uri A05(@NonNull Icon icon) {
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

    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public static IconCompat A06(Resources resources, String str, @DrawableRes int i) {
        String str2;
        if (str == null) {
            str2 = "Package must not be null.";
        } else if (i != 0) {
            IconCompat iconCompat = new IconCompat(2);
            iconCompat.A00 = i;
            if (resources != null) {
                try {
                    iconCompat.A06 = resources.getResourceName(i);
                    return iconCompat;
                } catch (Resources.NotFoundException unused) {
                    throw new IllegalArgumentException("Icon resource cannot be found");
                }
            } else {
                iconCompat.A06 = str;
                return iconCompat;
            }
        } else {
            str2 = "Drawable resource ID must not be 0";
        }
        throw new IllegalArgumentException(str2);
    }

    @Nullable
    @RequiresApi(23)
    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public static IconCompat A07(@NonNull Icon icon) {
        String uri;
        if (icon != null) {
            int A012 = A01(icon);
            if (A012 == 2) {
                return A06(null, A09(icon), A00(icon));
            }
            int i = 4;
            if (A012 != 4) {
                i = 6;
                if (A012 != 6) {
                    IconCompat iconCompat = new IconCompat(-1);
                    iconCompat.A06 = icon;
                    return iconCompat;
                }
            }
            Uri A052 = A05(icon);
            if (A052 == null || (uri = A052.toString()) == null) {
                throw new IllegalArgumentException("Uri must not be null.");
            }
            IconCompat iconCompat2 = new IconCompat(i);
            iconCompat2.A06 = uri;
            return iconCompat2;
        }
        throw null;
    }

    @NonNull
    private final String A08() {
        int i = this.A02;
        if (i == -1) {
            return A09((Icon) this.A06);
        }
        if (i == 2) {
            return ((String) this.A06).split(":", -1)[0];
        }
        throw new IllegalStateException("called getResPackage() on " + this);
    }

    @Nullable
    @RequiresApi(23)
    public static String A09(@NonNull Icon icon) {
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
    public final int A0A() {
        int i = this.A02;
        if (i == -1) {
            return A00((Icon) this.A06);
        }
        if (i == 2) {
            return this.A00;
        }
        throw new IllegalStateException("called getResId() on " + this);
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
                str = "UNKNOWN";
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
                sb.append(A08());
                sb.append(" id=");
                sb.append(String.format("0x%08x", Integer.valueOf(A0A())));
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

    @VisibleForTesting
    public static Bitmap A02(Bitmap bitmap) {
        int min = (int) (((float) Math.min(bitmap.getWidth(), bitmap.getHeight())) * 0.6666667f);
        Bitmap createBitmap = Bitmap.createBitmap(min, min, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint(3);
        float f = 0.5f * ((float) min);
        paint.setColor(-16777216);
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        BitmapShader bitmapShader = new BitmapShader(bitmap, tileMode, tileMode);
        Matrix matrix = new Matrix();
        matrix.setTranslate((float) ((-(bitmap.getWidth() - min)) >> 1), (float) ((-(bitmap.getHeight() - min)) >> 1));
        bitmapShader.setLocalMatrix(matrix);
        paint.setShader(bitmapShader);
        canvas.drawCircle(f, f, 0.9166667f * f, paint);
        canvas.setBitmap(null);
        return createBitmap;
    }

    @RestrictTo({AnonymousClass02D.LIBRARY})
    public IconCompat() {
    }

    public IconCompat(int i) {
        this.A02 = i;
    }
}
