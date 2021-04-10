package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.view.View;

/* access modifiers changed from: package-private */
public class AppCompatBackgroundHelper {
    private int mBackgroundResId = -1;
    private BackgroundTintInfo mBackgroundTint;
    private final AppCompatDrawableManager mDrawableManager;
    private BackgroundTintInfo mInternalBackgroundTint;
    private BackgroundTintInfo mTmpInfo;
    private final View mView;

    AppCompatBackgroundHelper(View view) {
        this.mView = view;
        this.mDrawableManager = AppCompatDrawableManager.get();
    }

    /* access modifiers changed from: package-private */
    public void loadFromAttributes(AttributeSet attributeSet, int i) {
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(this.mView.getContext(), attributeSet, R.styleable.ViewBackgroundHelper, i, 0);
        try {
            if (obtainStyledAttributes.hasValue(R.styleable.ViewBackgroundHelper_android_background)) {
                this.mBackgroundResId = obtainStyledAttributes.getResourceId(R.styleable.ViewBackgroundHelper_android_background, -1);
                ColorStateList tintList = this.mDrawableManager.getTintList(this.mView.getContext(), this.mBackgroundResId);
                if (tintList != null) {
                    setInternalBackgroundTint(tintList);
                }
            }
            if (obtainStyledAttributes.hasValue(R.styleable.ViewBackgroundHelper_backgroundTint)) {
                ViewCompat.setBackgroundTintList(this.mView, obtainStyledAttributes.getColorStateList(R.styleable.ViewBackgroundHelper_backgroundTint));
            }
            if (obtainStyledAttributes.hasValue(R.styleable.ViewBackgroundHelper_backgroundTintMode)) {
                ViewCompat.setBackgroundTintMode(this.mView, DrawableUtils.parseTintMode(obtainStyledAttributes.getInt(R.styleable.ViewBackgroundHelper_backgroundTintMode, -1), null));
            }
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    /* access modifiers changed from: package-private */
    public void onSetBackgroundResource(int i) {
        this.mBackgroundResId = i;
        AppCompatDrawableManager appCompatDrawableManager = this.mDrawableManager;
        setInternalBackgroundTint(appCompatDrawableManager != null ? appCompatDrawableManager.getTintList(this.mView.getContext(), i) : null);
        if (updateBackgroundTint()) {
            applySupportBackgroundTint();
        }
    }

    /* access modifiers changed from: package-private */
    public void onSetBackgroundDrawable(Drawable drawable) {
        this.mBackgroundResId = -1;
        setInternalBackgroundTint(null);
        if (updateBackgroundTint()) {
            applySupportBackgroundTint();
        }
    }

    /* access modifiers changed from: package-private */
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        if (this.mBackgroundTint == null) {
            this.mBackgroundTint = new BackgroundTintInfo();
        }
        BackgroundTintInfo backgroundTintInfo = this.mBackgroundTint;
        backgroundTintInfo.mOriginalTintList = colorStateList;
        backgroundTintInfo.mTintList = null;
        backgroundTintInfo.mHasTintList = true;
        if (updateBackgroundTint()) {
            applySupportBackgroundTint();
        }
    }

    private boolean updateBackgroundTint() {
        ColorStateList tintList;
        BackgroundTintInfo backgroundTintInfo = this.mBackgroundTint;
        if (backgroundTintInfo == null || !backgroundTintInfo.mHasTintList) {
            return false;
        }
        if (this.mBackgroundResId >= 0 && (tintList = this.mDrawableManager.getTintList(this.mView.getContext(), this.mBackgroundResId, this.mBackgroundTint.mOriginalTintList)) != null) {
            this.mBackgroundTint.mTintList = tintList;
            return true;
        } else if (this.mBackgroundTint.mTintList == this.mBackgroundTint.mOriginalTintList) {
            return false;
        } else {
            BackgroundTintInfo backgroundTintInfo2 = this.mBackgroundTint;
            backgroundTintInfo2.mTintList = backgroundTintInfo2.mOriginalTintList;
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public ColorStateList getSupportBackgroundTintList() {
        BackgroundTintInfo backgroundTintInfo = this.mBackgroundTint;
        if (backgroundTintInfo != null) {
            return backgroundTintInfo.mTintList;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        if (this.mBackgroundTint == null) {
            this.mBackgroundTint = new BackgroundTintInfo();
        }
        BackgroundTintInfo backgroundTintInfo = this.mBackgroundTint;
        backgroundTintInfo.mTintMode = mode;
        backgroundTintInfo.mHasTintMode = true;
        applySupportBackgroundTint();
    }

    /* access modifiers changed from: package-private */
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        BackgroundTintInfo backgroundTintInfo = this.mBackgroundTint;
        if (backgroundTintInfo != null) {
            return backgroundTintInfo.mTintMode;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void applySupportBackgroundTint() {
        Drawable background = this.mView.getBackground();
        if (background == null) {
            return;
        }
        if (Build.VERSION.SDK_INT != 21 || !applyFrameworkTintUsingColorFilter(background)) {
            BackgroundTintInfo backgroundTintInfo = this.mBackgroundTint;
            if (backgroundTintInfo != null) {
                AppCompatDrawableManager.tintDrawable(background, backgroundTintInfo, this.mView.getDrawableState());
                return;
            }
            BackgroundTintInfo backgroundTintInfo2 = this.mInternalBackgroundTint;
            if (backgroundTintInfo2 != null) {
                AppCompatDrawableManager.tintDrawable(background, backgroundTintInfo2, this.mView.getDrawableState());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setInternalBackgroundTint(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (this.mInternalBackgroundTint == null) {
                this.mInternalBackgroundTint = new BackgroundTintInfo();
            }
            BackgroundTintInfo backgroundTintInfo = this.mInternalBackgroundTint;
            backgroundTintInfo.mTintList = colorStateList;
            backgroundTintInfo.mHasTintList = true;
        } else {
            this.mInternalBackgroundTint = null;
        }
        applySupportBackgroundTint();
    }

    private boolean applyFrameworkTintUsingColorFilter(@NonNull Drawable drawable) {
        if (this.mTmpInfo == null) {
            this.mTmpInfo = new BackgroundTintInfo();
        }
        BackgroundTintInfo backgroundTintInfo = this.mTmpInfo;
        backgroundTintInfo.clear();
        ColorStateList backgroundTintList = ViewCompat.getBackgroundTintList(this.mView);
        if (backgroundTintList != null) {
            backgroundTintInfo.mHasTintList = true;
            backgroundTintInfo.mTintList = backgroundTintList;
        }
        PorterDuff.Mode backgroundTintMode = ViewCompat.getBackgroundTintMode(this.mView);
        if (backgroundTintMode != null) {
            backgroundTintInfo.mHasTintMode = true;
            backgroundTintInfo.mTintMode = backgroundTintMode;
        }
        if (!backgroundTintInfo.mHasTintList && !backgroundTintInfo.mHasTintMode) {
            return false;
        }
        AppCompatDrawableManager.tintDrawable(drawable, backgroundTintInfo, this.mView.getDrawableState());
        return true;
    }

    /* access modifiers changed from: private */
    public static class BackgroundTintInfo extends TintInfo {
        public ColorStateList mOriginalTintList;

        BackgroundTintInfo() {
        }

        /* access modifiers changed from: package-private */
        @Override // android.support.v7.widget.TintInfo
        public void clear() {
            super.clear();
            this.mOriginalTintList = null;
        }
    }
}
