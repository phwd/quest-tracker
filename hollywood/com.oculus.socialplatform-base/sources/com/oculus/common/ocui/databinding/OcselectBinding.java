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

public abstract class OcselectBinding extends AnonymousClass1uW {
    @NonNull
    public final OCButton dropdownButton;

    public OcselectBinding(Object obj, View view, int i, OCButton oCButton) {
        super(obj, view, i);
        this.dropdownButton = oCButton;
    }

    public static OcselectBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static OcselectBinding bind(@NonNull View view, @Nullable Object obj) {
        return (OcselectBinding) AnonymousClass1uW.bind(obj, view, R.layout.ocselect);
    }

    @NonNull
    public static OcselectBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static OcselectBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static OcselectBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (OcselectBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.ocselect, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static OcselectBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (OcselectBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.ocselect, null, false);
    }
}
