package com.oculus.panelapp.anytimeui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCToggle;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType;

public abstract class SettingsToggleActionViewBinding extends ViewDataBinding {
    @Bindable
    protected SettingsToggleActionType mToggleAction;
    @NonNull
    public final OCToggle toggle;

    public abstract void setToggleAction(@Nullable SettingsToggleActionType settingsToggleActionType);

    protected SettingsToggleActionViewBinding(Object obj, View view, int i, OCToggle oCToggle) {
        super(obj, view, i);
        this.toggle = oCToggle;
    }

    @Nullable
    public SettingsToggleActionType getToggleAction() {
        return this.mToggleAction;
    }

    @NonNull
    public static SettingsToggleActionViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SettingsToggleActionViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (SettingsToggleActionViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.settings_toggle_action_view, viewGroup, z, obj);
    }

    @NonNull
    public static SettingsToggleActionViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SettingsToggleActionViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (SettingsToggleActionViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.settings_toggle_action_view, null, false, obj);
    }

    public static SettingsToggleActionViewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SettingsToggleActionViewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SettingsToggleActionViewBinding) bind(obj, view, R.layout.settings_toggle_action_view);
    }
}
