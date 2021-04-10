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

public abstract class OcsidenavBadgeBinding extends AnonymousClass1uW {
    @Bindable
    public String mBadgeCount;
    @NonNull
    public final OCTextView text;

    public abstract void setBadgeCount(@Nullable String str);

    @Nullable
    public String getBadgeCount() {
        return this.mBadgeCount;
    }

    public OcsidenavBadgeBinding(Object obj, View view, int i, OCTextView oCTextView) {
        super(obj, view, i);
        this.text = oCTextView;
    }

    public static OcsidenavBadgeBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static OcsidenavBadgeBinding bind(@NonNull View view, @Nullable Object obj) {
        return (OcsidenavBadgeBinding) AnonymousClass1uW.bind(obj, view, R.layout.ocsidenav_badge);
    }

    @NonNull
    public static OcsidenavBadgeBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static OcsidenavBadgeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static OcsidenavBadgeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (OcsidenavBadgeBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.ocsidenav_badge, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static OcsidenavBadgeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (OcsidenavBadgeBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.ocsidenav_badge, null, false);
    }
}
