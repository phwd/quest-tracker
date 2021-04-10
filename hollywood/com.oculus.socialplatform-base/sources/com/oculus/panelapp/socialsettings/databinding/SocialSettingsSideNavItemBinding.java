package com.oculus.panelapp.socialsettings.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.ocui.OCButton;
import com.oculus.socialplatform.R;

public abstract class SocialSettingsSideNavItemBinding extends AnonymousClass1uW {
    @NonNull
    public final OCButton navButton;

    public SocialSettingsSideNavItemBinding(Object obj, View view, int i, OCButton oCButton) {
        super(obj, view, i);
        this.navButton = oCButton;
    }

    public static SocialSettingsSideNavItemBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static SocialSettingsSideNavItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SocialSettingsSideNavItemBinding) AnonymousClass1uW.bind(obj, view, R.layout.social_settings_side_nav_item);
    }

    @NonNull
    public static SocialSettingsSideNavItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static SocialSettingsSideNavItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static SocialSettingsSideNavItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (SocialSettingsSideNavItemBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.social_settings_side_nav_item, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static SocialSettingsSideNavItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (SocialSettingsSideNavItemBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.social_settings_side_nav_item, null, false);
    }
}
