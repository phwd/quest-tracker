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

public abstract class AnytimeTabletInternalSettingsNavViewV2Binding extends ViewDataBinding {
    @NonNull
    public final OCSideNav sideNav;

    protected AnytimeTabletInternalSettingsNavViewV2Binding(Object obj, View view, int i, OCSideNav oCSideNav) {
        super(obj, view, i);
        this.sideNav = oCSideNav;
    }

    @NonNull
    public static AnytimeTabletInternalSettingsNavViewV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletInternalSettingsNavViewV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AnytimeTabletInternalSettingsNavViewV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_internal_settings_nav_view_v2, viewGroup, z, obj);
    }

    @NonNull
    public static AnytimeTabletInternalSettingsNavViewV2Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletInternalSettingsNavViewV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AnytimeTabletInternalSettingsNavViewV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_internal_settings_nav_view_v2, null, false, obj);
    }

    public static AnytimeTabletInternalSettingsNavViewV2Binding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AnytimeTabletInternalSettingsNavViewV2Binding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletInternalSettingsNavViewV2Binding) bind(obj, view, R.layout.anytime_tablet_internal_settings_nav_view_v2);
    }
}
