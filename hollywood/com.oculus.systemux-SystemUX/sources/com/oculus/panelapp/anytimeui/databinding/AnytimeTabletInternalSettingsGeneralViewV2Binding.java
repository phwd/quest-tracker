package com.oculus.panelapp.anytimeui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.tablet.common.ToggleButton;
import com.oculus.panelapp.anytimeui.v2.tablet.internalsettings.InternalSettingsEditText;

public abstract class AnytimeTabletInternalSettingsGeneralViewV2Binding extends ViewDataBinding {
    @NonNull
    public final OCButton buttonFilePreviewerDebug;
    @NonNull
    public final OCButton debugFilePickerButton;
    @NonNull
    public final OCTextView debugFilePickerSelectedFile;
    @NonNull
    public final OCTextView debugFilePickerSelectedFileTitle;
    @NonNull
    public final OCTextView debugFilePickerTitle;
    @NonNull
    public final OCTextView debugFilePreviewerTitle;
    @NonNull
    public final InternalSettingsEditText debugKeyboardTestInput;
    @NonNull
    public final OCTextView debugKeyboardTestTitle;
    @NonNull
    public final ToggleButton debugLaunchAppsInTabletSpaceButton;
    @NonNull
    public final OCTextView debugLaunchAppsInTabletSpaceTitle;
    @NonNull
    public final ToggleButton debugPinDebugBarButton;
    @NonNull
    public final OCTextView debugPinDebugBarTitle;
    @NonNull
    public final OCButton debugShellSettingsButton;
    @NonNull
    public final OCTextView debugShellSettingsTitle;
    @NonNull
    public final OCButton onboardingTutorial;
    @NonNull
    public final OCTextView onboardingTutorialTitle;

    protected AnytimeTabletInternalSettingsGeneralViewV2Binding(Object obj, View view, int i, OCButton oCButton, OCButton oCButton2, OCTextView oCTextView, OCTextView oCTextView2, OCTextView oCTextView3, OCTextView oCTextView4, InternalSettingsEditText internalSettingsEditText, OCTextView oCTextView5, ToggleButton toggleButton, OCTextView oCTextView6, ToggleButton toggleButton2, OCTextView oCTextView7, OCButton oCButton3, OCTextView oCTextView8, OCButton oCButton4, OCTextView oCTextView9) {
        super(obj, view, i);
        this.buttonFilePreviewerDebug = oCButton;
        this.debugFilePickerButton = oCButton2;
        this.debugFilePickerSelectedFile = oCTextView;
        this.debugFilePickerSelectedFileTitle = oCTextView2;
        this.debugFilePickerTitle = oCTextView3;
        this.debugFilePreviewerTitle = oCTextView4;
        this.debugKeyboardTestInput = internalSettingsEditText;
        this.debugKeyboardTestTitle = oCTextView5;
        this.debugLaunchAppsInTabletSpaceButton = toggleButton;
        this.debugLaunchAppsInTabletSpaceTitle = oCTextView6;
        this.debugPinDebugBarButton = toggleButton2;
        this.debugPinDebugBarTitle = oCTextView7;
        this.debugShellSettingsButton = oCButton3;
        this.debugShellSettingsTitle = oCTextView8;
        this.onboardingTutorial = oCButton4;
        this.onboardingTutorialTitle = oCTextView9;
    }

    @NonNull
    public static AnytimeTabletInternalSettingsGeneralViewV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletInternalSettingsGeneralViewV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AnytimeTabletInternalSettingsGeneralViewV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_internal_settings_general_view_v2, viewGroup, z, obj);
    }

    @NonNull
    public static AnytimeTabletInternalSettingsGeneralViewV2Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletInternalSettingsGeneralViewV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AnytimeTabletInternalSettingsGeneralViewV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_internal_settings_general_view_v2, null, false, obj);
    }

    public static AnytimeTabletInternalSettingsGeneralViewV2Binding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AnytimeTabletInternalSettingsGeneralViewV2Binding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletInternalSettingsGeneralViewV2Binding) bind(obj, view, R.layout.anytime_tablet_internal_settings_general_view_v2);
    }
}
