package com.oculus.panelapp.anytimeui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCSelect;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsDropdownActionType;

public abstract class SettingsDropdownActionViewBinding extends ViewDataBinding {
    @NonNull
    public final OCSelect dropdown;
    @Bindable
    protected SettingsDropdownActionType mDropdownAction;

    public abstract void setDropdownAction(@Nullable SettingsDropdownActionType settingsDropdownActionType);

    protected SettingsDropdownActionViewBinding(Object obj, View view, int i, OCSelect oCSelect) {
        super(obj, view, i);
        this.dropdown = oCSelect;
    }

    @Nullable
    public SettingsDropdownActionType getDropdownAction() {
        return this.mDropdownAction;
    }

    @NonNull
    public static SettingsDropdownActionViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SettingsDropdownActionViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (SettingsDropdownActionViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.settings_dropdown_action_view, viewGroup, z, obj);
    }

    @NonNull
    public static SettingsDropdownActionViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SettingsDropdownActionViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (SettingsDropdownActionViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.settings_dropdown_action_view, null, false, obj);
    }

    public static SettingsDropdownActionViewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SettingsDropdownActionViewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SettingsDropdownActionViewBinding) bind(obj, view, R.layout.settings_dropdown_action_view);
    }
}
