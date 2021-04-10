package org.chromium.ui.resources;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class HandleViewResources {

    /* renamed from: a  reason: collision with root package name */
    public static final int[] f11026a = {16843461};
    public static final int[] b = {16843463};
    public static final int[] c = {16843462};

    public static Bitmap a(Context context, int[] iArr, int i) {
        Bitmap decodeResource;
        if (context == null) {
            context = ContextUtils.getApplicationContext();
        }
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(iArr);
        int resourceId = obtainStyledAttributes.getResourceId(obtainStyledAttributes.getIndex(0), 0);
        Resources resources = obtainStyledAttributes.getResources();
        obtainStyledAttributes.recycle();
        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = config;
        Bitmap decodeResource2 = BitmapFactory.decodeResource(resources, resourceId, options);
        if (decodeResource2 != null) {
            return decodeResource2;
        }
        if (resources != context.getResources() && (decodeResource = BitmapFactory.decodeResource(context.getResources(), resourceId, options)) != null) {
            return decodeResource;
        }
        TypedArray obtainStyledAttributes2 = context.getTheme().obtainStyledAttributes(iArr);
        Drawable drawable = obtainStyledAttributes2.getDrawable(0);
        if (drawable == null) {
            try {
                drawable = AbstractC3153j7.c(context.getResources(), obtainStyledAttributes2.getResourceId(0, 0));
            } catch (Resources.NotFoundException unused) {
            }
        }
        obtainStyledAttributes2.recycle();
        drawable.setTint(i);
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, config);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        drawable.draw(canvas);
        return createBitmap;
    }

    public static Bitmap getCenterHandleBitmap(Context context) {
        return a(context, b, -16627010);
    }

    public static Bitmap getCenterHandleBitmapActive(Context context) {
        return a(context, b, -15506201);
    }

    public static Bitmap getCenterHandleBitmapHover(Context context) {
        return a(context, b, -13533966);
    }

    public static float getHandleHorizontalPaddingRatio() {
        return 0.25f;
    }

    public static Bitmap getLeftHandleBitmap(Context context) {
        return a(context, f11026a, -16627010);
    }

    public static Bitmap getLeftHandleBitmapActive(Context context) {
        return a(context, f11026a, -15506201);
    }

    public static Bitmap getLeftHandleBitmapHover(Context context) {
        return a(context, f11026a, -13533966);
    }

    public static Bitmap getRightHandleBitmap(Context context) {
        return a(context, c, -16627010);
    }

    public static Bitmap getRightHandleBitmapActive(Context context) {
        return a(context, c, -15506201);
    }

    public static Bitmap getRightHandleBitmapHover(Context context) {
        return a(context, c, -13533966);
    }
}
