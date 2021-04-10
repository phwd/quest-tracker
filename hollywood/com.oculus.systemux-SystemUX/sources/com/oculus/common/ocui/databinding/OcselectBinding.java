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

public abstract class OcselectBinding extends ViewDataBinding {
    @NonNull
    public final OCButton dropdownButton;

    protected OcselectBinding(Object obj, View view, int i, OCButton oCButton) {
        super(obj, view, i);
        this.dropdownButton = oCButton;
    }

    @NonNull
    public static OcselectBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static OcselectBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (OcselectBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.ocselect, viewGroup, z, obj);
    }

    @NonNull
    public static OcselectBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static OcselectBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (OcselectBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.ocselect, null, false, obj);
    }

    public static OcselectBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static OcselectBinding bind(@NonNull View view, @Nullable Object obj) {
        return (OcselectBinding) bind(obj, view, R.layout.ocselect);
    }
}
