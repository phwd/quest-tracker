package com.oculus.panelapp.androiddialog.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.panelapp.androiddialog.R;

public abstract class BlockDialogBulletBinding extends ViewDataBinding {
    @Bindable
    protected float mAlpha;
    @Bindable
    protected boolean mDisplayForBlocked;

    public abstract void setAlpha(float f);

    public abstract void setDisplayForBlocked(boolean z);

    protected BlockDialogBulletBinding(Object obj, View view, int i) {
        super(obj, view, i);
    }

    public float getAlpha() {
        return this.mAlpha;
    }

    public boolean getDisplayForBlocked() {
        return this.mDisplayForBlocked;
    }

    @NonNull
    public static BlockDialogBulletBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static BlockDialogBulletBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (BlockDialogBulletBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.block_dialog_bullet, viewGroup, z, obj);
    }

    @NonNull
    public static BlockDialogBulletBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static BlockDialogBulletBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (BlockDialogBulletBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.block_dialog_bullet, null, false, obj);
    }

    public static BlockDialogBulletBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BlockDialogBulletBinding bind(@NonNull View view, @Nullable Object obj) {
        return (BlockDialogBulletBinding) bind(obj, view, R.layout.block_dialog_bullet);
    }
}
