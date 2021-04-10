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
import com.oculus.ocui.OCLink;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsDescriptiveText;

public abstract class AnytimeTabletAndroidSettingsDescriptiveTextBinding extends ViewDataBinding {
    @NonNull
    public final OCLink body;
    @NonNull
    public final OCButton button;
    @NonNull
    public final OCTextView header;
    @Bindable
    protected SettingsDescriptiveText mDescription;

    public abstract void setDescription(@Nullable SettingsDescriptiveText settingsDescriptiveText);

    protected AnytimeTabletAndroidSettingsDescriptiveTextBinding(Object obj, View view, int i, OCLink oCLink, OCButton oCButton, OCTextView oCTextView) {
        super(obj, view, i);
        this.body = oCLink;
        this.button = oCButton;
        this.header = oCTextView;
    }

    @Nullable
    public SettingsDescriptiveText getDescription() {
        return this.mDescription;
    }

    @NonNull
    public static AnytimeTabletAndroidSettingsDescriptiveTextBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletAndroidSettingsDescriptiveTextBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AnytimeTabletAndroidSettingsDescriptiveTextBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_android_settings_descriptive_text, viewGroup, z, obj);
    }

    @NonNull
    public static AnytimeTabletAndroidSettingsDescriptiveTextBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletAndroidSettingsDescriptiveTextBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AnytimeTabletAndroidSettingsDescriptiveTextBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_android_settings_descriptive_text, null, false, obj);
    }

    public static AnytimeTabletAndroidSettingsDescriptiveTextBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AnytimeTabletAndroidSettingsDescriptiveTextBinding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletAndroidSettingsDescriptiveTextBinding) bind(obj, view, R.layout.anytime_tablet_android_settings_descriptive_text);
    }
}
