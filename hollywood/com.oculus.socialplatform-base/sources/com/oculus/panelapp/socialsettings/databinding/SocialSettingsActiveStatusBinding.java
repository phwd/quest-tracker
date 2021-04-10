package com.oculus.panelapp.socialsettings.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import com.oculus.ocui.OCLink;
import com.oculus.ocui.OCSpinner;
import com.oculus.ocui.OCTextView;
import com.oculus.ocui.OCToggle;
import com.oculus.panelapp.socialsettings.views.SocialSettingsActiveStatusViewModel;
import com.oculus.socialplatform.R;

public abstract class SocialSettingsActiveStatusBinding extends AnonymousClass1uW {
    @NonNull
    public final OCLink activeStatusBody;
    @NonNull
    public final OCTextView activeStatusDescription;
    @NonNull
    public final OCTextView activeStatusHeader;
    @NonNull
    public final OCSpinner activeStatusLoadingSpinner;
    @NonNull
    public final OCToggle activeStatusToggle;
    @NonNull
    public final RelativeLayout activeStatusToggleView;
    @Bindable
    public SocialSettingsActiveStatusViewModel mViewModel;

    public abstract void setViewModel(@Nullable SocialSettingsActiveStatusViewModel socialSettingsActiveStatusViewModel);

    @Nullable
    public SocialSettingsActiveStatusViewModel getViewModel() {
        return this.mViewModel;
    }

    public SocialSettingsActiveStatusBinding(Object obj, View view, int i, OCLink oCLink, OCTextView oCTextView, OCTextView oCTextView2, OCSpinner oCSpinner, OCToggle oCToggle, RelativeLayout relativeLayout) {
        super(obj, view, i);
        this.activeStatusBody = oCLink;
        this.activeStatusDescription = oCTextView;
        this.activeStatusHeader = oCTextView2;
        this.activeStatusLoadingSpinner = oCSpinner;
        this.activeStatusToggle = oCToggle;
        this.activeStatusToggleView = relativeLayout;
    }

    public static SocialSettingsActiveStatusBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static SocialSettingsActiveStatusBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SocialSettingsActiveStatusBinding) AnonymousClass1uW.bind(obj, view, R.layout.social_settings_active_status);
    }

    @NonNull
    public static SocialSettingsActiveStatusBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static SocialSettingsActiveStatusBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static SocialSettingsActiveStatusBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (SocialSettingsActiveStatusBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.social_settings_active_status, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static SocialSettingsActiveStatusBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (SocialSettingsActiveStatusBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.social_settings_active_status, null, false);
    }
}
