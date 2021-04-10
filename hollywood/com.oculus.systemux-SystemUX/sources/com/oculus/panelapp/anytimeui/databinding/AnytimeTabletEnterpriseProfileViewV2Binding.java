package com.oculus.panelapp.anytimeui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.tablet.profile.enterprise.EnterpriseProfileViewModel;

public abstract class AnytimeTabletEnterpriseProfileViewV2Binding extends ViewDataBinding {
    @NonNull
    public final AnytimeTabletEnterpriseProfileAdminKeypadBinding enterpriseProfileAdminKeypad;
    @NonNull
    public final OCButton enterpriseProfileCheckForUpdatesButton;
    @NonNull
    public final OCButton enterpriseProfileCheckingForUpdatesButton;
    @NonNull
    public final ImageView enterpriseProfileCompanyLogo;
    @NonNull
    public final OCTextView enterpriseProfileCompanyNameTitle;
    @NonNull
    public final OCTextView enterpriseProfileConfigurationTitle;
    @NonNull
    public final OCButton enterpriseProfileEnterAdminModeButton;
    @NonNull
    public final OCTextView enterpriseProfileEnterpriseProfileAdminModeInfo;
    @NonNull
    public final ImageView enterpriseProfileEnterpriseProfileAdminModeInfoIcon;
    @NonNull
    public final OCButton enterpriseProfileExitAdminModeButton;
    @NonNull
    public final OCTextView enterpriseProfileLastSyncTimeSubtitle;
    @NonNull
    public final OCTextView enterpriseProfileLastUpdateTimeSubtitle;
    @NonNull
    public final OCTextView enterpriseProfileLicenseSubtitle;
    @NonNull
    public final OCTextView enterpriseProfileLicenseTitle;
    @NonNull
    public final OCTextView enterpriseProfileTabletTitle;
    @Bindable
    protected EnterpriseProfileViewModel mViewModel;

    public abstract void setViewModel(@Nullable EnterpriseProfileViewModel enterpriseProfileViewModel);

    protected AnytimeTabletEnterpriseProfileViewV2Binding(Object obj, View view, int i, AnytimeTabletEnterpriseProfileAdminKeypadBinding anytimeTabletEnterpriseProfileAdminKeypadBinding, OCButton oCButton, OCButton oCButton2, ImageView imageView, OCTextView oCTextView, OCTextView oCTextView2, OCButton oCButton3, OCTextView oCTextView3, ImageView imageView2, OCButton oCButton4, OCTextView oCTextView4, OCTextView oCTextView5, OCTextView oCTextView6, OCTextView oCTextView7, OCTextView oCTextView8) {
        super(obj, view, i);
        this.enterpriseProfileAdminKeypad = anytimeTabletEnterpriseProfileAdminKeypadBinding;
        setContainedBinding(this.enterpriseProfileAdminKeypad);
        this.enterpriseProfileCheckForUpdatesButton = oCButton;
        this.enterpriseProfileCheckingForUpdatesButton = oCButton2;
        this.enterpriseProfileCompanyLogo = imageView;
        this.enterpriseProfileCompanyNameTitle = oCTextView;
        this.enterpriseProfileConfigurationTitle = oCTextView2;
        this.enterpriseProfileEnterAdminModeButton = oCButton3;
        this.enterpriseProfileEnterpriseProfileAdminModeInfo = oCTextView3;
        this.enterpriseProfileEnterpriseProfileAdminModeInfoIcon = imageView2;
        this.enterpriseProfileExitAdminModeButton = oCButton4;
        this.enterpriseProfileLastSyncTimeSubtitle = oCTextView4;
        this.enterpriseProfileLastUpdateTimeSubtitle = oCTextView5;
        this.enterpriseProfileLicenseSubtitle = oCTextView6;
        this.enterpriseProfileLicenseTitle = oCTextView7;
        this.enterpriseProfileTabletTitle = oCTextView8;
    }

    @Nullable
    public EnterpriseProfileViewModel getViewModel() {
        return this.mViewModel;
    }

    @NonNull
    public static AnytimeTabletEnterpriseProfileViewV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletEnterpriseProfileViewV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AnytimeTabletEnterpriseProfileViewV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_enterprise_profile_view_v2, viewGroup, z, obj);
    }

    @NonNull
    public static AnytimeTabletEnterpriseProfileViewV2Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletEnterpriseProfileViewV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AnytimeTabletEnterpriseProfileViewV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_enterprise_profile_view_v2, null, false, obj);
    }

    public static AnytimeTabletEnterpriseProfileViewV2Binding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AnytimeTabletEnterpriseProfileViewV2Binding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletEnterpriseProfileViewV2Binding) bind(obj, view, R.layout.anytime_tablet_enterprise_profile_view_v2);
    }
}
