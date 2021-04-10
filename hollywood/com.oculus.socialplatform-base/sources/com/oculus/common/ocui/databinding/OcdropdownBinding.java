package com.oculus.common.ocui.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.ocui.OCRecyclerView;
import com.oculus.ocui.OCTextView;
import com.oculus.socialplatform.R;

public abstract class OcdropdownBinding extends AnonymousClass1uW {
    @NonNull
    public final LinearLayout container;
    @NonNull
    public final OCRecyclerView contextMenuList;
    @NonNull
    public final OCTextView contextMenuTitle;

    public OcdropdownBinding(Object obj, View view, int i, LinearLayout linearLayout, OCRecyclerView oCRecyclerView, OCTextView oCTextView) {
        super(obj, view, i);
        this.container = linearLayout;
        this.contextMenuList = oCRecyclerView;
        this.contextMenuTitle = oCTextView;
    }

    public static OcdropdownBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static OcdropdownBinding bind(@NonNull View view, @Nullable Object obj) {
        return (OcdropdownBinding) AnonymousClass1uW.bind(obj, view, R.layout.ocdropdown);
    }

    @NonNull
    public static OcdropdownBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static OcdropdownBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static OcdropdownBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (OcdropdownBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.ocdropdown, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static OcdropdownBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (OcdropdownBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.ocdropdown, null, false);
    }
}
