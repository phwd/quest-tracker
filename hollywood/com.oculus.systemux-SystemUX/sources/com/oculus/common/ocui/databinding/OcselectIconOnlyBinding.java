package com.oculus.common.ocui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.common.ocui.R;
import com.oculus.ocui.OCButton;

public abstract class OcselectIconOnlyBinding extends ViewDataBinding {
    @NonNull
    public final OCButton dropdownIconButton;

    protected OcselectIconOnlyBinding(Object obj, View view, int i, OCButton oCButton) {
        super(obj, view, i);
        this.dropdownIconButton = oCButton;
    }

    @NonNull
    public static OcselectIconOnlyBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static OcselectIconOnlyBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (OcselectIconOnlyBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.ocselect_icon_only, viewGroup, z, obj);
    }

    @NonNull
    public static OcselectIconOnlyBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static OcselectIconOnlyBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (OcselectIconOnlyBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.ocselect_icon_only, null, false, obj);
    }

    public static OcselectIconOnlyBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static OcselectIconOnlyBinding bind(@NonNull View view, @Nullable Object obj) {
        return (OcselectIconOnlyBinding) bind(obj, view, R.layout.ocselect_icon_only);
    }
}
