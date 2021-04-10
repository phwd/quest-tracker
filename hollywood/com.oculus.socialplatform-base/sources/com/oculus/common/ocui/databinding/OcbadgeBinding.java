package com.oculus.common.ocui.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import com.oculus.ocui.OCTextView;
import com.oculus.socialplatform.R;

public abstract class OcbadgeBinding extends AnonymousClass1uW {
    @Bindable
    public String mBadgeCount;
    @NonNull
    public final OCTextView text;

    public abstract void setBadgeCount(@Nullable String str);

    @Nullable
    public String getBadgeCount() {
        return this.mBadgeCount;
    }

    public OcbadgeBinding(Object obj, View view, int i, OCTextView oCTextView) {
        super(obj, view, i);
        this.text = oCTextView;
    }

    public static OcbadgeBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static OcbadgeBinding bind(@NonNull View view, @Nullable Object obj) {
        return (OcbadgeBinding) AnonymousClass1uW.bind(obj, view, R.layout.ocbadge);
    }

    @NonNull
    public static OcbadgeBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static OcbadgeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static OcbadgeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (OcbadgeBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.ocbadge, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static OcbadgeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (OcbadgeBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.ocbadge, null, false);
    }
}
