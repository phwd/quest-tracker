package com.oculus.panelapp.anytimeui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsNullState;

public abstract class AnytimeTabletAndroidSettingsNullStateBinding extends ViewDataBinding {
    @Bindable
    protected SettingsNullState mNullState;

    public abstract void setNullState(@Nullable SettingsNullState settingsNullState);

    protected AnytimeTabletAndroidSettingsNullStateBinding(Object obj, View view, int i) {
        super(obj, view, i);
    }

    @Nullable
    public SettingsNullState getNullState() {
        return this.mNullState;
    }

    @NonNull
    public static AnytimeTabletAndroidSettingsNullStateBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletAndroidSettingsNullStateBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AnytimeTabletAndroidSettingsNullStateBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_android_settings_null_state, viewGroup, z, obj);
    }

    @NonNull
    public static AnytimeTabletAndroidSettingsNullStateBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletAndroidSettingsNullStateBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AnytimeTabletAndroidSettingsNullStateBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_android_settings_null_state, null, false, obj);
    }

    public static AnytimeTabletAndroidSettingsNullStateBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AnytimeTabletAndroidSettingsNullStateBinding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletAndroidSettingsNullStateBinding) bind(obj, view, R.layout.anytime_tablet_android_settings_null_state);
    }
}
