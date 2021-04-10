package com.oculus.panelapp.anytimeui.databinding;

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
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.anytimeui.BR;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.vrshell.SystemUXRoute;

public class AnytimeBarNavigationButtonV2BindingImpl extends AnytimeBarNavigationButtonV2Binding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    @NonNull
    private final ConstraintLayout mboundView0;

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    static {
        sViewsWithIds.put(R.id.button, 3);
        sViewsWithIds.put(R.id.badge, 4);
    }

    public AnytimeBarNavigationButtonV2BindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 5, sIncludes, sViewsWithIds));
    }

    private AnytimeBarNavigationButtonV2BindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (View) objArr[4], (OCButton) objArr[3], (ImageView) objArr[1], (OCTextView) objArr[2]);
        this.mDirtyFlags = -1;
        this.buttonIcon.setTag(null);
        this.buttonLabel.setTag(null);
        this.mboundView0 = (ConstraintLayout) objArr[0];
        this.mboundView0.setTag(null);
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
        if (BR.route == i) {
            setRoute((SystemUXRoute) obj);
        } else if (BR.label == i) {
            setLabel((String) obj);
        } else if (BR.icon != i) {
            return false;
        } else {
            setIcon((Drawable) obj);
        }
        return true;
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeBarNavigationButtonV2Binding
    public void setRoute(@Nullable SystemUXRoute systemUXRoute) {
        this.mRoute = systemUXRoute;
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeBarNavigationButtonV2Binding
    public void setLabel(@Nullable String str) {
        this.mLabel = str;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.label);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeBarNavigationButtonV2Binding
    public void setIcon(@Nullable Drawable drawable) {
        this.mIcon = drawable;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(BR.icon);
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
        String str = this.mLabel;
        Drawable drawable = this.mIcon;
        int i = ((10 & j) > 0 ? 1 : ((10 & j) == 0 ? 0 : -1));
        if ((j & 12) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.buttonIcon, drawable);
        }
        if (i != 0) {
            TextViewBindingAdapter.setText(this.buttonLabel, str);
        }
    }
}
