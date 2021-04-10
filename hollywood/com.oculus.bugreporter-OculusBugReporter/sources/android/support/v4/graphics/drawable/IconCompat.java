package android.support.v4.graphics.drawable;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
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
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.os.BuildCompat;
import android.support.v4.util.Preconditions;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.util.Log;
import androidx.versionedparcelable.CustomVersionedParcelable;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;

public class IconCompat extends CustomVersionedParcelable {
    private static final float ADAPTIVE_ICON_INSET_FACTOR = 0.25f;
    private static final int AMBIENT_SHADOW_ALPHA = 30;
    private static final float BLUR_FACTOR = 0.010416667f;
    static final PorterDuff.Mode DEFAULT_TINT_MODE = PorterDuff.Mode.SRC_IN;
    private static final float DEFAULT_VIEW_PORT_SCALE = 0.6666667f;
    private static final String EXTRA_INT1 = "int1";
    private static final String EXTRA_INT2 = "int2";
    private static final String EXTRA_OBJ = "obj";
    private static final String EXTRA_TINT_LIST = "tint_list";
    private static final String EXTRA_TINT_MODE = "tint_mode";
    private static final String EXTRA_TYPE = "type";
    private static final float ICON_DIAMETER_FACTOR = 0.9166667f;
    private static final int KEY_SHADOW_ALPHA = 61;
    private static final float KEY_SHADOW_OFFSET_FACTOR = 0.020833334f;
    private static final String TAG = "IconCompat";
    public static final int TYPE_UNKNOWN = -1;
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public byte[] mData;
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public int mInt1;
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public int mInt2;
    Object mObj1;
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public Parcelable mParcelable;
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public ColorStateList mTintList = null;
    PorterDuff.Mode mTintMode = DEFAULT_TINT_MODE;
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public String mTintModeStr;
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public int mType;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public @interface IconType {
    }

    public static IconCompat createWithResource(Context context, @DrawableRes int resId) {
        if (context != null) {
            IconCompat rep = new IconCompat(2);
            rep.mInt1 = resId;
            rep.mObj1 = context.getPackageName();
            return rep;
        }
        throw new IllegalArgumentException("Context must not be null.");
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static IconCompat createWithResource(String pkg, @DrawableRes int resId) {
        if (pkg != null) {
            IconCompat rep = new IconCompat(2);
            rep.mInt1 = resId;
            rep.mObj1 = pkg;
            return rep;
        }
        throw new IllegalArgumentException("Package must not be null.");
    }

    public static IconCompat createWithBitmap(Bitmap bits) {
        if (bits != null) {
            IconCompat rep = new IconCompat(1);
            rep.mObj1 = bits;
            return rep;
        }
        throw new IllegalArgumentException("Bitmap must not be null.");
    }

    public static IconCompat createWithAdaptiveBitmap(Bitmap bits) {
        if (bits != null) {
            IconCompat rep = new IconCompat(5);
            rep.mObj1 = bits;
            return rep;
        }
        throw new IllegalArgumentException("Bitmap must not be null.");
    }

    public static IconCompat createWithData(byte[] data, int offset, int length) {
        if (data != null) {
            IconCompat rep = new IconCompat(3);
            rep.mObj1 = data;
            rep.mInt1 = offset;
            rep.mInt2 = length;
            return rep;
        }
        throw new IllegalArgumentException("Data must not be null.");
    }

    public static IconCompat createWithContentUri(String uri) {
        if (uri != null) {
            IconCompat rep = new IconCompat(4);
            rep.mObj1 = uri;
            return rep;
        }
        throw new IllegalArgumentException("Uri must not be null.");
    }

    public static IconCompat createWithContentUri(Uri uri) {
        if (uri != null) {
            return createWithContentUri(uri.toString());
        }
        throw new IllegalArgumentException("Uri must not be null.");
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public IconCompat() {
    }

    private IconCompat(int mType2) {
        this.mType = mType2;
    }

    public int getType() {
        if (this.mType != -1 || Build.VERSION.SDK_INT < 23) {
            return this.mType;
        }
        return getType((Icon) this.mObj1);
    }

    @NonNull
    public String getResPackage() {
        if (this.mType == -1 && Build.VERSION.SDK_INT >= 23) {
            return getResPackage((Icon) this.mObj1);
        }
        if (this.mType == 2) {
            return (String) this.mObj1;
        }
        throw new IllegalStateException("called getResPackage() on " + ((Object) this));
    }

    @IdRes
    public int getResId() {
        if (this.mType == -1 && Build.VERSION.SDK_INT >= 23) {
            return getResId((Icon) this.mObj1);
        }
        if (this.mType == 2) {
            return this.mInt1;
        }
        throw new IllegalStateException("called getResId() on " + ((Object) this));
    }

    @NonNull
    public Uri getUri() {
        if (this.mType != -1 || Build.VERSION.SDK_INT < 23) {
            return Uri.parse((String) this.mObj1);
        }
        return getUri((Icon) this.mObj1);
    }

    public IconCompat setTint(@ColorInt int tint) {
        return setTintList(ColorStateList.valueOf(tint));
    }

    public IconCompat setTintList(ColorStateList tintList) {
        this.mTintList = tintList;
        return this;
    }

    public IconCompat setTintMode(PorterDuff.Mode mode) {
        this.mTintMode = mode;
        return this;
    }

    @RequiresApi(23)
    public Icon toIcon() {
        Icon icon;
        int i = this.mType;
        if (i == -1) {
            return (Icon) this.mObj1;
        }
        if (i == 1) {
            icon = Icon.createWithBitmap((Bitmap) this.mObj1);
        } else if (i == 2) {
            icon = Icon.createWithResource((String) this.mObj1, this.mInt1);
        } else if (i == 3) {
            icon = Icon.createWithData((byte[]) this.mObj1, this.mInt1, this.mInt2);
        } else if (i == 4) {
            icon = Icon.createWithContentUri((String) this.mObj1);
        } else if (i != 5) {
            throw new IllegalArgumentException("Unknown type");
        } else if (Build.VERSION.SDK_INT >= 26) {
            icon = Icon.createWithAdaptiveBitmap((Bitmap) this.mObj1);
        } else {
            icon = Icon.createWithBitmap(createLegacyIconFromAdaptiveIcon((Bitmap) this.mObj1, false));
        }
        ColorStateList colorStateList = this.mTintList;
        if (colorStateList != null) {
            icon.setTintList(colorStateList);
        }
        PorterDuff.Mode mode = this.mTintMode;
        if (mode != DEFAULT_TINT_MODE) {
            icon.setTintMode(mode);
        }
        return icon;
    }

    public Drawable loadDrawable(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            return toIcon().loadDrawable(context);
        }
        Drawable result = loadDrawableInner(context);
        if (!(result == null || (this.mTintList == null && this.mTintMode == DEFAULT_TINT_MODE))) {
            result.mutate();
            DrawableCompat.setTintList(result, this.mTintList);
            DrawableCompat.setTintMode(result, this.mTintMode);
        }
        return result;
    }

    private Drawable loadDrawableInner(Context context) {
        Resources res;
        int i = this.mType;
        if (i == 1) {
            return new BitmapDrawable(context.getResources(), (Bitmap) this.mObj1);
        }
        if (i == 2) {
            String resPackage = (String) this.mObj1;
            if (TextUtils.isEmpty(resPackage)) {
                resPackage = context.getPackageName();
            }
            if ("android".equals(resPackage)) {
                res = Resources.getSystem();
            } else {
                PackageManager pm = context.getPackageManager();
                try {
                    ApplicationInfo ai = pm.getApplicationInfo(resPackage, 8192);
                    if (ai == null) {
                        return null;
                    }
                    res = pm.getResourcesForApplication(ai);
                } catch (PackageManager.NameNotFoundException e) {
                    Log.e(TAG, String.format("Unable to find pkg=%s for icon %s", resPackage, this), e);
                    return null;
                }
            }
            try {
                return ResourcesCompat.getDrawable(res, this.mInt1, context.getTheme());
            } catch (RuntimeException e2) {
                Log.e(TAG, String.format("Unable to load resource 0x%08x from pkg=%s", Integer.valueOf(this.mInt1), this.mObj1), e2);
                return null;
            }
        } else if (i == 3) {
            return new BitmapDrawable(context.getResources(), BitmapFactory.decodeByteArray((byte[]) this.mObj1, this.mInt1, this.mInt2));
        } else {
            if (i == 4) {
                Uri uri = Uri.parse((String) this.mObj1);
                String scheme = uri.getScheme();
                InputStream is = null;
                if ("content".equals(scheme) || "file".equals(scheme)) {
                    try {
                        is = context.getContentResolver().openInputStream(uri);
                    } catch (Exception e3) {
                        Log.w(TAG, "Unable to load image from URI: " + ((Object) uri), e3);
                    }
                } else {
                    try {
                        is = new FileInputStream(new File((String) this.mObj1));
                    } catch (FileNotFoundException e4) {
                        Log.w(TAG, "Unable to load image from path: " + ((Object) uri), e4);
                    }
                }
                if (is != null) {
                    return new BitmapDrawable(context.getResources(), BitmapFactory.decodeStream(is));
                }
                return null;
            } else if (i != 5) {
                return null;
            } else {
                return new BitmapDrawable(context.getResources(), createLegacyIconFromAdaptiveIcon((Bitmap) this.mObj1, false));
            }
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void addToShortcutIntent(@NonNull Intent outIntent, @Nullable Drawable badge, @NonNull Context c) {
        Bitmap icon;
        Bitmap icon2;
        int i = this.mType;
        if (i == 1) {
            icon = (Bitmap) this.mObj1;
            if (badge != null) {
                icon = icon.copy(icon.getConfig(), true);
            }
        } else if (i == 2) {
            try {
                Context context = c.createPackageContext((String) this.mObj1, 0);
                if (badge == null) {
                    outIntent.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(context, this.mInt1));
                    return;
                }
                Drawable dr = ContextCompat.getDrawable(context, this.mInt1);
                if (dr.getIntrinsicWidth() > 0) {
                    if (dr.getIntrinsicHeight() > 0) {
                        icon2 = Bitmap.createBitmap(dr.getIntrinsicWidth(), dr.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                        dr.setBounds(0, 0, icon2.getWidth(), icon2.getHeight());
                        dr.draw(new Canvas(icon2));
                        icon = icon2;
                    }
                }
                int size = ((ActivityManager) context.getSystemService("activity")).getLauncherLargeIconSize();
                icon2 = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
                dr.setBounds(0, 0, icon2.getWidth(), icon2.getHeight());
                dr.draw(new Canvas(icon2));
                icon = icon2;
            } catch (PackageManager.NameNotFoundException e) {
                throw new IllegalArgumentException("Can't find package " + this.mObj1, e);
            }
        } else if (i == 5) {
            icon = createLegacyIconFromAdaptiveIcon((Bitmap) this.mObj1, true);
        } else {
            throw new IllegalArgumentException("Icon type not supported for intent shortcuts");
        }
        if (badge != null) {
            int w = icon.getWidth();
            int h = icon.getHeight();
            badge.setBounds(w / 2, h / 2, w, h);
            badge.draw(new Canvas(icon));
        }
        outIntent.putExtra("android.intent.extra.shortcut.ICON", icon);
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        int i = this.mType;
        if (i != -1) {
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        bundle.putByteArray(EXTRA_OBJ, (byte[]) this.mObj1);
                    } else if (i != 4) {
                        if (i != 5) {
                            throw new IllegalArgumentException("Invalid icon");
                        }
                    }
                }
                bundle.putString(EXTRA_OBJ, (String) this.mObj1);
            }
            bundle.putParcelable(EXTRA_OBJ, (Bitmap) this.mObj1);
        } else {
            bundle.putParcelable(EXTRA_OBJ, (Parcelable) this.mObj1);
        }
        bundle.putInt(EXTRA_TYPE, this.mType);
        bundle.putInt(EXTRA_INT1, this.mInt1);
        bundle.putInt(EXTRA_INT2, this.mInt2);
        ColorStateList colorStateList = this.mTintList;
        if (colorStateList != null) {
            bundle.putParcelable(EXTRA_TINT_LIST, colorStateList);
        }
        PorterDuff.Mode mode = this.mTintMode;
        if (mode != DEFAULT_TINT_MODE) {
            bundle.putString(EXTRA_TINT_MODE, mode.name());
        }
        return bundle;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002c, code lost:
        if (r1 != 5) goto L_0x009c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x00a0  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00b0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String toString() {
        /*
        // Method dump skipped, instructions count: 196
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.drawable.IconCompat.toString():java.lang.String");
    }

    @Override // androidx.versionedparcelable.CustomVersionedParcelable
    public void onPreParceling(boolean isStream) {
        this.mTintModeStr = this.mTintMode.name();
        int i = this.mType;
        if (i != -1) {
            if (i != 1) {
                if (i == 2) {
                    this.mData = ((String) this.mObj1).getBytes(Charset.forName("UTF-16"));
                    return;
                } else if (i == 3) {
                    this.mData = (byte[]) this.mObj1;
                    return;
                } else if (i == 4) {
                    this.mData = this.mObj1.toString().getBytes(Charset.forName("UTF-16"));
                    return;
                } else if (i != 5) {
                    return;
                }
            }
            if (isStream) {
                ByteArrayOutputStream data = new ByteArrayOutputStream();
                ((Bitmap) this.mObj1).compress(Bitmap.CompressFormat.PNG, 90, data);
                this.mData = data.toByteArray();
                return;
            }
            this.mParcelable = (Parcelable) this.mObj1;
        } else if (!isStream) {
            this.mParcelable = (Parcelable) this.mObj1;
        } else {
            throw new IllegalArgumentException("Can't serialize Icon created with IconCompat#createFromIcon");
        }
    }

    @Override // androidx.versionedparcelable.CustomVersionedParcelable
    public void onPostParceling() {
        this.mTintMode = PorterDuff.Mode.valueOf(this.mTintModeStr);
        int i = this.mType;
        if (i != -1) {
            if (i != 1) {
                if (i == 2) {
                    this.mObj1 = new String(this.mData, Charset.forName("UTF-16"));
                    return;
                } else if (i == 3) {
                    this.mObj1 = this.mData;
                    return;
                } else if (i == 4) {
                    this.mObj1 = Uri.parse(new String(this.mData, Charset.forName("UTF-16")));
                    return;
                } else if (i != 5) {
                    return;
                }
            }
            Parcelable parcelable = this.mParcelable;
            if (parcelable != null) {
                this.mObj1 = parcelable;
                return;
            }
            byte[] bArr = this.mData;
            this.mObj1 = bArr;
            this.mType = 3;
            this.mInt1 = 0;
            this.mInt2 = bArr.length;
            return;
        }
        Parcelable parcelable2 = this.mParcelable;
        if (parcelable2 != null) {
            this.mObj1 = parcelable2;
            return;
        }
        throw new IllegalArgumentException("Invalid icon");
    }

    private static String typeToString(int x) {
        if (x == 1) {
            return "BITMAP";
        }
        if (x == 2) {
            return "RESOURCE";
        }
        if (x == 3) {
            return "DATA";
        }
        if (x == 4) {
            return "URI";
        }
        if (x != 5) {
            return "UNKNOWN";
        }
        return "BITMAP_MASKABLE";
    }

    @Nullable
    public static IconCompat createFromBundle(@NonNull Bundle bundle) {
        int type = bundle.getInt(EXTRA_TYPE);
        IconCompat icon = new IconCompat(type);
        icon.mInt1 = bundle.getInt(EXTRA_INT1);
        icon.mInt2 = bundle.getInt(EXTRA_INT2);
        if (bundle.containsKey(EXTRA_TINT_LIST)) {
            icon.mTintList = (ColorStateList) bundle.getParcelable(EXTRA_TINT_LIST);
        }
        if (bundle.containsKey(EXTRA_TINT_MODE)) {
            icon.mTintMode = PorterDuff.Mode.valueOf(bundle.getString(EXTRA_TINT_MODE));
        }
        if (!(type == -1 || type == 1)) {
            if (type != 2) {
                if (type == 3) {
                    icon.mObj1 = bundle.getByteArray(EXTRA_OBJ);
                    return icon;
                } else if (type != 4) {
                    if (type != 5) {
                        Log.w(TAG, "Unknown type " + type);
                        return null;
                    }
                }
            }
            icon.mObj1 = bundle.getString(EXTRA_OBJ);
            return icon;
        }
        icon.mObj1 = bundle.getParcelable(EXTRA_OBJ);
        return icon;
    }

    @RequiresApi(23)
    @Nullable
    public static IconCompat createFromIcon(@NonNull Icon icon) {
        Preconditions.checkNotNull(icon);
        int type = getType(icon);
        if (type == 2) {
            return createWithResource(getResPackage(icon), getResId(icon));
        }
        if (type == 4) {
            return createWithContentUri(getUri(icon));
        }
        IconCompat iconCompat = new IconCompat(-1);
        iconCompat.mObj1 = icon;
        return iconCompat;
    }

    @RequiresApi(23)
    public static int getType(@NonNull Icon icon) {
        if (BuildCompat.isAtLeastP()) {
            return icon.getType();
        }
        try {
            return ((Integer) icon.getClass().getMethod("getType", new Class[0]).invoke(icon, new Object[0])).intValue();
        } catch (IllegalAccessException e) {
            Log.e(TAG, "Unable to get icon type " + ((Object) icon), e);
            return -1;
        } catch (InvocationTargetException e2) {
            Log.e(TAG, "Unable to get icon type " + ((Object) icon), e2);
            return -1;
        } catch (NoSuchMethodException e3) {
            Log.e(TAG, "Unable to get icon type " + ((Object) icon), e3);
            return -1;
        }
    }

    @RequiresApi(23)
    @Nullable
    public static String getResPackage(@NonNull Icon icon) {
        if (BuildCompat.isAtLeastP()) {
            return icon.getResPackage();
        }
        try {
            return (String) icon.getClass().getMethod("getResPackage", new Class[0]).invoke(icon, new Object[0]);
        } catch (IllegalAccessException e) {
            Log.e(TAG, "Unable to get icon package", e);
            return null;
        } catch (InvocationTargetException e2) {
            Log.e(TAG, "Unable to get icon package", e2);
            return null;
        } catch (NoSuchMethodException e3) {
            Log.e(TAG, "Unable to get icon package", e3);
            return null;
        }
    }

    @DrawableRes
    @RequiresApi(23)
    @IdRes
    public static int getResId(@NonNull Icon icon) {
        if (BuildCompat.isAtLeastP()) {
            return icon.getResId();
        }
        try {
            return ((Integer) icon.getClass().getMethod("getResId", new Class[0]).invoke(icon, new Object[0])).intValue();
        } catch (IllegalAccessException e) {
            Log.e(TAG, "Unable to get icon resource", e);
            return 0;
        } catch (InvocationTargetException e2) {
            Log.e(TAG, "Unable to get icon resource", e2);
            return 0;
        } catch (NoSuchMethodException e3) {
            Log.e(TAG, "Unable to get icon resource", e3);
            return 0;
        }
    }

    @RequiresApi(23)
    @Nullable
    public static Uri getUri(@NonNull Icon icon) {
        if (BuildCompat.isAtLeastP()) {
            return icon.getUri();
        }
        try {
            return (Uri) icon.getClass().getMethod("getUri", new Class[0]).invoke(icon, new Object[0]);
        } catch (IllegalAccessException e) {
            Log.e(TAG, "Unable to get icon uri", e);
            return null;
        } catch (InvocationTargetException e2) {
            Log.e(TAG, "Unable to get icon uri", e2);
            return null;
        } catch (NoSuchMethodException e3) {
            Log.e(TAG, "Unable to get icon uri", e3);
            return null;
        }
    }

    @VisibleForTesting
    static Bitmap createLegacyIconFromAdaptiveIcon(Bitmap adaptiveIconBitmap, boolean addShadow) {
        int size = (int) (((float) Math.min(adaptiveIconBitmap.getWidth(), adaptiveIconBitmap.getHeight())) * DEFAULT_VIEW_PORT_SCALE);
        Bitmap icon = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(icon);
        Paint paint = new Paint(3);
        float center = ((float) size) * 0.5f;
        float radius = ICON_DIAMETER_FACTOR * center;
        if (addShadow) {
            float blur = ((float) size) * BLUR_FACTOR;
            paint.setColor(0);
            paint.setShadowLayer(blur, 0.0f, ((float) size) * KEY_SHADOW_OFFSET_FACTOR, 1023410176);
            canvas.drawCircle(center, center, radius, paint);
            paint.setShadowLayer(blur, 0.0f, 0.0f, 503316480);
            canvas.drawCircle(center, center, radius, paint);
            paint.clearShadowLayer();
        }
        paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        BitmapShader shader = new BitmapShader(adaptiveIconBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        Matrix shift = new Matrix();
        shift.setTranslate((float) ((-(adaptiveIconBitmap.getWidth() - size)) / 2), (float) ((-(adaptiveIconBitmap.getHeight() - size)) / 2));
        shader.setLocalMatrix(shift);
        paint.setShader(shader);
        canvas.drawCircle(center, center, radius, paint);
        canvas.setBitmap(null);
        return icon;
    }
}
