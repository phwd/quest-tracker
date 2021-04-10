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
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsComplexButtonActionType;

public abstract class SettingsComplexButtonActionViewBinding extends ViewDataBinding {
    @NonNull
    public final OCButton button;
    @Bindable
    protected SettingsComplexButtonActionType mComplexButtonActionType;

    public abstract void setComplexButtonActionType(@Nullable SettingsComplexButtonActionType settingsComplexButtonActionType);

    protected SettingsComplexButtonActionViewBinding(Object obj, View view, int i, OCButton oCButton) {
        super(obj, view, i);
        this.button = oCButton;
    }

    @Nullable
    public SettingsComplexButtonActionType getComplexButtonActionType() {
        return this.mComplexButtonActionType;
    }

    @NonNull
    public static SettingsComplexButtonActionViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SettingsComplexButtonActionViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (SettingsComplexButtonActionViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.settings_complex_button_action_view, viewGroup, z, obj);
    }

    @NonNull
    public static SettingsComplexButtonActionViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SettingsComplexButtonActionViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (SettingsComplexButtonActionViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.settings_complex_button_action_view, null, false, obj);
    }

    public static SettingsComplexButtonActionViewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SettingsComplexButtonActionViewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SettingsComplexButtonActionViewBinding) bind(obj, view, R.layout.settings_complex_button_action_view);
    }
}
