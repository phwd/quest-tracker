package com.oculus.panelapp.socialsettings.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import com.oculus.common.socialtablet.navbar.SocialTabletSideNav;
import com.oculus.panelapp.socialsettings.views.SocialSettingsActiveStatus;
import com.oculus.panelapp.socialsettings.views.SocialSettingsMessengerAccount;
import com.oculus.panelapp.socialsettings.views.SocialSettingsNotifications;
import com.oculus.panelapp.socialsettings.views.SocialSettingsSideNav;
import com.oculus.panelapp.socialsettings.views.SocialSettingsView;
import com.oculus.panelapp.socialsettings.views.SocialSettingsViewModel;
import com.oculus.socialplatform.R;

public abstract class SocialSettingsTabletMainBinding extends AnonymousClass1uW {
    @Bindable
    public SocialSettingsViewModel mViewModel;
    @NonNull
    public final SocialSettingsActiveStatus socialSettingsActiveStatus;
    @NonNull
    public final SocialSettingsMessengerAccount socialSettingsMessengerAccount;
    @NonNull
    public final SocialSettingsNotifications socialSettingsNotifications;
    @NonNull
    public final SocialSettingsSideNav socialSettingsSideNav;
    @NonNull
    public final SocialSettingsView socialSettingsView;
    @NonNull
    public final SocialTabletSideNav socialTabletSideNav;

    public abstract void setViewModel(@Nullable SocialSettingsViewModel socialSettingsViewModel);

    @Nullable
    public SocialSettingsViewModel getViewModel() {
        return this.mViewModel;
    }

    public SocialSettingsTabletMainBinding(Object obj, View view, int i, SocialSettingsActiveStatus socialSettingsActiveStatus2, SocialSettingsMessengerAccount socialSettingsMessengerAccount2, SocialSettingsNotifications socialSettingsNotifications2, SocialSettingsSideNav socialSettingsSideNav2, SocialSettingsView socialSettingsView2, SocialTabletSideNav socialTabletSideNav2) {
        super(obj, view, i);
        this.socialSettingsActiveStatus = socialSettingsActiveStatus2;
        this.socialSettingsMessengerAccount = socialSettingsMessengerAccount2;
        this.socialSettingsNotifications = socialSettingsNotifications2;
        this.socialSettingsSideNav = socialSettingsSideNav2;
        this.socialSettingsView = socialSettingsView2;
        this.socialTabletSideNav = socialTabletSideNav2;
    }

    public static SocialSettingsTabletMainBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static SocialSettingsTabletMainBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SocialSettingsTabletMainBinding) AnonymousClass1uW.bind(obj, view, R.layout.social_settings_tablet_main);
    }

    @NonNull
    public static SocialSettingsTabletMainBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static SocialSettingsTabletMainBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static SocialSettingsTabletMainBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (SocialSettingsTabletMainBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.social_settings_tablet_main, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static SocialSettingsTabletMainBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (SocialSettingsTabletMainBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.social_settings_tablet_main, null, false);
    }
}
