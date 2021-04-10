package com.oculus.common.ocui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.common.ocui.R;

public abstract class OcplaceholderGlintBinding extends ViewDataBinding {
    @NonNull
    public final View placeholderActive;

    protected OcplaceholderGlintBinding(Object obj, View view, int i, View view2) {
        super(obj, view, i);
        this.placeholderActive = view2;
    }

    @NonNull
    public static OcplaceholderGlintBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static OcplaceholderGlintBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (OcplaceholderGlintBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.ocplaceholder_glint, viewGroup, z, obj);
    }

    @NonNull
    public static OcplaceholderGlintBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static OcplaceholderGlintBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (OcplaceholderGlintBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.ocplaceholder_glint, null, false, obj);
    }

    public static OcplaceholderGlintBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static OcplaceholderGlintBinding bind(@NonNull View view, @Nullable Object obj) {
        return (OcplaceholderGlintBinding) bind(obj, view, R.layout.ocplaceholder_glint);
    }
}
