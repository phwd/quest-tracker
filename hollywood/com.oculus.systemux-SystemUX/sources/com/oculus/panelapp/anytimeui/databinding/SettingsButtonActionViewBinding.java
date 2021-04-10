package com.oculus.panelapp.anytimeui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCButton;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsButtonActionType;

public abstract class SettingsButtonActionViewBinding extends ViewDataBinding {
    @NonNull
    public final OCButton button;
    @Bindable
    protected SettingsButtonActionType mButtonActionType;

    public abstract void setButtonActionType(@Nullable SettingsButtonActionType settingsButtonActionType);

    protected SettingsButtonActionViewBinding(Object obj, View view, int i, OCButton oCButton) {
        super(obj, view, i);
        this.button = oCButton;
    }

    @Nullable
    public SettingsButtonActionType getButtonActionType() {
        return this.mButtonActionType;
    }

    @NonNull
    public static SettingsButtonActionViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SettingsButtonActionViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (SettingsButtonActionViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.settings_button_action_view, viewGroup, z, obj);
    }

    @NonNull
    public static SettingsButtonActionViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SettingsButtonActionViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (SettingsButtonActionViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.settings_button_action_view, null, false, obj);
    }

    public static SettingsButtonActionViewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SettingsButtonActionViewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SettingsButtonActionViewBinding) bind(obj, view, R.layout.settings_button_action_view);
    }
}
