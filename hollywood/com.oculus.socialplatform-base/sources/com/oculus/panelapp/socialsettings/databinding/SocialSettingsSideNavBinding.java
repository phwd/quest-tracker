package com.oculus.panelapp.socialsettings.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.ocui.OCRecyclerView;
import com.oculus.socialplatform.R;

public abstract class SocialSettingsSideNavBinding extends AnonymousClass1uW {
    @NonNull
    public final OCRecyclerView recyclerView;

    public SocialSettingsSideNavBinding(Object obj, View view, int i, OCRecyclerView oCRecyclerView) {
        super(obj, view, i);
        this.recyclerView = oCRecyclerView;
    }

    public static SocialSettingsSideNavBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static SocialSettingsSideNavBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SocialSettingsSideNavBinding) AnonymousClass1uW.bind(obj, view, R.layout.social_settings_side_nav);
    }

    @NonNull
    public static SocialSettingsSideNavBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static SocialSettingsSideNavBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static SocialSettingsSideNavBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (SocialSettingsSideNavBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.social_settings_side_nav, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static SocialSettingsSideNavBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (SocialSettingsSideNavBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.social_settings_side_nav, null, false);
    }
}
