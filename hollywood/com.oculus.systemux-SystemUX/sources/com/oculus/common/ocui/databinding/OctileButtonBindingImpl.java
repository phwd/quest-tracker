package com.oculus.common.ocui.databinding;

import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.ImageViewBindingAdapter;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.databinding.adapters.ViewBindingAdapter;
import com.oculus.common.ocui.BR;
import com.oculus.common.ocui.R;
import com.oculus.ocui.OCTextView;

public class OctileButtonBindingImpl extends OctileButtonBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    @NonNull
    private final ConstraintLayout mboundView0;
    @NonNull
    private final OCTextView mboundView2;
    @NonNull
    private final OCTextView mboundView5;

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    static {
        sViewsWithIds.put(R.id.cta_icon, 6);
    }

    public OctileButtonBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 7, sIncludes, sViewsWithIds));
    }

    private OctileButtonBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (OCTextView) objArr[4], (ImageView) objArr[6], (OCTextView) objArr[3], (ImageView) objArr[1]);
        this.mDirtyFlags = -1;
        this.activeIndicatorView.setTag(null);
        this.mboundView0 = (ConstraintLayout) objArr[0];
        this.mboundView0.setTag(null);
        this.mboundView2 = (OCTextView) objArr[2];
        this.mboundView2.setTag(null);
        this.mboundView5 = (OCTextView) objArr[5];
        this.mboundView5.setTag(null);
        this.textView.setTag(null);
        this.titleIconImage.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 64;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return false;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.activeIndicator == i) {
            setActiveIndicator((Drawable) obj);
        } else if (BR.title == i) {
            setTitle((String) obj);
        } else if (BR.titleIcon == i) {
            setTitleIcon((Drawable) obj);
        } else if (BR.subtitle == i) {
            setSubtitle((String) obj);
        } else if (BR.ctaText == i) {
            setCtaText((String) obj);
        } else if (BR.background != i) {
            return false;
        } else {
            setBackground((Drawable) obj);
        }
        return true;
    }

    @Override // com.oculus.common.ocui.databinding.OctileButtonBinding
    public void setActiveIndicator(@Nullable Drawable drawable) {
        this.mActiveIndicator = drawable;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.activeIndicator);
        super.requestRebind();
    }

    @Override // com.oculus.common.ocui.databinding.OctileButtonBinding
    public void setTitle(@Nullable String str) {
        this.mTitle = str;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.title);
        super.requestRebind();
    }

    @Override // com.oculus.common.ocui.databinding.OctileButtonBinding
    public void setTitleIcon(@Nullable Drawable drawable) {
        this.mTitleIcon = drawable;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(BR.titleIcon);
        super.requestRebind();
    }

    @Override // com.oculus.common.ocui.databinding.OctileButtonBinding
    public void setSubtitle(@Nullable String str) {
        this.mSubtitle = str;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(BR.subtitle);
        super.requestRebind();
    }

    @Override // com.oculus.common.ocui.databinding.OctileButtonBinding
    public void setCtaText(@Nullable String str) {
        this.mCtaText = str;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(BR.ctaText);
        super.requestRebind();
    }

    @Override // com.oculus.common.ocui.databinding.OctileButtonBinding
    public void setBackground(@Nullable Drawable drawable) {
        this.mBackground = drawable;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(BR.background);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        Drawable drawable = this.mActiveIndicator;
        String str = this.mTitle;
        Drawable drawable2 = this.mTitleIcon;
        String str2 = this.mSubtitle;
        String str3 = this.mCtaText;
        Drawable drawable3 = this.mBackground;
        int i = ((65 & j) > 0 ? 1 : ((65 & j) == 0 ? 0 : -1));
        int i2 = ((66 & j) > 0 ? 1 : ((66 & j) == 0 ? 0 : -1));
        int i3 = ((68 & j) > 0 ? 1 : ((68 & j) == 0 ? 0 : -1));
        int i4 = ((72 & j) > 0 ? 1 : ((72 & j) == 0 ? 0 : -1));
        int i5 = ((80 & j) > 0 ? 1 : ((80 & j) == 0 ? 0 : -1));
        int i6 = ((j & 96) > 0 ? 1 : ((j & 96) == 0 ? 0 : -1));
        if (i != 0) {
            TextViewBindingAdapter.setDrawableTop(this.activeIndicatorView, drawable);
        }
        if (i6 != 0) {
            ViewBindingAdapter.setBackground(this.mboundView0, drawable3);
        }
        if (i2 != 0) {
            TextViewBindingAdapter.setText(this.mboundView2, str);
        }
        if (i5 != 0) {
            TextViewBindingAdapter.setText(this.mboundView5, str3);
        }
        if (i4 != 0) {
            TextViewBindingAdapter.setText(this.textView, str2);
        }
        if (i3 != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.titleIconImage, drawable2);
        }
    }
}
