package com.oculus.panelapp.androiddialog.databinding;

import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.CompoundButtonBindingAdapter;
import androidx.databinding.adapters.ImageViewBindingAdapter;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.oculus.ocui.OCTextView;
import com.oculus.ocui.OCToggle;
import com.oculus.panelapp.androiddialog.BR;

public class SocialMenuItemToggleBindingImpl extends SocialMenuItemToggleBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    public SocialMenuItemToggleBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 5, sIncludes, sViewsWithIds));
    }

    private SocialMenuItemToggleBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (OCTextView) objArr[3], (ImageView) objArr[1], (ConstraintLayout) objArr[0], (OCTextView) objArr[2], (OCToggle) objArr[4]);
        this.mDirtyFlags = -1;
        this.menuItemBottomText.setTag(null);
        this.menuItemIconImage.setTag(null);
        this.menuItemToggle.setTag(null);
        this.menuItemTopText.setTag(null);
        this.toggle.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 16;
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
        if (BR.bottomText == i) {
            setBottomText((String) obj);
        } else if (BR.topText == i) {
            setTopText((String) obj);
        } else if (BR.icon == i) {
            setIcon((Drawable) obj);
        } else if (BR.checked != i) {
            return false;
        } else {
            setChecked(((Boolean) obj).booleanValue());
        }
        return true;
    }

    @Override // com.oculus.panelapp.androiddialog.databinding.SocialMenuItemToggleBinding
    public void setBottomText(@Nullable String str) {
        this.mBottomText = str;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.bottomText);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.androiddialog.databinding.SocialMenuItemToggleBinding
    public void setTopText(@Nullable String str) {
        this.mTopText = str;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.topText);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.androiddialog.databinding.SocialMenuItemToggleBinding
    public void setIcon(@Nullable Drawable drawable) {
        this.mIcon = drawable;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(BR.icon);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.androiddialog.databinding.SocialMenuItemToggleBinding
    public void setChecked(boolean z) {
        this.mChecked = z;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(BR.checked);
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
        String str = this.mBottomText;
        String str2 = this.mTopText;
        Drawable drawable = this.mIcon;
        boolean z = this.mChecked;
        int i = ((17 & j) > 0 ? 1 : ((17 & j) == 0 ? 0 : -1));
        int i2 = ((18 & j) > 0 ? 1 : ((18 & j) == 0 ? 0 : -1));
        int i3 = ((20 & j) > 0 ? 1 : ((20 & j) == 0 ? 0 : -1));
        int i4 = ((j & 24) > 0 ? 1 : ((j & 24) == 0 ? 0 : -1));
        if (i != 0) {
            TextViewBindingAdapter.setText(this.menuItemBottomText, str);
        }
        if (i3 != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.menuItemIconImage, drawable);
        }
        if (i2 != 0) {
            TextViewBindingAdapter.setText(this.menuItemTopText, str2);
        }
        if (i4 != 0) {
            CompoundButtonBindingAdapter.setChecked(this.toggle, z);
        }
    }
}
