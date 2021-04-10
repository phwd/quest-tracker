package com.oculus.tablet.utils;

import X.AnonymousClass006;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.media.ThumbnailUtils;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ImageView;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.socialplatform.R;
import com.oculus.tablet.utils.ImageLoader;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import java.util.List;

public class ProfilePictureHelper {
    public static final int IMPL_RENDER_FLAG_BLACK_AND_WHITE = 1;
    public static final int IMPL_RENDER_FLAG_NORMAL = 0;
    public static final int IMPL_RENDER_FLAG_V2 = 2;
    public static final String TAG = LoggingUtil.tag(ProfilePictureHelper.class);
    public int PAINT_FLAGS = 3;
    public int mBackgroundColor;
    public Context mContext;
    public Bitmap mDefaultProfilePictureOffline;
    public Bitmap mDefaultProfilePictureOnline;
    public boolean mInitialized;
    public int mOnlineColor;

    public interface CombinedProfilePictureModifier {
        void modify(Canvas canvas, float f, int i);
    }

    /* JADX WARN: Init of enum NORMAL can be incorrect */
    /* JADX WARN: Init of enum INVITE_RECEIVED can be incorrect */
    public enum RenderFlags {
        NORMAL(r3),
        INVITE_SENT(ProfilePictureHelper.IMPL_RENDER_FLAG_BLACK_AND_WHITE),
        INVITE_RECEIVED(r3),
        V2(ProfilePictureHelper.IMPL_RENDER_FLAG_V2);
        
        public int mImplementationRenderFlags;

        /* access modifiers changed from: public */
        static {
            int i = ProfilePictureHelper.IMPL_RENDER_FLAG_NORMAL;
        }

        public int getImplementationRenderFlags() {
            return this.mImplementationRenderFlags;
        }

        /* access modifiers changed from: public */
        RenderFlags(int i) {
            this.mImplementationRenderFlags = i;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private Bitmap getCroppedProfile(Bitmap bitmap, boolean z, int i) {
        int dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(R.dimen.messenger_tablet_sticker_max_size);
        int dimensionPixelSize2 = this.mContext.getResources().getDimensionPixelSize(R.dimen.messenger_tablet_sticker_max_size);
        Bitmap createBitmap = Bitmap.createBitmap(dimensionPixelSize, dimensionPixelSize2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        float f = (float) dimensionPixelSize;
        float f2 = (float) dimensionPixelSize2;
        boolean z2 = false;
        canvas.drawBitmap(bitmap, (Rect) null, new Rect(0, 0, dimensionPixelSize, dimensionPixelSize2), setupCanvasBitmapMask(canvas, f, f2, 0));
        Paint paint = new Paint(this.PAINT_FLAGS);
        paint.setColor(this.mBackgroundColor);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OVER));
        drawCircle(canvas, f, f2, 0, paint);
        int i2 = IMPL_RENDER_FLAG_V2;
        if ((i & i2) == i2) {
            z2 = true;
        }
        if (z) {
            float f3 = 0.15f;
            if (z2) {
                f3 = 0.85f;
            }
            float f4 = f * f3;
            float f5 = f2 * 0.85f;
            if (z2) {
                Paint paint2 = new Paint(this.PAINT_FLAGS);
                paint2.setColor(this.mBackgroundColor);
                paint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
                canvas.drawCircle(f4, f5, 8.0f, paint2);
            }
            Paint paint3 = new Paint(this.PAINT_FLAGS);
            paint3.setColor(this.mOnlineColor);
            paint3.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
            float f6 = 4.0f;
            if (z2) {
                f6 = 6.0f;
            }
            canvas.drawCircle(f4, f5, f6, paint3);
        }
        return createBitmap;
    }

    private Bitmap scaleBitmap(Bitmap bitmap, int i) {
        return Bitmap.createScaledBitmap(bitmap, i, i, false);
    }

    public void drawDoubleStroke(Canvas canvas, float f, int i) {
        Paint paint = new Paint(1);
        paint.setColor(-14934496);
        paint.setStrokeWidth(6.0f);
        Paint.Style style = Paint.Style.STROKE;
        paint.setStyle(style);
        drawCircle(canvas, f, paint, i);
        Paint paint2 = new Paint(1);
        paint2.setColor(-986896);
        paint2.setStrokeWidth(2.0f);
        paint2.setStyle(style);
        drawCircle(canvas, f, paint2, i);
    }

    public Bitmap getCombinedProfilePictureBitmap(List<Bitmap> list, int i, int i2, CombinedProfilePictureModifier[] combinedProfilePictureModifierArr) {
        int i3;
        for (int i4 = 0; i4 < list.size(); i4++) {
            list.set(i4, scaleBitmap(list.get(i4), i));
        }
        Bitmap createBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        float f = (float) i;
        float f2 = f / 2.0f;
        Paint paint = setupCanvasBitmapMask(canvas, f, f, i2);
        int i5 = (int) f2;
        Rect rect = new Rect(0, 0, i, i);
        int i6 = i >> 2;
        Rect rect2 = new Rect(i6, 0, i6 * 3, i);
        Rect rect3 = new Rect(0, 0, i5, i);
        Rect rect4 = new Rect(i5, 0, i, i);
        Rect rect5 = new Rect(i5, 0, i, i5);
        Rect rect6 = new Rect(i5, i5, i, i);
        int size = list.size();
        if (size == 1) {
            i3 = 0;
            canvas.drawBitmap(list.get(0), rect, rect, paint);
        } else if (size != 2) {
            i3 = 0;
            canvas.drawBitmap(list.get(0), rect, rect5, paint);
            canvas.drawBitmap(list.get(1), rect, rect6, paint);
            canvas.drawBitmap(list.get(2), rect2, rect3, paint);
        } else {
            i3 = 0;
            canvas.drawBitmap(list.get(0), rect2, rect3, paint);
            canvas.drawBitmap(list.get(1), rect2, rect4, paint);
        }
        if (combinedProfilePictureModifierArr != null) {
            int length = combinedProfilePictureModifierArr.length;
            while (i3 < length) {
                combinedProfilePictureModifierArr[i3].modify(canvas, f2, i2);
                i3++;
            }
        }
        return createBitmap;
    }

    public Paint setupCanvasBitmapMask(Canvas canvas, float f, float f2, int i) {
        canvas.drawARGB(0, 0, 0, 0);
        Paint paint = new Paint(this.PAINT_FLAGS);
        paint.setColor(-12434878);
        drawCircle(canvas, f, f2, i, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        return paint;
    }

    public Bitmap getRoundedImageBitmap(Bitmap bitmap, int i, int i2) {
        Bitmap createBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        float f = (float) i;
        canvas.drawBitmap(bitmap, (Rect) null, new Rect(0, 0, i, i), setupCanvasBitmapMask(canvas, f, f, i2));
        return createBitmap;
    }

    public void initialize() {
        TypedValue typedValue = new TypedValue();
        this.mContext.getTheme().resolveAttribute(R.attr.ocPanelBackground, typedValue, true);
        this.mBackgroundColor = typedValue.data;
        this.mContext.getTheme().resolveAttribute(R.attr.ocPositive, typedValue, true);
        this.mOnlineColor = typedValue.data;
        this.mInitialized = true;
    }

    public void setImageViewToBitmap(ImageView imageView, Bitmap bitmap) {
        imageView.setImageDrawable(new BitmapDrawable(this.mContext.getResources(), bitmap));
    }

    public void setImageViewToDefaultProfilePicture(ImageView imageView, boolean z, RenderFlags renderFlags) {
        if (!this.mInitialized) {
            initialize();
        }
        setImageViewToBitmap(imageView, getDefaultProfilePicture(z, renderFlags));
    }

    public ProfilePictureHelper(Context context) {
        this.mContext = context;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private Bitmap bitmapToGreyscale(Bitmap bitmap) {
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmap, AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z, AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z, paint);
        return createBitmap;
    }

    private Bitmap getDefaultProfilePicture(boolean z, RenderFlags renderFlags) {
        int implementationRenderFlags = renderFlags.getImplementationRenderFlags();
        if (z) {
            Bitmap bitmap = this.mDefaultProfilePictureOnline;
            if (bitmap != null) {
                return bitmap;
            }
            Bitmap croppedProfile = getCroppedProfile(BitmapFactory.decodeResource(this.mContext.getResources(), R.drawable.ic_social_default_profile), true, implementationRenderFlags);
            this.mDefaultProfilePictureOnline = croppedProfile;
            return croppedProfile;
        }
        Bitmap bitmap2 = this.mDefaultProfilePictureOffline;
        if (bitmap2 != null) {
            return bitmap2;
        }
        Bitmap croppedProfile2 = getCroppedProfile(BitmapFactory.decodeResource(this.mContext.getResources(), R.drawable.ic_social_default_profile), false, implementationRenderFlags);
        this.mDefaultProfilePictureOffline = croppedProfile2;
        return croppedProfile2;
    }

    public void drawCircle(Canvas canvas, float f, float f2, int i, Paint paint) {
        float f3 = f / 2.0f;
        float f4 = f2 / 2.0f;
        canvas.drawCircle(f3, f4, ((f3 + f4) / 2.0f) - ((float) i), paint);
    }

    public void drawCircle(Canvas canvas, float f, Paint paint, int i) {
        canvas.drawCircle(f, f, f - ((float) i), paint);
    }

    public void setImageViewFromUrl(ImageView imageView, String str, boolean z) {
        setImageViewFromUrl(imageView, str, z, RenderFlags.NORMAL, true);
    }

    public void setImageViewFromUrl(final ImageView imageView, final String str, final boolean z, final RenderFlags renderFlags, boolean z2) {
        if (!this.mInitialized) {
            initialize();
        }
        if (z2 || str == null || str.isEmpty()) {
            setImageViewToDefaultProfilePicture(imageView, z, renderFlags);
            if (str == null) {
                return;
            }
        }
        if (!str.isEmpty()) {
            ImageLoader.getInstance(this.mContext).loadImage(str, new ImageLoader.ImageCallbacks() {
                /* class com.oculus.tablet.utils.ProfilePictureHelper.AnonymousClass1 */

                @Override // com.oculus.tablet.utils.ImageLoader.ImageCallbacks
                public void onFailure(Throwable th) {
                    Log.e(ProfilePictureHelper.TAG, AnonymousClass006.A09("setImageViewFromUrl.onFailure() with '", str, "'"), th);
                }

                @Override // com.oculus.tablet.utils.ImageLoader.ImageCallbacks
                public void onSuccess(Bitmap bitmap) {
                    int implementationRenderFlags = renderFlags.getImplementationRenderFlags();
                    int min = Math.min(bitmap.getWidth(), bitmap.getHeight());
                    Bitmap extractThumbnail = ThumbnailUtils.extractThumbnail(bitmap, min, min);
                    int i = ProfilePictureHelper.IMPL_RENDER_FLAG_BLACK_AND_WHITE;
                    if ((i & implementationRenderFlags) == i) {
                        extractThumbnail = ProfilePictureHelper.this.bitmapToGreyscale(extractThumbnail);
                    }
                    ProfilePictureHelper.this.setImageViewToBitmap(imageView, ProfilePictureHelper.this.getCroppedProfile(extractThumbnail, z, implementationRenderFlags));
                }
            });
        }
    }
}
