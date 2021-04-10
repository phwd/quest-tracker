package androidx.viewpager.widget;

import X.AnonymousClass04J;
import X.AnonymousClass0Cd;
import X.AnonymousClass0Ce;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PagerTabStrip extends PagerTitleStrip {
    public static final int FULL_UNDERLINE_HEIGHT = 1;
    public static final int INDICATOR_HEIGHT = 3;
    public static final int MIN_PADDING_BOTTOM = 6;
    public static final int MIN_STRIP_HEIGHT = 32;
    public static final int MIN_TEXT_SPACING = 64;
    public static final int TAB_PADDING = 16;
    public static final int TAB_SPACING = 32;
    public boolean mDrawFullUnderline;
    public boolean mDrawFullUnderlineSet;
    public int mFullUnderlineHeight;
    public boolean mIgnoreTap;
    public int mIndicatorColor;
    public int mIndicatorHeight;
    public float mInitialMotionX;
    public float mInitialMotionY;
    public int mMinPaddingBottom;
    public int mMinStripHeight;
    public int mMinTextSpacing;
    public int mTabAlpha;
    public int mTabPadding;
    public final Paint mTabPaint;
    public int mTouchSlop;

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();
        int left = this.mCurrText.getLeft() - this.mTabPadding;
        int right = this.mCurrText.getRight() + this.mTabPadding;
        int i = height - this.mIndicatorHeight;
        this.mTabPaint.setColor((this.mTabAlpha << 24) | (this.mIndicatorColor & 16777215));
        float f = (float) height;
        canvas.drawRect((float) left, (float) i, (float) right, f, this.mTabPaint);
        if (this.mDrawFullUnderline) {
            this.mTabPaint.setColor(-16777216 | (this.mIndicatorColor & 16777215));
            canvas.drawRect((float) getPaddingLeft(), (float) (height - this.mFullUnderlineHeight), (float) (getWidth() - getPaddingRight()), f, this.mTabPaint);
        }
    }

    public void setDrawFullUnderline(boolean z) {
        this.mDrawFullUnderline = z;
        this.mDrawFullUnderlineSet = true;
        invalidate();
    }

    public void setPadding(int i, int i2, int i3, int i4) {
        int i5 = this.mMinPaddingBottom;
        if (i4 < i5) {
            i4 = i5;
        }
        super.setPadding(i, i2, i3, i4);
    }

    public void setTabIndicatorColor(@ColorInt int i) {
        this.mIndicatorColor = i;
        this.mTabPaint.setColor(i);
        invalidate();
    }

    @Override // androidx.viewpager.widget.PagerTitleStrip
    public void setTextSpacing(int i) {
        int i2 = this.mMinTextSpacing;
        if (i < i2) {
            i = i2;
        }
        super.setTextSpacing(i);
    }

    public boolean getDrawFullUnderline() {
        return this.mDrawFullUnderline;
    }

    @Override // androidx.viewpager.widget.PagerTitleStrip
    public int getMinHeight() {
        return Math.max(super.getMinHeight(), this.mMinStripHeight);
    }

    @ColorInt
    public int getTabIndicatorColor() {
        return this.mIndicatorColor;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        ViewPager viewPager;
        int i;
        int action = motionEvent.getAction();
        if (action != 0 && this.mIgnoreTap) {
            return false;
        }
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        if (action != 0) {
            if (action == 1) {
                if (x < ((float) (this.mCurrText.getLeft() - this.mTabPadding))) {
                    viewPager = this.mPager;
                    i = viewPager.mCurItem - 1;
                } else if (x > ((float) (this.mCurrText.getRight() + this.mTabPadding))) {
                    viewPager = this.mPager;
                    i = viewPager.mCurItem + 1;
                }
                viewPager.setCurrentItem(i);
                return true;
            } else if (action == 2) {
                float abs = Math.abs(x - this.mInitialMotionX);
                float f = (float) this.mTouchSlop;
                if (abs > f || Math.abs(y - this.mInitialMotionY) > f) {
                    this.mIgnoreTap = true;
                }
            }
            return true;
        }
        this.mInitialMotionX = x;
        this.mInitialMotionY = y;
        this.mIgnoreTap = false;
        return true;
    }

    public void setBackgroundColor(@ColorInt int i) {
        super.setBackgroundColor(i);
        if (!this.mDrawFullUnderlineSet) {
            boolean z = false;
            if ((i & -16777216) == 0) {
                z = true;
            }
            this.mDrawFullUnderline = z;
        }
    }

    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        if (!this.mDrawFullUnderlineSet) {
            boolean z = false;
            if (drawable == null) {
                z = true;
            }
            this.mDrawFullUnderline = z;
        }
    }

    public void setBackgroundResource(@DrawableRes int i) {
        super.setBackgroundResource(i);
        if (!this.mDrawFullUnderlineSet) {
            boolean z = false;
            if (i == 0) {
                z = true;
            }
            this.mDrawFullUnderline = z;
        }
    }

    public void setTabIndicatorColorResource(@ColorRes int i) {
        setTabIndicatorColor(AnonymousClass04J.A00(getContext(), i));
    }

    @Override // androidx.viewpager.widget.PagerTitleStrip
    public void updateTextPositions(int i, float f, boolean z) {
        super.updateTextPositions(i, f, z);
        this.mTabAlpha = (int) (Math.abs(f - 0.5f) * 2.0f * 255.0f);
        invalidate();
    }

    public PagerTabStrip(@NonNull Context context) {
        this(context, null);
    }

    public PagerTabStrip(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        Paint paint = new Paint();
        this.mTabPaint = paint;
        this.mTabAlpha = 255;
        this.mDrawFullUnderline = false;
        this.mDrawFullUnderlineSet = false;
        int i = this.mTextColor;
        this.mIndicatorColor = i;
        paint.setColor(i);
        float f = context.getResources().getDisplayMetrics().density;
        this.mIndicatorHeight = (int) ((3.0f * f) + 0.5f);
        this.mMinPaddingBottom = (int) ((6.0f * f) + 0.5f);
        this.mMinTextSpacing = (int) (64.0f * f);
        this.mTabPadding = (int) ((16.0f * f) + 0.5f);
        this.mFullUnderlineHeight = (int) ((1.0f * f) + 0.5f);
        this.mMinStripHeight = (int) ((f * 32.0f) + 0.5f);
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
        setTextSpacing(this.mScaledTextSpacing);
        setWillNotDraw(false);
        this.mPrevText.setFocusable(true);
        this.mPrevText.setOnClickListener(new AnonymousClass0Cd(this));
        this.mNextText.setFocusable(true);
        this.mNextText.setOnClickListener(new AnonymousClass0Ce(this));
        if (getBackground() == null) {
            this.mDrawFullUnderline = true;
        }
    }
}
