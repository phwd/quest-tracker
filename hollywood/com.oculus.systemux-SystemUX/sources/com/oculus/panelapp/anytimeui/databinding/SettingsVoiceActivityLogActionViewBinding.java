package com.oculus.panelapp.anytimeui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCButton;
import com.oculus.panelapp.anytimeui.R;

public abstract class SettingsVoiceActivityLogActionViewBinding extends ViewDataBinding {
    @NonNull
    public final OCButton primaryButton;
    @NonNull
    public final OCButton secondaryButton;

    protected SettingsVoiceActivityLogActionViewBinding(Object obj, View view, int i, OCButton oCButton, OCButton oCButton2) {
        super(obj, view, i);
        this.primaryButton = oCButton;
        this.secondaryButton = oCButton2;
    }

    @NonNull
    public static SettingsVoiceActivityLogActionViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SettingsVoiceActivityLogActionViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (SettingsVoiceActivityLogActionViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.settings_voice_activity_log_action_view, viewGroup, z, obj);
    }

    @NonNull
    public static SettingsVoiceActivityLogActionViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SettingsVoiceActivityLogActionViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (SettingsVoiceActivityLogActionViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.settings_voice_activity_log_action_view, null, false, obj);
    }

    public static SettingsVoiceActivityLogActionViewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SettingsVoiceActivityLogActionViewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SettingsVoiceActivityLogActionViewBinding) bind(obj, view, R.layout.settings_voice_activity_log_action_view);
    }
}
