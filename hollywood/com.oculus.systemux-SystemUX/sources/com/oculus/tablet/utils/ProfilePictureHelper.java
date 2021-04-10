package com.oculus.tablet.utils;

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
import com.oculus.tablet.R;
import com.oculus.tablet.utils.ImageLoader;
import java.util.List;

public class ProfilePictureHelper {
    private static int IMPL_RENDER_FLAG_BLACK_AND_WHITE = 1;
    private static int IMPL_RENDER_FLAG_NORMAL = 0;
    private static int IMPL_RENDER_FLAG_V2 = 2;
    private static final String TAG = LoggingUtil.tag(ProfilePictureHelper.class);
    protected int PAINT_FLAGS = 3;
    private int mBackgroundColor;
    private Context mContext;
    private Bitmap mDefaultProfilePictureOffline;
    private Bitmap mDefaultProfilePictureOnline;
    private boolean mInitialized;
    private int mOnlineColor;

    public interface CombinedProfilePictureModifier {
        void modify(Canvas canvas, float f, int i);
    }

    public ProfilePictureHelper(Context context) {
        this.mContext = context;
    }

    /* access modifiers changed from: protected */
    public void initialize() {
        TypedValue typedValue = new TypedValue();
        this.mContext.getTheme().resolveAttribute(R.attr.ocPanelBackground, typedValue, true);
        this.mBackgroundColor = typedValue.data;
        this.mContext.getTheme().resolveAttribute(R.attr.ocPositive, typedValue, true);
        this.mOnlineColor = typedValue.data;
        this.mInitialized = true;
    }

    public void setImageViewToDefaultProfilePicture(ImageView imageView, boolean z, RenderFlags renderFlags) {
        if (!this.mInitialized) {
            initialize();
        }
        setImageViewToBitmap(imageView, getDefaultProfilePicture(z, renderFlags));
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
        }
        if (str != null && !str.isEmpty()) {
            ImageLoader.getInstance(this.mContext).loadImage(str, new ImageLoader.ImageCallbacks() {
                /* class com.oculus.tablet.utils.ProfilePictureHelper.AnonymousClass1 */

                @Override // com.oculus.tablet.utils.ImageLoader.ImageCallbacks
                public void onSuccess(Bitmap bitmap) {
                    int implementationRenderFlags = renderFlags.getImplementationRenderFlags();
                    int min = Math.min(bitmap.getWidth(), bitmap.getHeight());
                    Bitmap extractThumbnail = ThumbnailUtils.extractThumbnail(bitmap, min, min);
                    if ((ProfilePictureHelper.IMPL_RENDER_FLAG_BLACK_AND_WHITE & implementationRenderFlags) == ProfilePictureHelper.IMPL_RENDER_FLAG_BLACK_AND_WHITE) {
                        extractThumbnail = ProfilePictureHelper.this.bitmapToGreyscale(extractThumbnail);
                    }
                    ProfilePictureHelper.this.setImageViewToBitmap(imageView, ProfilePictureHelper.this.getCroppedProfile(extractThumbnail, z, implementationRenderFlags));
                }

                @Override // com.oculus.tablet.utils.ImageLoader.ImageCallbacks
                public void onFailure(Throwable th) {
                    String str = ProfilePictureHelper.TAG;
                    Log.e(str, "setImageViewFromUrl.onFailure() with '" + str + "'", th);
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void setImageViewToBitmap(ImageView imageView, Bitmap bitmap) {
        imageView.setImageDrawable(new BitmapDrawable(this.mContext.getResources(), bitmap));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private Bitmap bitmapToGreyscale(Bitmap bitmap) {
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0.0f);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        return createBitmap;
    }

    private Bitmap getDefaultProfilePicture(boolean z, RenderFlags renderFlags) {
        int implementationRenderFlags = renderFlags.getImplementationRenderFlags();
        if (z) {
            if (this.mDefaultProfilePictureOnline == null) {
                this.mDefaultProfilePictureOnline = getCroppedProfile(BitmapFactory.decodeResource(this.mContext.getResources(), R.drawable.ic_social_default_profile), true, implementationRenderFlags);
            }
            return this.mDefaultProfilePictureOnline;
        }
        if (this.mDefaultProfilePictureOffline == null) {
            this.mDefaultProfilePictureOffline = getCroppedProfile(BitmapFactory.decodeResource(this.mContext.getResources(), R.drawable.ic_social_default_profile), false, implementationRenderFlags);
        }
        return this.mDefaultProfilePictureOffline;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private Bitmap getCroppedProfile(Bitmap bitmap, boolean z, int i) {
        int dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(R.dimen.social_icon_width);
        int dimensionPixelSize2 = this.mContext.getResources().getDimensionPixelSize(R.dimen.social_icon_height);
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
        if (!z) {
            return createBitmap;
        }
        float f3 = f * (z2 ? 0.85f : 0.15f);
        float f4 = f2 * 0.85f;
        if (z2) {
            Paint paint2 = new Paint(this.PAINT_FLAGS);
            paint2.setColor(this.mBackgroundColor);
            paint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
            canvas.drawCircle(f3, f4, 8.0f, paint2);
        }
        Paint paint3 = new Paint(this.PAINT_FLAGS);
        paint3.setColor(this.mOnlineColor);
        paint3.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        canvas.drawCircle(f3, f4, z2 ? 6.0f : 4.0f, paint3);
        return createBitmap;
    }

    public Bitmap getRoundedImageBitmap(Bitmap bitmap, int i, int i2) {
        Bitmap createBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        float f = (float) i;
        canvas.drawBitmap(bitmap, (Rect) null, new Rect(0, 0, i, i), setupCanvasBitmapMask(canvas, f, f, i2));
        return createBitmap;
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
        int i6 = i / 4;
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

    /* access modifiers changed from: protected */
    public Paint setupCanvasBitmapMask(Canvas canvas, float f, float f2, int i) {
        canvas.drawARGB(0, 0, 0, 0);
        Paint paint = new Paint(this.PAINT_FLAGS);
        paint.setColor(-12434878);
        drawCircle(canvas, f, f2, i, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        return paint;
    }

    /* access modifiers changed from: protected */
    public void drawCircle(Canvas canvas, float f, float f2, int i, Paint paint) {
        float f3 = f / 2.0f;
        float f4 = f2 / 2.0f;
        canvas.drawCircle(f3, f4, ((f3 + f4) / 2.0f) - ((float) i), paint);
    }

    /* access modifiers changed from: protected */
    public void drawCircle(Canvas canvas, float f, Paint paint, int i) {
        canvas.drawCircle(f, f, f - ((float) i), paint);
    }

    public void drawDoubleStroke(Canvas canvas, float f, int i) {
        Paint paint = new Paint(1);
        paint.setColor(-14934496);
        paint.setStrokeWidth(6.0f);
        paint.setStyle(Paint.Style.STROKE);
        drawCircle(canvas, f, paint, i);
        Paint paint2 = new Paint(1);
        paint2.setColor(-986896);
        paint2.setStrokeWidth(2.0f);
        paint2.setStyle(Paint.Style.STROKE);
        drawCircle(canvas, f, paint2, i);
    }

    private Bitmap scaleBitmap(Bitmap bitmap, int i) {
        return Bitmap.createScaledBitmap(bitmap, i, i, false);
    }

    public enum RenderFlags {
        NORMAL(ProfilePictureHelper.IMPL_RENDER_FLAG_NORMAL),
        INVITE_SENT(ProfilePictureHelper.IMPL_RENDER_FLAG_BLACK_AND_WHITE),
        INVITE_RECEIVED(ProfilePictureHelper.IMPL_RENDER_FLAG_NORMAL),
        V2(ProfilePictureHelper.IMPL_RENDER_FLAG_V2);
        
        private int mImplementationRenderFlags;

        private RenderFlags(int i) {
            this.mImplementationRenderFlags = i;
        }

        public int getImplementationRenderFlags() {
            return this.mImplementationRenderFlags;
        }
    }
}
