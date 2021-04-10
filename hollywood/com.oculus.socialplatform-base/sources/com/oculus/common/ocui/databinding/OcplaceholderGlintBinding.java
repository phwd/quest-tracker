package com.oculus.common.ocui.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.socialplatform.R;

public abstract class OcplaceholderGlintBinding extends AnonymousClass1uW {
    @NonNull
    public final View placeholderActive;

    public OcplaceholderGlintBinding(Object obj, View view, int i, View view2) {
        super(obj, view, i);
        this.placeholderActive = view2;
    }

    public static OcplaceholderGlintBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static OcplaceholderGlintBinding bind(@NonNull View view, @Nullable Object obj) {
        return (OcplaceholderGlintBinding) AnonymousClass1uW.bind(obj, view, R.layout.ocplaceholder_glint);
    }

    @NonNull
    public static OcplaceholderGlintBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static OcplaceholderGlintBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static OcplaceholderGlintBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (OcplaceholderGlintBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.ocplaceholder_glint, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static OcplaceholderGlintBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (OcplaceholderGlintBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.ocplaceholder_glint, null, false);
    }
}
