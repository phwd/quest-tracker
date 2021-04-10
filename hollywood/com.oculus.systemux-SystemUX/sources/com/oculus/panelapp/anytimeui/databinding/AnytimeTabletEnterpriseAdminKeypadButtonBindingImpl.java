package com.oculus.panelapp.anytimeui.databinding;

import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.oculus.ocui.OCButton;
import com.oculus.panelapp.anytimeui.BR;
import com.oculus.panelapp.anytimeui.v2.tablet.profile.enterprise.BindingUtils;

public class AnytimeTabletEnterpriseAdminKeypadButtonBindingImpl extends AnytimeTabletEnterpriseAdminKeypadButtonBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    @NonNull
    private final LinearLayout mboundView0;

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    public AnytimeTabletEnterpriseAdminKeypadButtonBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 3, sIncludes, sViewsWithIds));
    }

    private AnytimeTabletEnterpriseAdminKeypadButtonBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (OCButton) objArr[1], (OCButton) objArr[2]);
        this.mDirtyFlags = -1;
        this.adminKeypadButton.setTag(null);
        this.adminKeypadButtonDisabled.setTag(null);
        this.mboundView0 = (LinearLayout) objArr[0];
        this.mboundView0.setTag(null);
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
        if (BR.paddingStartDip == i) {
            setPaddingStartDip((Integer) obj);
        } else if (BR.label == i) {
            setLabel((String) obj);
        } else if (BR.drawableStart == i) {
            setDrawableStart((Drawable) obj);
        } else if (BR.enabled != i) {
            return false;
        } else {
            setEnabled((Boolean) obj);
        }
        return true;
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeTabletEnterpriseAdminKeypadButtonBinding
    public void setPaddingStartDip(@Nullable Integer num) {
        this.mPaddingStartDip = num;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.paddingStartDip);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeTabletEnterpriseAdminKeypadButtonBinding
    public void setLabel(@Nullable String str) {
        this.mLabel = str;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.label);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeTabletEnterpriseAdminKeypadButtonBinding
    public void setDrawableStart(@Nullable Drawable drawable) {
        this.mDrawableStart = drawable;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(BR.drawableStart);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeTabletEnterpriseAdminKeypadButtonBinding
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
        int i;
        long j2;
        long j3;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        Integer num = this.mPaddingStartDip;
        String str = this.mLabel;
        Drawable drawable = this.mDrawableStart;
        Boolean bool = this.mEnabled;
        int i2 = ((j & 24) > 0 ? 1 : ((j & 24) == 0 ? 0 : -1));
        boolean z2 = true;
        int i3 = 0;
        if (i2 != 0) {
            z = bool == null;
            if (i2 != 0) {
                j |= z ? 64 : 32;
            }
        } else {
            z = false;
        }
        int i4 = ((j & 24) > 0 ? 1 : ((j & 24) == 0 ? 0 : -1));
        if (i4 != 0) {
            if (!z) {
                z2 = bool.booleanValue();
            }
            if (i4 != 0) {
                if (z2) {
                    j3 = j | 256;
                    j2 = 1024;
                } else {
                    j3 = j | 128;
                    j2 = 512;
                }
                j = j3 | j2;
            }
            i = z2 ? 8 : 0;
            if (!z2) {
                i3 = 8;
            }
        } else {
            i = 0;
        }
        if ((18 & j) != 0) {
            TextViewBindingAdapter.setText(this.adminKeypadButton, str);
            TextViewBindingAdapter.setText(this.adminKeypadButtonDisabled, str);
        }
        if ((j & 24) != 0) {
            this.adminKeypadButton.setVisibility(i3);
            this.adminKeypadButtonDisabled.setVisibility(i);
        }
        if ((20 & j) != 0) {
            TextViewBindingAdapter.setDrawableStart(this.adminKeypadButton, drawable);
            TextViewBindingAdapter.setDrawableStart(this.adminKeypadButtonDisabled, drawable);
        }
        if ((j & 17) != 0) {
            BindingUtils.updatePaddingStartDip(this.adminKeypadButton, num);
            BindingUtils.updatePaddingStartDip(this.adminKeypadButtonDisabled, num);
        }
    }
}
