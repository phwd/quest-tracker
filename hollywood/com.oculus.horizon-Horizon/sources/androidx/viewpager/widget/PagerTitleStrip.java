package androidx.viewpager.widget;

import X.AbstractC00660Cc;
import X.AnonymousClass0Cf;
import X.AnonymousClass0rL;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.viewpager.widget.ViewPager;
import java.lang.ref.WeakReference;

@ViewPager.DecorView
public class PagerTitleStrip extends ViewGroup {
    public static final int[] ATTRS = {16842804, 16842901, 16842904, 16842927};
    public static final float SIDE_ALPHA = 0.6f;
    public static final int[] TEXT_ATTRS = {16843660};
    public static final int TEXT_SPACING = 16;
    public TextView mCurrText;
    public int mGravity;
    public int mLastKnownCurrentPage;
    public float mLastKnownPositionOffset;
    public TextView mNextText;
    public int mNonPrimaryAlpha;
    public final AnonymousClass0rL mPageListener;
    public ViewPager mPager;
    public TextView mPrevText;
    public int mScaledTextSpacing;
    public int mTextColor;
    public boolean mUpdatingPositions;
    public boolean mUpdatingText;
    public final WeakReference<AbstractC00660Cc> mWatchingAdapter;

    public void updateText(int i, AbstractC00660Cc r7) {
        if (r7 != null) {
            throw null;
        }
        this.mUpdatingText = true;
        this.mPrevText.setText((CharSequence) null);
        this.mCurrText.setText((CharSequence) null);
        this.mNextText.setText((CharSequence) null);
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(Math.max(0, (int) (((float) ((getWidth() - getPaddingLeft()) - getPaddingRight())) * 0.8f)), Integer.MIN_VALUE);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(Math.max(0, (getHeight() - getPaddingTop()) - getPaddingBottom()), Integer.MIN_VALUE);
        this.mPrevText.measure(makeMeasureSpec, makeMeasureSpec2);
        this.mCurrText.measure(makeMeasureSpec, makeMeasureSpec2);
        this.mNextText.measure(makeMeasureSpec, makeMeasureSpec2);
        this.mLastKnownCurrentPage = i;
        if (!this.mUpdatingPositions) {
            updateTextPositions(i, this.mLastKnownPositionOffset, false);
        }
        this.mUpdatingText = false;
    }

    public static void A00(@NonNull TextView textView, @StyleRes int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            textView.setTextAppearance(i);
        } else {
            textView.setTextAppearance(textView.getContext(), i);
        }
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.mPager != null) {
            float f = this.mLastKnownPositionOffset;
            if (f < 0.0f) {
                f = 0.0f;
            }
            updateTextPositions(this.mLastKnownCurrentPage, f, true);
        }
    }

    public void requestLayout() {
        if (!this.mUpdatingText) {
            super.requestLayout();
        }
    }

    public void setGravity(int i) {
        this.mGravity = i;
        requestLayout();
    }

    public void setNonPrimaryAlpha(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        int i = ((int) (f * 255.0f)) & 255;
        this.mNonPrimaryAlpha = i;
        int i2 = (i << 24) | (this.mTextColor & 16777215);
        this.mPrevText.setTextColor(i2);
        this.mNextText.setTextColor(i2);
    }

    public void setTextColor(@ColorInt int i) {
        this.mTextColor = i;
        this.mCurrText.setTextColor(i);
        int i2 = (this.mNonPrimaryAlpha << 24) | (this.mTextColor & 16777215);
        this.mPrevText.setTextColor(i2);
        this.mNextText.setTextColor(i2);
    }

    public void setTextSize(int i, float f) {
        this.mPrevText.setTextSize(i, f);
        this.mCurrText.setTextSize(i, f);
        this.mNextText.setTextSize(i, f);
    }

    public void setTextSpacing(int i) {
        this.mScaledTextSpacing = i;
        requestLayout();
    }

    public void updateAdapter(AbstractC00660Cc r3, AbstractC00660Cc r4) {
        if (r3 == null && r4 == null) {
            ViewPager viewPager = this.mPager;
            if (viewPager != null) {
                this.mLastKnownCurrentPage = -1;
                this.mLastKnownPositionOffset = -1.0f;
                updateText(viewPager.mCurItem, r4);
                requestLayout();
                return;
            }
            return;
        }
        throw null;
    }

    public void updateTextPositions(int i, float f, boolean z) {
        int i2;
        int i3;
        int i4;
        int i5;
        if (i != this.mLastKnownCurrentPage) {
            updateText(i, null);
        } else if (!z && f == this.mLastKnownPositionOffset) {
            return;
        }
        this.mUpdatingPositions = true;
        int measuredWidth = this.mPrevText.getMeasuredWidth();
        int measuredWidth2 = this.mCurrText.getMeasuredWidth();
        int measuredWidth3 = this.mNextText.getMeasuredWidth();
        int i6 = measuredWidth2 >> 1;
        int width = getWidth();
        int height = getHeight();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int i7 = paddingRight + i6;
        int i8 = (width - (paddingLeft + i6)) - i7;
        float f2 = 0.5f + f;
        if (f2 > 1.0f) {
            f2 -= 1.0f;
        }
        int i9 = ((width - i7) - ((int) (((float) i8) * f2))) - i6;
        int i10 = measuredWidth2 + i9;
        int baseline = this.mPrevText.getBaseline();
        int baseline2 = this.mCurrText.getBaseline();
        int baseline3 = this.mNextText.getBaseline();
        int max = Math.max(Math.max(baseline, baseline2), baseline3);
        int i11 = max - baseline;
        int i12 = max - baseline2;
        int i13 = max - baseline3;
        int max2 = Math.max(Math.max(this.mPrevText.getMeasuredHeight() + i11, this.mCurrText.getMeasuredHeight() + i12), this.mNextText.getMeasuredHeight() + i13);
        int i14 = this.mGravity & 112;
        if (i14 == 16) {
            i2 = (((height - paddingTop) - paddingBottom) - max2) >> 1;
            i3 = i11 + i2;
            i4 = i12 + i2;
            i5 = i2 + i13;
        } else if (i14 != 80) {
            i3 = i11 + paddingTop;
            i4 = i12 + paddingTop;
            i5 = paddingTop + i13;
        } else {
            i2 = (height - paddingBottom) - max2;
            i3 = i11 + i2;
            i4 = i12 + i2;
            i5 = i2 + i13;
        }
        TextView textView = this.mCurrText;
        textView.layout(i9, i4, i10, textView.getMeasuredHeight() + i4);
        int min = Math.min(paddingLeft, (i9 - this.mScaledTextSpacing) - measuredWidth);
        TextView textView2 = this.mPrevText;
        textView2.layout(min, i3, measuredWidth + min, textView2.getMeasuredHeight() + i3);
        int max3 = Math.max((width - paddingRight) - measuredWidth3, i10 + this.mScaledTextSpacing);
        TextView textView3 = this.mNextText;
        textView3.layout(max3, i5, max3 + measuredWidth3, textView3.getMeasuredHeight() + i5);
        this.mLastKnownPositionOffset = f;
        this.mUpdatingPositions = false;
    }

    public static void setSingleLineAllCaps(TextView textView) {
        textView.setTransformationMethod(new AnonymousClass0Cf(textView.getContext()));
    }

    public int getMinHeight() {
        Drawable background = getBackground();
        if (background != null) {
            return background.getIntrinsicHeight();
        }
        return 0;
    }

    public int getTextSpacing() {
        return this.mScaledTextSpacing;
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewParent parent = getParent();
        if (parent instanceof ViewPager) {
            ViewPager viewPager = (ViewPager) parent;
            AnonymousClass0rL r0 = this.mPageListener;
            viewPager.mInternalPageChangeListener = r0;
            viewPager.addOnAdapterChangeListener(r0);
            this.mPager = viewPager;
            WeakReference<AbstractC00660Cc> weakReference = this.mWatchingAdapter;
            if (weakReference != null) {
                weakReference.get();
            }
            updateAdapter(null, null);
            return;
        }
        throw new IllegalStateException("PagerTitleStrip must be a direct child of a ViewPager.");
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mPager != null) {
            updateAdapter(null, null);
            ViewPager viewPager = this.mPager;
            viewPager.mInternalPageChangeListener = null;
            viewPager.removeOnAdapterChangeListener(this.mPageListener);
            this.mPager = null;
        }
    }

    public void onMeasure(int i, int i2) {
        int max;
        if (View.MeasureSpec.getMode(i) == 1073741824) {
            int paddingTop = getPaddingTop() + getPaddingBottom();
            int childMeasureSpec = getChildMeasureSpec(i2, paddingTop, -2);
            int size = View.MeasureSpec.getSize(i);
            int childMeasureSpec2 = getChildMeasureSpec(i, (int) (((float) size) * 0.2f), -2);
            this.mPrevText.measure(childMeasureSpec2, childMeasureSpec);
            this.mCurrText.measure(childMeasureSpec2, childMeasureSpec);
            this.mNextText.measure(childMeasureSpec2, childMeasureSpec);
            if (View.MeasureSpec.getMode(i2) == 1073741824) {
                max = View.MeasureSpec.getSize(i2);
            } else {
                max = Math.max(getMinHeight(), this.mCurrText.getMeasuredHeight() + paddingTop);
            }
            setMeasuredDimension(size, View.resolveSizeAndState(max, i2, this.mCurrText.getMeasuredState() << 16));
            return;
        }
        throw new IllegalStateException("Must measure with an exact width");
    }

    public PagerTitleStrip(@NonNull Context context) {
        this(context, null);
    }

    public PagerTitleStrip(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mLastKnownCurrentPage = -1;
        this.mLastKnownPositionOffset = -1.0f;
        this.mPageListener = new AnonymousClass0rL(this);
        TextView textView = new TextView(context);
        this.mPrevText = textView;
        addView(textView);
        TextView textView2 = new TextView(context);
        this.mCurrText = textView2;
        addView(textView2);
        TextView textView3 = new TextView(context);
        this.mNextText = textView3;
        addView(textView3);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, ATTRS);
        if (Build.VERSION.SDK_INT >= 29) {
            saveAttributeDataForStyleable(context, ATTRS, attributeSet, obtainStyledAttributes, 0, 0);
        }
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        if (resourceId != 0) {
            A00(this.mPrevText, resourceId);
            A00(this.mCurrText, resourceId);
            A00(this.mNextText, resourceId);
        }
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(1, 0);
        if (dimensionPixelSize != 0) {
            setTextSize(0, (float) dimensionPixelSize);
        }
        if (obtainStyledAttributes.hasValue(2)) {
            int color = obtainStyledAttributes.getColor(2, 0);
            this.mPrevText.setTextColor(color);
            this.mCurrText.setTextColor(color);
            this.mNextText.setTextColor(color);
        }
        this.mGravity = obtainStyledAttributes.getInteger(3, 80);
        obtainStyledAttributes.recycle();
        this.mTextColor = this.mCurrText.getTextColors().getDefaultColor();
        setNonPrimaryAlpha(0.6f);
        TextView textView4 = this.mPrevText;
        TextUtils.TruncateAt truncateAt = TextUtils.TruncateAt.END;
        textView4.setEllipsize(truncateAt);
        this.mCurrText.setEllipsize(truncateAt);
        this.mNextText.setEllipsize(truncateAt);
        if (resourceId != 0) {
            TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(resourceId, TEXT_ATTRS);
            boolean z = obtainStyledAttributes2.getBoolean(0, false);
            obtainStyledAttributes2.recycle();
            if (z) {
                setSingleLineAllCaps(this.mPrevText);
                setSingleLineAllCaps(this.mCurrText);
                setSingleLineAllCaps(this.mNextText);
                this.mScaledTextSpacing = (int) (context.getResources().getDisplayMetrics().density * 16.0f);
            }
        }
        this.mPrevText.setSingleLine();
        this.mCurrText.setSingleLine();
        this.mNextText.setSingleLine();
        this.mScaledTextSpacing = (int) (context.getResources().getDisplayMetrics().density * 16.0f);
    }
}
