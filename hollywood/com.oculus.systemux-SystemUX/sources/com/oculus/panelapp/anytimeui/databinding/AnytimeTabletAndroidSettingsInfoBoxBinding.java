package com.oculus.panelapp.anytimeui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCInfo;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsInfoBox;

public abstract class AnytimeTabletAndroidSettingsInfoBoxBinding extends ViewDataBinding {
    @NonNull
    public final OCInfo info;
    @Bindable
    protected SettingsInfoBox mInfoBox;

    public abstract void setInfoBox(@Nullable SettingsInfoBox settingsInfoBox);

    protected AnytimeTabletAndroidSettingsInfoBoxBinding(Object obj, View view, int i, OCInfo oCInfo) {
        super(obj, view, i);
        this.info = oCInfo;
    }

    @Nullable
    public SettingsInfoBox getInfoBox() {
        return this.mInfoBox;
    }

    @NonNull
    public static AnytimeTabletAndroidSettingsInfoBoxBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletAndroidSettingsInfoBoxBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AnytimeTabletAndroidSettingsInfoBoxBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_android_settings_info_box, viewGroup, z, obj);
    }

    @NonNull
    public static AnytimeTabletAndroidSettingsInfoBoxBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletAndroidSettingsInfoBoxBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AnytimeTabletAndroidSettingsInfoBoxBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_android_settings_info_box, null, false, obj);
    }

    public static AnytimeTabletAndroidSettingsInfoBoxBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AnytimeTabletAndroidSettingsInfoBoxBinding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletAndroidSettingsInfoBoxBinding) bind(obj, view, R.layout.anytime_tablet_android_settings_info_box);
    }
}
