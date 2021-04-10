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
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsNavigationActionType;

public abstract class SettingsNavigationActionViewBinding extends ViewDataBinding {
    @NonNull
    public final View button;
    @Bindable
    protected SettingsNavigationActionType mNavigationActionType;

    public abstract void setNavigationActionType(@Nullable SettingsNavigationActionType settingsNavigationActionType);

    protected SettingsNavigationActionViewBinding(Object obj, View view, int i, View view2) {
        super(obj, view, i);
        this.button = view2;
    }

    @Nullable
    public SettingsNavigationActionType getNavigationActionType() {
        return this.mNavigationActionType;
    }

    @NonNull
    public static SettingsNavigationActionViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SettingsNavigationActionViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (SettingsNavigationActionViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.settings_navigation_action_view, viewGroup, z, obj);
    }

    @NonNull
    public static SettingsNavigationActionViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SettingsNavigationActionViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (SettingsNavigationActionViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.settings_navigation_action_view, null, false, obj);
    }

    public static SettingsNavigationActionViewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SettingsNavigationActionViewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SettingsNavigationActionViewBinding) bind(obj, view, R.layout.settings_navigation_action_view);
    }
}
