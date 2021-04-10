package androidx.core.graphics.drawable;

import X.AnonymousClass02C;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.versionedparcelable.CustomVersionedParcelable;
import com.facebook.GraphRequest;
import com.oculus.horizon.abuse_prevention.FileReceiver;
import com.oculus.horizon.logging.LoggingKeys;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationTargetException;

public class IconCompat extends CustomVersionedParcelable {
    public static final PorterDuff.Mode A09 = PorterDuff.Mode.SRC_IN;
    @RestrictTo({AnonymousClass02C.LIBRARY})
    public int A00 = 0;
    @RestrictTo({AnonymousClass02C.LIBRARY})
    public int A01 = 0;
    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public int A02 = -1;
    @RestrictTo({AnonymousClass02C.LIBRARY})
    public ColorStateList A03 = null;
    public PorterDuff.Mode A04 = A09;
    @RestrictTo({AnonymousClass02C.LIBRARY})
    public Parcelable A05 = null;
    public Object A06;
    @RestrictTo({AnonymousClass02C.LIBRARY})
    public String A07 = null;
    @RestrictTo({AnonymousClass02C.LIBRARY})
    public byte[] A08 = null;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({AnonymousClass02C.LIBRARY})
    public @interface IconType {
    }

    @NonNull
    @RequiresApi(23)
    @Deprecated
    public final Icon A0G() {
        return A04(this, null);
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

    public static Resources A02(Context context, String str) {
        if ("android".equals(str)) {
            return Resources.getSystem();
        }
        PackageManager packageManager = context.getPackageManager();
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, 8192);
            if (applicationInfo != null) {
                return packageManager.getResourcesForApplication(applicationInfo);
            }
            return null;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("IconCompat", String.format("Unable to find pkg=%s for icon", str), e);
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x00a4  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00ad  */
    @androidx.annotation.NonNull
    @androidx.annotation.RequiresApi(23)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final android.graphics.drawable.Icon A04(@androidx.annotation.Nullable androidx.core.graphics.drawable.IconCompat r3, android.content.Context r4) {
        /*
        // Method dump skipped, instructions count: 198
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.graphics.drawable.IconCompat.A04(androidx.core.graphics.drawable.IconCompat, android.content.Context):android.graphics.drawable.Icon");
    }

    @NonNull
    private final Uri A05() {
        int i = this.A02;
        if (i == -1 && Build.VERSION.SDK_INT >= 23) {
            return A06((Icon) this.A06);
        }
        if (i == 4 || i == 6) {
            return Uri.parse((String) this.A06);
        }
        StringBuilder sb = new StringBuilder("called getUri() on ");
        sb.append(this);
        throw new IllegalStateException(sb.toString());
    }

    @Nullable
    @RequiresApi(23)
    public static Uri A06(@NonNull Icon icon) {
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

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public static IconCompat A07(Resources resources, String str, @DrawableRes int i) {
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
    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public static IconCompat A08(@NonNull Icon icon) {
        String obj;
        if (icon != null) {
            int A012 = A01(icon);
            if (A012 == 2) {
                return A07(null, A0C(icon), A00(icon));
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
            Uri A062 = A06(icon);
            if (A062 == null || (obj = A062.toString()) == null) {
                throw new IllegalArgumentException("Uri must not be null.");
            }
            IconCompat iconCompat2 = new IconCompat(i);
            iconCompat2.A06 = obj;
            return iconCompat2;
        }
        throw null;
    }

    @NonNull
    private final String A0B() {
        int i = this.A02;
        if (i == -1 && Build.VERSION.SDK_INT >= 23) {
            return A0C((Icon) this.A06);
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
    public static String A0C(@NonNull Icon icon) {
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
    public final int A0D() {
        int i = this.A02;
        if (i == -1 && Build.VERSION.SDK_INT >= 23) {
            return A00((Icon) this.A06);
        }
        if (i == 2) {
            return this.A00;
        }
        StringBuilder sb = new StringBuilder("called getResId() on ");
        sb.append(this);
        throw new IllegalStateException(sb.toString());
    }

    public final int A0E() {
        int i = this.A02;
        if (i != -1 || Build.VERSION.SDK_INT < 23) {
            return i;
        }
        return A01((Icon) this.A06);
    }

    @Nullable
    public final Drawable A0F(@NonNull Context context) {
        Resources resources;
        Bitmap bitmap;
        Resources resources2;
        Bitmap bitmap2;
        if (this.A02 == 2) {
            String str = (String) this.A06;
            if (str.contains(":")) {
                String str2 = str.split(":", -1)[1];
                String str3 = str2.split("/", -1)[0];
                String str4 = str2.split("/", -1)[1];
                String str5 = str.split(":", -1)[0];
                int identifier = A02(context, str5).getIdentifier(str4, str3, str5);
                if (this.A00 != identifier) {
                    this.A00 = identifier;
                }
            }
        }
        int i = Build.VERSION.SDK_INT;
        if (i >= 23) {
            return A04(this, context).loadDrawable(context);
        }
        Drawable drawable = null;
        switch (this.A02) {
            case 1:
                resources = context.getResources();
                bitmap = (Bitmap) this.A06;
                drawable = new BitmapDrawable(resources, bitmap);
                break;
            case 2:
                String A0B = A0B();
                if (TextUtils.isEmpty(A0B)) {
                    A0B = context.getPackageName();
                }
                try {
                    drawable = A02(context, A0B).getDrawable(this.A00, context.getTheme());
                    break;
                } catch (RuntimeException e) {
                    Log.e("IconCompat", String.format("Unable to load resource 0x%08x from pkg=%s", Integer.valueOf(this.A00), this.A06), e);
                    break;
                }
            case 3:
                resources = context.getResources();
                bitmap = BitmapFactory.decodeByteArray((byte[]) this.A06, this.A00, this.A01);
                drawable = new BitmapDrawable(resources, bitmap);
                break;
            case 4:
                InputStream A0A = A0A(context);
                if (A0A != null) {
                    resources2 = context.getResources();
                    bitmap2 = BitmapFactory.decodeStream(A0A);
                    drawable = new BitmapDrawable(resources2, bitmap2);
                    break;
                }
                break;
            case 5:
                resources = context.getResources();
                bitmap = A03((Bitmap) this.A06);
                drawable = new BitmapDrawable(resources, bitmap);
                break;
            case 6:
                InputStream A0A2 = A0A(context);
                if (A0A2 != null) {
                    resources2 = context.getResources();
                    Bitmap decodeStream = BitmapFactory.decodeStream(A0A2);
                    if (i < 26) {
                        bitmap2 = A03(decodeStream);
                        drawable = new BitmapDrawable(resources2, bitmap2);
                        break;
                    } else {
                        drawable = new AdaptiveIconDrawable(null, new BitmapDrawable(resources2, decodeStream));
                        break;
                    }
                }
                break;
        }
        if (drawable == null) {
            return drawable;
        }
        if (this.A03 == null && this.A04 == A09) {
            return drawable;
        }
        drawable.mutate();
        drawable.setTintList(this.A03);
        drawable.setTintMode(this.A04);
        return drawable;
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
                str = FileReceiver.URI_KEY;
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
                sb.append(A0B());
                sb.append(" id=");
                sb.append(String.format("0x%08x", Integer.valueOf(A0D())));
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
    public static Bitmap A03(Bitmap bitmap) {
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

    @Nullable
    @RequiresApi(23)
    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public static IconCompat A09(@NonNull Icon icon) {
        if (A01(icon) == 2 && A00(icon) == 0) {
            return null;
        }
        return A08(icon);
    }

    private InputStream A0A(Context context) {
        Throwable e;
        String str;
        Uri A052 = A05();
        String scheme = A052.getScheme();
        if (LoggingKeys.REFERRER_CONTENT.equals(scheme) || GraphRequest.ATTACHMENT_FILENAME_PREFIX.equals(scheme)) {
            try {
                return context.getContentResolver().openInputStream(A052);
            } catch (Exception e2) {
                e = e2;
                str = "Unable to load image from URI: ";
                StringBuilder sb = new StringBuilder(str);
                sb.append(A052);
                Log.w("IconCompat", sb.toString(), e);
                return null;
            }
        } else {
            try {
                return new FileInputStream(new File((String) this.A06));
            } catch (FileNotFoundException e3) {
                e = e3;
                str = "Unable to load image from path: ";
                StringBuilder sb2 = new StringBuilder(str);
                sb2.append(A052);
                Log.w("IconCompat", sb2.toString(), e);
                return null;
            }
        }
    }

    @RestrictTo({AnonymousClass02C.LIBRARY})
    public IconCompat() {
    }

    public IconCompat(int i) {
        this.A02 = i;
    }
}
