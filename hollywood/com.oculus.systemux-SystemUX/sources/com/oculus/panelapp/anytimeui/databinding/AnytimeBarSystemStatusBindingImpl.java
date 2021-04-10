package com.oculus.panelapp.anytimeui.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.facebook.drawee.view.SimpleDraweeView;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;

public class AnytimeBarSystemStatusBindingImpl extends AnytimeBarSystemStatusBinding {
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

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        return true;
    }

    public AnytimeBarSystemStatusBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View[] viewArr) {
        this(dataBindingComponent, viewArr, mapBindings(dataBindingComponent, viewArr, 7, sIncludes, sViewsWithIds));
    }

    private AnytimeBarSystemStatusBindingImpl(DataBindingComponent dataBindingComponent, View[] viewArr, Object[] objArr) {
        super(dataBindingComponent, viewArr[0], 0, (ImageView) objArr[5], (OCButton) objArr[3], (OCButton) objArr[1], (SimpleDraweeView) objArr[2], (OCButton) objArr[0], (OCTextView) objArr[4], (ImageView) objArr[6]);
        this.mDirtyFlags = -1;
        this.batteryIcon.setTag(null);
        this.notificationsButton.setTag(null);
        this.profileButton.setTag(null);
        this.profilePhoto.setTag(null);
        this.quickActionsButton.setTag(null);
        this.systemTime.setTag(null);
        this.wifiIcon.setTag(null);
        setRootTag(viewArr);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 1;
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

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        synchronized (this) {
            long j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
    }
}
