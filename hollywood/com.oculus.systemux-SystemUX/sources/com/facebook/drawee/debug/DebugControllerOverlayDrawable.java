package com.facebook.drawee.debug;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.drawee.debug.listener.ImageLoadingTimeListener;
import com.facebook.drawee.drawable.ScalingUtils;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nullable;

public class DebugControllerOverlayDrawable extends Drawable implements ImageLoadingTimeListener {
    private static final float IMAGE_SIZE_THRESHOLD_NOT_OK = 0.5f;
    private static final float IMAGE_SIZE_THRESHOLD_OK = 0.1f;
    private static final int MAX_LINE_WIDTH_EM = 8;
    private static final int MAX_NUMBER_OF_LINES = 9;
    private static final int MAX_TEXT_SIZE_PX = 40;
    private static final int MIN_TEXT_SIZE_PX = 10;
    private static final String NO_CONTROLLER_ID = "none";
    private static final int OUTLINE_COLOR = -26624;
    private static final int OUTLINE_STROKE_WIDTH_PX = 2;
    private static final int TEXT_BACKGROUND_COLOR = 1711276032;
    private static final int TEXT_COLOR = -1;
    @VisibleForTesting
    static final int TEXT_COLOR_IMAGE_ALMOST_OK = -256;
    @VisibleForTesting
    static final int TEXT_COLOR_IMAGE_NOT_OK = -65536;
    @VisibleForTesting
    static final int TEXT_COLOR_IMAGE_OK = -16711936;
    private static final int TEXT_LINE_SPACING_PX = 8;
    private static final int TEXT_PADDING_PX = 10;
    private HashMap<String, String> mAdditionalData = new HashMap<>();
    private String mControllerId;
    private int mCurrentTextXPx;
    private int mCurrentTextYPx;
    private long mFinalImageTimeMs;
    private int mFrameCount;
    private int mHeightPx;
    private String mImageFormat;
    private String mImageId;
    private int mImageSizeBytes;
    private int mLineIncrementPx;
    private int mLoopCount;
    private final Matrix mMatrix = new Matrix();
    private int mOriginColor = -1;
    private String mOriginText;
    private int mOverlayColor = 0;
    private final Paint mPaint = new Paint(1);
    private final Rect mRect = new Rect();
    private final RectF mRectF = new RectF();
    private ScalingUtils.ScaleType mScaleType;
    private int mStartTextXPx;
    private int mStartTextYPx;
    private int mTextGravity = 80;
    private int mWidthPx;

    public int getOpacity() {
        return -3;
    }

    public void setAlpha(int i) {
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }

    public DebugControllerOverlayDrawable() {
        reset();
    }

    public void reset() {
        this.mWidthPx = -1;
        this.mHeightPx = -1;
        this.mImageSizeBytes = -1;
        this.mAdditionalData = new HashMap<>();
        this.mFrameCount = -1;
        this.mLoopCount = -1;
        this.mImageFormat = null;
        setControllerId(null);
        this.mFinalImageTimeMs = -1;
        this.mOriginText = null;
        this.mOriginColor = -1;
        invalidateSelf();
    }

    public void setTextGravity(int i) {
        this.mTextGravity = i;
        invalidateSelf();
    }

    public void setControllerId(@Nullable String str) {
        if (str == null) {
            str = "none";
        }
        this.mControllerId = str;
        invalidateSelf();
    }

    public void setImageId(@Nullable String str) {
        this.mImageId = str;
        invalidateSelf();
    }

    public void setDimensions(int i, int i2) {
        this.mWidthPx = i;
        this.mHeightPx = i2;
        invalidateSelf();
    }

    public void setAnimationInfo(int i, int i2) {
        this.mFrameCount = i;
        this.mLoopCount = i2;
        invalidateSelf();
    }

    public void setOrigin(String str, int i) {
        this.mOriginText = str;
        this.mOriginColor = i;
        invalidateSelf();
    }

    public void setImageSize(int i) {
        this.mImageSizeBytes = i;
    }

    public void addAdditionalData(String str, String str2) {
        this.mAdditionalData.put(str, str2);
    }

    public void setImageFormat(@Nullable String str) {
        this.mImageFormat = str;
    }

    public void setScaleType(ScalingUtils.ScaleType scaleType) {
        this.mScaleType = scaleType;
    }

    public void setOverlayColor(int i) {
        this.mOverlayColor = i;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        prepareDebugTextParameters(rect, 9, 8);
    }

    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeWidth(2.0f);
        this.mPaint.setColor(OUTLINE_COLOR);
        canvas.drawRect((float) bounds.left, (float) bounds.top, (float) bounds.right, (float) bounds.bottom, this.mPaint);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setColor(this.mOverlayColor);
        canvas.drawRect((float) bounds.left, (float) bounds.top, (float) bounds.right, (float) bounds.bottom, this.mPaint);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setStrokeWidth(0.0f);
        this.mPaint.setColor(-1);
        this.mCurrentTextXPx = this.mStartTextXPx;
        this.mCurrentTextYPx = this.mStartTextYPx;
        String str = this.mImageId;
        if (str != null) {
            addDebugText(canvas, "IDs", format("%s, %s", this.mControllerId, str));
        } else {
            addDebugText(canvas, "ID", this.mControllerId);
        }
        addDebugText(canvas, "D", format("%dx%d", Integer.valueOf(bounds.width()), Integer.valueOf(bounds.height())));
        addDebugText(canvas, "I", format("%dx%d", Integer.valueOf(this.mWidthPx), Integer.valueOf(this.mHeightPx)), determineSizeHintColor(this.mWidthPx, this.mHeightPx, this.mScaleType));
        addDebugText(canvas, "I", format("%d KiB", Integer.valueOf(this.mImageSizeBytes / 1024)));
        String str2 = this.mImageFormat;
        if (str2 != null) {
            addDebugText(canvas, "i format", str2);
        }
        int i = this.mFrameCount;
        if (i > 0) {
            addDebugText(canvas, "anim", format("f %d, l %d", Integer.valueOf(i), Integer.valueOf(this.mLoopCount)));
        }
        ScalingUtils.ScaleType scaleType = this.mScaleType;
        if (scaleType != null) {
            addDebugText(canvas, "scale", scaleType);
        }
        long j = this.mFinalImageTimeMs;
        if (j >= 0) {
            addDebugText(canvas, "t", format("%d ms", Long.valueOf(j)));
        }
        String str3 = this.mOriginText;
        if (str3 != null) {
            addDebugText(canvas, "origin", str3, this.mOriginColor);
        }
        for (Map.Entry<String, String> entry : this.mAdditionalData.entrySet()) {
            addDebugText(canvas, entry.getKey(), entry.getValue());
        }
    }

    private void prepareDebugTextParameters(Rect rect, int i, int i2) {
        int min = Math.min(40, Math.max(10, Math.min(rect.width() / i2, rect.height() / i)));
        this.mPaint.setTextSize((float) min);
        this.mLineIncrementPx = min + 8;
        if (this.mTextGravity == 80) {
            this.mLineIncrementPx *= -1;
        }
        this.mStartTextXPx = rect.left + 10;
        this.mStartTextYPx = this.mTextGravity == 80 ? rect.bottom - 10 : rect.top + 10 + 10;
    }

    private static String format(String str, @Nullable Object... objArr) {
        return objArr == null ? str : String.format(Locale.US, str, objArr);
    }

    private void addDebugText(Canvas canvas, String str, Object obj) {
        addDebugText(canvas, str, String.valueOf(obj), -1);
    }

    private void addDebugText(Canvas canvas, String str, String str2) {
        addDebugText(canvas, str, str2, -1);
    }

    private void addDebugText(Canvas canvas, String str, String str2, int i) {
        String str3 = str + ": ";
        float measureText = this.mPaint.measureText(str3);
        float measureText2 = this.mPaint.measureText(str2);
        this.mPaint.setColor(TEXT_BACKGROUND_COLOR);
        int i2 = this.mCurrentTextXPx;
        int i3 = this.mCurrentTextYPx;
        canvas.drawRect((float) (i2 - 4), (float) (i3 + 8), ((float) i2) + measureText + measureText2 + 4.0f, (float) (i3 + this.mLineIncrementPx + 8), this.mPaint);
        this.mPaint.setColor(-1);
        canvas.drawText(str3, (float) this.mCurrentTextXPx, (float) this.mCurrentTextYPx, this.mPaint);
        this.mPaint.setColor(i);
        canvas.drawText(str2, ((float) this.mCurrentTextXPx) + measureText, (float) this.mCurrentTextYPx, this.mPaint);
        this.mCurrentTextYPx += this.mLineIncrementPx;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public int determineSizeHintColor(int i, int i2, @Nullable ScalingUtils.ScaleType scaleType) {
        int width = getBounds().width();
        int height = getBounds().height();
        if (width > 0 && height > 0 && i > 0 && i2 > 0) {
            if (scaleType != null) {
                Rect rect = this.mRect;
                rect.top = 0;
                rect.left = 0;
                rect.right = width;
                rect.bottom = height;
                this.mMatrix.reset();
                scaleType.getTransform(this.mMatrix, this.mRect, i, i2, 0.0f, 0.0f);
                RectF rectF = this.mRectF;
                rectF.top = 0.0f;
                rectF.left = 0.0f;
                rectF.right = (float) i;
                rectF.bottom = (float) i2;
                this.mMatrix.mapRect(rectF);
                width = Math.min(width, (int) this.mRectF.width());
                height = Math.min(height, (int) this.mRectF.height());
            }
            float f = (float) width;
            float f2 = f * 0.1f;
            float f3 = f * 0.5f;
            float f4 = (float) height;
            float f5 = 0.1f * f4;
            float f6 = f4 * 0.5f;
            int abs = Math.abs(i - width);
            int abs2 = Math.abs(i2 - height);
            float f7 = (float) abs;
            if (f7 < f2 && ((float) abs2) < f5) {
                return TEXT_COLOR_IMAGE_OK;
            }
            if (f7 >= f3 || ((float) abs2) >= f6) {
                return -65536;
            }
            return -256;
        }
        return -65536;
    }

    public void setFinalImageTimeMs(long j) {
        this.mFinalImageTimeMs = j;
    }

    @Override // com.facebook.drawee.debug.listener.ImageLoadingTimeListener
    public void onFinalImageSet(long j) {
        this.mFinalImageTimeMs = j;
        invalidateSelf();
    }
}
