package com.oculus.tablet.databinding;

import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.oculus.ocui.OCButton;
import com.oculus.tablet.BR;

public class OsigButtonBorderlessBindingImpl extends OsigButtonBorderlessBinding {
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

    public OsigButtonBorderlessBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 3, sIncludes, sViewsWithIds));
    }

    private OsigButtonBorderlessBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (OCButton) objArr[1], (View) objArr[2], (ConstraintLayout) objArr[0]);
        this.mDirtyFlags = -1;
        this.button.setTag(null);
        this.buttonForeground.setTag(null);
        this.container.setTag(null);
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
        if (BR.icon == i) {
            setIcon((Drawable) obj);
        } else if (BR.activeIndicator == i) {
            setActiveIndicator((Drawable) obj);
        } else if (BR.text == i) {
            setText((String) obj);
        } else if (BR.enabled != i) {
            return false;
        } else {
            setEnabled((Boolean) obj);
        }
        return true;
    }

    @Override // com.oculus.tablet.databinding.OsigButtonBorderlessBinding
    public void setIcon(@Nullable Drawable drawable) {
        this.mIcon = drawable;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.icon);
        super.requestRebind();
    }

    @Override // com.oculus.tablet.databinding.OsigButtonBorderlessBinding
    public void setActiveIndicator(@Nullable Drawable drawable) {
        this.mActiveIndicator = drawable;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.activeIndicator);
        super.requestRebind();
    }

    @Override // com.oculus.tablet.databinding.OsigButtonBorderlessBinding
    public void setText(@Nullable String str) {
        this.mText = str;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(BR.text);
        super.requestRebind();
    }

    @Override // com.oculus.tablet.databinding.OsigButtonBorderlessBinding
    public void setEnabled(@Nullable Boolean bool) {
        this.mEnabled = bool;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(BR.enabled);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j;
        boolean z;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        Drawable drawable = this.mIcon;
        Drawable drawable2 = this.mActiveIndicator;
        String str = this.mText;
        Boolean bool = this.mEnabled;
        int i = ((j & 24) > 0 ? 1 : ((j & 24) == 0 ? 0 : -1));
        boolean z2 = true;
        boolean z3 = false;
        if (i != 0) {
            z = bool != null;
            if (i != 0) {
                j |= z ? 64 : 32;
            }
        } else {
            z = false;
        }
        int i2 = ((24 & j) > 0 ? 1 : ((24 & j) == 0 ? 0 : -1));
        if (i2 != 0) {
            if (z) {
                z2 = bool.booleanValue();
            }
            z3 = ViewDataBinding.safeUnbox(Boolean.valueOf(z2));
        }
        if (i2 != 0) {
            this.button.setEnabled(z3);
        }
        if ((20 & j) != 0) {
            TextViewBindingAdapter.setText(this.button, str);
        }
        if ((17 & j) != 0) {
            TextViewBindingAdapter.setDrawableTop(this.button, drawable);
        }
        if ((j & 18) != 0 && getBuildSdkInt() >= 23) {
            this.buttonForeground.setForeground(drawable2);
        }
    }
}
