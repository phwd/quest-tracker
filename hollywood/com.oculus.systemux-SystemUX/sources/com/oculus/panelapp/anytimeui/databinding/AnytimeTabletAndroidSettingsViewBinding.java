package com.oculus.panelapp.anytimeui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCBackButton;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCRecyclerView;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.AndroidSettingsViewModel;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsSection;

public abstract class AnytimeTabletAndroidSettingsViewBinding extends ViewDataBinding {
    @NonNull
    public final OCBackButton backButton;
    @NonNull
    public final View internalDisclaimer;
    @Bindable
    protected SettingsSection mSection;
    @Bindable
    protected AndroidSettingsViewModel mViewModel;
    @NonNull
    public final OCRecyclerView settingsContent;
    @NonNull
    public final OCTextView title;
    @NonNull
    public final OCButton toolbarButton;
    @NonNull
    public final Guideline toolbarGuideline;

    public abstract void setSection(@Nullable SettingsSection settingsSection);

    public abstract void setViewModel(@Nullable AndroidSettingsViewModel androidSettingsViewModel);

    protected AnytimeTabletAndroidSettingsViewBinding(Object obj, View view, int i, OCBackButton oCBackButton, View view2, OCRecyclerView oCRecyclerView, OCTextView oCTextView, OCButton oCButton, Guideline guideline) {
        super(obj, view, i);
        this.backButton = oCBackButton;
        this.internalDisclaimer = view2;
        this.settingsContent = oCRecyclerView;
        this.title = oCTextView;
        this.toolbarButton = oCButton;
        this.toolbarGuideline = guideline;
    }

    @Nullable
    public SettingsSection getSection() {
        return this.mSection;
    }

    @Nullable
    public AndroidSettingsViewModel getViewModel() {
        return this.mViewModel;
    }

    @NonNull
    public static AnytimeTabletAndroidSettingsViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletAndroidSettingsViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AnytimeTabletAndroidSettingsViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_android_settings_view, viewGroup, z, obj);
    }

    @NonNull
    public static AnytimeTabletAndroidSettingsViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletAndroidSettingsViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AnytimeTabletAndroidSettingsViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_android_settings_view, null, false, obj);
    }

    public static AnytimeTabletAndroidSettingsViewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AnytimeTabletAndroidSettingsViewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletAndroidSettingsViewBinding) bind(obj, view, R.layout.anytime_tablet_android_settings_view);
    }
}
