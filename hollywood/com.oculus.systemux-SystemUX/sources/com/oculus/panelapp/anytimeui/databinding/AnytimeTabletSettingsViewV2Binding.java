package com.oculus.panelapp.anytimeui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCNotchedSlider;
import com.oculus.ocui.OCTileButton;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.bar.status.StatusViewModel;
import com.oculus.panelapp.anytimeui.v2.tablet.common.ToggleButton;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsViewModel;
import com.oculus.tablet.databinding.OsigSeekbarBinding;

public abstract class AnytimeTabletSettingsViewV2Binding extends ViewDataBinding {
    @NonNull
    public final ToggleButton buttonActivateAssistant;
    @NonNull
    public final ToggleButton buttonBugReport;
    @NonNull
    public final ToggleButton buttonDoNotDisturb;
    @NonNull
    public final OCTileButton buttonEnableLink;
    @NonNull
    public final OCTileButton buttonGuardian;
    @NonNull
    public final ToggleButton buttonHands;
    @NonNull
    public final ToggleButton buttonMicrophone;
    @NonNull
    public final ToggleButton buttonNightShift;
    @NonNull
    public final ToggleButton buttonResetView;
    @NonNull
    public final OCTileButton buttonWifi;
    @Bindable
    protected SettingsViewModel mSettingsViewModel;
    @Bindable
    protected StatusViewModel mStatusViewModel;
    @NonNull
    public final OsigSeekbarBinding seekbarBrightness;
    @NonNull
    public final OsigSeekbarBinding seekbarVolume;
    @NonNull
    public final OCNotchedSlider sliderRealityTuner;

    public abstract void setSettingsViewModel(@Nullable SettingsViewModel settingsViewModel);

    public abstract void setStatusViewModel(@Nullable StatusViewModel statusViewModel);

    protected AnytimeTabletSettingsViewV2Binding(Object obj, View view, int i, ToggleButton toggleButton, ToggleButton toggleButton2, ToggleButton toggleButton3, OCTileButton oCTileButton, OCTileButton oCTileButton2, ToggleButton toggleButton4, ToggleButton toggleButton5, ToggleButton toggleButton6, ToggleButton toggleButton7, OCTileButton oCTileButton3, OsigSeekbarBinding osigSeekbarBinding, OsigSeekbarBinding osigSeekbarBinding2, OCNotchedSlider oCNotchedSlider) {
        super(obj, view, i);
        this.buttonActivateAssistant = toggleButton;
        this.buttonBugReport = toggleButton2;
        this.buttonDoNotDisturb = toggleButton3;
        this.buttonEnableLink = oCTileButton;
        this.buttonGuardian = oCTileButton2;
        this.buttonHands = toggleButton4;
        this.buttonMicrophone = toggleButton5;
        this.buttonNightShift = toggleButton6;
        this.buttonResetView = toggleButton7;
        this.buttonWifi = oCTileButton3;
        this.seekbarBrightness = osigSeekbarBinding;
        setContainedBinding(this.seekbarBrightness);
        this.seekbarVolume = osigSeekbarBinding2;
        setContainedBinding(this.seekbarVolume);
        this.sliderRealityTuner = oCNotchedSlider;
    }

    @Nullable
    public SettingsViewModel getSettingsViewModel() {
        return this.mSettingsViewModel;
    }

    @Nullable
    public StatusViewModel getStatusViewModel() {
        return this.mStatusViewModel;
    }

    @NonNull
    public static AnytimeTabletSettingsViewV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletSettingsViewV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AnytimeTabletSettingsViewV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_settings_view_v2, viewGroup, z, obj);
    }

    @NonNull
    public static AnytimeTabletSettingsViewV2Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletSettingsViewV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AnytimeTabletSettingsViewV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_settings_view_v2, null, false, obj);
    }

    public static AnytimeTabletSettingsViewV2Binding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AnytimeTabletSettingsViewV2Binding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletSettingsViewV2Binding) bind(obj, view, R.layout.anytime_tablet_settings_view_v2);
    }
}
