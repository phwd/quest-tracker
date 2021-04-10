package com.oculus.common.ocui.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.ocui.OCButton;
import com.oculus.socialplatform.R;

public abstract class OcselectIconOnlyBinding extends AnonymousClass1uW {
    @NonNull
    public final OCButton dropdownIconButton;

    public OcselectIconOnlyBinding(Object obj, View view, int i, OCButton oCButton) {
        super(obj, view, i);
        this.dropdownIconButton = oCButton;
    }

    public static OcselectIconOnlyBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static OcselectIconOnlyBinding bind(@NonNull View view, @Nullable Object obj) {
        return (OcselectIconOnlyBinding) AnonymousClass1uW.bind(obj, view, R.layout.ocselect_icon_only);
    }

    @NonNull
    public static OcselectIconOnlyBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static OcselectIconOnlyBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static OcselectIconOnlyBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (OcselectIconOnlyBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.ocselect_icon_only, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static OcselectIconOnlyBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (OcselectIconOnlyBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.ocselect_icon_only, null, false);
    }
}
