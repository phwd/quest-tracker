package com.oculus.panelapp.anytimeui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCSideNav;
import com.oculus.panelapp.anytimeui.R;

public abstract class AnytimeTabletSettingsNavViewV2Binding extends ViewDataBinding {
    @NonNull
    public final OCSideNav sideNav;

    protected AnytimeTabletSettingsNavViewV2Binding(Object obj, View view, int i, OCSideNav oCSideNav) {
        super(obj, view, i);
        this.sideNav = oCSideNav;
    }

    @NonNull
    public static AnytimeTabletSettingsNavViewV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletSettingsNavViewV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AnytimeTabletSettingsNavViewV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_settings_nav_view_v2, viewGroup, z, obj);
    }

    @NonNull
    public static AnytimeTabletSettingsNavViewV2Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletSettingsNavViewV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AnytimeTabletSettingsNavViewV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_settings_nav_view_v2, null, false, obj);
    }

    public static AnytimeTabletSettingsNavViewV2Binding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AnytimeTabletSettingsNavViewV2Binding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletSettingsNavViewV2Binding) bind(obj, view, R.layout.anytime_tablet_settings_nav_view_v2);
    }
}
