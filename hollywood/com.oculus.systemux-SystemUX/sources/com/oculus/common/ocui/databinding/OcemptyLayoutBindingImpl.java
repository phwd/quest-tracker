package com.oculus.common.ocui.databinding;

import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.ImageViewBindingAdapter;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.oculus.common.ocui.BR;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;

public class OcemptyLayoutBindingImpl extends OcemptyLayoutBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    @NonNull
    private final RelativeLayout mboundView0;

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    public OcemptyLayoutBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 4, sIncludes, sViewsWithIds));
    }

    private OcemptyLayoutBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (OCButton) objArr[3], (OCTextView) objArr[2], (ImageView) objArr[1]);
        this.mDirtyFlags = -1;
        this.cta.setTag(null);
        this.headerText.setTag(null);
        this.mboundView0 = (RelativeLayout) objArr[0];
        this.mboundView0.setTag(null);
        this.splashImage.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8;
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
        if (BR.header == i) {
            setHeader((String) obj);
        } else if (BR.splash == i) {
            setSplash((Drawable) obj);
        } else if (BR.buttonText != i) {
            return false;
        } else {
            setButtonText((String) obj);
        }
        return true;
    }

    @Override // com.oculus.common.ocui.databinding.OcemptyLayoutBinding
    public void setHeader(@Nullable String str) {
        this.mHeader = str;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.header);
        super.requestRebind();
    }

    @Override // com.oculus.common.ocui.databinding.OcemptyLayoutBinding
    public void setSplash(@Nullable Drawable drawable) {
        this.mSplash = drawable;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.splash);
        super.requestRebind();
    }

    @Override // com.oculus.common.ocui.databinding.OcemptyLayoutBinding
    public void setButtonText(@Nullable String str) {
        this.mButtonText = str;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(BR.buttonText);
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
        String str = this.mHeader;
        Drawable drawable = this.mSplash;
        String str2 = this.mButtonText;
        int i = ((9 & j) > 0 ? 1 : ((9 & j) == 0 ? 0 : -1));
        int i2 = ((10 & j) > 0 ? 1 : ((10 & j) == 0 ? 0 : -1));
        if ((j & 12) != 0) {
            TextViewBindingAdapter.setText(this.cta, str2);
        }
        if (i != 0) {
            TextViewBindingAdapter.setText(this.headerText, str);
        }
        if (i2 != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.splashImage, drawable);
        }
    }
}
